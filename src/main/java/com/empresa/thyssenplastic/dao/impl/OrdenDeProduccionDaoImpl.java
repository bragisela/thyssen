/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.OrdenDeProduccionDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.OrdenDeProduccionModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class OrdenDeProduccionDaoImpl extends GenericDaoImpl implements OrdenDeProduccionDao {
    
    public List<OrdenDeProduccionModel> getAll() {
        List<OrdenDeProduccionModel> ordenDeProducciones = new ArrayList<OrdenDeProduccionModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            ordenDeProducciones = session.createQuery("from OrdenDeProduccionModel ", OrdenDeProduccionModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProducciones;
    }

    public OrdenDeProduccionModel getByPk(Integer pk) {
        OrdenDeProduccionModel ordenDeProduccion = null;        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionModel WHERE id=:id");
                query.setInteger("id", pk);
                
                List<OrdenDeProduccionModel> ordenDeProducciones = query.list();
                if(ordenDeProducciones != null && !ordenDeProducciones.isEmpty()) {
                    ordenDeProduccion = ordenDeProducciones.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccion;
    }

    public List<OrdenDeProduccionModel> getAllCompletedWithStock() {
        List<OrdenDeProduccionModel> ordenDeProducciones = new ArrayList<OrdenDeProduccionModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            ordenDeProducciones = session.createQuery("from OrdenDeProduccionModel WHERE estado = 'Completado' AND stockActual > 0 ", OrdenDeProduccionModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProducciones;
    }

    public List<OrdenDeProduccionModel> getAllWithStock() {
        List<OrdenDeProduccionModel> ordenDeProducciones = new ArrayList<OrdenDeProduccionModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            ordenDeProducciones = session.createQuery("from OrdenDeProduccionModel WHERE stockActual > 0 ", OrdenDeProduccionModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProducciones;
    }
    
    public List<OrdenDeProduccionModel> getAllCompleted() {
        List<OrdenDeProduccionModel> ordenDeProducciones = new ArrayList<OrdenDeProduccionModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            ordenDeProducciones = session.createQuery("from OrdenDeProduccionModel WHERE estado = 'Completado' ", OrdenDeProduccionModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProducciones;
    }

    public List<OrdenDeProduccionModel> getNotCompleted() {
        List<OrdenDeProduccionModel> ordenDeProducciones = new ArrayList<OrdenDeProduccionModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            ordenDeProducciones = session.createQuery("from OrdenDeProduccionModel WHERE estado <> 'Completado' ", OrdenDeProduccionModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProducciones;
    }
    
}
