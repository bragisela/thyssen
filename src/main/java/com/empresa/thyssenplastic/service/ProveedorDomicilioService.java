/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service;

import com.empresa.thyssenplastic.model.ProveedorDomicilioModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface ProveedorDomicilioService {
    
    void save(ProveedorDomicilioModel proveedorDomicilioModel);
    
    void delete(ProveedorDomicilioModel proveedorDomicilioModel);
    
    List<ProveedorDomicilioModel> getAll();
    
    List<ProveedorDomicilioModel> getByPkProveedor(Integer pk);
    
    ProveedorDomicilioModel getByPkProveedorAndDomicilio(Integer pkProveedor, Integer pkDomicilio);
}
