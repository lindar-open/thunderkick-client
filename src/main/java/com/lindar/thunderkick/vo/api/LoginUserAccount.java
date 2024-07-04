package com.lindar.thunderkick.vo.api;

import com.lindar.thunderkick.vo.api.util.MaxBet;
import lombok.Data;

@Data
public class LoginUserAccount {
    private String userName;
    private String password; // this password is different than user's password
    private String operatorSessionToken; // unique id for user's session. It will be provided to Thunderkick and they will return it on each wallet call
    private MaxBet maxBet; // Optional. Max bet player can place when placing a single bet. Can be use to for regulatory purposes in UK.
}
