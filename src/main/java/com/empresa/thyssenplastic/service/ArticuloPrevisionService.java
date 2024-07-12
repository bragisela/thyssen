/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service;

import com.empresa.thyssenplastic.model.ArticuloPrevisionModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface ArticuloPrevisionService {
    
    void save(ArticuloPrevisionModel articuloEtiquetaModel);
    
    void delete(ArticuloPrevisionModel articuloEtiquetaModel);
    
    List<ArticuloPrevisionModel> getAll();
    
    List<ArticuloPrevisionModel> getAllByArticulo(Integer articuloPk);
    
    ArticuloPrevisionModel getByPk(Integer pk);
   
}
