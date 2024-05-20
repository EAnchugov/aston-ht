package com.example.astonhtjdbc.user.service;

import com.example.astonhtjdbc.user.repository.JDBCUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {
    private final JDBCUserRepository repository;

    @Autowired
    public UserServiceImpl(JDBCUserRepository repository) {
        this.repository = repository;
    }


    @Override
    public void create(String name, String email) {
        repository.addUser(name,email);
    }

    @Override
    public void update(Long id,String name, String email) {
        repository.updateUser(id,name,email);

    }

    @Override
    public void delete(Long id) {
        repository.delete(id);

    }

    @Override
    public String getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public ArrayList<String> getAll() {
        return repository.getAll();

    }
}
