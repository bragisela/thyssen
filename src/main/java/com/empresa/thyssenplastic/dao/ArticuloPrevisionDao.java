/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao;

import com.empresa.thyssenplastic.model.ArticuloPrevisionModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface ArticuloPrevisionDao extends GenericDao {
    
    List<ArticuloPrevisionModel> getAll();
    
    ArticuloPrevisionModel getByPk(Integer pk);
 
    List<ArticuloPrevisionModel> getAllByArticulo(Integer articuloPk);
}
