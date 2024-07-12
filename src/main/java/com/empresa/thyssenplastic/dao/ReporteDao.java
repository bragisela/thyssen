/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao;

import com.empresa.thyssenplastic.model.MantenimientoCorrectivoModel;
import java.util.Date;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface ReporteDao extends GenericDao {
    
    List<MantenimientoCorrectivoModel> getAllByFilter(Date fechaDesde, Date fechaHasta, Integer idMaquina);
    
}
