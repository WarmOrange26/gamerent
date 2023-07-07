package ru.aston.gamerent.service;

import java.util.UUID;

public interface EmailService {
    String sendRegistrationMail(String username, String email, String password, UUID token);
}