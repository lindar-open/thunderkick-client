package com.lindar.thunderkick.vo.api;

import com.lindar.thunderkick.vo.api.util.PlayerAssignment;
import lombok.Data;

import java.util.List;

@Data
public class FreeRoundsTemplateFailedPlayerAssignments extends ErrorResponse {

    private List<PlayerAssignment> failedPlayerAssignments;

}
