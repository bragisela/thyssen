/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service;

import com.empresa.thyssenplastic.model.RemitoModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface RemitoService {
    
    void save(RemitoModel remitoModel);
    
    void delete(RemitoModel remitoModel);
    
    List<RemitoModel> getAll();
    
    List<RemitoModel> getAllCompletadoAvailable();
    
    List<RemitoModel> getAllAvailable();
    
    RemitoModel getByPk(Integer pk);
}
