package com.lindar.thunderkick.vo.api;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayerSessionTokenWrapper extends ErrorResponse {
    private String playerSessionToken;

    public PlayerSessionTokenWrapper(String playerSessionToken) {
        this.playerSessionToken = playerSessionToken;
    }
}
