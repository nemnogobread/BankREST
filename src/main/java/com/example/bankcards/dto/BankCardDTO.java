package com.example.bankcards.dto;

import com.example.bankcards.entity.Status;
import com.example.bankcards.entity.User;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BankCardDTO {
    private Integer id;

    @Pattern(regexp = "^[0-9]{16}$", message = "Card number must contain 16 digits")
    private String cardNumber;

    @Pattern(regexp = "^[A-Za-zА-Яа-яЁё\\s'-]{2,50}$", message = "Cardholder name must contain only letters, spaces, apostrophes or hyphens")
    private String cardHolderName;

    @Pattern(regexp = "^(0[1-9]|1[0-2])/[0-9]{2}$", message = "Expiry date must be in MM/YY format")
    private String expiryDate;

    private Status status;

    private double balance;
}

