package com.ic.userManagement.utils;

import java.util.regex.Pattern;

public class CheckUp {

    public static final String RegexPhone = "^1[3456789]\\d{9}$";
    public static final String RegexName = "^[a-zA-Z0-9_@$*.。/~!！￥]{1,16}$";
    public static final String RegexIdentityCard = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
    public static final String RegexPassword = "^[a-zA-Z0-9@#_]{6,20}$";
    public static final String RegexEmail = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
    public static final String RegexType = "(buyer|seller|compound)";

    public static boolean checkUpParameter(String paramName, Object paramValue) {
        switch (paramName) {
            case "phone":
                return Pattern.compile(RegexPhone).matcher((String) paramValue).matches();
            case "name":
                return Pattern.compile(RegexName).matcher((String) paramValue).matches();
            case "identityCard":
                return Pattern.compile(RegexIdentityCard).matcher((String) paramValue).matches();
            case "password":
                return Pattern.compile(RegexPassword).matcher((String) paramValue).matches();
            case "email":
                return Pattern.compile(RegexEmail).matcher((String) paramValue).matches();
            case "type":
                return Pattern.compile(RegexType).matcher((String)paramValue).matches();
            default:
                return false;
        }
    }

    public static void main(String[] args) {
        String a = "root";
        boolean result = CheckUp.checkUpParameter("phoneNum",a);
        System.out.println(result);
    }
}
