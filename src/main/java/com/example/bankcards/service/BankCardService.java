package com.example.bankcards.service;

import com.example.bankcards.entity.BankCard;
import com.example.bankcards.repository.BankCardRepository;
import com.example.bankcards.dto.requests.BankCardCreateRequest;
import com.example.bankcards.dto.responses.BankCardResponse;
import com.example.bankcards.util.BankCardUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankCardService {
    private final BankCardRepository bankCardRepository;
    private final BankCardUtils bankCardUtils;

    public BankCardResponse createCard(BankCardCreateRequest request) {
        if (!bankCardUtils.isExpiryDateValid(request.getExpiryDate())) {
            throw new IllegalArgumentException("Expire date is incorrect");
        }

        if (bankCardRepository.existsByCardNumber(request.getCardNumber())) {
            throw new IllegalArgumentException("This card number is already exists");
        }

        bankCardRepository.save(
                BankCard.builder()
                        .cardNumber(request.getCardNumber())
                        .expiryDate(request.getExpiryDate())
                        .cardHolderName(request.getCardHolderName())
                        .balance(request.getBalance())
                        .build());

        return BankCardResponse.builder()
                .message("Bank card created!")
                .build();
    }
}
