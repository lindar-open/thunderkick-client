package com.lindar.thunderkick.vo.api;

import com.google.gson.annotations.JsonAdapter;
import com.lindar.thunderkick.util.adapters.NonIsoInstantTypeAdapter;
import lombok.Data;

import java.time.Instant;

@Data
public class FreeRoundsTemplate extends FreeRoundsTemplateBase {
    private String  name;
    private String  description;
    private Integer numberOfFreeRounds;

    @JsonAdapter(NonIsoInstantTypeAdapter.class)
    private Instant creationDate;

    private Boolean isValid;
    private Integer id;
    private String  templateReference;
    private Integer operatorId;
    private Boolean usedForReferAFriend;
}
