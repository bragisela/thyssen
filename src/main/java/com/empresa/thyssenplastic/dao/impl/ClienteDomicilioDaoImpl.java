/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.ClienteDomicilioDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.ClienteDomicilioModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class ClienteDomicilioDaoImpl extends GenericDaoImpl implements ClienteDomicilioDao {
    
        public List<ClienteDomicilioModel> getAll() {
        List<ClienteDomicilioModel> clienteDomicilios = new ArrayList<ClienteDomicilioModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            clienteDomicilios = session.createQuery("from ClienteDomicilioModel", ClienteDomicilioModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return clienteDomicilios;
    }

    public List<ClienteDomicilioModel> getByPkCliente(Integer pk) {
        List<ClienteDomicilioModel> clienteDomicilios = new ArrayList<ClienteDomicilioModel>();        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from ClienteDomicilioModel WHERE idCliente=:idCliente");
                query.setInteger("idCliente", pk);
                
                clienteDomicilios = query.list();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return clienteDomicilios;
    }

    public ClienteDomicilioModel getByPk(Integer pk) {
        ClienteDomicilioModel clienteDomicilio = null;        
        List<ClienteDomicilioModel> clienteDomicilios = new ArrayList<ClienteDomicilioModel>();        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from ClienteDomicilioModel WHERE id=:id");
                query.setInteger("id", pk);
                
                clienteDomicilios = query.list();
                if(clienteDomicilios != null && !clienteDomicilios.isEmpty()) {
                    clienteDomicilio = clienteDomicilios.get(0);
                }                    
                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return clienteDomicilio;
    }

    
    public ClienteDomicilioModel getByPkClienteAndDomicilio(Integer pkCliente, Integer pkDomicilio) {
        List<ClienteDomicilioModel> clienteDomicilios = null;        
        try {
            if(pkCliente != null && pkDomicilio != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from ClienteDomicilioModel WHERE idCliente=:idCliente AND idDomicilio=:idDomicilio");
                query.setInteger("idCliente", pkCliente);
                query.setInteger("idDomicilio", pkDomicilio);
                
                clienteDomicilios = query.list();
                
                if(clienteDomicilios != null && !clienteDomicilios.isEmpty()) {
                    return clienteDomicilios.get(0);
                }                    
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
    
}
