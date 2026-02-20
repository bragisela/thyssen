/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.OrdenDeProduccionBobinaDao;
import com.empresa.thyssenplastic.dao.impl.OrdenDeProduccionBobinaDaoImpl;
import com.empresa.thyssenplastic.dto.OrdenDepositoDto;
import com.empresa.thyssenplastic.dto.EtiquetaDisponibleDto;
import com.empresa.thyssenplastic.model.OrdenDeProduccionBobinaModel;
import com.empresa.thyssenplastic.service.OrdenDeProduccionBobinaService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusta
 */
public class OrdenDeProduccionBobinaServiceImpl implements OrdenDeProduccionBobinaService {
    
    public void save(OrdenDeProduccionBobinaModel ordenDeProduccionModel) {
        try {
            OrdenDeProduccionBobinaDao ordenDeProduccionBobinaDao = new OrdenDeProduccionBobinaDaoImpl();
            ordenDeProduccionBobinaDao.save(ordenDeProduccionModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(OrdenDeProduccionBobinaModel ordenDeProduccionModel) {
        try {
            OrdenDeProduccionBobinaDao ordenDeProduccionBobinaDao = new OrdenDeProduccionBobinaDaoImpl();
            ordenDeProduccionBobinaDao.delete(ordenDeProduccionModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<OrdenDeProduccionBobinaModel> getAll() {
        List<OrdenDeProduccionBobinaModel> ordenDeProducciones = new ArrayList<OrdenDeProduccionBobinaModel>();
        try {
            OrdenDeProduccionBobinaDao ordenDeProduccionBobinaDao = new OrdenDeProduccionBobinaDaoImpl();
            ordenDeProducciones = ordenDeProduccionBobinaDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProducciones;
    }

    public List<OrdenDeProduccionBobinaModel> getAllByOrdenDeProduccion(Integer idOrdenDeProduccion) {
        List<OrdenDeProduccionBobinaModel> ordenDeProducciones = new ArrayList<OrdenDeProduccionBobinaModel>();
        try {
            OrdenDeProduccionBobinaDao ordenDeProduccionBobinaDao = new OrdenDeProduccionBobinaDaoImpl();
            ordenDeProducciones = ordenDeProduccionBobinaDao.getAllByOrdenDeProduccion(idOrdenDeProduccion);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProducciones;
    }

    public List<OrdenDeProduccionBobinaModel> getAllAvailableByOrdenDeProduccion(Integer idOrdenDeProduccion) {
        List<OrdenDeProduccionBobinaModel> ordenDeProducciones = new ArrayList<OrdenDeProduccionBobinaModel>();
        try {
            OrdenDeProduccionBobinaDao ordenDeProduccionBobinaDao = new OrdenDeProduccionBobinaDaoImpl();
            ordenDeProducciones = ordenDeProduccionBobinaDao.getAllAvailableByOrdenDeProduccion(idOrdenDeProduccion);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProducciones;
    }
    
    public OrdenDeProduccionBobinaModel getByPk(Integer pk) {
        OrdenDeProduccionBobinaModel ordenDeProduccion = null;
        try {
            OrdenDeProduccionBobinaDao ordenDeProduccionBobinaDao = new OrdenDeProduccionBobinaDaoImpl();
            ordenDeProduccion = ordenDeProduccionBobinaDao.getByPk(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccion;
    }

    public OrdenDeProduccionBobinaModel getByCode(String codigo) {
        OrdenDeProduccionBobinaModel ordenDeProduccion = null;
        try {
            OrdenDeProduccionBobinaDao ordenDeProduccionBobinaDao = new OrdenDeProduccionBobinaDaoImpl();
            ordenDeProduccion = ordenDeProduccionBobinaDao.getByCode(codigo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProduccion;
    }
    
    public List<OrdenDeProduccionBobinaModel> getAllAvailableForRemitoByOrdenDeProduccion(Integer idOrdenDeProduccion) {
        List<OrdenDeProduccionBobinaModel> ordenDeProducciones = new ArrayList<OrdenDeProduccionBobinaModel>();
        try {
            OrdenDeProduccionBobinaDao ordenDeProduccionBobinaDao = new OrdenDeProduccionBobinaDaoImpl();
            ordenDeProducciones = ordenDeProduccionBobinaDao.getAllAvailableForRemitoByOrdenDeProduccion(idOrdenDeProduccion);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProducciones;
    }

    public List<OrdenDeProduccionBobinaModel> getAllNotAvailableForRemitoByOrdenDeProduccion(Integer idOrdenDeProduccion) {
        List<OrdenDeProduccionBobinaModel> ordenDeProducciones = new ArrayList<OrdenDeProduccionBobinaModel>();
        try {
            OrdenDeProduccionBobinaDao ordenDeProduccionBobinaDao = new OrdenDeProduccionBobinaDaoImpl();
            ordenDeProducciones = ordenDeProduccionBobinaDao.getAllNotAvailableForRemitoByOrdenDeProduccion(idOrdenDeProduccion);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ordenDeProducciones;
    }
    
    public List<OrdenDepositoDto> getAllByDeposito() {
        List<OrdenDepositoDto> getAllByDepositoDto = null;
        try {
            OrdenDeProduccionBobinaDao ordenDeProduccionBobinaDao = new OrdenDeProduccionBobinaDaoImpl();
            getAllByDepositoDto = ordenDeProduccionBobinaDao.getAllByDeposito();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return getAllByDepositoDto;
    }

    public List<EtiquetaDisponibleDto> getDisponiblesByDepositoAndOrden(Integer idDeposito, Integer idOrden) {
        List<EtiquetaDisponibleDto> etiquetas = new ArrayList<EtiquetaDisponibleDto>();
        try {
            OrdenDeProduccionBobinaDao ordenDeProduccionBobinaDao = new OrdenDeProduccionBobinaDaoImpl();
            etiquetas = ordenDeProduccionBobinaDao.getDisponiblesByDepositoAndOrden(idDeposito, idOrden);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

            return etiquetas;
    }

    
}
