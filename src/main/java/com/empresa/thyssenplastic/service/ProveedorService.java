/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service;

import com.empresa.thyssenplastic.model.ProveedorModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface ProveedorService {
    
    void save(ProveedorModel proveedorModel);
    
    void delete(ProveedorModel proveedorModel);
    
    List<ProveedorModel> getAll();
    
    List<ProveedorModel> getAllByRubro(Integer idRubro);
    
    ProveedorModel getByPk(Integer pk);
}
