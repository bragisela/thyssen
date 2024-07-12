/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.HojaDeRutaDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.HojaDeRutaModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class HojaDeRutaDaoImpl extends GenericDaoImpl implements HojaDeRutaDao {
    
    public List<HojaDeRutaModel> getAll() {
        List<HojaDeRutaModel> hojasDeRuta = new ArrayList<HojaDeRutaModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            hojasDeRuta = session.createQuery("from HojaDeRutaModel ", HojaDeRutaModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return hojasDeRuta;
    }

    public HojaDeRutaModel getByPk(Integer pk) {
        HojaDeRutaModel hojaDeRuta = null;        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from HojaDeRutaModel WHERE id=:id");
                query.setInteger("id", pk);
                
                List<HojaDeRutaModel> hojasDeRuta = query.list();
                if(hojasDeRuta != null && !hojasDeRuta.isEmpty()) {
                    hojaDeRuta = hojasDeRuta.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return hojaDeRuta;
    }

}
