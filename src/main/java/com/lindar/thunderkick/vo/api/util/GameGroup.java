package com.lindar.thunderkick.vo.api.util;

import lombok.Data;

import java.util.List;

@Data
public class GameGroup {
    private String ref; // only used internally to identify new game groups
    private List<String> games; // a list of game ids provided by thunderkick
    private List<String> distributionChannels; // can be WEB or MOBILE but for now only WEB is supported
    private List<BetConfig> betConfigurations;
}
