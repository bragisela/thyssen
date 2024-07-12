/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service;

import com.empresa.thyssenplastic.model.UserModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface UserService {
    
    UserModel getUserByUsernameAndPassword(String username, String password);
    
    UserModel getUserById(Integer id);
             
    List<UserModel> getAll();
    
    void save(UserModel user);
}
