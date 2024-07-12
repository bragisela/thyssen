/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.ProveedorContactoDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.ProveedorContactoModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class ProveedorContactoDaoImpl extends GenericDaoImpl implements ProveedorContactoDao {
    
        public List<ProveedorContactoModel> getAll() {
        List<ProveedorContactoModel> proveedorContactos = new ArrayList<ProveedorContactoModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            proveedorContactos = session.createQuery("from ProveedorContactoModel", ProveedorContactoModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return proveedorContactos;
    }

    public List<ProveedorContactoModel> getByPkProveedor(Integer pk) {
        List<ProveedorContactoModel> proveedorContactos = new ArrayList<ProveedorContactoModel>();        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from ProveedorContactoModel WHERE idProveedor=:idProveedor");
                query.setInteger("idProveedor", pk);
                
                proveedorContactos = query.list();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return proveedorContactos;
    }

    public ProveedorContactoModel getByPkProveedorAndContacto(Integer pkProveedor, Integer pkContacto) {
        List<ProveedorContactoModel> proveedorContactos = null;        
        try {
            if(pkProveedor != null && pkContacto != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from ProveedorContactoModel WHERE idProveedor=:idProveedor AND idContacto=:idContacto");
                query.setInteger("idProveedor", pkProveedor);
                query.setInteger("idContacto", pkContacto);
                
                proveedorContactos = query.list();
                
                if(proveedorContactos != null && !proveedorContactos.isEmpty()) {
                    return proveedorContactos.get(0);
                }                    
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
    
    public ProveedorContactoModel getByPkContacto(Integer pkContacto) {
        List<ProveedorContactoModel> proveedorContactos = null;        
        try {
            if(pkContacto != null && pkContacto != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from ProveedorContactoModel WHERE idContacto=:idContacto");                
                query.setInteger("idContacto", pkContacto);
                
                proveedorContactos = query.list();
                
                if(proveedorContactos != null && !proveedorContactos.isEmpty()) {
                    return proveedorContactos.get(0);
                }                    
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }    
}
