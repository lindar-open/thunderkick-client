package com.lindar.thunderkick.vo.api.util;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by stevenhills on 21/06/2017.
 */
@Data
@NoArgsConstructor
public class Amount {
    private BigDecimal amount;
    private String currency;
}
