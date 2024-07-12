/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

 
import com.empresa.thyssenplastic.controller.beans.LocalidadBean;
import com.empresa.thyssenplastic.controller.beans.ProvinciaBean;
import com.empresa.thyssenplastic.controller.form.DomicilioForm;
import com.empresa.thyssenplastic.controller.form.UserForm;
import com.empresa.thyssenplastic.dto.ContactoDto;
import com.empresa.thyssenplastic.dto.DomicilioDto;
import com.empresa.thyssenplastic.model.ClienteContactoModel;
import com.empresa.thyssenplastic.model.ClienteDomicilioModel;
import com.empresa.thyssenplastic.model.ClienteModel;
import com.empresa.thyssenplastic.model.ContactoModel;
import com.empresa.thyssenplastic.model.DomicilioModel;
import com.empresa.thyssenplastic.model.LocalidadModel;
import com.empresa.thyssenplastic.model.ProveedorContactoModel;
import com.empresa.thyssenplastic.model.ProveedorDomicilioModel;
import com.empresa.thyssenplastic.model.ProveedorModel;
import com.empresa.thyssenplastic.model.TipoModel;
import com.empresa.thyssenplastic.service.ClienteContactoService;
import com.empresa.thyssenplastic.service.ClienteDomicilioService;
import com.empresa.thyssenplastic.service.ClienteService;
import com.empresa.thyssenplastic.service.ContactoService;
import com.empresa.thyssenplastic.service.DomicilioService;
import com.empresa.thyssenplastic.service.LocalidadService;
import com.empresa.thyssenplastic.service.ProveedorContactoService;
import com.empresa.thyssenplastic.service.ProveedorDomicilioService;
import com.empresa.thyssenplastic.service.ProveedorService;
import com.empresa.thyssenplastic.service.TipoService;
import com.empresa.thyssenplastic.service.impl.ClienteContactoServiceImpl;
import com.empresa.thyssenplastic.service.impl.ClienteDomicilioServiceImpl;
import com.empresa.thyssenplastic.service.impl.ClienteServiceImpl;
import com.empresa.thyssenplastic.service.impl.ContactoServiceImpl;
import com.empresa.thyssenplastic.service.impl.DomicilioServiceImpl;
import com.empresa.thyssenplastic.service.impl.LocalidadServiceImpl;
import com.empresa.thyssenplastic.service.impl.ProveedorContactoServiceImpl;
import com.empresa.thyssenplastic.service.impl.ProveedorDomicilioServiceImpl;
import com.empresa.thyssenplastic.service.impl.ProveedorServiceImpl;
import com.empresa.thyssenplastic.service.impl.TipoServiceImpl;
import com.empresa.thyssenplastic.utils.Utils;
import static com.empresa.thyssenplastic.utils.Utils.idProvinciaBsAs;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author gusta
 */
@Controller
public class DomicilioController {

    @RequestMapping(value = "/domicilio/proveedorpk/{proveedorpk}", method = RequestMethod.GET)
    public String getHomeDomicilioProveedor(@PathVariable String proveedorpk, HttpServletRequest req, ModelMap model) {

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(proveedorpk == null) {
            model.addAttribute("errorMessage", "Error: Proveedor inválido");         
            return "/error/error";                
        }
        
        ProveedorService proveedorService = new ProveedorServiceImpl();   
        ProveedorModel proveedor = proveedorService.getByPk(Integer.valueOf(proveedorpk));
        if(proveedor == null) {
            model.addAttribute("errorMessage", "Error: Proveedor inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        String externalName = proveedor.getId() + " - " + proveedor.getRazonSocial();
        
        DomicilioForm domicilioForm = new DomicilioForm();
        domicilioForm.setPk("-1");
        domicilioForm.setAction("add");
        //domicilioForm.setIdProvincia(Utils.idProvinciaBsAs);
        
        System.out.println("***** idProvinciaBsAs:"+idProvinciaBsAs);
        
        model.addAttribute("domicilioForm", domicilioForm);         
        model.addAttribute("pkExternal", proveedorpk);
        model.addAttribute("prefixUrl", "proveedor");
        model.addAttribute("labelBase", "Proveedores");        
        model.addAttribute("externalName", externalName);
        model.addAttribute("tipoDomicilio", "proveedor");
        model.addAttribute("titleDomicilio", "Nuevo Domicilio");  
        model.addAttribute("buttonLabel", "Guardar");
        
        TipoService tipoService = new TipoServiceImpl();  
        List<TipoModel> tiposModel = tipoService.getByType("tipodomicilio");
        Map<Integer, TipoModel> tiposModelMap = new HashMap<Integer, TipoModel>();
        for(TipoModel tipoModel :tiposModel) {
            tiposModelMap.put(tipoModel.getId(), tipoModel);
        }

        List<TipoModel> paisesModel = tipoService.getByType("pais");
        Map<Integer, TipoModel> paisesModelMap = new HashMap<Integer, TipoModel>();
        for(TipoModel tipoModel :paisesModel) {
            paisesModelMap.put(tipoModel.getId(), tipoModel);
        }
        
        List<TipoModel> provinciasModel = tipoService.getByType("provincia");
        Map<Integer, TipoModel> provinciasModelMap = new HashMap<Integer, TipoModel>();
        for(TipoModel tipoModel :provinciasModel) {
            provinciasModelMap.put(tipoModel.getId(), tipoModel);
        }
        
        List<TipoModel> localidadesModel = tipoService.getByType("localidad");
        Map<Integer, TipoModel> localidadesModelMap = new HashMap<Integer, TipoModel>();
        for(TipoModel tipoModel :localidadesModel) {
            localidadesModelMap.put(tipoModel.getId(), tipoModel);
        }
        
        //List<TipoModel> provinciasModel = tipoService.getByType("provincia");
        //Map<Integer, TipoModel> provinciasModelMap = new HashMap<Integer, TipoModel>();
        //for(TipoModel tipoModel :provinciasModel) {
            //TipoModel paisModel = tipoService.getByPk(tipoModel.getIdPais());
            //if(paisModel.getValor().equalsIgnoreCase("Argentina")) {
                //provinciasModelMap.put(tipoModel.getId(), tipoModel);
            //}
        //}

        //Integer idProvinciaBsAS = Integer.valueOf(Utils.idProvinciaBsAs);
        //LocalidadService localidadService = new LocalidadServiceImpl();
        //List<LocalidadModel> localidadesModel = localidadService.getByProvincia(idProvinciaBsAS);
        
        List<DomicilioModel> domicilios = new ArrayList<DomicilioModel>();
        List<DomicilioDto> domiciliosDtos = new ArrayList<DomicilioDto>();
        ProveedorDomicilioService proveedorDomicilioService = new ProveedorDomicilioServiceImpl();
        List<ProveedorDomicilioModel> proveedorDomicilios = proveedorDomicilioService.getByPkProveedor(Integer.valueOf(proveedorpk));
        if(proveedorDomicilios != null && !proveedorDomicilios.isEmpty()) {
            List<Integer> pkList = new ArrayList<Integer>();
            for(ProveedorDomicilioModel provedorDomicilios :proveedorDomicilios) {
                pkList.add(provedorDomicilios.getIdDomicilio());
            }
            DomicilioService domicilioService = new DomicilioServiceImpl();
            domicilios = domicilioService.getByPkList(pkList);
            
            TipoService tipoServicee = new TipoServiceImpl();
            
            for(DomicilioModel c :domicilios) {
                DomicilioDto domicilioDto = new DomicilioDto();
                domicilioDto.setId(c.getId().toString());
                domicilioDto.setNombre(c.getNombre());
                domicilioDto.setUbicacion(c.getUbicacion());
                
                
                TipoModel localidaad = null;
                TipoModel provinciaa = null;
                TipoModel paiis = null;
                
                Integer a = c.getIdLocalidad();
                Integer b = c.getIdProvincia();
                Integer d = c.getIdPais();
                
                if (d != null){
                    paiis = tipoServicee.getByPk(d);
                }
                
                if (a != null){
                    localidaad = tipoServicee.getByPk(a);
                }
                
                if (b != null){
                    provinciaa = tipoServicee.getByPk(b);
                }
    
 
                if (localidaad != null) {
                    domicilioDto.setLocalidad(localidaad.getValor());
                }
                
                if (provinciaa != null) {
                    domicilioDto.setProvincia(provinciaa.getValor());
                }
                
                if (paiis != null) {
                    domicilioDto.setPais(paiis.getValor());
                }

                TipoModel t = tiposModelMap.get(c.getIdTipo());
                if(t != null) {
                    domicilioDto.setTipo(t.getValor());
                }
                String nomContacto = c.getNombreContacto();
                if(nomContacto !=null){
                    domicilioDto.setNombreContacto(c.getNombreContacto());
                }
                String telContacto = c.getTelefonoContacto();
                if(telContacto !=null){
                    domicilioDto.setTelefonoContacto(c.getTelefonoContacto());
                }
                String horContacto = c.getHorarioContacto();
                if(horContacto !=null){
                    domicilioDto.setHorarioContacto(c.getHorarioContacto());
                }
                String obsContacto = c.getObservacionesContacto();
                if(obsContacto !=null){
                    domicilioDto.setObservacionesContacto(c.getObservacionesContacto());
                }
                domicilioDto.setPuntoGps(c.getPuntoGps());
                
                domiciliosDtos.add(domicilioDto);
            }
        }         
        Map<String,String> tipos = new LinkedHashMap<String,String>();      
        if(tiposModel != null && !tiposModel.isEmpty()){
            for(TipoModel tipoModel :tiposModel) {
                tipos.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        Map<String,String> paises = new LinkedHashMap<String,String>();      
        if(paisesModel != null && !paisesModel.isEmpty()){
            for(TipoModel tipoModel :paisesModel) {
                paises.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        
        Map<String,String> provincias = new LinkedHashMap<String,String>();      
        if(provinciasModel != null && !provinciasModel.isEmpty()){
            for(TipoModel tipoModel :provinciasModel) {
                provincias.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        
        
        //Map<String,String> provincias = new LinkedHashMap<String,String>();      
        //if(provinciasModel != null && !provinciasModel.isEmpty()){
            //for(TipoModel tipoModel :provinciasModel) {
                //TipoModel paisModel = tipoService.getByPk(tipoModel.getIdPais());
                //if(paisModel.getValor().equalsIgnoreCase("Argentina")) {                
                    //provincias.put(tipoModel.getId().toString(), tipoModel.getValor());
                //}
            //}
        //}
        
        Map<String,String> localidades = new LinkedHashMap<String,String>();      
        if(localidadesModel != null && !localidadesModel.isEmpty()){
            for(TipoModel tipoModel :localidadesModel) {
                localidades.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
                
        //Map<String,String> localidades = new LinkedHashMap<String,String>();      
        //if(localidadesModel != null && !localidadesModel.isEmpty()){
            //for(LocalidadModel localidadModel :localidadesModel) {
                //localidades.put(localidadModel.getId().toString(), localidadModel.getNombre());
            //}
        //}        
        
        List<ContactoModel> contactos = new ArrayList<ContactoModel>();
        Map<String,String> contactosMap = new LinkedHashMap<String,String>();      
        ProveedorContactoService proveedorContactoService = new ProveedorContactoServiceImpl();
        List<ProveedorContactoModel> proveedorContactos = proveedorContactoService.getByPkProveedor(Integer.valueOf(proveedorpk));
        if(proveedorContactos != null && !proveedorContactos.isEmpty()) {
            List<Integer> pkList = new ArrayList<Integer>();
            for(ProveedorContactoModel provedorContactos :proveedorContactos) {
                pkList.add(provedorContactos.getIdContacto());                
            }
            ContactoService contactoService = new ContactoServiceImpl();
            contactos = contactoService.getByPkList(pkList);
            for(ContactoModel c :contactos) {
                 contactosMap.put(c.getId().toString(), c.getNombre());
            }
        }
        
        model.addAttribute("tipoList", tipos);    
        model.addAttribute("paisList", paises);
        model.addAttribute("provinciaList", provincias);                     
        model.addAttribute("localidadList", localidades);            
        model.addAttribute("contactoList", contactosMap);  
        model.addAttribute("domicilios", domiciliosDtos);       
        
        System.out.println("*** getHomeDomicilio proveedorpk:"+proveedorpk);
        return "/domicilio/domicilio";
    }
    
    @RequestMapping(value = "/domicilio/clientepk/{clientepk}", method = RequestMethod.GET)
    public String getHomeDomicilioCliente(@PathVariable String clientepk, HttpServletRequest req, ModelMap model) {

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(clientepk == null) {
            model.addAttribute("errorMessage", "Error: Cliente inválido");         
            return "/error/error";                
        }
        
        ClienteService clienteService = new ClienteServiceImpl();   
        ClienteModel cliente = clienteService.getByPk(Integer.valueOf(clientepk));
        if(cliente == null) {
            model.addAttribute("errorMessage", "Error: Cliente inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        String externalName = cliente.getId() + " - " + cliente.getRazonSocial();
        
        DomicilioForm domicilioForm = new DomicilioForm();
        domicilioForm.setPk("-1");
        domicilioForm.setAction("add");
        //domicilioForm.setIdProvincia(Utils.idProvinciaBsAs);
        
        model.addAttribute("domicilioForm", domicilioForm);         
        model.addAttribute("pkExternal", clientepk);
        model.addAttribute("prefixUrl", "cliente");
        model.addAttribute("labelBase", "Clientes");        
        model.addAttribute("externalName", externalName);
        model.addAttribute("tipoDomicilio", "cliente");
        model.addAttribute("titleDomicilio", "Nuevo Domicilio");  
        model.addAttribute("buttonLabel", "Guardar");
        
        TipoService tipoService = new TipoServiceImpl();  
        List<TipoModel> tiposModel = tipoService.getByType("tipodomicilio");
        Map<Integer, TipoModel> tiposModelMap = new HashMap<Integer, TipoModel>();
        for(TipoModel tipoModel :tiposModel) {
            tiposModelMap.put(tipoModel.getId(), tipoModel);
        }

        List<TipoModel> paisesModel = tipoService.getByType("pais");
        Map<Integer, TipoModel> paisesModelMap = new HashMap<Integer, TipoModel>();
        for(TipoModel tipoModel :paisesModel) {
            paisesModelMap.put(tipoModel.getId(), tipoModel);
        }
        
        List<TipoModel> provinciasModel = tipoService.getByType("provincia");
        Map<Integer, TipoModel> provinciasModelMap = new HashMap<Integer, TipoModel>();
        for(TipoModel tipoModel :provinciasModel) {
            provinciasModelMap.put(tipoModel.getId(), tipoModel);
        }
        
                
        //List<TipoModel> provinciasModel = tipoService.getByType("provincia");
        //Map<Integer, TipoModel> provinciasModelMap = new HashMap<Integer, TipoModel>();
        //for(TipoModel tipoModel :provinciasModel) {
            //TipoModel paisModel = tipoService.getByPk(tipoModel.getIdPais());
            //if(paisModel.getValor().equalsIgnoreCase("Argentina")) {
                //provinciasModelMap.put(tipoModel.getId(), tipoModel);
            //}
        //}
        
        List<TipoModel> localidadesModel = tipoService.getByType("localidad");
        Map<Integer, TipoModel> localidadesModelMap = new HashMap<Integer, TipoModel>();
        for(TipoModel tipoModel :localidadesModel) {
            localidadesModelMap.put(tipoModel.getId(), tipoModel);
        }
        
        //Integer idProvinciaBsAS = Integer.valueOf(Utils.idProvinciaBsAs);
        //LocalidadService localidadService = new LocalidadServiceImpl();
        //List<LocalidadModel> localidadesModel = localidadService.getByProvincia(idProvinciaBsAS);        
        
        List<DomicilioModel> domicilios = new ArrayList<DomicilioModel>();
        List<DomicilioDto> domiciliosDtos = new ArrayList<DomicilioDto>();
        ClienteDomicilioService clienteDomicilioService = new ClienteDomicilioServiceImpl();
        List<ClienteDomicilioModel> clienteDomicilios = clienteDomicilioService.getByPkCliente(Integer.valueOf(clientepk));
        if(clienteDomicilios != null && !clienteDomicilios.isEmpty()) {
            List<Integer> pkList = new ArrayList<Integer>();
            for(ClienteDomicilioModel provedorDomicilios :clienteDomicilios) {
                pkList.add(provedorDomicilios.getIdDomicilio());
            }
            DomicilioService domicilioService = new DomicilioServiceImpl();
            domicilios = domicilioService.getByPkList(pkList);
            TipoService tipoServicee = new TipoServiceImpl(); 
            //LocalidadService localidadService = new LocalidadServiceImpl();
     
            for(DomicilioModel c :domicilios) {
                DomicilioDto domicilioDto = new DomicilioDto();
                domicilioDto.setId(c.getId().toString());
                domicilioDto.setNombre(c.getNombre());
                domicilioDto.setUbicacion(c.getUbicacion());
                
                Integer a = c.getIdLocalidad();
                Integer b = c.getIdProvincia();
                Integer d = c.getIdPais();

                
                TipoModel localidaad = null;
                TipoModel provinciaa = null;
                TipoModel paiis = null;
                
                if (a != null){
                    localidaad = tipoServicee.getByPk(a);
                }
                
                if (b != null){
                    provinciaa = tipoServicee.getByPk(b);
                }
                
                if (d != null){
                    paiis = tipoServicee.getByPk(d);
                }
    
 
                if (localidaad != null) {
                    domicilioDto.setLocalidad(localidaad.getValor());
                }
                
                if (provinciaa != null) {
                    domicilioDto.setProvincia(provinciaa.getValor());
                }
                
                if (paiis != null) {
                    domicilioDto.setPais(paiis.getValor());
                }

                TipoModel t = tiposModelMap.get(c.getIdTipo());
                if(t != null) {
                    domicilioDto.setTipo(t.getValor());
                }
                
                String nomContacto = c.getNombreContacto();
                if(nomContacto !=null){
                    domicilioDto.setNombreContacto(c.getNombreContacto());
                }
                String telContacto = c.getTelefonoContacto();
                if(telContacto !=null){
                    domicilioDto.setTelefonoContacto(c.getTelefonoContacto());
                }
                String horContacto = c.getHorarioContacto();
                if(horContacto !=null){
                    domicilioDto.setHorarioContacto(c.getHorarioContacto());
                }
                String obsContacto = c.getObservacionesContacto();
                if(obsContacto !=null){
                    domicilioDto.setObservacionesContacto(c.getObservacionesContacto());
                }
                String pGps = c.getPuntoGps();
                if(pGps !=null){
                   domicilioDto.setPuntoGps(c.getPuntoGps());
                }
                domiciliosDtos.add(domicilioDto);
            }
        }         
        Map<String,String> tipos = new LinkedHashMap<String,String>();      
        if(tiposModel != null && !tiposModel.isEmpty()){
            for(TipoModel tipoModel :tiposModel) {
                tipos.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        
        Map<String,String> paises = new LinkedHashMap<String,String>();      
        if(paisesModel != null && !paisesModel.isEmpty()){
            for(TipoModel tipoModel :paisesModel) {
                paises.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        
        
        Map<String,String> provincias = new LinkedHashMap<String,String>();      
        if(provinciasModel != null && !provinciasModel.isEmpty()){
            for(TipoModel tipoModel :provinciasModel) {
                provincias.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        
        //Map<String,String> provincias = new LinkedHashMap<String,String>();      
        //if(provinciasModel != null && !provinciasModel.isEmpty()){
            //for(TipoModel tipoModel :provinciasModel) {
                //TipoModel paisModel = tipoService.getByPk(tipoModel.getIdPais());
                //if(paisModel.getValor().equalsIgnoreCase("Argentina")) {
                    //provincias.put(tipoModel.getId().toString(), tipoModel.getValor());
                //}
            //}
        //}
        
        Map<String,String> localidades = new LinkedHashMap<String,String>();      
        if(localidadesModel != null && !localidadesModel.isEmpty()){
            for(TipoModel tipoModel :localidadesModel) {
                localidades.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        
        //Map<String,String> localidades = new LinkedHashMap<String,String>();      
        //if(localidadesModel != null && !localidadesModel.isEmpty()){
            //for(LocalidadModel localidadModel :localidadesModel) {
                //localidades.put(localidadModel.getId().toString(), localidadModel.getNombre());
            //}
        //}        
        
        List<ContactoModel> contactos = new ArrayList<ContactoModel>();
        Map<String,String> contactosMap = new LinkedHashMap<String,String>();      
        ClienteContactoService clienteContactoService = new ClienteContactoServiceImpl();
        List<ClienteContactoModel> clienteContactos = clienteContactoService.getByPkCliente(Integer.valueOf(clientepk));
        if(clienteContactos != null && !clienteContactos.isEmpty()) {
            List<Integer> pkList = new ArrayList<Integer>();
            for(ClienteContactoModel provedorContactos :clienteContactos) {
                pkList.add(provedorContactos.getIdContacto());                
            }
            ContactoService contactoService = new ContactoServiceImpl();
            contactos = contactoService.getByPkList(pkList);
            for(ContactoModel c :contactos) {
                 contactosMap.put(c.getId().toString(), c.getNombre());
            }
        }
        
        model.addAttribute("tipoList", tipos);                
        model.addAttribute("paisList", paises);
        model.addAttribute("provinciaList", provincias);                     
        model.addAttribute("localidadList", localidades);          
        model.addAttribute("contactoList", contactosMap);
        model.addAttribute("domicilios", domiciliosDtos);       
        
        System.out.println("*** getHomeDomicilio clientepk:"+clientepk);
        return "/domicilio/domicilio";
    }
    
    @RequestMapping(value = "/domicilio/addOrEditOrRemove", method = RequestMethod.POST)
    public ModelAndView addOrEditDomicilio(@ModelAttribute("domicilioForm")DomicilioForm domicilioForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {
        
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

        if(domicilioForm.getAction() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;            
        }
        
        DomicilioService domicilioService = new DomicilioServiceImpl();        
        
        String ubicacion = domicilioForm.getUbicacion();
        String nombre = domicilioForm.getNombre();
        String pkExternal = domicilioForm.getPkExternal();
        String tipoDomicilio = domicilioForm.getTipoDomicilio();
        String sessionId = req.getSession().getId();
        String id = domicilioForm.getPk();
        String nombreContacto = domicilioForm.getNombreContacto();
        String horarioContacto = domicilioForm.getHorarioContacto();
        String telefonoContacto = domicilioForm.getTelefonoContacto();
        String observacionesContacto = domicilioForm.getObservacionesContacto();
        String puntoGps = domicilioForm.getPuntoGps();
                
        DomicilioModel domicilioModel = null;
        if(id.equalsIgnoreCase("-1")) {
            domicilioModel = new DomicilioModel();
        } else {
            domicilioModel = domicilioService.getByPk(Integer.valueOf(id));
            if(domicilioModel == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: id de domicilio inválido.");
                return modelAndView;                
            } 
        }        
        
        domicilioModel.setUbicacion(ubicacion);
        domicilioModel.setNombre(nombre);
        domicilioModel.setNombreContacto(nombreContacto);
        domicilioModel.setHorarioContacto(horarioContacto);
        domicilioModel.setTelefonoContacto(telefonoContacto);
        domicilioModel.setObservacionesContacto(observacionesContacto);
        domicilioModel.setPuntoGps(puntoGps);
        if(domicilioForm.getUbicacion() != null && !domicilioForm.getUbicacion().trim().equals("")) {
            domicilioModel.setUbicacion(domicilioForm.getUbicacion());
        } else {
            domicilioModel.setUbicacion(null);
        }
        if(domicilioForm.getIdPais() != null && !domicilioForm.getIdPais().trim().equals("")) {
            domicilioModel.setIdPais(Integer.valueOf(domicilioForm.getIdPais()));
        }else {
           domicilioModel.setIdPais(null);
        }                
        if(domicilioForm.getIdProvincia() != null && !domicilioForm.getIdProvincia().trim().equals("")) {
          domicilioModel.setIdProvincia(Integer.valueOf(domicilioForm.getIdProvincia()));
        } else {
          domicilioModel.setIdProvincia(null);
        }        
        if(domicilioForm.getIdLocalidad() != null && !domicilioForm.getIdLocalidad().trim().equals("-1")) {
           domicilioModel.setIdLocalidad(Integer.valueOf(domicilioForm.getIdLocalidad()));
        } else {
          domicilioModel.setIdLocalidad(null);
        }        
        if(domicilioForm.getIdTipo() != null && !domicilioForm.getIdTipo().trim().equals("")) {
            domicilioModel.setIdTipo(Integer.valueOf(domicilioForm.getIdTipo()));
        } else {
            domicilioModel.setIdTipo(null);
        }
        if(domicilioForm.getIdContacto() != null && !domicilioForm.getIdContacto().trim().equals("-1")) {
            domicilioModel.setIdContacto(Integer.valueOf(domicilioForm.getIdContacto()));
        } else {
            domicilioModel.setIdContacto(null);
        }               
        
        if(domicilioForm.getAction().equalsIgnoreCase("add") || domicilioForm.getAction().equalsIgnoreCase("edit")) {
            domicilioService.save(domicilioModel);
            
            if(tipoDomicilio.equalsIgnoreCase("proveedor") && domicilioForm.getAction().equalsIgnoreCase("add")) {

                ProveedorDomicilioModel proveedorDomicilioModel = new ProveedorDomicilioModel();
                proveedorDomicilioModel.setIdDomicilio(domicilioModel.getId());
                proveedorDomicilioModel.setIdProveedor(Integer.valueOf(pkExternal));

                ProveedorDomicilioService proveedorDomicilioService = new ProveedorDomicilioServiceImpl();
                proveedorDomicilioService.save(proveedorDomicilioModel);
            }            
            if(tipoDomicilio.equalsIgnoreCase("cliente") && domicilioForm.getAction().equalsIgnoreCase("add")) {

                ClienteDomicilioModel clienteDomicilioModel = new ClienteDomicilioModel();
                clienteDomicilioModel.setIdDomicilio(domicilioModel.getId());
                clienteDomicilioModel.setIdCliente(Integer.valueOf(pkExternal));

                ClienteDomicilioService clienteDomicilioService = new ClienteDomicilioServiceImpl();
                clienteDomicilioService.save(clienteDomicilioModel);
            }                        
        } else if(domicilioForm.getAction().equalsIgnoreCase("remove")) {
            if(tipoDomicilio.equalsIgnoreCase("proveedor")) {
                ProveedorDomicilioService proveedorDomicilioService = new ProveedorDomicilioServiceImpl();
                ProveedorDomicilioModel proveedorDomicilio = proveedorDomicilioService.getByPkProveedorAndDomicilio(Integer.valueOf(pkExternal), domicilioModel.getId());
                if(proveedorDomicilio != null) {                    
                    proveedorDomicilioService.delete(proveedorDomicilio);
                }
                domicilioService.delete(domicilioModel);
            }
            if(tipoDomicilio.equalsIgnoreCase("cliente")) {
                ClienteDomicilioService clienteDomicilioService = new ClienteDomicilioServiceImpl();
                ClienteDomicilioModel clienteDomicilio = clienteDomicilioService.getByPkClienteAndDomicilio(Integer.valueOf(pkExternal), domicilioModel.getId());
                if(clienteDomicilio != null) {                    
                    clienteDomicilioService.delete(clienteDomicilio);
                }
                domicilioService.delete(domicilioModel);
            }            
        } else {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: operación inválida.");
            return modelAndView;                                
        }
        
        if(tipoDomicilio.equalsIgnoreCase("proveedor")) {            
            modelAndView.setViewName("redirect:/domicilio/proveedorpk/"+pkExternal);
        }
        if(tipoDomicilio.equalsIgnoreCase("cliente")) {            
            modelAndView.setViewName("redirect:/domicilio/clientepk/"+pkExternal);
        }

        return modelAndView; 
    }
    
    @RequestMapping(value = "/domicilio/proveedorpk/{proveedorpk}/edit/{domiciliopk}", method = RequestMethod.GET)
    public String editProveedorDomicilio(@PathVariable String proveedorpk, @PathVariable String domiciliopk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(domiciliopk == null) {
            model.addAttribute("errorMessage", "Error: Domicilio inválido");         
            return "/error/error";                
        }

        if(proveedorpk == null) {
            model.addAttribute("errorMessage", "Error: Proveedor inválido");         
            return "/error/error";                
        }
        
        DomicilioService domicilioService = new DomicilioServiceImpl();   
        DomicilioModel domicilio = domicilioService.getByPk(Integer.valueOf(domiciliopk));
        if(domicilio == null) {
            model.addAttribute("errorMessage", "Error: Domicilio inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        ProveedorService proveedorService = new ProveedorServiceImpl();   
        ProveedorModel proveedor = proveedorService.getByPk(Integer.valueOf(proveedorpk));
        if(proveedor == null) {
            model.addAttribute("errorMessage", "Error: Proveedor inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        String externalName = proveedor.getId() + " - " + proveedor.getRazonSocial();
        
        DomicilioForm domicilioForm = new DomicilioForm();
        domicilioForm.setPk(domicilio.getId().toString());
        domicilioForm.setUbicacion(domicilio.getUbicacion());
        domicilioForm.setNombre(domicilio.getNombre());
        if(domicilio.getUbicacion() != null) {
            domicilioForm.setUbicacion(domicilio.getUbicacion());
        }
        if(domicilio.getIdTipo() != null) {
            domicilioForm.setIdTipo(domicilio.getIdTipo().toString());
        }
        if(domicilio.getIdProvincia() != null) {
            domicilioForm.setIdProvincia(domicilio.getIdProvincia().toString());
        }
        if(domicilio.getIdLocalidad() != null) {
            domicilioForm.setIdLocalidad(domicilio.getIdLocalidad().toString());
        }
        if(domicilio.getIdContacto() != null) {
            domicilioForm.setIdContacto(domicilio.getIdContacto().toString());
        }
        if(domicilio.getNombreContacto() != null) {
            domicilioForm.setNombreContacto(domicilio.getNombreContacto());
        }
        if(domicilio.getTelefonoContacto() != null) {
            domicilioForm.setTelefonoContacto(domicilio.getTelefonoContacto());
        }
        if(domicilio.getHorarioContacto() != null) {
            domicilioForm.setHorarioContacto(domicilio.getHorarioContacto());
        }
        if(domicilio.getObservacionesContacto() != null) {
            domicilioForm.setObservacionesContacto(domicilio.getObservacionesContacto());
        }
        if(domicilio.getPuntoGps() != null) {
            domicilioForm.setPuntoGps(domicilio.getPuntoGps());
        }
        
        domicilioForm.setAction("edit");        
        model.addAttribute("domicilioForm", domicilioForm);  
        model.addAttribute("pkExternal", proveedorpk);
        model.addAttribute("prefixUrl", "proveedor");
        model.addAttribute("labelBase", "Proveedores");        
        model.addAttribute("externalName", externalName);
        model.addAttribute("tipoDomicilio", "proveedor");
        model.addAttribute("titleDomicilio", "Editar Domicilio");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("domicilioName", domicilio.getId() + " - " + domicilio.getNombre());
        
        TipoService tipoService = new TipoServiceImpl();  
        List<TipoModel> tiposModel = tipoService.getByType("tipodomicilio");
        Map<Integer, TipoModel> tiposModelMap = new HashMap<Integer, TipoModel>();
        for(TipoModel tipoModel :tiposModel) {
            tiposModelMap.put(tipoModel.getId(), tipoModel);
        }
        
        List<TipoModel> paisesModel = tipoService.getByType("pais");
        Map<Integer, TipoModel> paisesModelMap = new HashMap<Integer, TipoModel>();
        for(TipoModel tipoModel :paisesModel) {
            paisesModelMap.put(tipoModel.getId(), tipoModel);
        }
        
        List<TipoModel> provinciasModel = tipoService.getByType("provincia");
        Map<Integer, TipoModel> provinciasModelMap = new HashMap<Integer, TipoModel>();
        for(TipoModel tipoModel :provinciasModel) {
            provinciasModelMap.put(tipoModel.getId(), tipoModel);
        }
        
        //List<TipoModel> provinciasModel = tipoService.getByType("provincia");
        //Map<Integer, TipoModel> provinciasModelMap = new HashMap<Integer, TipoModel>();
        //for(TipoModel tipoModel :provinciasModel) {
            //TipoModel paisModel = tipoService.getByPk(tipoModel.getIdPais());
            //if(paisModel.getValor().equalsIgnoreCase("Argentina")) {
                //provinciasModelMap.put(tipoModel.getId(), tipoModel);
            //}
        //}
        
        List<TipoModel> localidadesModel = tipoService.getByType("localidad");
        Map<Integer, TipoModel> localidadesModelMap = new HashMap<Integer, TipoModel>();
        for(TipoModel tipoModel :localidadesModel) {
            localidadesModelMap.put(tipoModel.getId(), tipoModel);
        }
        
        //LocalidadService localidadService = new LocalidadServiceImpl(); 
        //List<LocalidadModel> localidadesModel = new ArrayList<LocalidadModel>();
        //if(domicilio.getIdProvincia() != null){
            //localidadesModel = localidadService.getByProvincia(domicilio.getIdProvincia());
        //} 
        
        List<DomicilioModel> domicilios = new ArrayList<DomicilioModel>();
        TipoService tipoServicee = new TipoServiceImpl(); 
        List<DomicilioDto> domiciliosDtos = new ArrayList<DomicilioDto>();
        ProveedorDomicilioService proveedorDomicilioService = new ProveedorDomicilioServiceImpl();
        List<ProveedorDomicilioModel> proveedorDomicilios = proveedorDomicilioService.getByPkProveedor(Integer.valueOf(proveedorpk));
        if(proveedorDomicilios != null && !proveedorDomicilios.isEmpty()) {
            List<Integer> pkList = new ArrayList<Integer>();
            for(ProveedorDomicilioModel provedorDomicilios :proveedorDomicilios) {
                pkList.add(provedorDomicilios.getIdDomicilio());
            }
            domicilios = domicilioService.getByPkList(pkList);
            for(DomicilioModel c :domicilios) {
                DomicilioDto domicilioDto = new DomicilioDto();
                domicilioDto.setId(c.getId().toString());                
                domicilioDto.setNombre(c.getNombre());
                domicilioDto.setUbicacion(c.getUbicacion());
                
                TipoModel localidaad = null;
                TipoModel provinciaa = null;
                TipoModel paiis = null;
                
                Integer a = c.getIdLocalidad();
                Integer b = c.getIdProvincia();
                Integer d = c.getIdPais();
                
                if (a != null){
                    localidaad = tipoServicee.getByPk(a);
                }
                
                if (b != null){
                    provinciaa = tipoServicee.getByPk(b);
                }
                
                if (d != null){
                    paiis = tipoServicee.getByPk(d);
                }
    
 
                if (localidaad != null) {
                    domicilioDto.setLocalidad(localidaad.getValor());
                }
                
                if (provinciaa != null) {
                    domicilioDto.setProvincia(provinciaa.getValor());
                }
                
                if (paiis != null) {
                    domicilioDto.setPais(paiis.getValor());
                }
                
                TipoModel t = tiposModelMap.get(c.getIdTipo());
                if(t != null) {
                    domicilioDto.setTipo(t.getValor());
                }
                String nomContacto = c.getNombreContacto();
                if(nomContacto !=null){
                    domicilioDto.setNombreContacto(c.getNombreContacto());
                }
                String telContacto = c.getTelefonoContacto();
                if(telContacto !=null){
                    domicilioDto.setTelefonoContacto(c.getTelefonoContacto());
                }
                String horContacto = c.getHorarioContacto();
                if(horContacto !=null){
                    domicilioDto.setHorarioContacto(c.getHorarioContacto());
                }
                String obsContacto = c.getObservacionesContacto();
                if(obsContacto !=null){
                    domicilioDto.setObservacionesContacto(c.getObservacionesContacto());
                }
                
                domiciliosDtos.add(domicilioDto);
            }
        }             
        Map<String,String> tipos = new LinkedHashMap<String,String>();        
        if(tiposModel != null && !tiposModel.isEmpty()){
            for(TipoModel tipoModel :tiposModel) {
                tipos.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        Map<String,String> paises = new LinkedHashMap<String,String>();      
        if(paisesModel != null && !paisesModel.isEmpty()){
            for(TipoModel tipoModel :paisesModel) {
                paises.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        
        Map<String,String> provincias = new LinkedHashMap<String,String>();      
        if(provinciasModel != null && !provinciasModel.isEmpty()){
            for(TipoModel tipoModel :provinciasModel) {
                provincias.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        } 
        //Map<String,String> provincias = new LinkedHashMap<String,String>();        
        //if(provinciasModel != null && !provinciasModel.isEmpty()){
            //for(TipoModel tipoModel :provinciasModel) {
                //TipoModel paisModel = tipoService.getByPk(tipoModel.getIdPais());
                //if(paisModel.getValor().equalsIgnoreCase("Argentina")) {                
                    //provincias.put(tipoModel.getId().toString(), tipoModel.getValor());
                //}
            //}
        //}
        
        Map<String,String> localidades = new LinkedHashMap<String,String>();      
        if(localidadesModel != null && !localidadesModel.isEmpty()){
            for(TipoModel tipoModel :localidadesModel) {
                localidades.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        //Map<String,String> localidades = new LinkedHashMap<String,String>();        
        //if(localidadesModel != null && !localidadesModel.isEmpty()){
            //for(LocalidadModel localidadModel :localidadesModel) {
                //localidades.put(localidadModel.getId().toString(), localidadModel.getNombre());
            //}
        //}

        List<ContactoModel> contactos = new ArrayList<ContactoModel>();
        Map<String,String> contactosMap = new LinkedHashMap<String,String>();      
        ProveedorContactoService proveedorContactoService = new ProveedorContactoServiceImpl();
        List<ProveedorContactoModel> proveedorContactos = proveedorContactoService.getByPkProveedor(Integer.valueOf(proveedorpk));
        if(proveedorContactos != null && !proveedorContactos.isEmpty()) {
            List<Integer> pkList = new ArrayList<Integer>();
            for(ProveedorContactoModel provedorContactos :proveedorContactos) {
                pkList.add(provedorContactos.getIdContacto());                
            }
            ContactoService contactoService = new ContactoServiceImpl();
            contactos = contactoService.getByPkList(pkList);
            for(ContactoModel c :contactos) {
                 contactosMap.put(c.getId().toString(), c.getNombre());
            }
        }
        
        model.addAttribute("tipoList", tipos);        
        model.addAttribute("paisList", paises);
        model.addAttribute("provinciaList", provincias);
        model.addAttribute("localidadList", localidades);
        model.addAttribute("contactoList", contactosMap);
        model.addAttribute("domicilios", domiciliosDtos);       
                
        return "/domicilio/domicilio";        
    }

    @RequestMapping(value = "/domicilio/clientepk/{clientepk}/edit/{domiciliopk}", method = RequestMethod.GET)
    public String editClienteDomicilio(@PathVariable String clientepk, @PathVariable String domiciliopk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(domiciliopk == null) {
            model.addAttribute("errorMessage", "Error: Domicilio inválido");         
            return "/error/error";                
        }

        if(clientepk == null) {
            model.addAttribute("errorMessage", "Error: Cliente inválido");         
            return "/error/error";                
        }
        
        DomicilioService domicilioService = new DomicilioServiceImpl();   
        DomicilioModel domicilio = domicilioService.getByPk(Integer.valueOf(domiciliopk));
        if(domicilio == null) {
            model.addAttribute("errorMessage", "Error: Domicilio inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        ClienteService clienteService = new ClienteServiceImpl();   
        ClienteModel cliente = clienteService.getByPk(Integer.valueOf(clientepk));
        if(cliente == null) {
            model.addAttribute("errorMessage", "Error: Cliente inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        String externalName = cliente.getId() + " - " + cliente.getRazonSocial();
        
        DomicilioForm domicilioForm = new DomicilioForm();
        domicilioForm.setPk(domicilio.getId().toString());
        domicilioForm.setUbicacion(domicilio.getUbicacion());
        domicilioForm.setNombre(domicilio.getNombre());
        if(domicilio.getUbicacion() != null) {
            domicilioForm.setUbicacion(domicilio.getUbicacion());
        }
        if(domicilio.getIdTipo() != null) {
            domicilioForm.setIdTipo(domicilio.getIdTipo().toString());
        }
        if(domicilio.getIdProvincia() != null) {
            domicilioForm.setIdProvincia(domicilio.getIdProvincia().toString());
        }
        if(domicilio.getIdLocalidad() != null) {
            domicilioForm.setIdLocalidad(domicilio.getIdLocalidad().toString());
        }
        if(domicilio.getIdContacto() != null) {
            domicilioForm.setIdContacto(domicilio.getIdContacto().toString());
        }
        if(domicilio.getNombreContacto() != null) {
            domicilioForm.setNombreContacto(domicilio.getNombreContacto());
        }
        if(domicilio.getTelefonoContacto() != null) {
            domicilioForm.setTelefonoContacto(domicilio.getTelefonoContacto());
        }
        if(domicilio.getHorarioContacto() != null) {
            domicilioForm.setHorarioContacto(domicilio.getHorarioContacto());
        }
        if(domicilio.getObservacionesContacto() != null) {
            domicilioForm.setObservacionesContacto(domicilio.getObservacionesContacto());
        }
        if(domicilio.getPuntoGps() != null) {
            domicilioForm.setPuntoGps(domicilio.getPuntoGps());
        }
        
        domicilioForm.setAction("edit");        
        model.addAttribute("domicilioForm", domicilioForm);  
        model.addAttribute("pkExternal", clientepk);
        model.addAttribute("prefixUrl", "cliente");
        model.addAttribute("labelBase", "Clientes");        
        model.addAttribute("externalName", externalName);
        model.addAttribute("tipoDomicilio", "cliente");
        model.addAttribute("titleDomicilio", "Editar Domicilio");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("domicilioName", domicilio.getId() + " - " + domicilio.getNombre());
        
        TipoService tipoService = new TipoServiceImpl();  
        List<TipoModel> tiposModel = tipoService.getByType("tipodomicilio");
        Map<Integer, TipoModel> tiposModelMap = new HashMap<Integer, TipoModel>();
        for(TipoModel tipoModel :tiposModel) {
            tiposModelMap.put(tipoModel.getId(), tipoModel);
        }
        
        List<TipoModel> paisesModel = tipoService.getByType("pais");
        Map<Integer, TipoModel> paisesModelMap = new HashMap<Integer, TipoModel>();
        for(TipoModel tipoModel :paisesModel) {
            paisesModelMap.put(tipoModel.getId(), tipoModel);
        }
        
        List<TipoModel> provinciasModel = tipoService.getByType("provincia");
        Map<Integer, TipoModel> provinciasModelMap = new HashMap<Integer, TipoModel>();
        for(TipoModel tipoModel :provinciasModel) {
            provinciasModelMap.put(tipoModel.getId(), tipoModel);
        }
        
        //List<TipoModel> provinciasModel = tipoService.getByType("provincia");
        //Map<Integer, TipoModel> provinciasModelMap = new HashMap<Integer, TipoModel>();
        //for(TipoModel tipoModel :provinciasModel) {
            //TipoModel paisModel = tipoService.getByPk(tipoModel.getIdPais());
            //if(paisModel.getValor().equalsIgnoreCase("Argentina")) {
                //provinciasModelMap.put(tipoModel.getId(), tipoModel);
            //}
        //}
        
        List<TipoModel> localidadesModel = tipoService.getByType("localidad");
        Map<Integer, TipoModel> localidadesModelMap = new HashMap<Integer, TipoModel>();
        for(TipoModel tipoModel :localidadesModel) {
            localidadesModelMap.put(tipoModel.getId(), tipoModel);
        }
        
        //LocalidadService localidadService = new LocalidadServiceImpl(); 
        //List<LocalidadModel> localidadesModel = new ArrayList<LocalidadModel>();
        //if(domicilio.getIdProvincia() != null){
            //localidadesModel = localidadService.getByProvincia(domicilio.getIdProvincia());
        //} 
                
        List<DomicilioModel> domicilios = new ArrayList<DomicilioModel>();
        TipoService tipoServicee = new TipoServiceImpl(); 
        List<DomicilioDto> domiciliosDtos = new ArrayList<DomicilioDto>();
        ClienteDomicilioService clienteDomicilioService = new ClienteDomicilioServiceImpl();
        List<ClienteDomicilioModel> clienteDomicilios = clienteDomicilioService.getByPkCliente(Integer.valueOf(clientepk));
        if(clienteDomicilios != null && !clienteDomicilios.isEmpty()) {
            List<Integer> pkList = new ArrayList<Integer>();
            for(ClienteDomicilioModel provedorDomicilios :clienteDomicilios) {
                pkList.add(provedorDomicilios.getIdDomicilio());
            }
            domicilios = domicilioService.getByPkList(pkList);
            for(DomicilioModel c :domicilios) {
                DomicilioDto domicilioDto = new DomicilioDto();
                domicilioDto.setId(c.getId().toString());                
                domicilioDto.setNombre(c.getNombre());
                domicilioDto.setUbicacion(c.getUbicacion());
                
                TipoModel localidaad = null;
                TipoModel provinciaa = null;
                TipoModel paiis = null;
                
                Integer a = c.getIdLocalidad();
                Integer b = c.getIdProvincia();
                Integer d = c.getIdPais();
                
                if (a != null){
                    localidaad = tipoServicee.getByPk(a);
                }
                
                if (b != null){
                    provinciaa = tipoServicee.getByPk(b);
                }
                
                if (d != null){
                    paiis = tipoServicee.getByPk(d);
                }
    
 
                if (localidaad != null) {
                    domicilioDto.setLocalidad(localidaad.getValor());
                }
                
                if (provinciaa != null) {
                    domicilioDto.setProvincia(provinciaa.getValor());
                }
                
                if (paiis != null) {
                    domicilioDto.setPais(paiis.getValor());
                }

                TipoModel t = tiposModelMap.get(c.getIdTipo());
                if(t != null) {
                    domicilioDto.setTipo(t.getValor());
                }
                String nomContacto = c.getNombreContacto();
                if(nomContacto !=null){
                    domicilioDto.setNombreContacto(c.getNombreContacto());
                }
                String telContacto = c.getTelefonoContacto();
                if(telContacto !=null){
                    domicilioDto.setTelefonoContacto(c.getTelefonoContacto());
                }
                String horContacto = c.getHorarioContacto();
                if(horContacto !=null){
                    domicilioDto.setHorarioContacto(c.getHorarioContacto());
                }
                String obsContacto = c.getObservacionesContacto();
                if(obsContacto !=null){
                    domicilioDto.setObservacionesContacto(c.getObservacionesContacto());
                }
                
                domiciliosDtos.add(domicilioDto);
            }
        }             
        Map<String,String> tipos = new LinkedHashMap<String,String>();        
        if(tiposModel != null && !tiposModel.isEmpty()){
            for(TipoModel tipoModel :tiposModel) {
                tipos.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        Map<String,String> paises = new LinkedHashMap<String,String>();      
        if(paisesModel != null && !paisesModel.isEmpty()){
            for(TipoModel tipoModel :paisesModel) {
                paises.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        
        Map<String,String> provincias = new LinkedHashMap<String,String>();      
        if(provinciasModel != null && !provinciasModel.isEmpty()){
            for(TipoModel tipoModel :provinciasModel) {
                provincias.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        } 
        
        //Map<String,String> provincias = new LinkedHashMap<String,String>();        
        //if(provinciasModel != null && !provinciasModel.isEmpty()){
            //for(TipoModel tipoModel :provinciasModel) {
                //TipoModel paisModel = tipoService.getByPk(tipoModel.getIdPais());
                //if(paisModel.getValor().equalsIgnoreCase("Argentina")) {                
                    //provincias.put(tipoModel.getId().toString(), tipoModel.getValor());
                //}
            //}
        //}
        
        Map<String,String> localidades = new LinkedHashMap<String,String>();      
        if(localidadesModel != null && !localidadesModel.isEmpty()){
            for(TipoModel tipoModel :localidadesModel) {
                localidades.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        //Map<String,String> localidades = new LinkedHashMap<String,String>();        
        //if(localidadesModel != null && !localidadesModel.isEmpty()){
            //for(LocalidadModel localidadModel :localidadesModel) {
                //localidades.put(localidadModel.getId().toString(), localidadModel.getNombre());
            //}
        //}

        List<ContactoModel> contactos = new ArrayList<ContactoModel>();
        Map<String,String> contactosMap = new LinkedHashMap<String,String>();      
        ClienteContactoService clienteContactoService = new ClienteContactoServiceImpl();
        List<ClienteContactoModel> clienteContactos = clienteContactoService.getByPkCliente(Integer.valueOf(clientepk));
        if(clienteContactos != null && !clienteContactos.isEmpty()) {
            List<Integer> pkList = new ArrayList<Integer>();
            for(ClienteContactoModel provedorContactos :clienteContactos) {
                pkList.add(provedorContactos.getIdContacto());                
            }
            ContactoService contactoService = new ContactoServiceImpl();
            contactos = contactoService.getByPkList(pkList);
            for(ContactoModel c :contactos) {
                 contactosMap.put(c.getId().toString(), c.getNombre());
            }
        }
        
        model.addAttribute("tipoList", tipos);
        model.addAttribute("paisList", paises);
        model.addAttribute("provinciaList", provincias);
        model.addAttribute("localidadList", localidades);        
        model.addAttribute("contactoList", contactosMap);     
        model.addAttribute("domicilios", domiciliosDtos);       
                
        return "/domicilio/domicilio";        
    }
    
    @RequestMapping(value = "/domicilio/proveedorpk/{proveedorpk}/remove/{domiciliopk}", method = RequestMethod.GET)
    public String removeProveedorDomicilio(@PathVariable String proveedorpk, @PathVariable String domiciliopk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(domiciliopk == null) {
            model.addAttribute("errorMessage", "Error: Domicilio inválido");         
            return "/error/error";                
        }

        if(proveedorpk == null) {
            model.addAttribute("errorMessage", "Error: Proveedor inválido");         
            return "/error/error";                
        }
        
        DomicilioService domicilioService = new DomicilioServiceImpl();   
        DomicilioModel domicilio = domicilioService.getByPk(Integer.valueOf(domiciliopk));
        if(domicilio == null) {
            model.addAttribute("errorMessage", "Error: Domicilio inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        ProveedorService proveedorService = new ProveedorServiceImpl();   
        ProveedorModel proveedor = proveedorService.getByPk(Integer.valueOf(proveedorpk));
        if(proveedor == null) {
            model.addAttribute("errorMessage", "Error: Proveedor inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        String externalName = proveedor.getId() + " - " + proveedor.getRazonSocial();
        
        DomicilioForm domicilioForm = new DomicilioForm();
        domicilioForm.setPk(domicilio.getId().toString());
        domicilioForm.setUbicacion(domicilio.getUbicacion());
        domicilioForm.setNombre(domicilio.getNombre());
        if(domicilio.getUbicacion() != null) {
            domicilioForm.setUbicacion(domicilio.getUbicacion());
        }
        if(domicilio.getIdTipo() != null) {
            domicilioForm.setIdTipo(domicilio.getIdTipo().toString());
        }
        if(domicilio.getIdProvincia() != null) {
            domicilioForm.setIdProvincia(domicilio.getIdProvincia().toString());
        }
        if(domicilio.getIdLocalidad() != null) {
            domicilioForm.setIdLocalidad(domicilio.getIdLocalidad().toString());
        }
        if(domicilio.getIdContacto() != null) {
            domicilioForm.setIdContacto(domicilio.getIdContacto().toString());
        }
        if(domicilio.getNombreContacto() != null) {
            domicilioForm.setNombreContacto(domicilio.getNombreContacto());
        }
        if(domicilio.getTelefonoContacto() != null) {
            domicilioForm.setTelefonoContacto(domicilio.getTelefonoContacto());
        }
        if(domicilio.getHorarioContacto() != null) {
            domicilioForm.setHorarioContacto(domicilio.getHorarioContacto());
        }
        if(domicilio.getObservacionesContacto() != null) {
            domicilioForm.setObservacionesContacto(domicilio.getObservacionesContacto());
        }
        if(domicilio.getPuntoGps() != null) {
            domicilioForm.setPuntoGps(domicilio.getPuntoGps());
        }
        
        domicilioForm.setAction("remove");        
        model.addAttribute("domicilioForm", domicilioForm);  
        model.addAttribute("pkExternal", proveedorpk);
        model.addAttribute("prefixUrl", "proveedor");
        model.addAttribute("labelBase", "Proveedores");        
        model.addAttribute("externalName", externalName);
        model.addAttribute("tipoDomicilio", "proveedor");
        model.addAttribute("titleDomicilio", "Eliminar Domicilio");
        model.addAttribute("buttonLabel", "Eliminar");
        model.addAttribute("domicilioName", domicilio.getId() + " - " + domicilio.getNombre());
        
        TipoService tipoService = new TipoServiceImpl();  
        List<TipoModel> tiposModel = tipoService.getByType("tipodomicilio");
        Map<Integer, TipoModel> tiposModelMap = new HashMap<Integer, TipoModel>();
        for(TipoModel tipoModel :tiposModel) {
            tiposModelMap.put(tipoModel.getId(), tipoModel);
        }

        List<TipoModel> paisesModel = tipoService.getByType("pais");
        Map<Integer, TipoModel> paisesModelMap = new HashMap<Integer, TipoModel>();
        for(TipoModel tipoModel :paisesModel) {
            paisesModelMap.put(tipoModel.getId(), tipoModel);
        }
        
        List<TipoModel> provinciasModel = tipoService.getByType("provincia");
        Map<Integer, TipoModel> provinciasModelMap = new HashMap<Integer, TipoModel>();
        for(TipoModel tipoModel :provinciasModel) {
            provinciasModelMap.put(tipoModel.getId(), tipoModel);
        }

        //List<TipoModel> provinciasModel = tipoService.getByType("provincia");
        //Map<Integer, TipoModel> provinciasModelMap = new HashMap<Integer, TipoModel>();
        //for(TipoModel tipoModel :provinciasModel) {
            //TipoModel paisModel = tipoService.getByPk(tipoModel.getIdPais());
            //if(paisModel.getValor().equalsIgnoreCase("Argentina")) {
                //provinciasModelMap.put(tipoModel.getId(), tipoModel);
            //}
        //}
        List<TipoModel> localidadesModel = tipoService.getByType("localidad");
        Map<Integer, TipoModel> localidadesModelMap = new HashMap<Integer, TipoModel>();
        for(TipoModel tipoModel :localidadesModel) {
            localidadesModelMap.put(tipoModel.getId(), tipoModel);
        }
        
        //LocalidadService localidadService = new LocalidadServiceImpl(); 
        //List<LocalidadModel> localidadesModel = new ArrayList<LocalidadModel>();
        //if(domicilio.getIdProvincia() != null){
            //localidadesModel = localidadService.getByProvincia(domicilio.getIdProvincia());
        //} 
                
        List<DomicilioModel> domicilios = new ArrayList<DomicilioModel>();
        TipoService tipoServicee = new TipoServiceImpl();
        List<DomicilioDto> domiciliosDtos = new ArrayList<DomicilioDto>();
        ProveedorDomicilioService proveedorDomicilioService = new ProveedorDomicilioServiceImpl();
        List<ProveedorDomicilioModel> proveedorDomicilios = proveedorDomicilioService.getByPkProveedor(Integer.valueOf(proveedorpk));
        if(proveedorDomicilios != null && !proveedorDomicilios.isEmpty()) {
            List<Integer> pkList = new ArrayList<Integer>();
            for(ProveedorDomicilioModel provedorDomicilios :proveedorDomicilios) {
                pkList.add(provedorDomicilios.getIdDomicilio());
            }
            domicilios = domicilioService.getByPkList(pkList);
            for(DomicilioModel c :domicilios) {
                DomicilioDto domicilioDto = new DomicilioDto();
                domicilioDto.setId(c.getId().toString());                
                domicilioDto.setNombre(c.getNombre());
                domicilioDto.setUbicacion(c.getUbicacion());
                
                TipoModel localidaad = null;
                TipoModel provinciaa = null;
                TipoModel paiis = null;
                
                Integer a = c.getIdLocalidad();
                Integer b = c.getIdProvincia();
                Integer d = c.getIdPais();
                
                if (a != null){
                    localidaad = tipoServicee.getByPk(a);
                }
                
                if (b != null){
                    provinciaa = tipoServicee.getByPk(b);
                }
                
                if (d != null){
                    paiis = tipoServicee.getByPk(d);
                }
    
 
                if (localidaad != null) {
                    domicilioDto.setLocalidad(localidaad.getValor());
                }
                
                if (provinciaa != null) {
                    domicilioDto.setProvincia(provinciaa.getValor());
                }
                
                if (paiis != null) {
                    domicilioDto.setPais(paiis.getValor());
                }

                TipoModel t = tiposModelMap.get(c.getIdTipo());
                if(t != null) {
                    domicilioDto.setTipo(t.getValor());
                }
                String nomContacto = c.getNombreContacto();
                if(nomContacto !=null){
                    domicilioDto.setNombreContacto(c.getNombreContacto());
                }
                String telContacto = c.getTelefonoContacto();
                if(telContacto !=null){
                    domicilioDto.setTelefonoContacto(c.getTelefonoContacto());
                }
                String horContacto = c.getHorarioContacto();
                if(horContacto !=null){
                    domicilioDto.setHorarioContacto(c.getHorarioContacto());
                }
                String obsContacto = c.getObservacionesContacto();
                if(obsContacto !=null){
                    domicilioDto.setObservacionesContacto(c.getObservacionesContacto());
                }
                
                domiciliosDtos.add(domicilioDto);
            }
        }             
        Map<String,String> tipos = new LinkedHashMap<String,String>();        
        if(tiposModel != null && !tiposModel.isEmpty()){
            for(TipoModel tipoModel :tiposModel) {
                tipos.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        Map<String,String> paises = new LinkedHashMap<String,String>();      
        if(paisesModel != null && !paisesModel.isEmpty()){
            for(TipoModel tipoModel :paisesModel) {
                paises.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        
        
        Map<String,String> provincias = new LinkedHashMap<String,String>();      
        if(provinciasModel != null && !provinciasModel.isEmpty()){
            for(TipoModel tipoModel :provinciasModel) {
                provincias.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        //Map<String,String> provincias = new LinkedHashMap<String,String>();        
        //if(provinciasModel != null && !provinciasModel.isEmpty()){
            //for(TipoModel tipoModel :provinciasModel) {
                //TipoModel paisModel = tipoService.getByPk(tipoModel.getIdPais());
                //if(paisModel.getValor().equalsIgnoreCase("Argentina")) {                
                    //provincias.put(tipoModel.getId().toString(), tipoModel.getValor());
                //}
            //}
        //}
        
        Map<String,String> localidades = new LinkedHashMap<String,String>();      
        if(localidadesModel != null && !localidadesModel.isEmpty()){
            for(TipoModel tipoModel :localidadesModel) {
                localidades.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        //Map<String,String> localidades = new LinkedHashMap<String,String>();        
        //if(localidadesModel != null && !localidadesModel.isEmpty()){
            //for(LocalidadModel localidadModel :localidadesModel) {
                //localidades.put(localidadModel.getId().toString(), localidadModel.getNombre());
            //}
        //}

        List<ContactoModel> contactos = new ArrayList<ContactoModel>();
        Map<String,String> contactosMap = new LinkedHashMap<String,String>();      
        ProveedorContactoService proveedorContactoService = new ProveedorContactoServiceImpl();
        List<ProveedorContactoModel> proveedorContactos = proveedorContactoService.getByPkProveedor(Integer.valueOf(proveedorpk));
        if(proveedorContactos != null && !proveedorContactos.isEmpty()) {
            List<Integer> pkList = new ArrayList<Integer>();
            for(ProveedorContactoModel provedorContactos :proveedorContactos) {
                pkList.add(provedorContactos.getIdContacto());                
            }
            ContactoService contactoService = new ContactoServiceImpl();
            contactos = contactoService.getByPkList(pkList);
            for(ContactoModel c :contactos) {
                 contactosMap.put(c.getId().toString(), c.getNombre());
            }
        }
        
        model.addAttribute("tipoList", tipos);
        model.addAttribute("paisList", paises);
        model.addAttribute("provinciaList", provincias);
        model.addAttribute("localidadList", localidades);        
        model.addAttribute("contactoList", contactosMap);        
        model.addAttribute("domicilios", domiciliosDtos);       
                
        return "/domicilio/domicilio";        
    }    
    
    @RequestMapping(value = "/domicilio/clientepk/{clientepk}/remove/{domiciliopk}", method = RequestMethod.GET)
    public String removeClienteDomicilio(@PathVariable String clientepk, @PathVariable String domiciliopk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(domiciliopk == null) {
            model.addAttribute("errorMessage", "Error: Domicilio inválido");         
            return "/error/error";                
        }

        if(clientepk == null) {
            model.addAttribute("errorMessage", "Error: Cliente inválido");         
            return "/error/error";                
        }
        
        DomicilioService domicilioService = new DomicilioServiceImpl();   
        DomicilioModel domicilio = domicilioService.getByPk(Integer.valueOf(domiciliopk));
        if(domicilio == null) {
            model.addAttribute("errorMessage", "Error: Domicilio inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        ClienteService clienteService = new ClienteServiceImpl();   
        ClienteModel cliente = clienteService.getByPk(Integer.valueOf(clientepk));
        if(cliente == null) {
            model.addAttribute("errorMessage", "Error: Cliente inválido. No ha sido encontrado.");         
            return "/error/error";    
        }
        
        String externalName = cliente.getId() + " - " + cliente.getRazonSocial();
        
        DomicilioForm domicilioForm = new DomicilioForm();
        domicilioForm.setPk(domicilio.getId().toString());
        domicilioForm.setUbicacion(domicilio.getUbicacion());
        domicilioForm.setNombre(domicilio.getNombre());
        if(domicilio.getUbicacion() != null) {
            domicilioForm.setUbicacion(domicilio.getUbicacion());
        }
        if(domicilio.getIdTipo() != null) {
            domicilioForm.setIdTipo(domicilio.getIdTipo().toString());
        }
        if(domicilio.getIdProvincia() != null) {
            domicilioForm.setIdProvincia(domicilio.getIdProvincia().toString());
        }
        if(domicilio.getIdLocalidad() != null) {
            domicilioForm.setIdLocalidad(domicilio.getIdLocalidad().toString());
        }
        if(domicilio.getIdContacto() != null) {
            domicilioForm.setIdContacto(domicilio.getIdContacto().toString());
        }
        if(domicilio.getNombreContacto() != null) {
            domicilioForm.setNombreContacto(domicilio.getNombreContacto());
        }
        if(domicilio.getTelefonoContacto() != null) {
            domicilioForm.setTelefonoContacto(domicilio.getTelefonoContacto());
        }
        if(domicilio.getHorarioContacto() != null) {
            domicilioForm.setHorarioContacto(domicilio.getHorarioContacto());
        }
        if(domicilio.getObservacionesContacto() != null) {
            domicilioForm.setObservacionesContacto(domicilio.getObservacionesContacto());
        }
        if(domicilio.getPuntoGps() != null) {
            domicilioForm.setPuntoGps(domicilio.getPuntoGps());
        }
        
        domicilioForm.setAction("remove");        
        model.addAttribute("domicilioForm", domicilioForm);  
        model.addAttribute("pkExternal", clientepk);
        model.addAttribute("prefixUrl", "cliente");
        model.addAttribute("labelBase", "Clientes");        
        model.addAttribute("externalName", externalName);
        model.addAttribute("tipoDomicilio", "cliente");
        model.addAttribute("titleDomicilio", "Eliminar Domicilio");
        model.addAttribute("buttonLabel", "Eliminar");
        model.addAttribute("domicilioName", domicilio.getId() + " - " + domicilio.getNombre());
        
        TipoService tipoService = new TipoServiceImpl();  
        List<TipoModel> tiposModel = tipoService.getByType("tipodomicilio");
        Map<Integer, TipoModel> tiposModelMap = new HashMap<Integer, TipoModel>();
        for(TipoModel tipoModel :tiposModel) {
            tiposModelMap.put(tipoModel.getId(), tipoModel);
        }

        List<TipoModel> paisesModel = tipoService.getByType("pais");
        Map<Integer, TipoModel> paisesModelMap = new HashMap<Integer, TipoModel>();
        for(TipoModel tipoModel :paisesModel) {
            paisesModelMap.put(tipoModel.getId(), tipoModel);
        }
        
        List<TipoModel> provinciasModel = tipoService.getByType("provincia");
        Map<Integer, TipoModel> provinciasModelMap = new HashMap<Integer, TipoModel>();
        for(TipoModel tipoModel :provinciasModel) {
            provinciasModelMap.put(tipoModel.getId(), tipoModel);
        }
        
        //List<TipoModel> provinciasModel = tipoService.getByType("provincia");
        //Map<Integer, TipoModel> provinciasModelMap = new HashMap<Integer, TipoModel>();
        //for(TipoModel tipoModel :provinciasModel) {
            //TipoModel paisModel = tipoService.getByPk(tipoModel.getIdPais());
            //if(paisModel.getValor().equalsIgnoreCase("Argentina")) {            
                //provinciasModelMap.put(tipoModel.getId(), tipoModel);
            //}
        //}
        
        List<TipoModel> localidadesModel = tipoService.getByType("localidad");
        Map<Integer, TipoModel> localidadesModelMap = new HashMap<Integer, TipoModel>();
        for(TipoModel tipoModel :localidadesModel) {
            localidadesModelMap.put(tipoModel.getId(), tipoModel);
        }
        
        //LocalidadService localidadService = new LocalidadServiceImpl(); 
        //List<LocalidadModel> localidadesModel = new ArrayList<LocalidadModel>();
        //if(domicilio.getIdProvincia() != null){
            //localidadesModel = localidadService.getByProvincia(domicilio.getIdProvincia());
        //} 
                        
        List<DomicilioModel> domicilios = new ArrayList<DomicilioModel>();
        TipoService tipoServicee = new TipoServiceImpl();
        List<DomicilioDto> domiciliosDtos = new ArrayList<DomicilioDto>();
        ClienteDomicilioService clienteDomicilioService = new ClienteDomicilioServiceImpl();
        List<ClienteDomicilioModel> clienteDomicilios = clienteDomicilioService.getByPkCliente(Integer.valueOf(clientepk));
        if(clienteDomicilios != null && !clienteDomicilios.isEmpty()) {
            List<Integer> pkList = new ArrayList<Integer>();
            for(ClienteDomicilioModel provedorDomicilios :clienteDomicilios) {
                pkList.add(provedorDomicilios.getIdDomicilio());
            }
            domicilios = domicilioService.getByPkList(pkList);
            for(DomicilioModel c :domicilios) {
                DomicilioDto domicilioDto = new DomicilioDto();
                domicilioDto.setId(c.getId().toString());                
                domicilioDto.setNombre(c.getNombre());
                domicilioDto.setUbicacion(c.getUbicacion());
                
                TipoModel localidaad = null;
                TipoModel provinciaa = null;
                TipoModel paiis = null;
                
                Integer a = c.getIdLocalidad();
                Integer b = c.getIdProvincia();
                Integer d = c.getIdPais();
                
                if (a != null){
                    localidaad = tipoServicee.getByPk(a);
                }
                
                if (b != null){
                    provinciaa = tipoServicee.getByPk(b);
                }
                
                if (d != null){
                    paiis = tipoServicee.getByPk(d);
                }
    
 
                if (localidaad != null) {
                    domicilioDto.setLocalidad(localidaad.getValor());
                }
                
                if (provinciaa != null) {
                    domicilioDto.setProvincia(provinciaa.getValor());
                }
                
                if (paiis != null) {
                    domicilioDto.setPais(paiis.getValor());
                }

                TipoModel t = tiposModelMap.get(c.getIdTipo());
                if(t != null) {
                    domicilioDto.setTipo(t.getValor());
                }
                String nomContacto = c.getNombreContacto();
                if(nomContacto !=null){
                    domicilioDto.setNombreContacto(c.getNombreContacto());
                }
                String telContacto = c.getTelefonoContacto();
                if(telContacto !=null){
                    domicilioDto.setTelefonoContacto(c.getTelefonoContacto());
                }
                String horContacto = c.getHorarioContacto();
                if(horContacto !=null){
                    domicilioDto.setHorarioContacto(c.getHorarioContacto());
                }
                String obsContacto = c.getObservacionesContacto();
                if(obsContacto !=null){
                    domicilioDto.setObservacionesContacto(c.getObservacionesContacto());
                }
                
                domiciliosDtos.add(domicilioDto);
            }
        }             
        Map<String,String> tipos = new LinkedHashMap<String,String>();        
        if(tiposModel != null && !tiposModel.isEmpty()){
            for(TipoModel tipoModel :tiposModel) {
                tipos.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        Map<String,String> paises = new LinkedHashMap<String,String>();      
        if(paisesModel != null && !paisesModel.isEmpty()){
            for(TipoModel tipoModel :paisesModel) {
                paises.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        
        Map<String,String> provincias = new LinkedHashMap<String,String>();      
        if(provinciasModel != null && !provinciasModel.isEmpty()){
            for(TipoModel tipoModel :provinciasModel) {
                provincias.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        
        //Map<String,String> provincias = new LinkedHashMap<String,String>();        
        //if(provinciasModel != null && !provinciasModel.isEmpty()){
            //for(TipoModel tipoModel :provinciasModel) {
                //TipoModel paisModel = tipoService.getByPk(tipoModel.getIdPais());
                //if(paisModel.getValor().equalsIgnoreCase("Argentina")) {                
                    //provincias.put(tipoModel.getId().toString(), tipoModel.getValor());
                //}
            //}
        //}
        
        Map<String,String> localidades = new LinkedHashMap<String,String>();      
        if(localidadesModel != null && !localidadesModel.isEmpty()){
            for(TipoModel tipoModel :localidadesModel) {
                localidades.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        //Map<String,String> localidades = new LinkedHashMap<String,String>();        
        //if(localidadesModel != null && !localidadesModel.isEmpty()){
            //for(LocalidadModel localidadModel :localidadesModel) {
                //localidades.put(localidadModel.getId().toString(), localidadModel.getNombre());
            //}
        //}

        List<ContactoModel> contactos = new ArrayList<ContactoModel>();
        Map<String,String> contactosMap = new LinkedHashMap<String,String>();      
        ClienteContactoService clienteContactoService = new ClienteContactoServiceImpl();
        List<ClienteContactoModel> clienteContactos = clienteContactoService.getByPkCliente(Integer.valueOf(clientepk));
        if(clienteContactos != null && !clienteContactos.isEmpty()) {
            List<Integer> pkList = new ArrayList<Integer>();
            for(ClienteContactoModel provedorContactos :clienteContactos) {
                pkList.add(provedorContactos.getIdContacto());                
            }
            ContactoService contactoService = new ContactoServiceImpl();
            contactos = contactoService.getByPkList(pkList);
            for(ContactoModel c :contactos) {
                 contactosMap.put(c.getId().toString(), c.getNombre());
            }
        }
        
        model.addAttribute("tipoList", tipos);
        model.addAttribute("paisList", paises);
        model.addAttribute("provinciaList", provincias);
        model.addAttribute("localidadList", localidades);        
        model.addAttribute("contactoList", contactosMap);        
        model.addAttribute("domicilios", domiciliosDtos);       
                
        return "/domicilio/domicilio";        
    }    

    //@ResponseBody
    //@RequestMapping(value = "/domicilio/getlocalidades/{idProvincia}", method = RequestMethod.GET)
    //public List<LocalidadBean> getLocalidadesByProvincia(@PathVariable String idProvincia, HttpServletRequest req, ModelMap model) {
        
        //System.out.println("**** /domicilio/getlocalidades/{idProvincia} : "+idProvincia);
        //LocalidadService localidadService = new LocalidadServiceImpl(); 
        //List<LocalidadModel> localidadesModel = new ArrayList<LocalidadModel>();
        //List<LocalidadBean> localidadesBean = new ArrayList<LocalidadBean>();
        //if(idProvincia != null){
            //localidadesModel = localidadService.getByProvincia(Integer.valueOf(idProvincia));
        //} 
        
        //for(LocalidadModel localidad: localidadesModel) {
            //LocalidadBean localidadBean = new LocalidadBean();
            //localidadBean.setId(localidad.getId().toString());
            //localidadBean.setNombre(localidad.getNombre());
            //localidadBean.setIdProvincia(localidad.getIdProvincia().toString());
            
            //localidadesBean.add(localidadBean);
        //}
        //return localidadesBean;
    //}

    //@ResponseBody
    //@RequestMapping(value = "/domicilio/getprovincias/{idPais}", method = RequestMethod.GET)
    //public List<ProvinciaBean> getProvinciasByPais(@PathVariable String idPais, HttpServletRequest req, ModelMap model) {
        
        //System.out.println("**** /domicilio/getprovincias/{idPais} : "+idPais);
        //TipoService tipoService = new TipoServiceImpl(); 
        //List<TipoModel> provinciasModel = new ArrayList<TipoModel>();
        //List<ProvinciaBean> provinciasBean = new ArrayList<ProvinciaBean>();
        //if(idPais != null){
            //provinciasModel = tipoService.getByType("provincia");
        //} 
        
        //for(TipoModel provincia: provinciasModel) {
            //if(provincia.getIdPais() == Integer.valueOf(idPais)) {
                //ProvinciaBean provinciaBean = new ProvinciaBean();
                //provinciaBean.setId(provincia.getId().toString());
                //provinciaBean.setNombre(provincia.getValor());
                //provinciaBean.setIdPais(provincia.getIdPais().toString());

                //provinciasBean.add(provinciaBean);
            //}
        //}
        //return provinciasBean;
    //}

    
}
