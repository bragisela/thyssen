/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao;

import com.empresa.thyssenplastic.model.UserModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface UserDao extends GenericDao {
    
    UserModel getUserByUsernameAndPassword(String username, String password);
    
    List<UserModel> getAll();
    
    UserModel getUserById(Integer id);

}
