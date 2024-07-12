/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.ClienteDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.ClienteModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class ClienteDaoImpl extends GenericDaoImpl implements ClienteDao {
    
        public List<ClienteModel> getAll() {
        List<ClienteModel> clientes = new ArrayList<ClienteModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            clientes = session.createQuery("from ClienteModel", ClienteModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return clientes;
    }

    public ClienteModel getByPk(Integer pk) {
        ClienteModel cliente = null;        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from ClienteModel WHERE id=:id");
                query.setInteger("id", pk);
                
                List<ClienteModel> clientes = query.list();
                if(clientes != null && !clientes.isEmpty()) {
                    cliente = clientes.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return cliente;
    }

}
