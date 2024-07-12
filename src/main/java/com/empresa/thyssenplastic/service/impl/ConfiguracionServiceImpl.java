/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.ConfiguracionDao;
import com.empresa.thyssenplastic.dao.impl.ConfiguracionDaoImpl;
import com.empresa.thyssenplastic.model.ConfiguracionModel;
import com.empresa.thyssenplastic.service.ConfiguracionService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusta
 */
public class ConfiguracionServiceImpl implements ConfiguracionService {
    
    public void save(ConfiguracionModel configuracionModel) {
        try {
            ConfiguracionDao configuracionDao = new ConfiguracionDaoImpl();
            configuracionDao.save(configuracionModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(ConfiguracionModel configuracionModel) {
        try {
            ConfiguracionDao configuracionDao = new ConfiguracionDaoImpl();
            configuracionDao.delete(configuracionModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<ConfiguracionModel> getAll() {
        List<ConfiguracionModel> configuraciones = new ArrayList<ConfiguracionModel>();
        try {
            ConfiguracionDao configuracionDao = new ConfiguracionDaoImpl();
            configuraciones = configuracionDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return configuraciones;
    }

    public List<ConfiguracionModel> getAllByIdTipo(Integer idTipo) {
        List<ConfiguracionModel> configuraciones = new ArrayList<ConfiguracionModel>();
        try {
            ConfiguracionDao configuracionDao = new ConfiguracionDaoImpl();
            configuraciones = configuracionDao.getAllByIdTipo(idTipo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return configuraciones;
    }
    
    public ConfiguracionModel getByPk(Integer pk) {
        ConfiguracionModel configuracion = null;
        try {
            ConfiguracionDao configuracionDao = new ConfiguracionDaoImpl();
            configuracion = configuracionDao.getByPk(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return configuracion;
    }
    
}
