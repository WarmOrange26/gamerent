package ru.aston.gamerent.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.aston.gamerent.exception.NoEntityException;
import ru.aston.gamerent.exception.NotEnoughMoneyException;
import ru.aston.gamerent.generator.DtoGenerator;
import ru.aston.gamerent.generator.EntityGenerator;
import ru.aston.gamerent.mapper.AccountMapper;
import ru.aston.gamerent.model.dto.request.AccountRequestDto;
import ru.aston.gamerent.model.dto.request.OrderRequestDto;
import ru.aston.gamerent.model.dto.response.AccountResponseInfoDto;
import ru.aston.gamerent.model.dto.response.ActiveAccountResponseDto;
import ru.aston.gamerent.model.entity.Account;
import ru.aston.gamerent.model.entity.Game;
import ru.aston.gamerent.model.entity.Platform;
import ru.aston.gamerent.model.entity.User;
import ru.aston.gamerent.model.entity.Wallet;
import ru.aston.gamerent.repository.AccountRepository;
import ru.aston.gamerent.repository.GameRepository;
import ru.aston.gamerent.repository.OrderAccountRepository;
import ru.aston.gamerent.repository.OrderRepository;
import ru.aston.gamerent.repository.PlatformRepository;
import ru.aston.gamerent.repository.UserRepository;
import ru.aston.gamerent.repository.WalletRepository;
import ru.aston.gamerent.util.AccountValidator;
import ru.aston.gamerent.util.BankApiConnector;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private PlatformRepository platformRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderAccountRepository orderAccountRepository;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private WalletRepository walletRepository;

    @Mock
    private AccountValidator accountValidator;

    @Mock
    private BankApiConnector bankApiConnector;

    @Mock
    private AccountMapper accountMapper;

    @InjectMocks
    private AccountServiceImpl accountService;

    private User user;
    private Wallet wallet;
    private Account account;
    private Game game;
    private ActiveAccountResponseDto accountResponseDto;
    private OrderRequestDto orderRequestDto;
    private List<Game> games;
    private Platform platform;
    private AccountResponseInfoDto accountResponseInfoDto;
    private AccountRequestDto accountRequestDto;

    @BeforeEach
    void setup() {
        EntityGenerator entityGenerator = new EntityGenerator();
        DtoGenerator dtoGenerator = new DtoGenerator();
        user = entityGenerator.getUser();
        wallet = entityGenerator.getWallet();
        account = entityGenerator.getAccount();
        game = entityGenerator.getGame1();
        games = entityGenerator.getGames();
        accountResponseDto = dtoGenerator.getAccountResponseDto();
        orderRequestDto = dtoGenerator.getOrderRequestDto();
        accountResponseInfoDto = dtoGenerator.getAccountResponseInfoDto();
        accountRequestDto = dtoGenerator.getAccountRequestDto();
        platform = entityGenerator.getPlatform();
    }

    @Test
    void blockAccounts() {
        when(accountRepository.findAll()).thenReturn(Collections.emptyList());
        accountService.blockAccounts();

        verify(accountRepository, times(1)).findAll();
    }

    @Test
    void buyAccount() {
        when(userRepository.findById(orderRequestDto.userId())).thenReturn(Optional.ofNullable(user));
        when(gameRepository.findAllById(orderRequestDto.gameIds())).thenReturn(games);
        when(walletRepository.findById(wallet.getId())).thenReturn(Optional.ofNullable(wallet));
        when(bankApiConnector.getCurrencyValue(wallet.getCurrency())).thenReturn(wallet.getValue());
        lenient().when(accountMapper.accountToActiveAccountResponse(account)).thenReturn(accountResponseDto);

        assertThat(accountService.buyAccount(orderRequestDto).get(0).login())
                .isEqualTo(accountResponseDto.login());
    }

    @Test
    void buyAccountThrowExceptionUserRepo() {
        when(userRepository.findById(orderRequestDto.userId())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> accountService.buyAccount(orderRequestDto))
                .isInstanceOf(NoEntityException.class)
                .hasMessage("User with id " + orderRequestDto.userId() + " was not found");
    }

    @Test
    void buyAccountThrowExceptionGameRepo() {
        game.setAccounts(Collections.emptySet());
        when(userRepository.findById(orderRequestDto.userId())).thenReturn(Optional.ofNullable(user));
        when(gameRepository.findAllById(orderRequestDto.gameIds())).thenReturn(games);

        assertThatThrownBy(() -> accountService.buyAccount(orderRequestDto))
                .isInstanceOf(NoEntityException.class)
                .hasMessage("Accounts were not found");
    }

    @Test
    void buyAccountThrowExceptionWalletRepo() {
        when(userRepository.findById(orderRequestDto.userId())).thenReturn(Optional.ofNullable(user));
        when(gameRepository.findAllById(orderRequestDto.gameIds())).thenReturn(games);
        when(walletRepository.findById(wallet.getId())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> accountService.buyAccount(orderRequestDto))
                .isInstanceOf(NoEntityException.class)
                .hasMessage("Wallet with id " + wallet.getId() + " was not found");
    }

    @Test
    void buyAccountNotEnoughMoney() {
        wallet.setValue(new BigDecimal("0.5"));
        when(userRepository.findById(orderRequestDto.userId())).thenReturn(Optional.ofNullable(user));
        when(gameRepository.findAllById(orderRequestDto.gameIds())).thenReturn(games);
        when(walletRepository.findById(wallet.getId())).thenReturn(Optional.ofNullable(wallet));
        when(bankApiConnector.getCurrencyValue(wallet.getCurrency())).thenReturn(wallet.getValue());

        assertThatThrownBy(() -> accountService.buyAccount(orderRequestDto))
                .isInstanceOf(NotEnoughMoneyException.class)
                .hasMessage("We have no money at wallet with id " + wallet.getId());
    }

    @Test
    void getAccountById() {
        when(accountRepository.findById(account.getId())).thenReturn(Optional.ofNullable(account));

        assertThat(accountService.getAccountById(account.getId())).isEqualTo(account);
    }

    @Test
    void getAccountByIdThrowException() {
        when(accountRepository.findById(account.getId())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> accountService.getAccountById(account.getId()))
                .isInstanceOf(NoEntityException.class)
                .hasMessage("Account with this id doesn't exists");
    }

    @Test
    void findByGameId() {
        when(accountRepository.findByGameId(game.getId())).thenReturn(List.of(account));

        assertThat(accountService.findByGameId(game.getId())).isEqualTo(List.of(account));
    }

    @Test
    void numberOfAvailableAccounts() {
        when(accountRepository.findByGameId(game.getId())).thenReturn(List.of(account));

        assertThat(accountService.numberOfAvailableAccounts(game.getId())).isEqualTo(game.getAccounts().size());
    }

    @Test
    void shouldSaveAccount() {
        when(accountMapper.accountRequestDtoToAccount(accountRequestDto)).thenReturn(account);
        when(platformRepository.findByName(platform.getName())).thenReturn(platform);
        when(accountRepository.save(account)).thenReturn(account);
        when(accountMapper.accountToAccountResponseInfoDto(account)).thenReturn(accountResponseInfoDto);
        AccountResponseInfoDto savedAccount = accountService.saveAccount(accountRequestDto);
        assertEquals(accountRequestDto.login(), savedAccount.login());
        assertEquals(accountResponseInfoDto.login(), savedAccount.login());
        verify(accountRepository, times(1)).save(account);
    }
}