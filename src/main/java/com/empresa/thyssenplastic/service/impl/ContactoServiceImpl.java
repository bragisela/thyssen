/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.ContactoDao;
import com.empresa.thyssenplastic.dao.impl.ContactoDaoImpl;
import com.empresa.thyssenplastic.model.ContactoModel;
import com.empresa.thyssenplastic.service.ContactoService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusta
 */
public class ContactoServiceImpl implements ContactoService {
    
    public void save(ContactoModel contactoModel) {
        try {
            ContactoDao contactoDao = new ContactoDaoImpl();
            contactoDao.save(contactoModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(ContactoModel contactoModel) {
        try {
            ContactoDao contactoDao = new ContactoDaoImpl();
            contactoDao.delete(contactoModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<ContactoModel> getAll() {
        List<ContactoModel> contactos = new ArrayList<ContactoModel>();
        try {
            ContactoDao contactoDao = new ContactoDaoImpl();
            contactos = contactoDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return contactos;
    }

    public ContactoModel getByPk(Integer pk) {
        ContactoModel contacto = null;
        try {
            ContactoDao contactoDao = new ContactoDaoImpl();
            contacto = contactoDao.getByPk(pk);            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return contacto;
    }
    
    public List<ContactoModel> getByPkList(List<Integer> pkList) {
        List<ContactoModel> contactos = new ArrayList<ContactoModel>();
        try {
            ContactoDao contactoDao = new ContactoDaoImpl();
            contactos = contactoDao.getByPkList(pkList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return contactos;        
    }
}
