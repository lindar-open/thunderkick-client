package com.lindar.thunderkick.vo.internal;

import lombok.Data;

@Data
public class AccessCredentials {
    private String apiUrl;
    private String operatorId;
    
    private String username;
    private String password;
}
