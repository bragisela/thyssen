/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.hibernate;

import com.empresa.thyssenplastic.model.AjusteDeInventarioModel;
import com.empresa.thyssenplastic.model.ClienteContactoModel;
import com.empresa.thyssenplastic.model.ClienteDomicilioModel;
import com.empresa.thyssenplastic.model.ClienteModel;
import com.empresa.thyssenplastic.model.ContactoModel;
import com.empresa.thyssenplastic.model.DomicilioModel;
import com.empresa.thyssenplastic.model.InsumoModel;
import com.empresa.thyssenplastic.model.MateriaPrimaModel;
import com.empresa.thyssenplastic.model.ProveedorContactoModel;
import com.empresa.thyssenplastic.model.ProveedorDomicilioModel;
import com.empresa.thyssenplastic.model.ProveedorModel;
import com.empresa.thyssenplastic.model.TipoModel;
import com.empresa.thyssenplastic.model.UserModel;
import com.empresa.thyssenplastic.model.ConfiguracionModel;
import com.empresa.thyssenplastic.model.RepuestoModel;
import com.empresa.thyssenplastic.model.ArticuloEtiquetaModel;
import com.empresa.thyssenplastic.model.ArticuloFichaTecnicaModel;
import com.empresa.thyssenplastic.model.ArticuloModel;
import com.empresa.thyssenplastic.model.ArticuloPrevisionModel;
import com.empresa.thyssenplastic.model.GraficoBobinaDetalleModel;
import com.empresa.thyssenplastic.model.GraficoBobinaModel;
import com.empresa.thyssenplastic.model.HojaDeRutaDetalleModel;
import com.empresa.thyssenplastic.model.HojaDeRutaModel;
import com.empresa.thyssenplastic.model.IngresarDepositoModel;
import com.empresa.thyssenplastic.model.LocalidadModel;
import com.empresa.thyssenplastic.model.MantenimientoCorrectivoModel;
import com.empresa.thyssenplastic.model.OrdenDeCompraItemModel;
import com.empresa.thyssenplastic.model.OrdenDeCompraItemRecepcionModel;
import com.empresa.thyssenplastic.model.OrdenDeCompraModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionBobinaModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionBultoModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionPalletBultoModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionPalletModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionScrapModel;
import com.empresa.thyssenplastic.model.RemitoDetalleModel;
import com.empresa.thyssenplastic.model.RemitoModel;
import com.empresa.thyssenplastic.model.ActivacionManualModel;
import com.empresa.thyssenplastic.model.EgresoDepositoModel;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


/**
 *
 * @author gusta
 */
public class HibernateUtil {
    
    
    private static SessionFactory sessionFactory;
    
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                        
                
                System.out.println("*** user.dir: "+System.getProperty("user.dir"));
                Properties properties= new Properties();
                properties.load(new FileInputStream(new File("local.properties")));
      
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, properties.get("db.driver"));
                settings.put(Environment.URL, properties.get("db.url"));
                settings.put(Environment.USER, properties.get("db.username"));
                settings.put(Environment.PASS, properties.get("db.password"));
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, properties.get("db.showsql"));                
                settings.put(Environment.FORMAT_SQL, "true");

                configuration.addAnnotatedClass(UserModel.class);                
                configuration.addAnnotatedClass(ContactoModel.class);
                configuration.addAnnotatedClass(DomicilioModel.class);
                configuration.addAnnotatedClass(ProveedorModel.class);
                configuration.addAnnotatedClass(ProveedorContactoModel.class);                
                configuration.addAnnotatedClass(ProveedorDomicilioModel.class);
                configuration.addAnnotatedClass(TipoModel.class);
                configuration.addAnnotatedClass(ActivacionManualModel.class);
                configuration.addAnnotatedClass(EgresoDepositoModel.class);
                configuration.addAnnotatedClass(ClienteModel.class);
                configuration.addAnnotatedClass(ClienteContactoModel.class);                
                configuration.addAnnotatedClass(ClienteDomicilioModel.class);
                configuration.addAnnotatedClass(InsumoModel.class);
                configuration.addAnnotatedClass(MateriaPrimaModel.class);
                configuration.addAnnotatedClass(ConfiguracionModel.class);
                configuration.addAnnotatedClass(RepuestoModel.class);
                configuration.addAnnotatedClass(ArticuloEtiquetaModel.class);
                configuration.addAnnotatedClass(ArticuloModel.class);
                configuration.addAnnotatedClass(ArticuloPrevisionModel.class);
                configuration.addAnnotatedClass(AjusteDeInventarioModel.class);
                configuration.addAnnotatedClass(MantenimientoCorrectivoModel.class);
                configuration.addAnnotatedClass(LocalidadModel.class);
                configuration.addAnnotatedClass(OrdenDeCompraModel.class);
                configuration.addAnnotatedClass(OrdenDeCompraItemModel.class);
                configuration.addAnnotatedClass(OrdenDeCompraItemRecepcionModel.class);
                configuration.addAnnotatedClass(ArticuloFichaTecnicaModel.class);
                configuration.addAnnotatedClass(OrdenDeProduccionModel.class);
                configuration.addAnnotatedClass(OrdenDeProduccionBobinaModel.class);
                configuration.addAnnotatedClass(OrdenDeProduccionBultoModel.class);
                configuration.addAnnotatedClass(OrdenDeProduccionPalletModel.class);
                configuration.addAnnotatedClass(OrdenDeProduccionPalletBultoModel.class);
                configuration.addAnnotatedClass(OrdenDeProduccionScrapModel.class);
                configuration.addAnnotatedClass(GraficoBobinaModel.class);
                configuration.addAnnotatedClass(GraficoBobinaDetalleModel.class);
                configuration.addAnnotatedClass(RemitoModel.class);
                configuration.addAnnotatedClass(RemitoDetalleModel.class);
                configuration.addAnnotatedClass(IngresarDepositoModel.class);
                configuration.addAnnotatedClass(HojaDeRutaModel.class);
                configuration.addAnnotatedClass(HojaDeRutaDetalleModel.class);
                
                configuration.setProperties(settings);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

}
