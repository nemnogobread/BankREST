package com.example.bankcards.controller;

import com.example.bankcards.dto.requests.BankCardNumberRequest;
import com.example.bankcards.dto.responses.MessageResponse;
import com.example.bankcards.entity.Status;
import com.example.bankcards.service.BankCardService;
import com.example.bankcards.dto.responses.BankCardResponse;
import com.example.bankcards.dto.requests.BankCardCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
    ) {
        return ResponseEntity.ok(bankCardService.createCard(request));
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/block")
    public ResponseEntity<MessageResponse> blockCard(
            @RequestBody BankCardNumberRequest request
    ) {
        return ResponseEntity.ok(bankCardService.changeCardStatus(request, Status.BLOCKED));
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/activate")
    public ResponseEntity<MessageResponse> activateCard(
            @RequestBody BankCardNumberRequest request
    ) {
        return ResponseEntity.ok(bankCardService.changeCardStatus(request, Status.ACTIVE));
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/delete")
    public ResponseEntity<MessageResponse> deleteCard(
            @RequestBody BankCardNumberRequest request
    ) {
        return ResponseEntity.ok(bankCardService.deleteCard(request));
    }
}
