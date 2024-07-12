/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service;

import com.empresa.thyssenplastic.model.OrdenDeCompraModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface OrdenDeCompraService {
    
    void save(OrdenDeCompraModel ordenDeCompraModel);
    
    void delete(OrdenDeCompraModel ordenDeCompraModel);
    
    List<OrdenDeCompraModel> getAll();
    
    OrdenDeCompraModel getByPk(Integer pk);
}
