/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.RemitoDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.RemitoModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class RemitoDaoImpl extends GenericDaoImpl implements RemitoDao {
    
    public List<RemitoModel> getAll() {
        List<RemitoModel> remitos = new ArrayList<RemitoModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            remitos = session.createQuery("from RemitoModel ", RemitoModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return remitos;
    }

    public List<RemitoModel> getAllCompletadoAvailable() {
        List<RemitoModel> remitos = new ArrayList<RemitoModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            remitos = session.createQuery("from RemitoModel WHERE estado = 'Completado' AND estaEnHojaDeRuta = 0 ORDER BY id DESC", RemitoModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return remitos;
    }

    public List<RemitoModel> getAllAvailable() {
        List<RemitoModel> remitos = new ArrayList<RemitoModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            remitos = session.createQuery("from RemitoModel WHERE estaEnHojaDeRuta = 0 order by id DESC ", RemitoModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return remitos;
    }

    public RemitoModel getByPk(Integer pk) {
        RemitoModel remito = null;        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from RemitoModel WHERE id=:id");
                query.setInteger("id", pk);
                
                List<RemitoModel> remitos = query.list();
                if(remitos != null && !remitos.isEmpty()) {
                    remito = remitos.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return remito;
    }

}
