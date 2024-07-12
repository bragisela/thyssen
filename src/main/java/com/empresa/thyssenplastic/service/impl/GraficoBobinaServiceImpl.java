/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.GraficoBobinaDao;
import com.empresa.thyssenplastic.dao.impl.GraficoBobinaDaoImpl;
import com.empresa.thyssenplastic.model.GraficoBobinaModel;
import com.empresa.thyssenplastic.service.GraficoBobinaService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusta
 */
public class GraficoBobinaServiceImpl implements GraficoBobinaService {
    
    public void save(GraficoBobinaModel graficoBobinaModel) {
        try {
            GraficoBobinaDao graficoBobinaDao = new GraficoBobinaDaoImpl();
            graficoBobinaDao.save(graficoBobinaModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(GraficoBobinaModel graficoBobinaModel) {
        try {
            GraficoBobinaDao graficoBobinaDao = new GraficoBobinaDaoImpl();
            graficoBobinaDao.delete(graficoBobinaModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<GraficoBobinaModel> getAll() {
        List<GraficoBobinaModel> graficosBobina = new ArrayList<GraficoBobinaModel>();
        try {
            GraficoBobinaDao graficoBobinaDao = new GraficoBobinaDaoImpl();
            graficosBobina = graficoBobinaDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return graficosBobina;
    }
    
    public GraficoBobinaModel getByPk(Integer pk) {
        GraficoBobinaModel graficoBobina = null;
        try {
            GraficoBobinaDao graficoBobinaDao = new GraficoBobinaDaoImpl();
            graficoBobina = graficoBobinaDao.getByPk(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return graficoBobina;
    }

    public List<GraficoBobinaModel> getByIdOrdenDeProduccionBobina(Integer idOrdenDeProduccionBobina) {
        List<GraficoBobinaModel> graficosBobina = new ArrayList<GraficoBobinaModel>();
        try {
            GraficoBobinaDao graficoBobinaDao = new GraficoBobinaDaoImpl();
            graficosBobina = graficoBobinaDao.getByIdOrdenDeProduccionBobina(idOrdenDeProduccionBobina);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return graficosBobina;
    }
    
}
