/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service;

import com.empresa.thyssenplastic.model.IngresarDepositoModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface IngresarDepositoService {
    
    void save(IngresarDepositoModel ingresarDepositoModel);
    
    void delete(IngresarDepositoModel ingresarDepositoModel);
    
    List<IngresarDepositoModel> getAll();
    
    IngresarDepositoModel getByPk(Integer pk);
}
