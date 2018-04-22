package com.lindar.thunderkick.api;

import com.lindar.thunderkick.vo.api.FreeRoundsTemplateFailedPlayerAssignments;
import com.lindar.thunderkick.vo.api.FreeRoundsTemplateMultiPlayerAssignment;
import com.lindar.thunderkick.vo.api.FreeRoundsTemplatePlayerAssignment;
import com.lindar.thunderkick.vo.api.FreeRoundsTemplatePlayerAssignments;
import com.lindar.thunderkick.vo.internal.AccessCredentials;
import com.lindar.wellrested.vo.Result;
import lindar.acolyte.util.UrlAcolyte;
import org.apache.commons.lang3.BooleanUtils;

public class FreeRoundsResource extends AbstractResource {
    private static final String ONLY_ACTIVE_PARAM = "onlyActive";

    private FreeRoundsTemplateResource freeRoundsTemplateResource;

    public FreeRoundsResource(AccessCredentials accessCredentials) {
        super(accessCredentials);
        this.freeRoundsTemplateResource = new FreeRoundsTemplateResource(accessCredentials);
    }

    public FreeRoundsTemplateResource template() {
        return this.freeRoundsTemplateResource;
    }

    public Result<FreeRoundsTemplateFailedPlayerAssignments> assignMultiple(FreeRoundsTemplateMultiPlayerAssignment templatePlayerAssignment, String templateRef) {
        String path = this.freeRoundsTemplateResource.templatePath() + Endpoints.FREE_ROUNDS.ASSIGN;
        return postAndGet(UrlAcolyte.safeConcat(path, templateRef), templatePlayerAssignment, FreeRoundsTemplateFailedPlayerAssignments.class);
    }

    public Result<FreeRoundsTemplatePlayerAssignments> getPlayerAssignmentsByUsername(String username, boolean onlyActive) {
        return sendAndGet(UrlAcolyte.addParam(UrlAcolyte.safeConcat(accountPath(), username), ONLY_ACTIVE_PARAM, BooleanUtils.toStringTrueFalse(onlyActive)),
                          FreeRoundsTemplatePlayerAssignments.class);
    }

    public Result<FreeRoundsTemplatePlayerAssignments> getPlayerAssignmentsByPlayerRef(String playerRef, boolean onlyActive) {
        return sendAndGet(UrlAcolyte.addParam(UrlAcolyte.safeConcat(accountByExternalRefPath(), playerRef), ONLY_ACTIVE_PARAM, BooleanUtils.toStringTrueFalse(onlyActive)),
                          FreeRoundsTemplatePlayerAssignments.class);
    }

    public Result<Void> assignByPlayerUsername(String username, String playerFreeRoundsRef, FreeRoundsTemplatePlayerAssignment templatePlayerAssignment) {
        return post(UrlAcolyte.safeConcat(accountPath(), username, playerFreeRoundsRef), templatePlayerAssignment);
    }

    public Result<Void> assignByPlayerRef(String playerRef, String playerFreeRoundsRef, FreeRoundsTemplatePlayerAssignment templatePlayerAssignment) {
        return post(UrlAcolyte.safeConcat(accountByExternalRefPath(), playerRef, playerFreeRoundsRef), templatePlayerAssignment);
    }

    public Result<FreeRoundsTemplatePlayerAssignment> getAssignmentByPlayerUsernameAndPlayerFreeRoundsRef(String username, String playerFreeRoundsRef) {
        return sendAndGet(UrlAcolyte.safeConcat(accountPath(), username, playerFreeRoundsRef), FreeRoundsTemplatePlayerAssignment.class);
    }

    public Result<FreeRoundsTemplatePlayerAssignment> getAssignmentByPlayerRefAndPlayerFreeRoundsRef(String playerRef, String playerFreeRoundsRef) {
        return sendAndGet(UrlAcolyte.safeConcat(accountByExternalRefPath(), playerRef, playerFreeRoundsRef), FreeRoundsTemplatePlayerAssignment.class);
    }

    public Result<Void> invalidateAssignmentByPlayerUsernameAndPlayerFreeRoundsRef(String username, String playerFreeRoundsRef) {
        return delete(UrlAcolyte.safeConcat(accountPath(), username, playerFreeRoundsRef));
    }

    public Result<Void> invalidateAssignmentByPlayerRefAndPlayerFreeRoundsRef(String playerRef, String playerFreeRoundsRef) {
        return delete(UrlAcolyte.safeConcat(accountByExternalRefPath(), playerRef, playerFreeRoundsRef));
    }

    private String accountPath() {
        return UrlAcolyte.addPathParams(Endpoints.FREE_ROUNDS.ACCOUNT, super.getAccessCredentials().getOperatorId());
    }

    private String accountByExternalRefPath() {
        return UrlAcolyte.addPathParams(Endpoints.FREE_ROUNDS.ACCOUNT_BY_EXTERNAL_REF, super.getAccessCredentials().getOperatorId());
    }
}
