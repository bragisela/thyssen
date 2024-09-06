/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.RemitoDao;
import com.empresa.thyssenplastic.dao.RemitoDetalleDao;
import com.empresa.thyssenplastic.dao.RemitoDetalleScrapDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.RemitoDetalleModel;
import com.empresa.thyssenplastic.model.RemitoDetalleScrapModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class RemitoDetalleScrapDaoImpl extends GenericDaoImpl implements RemitoDetalleScrapDao {
    
    public List<RemitoDetalleScrapModel> getAll() {
        List<RemitoDetalleScrapModel> remitoDetallesScrap = new ArrayList<RemitoDetalleScrapModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            remitoDetallesScrap = session.createQuery("from RemitoDetalleScrapModel ", RemitoDetalleScrapModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return remitoDetallesScrap;
    }

    public RemitoDetalleScrapModel getByPk(Integer pk) {
        RemitoDetalleScrapModel remitoDetalleScrap = null;        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from RemitoDetalleScrapModel WHERE id=:id");
                query.setInteger("id", pk);
                
                List<RemitoDetalleScrapModel> remitoDetalles = query.list();
                if(remitoDetalles != null && !remitoDetalles.isEmpty()) {
                    remitoDetalleScrap = remitoDetalles.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return remitoDetalleScrap;
    }
    
    public List<RemitoDetalleScrapModel> getAllByIdOrdenDeProduccionScrap(Integer idOrdenDeProduccionScrap) {
    List<RemitoDetalleScrapModel> remitoDetallesScrap = new ArrayList<RemitoDetalleScrapModel>();
    Session session = null;
    try {
        session = HibernateUtil.getSessionFactory().openSession();
        Query<RemitoDetalleScrapModel> query = session.createQuery(
            "from RemitoDetalleScrapModel WHERE idOrdenDeProduccionScrap = :idOs", 
            RemitoDetalleScrapModel.class
        );
        query.setParameter("idOs", idOrdenDeProduccionScrap);
        remitoDetallesScrap = query.list();
    } catch (Exception ex) {
        ex.printStackTrace();
    } 
    return remitoDetallesScrap;
}

   
    
}
