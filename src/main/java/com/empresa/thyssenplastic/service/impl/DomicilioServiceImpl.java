/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.DomicilioDao;
import com.empresa.thyssenplastic.dao.impl.DomicilioDaoImpl;
import com.empresa.thyssenplastic.model.DomicilioModel;
import com.empresa.thyssenplastic.service.DomicilioService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusta
 */
public class DomicilioServiceImpl implements DomicilioService {
    
    public void save(DomicilioModel domicilioModel) {
        try {
            DomicilioDao domicilioDao = new DomicilioDaoImpl();
            domicilioDao.save(domicilioModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(DomicilioModel domicilioModel) {
        try {
            DomicilioDao domicilioDao = new DomicilioDaoImpl();
            domicilioDao.delete(domicilioModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<DomicilioModel> getAll() {
        List<DomicilioModel> domicilios = new ArrayList<DomicilioModel>();
        try {
            DomicilioDao domicilioDao = new DomicilioDaoImpl();
            domicilios = domicilioDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return domicilios;
    }

    public DomicilioModel getByPk(Integer pk) {
        DomicilioModel domicilio = null;
        try {
            DomicilioDao domicilioDao = new DomicilioDaoImpl();
            domicilio = domicilioDao.getByPk(pk);            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return domicilio;
    }
    
    public List<DomicilioModel> getByPkList(List<Integer> pkList) {
        List<DomicilioModel> domicilios = new ArrayList<DomicilioModel>();
        try {
            DomicilioDao domicilioDao = new DomicilioDaoImpl();
            domicilios = domicilioDao.getByPkList(pkList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return domicilios;        
    }
}
