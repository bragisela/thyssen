/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service;

import com.empresa.thyssenplastic.model.OrdenDeCompraItemRecepcionModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface OrdenDeCompraItemRecepcionService {
    
    void save(OrdenDeCompraItemRecepcionModel ordenDeCompraItemRecepcionModel);
    
    void delete(OrdenDeCompraItemRecepcionModel ordenDeCompraItemRecepcionModel);
    
    List<OrdenDeCompraItemRecepcionModel> getAllByOrdenDeCompraItem(Integer idOrdenDeCompraItem);
    
    OrdenDeCompraItemRecepcionModel getByPk(Integer pk);
}
