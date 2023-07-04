package ru.aston.gamerent.service;

public interface EmailService {
    String sendRegistrationMail(String username, String email, String password);
}