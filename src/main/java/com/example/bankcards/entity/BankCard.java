package com.example.bankcards.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

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

    @Enumerated(EnumType.STRING)
    @Column
    private Status status;

    @Column
    private double balance;

    public String getMaskedCardNumber() {
        String lastFour = cardNumber.substring(cardNumber.length() - 4);
        return "**** **** **** " + lastFour;
    }

    @Transient // This field is not persisted in DB
    public boolean isExpired() {
        try {
            YearMonth expiry = YearMonth.parse(expiryDate, DateTimeFormatter.ofPattern("MM/yy"));
            return expiry.isBefore(YearMonth.now());
        } catch (Exception e) {
            return true;
        }
    }

    @PrePersist
    @PreUpdate
    private void updateStatus() {
        if (isExpired()) {
            this.status = Status.OUT_OF_DATE;
        } else if (this.status == null) {
            this.status = Status.ACTIVE;
        }
    }
}
