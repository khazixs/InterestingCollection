package com.ic.userManagement.entity;

import java.util.List;

public class ServerResponse {
    private static boolean sign;
    private static String reason;

    public ServerResponse(boolean sign,String reason){
        ServerResponse.sign = sign;
        ServerResponse.reason = reason;
    }

    public static ServerResponse createByErrorMessage(String reason){
        return new ServerResponse(false,reason);
    }

    public boolean isSign() {
        return sign;
    }

    public void setSign(boolean sign) {
        ServerResponse.sign = sign;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        ServerResponse.reason = reason;
    }
}
