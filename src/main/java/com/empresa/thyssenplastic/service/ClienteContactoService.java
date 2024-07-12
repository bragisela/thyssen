/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service;

import com.empresa.thyssenplastic.model.ClienteContactoModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface ClienteContactoService {
    
    void save(ClienteContactoModel clienteContactoModel);
    
    void delete(ClienteContactoModel clienteContactoModel);
    
    List<ClienteContactoModel> getAll();
    
    List<ClienteContactoModel> getByPkCliente(Integer pk);
    
    ClienteContactoModel getByPkClienteAndContacto(Integer pkCliente, Integer pkContacto);
}
