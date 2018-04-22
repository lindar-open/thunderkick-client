package com.lindar.thunderkick.vo.api;

import com.google.gson.annotations.JsonAdapter;
import com.lindar.thunderkick.util.adapters.NonIsoInstantTypeAdapter;
import com.lindar.thunderkick.vo.api.util.AmountHolder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class FreeRoundsTemplatePlayerAssignment extends ErrorResponse {

    @JsonAdapter(NonIsoInstantTypeAdapter.class)
    private Instant      validFrom;
    @JsonAdapter(NonIsoInstantTypeAdapter.class)
    private Instant      validTo;

    private String       freeRoundsBonusTemplateReference;
    private String       playerFreeRoundsReference;
    private Long         playerId;
    private Integer      initialNumberOfFreeRounds;
    private Integer      remainingNumberOfFreeRounds;
    private Boolean      isValid;
    private AmountHolder totalWin;

    public FreeRoundsTemplatePlayerAssignment(Instant validFrom, Instant validTo, String freeRoundsBonusTemplateReference) {
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.freeRoundsBonusTemplateReference = freeRoundsBonusTemplateReference;
    }
}
