/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service;

import com.empresa.thyssenplastic.model.ConfiguracionModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface ConfiguracionService {
    
    void save(ConfiguracionModel configuracionModel);
    
    void delete(ConfiguracionModel configuracionModel);
    
    List<ConfiguracionModel> getAll();
    
    ConfiguracionModel getByPk(Integer pk);
    
    List<ConfiguracionModel> getAllByIdTipo(Integer idTipo);
}
