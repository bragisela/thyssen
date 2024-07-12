/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.OrdenDeCompraItemRecepcionDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.OrdenDeCompraItemRecepcionModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class OrdenDeCompraItemRecepcionDaoImpl extends GenericDaoImpl implements OrdenDeCompraItemRecepcionDao {
    
    public List<OrdenDeCompraItemRecepcionModel> getAllByOrdenDeCompraItem(Integer idOrdenDeCompraItem) {
        
        List<OrdenDeCompraItemRecepcionModel> ordenDeCompraItemsRecepcion = new ArrayList<OrdenDeCompraItemRecepcionModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from OrdenDeCompraItemRecepcionModel WHERE idOrdenDeCompraItem=:idOrdenDeCompraItem");
            query.setInteger("idOrdenDeCompraItem", idOrdenDeCompraItem);

            ordenDeCompraItemsRecepcion = query.list();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeCompraItemsRecepcion;
    }

    public OrdenDeCompraItemRecepcionModel getByPk(Integer pk) {
        
        OrdenDeCompraItemRecepcionModel ordenDeCompraItemRecepcion = null;        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from OrdenDeCompraItemRecepcionModel WHERE id=:id");
                query.setInteger("id", pk);
                
                List<OrdenDeCompraItemRecepcionModel> ordenDeCompraItemsRecepcion = query.list();
                if(ordenDeCompraItemsRecepcion != null && !ordenDeCompraItemsRecepcion.isEmpty()) {
                    ordenDeCompraItemRecepcion = ordenDeCompraItemsRecepcion.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeCompraItemRecepcion;
    }

}
