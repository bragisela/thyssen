/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.EgresoDepositoDao;
import com.empresa.thyssenplastic.dao.impl.EgresoDepositoDaoImpl;
import com.empresa.thyssenplastic.model.EgresoDepositoModel;
import com.empresa.thyssenplastic.service.EgresoDepositoService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusta
 */
public class EgresoDepositoServiceImpl implements EgresoDepositoService {
    
    public void save(EgresoDepositoModel egresoDepositoModel) {
        try {
            EgresoDepositoDao egresoDepositoDao = new EgresoDepositoDaoImpl();
            System.out.print(egresoDepositoModel.getIdRemito());
            egresoDepositoDao.save(egresoDepositoModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(EgresoDepositoModel egresoDepositoModel) {
        try {
            EgresoDepositoDao egresoDepositoDao = new EgresoDepositoDaoImpl();
            egresoDepositoDao.delete(egresoDepositoModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<EgresoDepositoModel> getAll() {
        List<EgresoDepositoModel> egresoDepositos = new ArrayList<EgresoDepositoModel>();
        try {
            EgresoDepositoDao egresoDepositoDao = new EgresoDepositoDaoImpl();
            egresoDepositos = egresoDepositoDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return egresoDepositos;
    }
    
    public EgresoDepositoModel getByPk(Integer pk) {
        EgresoDepositoModel egresoDeposito = null;
        try {
            EgresoDepositoDao egresoDepositoDao = new EgresoDepositoDaoImpl();
            egresoDeposito = egresoDepositoDao.getByPk(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return egresoDeposito;
    }
    
}
