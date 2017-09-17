package com.lindar.thunderkick.api;

import com.lindar.thunderkick.vo.api.LoginUserAccount;
import com.lindar.thunderkick.vo.api.PlayerIdWrapper;
import com.lindar.thunderkick.vo.api.PlayerSessionTokenWrapper;
import com.lindar.thunderkick.vo.api.RegisterUserAccount;
import com.lindar.thunderkick.vo.internal.AccessCredentials;
import com.lindar.wellrested.vo.Result;
import lindar.acolyte.util.UrlAcolyte;

public class AccountResource extends AbstractResource {
    
    public AccountResource(AccessCredentials accessCredentials) {
        super(accessCredentials);
    }
    
    public Result<PlayerIdWrapper> register(RegisterUserAccount registerUserAccount) {
        return postAndGet(accountPath() + Endpoints.ACCOUNT.REGISTER, registerUserAccount, PlayerIdWrapper.class);
    }
    
    public Result<PlayerSessionTokenWrapper> login(LoginUserAccount loginUserAccount) {
        return postAndGet(accountPath() + Endpoints.ACCOUNT.LOGIN, loginUserAccount, PlayerSessionTokenWrapper.class);
    }

    public Result<Void> keepAlive(String playerSessionToken) {
        String path = accountPath() + Endpoints.ACCOUNT.KEEP_ALIVE;
        return put(UrlAcolyte.safeConcat(path, playerSessionToken));
    }

    public Result<Void> logout(String playerSessionToken) {
        String path = accountPath() + Endpoints.ACCOUNT.LOGOUT;
        return delete(UrlAcolyte.safeConcat(path, playerSessionToken));
    }

    private String accountPath() {
        return UrlAcolyte.addPathParams(Endpoints.ACCOUNT.ROOT, super.getAccessCredentials().getOperatorId());
    }
}
