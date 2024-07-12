/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.OrdenDeCompraItemRecepcionDao;
import com.empresa.thyssenplastic.dao.impl.OrdenDeCompraItemRecepcionDaoImpl;
import com.empresa.thyssenplastic.model.OrdenDeCompraItemRecepcionModel;
import com.empresa.thyssenplastic.service.OrdenDeCompraItemRecepcionService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusta
 */
public class OrdenDeCompraItemRecepcionServiceImpl implements OrdenDeCompraItemRecepcionService {
    
    public void save(OrdenDeCompraItemRecepcionModel ordenDeCompraItemRecepcionModel) {
        try {
            OrdenDeCompraItemRecepcionDao ordenDeCompraItemRecepcionDao = new OrdenDeCompraItemRecepcionDaoImpl();
            ordenDeCompraItemRecepcionDao.save(ordenDeCompraItemRecepcionModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(OrdenDeCompraItemRecepcionModel ordenDeCompraItemRecepcionModel) {
        try {
            OrdenDeCompraItemRecepcionDao ordenDeCompraItemRecepcionDao = new OrdenDeCompraItemRecepcionDaoImpl();
            ordenDeCompraItemRecepcionDao.delete(ordenDeCompraItemRecepcionModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<OrdenDeCompraItemRecepcionModel> getAllByOrdenDeCompraItem(Integer idOrdenDeCompraItem) {
        
        List<OrdenDeCompraItemRecepcionModel> ordenDeCompraItemsRecepcion = new ArrayList<OrdenDeCompraItemRecepcionModel>();
        try {
            OrdenDeCompraItemRecepcionDao ordenDeCompraItemRecepcionDao = new OrdenDeCompraItemRecepcionDaoImpl();
            ordenDeCompraItemsRecepcion = ordenDeCompraItemRecepcionDao.getAllByOrdenDeCompraItem(idOrdenDeCompraItem);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeCompraItemsRecepcion;
    }
    
    public OrdenDeCompraItemRecepcionModel getByPk(Integer pk) {
        OrdenDeCompraItemRecepcionModel ordenDeCompraItemRecepcion = null;
        try {
            OrdenDeCompraItemRecepcionDao ordenDeCompraItemRecepcionDao = new OrdenDeCompraItemRecepcionDaoImpl();
            ordenDeCompraItemRecepcion = ordenDeCompraItemRecepcionDao.getByPk(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeCompraItemRecepcion;
    }
    
}
