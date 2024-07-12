/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service;

import com.empresa.thyssenplastic.model.EgresoDepositoModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface EgresoDepositoService {
    
    void save(EgresoDepositoModel egresoDepositoModel);
    
    void delete(EgresoDepositoModel egresoDepositoModel);
    
    List<EgresoDepositoModel> getAll();
    
    EgresoDepositoModel getByPk(Integer pk);
}
