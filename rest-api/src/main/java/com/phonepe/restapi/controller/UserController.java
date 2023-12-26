package com.phonepe.restapi.controller;

import com.phonepe.restapi.models.User;
import com.phonepe.restapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping(path = "",
            method = {RequestMethod.POST},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> create(@RequestBody User user) {
        User response = userService.addUser(user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> get(@PathVariable("userId") final String userId) {
        User response = userService.get(userId);
        return ResponseEntity.ok(response);
    }

}
