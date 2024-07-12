/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.ProveedorDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.ProveedorModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class ProveedorDaoImpl extends GenericDaoImpl implements ProveedorDao {
    
    public List<ProveedorModel> getAll() {
        List<ProveedorModel> proveedores = new ArrayList<ProveedorModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            proveedores = session.createQuery("from ProveedorModel ORDER BY razonSocial", ProveedorModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return proveedores;
    }

    public List<ProveedorModel> getAllByRubro(Integer idRubro) {
        List<ProveedorModel> proveedores = new ArrayList<ProveedorModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from ProveedorModel WHERE idRubro=:idRubro ORDER BY razonSocial");
            query.setInteger("idRubro", idRubro);

            proveedores = query.list();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return proveedores;
    }

        
    public ProveedorModel getByPk(Integer pk) {
        ProveedorModel proveedor = null;        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from ProveedorModel WHERE id=:id");
                query.setInteger("id", pk);
                
                List<ProveedorModel> proveedores = query.list();
                if(proveedores != null && !proveedores.isEmpty()) {
                    proveedor = proveedores.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return proveedor;
    }

}
