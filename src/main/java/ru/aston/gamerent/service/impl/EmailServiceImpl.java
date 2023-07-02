package ru.aston.gamerent.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.aston.gamerent.service.EmailService;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public String sendRegistrationMail(String username, String email, String password) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(email);
            mailMessage.setText(String.format("""
                            Good day!
                            You have successfully registered in WARM online gamerent shop.
                            You username: %s
                            E-mail: %s
                            Password: %s
                            """, username, email, password)
                    );
            mailMessage.setSubject("WARM online gamerent shop registration");
            javaMailSender.send(mailMessage);
            log.info("A letter with registration data is sent to the e-mail: {}", email);
            return "A letter with registration data is sent to the e-mail specified during registration." +
                    " You can now use these details to log into the site";
        } catch (Exception e) {
            log.warn("Аn error occurred during email sending for e-mail: {} with error message: {}", email, e.getMessage());
            return "Аn error occurred during email sending!";
        }
    }
}
