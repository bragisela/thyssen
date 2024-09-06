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
import java.util.List;

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

//    public List<RemitoDetalleModel> getAllByRemito(Integer idRemito) {
//        List<RemitoDetalleModel> remitoDetalles = new ArrayList<RemitoDetalleModel>();
//        try {
//            RemitoDetalleDao remitoDetalleDao = new RemitoDetalleDaoImpl();
//            remitoDetalles = remitoDetalleDao.getAllByRemito(idRemito);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        return remitoDetalles;
//    }
   
        
}
