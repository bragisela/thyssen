/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service;

import com.empresa.thyssenplastic.model.ArticuloModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface ArticuloService {
    
    void save(ArticuloModel clienteModel);
    
    void delete(ArticuloModel clienteModel);
    
    List<ArticuloModel> getAll();
    
    List<ArticuloModel> getAllByCliente(Integer idCliente);
    
    ArticuloModel getByPk(Integer pk);
}
