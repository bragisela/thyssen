/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.RemitoDao;
import com.empresa.thyssenplastic.dao.RemitoDetalleDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.RemitoDetalleModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class RemitoDetalleDaoImpl extends GenericDaoImpl implements RemitoDetalleDao {
    
    public List<RemitoDetalleModel> getAll() {
        List<RemitoDetalleModel> remitoDetalles = new ArrayList<RemitoDetalleModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            remitoDetalles = session.createQuery("from RemitoDetalleModel ", RemitoDetalleModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return remitoDetalles;
    }

    public RemitoDetalleModel getByPk(Integer pk) {
        RemitoDetalleModel remitoDetalle = null;        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from RemitoDetalleModel WHERE id=:id");
                query.setInteger("id", pk);
                
                List<RemitoDetalleModel> remitoDetalles = query.list();
                if(remitoDetalles != null && !remitoDetalles.isEmpty()) {
                    remitoDetalle = remitoDetalles.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return remitoDetalle;
    }

    public List<RemitoDetalleModel> getAllByRemito(Integer idRemito) {
        List<RemitoDetalleModel> remitoDetalles = new ArrayList<RemitoDetalleModel>();
        try {
            if(idRemito != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from RemitoDetalleModel WHERE idRemito=:idRemito");
                query.setInteger("idRemito", idRemito);
                
                remitoDetalles = query.list();
            }            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return remitoDetalles;
    }
    
    public RemitoDetalleModel getByCompositeIds(int idRemito, int idDeposito, int idOrdenDeProduccion) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM RemitoDetalleModel WHERE idRemito = :idRemito AND idDeposito = :idDeposito AND idOrdenDeProduccion = :idOrdenDeProduccion";

        // Crea la consulta
        Query<RemitoDetalleModel> query = session.createQuery(hql, RemitoDetalleModel.class);
        query.setParameter("idRemito", idRemito);
        query.setParameter("idDeposito", idDeposito);
        query.setParameter("idOrdenDeProduccion", idOrdenDeProduccion);

  
        List<RemitoDetalleModel> resultList = query.getResultList();

        // Si hay resultados, retorna el primero (suponiendo que no debería haber más de una entrada con esta combinación de IDs)
        if (!resultList.isEmpty()) {
            return resultList.get(0);
        } else {
            return null; // Retorna null si no se encontró ninguna entrada
        }
    }

 
//    public RemitoDetalleModel getByBobina(Integer idBobina) {
//        
//        RemitoDetalleModel remitoDetalle = null;
//        try {
//            if(idBobina != null){
//                Session session = HibernateUtil.getSessionFactory().openSession();
//                Query query = session.createQuery("from RemitoDetalleModel WHERE idBobina=:idBobina");
//                query.setInteger("idBobina", idBobina);
//                
//                List<RemitoDetalleModel> remitoDetalles = query.list();
//                if(remitoDetalles != null && !remitoDetalles.isEmpty()) {
//                    remitoDetalle = remitoDetalles.get(0);
//                }                
//            }            
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        return remitoDetalle;
//    }
//
//    public RemitoDetalleModel getByBulto(Integer idBulto) {
//        
//        RemitoDetalleModel remitoDetalle = null;
//        try {
//            if(idBulto != null){
//                Session session = HibernateUtil.getSessionFactory().openSession();
//                Query query = session.createQuery("from RemitoDetalleModel WHERE idBulto=:idBulto");
//                query.setInteger("idBulto", idBulto);
//                
//                List<RemitoDetalleModel> remitoDetalles = query.list();
//                if(remitoDetalles != null && !remitoDetalles.isEmpty()) {
//                    remitoDetalle = remitoDetalles.get(0);
//                }                
//            }            
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        return remitoDetalle;
//    }
//
//    public RemitoDetalleModel getByPallet(Integer idPallet) {
//        
//        RemitoDetalleModel remitoDetalle = null;
//        try {
//            if(idPallet != null){
//                Session session = HibernateUtil.getSessionFactory().openSession();
//                Query query = session.createQuery("from RemitoDetalleModel WHERE idPallet=:idPallet");
//                query.setInteger("idPallet", idPallet);
//                
//                List<RemitoDetalleModel> remitoDetalles = query.list();
//                if(remitoDetalles != null && !remitoDetalles.isEmpty()) {
//                    remitoDetalle = remitoDetalles.get(0);
//                }                
//            }            
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        return remitoDetalle;
//    }
    
}
