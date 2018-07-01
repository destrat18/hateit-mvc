package com.hateit.common;

import java.util.UUID;

public class Utility {
    public static boolean isEmail(String s) {
        return s.matches("^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
    }

    public static boolean hasLetter(String s) {
        return s.matches(".*[a-zA-Z]+.*");
    }

    public static boolean hasNumber(String s) {
        return s.matches(".*[0-9]+.*");
    }

    public static String getUniqueId()
    {
        return UUID.randomUUID().toString();
    }

}
