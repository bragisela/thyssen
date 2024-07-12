/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.ProveedorDao;
import com.empresa.thyssenplastic.dao.impl.ProveedorDaoImpl;
import com.empresa.thyssenplastic.model.ProveedorModel;
import com.empresa.thyssenplastic.service.ProveedorService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusta
 */
public class ProveedorServiceImpl implements ProveedorService {
    
    public void save(ProveedorModel proveedorModel) {
        try {
            ProveedorDao proveedorDao = new ProveedorDaoImpl();
            proveedorDao.save(proveedorModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(ProveedorModel proveedorModel) {
        try {
            ProveedorDao proveedorDao = new ProveedorDaoImpl();
            proveedorDao.delete(proveedorModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<ProveedorModel> getAll() {
        List<ProveedorModel> proveedores = new ArrayList<ProveedorModel>();
        try {
            ProveedorDao proveedorDao = new ProveedorDaoImpl();
            proveedores = proveedorDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return proveedores;
    }

    public List<ProveedorModel> getAllByRubro(Integer idRubro) {
        List<ProveedorModel> proveedores = new ArrayList<ProveedorModel>();
        try {
            ProveedorDao proveedorDao = new ProveedorDaoImpl();
            proveedores = proveedorDao.getAllByRubro(idRubro);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return proveedores;
    }
    
    public ProveedorModel getByPk(Integer pk) {
        ProveedorModel proveedor = null;
        try {
            ProveedorDao proveedorDao = new ProveedorDaoImpl();
            proveedor = proveedorDao.getByPk(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return proveedor;
    }
    
}
