/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.ClienteContactoDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.ClienteContactoModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class ClienteContactoDaoImpl extends GenericDaoImpl implements ClienteContactoDao {
    
        public List<ClienteContactoModel> getAll() {
        List<ClienteContactoModel> clienteContactos = new ArrayList<ClienteContactoModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            clienteContactos = session.createQuery("from ClienteContactoModel", ClienteContactoModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return clienteContactos;
    }

    public List<ClienteContactoModel> getByPkCliente(Integer pk) {
        List<ClienteContactoModel> clienteContactos = new ArrayList<ClienteContactoModel>();        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from ClienteContactoModel WHERE idCliente=:idCliente");
                query.setInteger("idCliente", pk);
                
                clienteContactos = query.list();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return clienteContactos;
    }

    public ClienteContactoModel getByPkClienteAndContacto(Integer pkCliente, Integer pkContacto) {
        List<ClienteContactoModel> clienteContactos = null;        
        try {
            if(pkCliente != null && pkContacto != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from ClienteContactoModel WHERE idCliente=:idCliente AND idContacto=:idContacto");
                query.setInteger("idCliente", pkCliente);
                query.setInteger("idContacto", pkContacto);
                
                clienteContactos = query.list();
                
                if(clienteContactos != null && !clienteContactos.isEmpty()) {
                    return clienteContactos.get(0);
                }                    
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
    
}
