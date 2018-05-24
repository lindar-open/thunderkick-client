package com.lindar.thunderkick.vo.api;

import com.lindar.thunderkick.vo.api.util.GameGroup;
import lombok.Data;

import java.util.List;

@Data
public class FreeRoundsTemplateBase extends ErrorResponse {
    private List<GameGroup> gameGroups;
    private Boolean         usedForReferAFriend;
    private Boolean         usedForWelcomeOffer;
}
