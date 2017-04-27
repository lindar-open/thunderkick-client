package com.lindar.thunderkick.vo.api;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class FreeRoundsTemplatePlayerAssignment extends ErrorResponse {

    private Date validFrom;
    private Date validTo;
    private String freeRoundsBonusTemplateReference;


    private String playerFreeRoundsReference;
    private Long playerId;

    private Integer initialNumberOfFreeRounds;
    private Integer remainingNumberOfFreeRounds;
    private Boolean isValid;

    public FreeRoundsTemplatePlayerAssignment(Date validFrom, Date validTo, String freeRoundsBonusTemplateReference) {
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.freeRoundsBonusTemplateReference = freeRoundsBonusTemplateReference;
    }
}
