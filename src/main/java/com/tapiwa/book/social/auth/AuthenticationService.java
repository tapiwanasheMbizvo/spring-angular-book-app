package com.tapiwa.book.social.auth;

import com.tapiwa.book.social.messenger.EmailService;
import com.tapiwa.book.social.messenger.EmailTemplate;
import com.tapiwa.book.social.role.RoleRepository;
import com.tapiwa.book.social.user.Token;
import com.tapiwa.book.social.user.TokenRepository;
import com.tapiwa.book.social.user.User;
import com.tapiwa.book.social.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private  final RoleRepository roleRepository;
    private  final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;

    private final EmailService emailService;
    public void registerUser(RegistrationRequest request) {

        var userRole = roleRepository.findByName("USER").orElseThrow(() -> new RuntimeException("User role not found"));
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .enabled(false)
                .locked(true)
                .roles(List.of(userRole))
                .build();

        userRepository.save(user);
        sendValidationEmail(user);

    }

    private void sendValidationEmail(User user)  {

        var newToken = generateAndSaveToken(user);

        emailService.sendEmail(user.getUsername(), EmailTemplate.ACTIVATE_ACCOUNT,
                user.fullName(),
                "http://localhost:8080/auth/confirm?token=" + newToken,
                newToken, "Account Activation Email");

    }

    private String generateAndSaveToken(User user) {
        var generatedToken = generateActivationCode(6);
        var token = Token.builder()
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .token(generatedToken)
                .user(user)
                .build();

        tokenRepository.save(token);
        return generatedToken;
    }

    private String generateActivationCode(int length) {

        String chars = "1234567890";

        StringBuilder code = new StringBuilder();

        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            code.append(chars.charAt(random.nextInt(chars.length())));
        }

        return code.toString();
    }
}
