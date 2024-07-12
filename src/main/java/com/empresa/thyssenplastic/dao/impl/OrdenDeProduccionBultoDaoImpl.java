/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.OrdenDeProduccionBultoDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.OrdenDeProduccionBultoModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.Date;

/**
 *
 * @author gusta
 */
public class OrdenDeProduccionBultoDaoImpl extends GenericDaoImpl implements OrdenDeProduccionBultoDao {
    
        public List<OrdenDeProduccionBultoModel> getAll() {
        List<OrdenDeProduccionBultoModel> ordenDeProduccionBultos = new ArrayList<OrdenDeProduccionBultoModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            ordenDeProduccionBultos = session.createQuery("from OrdenDeProduccionBultoModel ", OrdenDeProduccionBultoModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionBultos;
    }

    public OrdenDeProduccionBultoModel getByPk(Integer pk) {
        OrdenDeProduccionBultoModel ordenDeProduccionBulto = null;        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionBultoModel WHERE id=:id");
                query.setInteger("id", pk);
                
                List<OrdenDeProduccionBultoModel> ordenDeProduccionBultoBobinas = query.list();
                if(ordenDeProduccionBultoBobinas != null && !ordenDeProduccionBultoBobinas.isEmpty()) {
                    ordenDeProduccionBulto = ordenDeProduccionBultoBobinas.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionBulto;
    }

    public OrdenDeProduccionBultoModel getByCode(String codigo) {
        OrdenDeProduccionBultoModel ordenDeProduccionBulto = null;        
        try {
            if(codigo != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionBultoModel WHERE codigo=:codigo");
                query.setString("codigo", codigo);
                
                List<OrdenDeProduccionBultoModel> ordenDeProduccionBultoBobinas = query.list();
                if(ordenDeProduccionBultoBobinas != null && !ordenDeProduccionBultoBobinas.isEmpty()) {
                    ordenDeProduccionBulto = ordenDeProduccionBultoBobinas.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionBulto;
    }

    public List<OrdenDeProduccionBultoModel> getAllByOrdenDeProduccion(Integer idOrdenDeProduccion) {
        
        List<OrdenDeProduccionBultoModel> ordenDeProduccionBultos = null;
        try {
            if(idOrdenDeProduccion != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionBultoModel WHERE idOrdenDeProduccion=:idOrdenDeProduccion");
                query.setInteger("idOrdenDeProduccion", idOrdenDeProduccion);
                
                ordenDeProduccionBultos = query.list();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionBultos;
    }

    public List<OrdenDeProduccionBultoModel> getAllAvailableByOrdenDeProduccion(Integer idOrdenDeProduccion) {
        
        List<OrdenDeProduccionBultoModel> ordenDeProduccionBultos = null;
        try {
            if(idOrdenDeProduccion != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionBultoModel WHERE idOrdenDeProduccion=:idOrdenDeProduccion AND estaEnPallet = 0");
                query.setInteger("idOrdenDeProduccion", idOrdenDeProduccion);
                
                ordenDeProduccionBultos = query.list();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionBultos;
    }
    
    public OrdenDeProduccionBultoModel getByOrdenDeProduccionBobina(Integer idOrdenDeProduccionBobina) {
        OrdenDeProduccionBultoModel ordenDeProduccionBulto = null;        
        try {
            if(idOrdenDeProduccionBobina != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionBultoModel WHERE idOrdenDeProduccionBobina=:idOrdenDeProduccionBobina");
                query.setInteger("idOrdenDeProduccionBobina", idOrdenDeProduccionBobina);
                
                List<OrdenDeProduccionBultoModel> ordenDeProduccionBultoBobinas = query.list();
                if(ordenDeProduccionBultoBobinas != null && !ordenDeProduccionBultoBobinas.isEmpty()) {
                    ordenDeProduccionBulto = ordenDeProduccionBultoBobinas.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionBulto;
    }
 
    public List<OrdenDeProduccionBultoModel> getAllAvailableForRemitoByOrdenDeProduccion(Integer idOrdenDeProduccion) {
        
        List<OrdenDeProduccionBultoModel> ordenDeProduccionBultos = null;
        try {
            if(idOrdenDeProduccion != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionBultoModel WHERE idOrdenDeProduccion=:idOrdenDeProduccion AND estaDisponibleParaRemito = 1");
                query.setInteger("idOrdenDeProduccion", idOrdenDeProduccion);
                
                ordenDeProduccionBultos = query.list();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionBultos;
    }

    public List<OrdenDeProduccionBultoModel> getAllNotAvailableForRemitoByOrdenDeProduccion(Integer idOrdenDeProduccion) {
        
        List<OrdenDeProduccionBultoModel> ordenDeProduccionBultos = null;
        try {
            if(idOrdenDeProduccion != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionBultoModel WHERE idOrdenDeProduccion=:idOrdenDeProduccion AND estaDisponibleParaRemito = 0");
                query.setInteger("idOrdenDeProduccion", idOrdenDeProduccion);
                
                ordenDeProduccionBultos = query.list();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionBultos;
    }
    
    public List<OrdenDeProduccionBultoModel> getByOrdenDeProduccionAndFechaAlta(Integer idOrdenDeProduccion, Date fechaDesde) {
        List<OrdenDeProduccionBultoModel> ordenDeProduccionPallets = null;
        try {
            if (idOrdenDeProduccion != null && fechaDesde != null) {
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionBultoModel WHERE idOrdenDeProduccion=:idOrdenDeProduccion AND DATE(fechaAlta)=:fechaDesde");
                query.setInteger("idOrdenDeProduccion", idOrdenDeProduccion);
                query.setDate("fechaDesde", fechaDesde);

                ordenDeProduccionPallets = query.list();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
      return ordenDeProduccionPallets;
    }
    
    public List<OrdenDeProduccionBultoModel> getByFechaAlta(Date fechaDesde) {
        List<OrdenDeProduccionBultoModel> ordenDeProduccionPallets = null;
        try {
            if (fechaDesde != null) {
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionBultoModel WHERE DATE(fechaAlta)=:fechaDesde");
                query.setDate("fechaDesde", fechaDesde);

                ordenDeProduccionPallets = query.list();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
      return ordenDeProduccionPallets;
    }
    
}
