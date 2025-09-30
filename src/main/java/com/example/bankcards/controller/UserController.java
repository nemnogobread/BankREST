package com.example.bankcards.controller;

import com.example.bankcards.dto.requests.BecomeAdminRequest;
import com.example.bankcards.dto.responses.BecomeAdminResponse;
import com.example.bankcards.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping(path = "/become-admin", consumes = "application/json", produces = "application/json")
    public ResponseEntity<BecomeAdminResponse> becomeAdmin(
            @RequestBody BecomeAdminRequest request
    ) {
        return ResponseEntity.ok(userService.makeAdmin(request));
    }
}
