/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.GraficoBobinaDetalleDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.GraficoBobinaDetalleModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class GraficoBobinaDetalleDaoImpl extends GenericDaoImpl implements GraficoBobinaDetalleDao {
    
    public List<GraficoBobinaDetalleModel> getByIdGraficoBobina(Integer idGraficoBobina) {
        
        List<GraficoBobinaDetalleModel> graficosDetalleBobina = new ArrayList<GraficoBobinaDetalleModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from GraficoBobinaDetalleModel WHERE idGraficoBobina=:idGraficoBobina");
            query.setInteger("idGraficoBobina", idGraficoBobina);

            graficosDetalleBobina = query.list();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return graficosDetalleBobina;
    }

    public GraficoBobinaDetalleModel getByPk(Integer pk) {
        
        GraficoBobinaDetalleModel graficoDetalleBobina = null;        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from GraficoBobinaDetalleModel WHERE id=:id");
                query.setInteger("id", pk);
                
                List<GraficoBobinaDetalleModel> graficosDetalleBobina = query.list();
                if(graficosDetalleBobina != null && !graficosDetalleBobina.isEmpty()) {
                    graficoDetalleBobina = graficosDetalleBobina.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return graficoDetalleBobina;
    }

    public List<GraficoBobinaDetalleModel> getAll() {
        List<GraficoBobinaDetalleModel> graficosDetalleBobina = new ArrayList<GraficoBobinaDetalleModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            graficosDetalleBobina = session.createQuery("from GraficoBobinaDetalleModel ", GraficoBobinaDetalleModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return graficosDetalleBobina;
    }    
    
    public GraficoBobinaDetalleModel getMaximoAnguloGraficoBobinaDetalle(Integer idGraficoBobina) {
        List<GraficoBobinaDetalleModel> graficosDetalleBobina = new ArrayList<GraficoBobinaDetalleModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from GraficoBobinaDetalleModel WHERE idGraficoBobina=:idGraficoBobina ORDER BY angulo DESC");
            query.setInteger("idGraficoBobina", idGraficoBobina);

            graficosDetalleBobina = query.list();

            if(graficosDetalleBobina != null && !graficosDetalleBobina.isEmpty()) {
                return graficosDetalleBobina.get(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
    
    public GraficoBobinaDetalleModel obtenerUltimaMedicion(Integer idGraficoBobina) {
        List<GraficoBobinaDetalleModel> graficosDetalleBobina = new ArrayList<GraficoBobinaDetalleModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from GraficoBobinaDetalleModel WHERE idGraficoBobina=:idGraficoBobina ORDER BY medicion DESC");
            query.setInteger("idGraficoBobina", idGraficoBobina);

            graficosDetalleBobina = query.list();

            if(graficosDetalleBobina != null && !graficosDetalleBobina.isEmpty()) {
                return graficosDetalleBobina.get(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
