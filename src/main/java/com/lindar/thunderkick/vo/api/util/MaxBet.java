package com.lindar.thunderkick.vo.api.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class MaxBet {
    private BigDecimal amount; // Monetary value of the max bet. Mandatory. Decimal(15,6)
    private String     currency; // Currency code in which the max bet applies and it has to match the player currency. Mandatory. ISO 4217 currency code or agreed 3 characters code for fake currency.
}
