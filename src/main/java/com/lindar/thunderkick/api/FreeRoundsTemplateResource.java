package com.lindar.thunderkick.api;

import com.lindar.thunderkick.util.Endpoints;
import com.lindar.thunderkick.vo.api.FreeRoundsTemplate;
import com.lindar.thunderkick.vo.api.FreeRoundsTemplateBase;
import com.lindar.thunderkick.vo.api.FreeRoundsTemplateList;
import com.lindar.thunderkick.vo.api.FreeRoundsTemplateSearchQuery;
import com.lindar.thunderkick.vo.internal.AccessCredentials;
import com.lindar.wellrested.vo.Result;

public class FreeRoundsTemplateResource extends AbstractResource {
    public FreeRoundsTemplateResource(AccessCredentials accessCredentials) {
        super(accessCredentials);
    }

    public Result<Void> create(FreeRoundsTemplate freeRoundsTemplate, String templateRef) {
        String path = Endpoints.FREE_ROUNDS.TEMPLATE;
        return post(buildPathWithTemplateRef(path, templateRef), freeRoundsTemplate);
    }

    public Result<Void> update(FreeRoundsTemplateBase freeRoundsTemplateUpdate, String templateRef) {
        String path = Endpoints.FREE_ROUNDS.TEMPLATE;
        return put(buildPathWithTemplateRef(path, templateRef), freeRoundsTemplateUpdate);
    }

    public Result<FreeRoundsTemplate> get(String templateRef) {
        String path = Endpoints.FREE_ROUNDS.TEMPLATE;
        return sendAndGet(buildPathWithTemplateRef(path, templateRef), FreeRoundsTemplate.class);
    }

    public Result<Void> invalidate(String templateRef) {
        String path = Endpoints.FREE_ROUNDS.TEMPLATE;
        return delete(buildPathWithTemplateRef(path, templateRef));
    }

    public Result<FreeRoundsTemplateList> search(FreeRoundsTemplateSearchQuery templateSearchQuery) {
        String path = Endpoints.FREE_ROUNDS.SEARCH;
        return postAndGet(buildPath(path), templateSearchQuery, FreeRoundsTemplateList.class);
    }
    
}
