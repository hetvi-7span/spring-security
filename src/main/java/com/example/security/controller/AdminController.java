package com.example.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/home")
    public String adminHome(){
        return "HELLO ADMIN";
    }
}
