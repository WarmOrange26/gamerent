package ru.aston.gamerent.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aston.gamerent.model.entity.Account;
import ru.aston.gamerent.repository.AccountRepository;
import ru.aston.gamerent.service.AccountService;

import java.time.LocalDateTime;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final Random random = new Random();

    @Override
    @Scheduled(cron = "${game-rent.scheduling.account-blocking}")
    @Transactional
    public void blockAccounts() {
        accountRepository.findAll().stream()
                .filter(account -> account.getExpirationTime().isBefore(LocalDateTime.now()) &&
                        account.getExpirationTime().isAfter(account.getUpdateTime()))
                .forEach(this::changeAccountPassword);
    }

    private void changeAccountPassword(Account account) {
        try {
            String newPassword = generateNewPassword();
            //some code that connect to account platform api and set new password
            log.info("connection to " + account.getPlatform().getName() + " api and set password: " + newPassword);
            account.setPassword(newPassword);
            account.setUpdateTime(LocalDateTime.now());
            accountRepository.save(account);
        } catch (RuntimeException e) {
            log.warn("connection to " + account.getPlatform().getName() + " was failed");
        }
    }

    private String generateNewPassword() {
        RandomStringGenerator randomStringGenerator = new RandomStringGenerator.Builder()
                .withinRange(33, 122)
                .build();
        return randomStringGenerator.generate(random.nextInt(20) + 25);
    }
}
