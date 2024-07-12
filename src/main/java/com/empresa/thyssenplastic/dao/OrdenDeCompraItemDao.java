/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao;

import com.empresa.thyssenplastic.model.OrdenDeCompraItemModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface OrdenDeCompraItemDao extends GenericDao {
    
    List<OrdenDeCompraItemModel> getAllByOrdenDeCompra(Integer idOrdenDeCompra);
    
    OrdenDeCompraItemModel getByPk(Integer pk);
}
