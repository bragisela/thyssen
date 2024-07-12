/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.OrdenDeProduccionPalletDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.OrdenDeProduccionPalletModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class OrdenDeProduccionPalletDaoImpl extends GenericDaoImpl implements OrdenDeProduccionPalletDao {
    
        public List<OrdenDeProduccionPalletModel> getAll() {
        List<OrdenDeProduccionPalletModel> ordenDeProduccionPallets = new ArrayList<OrdenDeProduccionPalletModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            ordenDeProduccionPallets = session.createQuery("from OrdenDeProduccionPalletModel ", OrdenDeProduccionPalletModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionPallets;
    }

    public OrdenDeProduccionPalletModel getByPk(Integer pk) {
        OrdenDeProduccionPalletModel ordenDeProduccionPallet = null;        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionPalletModel WHERE id=:id");
                query.setInteger("id", pk);
                
                List<OrdenDeProduccionPalletModel> ordenDeProduccionPalletBobinas = query.list();
                if(ordenDeProduccionPalletBobinas != null && !ordenDeProduccionPalletBobinas.isEmpty()) {
                    ordenDeProduccionPallet = ordenDeProduccionPalletBobinas.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionPallet;
    }

    public OrdenDeProduccionPalletModel getByCode(String codigo) {
        OrdenDeProduccionPalletModel ordenDeProduccionPallet = null;        
        try {
            if(codigo != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionPalletModel WHERE codigo=:codigo");
                query.setString("codigo", codigo);
                
                List<OrdenDeProduccionPalletModel> ordenDeProduccionPalletBobinas = query.list();
                if(ordenDeProduccionPalletBobinas != null && !ordenDeProduccionPalletBobinas.isEmpty()) {
                    ordenDeProduccionPallet = ordenDeProduccionPalletBobinas.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionPallet;
    }

    public List<OrdenDeProduccionPalletModel> getAllByOrdenDeProduccion(Integer idOrdenDeProduccion) {
        
        List<OrdenDeProduccionPalletModel> ordenDeProduccionPallets = null;
        try {
            if(idOrdenDeProduccion != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionPalletModel WHERE idOrdenDeProduccion=:idOrdenDeProduccion");
                query.setInteger("idOrdenDeProduccion", idOrdenDeProduccion);
                
                ordenDeProduccionPallets = query.list();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionPallets;
    }
    
    public List<OrdenDeProduccionPalletModel> getAllByOrdenDeProduccionAndFecha(Integer idOrdenDeProduccion, Date fechaInicio, Date fechaFin) {
        
        List<OrdenDeProduccionPalletModel> ordenDeProduccionPallets = null;
        try {
            if(idOrdenDeProduccion != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionPalletModel WHERE idOrdenDeProduccion=:idOrdenDeProduccion AND fechaAlta >= :fechaInicio AND fechaAlta <= :fechaFin");
                query.setParameter("idOrdenDeProduccion", idOrdenDeProduccion);
                
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

    public List<OrdenDeProduccionPalletModel> getAllAvailableForRemitoByOrdenDeProduccion(Integer idOrdenDeProduccion) {
        
        List<OrdenDeProduccionPalletModel> ordenDeProduccionPallets = null;
        try {
            if(idOrdenDeProduccion != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionPalletModel WHERE idOrdenDeProduccion=:idOrdenDeProduccion AND estaDisponibleParaRemito = 1");
                query.setInteger("idOrdenDeProduccion", idOrdenDeProduccion);
                
                ordenDeProduccionPallets = query.list();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionPallets;
    }

    public List<OrdenDeProduccionPalletModel> getAllNotAvailableForRemitoByOrdenDeProduccion(Integer idOrdenDeProduccion) {
        
        List<OrdenDeProduccionPalletModel> ordenDeProduccionPallets = null;
        try {
            if(idOrdenDeProduccion != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionPalletModel WHERE idOrdenDeProduccion=:idOrdenDeProduccion AND estaDisponibleParaRemito = 0");
                query.setInteger("idOrdenDeProduccion", idOrdenDeProduccion);
                
                ordenDeProduccionPallets = query.list();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionPallets;
    }
    
}
