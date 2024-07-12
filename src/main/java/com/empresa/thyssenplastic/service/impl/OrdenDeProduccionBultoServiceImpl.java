/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.OrdenDeProduccionBultoDao;
import com.empresa.thyssenplastic.dao.impl.OrdenDeProduccionBultoDaoImpl;
import com.empresa.thyssenplastic.model.OrdenDeProduccionBultoModel;
import com.empresa.thyssenplastic.service.OrdenDeProduccionBultoService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author gusta
 */
public class OrdenDeProduccionBultoServiceImpl implements OrdenDeProduccionBultoService {
    
    public void save(OrdenDeProduccionBultoModel ordenDeProduccionModel) {
        try {
            OrdenDeProduccionBultoDao ordenDeProduccionBultoDao = new OrdenDeProduccionBultoDaoImpl();
            ordenDeProduccionBultoDao.save(ordenDeProduccionModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(OrdenDeProduccionBultoModel ordenDeProduccionModel) {
        try {
            OrdenDeProduccionBultoDao ordenDeProduccionBultoDao = new OrdenDeProduccionBultoDaoImpl();
            ordenDeProduccionBultoDao.delete(ordenDeProduccionModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<OrdenDeProduccionBultoModel> getAll() {
        List<OrdenDeProduccionBultoModel> ordenDeProduccionBultos = new ArrayList<OrdenDeProduccionBultoModel>();
        try {
            OrdenDeProduccionBultoDao ordenDeProduccionBultoDao = new OrdenDeProduccionBultoDaoImpl();
            ordenDeProduccionBultos = ordenDeProduccionBultoDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionBultos;
    }

    public List<OrdenDeProduccionBultoModel> getAllByOrdenDeProduccion(Integer idOrdenDeProduccion) {
        List<OrdenDeProduccionBultoModel> ordenDeProduccionBultos = new ArrayList<OrdenDeProduccionBultoModel>();
        try {
            OrdenDeProduccionBultoDao ordenDeProduccionBultoDao = new OrdenDeProduccionBultoDaoImpl();
            ordenDeProduccionBultos = ordenDeProduccionBultoDao.getAllByOrdenDeProduccion(idOrdenDeProduccion);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionBultos;
    }

    public List<OrdenDeProduccionBultoModel> getAllAvailableByOrdenDeProduccion(Integer idOrdenDeProduccion) {
        List<OrdenDeProduccionBultoModel> ordenDeProduccionBultos = new ArrayList<OrdenDeProduccionBultoModel>();
        try {
            OrdenDeProduccionBultoDao ordenDeProduccionBultoDao = new OrdenDeProduccionBultoDaoImpl();
            ordenDeProduccionBultos = ordenDeProduccionBultoDao.getAllAvailableByOrdenDeProduccion(idOrdenDeProduccion);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionBultos;
    }

    
    public OrdenDeProduccionBultoModel getByPk(Integer pk) {
        OrdenDeProduccionBultoModel ordenDeProduccionBulto = null;
        try {
            OrdenDeProduccionBultoDao ordenDeProduccionBultoDao = new OrdenDeProduccionBultoDaoImpl();
            ordenDeProduccionBulto = ordenDeProduccionBultoDao.getByPk(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionBulto;
    }

    public OrdenDeProduccionBultoModel getByCode(String codigo) {
        OrdenDeProduccionBultoModel ordenDeProduccionBulto = null;
        try {
            OrdenDeProduccionBultoDao ordenDeProduccionBultoDao = new OrdenDeProduccionBultoDaoImpl();
            ordenDeProduccionBulto = ordenDeProduccionBultoDao.getByCode(codigo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionBulto;
    }
    
    public OrdenDeProduccionBultoModel getByOrdenDeProduccionBobina(Integer idOrdenDeProduccionBobina) {
        OrdenDeProduccionBultoModel ordenDeProduccionBulto = null;
        try {
            OrdenDeProduccionBultoDao ordenDeProduccionBultoDao = new OrdenDeProduccionBultoDaoImpl();
            ordenDeProduccionBulto = ordenDeProduccionBultoDao.getByOrdenDeProduccionBobina(idOrdenDeProduccionBobina);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionBulto;
    }    
    
    public List<OrdenDeProduccionBultoModel> getAllAvailableForRemitoByOrdenDeProduccion(Integer idOrdenDeProduccion) {
        List<OrdenDeProduccionBultoModel> ordenDeProduccionBultos = new ArrayList<OrdenDeProduccionBultoModel>();
        try {
            OrdenDeProduccionBultoDao ordenDeProduccionBultoDao = new OrdenDeProduccionBultoDaoImpl();
            ordenDeProduccionBultos = ordenDeProduccionBultoDao.getAllAvailableForRemitoByOrdenDeProduccion(idOrdenDeProduccion);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionBultos;
    }

    public List<OrdenDeProduccionBultoModel> getAllNotAvailableForRemitoByOrdenDeProduccion(Integer idOrdenDeProduccion) {
        List<OrdenDeProduccionBultoModel> ordenDeProduccionBultos = new ArrayList<OrdenDeProduccionBultoModel>();
        try {
            OrdenDeProduccionBultoDao ordenDeProduccionBultoDao = new OrdenDeProduccionBultoDaoImpl();
            ordenDeProduccionBultos = ordenDeProduccionBultoDao.getAllNotAvailableForRemitoByOrdenDeProduccion(idOrdenDeProduccion);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionBultos;
    }
    
    public List<OrdenDeProduccionBultoModel> getByOrdenDeProduccionAndFechaAlta(Integer idOrdenDeProduccion, Date fechaDesde) {
        List<OrdenDeProduccionBultoModel> ordenDeProduccionPallets = new ArrayList<OrdenDeProduccionBultoModel>();
        try {
            OrdenDeProduccionBultoDao ordenDeProduccionPalletBultoDao = new OrdenDeProduccionBultoDaoImpl();
            ordenDeProduccionPallets = ordenDeProduccionPalletBultoDao.getByOrdenDeProduccionAndFechaAlta(idOrdenDeProduccion, fechaDesde);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionPallets;
    }
    
     public List<OrdenDeProduccionBultoModel> getByFechaAlta(Date fechaDesde) {
        List<OrdenDeProduccionBultoModel> ordenDeProduccionPallets = new ArrayList<OrdenDeProduccionBultoModel>();
        try {
            OrdenDeProduccionBultoDao ordenDeProduccionPalletBultoDao = new OrdenDeProduccionBultoDaoImpl();
            ordenDeProduccionPallets = ordenDeProduccionPalletBultoDao.getByFechaAlta(fechaDesde);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccionPallets;
    }
    
}
