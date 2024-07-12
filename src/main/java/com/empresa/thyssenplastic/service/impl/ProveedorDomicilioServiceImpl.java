/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.ProveedorDomicilioDao;
import com.empresa.thyssenplastic.dao.impl.ProveedorDomicilioDaoImpl;
import com.empresa.thyssenplastic.model.ProveedorDomicilioModel;
import com.empresa.thyssenplastic.service.ProveedorDomicilioService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusta
 */
public class ProveedorDomicilioServiceImpl implements ProveedorDomicilioService {
    
    public void save(ProveedorDomicilioModel proveedorDomicilioModel) {
        try {
            ProveedorDomicilioDao proveedorDomicilioDao = new ProveedorDomicilioDaoImpl();
            proveedorDomicilioDao.save(proveedorDomicilioModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(ProveedorDomicilioModel proveedorDomicilioModel) {
        try {
            ProveedorDomicilioDao proveedorDomicilioDao = new ProveedorDomicilioDaoImpl();
            proveedorDomicilioDao.delete(proveedorDomicilioModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<ProveedorDomicilioModel> getAll() {
        List<ProveedorDomicilioModel> proveedoresDomicilios = new ArrayList<ProveedorDomicilioModel>();
        try {
            ProveedorDomicilioDao proveedorDomicilioDao = new ProveedorDomicilioDaoImpl();
            proveedoresDomicilios = proveedorDomicilioDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return proveedoresDomicilios;
    }
    
    public List<ProveedorDomicilioModel> getByPkProveedor(Integer pk) {
        List<ProveedorDomicilioModel> proveedorDomicilios = new ArrayList<ProveedorDomicilioModel>();
        try {
            ProveedorDomicilioDao proveedorDao = new ProveedorDomicilioDaoImpl();
            proveedorDomicilios = proveedorDao.getByPkProveedor(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return proveedorDomicilios;
    }

    public ProveedorDomicilioModel getByPkProveedorAndDomicilio(Integer pkProveedor, Integer pkDomicilio) {
        ProveedorDomicilioModel proveedorDomicilio = null;
        try {
            ProveedorDomicilioDao proveedorDao = new ProveedorDomicilioDaoImpl();
            proveedorDomicilio = proveedorDao.getByPkProveedorAndDomicilio(pkProveedor, pkDomicilio);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return proveedorDomicilio;
    }
    
    
}
