/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

 
import com.empresa.thyssenplastic.controller.form.AjusteDeInventarioForm;
import com.empresa.thyssenplastic.controller.form.UserForm;
import com.empresa.thyssenplastic.dto.AjusteDeInventarioDto;
import com.empresa.thyssenplastic.model.AjusteDeInventarioModel;
import com.empresa.thyssenplastic.model.ArticuloModel;
import com.empresa.thyssenplastic.model.InsumoModel;
import com.empresa.thyssenplastic.model.MateriaPrimaModel;
import com.empresa.thyssenplastic.model.UserModel;
import com.empresa.thyssenplastic.service.AjusteDeInventarioService;
import com.empresa.thyssenplastic.service.ArticuloService;
import com.empresa.thyssenplastic.service.InsumoService;
import com.empresa.thyssenplastic.service.MateriaPrimaService;
import com.empresa.thyssenplastic.service.UserService;
import com.empresa.thyssenplastic.service.impl.AjusteDeInventarioServiceImpl;
import com.empresa.thyssenplastic.service.impl.ArticuloServiceImpl;
import com.empresa.thyssenplastic.service.impl.InsumoServiceImpl;
import com.empresa.thyssenplastic.service.impl.MateriaPrimaServiceImpl;
import com.empresa.thyssenplastic.service.impl.UserServiceImpl;
import com.empresa.thyssenplastic.utils.Utils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author gusta
 */
@Controller
public class AjusteDeInventarioController {
    

    @RequestMapping(value = "/ajusteDeInventario", method = RequestMethod.GET)
    public String getHomeAjusteDeInventario(HttpServletRequest req, ModelMap model) {

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        AjusteDeInventarioForm ajusteDeInventarioForm = new AjusteDeInventarioForm();
        ajusteDeInventarioForm.setPk("-1");
        ajusteDeInventarioForm.setAction("add");
        model.addAttribute("ajusteDeInventarioForm", ajusteDeInventarioForm);  
        model.addAttribute("titleAjusteDeInventario", "Nuevo Ajuste De Inventario");  
        model.addAttribute("buttonLabel", "Guardar");
        
        AjusteDeInventarioService ajusteDeInventarioService = new AjusteDeInventarioServiceImpl();   
        List<AjusteDeInventarioModel> ajustesDeInventario = ajusteDeInventarioService.getAll();

        ArticuloService articuloService = new ArticuloServiceImpl();   
        Map<String,String> articulos = new LinkedHashMap<String,String>();
        List<ArticuloModel> articulosModel = articuloService.getAll();

        MateriaPrimaService materiaPrimaService = new MateriaPrimaServiceImpl();   
        Map<String,String> materiasPrima = new LinkedHashMap<String,String>();
        List<MateriaPrimaModel> materiasPrimaModel = materiaPrimaService.getAll();

        InsumoService insumoService = new InsumoServiceImpl();   
        Map<String,String> insumos = new LinkedHashMap<String,String>();
        List<InsumoModel> insumosModel = insumoService.getAll();
        
        Map<String,String> depositos = new LinkedHashMap<String,String>();
                
        List<AjusteDeInventarioDto> ajustesDeInventarioDtos = new ArrayList<AjusteDeInventarioDto>();
        for(AjusteDeInventarioModel ajusteDeInventario: ajustesDeInventario) {
            AjusteDeInventarioDto ajusteDeInventarioDto = new AjusteDeInventarioDto();
            ajusteDeInventarioDto.setPk(ajusteDeInventario.getId().toString());
            ajusteDeInventarioDto.setFechaAlta(ajusteDeInventario.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            ajusteDeInventarioDto.setTipo(ajusteDeInventario.getTipo());
            if(ajusteDeInventario.getTipo().equalsIgnoreCase("articulos") && ajusteDeInventario.getIdArticulo() != null) {
                String descripcion = "";
                ArticuloModel articuloModel = articuloService.getByPk(ajusteDeInventario.getIdArticulo());
                if(articuloModel != null){
                    descripcion = articuloModel.getDenominacion();
                }                
                ajusteDeInventarioDto.setDescripcion(descripcion);
            }                                    
            if(ajusteDeInventario.getTipo().equalsIgnoreCase("materiaPrima") && ajusteDeInventario.getIdMateriaPrima() != null) {
                String descripcion = "";
                MateriaPrimaModel materiaPrimaModel = materiaPrimaService.getByPk(ajusteDeInventario.getIdMateriaPrima());
                if(materiaPrimaModel != null){
                    descripcion = materiaPrimaModel.getDescripcion();
                }                
                ajusteDeInventarioDto.setDescripcion(descripcion);
            }                                    
            if(ajusteDeInventario.getTipo().equalsIgnoreCase("insumos") && ajusteDeInventario.getIdInsumo() != null) {
                String descripcion = "";
                InsumoModel insumoModel = insumoService.getByPk(ajusteDeInventario.getIdInsumo());
                if(insumoModel != null){
                    descripcion = insumoModel.getDescripcion();
                }                
                ajusteDeInventarioDto.setDescripcion(descripcion);
            }                                    
            ajusteDeInventarioDto.setAjusteCantidad(ajusteDeInventario.getAjusteCantidad().toString());
            ajusteDeInventarioDto.setAjustePeso(ajusteDeInventario.getAjustePeso().toString());
            ajusteDeInventarioDto.setExistenteCantidad(ajusteDeInventario.getExistenteCantidad().toString());
            ajusteDeInventarioDto.setExistentePeso(ajusteDeInventario.getExistentePeso().toString());
            ajusteDeInventarioDto.setResultadoCantidad(ajusteDeInventario.getResultadoCantidad().toString());
            ajusteDeInventarioDto.setResultadoPeso(ajusteDeInventario.getResultadoPeso().toString());
            
            ajustesDeInventarioDtos.add(ajusteDeInventarioDto);
        }
                       
        if(articulosModel != null && !articulosModel.isEmpty()){
            for(ArticuloModel articuloModel :articulosModel) {
                articulos.put(articuloModel.getId().toString(), articuloModel.getDenominacion());
            }
        }
        if(materiasPrimaModel != null && !materiasPrimaModel.isEmpty()){
            for(MateriaPrimaModel materiaPrimaModel :materiasPrimaModel) {
                materiasPrima.put(materiaPrimaModel.getId().toString(), materiaPrimaModel.getDescripcion());
            }
        }
        if(insumosModel != null && !insumosModel.isEmpty()){
            for(InsumoModel insumoModel :insumosModel) {
                insumos.put(insumoModel.getId().toString(), insumoModel.getDescripcion());
            }
        }
                        
        model.addAttribute("displayUser", "none");
        model.addAttribute("displayActionButton", "block");
        model.addAttribute("articuloList", articulos);        
        model.addAttribute("insumoList", insumos);        
        model.addAttribute("materiaPrimaList", materiasPrima);                
        model.addAttribute("depositosList", depositos);        
        model.addAttribute("ajustesDeInventario", ajustesDeInventarioDtos);        
                
        return "/ajustedeinventario/ajustedeinventario";
    }
    
    @RequestMapping(value = "/ajusteDeInventario/addOrEditOrRemove", method = RequestMethod.POST)
    public ModelAndView addOrEditOrRemoveAjusteDeInventario(@ModelAttribute("ajusteDeInventarioForm")AjusteDeInventarioForm ajusteDeInventarioForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {
                
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
        
        if(ajusteDeInventarioForm.getAction() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;            
        }
            
        ArticuloService articuloService = new ArticuloServiceImpl();
        MateriaPrimaService materiaPrimaService = new MateriaPrimaServiceImpl();
        InsumoService insumoService = new InsumoServiceImpl();
        
        ArticuloModel articulo = null;
        MateriaPrimaModel materiaPrima = null;
        InsumoModel insumo = null;
        
        if(ajusteDeInventarioForm.getTipo().equalsIgnoreCase("articulos")){            
            articulo = articuloService.getByPk(Integer.valueOf(ajusteDeInventarioForm.getIdArticulo()));
            if(articulo == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: articulo no encontrado");
                return modelAndView;                            
            }
        }
        if(ajusteDeInventarioForm.getTipo().equalsIgnoreCase("materiaPrima")){            
            materiaPrima = materiaPrimaService.getByPk(Integer.valueOf(ajusteDeInventarioForm.getIdMateriaPrima()));
            if(materiaPrima == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: materia prima no encontrada");
                return modelAndView;                            
            }            
        }            
        if(ajusteDeInventarioForm.getTipo().equalsIgnoreCase("insumos")){            
            insumo = insumoService.getByPk(Integer.valueOf(ajusteDeInventarioForm.getIdInsumo()));
            if(insumo == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: insumo no encontrado");
                return modelAndView;                            
            }                        
        }
        
        if(ajusteDeInventarioForm.getResultadoCantidadHdn() == null || ajusteDeInventarioForm.getResultadoCantidadHdn().isEmpty()) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: resultado cantidad incorrecta");
            return modelAndView;                                        
        }

        if(ajusteDeInventarioForm.getResultadoPesoHdn() == null || ajusteDeInventarioForm.getResultadoPesoHdn().isEmpty()) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: resultado peso incorrecto");
            return modelAndView;                                        
        }

        if(ajusteDeInventarioForm.getExistenteCantidadHdn() == null || ajusteDeInventarioForm.getExistenteCantidadHdn().isEmpty()) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: existente cantidad incorrecta");
            return modelAndView;                                        
        }

        if(ajusteDeInventarioForm.getExistentePesoHdn() == null || ajusteDeInventarioForm.getExistentePesoHdn().isEmpty()) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: existente peso incorrecto");
            return modelAndView;                                        
        }
        
        AjusteDeInventarioService ajusteDeInventarioService = new AjusteDeInventarioServiceImpl();        
        
        String sessionId = req.getSession().getId();
        String id = ajusteDeInventarioForm.getPk();
            
        AjusteDeInventarioModel ajusteDeInventarioModel = null;
        if(id.equalsIgnoreCase("-1")) {
            ajusteDeInventarioModel = new AjusteDeInventarioModel();
        } else {
            ajusteDeInventarioModel = ajusteDeInventarioService.getByPk(Integer.valueOf(id));
            if(ajusteDeInventarioModel == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: id de ajusteDeInventario inválido.");
                return modelAndView;                
            } 
        }        
        ajusteDeInventarioModel.setTipo(ajusteDeInventarioForm.getTipo());
        if(ajusteDeInventarioForm.getFechaAlta() != null && !ajusteDeInventarioForm.getFechaAlta().trim().equals("")) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
            Date fecha = formato.parse(ajusteDeInventarioForm.getFechaAlta());
            ajusteDeInventarioModel.setFechaAlta(fecha);
        } else {
            ajusteDeInventarioModel.setFechaAlta(null);
        }                
        if(ajusteDeInventarioForm.getTipo().equalsIgnoreCase("articulos") && ajusteDeInventarioForm.getIdArticulo() != null && !ajusteDeInventarioForm.getIdArticulo().isEmpty()) {
            ajusteDeInventarioModel.setIdArticulo(Integer.valueOf(ajusteDeInventarioForm.getIdArticulo()));
        } else {
            ajusteDeInventarioModel.setIdArticulo(null);
        }
        if(ajusteDeInventarioForm.getTipo().equalsIgnoreCase("materiaPrima") && ajusteDeInventarioForm.getIdMateriaPrima() != null && !ajusteDeInventarioForm.getIdMateriaPrima().isEmpty()) {
            ajusteDeInventarioModel.setIdMateriaPrima(Integer.valueOf(ajusteDeInventarioForm.getIdMateriaPrima()));
        } else {
            ajusteDeInventarioModel.setIdMateriaPrima(null);
        }
        if(ajusteDeInventarioForm.getTipo().equalsIgnoreCase("insumos") && ajusteDeInventarioForm.getIdInsumo() != null && !ajusteDeInventarioForm.getIdInsumo().isEmpty()) {
            ajusteDeInventarioModel.setIdInsumo(Integer.valueOf(ajusteDeInventarioForm.getIdInsumo()));
        } else {
            ajusteDeInventarioModel.setIdInsumo(null);
        }
        if(ajusteDeInventarioForm.getIdDeposito() != null && !ajusteDeInventarioForm.getIdDeposito().isEmpty()) {
            ajusteDeInventarioModel.setIdDeposito(Integer.valueOf(ajusteDeInventarioForm.getIdDeposito()));
        } else {
            ajusteDeInventarioModel.setIdDeposito(null);
        }        
        if(ajusteDeInventarioForm.getLote() != null && !ajusteDeInventarioForm.getLote().isEmpty()) {
            ajusteDeInventarioModel.setLote(ajusteDeInventarioForm.getLote());
        } else {
            ajusteDeInventarioModel.setLote(null);
        }
        if(ajusteDeInventarioForm.getExistentePesoHdn() != null && !ajusteDeInventarioForm.getExistentePesoHdn().isEmpty()) {
            ajusteDeInventarioModel.setExistentePeso(Double.valueOf(ajusteDeInventarioForm.getExistentePesoHdn()));
        } else {
            ajusteDeInventarioModel.setExistentePeso(null);
        }
        if(ajusteDeInventarioForm.getExistenteCantidadHdn() != null && !ajusteDeInventarioForm.getExistenteCantidadHdn().isEmpty()) {
            ajusteDeInventarioModel.setExistenteCantidad(Double.valueOf(ajusteDeInventarioForm.getExistenteCantidadHdn()));
        } else {
            ajusteDeInventarioModel.setExistenteCantidad(null);
        }
        if(ajusteDeInventarioForm.getAjustePeso() != null && !ajusteDeInventarioForm.getAjustePeso().isEmpty()) {
            ajusteDeInventarioModel.setAjustePeso(Double.valueOf(ajusteDeInventarioForm.getAjustePeso()));
        } else {
            ajusteDeInventarioModel.setAjustePeso(null);
        }
        if(ajusteDeInventarioForm.getAjusteCantidad() != null && !ajusteDeInventarioForm.getAjusteCantidad().isEmpty()) {
            ajusteDeInventarioModel.setAjusteCantidad(Double.valueOf(ajusteDeInventarioForm.getAjusteCantidad()));
        } else {
            ajusteDeInventarioModel.setAjusteCantidad(null);
        }
        if(ajusteDeInventarioForm.getResultadoPesoHdn() != null && !ajusteDeInventarioForm.getResultadoPesoHdn().isEmpty()) {
            ajusteDeInventarioModel.setResultadoPeso(Double.valueOf(ajusteDeInventarioForm.getResultadoPesoHdn()));
        } else {
            ajusteDeInventarioModel.setResultadoPeso(null);
        }
        if(ajusteDeInventarioForm.getResultadoCantidadHdn() != null && !ajusteDeInventarioForm.getResultadoCantidadHdn().isEmpty()) {
            ajusteDeInventarioModel.setResultadoCantidad(Double.valueOf(ajusteDeInventarioForm.getResultadoCantidadHdn()));
        } else {
            ajusteDeInventarioModel.setResultadoCantidad(null);
        }
        if(ajusteDeInventarioForm.getMotivo() != null && !ajusteDeInventarioForm.getMotivo().isEmpty()) {
            ajusteDeInventarioModel.setMotivo(ajusteDeInventarioForm.getMotivo());
        } else {
            ajusteDeInventarioModel.setMotivo(null);
        }
        ajusteDeInventarioModel.setIdUser(Integer.valueOf(Utils.getUserLoggedId(req)));
        
        
        if(ajusteDeInventarioForm.getAction().equalsIgnoreCase("add") || ajusteDeInventarioForm.getAction().equalsIgnoreCase("edit")) {
            ajusteDeInventarioService.save(ajusteDeInventarioModel);
            
            if(ajusteDeInventarioForm.getTipo().equalsIgnoreCase("articulos")){
                articulo.setStock(Integer.valueOf(ajusteDeInventarioForm.getResultadoCantidadHdn()));
                articulo.setStockPeso(Double.valueOf(ajusteDeInventarioForm.getResultadoPesoHdn()));
                
                articuloService.save(articulo);
            }
            if(ajusteDeInventarioForm.getTipo().equalsIgnoreCase("materiaPrima")){
                materiaPrima.setStock(Integer.valueOf(ajusteDeInventarioForm.getResultadoCantidadHdn()));
                materiaPrima.setStockPeso(Double.valueOf(ajusteDeInventarioForm.getResultadoPesoHdn()));
                
                materiaPrimaService.save(materiaPrima);                
            }            
            if(ajusteDeInventarioForm.getTipo().equalsIgnoreCase("insumos")){
                insumo.setStock(Integer.valueOf(ajusteDeInventarioForm.getResultadoCantidadHdn()));
                insumo.setStockPeso(Double.valueOf(ajusteDeInventarioForm.getResultadoPesoHdn()));
                
                insumoService.save(insumo);                
            }
            
        } else {
            if(ajusteDeInventarioForm.getAction().equalsIgnoreCase("remove")) {
                if(ajusteDeInventarioModel.getId() == null) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: id de ajusteDeInventario inválido.");
                    return modelAndView;                                    
                }
                
                ajusteDeInventarioService.delete(ajusteDeInventarioModel);
            } else {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: operación inválida.");
                return modelAndView;                                
            }
        }
                        
        modelAndView.setViewName("redirect:/ajusteDeInventario");

        return modelAndView; 
    }

    @RequestMapping(value = "/ajusteDeInventario/edit/{ajusteDeInventariopk}", method = RequestMethod.GET)
    public String editAjusteDeInventario(@PathVariable String ajusteDeInventariopk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ajusteDeInventariopk == null) {
            model.addAttribute("errorMessage", "Error: AjusteDeInventario inválido");         
            return "/error/error";                
        }
        
        AjusteDeInventarioService ajusteDeInventarioService = new AjusteDeInventarioServiceImpl();   
        AjusteDeInventarioModel ajusteDeInventario = ajusteDeInventarioService.getByPk(Integer.valueOf(ajusteDeInventariopk));
        if(ajusteDeInventario == null) {
            model.addAttribute("errorMessage", "Error: AjusteDeInventario inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        AjusteDeInventarioForm ajusteDeInventarioForm = new AjusteDeInventarioForm();
        ajusteDeInventarioForm.setPk(ajusteDeInventario.getId().toString());
        if(ajusteDeInventario.getFechaAlta() != null) {
            ajusteDeInventarioForm.setFechaAlta(ajusteDeInventario.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(ajusteDeInventario.getTipo() != null && !ajusteDeInventario.getTipo().isEmpty()) {
            ajusteDeInventarioForm.setTipo(ajusteDeInventario.getTipo());
        }        
        if(ajusteDeInventario.getIdArticulo() != null) {
            ajusteDeInventarioForm.setIdArticulo(ajusteDeInventario.getIdArticulo().toString());
        } else {
            ajusteDeInventarioForm.setIdArticulo("-1");
        }
        if(ajusteDeInventario.getIdMateriaPrima() != null) {
            ajusteDeInventarioForm.setIdMateriaPrima(ajusteDeInventario.getIdMateriaPrima().toString());
        } else {
            ajusteDeInventarioForm.setIdMateriaPrima("-1");
        }
        if(ajusteDeInventario.getIdInsumo() != null) {
            ajusteDeInventarioForm.setIdInsumo(ajusteDeInventario.getIdInsumo().toString());
        } else {
            ajusteDeInventarioForm.setIdInsumo("-1");
        }                
        if(ajusteDeInventario.getIdDeposito() != null) {
            ajusteDeInventarioForm.setIdDeposito(ajusteDeInventario.getIdDeposito().toString());
        }
        if(ajusteDeInventario.getExistentePeso() != null) {
            ajusteDeInventarioForm.setExistentePeso(ajusteDeInventario.getExistentePeso().toString());
        }
        if(ajusteDeInventario.getExistenteCantidad() != null) {
            ajusteDeInventarioForm.setExistenteCantidad(ajusteDeInventario.getExistenteCantidad().toString());
        }
        if(ajusteDeInventario.getAjustePeso() != null) {
            ajusteDeInventarioForm.setAjustePeso(ajusteDeInventario.getAjustePeso().toString());
        }
        if(ajusteDeInventario.getAjusteCantidad() != null) {
            ajusteDeInventarioForm.setAjusteCantidad(ajusteDeInventario.getAjusteCantidad().toString());
        }
        if(ajusteDeInventario.getResultadoPeso() != null) {
            ajusteDeInventarioForm.setResultadoPeso(ajusteDeInventario.getResultadoPeso().toString());
        }
        if(ajusteDeInventario.getResultadoCantidad() != null) {
            ajusteDeInventarioForm.setResultadoCantidad(ajusteDeInventario.getResultadoCantidad().toString());
        }        
        if(ajusteDeInventario.getMotivo() != null) {
            ajusteDeInventarioForm.setMotivo(ajusteDeInventario.getMotivo());
        }
        
        ajusteDeInventarioForm.setAction("edit");
        model.addAttribute("ajusteDeInventarioForm", ajusteDeInventarioForm);  
        model.addAttribute("titleAjusteDeInventario", "Editar AjusteDeInventario");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("ajusteDeInventarioName", ajusteDeInventario.getId() + " - " + ajusteDeInventario.getTipo());        
        
        List<AjusteDeInventarioModel> ajustesDeInventario = ajusteDeInventarioService.getAll();

        ArticuloService articuloService = new ArticuloServiceImpl();   
        Map<String,String> articulos = new LinkedHashMap<String,String>();
        List<ArticuloModel> articulosModel = articuloService.getAll();

        MateriaPrimaService materiaPrimaService = new MateriaPrimaServiceImpl();   
        Map<String,String> materiasPrima = new LinkedHashMap<String,String>();
        List<MateriaPrimaModel> materiasPrimaModel = materiaPrimaService.getAll();

        InsumoService insumoService = new InsumoServiceImpl();   
        Map<String,String> insumos = new LinkedHashMap<String,String>();
        List<InsumoModel> insumosModel = insumoService.getAll();
        
        Map<String,String> depositos = new LinkedHashMap<String,String>();
                
        List<AjusteDeInventarioDto> ajustesDeInventarioDtos = new ArrayList<AjusteDeInventarioDto>();
        for(AjusteDeInventarioModel p: ajustesDeInventario) {
            AjusteDeInventarioDto ajusteDeInventarioDto = new AjusteDeInventarioDto();
            ajusteDeInventarioDto.setPk(p.getId().toString());
            ajusteDeInventarioDto.setFechaAlta(p.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            ajusteDeInventarioDto.setTipo(p.getTipo());
            if(p.getTipo().equalsIgnoreCase("articulos") && p.getIdArticulo() != null) {
                String descripcion = "";
                ArticuloModel articuloModel = articuloService.getByPk(p.getIdArticulo());
                if(articuloModel != null){
                    descripcion = articuloModel.getDenominacion();
                }                
                ajusteDeInventarioDto.setDescripcion(descripcion);
            }                                    
            if(p.getTipo().equalsIgnoreCase("materiaPrima") && p.getIdMateriaPrima() != null) {
                String descripcion = "";
                MateriaPrimaModel materiaPrimaModel = materiaPrimaService.getByPk(p.getIdMateriaPrima());
                if(materiaPrimaModel != null){
                    descripcion = materiaPrimaModel.getDescripcion();
                }                
                ajusteDeInventarioDto.setDescripcion(descripcion);
            }                                    
            if(p.getTipo().equalsIgnoreCase("insumos") && p.getIdInsumo() != null) {
                String descripcion = "";
                InsumoModel insumoModel = insumoService.getByPk(p.getIdInsumo());
                if(insumoModel != null){
                    descripcion = insumoModel.getDescripcion();
                }                
                ajusteDeInventarioDto.setDescripcion(descripcion);
            }                                    
            ajusteDeInventarioDto.setAjusteCantidad(p.getAjusteCantidad().toString());
            ajusteDeInventarioDto.setAjustePeso(p.getAjustePeso().toString());
            ajusteDeInventarioDto.setExistenteCantidad(p.getExistenteCantidad().toString());
            ajusteDeInventarioDto.setExistentePeso(p.getExistentePeso().toString());
            ajusteDeInventarioDto.setResultadoCantidad(p.getResultadoCantidad().toString());
            ajusteDeInventarioDto.setResultadoPeso(p.getResultadoPeso().toString());
            
            ajustesDeInventarioDtos.add(ajusteDeInventarioDto);
        }
                       
        if(articulosModel != null && !articulosModel.isEmpty()){
            for(ArticuloModel articuloModel :articulosModel) {
                articulos.put(articuloModel.getId().toString(), articuloModel.getDenominacion());
            }
        }
        if(materiasPrimaModel != null && !materiasPrimaModel.isEmpty()){
            for(MateriaPrimaModel materiaPrimaModel :materiasPrimaModel) {
                materiasPrima.put(materiaPrimaModel.getId().toString(), materiaPrimaModel.getDescripcion());
            }
        }
        if(insumosModel != null && !insumosModel.isEmpty()){
            for(InsumoModel insumoModel :insumosModel) {
                insumos.put(insumoModel.getId().toString(), insumoModel.getDescripcion());
            }
        }
        
        model.addAttribute("displayUser", "none");
        model.addAttribute("displayActionButton", "block");
        model.addAttribute("articuloList", articulos);        
        model.addAttribute("insumoList", insumos);        
        model.addAttribute("materiaPrimaList", materiasPrima);                
        model.addAttribute("depositosList", depositos);        
        model.addAttribute("ajustesDeInventario", ajustesDeInventarioDtos);        
                
        return "/ajustedeinventario/ajustedeinventario";        
    }
    
    @RequestMapping(value = "/ajusteDeInventario/remove/{ajusteDeInventariopk}", method = RequestMethod.GET)
    public String removeAjusteDeInventario(@PathVariable String ajusteDeInventariopk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ajusteDeInventariopk == null) {
            model.addAttribute("errorMessage", "Error: AjusteDeInventario inválido");         
            return "/error/error";                
        }
        
        AjusteDeInventarioService ajusteDeInventarioService = new AjusteDeInventarioServiceImpl();   
        AjusteDeInventarioModel ajusteDeInventario = ajusteDeInventarioService.getByPk(Integer.valueOf(ajusteDeInventariopk));
        if(ajusteDeInventario == null) {
            model.addAttribute("errorMessage", "Error: AjusteDeInventario inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        AjusteDeInventarioForm ajusteDeInventarioForm = new AjusteDeInventarioForm();
        ajusteDeInventarioForm.setPk(ajusteDeInventario.getId().toString());
        if(ajusteDeInventario.getFechaAlta() != null) {
            ajusteDeInventarioForm.setFechaAlta(ajusteDeInventario.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(ajusteDeInventario.getTipo() != null && !ajusteDeInventario.getTipo().isEmpty()) {
            ajusteDeInventarioForm.setTipo(ajusteDeInventario.getTipo());
        }        
        if(ajusteDeInventario.getIdArticulo() != null) {
            ajusteDeInventarioForm.setIdArticulo(ajusteDeInventario.getIdArticulo().toString());
        } else {
            ajusteDeInventarioForm.setIdArticulo("-1");
        }
        if(ajusteDeInventario.getIdMateriaPrima() != null) {
            ajusteDeInventarioForm.setIdMateriaPrima(ajusteDeInventario.getIdMateriaPrima().toString());
        } else {
            ajusteDeInventarioForm.setIdMateriaPrima("-1");
        }
        if(ajusteDeInventario.getIdInsumo() != null) {
            ajusteDeInventarioForm.setIdInsumo(ajusteDeInventario.getIdInsumo().toString());
        } else {
            ajusteDeInventarioForm.setIdInsumo("-1");
        }                
        if(ajusteDeInventario.getIdDeposito() != null) {
            ajusteDeInventarioForm.setIdDeposito(ajusteDeInventario.getIdDeposito().toString());
        }
        if(ajusteDeInventario.getExistentePeso() != null) {
            ajusteDeInventarioForm.setExistentePeso(ajusteDeInventario.getExistentePeso().toString());
        }
        if(ajusteDeInventario.getExistenteCantidad() != null) {
            ajusteDeInventarioForm.setExistenteCantidad(ajusteDeInventario.getExistenteCantidad().toString());
        }
        if(ajusteDeInventario.getAjustePeso() != null) {
            ajusteDeInventarioForm.setAjustePeso(ajusteDeInventario.getAjustePeso().toString());
        }
        if(ajusteDeInventario.getAjusteCantidad() != null) {
            ajusteDeInventarioForm.setAjusteCantidad(ajusteDeInventario.getAjusteCantidad().toString());
        }
        if(ajusteDeInventario.getResultadoPeso() != null) {
            ajusteDeInventarioForm.setResultadoPeso(ajusteDeInventario.getResultadoPeso().toString());
        }
        if(ajusteDeInventario.getResultadoCantidad() != null) {
            ajusteDeInventarioForm.setResultadoCantidad(ajusteDeInventario.getResultadoCantidad().toString());
        }        
        if(ajusteDeInventario.getMotivo() != null) {
            ajusteDeInventarioForm.setMotivo(ajusteDeInventario.getMotivo());
        }
                
        ajusteDeInventarioForm.setAction("remove");
        model.addAttribute("ajusteDeInventarioForm", ajusteDeInventarioForm);  
        model.addAttribute("titleAjusteDeInventario", "Eliminar AjusteDeInventario");
        model.addAttribute("buttonLabel", "Eliminar");
        model.addAttribute("ajusteDeInventarioName", ajusteDeInventario.getId() + " - " + ajusteDeInventario.getTipo());
        
        List<AjusteDeInventarioModel> ajustesDeInventario = ajusteDeInventarioService.getAll();

        ArticuloService articuloService = new ArticuloServiceImpl();   
        Map<String,String> articulos = new LinkedHashMap<String,String>();
        List<ArticuloModel> articulosModel = articuloService.getAll();

        MateriaPrimaService materiaPrimaService = new MateriaPrimaServiceImpl();   
        Map<String,String> materiasPrima = new LinkedHashMap<String,String>();
        List<MateriaPrimaModel> materiasPrimaModel = materiaPrimaService.getAll();

        InsumoService insumoService = new InsumoServiceImpl();   
        Map<String,String> insumos = new LinkedHashMap<String,String>();
        List<InsumoModel> insumosModel = insumoService.getAll();
        
        Map<String,String> depositos = new LinkedHashMap<String,String>();
                
        List<AjusteDeInventarioDto> ajustesDeInventarioDtos = new ArrayList<AjusteDeInventarioDto>();
        for(AjusteDeInventarioModel p: ajustesDeInventario) {
            AjusteDeInventarioDto ajusteDeInventarioDto = new AjusteDeInventarioDto();
            ajusteDeInventarioDto.setPk(p.getId().toString());
            ajusteDeInventarioDto.setFechaAlta(p.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            ajusteDeInventarioDto.setTipo(p.getTipo());
            if(p.getTipo().equalsIgnoreCase("articulos") && p.getIdArticulo() != null) {
                String descripcion = "";
                ArticuloModel articuloModel = articuloService.getByPk(p.getIdArticulo());
                if(articuloModel != null){
                    descripcion = articuloModel.getDenominacion();
                }                
                ajusteDeInventarioDto.setDescripcion(descripcion);
            }                                    
            if(p.getTipo().equalsIgnoreCase("materiaPrima") && p.getIdMateriaPrima() != null) {
                String descripcion = "";
                MateriaPrimaModel materiaPrimaModel = materiaPrimaService.getByPk(p.getIdMateriaPrima());
                if(materiaPrimaModel != null){
                    descripcion = materiaPrimaModel.getDescripcion();
                }                
                ajusteDeInventarioDto.setDescripcion(descripcion);
            }                                    
            if(p.getTipo().equalsIgnoreCase("insumos") && p.getIdInsumo() != null) {
                String descripcion = "";
                InsumoModel insumoModel = insumoService.getByPk(p.getIdInsumo());
                if(insumoModel != null){
                    descripcion = insumoModel.getDescripcion();
                }                
                ajusteDeInventarioDto.setDescripcion(descripcion);
            }                                    
            ajusteDeInventarioDto.setAjusteCantidad(p.getAjusteCantidad().toString());
            ajusteDeInventarioDto.setAjustePeso(p.getAjustePeso().toString());
            ajusteDeInventarioDto.setExistenteCantidad(p.getExistenteCantidad().toString());
            ajusteDeInventarioDto.setExistentePeso(p.getExistentePeso().toString());
            ajusteDeInventarioDto.setResultadoCantidad(p.getResultadoCantidad().toString());
            ajusteDeInventarioDto.setResultadoPeso(p.getResultadoPeso().toString());
            
            ajustesDeInventarioDtos.add(ajusteDeInventarioDto);
        }
                       
        if(articulosModel != null && !articulosModel.isEmpty()){
            for(ArticuloModel articuloModel :articulosModel) {
                articulos.put(articuloModel.getId().toString(), articuloModel.getDenominacion());
            }
        }
        if(materiasPrimaModel != null && !materiasPrimaModel.isEmpty()){
            for(MateriaPrimaModel materiaPrimaModel :materiasPrimaModel) {
                materiasPrima.put(materiaPrimaModel.getId().toString(), materiaPrimaModel.getDescripcion());
            }
        }
        if(insumosModel != null && !insumosModel.isEmpty()){
            for(InsumoModel insumoModel :insumosModel) {
                insumos.put(insumoModel.getId().toString(), insumoModel.getDescripcion());
            }
        }
        
        model.addAttribute("displayUser", "none");
        model.addAttribute("displayActionButton", "block");
        model.addAttribute("articuloList", articulos);        
        model.addAttribute("insumoList", insumos);        
        model.addAttribute("materiaPrimaList", materiasPrima);                
        model.addAttribute("depositosList", depositos);        
        model.addAttribute("ajustesDeInventario", ajustesDeInventarioDtos);    
                
        return "/ajustedeinventario/ajustedeinventario";        
    }    
    
    @RequestMapping(value = "/ajusteDeInventario/view/{ajusteDeInventariopk}", method = RequestMethod.GET)
    public String viewAjusteDeInventario(@PathVariable String ajusteDeInventariopk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ajusteDeInventariopk == null) {
            model.addAttribute("errorMessage", "Error: AjusteDeInventario inválido");         
            return "/error/error";                
        }
        
        AjusteDeInventarioService ajusteDeInventarioService = new AjusteDeInventarioServiceImpl();   
        AjusteDeInventarioModel ajusteDeInventario = ajusteDeInventarioService.getByPk(Integer.valueOf(ajusteDeInventariopk));
        if(ajusteDeInventario == null) {
            model.addAttribute("errorMessage", "Error: AjusteDeInventario inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        AjusteDeInventarioForm ajusteDeInventarioForm = new AjusteDeInventarioForm();
        ajusteDeInventarioForm.setPk(ajusteDeInventario.getId().toString());
        if(ajusteDeInventario.getFechaAlta() != null) {
            ajusteDeInventarioForm.setFechaAlta(ajusteDeInventario.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(ajusteDeInventario.getTipo() != null && !ajusteDeInventario.getTipo().isEmpty()) {
            ajusteDeInventarioForm.setTipo(ajusteDeInventario.getTipo());
        }        
        if(ajusteDeInventario.getIdArticulo() != null) {
            ajusteDeInventarioForm.setIdArticulo(ajusteDeInventario.getIdArticulo().toString());
        } else {
            ajusteDeInventarioForm.setIdArticulo("-1");
        }
        if(ajusteDeInventario.getIdMateriaPrima() != null) {
            ajusteDeInventarioForm.setIdMateriaPrima(ajusteDeInventario.getIdMateriaPrima().toString());
        } else {
            ajusteDeInventarioForm.setIdMateriaPrima("-1");
        }
        if(ajusteDeInventario.getIdInsumo() != null) {
            ajusteDeInventarioForm.setIdInsumo(ajusteDeInventario.getIdInsumo().toString());
        } else {
            ajusteDeInventarioForm.setIdInsumo("-1");
        }                
        if(ajusteDeInventario.getIdDeposito() != null) {
            ajusteDeInventarioForm.setIdDeposito(ajusteDeInventario.getIdDeposito().toString());
        }
        if(ajusteDeInventario.getExistentePeso() != null) {
            ajusteDeInventarioForm.setExistentePeso(ajusteDeInventario.getExistentePeso().toString());
        }
        if(ajusteDeInventario.getExistenteCantidad() != null) {
            ajusteDeInventarioForm.setExistenteCantidad(ajusteDeInventario.getExistenteCantidad().toString());
        }
        if(ajusteDeInventario.getAjustePeso() != null) {
            ajusteDeInventarioForm.setAjustePeso(ajusteDeInventario.getAjustePeso().toString());
        }
        if(ajusteDeInventario.getAjusteCantidad() != null) {
            ajusteDeInventarioForm.setAjusteCantidad(ajusteDeInventario.getAjusteCantidad().toString());
        }
        if(ajusteDeInventario.getResultadoPeso() != null) {
            ajusteDeInventarioForm.setResultadoPeso(ajusteDeInventario.getResultadoPeso().toString());
        }
        if(ajusteDeInventario.getResultadoCantidad() != null) {
            ajusteDeInventarioForm.setResultadoCantidad(ajusteDeInventario.getResultadoCantidad().toString());
        }        
        if(ajusteDeInventario.getMotivo() != null) {
            ajusteDeInventarioForm.setMotivo(ajusteDeInventario.getMotivo());
        }
                
        ajusteDeInventarioForm.setAction("view");
        model.addAttribute("ajusteDeInventarioForm", ajusteDeInventarioForm);  
        model.addAttribute("titleAjusteDeInventario", "Ver AjusteDeInventario " + ajusteDeInventario.getId());
        model.addAttribute("buttonLabel", "Eliminar");
        model.addAttribute("ajusteDeInventarioName", ajusteDeInventario.getId() + " - " + ajusteDeInventario.getTipo());
        
        List<AjusteDeInventarioModel> ajustesDeInventario = ajusteDeInventarioService.getAll();

        ArticuloService articuloService = new ArticuloServiceImpl();   
        Map<String,String> articulos = new LinkedHashMap<String,String>();
        List<ArticuloModel> articulosModel = articuloService.getAll();

        MateriaPrimaService materiaPrimaService = new MateriaPrimaServiceImpl();   
        Map<String,String> materiasPrima = new LinkedHashMap<String,String>();
        List<MateriaPrimaModel> materiasPrimaModel = materiaPrimaService.getAll();

        InsumoService insumoService = new InsumoServiceImpl();   
        Map<String,String> insumos = new LinkedHashMap<String,String>();
        List<InsumoModel> insumosModel = insumoService.getAll();
        
        Map<String,String> depositos = new LinkedHashMap<String,String>();
                
        List<AjusteDeInventarioDto> ajustesDeInventarioDtos = new ArrayList<AjusteDeInventarioDto>();
        for(AjusteDeInventarioModel p: ajustesDeInventario) {
            AjusteDeInventarioDto ajusteDeInventarioDto = new AjusteDeInventarioDto();
            ajusteDeInventarioDto.setPk(p.getId().toString());
            ajusteDeInventarioDto.setFechaAlta(p.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            ajusteDeInventarioDto.setTipo(p.getTipo());
            if(p.getTipo().equalsIgnoreCase("articulos") && p.getIdArticulo() != null) {
                String descripcion = "";
                ArticuloModel articuloModel = articuloService.getByPk(p.getIdArticulo());
                if(articuloModel != null){
                    descripcion = articuloModel.getDenominacion();
                }                
                ajusteDeInventarioDto.setDescripcion(descripcion);
            }                                    
            if(p.getTipo().equalsIgnoreCase("materiaPrima") && p.getIdMateriaPrima() != null) {
                String descripcion = "";
                MateriaPrimaModel materiaPrimaModel = materiaPrimaService.getByPk(p.getIdMateriaPrima());
                if(materiaPrimaModel != null){
                    descripcion = materiaPrimaModel.getDescripcion();
                }                
                ajusteDeInventarioDto.setDescripcion(descripcion);
            }                                    
            if(p.getTipo().equalsIgnoreCase("insumos") && p.getIdInsumo() != null) {
                String descripcion = "";
                InsumoModel insumoModel = insumoService.getByPk(p.getIdInsumo());
                if(insumoModel != null){
                    descripcion = insumoModel.getDescripcion();
                }                
                ajusteDeInventarioDto.setDescripcion(descripcion);
            }                                    
            ajusteDeInventarioDto.setAjusteCantidad(p.getAjusteCantidad().toString());
            ajusteDeInventarioDto.setAjustePeso(p.getAjustePeso().toString());
            ajusteDeInventarioDto.setExistenteCantidad(p.getExistenteCantidad().toString());
            ajusteDeInventarioDto.setExistentePeso(p.getExistentePeso().toString());
            ajusteDeInventarioDto.setResultadoCantidad(p.getResultadoCantidad().toString());
            ajusteDeInventarioDto.setResultadoPeso(p.getResultadoPeso().toString());
            
            ajustesDeInventarioDtos.add(ajusteDeInventarioDto);
        }
                       
        if(articulosModel != null && !articulosModel.isEmpty()){
            for(ArticuloModel articuloModel :articulosModel) {
                articulos.put(articuloModel.getId().toString(), articuloModel.getDenominacion());
            }
        }
        if(materiasPrimaModel != null && !materiasPrimaModel.isEmpty()){
            for(MateriaPrimaModel materiaPrimaModel :materiasPrimaModel) {
                materiasPrima.put(materiaPrimaModel.getId().toString(), materiaPrimaModel.getDescripcion());
            }
        }
        if(insumosModel != null && !insumosModel.isEmpty()){
            for(InsumoModel insumoModel :insumosModel) {
                insumos.put(insumoModel.getId().toString(), insumoModel.getDescripcion());
            }
        }
        
        UserService userService = new UserServiceImpl();   
        UserModel user = userService.getUserById(ajusteDeInventario.getIdUser());

        model.addAttribute("userName", user.getUsername());
        model.addAttribute("displayUser", "block");
        model.addAttribute("displayActionButton", "none");
        model.addAttribute("articuloList", articulos);        
        model.addAttribute("insumoList", insumos);        
        model.addAttribute("materiaPrimaList", materiasPrima);                
        model.addAttribute("depositosList", depositos);        
        model.addAttribute("ajustesDeInventario", ajustesDeInventarioDtos);    
                
        return "/ajustedeinventario/ajustedeinventario";        
    }    
    
    @RequestMapping(value = "/ajusteDeInventario/loadStock/{tipo}/id/{idTipo}", method = RequestMethod.GET)
    public @ResponseBody String loadStock(@PathVariable String tipo, @PathVariable String idTipo, HttpServletRequest req, ModelMap model) throws Exception {
            
        String result = "nn|nn";
        
        if(tipo == null || tipo.isEmpty() || idTipo == null || idTipo.isEmpty()) {
            return result;
        }
        if(tipo.equalsIgnoreCase("materiaprima")) {
            MateriaPrimaService materiaPrimaService = new MateriaPrimaServiceImpl();
            MateriaPrimaModel materiaPrima = materiaPrimaService.getByPk(Integer.valueOf(idTipo));
            if(materiaPrima != null) {
                result = materiaPrima.getStock() + "|" + materiaPrima.getStockPeso();
            } 
        }
        if(tipo.equalsIgnoreCase("articulo")) {
            ArticuloService articuloService = new ArticuloServiceImpl();
            ArticuloModel articulo = articuloService.getByPk(Integer.valueOf(idTipo));
            if(articulo != null) {
                result = articulo.getStock() + "|" + articulo.getStockPeso();
            }             
        }
        if(tipo.equalsIgnoreCase("insumo")) {
            InsumoService insumoService = new InsumoServiceImpl();
            InsumoModel insumo = insumoService.getByPk(Integer.valueOf(idTipo));
            if(insumo != null) {
                result = insumo.getStock() + "|" + insumo.getStockPeso();
            } 
        }
        
        System.out.println("**** loadStock:"+tipo+" - "+idTipo);
        
        return result;
    }    
    
}
