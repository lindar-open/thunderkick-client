package com.lindar.thunderkick.vo.api;

import lombok.Data;

import java.util.Date;

@Data
public class FreeRoundsTemplateSearchQuery extends ErrorResponse {

    private String gameName;

    private Date creationDateFrom;

    private Boolean onlyValid;

}
