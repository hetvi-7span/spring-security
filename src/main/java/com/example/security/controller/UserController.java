package com.example.security.controller;

import com.example.security.model.User;
import com.example.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{username}")
    public User getSingleUsers(@PathVariable  String username){
        return userService.getUser(username);
    }


    @PreAuthorize("hasRole('USER')")
    @PostMapping("/")
    public User addUser(@RequestBody User user){
        return userService.addUer(user);
    }

}
