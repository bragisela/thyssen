/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.InsumoDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.InsumoModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class InsumoDaoImpl extends GenericDaoImpl implements InsumoDao {
    
    public List<InsumoModel> getAll() {
        List<InsumoModel> insumos = new ArrayList<InsumoModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            insumos = session.createQuery("from InsumoModel ORDER BY descripcion", InsumoModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return insumos;
    }

        public List<InsumoModel> getAllByIdProveedor(Integer idProveedor) {
        List<InsumoModel> insumos = new ArrayList<InsumoModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from InsumoModel WHERE idProveedor =:idProveedor ORDER BY descripcion", InsumoModel.class);
            query.setParameter("idProveedor", idProveedor);
            
            return query.list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return insumos;
    }

    public InsumoModel getByPk(Integer pk) {
        InsumoModel insumo = null;        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from InsumoModel WHERE id=:id");
                query.setInteger("id", pk);
                
                List<InsumoModel> insumos = query.list();
                if(insumos != null && !insumos.isEmpty()) {
                    insumo = insumos.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return insumo;
    }

}
