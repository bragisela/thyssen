/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.UserDao;
import com.empresa.thyssenplastic.dao.impl.UserDaoImpl;
import com.empresa.thyssenplastic.model.UserModel;
import java.util.ArrayList;
import java.util.List;
import com.empresa.thyssenplastic.service.UserService;

/**
 *
 * @author gusta
 */
public class UserServiceImpl implements UserService {
    
    
    public void save(UserModel userModel) {
        try {
            UserDao userDao = new UserDaoImpl();
            userDao.save(userModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public UserModel getUserByUsernameAndPassword(String username, String password) {

        UserModel user = null;
        try {
            UserDao userDao = new UserDaoImpl();
            user = userDao.getUserByUsernameAndPassword(username, password);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return user;
    }

    public UserModel getUserById(Integer id) {

        UserModel user = null;
        try {
            UserDao userDao = new UserDaoImpl();
            user = userDao.getUserById(id);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return user;
    }
    
    public List<UserModel> getAll() {
        List<UserModel> users = new ArrayList<UserModel>();
        try {
            UserDao userDao = new UserDaoImpl();
            users = userDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return users;
    }
}
