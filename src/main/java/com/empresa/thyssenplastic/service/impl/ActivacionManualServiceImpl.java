/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.ActivacionManualDao;
import com.empresa.thyssenplastic.dao.impl.ActivacionManualDaoImpl;
import com.empresa.thyssenplastic.model.ActivacionManualModel;
import com.empresa.thyssenplastic.service.ActivacionManualService;
/**
 *
 * @author gusta
 */
public class ActivacionManualServiceImpl implements ActivacionManualService {
    
    public void save(ActivacionManualModel activacionManual) {
        try {
            ActivacionManualDao activacionManualDao = new ActivacionManualDaoImpl();
            activacionManualDao.save(activacionManual);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
        
    public ActivacionManualModel getByPk(Integer pk) {
        ActivacionManualModel activacionmanual = null;
        try {
            ActivacionManualDao activacionManualDao = new ActivacionManualDaoImpl();
            activacionmanual = activacionManualDao.getByPk(1);            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return activacionmanual;
    }

}
