/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.AjusteDeInventarioDao;
import com.empresa.thyssenplastic.dao.impl.AjusteDeInventarioDaoImpl;
import com.empresa.thyssenplastic.model.AjusteDeInventarioModel;
import com.empresa.thyssenplastic.service.AjusteDeInventarioService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusta
 */
public class AjusteDeInventarioServiceImpl implements AjusteDeInventarioService {
    
    public void save(AjusteDeInventarioModel ajusteDeInventarioModel) {
        try {
            AjusteDeInventarioDao ajusteDeInventarioDao = new AjusteDeInventarioDaoImpl();
            ajusteDeInventarioDao.save(ajusteDeInventarioModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(AjusteDeInventarioModel ajusteDeInventarioModel) {
        try {
            AjusteDeInventarioDao ajusteDeInventarioDao = new AjusteDeInventarioDaoImpl();
            ajusteDeInventarioDao.delete(ajusteDeInventarioModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<AjusteDeInventarioModel> getAll() {
        List<AjusteDeInventarioModel> ajusteDeInventarios = new ArrayList<AjusteDeInventarioModel>();
        try {
            AjusteDeInventarioDao ajusteDeInventarioDao = new AjusteDeInventarioDaoImpl();
            ajusteDeInventarios = ajusteDeInventarioDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ajusteDeInventarios;
    }
    
    public AjusteDeInventarioModel getByPk(Integer pk) {
        AjusteDeInventarioModel ajusteDeInventario = null;
        try {
            AjusteDeInventarioDao ajusteDeInventarioDao = new AjusteDeInventarioDaoImpl();
            ajusteDeInventario = ajusteDeInventarioDao.getByPk(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ajusteDeInventario;
    }
    
}
