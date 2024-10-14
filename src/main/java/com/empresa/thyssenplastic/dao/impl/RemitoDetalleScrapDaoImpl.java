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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    
    public List<RemitoDetalleScrapModel> getAllByRemito(Integer idRemito) {
        List<RemitoDetalleScrapModel> remitoDetalles = new ArrayList<RemitoDetalleScrapModel>();
        try {
            if(idRemito != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from RemitoDetalleScrapModel WHERE idRemito=:idRemito");
                query.setInteger("idRemito", idRemito);
                
                remitoDetalles = query.list();
            }            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return remitoDetalles;
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
    
    public RemitoDetalleScrapModel getByCodigoAndRemito(String codigo, Integer idRemito) {
        RemitoDetalleScrapModel remitoDetalleScrap = null;        
        Session session = null;
        try {
            if (codigo != null) {
                session = HibernateUtil.getSessionFactory().openSession();
                Query<RemitoDetalleScrapModel> query = session.createQuery("from RemitoDetalleScrapModel where codigo = :codigo and idRemito = :idRemito", RemitoDetalleScrapModel.class);
                query.setParameter("codigo", codigo);
                query.setParameter("idRemito", idRemito);

                List<RemitoDetalleScrapModel> remitoDetalles = query.list();
                if (remitoDetalles != null && !remitoDetalles.isEmpty()) {
                    remitoDetalleScrap = remitoDetalles.get(0);
                }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } 

            return remitoDetalleScrap;
    }
    
    public Double getSumaCantidadUtilizadaByCodigo(String codigo) {
        Double sumaCantidadUtilizada = 0.0;
        Session session = null;
        try {
            if (codigo != null) {
                session = HibernateUtil.getSessionFactory().openSession();
                Query<Double> query = session.createQuery("select sum(cantidadUtilizadaRemito) from RemitoDetalleScrapModel where codigo = :codigo and dadoDeBaja = 0", Double.class);
                query.setParameter("codigo", codigo);

                sumaCantidadUtilizada = query.uniqueResult(); // Devuelve la suma total
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return sumaCantidadUtilizada != null ? sumaCantidadUtilizada : 0.0;
    }
    
    public Map<String, Double> getMapSumaCantidadUtilizadaByCodigo(String codigo) {
        Session session = null;
        Map<String, Double> resultado = new HashMap<String, Double>();
        resultado.put("noDadoDeBaja", 0.0);
        resultado.put("dadoDeBaja", 0.0);

        try {
            if (codigo != null) {
                session = HibernateUtil.getSessionFactory().openSession();

                Query<Object[]> query = session.createQuery(
                    "select dadoDeBaja, sum(cantidadUtilizadaRemito) " +
                    "from RemitoDetalleScrapModel " +
                    "where codigo = :codigo " +
                    "group by dadoDeBaja", Object[].class
                );
                query.setParameter("codigo", codigo);

                List<Object[]> results = query.getResultList();
                for (Object[] result : results) {
                    Boolean dadoDeBaja = (Boolean) result[0];
                    Double suma = (Double) result[1];

                    if (dadoDeBaja != null && dadoDeBaja) {
                        resultado.put("dadoDeBaja", suma != null ? suma : 0.0);
                    } else {
                        resultado.put("noDadoDeBaja", suma != null ? suma : 0.0);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return resultado;
    }


    
}
