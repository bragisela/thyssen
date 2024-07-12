/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.ArticuloDao;
import com.empresa.thyssenplastic.dao.impl.ArticuloDaoImpl;
import com.empresa.thyssenplastic.model.ArticuloModel;
import com.empresa.thyssenplastic.service.ArticuloService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusta
 */
public class ArticuloServiceImpl implements ArticuloService {
    
    public void save(ArticuloModel articuloModel) {
        try {
            ArticuloDao articuloDao = new ArticuloDaoImpl();
            articuloDao.save(articuloModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(ArticuloModel articuloModel) {
        try {
            ArticuloDao articuloDao = new ArticuloDaoImpl();
            articuloDao.delete(articuloModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<ArticuloModel> getAll() {
        List<ArticuloModel> articulos = new ArrayList<ArticuloModel>();
        try {
            ArticuloDao articuloDao = new ArticuloDaoImpl();
            articulos = articuloDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return articulos;
    }

    public List<ArticuloModel> getAllByCliente(Integer idCliente) {
        List<ArticuloModel> articulos = new ArrayList<ArticuloModel>();
        try {
            ArticuloDao articuloDao = new ArticuloDaoImpl();
            articulos = articuloDao.getAllByCliente(idCliente);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return articulos;
    }
    
    public ArticuloModel getByPk(Integer pk) {
        ArticuloModel articulo = null;
        try {
            ArticuloDao articuloDao = new ArticuloDaoImpl();
            articulo = articuloDao.getByPk(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return articulo;
    }
    
}
