/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao;

import com.empresa.thyssenplastic.model.InsumoModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface InsumoDao extends GenericDao {
    
    List<InsumoModel> getAll();
    
    InsumoModel getByPk(Integer pk);
    
    List<InsumoModel> getAllByIdProveedor(Integer idProveedor);
}
