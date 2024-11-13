package com.tapiwa.book.social.auth;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class RegistrationRequest {

    @NotEmpty(message = "First name is required")
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotEmpty(message = "Last name is required")
    @NotBlank(message = "Last name is required")
    private String lastName;

    @Email(message = "Email is not a valid email")
    private String email;

    @NotEmpty(message = "Password is required")
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
}
