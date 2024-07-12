/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.ActivacionManualDao;
import com.empresa.thyssenplastic.dao.TipoDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.ActivacionManualModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class ActivacionManualDaoImpl extends GenericDaoImpl implements ActivacionManualDao {
    
     public List<ActivacionManualModel> getAll() {
        List<ActivacionManualModel> activacionmanuales = new ArrayList<ActivacionManualModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            activacionmanuales = session.createQuery("from ActivacionManualModel", ActivacionManualModel.class).list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return activacionmanuales;
    }
    

    public ActivacionManualModel getByPk(Integer pk) {
    try {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from ActivacionManualModel WHERE id = :pk");
        query.setParameter("pk", pk);

        List<ActivacionManualModel> activacionmanuals = query.list();
        
        // Verifica si la lista no está vacía antes de intentar acceder al primer elemento
        if (!activacionmanuals.isEmpty()) {
            return activacionmanuals.get(0);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }

    return null;
}
    
}
