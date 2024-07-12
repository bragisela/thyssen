/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.OrdenDeCompraDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.OrdenDeCompraModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class OrdenDeCompraDaoImpl extends GenericDaoImpl implements OrdenDeCompraDao {
    
    public List<OrdenDeCompraModel> getAll() {
        List<OrdenDeCompraModel> ordenDeCompras = new ArrayList<OrdenDeCompraModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            ordenDeCompras = session.createQuery("from OrdenDeCompraModel ", OrdenDeCompraModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeCompras;
    }

    public OrdenDeCompraModel getByPk(Integer pk) {
        OrdenDeCompraModel ordenDeCompra = null;        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeCompraModel WHERE id=:id");
                query.setInteger("id", pk);
                
                List<OrdenDeCompraModel> ordenDeCompras = query.list();
                if(ordenDeCompras != null && !ordenDeCompras.isEmpty()) {
                    ordenDeCompra = ordenDeCompras.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeCompra;
    }

}
