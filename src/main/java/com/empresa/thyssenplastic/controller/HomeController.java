/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

import com.empresa.thyssenplastic.controller.form.UserForm;
import com.empresa.thyssenplastic.utils.Utils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author gusta
 */
@Controller
public class HomeController {
    
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHome(HttpServletRequest req, ModelMap model) {
        
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }
        
        return "/home";
    }
}
