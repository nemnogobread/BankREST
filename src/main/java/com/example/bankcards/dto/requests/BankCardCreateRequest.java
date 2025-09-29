package com.example.bankcards.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankCardCreateRequest {
    @NotBlank(message = "Card number is required")
    @Pattern(regexp = "^[0-9]{16}$", message = "Card number must contain 16 digits")
    private String cardNumber;

    @NotBlank(message = "Cardholder name is required")
    @Pattern(regexp = "^[A-Za-zА-Яа-яЁё\\s'-]{2,50}$", message = "Cardholder name must contain only letters, spaces, apostrophes or hyphens")
    private String cardHolderName;

    @NotBlank(message = "Expiry date is required")
    @Pattern(regexp = "^(0[1-9]|1[0-2])/[0-9]{2}$", message = "Expiry date must be in MM/YY format")
    private String expiryDate;

    private double balance;
}
