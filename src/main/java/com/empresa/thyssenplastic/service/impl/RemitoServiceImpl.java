/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.RemitoDao;
import com.empresa.thyssenplastic.dao.impl.RemitoDaoImpl;
import com.empresa.thyssenplastic.model.RemitoModel;
import com.empresa.thyssenplastic.service.RemitoService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusta
 */
public class RemitoServiceImpl implements RemitoService {
    
    public void save(RemitoModel remitoModel) {
        try {
            RemitoDao remitoDao = new RemitoDaoImpl();
            remitoDao.save(remitoModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(RemitoModel remitoModel) {
        try {
            RemitoDao remitoDao = new RemitoDaoImpl();
            remitoDao.delete(remitoModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<RemitoModel> getAll() {
        List<RemitoModel> remitos = new ArrayList<RemitoModel>();
        try {
            RemitoDao remitoDao = new RemitoDaoImpl();
            remitos = remitoDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return remitos;
    }

    public List<RemitoModel> getAllCompletadoAvailable() {
        List<RemitoModel> remitos = new ArrayList<RemitoModel>();
        try {
            RemitoDao remitoDao = new RemitoDaoImpl();
            remitos = remitoDao.getAllCompletadoAvailable();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return remitos;
    }

    public List<RemitoModel> getAllAvailable() {
        List<RemitoModel> remitos = new ArrayList<RemitoModel>();
        try {
            RemitoDao remitoDao = new RemitoDaoImpl();
            remitos = remitoDao.getAllAvailable();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return remitos;
    }
    
    public RemitoModel getByPk(Integer pk) {
        RemitoModel remito = null;
        try {
            RemitoDao remitoDao = new RemitoDaoImpl();
            remito = remitoDao.getByPk(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return remito;
    }
    
}
