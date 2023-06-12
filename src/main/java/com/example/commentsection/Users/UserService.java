/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.commentsection.Users;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Asus
 */
@Service
public class UserService {
    @Autowired
    private UserRepository res;
    public List<Users> findAllUser(){
        return res.findAll();
    }
    public void addUser(Users user){
        res.save(user);
    }
    public Optional<Users> findSingleUSer(long id){
        return res.findById(id);
    }
    public Users findByUsername(String name){
        return res.findByUsername(name);
    }
}
