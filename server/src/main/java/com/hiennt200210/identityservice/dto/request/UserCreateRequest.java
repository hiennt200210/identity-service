package com.hiennt200210.identityservice.dto.request;

import com.hiennt200210.identityservice.enums.Gender;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class UserCreateRequest {

    @NotBlank(message = "{username.required}")
    @Size(min = 1, max = 50, message = "{username.size}")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "{username.pattern}")
    private String username;

    @NotBlank(message = "{password.required}")
    @Size(min = 8, max = 255, message = "{password.size}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "{password.pattern}")
    private String password;

    @NotBlank(message = "{firstName.required}")
    @Size(min = 1, max = 100, message = "{firstName.size}")
    private String firstName;

    @NotBlank(message = "{lastName.required}")
    @Size(min = 1, max = 100, message = "{lastName.size}")
    private String lastName;

    @NotNull(message = "{gender.required}")
    private Gender gender;

    @NotNull(message = "{dateOfBirth.required}")
    @Past(message = "{dateOfBirth.past}")
    private LocalDate dateOfBirth;

    @NotBlank(message = "{email.required}")
    @Email(message = "{email.invalidFormat}")
    @Size(max = 255, message = "{email.size}")
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
