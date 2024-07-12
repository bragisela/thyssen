/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

 
import com.empresa.thyssenplastic.controller.form.ArticuloPrevisionForm;
import com.empresa.thyssenplastic.controller.form.UserForm;
import com.empresa.thyssenplastic.dto.ArticuloPrevisionDto;
import com.empresa.thyssenplastic.model.ArticuloPrevisionModel;
import com.empresa.thyssenplastic.model.ArticuloModel;
import com.empresa.thyssenplastic.service.ArticuloPrevisionService;
import com.empresa.thyssenplastic.service.ArticuloService;
import com.empresa.thyssenplastic.service.impl.ArticuloPrevisionServiceImpl;
import com.empresa.thyssenplastic.service.impl.ArticuloServiceImpl;
import com.empresa.thyssenplastic.utils.Utils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author gusta
 */
@Controller
public class ArticuloPrevisionController {
    

    @RequestMapping(value = "/articulo/articuloprevision/{articulopk}", method = RequestMethod.GET)
    public String getHomeArticuloPrevision(@PathVariable String articulopk, HttpServletRequest req, ModelMap model) {

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(articulopk == null) {
            model.addAttribute("errorMessage", "Error: Artículo inválido");         
            return "/error/error";                
        }
        
        ArticuloService articuloService = new ArticuloServiceImpl();   
        ArticuloModel articulo = articuloService.getByPk(Integer.valueOf(articulopk));
        if(articulo == null) {
            model.addAttribute("errorMessage", "Error: Proveedor inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        String externalName = articulo.getId() + " - " + articulo.getDenominacion();
        
        ArticuloPrevisionForm articuloPrevisionForm = new ArticuloPrevisionForm();
        articuloPrevisionForm.setPk("-1");
        articuloPrevisionForm.setAction("add");
                
        model.addAttribute("displayActionButton", "block");
        model.addAttribute("pkExternal", articulopk);
        model.addAttribute("prefixUrl", "articulo");
        model.addAttribute("labelBase", "Articulos");                
        model.addAttribute("externalName", externalName);
        model.addAttribute("articuloPrevisionForm", articuloPrevisionForm);  
        model.addAttribute("titleArticuloPrevision", "Nuevo Articulo Etiqueta");  
        model.addAttribute("buttonLabel", "Guardar");
        
        ArticuloPrevisionService articuloPrevisionService = new ArticuloPrevisionServiceImpl();   
        List<ArticuloPrevisionModel> articulosprevision = articuloPrevisionService.getAllByArticulo(Integer.valueOf(articulopk));
        
        List<ArticuloPrevisionDto> articulosprevisionDtos = new ArrayList<ArticuloPrevisionDto>();
        for(ArticuloPrevisionModel articuloprevision: articulosprevision) {
            ArticuloPrevisionDto articuloprevisionDto = new ArticuloPrevisionDto();
            articuloprevisionDto.setPk(articuloprevision.getId().toString());            
            articuloprevisionDto.setFechaAlta(articuloprevision.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            articuloprevisionDto.setDisponible(articuloprevision.getDisponible().toString());            
            articuloprevisionDto.setProduccion(articuloprevision.getProduccion().toString());            
            articuloprevisionDto.setPuntoDePedido(articuloprevision.getPuntoDePedido().toString());            
            articulosprevisionDtos.add(articuloprevisionDto);
        }
                             
        model.addAttribute("articulosprevision", articulosprevisionDtos);        
                
        return "/articuloprevision/articuloprevision";
    }
    
    @RequestMapping(value = "/articulo/articuloprevision/addOrEditOrRemove", method = RequestMethod.POST)
    public ModelAndView addOrEditOrRemoveArticuloPrevision(@ModelAttribute("articuloPrevisionForm")ArticuloPrevisionForm articuloPrevisionForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {
        
        ModelAndView modelAndView = new ModelAndView();
        
        if (result.hasErrors()) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;
        }
        
        if(!Utils.isAutenticated(req)) {
            modelAndView.setViewName("/login/login");    
            model.addAttribute("userForm", new UserForm());         
            return modelAndView;
        }
        
        if(articuloPrevisionForm.getAction() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;            
        
        }

        if(articuloPrevisionForm.getPkExternal() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;            
        
        }
        
        String articulopk = articuloPrevisionForm.getPkExternal();        
        ArticuloPrevisionService articuloPrevisionService = new ArticuloPrevisionServiceImpl();        
        
        String sessionId = req.getSession().getId();
        String id = articuloPrevisionForm.getPk();
            
        ArticuloPrevisionModel articuloPrevisionModel = null;
        if(id.equalsIgnoreCase("-1")) {
            articuloPrevisionModel = new ArticuloPrevisionModel();
        } else {
            articuloPrevisionModel = articuloPrevisionService.getByPk(Integer.valueOf(id));
            if(articuloPrevisionModel == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: id de Artículo Etiqueta inválido.");
                return modelAndView;                
            } 
        }                
        
        if(articuloPrevisionForm.getFechaAlta() != null && !articuloPrevisionForm.getFechaAlta().trim().equals("")) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
            Date fecha = formato.parse(articuloPrevisionForm.getFechaAlta());
            articuloPrevisionModel.setFechaAlta(fecha);
        } else {
            articuloPrevisionModel.setFechaAlta(null);
        }        
        
        articuloPrevisionModel.setIdArticulo(Integer.valueOf(articulopk));
        
        if(articuloPrevisionForm.getDisponible() != null) {
            articuloPrevisionModel.setDisponible(Integer.valueOf(articuloPrevisionForm.getDisponible()));
        }

        if(articuloPrevisionForm.getProduccion()!= null) {
            articuloPrevisionModel.setProduccion(Integer.valueOf(articuloPrevisionForm.getProduccion()));
        }

        if(articuloPrevisionForm.getPuntoDePedido() != null) {
            articuloPrevisionModel.setPuntoDePedido(Integer.valueOf(articuloPrevisionForm.getPuntoDePedido()));
        }
        
        if(articuloPrevisionForm.getAction().equalsIgnoreCase("add") || articuloPrevisionForm.getAction().equalsIgnoreCase("edit")) {
            articuloPrevisionService.save(articuloPrevisionModel);
        } else {
            if(articuloPrevisionForm.getAction().equalsIgnoreCase("remove")) {
                if(articuloPrevisionModel.getId() == null) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: id de Artículo Etiqueta inválido.");
                    return modelAndView;                                    
                }                                      
                articuloPrevisionService.delete(articuloPrevisionModel);
            } else {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: operación inválida.");
                return modelAndView;                                
            }
        }
                        
        modelAndView.setViewName("redirect:/articulo/articuloprevision/"+articulopk);

        return modelAndView; 
    }

    @RequestMapping(value = "/articulo/articuloprevision/edit/{articuloprevisionpk}", method = RequestMethod.GET)
    public String editArticuloPrevision(@PathVariable String articuloprevisionpk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(articuloprevisionpk == null) {
            model.addAttribute("errorMessage", "Error: Artículo Etiqueta inválido");         
            return "/error/error";                
        }
        
        ArticuloPrevisionService articuloprevisionService = new ArticuloPrevisionServiceImpl();   
        ArticuloPrevisionModel articuloprevision = articuloprevisionService.getByPk(Integer.valueOf(articuloprevisionpk));
        if(articuloprevision == null) {
            model.addAttribute("errorMessage", "Error: Artículo Etiqueta inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        ArticuloPrevisionForm articuloprevisionForm = new ArticuloPrevisionForm();
        articuloprevisionForm.setPk(articuloprevision.getId().toString());
        if(articuloprevision.getFechaAlta() != null) {
            articuloprevisionForm.setFechaAlta(articuloprevision.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        
        
        articuloprevisionForm.setAction("edit");
        model.addAttribute("articuloPrevisionForm", articuloprevisionForm);  
        model.addAttribute("titleArticuloPrevision", "Editar Etiqueta Artículo");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("articuloPrevisionName", articuloprevision.getId());        
        
        List<ArticuloPrevisionModel> articulosprevision = articuloprevisionService.getAll();
        
        List<ArticuloPrevisionDto> articulosprevisionDtos = new ArrayList<ArticuloPrevisionDto>();
        for(ArticuloPrevisionModel p: articulosprevision) {
            ArticuloPrevisionDto articuloPrevisionDto = new ArticuloPrevisionDto();
            articuloPrevisionDto.setPk(p.getId().toString());
            articuloPrevisionDto.setDisponible(p.getDisponible().toString());            
            articuloPrevisionDto.setProduccion(p.getProduccion().toString());            
            articuloPrevisionDto.setPuntoDePedido(p.getPuntoDePedido().toString());            
            
            articulosprevisionDtos.add(articuloPrevisionDto);
        }
        
               
        model.addAttribute("articulosprevision", articulosprevisionDtos);
                
        return "/articuloprevision/articuloprevision";        
    }
    
    @RequestMapping(value = "/articulo/articuloprevision/remove/{articuloprevisionpk}", method = RequestMethod.GET)
    public String removeArticuloPrevision(@PathVariable String articuloprevisionpk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(articuloprevisionpk == null) {
            model.addAttribute("errorMessage", "Error: Artículo Etiqueta inválido");         
            return "/error/error";                
        }
        
        ArticuloPrevisionService articuloPrevisionService = new ArticuloPrevisionServiceImpl();   
        ArticuloPrevisionModel articuloprevision = articuloPrevisionService.getByPk(Integer.valueOf(articuloprevisionpk));
        if(articuloprevision == null) {
            model.addAttribute("errorMessage", "Error: Artículo Etiqueta inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        ArticuloPrevisionForm articuloprevisionForm = new ArticuloPrevisionForm();
        articuloprevisionForm.setPk(articuloprevision.getId().toString());        
        if(articuloprevision.getFechaAlta() != null) {
            articuloprevisionForm.setFechaAlta(articuloprevision.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        
        if(articuloprevision.getDisponible() != null) {
            articuloprevisionForm.setDisponible(articuloprevision.getDisponible().toString());
        }

        if(articuloprevision.getProduccion() != null) {
            articuloprevisionForm.setProduccion(articuloprevision.getProduccion().toString());
        }

        if(articuloprevision.getPuntoDePedido() != null) {
            articuloprevisionForm.setPuntoDePedido(articuloprevision.getPuntoDePedido().toString());
        }
        
        String articulopk = articuloprevision.getIdArticulo().toString();
        
        ArticuloService articuloService = new ArticuloServiceImpl();   
        ArticuloModel articulo = articuloService.getByPk(Integer.valueOf(articulopk));
        if(articulo == null) {
            model.addAttribute("errorMessage", "Error: Proveedor inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        String externalName = articulo.getId() + " - " + articulo.getDenominacion();
                        
        articuloprevisionForm.setAction("remove");
        
        model.addAttribute("displayActionButton", "block");
        model.addAttribute("pkExternal", articulopk);
        model.addAttribute("prefixUrl", "articulo");
        model.addAttribute("labelBase", "Articulos");                
        model.addAttribute("externalName", externalName);
        
        model.addAttribute("articuloPrevisionForm", articuloprevisionForm);  
        model.addAttribute("titleArticuloPrevision", "Eliminar Etiqueta Artículo");
        model.addAttribute("buttonLabel", "Eliminar");
        model.addAttribute("articuloName", articuloprevision.getId());
        
        List<ArticuloPrevisionModel> articulosprevision = articuloPrevisionService.getAllByArticulo(articulo.getId());
        
        List<ArticuloPrevisionDto> articulosprevisionDtos = new ArrayList<ArticuloPrevisionDto>();
        for(ArticuloPrevisionModel p: articulosprevision) {
            ArticuloPrevisionDto articuloprevisionDto = new ArticuloPrevisionDto();
            articuloprevisionDto.setPk(p.getId().toString());
            articuloprevisionDto.setFechaAlta(p.getFechaAlta().toString());
            articuloprevisionDto.setDisponible(p.getDisponible().toString());            
            articuloprevisionDto.setProduccion(p.getProduccion().toString());            
            articuloprevisionDto.setPuntoDePedido(p.getPuntoDePedido().toString());            
            
            articulosprevisionDtos.add(articuloprevisionDto);
        }

               
        model.addAttribute("articulosprevision", articulosprevisionDtos);
                
        return "/articuloprevision/articuloprevision";        
    }    
    
    @RequestMapping(value = "/articulo/articuloprevision/view/{articuloprevisionpk}", method = RequestMethod.GET)
    public String viewArticuloPrevision(@PathVariable String articuloprevisionpk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(articuloprevisionpk == null) {
            model.addAttribute("errorMessage", "Error: Artículo Etiqueta inválido");         
            return "/error/error";                
        }
        
        ArticuloPrevisionService articuloPrevisionService = new ArticuloPrevisionServiceImpl();   
        ArticuloPrevisionModel articuloprevision = articuloPrevisionService.getByPk(Integer.valueOf(articuloprevisionpk));
        if(articuloprevision == null) {
            model.addAttribute("errorMessage", "Error: Artículo Etiqueta inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        ArticuloPrevisionForm articuloprevisionForm = new ArticuloPrevisionForm();
        articuloprevisionForm.setPk(articuloprevision.getId().toString());        
        if(articuloprevision.getFechaAlta() != null) {
            articuloprevisionForm.setFechaAlta(articuloprevision.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }

        if(articuloprevision.getDisponible() != null) {
            articuloprevisionForm.setDisponible(articuloprevision.getDisponible().toString());
        }

        if(articuloprevision.getProduccion() != null) {
            articuloprevisionForm.setProduccion(articuloprevision.getProduccion().toString());
        }

        if(articuloprevision.getPuntoDePedido() != null) {
            articuloprevisionForm.setPuntoDePedido(articuloprevision.getPuntoDePedido().toString());
        }
        
        String articulopk = articuloprevision.getIdArticulo().toString();
        
        ArticuloService articuloService = new ArticuloServiceImpl();   
        ArticuloModel articulo = articuloService.getByPk(Integer.valueOf(articulopk));
        if(articulo == null) {
            model.addAttribute("errorMessage", "Error: Proveedor inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        String externalName = articulo.getId() + " - " + articulo.getDenominacion();
                        
        articuloprevisionForm.setAction("remove");
        
        model.addAttribute("displayActionButton", "none");
        model.addAttribute("pkExternal", articulopk);
        model.addAttribute("prefixUrl", "articulo");
        model.addAttribute("labelBase", "Articulos");                
        model.addAttribute("externalName", externalName);
        
        model.addAttribute("articuloPrevisionForm", articuloprevisionForm);  
        model.addAttribute("titleArticuloPrevision", "Ver Etiqueta Artículo "+articulo.getId());
        model.addAttribute("buttonLabel", "Eliminar");
        model.addAttribute("articuloName", articuloprevision.getId());
        
        List<ArticuloPrevisionModel> articulosprevision = articuloPrevisionService.getAllByArticulo(articulo.getId());
        
        List<ArticuloPrevisionDto> articulosprevisionDtos = new ArrayList<ArticuloPrevisionDto>();
        for(ArticuloPrevisionModel p: articulosprevision) {
            ArticuloPrevisionDto articuloprevisionDto = new ArticuloPrevisionDto();
            articuloprevisionDto.setPk(p.getId().toString());
            articuloprevisionDto.setFechaAlta(p.getFechaAlta().toString());
            articuloprevisionDto.setDisponible(p.getDisponible().toString());            
            articuloprevisionDto.setProduccion(p.getProduccion().toString());            
            articuloprevisionDto.setPuntoDePedido(p.getPuntoDePedido().toString());            
            
            articulosprevisionDtos.add(articuloprevisionDto);
        }

               
        model.addAttribute("articulosprevision", articulosprevisionDtos);
                
        return "/articuloprevision/articuloprevision";        
    }        
}
