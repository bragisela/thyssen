/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.empresa.thyssenplastic.dao;

import com.empresa.thyssenplastic.model.ProveedorDomicilioModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface ProveedorDomicilioDao extends GenericDao {
    
    List<ProveedorDomicilioModel> getAll();
    
    List<ProveedorDomicilioModel> getByPkProveedor(Integer pk);
    
    ProveedorDomicilioModel getByPkProveedorAndDomicilio(Integer pkProveedor, Integer pkDomicilio);

}
