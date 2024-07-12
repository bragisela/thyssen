/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.OrdenDeProduccionScrapDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.OrdenDeProduccionScrapModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class OrdenDeProduccionScrapDaoImpl extends GenericDaoImpl implements OrdenDeProduccionScrapDao {
    
        public List<OrdenDeProduccionScrapModel> getAll() {
        List<OrdenDeProduccionScrapModel> ordenDeProduccionScrap = new ArrayList<OrdenDeProduccionScrapModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            ordenDeProduccionScrap = session.createQuery("from OrdenDeProduccionScrapModel ", OrdenDeProduccionScrapModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionScrap;
    }

    public OrdenDeProduccionScrapModel getByPk(Integer pk) {
        OrdenDeProduccionScrapModel ordenDeProduccionScrap = null;        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionScrapModel WHERE id=:id");
                query.setInteger("id", pk);
                
                List<OrdenDeProduccionScrapModel> ordenDeProduccionScrapList = query.list();
                if(ordenDeProduccionScrapList != null && !ordenDeProduccionScrapList.isEmpty()) {
                    ordenDeProduccionScrap = ordenDeProduccionScrapList.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionScrap;
    }

    public OrdenDeProduccionScrapModel getByCode(String codigo) {
        OrdenDeProduccionScrapModel ordenDeProduccionScrap = null;        
        try {
            if(codigo != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionScrapModel WHERE codigo=:codigo");
                query.setString("codigo", codigo);
                
                List<OrdenDeProduccionScrapModel> ordenDeProduccionScrapList = query.list();
                if(ordenDeProduccionScrapList != null && !ordenDeProduccionScrapList.isEmpty()) {
                    ordenDeProduccionScrap = ordenDeProduccionScrapList.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionScrap;
    }

    public List<OrdenDeProduccionScrapModel> getAllByOrdenDeProduccion(Integer idOrdenDeProduccion) {
        
        List<OrdenDeProduccionScrapModel> ordenDeProduccionScrap = null;
        try {
            if(idOrdenDeProduccion != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionScrapModel WHERE idOrdenDeProduccion=:idOrdenDeProduccion");
                query.setInteger("idOrdenDeProduccion", idOrdenDeProduccion);
                
                ordenDeProduccionScrap = query.list();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionScrap;
    }

    /*
    public List<OrdenDeProduccionScrapModel> getAllAvailableByOrdenDeProduccion(Integer idOrdenDeProduccion) {
        
        List<OrdenDeProduccionScrapModel> ordenDeProduccionScrap = null;
        try {
            if(idOrdenDeProduccion != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionScrapModel WHERE idOrdenDeProduccion=:idOrdenDeProduccion AND estaEnBulto = 0");
                query.setInteger("idOrdenDeProduccion", idOrdenDeProduccion);
                
                ordenDeProduccionScrap = query.list();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionScrap;
    }
 
    public List<OrdenDeProduccionScrapModel> getAllAvailableForRemitoByOrdenDeProduccion(Integer idOrdenDeProduccion) {
        
        List<OrdenDeProduccionScrapModel> ordenDeProduccionScraps = null;
        try {
            if(idOrdenDeProduccion != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionScrapModel WHERE idOrdenDeProduccion=:idOrdenDeProduccion AND estaDisponibleParaRemito = 1");
                query.setInteger("idOrdenDeProduccion", idOrdenDeProduccion);
                
                ordenDeProduccionScraps = query.list();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionScraps;
    }

    public List<OrdenDeProduccionScrapModel> getAllNotAvailableForRemitoByOrdenDeProduccion(Integer idOrdenDeProduccion) {
        
        List<OrdenDeProduccionScrapModel> ordenDeProduccionScraps = null;
        try {
            if(idOrdenDeProduccion != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionScrapModel WHERE idOrdenDeProduccion=:idOrdenDeProduccion AND estaDisponibleParaRemito = 0");
                query.setInteger("idOrdenDeProduccion", idOrdenDeProduccion);
                
                ordenDeProduccionScraps = query.list();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionScraps;
    }
    */
}
