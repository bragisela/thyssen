/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.RepuestoDao;
import com.empresa.thyssenplastic.dao.impl.RepuestoDaoImpl;
import com.empresa.thyssenplastic.model.RepuestoModel;
import com.empresa.thyssenplastic.service.RepuestoService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusta
 */
public class RepuestoServiceImpl implements RepuestoService {
    
    public void save(RepuestoModel repuestoModel) {
        try {
            RepuestoDao repuestoDao = new RepuestoDaoImpl();
            repuestoDao.save(repuestoModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(RepuestoModel repuestoModel) {
        try {
            RepuestoDao repuestoDao = new RepuestoDaoImpl();
            repuestoDao.delete(repuestoModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<RepuestoModel> getAll() {
        List<RepuestoModel> repuestos = new ArrayList<RepuestoModel>();
        try {
            RepuestoDao repuestoDao = new RepuestoDaoImpl();
            repuestos = repuestoDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return repuestos;
    }

    public List<RepuestoModel> getAllByPk(Integer pk) {
        List<RepuestoModel> repuestos = new ArrayList<RepuestoModel>();
        try {
            RepuestoDao repuestoDao = new RepuestoDaoImpl();
            repuestos = repuestoDao.getAllByPk(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return repuestos;
    }
    
    public RepuestoModel getByPk(Integer pk) {
        RepuestoModel repuesto = null;
        try {
            RepuestoDao repuestoDao = new RepuestoDaoImpl();
            repuesto = repuestoDao.getByPk(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return repuesto;
    }
    
}
