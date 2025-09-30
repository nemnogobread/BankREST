package com.example.bankcards.service;

import com.example.bankcards.dto.requests.BankCardNumberRequest;
import com.example.bankcards.dto.responses.MessageResponse;
import com.example.bankcards.entity.BankCard;
import com.example.bankcards.entity.Status;
import com.example.bankcards.repository.BankCardRepository;
import com.example.bankcards.dto.requests.BankCardCreateRequest;
import com.example.bankcards.dto.responses.BankCardResponse;
import com.example.bankcards.util.BankCardUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankCardService {
    private final BankCardRepository bankCardRepository;
    private final BankCardUtils bankCardUtils;

    public BankCardResponse createCard(BankCardCreateRequest request) {
        if (!bankCardUtils.isExpiryDateValid(request.getExpiryDate())) {
            throw new IllegalArgumentException("Expire date is incorrect!");
        }

        if (bankCardRepository.findByCardNumber(request.getCardNumber()).isPresent()) {
            throw new IllegalArgumentException("This card number is already exists!");
        }

        bankCardRepository.save(
                BankCard.builder()
                        .cardNumber(request.getCardNumber())
                        .expiryDate(request.getExpiryDate())
                        .cardHolderName(request.getCardHolderName())
                        .balance(request.getBalance())
                        .build());

        return BankCardResponse.builder()
                .message("Bank card created")
                .build();
    }

    public MessageResponse changeCardStatus(BankCardNumberRequest request, Status status) {
        if (bankCardRepository.findByCardNumber(request.getCardNumber()).isEmpty()) {
            throw new EntityNotFoundException("Card with this number does not exist!");
        }

        BankCard bankCard = bankCardRepository.findByCardNumber(request.getCardNumber()).get();
        bankCard.setStatus(status);
        bankCardRepository.save(bankCard);
        return MessageResponse.builder()
                .message("Status was changed successfully")
                .build();
    }

    public MessageResponse deleteCard(BankCardNumberRequest request) {
        if (bankCardRepository.findByCardNumber(request.getCardNumber()).isEmpty()) {
            throw new EntityNotFoundException("Card with this number does not exist!");
        }

        bankCardRepository.deleteByCardNumber(request.getCardNumber());

        return MessageResponse.builder()
                .message("Card was deleted")
                .build();
    }
}
