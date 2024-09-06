/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.OrdenDeProduccionScrapDao;
import com.empresa.thyssenplastic.dao.impl.OrdenDeProduccionScrapDaoImpl;
import com.empresa.thyssenplastic.dto.DepositoScrapDto;
import com.empresa.thyssenplastic.model.OrdenDeProduccionScrapModel;
import com.empresa.thyssenplastic.service.OrdenDeProduccionScrapService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusta
 */
public class OrdenDeProduccionScrapServiceImpl implements OrdenDeProduccionScrapService {
    
    public void save(OrdenDeProduccionScrapModel ordenDeProduccionModel) {
        try {
            OrdenDeProduccionScrapDao ordenDeProduccionScrapDao = new OrdenDeProduccionScrapDaoImpl();
            ordenDeProduccionScrapDao.save(ordenDeProduccionModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(OrdenDeProduccionScrapModel ordenDeProduccionModel) {
        try {
            OrdenDeProduccionScrapDao ordenDeProduccionScrapDao = new OrdenDeProduccionScrapDaoImpl();
            ordenDeProduccionScrapDao.delete(ordenDeProduccionModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<OrdenDeProduccionScrapModel> getAll() {
        List<OrdenDeProduccionScrapModel> ordenDeProducciones = new ArrayList<OrdenDeProduccionScrapModel>();
        try {
            OrdenDeProduccionScrapDao ordenDeProduccionScrapDao = new OrdenDeProduccionScrapDaoImpl();
            ordenDeProducciones = ordenDeProduccionScrapDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProducciones;
    }
    
    public List<OrdenDeProduccionScrapModel> getAllPaginated(int pageNumber, int pageSize) {
        List<OrdenDeProduccionScrapModel> ordenDeProducciones = new ArrayList<OrdenDeProduccionScrapModel>();
        try {
            OrdenDeProduccionScrapDao ordenDeProduccionScrapDao = new OrdenDeProduccionScrapDaoImpl();
            ordenDeProducciones = ordenDeProduccionScrapDao.getAllPaginated(pageNumber,pageSize);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProducciones;
    }
    
    public List<DepositoScrapDto> getResumenPorOrdenDeProduccion(int pageNumber, int pageSize) {
        List<DepositoScrapDto> ordenDeProducciones = new ArrayList<DepositoScrapDto>();
        try {
            OrdenDeProduccionScrapDao ordenDeProduccionScrapDao = new OrdenDeProduccionScrapDaoImpl();
            ordenDeProducciones = ordenDeProduccionScrapDao.getResumenPorOrdenDeProduccion(pageNumber,pageSize);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProducciones;
    }

    public List<OrdenDeProduccionScrapModel> getAllByOrdenDeProduccion(Integer idOrdenDeProduccion) {
        List<OrdenDeProduccionScrapModel> ordenDeProducciones = new ArrayList<OrdenDeProduccionScrapModel>();
        try {
            OrdenDeProduccionScrapDao ordenDeProduccionScrapDao = new OrdenDeProduccionScrapDaoImpl();
            ordenDeProducciones = ordenDeProduccionScrapDao.getAllByOrdenDeProduccion(idOrdenDeProduccion);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProducciones;
    }
    
    public OrdenDeProduccionScrapModel getByPk(Integer pk) {
        OrdenDeProduccionScrapModel ordenDeProduccion = null;
        try {
            OrdenDeProduccionScrapDao ordenDeProduccionScrapDao = new OrdenDeProduccionScrapDaoImpl();
            ordenDeProduccion = ordenDeProduccionScrapDao.getByPk(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccion;
    }

    public OrdenDeProduccionScrapModel getByCode(String codigo) {
        OrdenDeProduccionScrapModel ordenDeProduccion = null;
        try {
            OrdenDeProduccionScrapDao ordenDeProduccionScrapDao = new OrdenDeProduccionScrapDaoImpl();
            ordenDeProduccion = ordenDeProduccionScrapDao.getByCode(codigo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccion;
    }
    
    /*
    public List<OrdenDeProduccionScrapModel> getAllAvailableByOrdenDeProduccion(Integer idOrdenDeProduccion) {
        List<OrdenDeProduccionScrapModel> ordenDeProducciones = new ArrayList<OrdenDeProduccionScrapModel>();
        try {
            OrdenDeProduccionScrapDao ordenDeProduccionScrapDao = new OrdenDeProduccionScrapDaoImpl();
            ordenDeProducciones = ordenDeProduccionScrapDao.getAllAvailableByOrdenDeProduccion(idOrdenDeProduccion);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProducciones;
    }
    
    public List<OrdenDeProduccionScrapModel> getAllAvailableForRemitoByOrdenDeProduccion(Integer idOrdenDeProduccion) {
        List<OrdenDeProduccionScrapModel> ordenDeProducciones = new ArrayList<OrdenDeProduccionScrapModel>();
        try {
            OrdenDeProduccionScrapDao ordenDeProduccionScrapDao = new OrdenDeProduccionScrapDaoImpl();
            ordenDeProducciones = ordenDeProduccionScrapDao.getAllAvailableForRemitoByOrdenDeProduccion(idOrdenDeProduccion);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProducciones;
    }

    public List<OrdenDeProduccionScrapModel> getAllNotAvailableForRemitoByOrdenDeProduccion(Integer idOrdenDeProduccion) {
        List<OrdenDeProduccionScrapModel> ordenDeProducciones = new ArrayList<OrdenDeProduccionScrapModel>();
        try {
            OrdenDeProduccionScrapDao ordenDeProduccionScrapDao = new OrdenDeProduccionScrapDaoImpl();
            ordenDeProducciones = ordenDeProduccionScrapDao.getAllNotAvailableForRemitoByOrdenDeProduccion(idOrdenDeProduccion);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProducciones;
    }
    */
}
