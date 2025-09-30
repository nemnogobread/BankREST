package com.example.bankcards.service;

import com.example.bankcards.dto.requests.BecomeAdminRequest;
import com.example.bankcards.dto.responses.BecomeAdminResponse;
import com.example.bankcards.entity.Role;
import com.example.bankcards.entity.User;
import com.example.bankcards.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final String ADMIN_SECRET_KEY = "1f37765384332a648644593dc8ee12f1";
    private final UserRepository userRepository;

    public BecomeAdminResponse makeAdmin(BecomeAdminRequest request) {
        Optional<User> user = userRepository.findByEmail(request.getUsername());
        if (user.isEmpty()) {
            throw new EntityNotFoundException("User with this email does not exist!");
        } else if (user.get().getRole() == Role.ADMIN) {
            throw new RuntimeException("You are already admin!");
        } else if (!Objects.equals(request.getAdminPassword(), ADMIN_SECRET_KEY)) {
            throw new SecurityException("Password is wrong!");
        }

        user.get().setRole(Role.ADMIN);
        userRepository.save(user.get());

        return BecomeAdminResponse.builder()
                .message("You are admin now!")
                .build();
    }
}
