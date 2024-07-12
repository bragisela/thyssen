/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.GraficoBobinaDetalleDao;
import com.empresa.thyssenplastic.dao.impl.GraficoBobinaDetalleDaoImpl;
import com.empresa.thyssenplastic.model.GraficoBobinaDetalleModel;
import com.empresa.thyssenplastic.service.GraficoBobinaDetalleService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusta
 */
public class GraficoBobinaDetalleServiceImpl implements GraficoBobinaDetalleService {
    
    public void save(GraficoBobinaDetalleModel graficoBobinaModel) {
        try {
            GraficoBobinaDetalleDao graficoBobinaDetalleDao = new GraficoBobinaDetalleDaoImpl();
            graficoBobinaDetalleDao.save(graficoBobinaModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(GraficoBobinaDetalleModel graficoBobinaModel) {
        try {
            GraficoBobinaDetalleDao graficoBobinaDetalleDao = new GraficoBobinaDetalleDaoImpl();
            graficoBobinaDetalleDao.delete(graficoBobinaModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<GraficoBobinaDetalleModel> getAll() {
        List<GraficoBobinaDetalleModel> graficosBobina = new ArrayList<GraficoBobinaDetalleModel>();
        try {
            GraficoBobinaDetalleDao graficoBobinaDetalleDao = new GraficoBobinaDetalleDaoImpl();
            graficosBobina = graficoBobinaDetalleDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return graficosBobina;
    }
    
    public GraficoBobinaDetalleModel getByPk(Integer pk) {
        GraficoBobinaDetalleModel graficoBobina = null;
        try {
            GraficoBobinaDetalleDao graficoBobinaDetalleDao = new GraficoBobinaDetalleDaoImpl();
            graficoBobina = graficoBobinaDetalleDao.getByPk(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return graficoBobina;
    }

    public List<GraficoBobinaDetalleModel> getByIdGraficoBobina(Integer idGraficoBobina) {
        List<GraficoBobinaDetalleModel> graficosBobina = new ArrayList<GraficoBobinaDetalleModel>();
        try {
            GraficoBobinaDetalleDao graficoBobinaDetalleDao = new GraficoBobinaDetalleDaoImpl();
            graficosBobina = graficoBobinaDetalleDao.getByIdGraficoBobina(idGraficoBobina);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return graficosBobina;
    }
    
    public GraficoBobinaDetalleModel getMaximoAnguloGraficoBobinaDetalle(Integer idGraficoBobina) {

        GraficoBobinaDetalleModel graficosBobinaDetalle = null;
        try {            
            GraficoBobinaDetalleDao graficoBobinaDetalleDao = new GraficoBobinaDetalleDaoImpl();
            graficosBobinaDetalle = graficoBobinaDetalleDao.getMaximoAnguloGraficoBobinaDetalle(idGraficoBobina);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return graficosBobinaDetalle;
        
    }
    
     public GraficoBobinaDetalleModel obtenerUltimaMedicion(Integer idGraficoBobina) {

        GraficoBobinaDetalleModel graficosBobinaDetalle = null;
        try {            
            GraficoBobinaDetalleDao graficoBobinaDetalleDao = new GraficoBobinaDetalleDaoImpl();
            graficosBobinaDetalle = graficoBobinaDetalleDao.obtenerUltimaMedicion(idGraficoBobina);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return graficosBobinaDetalle;
        
    }
}
