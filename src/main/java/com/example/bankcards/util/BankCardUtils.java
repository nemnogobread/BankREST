package com.example.bankcards.util;

import org.springframework.stereotype.Component;

import java.time.YearMonth;

@Component
public class BankCardUtils {
    public boolean isExpiryDateValid(String expiryDate) {
        try {
            String[] parts = expiryDate.split("/");
            int month = Integer.parseInt(parts[0]);
            int year = Integer.parseInt(parts[1]) + 2000; // Convert YY to YYYY

            YearMonth expiry = YearMonth.of(year, month);
            return expiry.isAfter(YearMonth.now());
        } catch (Exception e) {
            return false;
        }
    }
}
