/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.OrdenDeProduccionPalletBultoDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.OrdenDeProduccionPalletBultoModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.Date;

/**
 *
 * @author gusta
 */
public class OrdenDeProduccionPalletBultoDaoImpl extends GenericDaoImpl implements OrdenDeProduccionPalletBultoDao {
    
        public List<OrdenDeProduccionPalletBultoModel> getAll() {
        List<OrdenDeProduccionPalletBultoModel> ordenDeProduccionPallets = new ArrayList<OrdenDeProduccionPalletBultoModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            ordenDeProduccionPallets = session.createQuery("from OrdenDeProduccionPalletBultoModel ", OrdenDeProduccionPalletBultoModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionPallets;
    }

    public OrdenDeProduccionPalletBultoModel getByPk(Integer pk) {
        OrdenDeProduccionPalletBultoModel ordenDeProduccionPallet = null;        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionPalletBultoModel WHERE id=:id");
                query.setInteger("id", pk);
                
                List<OrdenDeProduccionPalletBultoModel> ordenDeProduccionPalletBobinas = query.list();
                if(ordenDeProduccionPalletBobinas != null && !ordenDeProduccionPalletBobinas.isEmpty()) {
                    ordenDeProduccionPallet = ordenDeProduccionPalletBobinas.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionPallet;
    }

    public OrdenDeProduccionPalletBultoModel getByCode(String codigo) {
        OrdenDeProduccionPalletBultoModel ordenDeProduccionPallet = null;        
        try {
            if(codigo != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionPalletBultoModel WHERE codigo=:codigo");
                query.setString("codigo", codigo);
                
                List<OrdenDeProduccionPalletBultoModel> ordenDeProduccionPalletBobinas = query.list();
                if(ordenDeProduccionPalletBobinas != null && !ordenDeProduccionPalletBobinas.isEmpty()) {
                    ordenDeProduccionPallet = ordenDeProduccionPalletBobinas.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionPallet;
    }

    public List<OrdenDeProduccionPalletBultoModel> getAllByOrdenDeProduccionPallet(Integer idOrdenDeProduccionPallet) {
        
        List<OrdenDeProduccionPalletBultoModel> ordenDeProduccionPallets = null;
        try {
            if(idOrdenDeProduccionPallet != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionPalletBultoModel WHERE idOrdenDeProduccionPallet=:idOrdenDeProduccionPallet");
                query.setInteger("idOrdenDeProduccionPallet", idOrdenDeProduccionPallet);
                
                ordenDeProduccionPallets = query.list();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionPallets;
    }
    
    public List<OrdenDeProduccionPalletBultoModel> getAllByOrdenDeProduccionPalletAndNotRemito(Integer idOrdenDeProduccionPallet) {
        
        List<OrdenDeProduccionPalletBultoModel> ordenDeProduccionPallets = null;
        try {
            if(idOrdenDeProduccionPallet != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                
                Query query = session.createQuery("SELECT opb FROM OrdenDeProduccionPalletBultoModel opb JOIN OrdenDeProduccionBultoModel opb2 ON opb.idOrdenDeProduccionBulto = opb2.id WHERE opb.idOrdenDeProduccionPallet = :idOrdenDeProduccionPallet AND opb2.idRemito IS NULL");
                query.setInteger("idOrdenDeProduccionPallet", idOrdenDeProduccionPallet);
                
                ordenDeProduccionPallets = query.list();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionPallets;
    }
    
    

    public List<OrdenDeProduccionPalletBultoModel> getByOrdenDeProduccionBulto(Integer idOrdenDeProduccionBulto) {
        
        List<OrdenDeProduccionPalletBultoModel> ordenDeProduccionPallets = null;
        try {
            if(idOrdenDeProduccionBulto != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionPalletBultoModel WHERE idOrdenDeProduccionBulto=:idOrdenDeProduccionBulto");
                query.setInteger("idOrdenDeProduccionBulto", idOrdenDeProduccionBulto);
                
                ordenDeProduccionPallets = query.list();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionPallets;
    }
    
    public List<OrdenDeProduccionPalletBultoModel> getAllByOrdenDeProduccionPalletAndFecha(Integer idOrdenDeProduccionPallet, Date fechaInicio, Date fechaFin) {
        List<OrdenDeProduccionPalletBultoModel> ordenDeProduccionPallets = null;
         System.out.println("*** fechai:"+fechaInicio);
         System.out.println("*** fechaf:"+fechaFin);
        try {
            if(idOrdenDeProduccionPallet != null && fechaInicio != null && fechaFin != null){
                Session session = HibernateUtil.getSessionFactory().openSession();

                Query query = session.createQuery("FROM OrdenDeProduccionPalletBultoModel WHERE idOrdenDeProduccionPallet = :idOrdenDeProduccionPallet AND fechaAlta >= :fechaInicio AND fechaAlta <= :fechaFin");
                query.setParameter("idOrdenDeProduccionPallet", idOrdenDeProduccionPallet);
                // Ajusta las fechas para incluir el rango completo del día
                long tiempoInicio = fechaInicio.getTime();
                tiempoInicio = tiempoInicio / (24 * 60 * 60 * 1000) * (24 * 60 * 60 * 1000);
                Date fechaInicioConHora = new Date(tiempoInicio);

                long tiempoFin = fechaFin.getTime();
                tiempoFin = (tiempoFin / (24 * 60 * 60 * 1000) + 1) * (24 * 60 * 60 * 1000) - 1;
                Date fechaFinConHora = new Date(tiempoFin);

                query.setParameter("fechaInicio", fechaInicioConHora);
                query.setParameter("fechaFin", fechaFinConHora);

                ordenDeProduccionPallets = query.list();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionPallets;
    }

    
}
