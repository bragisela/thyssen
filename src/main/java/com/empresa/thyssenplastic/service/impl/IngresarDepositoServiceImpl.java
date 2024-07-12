/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.IngresarDepositoDao;
import com.empresa.thyssenplastic.dao.impl.IngresarDepositoDaoImpl;
import com.empresa.thyssenplastic.model.IngresarDepositoModel;
import com.empresa.thyssenplastic.service.IngresarDepositoService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusta
 */
public class IngresarDepositoServiceImpl implements IngresarDepositoService {
    
    public void save(IngresarDepositoModel ingresarDepositoModel) {
        try {
            IngresarDepositoDao ingresarDepositoDao = new IngresarDepositoDaoImpl();
            ingresarDepositoDao.save(ingresarDepositoModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(IngresarDepositoModel ingresarDepositoModel) {
        try {
            IngresarDepositoDao ingresarDepositoDao = new IngresarDepositoDaoImpl();
            ingresarDepositoDao.delete(ingresarDepositoModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<IngresarDepositoModel> getAll() {
        List<IngresarDepositoModel> ingresarDepositos = new ArrayList<IngresarDepositoModel>();
        try {
            IngresarDepositoDao ingresarDepositoDao = new IngresarDepositoDaoImpl();
            ingresarDepositos = ingresarDepositoDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ingresarDepositos;
    }
    
    public IngresarDepositoModel getByPk(Integer pk) {
        IngresarDepositoModel ingresarDeposito = null;
        try {
            IngresarDepositoDao ingresarDepositoDao = new IngresarDepositoDaoImpl();
            ingresarDeposito = ingresarDepositoDao.getByPk(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ingresarDeposito;
    }
    
}
