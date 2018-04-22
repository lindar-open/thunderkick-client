package com.lindar.thunderkick.vo.api;

import lombok.Data;

@Data
public class LoginUserAccount {
    private String userName;
    private String password; // this password is different than user's password
    private String operatorSessionToken; // unique id for user's session. It will be provided to Thunderkick and they will return it on each wallet call
}
