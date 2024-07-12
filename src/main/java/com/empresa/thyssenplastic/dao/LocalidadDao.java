/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao;

import com.empresa.thyssenplastic.model.LocalidadModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface LocalidadDao extends GenericDao {
    
    List<LocalidadModel> getAll();
    
    LocalidadModel getByPk(Integer pk);
    
    List<LocalidadModel> getByProvincia(Integer idProvincia);
}
