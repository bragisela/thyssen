/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao;

import com.empresa.thyssenplastic.model.OrdenDeCompraItemRecepcionModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface OrdenDeCompraItemRecepcionDao extends GenericDao {
    
    List<OrdenDeCompraItemRecepcionModel> getAllByOrdenDeCompraItem(Integer idOrdenDeCompraItem);
    
    OrdenDeCompraItemRecepcionModel getByPk(Integer pk);
}
