package com.lindar.thunderkick.vo.api;

import com.google.gson.annotations.JsonAdapter;
import com.lindar.thunderkick.util.adapters.NonIsoInstantTypeAdapter;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class FreeRoundsTemplateSearchQuery extends ErrorResponse {
    private String     freeRoundsBonusTemplateReference;
    private String     numberOfFreeRounds;
    private String     currencyCode;
    private BigDecimal betAmount;
    private String     gameName;
    @JsonAdapter(NonIsoInstantTypeAdapter.class)
    private Instant    creationDateFrom;
    @JsonAdapter(NonIsoInstantTypeAdapter.class)
    private Instant    creationDateTo;
    private String     distributionChannel;
    private Boolean    onlyValid;
}
