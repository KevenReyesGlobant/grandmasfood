package org.grandmasfood.springcloud.users.infrastructure.adapters.config;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import org.grandmasfood.springcloud.users.domain.model.User;
import org.grandmasfood.springcloud.users.infrastructure.adapters.output.mapper.UserMapper;
import org.grandmasfood.springcloud.users.infrastructure.adapters.output.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class EmailSender {
    private static final Logger logger = LoggerFactory.getLogger(EmailSender.class);

    @Value("http://localhost:3001/api/v1/user")
    private String baseUrl;

    @Value("${spring.mail.username}")
    private String emailFrom;

    private final JavaMailSender mail;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public EmailSender(JavaMailSender mail, UserRepository userRepository, UserMapper userMapper) {
        this.mail = mail;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void sendValidateEmail(User user) {
        logger.info("Initiating email validation process for: {}", user.getEmail());
        logger.debug("User details: {}", user);

        if (user.getIdUser() == null) {
            logger.error("User ID is null, cannot send verification email.");
            throw new IllegalArgumentException("User ID must not be null");
        }

        try {
            userRepository.findById(user.getIdUser()).ifPresent(existingUser -> {
                String token = UUID.randomUUID().toString();
                logger.debug("Generated verification token: {}", token);

                existingUser.setVerification(token);
                existingUser.setTokenExpiry(LocalDateTime.now().plusDays(1));
                userRepository.save(existingUser);
                logger.debug("User updated with verification token: {}", existingUser);

                sendEmail(user.getEmail(), token);
                logger.info("Verification email sent successfully");
            });
        } catch (Exception e) {
            logger.error("Failed to send verification email: ", e);
            throw new RuntimeException("Error sending verification email", e);
        }
    }

    private void sendEmail(String to, String token) {
        logger.debug("Building verification email for: {}", to);

        String verificationLink = baseUrl + "/verify?token=" + token;
        logger.debug("Verification link generated: {}", verificationLink);

        MimeMessage message = mail.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(emailFrom);
            helper.setTo(to);
            helper.setSubject("Verify your account");
            helper.setText(buildEmailContent(verificationLink), true);

            logger.debug("Attempting to send verification email...");
            mail.send(message);
            logger.debug("Verification email sent successfully");

        } catch (MessagingException e) {
            logger.error("Failed to send verification email: ", e);
            throw new RuntimeException("Error sending verification email: " + e.getMessage(), e);
        }
    }

    private String buildEmailContent(String verificationLink) {
        return """
                <html>
                <head>
                    <style>
                        .button {
                            background-color: rgb(175, 91, 76);
                            border: none;
                            color: white;
                            padding: 15px 32px;
                            text-align: center;
                            text-decoration: none;
                            display: inline-block;
                            font-size: 16px;
                            margin: 4px 2px;
                            cursor: pointer;
                            border-radius: 4px;
                        }
                        .container {
                            font-family: Arial, sans-serif;
                            max-width: 600px;
                            margin: 0 auto;
                            padding: 20px;
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <h2>Account verification</h2>
                        <p>To verify your account, click on the following button: </p>
                        <a href='%s' class='button'>Verify account</a>
                        <p>If the button doesn't work, copy and paste this link into your browser:</p>
                        <p>%s</p>
                        <p>This link will expire in 24 hours.</p>
                    </div>
                </body>
                </html>
                """.formatted(verificationLink, verificationLink);
    }

    public boolean verifyEmail(String token) {
        logger.debug("Attempting to verify token: {}", token);

        return userRepository.findByVerification(token)
                .filter(user -> {
                    boolean isValid = !user.getEmailVerified() &&
                            user.getTokenExpiry().isAfter(LocalDateTime.now());

                    if (!isValid) {
                        logger.warn("Invalid verification attempt for token: {}. Already verified: {}, Token expired: {}",
                                token,
                                user.getEmailVerified(),
                                !user.getTokenExpiry().isAfter(LocalDateTime.now()));
                    }

                    return isValid;
                })
                .map(user -> {
                    try {
                        user.verifyEmail();
                        user.setActive(true);
                        user.setVerification(null);
                        userRepository.save(user);
                        logger.info("Email successfully verified for user: {}", user.getEmail());
                        return true;
                    } catch (Exception e) {
                        logger.error("Error during email verification for user: {}", user.getEmail(), e);
                        return false;
                    }
                })
                .orElseGet(() -> {
                    logger.warn("No user found with verification token: {}", token);
                    return false;
                });
    }
}