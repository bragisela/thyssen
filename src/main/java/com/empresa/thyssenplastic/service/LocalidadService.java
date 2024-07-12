/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service;

import com.empresa.thyssenplastic.model.LocalidadModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface LocalidadService {
    
    void save(LocalidadModel localidadModel);
    
    void delete(LocalidadModel localidadModel);
    
    LocalidadModel getByPk(Integer pk);
    
    List<LocalidadModel> getAll();
    
    List<LocalidadModel> getByProvincia(Integer idProvincia);
}
