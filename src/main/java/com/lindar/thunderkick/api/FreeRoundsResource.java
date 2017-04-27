package com.lindar.thunderkick.api;

import com.lindar.thunderkick.util.Endpoints;
import com.lindar.thunderkick.vo.api.FreeRoundsTemplateFailedPlayerAssignments;
import com.lindar.thunderkick.vo.api.FreeRoundsTemplateMultiPlayerAssignment;
import com.lindar.thunderkick.vo.api.FreeRoundsTemplatePlayerAssignment;
import com.lindar.thunderkick.vo.api.FreeRoundsTemplatePlayerAssignments;
import com.lindar.thunderkick.vo.internal.AccessCredentials;
import com.lindar.wellrested.vo.Result;
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
        String path = Endpoints.FREE_ROUNDS.ASSIGNMENT_MULTIPLE;
        return postAndGet(buildPathWithTemplateRef(path, templateRef), templatePlayerAssignment, FreeRoundsTemplateFailedPlayerAssignments.class);
    }

    public Result<FreeRoundsTemplatePlayerAssignments> getPlayerAssignmentsByUsername(String username, boolean onlyActive) {
        String path = Endpoints.FREE_ROUNDS.ASSIGNMENT_BY_USERNAME;
        return sendAndGet(addAttrAndValueToPath(buildPathWithUser(path, username), ONLY_ACTIVE_PARAM, BooleanUtils.toStringTrueFalse(onlyActive)),
                FreeRoundsTemplatePlayerAssignments.class);
    }

    public Result<FreeRoundsTemplatePlayerAssignments> getPlayerAssignmentsByPlayerRef(String playerRef, boolean onlyActive) {
        String path = Endpoints.FREE_ROUNDS.ASSIGNMENT_BY_REF;
        return sendAndGet(addAttrAndValueToPath(buildPathWithUser(path, playerRef), ONLY_ACTIVE_PARAM, BooleanUtils.toStringTrueFalse(onlyActive)),
                FreeRoundsTemplatePlayerAssignments.class);
    }

    public Result<Void> assignByPlayerUsername(String username, String playerFreeRoundsRef, FreeRoundsTemplatePlayerAssignment templatePlayerAssignment) {
        String path = Endpoints.FREE_ROUNDS.ASSIGNMENT_BY_USERNAME_AND_ROUNDS_REF;
        return post(buildPathWithUserAndPlayerFreeRoundsRef(path, username, playerFreeRoundsRef), templatePlayerAssignment);
    }

    public Result<Void> assignByPlayerRef(String playerRef, String playerFreeRoundsRef, FreeRoundsTemplatePlayerAssignment templatePlayerAssignment) {
        String path = Endpoints.FREE_ROUNDS.ASSIGNMENT_BY_REF_AND_ROUNDS_REF;
        return post(buildPathWithUserAndPlayerFreeRoundsRef(path, playerRef, playerFreeRoundsRef), templatePlayerAssignment);
    }

    public Result<FreeRoundsTemplatePlayerAssignment> getAssignmentByPlayerUsernameAndPlayerFreeRoundsRef(String username, String playerFreeRoundsRef) {
        String path = Endpoints.FREE_ROUNDS.ASSIGNMENT_BY_USERNAME_AND_ROUNDS_REF;
        return sendAndGet(buildPathWithUserAndPlayerFreeRoundsRef(path, username, playerFreeRoundsRef), FreeRoundsTemplatePlayerAssignment.class);
    }

    public Result<FreeRoundsTemplatePlayerAssignment> getAssignmentByPlayerRefAndPlayerFreeRoundsRef(String playerRef, String playerFreeRoundsRef) {
        String path = Endpoints.FREE_ROUNDS.ASSIGNMENT_BY_REF_AND_ROUNDS_REF;
        return sendAndGet(buildPathWithUserAndPlayerFreeRoundsRef(path, playerRef, playerFreeRoundsRef), FreeRoundsTemplatePlayerAssignment.class);
    }

    public Result<Void> invalidateAssignmentByPlayerUsernameAndPlayerFreeRoundsRef(String username, String playerFreeRoundsRef) {
        String path = Endpoints.FREE_ROUNDS.ASSIGNMENT_BY_USERNAME_AND_ROUNDS_REF;
        return delete(buildPathWithUserAndPlayerFreeRoundsRef(path, username, playerFreeRoundsRef));
    }

    public Result<Void> invalidateAssignmentByPlayerRefAndPlayerFreeRoundsRef(String playerRef, String playerFreeRoundsRef) {
        String path = Endpoints.FREE_ROUNDS.ASSIGNMENT_BY_REF_AND_ROUNDS_REF;
        return delete(buildPathWithUserAndPlayerFreeRoundsRef(path, playerRef, playerFreeRoundsRef));
    }
}
