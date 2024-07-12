/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.AjusteDeInventarioDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.AjusteDeInventarioModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class AjusteDeInventarioDaoImpl extends GenericDaoImpl implements AjusteDeInventarioDao {
    
        public List<AjusteDeInventarioModel> getAll() {
        List<AjusteDeInventarioModel> articulos = new ArrayList<AjusteDeInventarioModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            articulos = session.createQuery("from AjusteDeInventarioModel", AjusteDeInventarioModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return articulos;
    }

    public AjusteDeInventarioModel getByPk(Integer pk) {
        AjusteDeInventarioModel articulo = null;        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from AjusteDeInventarioModel WHERE id=:id");
                query.setInteger("id", pk);
                
                List<AjusteDeInventarioModel> articulos = query.list();
                if(articulos != null && !articulos.isEmpty()) {
                    articulo = articulos.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return articulo;
    }

}
