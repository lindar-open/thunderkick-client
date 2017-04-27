package com.lindar.thunderkick.util;

public interface Endpoints {
    interface UTIL {
        String ID_VAR = "/{id}";
        String REF_VAR = "/{ref}";
        String ID_PARAM = "id";
        String REF_PARAM = "ref";
    }
    
    interface ACCOUNT {
        String ROOT = "/casino/${operatorId}/player";
        String REGISTER = "/register";
        String LOGIN = "/session/login";
        String KEEP_ALIVE = "/session/keepalive/${playerSessionToken}";
        String LOGOUT = "/session/logout/${playerSessionToken}";
    }
    
    interface FREE_ROUNDS {
        String ROOT_TEMPLATE = "/casino-bonus/${operatorId}/bonus/freerounds";
        String SEARCH = ROOT_TEMPLATE + "/searchtemplates";
        String TEMPLATE = ROOT_TEMPLATE + "/template/${templateReference}";
        String ASSIGNMENT_MULTIPLE = ROOT_TEMPLATE + "/template/assign/${templateReference}";
        String ASSIGNMENT_BY_USERNAME = ROOT_TEMPLATE + "/account/${user}";
        String ASSIGNMENT_BY_USERNAME_AND_ROUNDS_REF = ASSIGNMENT_BY_USERNAME + "$/{playerFreeRoundsReference}";
        String ASSIGNMENT_BY_REF = ROOT_TEMPLATE + "/accountByPlayerExternalReference/${user}";
        String ASSIGNMENT_BY_REF_AND_ROUNDS_REF = ASSIGNMENT_BY_REF + "/${playerFreeRoundsReference}";
    }
}
