/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao;

import com.empresa.thyssenplastic.model.TipoModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface TipoDao extends GenericDao {
    
    List<TipoModel> getAll();
    
    List<TipoModel> getAllByOrder();
    
    TipoModel getByPk(Integer pk);
    
    List<TipoModel> getByType(String type);
    
    TipoModel getByValor(String valor);
}
