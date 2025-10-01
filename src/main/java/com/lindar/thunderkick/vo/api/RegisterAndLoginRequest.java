package com.lindar.thunderkick.vo.api;

import com.lindar.thunderkick.vo.api.util.MaxBet;
import lombok.Data;

@Data
public class RegisterAndLoginRequest {
    private RegisterUserAccount player;
    private String operatorSessionToken;
    private MaxBet maxBet;
}
