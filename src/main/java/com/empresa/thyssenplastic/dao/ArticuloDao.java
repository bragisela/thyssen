/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao;

import com.empresa.thyssenplastic.model.ArticuloModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface ArticuloDao extends GenericDao {
    
    List<ArticuloModel> getAll();
            
    ArticuloModel getByPk(Integer pk);
    
    List<ArticuloModel> getAllByCliente(Integer idCliente);
}
