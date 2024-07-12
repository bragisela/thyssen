/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.empresa.thyssenplastic.dao;

import com.empresa.thyssenplastic.model.ProveedorContactoModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface ProveedorContactoDao extends GenericDao {
    
    List<ProveedorContactoModel> getAll();
    
    List<ProveedorContactoModel> getByPkProveedor(Integer pk);
    
    ProveedorContactoModel getByPkProveedorAndContacto(Integer pkProveedor, Integer pkContacto);
    
    ProveedorContactoModel getByPkContacto(Integer pkContacto);

}
