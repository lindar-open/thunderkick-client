package com.lindar.thunderkick.vo.api;

import com.google.gson.annotations.JsonAdapter;
import com.lindar.thunderkick.util.adapters.NonIsoInstantTypeAdapter;
import com.lindar.thunderkick.vo.api.util.PlayerAssignment;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class FreeRoundsTemplateMultiPlayerAssignment extends ErrorResponse {

    @JsonAdapter(NonIsoInstantTypeAdapter.class)
    private Instant                validFrom;
    @JsonAdapter(NonIsoInstantTypeAdapter.class)
    private Instant                validTo;

    private List<PlayerAssignment> players;
}
