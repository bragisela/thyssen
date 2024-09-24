/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

 
import com.empresa.thyssenplastic.controller.form.ArticuloForm;
import com.empresa.thyssenplastic.controller.form.UserForm;
import com.empresa.thyssenplastic.dto.ArticuloDto;
import com.empresa.thyssenplastic.model.ArticuloModel;
import com.empresa.thyssenplastic.model.ClienteModel;
import com.empresa.thyssenplastic.model.TipoModel;
import com.empresa.thyssenplastic.service.ArticuloService;
import com.empresa.thyssenplastic.service.ClienteService;
import com.empresa.thyssenplastic.service.TipoService;
import com.empresa.thyssenplastic.service.impl.ArticuloServiceImpl;
import com.empresa.thyssenplastic.service.impl.ClienteServiceImpl;
import com.empresa.thyssenplastic.service.impl.TipoServiceImpl;
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
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author gusta
 */
@Controller
public class ArticuloController {
    

    @RequestMapping(value = "/articulo", method = RequestMethod.GET)
    public String getHomeArticulo(HttpServletRequest req, ModelMap model) {

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        ArticuloForm articuloForm = new ArticuloForm();
        articuloForm.setPk("-1");
        articuloForm.setAction("add");
        model.addAttribute("articuloForm", articuloForm);  
        model.addAttribute("titleArticulo", "Nuevo Articulo");  
        model.addAttribute("buttonLabel", "Guardar");
        
        ArticuloService articuloService = new ArticuloServiceImpl();   
        List<ArticuloModel> articulos = articuloService.getAll();

        TipoService tipoService = new TipoServiceImpl();   
        Map<String,String> rubros = new LinkedHashMap<String,String>();
        List<TipoModel> rubrosModel = tipoService.getByType("rubroArticulo");

        Map<String,String> unidadesVenta = new LinkedHashMap<String,String>();
        List<TipoModel> unidadesVentaModel = tipoService.getByType("unidadVentaArticulo");

        Map<String,String> tipoMateriales = new LinkedHashMap<String,String>();
        List<TipoModel> tipoMaterialesModel = tipoService.getByType("tipoMaterialesArticulo");
        
        ClienteService clienteService = new ClienteServiceImpl();   
        Map<String,String> clientes = new LinkedHashMap<String,String>();
        List<ClienteModel> clientesModel = clienteService.getAll();

        List<ArticuloDto> articulosDtos = new ArrayList<ArticuloDto>();
        for(ArticuloModel articulo: articulos) {
            ArticuloDto articuloDto = new ArticuloDto();
            articuloDto.setPk(articulo.getId().toString());
            if(articulo.getIdCliente() != null) {
                String cliente = "";
                if(clientesModel != null && !clientesModel.isEmpty()){
                    for(ClienteModel clienteModel :clientesModel) {
                        if(articulo.getIdCliente() == clienteModel.getId()) {
                            cliente = clienteModel.getRazonSocial();
                            System.out.println("*** cliente:"+clienteModel.getRazonSocial());
                        }
                    }
                }                
                articuloDto.setCliente(cliente);
            }                                    
            if(articulo.getIdRubro() != null) {
                String rubro = "";
                Integer artTipo = 0;
                Integer artRubro = 0;
                if(rubrosModel != null && !rubrosModel.isEmpty()){
                    for(TipoModel tipoModel :rubrosModel) {
                        // Mauro nota: tuve que forzar que la comparación del if sea como string porque por algún motivo de otra galaxia
                        // el condicional que estaba ( que estaba correctísimo ) como Integers fallaba para algunos casos. 
                        artTipo = tipoModel.getId();
                        artRubro = articulo.getIdRubro();
                        
                        if(artTipo.toString().equalsIgnoreCase(artRubro.toString())) {
                            rubro = tipoModel.getValor();
                        }
                    }
                }                
                articuloDto.setRubro(rubro);
            }            
            articuloDto.setDenominacion(articulo.getDenominacion());
            if(articulo.getAncho() != null){
                articuloDto.setAncho(articulo.getAncho().toString());
            }
            if(articulo.getAlto() != null){
                articuloDto.setAlto(articulo.getAlto().toString());
            }
            if(articulo.getEspesor() != null){
                articuloDto.setEspesor(articulo.getEspesor().toString());
            }
            if(articulo.getScrap()!= null){
                articuloDto.setScrap(articulo.getScrap().toString());
            }
            if(articulo.getReferenciaAdministrativa() != null){
                articuloDto.setReferenciaAdministrativa(articulo.getReferenciaAdministrativa());
            }
            
            System.out.println("*** p.getDenominacion():"+articulo.getDenominacion());
            articulosDtos.add(articuloDto);
        }
                       
        if(rubrosModel != null && !rubrosModel.isEmpty()){
            for(TipoModel tipoModel :rubrosModel) {
                rubros.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        if(unidadesVentaModel != null && !unidadesVentaModel.isEmpty()){
            for(TipoModel tipoModel :unidadesVentaModel) {
                unidadesVenta.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        
        if(tipoMaterialesModel != null && !tipoMaterialesModel.isEmpty()){
            for(TipoModel tipoModel :tipoMaterialesModel) {
                tipoMateriales.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }                
        if(clientesModel != null && !clientesModel.isEmpty()){
            for(ClienteModel clienteModel :clientesModel) {
                clientes.put(clienteModel.getId().toString(), clienteModel.getRazonSocial());
            }
        }
        
                
        model.addAttribute("rubroList", rubros);        
        model.addAttribute("unidadesVentaList", unidadesVenta);        
        model.addAttribute("tipoMaterialList", tipoMateriales);                
        model.addAttribute("clienteList", clientes);        
        model.addAttribute("articulos", articulosDtos);        
                
        return "/articulo/articulo";
    }
    
    @RequestMapping(value = "/articulo/addOrEditOrRemove", method = RequestMethod.POST)
    public ModelAndView addOrEditOrRemoveArticulo(@ModelAttribute("articuloForm")ArticuloForm articuloForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {
                
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
        
        if(articuloForm.getAction() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;            
        }
        
        ArticuloService articuloService = new ArticuloServiceImpl();        
        
        String idCliente = articuloForm.getIdCliente();
        String denominacion = articuloForm.getDenominacion();
        String sessionId = req.getSession().getId();
        String id = articuloForm.getPk();
            
        ArticuloModel articuloModel = null;
        if(id.equalsIgnoreCase("-1")) {
            articuloModel = new ArticuloModel();
            articuloModel.setStock(0);
            articuloModel.setStockPeso(0.0);
        } else {
            articuloModel = articuloService.getByPk(Integer.valueOf(id));
            if(articuloModel == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: id de articulo inválido.");
                return modelAndView;                
            } 
        }        
        articuloModel.setIdCliente(Integer.valueOf(idCliente));
        articuloModel.setDenominacion(denominacion);
        if(articuloForm.getIdRubro() != null && !articuloForm.getIdRubro().equals("-1")) {
            articuloModel.setIdRubro(Integer.valueOf(articuloForm.getIdRubro()));
        } else {
            articuloModel.setIdRubro(null);
        }
        if(articuloForm.getFechaAlta() != null && !articuloForm.getFechaAlta().trim().equals("")) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
            Date fecha = formato.parse(articuloForm.getFechaAlta());
            articuloModel.setFechaAlta(fecha);
        } else {
            articuloModel.setFechaAlta(null);
        }        
        if(articuloForm.getIdUnidadesVenta() != null && !articuloForm.getIdUnidadesVenta().equals("-1")) {
            articuloModel.setIdUnidadesVenta(Integer.valueOf(articuloForm.getIdUnidadesVenta()));
        } else {
            articuloModel.setIdUnidadesVenta(null);
        }
        if(articuloForm.getIdTipoMaterial() != null && !articuloForm.getIdTipoMaterial().equals("-1")) {
            articuloModel.setIdTipoMaterial(Integer.valueOf(articuloForm.getIdTipoMaterial()));
        } else {
            articuloModel.setIdTipoMaterial(null);
        }
        
        if(articuloForm.getScrap() != null && !articuloForm.getScrap().isEmpty()) {
            articuloModel.setScrap(Double.valueOf(articuloForm.getScrap()));
        } else {
            articuloModel.setScrap(null);
        }
        if(articuloForm.getEspesor() != null && !articuloForm.getEspesor().isEmpty()) {
            articuloModel.setEspesor(Double.valueOf(articuloForm.getEspesor()));
        } else {
            articuloModel.setEspesor(null);
        }
        if(articuloForm.getAncho() != null && !articuloForm.getAncho().isEmpty()) {
            articuloModel.setAncho(Double.valueOf(articuloForm.getAncho()));
        } else {
            articuloModel.setAncho(null);
        }
        if(articuloForm.getAlto() != null && !articuloForm.getAlto().isEmpty()) {
            articuloModel.setAlto(Double.valueOf(articuloForm.getAlto()));
        } else {
            articuloModel.setAlto(null);
        }        
        if(articuloForm.getTubo() != null && !articuloForm.getTubo().isEmpty()) {
            if(articuloForm.getTubo().equals("1")) {
                articuloModel.setTubo(Boolean.TRUE);
            } else {
                articuloModel.setTubo(Boolean.FALSE);
            }
        } else {
            articuloModel.setTubo(Boolean.FALSE);
        }
        if(articuloForm.getActivo() != null) {
            if(articuloForm.getActivo().equals("1")) {
                articuloModel.setActivo(Boolean.TRUE);
            } else {
                articuloModel.setActivo(Boolean.FALSE);
            }
        } else {
            articuloModel.setActivo(Boolean.FALSE);
        }
        if(articuloForm.getReferenciaAdministrativa() != null) {
            articuloModel.setReferenciaAdministrativa(articuloForm.getReferenciaAdministrativa());
        } else {
            articuloModel.setReferenciaAdministrativa(null);
        }
        if(articuloForm.getObservaciones() != null) {
            articuloModel.setObservaciones(articuloForm.getObservaciones());
        } else {
            articuloModel.setObservaciones(null);
        }
        
        if(articuloForm.getAction().equalsIgnoreCase("add") || articuloForm.getAction().equalsIgnoreCase("edit")) {
            articuloService.save(articuloModel);
        } else {
            if(articuloForm.getAction().equalsIgnoreCase("remove")) {
                if(articuloModel.getId() == null) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: id de articulo inválido.");
                    return modelAndView;                                    
                }
                
                /*
                //Elimino todos los contactos del articulo
                ArticuloContactoService articuloContactoService = new ArticuloContactoServiceImpl();
                ContactoService contactoService = new ContactoServiceImpl();
                List<ArticuloContactoModel> articuloContactos = articuloContactoService.getByPkArticulo(articuloModel.getId());
                if(articuloContactos != null && !articuloContactos.isEmpty()) {                    
                    List<Integer> contactosPk = new ArrayList<Integer>();
                    for(ArticuloContactoModel provedorContactos :articuloContactos) {
                        contactosPk.add(provedorContactos.getIdContacto());
                    }                    
                    List<ContactoModel> contactos = contactoService.getByPkList(contactosPk);
                    if(contactos != null && !contactos.isEmpty()) {
                        for(ContactoModel contacto: contactos) {
                            contactoService.delete(contacto);
                        }
                    }
                    for(ArticuloContactoModel provedorContactos :articuloContactos) {
                        articuloContactoService.delete(provedorContactos);
                    }
                }                
                //@TODO: hacer lo mismo con los domicilios
                //Elimino todos los domicilios del articulo
                ArticuloDomicilioService articuloDomicilioService = new ArticuloDomicilioServiceImpl();
                DomicilioService domicilioService = new DomicilioServiceImpl();
                List<ArticuloDomicilioModel> articuloDomicilios = articuloDomicilioService.getByPkArticulo(articuloModel.getId());
                if(articuloDomicilios != null && !articuloDomicilios.isEmpty()) {                    
                    List<Integer> domiciliosPk = new ArrayList<Integer>();
                    for(ArticuloDomicilioModel provedorDomicilios :articuloDomicilios) {
                        domiciliosPk.add(provedorDomicilios.getIdDomicilio());
                    }                    
                    List<DomicilioModel> domicilios = domicilioService.getByPkList(domiciliosPk);
                    if(domicilios != null && !domicilios.isEmpty()) {
                        for(DomicilioModel domicilio: domicilios) {
                            domicilioService.delete(domicilio);
                        }
                    }
                    for(ArticuloContactoModel provedorContactos :articuloContactos) {
                        articuloContactoService.delete(provedorContactos);
                    }
                }                
                */
                articuloService.delete(articuloModel);
            } else {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: operación inválida.");
                return modelAndView;                                
            }
        }
                        
        modelAndView.setViewName("redirect:/articulo");

        return modelAndView; 
    }

    @RequestMapping(value = "/articulo/edit/{articulopk}", method = RequestMethod.GET)
    public String editArticulo(@PathVariable String articulopk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(articulopk == null) {
            model.addAttribute("errorMessage", "Error: Articulo inválido");         
            return "/error/error";                
        }
        
        ArticuloService articuloService = new ArticuloServiceImpl();   
        ArticuloModel articulo = articuloService.getByPk(Integer.valueOf(articulopk));
        if(articulo == null) {
            model.addAttribute("errorMessage", "Error: Articulo inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        ArticuloForm articuloForm = new ArticuloForm();
        articuloForm.setPk(articulo.getId().toString());
        articuloForm.setIdCliente(articulo.getIdCliente().toString());
        articuloForm.setDenominacion(articulo.getDenominacion());
        if(articulo.getFechaAlta() != null) {
            articuloForm.setFechaAlta(articulo.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(articulo.getIdRubro() != null) {
            articuloForm.setIdRubro(articulo.getIdRubro().toString());
        }
        if(articulo.getIdUnidadesVenta() != null) {
            articuloForm.setIdUnidadesVenta(articulo.getIdUnidadesVenta().toString());
        }
        if(articulo.getIdTipoMaterial() != null) {
            articuloForm.setIdTipoMaterial(articulo.getIdTipoMaterial().toString());
        }
        
        if(articulo.getScrap() != null) {
            articuloForm.setScrap(articulo.getScrap().toString());
        }
        if(articulo.getEspesor() != null) {
            articuloForm.setEspesor(articulo.getEspesor().toString());
        }
        if(articulo.getAncho() != null) {
            articuloForm.setAncho(articulo.getAncho().toString());
        }
        if(articulo.getAlto() != null) {
            articuloForm.setAlto(articulo.getAlto().toString());
        }
        if(articulo.getTubo() != null) {
            if(articulo.getTubo()) {
                articuloForm.setTubo("1");
            } else {
                articuloForm.setTubo("0");
            }
        } else {
            articuloForm.setTubo("0");
        }
        if(articulo.getActivo() != null) {
            if(articulo.getActivo()) {
                articuloForm.setActivo("1");
            } else {
                articuloForm.setActivo("0");
            }
        } else {
            articuloForm.setActivo("0");
        }
        if(articulo.getReferenciaAdministrativa() != null) {
            articuloForm.setReferenciaAdministrativa(articulo.getReferenciaAdministrativa().toString());
        }        
        if(articulo.getObservaciones() != null) {
            articuloForm.setObservaciones(articulo.getObservaciones());
        }
        
        articuloForm.setAction("edit");
        model.addAttribute("articuloForm", articuloForm);  
        model.addAttribute("titleArticulo", "Editar Articulo");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("articuloName", articulo.getId() + " - " + articulo.getDenominacion());        
        
        List<ArticuloModel> articulos = articuloService.getAll();

        TipoService tipoService = new TipoServiceImpl();   
        Map<String,String> rubros = new LinkedHashMap<String,String>();
        List<TipoModel> rubrosModel = tipoService.getByType("rubroArticulo");
        
        Map<String,String> unidadesVenta = new LinkedHashMap<String,String>();
        List<TipoModel> unidadesVentaModel = tipoService.getByType("unidadVentaArticulo");

        Map<String,String> tipoMateriales = new LinkedHashMap<String,String>();
        List<TipoModel> tipoMaterialesModel = tipoService.getByType("tipoMaterialesArticulo");
        
        ClienteService clienteService = new ClienteServiceImpl();   
        Map<String,String> clientes = new LinkedHashMap<String,String>();
        List<ClienteModel> clientesModel = clienteService.getAll();

        
        List<ArticuloDto> articulosDtos = new ArrayList<ArticuloDto>();
        for(ArticuloModel p: articulos) {
            ArticuloDto articuloDto = new ArticuloDto();
            articuloDto.setPk(p.getId().toString());
            if(p.getIdCliente() != null) {
                String cliente = "";
                if(clientesModel != null && !clientesModel.isEmpty()){
                    for(ClienteModel clienteModel :clientesModel) {
                        if(p.getIdCliente() == clienteModel.getId()) {
                            cliente = clienteModel.getRazonSocial();                            
                        }
                    }
                }                
                articuloDto.setCliente(cliente);
            }                                                            
            if(p.getIdRubro() != null) {
                String rubro = "";
                if(rubrosModel != null && !rubrosModel.isEmpty()){
                    for(TipoModel tipoModel :rubrosModel) {
                        if(p.getIdRubro() == tipoModel.getId()) {
                            rubro = tipoModel.getValor();
                        }
                    }
                }                
                articuloDto.setRubro(rubro);
            }                                    
            articuloDto.setDenominacion(p.getDenominacion());
            if(p.getAncho() != null){
                articuloDto.setAncho(p.getAncho().toString());
            }
            if(p.getAlto() != null){
                articuloDto.setAlto(p.getAlto().toString());
            }
            if(p.getEspesor() != null){
                articuloDto.setEspesor(p.getEspesor().toString());
            }
            if(p.getScrap()!= null){
                articuloDto.setScrap(p.getScrap().toString());
            }
            if(p.getReferenciaAdministrativa() != null){
                articuloDto.setReferenciaAdministrativa(p.getReferenciaAdministrativa());
            }
            
            articulosDtos.add(articuloDto);
        }
        
        if(rubrosModel != null && !rubrosModel.isEmpty()){
            for(TipoModel tipoModel :rubrosModel) {
                rubros.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        
        if(unidadesVentaModel != null && !unidadesVentaModel.isEmpty()){
            for(TipoModel tipoModel :unidadesVentaModel) {
                unidadesVenta.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        
        if(tipoMaterialesModel != null && !tipoMaterialesModel.isEmpty()){
            for(TipoModel tipoModel :tipoMaterialesModel) {
                tipoMateriales.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }                
        if(clientesModel != null && !clientesModel.isEmpty()){
            for(ClienteModel clienteModel :clientesModel) {
                clientes.put(clienteModel.getId().toString(), clienteModel.getRazonSocial());
            }
        }
        
        model.addAttribute("rubroList", rubros);        
        model.addAttribute("unidadesVentaList", unidadesVenta);        
        model.addAttribute("tipoMaterialList", tipoMateriales);                
        model.addAttribute("clienteList", clientes);        
        model.addAttribute("articulos", articulosDtos);        
                
        return "/articulo/articulo";        
    }
    
    @RequestMapping(value = "/articulo/remove/{articulopk}", method = RequestMethod.GET)
    public String removeArticulo(@PathVariable String articulopk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(articulopk == null) {
            model.addAttribute("errorMessage", "Error: Articulo inválido");         
            return "/error/error";                
        }
        
        ArticuloService articuloService = new ArticuloServiceImpl();   
        ArticuloModel articulo = articuloService.getByPk(Integer.valueOf(articulopk));
        if(articulo == null) {
            model.addAttribute("errorMessage", "Error: Articulo inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        ArticuloForm articuloForm = new ArticuloForm();
        articuloForm.setPk(articulo.getId().toString());
        articuloForm.setIdCliente(articulo.getIdCliente().toString());
        articuloForm.setDenominacion(articulo.getDenominacion());
        if(articulo.getFechaAlta() != null) {
            articuloForm.setFechaAlta(articulo.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(articulo.getIdRubro() != null) {
            articuloForm.setIdRubro(articulo.getIdRubro().toString());
        }
        if(articulo.getIdUnidadesVenta() != null) {
            articuloForm.setIdUnidadesVenta(articulo.getIdUnidadesVenta().toString());
        }
        if(articulo.getIdTipoMaterial() != null) {
            articuloForm.setIdTipoMaterial(articulo.getIdTipoMaterial().toString());
        }
        
        if(articulo.getScrap() != null) {
            articuloForm.setScrap(articulo.getScrap().toString());
        }
        if(articulo.getEspesor() != null) {
            articuloForm.setEspesor(articulo.getEspesor().toString());
        }
        if(articulo.getAncho() != null) {
            articuloForm.setAncho(articulo.getAncho().toString());
        }
        if(articulo.getAlto() != null) {
            articuloForm.setAlto(articulo.getAlto().toString());
        }
        if(articulo.getTubo() != null) {
            if(articulo.getTubo()) {
                articuloForm.setTubo("1");
            } else {
                articuloForm.setTubo("0");
            }
        } else {
            articuloForm.setTubo("0");            
        }
        if(articulo.getActivo() != null) {
            if(articulo.getActivo()) {
                articuloForm.setActivo("1");
            } else {
                articuloForm.setActivo("0");
            }
        } else {
            articuloForm.setActivo("0");            
        }
        if(articulo.getReferenciaAdministrativa() != null) {
            articuloForm.setReferenciaAdministrativa(articulo.getReferenciaAdministrativa().toString());
        }        
        if(articulo.getObservaciones() != null) {
            articuloForm.setObservaciones(articulo.getObservaciones());
        }
        
        articuloForm.setAction("remove");
        model.addAttribute("articuloForm", articuloForm);  
        model.addAttribute("titleArticulo", "Eliminar Articulo");
        model.addAttribute("buttonLabel", "Eliminar");
        model.addAttribute("articuloName", articulo.getId() + " - " + articulo.getDenominacion());
        
        List<ArticuloModel> articulos = articuloService.getAll();
        
        TipoService tipoService = new TipoServiceImpl();   
        Map<String,String> rubros = new LinkedHashMap<String,String>();
        List<TipoModel> rubrosModel = tipoService.getByType("rubroArticulo");
        
        Map<String,String> unidadesVenta = new LinkedHashMap<String,String>();
        List<TipoModel> unidadesVentaModel = tipoService.getByType("unidadVentaArticulo");

        Map<String,String> tipoMateriales = new LinkedHashMap<String,String>();
        List<TipoModel> tipoMaterialesModel = tipoService.getByType("tipoMaterialesArticulo");
        
        ClienteService clienteService = new ClienteServiceImpl();   
        Map<String,String> clientes = new LinkedHashMap<String,String>();
        List<ClienteModel> clientesModel = clienteService.getAll();

        
        List<ArticuloDto> articulosDtos = new ArrayList<ArticuloDto>();
        for(ArticuloModel p: articulos) {
            ArticuloDto articuloDto = new ArticuloDto();
            articuloDto.setPk(p.getId().toString());
            if(p.getIdCliente() != null) {
                String cliente = "";
                if(clientesModel != null && !clientesModel.isEmpty()){
                    for(ClienteModel clienteModel :clientesModel) {
                        if(p.getIdCliente() == clienteModel.getId()) {
                            cliente = clienteModel.getRazonSocial();
                        }
                    }
                }                
                articuloDto.setCliente(cliente);
            }                                                            
            if(p.getIdRubro() != null) {
                String rubro = "";
                if(rubrosModel != null && !rubrosModel.isEmpty()){
                    for(TipoModel tipoModel :rubrosModel) {
                        if(p.getIdRubro() == tipoModel.getId()) {
                            rubro = tipoModel.getValor();
                        }
                    }
                }                
                articuloDto.setRubro(rubro);
            }                        
            articuloDto.setDenominacion(p.getDenominacion());
            if(p.getAncho() != null){
                articuloDto.setAncho(p.getAncho().toString());
            }
            if(p.getAlto() != null){
                articuloDto.setAlto(p.getAlto().toString());
            }
            if(p.getEspesor() != null){
                articuloDto.setEspesor(p.getEspesor().toString());
            }
            if(p.getScrap()!= null){
                articuloDto.setScrap(p.getScrap().toString());
            }
            if(p.getReferenciaAdministrativa() != null){
                articuloDto.setReferenciaAdministrativa(p.getReferenciaAdministrativa());
            }
            
            articulosDtos.add(articuloDto);
        }
        
        if(rubrosModel != null && !rubrosModel.isEmpty()){
            for(TipoModel tipoModel :rubrosModel) {
                rubros.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        
        if(unidadesVentaModel != null && !unidadesVentaModel.isEmpty()){
            for(TipoModel tipoModel :unidadesVentaModel) {
                unidadesVenta.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        
        if(tipoMaterialesModel != null && !tipoMaterialesModel.isEmpty()){
            for(TipoModel tipoModel :tipoMaterialesModel) {
                tipoMateriales.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }                
        if(clientesModel != null && !clientesModel.isEmpty()){
            for(ClienteModel clienteModel :clientesModel) {
                clientes.put(clienteModel.getId().toString(), clienteModel.getRazonSocial());
            }
        }
        
        model.addAttribute("rubroList", rubros);        
        model.addAttribute("unidadesVentaList", unidadesVenta);        
        model.addAttribute("tipoMaterialList", tipoMateriales);                
        model.addAttribute("clienteList", clientes);        
        model.addAttribute("articulos", articulosDtos); 
                
        return "/articulo/articulo";        
    }    
}
