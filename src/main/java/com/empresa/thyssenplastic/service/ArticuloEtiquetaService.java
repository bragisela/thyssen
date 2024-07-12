/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service;

import com.empresa.thyssenplastic.model.ArticuloEtiquetaModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface ArticuloEtiquetaService {
    
    void save(ArticuloEtiquetaModel articuloEtiquetaModel);
    
    void delete(ArticuloEtiquetaModel articuloEtiquetaModel);
    
    List<ArticuloEtiquetaModel> getAll();
    
    List<ArticuloEtiquetaModel> getAllByArticulo(Integer articuloPk);
    
    ArticuloEtiquetaModel getByPk(Integer pk);
   
}
