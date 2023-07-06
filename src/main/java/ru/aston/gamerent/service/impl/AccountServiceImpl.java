package ru.aston.gamerent.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aston.gamerent.exception.NoEntityException;
import ru.aston.gamerent.exception.NotEnoughMoneyException;
import ru.aston.gamerent.exception.PlatformApiConnectionException;
import ru.aston.gamerent.mapper.AccountMapper;
import ru.aston.gamerent.model.dto.request.OrderRequestDto;
import ru.aston.gamerent.model.dto.response.ActiveAccountResponseDto;
import ru.aston.gamerent.model.entity.*;
import ru.aston.gamerent.repository.*;
import ru.aston.gamerent.service.AccountService;
import ru.aston.gamerent.util.AccountValidator;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderAccountRepository orderAccountRepository;
    private final GameRepository gameRepository;
    private final WalletRepository walletRepository;
    private final AccountValidator accountValidator;
    private final AccountMapper accountMapper;
    private final Random random = new Random();

    @Override
    @Scheduled(cron = "${game-rent.scheduling.account-blocking}")
    @Transactional
    public void blockAccounts() {
        accountRepository.findAll().stream()
                .filter(accountValidator::validateAccountExpiration)
                .forEach(this::changeAccountPassword);
    }

    private void changeAccountPassword(Account account) {
        try {
            String newPassword = generateNewPassword();
            //some code that connect to account platform api and set new password
            log.info("connection to " + account.getPlatform().getName() + " api and set password: " + newPassword);
            saveNewPassword(account, newPassword);
        } catch (PlatformApiConnectionException e) {
            log.warn("connection to " + account.getPlatform().getName() + " was failed");
        }
    }

    private String generateNewPassword() {
        return new RandomStringGenerator.Builder()
                .withinRange(33, 122)
                .build()
                .generate(random.nextInt(20) + 25);
    }

    private void saveNewPassword(Account account, String newPassword) {
        account.setPassword(newPassword);
        account.setUpdateTime(LocalDateTime.now());
        accountRepository.save(account);
    }

    @Override
    @Transactional
    public List<ActiveAccountResponseDto> buyAccount(OrderRequestDto orderRequestDto) {
        User user = userRepository.findById(orderRequestDto.userId())
                .orElseThrow(() -> new NoEntityException("User with id " + orderRequestDto.userId() + " was not found"));

        List<Account> accounts = getAccounts(orderRequestDto.gameIds());
        BigDecimal gamesCost = getTotalCost(accounts, orderRequestDto.periods());

        executePayment(gamesCost, orderRequestDto.walletId());
        savePaymentData(accounts, user, orderRequestDto.periods());

        return getAccountsPasswords(accounts);
    }

    private List<Account> getAccounts(List<Long> gameIds) {
        return gameRepository.findAllById(gameIds).stream()
                .map(game -> game.getAccounts().stream()
                        .filter(account -> account.getExpirationTime().isBefore(LocalDateTime.now()))
                        .findFirst()
                        .orElseThrow(() -> new NoEntityException("Accounts were not found")))
                .toList();
    }

    private BigDecimal getTotalCost(List<Account> accounts, Integer periods) {
        return accounts.stream()
                .flatMap(account -> account.getAccountsGame().stream())
                .map(accountsGames -> accountsGames.getGame().getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .multiply(BigDecimal.valueOf(periods));
    }

    private void executePayment(BigDecimal cost, Long walletId) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new NoEntityException("Wallet with id " + walletId + " was not found"));

        if (wallet.getValue().compareTo(cost) < 0) {
            throw new NotEnoughMoneyException("We have no money at wallet with id " + wallet.getId());
        }

        wallet.setValue(wallet.getValue().subtract(cost));
        walletRepository.save(wallet);
    }

    private void savePaymentData(List<Account> accounts, User player, Integer periods) {
        Order order = new Order();
        order.setPaymentTime(LocalDateTime.now());
        order.setUser(player);
        orderRepository.save(order);

        accounts.forEach(account -> {
            account.setExpirationTime(LocalDateTime.now().plusDays(periods));
            account.setUpdateTime(LocalDateTime.now());
            OrdersAccount ordersAccount = new OrdersAccount();
            ordersAccount.setAccount(accountRepository.save(account));
            ordersAccount.setOrder(order);
            ordersAccount.setPeriods(periods);
            orderAccountRepository.save(ordersAccount);
        });
    }

    private List<ActiveAccountResponseDto> getAccountsPasswords(List<Account> accounts) {
        return accounts.stream()
                .map(accountMapper::accountToActiveAccountResponse)
                .toList();
    }
    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(( ) -> new NoEntityException("Account with this id doesn't exists"));
    }
    @Override
    public List<Account> findByGameId(Long gameId) {
        return accountRepository.findByGameId(gameId);
    }
    @Override
    public List<Account> selectAvailableAccounts(Long gameId) {
        return findByGameId(gameId).stream().filter(account -> account.getExpirationTime().isBefore(LocalDateTime.now()))
                .collect(Collectors.toList());
    }
    @Override
    public int numberOfAvailableAccounts(Long gameId) {
        return selectAvailableAccounts(gameId).size();
    }
}