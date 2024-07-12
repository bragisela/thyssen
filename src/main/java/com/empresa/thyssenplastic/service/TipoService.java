/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service;

import com.empresa.thyssenplastic.model.TipoModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface TipoService {
    
    void save(TipoModel tipoModel);
    
    void delete(TipoModel tipoModel);
    
    TipoModel getByPk(Integer pk);
    
    List<TipoModel> getAll();
    
    List<TipoModel> getAllByOrder();
    
    List<TipoModel> getByType(String type);
    
    TipoModel getByValor(String valor);
}
