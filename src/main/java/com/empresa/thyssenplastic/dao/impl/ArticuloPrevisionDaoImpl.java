/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.ArticuloPrevisionModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import com.empresa.thyssenplastic.dao.ArticuloPrevisionDao;

/**
 *
 * @author gusta
 */
public class ArticuloPrevisionDaoImpl extends GenericDaoImpl implements ArticuloPrevisionDao {
    
    public List<ArticuloPrevisionModel> getAll() {
        List<ArticuloPrevisionModel> articulosetiqueta = new ArrayList<ArticuloPrevisionModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            articulosetiqueta = session.createQuery("from ArticuloPrevisionModel ORDER BY id", ArticuloPrevisionModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return articulosetiqueta;
    }

    public List<ArticuloPrevisionModel> getAllByArticulo(Integer articuloPk) {
        List<ArticuloPrevisionModel> articulosetiqueta = new ArrayList<ArticuloPrevisionModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from ArticuloPrevisionModel WHERE idArticulo=:idArticulo ORDER BY id");                        
            query.setInteger("idArticulo", articuloPk);

            articulosetiqueta = query.list();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return articulosetiqueta;
    }
       

    public ArticuloPrevisionModel getByPk(Integer pk) {
        ArticuloPrevisionModel articuloetiqueta = null;        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from ArticuloPrevisionModel WHERE id=:id");
                query.setInteger("id", pk);
                
                List<ArticuloPrevisionModel> articulosetiqueta = query.list();
                if(articulosetiqueta != null && !articulosetiqueta.isEmpty()) {
                    articuloetiqueta = articulosetiqueta.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return articuloetiqueta;
    }

}
