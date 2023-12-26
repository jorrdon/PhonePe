package com.phonepe.restapi.Utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class util {

    public static String generateRandomUUID() {
        return UUID.randomUUID().toString();
    }




    public static String getUsersKey(String userId) {
        return "users_" + userId;
    }


    public static String getProblemKey(String problemId) {
        return "problems_" + problemId;
    }
}
