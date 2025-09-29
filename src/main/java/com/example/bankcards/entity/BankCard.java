package com.example.bankcards.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bank_cards")
public class BankCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 16, unique = true)
    private String cardNumber;

    @Column
    private String cardHolderName;

    @Column
    private String expiryDate;

    @Column
    private Status status;

    @Column
    private double balance;

    public String getMaskedCardNumber() {
        String lastFour = cardNumber.substring(cardNumber.length() - 4);
        return "**** **** **** " + lastFour;
    }
}
