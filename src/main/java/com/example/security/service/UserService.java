package com.example.security.service;

import com.example.security.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    List<User> userList = new ArrayList<>();

    public UserService() {
        userList.add(new User("hetvi", "hetvi", "hetvi@gmail.com"));
        userList.add(new User("abc", "abc", "abc@gmail.com"));
    }


    public List<User> getAllUsers() {
        return this.userList;
    }

    public User getUser(String username) {
        return this.userList.stream().filter(user -> user.getUsername().equalsIgnoreCase(username)).findAny().orElse(null);
    }

    public User addUer(User user) {
        this.userList.add(user);
        return user;
    }
}
