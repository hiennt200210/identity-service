package com.hiennt200210.identityservice.dto.response;

import com.hiennt200210.identityservice.enums.Gender;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

    String userId;
    String username;
    String firstName;
    String lastName;
    Gender gender;
    LocalDate dateOfBirth;
    String email;

}
