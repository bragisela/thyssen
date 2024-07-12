/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.UserDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.UserModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class UserDaoImpl extends GenericDaoImpl implements UserDao {

    public UserModel getUserByUsernameAndPassword(String username, String password) {

        UserModel user = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            String hql = "from UserModel u where u.username = :username AND u.password = :password";     
            Query query = session.createQuery(hql, UserModel.class);

            query.setParameter("username", username);
            query.setParameter("password", password);

            List<UserModel> users = query.list();
            if(users != null && !users.isEmpty()) {
                return users.get(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return user;
    }

    public UserModel getUserById(Integer id) {

        UserModel user = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            String hql = "from UserModel u where u.id = :id";     
            Query query = session.createQuery(hql, UserModel.class);

            query.setParameter("id", id);

            List<UserModel> users = query.list();
            if(users != null && !users.isEmpty()) {
                return users.get(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return user;
    }
    
    public List<UserModel> getAll() {
        List<UserModel> users = new ArrayList<UserModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            users = session.createQuery("from UserModel", UserModel.class).list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return users;
    }
}
