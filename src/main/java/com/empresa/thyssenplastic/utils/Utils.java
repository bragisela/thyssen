/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.utils;

import com.empresa.thyssenplastic.dto.SessionInformation;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author gusta
 */
public class Utils {
 
    public static String idProvinciaBsAs = "-1";
    public static final Integer ROL_PLANTA = 3;
    public static final Integer ROL_MANTENIMIENTO = 4;
    public static final Integer ROL_OFICINA = 1;
    public static final Integer ROL_DEPOSITO = 2;
    
    static { 
        Properties properties = new Properties();
        try {            
            properties.load(new FileInputStream(new File("local.properties")));
        } catch (Exception ex) {
            
        }
        //idProvinciaBsAs = properties.get("provincia.buenos.aires.id").toString();
        System.out.println("***** idProvinciaBsAs:"+idProvinciaBsAs);

    }
    
    public static boolean isAutenticated(HttpServletRequest req) {
        SessionInformation sessionInformation = (SessionInformation)req.getSession().getAttribute("sessionInformation");
        String sessionId = req.getSession().getId();
        if(sessionInformation == null || sessionInformation.getIsAutenticated() == null || sessionInformation.getIsAutenticated().equals("false") || !sessionInformation.getSessionId().equalsIgnoreCase(sessionId)) {
            return false;
        }
        return true;
    }
    
    public static String getUserLoggedId(HttpServletRequest req) {
        SessionInformation sessionInformation = (SessionInformation)req.getSession().getAttribute("sessionInformation");
        String sessionId = req.getSession().getId();
        if(sessionInformation == null || sessionInformation.getIsAutenticated() == null || sessionInformation.getIsAutenticated().equals("false") || !sessionInformation.getSessionId().equalsIgnoreCase(sessionId)) {
            return "-1";
        }
        return sessionInformation.getUserId();
    }

    public static String getUserNameLogged(HttpServletRequest req) {
        SessionInformation sessionInformation = (SessionInformation)req.getSession().getAttribute("sessionInformation");
        String sessionId = req.getSession().getId();
        if(sessionInformation == null || sessionInformation.getIsAutenticated() == null || sessionInformation.getIsAutenticated().equals("false") || !sessionInformation.getSessionId().equalsIgnoreCase(sessionId)) {
            return "-1";
        }
        return sessionInformation.getUserName();
    }

    public static String getIdProvinciaBsAs() {
        return idProvinciaBsAs;
    }
     
}
