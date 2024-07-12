/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.ProveedorDomicilioDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.ProveedorDomicilioModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class ProveedorDomicilioDaoImpl extends GenericDaoImpl implements ProveedorDomicilioDao {
    
        public List<ProveedorDomicilioModel> getAll() {
        List<ProveedorDomicilioModel> proveedorDomicilios = new ArrayList<ProveedorDomicilioModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            proveedorDomicilios = session.createQuery("from ProveedorDomicilioModel", ProveedorDomicilioModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return proveedorDomicilios;
    }

    public List<ProveedorDomicilioModel> getByPkProveedor(Integer pk) {
        List<ProveedorDomicilioModel> proveedorDomicilios = new ArrayList<ProveedorDomicilioModel>();        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from ProveedorDomicilioModel WHERE idProveedor=:idProveedor");
                query.setInteger("idProveedor", pk);
                
                proveedorDomicilios = query.list();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return proveedorDomicilios;
    }

    public ProveedorDomicilioModel getByPkProveedorAndDomicilio(Integer pkProveedor, Integer pkDomicilio) {
        List<ProveedorDomicilioModel> proveedorDomicilios = null;        
        try {
            if(pkProveedor != null && pkDomicilio != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from ProveedorDomicilioModel WHERE idProveedor=:idProveedor AND idDomicilio=:idDomicilio");
                query.setInteger("idProveedor", pkProveedor);
                query.setInteger("idDomicilio", pkDomicilio);
                
                proveedorDomicilios = query.list();
                
                if(proveedorDomicilios != null && !proveedorDomicilios.isEmpty()) {
                    return proveedorDomicilios.get(0);
                }                    
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
    
}
