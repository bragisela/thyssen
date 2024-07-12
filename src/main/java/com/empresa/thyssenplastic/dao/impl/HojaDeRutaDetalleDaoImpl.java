/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dao.impl;

import com.empresa.thyssenplastic.dao.HojaDeRutaDao;
import com.empresa.thyssenplastic.dao.HojaDeRutaDetalleDao;
import com.empresa.thyssenplastic.hibernate.HibernateUtil;
import com.empresa.thyssenplastic.model.HojaDeRutaDetalleModel;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author gusta
 */
public class HojaDeRutaDetalleDaoImpl extends GenericDaoImpl implements HojaDeRutaDetalleDao {
    
    public List<HojaDeRutaDetalleModel> getAll() {
        List<HojaDeRutaDetalleModel> hojasDeRutaDetalle = new ArrayList<HojaDeRutaDetalleModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            hojasDeRutaDetalle = session.createQuery("from HojaDeRutaDetalleModel ", HojaDeRutaDetalleModel.class).list();                        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return hojasDeRutaDetalle;
    }

    public HojaDeRutaDetalleModel getByPk(Integer pk) {
        HojaDeRutaDetalleModel hojaDeRutaDetalle = null;        
        try {
            if(pk != null){
                Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from HojaDeRutaDetalleModel WHERE id=:id");
                query.setInteger("id", pk);
                
                List<HojaDeRutaDetalleModel> hojasDeRutaDetalle = query.list();
                if(hojasDeRutaDetalle != null && !hojasDeRutaDetalle.isEmpty()) {
                    hojaDeRutaDetalle = hojasDeRutaDetalle.get(0);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return hojaDeRutaDetalle;
    }

    public List<HojaDeRutaDetalleModel> getAllByHojaDeRuta(Integer idHojaDeRuta) {
        List<HojaDeRutaDetalleModel> hojasDeRutaDetalle = new ArrayList<HojaDeRutaDetalleModel>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from HojaDeRutaDetalleModel WHERE idHojaDeRuta=:idHojaDeRuta ORDER BY Orden ASC");
                query.setInteger("idHojaDeRuta", idHojaDeRuta);
                
                hojasDeRutaDetalle = query.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return hojasDeRutaDetalle;
    }

    public HojaDeRutaDetalleModel getByRemito(Integer idRemito) {
        HojaDeRutaDetalleModel hojaDeRutaDetalle = null;    
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
                Query query = session.createQuery("from HojaDeRutaDetalleModel WHERE idRemito=:idRemito");
                query.setInteger("idRemito", idRemito);
                
                List<HojaDeRutaDetalleModel> hojasDeRutaDetalle = query.list();
                if(hojasDeRutaDetalle != null && !hojasDeRutaDetalle.isEmpty()) {
                    hojaDeRutaDetalle = hojasDeRutaDetalle.get(0);
                }                
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return hojaDeRutaDetalle;
    }
    
}
