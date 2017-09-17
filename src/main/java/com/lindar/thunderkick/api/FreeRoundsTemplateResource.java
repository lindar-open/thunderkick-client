package com.lindar.thunderkick.api;

import com.lindar.thunderkick.vo.api.FreeRoundsTemplate;
import com.lindar.thunderkick.vo.api.FreeRoundsTemplateBase;
import com.lindar.thunderkick.vo.api.FreeRoundsTemplateList;
import com.lindar.thunderkick.vo.api.FreeRoundsTemplateSearchQuery;
import com.lindar.thunderkick.vo.internal.AccessCredentials;
import com.lindar.wellrested.vo.Result;
import lindar.acolyte.util.UrlAcolyte;

public class FreeRoundsTemplateResource extends AbstractResource {
    public FreeRoundsTemplateResource(AccessCredentials accessCredentials) {
        super(accessCredentials);
    }

    public Result<Void> create(FreeRoundsTemplate freeRoundsTemplate, String templateRef) {
        return post(UrlAcolyte.safeConcat(templatePath(), templateRef), freeRoundsTemplate);
    }

    public Result<Void> update(FreeRoundsTemplateBase freeRoundsTemplateUpdate, String templateRef) {
        return put(UrlAcolyte.safeConcat(templatePath(), templateRef), freeRoundsTemplateUpdate);
    }

    public Result<FreeRoundsTemplate> get(String templateRef) {
        return sendAndGet(UrlAcolyte.safeConcat(templatePath(), templateRef), FreeRoundsTemplate.class);
    }

    public Result<Void> invalidate(String templateRef) {
        return delete(UrlAcolyte.safeConcat(templatePath(), templateRef));
    }

    public Result<FreeRoundsTemplateList> search(FreeRoundsTemplateSearchQuery templateSearchQuery) {
        String path = UrlAcolyte.addPathParams(Endpoints.FREE_ROUNDS.SEARCH, super.getAccessCredentials().getOperatorId());
        return postAndGet(path, templateSearchQuery, FreeRoundsTemplateList.class);
    }

    String templatePath() {
        return UrlAcolyte.addPathParams(Endpoints.FREE_ROUNDS.TEMPLATE, super.getAccessCredentials().getOperatorId());
    }
    
}
