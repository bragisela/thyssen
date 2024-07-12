/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service;

import com.empresa.thyssenplastic.model.ProveedorContactoModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface ProveedorContactoService {
    
    void save(ProveedorContactoModel proveedorContactoModel);
    
    void delete(ProveedorContactoModel proveedorContactoModel);
    
    List<ProveedorContactoModel> getAll();
    
    List<ProveedorContactoModel> getByPkProveedor(Integer pk);
    
    ProveedorContactoModel getByPkProveedorAndContacto(Integer pkProveedor, Integer pkContacto);
    
    ProveedorContactoModel getByPkContacto(Integer pkContacto);
}
