/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.ClienteDomicilioDao;
import com.empresa.thyssenplastic.dao.impl.ClienteDomicilioDaoImpl;
import com.empresa.thyssenplastic.model.ClienteDomicilioModel;
import com.empresa.thyssenplastic.service.ClienteDomicilioService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusta
 */
public class ClienteDomicilioServiceImpl implements ClienteDomicilioService {
    
    public void save(ClienteDomicilioModel clienteDomicilioModel) {
        try {
            ClienteDomicilioDao clienteDomicilioDao = new ClienteDomicilioDaoImpl();
            clienteDomicilioDao.save(clienteDomicilioModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(ClienteDomicilioModel clienteDomicilioModel) {
        try {
            ClienteDomicilioDao clienteDomicilioDao = new ClienteDomicilioDaoImpl();
            clienteDomicilioDao.delete(clienteDomicilioModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<ClienteDomicilioModel> getAll() {
        List<ClienteDomicilioModel> clientesDomicilios = new ArrayList<ClienteDomicilioModel>();
        try {
            ClienteDomicilioDao clienteDomicilioDao = new ClienteDomicilioDaoImpl();
            clientesDomicilios = clienteDomicilioDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return clientesDomicilios;
    }
    
    public List<ClienteDomicilioModel> getByPkCliente(Integer pk) {
        List<ClienteDomicilioModel> clienteDomicilios = new ArrayList<ClienteDomicilioModel>();
        try {
            ClienteDomicilioDao clienteDao = new ClienteDomicilioDaoImpl();
            clienteDomicilios = clienteDao.getByPkCliente(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return clienteDomicilios;
    }

    public ClienteDomicilioModel getByPkClienteAndDomicilio(Integer pkCliente, Integer pkDomicilio) {
        ClienteDomicilioModel clienteDomicilio = null;
        try {
            ClienteDomicilioDao clienteDao = new ClienteDomicilioDaoImpl();
            clienteDomicilio = clienteDao.getByPkClienteAndDomicilio(pkCliente, pkDomicilio);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return clienteDomicilio;
    }
    
    public ClienteDomicilioModel getByPk(Integer pk) {
        ClienteDomicilioModel clienteDomicilio = null;
        try {
            ClienteDomicilioDao clienteDao = new ClienteDomicilioDaoImpl();
            clienteDomicilio = clienteDao.getByPk(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return clienteDomicilio;
    }
    
}
