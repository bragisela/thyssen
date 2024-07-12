/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

 
import com.empresa.thyssenplastic.controller.form.InsumoForm;
import com.empresa.thyssenplastic.controller.form.UserForm;
import com.empresa.thyssenplastic.dto.InsumoDto;
import com.empresa.thyssenplastic.model.InsumoModel;
import com.empresa.thyssenplastic.model.ProveedorModel;
import com.empresa.thyssenplastic.service.InsumoService;
import com.empresa.thyssenplastic.service.ProveedorService;
import com.empresa.thyssenplastic.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.empresa.thyssenplastic.service.impl.InsumoServiceImpl;
import com.empresa.thyssenplastic.service.impl.ProveedorServiceImpl;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author gusta
 */
@Controller
public class InsumoController {
    

    @RequestMapping(value = "/insumo", method = RequestMethod.GET)
    public String getHomeInsumo(HttpServletRequest req, ModelMap model) {

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        InsumoForm insumoForm = new InsumoForm();
        insumoForm.setPk("-1");
        insumoForm.setAction("add");
        model.addAttribute("insumoForm", insumoForm);  
        model.addAttribute("titleInsumo", "Nuevo Insumo");  
        model.addAttribute("buttonLabel", "Guardar");
        
        InsumoService insumoService = new InsumoServiceImpl();   
        List<InsumoModel> insumos = insumoService.getAll();

        ProveedorService proveedorService = new ProveedorServiceImpl();   
        
        List<InsumoDto> insumosDtos = new ArrayList<InsumoDto>();
        for(InsumoModel insumo: insumos) {
            InsumoDto insumoDto = new InsumoDto();
            insumoDto.setPk(insumo.getId().toString());
            insumoDto.setDescripcion(insumo.getDescripcion());            
            if(insumo.getUnidad()!= null) {
                insumoDto.setUnidad(insumo.getUnidad());
            }
            if(insumo.getStock()!= null) {
                insumoDto.setStock(insumo.getStock().toString());
            }            
            if(insumo.getIdProveedor() != null) {
                ProveedorModel proveedorModel = proveedorService.getByPk(insumo.getIdProveedor());
                if(proveedorModel != null) {
                    insumoDto.setProveedor(proveedorModel.getRazonSocial());
                }
            }
            insumosDtos.add(insumoDto);
        }
                       
           
        Map<String,String> proveedores = new LinkedHashMap<String,String>();
        List<ProveedorModel> proveedoresModel = proveedorService.getAll();
        if(proveedoresModel != null && !proveedoresModel.isEmpty()){
            for(ProveedorModel proveedorModel :proveedoresModel) {
                proveedores.put(proveedorModel.getId().toString(), proveedorModel.getRazonSocial());
            }
        }
        model.addAttribute("proveedorList", proveedores);        
        model.addAttribute("insumos", insumosDtos);        
                
        return "/insumo/insumo";
    }
    
    @RequestMapping(value = "/insumo/addOrEditOrRemove", method = RequestMethod.POST)
    public ModelAndView addOrEditOrRemoveInsumo(@ModelAttribute("insumoForm")InsumoForm insumoForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {
        
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
        
        if(insumoForm.getAction() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;            
        }
        
        InsumoService insumoService = new InsumoServiceImpl();        
        
        String descripcion = insumoForm.getDescripcion();
        String sessionId = req.getSession().getId();
        String id = insumoForm.getPk();
            
        InsumoModel insumoModel = null;
        if(id.equalsIgnoreCase("-1")) {
            insumoModel = new InsumoModel();
            insumoModel.setStock(0);
            insumoModel.setStockPeso(0.0);
        } else {
            insumoModel = insumoService.getByPk(Integer.valueOf(id));
            if(insumoModel == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: id de insumo inválido.");
                return modelAndView;                
            } 
        }                
        insumoModel.setDescripcion(descripcion);
        if(insumoForm.getIdProveedor() != null && !insumoForm.getIdProveedor().equals("-1")) {
            insumoModel.setIdProveedor(Integer.valueOf(insumoForm.getIdProveedor()));
        } else {
            insumoModel.setIdProveedor(null);
        }
        if(insumoForm.getFechaAlta() != null && !insumoForm.getFechaAlta().trim().equals("")) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
            Date fecha = formato.parse(insumoForm.getFechaAlta());
            insumoModel.setFechaAlta(fecha);
        } else {
            insumoModel.setFechaAlta(null);
        }        
        if(insumoForm.getUnidad() != null) {
            insumoModel.setUnidad(insumoForm.getUnidad());
        } else {
            insumoModel.setUnidad(null);
        }
        
        if(insumoForm.getAction().equalsIgnoreCase("add") || insumoForm.getAction().equalsIgnoreCase("edit")) {
            insumoService.save(insumoModel);
        } else {
            if(insumoForm.getAction().equalsIgnoreCase("remove")) {
                if(insumoModel.getId() == null) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: id de insumo inválido.");
                    return modelAndView;                                    
                }                                      
                insumoService.delete(insumoModel);
            } else {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: operación inválida.");
                return modelAndView;                                
            }
        }
                        
        modelAndView.setViewName("redirect:/insumo");

        return modelAndView; 
    }

    @RequestMapping(value = "/insumo/edit/{insumopk}", method = RequestMethod.GET)
    public String editInsumo(@PathVariable String insumopk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(insumopk == null) {
            model.addAttribute("errorMessage", "Error: Insumo inválido");         
            return "/error/error";                
        }
        
        InsumoService insumoService = new InsumoServiceImpl();   
        InsumoModel insumo = insumoService.getByPk(Integer.valueOf(insumopk));
        if(insumo == null) {
            model.addAttribute("errorMessage", "Error: Insumo inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        InsumoForm insumoForm = new InsumoForm();
        insumoForm.setPk(insumo.getId().toString());
        insumoForm.setDescripcion(insumo.getDescripcion());
        if(insumo.getFechaAlta() != null) {
            insumoForm.setFechaAlta(insumo.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(insumo.getIdProveedor() != null) {
            insumoForm.setIdProveedor(insumo.getIdProveedor().toString());
        }
        if(insumo.getUnidad() != null) {
            insumoForm.setUnidad(insumo.getUnidad());
        }
        
        insumoForm.setAction("edit");
        model.addAttribute("insumoForm", insumoForm);  
        model.addAttribute("titleInsumo", "Editar Insumo");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("insumoName", insumo.getId() + " - " + insumo.getDescripcion());        
        
        ProveedorService proveedorService = new ProveedorServiceImpl();   
        
        List<InsumoModel> insumos = insumoService.getAll();
        
        List<InsumoDto> insumosDtos = new ArrayList<InsumoDto>();
        for(InsumoModel p: insumos) {
            InsumoDto insumoDto = new InsumoDto();
            insumoDto.setPk(p.getId().toString());
            insumoDto.setDescripcion(p.getDescripcion());
            if(p.getStock()!= null) {
                insumoDto.setStock(p.getStock().toString());
            }                                    
            if(p.getUnidad() != null) {
                insumoDto.setUnidad(p.getUnidad());
            }
            if(insumo.getIdProveedor() != null) {
                ProveedorModel proveedorModel = proveedorService.getByPk(insumo.getIdProveedor());
                if(proveedorModel != null) {
                    insumoDto.setProveedor(proveedorModel.getRazonSocial());
                }
            }
            
            insumosDtos.add(insumoDto);
        }
                
        Map<String,String> proveedores = new LinkedHashMap<String,String>();
        List<ProveedorModel> proveedoresModel = proveedorService.getAll();
        if(proveedoresModel != null && !proveedoresModel.isEmpty()){
            for(ProveedorModel proveedorModel :proveedoresModel) {
                proveedores.put(proveedorModel.getId().toString(), proveedorModel.getRazonSocial());
            }
        }
        model.addAttribute("proveedorList", proveedores);        
        model.addAttribute("insumos", insumosDtos);
                
        return "/insumo/insumo";        
    }
    
    @RequestMapping(value = "/insumo/remove/{insumopk}", method = RequestMethod.GET)
    public String removeInsumo(@PathVariable String insumopk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(insumopk == null) {
            model.addAttribute("errorMessage", "Error: Insumo inválido");         
            return "/error/error";                
        }
        
        InsumoService insumoService = new InsumoServiceImpl();   
        InsumoModel insumo = insumoService.getByPk(Integer.valueOf(insumopk));
        if(insumo == null) {
            model.addAttribute("errorMessage", "Error: Insumo inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        InsumoForm insumoForm = new InsumoForm();
        insumoForm.setPk(insumo.getId().toString());        
        insumoForm.setDescripcion(insumo.getDescripcion());
        if(insumo.getFechaAlta() != null) {
            insumoForm.setFechaAlta(insumo.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(insumo.getIdProveedor() != null) {
            insumoForm.setIdProveedor(insumo.getIdProveedor().toString());
        }
        if(insumo.getUnidad() != null) {
            insumoForm.setUnidad(insumo.getUnidad());
        }        
        insumoForm.setAction("remove");
        model.addAttribute("insumoForm", insumoForm);  
        model.addAttribute("titleInsumo", "Eliminar Insumo");
        model.addAttribute("buttonLabel", "Eliminar");
        model.addAttribute("insumoName", insumo.getId() + " - " + insumo.getDescripcion());
        
        ProveedorService proveedorService = new ProveedorServiceImpl();   
        List<InsumoModel> insumos = insumoService.getAll();
        
        List<InsumoDto> insumosDtos = new ArrayList<InsumoDto>();
        for(InsumoModel p: insumos) {
            InsumoDto insumoDto = new InsumoDto();
            insumoDto.setPk(p.getId().toString());
            insumoDto.setDescripcion(p.getDescripcion());
            if(p.getStock()!= null) {
                insumoDto.setStock(p.getStock().toString());
            }                        
            if(p.getUnidad() != null) {
                insumoDto.setUnidad(p.getUnidad());
            }
            if(insumo.getIdProveedor() != null) {
                ProveedorModel proveedorModel = proveedorService.getByPk(insumo.getIdProveedor());
                if(proveedorModel != null) {
                    insumoDto.setProveedor(proveedorModel.getRazonSocial());
                }
            }            
            insumosDtos.add(insumoDto);
        }
        
        Map<String,String> proveedores = new LinkedHashMap<String,String>();
        List<ProveedorModel> proveedoresModel = proveedorService.getAll();
        if(proveedoresModel != null && !proveedoresModel.isEmpty()){
            for(ProveedorModel proveedorModel :proveedoresModel) {
                proveedores.put(proveedorModel.getId().toString(), proveedorModel.getRazonSocial());
            }
        }
        model.addAttribute("proveedorList", proveedores);        
        model.addAttribute("insumos", insumosDtos);
                
        return "/insumo/insumo";        
    }    
}
