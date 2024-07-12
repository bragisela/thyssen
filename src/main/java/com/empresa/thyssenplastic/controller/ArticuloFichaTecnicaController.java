/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

 
import com.empresa.thyssenplastic.controller.beans.MateriaPrimaBean;
import com.empresa.thyssenplastic.controller.form.ArticuloFichaTecnicaForm;
import com.empresa.thyssenplastic.controller.form.ArticuloForm;
import com.empresa.thyssenplastic.controller.form.UserForm;
import com.empresa.thyssenplastic.dto.ArticuloDto;
import com.empresa.thyssenplastic.dto.ArticuloFichaTecnicaDto;
import com.empresa.thyssenplastic.model.ArticuloFichaTecnicaModel;
import com.empresa.thyssenplastic.model.ArticuloModel;
import com.empresa.thyssenplastic.model.ClienteModel;
import com.empresa.thyssenplastic.model.MateriaPrimaModel;
import com.empresa.thyssenplastic.model.ProveedorModel;
import com.empresa.thyssenplastic.model.TipoModel;
import com.empresa.thyssenplastic.model.UserModel;
import com.empresa.thyssenplastic.service.ArticuloFichaTecnicaService;
import com.empresa.thyssenplastic.service.ArticuloService;
import com.empresa.thyssenplastic.service.ClienteService;
import com.empresa.thyssenplastic.service.MateriaPrimaService;
import com.empresa.thyssenplastic.service.ProveedorService;
import com.empresa.thyssenplastic.service.TipoService;
import com.empresa.thyssenplastic.service.UserService;
import com.empresa.thyssenplastic.service.impl.ArticuloFichaTecnicaServiceImpl;
import com.empresa.thyssenplastic.service.impl.ArticuloServiceImpl;
import com.empresa.thyssenplastic.service.impl.ClienteServiceImpl;
import com.empresa.thyssenplastic.service.impl.MateriaPrimaServiceImpl;
import com.empresa.thyssenplastic.service.impl.ProveedorServiceImpl;
import com.empresa.thyssenplastic.service.impl.TipoServiceImpl;
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
public class ArticuloFichaTecnicaController {
    

    @RequestMapping(value = "/articulofichatecnica/{idArticulo}", method = RequestMethod.GET)
    public String getHomeArticulo(@PathVariable String idArticulo, HttpServletRequest req, ModelMap model) {

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(idArticulo == null) {
            model.addAttribute("errorMessage", "Error: Articulo inválido");         
            return "/error/error";                
        }
        
        ArticuloService articuloService = new ArticuloServiceImpl();   
        ArticuloModel articulo = articuloService.getByPk(Integer.valueOf(idArticulo));
        
        if(articulo == null) {
            model.addAttribute("errorMessage", "Error: Articulo inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        ClienteService clienteService = new ClienteServiceImpl();   
        ClienteModel cliente = clienteService.getByPk(articulo.getIdCliente());
        
        ArticuloFichaTecnicaForm articuloFichaTecnicaForm = new ArticuloFichaTecnicaForm();
        articuloFichaTecnicaForm.setPk("-1");
        articuloFichaTecnicaForm.setAction("add");
        articuloFichaTecnicaForm.setIdArticulo(articulo.getId().toString());
        articuloFichaTecnicaForm.setIdCliente(cliente.getId().toString());
        articuloFichaTecnicaForm.setCliente(cliente.getRazonSocial());
        
        model.addAttribute("articuloFichaTecnicaForm", articuloFichaTecnicaForm);  
        model.addAttribute("titleArticuloFichaTecnica", "Nueva Ficha Técnica de Articulo");  
        model.addAttribute("buttonLabel", "Guardar");
        
        ArticuloFichaTecnicaService articuloFichaTecnicaService = new ArticuloFichaTecnicaServiceImpl();   
        List<ArticuloFichaTecnicaModel> fichasTecnicas = articuloFichaTecnicaService.getAllByArticulo(articulo.getId());
        
        Integer ultimaVersion = null;
        if(fichasTecnicas != null && !fichasTecnicas.isEmpty()) {
            ultimaVersion = fichasTecnicas.get(0).getId();
        }
        List<ArticuloFichaTecnicaDto> articuloFichasTecnicaDtos = new ArrayList<ArticuloFichaTecnicaDto>();
        for(ArticuloFichaTecnicaModel fichasTecnica: fichasTecnicas) {
            ArticuloFichaTecnicaDto fichaTecnicaDto = new ArticuloFichaTecnicaDto();
            fichaTecnicaDto.setPk(fichasTecnica.getId().toString());
            fichaTecnicaDto.setFechaAlta(fichasTecnica.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            fichaTecnicaDto.setVersion(fichasTecnica.getVersion().toString());
            if(ultimaVersion !=null && ultimaVersion == fichasTecnica.getId()){
                fichaTecnicaDto.setUltimaVersion("true");
            }
            articuloFichasTecnicaDtos.add(fichaTecnicaDto);
        }

        TipoService tipoService = new TipoServiceImpl();
        Map<String,String> materiales = new LinkedHashMap<String,String>();
        List<TipoModel> materialesModel = tipoService.getByType("articuloMaterial");        
        
        if(materialesModel != null && !materialesModel.isEmpty()){
            for(TipoModel tipoModel :materialesModel) {
                materiales.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        

        Map<String,String> tipoBobinas = new LinkedHashMap<String,String>();
        List<TipoModel> tipoBobinasModel = tipoService.getByType("tipoBobina");        
        
        if(tipoBobinasModel != null && !tipoBobinasModel.isEmpty()){
            for(TipoModel tipoModel :tipoBobinasModel) {
                tipoBobinas.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        

        Map<String,String> lineaTintas = new LinkedHashMap<String,String>();
        List<TipoModel> lineaTintasModel = tipoService.getByType("lineaTintas");        
        
        if(lineaTintasModel != null && !lineaTintasModel.isEmpty()){
            for(TipoModel tipoModel :lineaTintasModel) {
                lineaTintas.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        

        Map<String,String> lineaSolventes = new LinkedHashMap<String,String>();
        List<TipoModel> lineaSolventesModel = tipoService.getByType("lineaSolventes");        
        
        if(lineaSolventesModel != null && !lineaSolventesModel.isEmpty()){
            for(TipoModel tipoModel :lineaSolventesModel) {
                lineaSolventes.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        

        Map<String,String> bultosEn = new LinkedHashMap<String,String>();
        List<TipoModel> bultosEnModel = tipoService.getByType("bultosEn");        
        
        if(bultosEnModel != null && !bultosEnModel.isEmpty()){
            for(TipoModel tipoModel :bultosEnModel) {
                bultosEn.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        

        Map<String,String> palets = new LinkedHashMap<String,String>();
        List<TipoModel> paletsModel = tipoService.getByType("palets");        
        
        if(paletsModel != null && !paletsModel.isEmpty()){
            for(TipoModel tipoModel :paletsModel) {
                palets.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        
        
        Map<String,String> colores = new LinkedHashMap<String,String>();
        List<TipoModel> coloresModel = tipoService.getByType("articuloColor");        
        
        if(coloresModel != null && !coloresModel.isEmpty()){
            for(TipoModel tipoModel :coloresModel) {
                colores.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        
        
        ProveedorService proveedorService = new ProveedorServiceImpl();
        Map<String,String> proveedores = new LinkedHashMap<String,String>();
        List<ProveedorModel> proveedoresModel = proveedorService.getAll();        
        
        if(proveedoresModel != null && !proveedoresModel.isEmpty()){
            for(ProveedorModel proveedorModel :proveedoresModel) {
                proveedores.put(proveedorModel.getId().toString(), proveedorModel.getRazonSocial());
            }
        }        

        MateriaPrimaService materiaPrimaService = new MateriaPrimaServiceImpl();
        Map<String,String> materiasPrima = new LinkedHashMap<String,String>();
        List<MateriaPrimaModel> materiasPrimaModel = materiaPrimaService.getAll();        
        
        if(materiasPrimaModel != null && !materiasPrimaModel.isEmpty()){
            for(MateriaPrimaModel materiaPrimaModel :materiasPrimaModel) {
                materiasPrima.put(materiaPrimaModel.getId().toString(), materiaPrimaModel.getDescripcion());
            }
        }        

        model.addAttribute("materiaPrimaList", materiasPrima);        
        model.addAttribute("proveedorList", proveedores);        
        model.addAttribute("colorList", colores);        
        model.addAttribute("materialList", materiales);                                
        model.addAttribute("tipoBobinaList", tipoBobinas);  
        model.addAttribute("lineaTintasList", lineaTintas);  
        model.addAttribute("lineaSolventesList", lineaSolventes);  
        model.addAttribute("bultoEnList", bultosEn);  
        model.addAttribute("paletList", palets);  
        model.addAttribute("articulo", articulo.getDenominacion());        
        model.addAttribute("articuloPk", articulo.getId());
        model.addAttribute("operacion", "new");
        model.addAttribute("articuloFichasTecnica", articuloFichasTecnicaDtos);   

        return "/articulofichatecnica/articulofichatecnica";
    }
    
    @RequestMapping(value = "/articulofichatecnica/addOrEditOrRemove", method = RequestMethod.POST)
    public ModelAndView addOrEditOrRemoveArticulo(@ModelAttribute("articuloFichaTecnicaForm")ArticuloFichaTecnicaForm articuloFichaTecnicaForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {
                
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
        
        if(articuloFichaTecnicaForm.getAction() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;            
        }

        if(articuloFichaTecnicaForm.getIdArticulo() == null || articuloFichaTecnicaForm.getIdArticulo().isEmpty()) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;            
        }
        
        ArticuloService articuloService = new ArticuloServiceImpl();   
        ArticuloModel articulo = articuloService.getByPk(Integer.valueOf(articuloFichaTecnicaForm.getIdArticulo()));
        
        if(articulo == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: Articulo inválido. No ha sido encontrado.");         
            return modelAndView;     
        }
        
        ArticuloFichaTecnicaService articuloFichaTecnicaService = new ArticuloFichaTecnicaServiceImpl();        
        
        String idCliente = articuloFichaTecnicaForm.getIdCliente();
        String sessionId = req.getSession().getId();
        String id = articuloFichaTecnicaForm.getPk();
            
        ArticuloFichaTecnicaModel articuloFichaTecnicaModel = null;
        if(id.equalsIgnoreCase("-1")) {
            articuloFichaTecnicaModel = new ArticuloFichaTecnicaModel();
            articuloFichaTecnicaModel.setIdArticulo(Integer.valueOf(articulo.getId()));
            articuloFichaTecnicaModel.setIdUsuarioCreacion(Integer.valueOf(Utils.getUserLoggedId(req)));
            if(articuloFichaTecnicaForm.getFechaAlta() != null && !articuloFichaTecnicaForm.getFechaAlta().isEmpty()) {
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
                Date fecha = formato.parse(articuloFichaTecnicaForm.getFechaAlta());
                articuloFichaTecnicaModel.setFechaAlta(fecha);
            } else {
                articuloFichaTecnicaModel.setFechaAlta(null);
            }                                                    
        } else {
            articuloFichaTecnicaModel = articuloFichaTecnicaService.getByPk(Integer.valueOf(id));
            if(articuloFichaTecnicaModel == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: id de ficha técnica de artículo inválido.");
                return modelAndView;                
            } 
        }        
        Date today = new Date();
        articuloFichaTecnicaModel.setFechaModificacion(today);
        if(articuloFichaTecnicaForm.getEspecificacion() != null && !articuloFichaTecnicaForm.getEspecificacion().isEmpty()) {
            articuloFichaTecnicaModel.setEspecificacion(articuloFichaTecnicaForm.getEspecificacion());
        }
        if(articuloFichaTecnicaForm.getObservaciones() != null && !articuloFichaTecnicaForm.getObservaciones().isEmpty()) {
            articuloFichaTecnicaModel.setObservaciones(articuloFichaTecnicaForm.getObservaciones());
        }
        if(articuloFichaTecnicaForm.getUrlFichaTecnica() != null && !articuloFichaTecnicaForm.getUrlFichaTecnica().isEmpty()) {
            articuloFichaTecnicaModel.setUrlEspecificacion(articuloFichaTecnicaForm.getUrlFichaTecnica());
        }
               
        articuloFichaTecnicaModel.setIdUsuarioModificacion(Integer.valueOf(Utils.getUserLoggedId(req)));                       

        if(articuloFichaTecnicaForm.getIdMaterial() != null && !articuloFichaTecnicaForm.getIdMaterial().isEmpty() && !articuloFichaTecnicaForm.getIdMaterial().equalsIgnoreCase("-1")) {
            articuloFichaTecnicaModel.setIdMaterial(Integer.valueOf(articuloFichaTecnicaForm.getIdMaterial()));
        } else {
            articuloFichaTecnicaModel.setIdMaterial(null);
        }
        Integer cantidadCapas = 1;
        
        if(articuloFichaTecnicaForm.getCantidadCapas() != null && !articuloFichaTecnicaForm.getCantidadCapas().isEmpty()) {
            cantidadCapas = Integer.valueOf(articuloFichaTecnicaForm.getCantidadCapas());
        }
        
        articuloFichaTecnicaModel.setCantidadCapas(cantidadCapas);
        
        if(articuloFichaTecnicaForm.getColorA() != null && !articuloFichaTecnicaForm.getColorA().isEmpty() && !articuloFichaTecnicaForm.getColorA().equalsIgnoreCase("-1")) {
            articuloFichaTecnicaModel.setIdColorA(Integer.valueOf(articuloFichaTecnicaForm.getColorA()));
        } else {
            articuloFichaTecnicaModel.setIdColorA(null);
        }
        
        if(cantidadCapas > 1) {
            if(articuloFichaTecnicaForm.getColorB() != null && !articuloFichaTecnicaForm.getColorB().isEmpty() && !articuloFichaTecnicaForm.getColorB().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdColorB(Integer.valueOf(articuloFichaTecnicaForm.getColorB()));
            } else {
                articuloFichaTecnicaModel.setIdColorB(null);
            }            
        } else {
            articuloFichaTecnicaModel.setIdColorB(null);
        }

        if(cantidadCapas > 2) {
            if(articuloFichaTecnicaForm.getColorC() != null && !articuloFichaTecnicaForm.getColorC().isEmpty() && !articuloFichaTecnicaForm.getColorC().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdColorC(Integer.valueOf(articuloFichaTecnicaForm.getColorC()));
            } else {
                articuloFichaTecnicaModel.setIdColorC(null);
            }            
        } else {
            articuloFichaTecnicaModel.setIdColorC(null);
        }

        if(cantidadCapas > 3) {
            if(articuloFichaTecnicaForm.getColorD() != null && !articuloFichaTecnicaForm.getColorD().isEmpty() && !articuloFichaTecnicaForm.getColorD().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdColorD(Integer.valueOf(articuloFichaTecnicaForm.getColorD()));
            } else {
                articuloFichaTecnicaModel.setIdColorD(null);
            }            
        } else {
            articuloFichaTecnicaModel.setIdColorD(null);
        }

        if(cantidadCapas > 4) {
            if(articuloFichaTecnicaForm.getColorE() != null && !articuloFichaTecnicaForm.getColorE().isEmpty() && !articuloFichaTecnicaForm.getColorE().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdColorE(Integer.valueOf(articuloFichaTecnicaForm.getColorE()));
            } else {
                articuloFichaTecnicaModel.setIdColorE(null);
            }            
        } else {
            articuloFichaTecnicaModel.setIdColorE(null);
        }

        if(cantidadCapas > 5) {
            if(articuloFichaTecnicaForm.getColorF() != null && !articuloFichaTecnicaForm.getColorF().isEmpty() && !articuloFichaTecnicaForm.getColorF().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdColorF(Integer.valueOf(articuloFichaTecnicaForm.getColorF()));
            } else {
                articuloFichaTecnicaModel.setIdColorF(null);
            }            
        }

        if(cantidadCapas > 6) {
            if(articuloFichaTecnicaForm.getColorG() != null && !articuloFichaTecnicaForm.getColorG().isEmpty() && !articuloFichaTecnicaForm.getColorG().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdColorG(Integer.valueOf(articuloFichaTecnicaForm.getColorG()));
            } else {
                articuloFichaTecnicaModel.setIdColorG(null);
            }            
        } else {
            articuloFichaTecnicaModel.setIdColorG(null);
        }

        if(articuloFichaTecnicaForm.getLamina() != null && !articuloFichaTecnicaForm.getLamina().isEmpty()) {
            if(articuloFichaTecnicaForm.getLamina().equals("1")) {
                articuloFichaTecnicaModel.setLamina(Boolean.TRUE);
            } else {
                articuloFichaTecnicaModel.setLamina(Boolean.FALSE);
            }
        } else {
            articuloFichaTecnicaModel.setLamina(Boolean.FALSE);
        }
                            
        if(articuloFichaTecnicaForm.getBobina() != null && !articuloFichaTecnicaForm.getBobina().isEmpty()) {            
            if(articuloFichaTecnicaForm.getBobina().equals("1")) {
                articuloFichaTecnicaModel.setBobina(Boolean.TRUE);
            } else {
                articuloFichaTecnicaModel.setBobina(Boolean.FALSE);
            }
        } else {
            articuloFichaTecnicaModel.setBobina(Boolean.FALSE);
        }

        if(articuloFichaTecnicaForm.getAncho() != null && !articuloFichaTecnicaForm.getAncho().isEmpty()) {
            articuloFichaTecnicaModel.setAncho(Double.valueOf(articuloFichaTecnicaForm.getAncho()));
        }            

        if(articuloFichaTecnicaForm.getFuelleIzquierdo() != null && !articuloFichaTecnicaForm.getFuelleIzquierdo().isEmpty()) {
            articuloFichaTecnicaModel.setFuelleIzquierdo(Double.valueOf(articuloFichaTecnicaForm.getFuelleIzquierdo()));
        }            

        if(articuloFichaTecnicaForm.getFuelleDerecho() != null && !articuloFichaTecnicaForm.getFuelleDerecho().isEmpty()) {
            articuloFichaTecnicaModel.setFuelleDerecho(Double.valueOf(articuloFichaTecnicaForm.getFuelleDerecho()));
        }            

        if(articuloFichaTecnicaForm.getEspesorNominal() != null && !articuloFichaTecnicaForm.getEspesorNominal().isEmpty()) {
            articuloFichaTecnicaModel.setEspesorNominal(Double.valueOf(articuloFichaTecnicaForm.getEspesorNominal()));
        }            

        if(articuloFichaTecnicaForm.getEspesorPromedio() != null && !articuloFichaTecnicaForm.getEspesorPromedio().isEmpty()) {
            articuloFichaTecnicaModel.setEspesorPromedio(Double.valueOf(articuloFichaTecnicaForm.getEspesorPromedio()));
        }            

        if(articuloFichaTecnicaForm.getEspesorArticulo() != null && !articuloFichaTecnicaForm.getEspesorArticulo().isEmpty()) {
            articuloFichaTecnicaModel.setEspesorArticulo(Double.valueOf(articuloFichaTecnicaForm.getEspesorArticulo()));
        }            

        if(articuloFichaTecnicaForm.getPesoEspecifico() != null && !articuloFichaTecnicaForm.getPesoEspecifico().isEmpty()) {
            articuloFichaTecnicaModel.setPesoEspecifico(Double.valueOf(articuloFichaTecnicaForm.getPesoEspecifico()));
        }            

        if(articuloFichaTecnicaForm.getAnchoBruto() != null && !articuloFichaTecnicaForm.getAnchoBruto().isEmpty()) {
            articuloFichaTecnicaModel.setAnchoBruto(Double.valueOf(articuloFichaTecnicaForm.getAnchoBruto()));
        }            

        if(articuloFichaTecnicaForm.getBobinadoBarras() != null && !articuloFichaTecnicaForm.getBobinadoBarras().isEmpty()) {
            articuloFichaTecnicaModel.setBobinadoBarras(Double.valueOf(articuloFichaTecnicaForm.getBobinadoBarras()));
        }            

        if(articuloFichaTecnicaForm.getMetros() != null && !articuloFichaTecnicaForm.getMetros().isEmpty()) {
            articuloFichaTecnicaModel.setMetros(Double.valueOf(articuloFichaTecnicaForm.getMetros()));
        }            

        if(articuloFichaTecnicaForm.getPeso() != null && !articuloFichaTecnicaForm.getPeso().isEmpty()) {
            articuloFichaTecnicaModel.setPeso(Double.valueOf(articuloFichaTecnicaForm.getPeso()));
        }            

        if(articuloFichaTecnicaForm.getDiametro() != null && !articuloFichaTecnicaForm.getDiametro().isEmpty()) {
            articuloFichaTecnicaModel.setDiametro(Double.valueOf(articuloFichaTecnicaForm.getDiametro()));
        }            

        if(articuloFichaTecnicaForm.getEmpalmes() != null && !articuloFichaTecnicaForm.getEmpalmes().isEmpty()) {
            articuloFichaTecnicaModel.setEmpalmes(Double.valueOf(articuloFichaTecnicaForm.getEmpalmes()));
        }            

        if(articuloFichaTecnicaForm.getIdTipoBobina() != null && !articuloFichaTecnicaForm.getIdTipoBobina().isEmpty() && !articuloFichaTecnicaForm.getIdTipoBobina().equalsIgnoreCase("-1")) {
            articuloFichaTecnicaModel.setIdTipoBobina(Integer.valueOf(articuloFichaTecnicaForm.getIdTipoBobina()));
        } else {
            articuloFichaTecnicaModel.setIdTipoBobina(null);
        }            

        if(articuloFichaTecnicaForm.getDiametro2() != null && !articuloFichaTecnicaForm.getDiametro2().isEmpty()) {
            articuloFichaTecnicaModel.setDiametro2(Double.valueOf(articuloFichaTecnicaForm.getDiametro2()));
        }            

        if(articuloFichaTecnicaForm.getLargo() != null && !articuloFichaTecnicaForm.getLargo().isEmpty()) {
            articuloFichaTecnicaModel.setLargo(Double.valueOf(articuloFichaTecnicaForm.getLargo()));
        }            

        if(articuloFichaTecnicaForm.getEspesor() != null && !articuloFichaTecnicaForm.getEspesor().isEmpty()) {
            articuloFichaTecnicaModel.setEspesor(Double.valueOf(articuloFichaTecnicaForm.getEspesor()));
        }            

        if(articuloFichaTecnicaForm.getPeso2() != null && !articuloFichaTecnicaForm.getPeso2().isEmpty()) {
            articuloFichaTecnicaModel.setPeso2(Double.valueOf(articuloFichaTecnicaForm.getPeso2()));
        }            
        
        if(articuloFichaTecnicaForm.getIdLineaTintas() != null && !articuloFichaTecnicaForm.getIdLineaTintas().isEmpty() && !articuloFichaTecnicaForm.getIdLineaTintas().equalsIgnoreCase("-1")) {
            articuloFichaTecnicaModel.setIdLineaTintas(Integer.valueOf(articuloFichaTecnicaForm.getIdLineaTintas()));
        } else {
            articuloFichaTecnicaModel.setIdLineaTintas(null);
        }                  

        if(articuloFichaTecnicaForm.getIdLineaSolventes()!= null && !articuloFichaTecnicaForm.getIdLineaSolventes().isEmpty() && !articuloFichaTecnicaForm.getIdLineaSolventes().equalsIgnoreCase("-1")) {
            articuloFichaTecnicaModel.setIdLineaSolventes(Integer.valueOf(articuloFichaTecnicaForm.getIdLineaSolventes()));
        } else {
            articuloFichaTecnicaModel.setIdLineaSolventes(null);
        }            

        if(articuloFichaTecnicaForm.getIdBultosEn() != null && !articuloFichaTecnicaForm.getIdBultosEn().isEmpty() && !articuloFichaTecnicaForm.getIdBultosEn().equalsIgnoreCase("-1")) {
            articuloFichaTecnicaModel.setIdBultosEn(Integer.valueOf(articuloFichaTecnicaForm.getIdBultosEn()));
        } else {
            articuloFichaTecnicaModel.setIdBultosEn(null);
        }            

        if(articuloFichaTecnicaForm.getBultosPorPalet() != null && !articuloFichaTecnicaForm.getBultosPorPalet().isEmpty()) {
            articuloFichaTecnicaModel.setBultosPorPalet(Double.valueOf(articuloFichaTecnicaForm.getBultosPorPalet()));
        } else {
            articuloFichaTecnicaModel.setBultosPorPalet(null);
        }            

        if(articuloFichaTecnicaForm.getIdPalet() != null && !articuloFichaTecnicaForm.getIdPalet().isEmpty() && !articuloFichaTecnicaForm.getIdPalet().equalsIgnoreCase("-1")) {
            articuloFichaTecnicaModel.setIdPalet(Integer.valueOf(articuloFichaTecnicaForm.getIdPalet()));
        } else {
            articuloFichaTecnicaModel.setIdPalet(null);
        }            

        if(articuloFichaTecnicaForm.getPosicionPalet() != null && !articuloFichaTecnicaForm.getPosicionPalet().isEmpty() && !articuloFichaTecnicaForm.getPosicionPalet().equalsIgnoreCase("-1")) {
            articuloFichaTecnicaModel.setPosicionPalet(articuloFichaTecnicaForm.getPosicionPalet());
        } else {
            articuloFichaTecnicaModel.setPosicionPalet(null);
        }            
        
        //FORMULACION
        //CAPA A
        if(articuloFichaTecnicaForm.getIdMateriaPrima1CapaA() != null && !articuloFichaTecnicaForm.getIdMateriaPrima1CapaA().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima1CapaA().equalsIgnoreCase("-1")) {
            articuloFichaTecnicaModel.setIdMateriaPrima1CapaA(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima1CapaA()));
        } else {
            articuloFichaTecnicaModel.setIdMateriaPrima1CapaA(null);
        }
        if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage1CapaA() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage1CapaA().isEmpty()) {
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage1CapaA(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage1CapaA()));
        } else {
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage1CapaA(null);
        }
        if(articuloFichaTecnicaForm.getIdMateriaPrima2CapaA() != null && !articuloFichaTecnicaForm.getIdMateriaPrima2CapaA().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima2CapaA().equalsIgnoreCase("-1")) {
            articuloFichaTecnicaModel.setIdMateriaPrima2CapaA(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima2CapaA()));
        } else {
            articuloFichaTecnicaModel.setIdMateriaPrima2CapaA(null);
        }
        if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage2CapaA() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage2CapaA().isEmpty()) {
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage2CapaA(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage2CapaA()));
        } else {
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage2CapaA(null);
        }
        if(articuloFichaTecnicaForm.getIdMateriaPrima3CapaA() != null && !articuloFichaTecnicaForm.getIdMateriaPrima3CapaA().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima3CapaA().equalsIgnoreCase("-1")) {
            articuloFichaTecnicaModel.setIdMateriaPrima3CapaA(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima3CapaA()));
        } else {
            articuloFichaTecnicaModel.setIdMateriaPrima3CapaA(null);
        }
        if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage3CapaA() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage3CapaA().isEmpty()) {
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage3CapaA(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage3CapaA()));
        } else {
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage3CapaA(null);
        }
        if(articuloFichaTecnicaForm.getIdMateriaPrima4CapaA() != null && !articuloFichaTecnicaForm.getIdMateriaPrima4CapaA().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima4CapaA().equalsIgnoreCase("-1")) {
            articuloFichaTecnicaModel.setIdMateriaPrima4CapaA(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima4CapaA()));
        } else {
            articuloFichaTecnicaModel.setIdMateriaPrima4CapaA(null);
        }
        if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage4CapaA() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage4CapaA().isEmpty()) {
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage4CapaA(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage4CapaA()));
        } else {
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage4CapaA(null);
        }
        if(articuloFichaTecnicaForm.getIdMateriaPrima5CapaA() != null && !articuloFichaTecnicaForm.getIdMateriaPrima5CapaA().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima5CapaA().equalsIgnoreCase("-1")) {
            articuloFichaTecnicaModel.setIdMateriaPrima5CapaA(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima5CapaA()));
        } else {
            articuloFichaTecnicaModel.setIdMateriaPrima5CapaA(null);
        }
        if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage5CapaA() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage5CapaA().isEmpty()) {
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage5CapaA(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage5CapaA()));
        } else {
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage5CapaA(null);
        }
        if(articuloFichaTecnicaForm.getIdMateriaPrima6CapaA() != null && !articuloFichaTecnicaForm.getIdMateriaPrima6CapaA().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima6CapaA().equalsIgnoreCase("-1")) {
            articuloFichaTecnicaModel.setIdMateriaPrima6CapaA(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima6CapaA()));
        } else {
            articuloFichaTecnicaModel.setIdMateriaPrima6CapaA(null);
        }
        if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage6CapaA() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage6CapaA().isEmpty()) {
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage6CapaA(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage6CapaA()));
        } else {
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage6CapaA(null);
        }
        
        if(cantidadCapas > 1) {
            if(articuloFichaTecnicaForm.getIdMateriaPrima1CapaB() != null && !articuloFichaTecnicaForm.getIdMateriaPrima1CapaB().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima1CapaB().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima1CapaB(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima1CapaB()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima1CapaB(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage1CapaB() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage1CapaB().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage1CapaB(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage1CapaB()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage1CapaB(null);
            }
            if(articuloFichaTecnicaForm.getIdMateriaPrima2CapaB() != null && !articuloFichaTecnicaForm.getIdMateriaPrima2CapaB().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima2CapaB().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima2CapaB(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima2CapaB()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima2CapaB(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage2CapaB() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage2CapaB().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage2CapaB(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage2CapaB()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage2CapaB(null);
            }
            if(articuloFichaTecnicaForm.getIdMateriaPrima3CapaB() != null && !articuloFichaTecnicaForm.getIdMateriaPrima3CapaB().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima3CapaB().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima3CapaB(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima3CapaB()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima3CapaB(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage3CapaB() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage3CapaB().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage3CapaB(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage3CapaB()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage3CapaB(null);
            }
            if(articuloFichaTecnicaForm.getIdMateriaPrima4CapaB() != null && !articuloFichaTecnicaForm.getIdMateriaPrima4CapaB().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima4CapaB().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima4CapaB(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima4CapaB()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima4CapaB(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage4CapaB() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage4CapaB().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage4CapaB(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage4CapaB()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage4CapaB(null);
            }
            if(articuloFichaTecnicaForm.getIdMateriaPrima5CapaB() != null && !articuloFichaTecnicaForm.getIdMateriaPrima5CapaB().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima5CapaB().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima5CapaB(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima5CapaB()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima5CapaB(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage5CapaB() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage5CapaB().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage5CapaB(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage5CapaB()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage5CapaB(null);
            }
            if(articuloFichaTecnicaForm.getIdMateriaPrima6CapaB() != null && !articuloFichaTecnicaForm.getIdMateriaPrima6CapaB().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima6CapaB().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima6CapaB(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima6CapaB()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima6CapaB(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage6CapaB() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage6CapaB().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage6CapaB(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage6CapaB()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage6CapaB(null);
            }            
        } else {
            articuloFichaTecnicaModel.setIdMateriaPrima1CapaB(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage1CapaB(null);            
            articuloFichaTecnicaModel.setIdMateriaPrima2CapaB(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage2CapaB(null);            
            articuloFichaTecnicaModel.setIdMateriaPrima3CapaB(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage3CapaB(null);            
            articuloFichaTecnicaModel.setIdMateriaPrima4CapaB(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage4CapaB(null);            
            articuloFichaTecnicaModel.setIdMateriaPrima5CapaB(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage5CapaB(null);            
            articuloFichaTecnicaModel.setIdMateriaPrima6CapaB(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage6CapaB(null);
        }
        
        if(cantidadCapas > 2) {
            if(articuloFichaTecnicaForm.getIdMateriaPrima1CapaC() != null && !articuloFichaTecnicaForm.getIdMateriaPrima1CapaC().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima1CapaC().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima1CapaC(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima1CapaC()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima1CapaC(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage1CapaC() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage1CapaC().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage1CapaC(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage1CapaC()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage1CapaC(null);
            }
            if(articuloFichaTecnicaForm.getIdMateriaPrima2CapaC() != null && !articuloFichaTecnicaForm.getIdMateriaPrima2CapaC().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima2CapaC().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima2CapaC(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima2CapaC()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima2CapaC(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage2CapaC() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage2CapaC().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage2CapaC(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage2CapaC()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage2CapaC(null);
            }
            if(articuloFichaTecnicaForm.getIdMateriaPrima3CapaC() != null && !articuloFichaTecnicaForm.getIdMateriaPrima3CapaC().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima3CapaC().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima3CapaC(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima3CapaC()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima3CapaC(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage3CapaC() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage3CapaC().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage3CapaC(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage3CapaC()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage3CapaC(null);
            }
            if(articuloFichaTecnicaForm.getIdMateriaPrima4CapaC() != null && !articuloFichaTecnicaForm.getIdMateriaPrima4CapaC().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima4CapaC().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima4CapaC(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima4CapaC()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima4CapaC(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage4CapaC() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage4CapaC().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage4CapaC(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage4CapaC()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage4CapaC(null);
            }
            if(articuloFichaTecnicaForm.getIdMateriaPrima5CapaC() != null && !articuloFichaTecnicaForm.getIdMateriaPrima5CapaC().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima5CapaC().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima5CapaC(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima5CapaC()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima5CapaC(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage5CapaC() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage5CapaC().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage5CapaC(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage5CapaC()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage5CapaC(null);
            }
            if(articuloFichaTecnicaForm.getIdMateriaPrima6CapaC() != null && !articuloFichaTecnicaForm.getIdMateriaPrima6CapaC().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima6CapaC().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima6CapaC(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima6CapaC()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima6CapaC(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage6CapaC() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage6CapaC().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage6CapaC(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage6CapaC()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage6CapaC(null);
            }            
        } else {
            articuloFichaTecnicaModel.setIdMateriaPrima1CapaC(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage1CapaC(null);            
            articuloFichaTecnicaModel.setIdMateriaPrima2CapaC(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage2CapaC(null);            
            articuloFichaTecnicaModel.setIdMateriaPrima3CapaC(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage3CapaC(null);            
            articuloFichaTecnicaModel.setIdMateriaPrima4CapaC(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage4CapaC(null);            
            articuloFichaTecnicaModel.setIdMateriaPrima5CapaC(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage5CapaC(null);            
            articuloFichaTecnicaModel.setIdMateriaPrima6CapaC(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage6CapaC(null);            
        }
        
        if(cantidadCapas > 3) {
            if(articuloFichaTecnicaForm.getIdMateriaPrima1CapaD() != null && !articuloFichaTecnicaForm.getIdMateriaPrima1CapaD().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima1CapaD().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima1CapaD(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima1CapaD()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima1CapaD(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage1CapaD() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage1CapaD().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage1CapaD(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage1CapaD()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage1CapaD(null);
            }
            if(articuloFichaTecnicaForm.getIdMateriaPrima2CapaD() != null && !articuloFichaTecnicaForm.getIdMateriaPrima2CapaD().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima2CapaD().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima2CapaD(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima2CapaD()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima2CapaD(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage2CapaD() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage2CapaD().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage2CapaD(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage2CapaD()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage2CapaD(null);
            }
            if(articuloFichaTecnicaForm.getIdMateriaPrima3CapaD() != null && !articuloFichaTecnicaForm.getIdMateriaPrima3CapaD().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima3CapaD().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima3CapaD(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima3CapaD()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima3CapaD(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage3CapaD() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage3CapaD().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage3CapaD(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage3CapaD()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage3CapaD(null);
            }
            if(articuloFichaTecnicaForm.getIdMateriaPrima4CapaD() != null && !articuloFichaTecnicaForm.getIdMateriaPrima4CapaD().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima4CapaD().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima4CapaD(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima4CapaD()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima4CapaD(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage4CapaD() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage4CapaD().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage4CapaD(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage4CapaD()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage4CapaD(null);
            }
            if(articuloFichaTecnicaForm.getIdMateriaPrima5CapaD() != null && !articuloFichaTecnicaForm.getIdMateriaPrima5CapaD().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima5CapaD().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima5CapaD(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima5CapaD()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima5CapaD(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage5CapaD() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage5CapaD().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage5CapaD(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage5CapaD()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage5CapaD(null);
            }
            if(articuloFichaTecnicaForm.getIdMateriaPrima6CapaD() != null && !articuloFichaTecnicaForm.getIdMateriaPrima6CapaD().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima6CapaD().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima6CapaD(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima6CapaD()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima6CapaD(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage6CapaD() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage6CapaD().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage6CapaD(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage6CapaD()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage6CapaD(null);
            }            
        } else {
            articuloFichaTecnicaModel.setIdMateriaPrima1CapaD(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage1CapaD(null);            
            articuloFichaTecnicaModel.setIdMateriaPrima2CapaD(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage2CapaD(null);            
            articuloFichaTecnicaModel.setIdMateriaPrima3CapaD(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage3CapaD(null);            
            articuloFichaTecnicaModel.setIdMateriaPrima4CapaD(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage4CapaD(null);            
            articuloFichaTecnicaModel.setIdMateriaPrima5CapaD(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage5CapaD(null);            
            articuloFichaTecnicaModel.setIdMateriaPrima6CapaD(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage6CapaD(null);                        
        }
                
        if(cantidadCapas > 4) {
            if(articuloFichaTecnicaForm.getIdMateriaPrima1CapaE() != null && !articuloFichaTecnicaForm.getIdMateriaPrima1CapaE().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima1CapaE().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima1CapaE(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima1CapaE()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima1CapaE(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage1CapaE() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage1CapaE().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage1CapaE(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage1CapaE()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage1CapaE(null);
            }
            if(articuloFichaTecnicaForm.getIdMateriaPrima2CapaE() != null && !articuloFichaTecnicaForm.getIdMateriaPrima2CapaE().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima2CapaE().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima2CapaE(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima2CapaE()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima2CapaE(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage2CapaE() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage2CapaE().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage2CapaE(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage2CapaE()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage2CapaE(null);
            }
            if(articuloFichaTecnicaForm.getIdMateriaPrima3CapaE() != null && !articuloFichaTecnicaForm.getIdMateriaPrima3CapaE().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima3CapaE().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima3CapaE(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima3CapaE()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima3CapaE(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage3CapaE() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage3CapaE().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage3CapaE(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage3CapaE()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage3CapaE(null);
            }
            if(articuloFichaTecnicaForm.getIdMateriaPrima4CapaE() != null && !articuloFichaTecnicaForm.getIdMateriaPrima4CapaE().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima4CapaE().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima4CapaE(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima4CapaE()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima4CapaE(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage4CapaE() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage4CapaE().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage4CapaE(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage4CapaE()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage4CapaE(null);
            }
            if(articuloFichaTecnicaForm.getIdMateriaPrima5CapaE() != null && !articuloFichaTecnicaForm.getIdMateriaPrima5CapaE().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima5CapaE().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima5CapaE(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima5CapaE()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima5CapaE(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage5CapaE() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage5CapaE().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage5CapaE(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage5CapaE()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage5CapaE(null);
            }
            if(articuloFichaTecnicaForm.getIdMateriaPrima6CapaE() != null && !articuloFichaTecnicaForm.getIdMateriaPrima6CapaE().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima6CapaE().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima6CapaE(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima6CapaE()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima6CapaE(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage6CapaE() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage6CapaE().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage6CapaE(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage6CapaE()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage6CapaE(null);
            }            
        } else {
            articuloFichaTecnicaModel.setIdMateriaPrima1CapaE(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage1CapaE(null);            
            articuloFichaTecnicaModel.setIdMateriaPrima2CapaE(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage2CapaE(null);            
            articuloFichaTecnicaModel.setIdMateriaPrima3CapaE(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage3CapaE(null);            
            articuloFichaTecnicaModel.setIdMateriaPrima4CapaE(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage4CapaE(null);            
            articuloFichaTecnicaModel.setIdMateriaPrima5CapaE(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage5CapaE(null);            
            articuloFichaTecnicaModel.setIdMateriaPrima6CapaE(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage6CapaE(null);                                    
        }
        
        if(cantidadCapas > 5) {
            if(articuloFichaTecnicaForm.getIdMateriaPrima1CapaF() != null && !articuloFichaTecnicaForm.getIdMateriaPrima1CapaF().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima1CapaF().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima1CapaF(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima1CapaF()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima1CapaF(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage1CapaF() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage1CapaF().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage1CapaF(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage1CapaF()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage1CapaF(null);
            }
            if(articuloFichaTecnicaForm.getIdMateriaPrima2CapaF() != null && !articuloFichaTecnicaForm.getIdMateriaPrima2CapaF().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima2CapaF().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima2CapaF(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima2CapaF()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima2CapaF(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage2CapaF() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage2CapaF().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage2CapaF(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage2CapaF()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage2CapaF(null);
            }
            if(articuloFichaTecnicaForm.getIdMateriaPrima3CapaF() != null && !articuloFichaTecnicaForm.getIdMateriaPrima3CapaF().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima3CapaF().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima3CapaF(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima3CapaF()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima3CapaF(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage3CapaF() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage3CapaF().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage3CapaF(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage3CapaF()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage3CapaF(null);
            }
            if(articuloFichaTecnicaForm.getIdMateriaPrima4CapaF() != null && !articuloFichaTecnicaForm.getIdMateriaPrima4CapaF().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima4CapaF().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima4CapaF(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima4CapaF()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima4CapaF(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage4CapaF() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage4CapaF().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage4CapaF(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage4CapaF()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage4CapaF(null);
            }
            if(articuloFichaTecnicaForm.getIdMateriaPrima5CapaF() != null && !articuloFichaTecnicaForm.getIdMateriaPrima5CapaF().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima5CapaF().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima5CapaF(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima5CapaF()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima5CapaF(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage5CapaF() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage5CapaF().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage5CapaF(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage5CapaF()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage5CapaF(null);
            }
            if(articuloFichaTecnicaForm.getIdMateriaPrima6CapaF() != null && !articuloFichaTecnicaForm.getIdMateriaPrima6CapaF().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima6CapaF().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima6CapaF(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima6CapaF()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima6CapaF(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage6CapaF() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage6CapaF().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage6CapaF(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage6CapaF()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage6CapaF(null);
            }            
        } else {
            articuloFichaTecnicaModel.setIdMateriaPrima1CapaF(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage1CapaF(null);            
            articuloFichaTecnicaModel.setIdMateriaPrima2CapaF(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage2CapaF(null);            
            articuloFichaTecnicaModel.setIdMateriaPrima3CapaF(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage3CapaF(null);            
            articuloFichaTecnicaModel.setIdMateriaPrima4CapaF(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage4CapaF(null);            
            articuloFichaTecnicaModel.setIdMateriaPrima5CapaF(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage5CapaF(null);            
            articuloFichaTecnicaModel.setIdMateriaPrima6CapaF(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage6CapaF(null);                                                
        }
        
        if(cantidadCapas > 6) {
            if(articuloFichaTecnicaForm.getIdMateriaPrima1CapaG() != null && !articuloFichaTecnicaForm.getIdMateriaPrima1CapaG().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima1CapaG().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima1CapaG(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima1CapaG()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima1CapaG(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage1CapaG() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage1CapaG().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage1CapaG(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage1CapaG()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage1CapaG(null);
            }
            if(articuloFichaTecnicaForm.getIdMateriaPrima2CapaG() != null && !articuloFichaTecnicaForm.getIdMateriaPrima2CapaG().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima2CapaG().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima2CapaG(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima2CapaG()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima2CapaG(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage2CapaG() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage2CapaG().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage2CapaG(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage2CapaG()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage2CapaG(null);
            }
            if(articuloFichaTecnicaForm.getIdMateriaPrima3CapaG() != null && !articuloFichaTecnicaForm.getIdMateriaPrima3CapaG().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima3CapaG().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima3CapaG(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima3CapaG()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima3CapaG(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage3CapaG() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage3CapaG().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage3CapaG(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage3CapaG()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage3CapaG(null);
            }
            if(articuloFichaTecnicaForm.getIdMateriaPrima4CapaG() != null && !articuloFichaTecnicaForm.getIdMateriaPrima4CapaG().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima4CapaG().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima4CapaG(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima4CapaG()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima4CapaG(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage4CapaG() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage4CapaG().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage4CapaG(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage4CapaG()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage4CapaG(null);
            }
            if(articuloFichaTecnicaForm.getIdMateriaPrima5CapaG() != null && !articuloFichaTecnicaForm.getIdMateriaPrima5CapaG().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima5CapaG().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima5CapaG(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima5CapaG()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima5CapaG(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage5CapaG() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage5CapaG().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage5CapaG(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage5CapaG()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage5CapaG(null);
            }
            if(articuloFichaTecnicaForm.getIdMateriaPrima6CapaG() != null && !articuloFichaTecnicaForm.getIdMateriaPrima6CapaG().isEmpty() && !articuloFichaTecnicaForm.getIdMateriaPrima6CapaG().equalsIgnoreCase("-1")) {
                articuloFichaTecnicaModel.setIdMateriaPrima6CapaG(Integer.valueOf(articuloFichaTecnicaForm.getIdMateriaPrima6CapaG()));
            } else {
                articuloFichaTecnicaModel.setIdMateriaPrima6CapaG(null);
            }
            if(articuloFichaTecnicaForm.getMateriaPrimaPorcentage6CapaG() != null && !articuloFichaTecnicaForm.getMateriaPrimaPorcentage6CapaG().isEmpty()) {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage6CapaG(Double.valueOf(articuloFichaTecnicaForm.getMateriaPrimaPorcentage6CapaG()));
            } else {
                articuloFichaTecnicaModel.setMateriaPrimaPorcentage6CapaG(null);
            }            
        } else {
            articuloFichaTecnicaModel.setIdMateriaPrima1CapaG(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage1CapaG(null);            
            articuloFichaTecnicaModel.setIdMateriaPrima2CapaG(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage2CapaG(null);            
            articuloFichaTecnicaModel.setIdMateriaPrima3CapaG(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage3CapaG(null);            
            articuloFichaTecnicaModel.setIdMateriaPrima4CapaG(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage4CapaG(null);            
            articuloFichaTecnicaModel.setIdMateriaPrima5CapaG(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage5CapaG(null);            
            articuloFichaTecnicaModel.setIdMateriaPrima6CapaG(null);
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage6CapaG(null);                                                
        }

        //RESUMEN CAPAS
        if(articuloFichaTecnicaForm.getResumenCapaA() != null && !articuloFichaTecnicaForm.getResumenCapaA().isEmpty()) {
            articuloFichaTecnicaModel.setResumenCapaA(Double.valueOf(articuloFichaTecnicaForm.getResumenCapaA()));
        } else {
            articuloFichaTecnicaModel.setResumenCapaA(null);
        }            
        
        if(cantidadCapas > 1) {
            if(articuloFichaTecnicaForm.getResumenCapaB() != null && !articuloFichaTecnicaForm.getResumenCapaB().isEmpty()) {
                articuloFichaTecnicaModel.setResumenCapaB(Double.valueOf(articuloFichaTecnicaForm.getResumenCapaB()));
            } else {
                articuloFichaTecnicaModel.setResumenCapaB(null);
            }            
        } else {
            articuloFichaTecnicaModel.setResumenCapaB(null);
        }

        if(cantidadCapas > 2) {
            if(articuloFichaTecnicaForm.getResumenCapaC() != null && !articuloFichaTecnicaForm.getResumenCapaC().isEmpty()) {
                articuloFichaTecnicaModel.setResumenCapaC(Double.valueOf(articuloFichaTecnicaForm.getResumenCapaC()));
            } else {
                articuloFichaTecnicaModel.setResumenCapaC(null);
            }            
        } else {
            articuloFichaTecnicaModel.setResumenCapaC(null);
        }

        if(cantidadCapas > 3) {
            if(articuloFichaTecnicaForm.getResumenCapaD() != null && !articuloFichaTecnicaForm.getResumenCapaD().isEmpty()) {
                articuloFichaTecnicaModel.setResumenCapaD(Double.valueOf(articuloFichaTecnicaForm.getResumenCapaD()));
            } else {
                articuloFichaTecnicaModel.setResumenCapaD(null);
            }            
        } else {
            articuloFichaTecnicaModel.setResumenCapaD(null);
        }

        if(cantidadCapas > 4) {
            if(articuloFichaTecnicaForm.getResumenCapaE() != null && !articuloFichaTecnicaForm.getResumenCapaE().isEmpty()) {
                articuloFichaTecnicaModel.setResumenCapaE(Double.valueOf(articuloFichaTecnicaForm.getResumenCapaE()));
            } else {
                articuloFichaTecnicaModel.setResumenCapaE(null);
            }            
        } else {
            articuloFichaTecnicaModel.setResumenCapaE(null);
        }

        if(cantidadCapas > 5) {
            if(articuloFichaTecnicaForm.getResumenCapaF() != null && !articuloFichaTecnicaForm.getResumenCapaF().isEmpty()) {
                articuloFichaTecnicaModel.setResumenCapaF(Double.valueOf(articuloFichaTecnicaForm.getResumenCapaF()));
            } else {
                articuloFichaTecnicaModel.setResumenCapaF(null);
            }            
        } else {
            articuloFichaTecnicaModel.setResumenCapaF(null);
        }

        if(cantidadCapas > 6) {
            if(articuloFichaTecnicaForm.getResumenCapaG() != null && !articuloFichaTecnicaForm.getResumenCapaG().isEmpty()) {
                articuloFichaTecnicaModel.setResumenCapaG(Double.valueOf(articuloFichaTecnicaForm.getResumenCapaG()));
            } else {
                articuloFichaTecnicaModel.setResumenCapaG(null);
            }            
        } else {
            articuloFichaTecnicaModel.setResumenCapaG(null);
        }
        
        if(articuloFichaTecnicaForm.getAction().equalsIgnoreCase("add") || articuloFichaTecnicaForm.getAction().equalsIgnoreCase("edit")) {
            articuloFichaTecnicaService.save(articuloFichaTecnicaModel);
            
            if(articuloFichaTecnicaForm.getAction().equalsIgnoreCase("add")) {
                Double version = (articuloFichaTecnicaModel.getId() + 10) / 10.0;
                articuloFichaTecnicaModel.setVersion(version);
                
                articuloFichaTecnicaService.save(articuloFichaTecnicaModel);
            } 
        } else {
            if(articuloFichaTecnicaForm.getAction().equalsIgnoreCase("remove")) {
                if(articuloFichaTecnicaModel.getId() == null) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: id de ficha técnica de artículo inválido.");
                    return modelAndView;                                    
                }
                
                articuloFichaTecnicaService.delete(articuloFichaTecnicaModel);
            } else {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: operación inválida.");
                return modelAndView;                                
            }
        }
                        
        modelAndView.setViewName("redirect:/articulofichatecnica/"+articulo.getId());

        return modelAndView; 
    }

    @RequestMapping(value = "/articulofichatecnica/edit/{articuloFichaTecnicapk}", method = RequestMethod.GET)
    public String editArticulo(@PathVariable String articuloFichaTecnicapk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(articuloFichaTecnicapk == null) {
            model.addAttribute("errorMessage", "Error: Ficha Técnica de Articulo inválido");         
            return "/error";                
        }
        
        ArticuloFichaTecnicaService articuloFichaTecnicaService = new ArticuloFichaTecnicaServiceImpl();   
        ArticuloFichaTecnicaModel articuloFichaTecnica = articuloFichaTecnicaService.getByPk(Integer.valueOf(articuloFichaTecnicapk));
        if(articuloFichaTecnica == null) {
            model.addAttribute("errorMessage", "Error: Ficha Técnica de Articulo inválido. No ha sido encontrado.");         
            return "/error";    
        }
        
        ArticuloService articuloService = new ArticuloServiceImpl();   
        ArticuloModel articulo = articuloService.getByPk(articuloFichaTecnica.getIdArticulo());
        
        if(articulo == null) {            
            model.addAttribute("errorMessage", "Error: Articulo inválido. No ha sido encontrado.");         
            return "/error";     
        }
        
        ClienteService clienteService = new ClienteServiceImpl();   
        ClienteModel cliente = clienteService.getByPk(articulo.getIdCliente());

        UserService userService = new UserServiceImpl();   
        UserModel user = null;
        if(articuloFichaTecnica.getIdUsuarioModificacion() != null) {
            user = userService.getUserById(articuloFichaTecnica.getIdUsuarioModificacion());                
        }
                
        ArticuloFichaTecnicaForm articuloFichaTecnicaForm = new ArticuloFichaTecnicaForm();
        articuloFichaTecnicaForm.setPk(articuloFichaTecnica.getId().toString());
        articuloFichaTecnicaForm.setIdArticulo(articulo.getId().toString());
        articuloFichaTecnicaForm.setIdCliente(cliente.getId().toString());
        articuloFichaTecnicaForm.setCliente(cliente.getRazonSocial());
        if(user != null) {
            articuloFichaTecnicaForm.setIdUsuarioModificacion(user.getId().toString());
            articuloFichaTecnicaForm.setUsuarioModificacion(user.getUsername());
        }
        if(articuloFichaTecnica.getFechaModificacion() != null) {
            articuloFichaTecnicaForm.setFechaModificacion(articuloFichaTecnica.getFechaModificacion().toString().replace(" 00:00:00.0", ""));
        }
        articuloFichaTecnicaForm.setVersion(articuloFichaTecnica.getVersion().toString());
        if(articuloFichaTecnica.getFechaAlta() != null) {
            articuloFichaTecnicaForm.setFechaAlta(articulo.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(articuloFichaTecnica.getUrlEspecificacion() != null) {
            articuloFichaTecnicaForm.setUrlFichaTecnica(articuloFichaTecnica.getUrlEspecificacion().toString());
        }                
        if(articuloFichaTecnica.getEspecificacion() != null) {
            articuloFichaTecnicaForm.setEspecificacion(articuloFichaTecnica.getEspecificacion().toString());
        }                        
        if(articuloFichaTecnica.getObservaciones() != null) {
            articuloFichaTecnicaForm.setObservaciones(articuloFichaTecnica.getObservaciones());
        }        
        if(articuloFichaTecnica.getIdMaterial() != null) {
            articuloFichaTecnicaForm.setIdMaterial(articuloFichaTecnica.getIdMaterial().toString());
        }
        if(articuloFichaTecnica.getLamina() != null) {
            if(articuloFichaTecnica.getLamina()) {
                articuloFichaTecnicaForm.setLamina("1");
            } else {
                articuloFichaTecnicaForm.setLamina("0");
            }
        } else {
            articuloFichaTecnicaForm.setLamina("0");
        }
        if(articuloFichaTecnica.getBobina() != null) {
            if(articuloFichaTecnica.getBobina()) {
                articuloFichaTecnicaForm.setBobina("1");
            } else {
                articuloFichaTecnicaForm.setBobina("0");
            }
        } else {
            articuloFichaTecnicaForm.setBobina("0");
        }
        if(articuloFichaTecnica.getCantidadCapas() != null) {            
            articuloFichaTecnicaForm.setCantidadCapas(articuloFichaTecnica.getCantidadCapas().toString());
        }
        if(articuloFichaTecnica.getIdColorA() != null) {
            articuloFichaTecnicaForm.setColorA(articuloFichaTecnica.getIdColorA().toString());
        }
        if(articuloFichaTecnica.getIdColorB() != null) {
            articuloFichaTecnicaForm.setColorB(articuloFichaTecnica.getIdColorB().toString());
        }
        if(articuloFichaTecnica.getIdColorC() != null) {
            articuloFichaTecnicaForm.setColorC(articuloFichaTecnica.getIdColorC().toString());
        }
        if(articuloFichaTecnica.getIdColorD() != null) {
            articuloFichaTecnicaForm.setColorD(articuloFichaTecnica.getIdColorD().toString());
        }
        if(articuloFichaTecnica.getIdColorE() != null) {
            articuloFichaTecnicaForm.setColorE(articuloFichaTecnica.getIdColorE().toString());
        }
        if(articuloFichaTecnica.getIdColorF() != null) {
            articuloFichaTecnicaForm.setColorF(articuloFichaTecnica.getIdColorF().toString());
        }
        if(articuloFichaTecnica.getIdColorG() != null) {
            articuloFichaTecnicaForm.setColorG(articuloFichaTecnica.getIdColorG().toString());
        }
        if(articuloFichaTecnica.getAncho() != null) {
            articuloFichaTecnicaForm.setAncho(articuloFichaTecnica.getAncho().toString());
        }
        if(articuloFichaTecnica.getFuelleIzquierdo() != null) {
            articuloFichaTecnicaForm.setFuelleIzquierdo(articuloFichaTecnica.getFuelleIzquierdo().toString());
        }
        if(articuloFichaTecnica.getFuelleDerecho() != null) {
            articuloFichaTecnicaForm.setFuelleDerecho(articuloFichaTecnica.getFuelleDerecho().toString());
        }
        if(articuloFichaTecnica.getEspesorNominal() != null) {
            articuloFichaTecnicaForm.setEspesorNominal(articuloFichaTecnica.getEspesorNominal().toString());
        }
        if(articuloFichaTecnica.getEspesorPromedio() != null) {
            articuloFichaTecnicaForm.setEspesorPromedio(articuloFichaTecnica.getEspesorPromedio().toString());
        }
        if(articuloFichaTecnica.getEspesorArticulo() != null) {
            articuloFichaTecnicaForm.setEspesorArticulo(articuloFichaTecnica.getEspesorArticulo().toString());
        }
        if(articuloFichaTecnica.getPesoEspecifico() != null) {
            articuloFichaTecnicaForm.setPesoEspecifico(articuloFichaTecnica.getPesoEspecifico().toString());
        }
        if(articuloFichaTecnica.getAnchoBruto() != null) {
            articuloFichaTecnicaForm.setAnchoBruto(articuloFichaTecnica.getAnchoBruto().toString());
        }
        if(articuloFichaTecnica.getBobinadoBarras() != null) {
            articuloFichaTecnicaForm.setBobinadoBarras(articuloFichaTecnica.getBobinadoBarras().toString());
        }
        if(articuloFichaTecnica.getMetros() != null) {
            articuloFichaTecnicaForm.setMetros(articuloFichaTecnica.getMetros().toString());
        }
        if(articuloFichaTecnica.getPeso() != null) {
            articuloFichaTecnicaForm.setPeso(articuloFichaTecnica.getPeso().toString());
        }
        if(articuloFichaTecnica.getDiametro() != null) {
            articuloFichaTecnicaForm.setDiametro(articuloFichaTecnica.getDiametro().toString());
        }
        if(articuloFichaTecnica.getEmpalmes() != null) {
            articuloFichaTecnicaForm.setEmpalmes(articuloFichaTecnica.getEmpalmes().toString());
        }
        if(articuloFichaTecnica.getIdTipoBobina() != null) {
            articuloFichaTecnicaForm.setIdTipoBobina(articuloFichaTecnica.getIdTipoBobina().toString());
        }
        if(articuloFichaTecnica.getDiametro2() != null) {
            articuloFichaTecnicaForm.setDiametro2(articuloFichaTecnica.getDiametro2().toString());
        }
        if(articuloFichaTecnica.getLargo() != null) {
            articuloFichaTecnicaForm.setLargo(articuloFichaTecnica.getLargo().toString());
        }
        if(articuloFichaTecnica.getEspesor() != null) {
            articuloFichaTecnicaForm.setEspesor(articuloFichaTecnica.getEspesor().toString());
        }
        if(articuloFichaTecnica.getPeso2() != null) {
            articuloFichaTecnicaForm.setPeso2(articuloFichaTecnica.getPeso2().toString());
        }
        if(articuloFichaTecnica.getIdLineaTintas() != null) {
            articuloFichaTecnicaForm.setIdLineaTintas(articuloFichaTecnica.getIdLineaTintas().toString());
        }
        if(articuloFichaTecnica.getIdLineaSolventes() != null) {
            articuloFichaTecnicaForm.setIdLineaSolventes(articuloFichaTecnica.getIdLineaSolventes().toString());
        }
        if(articuloFichaTecnica.getIdBultosEn() != null) {
            articuloFichaTecnicaForm.setIdBultosEn(articuloFichaTecnica.getIdBultosEn().toString());
        }
        if(articuloFichaTecnica.getBultosPorPalet() != null) {
            articuloFichaTecnicaForm.setBultosPorPalet(articuloFichaTecnica.getBultosPorPalet().toString());
        }
        if(articuloFichaTecnica.getIdPalet() != null) {
            articuloFichaTecnicaForm.setIdPalet(articuloFichaTecnica.getIdPalet().toString());
        }
        if(articuloFichaTecnica.getIdPalet() != null) {
            articuloFichaTecnicaForm.setIdPalet(articuloFichaTecnica.getIdPalet().toString());
        }
        if(articuloFichaTecnica.getPosicionPalet() != null) {
            articuloFichaTecnicaForm.setPosicionPalet(articuloFichaTecnica.getPosicionPalet().toString());
        }
        
        MateriaPrimaService materiaPrimaService = new MateriaPrimaServiceImpl();
        Map<String,String> materiasPrima = new LinkedHashMap<String,String>();
        List<MateriaPrimaModel> materiasPrimaModel = materiaPrimaService.getAll();        
        
        if(materiasPrimaModel != null && !materiasPrimaModel.isEmpty()){
            for(MateriaPrimaModel materiaPrimaModel :materiasPrimaModel) {
                materiasPrima.put(materiaPrimaModel.getId().toString(), materiaPrimaModel.getDescripcion());
            }
        }        
        
        //CAPA A
        if(articuloFichaTecnica.getIdMateriaPrima1CapaA() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima1CapaA(articuloFichaTecnica.getIdMateriaPrima1CapaA().toString());
            articuloFichaTecnicaForm.setMateriaPrima1CapaA(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima1CapaA().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaA() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage1CapaA(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaA().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima2CapaA() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima2CapaA(articuloFichaTecnica.getIdMateriaPrima2CapaA().toString());
            articuloFichaTecnicaForm.setMateriaPrima2CapaA(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima2CapaA().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaA() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage2CapaA(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaA().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima3CapaA() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima3CapaA(articuloFichaTecnica.getIdMateriaPrima3CapaA().toString());
            articuloFichaTecnicaForm.setMateriaPrima3CapaA(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima3CapaA().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaA() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage3CapaA(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaA().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima4CapaA() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima4CapaA(articuloFichaTecnica.getIdMateriaPrima4CapaA().toString());
            articuloFichaTecnicaForm.setMateriaPrima4CapaA(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima4CapaA().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaA() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage4CapaA(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaA().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima5CapaA() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima5CapaA(articuloFichaTecnica.getIdMateriaPrima5CapaA().toString());
            articuloFichaTecnicaForm.setMateriaPrima5CapaA(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima5CapaA().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaA() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage5CapaA(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaA().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima6CapaA() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima6CapaA(articuloFichaTecnica.getIdMateriaPrima6CapaA().toString());
            articuloFichaTecnicaForm.setMateriaPrima6CapaA(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima6CapaA().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaA() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage6CapaA(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaA().toString());
        }

        //CAPA B
        if(articuloFichaTecnica.getIdMateriaPrima1CapaB() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima1CapaB(articuloFichaTecnica.getIdMateriaPrima1CapaB().toString());
            articuloFichaTecnicaForm.setMateriaPrima1CapaB(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima1CapaB().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaB() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage1CapaB(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaB().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima2CapaB() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima2CapaB(articuloFichaTecnica.getIdMateriaPrima2CapaB().toString());
            articuloFichaTecnicaForm.setMateriaPrima2CapaB(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima2CapaB().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaB() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage2CapaB(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaB().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima3CapaB() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima3CapaB(articuloFichaTecnica.getIdMateriaPrima3CapaB().toString());
            articuloFichaTecnicaForm.setMateriaPrima3CapaB(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima3CapaB().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaB() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage3CapaB(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaB().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima4CapaB() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima4CapaB(articuloFichaTecnica.getIdMateriaPrima4CapaB().toString());
            articuloFichaTecnicaForm.setMateriaPrima4CapaB(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima4CapaB().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaB() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage4CapaB(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaB().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima5CapaB() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima5CapaB(articuloFichaTecnica.getIdMateriaPrima5CapaB().toString());
            articuloFichaTecnicaForm.setMateriaPrima5CapaB(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima5CapaB().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaB() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage5CapaB(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaB().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima6CapaB() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima6CapaB(articuloFichaTecnica.getIdMateriaPrima6CapaB().toString());
            articuloFichaTecnicaForm.setMateriaPrima6CapaB(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima6CapaB().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaB() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage6CapaB(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaB().toString());
        }

        //CAPA C
        if(articuloFichaTecnica.getIdMateriaPrima1CapaC() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima1CapaC(articuloFichaTecnica.getIdMateriaPrima1CapaC().toString());
            articuloFichaTecnicaForm.setMateriaPrima1CapaC(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima1CapaC().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaC() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage1CapaC(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaC().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima2CapaC() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima2CapaC(articuloFichaTecnica.getIdMateriaPrima2CapaC().toString());
            articuloFichaTecnicaForm.setMateriaPrima2CapaC(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima2CapaC().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaC() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage2CapaC(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaC().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima3CapaC() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima3CapaC(articuloFichaTecnica.getIdMateriaPrima3CapaC().toString());
            articuloFichaTecnicaForm.setMateriaPrima3CapaC(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima3CapaC().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaC() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage3CapaC(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaC().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima4CapaC() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima4CapaC(articuloFichaTecnica.getIdMateriaPrima4CapaC().toString());
            articuloFichaTecnicaForm.setMateriaPrima4CapaC(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima4CapaC().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaC() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage4CapaC(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaC().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima5CapaC() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima5CapaC(articuloFichaTecnica.getIdMateriaPrima5CapaC().toString());
            articuloFichaTecnicaForm.setMateriaPrima5CapaC(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima5CapaC().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaC() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage5CapaC(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaC().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima6CapaC() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima6CapaC(articuloFichaTecnica.getIdMateriaPrima6CapaC().toString());
            articuloFichaTecnicaForm.setMateriaPrima6CapaC(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima6CapaC().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaC() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage6CapaC(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaC().toString());
        }
        
        //CAPA D
        if(articuloFichaTecnica.getIdMateriaPrima1CapaD() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima1CapaD(articuloFichaTecnica.getIdMateriaPrima1CapaD().toString());
            articuloFichaTecnicaForm.setMateriaPrima1CapaD(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima1CapaD().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaD() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage1CapaD(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaD().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima2CapaD() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima2CapaD(articuloFichaTecnica.getIdMateriaPrima2CapaD().toString());
            articuloFichaTecnicaForm.setMateriaPrima2CapaD(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima2CapaD().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaD() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage2CapaD(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaD().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima3CapaD() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima3CapaD(articuloFichaTecnica.getIdMateriaPrima3CapaD().toString());
            articuloFichaTecnicaForm.setMateriaPrima3CapaD(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima3CapaD().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaD() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage3CapaD(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaD().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima4CapaD() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima4CapaD(articuloFichaTecnica.getIdMateriaPrima4CapaD().toString());
            articuloFichaTecnicaForm.setMateriaPrima4CapaD(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima4CapaD().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaD() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage4CapaD(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaD().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima5CapaD() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima5CapaD(articuloFichaTecnica.getIdMateriaPrima5CapaD().toString());
            articuloFichaTecnicaForm.setMateriaPrima5CapaD(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima5CapaD().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaD() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage5CapaD(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaD().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima6CapaD() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima6CapaD(articuloFichaTecnica.getIdMateriaPrima6CapaD().toString());
            articuloFichaTecnicaForm.setMateriaPrima6CapaD(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima6CapaD().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaD() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage6CapaD(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaD().toString());
        }
        
        //CAPA E
        if(articuloFichaTecnica.getIdMateriaPrima1CapaE() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima1CapaE(articuloFichaTecnica.getIdMateriaPrima1CapaE().toString());
            articuloFichaTecnicaForm.setMateriaPrima1CapaE(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima1CapaE().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaE() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage1CapaE(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaE().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima2CapaE() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima2CapaE(articuloFichaTecnica.getIdMateriaPrima2CapaE().toString());
            articuloFichaTecnicaForm.setMateriaPrima2CapaE(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima2CapaE().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaE() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage2CapaE(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaE().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima3CapaE() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima3CapaE(articuloFichaTecnica.getIdMateriaPrima3CapaE().toString());
            articuloFichaTecnicaForm.setMateriaPrima3CapaE(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima3CapaE().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaE() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage3CapaE(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaE().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima4CapaE() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima4CapaE(articuloFichaTecnica.getIdMateriaPrima4CapaE().toString());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaE() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage4CapaE(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaE().toString());
            articuloFichaTecnicaForm.setMateriaPrima4CapaE(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima4CapaE().toString()));
        }
        if(articuloFichaTecnica.getIdMateriaPrima5CapaE() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima5CapaE(articuloFichaTecnica.getIdMateriaPrima5CapaE().toString());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaE() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage5CapaE(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaE().toString());
            articuloFichaTecnicaForm.setMateriaPrima5CapaE(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima5CapaE().toString()));
        }
        if(articuloFichaTecnica.getIdMateriaPrima6CapaE() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima6CapaE(articuloFichaTecnica.getIdMateriaPrima6CapaE().toString());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaE() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage6CapaE(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaE().toString());
            articuloFichaTecnicaForm.setMateriaPrima6CapaE(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima6CapaE().toString()));
        }

        //CAPA F
        if(articuloFichaTecnica.getIdMateriaPrima1CapaF() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima1CapaF(articuloFichaTecnica.getIdMateriaPrima1CapaF().toString());
            articuloFichaTecnicaForm.setMateriaPrima1CapaF(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima1CapaF().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaF() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage1CapaF(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaF().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima2CapaF() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima2CapaF(articuloFichaTecnica.getIdMateriaPrima2CapaF().toString());
            articuloFichaTecnicaForm.setMateriaPrima2CapaF(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima2CapaF().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaF() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage2CapaF(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaF().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima3CapaF() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima3CapaF(articuloFichaTecnica.getIdMateriaPrima3CapaF().toString());
            articuloFichaTecnicaForm.setMateriaPrima3CapaF(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima3CapaF().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaF() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage3CapaF(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaF().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima4CapaF() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima4CapaF(articuloFichaTecnica.getIdMateriaPrima4CapaF().toString());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaF() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage4CapaF(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaF().toString());
            articuloFichaTecnicaForm.setMateriaPrima4CapaF(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima4CapaF().toString()));
        }
        if(articuloFichaTecnica.getIdMateriaPrima5CapaF() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima5CapaF(articuloFichaTecnica.getIdMateriaPrima5CapaF().toString());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaF() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage5CapaF(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaF().toString());
            articuloFichaTecnicaForm.setMateriaPrima5CapaF(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima5CapaF().toString()));
        }
        if(articuloFichaTecnica.getIdMateriaPrima6CapaF() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima6CapaF(articuloFichaTecnica.getIdMateriaPrima6CapaF().toString());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaF() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage6CapaF(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaF().toString());
            articuloFichaTecnicaForm.setMateriaPrima6CapaF(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima6CapaF().toString()));
        }
        
        //CAPA G
        if(articuloFichaTecnica.getIdMateriaPrima1CapaG() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima1CapaG(articuloFichaTecnica.getIdMateriaPrima1CapaG().toString());
            articuloFichaTecnicaForm.setMateriaPrima1CapaG(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima1CapaG().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaG() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage1CapaG(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaG().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima2CapaG() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima2CapaG(articuloFichaTecnica.getIdMateriaPrima2CapaG().toString());
            articuloFichaTecnicaForm.setMateriaPrima2CapaG(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima2CapaG().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaG() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage2CapaG(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaG().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima3CapaG() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima3CapaG(articuloFichaTecnica.getIdMateriaPrima3CapaG().toString());
            articuloFichaTecnicaForm.setMateriaPrima3CapaG(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima3CapaG().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaG() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage3CapaG(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaG().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima4CapaG() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima4CapaG(articuloFichaTecnica.getIdMateriaPrima4CapaG().toString());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaG() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage4CapaG(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaG().toString());
            articuloFichaTecnicaForm.setMateriaPrima4CapaG(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima4CapaG().toString()));
        }
        if(articuloFichaTecnica.getIdMateriaPrima5CapaG() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima5CapaG(articuloFichaTecnica.getIdMateriaPrima5CapaG().toString());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaG() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage5CapaG(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaG().toString());
            articuloFichaTecnicaForm.setMateriaPrima5CapaG(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima5CapaG().toString()));
        }
        if(articuloFichaTecnica.getIdMateriaPrima6CapaG() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima6CapaG(articuloFichaTecnica.getIdMateriaPrima6CapaG().toString());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaG() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage6CapaG(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaG().toString());
            articuloFichaTecnicaForm.setMateriaPrima6CapaG(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima6CapaG().toString()));
        }

        //RESUMEN CAPAS
        if(articuloFichaTecnica.getResumenCapaA() != null) {
            articuloFichaTecnicaForm.setResumenCapaA(articuloFichaTecnica.getResumenCapaA().toString());
        }
        if(articuloFichaTecnica.getResumenCapaB() != null) {
            articuloFichaTecnicaForm.setResumenCapaB(articuloFichaTecnica.getResumenCapaB().toString());
        }
        if(articuloFichaTecnica.getResumenCapaC() != null) {
            articuloFichaTecnicaForm.setResumenCapaC(articuloFichaTecnica.getResumenCapaC().toString());
        }
        if(articuloFichaTecnica.getResumenCapaD() != null) {
            articuloFichaTecnicaForm.setResumenCapaD(articuloFichaTecnica.getResumenCapaD().toString());
        }
        if(articuloFichaTecnica.getResumenCapaE() != null) {
            articuloFichaTecnicaForm.setResumenCapaE(articuloFichaTecnica.getResumenCapaE().toString());
        }
        if(articuloFichaTecnica.getResumenCapaF() != null) {
            articuloFichaTecnicaForm.setResumenCapaF(articuloFichaTecnica.getResumenCapaF().toString());
        }
        if(articuloFichaTecnica.getResumenCapaG() != null) {
            articuloFichaTecnicaForm.setResumenCapaG(articuloFichaTecnica.getResumenCapaG().toString());
        }
        
        articuloFichaTecnicaForm.setAction("edit");
        model.addAttribute("articuloFichaTecnicaForm", articuloFichaTecnicaForm);  
        model.addAttribute("titleArticuloFichaTecnica", "Editar Ficha Técnica de Articulo");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("articuloFichaTecnicaName", "Ficha Técnica: "+articuloFichaTecnica.getId());        
        
        
        List<ArticuloFichaTecnicaModel> fichasTecnicas = articuloFichaTecnicaService.getAllByArticulo(articulo.getId());
        
        Integer ultimaVersion = null;
        if(fichasTecnicas != null && !fichasTecnicas.isEmpty()) {
            ultimaVersion = fichasTecnicas.get(0).getId();
        }
        List<ArticuloFichaTecnicaDto> articuloFichasTecnicaDtos = new ArrayList<ArticuloFichaTecnicaDto>();
        for(ArticuloFichaTecnicaModel fichasTecnica: fichasTecnicas) {
            ArticuloFichaTecnicaDto fichaTecnicaDto = new ArticuloFichaTecnicaDto();
            fichaTecnicaDto.setPk(fichasTecnica.getId().toString());
            fichaTecnicaDto.setFechaAlta(fichasTecnica.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            fichaTecnicaDto.setVersion(fichasTecnica.getVersion().toString());
            if(ultimaVersion !=null && ultimaVersion == fichasTecnica.getId()){
                fichaTecnicaDto.setUltimaVersion("true");
            }
            articuloFichasTecnicaDtos.add(fichaTecnicaDto);
        }

        TipoService tipoService = new TipoServiceImpl();
        Map<String,String> materiales = new LinkedHashMap<String,String>();
        List<TipoModel> materialesModel = tipoService.getByType("articuloMaterial");        
        
        if(materialesModel != null && !materialesModel.isEmpty()){
            for(TipoModel tipoModel :materialesModel) {
                materiales.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        

        Map<String,String> tipoBobinas = new LinkedHashMap<String,String>();
        List<TipoModel> tipoBobinasModel = tipoService.getByType("tipoBobina");        
        
        if(tipoBobinasModel != null && !tipoBobinasModel.isEmpty()){
            for(TipoModel tipoModel :tipoBobinasModel) {
                tipoBobinas.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        

        Map<String,String> lineaTintas = new LinkedHashMap<String,String>();
        List<TipoModel> lineaTintasModel = tipoService.getByType("lineaTintas");        
        
        if(lineaTintasModel != null && !lineaTintasModel.isEmpty()){
            for(TipoModel tipoModel :lineaTintasModel) {
                lineaTintas.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        

        Map<String,String> lineaSolventes = new LinkedHashMap<String,String>();
        List<TipoModel> lineaSolventesModel = tipoService.getByType("lineaSolventes");        
        
        if(lineaSolventesModel != null && !lineaSolventesModel.isEmpty()){
            for(TipoModel tipoModel :lineaTintasModel) {
                lineaSolventes.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        
        
        Map<String,String> bultosEn = new LinkedHashMap<String,String>();
        List<TipoModel> bultosEnModel = tipoService.getByType("bultosEn");        
        
        if(bultosEnModel != null && !bultosEnModel.isEmpty()){
            for(TipoModel tipoModel :bultosEnModel) {
                bultosEn.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        

        Map<String,String> palets = new LinkedHashMap<String,String>();
        List<TipoModel> paletsModel = tipoService.getByType("palets");        
        
        if(paletsModel != null && !paletsModel.isEmpty()){
            for(TipoModel tipoModel :paletsModel) {
                palets.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        
                
        Map<String,String> colores = new LinkedHashMap<String,String>();
        List<TipoModel> coloresModel = tipoService.getByType("articuloColor");        
        
        if(coloresModel != null && !coloresModel.isEmpty()){
            for(TipoModel tipoModel :coloresModel) {
                colores.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        
        ProveedorService proveedorService = new ProveedorServiceImpl();
        Map<String,String> proveedores = new LinkedHashMap<String,String>();
        List<ProveedorModel> proveedoresModel = proveedorService.getAll();        
        
        if(proveedoresModel != null && !proveedoresModel.isEmpty()){
            for(ProveedorModel proveedorModel :proveedoresModel) {
                proveedores.put(proveedorModel.getId().toString(), proveedorModel.getRazonSocial());
            }
        }        

        model.addAttribute("materiaPrimaList", materiasPrima);        
        model.addAttribute("proveedorList", proveedores);                
        model.addAttribute("colorList", colores);        
        model.addAttribute("materialList", materiales);    
        model.addAttribute("tipoBobinaList", tipoBobinas);
        model.addAttribute("lineaTintasList", lineaTintas);  
        model.addAttribute("lineaSolventesList", lineaSolventes);    
        model.addAttribute("bultoEnList", bultosEn);  
        model.addAttribute("paletList", palets);          
        model.addAttribute("articulo", articulo.getDenominacion());        
        model.addAttribute("articuloPk", articulo.getId());
        model.addAttribute("operacion", "edit");
        model.addAttribute("articuloFichasTecnica", articuloFichasTecnicaDtos);   
                
        return "/articulofichatecnica/articulofichatecnica";
    }
    
    @RequestMapping(value = "/articulofichatecnica/remove/{articuloFichaTecnicapk}", method = RequestMethod.GET)
    public String removeArticulo(@PathVariable String articuloFichaTecnicapk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(articuloFichaTecnicapk == null) {
            model.addAttribute("errorMessage", "Error: Ficha Técnica de Articulo inválido");         
            return "/error";                
        }
        
        ArticuloFichaTecnicaService articuloFichaTecnicaService = new ArticuloFichaTecnicaServiceImpl();   
        ArticuloFichaTecnicaModel articuloFichaTecnica = articuloFichaTecnicaService.getByPk(Integer.valueOf(articuloFichaTecnicapk));
        if(articuloFichaTecnica == null) {
            model.addAttribute("errorMessage", "Error: Ficha Técnica de Articulo inválido. No ha sido encontrado.");         
            return "/error";    
        }
        
        ArticuloService articuloService = new ArticuloServiceImpl();   
        ArticuloModel articulo = articuloService.getByPk(articuloFichaTecnica.getIdArticulo());
        
        if(articulo == null) {            
            model.addAttribute("errorMessage", "Error: Articulo inválido. No ha sido encontrado.");         
            return "/error";     
        }
        
        ClienteService clienteService = new ClienteServiceImpl();   
        ClienteModel cliente = clienteService.getByPk(articulo.getIdCliente());

        UserService userService = new UserServiceImpl();   
        UserModel user = null;
        if(articuloFichaTecnica.getIdUsuarioModificacion() != null) {
            user = userService.getUserById(articuloFichaTecnica.getIdUsuarioModificacion());        
        }
                
        ArticuloFichaTecnicaForm articuloFichaTecnicaForm = new ArticuloFichaTecnicaForm();
        articuloFichaTecnicaForm.setPk(articuloFichaTecnica.getId().toString());
        articuloFichaTecnicaForm.setIdArticulo(articulo.getId().toString());
        articuloFichaTecnicaForm.setIdCliente(cliente.getId().toString());
        articuloFichaTecnicaForm.setCliente(cliente.getRazonSocial());        
        if(user != null) {
            articuloFichaTecnicaForm.setIdUsuarioModificacion(user.getId().toString());
            articuloFichaTecnicaForm.setUsuarioModificacion(user.getUsername());
        }        
        if(articuloFichaTecnica.getFechaModificacion() != null) {
            articuloFichaTecnicaForm.setFechaModificacion(articuloFichaTecnica.getFechaModificacion().toString().replace(" 00:00:00.0", ""));
        }
        articuloFichaTecnicaForm.setVersion(articuloFichaTecnica.getVersion().toString());
        if(articuloFichaTecnica.getFechaAlta() != null) {
            articuloFichaTecnicaForm.setFechaAlta(articulo.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(articuloFichaTecnica.getUrlEspecificacion() != null) {
            articuloFichaTecnicaForm.setUrlFichaTecnica(articuloFichaTecnica.getUrlEspecificacion().toString());
        }                
        if(articuloFichaTecnica.getEspecificacion() != null) {
            articuloFichaTecnicaForm.setEspecificacion(articuloFichaTecnica.getEspecificacion().toString());
        }                        
        if(articuloFichaTecnica.getObservaciones() != null) {
            articuloFichaTecnicaForm.setObservaciones(articuloFichaTecnica.getObservaciones());
        }
        if(articuloFichaTecnica.getIdMaterial() != null) {
            articuloFichaTecnicaForm.setIdMaterial(articuloFichaTecnica.getIdMaterial().toString());
        }
        if(articuloFichaTecnica.getLamina() != null) {
            if(articuloFichaTecnica.getLamina()) {
                articuloFichaTecnicaForm.setLamina("1");
            } else {
                articuloFichaTecnicaForm.setLamina("0");
            }
        } else {
            articuloFichaTecnicaForm.setLamina("0");
        }
        if(articuloFichaTecnica.getBobina() != null) {
            if(articuloFichaTecnica.getBobina()) {
                articuloFichaTecnicaForm.setBobina("1");
            } else {
                articuloFichaTecnicaForm.setBobina("0");
            }
        } else {
            articuloFichaTecnicaForm.setBobina("0");
        }
        if(articuloFichaTecnica.getCantidadCapas() != null) {
            articuloFichaTecnicaForm.setCantidadCapas(articuloFichaTecnica.getCantidadCapas().toString());
        }
        if(articuloFichaTecnica.getIdColorA() != null) {
            articuloFichaTecnicaForm.setColorA(articuloFichaTecnica.getIdColorA().toString());
        }
        if(articuloFichaTecnica.getIdColorB() != null) {
            articuloFichaTecnicaForm.setColorB(articuloFichaTecnica.getIdColorB().toString());
        }
        if(articuloFichaTecnica.getIdColorC() != null) {
            articuloFichaTecnicaForm.setColorC(articuloFichaTecnica.getIdColorC().toString());
        }
        if(articuloFichaTecnica.getIdColorD() != null) {
            articuloFichaTecnicaForm.setColorD(articuloFichaTecnica.getIdColorD().toString());
        }
        if(articuloFichaTecnica.getIdColorE() != null) {
            articuloFichaTecnicaForm.setColorE(articuloFichaTecnica.getIdColorE().toString());
        }
        if(articuloFichaTecnica.getIdColorF() != null) {
            articuloFichaTecnicaForm.setColorF(articuloFichaTecnica.getIdColorF().toString());
        }
        if(articuloFichaTecnica.getIdColorG() != null) {
            articuloFichaTecnicaForm.setColorG(articuloFichaTecnica.getIdColorG().toString());
        }
        if(articuloFichaTecnica.getAncho() != null) {
            articuloFichaTecnicaForm.setAncho(articuloFichaTecnica.getAncho().toString());
        }
        if(articuloFichaTecnica.getFuelleIzquierdo() != null) {
            articuloFichaTecnicaForm.setFuelleIzquierdo(articuloFichaTecnica.getFuelleIzquierdo().toString());
        }
        if(articuloFichaTecnica.getFuelleDerecho() != null) {
            articuloFichaTecnicaForm.setFuelleDerecho(articuloFichaTecnica.getFuelleDerecho().toString());
        }
        if(articuloFichaTecnica.getEspesorNominal() != null) {
            articuloFichaTecnicaForm.setEspesorNominal(articuloFichaTecnica.getEspesorNominal().toString());
        }
        if(articuloFichaTecnica.getEspesorPromedio() != null) {
            articuloFichaTecnicaForm.setEspesorPromedio(articuloFichaTecnica.getEspesorPromedio().toString());
        }
        if(articuloFichaTecnica.getEspesorArticulo() != null) {
            articuloFichaTecnicaForm.setEspesorArticulo(articuloFichaTecnica.getEspesorArticulo().toString());
        }
        if(articuloFichaTecnica.getPesoEspecifico() != null) {
            articuloFichaTecnicaForm.setPesoEspecifico(articuloFichaTecnica.getPesoEspecifico().toString());
        }
        if(articuloFichaTecnica.getAnchoBruto() != null) {
            articuloFichaTecnicaForm.setAnchoBruto(articuloFichaTecnica.getAnchoBruto().toString());
        }
        if(articuloFichaTecnica.getBobinadoBarras() != null) {
            articuloFichaTecnicaForm.setBobinadoBarras(articuloFichaTecnica.getBobinadoBarras().toString());
        }
        if(articuloFichaTecnica.getMetros() != null) {
            articuloFichaTecnicaForm.setMetros(articuloFichaTecnica.getMetros().toString());
        }
        if(articuloFichaTecnica.getPeso() != null) {
            articuloFichaTecnicaForm.setPeso(articuloFichaTecnica.getPeso().toString());
        }
        if(articuloFichaTecnica.getDiametro() != null) {
            articuloFichaTecnicaForm.setDiametro(articuloFichaTecnica.getDiametro().toString());
        }
        if(articuloFichaTecnica.getEmpalmes() != null) {
            articuloFichaTecnicaForm.setEmpalmes(articuloFichaTecnica.getEmpalmes().toString());
        }
        if(articuloFichaTecnica.getIdTipoBobina() != null) {
            articuloFichaTecnicaForm.setIdTipoBobina(articuloFichaTecnica.getIdTipoBobina().toString());
        }
        if(articuloFichaTecnica.getDiametro2() != null) {
            articuloFichaTecnicaForm.setDiametro2(articuloFichaTecnica.getDiametro2().toString());
        }
        if(articuloFichaTecnica.getLargo() != null) {
            articuloFichaTecnicaForm.setLargo(articuloFichaTecnica.getLargo().toString());
        }
        if(articuloFichaTecnica.getEspesor() != null) {
            articuloFichaTecnicaForm.setEspesor(articuloFichaTecnica.getEspesor().toString());
        }
        if(articuloFichaTecnica.getPeso2() != null) {
            articuloFichaTecnicaForm.setPeso2(articuloFichaTecnica.getPeso2().toString());
        }
        if(articuloFichaTecnica.getIdLineaTintas() != null) {
            articuloFichaTecnicaForm.setIdLineaTintas(articuloFichaTecnica.getIdLineaTintas().toString());
        }
        if(articuloFichaTecnica.getIdLineaSolventes() != null) {
            articuloFichaTecnicaForm.setIdLineaSolventes(articuloFichaTecnica.getIdLineaSolventes().toString());
        }
        if(articuloFichaTecnica.getIdBultosEn() != null) {
            articuloFichaTecnicaForm.setIdBultosEn(articuloFichaTecnica.getIdBultosEn().toString());
        }
        if(articuloFichaTecnica.getBultosPorPalet() != null) {
            articuloFichaTecnicaForm.setBultosPorPalet(articuloFichaTecnica.getBultosPorPalet().toString());
        }
        if(articuloFichaTecnica.getIdPalet() != null) {
            articuloFichaTecnicaForm.setIdPalet(articuloFichaTecnica.getIdPalet().toString());
        }
        if(articuloFichaTecnica.getIdPalet() != null) {
            articuloFichaTecnicaForm.setIdPalet(articuloFichaTecnica.getIdPalet().toString());
        }
        if(articuloFichaTecnica.getPosicionPalet() != null) {
            articuloFichaTecnicaForm.setPosicionPalet(articuloFichaTecnica.getPosicionPalet().toString());
        }
        
        MateriaPrimaService materiaPrimaService = new MateriaPrimaServiceImpl();
        Map<String,String> materiasPrima = new LinkedHashMap<String,String>();
        List<MateriaPrimaModel> materiasPrimaModel = materiaPrimaService.getAll();        
        
        if(materiasPrimaModel != null && !materiasPrimaModel.isEmpty()){
            for(MateriaPrimaModel materiaPrimaModel :materiasPrimaModel) {
                materiasPrima.put(materiaPrimaModel.getId().toString(), materiaPrimaModel.getDescripcion());
            }
        }        
        
        //CAPA A
        if(articuloFichaTecnica.getIdMateriaPrima1CapaA() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima1CapaA(articuloFichaTecnica.getIdMateriaPrima1CapaA().toString());
            articuloFichaTecnicaForm.setMateriaPrima1CapaA(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima1CapaA().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaA() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage1CapaA(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaA().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima2CapaA() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima2CapaA(articuloFichaTecnica.getIdMateriaPrima2CapaA().toString());
            articuloFichaTecnicaForm.setMateriaPrima2CapaA(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima2CapaA().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaA() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage2CapaA(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaA().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima3CapaA() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima3CapaA(articuloFichaTecnica.getIdMateriaPrima3CapaA().toString());
            articuloFichaTecnicaForm.setMateriaPrima3CapaA(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima3CapaA().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaA() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage3CapaA(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaA().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima4CapaA() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima4CapaA(articuloFichaTecnica.getIdMateriaPrima4CapaA().toString());
            articuloFichaTecnicaForm.setMateriaPrima4CapaA(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima4CapaA().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaA() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage4CapaA(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaA().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima5CapaA() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima5CapaA(articuloFichaTecnica.getIdMateriaPrima5CapaA().toString());
            articuloFichaTecnicaForm.setMateriaPrima5CapaA(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima5CapaA().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaA() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage5CapaA(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaA().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima6CapaA() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima6CapaA(articuloFichaTecnica.getIdMateriaPrima6CapaA().toString());
            articuloFichaTecnicaForm.setMateriaPrima6CapaA(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima6CapaA().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaA() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage6CapaA(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaA().toString());
        }

        //CAPA B
        if(articuloFichaTecnica.getIdMateriaPrima1CapaB() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima1CapaB(articuloFichaTecnica.getIdMateriaPrima1CapaB().toString());
            articuloFichaTecnicaForm.setMateriaPrima1CapaB(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima1CapaB().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaB() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage1CapaB(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaB().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima2CapaB() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima2CapaB(articuloFichaTecnica.getIdMateriaPrima2CapaB().toString());
            articuloFichaTecnicaForm.setMateriaPrima2CapaB(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima2CapaB().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaB() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage2CapaB(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaB().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima3CapaB() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima3CapaB(articuloFichaTecnica.getIdMateriaPrima3CapaB().toString());
            articuloFichaTecnicaForm.setMateriaPrima3CapaB(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima3CapaB().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaB() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage3CapaB(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaB().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima4CapaB() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima4CapaB(articuloFichaTecnica.getIdMateriaPrima4CapaB().toString());
            articuloFichaTecnicaForm.setMateriaPrima4CapaB(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima4CapaB().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaB() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage4CapaB(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaB().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima5CapaB() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima5CapaB(articuloFichaTecnica.getIdMateriaPrima5CapaB().toString());
            articuloFichaTecnicaForm.setMateriaPrima5CapaB(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima5CapaB().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaB() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage5CapaB(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaB().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima6CapaB() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima6CapaB(articuloFichaTecnica.getIdMateriaPrima6CapaB().toString());
            articuloFichaTecnicaForm.setMateriaPrima6CapaB(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima6CapaB().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaB() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage6CapaB(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaB().toString());
        }

        //CAPA C
        if(articuloFichaTecnica.getIdMateriaPrima1CapaC() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima1CapaC(articuloFichaTecnica.getIdMateriaPrima1CapaC().toString());
            articuloFichaTecnicaForm.setMateriaPrima1CapaC(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima1CapaC().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaC() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage1CapaC(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaC().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima2CapaC() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima2CapaC(articuloFichaTecnica.getIdMateriaPrima2CapaC().toString());
            articuloFichaTecnicaForm.setMateriaPrima2CapaC(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima2CapaC().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaC() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage2CapaC(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaC().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima3CapaC() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima3CapaC(articuloFichaTecnica.getIdMateriaPrima3CapaC().toString());
            articuloFichaTecnicaForm.setMateriaPrima3CapaC(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima3CapaC().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaC() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage3CapaC(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaC().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima4CapaC() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima4CapaC(articuloFichaTecnica.getIdMateriaPrima4CapaC().toString());
            articuloFichaTecnicaForm.setMateriaPrima4CapaC(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima4CapaC().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaC() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage4CapaC(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaC().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima5CapaC() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima5CapaC(articuloFichaTecnica.getIdMateriaPrima5CapaC().toString());
            articuloFichaTecnicaForm.setMateriaPrima5CapaC(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima5CapaC().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaC() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage5CapaC(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaC().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima6CapaC() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima6CapaC(articuloFichaTecnica.getIdMateriaPrima6CapaC().toString());
            articuloFichaTecnicaForm.setMateriaPrima6CapaC(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima6CapaC().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaC() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage6CapaC(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaC().toString());
        }
        
        //CAPA D
        if(articuloFichaTecnica.getIdMateriaPrima1CapaD() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima1CapaD(articuloFichaTecnica.getIdMateriaPrima1CapaD().toString());
            articuloFichaTecnicaForm.setMateriaPrima1CapaD(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima1CapaD().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaD() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage1CapaD(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaD().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima2CapaD() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima2CapaD(articuloFichaTecnica.getIdMateriaPrima2CapaD().toString());
            articuloFichaTecnicaForm.setMateriaPrima2CapaD(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima2CapaD().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaD() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage2CapaD(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaD().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima3CapaD() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima3CapaD(articuloFichaTecnica.getIdMateriaPrima3CapaD().toString());
            articuloFichaTecnicaForm.setMateriaPrima3CapaD(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima3CapaD().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaD() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage3CapaD(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaD().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima4CapaD() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima4CapaD(articuloFichaTecnica.getIdMateriaPrima4CapaD().toString());
            articuloFichaTecnicaForm.setMateriaPrima4CapaD(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima4CapaD().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaD() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage4CapaD(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaD().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima5CapaD() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima5CapaD(articuloFichaTecnica.getIdMateriaPrima5CapaD().toString());
            articuloFichaTecnicaForm.setMateriaPrima5CapaD(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima5CapaD().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaD() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage5CapaD(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaD().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima6CapaD() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima6CapaD(articuloFichaTecnica.getIdMateriaPrima6CapaD().toString());
            articuloFichaTecnicaForm.setMateriaPrima6CapaD(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima6CapaD().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaD() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage6CapaD(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaD().toString());
        }
        
        //CAPA E
        if(articuloFichaTecnica.getIdMateriaPrima1CapaE() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima1CapaE(articuloFichaTecnica.getIdMateriaPrima1CapaE().toString());
            articuloFichaTecnicaForm.setMateriaPrima1CapaE(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima1CapaE().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaE() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage1CapaE(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaE().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima2CapaE() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima2CapaE(articuloFichaTecnica.getIdMateriaPrima2CapaE().toString());
            articuloFichaTecnicaForm.setMateriaPrima2CapaE(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima2CapaE().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaE() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage2CapaE(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaE().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima3CapaE() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima3CapaE(articuloFichaTecnica.getIdMateriaPrima3CapaE().toString());
            articuloFichaTecnicaForm.setMateriaPrima3CapaE(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima3CapaE().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaE() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage3CapaE(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaE().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima4CapaE() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima4CapaE(articuloFichaTecnica.getIdMateriaPrima4CapaE().toString());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaE() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage4CapaE(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaE().toString());
            articuloFichaTecnicaForm.setMateriaPrima4CapaE(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima4CapaE().toString()));
        }
        if(articuloFichaTecnica.getIdMateriaPrima5CapaE() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima5CapaE(articuloFichaTecnica.getIdMateriaPrima5CapaE().toString());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaE() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage5CapaE(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaE().toString());
            articuloFichaTecnicaForm.setMateriaPrima5CapaE(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima5CapaE().toString()));
        }
        if(articuloFichaTecnica.getIdMateriaPrima6CapaE() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima6CapaE(articuloFichaTecnica.getIdMateriaPrima6CapaE().toString());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaE() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage6CapaE(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaE().toString());
            articuloFichaTecnicaForm.setMateriaPrima6CapaE(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima6CapaE().toString()));
        }

        //CAPA F
        if(articuloFichaTecnica.getIdMateriaPrima1CapaF() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima1CapaF(articuloFichaTecnica.getIdMateriaPrima1CapaF().toString());
            articuloFichaTecnicaForm.setMateriaPrima1CapaF(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima1CapaF().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaF() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage1CapaF(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaF().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima2CapaF() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima2CapaF(articuloFichaTecnica.getIdMateriaPrima2CapaF().toString());
            articuloFichaTecnicaForm.setMateriaPrima2CapaF(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima2CapaF().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaF() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage2CapaF(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaF().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima3CapaF() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima3CapaF(articuloFichaTecnica.getIdMateriaPrima3CapaF().toString());
            articuloFichaTecnicaForm.setMateriaPrima3CapaF(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima3CapaF().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaF() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage3CapaF(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaF().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima4CapaF() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima4CapaF(articuloFichaTecnica.getIdMateriaPrima4CapaF().toString());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaF() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage4CapaF(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaF().toString());
            articuloFichaTecnicaForm.setMateriaPrima4CapaF(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima4CapaF().toString()));
        }
        if(articuloFichaTecnica.getIdMateriaPrima5CapaF() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima5CapaF(articuloFichaTecnica.getIdMateriaPrima5CapaF().toString());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaF() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage5CapaF(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaF().toString());
            articuloFichaTecnicaForm.setMateriaPrima5CapaF(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima5CapaF().toString()));
        }
        if(articuloFichaTecnica.getIdMateriaPrima6CapaF() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima6CapaF(articuloFichaTecnica.getIdMateriaPrima6CapaF().toString());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaF() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage6CapaF(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaF().toString());
            articuloFichaTecnicaForm.setMateriaPrima6CapaF(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima6CapaF().toString()));
        }
        
        //CAPA G
        if(articuloFichaTecnica.getIdMateriaPrima1CapaG() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima1CapaG(articuloFichaTecnica.getIdMateriaPrima1CapaG().toString());
            articuloFichaTecnicaForm.setMateriaPrima1CapaG(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima1CapaG().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaG() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage1CapaG(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaG().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima2CapaG() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima2CapaG(articuloFichaTecnica.getIdMateriaPrima2CapaG().toString());
            articuloFichaTecnicaForm.setMateriaPrima2CapaG(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima2CapaG().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaG() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage2CapaG(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaG().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima3CapaG() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima3CapaG(articuloFichaTecnica.getIdMateriaPrima3CapaG().toString());
            articuloFichaTecnicaForm.setMateriaPrima3CapaG(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima3CapaG().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaG() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage3CapaG(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaG().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima4CapaG() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima4CapaG(articuloFichaTecnica.getIdMateriaPrima4CapaG().toString());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaG() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage4CapaG(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaG().toString());
            articuloFichaTecnicaForm.setMateriaPrima4CapaG(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima4CapaG().toString()));
        }
        if(articuloFichaTecnica.getIdMateriaPrima5CapaG() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima5CapaG(articuloFichaTecnica.getIdMateriaPrima5CapaG().toString());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaG() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage5CapaG(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaG().toString());
            articuloFichaTecnicaForm.setMateriaPrima5CapaG(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima5CapaG().toString()));
        }
        if(articuloFichaTecnica.getIdMateriaPrima6CapaG() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima6CapaG(articuloFichaTecnica.getIdMateriaPrima6CapaG().toString());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaG() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage6CapaG(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaG().toString());
            articuloFichaTecnicaForm.setMateriaPrima6CapaG(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima6CapaG().toString()));
        }
        
        //RESUMEN CAPAS
        if(articuloFichaTecnica.getResumenCapaA() != null) {
            articuloFichaTecnicaForm.setResumenCapaA(articuloFichaTecnica.getResumenCapaA().toString());
        }
        if(articuloFichaTecnica.getResumenCapaB() != null) {
            articuloFichaTecnicaForm.setResumenCapaB(articuloFichaTecnica.getResumenCapaB().toString());
        }
        if(articuloFichaTecnica.getResumenCapaC() != null) {
            articuloFichaTecnicaForm.setResumenCapaC(articuloFichaTecnica.getResumenCapaC().toString());
        }
        if(articuloFichaTecnica.getResumenCapaD() != null) {
            articuloFichaTecnicaForm.setResumenCapaD(articuloFichaTecnica.getResumenCapaD().toString());
        }
        if(articuloFichaTecnica.getResumenCapaE() != null) {
            articuloFichaTecnicaForm.setResumenCapaE(articuloFichaTecnica.getResumenCapaE().toString());
        }
        if(articuloFichaTecnica.getResumenCapaF() != null) {
            articuloFichaTecnicaForm.setResumenCapaF(articuloFichaTecnica.getResumenCapaF().toString());
        }
        if(articuloFichaTecnica.getResumenCapaG() != null) {
            articuloFichaTecnicaForm.setResumenCapaG(articuloFichaTecnica.getResumenCapaG().toString());
        }
        
        articuloFichaTecnicaForm.setAction("remove");
        model.addAttribute("articuloFichaTecnicaForm", articuloFichaTecnicaForm);  
        model.addAttribute("titleArticuloFichaTecnica", "Eliminar Ficha Técnica de Articulo");
        model.addAttribute("buttonLabel", "Eliminar");
        model.addAttribute("articuloFichaTecnicaName", "Ficha Técnica: "+articuloFichaTecnica.getId());        
        
        
        List<ArticuloFichaTecnicaModel> fichasTecnicas = articuloFichaTecnicaService.getAllByArticulo(articulo.getId());
        
        Integer ultimaVersion = null;
        if(fichasTecnicas != null && !fichasTecnicas.isEmpty()) {
            ultimaVersion = fichasTecnicas.get(0).getId();
        }
        List<ArticuloFichaTecnicaDto> articuloFichasTecnicaDtos = new ArrayList<ArticuloFichaTecnicaDto>();
        for(ArticuloFichaTecnicaModel fichasTecnica: fichasTecnicas) {
            ArticuloFichaTecnicaDto fichaTecnicaDto = new ArticuloFichaTecnicaDto();
            fichaTecnicaDto.setPk(fichasTecnica.getId().toString());
            fichaTecnicaDto.setFechaAlta(fichasTecnica.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            fichaTecnicaDto.setVersion(fichasTecnica.getVersion().toString());
            if(ultimaVersion !=null && ultimaVersion == fichasTecnica.getId()){
                fichaTecnicaDto.setUltimaVersion("true");
            }
            articuloFichasTecnicaDtos.add(fichaTecnicaDto);
        }
        
        TipoService tipoService = new TipoServiceImpl();
        Map<String,String> materiales = new LinkedHashMap<String,String>();
        List<TipoModel> materialesModel = tipoService.getByType("articuloMaterial");        
        
        if(materialesModel != null && !materialesModel.isEmpty()){
            for(TipoModel tipoModel :materialesModel) {
                materiales.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        

        Map<String,String> tipoBobinas = new LinkedHashMap<String,String>();
        List<TipoModel> tipoBobinasModel = tipoService.getByType("tipoBobina");        
        
        if(tipoBobinasModel != null && !tipoBobinasModel.isEmpty()){
            for(TipoModel tipoModel :tipoBobinasModel) {
                tipoBobinas.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        

        Map<String,String> lineaTintas = new LinkedHashMap<String,String>();
        List<TipoModel> lineaTintasModel = tipoService.getByType("lineaTintas");        
        
        if(lineaTintasModel != null && !lineaTintasModel.isEmpty()){
            for(TipoModel tipoModel :lineaTintasModel) {
                lineaTintas.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        

        Map<String,String> lineaSolventes = new LinkedHashMap<String,String>();
        List<TipoModel> lineaSolventesModel = tipoService.getByType("lineaSolventes");        
        
        if(lineaSolventesModel != null && !lineaSolventesModel.isEmpty()){
            for(TipoModel tipoModel :lineaTintasModel) {
                lineaSolventes.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        
        
        Map<String,String> bultosEn = new LinkedHashMap<String,String>();
        List<TipoModel> bultosEnModel = tipoService.getByType("bultosEn");        
        
        if(bultosEnModel != null && !bultosEnModel.isEmpty()){
            for(TipoModel tipoModel :bultosEnModel) {
                bultosEn.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        

        Map<String,String> palets = new LinkedHashMap<String,String>();
        List<TipoModel> paletsModel = tipoService.getByType("palets");        
        
        if(paletsModel != null && !paletsModel.isEmpty()){
            for(TipoModel tipoModel :paletsModel) {
                palets.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        
                
        Map<String,String> colores = new LinkedHashMap<String,String>();
        List<TipoModel> coloresModel = tipoService.getByType("articuloColor");        
        
        if(coloresModel != null && !coloresModel.isEmpty()){
            for(TipoModel tipoModel :coloresModel) {
                colores.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        
        
        model.addAttribute("colorList", colores);        
        model.addAttribute("materialList", materiales); 
        model.addAttribute("tipoBobinaList", tipoBobinas);
        model.addAttribute("lineaTintasList", lineaTintas);  
        model.addAttribute("lineaSolventesList", lineaSolventes);   
        model.addAttribute("bultoEnList", bultosEn);  
        model.addAttribute("paletList", palets);          
        model.addAttribute("articulo", articulo.getDenominacion());        
        model.addAttribute("articuloPk", articulo.getId());
        model.addAttribute("operacion", "remove");
        model.addAttribute("articuloFichasTecnica", articuloFichasTecnicaDtos);   
                
        return "/articulofichatecnica/articulofichatecnica";      
    }    

    @RequestMapping(value = "/articulofichatecnica/view/{articuloFichaTecnicapk}", method = RequestMethod.GET)
    public String viewArticulo(@PathVariable String articuloFichaTecnicapk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(articuloFichaTecnicapk == null) {
            model.addAttribute("errorMessage", "Error: Ficha Técnica de Articulo inválido");         
            return "/error";                
        }
        
        ArticuloFichaTecnicaService articuloFichaTecnicaService = new ArticuloFichaTecnicaServiceImpl();   
        ArticuloFichaTecnicaModel articuloFichaTecnica = articuloFichaTecnicaService.getByPk(Integer.valueOf(articuloFichaTecnicapk));
        if(articuloFichaTecnica == null) {
            model.addAttribute("errorMessage", "Error: Ficha Técnica de Articulo inválido. No ha sido encontrado.");         
            return "/error";    
        }
        
        ArticuloService articuloService = new ArticuloServiceImpl();   
        ArticuloModel articulo = articuloService.getByPk(articuloFichaTecnica.getIdArticulo());
        
        if(articulo == null) {            
            model.addAttribute("errorMessage", "Error: Articulo inválido. No ha sido encontrado.");         
            return "/error";     
        }
        
        ClienteService clienteService = new ClienteServiceImpl();   
        ClienteModel cliente = clienteService.getByPk(articulo.getIdCliente());

        UserService userService = new UserServiceImpl();   
        UserModel user = null;
        if(articuloFichaTecnica.getIdUsuarioModificacion() != null) {
            user = userService.getUserById(articuloFichaTecnica.getIdUsuarioModificacion());        
        }
                
        ArticuloFichaTecnicaForm articuloFichaTecnicaForm = new ArticuloFichaTecnicaForm();
        articuloFichaTecnicaForm.setPk(articuloFichaTecnica.getId().toString());
        articuloFichaTecnicaForm.setIdArticulo(articulo.getId().toString());
        articuloFichaTecnicaForm.setIdCliente(cliente.getId().toString());
        articuloFichaTecnicaForm.setCliente(cliente.getRazonSocial());
        if(user != null) {
            articuloFichaTecnicaForm.setIdUsuarioModificacion(user.getId().toString());
            articuloFichaTecnicaForm.setUsuarioModificacion(user.getUsername());
        }                
        if(articuloFichaTecnica.getFechaModificacion() != null) {
            articuloFichaTecnicaForm.setFechaModificacion(articuloFichaTecnica.getFechaModificacion().toString().replace(" 00:00:00.0", ""));
        }
        articuloFichaTecnicaForm.setVersion(articuloFichaTecnica.getVersion().toString());
        if(articuloFichaTecnica.getFechaAlta() != null) {
            articuloFichaTecnicaForm.setFechaAlta(articulo.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(articuloFichaTecnica.getUrlEspecificacion() != null) {
            articuloFichaTecnicaForm.setUrlFichaTecnica(articuloFichaTecnica.getUrlEspecificacion().toString());
        }                
        if(articuloFichaTecnica.getEspecificacion() != null) {
            articuloFichaTecnicaForm.setEspecificacion(articuloFichaTecnica.getEspecificacion().toString());
        }                        
        if(articuloFichaTecnica.getObservaciones() != null) {
            articuloFichaTecnicaForm.setObservaciones(articuloFichaTecnica.getObservaciones());
        }
                if(articuloFichaTecnica.getIdMaterial() != null) {
            articuloFichaTecnicaForm.setIdMaterial(articuloFichaTecnica.getIdMaterial().toString());
        }
        if(articuloFichaTecnica.getLamina() != null) {
            if(articuloFichaTecnica.getLamina()) {
                articuloFichaTecnicaForm.setLamina("1");
            } else {
                articuloFichaTecnicaForm.setLamina("0");
            }
        } else {
            articuloFichaTecnicaForm.setLamina("0");
        }
        if(articuloFichaTecnica.getBobina() != null) {
            if(articuloFichaTecnica.getBobina()) {
                articuloFichaTecnicaForm.setBobina("1");
            } else {
                articuloFichaTecnicaForm.setBobina("0");
            }
        } else {
            articuloFichaTecnicaForm.setBobina("0");
        }
        if(articuloFichaTecnica.getCantidadCapas() != null) {
            articuloFichaTecnicaForm.setCantidadCapas(articuloFichaTecnica.getCantidadCapas().toString());
        }
        if(articuloFichaTecnica.getIdColorA() != null) {
            articuloFichaTecnicaForm.setColorA(articuloFichaTecnica.getIdColorA().toString());
        }
        if(articuloFichaTecnica.getIdColorB() != null) {
            articuloFichaTecnicaForm.setColorB(articuloFichaTecnica.getIdColorB().toString());
        }
        if(articuloFichaTecnica.getIdColorC() != null) {
            articuloFichaTecnicaForm.setColorC(articuloFichaTecnica.getIdColorC().toString());
        }
        if(articuloFichaTecnica.getIdColorD() != null) {
            articuloFichaTecnicaForm.setColorD(articuloFichaTecnica.getIdColorD().toString());
        }
        if(articuloFichaTecnica.getIdColorE() != null) {
            articuloFichaTecnicaForm.setColorE(articuloFichaTecnica.getIdColorE().toString());
        }
        if(articuloFichaTecnica.getIdColorF() != null) {
            articuloFichaTecnicaForm.setColorF(articuloFichaTecnica.getIdColorF().toString());
        }
        if(articuloFichaTecnica.getIdColorG() != null) {
            articuloFichaTecnicaForm.setColorG(articuloFichaTecnica.getIdColorG().toString());
        }
        if(articuloFichaTecnica.getAncho() != null) {
            articuloFichaTecnicaForm.setAncho(articuloFichaTecnica.getAncho().toString());
        }
        if(articuloFichaTecnica.getFuelleIzquierdo() != null) {
            articuloFichaTecnicaForm.setFuelleIzquierdo(articuloFichaTecnica.getFuelleIzquierdo().toString());
        }
        if(articuloFichaTecnica.getFuelleDerecho() != null) {
            articuloFichaTecnicaForm.setFuelleDerecho(articuloFichaTecnica.getFuelleDerecho().toString());
        }
        if(articuloFichaTecnica.getEspesorNominal() != null) {
            articuloFichaTecnicaForm.setEspesorNominal(articuloFichaTecnica.getEspesorNominal().toString());
        }
        if(articuloFichaTecnica.getEspesorPromedio() != null) {
            articuloFichaTecnicaForm.setEspesorPromedio(articuloFichaTecnica.getEspesorPromedio().toString());
        }
        if(articuloFichaTecnica.getEspesorArticulo() != null) {
            articuloFichaTecnicaForm.setEspesorArticulo(articuloFichaTecnica.getEspesorArticulo().toString());
        }
        if(articuloFichaTecnica.getPesoEspecifico() != null) {
            articuloFichaTecnicaForm.setPesoEspecifico(articuloFichaTecnica.getPesoEspecifico().toString());
        }
        if(articuloFichaTecnica.getAnchoBruto() != null) {
            articuloFichaTecnicaForm.setAnchoBruto(articuloFichaTecnica.getAnchoBruto().toString());
        }
        if(articuloFichaTecnica.getBobinadoBarras() != null) {
            articuloFichaTecnicaForm.setBobinadoBarras(articuloFichaTecnica.getBobinadoBarras().toString());
        }
        if(articuloFichaTecnica.getMetros() != null) {
            articuloFichaTecnicaForm.setMetros(articuloFichaTecnica.getMetros().toString());
        }
        if(articuloFichaTecnica.getPeso() != null) {
            articuloFichaTecnicaForm.setPeso(articuloFichaTecnica.getPeso().toString());
        }
        if(articuloFichaTecnica.getDiametro() != null) {
            articuloFichaTecnicaForm.setDiametro(articuloFichaTecnica.getDiametro().toString());
        }
        if(articuloFichaTecnica.getEmpalmes() != null) {
            articuloFichaTecnicaForm.setEmpalmes(articuloFichaTecnica.getEmpalmes().toString());
        }
        if(articuloFichaTecnica.getIdTipoBobina() != null) {
            articuloFichaTecnicaForm.setIdTipoBobina(articuloFichaTecnica.getIdTipoBobina().toString());
        }
        if(articuloFichaTecnica.getDiametro2() != null) {
            articuloFichaTecnicaForm.setDiametro2(articuloFichaTecnica.getDiametro2().toString());
        }
        if(articuloFichaTecnica.getLargo() != null) {
            articuloFichaTecnicaForm.setLargo(articuloFichaTecnica.getLargo().toString());
        }
        if(articuloFichaTecnica.getEspesor() != null) {
            articuloFichaTecnicaForm.setEspesor(articuloFichaTecnica.getEspesor().toString());
        }
        if(articuloFichaTecnica.getPeso2() != null) {
            articuloFichaTecnicaForm.setPeso2(articuloFichaTecnica.getPeso2().toString());
        }
        if(articuloFichaTecnica.getIdLineaTintas() != null) {
            articuloFichaTecnicaForm.setIdLineaTintas(articuloFichaTecnica.getIdLineaTintas().toString());
        }
        if(articuloFichaTecnica.getIdLineaSolventes() != null) {
            articuloFichaTecnicaForm.setIdLineaSolventes(articuloFichaTecnica.getIdLineaSolventes().toString());
        }
        if(articuloFichaTecnica.getIdBultosEn() != null) {
            articuloFichaTecnicaForm.setIdBultosEn(articuloFichaTecnica.getIdBultosEn().toString());
        }
        if(articuloFichaTecnica.getBultosPorPalet() != null) {
            articuloFichaTecnicaForm.setBultosPorPalet(articuloFichaTecnica.getBultosPorPalet().toString());
        }
        if(articuloFichaTecnica.getIdPalet() != null) {
            articuloFichaTecnicaForm.setIdPalet(articuloFichaTecnica.getIdPalet().toString());
        }
        if(articuloFichaTecnica.getIdPalet() != null) {
            articuloFichaTecnicaForm.setIdPalet(articuloFichaTecnica.getIdPalet().toString());
        }
        if(articuloFichaTecnica.getPosicionPalet() != null) {
            articuloFichaTecnicaForm.setPosicionPalet(articuloFichaTecnica.getPosicionPalet().toString());
        }

        MateriaPrimaService materiaPrimaService = new MateriaPrimaServiceImpl();
        Map<String,String> materiasPrima = new LinkedHashMap<String,String>();
        List<MateriaPrimaModel> materiasPrimaModel = materiaPrimaService.getAll();        
        
        if(materiasPrimaModel != null && !materiasPrimaModel.isEmpty()){
            for(MateriaPrimaModel materiaPrimaModel :materiasPrimaModel) {
                materiasPrima.put(materiaPrimaModel.getId().toString(), materiaPrimaModel.getDescripcion());
            }
        }        
        
        //CAPA A
        if(articuloFichaTecnica.getIdMateriaPrima1CapaA() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima1CapaA(articuloFichaTecnica.getIdMateriaPrima1CapaA().toString());
            articuloFichaTecnicaForm.setMateriaPrima1CapaA(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima1CapaA().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaA() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage1CapaA(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaA().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima2CapaA() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima2CapaA(articuloFichaTecnica.getIdMateriaPrima2CapaA().toString());
            articuloFichaTecnicaForm.setMateriaPrima2CapaA(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima2CapaA().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaA() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage2CapaA(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaA().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima3CapaA() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima3CapaA(articuloFichaTecnica.getIdMateriaPrima3CapaA().toString());
            articuloFichaTecnicaForm.setMateriaPrima3CapaA(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima3CapaA().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaA() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage3CapaA(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaA().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima4CapaA() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima4CapaA(articuloFichaTecnica.getIdMateriaPrima4CapaA().toString());
            articuloFichaTecnicaForm.setMateriaPrima4CapaA(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima4CapaA().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaA() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage4CapaA(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaA().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima5CapaA() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima5CapaA(articuloFichaTecnica.getIdMateriaPrima5CapaA().toString());
            articuloFichaTecnicaForm.setMateriaPrima5CapaA(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima5CapaA().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaA() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage5CapaA(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaA().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima6CapaA() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima6CapaA(articuloFichaTecnica.getIdMateriaPrima6CapaA().toString());
            articuloFichaTecnicaForm.setMateriaPrima6CapaA(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima6CapaA().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaA() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage6CapaA(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaA().toString());
        }

        //CAPA B
        if(articuloFichaTecnica.getIdMateriaPrima1CapaB() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima1CapaB(articuloFichaTecnica.getIdMateriaPrima1CapaB().toString());
            articuloFichaTecnicaForm.setMateriaPrima1CapaB(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima1CapaB().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaB() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage1CapaB(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaB().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima2CapaB() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima2CapaB(articuloFichaTecnica.getIdMateriaPrima2CapaB().toString());
            articuloFichaTecnicaForm.setMateriaPrima2CapaB(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima2CapaB().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaB() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage2CapaB(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaB().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima3CapaB() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima3CapaB(articuloFichaTecnica.getIdMateriaPrima3CapaB().toString());
            articuloFichaTecnicaForm.setMateriaPrima3CapaB(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima3CapaB().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaB() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage3CapaB(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaB().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima4CapaB() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima4CapaB(articuloFichaTecnica.getIdMateriaPrima4CapaB().toString());
            articuloFichaTecnicaForm.setMateriaPrima4CapaB(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima4CapaB().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaB() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage4CapaB(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaB().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima5CapaB() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima5CapaB(articuloFichaTecnica.getIdMateriaPrima5CapaB().toString());
            articuloFichaTecnicaForm.setMateriaPrima5CapaB(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima5CapaB().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaB() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage5CapaB(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaB().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima6CapaB() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima6CapaB(articuloFichaTecnica.getIdMateriaPrima6CapaB().toString());
            articuloFichaTecnicaForm.setMateriaPrima6CapaB(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima6CapaB().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaB() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage6CapaB(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaB().toString());
        }

        //CAPA C
        if(articuloFichaTecnica.getIdMateriaPrima1CapaC() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima1CapaC(articuloFichaTecnica.getIdMateriaPrima1CapaC().toString());
            articuloFichaTecnicaForm.setMateriaPrima1CapaC(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima1CapaC().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaC() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage1CapaC(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaC().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima2CapaC() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima2CapaC(articuloFichaTecnica.getIdMateriaPrima2CapaC().toString());
            articuloFichaTecnicaForm.setMateriaPrima2CapaC(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima2CapaC().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaC() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage2CapaC(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaC().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima3CapaC() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima3CapaC(articuloFichaTecnica.getIdMateriaPrima3CapaC().toString());
            articuloFichaTecnicaForm.setMateriaPrima3CapaC(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima3CapaC().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaC() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage3CapaC(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaC().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima4CapaC() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima4CapaC(articuloFichaTecnica.getIdMateriaPrima4CapaC().toString());
            articuloFichaTecnicaForm.setMateriaPrima4CapaC(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima4CapaC().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaC() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage4CapaC(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaC().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima5CapaC() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima5CapaC(articuloFichaTecnica.getIdMateriaPrima5CapaC().toString());
            articuloFichaTecnicaForm.setMateriaPrima5CapaC(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima5CapaC().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaC() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage5CapaC(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaC().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima6CapaC() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima6CapaC(articuloFichaTecnica.getIdMateriaPrima6CapaC().toString());
            articuloFichaTecnicaForm.setMateriaPrima6CapaC(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima6CapaC().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaC() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage6CapaC(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaC().toString());
        }
        
        //CAPA D
        if(articuloFichaTecnica.getIdMateriaPrima1CapaD() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima1CapaD(articuloFichaTecnica.getIdMateriaPrima1CapaD().toString());
            articuloFichaTecnicaForm.setMateriaPrima1CapaD(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima1CapaD().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaD() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage1CapaD(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaD().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima2CapaD() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima2CapaD(articuloFichaTecnica.getIdMateriaPrima2CapaD().toString());
            articuloFichaTecnicaForm.setMateriaPrima2CapaD(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima2CapaD().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaD() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage2CapaD(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaD().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima3CapaD() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima3CapaD(articuloFichaTecnica.getIdMateriaPrima3CapaD().toString());
            articuloFichaTecnicaForm.setMateriaPrima3CapaD(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima3CapaD().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaD() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage3CapaD(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaD().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima4CapaD() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima4CapaD(articuloFichaTecnica.getIdMateriaPrima4CapaD().toString());
            articuloFichaTecnicaForm.setMateriaPrima4CapaD(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima4CapaD().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaD() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage4CapaD(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaD().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima5CapaD() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima5CapaD(articuloFichaTecnica.getIdMateriaPrima5CapaD().toString());
            articuloFichaTecnicaForm.setMateriaPrima5CapaD(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima5CapaD().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaD() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage5CapaD(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaD().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima6CapaD() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima6CapaD(articuloFichaTecnica.getIdMateriaPrima6CapaD().toString());
            articuloFichaTecnicaForm.setMateriaPrima6CapaD(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima6CapaD().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaD() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage6CapaD(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaD().toString());
        }
        
        //CAPA E
        if(articuloFichaTecnica.getIdMateriaPrima1CapaE() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima1CapaE(articuloFichaTecnica.getIdMateriaPrima1CapaE().toString());
            articuloFichaTecnicaForm.setMateriaPrima1CapaE(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima1CapaE().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaE() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage1CapaE(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaE().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima2CapaE() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima2CapaE(articuloFichaTecnica.getIdMateriaPrima2CapaE().toString());
            articuloFichaTecnicaForm.setMateriaPrima2CapaE(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima2CapaE().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaE() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage2CapaE(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaE().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima3CapaE() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima3CapaE(articuloFichaTecnica.getIdMateriaPrima3CapaE().toString());
            articuloFichaTecnicaForm.setMateriaPrima3CapaE(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima3CapaE().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaE() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage3CapaE(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaE().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima4CapaE() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima4CapaE(articuloFichaTecnica.getIdMateriaPrima4CapaE().toString());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaE() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage4CapaE(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaE().toString());
            articuloFichaTecnicaForm.setMateriaPrima4CapaE(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima4CapaE().toString()));
        }
        if(articuloFichaTecnica.getIdMateriaPrima5CapaE() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima5CapaE(articuloFichaTecnica.getIdMateriaPrima5CapaE().toString());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaE() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage5CapaE(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaE().toString());
            articuloFichaTecnicaForm.setMateriaPrima5CapaE(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima5CapaE().toString()));
        }
        if(articuloFichaTecnica.getIdMateriaPrima6CapaE() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima6CapaE(articuloFichaTecnica.getIdMateriaPrima6CapaE().toString());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaE() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage6CapaE(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaE().toString());
            articuloFichaTecnicaForm.setMateriaPrima6CapaE(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima6CapaE().toString()));
        }

        //CAPA F
        if(articuloFichaTecnica.getIdMateriaPrima1CapaF() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima1CapaF(articuloFichaTecnica.getIdMateriaPrima1CapaF().toString());
            articuloFichaTecnicaForm.setMateriaPrima1CapaF(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima1CapaF().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaF() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage1CapaF(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaF().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima2CapaF() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima2CapaF(articuloFichaTecnica.getIdMateriaPrima2CapaF().toString());
            articuloFichaTecnicaForm.setMateriaPrima2CapaF(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima2CapaF().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaF() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage2CapaF(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaF().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima3CapaF() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima3CapaF(articuloFichaTecnica.getIdMateriaPrima3CapaF().toString());
            articuloFichaTecnicaForm.setMateriaPrima3CapaF(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima3CapaF().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaF() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage3CapaF(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaF().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima4CapaF() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima4CapaF(articuloFichaTecnica.getIdMateriaPrima4CapaF().toString());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaF() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage4CapaF(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaF().toString());
            articuloFichaTecnicaForm.setMateriaPrima4CapaF(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima4CapaF().toString()));
        }
        if(articuloFichaTecnica.getIdMateriaPrima5CapaF() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima5CapaF(articuloFichaTecnica.getIdMateriaPrima5CapaF().toString());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaF() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage5CapaF(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaF().toString());
            articuloFichaTecnicaForm.setMateriaPrima5CapaF(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima5CapaF().toString()));
        }
        if(articuloFichaTecnica.getIdMateriaPrima6CapaF() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima6CapaF(articuloFichaTecnica.getIdMateriaPrima6CapaF().toString());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaF() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage6CapaF(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaF().toString());
            articuloFichaTecnicaForm.setMateriaPrima6CapaF(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima6CapaF().toString()));
        }
        
        //CAPA G
        if(articuloFichaTecnica.getIdMateriaPrima1CapaG() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima1CapaG(articuloFichaTecnica.getIdMateriaPrima1CapaG().toString());
            articuloFichaTecnicaForm.setMateriaPrima1CapaG(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima1CapaG().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaG() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage1CapaG(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaG().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima2CapaG() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima2CapaG(articuloFichaTecnica.getIdMateriaPrima2CapaG().toString());
            articuloFichaTecnicaForm.setMateriaPrima2CapaG(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima2CapaG().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaG() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage2CapaG(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaG().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima3CapaG() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima3CapaG(articuloFichaTecnica.getIdMateriaPrima3CapaG().toString());
            articuloFichaTecnicaForm.setMateriaPrima3CapaG(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima3CapaG().toString()));
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaG() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage3CapaG(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaG().toString());
        }
        if(articuloFichaTecnica.getIdMateriaPrima4CapaG() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima4CapaG(articuloFichaTecnica.getIdMateriaPrima4CapaG().toString());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaG() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage4CapaG(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaG().toString());
            articuloFichaTecnicaForm.setMateriaPrima4CapaG(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima4CapaG().toString()));
        }
        if(articuloFichaTecnica.getIdMateriaPrima5CapaG() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima5CapaG(articuloFichaTecnica.getIdMateriaPrima5CapaG().toString());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaG() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage5CapaG(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaG().toString());
            articuloFichaTecnicaForm.setMateriaPrima5CapaG(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima5CapaG().toString()));
        }
        if(articuloFichaTecnica.getIdMateriaPrima6CapaG() != null) {
            articuloFichaTecnicaForm.setIdMateriaPrima6CapaG(articuloFichaTecnica.getIdMateriaPrima6CapaG().toString());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaG() != null) {
            articuloFichaTecnicaForm.setMateriaPrimaPorcentage6CapaG(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaG().toString());
            articuloFichaTecnicaForm.setMateriaPrima6CapaG(materiasPrima.get(articuloFichaTecnica.getIdMateriaPrima6CapaG().toString()));
        }
                
        //RESUMEN CAPAS
        if(articuloFichaTecnica.getResumenCapaA() != null) {
            articuloFichaTecnicaForm.setResumenCapaA(articuloFichaTecnica.getResumenCapaA().toString());
        }
        if(articuloFichaTecnica.getResumenCapaB() != null) {
            articuloFichaTecnicaForm.setResumenCapaB(articuloFichaTecnica.getResumenCapaB().toString());
        }
        if(articuloFichaTecnica.getResumenCapaC() != null) {
            articuloFichaTecnicaForm.setResumenCapaC(articuloFichaTecnica.getResumenCapaC().toString());
        }
        if(articuloFichaTecnica.getResumenCapaD() != null) {
            articuloFichaTecnicaForm.setResumenCapaD(articuloFichaTecnica.getResumenCapaD().toString());
        }
        if(articuloFichaTecnica.getResumenCapaE() != null) {
            articuloFichaTecnicaForm.setResumenCapaE(articuloFichaTecnica.getResumenCapaE().toString());
        }
        if(articuloFichaTecnica.getResumenCapaF() != null) {
            articuloFichaTecnicaForm.setResumenCapaF(articuloFichaTecnica.getResumenCapaF().toString());
        }
        if(articuloFichaTecnica.getResumenCapaG() != null) {
            articuloFichaTecnicaForm.setResumenCapaG(articuloFichaTecnica.getResumenCapaG().toString());
        }
        
        articuloFichaTecnicaForm.setAction("view");
        model.addAttribute("articuloFichaTecnicaForm", articuloFichaTecnicaForm);  
        model.addAttribute("titleArticuloFichaTecnica", "Ver Ficha Técnica de Articulo");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("articuloFichaTecnicaName", "Ficha Técnica: "+articuloFichaTecnica.getId());        
        
        
        List<ArticuloFichaTecnicaModel> fichasTecnicas = articuloFichaTecnicaService.getAllByArticulo(articulo.getId());
        
        Integer ultimaVersion = null;
        if(fichasTecnicas != null && !fichasTecnicas.isEmpty()) {
            ultimaVersion = fichasTecnicas.get(0).getId();
        }
        List<ArticuloFichaTecnicaDto> articuloFichasTecnicaDtos = new ArrayList<ArticuloFichaTecnicaDto>();
        for(ArticuloFichaTecnicaModel fichasTecnica: fichasTecnicas) {
            ArticuloFichaTecnicaDto fichaTecnicaDto = new ArticuloFichaTecnicaDto();
            fichaTecnicaDto.setPk(fichasTecnica.getId().toString());
            fichaTecnicaDto.setFechaAlta(fichasTecnica.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            fichaTecnicaDto.setVersion(fichasTecnica.getVersion().toString());
            if(ultimaVersion !=null && ultimaVersion == fichasTecnica.getId()){
                fichaTecnicaDto.setUltimaVersion("true");
            }
            articuloFichasTecnicaDtos.add(fichaTecnicaDto);
        }
        
        TipoService tipoService = new TipoServiceImpl();
        Map<String,String> materiales = new LinkedHashMap<String,String>();
        List<TipoModel> materialesModel = tipoService.getByType("articuloMaterial");        
        
        if(materialesModel != null && !materialesModel.isEmpty()){
            for(TipoModel tipoModel :materialesModel) {
                materiales.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        

        Map<String,String> tipoBobinas = new LinkedHashMap<String,String>();
        List<TipoModel> tipoBobinasModel = tipoService.getByType("tipoBobina");        
        
        if(tipoBobinasModel != null && !tipoBobinasModel.isEmpty()){
            for(TipoModel tipoModel :tipoBobinasModel) {
                tipoBobinas.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        

        Map<String,String> lineaTintas = new LinkedHashMap<String,String>();
        List<TipoModel> lineaTintasModel = tipoService.getByType("lineaTintas");        
        
        if(lineaTintasModel != null && !lineaTintasModel.isEmpty()){
            for(TipoModel tipoModel :lineaTintasModel) {
                lineaTintas.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        

        Map<String,String> lineaSolventes = new LinkedHashMap<String,String>();
        List<TipoModel> lineaSolventesModel = tipoService.getByType("lineaSolventes");        
        
        if(lineaSolventesModel != null && !lineaSolventesModel.isEmpty()){
            for(TipoModel tipoModel :lineaTintasModel) {
                lineaSolventes.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        

        Map<String,String> bultosEn = new LinkedHashMap<String,String>();
        List<TipoModel> bultosEnModel = tipoService.getByType("bultosEn");        
        
        if(bultosEnModel != null && !bultosEnModel.isEmpty()){
            for(TipoModel tipoModel :bultosEnModel) {
                bultosEn.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        

        Map<String,String> palets = new LinkedHashMap<String,String>();
        List<TipoModel> paletsModel = tipoService.getByType("palets");        
        
        if(paletsModel != null && !paletsModel.isEmpty()){
            for(TipoModel tipoModel :paletsModel) {
                palets.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        
                
        Map<String,String> colores = new LinkedHashMap<String,String>();
        List<TipoModel> coloresModel = tipoService.getByType("articuloColor");        
        
        if(coloresModel != null && !coloresModel.isEmpty()){
            for(TipoModel tipoModel :coloresModel) {
                colores.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        
        
        model.addAttribute("colorList", colores);        
        model.addAttribute("materialList", materiales);    
        model.addAttribute("tipoBobinaList", tipoBobinas);
        model.addAttribute("lineaTintasList", lineaTintas);  
        model.addAttribute("lineaSolventesList", lineaSolventes);  
        model.addAttribute("bultoEnList", bultosEn);  
        model.addAttribute("paletList", palets);  
        model.addAttribute("articulo", articulo.getDenominacion());        
        model.addAttribute("articuloPk", articulo.getId());
        model.addAttribute("operacion", "view");
        model.addAttribute("articuloFichasTecnica", articuloFichasTecnicaDtos);   
                
        return "/articulofichatecnica/articulofichatecnica";      
    }    
    
    @ResponseBody
    @RequestMapping(value = "/articulofichatecnica/getMateriaPrimaByProveedor/{idProveedor}", method = RequestMethod.GET)
    public List<MateriaPrimaBean> getMateriaPrimaByProveedor(@PathVariable String idProveedor, HttpServletRequest req, ModelMap model) throws Exception {
        
        List<MateriaPrimaBean> result = new ArrayList<MateriaPrimaBean>();
        if(idProveedor != null && !idProveedor.isEmpty()) {
            MateriaPrimaService materiaPrimaService = new MateriaPrimaServiceImpl();
            List<MateriaPrimaModel> materiasPrimas = materiaPrimaService.getAllByIdProveedor(Integer.valueOf(idProveedor));
            if(!materiasPrimas.isEmpty()) {
                for(MateriaPrimaModel materiaPrima: materiasPrimas) {
                    MateriaPrimaBean bean = new MateriaPrimaBean();
                    bean.setId(materiaPrima.getId().toString());
                    bean.setNombre(materiaPrima.getDescripcion());
                    
                    result.add(bean);
                }
            }
        }
        
        return result;
    }
    
    @RequestMapping(value = "/articulofichatecnica/clone/{articuloFichaTecnicapk}", method = RequestMethod.GET)
    public String cloneArticulo(@PathVariable String articuloFichaTecnicapk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(articuloFichaTecnicapk == null) {
            model.addAttribute("errorMessage", "Error: Ficha Técnica de Articulo inválido");         
            return "/error";                
        }
        
        ArticuloFichaTecnicaService articuloFichaTecnicaService = new ArticuloFichaTecnicaServiceImpl();   
        ArticuloFichaTecnicaModel articuloFichaTecnica = articuloFichaTecnicaService.getByPk(Integer.valueOf(articuloFichaTecnicapk));
        if(articuloFichaTecnica == null) {
            model.addAttribute("errorMessage", "Error: Ficha Técnica de Articulo inválido. No ha sido encontrado.");         
            return "/error";    
        }
        
        ArticuloService articuloService = new ArticuloServiceImpl();   
        ArticuloModel articulo = articuloService.getByPk(articuloFichaTecnica.getIdArticulo());
        
        if(articulo == null) {            
            model.addAttribute("errorMessage", "Error: Articulo inválido. No ha sido encontrado.");         
            return "/error";     
        }
        
        
        ArticuloFichaTecnicaModel articuloFichaTecnicaModel = new ArticuloFichaTecnicaModel();
        articuloFichaTecnicaModel.setIdArticulo(Integer.valueOf(articulo.getId()));
        articuloFichaTecnicaModel.setIdUsuarioCreacion(Integer.valueOf(Utils.getUserLoggedId(req)));
        Date today = new Date();
        articuloFichaTecnicaModel.setFechaAlta(today);

        if(articuloFichaTecnica.getObservaciones() != null){
            articuloFichaTecnicaModel.setObservaciones(articuloFichaTecnica.getObservaciones());
        }
        if(articuloFichaTecnica.getEspecificacion() != null){
            articuloFichaTecnicaModel.setEspecificacion(articuloFichaTecnica.getEspecificacion());
        }
        if(articuloFichaTecnica.getUrlEspecificacion() != null){
            articuloFichaTecnicaModel.setUrlEspecificacion(articuloFichaTecnica.getUrlEspecificacion());
        }
        if(articuloFichaTecnica.getIdMaterial() != null){
            articuloFichaTecnicaModel.setIdMaterial(articuloFichaTecnica.getIdMaterial());
        }
        if(articuloFichaTecnica.getLamina() != null){
            articuloFichaTecnicaModel.setLamina(articuloFichaTecnica.getLamina());
        }
        if(articuloFichaTecnica.getBobina() != null){
            articuloFichaTecnicaModel.setBobina(articuloFichaTecnica.getBobina());
        }
        if(articuloFichaTecnica.getIdColorA() != null){
            articuloFichaTecnicaModel.setIdColorA(articuloFichaTecnica.getIdColorA());
        }        
        if(articuloFichaTecnica.getIdColorB() != null){
            articuloFichaTecnicaModel.setIdColorB(articuloFichaTecnica.getIdColorB());
        }
        if(articuloFichaTecnica.getIdColorC() != null){
            articuloFichaTecnicaModel.setIdColorC(articuloFichaTecnica.getIdColorC());
        }
        if(articuloFichaTecnica.getIdColorD() != null){
            articuloFichaTecnicaModel.setIdColorD(articuloFichaTecnica.getIdColorD());
        }
        if(articuloFichaTecnica.getIdColorE() != null){
            articuloFichaTecnicaModel.setIdColorE(articuloFichaTecnica.getIdColorE());
        }
        if(articuloFichaTecnica.getIdColorF() != null){
            articuloFichaTecnicaModel.setIdColorF(articuloFichaTecnica.getIdColorF());
        }
        if(articuloFichaTecnica.getIdColorG() != null){
            articuloFichaTecnicaModel.setIdColorG(articuloFichaTecnica.getIdColorG());
        }
        if(articuloFichaTecnica.getAncho() != null){
            articuloFichaTecnicaModel.setAncho(articuloFichaTecnica.getAncho());
        }
        if(articuloFichaTecnica.getFuelleIzquierdo() != null){
            articuloFichaTecnicaModel.setFuelleIzquierdo(articuloFichaTecnica.getFuelleIzquierdo());
        }
        if(articuloFichaTecnica.getFuelleDerecho() != null){
            articuloFichaTecnicaModel.setFuelleDerecho(articuloFichaTecnica.getFuelleDerecho());
        }
        if(articuloFichaTecnica.getEspesorNominal() != null){
            articuloFichaTecnicaModel.setEspesorNominal(articuloFichaTecnica.getEspesorNominal());
        }
        if(articuloFichaTecnica.getEspesorPromedio() != null){
            articuloFichaTecnicaModel.setEspesorPromedio(articuloFichaTecnica.getEspesorPromedio());
        }
        if(articuloFichaTecnica.getEspesorArticulo() != null){
            articuloFichaTecnicaModel.setEspesorArticulo(articuloFichaTecnica.getEspesorArticulo());
        }
        if(articuloFichaTecnica.getCantidadCapas() != null){
            articuloFichaTecnicaModel.setCantidadCapas(articuloFichaTecnica.getCantidadCapas());
        }
        if(articuloFichaTecnica.getPesoEspecifico() != null){
            articuloFichaTecnicaModel.setPesoEspecifico(articuloFichaTecnica.getPesoEspecifico());
        }
        if(articuloFichaTecnica.getAnchoBruto() != null){
            articuloFichaTecnicaModel.setAnchoBruto(articuloFichaTecnica.getAnchoBruto());
        }
        if(articuloFichaTecnica.getBobinadoBarras() != null){
            articuloFichaTecnicaModel.setBobinadoBarras(articuloFichaTecnica.getBobinadoBarras());
        }
        if(articuloFichaTecnica.getMetros() != null){
            articuloFichaTecnicaModel.setMetros(articuloFichaTecnica.getMetros());
        }
        if(articuloFichaTecnica.getPeso() != null){
            articuloFichaTecnicaModel.setPeso(articuloFichaTecnica.getPeso());
        }
        if(articuloFichaTecnica.getDiametro() != null){
            articuloFichaTecnicaModel.setDiametro(articuloFichaTecnica.getDiametro());
        }
        if(articuloFichaTecnica.getEmpalmes() != null){
            articuloFichaTecnicaModel.setEmpalmes(articuloFichaTecnica.getEmpalmes());
        }
        if(articuloFichaTecnica.getIdTipoBobina() != null){
            articuloFichaTecnicaModel.setIdTipoBobina(articuloFichaTecnica.getIdTipoBobina());
        }
        if(articuloFichaTecnica.getDiametro2() != null){
            articuloFichaTecnicaModel.setDiametro2(articuloFichaTecnica.getDiametro2());
        }
        if(articuloFichaTecnica.getLargo() != null){
            articuloFichaTecnicaModel.setLargo(articuloFichaTecnica.getLargo());
        }
        if(articuloFichaTecnica.getEspesor() != null){
            articuloFichaTecnicaModel.setEspesor(articuloFichaTecnica.getEspesor());
        }
        if(articuloFichaTecnica.getPeso2() != null){
            articuloFichaTecnicaModel.setPeso2(articuloFichaTecnica.getPeso2());
        }
        if(articuloFichaTecnica.getIdLineaTintas() != null){
            articuloFichaTecnicaModel.setIdLineaTintas(articuloFichaTecnica.getIdLineaTintas());
        }
        if(articuloFichaTecnica.getIdLineaSolventes() != null){
            articuloFichaTecnicaModel.setIdLineaSolventes(articuloFichaTecnica.getIdLineaSolventes());
        }
        if(articuloFichaTecnica.getIdBultosEn() != null){
            articuloFichaTecnicaModel.setIdBultosEn(articuloFichaTecnica.getIdBultosEn());
        }
        if(articuloFichaTecnica.getBultosPorPalet() != null){
            articuloFichaTecnicaModel.setBultosPorPalet(articuloFichaTecnica.getBultosPorPalet());
        }
        if(articuloFichaTecnica.getIdPalet() != null){
            articuloFichaTecnicaModel.setIdPalet(articuloFichaTecnica.getIdPalet());
        }
        if(articuloFichaTecnica.getPosicionPalet() != null){
            articuloFichaTecnicaModel.setPosicionPalet(articuloFichaTecnica.getPosicionPalet());
        }
        //CAPA A
        if(articuloFichaTecnica.getIdMateriaPrima1CapaA() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima1CapaA(articuloFichaTecnica.getIdMateriaPrima1CapaA());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaA() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage1CapaA(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaA());
        }
        if(articuloFichaTecnica.getIdMateriaPrima2CapaA() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima2CapaA(articuloFichaTecnica.getIdMateriaPrima2CapaA());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaA() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage2CapaA(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaA());
        }
        if(articuloFichaTecnica.getIdMateriaPrima3CapaA() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima3CapaA(articuloFichaTecnica.getIdMateriaPrima3CapaA());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaA() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage3CapaA(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaA());
        }
        if(articuloFichaTecnica.getIdMateriaPrima4CapaA() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima4CapaA(articuloFichaTecnica.getIdMateriaPrima4CapaA());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaA() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage4CapaA(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaA());
        }
        if(articuloFichaTecnica.getIdMateriaPrima5CapaA() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima5CapaA(articuloFichaTecnica.getIdMateriaPrima5CapaA());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaA() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage5CapaA(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaA());
        }
        if(articuloFichaTecnica.getIdMateriaPrima6CapaA() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima6CapaA(articuloFichaTecnica.getIdMateriaPrima6CapaA());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaA() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage6CapaA(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaA());
        }
        //CAPA B
        if(articuloFichaTecnica.getIdMateriaPrima1CapaB() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima1CapaB(articuloFichaTecnica.getIdMateriaPrima1CapaB());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaB() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage1CapaB(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaB());
        }
        if(articuloFichaTecnica.getIdMateriaPrima2CapaB() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima2CapaB(articuloFichaTecnica.getIdMateriaPrima2CapaB());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaB() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage2CapaB(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaB());
        }
        if(articuloFichaTecnica.getIdMateriaPrima3CapaB() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima3CapaB(articuloFichaTecnica.getIdMateriaPrima3CapaB());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaB() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage3CapaB(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaB());
        }
        if(articuloFichaTecnica.getIdMateriaPrima4CapaB() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima4CapaB(articuloFichaTecnica.getIdMateriaPrima4CapaB());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaB() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage4CapaB(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaB());
        }
        if(articuloFichaTecnica.getIdMateriaPrima5CapaB() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima5CapaB(articuloFichaTecnica.getIdMateriaPrima5CapaB());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaB() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage5CapaB(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaB());
        }
        if(articuloFichaTecnica.getIdMateriaPrima6CapaB() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima6CapaB(articuloFichaTecnica.getIdMateriaPrima6CapaB());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaB() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage6CapaB(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaB());
        }                

        //CAPA C
        if(articuloFichaTecnica.getIdMateriaPrima1CapaC() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima1CapaC(articuloFichaTecnica.getIdMateriaPrima1CapaC());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaC() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage1CapaC(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaC());
        }
        if(articuloFichaTecnica.getIdMateriaPrima2CapaC() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima2CapaC(articuloFichaTecnica.getIdMateriaPrima2CapaC());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaC() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage2CapaC(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaC());
        }
        if(articuloFichaTecnica.getIdMateriaPrima3CapaC() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima3CapaC(articuloFichaTecnica.getIdMateriaPrima3CapaC());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaC() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage3CapaC(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaC());
        }
        if(articuloFichaTecnica.getIdMateriaPrima4CapaC() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima4CapaC(articuloFichaTecnica.getIdMateriaPrima4CapaC());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaC() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage4CapaC(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaC());
        }
        if(articuloFichaTecnica.getIdMateriaPrima5CapaC() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima5CapaC(articuloFichaTecnica.getIdMateriaPrima5CapaC());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaC() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage5CapaC(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaC());
        }
        if(articuloFichaTecnica.getIdMateriaPrima6CapaC() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima6CapaC(articuloFichaTecnica.getIdMateriaPrima6CapaC());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaC() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage6CapaC(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaC());
        } 
        
        //CAPA D
        if(articuloFichaTecnica.getIdMateriaPrima1CapaD() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima1CapaD(articuloFichaTecnica.getIdMateriaPrima1CapaD());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaD() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage1CapaD(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaD());
        }
        if(articuloFichaTecnica.getIdMateriaPrima2CapaD() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima2CapaD(articuloFichaTecnica.getIdMateriaPrima2CapaD());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaD() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage2CapaD(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaD());
        }
        if(articuloFichaTecnica.getIdMateriaPrima3CapaD() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima3CapaD(articuloFichaTecnica.getIdMateriaPrima3CapaD());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaD() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage3CapaD(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaD());
        }
        if(articuloFichaTecnica.getIdMateriaPrima4CapaD() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima4CapaD(articuloFichaTecnica.getIdMateriaPrima4CapaD());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaD() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage4CapaD(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaD());
        }
        if(articuloFichaTecnica.getIdMateriaPrima5CapaD() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima5CapaD(articuloFichaTecnica.getIdMateriaPrima5CapaD());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaD() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage5CapaD(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaD());
        }
        if(articuloFichaTecnica.getIdMateriaPrima6CapaD() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima6CapaD(articuloFichaTecnica.getIdMateriaPrima6CapaD());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaD() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage6CapaD(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaD());
        } 
        
        //CAPA E
        if(articuloFichaTecnica.getIdMateriaPrima1CapaE() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima1CapaE(articuloFichaTecnica.getIdMateriaPrima1CapaE());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaE() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage1CapaE(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaE());
        }
        if(articuloFichaTecnica.getIdMateriaPrima2CapaE() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima2CapaE(articuloFichaTecnica.getIdMateriaPrima2CapaE());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaE() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage2CapaE(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaE());
        }
        if(articuloFichaTecnica.getIdMateriaPrima3CapaE() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima3CapaE(articuloFichaTecnica.getIdMateriaPrima3CapaE());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaE() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage3CapaE(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaE());
        }
        if(articuloFichaTecnica.getIdMateriaPrima4CapaE() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima4CapaE(articuloFichaTecnica.getIdMateriaPrima4CapaE());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaE() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage4CapaE(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaE());
        }
        if(articuloFichaTecnica.getIdMateriaPrima5CapaE() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima5CapaE(articuloFichaTecnica.getIdMateriaPrima5CapaE());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaE() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage5CapaE(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaE());
        }
        if(articuloFichaTecnica.getIdMateriaPrima6CapaE() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima6CapaE(articuloFichaTecnica.getIdMateriaPrima6CapaE());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaE() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage6CapaE(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaE());
        } 
        
        //CAPA F
        if(articuloFichaTecnica.getIdMateriaPrima1CapaF() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima1CapaF(articuloFichaTecnica.getIdMateriaPrima1CapaF());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaF() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage1CapaF(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaF());
        }
        if(articuloFichaTecnica.getIdMateriaPrima2CapaF() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima2CapaF(articuloFichaTecnica.getIdMateriaPrima2CapaF());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaF() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage2CapaF(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaF());
        }
        if(articuloFichaTecnica.getIdMateriaPrima3CapaF() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima3CapaF(articuloFichaTecnica.getIdMateriaPrima3CapaF());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaF() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage3CapaF(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaF());
        }
        if(articuloFichaTecnica.getIdMateriaPrima4CapaF() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima4CapaF(articuloFichaTecnica.getIdMateriaPrima4CapaF());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaF() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage4CapaF(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaF());
        }
        if(articuloFichaTecnica.getIdMateriaPrima5CapaF() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima5CapaF(articuloFichaTecnica.getIdMateriaPrima5CapaF());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaF() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage5CapaF(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaF());
        }
        if(articuloFichaTecnica.getIdMateriaPrima6CapaF() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima6CapaF(articuloFichaTecnica.getIdMateriaPrima6CapaF());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaF() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage6CapaF(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaF());
        } 
        
        //CAPA G
        if(articuloFichaTecnica.getIdMateriaPrima1CapaG() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima1CapaG(articuloFichaTecnica.getIdMateriaPrima1CapaG());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaG() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage1CapaG(articuloFichaTecnica.getMateriaPrimaPorcentage1CapaG());
        }
        if(articuloFichaTecnica.getIdMateriaPrima2CapaG() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima2CapaG(articuloFichaTecnica.getIdMateriaPrima2CapaG());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaG() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage2CapaG(articuloFichaTecnica.getMateriaPrimaPorcentage2CapaG());
        }
        if(articuloFichaTecnica.getIdMateriaPrima3CapaG() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima3CapaG(articuloFichaTecnica.getIdMateriaPrima3CapaG());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaG() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage3CapaG(articuloFichaTecnica.getMateriaPrimaPorcentage3CapaG());
        }
        if(articuloFichaTecnica.getIdMateriaPrima4CapaG() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima4CapaG(articuloFichaTecnica.getIdMateriaPrima4CapaG());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaG() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage4CapaG(articuloFichaTecnica.getMateriaPrimaPorcentage4CapaG());
        }
        if(articuloFichaTecnica.getIdMateriaPrima5CapaG() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima5CapaG(articuloFichaTecnica.getIdMateriaPrima5CapaG());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaG() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage5CapaG(articuloFichaTecnica.getMateriaPrimaPorcentage5CapaG());
        }
        if(articuloFichaTecnica.getIdMateriaPrima6CapaG() != null){
            articuloFichaTecnicaModel.setIdMateriaPrima6CapaG(articuloFichaTecnica.getIdMateriaPrima6CapaG());
        }
        if(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaG() != null){
            articuloFichaTecnicaModel.setMateriaPrimaPorcentage6CapaG(articuloFichaTecnica.getMateriaPrimaPorcentage6CapaG());
        } 
        
        if(articuloFichaTecnica.getResumenCapaA() != null){
            articuloFichaTecnicaModel.setResumenCapaA(articuloFichaTecnica.getResumenCapaA());
        }
        if(articuloFichaTecnica.getResumenCapaB() != null){
            articuloFichaTecnicaModel.setResumenCapaB(articuloFichaTecnica.getResumenCapaB());
        }
        if(articuloFichaTecnica.getResumenCapaC() != null){
            articuloFichaTecnicaModel.setResumenCapaC(articuloFichaTecnica.getResumenCapaC());
        }
        if(articuloFichaTecnica.getResumenCapaD() != null){
            articuloFichaTecnicaModel.setResumenCapaD(articuloFichaTecnica.getResumenCapaD());
        }
        if(articuloFichaTecnica.getResumenCapaE() != null){
            articuloFichaTecnicaModel.setResumenCapaE(articuloFichaTecnica.getResumenCapaE());
        }
        if(articuloFichaTecnica.getResumenCapaF() != null){
            articuloFichaTecnicaModel.setResumenCapaF(articuloFichaTecnica.getResumenCapaF());
        }
        if(articuloFichaTecnica.getResumenCapaG() != null){
            articuloFichaTecnicaModel.setResumenCapaG(articuloFichaTecnica.getResumenCapaG());
        }
           
        articuloFichaTecnicaService.save(articuloFichaTecnicaModel);

        Double version = (articuloFichaTecnicaModel.getId() + 10) / 10.0;
        articuloFichaTecnicaModel.setVersion(version);

        articuloFichaTecnicaService.save(articuloFichaTecnicaModel);

        return "redirect:/articulofichatecnica/"+articulo.getId();    
    }
}
