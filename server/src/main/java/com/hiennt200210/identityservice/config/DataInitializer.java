package com.hiennt200210.identityservice.config;

import com.hiennt200210.identityservice.entity.Role;
import com.hiennt200210.identityservice.entity.User;
import com.hiennt200210.identityservice.enums.Gender;
import com.hiennt200210.identityservice.repository.RoleRepository;
import com.hiennt200210.identityservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    public DataInitializer(PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Create roles if they do not exist
        if (roleRepository.findByName("ADMIN") == null) {
            Role admin = new Role();
            admin.setName("ADMIN");
            roleRepository.save(admin);
        }

        if (roleRepository.findByName("USER") == null) {
            Role user = new Role();
            user.setName("USER");
            roleRepository.save(user);
        }

        // Create admin user if it does not exist
        if (!userRepository.existsByUsername("admin")) {
            Role adminRole = roleRepository.findByName("ADMIN");
            Role userRole = roleRepository.findByName("USER");

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);
            roles.add(userRole);

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("Admin123!"));
            admin.setFirstName("Admin");
            admin.setLastName("Admin");
            admin.setGender(Gender.OTHER);
            admin.setDateOfBirth(LocalDate.now());
            admin.setEmail("admin@email.com");
            admin.setRoles(roles);

            userRepository.save(admin);
        }
    }
}
