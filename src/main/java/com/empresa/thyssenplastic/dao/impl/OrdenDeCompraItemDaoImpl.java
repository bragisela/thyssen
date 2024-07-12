/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.OrdenDeCompraDao;
import com.empresa.thyssenplastic.dao.OrdenDeCompraItemDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.OrdenDeCompraItemModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class OrdenDeCompraItemDaoImpl extends GenericDaoImpl implements OrdenDeCompraItemDao {
    
    public List<OrdenDeCompraItemModel> getAllByOrdenDeCompra(Integer idOrdenDeCompra) {
        
        List<OrdenDeCompraItemModel> ordenDeCompraItems = new ArrayList<OrdenDeCompraItemModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from OrdenDeCompraItemModel WHERE idOrdenDeCompra=:idOrdenDeCompra");
            query.setInteger("idOrdenDeCompra", idOrdenDeCompra);

            ordenDeCompraItems = query.list();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeCompraItems;
    }

    public OrdenDeCompraItemModel getByPk(Integer pk) {
        
        OrdenDeCompraItemModel ordenDeCompraItem = null;        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeCompraItemModel WHERE id=:id");
                query.setInteger("id", pk);
                
                List<OrdenDeCompraItemModel> ordenDeCompraItemItems = query.list();
                if(ordenDeCompraItemItems != null && !ordenDeCompraItemItems.isEmpty()) {
                    ordenDeCompraItem = ordenDeCompraItemItems.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeCompraItem;
    }

}
