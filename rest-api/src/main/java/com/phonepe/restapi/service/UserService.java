package com.phonepe.restapi.service;

import com.phonepe.restapi.models.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserService {

    public HashMap<String, User> usersData = new HashMap<>();

    public User addUser(User user) {
        usersData.put(user.getUserId(), user);
        return user;
    }

    public User get(String userId) {
        return usersData.get(userId);
    }
}
