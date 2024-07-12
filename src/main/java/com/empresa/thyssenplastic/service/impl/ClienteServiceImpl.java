/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.ClienteDao;
import com.empresa.thyssenplastic.dao.impl.ClienteDaoImpl;
import com.empresa.thyssenplastic.model.ClienteModel;
import com.empresa.thyssenplastic.service.ClienteService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusta
 */
public class ClienteServiceImpl implements ClienteService {
    
    public void save(ClienteModel clienteModel) {
        try {
            ClienteDao clienteDao = new ClienteDaoImpl();
            clienteDao.save(clienteModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(ClienteModel clienteModel) {
        try {
            ClienteDao clienteDao = new ClienteDaoImpl();
            clienteDao.delete(clienteModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<ClienteModel> getAll() {
        List<ClienteModel> clientes = new ArrayList<ClienteModel>();
        try {
            ClienteDao clienteDao = new ClienteDaoImpl();
            clientes = clienteDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return clientes;
    }
    
    public ClienteModel getByPk(Integer pk) {
        ClienteModel cliente = null;
        try {
            ClienteDao clienteDao = new ClienteDaoImpl();
            cliente = clienteDao.getByPk(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return cliente;
    }
    
}
