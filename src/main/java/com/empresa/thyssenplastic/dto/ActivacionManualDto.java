/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.dto;

import java.io.Serializable;

/**
 *
 * @author gusta
 */
public class ActivacionManualDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String pk;
    private Boolean activacionManual;
    

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public Boolean getActivacionManual() {
        return activacionManual;
    }

    public void setActivacionManual(boolean activacionManual) {
        this.activacionManual = activacionManual;
    }

    
}
