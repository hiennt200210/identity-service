package com.hiennt200210.identityservice.repository;

import com.hiennt200210.identityservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
