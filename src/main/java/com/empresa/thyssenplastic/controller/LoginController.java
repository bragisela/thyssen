/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

 
import com.empresa.thyssenplastic.controller.form.UserForm;
import com.empresa.thyssenplastic.dto.SessionInformation;
import com.empresa.thyssenplastic.dto.UsuarioDto;
import com.empresa.thyssenplastic.model.UserModel;
import com.empresa.thyssenplastic.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.empresa.thyssenplastic.service.UserService;

/**
 *
 * @author gusta
 */
@Controller
@SessionAttributes("sessionInformation")
public class LoginController {
            
    @RequestMapping(value = "/login/user", method = RequestMethod.POST)
    public ModelAndView loginUser(@ModelAttribute("userForm")UserForm userForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {
        
        ModelAndView modelAndView = new ModelAndView();
        
        if (result.hasErrors()) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;
        }
        
        String username = userForm.getUserName();
        String password = userForm.getPassword();
        String sessionId = req.getSession().getId();
        
        UserService userService = new UserServiceImpl();        
        UserModel userModel = userService.getUserByUsernameAndPassword(username, password);
                        
        if(userModel != null) {
            modelAndView.setViewName("home");
            
            SessionInformation sessionInformation = new SessionInformation();
            sessionInformation.setEmailUser(userModel.getUsername());      
            sessionInformation.setUserId(userModel.getId().toString());  
            sessionInformation.setIsAutenticated("true");
            sessionInformation.setSessionId(sessionId);
            sessionInformation.setRolUser(userModel.getRol().toString());

            modelAndView.addObject("sessionInformation", sessionInformation);
        } else {
            modelAndView.setViewName("login/login");
            modelAndView.addObject("errorMessage", "Usuario o password inválida");
        }
        
        return modelAndView; 
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logOut(@ModelAttribute("userForm")UserForm userForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {

        SessionInformation sessionInformation = new SessionInformation();
        sessionInformation.setEmailUser(null);
        sessionInformation.setUserId(null);
        sessionInformation.setIsAutenticated("false");
        sessionInformation.setSessionId(null);
        sessionInformation.setRolUser("-1");
        
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("sessionInformation", sessionInformation);
        modelAndView.setViewName("/login/login");
        
        return modelAndView;
    }
    
    @RequestMapping(value = "/login/user2", method = RequestMethod.GET)
    public String login2(HttpServletRequest req, ModelMap model) throws Exception {

        UsuarioDto value = (UsuarioDto)req.getSession().getAttribute("userLogged");
        String sessionId = req.getSession().getId();
        
        System.out.println("**** 2 value:"+value.getNombre() + " sessionId:"+sessionId);
        
        return "hello_1"; 
    }

    @RequestMapping(value = "/login/user3", method = RequestMethod.GET)
    public String login3(HttpServletRequest req, ModelMap model) throws Exception {

        UsuarioDto value = (UsuarioDto)req.getSession().getAttribute("userLogged");
        String sessionId = req.getSession().getId();
        
        System.out.println("**** 3 value:"+value.getNombre() + " sessionId:"+sessionId);
        
        return "hello_1"; 
    }

    
    @RequestMapping(value = "/authenticateLogin", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String authenticateLogin(){
        UsuarioDto obj = new UsuarioDto();
        return "{\"test\": \"Hello using @ResponseBody\"}";
    }    
    /*
    @RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
    public ModelAndView hello(@PathVariable("name") String name) {

            ModelAndView model = new ModelAndView();
            model.setViewName("hello");
            model.addObject("msg", name);

            return model;

    }
    */
}
