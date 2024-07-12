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
import org.springframework.web.servlet.ModelAndView;
import com.empresa.thyssenplastic.service.impl.TipoServiceImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.empresa.thyssenplastic.service.ActivacionManualService;
import com.empresa.thyssenplastic.service.UserService;
import com.empresa.thyssenplastic.service.impl.ActivacionManualServiceImpl;
import com.empresa.thyssenplastic.service.impl.UserServiceImpl;
import com.empresa.thyssenplastic.controller.form.ActivacionManualForm;
import com.empresa.thyssenplastic.model.UserModel;
import com.empresa.thyssenplastic.model.ActivacionManualModel;

/**
 *
 * @author gusta
 */
@Controller
public class ActivacionManualController {


    @RequestMapping(value = "/activacionmanual", method = RequestMethod.GET)
    public String getHomeTipo(HttpServletRequest req, ModelMap model) {

        if (!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());
            return "/login/login";
        }

        ActivacionManualService activacionManualService = new ActivacionManualServiceImpl();
              
        ActivacionManualModel activacionManual = activacionManualService.getByPk(1);

        ActivacionManualForm activacionManualForm = new ActivacionManualForm();
        activacionManualForm.setAction("add");
        model.addAttribute("tipoForm", activacionManualForm);
        model.addAttribute("titleTipo", "Activación Manual");
        model.addAttribute("status", activacionManual.getActivacionManual());
        model.addAttribute("buttonLabel", "Guardar");

        //List<TipoModel> tipos = tipoService.getAllByOrder();
        //List<TipoDto> tiposDtos = new ArrayList<TipoDto>();
        //for (TipoModel tipo : tipos) {
            //TipoDto tipoDto = new TipoDto();
            //tipoDto.setPk(tipo.getId().toString());
            //tipoDto.setTipo(tipo.getTipo());
            //tipoDto.setValor(tipo.getValor());

            //tiposDtos.add(tipoDto);
        //}

        //model.addAttribute("tipos", tiposDtos);

        return "/activacionmanual/activacionmanual";
    }
    
    @RequestMapping(value = "/activacionmanual/setActivacionManual/{activacionManualPk}", method = RequestMethod.GET)
    public String setActivacionManual(@PathVariable String activacionManualPk, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

       if(!Utils.isAutenticated(req)) {
           model.addAttribute("userForm", new UserForm());         
           return "/login/login";            
        }

        if(activacionManualPk == null || activacionManualPk.isEmpty()) {
            model.addAttribute("errorMessage", "Error: Activacion Manual inválida");         
           return "/error";                
        }
       
        if(user.getRol() != Utils.ROL_OFICINA) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
                 
        ActivacionManualService activacionManualService = new ActivacionManualServiceImpl();      
        
        ActivacionManualModel activacionManual = activacionManualService.getByPk(1);

        if(activacionManual == null) {            
            model.addAttribute("errorMessage", "Error: orden de compra no encontrada");
            return "/error";
        }
         
        if (activacionManual.getActivacionManual()) {
            activacionManual.setActivacionManual(false);
        }else{
            activacionManual.setActivacionManual(true);
        }
        
        activacionManualService.save(activacionManual);
        
        return "redirect:/activacionmanual";                         
        
    }
    

}
