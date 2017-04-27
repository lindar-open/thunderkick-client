package com.lindar.thunderkick.vo.api.util;

import com.lindar.thunderkick.vo.api.ErrorResponse;
import lombok.Data;

@Data
public class PlayerAssignment extends ErrorResponse {
    private String userName;

    private String playerExternalReference;

    private String playerFreeRoundsReference;
}
