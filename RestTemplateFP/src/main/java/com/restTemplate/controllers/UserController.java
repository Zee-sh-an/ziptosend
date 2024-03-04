package com.restTemplate.controllers;

import com.restTemplate.models.User;
import com.restTemplate.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("/getAllUser")
    public ResponseEntity getAllUser(){
        return userService.getAll();
    }

    @GetMapping("/getUserById/{userId}")
    public ResponseEntity getUserById(@PathVariable long userId){
        return userService.getUser(userId);
    }

}
