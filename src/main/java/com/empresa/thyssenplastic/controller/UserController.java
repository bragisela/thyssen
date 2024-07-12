/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

 
import com.empresa.thyssenplastic.controller.form.UserForm;
import com.empresa.thyssenplastic.dto.UsuarioDto;
import com.empresa.thyssenplastic.model.UserModel;
import com.empresa.thyssenplastic.service.impl.UserServiceImpl;
import com.empresa.thyssenplastic.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.empresa.thyssenplastic.service.UserService;

/**
 *
 * @author gusta
 */
@Controller
public class UserController {
    
        
    @RequestMapping(value = "/user/getAll", method = RequestMethod.GET)
    public ModelAndView loginUser(HttpServletRequest req, ModelMap model) throws Exception {
        
        ModelAndView modelAndView = new ModelAndView();
        
        if(!Utils.isAutenticated(req)) {
            modelAndView.setViewName("/login/login");    
            model.addAttribute("userForm", new UserForm());         
            return modelAndView;
        }

        modelAndView.setViewName("/user/user");
        
        UserService userService = new UserServiceImpl();        
        List<UserModel> users = userService.getAll();

        List<UsuarioDto> usuarioDtos = new ArrayList<UsuarioDto>();
        for(UserModel user: users) {
            UsuarioDto userDto = new UsuarioDto();
            userDto.setUserName(user.getUsername());
            userDto.setNombre(user.getNombre());
            userDto.setApellido(user.getApellido());
            
            usuarioDtos.add(userDto);
        }
        
        modelAndView.addObject("users", usuarioDtos);

        return modelAndView; 
    }

}
