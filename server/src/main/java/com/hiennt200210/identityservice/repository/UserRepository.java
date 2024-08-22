package com.hiennt200210.identityservice.repository;

import com.hiennt200210.identityservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
