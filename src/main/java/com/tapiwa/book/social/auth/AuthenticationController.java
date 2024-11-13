package com.tapiwa.book.social.auth;

import com.tapiwa.book.social.custom.HttpResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Authentication", description = "Authentication API")
@RequestMapping("/auth")
public class AuthenticationController {

    private  final  AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<HttpResponse> registerUser(@RequestBody @Valid RegistrationRequest request) {

        log.info("Registering user {}", request);
        authenticationService.registerUser(request);

        HttpResponse response = HttpResponse.builder()
                .httpStatusCode(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .httpStatusMessage("User registered successfully")
                .body(Map.of("user",request ))
                .build();
        return ResponseEntity.ok(response);
    }

}
