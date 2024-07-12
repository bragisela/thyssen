/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao;

import com.empresa.thyssenplastic.model.GraficoBobinaModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface GraficoBobinaDao extends GenericDao {
    
    List<GraficoBobinaModel> getAll();
    
    GraficoBobinaModel getByPk(Integer pk);
    
    List<GraficoBobinaModel> getByIdOrdenDeProduccionBobina(Integer idOrdenDeProduccionBobina);
    
}
