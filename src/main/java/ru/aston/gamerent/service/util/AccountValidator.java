package ru.aston.gamerent.service.util;

import org.springframework.stereotype.Service;
import ru.aston.gamerent.model.entity.Account;

import java.time.LocalDateTime;

@Service
public class AccountValidator {

    public boolean validateAccountExpiration(Account account) {
        return account.getExpirationTime().isBefore(LocalDateTime.now()) &&
                account.getExpirationTime().isAfter(account.getUpdateTime());
    }
}
