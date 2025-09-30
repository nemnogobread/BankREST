package com.example.bankcards.controller;

import com.example.bankcards.service.BankCardService;
import com.example.bankcards.dto.responses.BankCardResponse;
import com.example.bankcards.dto.requests.BankCardCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class BankCardController {
    private final BankCardService bankCardService;

    @Secured("ROLE_ADMIN")
    @PostMapping("/create")
    public ResponseEntity<BankCardResponse> createCard(
            @RequestBody BankCardCreateRequest request
    ){
        return ResponseEntity.ok(bankCardService.createCard(request));
    }
}
