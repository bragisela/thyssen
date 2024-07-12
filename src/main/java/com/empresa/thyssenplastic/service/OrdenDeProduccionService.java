/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service;

import com.empresa.thyssenplastic.model.OrdenDeProduccionModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface OrdenDeProduccionService {
    
    void save(OrdenDeProduccionModel ordenDeProduccionModel);
    
    void delete(OrdenDeProduccionModel ordenDeProduccionModel);
    
    List<OrdenDeProduccionModel> getAll();
    
    OrdenDeProduccionModel getByPk(Integer pk);
    
    List<OrdenDeProduccionModel> getAllCompletedWithStock();
    
    List<OrdenDeProduccionModel> getAllWithStock();
    
    List<OrdenDeProduccionModel> getAllCompleted();
    
    List<OrdenDeProduccionModel> getNotCompleted();
}
