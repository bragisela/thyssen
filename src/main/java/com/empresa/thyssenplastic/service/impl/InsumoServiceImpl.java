/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.InsumoDao;
import com.empresa.thyssenplastic.dao.impl.InsumoDaoImpl;
import com.empresa.thyssenplastic.model.InsumoModel;
import com.empresa.thyssenplastic.service.InsumoService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusta
 */
public class InsumoServiceImpl implements InsumoService {
    
    public void save(InsumoModel insumoModel) {
        try {
            InsumoDao insumoDao = new InsumoDaoImpl();
            insumoDao.save(insumoModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(InsumoModel insumoModel) {
        try {
            InsumoDao insumoDao = new InsumoDaoImpl();
            insumoDao.delete(insumoModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<InsumoModel> getAll() {
        List<InsumoModel> insumos = new ArrayList<InsumoModel>();
        try {
            InsumoDao insumoDao = new InsumoDaoImpl();
            insumos = insumoDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return insumos;
    }

    public List<InsumoModel> getAllByIdProveedor(Integer idProveedor) {
        List<InsumoModel> insumos = new ArrayList<InsumoModel>();
        try {
            InsumoDao insumoDao = new InsumoDaoImpl();
            insumos = insumoDao.getAllByIdProveedor(idProveedor);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return insumos;
    }
    
    public InsumoModel getByPk(Integer pk) {
        InsumoModel insumo = null;
        try {
            InsumoDao insumoDao = new InsumoDaoImpl();
            insumo = insumoDao.getByPk(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return insumo;
    }
    
}
