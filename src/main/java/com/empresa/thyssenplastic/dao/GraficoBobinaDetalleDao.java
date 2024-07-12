/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao;

import com.empresa.thyssenplastic.model.GraficoBobinaDetalleModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface GraficoBobinaDetalleDao extends GenericDao {
    
    List<GraficoBobinaDetalleModel> getAll();
    
    GraficoBobinaDetalleModel getByPk(Integer pk);
    
    List<GraficoBobinaDetalleModel> getByIdGraficoBobina(Integer idGraficoBobina);
    
    GraficoBobinaDetalleModel getMaximoAnguloGraficoBobinaDetalle(Integer idGraficoBobina);
    
    GraficoBobinaDetalleModel obtenerUltimaMedicion(Integer idGraficoBobina);
}
