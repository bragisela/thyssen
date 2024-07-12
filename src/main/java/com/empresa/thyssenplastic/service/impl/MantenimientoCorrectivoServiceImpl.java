/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.MantenimientoCorrectivoDao;
import com.empresa.thyssenplastic.dao.impl.MantenimientoCorrectivoDaoImpl;
import com.empresa.thyssenplastic.model.MantenimientoCorrectivoModel;
import com.empresa.thyssenplastic.service.MantenimientoCorrectivoService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusta
 */
public class MantenimientoCorrectivoServiceImpl implements MantenimientoCorrectivoService {
    
    public void save(MantenimientoCorrectivoModel mantenimientoCorrectivoModel) {
        try {
            MantenimientoCorrectivoDao mantenimientoCorrectivoDao = new MantenimientoCorrectivoDaoImpl();
            mantenimientoCorrectivoDao.save(mantenimientoCorrectivoModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(MantenimientoCorrectivoModel mantenimientoCorrectivoModel) {
        try {
            MantenimientoCorrectivoDao mantenimientoCorrectivoDao = new MantenimientoCorrectivoDaoImpl();
            mantenimientoCorrectivoDao.delete(mantenimientoCorrectivoModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<MantenimientoCorrectivoModel> getAll() {
        List<MantenimientoCorrectivoModel> mantenimientosCorrectivo = new ArrayList<MantenimientoCorrectivoModel>();
        try {
            MantenimientoCorrectivoDao mantenimientoCorrectivoDao = new MantenimientoCorrectivoDaoImpl();
            mantenimientosCorrectivo = mantenimientoCorrectivoDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return mantenimientosCorrectivo;
    }
    
    public MantenimientoCorrectivoModel getByPk(Integer pk) {
        MantenimientoCorrectivoModel mantenimientoCorrectivo = null;
        try {
            MantenimientoCorrectivoDao mantenimientoCorrectivoDao = new MantenimientoCorrectivoDaoImpl();
            mantenimientoCorrectivo = mantenimientoCorrectivoDao.getByPk(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return mantenimientoCorrectivo;
    }
    
}
