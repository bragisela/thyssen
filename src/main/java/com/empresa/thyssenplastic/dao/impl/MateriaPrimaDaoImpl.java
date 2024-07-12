/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.MateriaPrimaDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.MateriaPrimaModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class MateriaPrimaDaoImpl extends GenericDaoImpl implements MateriaPrimaDao {
    
    public List<MateriaPrimaModel> getAll() {
        List<MateriaPrimaModel> materiasprima = new ArrayList<MateriaPrimaModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            materiasprima = session.createQuery("from MateriaPrimaModel ORDER BY descripcion", MateriaPrimaModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return materiasprima;
    }

    public List<MateriaPrimaModel> getAllByIdProveedor(Integer idProveedor) {
        List<MateriaPrimaModel> materiasprima = new ArrayList<MateriaPrimaModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from MateriaPrimaModel WHERE idProveedor =:idProveedor ORDER BY descripcion", MateriaPrimaModel.class);
            query.setParameter("idProveedor", idProveedor);
            
            return query.list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return materiasprima;
    }

    public MateriaPrimaModel getByPk(Integer pk) {
        MateriaPrimaModel materiaprima = null;        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from MateriaPrimaModel WHERE id=:id");
                query.setInteger("id", pk);
                
                List<MateriaPrimaModel> materiasprima = query.list();
                if(materiasprima != null && !materiasprima.isEmpty()) {
                    materiaprima = materiasprima.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return materiaprima;
    }

}
