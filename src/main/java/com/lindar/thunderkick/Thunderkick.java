package com.lindar.thunderkick;

import com.lindar.thunderkick.api.AccountResource;
import com.lindar.thunderkick.api.FreeRoundsResource;
import com.lindar.thunderkick.api.FreeRoundsTemplateResource;
import com.lindar.thunderkick.api.GameResource;
import com.lindar.thunderkick.vo.internal.AccessCredentials;
import lombok.extern.slf4j.Slf4j;

/**
 * Thunderkick Casino API Client Facade
 */
@Slf4j
public class Thunderkick {
    private AccountResource            accountResource;
    private FreeRoundsResource         freeRoundsResource;
    private FreeRoundsTemplateResource freeRoundsTemplateResource;
    private GameResource               gameResource;

    private Thunderkick(AccessCredentials accessCredentials) {
        this.accountResource = new AccountResource(accessCredentials);
        this.freeRoundsResource = new FreeRoundsResource(accessCredentials);
        this.freeRoundsTemplateResource = new FreeRoundsTemplateResource(accessCredentials);
        this.gameResource = new GameResource(accessCredentials.getGameServiceApiUrl(), accessCredentials.getOperatorName());
    }

    /**
     * Builds Thunderkick Casino API Client
     */
    public static Thunderkick build(String apiUrl, String gameServiceApiUrl, String operatorId, String operatorName, String username, String password) {
        AccessCredentials accessCredentials = new AccessCredentials();
        accessCredentials.setApiUrl(apiUrl);
        accessCredentials.setGameServiceApiUrl(gameServiceApiUrl);
        accessCredentials.setOperatorId(operatorId);
        accessCredentials.setOperatorName(operatorName);
        accessCredentials.setUsername(username);
        accessCredentials.setPassword(password);
        return new Thunderkick(accessCredentials);
    }

    /**
     * Builds Thunderkick Casino API Client
     */
    public static Thunderkick build(AccessCredentials accessCredentials) {
        return new Thunderkick(accessCredentials);
    }

    /**
     * Returns an account resource that allows you to interact with all account endpoints: register, login, logout, keepAlive
     */
    public AccountResource account() {
        return accountResource;
    }

    /**
     * Returns a free rounds template resource that allows you to manage free rounds templates
     */
    public FreeRoundsTemplateResource freeRoundsTemplate() {
        return freeRoundsTemplateResource;
    }

    /**
     * Returns a free rounds resource that allows you to manage player assignments
     */
    public FreeRoundsResource freeRounds() {
        return freeRoundsResource;
    }

    public GameResource games() {
        return gameResource;
    }
}
