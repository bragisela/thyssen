/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.service;

import com.empresa.thyssenplastic.model.ClienteDomicilioModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public interface ClienteDomicilioService {
    
    void save(ClienteDomicilioModel clienteDomicilioModel);
    
    void delete(ClienteDomicilioModel clienteDomicilioModel);
    
    List<ClienteDomicilioModel> getAll();
    
    List<ClienteDomicilioModel> getByPkCliente(Integer pk);
    
    ClienteDomicilioModel getByPkClienteAndDomicilio(Integer pkCliente, Integer pkDomicilio);
    
    ClienteDomicilioModel getByPk(Integer pk);
}
