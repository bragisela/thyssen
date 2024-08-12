/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

 
import com.empresa.thyssenplastic.controller.form.MateriaPrimaForm;
import com.empresa.thyssenplastic.controller.form.UserForm;
import com.empresa.thyssenplastic.dto.MateriaPrimaDto;
import com.empresa.thyssenplastic.model.MateriaPrimaModel;
import com.empresa.thyssenplastic.model.ProveedorModel;
import com.empresa.thyssenplastic.model.TipoModel;
import com.empresa.thyssenplastic.service.MateriaPrimaService;
import com.empresa.thyssenplastic.service.ProveedorService;
import com.empresa.thyssenplastic.service.TipoService;
import com.empresa.thyssenplastic.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.empresa.thyssenplastic.service.impl.MateriaPrimaServiceImpl;
import com.empresa.thyssenplastic.service.impl.ProveedorServiceImpl;
import com.empresa.thyssenplastic.service.impl.TipoServiceImpl;
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
public class MateriaPrimaController {
    

    @RequestMapping(value = "/materiaprima", method = RequestMethod.GET)
    public String getHomeMateriaPrima(HttpServletRequest req, ModelMap model) {

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        MateriaPrimaForm materiaprimaForm = new MateriaPrimaForm();
        materiaprimaForm.setPk("-1");
        materiaprimaForm.setAction("add");
        model.addAttribute("materiaprimaForm", materiaprimaForm);  
        model.addAttribute("titleMateriaPrima", "Nuevo MateriaPrima");  
        model.addAttribute("buttonLabel", "Guardar");
        
        ProveedorService proveedorService = new ProveedorServiceImpl();   
        TipoService tipoService = new TipoServiceImpl();   
        MateriaPrimaService materiaprimaService = new MateriaPrimaServiceImpl();   
        List<MateriaPrimaModel> materiasprima = materiaprimaService.getAll();
        
        List<MateriaPrimaDto> materiasprimaDtos = new ArrayList<MateriaPrimaDto>();
        for(MateriaPrimaModel materiaprima: materiasprima) {
            MateriaPrimaDto materiaprimaDto = new MateriaPrimaDto();
            materiaprimaDto.setPk(materiaprima.getId().toString());
            materiaprimaDto.setDescripcion(materiaprima.getDescripcion());            

            materiaprimaDto.setIndiceDeFluidez(materiaprima.getIndiceDeFluidez());
            materiaprimaDto.setDensidad(materiaprima.getDensidad());

            
            if(materiaprima.getIdProveedor() != null){
                ProveedorModel proveedorModel = proveedorService.getByPk(Integer.valueOf(materiaprima.getIdProveedor()));
                if(proveedorModel != null) {
                    materiaprimaDto.setIdProveedor(proveedorModel.getRazonSocial());
                }
            }
            if(materiaprima.getIdTipo() != null){
                TipoModel tipoModel = tipoService.getByPk(Integer.valueOf(materiaprima.getIdTipo()));
                if(tipoModel != null) {
                    materiaprimaDto.setIdTipo(tipoModel.getValor());
                }
            }
            
            
            if(materiaprima.getIdDenominacion() != null){
                TipoModel tipoModel = tipoService.getByPk(Integer.valueOf(materiaprima.getIdDenominacion()));
                if(tipoModel != null) {
                    materiaprimaDto.setIdDenominacion(tipoModel.getValor());
                }
            }
            if(materiaprima.getIdPetroquimica() != null){
                TipoModel tipoModel = tipoService.getByPk(Integer.valueOf(materiaprima.getIdPetroquimica()));
                if(tipoModel != null) {
                    materiaprimaDto.setIdPetroquimica(tipoModel.getValor());
                }
            }
            if(materiaprima.getStock() != null) {
                materiaprimaDto.setStock(materiaprima.getStock().toString());
            } 
            
            materiasprimaDtos.add(materiaprimaDto);
        }
                       
        
        Map<String,String> proveedores = new LinkedHashMap<String,String>();
        List<ProveedorModel> proveedoresModel = proveedorService.getAll();
        if(proveedoresModel != null && !proveedoresModel.isEmpty()){
            for(ProveedorModel proveedorModel :proveedoresModel) {
                proveedores.put(proveedorModel.getId().toString(), proveedorModel.getRazonSocial());
            }
        }        
        Map<String,String> tipos = new LinkedHashMap<String,String>();
        List<TipoModel> tiposModel = tipoService.getByType("tipomateriaprima");
        if(tiposModel != null && !tiposModel.isEmpty()){
            for(TipoModel tipoModel :tiposModel) {
                tipos.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        Map<String,String> denominaciones = new LinkedHashMap<String,String>();
        List<TipoModel> denominacionesModel = tipoService.getByType("denominacion");
        if(denominacionesModel != null && !denominacionesModel.isEmpty()){
            for(TipoModel tipoModel :denominacionesModel) {
                denominaciones.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        Map<String,String> petroquimicas = new LinkedHashMap<String,String>();
        List<TipoModel> petroquimicasModel = tipoService.getByType("petroquimica");
        if(petroquimicasModel != null && !petroquimicasModel.isEmpty()){
            for(TipoModel tipoModel :petroquimicasModel) {
                petroquimicas.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        
        model.addAttribute("petroquimicaList", petroquimicas); 
        model.addAttribute("denominacionList", denominaciones); 
        model.addAttribute("tipoList", tipos); 
        model.addAttribute("proveedorList", proveedores);        
        model.addAttribute("materiasprima", materiasprimaDtos);        
                
        return "/materiaprima/materiaprima";
    }
    
    @RequestMapping(value = "/materiaprima/addOrEditOrRemove", method = RequestMethod.POST)
    public ModelAndView addOrEditOrRemoveMateriaPrima(@ModelAttribute("materiaprimaForm")MateriaPrimaForm materiaprimaForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {
        
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
        
        if(materiaprimaForm.getAction() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;            
        }
        
        MateriaPrimaService materiaprimaService = new MateriaPrimaServiceImpl();        
        
        String descripcion = materiaprimaForm.getDescripcion();
        String sessionId = req.getSession().getId();
        String id = materiaprimaForm.getPk();
            
        MateriaPrimaModel materiaprimaModel = null;
        if(id.equalsIgnoreCase("-1")) {
            materiaprimaModel = new MateriaPrimaModel();
            materiaprimaModel.setStock(0);
            materiaprimaModel.setStockPeso(0.0);
        } else {
            materiaprimaModel = materiaprimaService.getByPk(Integer.valueOf(id));
            if(materiaprimaModel == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: id de materiaprima inválido.");
                return modelAndView;                
            } 
        }                
        materiaprimaModel.setDescripcion(descripcion);
        if(materiaprimaForm.getUrlFichaTecnica() != null) {
            materiaprimaModel.setUrlFichaTecnica(materiaprimaForm.getUrlFichaTecnica());
        } else {
            materiaprimaModel.setUrlFichaTecnica(null);
        }
        if(materiaprimaForm.getIdProveedor() != null && !materiaprimaForm.getIdProveedor().equals("-1")) {
            materiaprimaModel.setIdProveedor(Integer.valueOf(materiaprimaForm.getIdProveedor()));
        } else {
            materiaprimaModel.setIdProveedor(null);
        }
        if(materiaprimaForm.getIdTipo() != null && !materiaprimaForm.getIdTipo().equals("-1")) {
            materiaprimaModel.setIdTipo(Integer.valueOf(materiaprimaForm.getIdTipo()));
        } else {
            materiaprimaModel.setIdTipo(null);
        }
        if(materiaprimaForm.getIdDenominacion() != null && !materiaprimaForm.getIdDenominacion().equals("-1")) {
            materiaprimaModel.setIdDenominacion(Integer.valueOf(materiaprimaForm.getIdDenominacion()));
        } else {
            materiaprimaModel.setIdDenominacion(null);
        }        
        if(materiaprimaForm.getIdPetroquimica() != null && !materiaprimaForm.getIdPetroquimica().equals("-1")) {
            materiaprimaModel.setIdPetroquimica(Integer.valueOf(materiaprimaForm.getIdPetroquimica()));
        } else {
            materiaprimaModel.setIdPetroquimica(null);
        }                
        if(materiaprimaForm.getFechaAlta() != null && !materiaprimaForm.getFechaAlta().trim().equals("")) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
            Date fecha = formato.parse(materiaprimaForm.getFechaAlta());
            materiaprimaModel.setFechaAlta(fecha);
        } else {
            materiaprimaModel.setFechaAlta(null);
        }        
        if(materiaprimaForm.getObservaciones() != null) {
            materiaprimaModel.setObservaciones(materiaprimaForm.getObservaciones());
        } else {
            materiaprimaModel.setObservaciones(null);
        }
        if(materiaprimaForm.getDensidad() != null ) {
            materiaprimaModel.setDensidad(materiaprimaForm.getDensidad());
        }
        if(materiaprimaForm.getIndiceDeFluidez() != null ) {
            materiaprimaModel.setIndiceDeFluidez(materiaprimaForm.getIndiceDeFluidez());
        }

        if(materiaprimaForm.getAction().equalsIgnoreCase("add") || materiaprimaForm.getAction().equalsIgnoreCase("edit")) {
            materiaprimaService.save(materiaprimaModel);
        } else {
            if(materiaprimaForm.getAction().equalsIgnoreCase("remove")) {
                if(materiaprimaModel.getId() == null) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: id de materia prima inválida.");
                    return modelAndView;                                    
                }                                      
                materiaprimaService.delete(materiaprimaModel);
            } else {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: operación inválida.");
                return modelAndView;                                
            }
        }
                        
        modelAndView.setViewName("redirect:/materiaprima");

        return modelAndView; 
    }

    @RequestMapping(value = "/materiaprima/edit/{materiaprimapk}", method = RequestMethod.GET)
    public String editMateriaPrima(@PathVariable String materiaprimapk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(materiaprimapk == null) {
            model.addAttribute("errorMessage", "Error: Materia Prima inválida");         
            return "/error/error";                
        }
        
        TipoService tipoService = new TipoServiceImpl();  
        ProveedorService proveedorService = new ProveedorServiceImpl(); 
        MateriaPrimaService materiaprimaService = new MateriaPrimaServiceImpl();   
        MateriaPrimaModel materiaprima = materiaprimaService.getByPk(Integer.valueOf(materiaprimapk));
        if(materiaprima == null) {
            model.addAttribute("errorMessage", "Error: Materia Prima inválida. No ha sido encontrada.");         
            return "/error/error";    
        }
        
        MateriaPrimaForm materiaprimaForm = new MateriaPrimaForm();
        materiaprimaForm.setPk(materiaprima.getId().toString());
        materiaprimaForm.setDescripcion(materiaprima.getDescripcion());
        if(materiaprima.getUrlFichaTecnica() != null) {
            materiaprimaForm.setUrlFichaTecnica(materiaprima.getUrlFichaTecnica());
        }
        if(materiaprima.getFechaAlta() != null) {
            materiaprimaForm.setFechaAlta(materiaprima.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(materiaprima.getIdProveedor() != null) {
            materiaprimaForm.setIdProveedor(materiaprima.getIdProveedor().toString());
        }
        if(materiaprima.getIdTipo() != null) {
            materiaprimaForm.setIdTipo(materiaprima.getIdTipo().toString());
        }
        if(materiaprima.getIdDenominacion() != null) {
            materiaprimaForm.setIdDenominacion(materiaprima.getIdDenominacion().toString());
        }
        if(materiaprima.getIdPetroquimica() != null) {
            materiaprimaForm.setIdPetroquimica(materiaprima.getIdPetroquimica().toString());
        }        
        if(materiaprima.getDensidad() != null) {
            materiaprimaForm.setDensidad(materiaprima.getDensidad());
        }        
        if(materiaprima.getIndiceDeFluidez() != null) {
            materiaprimaForm.setIndiceDeFluidez(materiaprima.getIndiceDeFluidez());
        }        
        if(materiaprima.getObservaciones() != null) {
            materiaprimaForm.setObservaciones(materiaprima.getObservaciones());
        }
        
        materiaprimaForm.setAction("edit");
        model.addAttribute("materiaprimaForm", materiaprimaForm);  
        model.addAttribute("titleMateriaPrima", "Editar MateriaPrima");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("materiaprimaName", materiaprima.getId() + " - " + materiaprima.getDescripcion());        
        
        List<MateriaPrimaModel> materiasprima = materiaprimaService.getAll();
        
        List<MateriaPrimaDto> materiasprimaDtos = new ArrayList<MateriaPrimaDto>();
        for(MateriaPrimaModel p: materiasprima) {
            MateriaPrimaDto materiaprimaDto = new MateriaPrimaDto();
            materiaprimaDto.setPk(p.getId().toString());
            materiaprimaDto.setDescripcion(p.getDescripcion());
            if(materiaprima.getIdProveedor() != null){
                ProveedorModel proveedorModel = proveedorService.getByPk(Integer.valueOf(materiaprima.getIdProveedor()));
                if(proveedorModel != null) {
                    materiaprimaDto.setIdProveedor(proveedorModel.getRazonSocial());
                }
            }
            if(materiaprima.getIdTipo() != null){
                TipoModel tipoModel = tipoService.getByPk(Integer.valueOf(materiaprima.getIdTipo()));
                if(tipoModel != null) {
                    materiaprimaDto.setIdTipo(tipoModel.getValor());
                }
            }
            if(materiaprima.getIdDenominacion() != null){
                TipoModel tipoModel = tipoService.getByPk(Integer.valueOf(materiaprima.getIdDenominacion()));
                if(tipoModel != null) {
                    materiaprimaDto.setIdDenominacion(tipoModel.getValor());
                }
            }
            if(materiaprima.getIdPetroquimica() != null){
                TipoModel tipoModel = tipoService.getByPk(Integer.valueOf(materiaprima.getIdPetroquimica()));
                if(tipoModel != null) {
                    materiaprimaDto.setIdPetroquimica(tipoModel.getValor());
                }
            }
            if(materiaprima.getStock() != null) {
                materiaprimaDto.setStock(materiaprima.getStock().toString());
            } 
            if(materiaprima.getDensidad() != null) {
                materiaprimaDto.setDensidad(materiaprima.getDensidad());
            } 
            if(materiaprima.getIndiceDeFluidez() != null) {
                materiaprimaDto.setIndiceDeFluidez(materiaprima.getIndiceDeFluidez());
            } 
            
            materiasprimaDtos.add(materiaprimaDto);
        }
                  
        Map<String,String> proveedores = new LinkedHashMap<String,String>();
        List<ProveedorModel> proveedoresModel = proveedorService.getAll();
        if(proveedoresModel != null && !proveedoresModel.isEmpty()){
            for(ProveedorModel proveedorModel :proveedoresModel) {
                proveedores.put(proveedorModel.getId().toString(), proveedorModel.getRazonSocial());
            }
        }        
        Map<String,String> tipos = new LinkedHashMap<String,String>();
        List<TipoModel> tiposModel = tipoService.getByType("tipomateriaprima");
        if(tiposModel != null && !tiposModel.isEmpty()){
            for(TipoModel tipoModel :tiposModel) {
                tipos.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        Map<String,String> denominaciones = new LinkedHashMap<String,String>();
        List<TipoModel> denominacionesModel = tipoService.getByType("denominacion");
        if(denominacionesModel != null && !denominacionesModel.isEmpty()){
            for(TipoModel tipoModel :denominacionesModel) {
                denominaciones.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        Map<String,String> petroquimicas = new LinkedHashMap<String,String>();
        List<TipoModel> petroquimicasModel = tipoService.getByType("petroquimica");
        if(petroquimicasModel != null && !petroquimicasModel.isEmpty()){
            for(TipoModel tipoModel :petroquimicasModel) {
                petroquimicas.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        
        model.addAttribute("petroquimicaList", petroquimicas); 
        model.addAttribute("denominacionList", denominaciones); 
        model.addAttribute("tipoList", tipos);         
        model.addAttribute("proveedorList", proveedores);        
        model.addAttribute("materiasprima", materiasprimaDtos);
                
        return "/materiaprima/materiaprima";        
    }
    
    @RequestMapping(value = "/materiaprima/remove/{materiaprimapk}", method = RequestMethod.GET)
    public String removeMateriaPrima(@PathVariable String materiaprimapk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(materiaprimapk == null) {
            model.addAttribute("errorMessage", "Error: MateriaPrima inválido");         
            return "/error/error";                
        }
        
        TipoService tipoService = new TipoServiceImpl();  
        ProveedorService proveedorService = new ProveedorServiceImpl(); 
        MateriaPrimaService materiaprimaService = new MateriaPrimaServiceImpl();   
        MateriaPrimaModel materiaprima = materiaprimaService.getByPk(Integer.valueOf(materiaprimapk));
        if(materiaprima == null) {
            model.addAttribute("errorMessage", "Error: MateriaPrima inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        MateriaPrimaForm materiaprimaForm = new MateriaPrimaForm();
        materiaprimaForm.setPk(materiaprima.getId().toString());        
        materiaprimaForm.setDescripcion(materiaprima.getDescripcion());
        if(materiaprima.getUrlFichaTecnica() != null) {
            materiaprimaForm.setUrlFichaTecnica(materiaprima.getUrlFichaTecnica());
        }
        if(materiaprima.getFechaAlta() != null) {
            materiaprimaForm.setFechaAlta(materiaprima.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(materiaprima.getIdProveedor() != null) {
            materiaprimaForm.setIdProveedor(materiaprima.getIdProveedor().toString());
        }
        if(materiaprima.getIdTipo() != null) {
            materiaprimaForm.setIdTipo(materiaprima.getIdTipo().toString());
        }
        if(materiaprima.getIdDenominacion() != null) {
            materiaprimaForm.setIdDenominacion(materiaprima.getIdDenominacion().toString());
        }
        if(materiaprima.getIdPetroquimica() != null) {
            materiaprimaForm.setIdPetroquimica(materiaprima.getIdPetroquimica().toString());
        }        
        if(materiaprima.getObservaciones() != null) {
            materiaprimaForm.setObservaciones(materiaprima.getObservaciones());
        }
        materiaprimaForm.setAction("remove");
        model.addAttribute("materiaprimaForm", materiaprimaForm);  
        model.addAttribute("titleMateriaPrima", "Eliminar MateriaPrima");
        model.addAttribute("buttonLabel", "Eliminar");
        model.addAttribute("materiaprimaName", materiaprima.getId() + " - " + materiaprima.getDescripcion());
        
        List<MateriaPrimaModel> materiasprima = materiaprimaService.getAll();
        
        List<MateriaPrimaDto> materiasprimaDtos = new ArrayList<MateriaPrimaDto>();
        for(MateriaPrimaModel p: materiasprima) {
            MateriaPrimaDto materiaprimaDto = new MateriaPrimaDto();
            materiaprimaDto.setPk(p.getId().toString());
            materiaprimaDto.setDescripcion(p.getDescripcion());
            if(materiaprima.getIdProveedor() != null){
                ProveedorModel proveedorModel = proveedorService.getByPk(Integer.valueOf(materiaprima.getIdProveedor()));
                if(proveedorModel != null) {
                    materiaprimaDto.setIdProveedor(proveedorModel.getRazonSocial());
                }
            }
            if(materiaprima.getIdTipo() != null){
                TipoModel tipoModel = tipoService.getByPk(Integer.valueOf(materiaprima.getIdTipo()));
                if(tipoModel != null) {
                    materiaprimaDto.setIdTipo(tipoModel.getValor());
                }
            }
            if(materiaprima.getIdDenominacion() != null){
                TipoModel tipoModel = tipoService.getByPk(Integer.valueOf(materiaprima.getIdDenominacion()));
                if(tipoModel != null) {
                    materiaprimaDto.setIdDenominacion(tipoModel.getValor());
                }
            }
            if(materiaprima.getIdPetroquimica() != null){
                TipoModel tipoModel = tipoService.getByPk(Integer.valueOf(materiaprima.getIdPetroquimica()));
                if(tipoModel != null) {
                    materiaprimaDto.setIdPetroquimica(tipoModel.getValor());
                }
            }
            if(materiaprima.getStock() != null) {
                materiaprimaDto.setStock(materiaprima.getStock().toString());
            } 

            materiasprimaDtos.add(materiaprimaDto);
        }
        
        Map<String,String> proveedores = new LinkedHashMap<String,String>();
        List<ProveedorModel> proveedoresModel = proveedorService.getAll();
        if(proveedoresModel != null && !proveedoresModel.isEmpty()){
            for(ProveedorModel proveedorModel :proveedoresModel) {
                proveedores.put(proveedorModel.getId().toString(), proveedorModel.getRazonSocial());
            }
        }
        Map<String,String> tipos = new LinkedHashMap<String,String>();
        List<TipoModel> tiposModel = tipoService.getByType("tipomateriaprima");
        if(tiposModel != null && !tiposModel.isEmpty()){
            for(TipoModel tipoModel :tiposModel) {
                tipos.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        Map<String,String> denominaciones = new LinkedHashMap<String,String>();
        List<TipoModel> denominacionesModel = tipoService.getByType("denominacion");
        if(denominacionesModel != null && !denominacionesModel.isEmpty()){
            for(TipoModel tipoModel :denominacionesModel) {
                denominaciones.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        Map<String,String> petroquimicas = new LinkedHashMap<String,String>();
        List<TipoModel> petroquimicasModel = tipoService.getByType("petroquimica");
        if(petroquimicasModel != null && !petroquimicasModel.isEmpty()){
            for(TipoModel tipoModel :petroquimicasModel) {
                petroquimicas.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        
        model.addAttribute("petroquimicaList", petroquimicas); 
        model.addAttribute("denominacionList", denominaciones); 
        model.addAttribute("tipoList", tipos);                
        model.addAttribute("proveedorList", proveedores);        
        model.addAttribute("materiasprima", materiasprimaDtos);
                
        return "/materiaprima/materiaprima";        
    }    
}
