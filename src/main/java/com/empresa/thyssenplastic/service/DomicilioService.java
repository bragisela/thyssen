/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service;

import com.empresa.thyssenplastic.model.DomicilioModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface DomicilioService {
    
    void save(DomicilioModel domicilioModel);
    
    void delete(DomicilioModel domicilioModel);
    
    DomicilioModel getByPk(Integer pk);
    
    List<DomicilioModel> getAll();
    
    List<DomicilioModel> getByPkList(List<Integer> pkList);
}
