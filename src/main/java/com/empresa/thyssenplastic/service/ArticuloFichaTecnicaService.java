/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service;

import com.empresa.thyssenplastic.model.ArticuloFichaTecnicaModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface ArticuloFichaTecnicaService {
    
    void save(ArticuloFichaTecnicaModel objectModel);
    
    void delete(ArticuloFichaTecnicaModel objectModel);
    
    List<ArticuloFichaTecnicaModel> getAll();
    
    List<ArticuloFichaTecnicaModel> getAllByArticulo(Integer idArticulo);
    
    ArticuloFichaTecnicaModel getByPk(Integer pk);
}
