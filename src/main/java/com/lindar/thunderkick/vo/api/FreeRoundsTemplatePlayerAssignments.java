package com.lindar.thunderkick.vo.api;

import lombok.Data;

import java.util.List;

@Data
public class FreeRoundsTemplatePlayerAssignments extends ErrorResponse {
    private List<FreeRoundsTemplatePlayerAssignment> playerFreeRoundsBonusAccounts;
}
