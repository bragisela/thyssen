/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao;

import com.empresa.thyssenplastic.model.ArticuloEtiquetaModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface ArticuloEtiquetaDao extends GenericDao {
    
    List<ArticuloEtiquetaModel> getAll();
    
    ArticuloEtiquetaModel getByPk(Integer pk);
 
    List<ArticuloEtiquetaModel> getAllByArticulo(Integer articuloPk);
}
