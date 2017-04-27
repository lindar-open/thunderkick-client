package com.lindar.thunderkick.vo.api;

import com.lindar.thunderkick.vo.api.util.PlayerAssignment;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class FreeRoundsTemplateMultiPlayerAssignment extends ErrorResponse {

    private Date validFrom;
    private Date validTo;
    private List<PlayerAssignment> players;

}
