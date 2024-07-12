/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.ArticuloEtiquetaModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import com.empresa.thyssenplastic.dao.ArticuloEtiquetaDao;

/**
 *
 * @author gusta
 */
public class ArticuloEtiquetaDaoImpl extends GenericDaoImpl implements ArticuloEtiquetaDao {
    
    public List<ArticuloEtiquetaModel> getAll() {
        List<ArticuloEtiquetaModel> articulosetiqueta = new ArrayList<ArticuloEtiquetaModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            articulosetiqueta = session.createQuery("from ArticuloEtiquetaModel ORDER BY id", ArticuloEtiquetaModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return articulosetiqueta;
    }

    public List<ArticuloEtiquetaModel> getAllByArticulo(Integer articuloPk) {
        List<ArticuloEtiquetaModel> articulosetiqueta = new ArrayList<ArticuloEtiquetaModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from ArticuloEtiquetaModel WHERE idArticulo=:idArticulo ORDER BY id DESC");                        
            query.setInteger("idArticulo", articuloPk);

            articulosetiqueta = query.list();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return articulosetiqueta;
    }
       

    public ArticuloEtiquetaModel getByPk(Integer pk) {
        ArticuloEtiquetaModel articuloetiqueta = null;        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from ArticuloEtiquetaModel WHERE id=:id");
                query.setInteger("id", pk);
                
                List<ArticuloEtiquetaModel> articulosetiqueta = query.list();
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
