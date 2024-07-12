/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.DomicilioDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.DomicilioModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class DomicilioDaoImpl extends GenericDaoImpl implements DomicilioDao {
    
        public List<DomicilioModel> getAll() {
        List<DomicilioModel> domicilios = new ArrayList<DomicilioModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            domicilios = session.createQuery("from DomicilioModel", DomicilioModel.class).list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return domicilios;
    }

    public List<DomicilioModel> getByPkList(List<Integer> pkList) {
        List<DomicilioModel> domicilios = new ArrayList<DomicilioModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from DomicilioModel WHERE id IN :pkList");
            query.setParameterList("pkList", pkList);
            
            domicilios = query.list();
                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return domicilios;
    }

    public DomicilioModel getByPk(Integer pk) {
        List<DomicilioModel> domicilios = new ArrayList<DomicilioModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from DomicilioModel WHERE id=:pk");
            query.setParameter("pk", pk);
            
            domicilios = query.list();
            if(domicilios != null && !domicilios.isEmpty()) {
                return domicilios.get(0);
            }            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
