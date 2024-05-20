package com.example.astonhtjdbc.user.controller;

import com.example.astonhtjdbc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/users/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createUser(@RequestParam String name,
                           @RequestParam String email) {
        userService.create(name, email);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public String getUser(@RequestParam Long id) {
        return userService.getById(id);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void UpdateUser(@RequestParam Long id,
                           @RequestParam String name,
                           @RequestParam String email) {
        userService.update(id, name, email);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void DeleteUser(@RequestParam Long id) {
        userService.delete(id);
    }

    @GetMapping("all")
    @ResponseStatus(HttpStatus.FOUND)
    public ArrayList<String> getAll(){
        return userService.getAll();
    }
}
