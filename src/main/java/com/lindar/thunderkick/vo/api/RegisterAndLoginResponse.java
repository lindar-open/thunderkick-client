package com.lindar.thunderkick.vo.api;

import lombok.Data;

@Data
public class RegisterAndLoginResponse extends ErrorResponse {
    private String playerId;
    private String playerSessionToken;
}
