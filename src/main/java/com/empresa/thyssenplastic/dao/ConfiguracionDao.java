/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao;

import com.empresa.thyssenplastic.model.ConfiguracionModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface ConfiguracionDao extends GenericDao {
    
    List<ConfiguracionModel> getAll();
    
    ConfiguracionModel getByPk(Integer pk);
    
    List<ConfiguracionModel> getAllByIdTipo(Integer idTipo);
}
