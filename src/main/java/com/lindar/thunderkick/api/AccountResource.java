package com.lindar.thunderkick.api;

import com.lindar.thunderkick.util.Endpoints;
import com.lindar.thunderkick.vo.api.PlayerIdWrapper;
import com.lindar.thunderkick.vo.api.PlayerSessionTokenWrapper;
import com.lindar.thunderkick.vo.api.LoginUserAccount;
import com.lindar.thunderkick.vo.api.RegisterUserAccount;
import com.lindar.thunderkick.vo.internal.AccessCredentials;
import com.lindar.wellrested.vo.Result;

public class AccountResource extends AbstractResource {
    
    public AccountResource(AccessCredentials accessCredentials) {
        super(accessCredentials);
    }
    
    public Result<PlayerIdWrapper> register(RegisterUserAccount registerUserAccount) {
        String path = Endpoints.ACCOUNT.ROOT + Endpoints.ACCOUNT.REGISTER;
        return postAndGet(buildPath(path), registerUserAccount, PlayerIdWrapper.class);
    }
    
    public Result<PlayerSessionTokenWrapper> login(LoginUserAccount loginUserAccount) {
        String path = Endpoints.ACCOUNT.ROOT + Endpoints.ACCOUNT.LOGIN;
        return postAndGet(buildPath(path), loginUserAccount, PlayerSessionTokenWrapper.class);
    }

    public Result<Void> keepAlive(String playerSessionToken) {
        String path = Endpoints.ACCOUNT.ROOT + Endpoints.ACCOUNT.KEEP_ALIVE;
        return put(buildPathWithSessionToken(path, playerSessionToken));
    }

    public Result<Void> logout(String playerSessionToken) {
        String path = Endpoints.ACCOUNT.ROOT + Endpoints.ACCOUNT.LOGOUT;
        return delete(buildPathWithSessionToken(path, playerSessionToken));
    }
}
