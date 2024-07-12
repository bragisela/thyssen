/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service.impl;

import com.empresa.thyssenplastic.dao.ClienteContactoDao;
import com.empresa.thyssenplastic.dao.impl.ClienteContactoDaoImpl;
import com.empresa.thyssenplastic.model.ClienteContactoModel;
import com.empresa.thyssenplastic.service.ClienteContactoService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gusta
 */
public class ClienteContactoServiceImpl implements ClienteContactoService {
    
    public void save(ClienteContactoModel clienteContactoModel) {
        try {
            ClienteContactoDao clienteContactoDao = new ClienteContactoDaoImpl();
            clienteContactoDao.save(clienteContactoModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }

    public void delete(ClienteContactoModel clienteContactoModel) {
        try {
            ClienteContactoDao clienteContactoDao = new ClienteContactoDaoImpl();
            clienteContactoDao.delete(clienteContactoModel);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }        
    }
    
    public List<ClienteContactoModel> getAll() {
        List<ClienteContactoModel> clientesContactos = new ArrayList<ClienteContactoModel>();
        try {
            ClienteContactoDao clienteContactoDao = new ClienteContactoDaoImpl();
            clientesContactos = clienteContactoDao.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return clientesContactos;
    }
    
    public List<ClienteContactoModel> getByPkCliente(Integer pk) {
        List<ClienteContactoModel> clienteContactos = new ArrayList<ClienteContactoModel>();
        try {
            ClienteContactoDao clienteDao = new ClienteContactoDaoImpl();
            clienteContactos = clienteDao.getByPkCliente(pk);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return clienteContactos;
    }

    public ClienteContactoModel getByPkClienteAndContacto(Integer pkCliente, Integer pkContacto) {
        ClienteContactoModel clienteContacto = null;
        try {
            ClienteContactoDao clienteDao = new ClienteContactoDaoImpl();
            clienteContacto = clienteDao.getByPkClienteAndContacto(pkCliente, pkContacto);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return clienteContacto;
    }
    
    
}
