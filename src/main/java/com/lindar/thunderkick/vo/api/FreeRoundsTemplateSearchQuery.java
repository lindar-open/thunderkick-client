package com.lindar.thunderkick.vo.api;

import com.google.gson.annotations.JsonAdapter;
import com.lindar.thunderkick.util.adapters.NonIsoInstantTypeAdapter;
import lombok.Data;

import java.time.Instant;

@Data
public class FreeRoundsTemplateSearchQuery extends ErrorResponse {
    private String  gameName;

    @JsonAdapter(NonIsoInstantTypeAdapter.class)
    private Instant creationDateFrom;

    private Boolean onlyValid;
}
