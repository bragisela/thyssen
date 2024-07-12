/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao;

import com.empresa.thyssenplastic.model.RemitoModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface RemitoDao extends GenericDao {
    
    List<RemitoModel> getAll();
    
    List<RemitoModel> getAllCompletadoAvailable();
    
    List<RemitoModel> getAllAvailable();
    
    RemitoModel getByPk(Integer pk);
}
