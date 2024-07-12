/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.LocalidadDao;
import com.empresa.thyssenplastic.dao.impl.LocalidadDaoImpl;
import com.empresa.thyssenplastic.model.LocalidadModel;
import com.empresa.thyssenplastic.service.LocalidadService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusta
 */
public class LocalidadServiceImpl implements LocalidadService {
    
    public void save(LocalidadModel localidadModel) {
        try {
            LocalidadDao localidadDao = new LocalidadDaoImpl();
            localidadDao.save(localidadModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(LocalidadModel localidadModel) {
        try {
            LocalidadDao localidadDao = new LocalidadDaoImpl();
            localidadDao.delete(localidadModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<LocalidadModel> getAll() {
        List<LocalidadModel> localidades = new ArrayList<LocalidadModel>();
        try {
            LocalidadDao localidadDao = new LocalidadDaoImpl();
            localidades = localidadDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return localidades;
    }

    public LocalidadModel getByPk(Integer pk) {
        LocalidadModel localidad = null;
        try {
            LocalidadDao localidadDao = new LocalidadDaoImpl();
            localidad = localidadDao.getByPk(pk);            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return localidad;
    }
    
    public List<LocalidadModel> getByProvincia(Integer idProvincia) {
        List<LocalidadModel> localidades = new ArrayList<LocalidadModel>();
        try {
            LocalidadDao localidadDao = new LocalidadDaoImpl();
            localidades = localidadDao.getByProvincia(idProvincia);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return localidades;        
    }
}
