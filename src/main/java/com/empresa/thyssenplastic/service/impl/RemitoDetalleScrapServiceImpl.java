/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.RemitoDetalleDao;
import com.empresa.thyssenplastic.dao.RemitoDetalleScrapDao;
import com.empresa.thyssenplastic.dao.impl.RemitoDetalleDaoImpl;
import com.empresa.thyssenplastic.dao.impl.RemitoDetalleScrapDaoImpl;
import com.empresa.thyssenplastic.model.RemitoDetalleModel;
import com.empresa.thyssenplastic.model.RemitoDetalleScrapModel;
import com.empresa.thyssenplastic.service.RemitoDetalleScrapService;
import com.empresa.thyssenplastic.service.RemitoDetalleService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gusta
 */
public class RemitoDetalleScrapServiceImpl implements RemitoDetalleScrapService {
    
    public void save(RemitoDetalleScrapModel remitoDetalleScrapModel) {
        try {
            RemitoDetalleScrapDao remitoDetalleScrapDao = new RemitoDetalleScrapDaoImpl();
            remitoDetalleScrapDao.save(remitoDetalleScrapModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(RemitoDetalleScrapModel remitoDetalleScrapModel) {
        try {
            RemitoDetalleScrapDao remitoDetalleScrapDao = new RemitoDetalleScrapDaoImpl();
            remitoDetalleScrapDao.delete(remitoDetalleScrapModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<RemitoDetalleScrapModel> getAll() {
        List<RemitoDetalleScrapModel> remitoDetalles = new ArrayList<RemitoDetalleScrapModel>();
        try {
            RemitoDetalleScrapDao remitoDetalleScrapDao = new RemitoDetalleScrapDaoImpl();
            remitoDetalles = remitoDetalleScrapDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return remitoDetalles;
    }
    
    public RemitoDetalleScrapModel getByPk(Integer pk) {
        RemitoDetalleScrapModel remitoDetalles = null;
        try {
            RemitoDetalleScrapDao remitoDetalleScrapDao = new RemitoDetalleScrapDaoImpl();
            remitoDetalles = remitoDetalleScrapDao.getByPk(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return remitoDetalles;
    }
    
    public List<RemitoDetalleScrapModel> getAllByIdOrdenDeProduccionScrap(Integer idOrdenDeProduccionScrap) {
        List<RemitoDetalleScrapModel> remitoDetalles = new ArrayList<RemitoDetalleScrapModel>();
        try {
            RemitoDetalleScrapDao remitoDetalleScrapDao = new RemitoDetalleScrapDaoImpl();
            remitoDetalles = remitoDetalleScrapDao.getAllByIdOrdenDeProduccionScrap(idOrdenDeProduccionScrap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return remitoDetalles;
    }
    
    public List<RemitoDetalleScrapModel> getAllByRemito(Integer idRemito) {
        List<RemitoDetalleScrapModel> remitoDetalles = new ArrayList<RemitoDetalleScrapModel>();
        try {
            RemitoDetalleScrapDao remitoDetalleScrapDao = new RemitoDetalleScrapDaoImpl();
            remitoDetalles = remitoDetalleScrapDao.getAllByRemito(idRemito);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return remitoDetalles;
    }        
    
    public RemitoDetalleScrapModel getByCodigoAndRemito(String codigo, Integer idRemito) {
        RemitoDetalleScrapModel remitoDetalles = null;
        try {
            RemitoDetalleScrapDao remitoDetalleScrapDao = new RemitoDetalleScrapDaoImpl();
            remitoDetalles = remitoDetalleScrapDao.getByCodigoAndRemito(codigo, idRemito);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return remitoDetalles;
    }
    
    public Double getSumaCantidadUtilizadaByCodigo(String codigo) {
        Double sumaCantidadUtilizada = 0.0;
        try {
            RemitoDetalleScrapDao remitoDetalleScrapDao = new RemitoDetalleScrapDaoImpl();
            sumaCantidadUtilizada = remitoDetalleScrapDao.getSumaCantidadUtilizadaByCodigo(codigo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return sumaCantidadUtilizada;
    }
    
    public Map<String, Double> getMapSumaCantidadUtilizadaByCodigo(String codigo) {
        Map<String, Double> resultado = new HashMap<String, Double>();
        resultado.put("noDadoDeBaja", 0.0);
        resultado.put("dadoDeBaja", 0.0);
        try {
            RemitoDetalleScrapDao remitoDetalleScrapDao = new RemitoDetalleScrapDaoImpl();
            resultado = remitoDetalleScrapDao.getMapSumaCantidadUtilizadaByCodigo(codigo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return resultado;
    }
    
}
