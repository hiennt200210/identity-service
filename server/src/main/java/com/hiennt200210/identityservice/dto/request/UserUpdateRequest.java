package com.hiennt200210.identityservice.dto.request;

import com.hiennt200210.identityservice.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {

    @NotBlank(message = "{password.required}")
    @Size(min = 8, max = 255, message = "{password.size}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
             message = "{password.pattern}")
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

}
