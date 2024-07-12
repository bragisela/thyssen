/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.ArticuloDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.ArticuloModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class ArticuloDaoImpl extends GenericDaoImpl implements ArticuloDao {
    
        public List<ArticuloModel> getAll() {
        List<ArticuloModel> articulos = new ArrayList<ArticuloModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            articulos = session.createQuery("from ArticuloModel", ArticuloModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return articulos;
    }

    public ArticuloModel getByPk(Integer pk) {
        ArticuloModel articulo = null;        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from ArticuloModel WHERE id=:id");
                query.setInteger("id", pk);
                
                List<ArticuloModel> articulos = query.list();
                if(articulos != null && !articulos.isEmpty()) {
                    articulo = articulos.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return articulo;
    }

    public List<ArticuloModel> getAllByCliente(Integer idCliente) {
        List<ArticuloModel> articulos = new ArrayList<ArticuloModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from ArticuloModel WHERE idCliente =:idCliente ORDER BY denominacion", ArticuloModel.class);
            query.setParameter("idCliente", idCliente);
            
            return query.list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return articulos;
    }
    
}
