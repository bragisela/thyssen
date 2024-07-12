/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.TipoDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.TipoModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class TipoDaoImpl extends GenericDaoImpl implements TipoDao {
    
    public List<TipoModel> getAll() {
        List<TipoModel> tipos = new ArrayList<TipoModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            tipos = session.createQuery("from TipoModel", TipoModel.class).list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return tipos;
    }

    public List<TipoModel> getAllByOrder() {
        List<TipoModel> tipos = new ArrayList<TipoModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            tipos = session.createQuery("from TipoModel ORDER BY tipo ASC", TipoModel.class).list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return tipos;
    }

    public List<TipoModel> getByType(String type) {
        List<TipoModel> tipos = new ArrayList<TipoModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from TipoModel WHERE tipo = :type");
            query.setParameter("type", type);
            
            tipos = query.list();
                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return tipos;
    }

    public TipoModel getByPk(Integer pk) {
        List<TipoModel> tipos = new ArrayList<TipoModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from TipoModel WHERE id=:pk");
            query.setParameter("pk", pk);
            
            tipos = query.list();
            if(tipos != null && !tipos.isEmpty()) {
                return tipos.get(0);
            }            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
    
    public TipoModel getByValor(String valor) {
        List<TipoModel> tipos = new ArrayList<TipoModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("FROM TipoModel WHERE LOWER(valor) = LOWER(:valor)");
            query.setParameter("valor", valor);
            
            tipos = query.list();
            if(tipos != null && !tipos.isEmpty()) {
                return tipos.get(0);
            }            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
