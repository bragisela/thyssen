/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.OrdenDeProduccionDao;
import com.empresa.thyssenplastic.dao.impl.OrdenDeProduccionDaoImpl;
import com.empresa.thyssenplastic.model.OrdenDeProduccionModel;
import com.empresa.thyssenplastic.service.OrdenDeProduccionService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusta
 */
public class OrdenDeProduccionServiceImpl implements OrdenDeProduccionService {
    
    public void save(OrdenDeProduccionModel ordenDeProduccionModel) {
        try {
            OrdenDeProduccionDao ordenDeProduccionDao = new OrdenDeProduccionDaoImpl();
            ordenDeProduccionDao.save(ordenDeProduccionModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(OrdenDeProduccionModel ordenDeProduccionModel) {
        try {
            OrdenDeProduccionDao ordenDeProduccionDao = new OrdenDeProduccionDaoImpl();
            ordenDeProduccionDao.delete(ordenDeProduccionModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<OrdenDeProduccionModel> getAll() {
        List<OrdenDeProduccionModel> ordenDeProducciones = new ArrayList<OrdenDeProduccionModel>();
        try {
            OrdenDeProduccionDao ordenDeProduccionDao = new OrdenDeProduccionDaoImpl();
            ordenDeProducciones = ordenDeProduccionDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProducciones;
    }
    
    public OrdenDeProduccionModel getByPk(Integer pk) {
        OrdenDeProduccionModel ordenDeProduccion = null;
        try {
            OrdenDeProduccionDao ordenDeProduccionDao = new OrdenDeProduccionDaoImpl();
            ordenDeProduccion = ordenDeProduccionDao.getByPk(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccion;
    }
 
    public List<OrdenDeProduccionModel> getAllCompletedWithStock() {
        List<OrdenDeProduccionModel> ordenDeProducciones = new ArrayList<OrdenDeProduccionModel>();
        try {
            OrdenDeProduccionDao ordenDeProduccionDao = new OrdenDeProduccionDaoImpl();
            ordenDeProducciones = ordenDeProduccionDao.getAllCompletedWithStock();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProducciones;
    }

        public List<OrdenDeProduccionModel> getAllWithStock() {
        List<OrdenDeProduccionModel> ordenDeProducciones = new ArrayList<OrdenDeProduccionModel>();
        try {
            OrdenDeProduccionDao ordenDeProduccionDao = new OrdenDeProduccionDaoImpl();
            ordenDeProducciones = ordenDeProduccionDao.getAllWithStock();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProducciones;
    }

    public List<OrdenDeProduccionModel> getAllCompleted() {
        List<OrdenDeProduccionModel> ordenDeProducciones = new ArrayList<OrdenDeProduccionModel>();
        try {
            OrdenDeProduccionDao ordenDeProduccionDao = new OrdenDeProduccionDaoImpl();
            ordenDeProducciones = ordenDeProduccionDao.getAllCompleted();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProducciones;
    }

    public List<OrdenDeProduccionModel> getNotCompleted() {
        List<OrdenDeProduccionModel> ordenDeProducciones = new ArrayList<OrdenDeProduccionModel>();
        try {
            OrdenDeProduccionDao ordenDeProduccionDao = new OrdenDeProduccionDaoImpl();
            ordenDeProducciones = ordenDeProduccionDao.getNotCompleted();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProducciones;
    }    
}
