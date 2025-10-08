package com.lindar.thunderkick.api;

interface Endpoints {

    interface ACCOUNT {
        String ROOT = "/casino/{}/player";

        String REGISTER = "/register";
        String LOGIN = "/session/login";
        String REGISTER_AND_LOGIN = "/registerAndLogin";
        String KEEP_ALIVE = "/session/keepalive";
        String LOGOUT = "/session/logout";
        String UPDATE = "/update/";
    }

    interface FREE_ROUNDS {
        String ROOT = "/casino-bonus/{}/bonus/freerounds";

        String SEARCH   = ROOT + "/searchtemplates";
        String TEMPLATE = ROOT + "/template";

        String ACCOUNT                 = ROOT + "/account";
        String ACCOUNT_BY_EXTERNAL_REF = ROOT + "/accountByPlayerExternalReference";

        String ASSIGN = "/assign";
    }

    interface GAME {
        String ROOT      = "/gamelauncher";
        String GAME_LIST = ROOT + "/gamelist";
    }
}
