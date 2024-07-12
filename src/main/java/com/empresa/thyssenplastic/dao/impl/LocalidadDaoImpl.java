/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.LocalidadDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.LocalidadModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class LocalidadDaoImpl extends GenericDaoImpl implements LocalidadDao {
    
        public List<LocalidadModel> getAll() {
        List<LocalidadModel> localidades = new ArrayList<LocalidadModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            localidades = session.createQuery("from LocalidadModel", LocalidadModel.class).list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return localidades;
    }

    public List<LocalidadModel> getByProvincia(Integer idProvincia) {
        List<LocalidadModel> localidades = new ArrayList<LocalidadModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from LocalidadModel WHERE idProvincia = :idProvincia");            
            query.setParameter("idProvincia", idProvincia);
            
            localidades = query.list();
                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return localidades;
    }

    public LocalidadModel getByPk(Integer pk) {
        List<LocalidadModel> localidades = new ArrayList<LocalidadModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from LocalidadModel WHERE id=:pk");
            query.setParameter("pk", pk);
            
            localidades = query.list();
            if(localidades != null && !localidades.isEmpty()) {
                return localidades.get(0);
            }            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
