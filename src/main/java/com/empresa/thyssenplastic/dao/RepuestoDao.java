/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao;

import com.empresa.thyssenplastic.model.RepuestoModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface RepuestoDao extends GenericDao {
    
    List<RepuestoModel> getAll();
    
    RepuestoModel getByPk(Integer pk);
    
    List<RepuestoModel> getAllByPk(Integer pk);
}
