package com.lindar.thunderkick.vo.api;

import lombok.Data;

import java.util.Date;

@Data
public class FreeRoundsTemplate extends FreeRoundsTemplateBase {

    private String name;

    private String description;

    private Integer numberOfFreeRounds;


    private Date creationDate;

    private Boolean isValid;

    private Integer id;

    private String templateReference;

    private Integer operatorId;

}
