package com.example.astonhtjdbc.user.service;

import java.util.ArrayList;

public interface UserService {
    void create(String name, String email);
    void update(Long id, String name, String email);

    void delete(Long id);

    String getById(Long id);

    ArrayList<String> getAll();
}
