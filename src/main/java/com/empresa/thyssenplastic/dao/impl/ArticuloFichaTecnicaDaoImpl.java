/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.ArticuloFichaTecnicaDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.ArticuloFichaTecnicaModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class ArticuloFichaTecnicaDaoImpl extends GenericDaoImpl implements ArticuloFichaTecnicaDao {
    
        public List<ArticuloFichaTecnicaModel> getAll() {
        List<ArticuloFichaTecnicaModel> articuloFichasTecnica = new ArrayList<ArticuloFichaTecnicaModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            articuloFichasTecnica = session.createQuery("from ArticuloFichaTecnicaModel", ArticuloFichaTecnicaModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return articuloFichasTecnica;
    }

    public List<ArticuloFichaTecnicaModel> getAllByArticulo(Integer idArticulo) {
        List<ArticuloFichaTecnicaModel> articuloFichasTecnica = new ArrayList<ArticuloFichaTecnicaModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from ArticuloFichaTecnicaModel WHERE idArticulo=:idArticulo ORDER BY version DESC");
            query.setInteger("idArticulo", idArticulo);
            articuloFichasTecnica = query.list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return articuloFichasTecnica;
    }
    
    public ArticuloFichaTecnicaModel getByPk(Integer pk) {
        ArticuloFichaTecnicaModel articuloFichaTecnica = null;        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from ArticuloFichaTecnicaModel WHERE id=:id");
                query.setInteger("id", pk);
                
                List<ArticuloFichaTecnicaModel> articuloFichasTecnica = query.list();
                if(articuloFichasTecnica != null && !articuloFichasTecnica.isEmpty()) {
                    articuloFichaTecnica = articuloFichasTecnica.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return articuloFichaTecnica;
    }

}
