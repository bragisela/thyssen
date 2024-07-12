/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.ContactoDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.ContactoModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class ContactoDaoImpl extends GenericDaoImpl implements ContactoDao {
    
        public List<ContactoModel> getAll() {
        List<ContactoModel> contactos = new ArrayList<ContactoModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            contactos = session.createQuery("from ContactoModel", ContactoModel.class).list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return contactos;
    }

    public List<ContactoModel> getByPkList(List<Integer> pkList) {
        List<ContactoModel> contactos = new ArrayList<ContactoModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from ContactoModel WHERE id IN :pkList");
            query.setParameterList("pkList", pkList);
            
            contactos = query.list();
                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return contactos;
    }

    public ContactoModel getByPk(Integer pk) {
        List<ContactoModel> contactos = new ArrayList<ContactoModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from ContactoModel WHERE id=:pk");
            query.setParameter("pk", pk);
            
            contactos = query.list();
            if(contactos != null && !contactos.isEmpty()) {
                return contactos.get(0);
            }            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
