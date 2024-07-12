/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.OrdenDeCompraDao;
import com.empresa.thyssenplastic.dao.impl.OrdenDeCompraDaoImpl;
import com.empresa.thyssenplastic.model.OrdenDeCompraModel;
import com.empresa.thyssenplastic.service.OrdenDeCompraService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusta
 */
public class OrdenDeCompraServiceImpl implements OrdenDeCompraService {
    
    public void save(OrdenDeCompraModel ordenDeCompraModel) {
        try {
            OrdenDeCompraDao ordenDeCompraDao = new OrdenDeCompraDaoImpl();
            ordenDeCompraDao.save(ordenDeCompraModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(OrdenDeCompraModel ordenDeCompraModel) {
        try {
            OrdenDeCompraDao ordenDeCompraDao = new OrdenDeCompraDaoImpl();
            ordenDeCompraDao.delete(ordenDeCompraModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<OrdenDeCompraModel> getAll() {
        List<OrdenDeCompraModel> ordenDeCompras = new ArrayList<OrdenDeCompraModel>();
        try {
            OrdenDeCompraDao ordenDeCompraDao = new OrdenDeCompraDaoImpl();
            ordenDeCompras = ordenDeCompraDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeCompras;
    }
    
    public OrdenDeCompraModel getByPk(Integer pk) {
        OrdenDeCompraModel ordenDeCompra = null;
        try {
            OrdenDeCompraDao ordenDeCompraDao = new OrdenDeCompraDaoImpl();
            ordenDeCompra = ordenDeCompraDao.getByPk(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeCompra;
    }
    
}
