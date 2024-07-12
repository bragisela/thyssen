/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.ProveedorContactoDao;
import com.empresa.thyssenplastic.dao.impl.ProveedorContactoDaoImpl;
import com.empresa.thyssenplastic.model.ProveedorContactoModel;
import com.empresa.thyssenplastic.service.ProveedorContactoService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusta
 */
public class ProveedorContactoServiceImpl implements ProveedorContactoService {
    
    public void save(ProveedorContactoModel proveedorContactoModel) {
        try {
            ProveedorContactoDao proveedorContactoDao = new ProveedorContactoDaoImpl();
            proveedorContactoDao.save(proveedorContactoModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(ProveedorContactoModel proveedorContactoModel) {
        try {
            ProveedorContactoDao proveedorContactoDao = new ProveedorContactoDaoImpl();
            proveedorContactoDao.delete(proveedorContactoModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<ProveedorContactoModel> getAll() {
        List<ProveedorContactoModel> proveedoresContactos = new ArrayList<ProveedorContactoModel>();
        try {
            ProveedorContactoDao proveedorContactoDao = new ProveedorContactoDaoImpl();
            proveedoresContactos = proveedorContactoDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return proveedoresContactos;
    }
    
    public List<ProveedorContactoModel> getByPkProveedor(Integer pk) {
        List<ProveedorContactoModel> proveedorContactos = new ArrayList<ProveedorContactoModel>();
        try {
            ProveedorContactoDao proveedorDao = new ProveedorContactoDaoImpl();
            proveedorContactos = proveedorDao.getByPkProveedor(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return proveedorContactos;
    }

    public ProveedorContactoModel getByPkProveedorAndContacto(Integer pkProveedor, Integer pkContacto) {
        ProveedorContactoModel proveedorContacto = null;
        try {
            ProveedorContactoDao proveedorDao = new ProveedorContactoDaoImpl();
            proveedorContacto = proveedorDao.getByPkProveedorAndContacto(pkProveedor, pkContacto);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return proveedorContacto;
    }
    
    public ProveedorContactoModel getByPkContacto(Integer pkContacto) {
        ProveedorContactoModel proveedorContacto = null;
        try {
            ProveedorContactoDao proveedorDao = new ProveedorContactoDaoImpl();
            proveedorContacto = proveedorDao.getByPkContacto(pkContacto);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return proveedorContacto;
    }
    
}
