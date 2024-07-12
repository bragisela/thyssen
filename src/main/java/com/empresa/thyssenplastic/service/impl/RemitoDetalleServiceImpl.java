/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.RemitoDetalleDao;
import com.empresa.thyssenplastic.dao.impl.RemitoDetalleDaoImpl;
import com.empresa.thyssenplastic.model.RemitoDetalleModel;
import com.empresa.thyssenplastic.service.RemitoDetalleService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusta
 */
public class RemitoDetalleServiceImpl implements RemitoDetalleService {
    
    public void save(RemitoDetalleModel remitoDetalleModel) {
        try {
            RemitoDetalleDao remitoDetalleDao = new RemitoDetalleDaoImpl();
            remitoDetalleDao.save(remitoDetalleModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(RemitoDetalleModel remitoDetalleModel) {
        try {
            RemitoDetalleDao remitoDetalleDao = new RemitoDetalleDaoImpl();
            remitoDetalleDao.delete(remitoDetalleModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<RemitoDetalleModel> getAll() {
        List<RemitoDetalleModel> remitoDetalles = new ArrayList<RemitoDetalleModel>();
        try {
            RemitoDetalleDao remitoDetalleDao = new RemitoDetalleDaoImpl();
            remitoDetalles = remitoDetalleDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return remitoDetalles;
    }
    
    public RemitoDetalleModel getByPk(Integer pk) {
        RemitoDetalleModel remitoDetalles = null;
        try {
            RemitoDetalleDao remitoDetalleDao = new RemitoDetalleDaoImpl();
            remitoDetalles = remitoDetalleDao.getByPk(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return remitoDetalles;
    }

    public List<RemitoDetalleModel> getAllByRemito(Integer idRemito) {
        List<RemitoDetalleModel> remitoDetalles = new ArrayList<RemitoDetalleModel>();
        try {
            RemitoDetalleDao remitoDetalleDao = new RemitoDetalleDaoImpl();
            remitoDetalles = remitoDetalleDao.getAllByRemito(idRemito);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return remitoDetalles;
    }
    
     public RemitoDetalleModel getByCompositeIds(int idRemito, int idDeposito, int idOrdenDeProduccion) {
        RemitoDetalleModel remitoDetalles = null;
        try {
            RemitoDetalleDao remitoDetalleDao = new RemitoDetalleDaoImpl();
            remitoDetalles = remitoDetalleDao.getByCompositeIds(idRemito, idDeposito, idOrdenDeProduccion);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return remitoDetalles;
    }

//    public RemitoDetalleModel getByBobina(Integer idBobina) {
//        RemitoDetalleModel remitoDetalles = null;
//        try {
//            RemitoDetalleDao remitoDetalleDao = new RemitoDetalleDaoImpl();
//            remitoDetalles = remitoDetalleDao.getByBobina(idBobina);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        return remitoDetalles;
//    }
//
//    public RemitoDetalleModel getByBulto(Integer idBulto) {
//        RemitoDetalleModel remitoDetalles = null;
//        try {
//            RemitoDetalleDao remitoDetalleDao = new RemitoDetalleDaoImpl();
//            remitoDetalles = remitoDetalleDao.getByBulto(idBulto);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        return remitoDetalles;
//    }
//
//    public RemitoDetalleModel getByPallet(Integer idPallet) {
//        RemitoDetalleModel remitoDetalles = null;
//        try {
//            RemitoDetalleDao remitoDetalleDao = new RemitoDetalleDaoImpl();
//            remitoDetalles = remitoDetalleDao.getByPallet(idPallet);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        return remitoDetalles;
//    }
        
}
