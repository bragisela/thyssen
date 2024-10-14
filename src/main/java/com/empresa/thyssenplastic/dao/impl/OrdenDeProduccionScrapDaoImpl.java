/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.OrdenDeProduccionScrapDao;
import com.empresa.thyssenplastic.dto.DepositoScrapDto;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.OrdenDeProduccionScrapModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class OrdenDeProduccionScrapDaoImpl extends GenericDaoImpl implements OrdenDeProduccionScrapDao {
    
    public List<OrdenDeProduccionScrapModel> getAll() {
        List<OrdenDeProduccionScrapModel> ordenDeProduccionScrap = new ArrayList<OrdenDeProduccionScrapModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            ordenDeProduccionScrap = session.createQuery("from OrdenDeProduccionScrapModel o ORDER BY o.fechaAlta DESC", OrdenDeProduccionScrapModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionScrap;
    }
        
    public List<OrdenDeProduccionScrapModel> getAllPaginated(int pageNumber, int pageSize) {
        List<OrdenDeProduccionScrapModel> ordenDeProduccionScrap = new ArrayList<OrdenDeProduccionScrapModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            ordenDeProduccionScrap = session.createQuery("from OrdenDeProduccionScrapModel o ORDER BY o.fechaAlta DESC", OrdenDeProduccionScrapModel.class)
                                            .setFirstResult((pageNumber - 1) * pageSize)
                                            .setMaxResults(pageSize)
                                            .list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionScrap;
    }
    
        
     public List<DepositoScrapDto> getResumenPorOrdenDeProduccion(int pageNumber, int pageSize) {
        List<DepositoScrapDto> resumenList = new ArrayList<DepositoScrapDto>();
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "SELECT o.idOrdenDeProduccion, SUM(o.pesoTotal - o.cantidadUtilizada) " +
                         "FROM OrdenDeProduccionScrapModel o " +
                         "GROUP BY o.idOrdenDeProduccion";

            List<Object[]> results = session.createQuery(hql)
                                            .setFirstResult((pageNumber - 1) * pageSize)
                                            .setMaxResults(pageSize)
                                            .getResultList();

            // Convertir Object[] a DTO
            for (Object[] result : results) {
                Integer idOrdenDeProduccion = (Integer) result[0];
                Double pesoSuma = ((Number) result[1]).doubleValue(); // Convertir a Double
                resumenList.add(new DepositoScrapDto(idOrdenDeProduccion, pesoSuma));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return resumenList;
    }







    public OrdenDeProduccionScrapModel getByPk(Integer pk) {
        OrdenDeProduccionScrapModel ordenDeProduccionScrap = null;        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionScrapModel WHERE id=:id");
                query.setInteger("id", pk);
                
                List<OrdenDeProduccionScrapModel> ordenDeProduccionScrapList = query.list();
                if(ordenDeProduccionScrapList != null && !ordenDeProduccionScrapList.isEmpty()) {
                    ordenDeProduccionScrap = ordenDeProduccionScrapList.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionScrap;
    }

    public OrdenDeProduccionScrapModel getByCode(String codigo) {
        OrdenDeProduccionScrapModel ordenDeProduccionScrap = null;        
        try {
            if(codigo != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionScrapModel WHERE codigo=:codigo");
                query.setString("codigo", codigo);
                
                List<OrdenDeProduccionScrapModel> ordenDeProduccionScrapList = query.list();
                if(ordenDeProduccionScrapList != null && !ordenDeProduccionScrapList.isEmpty()) {
                    ordenDeProduccionScrap = ordenDeProduccionScrapList.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionScrap;
    }
    
    public OrdenDeProduccionScrapModel getByIdBobina(Integer idBobina) {
        OrdenDeProduccionScrapModel ordenDeProduccionScrap = null;        
        try {
            if(idBobina != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionScrapModel WHERE idBobina=:idBobina");
                query.setInteger("idBobina", idBobina);
                
                List<OrdenDeProduccionScrapModel> ordenDeProduccionScrapList = query.list();
                if(ordenDeProduccionScrapList != null && !ordenDeProduccionScrapList.isEmpty()) {
                    ordenDeProduccionScrap = ordenDeProduccionScrapList.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionScrap;
    }
    
    public OrdenDeProduccionScrapModel getByIdBulto(Integer idBulto) {
        OrdenDeProduccionScrapModel ordenDeProduccionScrap = null;        
        try {
            if(idBulto != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionScrapModel WHERE idBulto=:idBulto");
                query.setInteger("idBulto", idBulto);
                
                List<OrdenDeProduccionScrapModel> ordenDeProduccionScrapList = query.list();
                if(ordenDeProduccionScrapList != null && !ordenDeProduccionScrapList.isEmpty()) {
                    ordenDeProduccionScrap = ordenDeProduccionScrapList.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionScrap;
    }
    
    

    public List<OrdenDeProduccionScrapModel> getAllByOrdenDeProduccion(Integer idOrdenDeProduccion) {
        
        List<OrdenDeProduccionScrapModel> ordenDeProduccionScrap = null;
        try {
            if(idOrdenDeProduccion != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeProduccionScrapModel WHERE idOrdenDeProduccion=:idOrdenDeProduccion");
                query.setInteger("idOrdenDeProduccion", idOrdenDeProduccion);
                
                ordenDeProduccionScrap = query.list();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionScrap;
    }
}
