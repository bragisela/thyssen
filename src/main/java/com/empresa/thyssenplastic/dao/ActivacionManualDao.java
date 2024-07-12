/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao;
import com.empresa.thyssenplastic.model.ActivacionManualModel;
import java.util.List;


/**
 *
 * @author gusta
 */
public interface ActivacionManualDao extends GenericDao {
    
    List<ActivacionManualModel> getAll();
    
    
    ActivacionManualModel getByPk(Integer pk);
    
}
