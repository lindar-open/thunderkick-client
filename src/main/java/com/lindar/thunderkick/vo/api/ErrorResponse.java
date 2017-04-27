package com.lindar.thunderkick.vo.api;

import lombok.Data;

@Data
public class ErrorResponse {
    
    private String errorCode;
    private String errorMessage;
}
