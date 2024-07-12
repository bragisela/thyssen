/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.OrdenDeCompraItemDao;
import com.empresa.thyssenplastic.dao.impl.OrdenDeCompraItemDaoImpl;
import com.empresa.thyssenplastic.model.OrdenDeCompraItemModel;
import com.empresa.thyssenplastic.service.OrdenDeCompraItemService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusta
 */
public class OrdenDeCompraItemServiceImpl implements OrdenDeCompraItemService {
    
    public void save(OrdenDeCompraItemModel ordenDeCompraItemModel) {
        try {
            OrdenDeCompraItemDao ordenDeCompraItemDao = new OrdenDeCompraItemDaoImpl();
            ordenDeCompraItemDao.save(ordenDeCompraItemModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(OrdenDeCompraItemModel ordenDeCompraItemModel) {
        try {
            OrdenDeCompraItemDao ordenDeCompraItemDao = new OrdenDeCompraItemDaoImpl();
            ordenDeCompraItemDao.delete(ordenDeCompraItemModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<OrdenDeCompraItemModel> getAllByOrdenDeCompra(Integer idOrdenDeCompra) {
        
        List<OrdenDeCompraItemModel> ordenDeCompraItemItems = new ArrayList<OrdenDeCompraItemModel>();
        try {
            OrdenDeCompraItemDao ordenDeCompraItemDao = new OrdenDeCompraItemDaoImpl();
            ordenDeCompraItemItems = ordenDeCompraItemDao.getAllByOrdenDeCompra(idOrdenDeCompra);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeCompraItemItems;
    }
    
    public OrdenDeCompraItemModel getByPk(Integer pk) {
        OrdenDeCompraItemModel ordenDeCompraItem = null;
        try {
            OrdenDeCompraItemDao ordenDeCompraItemDao = new OrdenDeCompraItemDaoImpl();
            ordenDeCompraItem = ordenDeCompraItemDao.getByPk(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeCompraItem;
    }
    
}
