/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.ReporteDao;
import com.empresa.thyssenplastic.dao.impl.ReporteDaoImpl;
import com.empresa.thyssenplastic.model.MantenimientoCorrectivoModel;
import com.empresa.thyssenplastic.service.ReporteService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author gusta
 */
public class ReporteServiceImpl implements ReporteService {
    
    public List<MantenimientoCorrectivoModel> getAllByFilter(Date fechaDesde, Date fechaHasta, Integer idMaquina) {    
        List<MantenimientoCorrectivoModel> mantenimientos = new ArrayList<MantenimientoCorrectivoModel>();
        try {
            ReporteDao reporteDao = new ReporteDaoImpl();
            mantenimientos = reporteDao.getAllByFilter(fechaDesde, fechaHasta, idMaquina);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return mantenimientos;
    }
}
