/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

 
import com.empresa.thyssenplastic.controller.form.ArticuloEtiquetaForm;
import com.empresa.thyssenplastic.controller.form.UserForm;
import com.empresa.thyssenplastic.dto.ArticuloEtiquetaDto;
import com.empresa.thyssenplastic.model.ArticuloEtiquetaModel;
import com.empresa.thyssenplastic.model.ArticuloModel;
import com.empresa.thyssenplastic.service.ArticuloEtiquetaService;
import com.empresa.thyssenplastic.service.ArticuloService;
import com.empresa.thyssenplastic.service.impl.ArticuloEtiquetaServiceImpl;
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
public class ArticuloEtiquetaController {
    

    @RequestMapping(value = "/articulo/articuloetiqueta/{articulopk}", method = RequestMethod.GET)
    public String getHomeArticuloEtiqueta(@PathVariable String articulopk, HttpServletRequest req, ModelMap model) {

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
        
        ArticuloEtiquetaForm articuloEtiquetaForm = new ArticuloEtiquetaForm();
        articuloEtiquetaForm.setPk("-1");
        articuloEtiquetaForm.setAction("add");
                
        model.addAttribute("displayActionButton", "block");
        model.addAttribute("pkExternal", articulopk);
        model.addAttribute("prefixUrl", "articulo");
        model.addAttribute("labelBase", "Articulos");                
        model.addAttribute("externalName", externalName);
        model.addAttribute("articuloEtiquetaForm", articuloEtiquetaForm);  
        model.addAttribute("titleArticuloEtiqueta", "Nuevo Articulo Etiqueta");  
        model.addAttribute("buttonLabel", "Guardar");
        
        ArticuloEtiquetaService articuloEtiquetaService = new ArticuloEtiquetaServiceImpl();   
        List<ArticuloEtiquetaModel> articulosetiqueta = articuloEtiquetaService.getAllByArticulo(Integer.valueOf(articulopk));
        
        List<ArticuloEtiquetaDto> articulosetiquetaDtos = new ArrayList<ArticuloEtiquetaDto>();
        for(ArticuloEtiquetaModel articuloetiqueta: articulosetiqueta) {
            ArticuloEtiquetaDto articuloetiquetaDto = new ArticuloEtiquetaDto();
            articuloetiquetaDto.setPk(articuloetiqueta.getId().toString());            
            articuloetiquetaDto.setFechaAlta(articuloetiqueta.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            articulosetiquetaDtos.add(articuloetiquetaDto);
        }
                             
        model.addAttribute("articulosetiqueta", articulosetiquetaDtos);        
                
        return "/articuloetiqueta/articuloetiqueta";
    }
    
    @RequestMapping(value = "/articulo/articuloetiqueta/addOrEditOrRemove", method = RequestMethod.POST)
    public ModelAndView addOrEditOrRemoveArticuloEtiqueta(@ModelAttribute("articuloEtiquetaForm")ArticuloEtiquetaForm articuloEtiquetaForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {
        
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
        
        if(articuloEtiquetaForm.getAction() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;            
        
        }

        if(articuloEtiquetaForm.getPkExternal() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;            
        
        }
        
        String articulopk = articuloEtiquetaForm.getPkExternal();        
        ArticuloEtiquetaService articuloEtiquetaService = new ArticuloEtiquetaServiceImpl();        
        
        String sessionId = req.getSession().getId();
        String id = articuloEtiquetaForm.getPk();
            
        ArticuloEtiquetaModel articuloEtiquetaModel = null;
        if(id.equalsIgnoreCase("-1")) {
            articuloEtiquetaModel = new ArticuloEtiquetaModel();
        } else {
            articuloEtiquetaModel = articuloEtiquetaService.getByPk(Integer.valueOf(id));
            if(articuloEtiquetaModel == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: id de Artículo Etiqueta inválido.");
                return modelAndView;                
            } 
        }                
        
        if(articuloEtiquetaForm.getFechaAlta() != null && !articuloEtiquetaForm.getFechaAlta().trim().equals("")) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
            Date fecha = formato.parse(articuloEtiquetaForm.getFechaAlta());
            articuloEtiquetaModel.setFechaAlta(fecha);
        } else {
            articuloEtiquetaModel.setFechaAlta(null);
        }        
        
        articuloEtiquetaModel.setIdArticulo(Integer.valueOf(articulopk));
        articuloEtiquetaModel.setLinea1(articuloEtiquetaForm.getLinea1());
        
        if(articuloEtiquetaForm.getLinea2() != null) {
            articuloEtiquetaModel.setLinea2(articuloEtiquetaForm.getLinea2());
        }
        
        if(articuloEtiquetaForm.getLinea3() != null) {
           articuloEtiquetaModel.setLinea3(articuloEtiquetaForm.getLinea3()); 
        }

        if(articuloEtiquetaForm.getLinea4() != null) {
           articuloEtiquetaModel.setLinea4(articuloEtiquetaForm.getLinea4()); 
        }

        if(articuloEtiquetaForm.getLinea5() != null) {
           articuloEtiquetaModel.setLinea5(articuloEtiquetaForm.getLinea5()); 
        }
        
        if(articuloEtiquetaForm.getAction().equalsIgnoreCase("add") || articuloEtiquetaForm.getAction().equalsIgnoreCase("edit")) {
            articuloEtiquetaService.save(articuloEtiquetaModel);
        } else {
            if(articuloEtiquetaForm.getAction().equalsIgnoreCase("remove")) {
                if(articuloEtiquetaModel.getId() == null) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: id de Artículo Etiqueta inválido.");
                    return modelAndView;                                    
                }                                      
                articuloEtiquetaService.delete(articuloEtiquetaModel);
            } else {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: operación inválida.");
                return modelAndView;                                
            }
        }
                        
        modelAndView.setViewName("redirect:/articulo/articuloetiqueta/"+articulopk);

        return modelAndView; 
    }

    @RequestMapping(value = "/articulo/articuloetiqueta/edit/{articuloetiquetapk}", method = RequestMethod.GET)
    public String editArticuloEtiqueta(@PathVariable String articuloetiquetapk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(articuloetiquetapk == null) {
            model.addAttribute("errorMessage", "Error: Artículo Etiqueta inválido");         
            return "/error/error";                
        }
        
        ArticuloEtiquetaService articuloetiquetaService = new ArticuloEtiquetaServiceImpl();   
        ArticuloEtiquetaModel articuloetiqueta = articuloetiquetaService.getByPk(Integer.valueOf(articuloetiquetapk));
        if(articuloetiqueta == null) {
            model.addAttribute("errorMessage", "Error: Artículo Etiqueta inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        ArticuloEtiquetaForm articuloetiquetaForm = new ArticuloEtiquetaForm();
        articuloetiquetaForm.setPk(articuloetiqueta.getId().toString());
        if(articuloetiqueta.getFechaAlta() != null) {
            articuloetiquetaForm.setFechaAlta(articuloetiqueta.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        
        
        articuloetiquetaForm.setAction("edit");
        model.addAttribute("articuloEtiquetaForm", articuloetiquetaForm);  
        model.addAttribute("titleArticuloEtiqueta", "Editar Etiqueta Artículo");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("articuloEtiquetaName", articuloetiqueta.getId());        
        
        List<ArticuloEtiquetaModel> articulosetiqueta = articuloetiquetaService.getAll();
        
        List<ArticuloEtiquetaDto> articulosetiquetaDtos = new ArrayList<ArticuloEtiquetaDto>();
        for(ArticuloEtiquetaModel p: articulosetiqueta) {
            ArticuloEtiquetaDto articuloEtiquetaDto = new ArticuloEtiquetaDto();
            articuloEtiquetaDto.setPk(p.getId().toString());
            
            articulosetiquetaDtos.add(articuloEtiquetaDto);
        }
        
               
        model.addAttribute("articulosEtiqueta", articulosetiquetaDtos);
                
        return "/articuloetiqueta/articuloetiqueta";        
    }
    
    @RequestMapping(value = "/articulo/articuloetiqueta/remove/{articuloetiquetapk}", method = RequestMethod.GET)
    public String removeArticuloEtiqueta(@PathVariable String articuloetiquetapk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(articuloetiquetapk == null) {
            model.addAttribute("errorMessage", "Error: Artículo Etiqueta inválido");         
            return "/error/error";                
        }
        
        ArticuloEtiquetaService articuloEtiquetaService = new ArticuloEtiquetaServiceImpl();   
        ArticuloEtiquetaModel articuloetiqueta = articuloEtiquetaService.getByPk(Integer.valueOf(articuloetiquetapk));
        if(articuloetiqueta == null) {
            model.addAttribute("errorMessage", "Error: Artículo Etiqueta inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        ArticuloEtiquetaForm articuloetiquetaForm = new ArticuloEtiquetaForm();
        articuloetiquetaForm.setPk(articuloetiqueta.getId().toString());        
        if(articuloetiqueta.getFechaAlta() != null) {
            articuloetiquetaForm.setFechaAlta(articuloetiqueta.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        articuloetiquetaForm.setLinea1(articuloetiqueta.getLinea1());
        articuloetiquetaForm.setLinea2(articuloetiqueta.getLinea2());
        
        if(articuloetiqueta.getLinea3() != null) {
            articuloetiquetaForm.setLinea3(articuloetiqueta.getLinea3());
        }

        if(articuloetiqueta.getLinea4() != null) {
            articuloetiquetaForm.setLinea4(articuloetiqueta.getLinea4());
        }

        if(articuloetiqueta.getLinea5() != null) {
            articuloetiquetaForm.setLinea5(articuloetiqueta.getLinea5());
        }
        
        String articulopk = articuloetiqueta.getIdArticulo().toString();
        
        ArticuloService articuloService = new ArticuloServiceImpl();   
        ArticuloModel articulo = articuloService.getByPk(Integer.valueOf(articulopk));
        if(articulo == null) {
            model.addAttribute("errorMessage", "Error: Proveedor inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        String externalName = articulo.getId() + " - " + articulo.getDenominacion();
                        
        articuloetiquetaForm.setAction("remove");
        
        model.addAttribute("displayActionButton", "block");
        model.addAttribute("pkExternal", articulopk);
        model.addAttribute("prefixUrl", "articulo");
        model.addAttribute("labelBase", "Articulos");                
        model.addAttribute("externalName", externalName);
        
        model.addAttribute("articuloEtiquetaForm", articuloetiquetaForm);  
        model.addAttribute("titleArticuloEtiqueta", "Eliminar Etiqueta Artículo");
        model.addAttribute("buttonLabel", "Eliminar");
        model.addAttribute("articuloName", articuloetiqueta.getId());
        
        List<ArticuloEtiquetaModel> articulosetiqueta = articuloEtiquetaService.getAllByArticulo(articulo.getId());
        
        List<ArticuloEtiquetaDto> articulosetiquetaDtos = new ArrayList<ArticuloEtiquetaDto>();
        for(ArticuloEtiquetaModel p: articulosetiqueta) {
            ArticuloEtiquetaDto articuloetiquetaDto = new ArticuloEtiquetaDto();
            articuloetiquetaDto.setPk(p.getId().toString());
            articuloetiquetaDto.setFechaAlta(p.getFechaAlta().toString());
            
            articulosetiquetaDtos.add(articuloetiquetaDto);
        }

               
        model.addAttribute("articulosetiqueta", articulosetiquetaDtos);
                
        return "/articuloetiqueta/articuloetiqueta";        
    }    
    
    @RequestMapping(value = "/articulo/articuloetiqueta/view/{articuloetiquetapk}", method = RequestMethod.GET)
    public String viewArticuloEtiqueta(@PathVariable String articuloetiquetapk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(articuloetiquetapk == null) {
            model.addAttribute("errorMessage", "Error: Artículo Etiqueta inválido");         
            return "/error/error";                
        }
        
        ArticuloEtiquetaService articuloEtiquetaService = new ArticuloEtiquetaServiceImpl();   
        ArticuloEtiquetaModel articuloetiqueta = articuloEtiquetaService.getByPk(Integer.valueOf(articuloetiquetapk));
        if(articuloetiqueta == null) {
            model.addAttribute("errorMessage", "Error: Artículo Etiqueta inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        ArticuloEtiquetaForm articuloetiquetaForm = new ArticuloEtiquetaForm();
        articuloetiquetaForm.setPk(articuloetiqueta.getId().toString());        
        if(articuloetiqueta.getFechaAlta() != null) {
            articuloetiquetaForm.setFechaAlta(articuloetiqueta.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        articuloetiquetaForm.setLinea1(articuloetiqueta.getLinea1());
        articuloetiquetaForm.setLinea2(articuloetiqueta.getLinea2());
        
        if(articuloetiqueta.getLinea3() != null) {
            articuloetiquetaForm.setLinea3(articuloetiqueta.getLinea3());
        }

        if(articuloetiqueta.getLinea4() != null) {
            articuloetiquetaForm.setLinea4(articuloetiqueta.getLinea4());
        }

        if(articuloetiqueta.getLinea5() != null) {
            articuloetiquetaForm.setLinea5(articuloetiqueta.getLinea5());
        }
        
        String articulopk = articuloetiqueta.getIdArticulo().toString();
        
        ArticuloService articuloService = new ArticuloServiceImpl();   
        ArticuloModel articulo = articuloService.getByPk(Integer.valueOf(articulopk));
        if(articulo == null) {
            model.addAttribute("errorMessage", "Error: Proveedor inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        String externalName = articulo.getId() + " - " + articulo.getDenominacion();
                        
        articuloetiquetaForm.setAction("view");
        
        model.addAttribute("displayActionButton", "none");
        model.addAttribute("pkExternal", articulopk);
        model.addAttribute("prefixUrl", "articulo");
        model.addAttribute("labelBase", "Articulos");                
        model.addAttribute("externalName", externalName);
        
        model.addAttribute("articuloEtiquetaForm", articuloetiquetaForm);  
        model.addAttribute("titleArticuloEtiqueta", "Eliminar Etiqueta Artículo");
        model.addAttribute("buttonLabel", "Eliminar");
        model.addAttribute("articuloName", articuloetiqueta.getId());
        
        List<ArticuloEtiquetaModel> articulosetiqueta = articuloEtiquetaService.getAllByArticulo(articulo.getId());
        
        List<ArticuloEtiquetaDto> articulosetiquetaDtos = new ArrayList<ArticuloEtiquetaDto>();
        for(ArticuloEtiquetaModel p: articulosetiqueta) {
            ArticuloEtiquetaDto articuloetiquetaDto = new ArticuloEtiquetaDto();
            articuloetiquetaDto.setPk(p.getId().toString());
            articuloetiquetaDto.setFechaAlta(p.getFechaAlta().toString());
            
            articulosetiquetaDtos.add(articuloetiquetaDto);
        }

               
        model.addAttribute("articulosetiqueta", articulosetiquetaDtos);
                
        return "/articuloetiqueta/articuloetiqueta";        
    }        
    
    @RequestMapping(value = "/articulo/articuloetiqueta/print/{articuloetiquetapk}", method = RequestMethod.GET)
    public String printOrdenDeCompraItemRecepcion(@PathVariable String articuloetiquetapk, HttpServletRequest req, ModelMap model) throws Exception {
        
        String articulo = "Sin infirmación";
        String linea1 = "Sin infirmación";
        String linea2 = "Sin infirmación";
        String linea3 = "Sin infirmación";
        String linea4 = "Sin infirmación";
        String linea5 = "Sin infirmación";
        
        if(articuloetiquetapk != null && !articuloetiquetapk.isEmpty()) {
            
            ArticuloEtiquetaService articuloEtiquetaService = new ArticuloEtiquetaServiceImpl();        
            ArticuloEtiquetaModel etiqueta = articuloEtiquetaService.getByPk(Integer.valueOf(articuloetiquetapk));

            if(etiqueta != null) {
                if(etiqueta.getIdArticulo() != null) {
                    ArticuloService articuloService = new ArticuloServiceImpl();        
                    ArticuloModel articuloModel = articuloService.getByPk(Integer.valueOf(etiqueta.getIdArticulo()));
                    if(articuloModel != null) {
                        articulo = "Artículo: " + articuloModel.getDenominacion() + " Cod: " + articuloModel.getId();
                        if(etiqueta.getLinea1() != null) {
                            linea1 = etiqueta.getLinea1();
                        }
                        if(etiqueta.getLinea2() != null) {
                            linea2 = etiqueta.getLinea2();
                        }
                        if(etiqueta.getLinea3() != null) {
                            linea3 = etiqueta.getLinea3();
                        }
                        if(etiqueta.getLinea4() != null) {
                            linea4 = etiqueta.getLinea4();
                        }
                        if(etiqueta.getLinea5() != null) {
                            linea5 = etiqueta.getLinea5();
                        }                        
                    }
                }
            } 
        } 
        
        model.addAttribute("articulo", articulo);
        model.addAttribute("linea1", linea1);
        model.addAttribute("linea2", linea2);
        model.addAttribute("linea3", linea3);
        model.addAttribute("linea4", linea4);
        model.addAttribute("linea5", linea5);
        
        return "/articuloetiqueta/articuloetiquetaprint";        
    }        
}
