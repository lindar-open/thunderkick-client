package com.lindar.thunderkick.vo.api;

import lombok.Data;

import java.util.Map;

@Data
public class GameListWrapper {
    private Map<String, String> games;

    private String operator;
}
