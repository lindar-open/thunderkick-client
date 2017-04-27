package com.lindar.thunderkick.vo.api;

import lombok.Data;

@Data
public class RegisterUserAccount {

    private String userName;
    private String externalReference; // this can be the actual user ref

    private String password; // this password is different than user's password
    private String currencyCode; // CurrencyEnum
}
