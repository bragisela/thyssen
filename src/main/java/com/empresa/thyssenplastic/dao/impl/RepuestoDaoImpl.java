/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.RepuestoDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.RepuestoModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class RepuestoDaoImpl extends GenericDaoImpl implements RepuestoDao {
    
    public List<RepuestoModel> getAll() {
        List<RepuestoModel> repuestos = new ArrayList<RepuestoModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            repuestos = session.createQuery("from RepuestoModel WHERE baja = 0 ORDER BY descripcion", RepuestoModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return repuestos;
    }

        public List<RepuestoModel> getAllByPk(Integer pk) {
        List<RepuestoModel> repuestos = new ArrayList<RepuestoModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from RepuestoModel WHERE id =:id ORDER BY descripcion", RepuestoModel.class);
            query.setParameter("id", pk);
            
            return query.list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return repuestos;
    }

    
    public RepuestoModel getByPk(Integer pk) {
        RepuestoModel repuesto = null;        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from RepuestoModel WHERE id=:id");
                query.setInteger("id", pk);
                
                List<RepuestoModel> repuestos = query.list();
                if(repuestos != null && !repuestos.isEmpty()) {
                    repuesto = repuestos.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return repuesto;
    }

}
