package com.faig.elearningapi.controller;


import com.faig.elearningapi.model.User;
import com.faig.elearningapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin("*")
@RequestMapping("/login")


public class UserController {

    @Autowired
    private UserService service;


    @GetMapping
    public List<User> findAll() {
        return service.findAll();
    }

    @PostMapping("/user")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        User foundUser = service.finByUserPassword(user.getUsername(), user.getPassword());
        System.out.println("mi suario " + user.getUsername() + "::::"+ user.getPassword());
        if (foundUser != null) {
            return ResponseEntity.ok(foundUser);
        } else {
            String errorMessage = "Usuario o contraseña incorrectos";
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"" + errorMessage + "\"}");
        }
    }
}