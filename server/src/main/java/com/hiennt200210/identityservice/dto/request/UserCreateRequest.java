package com.hiennt200210.identityservice.dto.request;

import com.hiennt200210.identityservice.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {

    @NotBlank(message = "{username.required}")
    @Size(min = 1, max = 50, message = "{username.size}")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "{username.pattern}")
    String username;

    @NotBlank(message = "{password.required}")
    @Size(min = 8, max = 255, message = "{password.size}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "{password.pattern}")
    String password;

    @NotBlank(message = "{firstName.required}")
    @Size(min = 1, max = 100, message = "{firstName.size}")
    String firstName;

    @NotBlank(message = "{lastName.required}")
    @Size(min = 1, max = 100, message = "{lastName.size}")
    String lastName;

    @NotNull(message = "{gender.required}")
    Gender gender;

    @NotNull(message = "{dateOfBirth.required}")
    @Past(message = "{dateOfBirth.past}")
    LocalDate dateOfBirth;

    @NotBlank(message = "{email.required}")
    @Email(message = "{email.invalidFormat}")
    @Size(max = 255, message = "{email.size}")
    String email;

}
