/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service;

import com.empresa.thyssenplastic.model.AjusteDeInventarioModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface AjusteDeInventarioService {
    
    void save(AjusteDeInventarioModel clienteModel);
    
    void delete(AjusteDeInventarioModel clienteModel);
    
    List<AjusteDeInventarioModel> getAll();
    
    AjusteDeInventarioModel getByPk(Integer pk);
}
