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
import ru.aston.gamerent.model.entity.Account;
import ru.aston.gamerent.model.entity.Order;
import ru.aston.gamerent.model.entity.OrdersAccount;
import ru.aston.gamerent.model.entity.User;
import ru.aston.gamerent.model.entity.Wallet;
import ru.aston.gamerent.repository.AccountRepository;
import ru.aston.gamerent.repository.GameRepository;
import ru.aston.gamerent.repository.OrderAccountRepository;
import ru.aston.gamerent.repository.OrderRepository;
import ru.aston.gamerent.repository.UserRepository;
import ru.aston.gamerent.repository.WalletRepository;
import ru.aston.gamerent.service.AccountService;
import ru.aston.gamerent.util.AccountValidator;
import ru.aston.gamerent.util.BankApiConnector;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

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
    private final BankApiConnector bankApiConnector;
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

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new NoEntityException("Account with this id doesn't exists"));
    }

    @Override
    public List<Account> findByGameId(Long gameId) {
        return accountRepository.findByGameId(gameId);
    }

    @Override
    public int numberOfAvailableAccounts(Long gameId) {
        return accountRepository.findByGameId(gameId).stream()
                .filter(account -> account.getExpirationTime().isBefore(LocalDateTime.now()))
                .toList()
                .size();
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

        BigDecimal currencyRate = bankApiConnector.getCurrencyValue(wallet.getCurrency());
        BigDecimal costInRoubles = cost.divide(currencyRate, RoundingMode.DOWN);

        if (wallet.getValue().compareTo(costInRoubles) < 0) {
            throw new NotEnoughMoneyException("We have no money at wallet with id " + wallet.getId());
        }

        wallet.setValue(wallet.getValue().subtract(cost));
        walletRepository.save(wallet);
    }

    private void savePaymentData(List<Account> accounts, User player, Integer periods) {
        Order order = Order.builder()
                .paymentTime(LocalDateTime.now())
                .user(player)
                .build();
        orderRepository.save(order);

        accounts.stream()
                .map(account -> saveAccount(account, periods))
                .map(account -> createOrdersAccount(account, order, periods))
                .forEach(orderAccountRepository::save);
    }

    private Account saveAccount(Account account, Integer periods) {
        account.setExpirationTime(LocalDateTime.now().plusDays(periods));
        account.setUpdateTime(LocalDateTime.now());

        return accountRepository.save(account);
    }

    private OrdersAccount createOrdersAccount(Account account, Order order, Integer periods) {
        return OrdersAccount.builder()
                .account(account)
                .order(order)
                .periods(periods)
                .build();
    }

    private List<ActiveAccountResponseDto> getAccountsPasswords(List<Account> accounts) {
        return accounts.stream()
                .map(accountMapper::accountToActiveAccountResponse)
                .toList();
    }
}