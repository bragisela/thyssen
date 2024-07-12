/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.ConfiguracionDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.ConfiguracionModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class ConfiguracionDaoImpl extends GenericDaoImpl implements ConfiguracionDao {
    
    public List<ConfiguracionModel> getAll() {
        List<ConfiguracionModel> configuraciones = new ArrayList<ConfiguracionModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            configuraciones = session.createQuery("from ConfiguracionModel ORDER BY descripcion", ConfiguracionModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return configuraciones;
    }

        public List<ConfiguracionModel> getAllByIdTipo(Integer idTipo) {
        List<ConfiguracionModel> configuraciones = new ArrayList<ConfiguracionModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from ConfiguracionModel WHERE idTipo =:idTipo ORDER BY descripcion", ConfiguracionModel.class);
            query.setParameter("idTipo", idTipo);
            
            return query.list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return configuraciones;
    }

    public ConfiguracionModel getByPk(Integer pk) {
        ConfiguracionModel configuracion = null;        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from ConfiguracionModel WHERE id=:id");
                query.setInteger("id", pk);
                
                List<ConfiguracionModel> configuraciones = query.list();
                if(configuraciones != null && !configuraciones.isEmpty()) {
                    configuracion = configuraciones.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return configuracion;
    }

}
