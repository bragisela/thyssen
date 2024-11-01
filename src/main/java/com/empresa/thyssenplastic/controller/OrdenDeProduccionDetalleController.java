/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

 
import com.empresa.thyssenplastic.controller.beans.ArticuloBean;
import com.empresa.thyssenplastic.controller.beans.ArticuloFichaTecnicaBean;
import com.empresa.thyssenplastic.controller.form.OrdenDeProduccionDetalleForm;
import com.empresa.thyssenplastic.controller.form.OrdenDeProduccionForm;
import com.empresa.thyssenplastic.controller.form.UserForm;
import com.empresa.thyssenplastic.model.GraficoBobinaDetalleModel;
import com.empresa.thyssenplastic.model.GraficoBobinaModel;
import com.empresa.thyssenplastic.dto.OrdenDeProduccionBobinaDto;
import com.empresa.thyssenplastic.dto.OrdenDeProduccionBobinaSelectedDto;
import com.empresa.thyssenplastic.dto.OrdenDeProduccionBultoDto;
import com.empresa.thyssenplastic.dto.OrdenDeProduccionBultoSelectedDto;
import com.empresa.thyssenplastic.dto.OrdenDeProduccionDto;
import com.empresa.thyssenplastic.dto.OrdenDeProduccionPalletDto;
import com.empresa.thyssenplastic.dto.OrdenDeProduccionScrapDto;
import com.empresa.thyssenplastic.model.ActivacionManualModel;
import com.empresa.thyssenplastic.model.ArticuloEtiquetaModel;
import com.empresa.thyssenplastic.model.ArticuloFichaTecnicaModel;
import com.empresa.thyssenplastic.model.ArticuloModel;
import com.empresa.thyssenplastic.model.ClienteModel;
import com.empresa.thyssenplastic.model.MateriaPrimaModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionBobinaModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionBultoModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionModel;
import com.empresa.thyssenplastic.service.impl.GraficoBobinaDetalleServiceImpl;
import com.empresa.thyssenplastic.model.OrdenDeProduccionPalletBultoModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionPalletModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionScrapModel;
import com.empresa.thyssenplastic.model.TipoModel;
import com.empresa.thyssenplastic.dto.GraficoBobinaDto;
import com.empresa.thyssenplastic.model.RemitoDetalleScrapModel;
import com.empresa.thyssenplastic.model.UserModel;
import com.empresa.thyssenplastic.service.OrdenDeProduccionBobinaService;
import com.empresa.thyssenplastic.service.GraficoBobinaService;
import com.empresa.thyssenplastic.service.ArticuloEtiquetaService;
import com.empresa.thyssenplastic.service.ArticuloFichaTecnicaService;
import com.empresa.thyssenplastic.service.ArticuloService;
import com.empresa.thyssenplastic.service.ClienteService;
import com.empresa.thyssenplastic.service.MateriaPrimaService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionBobinaService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionBultoService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionPalletBultoService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionPalletService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionScrapService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionService;
import com.empresa.thyssenplastic.service.TipoService;
import com.empresa.thyssenplastic.service.UserService;
import com.empresa.thyssenplastic.service.impl.ArticuloEtiquetaServiceImpl;
import com.empresa.thyssenplastic.service.impl.ArticuloFichaTecnicaServiceImpl;
import com.empresa.thyssenplastic.service.impl.ArticuloServiceImpl;
import com.empresa.thyssenplastic.service.impl.ClienteServiceImpl;
import com.empresa.thyssenplastic.service.impl.MateriaPrimaServiceImpl;
import com.empresa.thyssenplastic.service.GraficoBobinaDetalleService;
import com.empresa.thyssenplastic.service.impl.GraficoBobinaServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionBobinaServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionBultoServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionPalletBultoServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionPalletServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionScrapServiceImpl;
import com.empresa.thyssenplastic.service.ActivacionManualService;
import com.empresa.thyssenplastic.service.RemitoDetalleScrapService;
import com.empresa.thyssenplastic.service.impl.ActivacionManualServiceImpl;
import com.empresa.thyssenplastic.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionServiceImpl;
import com.empresa.thyssenplastic.service.impl.RemitoDetalleScrapServiceImpl;
import com.empresa.thyssenplastic.service.impl.TipoServiceImpl;
import com.empresa.thyssenplastic.service.impl.UserServiceImpl;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Collections;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author gusta
 */
@Controller
public class OrdenDeProduccionDetalleController {
    private int cantidadDeBobinasQueNoEstanEnBulto = 0;
    private int cantidadDeBultosQueNoEstanEnPallet = 0;
    private int cantidadPallet = 0;
    private int cantidadBobinasEnProduccion = 0;
    private int cantidadScrap = 0;
    
    
    public OrdenDeProduccionDetalleController() {
       this.cantidadDeBobinasQueNoEstanEnBulto = 0;
       this.cantidadDeBultosQueNoEstanEnPallet = 0;
       this.cantidadPallet = 0;
       this.cantidadBobinasEnProduccion = 0;
       this.cantidadScrap = 0;
    };
    
    @RequestMapping(value = "/accesodirectograficopolar", method = RequestMethod.GET)
    public String getHomeAcceso(HttpServletRequest req, ModelMap model) {

        if (!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());
            return "/login/login";
        }
        
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();
        List<OrdenDeProduccionModel> ordenesDeProduccionModel = ordenDeProduccionService.getAll();
        
        List<String> ordeness = new ArrayList<String>();
        
        if(ordenesDeProduccionModel != null && !ordenesDeProduccionModel.isEmpty()){
            for(OrdenDeProduccionModel ordenDeProduccionModel :ordenesDeProduccionModel) {
                ordeness.add(ordenDeProduccionModel.getId().toString());
            }
        }
        
        Collections.reverse(ordeness);
       
        model.addAttribute("provinciaList", ordeness);
        
        return "/accesodirectograficopolar/accesodirectograficopolar";
    }
    
    @RequestMapping(value = "/accesoDirectoGraficoPolarDetalle/{ordenDeProduccionPk}", method = RequestMethod.GET)
    public String getHomeOrdenDeProduccionGraficoDetalle(@PathVariable String ordenDeProduccionPk,HttpServletRequest req, ModelMap model) {

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeProduccionPk == null) {
            model.addAttribute("errorMessage", "Error: orden de producción inválida");         
            return "/error";                            
        }
        
        if(user.getRol() != Utils.ROL_OFICINA && user.getRol() != Utils.ROL_PLANTA && user.getRol() != Utils.ROL_DEPOSITO) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
        
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();   
        OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(Integer.valueOf(ordenDeProduccionPk));

        if(ordenDeProduccion == null) {
            model.addAttribute("errorMessage", "Error: orden de produccion no encontrada");         
            return "/error";                
        }
        
        ClienteService clienteService = new ClienteServiceImpl();
        ClienteModel cliente = clienteService.getByPk(ordenDeProduccion.getIdCliente());
        if(cliente == null) {
            model.addAttribute("errorMessage", "Error: cliente no encontrado");         
            return "/error";                            
        }

        ArticuloService articuloService = new ArticuloServiceImpl();
        ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
        if(articulo == null) {
            model.addAttribute("errorMessage", "Error: articulo no encontrado");         
            return "/error";                            
        }
        
        ArticuloFichaTecnicaService articuloFichaTecnicaService = new ArticuloFichaTecnicaServiceImpl();
        ArticuloFichaTecnicaModel articuloFichaTecnica = articuloFichaTecnicaService.getByPk(ordenDeProduccion.getIdFichaTecnica());
        if(articuloFichaTecnica == null) {
            model.addAttribute("errorMessage", "Error: ficha tecnica no encontrada");         
            return "/error";                            
        }
        
        String rol = "";    
        String operacion = "";        

        OrdenDeProduccionDetalleForm ordenDeProduccionDetalleForm = new OrdenDeProduccionDetalleForm();
        ordenDeProduccionDetalleForm.setPk("-1");
        ordenDeProduccionDetalleForm.setAction("add");        
        ordenDeProduccionDetalleForm.setTipoDetalle("bobina");
        ordenDeProduccionDetalleForm.setImprimir("false");
        
        ordenDeProduccionDetalleForm.setBobinaSelectedLabel("No existe bobina seleccionada.");
        ordenDeProduccionDetalleForm.setEstadoBobinaSelectedLabel("");
        ordenDeProduccionDetalleForm.setIdBobinaSelected("-1");
        
        ordenDeProduccionDetalleForm.setFechaAltaOrdenDeProduccion(ordenDeProduccion.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        ordenDeProduccionDetalleForm.setEstadoOrdenDeProduccion(ordenDeProduccion.getEstado());
        ordenDeProduccionDetalleForm.setIdOrdenProduccion(ordenDeProduccion.getId().toString());
        ordenDeProduccionDetalleForm.setIdCliente(ordenDeProduccion.getIdCliente().toString());
        ordenDeProduccionDetalleForm.setIdArticulo(ordenDeProduccion.getIdArticulo().toString());
        ordenDeProduccionDetalleForm.setIdFichaTecnica(ordenDeProduccion.getIdFichaTecnica().toString());
        if(articuloFichaTecnica.getPeso() != null) {
            ordenDeProduccionDetalleForm.setPesoTecnicoArticulo(articuloFichaTecnica.getPeso().toString());
        } else {
            ordenDeProduccionDetalleForm.setPesoTecnicoArticulo("No disponible");
        }
        if(articuloFichaTecnica.getMetros() != null) {
            ordenDeProduccionDetalleForm.setMetrosArticulo(articuloFichaTecnica.getMetros().toString());
        } else {
            ordenDeProduccionDetalleForm.setMetrosArticulo("No disponible");
        }
        if(articulo.getAlto() != null) {
            ordenDeProduccionDetalleForm.setAltoArticulo(articulo.getAlto().toString());
        } else {
            ordenDeProduccionDetalleForm.setAltoArticulo("No disponible");
        }
        if(articulo.getEspesor() != null) {
            ordenDeProduccionDetalleForm.setEspesorArticulo(articulo.getEspesor().toString());
        } else {
            ordenDeProduccionDetalleForm.setEspesorArticulo("No disponible");
        }
        if(articulo.getAncho() != null) {
            ordenDeProduccionDetalleForm.setAnchoArticulo(articulo.getAncho().toString());
        } else {
            ordenDeProduccionDetalleForm.setAnchoArticulo("No disponible");
        }
        if(articulo.getEspesor() != null) {
            ordenDeProduccionDetalleForm.setEspesorBobina(articulo.getEspesor().toString());
        } else {
            ordenDeProduccionDetalleForm.setEspesorBobina("No disponible");
        }
        
        model.addAttribute("ordenDeProduccionDetalleForm", ordenDeProduccionDetalleForm);  
        model.addAttribute("titleOrdenDeProduccion", "Acceso Directo Grafico Polar Detalle");  
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("buttonSearchBobinaLabel", "Buscar");
        model.addAttribute("buttonSearchBultoLabel", "Agregar");
        
   
        OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();
                
        List<OrdenDeProduccionBobinaModel> ordenDeProduccionBobinasModel = ordenDeProduccionBobinaService.getAllByOrdenDeProduccion(ordenDeProduccion.getId());       
        List<OrdenDeProduccionBobinaDto> ordenDeProduccionBobinasDtos = new ArrayList<OrdenDeProduccionBobinaDto>();
        List<GraficoBobinaDto> graficoBobinasDtos = new ArrayList<GraficoBobinaDto>();
        
        if(ordenDeProduccionBobinasModel != null && !ordenDeProduccionBobinasModel.isEmpty()) {
            for(OrdenDeProduccionBobinaModel ordenDeProduccionBobinaModel: ordenDeProduccionBobinasModel) {
                OrdenDeProduccionBobinaDto ordenDeProduccionBobinaDto = new OrdenDeProduccionBobinaDto();
                ordenDeProduccionBobinaDto.setPk(ordenDeProduccionBobinaModel.getId().toString());
                ordenDeProduccionBobinaDto.setFechaAlta(ordenDeProduccionBobinaModel.getFechaAlta().toString().replace(".0", ""));
                if(ordenDeProduccionBobinaModel.getEstado() != null) {
                    ordenDeProduccionBobinaDto.setEstado(ordenDeProduccionBobinaModel.getEstado());
                    if(ordenDeProduccionBobinaModel.getEstado().equals("ok")) {
                        ordenDeProduccionBobinaDto.setEstadoLabel("OK");
                    }
                    if(ordenDeProduccionBobinaModel.getEstado().equals("observado")) {
                        ordenDeProduccionBobinaDto.setEstadoLabel("Observado");
                    }
                    if(ordenDeProduccionBobinaModel.getEstado().equals("rechazado")) {
                        ordenDeProduccionBobinaDto.setEstadoLabel("Rechazado");
                    }
                    if(ordenDeProduccionBobinaModel.getEstado().equals("sinmesurar")) {
                        ordenDeProduccionBobinaDto.setEstadoLabel("Sin Mesurar");
                    }                
                }
                
                
                
                ordenDeProduccionBobinaDto.setPk(ordenDeProduccionBobinaModel.getId().toString());
                ordenDeProduccionBobinaDto.setCodigo(ordenDeProduccionBobinaModel.getCodigo());
                ordenDeProduccionBobinaDto.setPesoTotal(ordenDeProduccionBobinaModel.getPesoTotal().toString());
                ordenDeProduccionBobinaDto.setPesoNeto(ordenDeProduccionBobinaModel.getPesoNeto().toString());
                ordenDeProduccionBobinaDto.setEspesor(articulo.getEspesor().toString());
                ordenDeProduccionBobinaDto.setEstaEnBulto(ordenDeProduccionBobinaModel.getEstaEnBulto().toString());
              
                if(ordenDeProduccionBobinaModel.getEstaEnBulto()) {
                    ordenDeProduccionBobinaDto.setEstaEnBultoLabel("Si");
                    this.cantidadBobinasEnProduccion = this.cantidadBobinasEnProduccion + 1;
                } else {
                    ordenDeProduccionBobinaDto.setEstaEnBultoLabel("No");
                    this.cantidadDeBobinasQueNoEstanEnBulto = this.cantidadDeBobinasQueNoEstanEnBulto + 1;
                    this.cantidadBobinasEnProduccion = this.cantidadBobinasEnProduccion + 1;
                }

                if(!ordenDeProduccionBobinaModel.getEstaEnBulto()) {
                    ordenDeProduccionBobinasDtos.add(ordenDeProduccionBobinaDto);
                }
                
        
        GraficoBobinaService graficoBobinaService = new GraficoBobinaServiceImpl();   
        List<GraficoBobinaModel> graficoBobinas = graficoBobinaService.getByIdOrdenDeProduccionBobina(ordenDeProduccionBobinaModel.getId());
     
        for(GraficoBobinaModel graficoBobina: graficoBobinas) {
            System.out.println("Id: " + graficoBobina.getId());
                
            OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(graficoBobina.getIdOrdenDeProduccionBobina());
          
            GraficoBobinaDto graficoBobinaDto = new GraficoBobinaDto();
            graficoBobinaDto.setPk(graficoBobina.getId().toString());
            graficoBobinaDto.setFechaAlta(graficoBobina.getFechaAlta().toString().replace(".0", ""));
            graficoBobinaDto.setEspesorNominal(graficoBobina.getEspesorNominal().toString());
            
            GraficoBobinaDetalleService graficoBobinaDetalleService = new GraficoBobinaDetalleServiceImpl();
            List<GraficoBobinaDetalleModel> detalle = graficoBobinaDetalleService.getByIdGraficoBobina(graficoBobina.getId());
            graficoBobinaDto.setMediciones(String.valueOf(detalle.size()));
            graficoBobinaDto.setCodigo(bobina.getCodigo());
            
            if(detalle.size() >= 5) {
                graficoBobinaDto.setTieneMediciones("true");
            } else {
                graficoBobinaDto.setTieneMediciones("false");
            }
                
            graficoBobinasDtos.add(graficoBobinaDto);
        }

            }
        }
                
        Map<String,String> bobinaDisponibleList = new LinkedHashMap<String,String>();
        List<OrdenDeProduccionBobinaModel> ordenDeProduccionBobinasAvailablesModel = ordenDeProduccionBobinaService.getAllAvailableByOrdenDeProduccion(ordenDeProduccion.getId());       
        if(ordenDeProduccionBobinasAvailablesModel != null && !ordenDeProduccionBobinasAvailablesModel.isEmpty()){
            for(OrdenDeProduccionBobinaModel bobinaModel :ordenDeProduccionBobinasAvailablesModel) {
                if(bobinaModel.getEstado() != null) {
                    bobinaDisponibleList.put(bobinaModel.getId().toString(), bobinaModel.getCodigo() + "(" + bobinaModel.getEstado() + ")");
                } else {
                    bobinaDisponibleList.put(bobinaModel.getId().toString(), bobinaModel.getCodigo());
                }
            }
        }
    
        model.addAttribute("bobinaDisponibleList", bobinaDisponibleList);
        model.addAttribute("ordenDeProduccionBobinas", ordenDeProduccionBobinasDtos);
        model.addAttribute("graficoBobinas", graficoBobinasDtos); 
         model.addAttribute("ordenDeProduccionPk", ordenDeProduccionPk);
   
        
        System.out.println("*** ordenDeProduccionBobinas size:"+ordenDeProduccionBobinasDtos.size());
        
        return "/accesodirectograficopolar/accesodirectograficopolardetalle";
    }
    

    @RequestMapping(value = "/ordenDeProduccionDetalle/{ordenDeProduccionPk}", method = RequestMethod.GET)
    public String getHomeOrdenDeProduccionDetalle(@PathVariable String ordenDeProduccionPk,HttpServletRequest req, ModelMap model) {

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeProduccionPk == null) {
            model.addAttribute("errorMessage", "Error: orden de producción inválida");         
            return "/error";                            
        }
        
        if(user.getRol() != Utils.ROL_OFICINA && user.getRol() != Utils.ROL_PLANTA && user.getRol() != Utils.ROL_DEPOSITO) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
        
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();   
        OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(Integer.valueOf(ordenDeProduccionPk));

        if(ordenDeProduccion == null) {
            model.addAttribute("errorMessage", "Error: orden de produccion no encontrada");         
            return "/error";                
        }
        
        ClienteService clienteService = new ClienteServiceImpl();
        ClienteModel cliente = clienteService.getByPk(ordenDeProduccion.getIdCliente());
        //if(cliente == null) {
            //model.addAttribute("errorMessage", "Error: cliente no encontrado");         
            //return "/error";                            
        //}

        ArticuloService articuloService = new ArticuloServiceImpl();
        ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
        if(articulo == null) {
            model.addAttribute("errorMessage", "Error: articulo no encontrado");         
            return "/error";                            
        }
        
        ArticuloFichaTecnicaService articuloFichaTecnicaService = new ArticuloFichaTecnicaServiceImpl();
        ArticuloFichaTecnicaModel articuloFichaTecnica = articuloFichaTecnicaService.getByPk(ordenDeProduccion.getIdFichaTecnica());
        if(articuloFichaTecnica == null) {
            model.addAttribute("errorMessage", "Error: ficha tecnica no encontrada");         
            return "/error";                            
        }
        
        String rol = "";    
        String operacion = "";        
        String displayActionButton = "block";
        String displaySearchBobinaButton = "block";        
        String displaySearchBultoButton = "block";
        String displayActionButtonScrap = "block";
        
        
        this.cantidadDeBobinasQueNoEstanEnBulto = 0;
        this.cantidadDeBultosQueNoEstanEnPallet = 0;
        this.cantidadPallet = 0;
        this.cantidadBobinasEnProduccion = 0;
                
        OrdenDeProduccionDetalleForm ordenDeProduccionDetalleForm = new OrdenDeProduccionDetalleForm();
        ordenDeProduccionDetalleForm.setPk("-1");
        ordenDeProduccionDetalleForm.setAction("add");        
        ordenDeProduccionDetalleForm.setTipoDetalle("bobina");
        ordenDeProduccionDetalleForm.setImprimir("false");
        
        ordenDeProduccionDetalleForm.setBobinaSelectedLabel("No existe bobina seleccionada.");
        ordenDeProduccionDetalleForm.setEstadoBobinaSelectedLabel("");
        ordenDeProduccionDetalleForm.setIdBobinaSelected("-1");
        
        ordenDeProduccionDetalleForm.setFechaAltaOrdenDeProduccion(ordenDeProduccion.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        ordenDeProduccionDetalleForm.setEstadoOrdenDeProduccion(ordenDeProduccion.getEstado());
        ordenDeProduccionDetalleForm.setIdOrdenProduccion(ordenDeProduccion.getId().toString());
        if(cliente == null){
          ordenDeProduccionDetalleForm.setIdCliente("Cliente eliminado");
        }else{
          ordenDeProduccionDetalleForm.setIdCliente(ordenDeProduccion.getIdCliente().toString());
        }
        ordenDeProduccionDetalleForm.setIdArticulo(ordenDeProduccion.getIdArticulo().toString());
        ordenDeProduccionDetalleForm.setIdFichaTecnica(ordenDeProduccion.getIdFichaTecnica().toString());
        if(articuloFichaTecnica.getPeso() != null) {
            ordenDeProduccionDetalleForm.setPesoTecnicoArticulo(articuloFichaTecnica.getPeso().toString());
        } else {
            ordenDeProduccionDetalleForm.setPesoTecnicoArticulo("No disponible");
        }
        if(articuloFichaTecnica.getMetros() != null) {
            ordenDeProduccionDetalleForm.setMetrosArticulo(articuloFichaTecnica.getMetros().toString());
        } else {
            ordenDeProduccionDetalleForm.setMetrosArticulo("No disponible");
        }
        if(articulo.getAlto() != null) {
            ordenDeProduccionDetalleForm.setAltoArticulo(articulo.getAlto().toString());
        } else {
            ordenDeProduccionDetalleForm.setAltoArticulo("No disponible");
        }
        if(articulo.getEspesor() != null) {
            ordenDeProduccionDetalleForm.setEspesorArticulo(articulo.getEspesor().toString());
        } else {
            ordenDeProduccionDetalleForm.setEspesorArticulo("No disponible");
        }
        if(articulo.getAncho() != null) {
            ordenDeProduccionDetalleForm.setAnchoArticulo(articulo.getAncho().toString());
        } else {
            ordenDeProduccionDetalleForm.setAnchoArticulo("No disponible");
        }
        if(articulo.getEspesor() != null) {
            ordenDeProduccionDetalleForm.setEspesorBobina(articulo.getEspesor().toString());
        } else {
            ordenDeProduccionDetalleForm.setEspesorBobina("No disponible");
        }
        String estado = ordenDeProduccion.getEstado();
 
        if(user.getRol() == Utils.ROL_OFICINA) {
            operacion = "alta";
            displayActionButton = "none";
            displayActionButtonScrap = "block";
            rol = "oficina";
        }
        if(user.getRol() == Utils.ROL_PLANTA) {
            if (!estado.equals("Nuevo")) {
                displayActionButton = "block";
            } else {
                displayActionButton = "none";
            }
            displayActionButtonScrap = "block";
            rol = "planta";
            operacion = "alta";
        }
        if(user.getRol() == Utils.ROL_DEPOSITO && estado != "Nuevo") {
            operacion = "alta";
            displayActionButton = "block";     
            displayActionButtonScrap = "none";
            rol = "deposito";
        }
                
        ordenDeProduccionDetalleForm.setOperacion(operacion);
        
        model.addAttribute("ordenDeProduccionDetalleForm", ordenDeProduccionDetalleForm);  
        model.addAttribute("titleOrdenDeProduccion", "Order De Producción Detalle");  
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("buttonSearchBobinaLabel", "Buscar");
        model.addAttribute("buttonSearchBultoLabel", "Agregar");
        
        OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();
                
        List<OrdenDeProduccionBobinaModel> ordenDeProduccionBobinasModel = ordenDeProduccionBobinaService.getAllByOrdenDeProduccion(ordenDeProduccion.getId());       
        List<OrdenDeProduccionBobinaDto> ordenDeProduccionBobinasDtos = new ArrayList<OrdenDeProduccionBobinaDto>();
        
        if(ordenDeProduccionBobinasModel != null && !ordenDeProduccionBobinasModel.isEmpty()) {
            for(OrdenDeProduccionBobinaModel ordenDeProduccionBobinaModel: ordenDeProduccionBobinasModel) {
                OrdenDeProduccionBobinaDto ordenDeProduccionBobinaDto = new OrdenDeProduccionBobinaDto();
                ordenDeProduccionBobinaDto.setPk(ordenDeProduccionBobinaModel.getId().toString());
                ordenDeProduccionBobinaDto.setFechaAlta(ordenDeProduccionBobinaModel.getFechaAlta().toString().replace(".0", ""));
                if(ordenDeProduccionBobinaModel.getEstado() != null) {
                    ordenDeProduccionBobinaDto.setEstado(ordenDeProduccionBobinaModel.getEstado());
                    if(ordenDeProduccionBobinaModel.getEstado().equals("ok")) {
                        ordenDeProduccionBobinaDto.setEstadoLabel("OK");
                    }
                    if(ordenDeProduccionBobinaModel.getEstado().equals("observado")) {
                        ordenDeProduccionBobinaDto.setEstadoLabel("Observado");
                    }
                    if(ordenDeProduccionBobinaModel.getEstado().equals("rechazado")) {
                        ordenDeProduccionBobinaDto.setEstadoLabel("Rechazado");
                    }
                    if(ordenDeProduccionBobinaModel.getEstado().equals("sinmesurar")) {
                        ordenDeProduccionBobinaDto.setEstadoLabel("Sin Mesurar");
                    }                
                }
                ordenDeProduccionBobinaDto.setCodigo(ordenDeProduccionBobinaModel.getCodigo());
                ordenDeProduccionBobinaDto.setPesoTotal(ordenDeProduccionBobinaModel.getPesoTotal().toString());
                ordenDeProduccionBobinaDto.setPesoNeto(ordenDeProduccionBobinaModel.getPesoNeto().toString());
                ordenDeProduccionBobinaDto.setEspesor(articulo.getEspesor().toString());
                ordenDeProduccionBobinaDto.setEstaEnBulto(ordenDeProduccionBobinaModel.getEstaEnBulto().toString());
                if(ordenDeProduccionBobinaModel.getEstaEnBulto()) {
                    ordenDeProduccionBobinaDto.setEstaEnBultoLabel("Si");
                    this.cantidadBobinasEnProduccion = this.cantidadBobinasEnProduccion + 1;
                } else {
                    ordenDeProduccionBobinaDto.setEstaEnBultoLabel("No");
                    this.cantidadDeBobinasQueNoEstanEnBulto = this.cantidadDeBobinasQueNoEstanEnBulto + 1;
                    this.cantidadBobinasEnProduccion = this.cantidadBobinasEnProduccion + 1;
                }
                
                if(ordenDeProduccionBobinaModel.getIdDeposito() != null) {
                    ordenDeProduccionBobinaDto.setEstaEnDeposito(true);
                } else {
                    ordenDeProduccionBobinaDto.setEstaEnDeposito(false);
                }
                
                
                
                

                if(!ordenDeProduccionBobinaModel.getEstaEnBulto()) {
                    ordenDeProduccionBobinasDtos.add(ordenDeProduccionBobinaDto);
                }

            }
        }
                
        Map<String,String> bobinaDisponibleList = new LinkedHashMap<String,String>();
        List<OrdenDeProduccionBobinaModel> ordenDeProduccionBobinasAvailablesModel = ordenDeProduccionBobinaService.getAllAvailableByOrdenDeProduccion(ordenDeProduccion.getId());       
        if(ordenDeProduccionBobinasAvailablesModel != null && !ordenDeProduccionBobinasAvailablesModel.isEmpty()){
            for(OrdenDeProduccionBobinaModel bobinaModel :ordenDeProduccionBobinasAvailablesModel) {
                if(bobinaModel.getEstado() != null) {
                    bobinaDisponibleList.put(bobinaModel.getId().toString(), bobinaModel.getCodigo() + "(" + bobinaModel.getEstado() + ")");
                } else {
                    bobinaDisponibleList.put(bobinaModel.getId().toString(), bobinaModel.getCodigo());
                }
            }
        }
        
        TipoService tipoService = new TipoServiceImpl();
        Map<String,String> plegadoraList = new LinkedHashMap<String,String>();
        List<TipoModel> plegadorasModel = tipoService.getByType("plegadoraBobina");       
        if(plegadorasModel != null && !plegadorasModel.isEmpty()){
            for(TipoModel tipoModel :plegadorasModel) {
                plegadoraList.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        
        Map<String,String> origenScrapList = new LinkedHashMap<String,String>();
        List<TipoModel> origenScrapModel = tipoService.getByType("origenScrap");       
        if(origenScrapModel != null && !origenScrapModel.isEmpty()){
            for(TipoModel tipoModel :origenScrapModel) {
                origenScrapList.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        Map<String,String> tipoMaterialScrapList = new LinkedHashMap<String,String>();
        List<TipoModel> tipoMaterialScrapModel = tipoService.getByType("tipoMaterialScrap");       
        if(tipoMaterialScrapModel != null && !tipoMaterialScrapModel.isEmpty()){
            for(TipoModel tipoModel :tipoMaterialScrapModel) {
                tipoMaterialScrapList.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        Map<String,String> motivoScrapList = new LinkedHashMap<String,String>();
        List<TipoModel> motivoScrapModel = tipoService.getByType("motivoScrap");       
        if(motivoScrapModel != null && !motivoScrapModel.isEmpty()){
            for(TipoModel tipoModel :motivoScrapModel) {
                motivoScrapList.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        Map<String,String> formatoScrapList = new LinkedHashMap<String,String>();
        List<TipoModel> formatoScrapModel = tipoService.getByType("formatoScrap");       
        if(formatoScrapModel != null && !formatoScrapModel.isEmpty()){
            for(TipoModel tipoModel :formatoScrapModel) {
                formatoScrapList.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        
        OrdenDeProduccionBultoService ordenDeProduccionBultoService = new OrdenDeProduccionBultoServiceImpl();
        Map<String,String> bultoDisponibleList = new LinkedHashMap<String,String>();
        List<OrdenDeProduccionBultoModel> ordenDeProduccionBultoAvailablesModel = ordenDeProduccionBultoService.getAllAvailableByOrdenDeProduccion(ordenDeProduccion.getId());       
        if(ordenDeProduccionBultoAvailablesModel != null && !ordenDeProduccionBultoAvailablesModel.isEmpty()){
            for(OrdenDeProduccionBultoModel bultoModel :ordenDeProduccionBultoAvailablesModel) {
                if(bultoModel.getEstado() != null) {
                    bultoDisponibleList.put(bultoModel.getId().toString(), bultoModel.getCodigo() + " (" + bultoModel.getEstado() + ")");
                } else {
                    bultoDisponibleList.put(bultoModel.getId().toString(), bultoModel.getCodigo());
                }
            }
        }
        
        List<OrdenDeProduccionBultoModel> ordenDeProduccionBultosModel = ordenDeProduccionBultoService.getAllByOrdenDeProduccion(ordenDeProduccion.getId());       
        List<OrdenDeProduccionBultoDto> ordenDeProduccionBultosDtos = new ArrayList<OrdenDeProduccionBultoDto>();
        
        if(ordenDeProduccionBultosModel != null && !ordenDeProduccionBultosModel.isEmpty()) {
            for(OrdenDeProduccionBultoModel ordenDeProduccionBultoModel: ordenDeProduccionBultosModel) {
                OrdenDeProduccionBultoDto ordenDeProduccionBultoDto = new OrdenDeProduccionBultoDto();
                ordenDeProduccionBultoDto.setPk(ordenDeProduccionBultoModel.getId().toString());
                ordenDeProduccionBultoDto.setFechaAlta(ordenDeProduccionBultoModel.getFechaAlta().toString().replace(".0", ""));
                if(ordenDeProduccionBultoModel.getEstado() != null && !ordenDeProduccionBultoModel.getEstado().equalsIgnoreCase("-1")) {
                    ordenDeProduccionBultoDto.setEstado(ordenDeProduccionBultoModel.getEstado());
                    if(ordenDeProduccionBultoModel.getEstado().equals("ok")) {
                        ordenDeProduccionBultoDto.setEstadoLabel("OK");
                    }
                    if(ordenDeProduccionBultoModel.getEstado().equals("observado")) {
                        ordenDeProduccionBultoDto.setEstadoLabel("Observado");
                    }
                    if(ordenDeProduccionBultoModel.getEstado().equals("rechazado")) {
                        ordenDeProduccionBultoDto.setEstadoLabel("Rechazado");
                    }
                    if(ordenDeProduccionBultoModel.getEstado().equals("sinmesurar")) {
                        ordenDeProduccionBultoDto.setEstadoLabel("Sin Mesurar");
                    }                
                }
                ordenDeProduccionBultoDto.setCodigo(ordenDeProduccionBultoModel.getCodigo());
                if (ordenDeProduccionBultoModel.getIdOrdenDeProduccionBobina() != null) {
                    ordenDeProduccionBultoDto.setIdBobina(ordenDeProduccionBultoModel.getIdOrdenDeProduccionBobina().toString());
                }
                ordenDeProduccionBultoDto.setEstaEnPallet(ordenDeProduccionBultoModel.getEstaEnPallet().toString());
                if(ordenDeProduccionBultoModel.getEstaEnPallet()) {
                    ordenDeProduccionBultoDto.setEstaEnPalletLabel("Si");
                   
                } else {
                    ordenDeProduccionBultoDto.setEstaEnPalletLabel("No");
                    this.cantidadDeBultosQueNoEstanEnPallet = this.cantidadDeBultosQueNoEstanEnPallet + 1;
                }

                if(ordenDeProduccionBultoModel.getIdDeposito() != null) {
                    ordenDeProduccionBultoDto.setEstaEnDeposito(true);
                } else {
                    ordenDeProduccionBultoDto.setEstaEnDeposito(false);
                }                
                
                String plegadora = "";
                if(ordenDeProduccionBultoModel.getIdPlegadora() != null) {
                    TipoModel plegadoraModel = tipoService.getByPk(ordenDeProduccionBultoModel.getIdPlegadora());
                    if(plegadora != null) {
                        plegadora = plegadoraModel.getValor();
                    }
                }        
                ordenDeProduccionBultoDto.setPlegadora(plegadora);
                
                OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(ordenDeProduccionBultoModel.getIdOrdenDeProduccionBobina());
                if(bobina != null) {
                    ordenDeProduccionBultoDto.setPesoTotal(bobina.getPesoTotal().toString());
                    ordenDeProduccionBultoDto.setPesoNeto(bobina.getPesoNeto().toString());
                }

                if(!ordenDeProduccionBultoModel.getEstaEnPallet()) {
                    ordenDeProduccionBultosDtos.add(ordenDeProduccionBultoDto);
                }
                System.out.println("*** ordenDeProduccionBultosDtos size:"+ordenDeProduccionBultosDtos.size());
            }
        }

        OrdenDeProduccionPalletBultoService ordenDeProduccionPalletBultoService = new OrdenDeProduccionPalletBultoServiceImpl();
        OrdenDeProduccionPalletService ordenDeProduccionPalletService = new OrdenDeProduccionPalletServiceImpl();
        List<OrdenDeProduccionPalletModel> ordenDeProduccionPalletsModel = ordenDeProduccionPalletService.getAllByOrdenDeProduccion(ordenDeProduccion.getId());       
        List<OrdenDeProduccionPalletDto> ordenDeProduccionPalletsDtos = new ArrayList<OrdenDeProduccionPalletDto>();
        
        if(ordenDeProduccionPalletsModel != null && !ordenDeProduccionPalletsModel.isEmpty()) {
            for(OrdenDeProduccionPalletModel ordenDeProduccionPalletModel: ordenDeProduccionPalletsModel) {
                OrdenDeProduccionPalletDto ordenDeProduccionPalletDto = new OrdenDeProduccionPalletDto();
                ordenDeProduccionPalletDto.setPk(ordenDeProduccionPalletModel.getId().toString());
                ordenDeProduccionPalletDto.setFechaAlta(ordenDeProduccionPalletModel.getFechaAlta().toString().replace(".0", ""));
                if(ordenDeProduccionPalletModel.getEstado() != null && !ordenDeProduccionPalletModel.getEstado().equalsIgnoreCase("-1")) {
                    ordenDeProduccionPalletDto.setEstado(ordenDeProduccionPalletModel.getEstado());
                    if(ordenDeProduccionPalletModel.getEstado().equals("ok")) {
                        ordenDeProduccionPalletDto.setEstadoLabel("OK");
                    }
                    if(ordenDeProduccionPalletModel.getEstado().equals("observado")) {
                        ordenDeProduccionPalletDto.setEstadoLabel("Observado");
                    }
                    if(ordenDeProduccionPalletModel.getEstado().equals("rechazado")) {
                        ordenDeProduccionPalletDto.setEstadoLabel("Rechazado");
                    }
                    if(ordenDeProduccionPalletModel.getEstado().equals("sinmesurar")) {
                        ordenDeProduccionPalletDto.setEstadoLabel("Sin Mesurar");
                    }                
                }
                ordenDeProduccionPalletDto.setCodigo(ordenDeProduccionPalletModel.getCodigo());
                List<OrdenDeProduccionPalletBultoModel> palletbultoList = ordenDeProduccionPalletBultoService.getAllByOrdenDeProduccionPallet(ordenDeProduccionPalletModel.getId());
                System.out.println("*** palletbultoList:"+palletbultoList);
                ordenDeProduccionPalletDto.setCantidadBultos(String.valueOf(palletbultoList.size()));
                String listaCodigos = "";
              
                Map<String,String> mapaBultos = new LinkedHashMap<String,String>();
                for(OrdenDeProduccionPalletBultoModel item :palletbultoList) {
                OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(item.getIdOrdenDeProduccionBulto());
                if(bulto != null) {
                 System.out.print("CODIGOOO" + bulto.getIdOrdenDeProduccionBobina());
                 mapaBultos.put(bulto.getIdOrdenDeProduccionBobina().toString(), bulto.getCodigo());
                 listaCodigos += bulto.getCodigo() + " ";
                }
               }
               ordenDeProduccionPalletDto.setMapaBultos(mapaBultos);
               ordenDeProduccionPalletDto.setListaCodigoBultos(listaCodigos);
               
               if(ordenDeProduccionPalletModel.getIdDeposito() != null) {
                    ordenDeProduccionPalletDto.setEstaEnDeposito(true);
                } else {
                    ordenDeProduccionPalletDto.setEstaEnDeposito(false);
                } 

                Double pesoTotal = 0.0;
                if(!palletbultoList.isEmpty()) {
                    for(OrdenDeProduccionPalletBultoModel palletbulto: palletbultoList) {
                        OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(palletbulto.getIdOrdenDeProduccionBulto());
                        if(bulto != null) {
                            OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(bulto.getIdOrdenDeProduccionBobina());
                            if(bobina != null) {
                                pesoTotal += bobina.getPesoTotal();
                            }
                        }
                    }
                }
                ordenDeProduccionPalletDto.setPesoTotal(pesoTotal.toString());
                
                ordenDeProduccionPalletsDtos.add(ordenDeProduccionPalletDto);
                
               
                this.cantidadPallet = ordenDeProduccionPalletsDtos.size();
               
                
                System.out.println("*** ordenDeProduccionPalletsDtos size:"+ordenDeProduccionPalletsDtos.size());
            }
        }

        //SCRAP
        OrdenDeProduccionScrapService ordenDeProduccionScrapService = new OrdenDeProduccionScrapServiceImpl();
        List<OrdenDeProduccionScrapModel> ordenDeProduccionScrapsModel = ordenDeProduccionScrapService.getAllByOrdenDeProduccion(ordenDeProduccion.getId());               
        List<OrdenDeProduccionScrapDto> ordenDeProduccionScrapsDtos = new ArrayList<OrdenDeProduccionScrapDto>();
        if(ordenDeProduccionScrapsModel != null && !ordenDeProduccionScrapsModel.isEmpty()) {
            for(OrdenDeProduccionScrapModel ordenDeProduccionScrapModel: ordenDeProduccionScrapsModel) {
                OrdenDeProduccionScrapDto ordenDeProduccionScrapDto = new OrdenDeProduccionScrapDto();
                ordenDeProduccionScrapDto.setPk(ordenDeProduccionScrapModel.getId().toString());
                ordenDeProduccionScrapDto.setFechaAlta(ordenDeProduccionScrapModel.getFechaAlta().toString().replace(".0", ""));
                ordenDeProduccionScrapDto.setCodigo(ordenDeProduccionScrapModel.getCodigo());
                String origen = "";
                if(ordenDeProduccionScrapModel.getIdOrigen() != null) {
                    TipoModel origenModel = tipoService.getByPk(ordenDeProduccionScrapModel.getIdOrigen());
                    if(origenModel != null) {
                        origen = origenModel.getValor();
                    }
                }                        
                ordenDeProduccionScrapDto.setOrigen(origen);
                String tipoMaterial = "";
                if(ordenDeProduccionScrapModel.getIdTipoMaterial() != null) {
                    TipoModel tipoMaterialModel = tipoService.getByPk(ordenDeProduccionScrapModel.getIdTipoMaterial());
                    if(tipoMaterialModel != null) {
                        tipoMaterial = tipoMaterialModel.getValor();
                    }
                }                                        
                ordenDeProduccionScrapDto.setTipoMaterial(tipoMaterial);
                String motivo = "";
                if(ordenDeProduccionScrapModel.getIdMotivo() != null) {
                    TipoModel motivoModel = tipoService.getByPk(ordenDeProduccionScrapModel.getIdMotivo());
                    if(motivoModel != null) {
                        motivo = motivoModel.getValor();
                    }
                }                                                        
                ordenDeProduccionScrapDto.setMotivo(motivo);
                String formato = "";
                if(ordenDeProduccionScrapModel.getIdFormato() != null) {
                    TipoModel formatoModel = tipoService.getByPk(ordenDeProduccionScrapModel.getIdFormato());
                    if(formatoModel != null) {
                        formato = formatoModel.getValor();
                    }
                }                                                                        
                ordenDeProduccionScrapDto.setFormato(formato);
                if(ordenDeProduccionScrapModel.getEsRecuperable()) {
                    ordenDeProduccionScrapDto.setEsRecuperable("Si");
                } else {
                    ordenDeProduccionScrapDto.setEsRecuperable("No");
                }
                if(ordenDeProduccionScrapModel.getMaterialImpreso()) {
                    ordenDeProduccionScrapDto.setMaterialImpreso("Si");
                } else {
                    ordenDeProduccionScrapDto.setMaterialImpreso("No");
                }                
                ordenDeProduccionScrapDto.setPesoTotal(ordenDeProduccionScrapModel.getPesoTotal().toString());
                
                RemitoDetalleScrapService remitoDetalleScrapService = new RemitoDetalleScrapServiceImpl();
                List<RemitoDetalleScrapModel> remitosScrapDetalles = remitoDetalleScrapService.getAllByIdOrdenDeProduccionScrap(ordenDeProduccionScrapModel.getId());
                
                boolean puedoEliminarScrap = (remitosScrapDetalles.isEmpty() && ordenDeProduccionScrapModel.getIdBulto() == null && ordenDeProduccionScrapModel.getIdBobina() == null);
                
                ordenDeProduccionScrapDto.setPuedoBorrarlo(puedoEliminarScrap);
                
                
                ordenDeProduccionScrapsDtos.add(ordenDeProduccionScrapDto);
                
               
                this.cantidadScrap = ordenDeProduccionScrapsDtos.size();
               
                
                System.out.println("*** ordenDeProduccionScrapsDtos size:"+ordenDeProduccionScrapsDtos.size());
            }
        }

        
        String displayButtonCambiarEstadoFabricacion = "none";
        if(rol.equalsIgnoreCase("planta") && ordenDeProduccion.getEstado().equalsIgnoreCase("Abierto")) {
            displayButtonCambiarEstadoFabricacion = "block";
        }
        String displayButtonCambiarEstadoEmpaque = "none";
        if(rol.equalsIgnoreCase("planta") && ordenDeProduccion.getEstado().equalsIgnoreCase("Fabricacion")) {
            displayButtonCambiarEstadoEmpaque = "block";
        }
        String displayButtonCambiarEstadoPallet = "none";
        if(rol.equalsIgnoreCase("planta") && ordenDeProduccion.getEstado().equalsIgnoreCase("Empaque")) {
            displayButtonCambiarEstadoPallet = "block";
        }
        String displayButtonCambiarEstadoCompletado = "none";
        if(rol.equalsIgnoreCase("oficina") && ordenDeProduccion.getEstado().equalsIgnoreCase("Pallet")) {
            displayButtonCambiarEstadoCompletado = "block";
        }
        
        if(ordenDeProduccion.getEstado().equalsIgnoreCase("Completado")) {
            displayActionButton = "none";
            displaySearchBobinaButton = "none";
            displaySearchBultoButton = "none";
        }
        
        ActivacionManualService activacionManualService = new ActivacionManualServiceImpl();
        ActivacionManualModel activacionManual = activacionManualService.getByPk(1);
        
        model.addAttribute("plegadoraList", plegadoraList);                
        model.addAttribute("origenScrapList", origenScrapList);
        model.addAttribute("tipoMaterialScrapList", tipoMaterialScrapList);
        model.addAttribute("motivoScrapList", motivoScrapList);
        model.addAttribute("formatoScrapList", formatoScrapList);
        model.addAttribute("bobinaDisponibleList", bobinaDisponibleList);                
        model.addAttribute("bultoDisponibleList", bultoDisponibleList);          
        model.addAttribute("displayButtonCambiarEstadoFabricacion", displayButtonCambiarEstadoFabricacion);        
        model.addAttribute("displayButtonCambiarEstadoEmpaque", displayButtonCambiarEstadoEmpaque);    
        model.addAttribute("displayButtonCambiarEstadoPallet", displayButtonCambiarEstadoPallet);    
        model.addAttribute("displayButtonCambiarEstadoCompletado", displayButtonCambiarEstadoCompletado);    
        model.addAttribute("idOrdenDeProduccion", ordenDeProduccion.getId().toString());
        if(cliente == null){
            model.addAttribute("Cliente eliinado");
        }else{
            model.addAttribute("clienteLabel", cliente.getRazonSocial());
        }
        model.addAttribute("idArticulo", articulo.getId());
        model.addAttribute("articuloLabel", articulo.getDenominacion());
        model.addAttribute("fichaTecnicaVersionLabel", articuloFichaTecnica.getVersion());
        
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "new");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("displayActionButtonScrap", displayActionButtonScrap);
        model.addAttribute("displaySearchBobinaButton", displaySearchBobinaButton);        
        model.addAttribute("displaySearchBultoButton", displaySearchBultoButton);                
        model.addAttribute("operacion", operacion);        
        model.addAttribute("ordenDeProduccionBobinas", ordenDeProduccionBobinasDtos);        
        model.addAttribute("ordenDeProduccionBultos", ordenDeProduccionBultosDtos);        
        model.addAttribute("ordenDeProduccionPallets", ordenDeProduccionPalletsDtos);
        model.addAttribute("ordenDeProduccionScraps", ordenDeProduccionScrapsDtos);
        model.addAttribute("estadoOrderProduccion", ordenDeProduccion.getEstado());
        
        model.addAttribute("cantidadDeBobinasQueNoEstanEnBulto", this.cantidadDeBobinasQueNoEstanEnBulto);
        model.addAttribute("cantidadDeBultosQueNoEstanEnPallet", this.cantidadDeBultosQueNoEstanEnPallet);
        model.addAttribute("cantidadPallet", this.cantidadPallet);
        model.addAttribute("cantidadBobinasEnProduccion", this.cantidadBobinasEnProduccion);
        model.addAttribute("cantidadScrap", this.cantidadScrap);
        
        model.addAttribute("statusAct", activacionManual.getActivacionManual() || rol.equalsIgnoreCase("oficina"));
        
        
        model.addAttribute("h", true);
        
        
        return "/ordendeproduccion/ordendeproducciondetalle";
    }
    
    @RequestMapping(value = "/ordenDeProduccionDetalle/{ordenDeProduccionPk}/tipo/{tipo}/pk/{pk}", method = RequestMethod.GET)
    public String getHomeOrdenDeProduccionDetalleAndPrint(@PathVariable String ordenDeProduccionPk, @PathVariable String tipo, @PathVariable String pk, HttpServletRequest req, ModelMap model) {

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeProduccionPk == null) {
            model.addAttribute("errorMessage", "Error: orden de producción inválida");         
            return "/error";                            
        }
        
        if(user.getRol() != Utils.ROL_OFICINA && user.getRol() != Utils.ROL_PLANTA && user.getRol() != Utils.ROL_DEPOSITO) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
        
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();   
        OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(Integer.valueOf(ordenDeProduccionPk));

        if(ordenDeProduccion == null) {
            model.addAttribute("errorMessage", "Error: orden de produccion no encontrada");         
            return "/error";                
        }
        
        ClienteService clienteService = new ClienteServiceImpl();
        ClienteModel cliente = clienteService.getByPk(ordenDeProduccion.getIdCliente());
        if(cliente == null) {
            model.addAttribute("errorMessage", "Error: cliente no encontrado");         
            return "/error";                            
        }

        ArticuloService articuloService = new ArticuloServiceImpl();
        ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
        if(articulo == null) {
            model.addAttribute("errorMessage", "Error: articulo no encontrado");         
            return "/error";                            
        }
        
        ArticuloFichaTecnicaService articuloFichaTecnicaService = new ArticuloFichaTecnicaServiceImpl();
        ArticuloFichaTecnicaModel articuloFichaTecnica = articuloFichaTecnicaService.getByPk(ordenDeProduccion.getIdFichaTecnica());
        if(articuloFichaTecnica == null) {
            model.addAttribute("errorMessage", "Error: ficha tecnica no encontrada");         
            return "/error";                            
        }
        
        String rol = "";    
        String operacion = "";        
        String displayActionButton = "block";
        String displayActionButtonScrap = "block";
        String displaySearchBobinaButton = "block";        
        String displaySearchBultoButton = "block";
                
        OrdenDeProduccionDetalleForm ordenDeProduccionDetalleForm = new OrdenDeProduccionDetalleForm();
        ordenDeProduccionDetalleForm.setPk("-1");
        ordenDeProduccionDetalleForm.setAction("add");        
        ordenDeProduccionDetalleForm.setTipoDetalle("bobina");
        ordenDeProduccionDetalleForm.setImprimir("true");
        ordenDeProduccionDetalleForm.setImprimirTipo(tipo);
        ordenDeProduccionDetalleForm.setImprimirPk(pk);
        
        ordenDeProduccionDetalleForm.setBobinaSelectedLabel("No existe bobina seleccionada.");
        ordenDeProduccionDetalleForm.setEstadoBobinaSelectedLabel("");
        ordenDeProduccionDetalleForm.setIdBobinaSelected("-1");
        
        ordenDeProduccionDetalleForm.setFechaAltaOrdenDeProduccion(ordenDeProduccion.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        ordenDeProduccionDetalleForm.setEstadoOrdenDeProduccion(ordenDeProduccion.getEstado());
        ordenDeProduccionDetalleForm.setIdOrdenProduccion(ordenDeProduccion.getId().toString());
        ordenDeProduccionDetalleForm.setIdCliente(ordenDeProduccion.getIdCliente().toString());
        ordenDeProduccionDetalleForm.setIdArticulo(ordenDeProduccion.getIdArticulo().toString());
        ordenDeProduccionDetalleForm.setIdFichaTecnica(ordenDeProduccion.getIdFichaTecnica().toString());
        if(articuloFichaTecnica.getPeso() != null) {
            ordenDeProduccionDetalleForm.setPesoTecnicoArticulo(articuloFichaTecnica.getPeso().toString());
        } else {
            ordenDeProduccionDetalleForm.setPesoTecnicoArticulo("No disponible");
        }
        if(articuloFichaTecnica.getMetros() != null) {
            ordenDeProduccionDetalleForm.setMetrosArticulo(articuloFichaTecnica.getMetros().toString());
        } else {
            ordenDeProduccionDetalleForm.setMetrosArticulo("No disponible");
        }
        if(articulo.getAlto() != null) {
            ordenDeProduccionDetalleForm.setAltoArticulo(articulo.getAlto().toString());
        } else {
            ordenDeProduccionDetalleForm.setAltoArticulo("No disponible");
        }
        if(articulo.getEspesor() != null) {
            ordenDeProduccionDetalleForm.setEspesorArticulo(articulo.getEspesor().toString());
        } else {
            ordenDeProduccionDetalleForm.setEspesorArticulo("No disponible");
        }
        if(articulo.getAncho() != null) {
            ordenDeProduccionDetalleForm.setAnchoArticulo(articulo.getAncho().toString());
        } else {
            ordenDeProduccionDetalleForm.setAnchoArticulo("No disponible");
        }
        if(articulo.getEspesor() != null) {
            ordenDeProduccionDetalleForm.setEspesorBobina(articulo.getEspesor().toString());
        } else {
            ordenDeProduccionDetalleForm.setEspesorBobina("No disponible");
        }
                
        if(user.getRol() == Utils.ROL_OFICINA) {
            operacion = "alta";
            displayActionButton = "none";
            displayActionButtonScrap = "block";
            rol = "oficina";
        }
        if(user.getRol() == Utils.ROL_PLANTA) {
            operacion = "alta";
            displayActionButton = "block";            
            displayActionButtonScrap = "block";
            rol = "planta";
        }
        if(user.getRol() == Utils.ROL_DEPOSITO) {
            operacion = "alta";
            displayActionButton = "block";     
            displayActionButtonScrap = "none";
            rol = "deposito";
        }
                
        ordenDeProduccionDetalleForm.setOperacion(operacion);
        
        ActivacionManualService activacionManualService = new ActivacionManualServiceImpl();
        ActivacionManualModel activacionManual = activacionManualService.getByPk(1);
        
        model.addAttribute("ordenDeProduccionDetalleForm", ordenDeProduccionDetalleForm);  
        model.addAttribute("titleOrdenDeProduccion", "Order De Producción Detalle");  
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("buttonSearchBobinaLabel", "Buscar");
        model.addAttribute("buttonSearchBultoLabel", "Agregar");
        
        OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();
                
        List<OrdenDeProduccionBobinaModel> ordenDeProduccionBobinasModel = ordenDeProduccionBobinaService.getAllByOrdenDeProduccion(ordenDeProduccion.getId());       
        List<OrdenDeProduccionBobinaDto> ordenDeProduccionBobinasDtos = new ArrayList<OrdenDeProduccionBobinaDto>();
        
        if(ordenDeProduccionBobinasModel != null && !ordenDeProduccionBobinasModel.isEmpty()) {
            for(OrdenDeProduccionBobinaModel ordenDeProduccionBobinaModel: ordenDeProduccionBobinasModel) {
                OrdenDeProduccionBobinaDto ordenDeProduccionBobinaDto = new OrdenDeProduccionBobinaDto();
                ordenDeProduccionBobinaDto.setPk(ordenDeProduccionBobinaModel.getId().toString());
                ordenDeProduccionBobinaDto.setFechaAlta(ordenDeProduccionBobinaModel.getFechaAlta().toString().replace(".0", ""));
                if(ordenDeProduccionBobinaModel.getEstado() != null) {
                    ordenDeProduccionBobinaDto.setEstado(ordenDeProduccionBobinaModel.getEstado());
                    if(ordenDeProduccionBobinaModel.getEstado().equals("ok")) {
                        ordenDeProduccionBobinaDto.setEstadoLabel("OK");
                    }
                    if(ordenDeProduccionBobinaModel.getEstado().equals("observado")) {
                        ordenDeProduccionBobinaDto.setEstadoLabel("Observado");
                    }
                    if(ordenDeProduccionBobinaModel.getEstado().equals("rechazado")) {
                        ordenDeProduccionBobinaDto.setEstadoLabel("Rechazado");
                    }
                    if(ordenDeProduccionBobinaModel.getEstado().equals("sinmesurar")) {
                        ordenDeProduccionBobinaDto.setEstadoLabel("Sin Mesurar");
                    }                
                }
                ordenDeProduccionBobinaDto.setCodigo(ordenDeProduccionBobinaModel.getCodigo());
                ordenDeProduccionBobinaDto.setPesoTotal(ordenDeProduccionBobinaModel.getPesoTotal().toString());
                ordenDeProduccionBobinaDto.setPesoNeto(ordenDeProduccionBobinaModel.getPesoNeto().toString());
                ordenDeProduccionBobinaDto.setEspesor(articulo.getEspesor().toString());
                ordenDeProduccionBobinaDto.setEstaEnBulto(ordenDeProduccionBobinaModel.getEstaEnBulto().toString());
                if(ordenDeProduccionBobinaModel.getEstaEnBulto()) {
                    ordenDeProduccionBobinaDto.setEstaEnBultoLabel("Si");
                } else {
                    ordenDeProduccionBobinaDto.setEstaEnBultoLabel("No");
                }
                
                if(ordenDeProduccionBobinaModel.getIdDeposito() != null) {
                    ordenDeProduccionBobinaDto.setEstaEnDeposito(true);
                } else {
                    ordenDeProduccionBobinaDto.setEstaEnDeposito(false);
                }

                if(!ordenDeProduccionBobinaModel.getEstaEnBulto()) {
                    ordenDeProduccionBobinasDtos.add(ordenDeProduccionBobinaDto);
                }
            }
        }
                
        Map<String,String> bobinaDisponibleList = new LinkedHashMap<String,String>();
        List<OrdenDeProduccionBobinaModel> ordenDeProduccionBobinasAvailablesModel = ordenDeProduccionBobinaService.getAllAvailableByOrdenDeProduccion(ordenDeProduccion.getId());       
        if(ordenDeProduccionBobinasAvailablesModel != null && !ordenDeProduccionBobinasAvailablesModel.isEmpty()){
            for(OrdenDeProduccionBobinaModel bobinaModel :ordenDeProduccionBobinasAvailablesModel) {
                if(bobinaModel.getEstado() != null) {
                    bobinaDisponibleList.put(bobinaModel.getId().toString(), bobinaModel.getCodigo() + "(" + bobinaModel.getEstado() + ")");
                } else {
                    bobinaDisponibleList.put(bobinaModel.getId().toString(), bobinaModel.getCodigo());
                }
            }
        }
        
        TipoService tipoService = new TipoServiceImpl();
        Map<String,String> plegadoraList = new LinkedHashMap<String,String>();
        List<TipoModel> plegadorasModel = tipoService.getByType("plegadoraBobina");       
        if(plegadorasModel != null && !plegadorasModel.isEmpty()){
            for(TipoModel tipoModel :plegadorasModel) {
                plegadoraList.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        
        Map<String,String> origenScrapList = new LinkedHashMap<String,String>();
        List<TipoModel> origenScrapModel = tipoService.getByType("origenScrap");       
        if(origenScrapModel != null && !origenScrapModel.isEmpty()){
            for(TipoModel tipoModel :origenScrapModel) {
                origenScrapList.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        Map<String,String> tipoMaterialScrapList = new LinkedHashMap<String,String>();
        List<TipoModel> tipoMaterialScrapModel = tipoService.getByType("tipoMaterialScrap");       
        if(tipoMaterialScrapModel != null && !tipoMaterialScrapModel.isEmpty()){
            for(TipoModel tipoModel :tipoMaterialScrapModel) {
                tipoMaterialScrapList.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        Map<String,String> motivoScrapList = new LinkedHashMap<String,String>();
        List<TipoModel> motivoScrapModel = tipoService.getByType("motivoScrap");       
        if(motivoScrapModel != null && !motivoScrapModel.isEmpty()){
            for(TipoModel tipoModel :motivoScrapModel) {
                motivoScrapList.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        Map<String,String> formatoScrapList = new LinkedHashMap<String,String>();
        List<TipoModel> formatoScrapModel = tipoService.getByType("formatoScrap");       
        if(formatoScrapModel != null && !formatoScrapModel.isEmpty()){
            for(TipoModel tipoModel :formatoScrapModel) {
                formatoScrapList.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        
        OrdenDeProduccionBultoService ordenDeProduccionBultoService = new OrdenDeProduccionBultoServiceImpl();
        Map<String,String> bultoDisponibleList = new LinkedHashMap<String,String>();
        List<OrdenDeProduccionBultoModel> ordenDeProduccionBultoAvailablesModel = ordenDeProduccionBultoService.getAllAvailableByOrdenDeProduccion(ordenDeProduccion.getId());       
        if(ordenDeProduccionBultoAvailablesModel != null && !ordenDeProduccionBultoAvailablesModel.isEmpty()){
            for(OrdenDeProduccionBultoModel bultoModel :ordenDeProduccionBultoAvailablesModel) {
                if(bultoModel.getEstado() != null) {
                    bultoDisponibleList.put(bultoModel.getId().toString(), bultoModel.getCodigo() + " (" + bultoModel.getEstado() + ")");
                } else {
                    bultoDisponibleList.put(bultoModel.getId().toString(), bultoModel.getCodigo());
                }
            }
        }
        
        List<OrdenDeProduccionBultoModel> ordenDeProduccionBultosModel = ordenDeProduccionBultoService.getAllByOrdenDeProduccion(ordenDeProduccion.getId());       
        List<OrdenDeProduccionBultoDto> ordenDeProduccionBultosDtos = new ArrayList<OrdenDeProduccionBultoDto>();
        
        if(ordenDeProduccionBultosModel != null && !ordenDeProduccionBultosModel.isEmpty()) {
            for(OrdenDeProduccionBultoModel ordenDeProduccionBultoModel: ordenDeProduccionBultosModel) {
                OrdenDeProduccionBultoDto ordenDeProduccionBultoDto = new OrdenDeProduccionBultoDto();
                ordenDeProduccionBultoDto.setPk(ordenDeProduccionBultoModel.getId().toString());
                ordenDeProduccionBultoDto.setFechaAlta(ordenDeProduccionBultoModel.getFechaAlta().toString().replace(".0", ""));
                if(ordenDeProduccionBultoModel.getEstado() != null && !ordenDeProduccionBultoModel.getEstado().equalsIgnoreCase("-1")) {
                    ordenDeProduccionBultoDto.setEstado(ordenDeProduccionBultoModel.getEstado());
                    if(ordenDeProduccionBultoModel.getEstado().equals("ok")) {
                        ordenDeProduccionBultoDto.setEstadoLabel("OK");
                    }
                    if(ordenDeProduccionBultoModel.getEstado().equals("observado")) {
                        ordenDeProduccionBultoDto.setEstadoLabel("Observado");
                    }
                    if(ordenDeProduccionBultoModel.getEstado().equals("rechazado")) {
                        ordenDeProduccionBultoDto.setEstadoLabel("Rechazado");
                    }
                    if(ordenDeProduccionBultoModel.getEstado().equals("sinmesurar")) {
                        ordenDeProduccionBultoDto.setEstadoLabel("Sin Mesurar");
                    }                
                }
                ordenDeProduccionBultoDto.setCodigo(ordenDeProduccionBultoModel.getCodigo());
                ordenDeProduccionBultoDto.setIdBobina(ordenDeProduccionBultoModel.getIdOrdenDeProduccionBobina().toString());
                ordenDeProduccionBultoDto.setEstaEnPallet(ordenDeProduccionBultoModel.getEstaEnPallet().toString());                
                if(ordenDeProduccionBultoModel.getEstaEnPallet()) {
                    ordenDeProduccionBultoDto.setEstaEnPalletLabel("Si");                
                } else {
                    ordenDeProduccionBultoDto.setEstaEnPalletLabel("No");                
                }
                
                String plegadora = "";
                if(ordenDeProduccionBultoModel.getIdPlegadora() != null) {
                    TipoModel plegadoraModel = tipoService.getByPk(ordenDeProduccionBultoModel.getIdPlegadora());
                    if(plegadora != null) {
                        plegadora = plegadoraModel.getValor();
                    }
                }        
                ordenDeProduccionBultoDto.setPlegadora(plegadora);
                        
                OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(ordenDeProduccionBultoModel.getIdOrdenDeProduccionBobina());
                if(bobina != null) {
                    ordenDeProduccionBultoDto.setPesoTotal(bobina.getPesoTotal().toString());
                    ordenDeProduccionBultoDto.setPesoNeto(bobina.getPesoNeto().toString());
                }
                
                if(ordenDeProduccionBultoModel.getIdDeposito() != null) {
                    ordenDeProduccionBultoDto.setEstaEnDeposito(true);
                } else {
                    ordenDeProduccionBultoDto.setEstaEnDeposito(false);
                }   

                if(!ordenDeProduccionBultoModel.getEstaEnPallet()) {
                    ordenDeProduccionBultosDtos.add(ordenDeProduccionBultoDto);
                }
                System.out.println("*** ordenDeProduccionBultosDtos size:"+ordenDeProduccionBultosDtos.size());
            }
        }

        OrdenDeProduccionPalletBultoService ordenDeProduccionPalletBultoService = new OrdenDeProduccionPalletBultoServiceImpl();
        OrdenDeProduccionPalletService ordenDeProduccionPalletService = new OrdenDeProduccionPalletServiceImpl();
        List<OrdenDeProduccionPalletModel> ordenDeProduccionPalletsModel = ordenDeProduccionPalletService.getAllByOrdenDeProduccion(ordenDeProduccion.getId());       
        List<OrdenDeProduccionPalletDto> ordenDeProduccionPalletsDtos = new ArrayList<OrdenDeProduccionPalletDto>();
        
        if(ordenDeProduccionPalletsModel != null && !ordenDeProduccionPalletsModel.isEmpty()) {
            for(OrdenDeProduccionPalletModel ordenDeProduccionPalletModel: ordenDeProduccionPalletsModel) {
                OrdenDeProduccionPalletDto ordenDeProduccionPalletDto = new OrdenDeProduccionPalletDto();
                ordenDeProduccionPalletDto.setPk(ordenDeProduccionPalletModel.getId().toString());
                ordenDeProduccionPalletDto.setFechaAlta(ordenDeProduccionPalletModel.getFechaAlta().toString().replace(".0", ""));
                if(ordenDeProduccionPalletModel.getEstado() != null && !ordenDeProduccionPalletModel.getEstado().equalsIgnoreCase("-1")) {
                    ordenDeProduccionPalletDto.setEstado(ordenDeProduccionPalletModel.getEstado());
                    if(ordenDeProduccionPalletModel.getEstado().equals("ok")) {
                        ordenDeProduccionPalletDto.setEstadoLabel("OK");
                    }
                    if(ordenDeProduccionPalletModel.getEstado().equals("observado")) {
                        ordenDeProduccionPalletDto.setEstadoLabel("Observado");
                    }
                    if(ordenDeProduccionPalletModel.getEstado().equals("rechazado")) {
                        ordenDeProduccionPalletDto.setEstadoLabel("Rechazado");
                    }
                    if(ordenDeProduccionPalletModel.getEstado().equals("sinmesurar")) {
                        ordenDeProduccionPalletDto.setEstadoLabel("Sin Mesurar");
                    }                
                }
                ordenDeProduccionPalletDto.setCodigo(ordenDeProduccionPalletModel.getCodigo());
                List<OrdenDeProduccionPalletBultoModel> palletbultoList = ordenDeProduccionPalletBultoService.getAllByOrdenDeProduccionPallet(ordenDeProduccionPalletModel.getId());
               
                ordenDeProduccionPalletDto.setCantidadBultos(String.valueOf(palletbultoList.size()));
                
                String listaCodigos = "";
               
               
               Map<String,String> mapaBultos = new LinkedHashMap<String,String>();
                for(OrdenDeProduccionPalletBultoModel item :palletbultoList) {
                OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(item.getIdOrdenDeProduccionBulto());
                if(bulto != null) {
                 
                 mapaBultos.put(bulto.getIdOrdenDeProduccionBobina().toString(), bulto.getCodigo());
                 listaCodigos += bulto.getCodigo() + " ";
                }
               }
               ordenDeProduccionPalletDto.setMapaBultos(mapaBultos);
               ordenDeProduccionPalletDto.setListaCodigoBultos(listaCodigos);
               
               if(ordenDeProduccionPalletModel.getIdDeposito() != null) {
                    ordenDeProduccionPalletDto.setEstaEnDeposito(true);
                } else {
                    ordenDeProduccionPalletDto.setEstaEnDeposito(false);
                } 
      

                Double pesoTotal = 0.0;
                if(!palletbultoList.isEmpty()) {
                    for(OrdenDeProduccionPalletBultoModel palletbulto: palletbultoList) {
                        OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(palletbulto.getIdOrdenDeProduccionBulto());
                        if(bulto != null) {
                            OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(bulto.getIdOrdenDeProduccionBobina());
                            if(bobina != null) {
                                pesoTotal += bobina.getPesoTotal();
                            }
                        }
                    }
                }
                ordenDeProduccionPalletDto.setPesoTotal(pesoTotal.toString());
                  
                ordenDeProduccionPalletsDtos.add(ordenDeProduccionPalletDto);
                System.out.println("*** ordenDeProduccionPalletsDtos sizehh:"+ordenDeProduccionPalletsDtos.size());
                System.out.println("*** ordenDeProduccionPalletsDtos sizekkk:"+listaCodigos);
            }
        }
        
        //SCRAP
        OrdenDeProduccionScrapService ordenDeProduccionScrapService = new OrdenDeProduccionScrapServiceImpl();
        List<OrdenDeProduccionScrapModel> ordenDeProduccionScrapsModel = ordenDeProduccionScrapService.getAllByOrdenDeProduccion(ordenDeProduccion.getId());               
        List<OrdenDeProduccionScrapDto> ordenDeProduccionScrapsDtos = new ArrayList<OrdenDeProduccionScrapDto>();
        if(ordenDeProduccionScrapsModel != null && !ordenDeProduccionScrapsModel.isEmpty()) {
            for(OrdenDeProduccionScrapModel ordenDeProduccionScrapModel: ordenDeProduccionScrapsModel) {
                OrdenDeProduccionScrapDto ordenDeProduccionScrapDto = new OrdenDeProduccionScrapDto();
                ordenDeProduccionScrapDto.setPk(ordenDeProduccionScrapModel.getId().toString());
                ordenDeProduccionScrapDto.setFechaAlta(ordenDeProduccionScrapModel.getFechaAlta().toString().replace(".0", ""));
                ordenDeProduccionScrapDto.setCodigo(ordenDeProduccionScrapModel.getCodigo());
                String origen = "";
                if(ordenDeProduccionScrapModel.getIdOrigen() != null) {
                    TipoModel origenModel = tipoService.getByPk(ordenDeProduccionScrapModel.getIdOrigen());
                    if(origenModel != null) {
                        origen = origenModel.getValor();
                    }
                }                        
                ordenDeProduccionScrapDto.setOrigen(origen);
                String tipoMaterial = "";
                if(ordenDeProduccionScrapModel.getIdTipoMaterial() != null) {
                    TipoModel tipoMaterialModel = tipoService.getByPk(ordenDeProduccionScrapModel.getIdTipoMaterial());
                    if(tipoMaterialModel != null) {
                        tipoMaterial = tipoMaterialModel.getValor();
                    }
                }                                        
                ordenDeProduccionScrapDto.setTipoMaterial(tipoMaterial);
                String motivo = "";
                if(ordenDeProduccionScrapModel.getIdMotivo() != null) {
                    TipoModel motivoModel = tipoService.getByPk(ordenDeProduccionScrapModel.getIdMotivo());
                    if(motivoModel != null) {
                        motivo = motivoModel.getValor();
                    }
                }                                                        
                ordenDeProduccionScrapDto.setMotivo(motivo);
                String formato = "";
                if(ordenDeProduccionScrapModel.getIdFormato() != null) {
                    TipoModel formatoModel = tipoService.getByPk(ordenDeProduccionScrapModel.getIdFormato());
                    if(formatoModel != null) {
                        formato = formatoModel.getValor();
                    }
                }                     
                ordenDeProduccionScrapDto.setFormato(formato);
                if(ordenDeProduccionScrapModel.getEsRecuperable()) {
                    ordenDeProduccionScrapDto.setEsRecuperable("Si");
                } else {
                    ordenDeProduccionScrapDto.setEsRecuperable("No");
                }
                if(ordenDeProduccionScrapModel.getMaterialImpreso()) {
                    ordenDeProduccionScrapDto.setMaterialImpreso("Si");
                } else {
                    ordenDeProduccionScrapDto.setMaterialImpreso("No");
                }                
                ordenDeProduccionScrapDto.setPesoTotal(ordenDeProduccionScrapModel.getPesoTotal().toString());
                
                RemitoDetalleScrapService remitoDetalleScrapService = new RemitoDetalleScrapServiceImpl();
                List<RemitoDetalleScrapModel> remitosScrapDetalles = remitoDetalleScrapService.getAllByIdOrdenDeProduccionScrap(ordenDeProduccionScrapModel.getId());
                
                boolean puedoEliminarScrap = (remitosScrapDetalles.isEmpty() && ordenDeProduccionScrapModel.getIdBulto() == null && ordenDeProduccionScrapModel.getIdBobina() == null);
                
                ordenDeProduccionScrapDto.setPuedoBorrarlo(puedoEliminarScrap);
                
                ordenDeProduccionScrapsDtos.add(ordenDeProduccionScrapDto);
                
               
                this.cantidadScrap = ordenDeProduccionScrapsDtos.size();
               
                
                System.out.println("*** ordenDeProduccionScrapsDtos size:"+ordenDeProduccionScrapsDtos.size());
            }
        }
        
        String displayButtonCambiarEstadoFabricacion = "none";
        if(rol.equalsIgnoreCase("planta") && ordenDeProduccion.getEstado().equalsIgnoreCase("Abierto")) {
            displayButtonCambiarEstadoFabricacion = "block";
        }
        String displayButtonCambiarEstadoEmpaque = "none";
        if(rol.equalsIgnoreCase("planta") && ordenDeProduccion.getEstado().equalsIgnoreCase("Fabricacion")) {
            displayButtonCambiarEstadoEmpaque = "block";
        }
        String displayButtonCambiarEstadoPallet = "none";
        if(rol.equalsIgnoreCase("planta") && ordenDeProduccion.getEstado().equalsIgnoreCase("Empaque")) {
            displayButtonCambiarEstadoPallet = "block";
        }
        String displayButtonCambiarEstadoCompletado = "none";
        if(rol.equalsIgnoreCase("oficina") && ordenDeProduccion.getEstado().equalsIgnoreCase("Pallet")) {
            displayButtonCambiarEstadoCompletado = "block";
        }
        
        if(ordenDeProduccion.getEstado().equalsIgnoreCase("Completado")) {
            displayActionButton = "none";
            displayActionButtonScrap = "none";
            displaySearchBobinaButton = "none";
            displaySearchBultoButton = "none";
        }
        
        model.addAttribute("plegadoraList", plegadoraList);   
        model.addAttribute("origenScrapList", origenScrapList);
        model.addAttribute("tipoMaterialScrapList", tipoMaterialScrapList);
        model.addAttribute("motivoScrapList", motivoScrapList);
        model.addAttribute("formatoScrapList", formatoScrapList);        
        model.addAttribute("bobinaDisponibleList", bobinaDisponibleList);                
        model.addAttribute("bultoDisponibleList", bultoDisponibleList);  
        model.addAttribute("displayButtonCambiarEstadoFabricacion", displayButtonCambiarEstadoFabricacion);        
        model.addAttribute("displayButtonCambiarEstadoEmpaque", displayButtonCambiarEstadoEmpaque);    
        model.addAttribute("displayButtonCambiarEstadoPallet", displayButtonCambiarEstadoPallet);    
        model.addAttribute("displayButtonCambiarEstadoCompletado", displayButtonCambiarEstadoCompletado);    
        model.addAttribute("idOrdenDeProduccion", ordenDeProduccion.getId().toString());                
        model.addAttribute("clienteLabel", cliente.getRazonSocial());
        model.addAttribute("idArticulo", articulo.getId());
        model.addAttribute("articuloLabel", articulo.getDenominacion());
        model.addAttribute("fichaTecnicaVersionLabel", articuloFichaTecnica.getVersion());
        
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "new");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("displayActionButtonScrap", displayActionButtonScrap);
        model.addAttribute("displaySearchBobinaButton", displaySearchBobinaButton);        
        model.addAttribute("displaySearchBultoButton", displaySearchBultoButton);                
        model.addAttribute("operacion", operacion);        
        model.addAttribute("ordenDeProduccionBobinas", ordenDeProduccionBobinasDtos);        
        model.addAttribute("ordenDeProduccionBultos", ordenDeProduccionBultosDtos);        
        model.addAttribute("ordenDeProduccionPallets", ordenDeProduccionPalletsDtos);
        model.addAttribute("ordenDeProduccionScraps", ordenDeProduccionScrapsDtos);
        model.addAttribute("estadoOrderProduccion", ordenDeProduccion.getEstado());
        
        model.addAttribute("cantidadDeBobinasQueNoEstanEnBulto", this.cantidadDeBobinasQueNoEstanEnBulto);
        model.addAttribute("cantidadDeBultosQueNoEstanEnPallet", this.cantidadDeBultosQueNoEstanEnPallet);
        model.addAttribute("cantidadPallet", this.cantidadPallet);
        model.addAttribute("cantidadBobinasEnProduccion", this.cantidadBobinasEnProduccion);
        
        model.addAttribute("statusAct", activacionManual.getActivacionManual() || rol.equalsIgnoreCase("oficina"));
                
        return "/ordendeproduccion/ordendeproducciondetalle";
    }
    
    @RequestMapping(value = "/ordenDeProduccionDetalle/addOrEditOrRemove", method = RequestMethod.POST)
    public ModelAndView addOrEditOrRemoveOrdenDeProduccion(@ModelAttribute("ordenDeProduccionDetalleForm")OrdenDeProduccionDetalleForm ordenDeProduccionDetalleForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

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
        
        if(ordenDeProduccionDetalleForm.getAction() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;            
        }
        
        if(user.getRol() != Utils.ROL_OFICINA && user.getRol() != Utils.ROL_PLANTA) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: usuario no tiene rol para este funcionalidad");
            return modelAndView;            
        }
        
        String operacion = ordenDeProduccionDetalleForm.getOperacion();
        
        if(operacion == null || (!operacion.equalsIgnoreCase("alta") && !operacion.equalsIgnoreCase("edit") && !operacion.equalsIgnoreCase("remove"))) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;                        
        }        
        
        if(operacion.equalsIgnoreCase("remove") && (user.getRol() != Utils.ROL_OFICINA || (!ordenDeProduccionDetalleForm.getTipoDetalle().equalsIgnoreCase("bulto") && !ordenDeProduccionDetalleForm.getTipoDetalle().equalsIgnoreCase("scrap")))) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;                                    
        }
        
        if(ordenDeProduccionDetalleForm.getPk().equalsIgnoreCase("-1") && !operacion.equalsIgnoreCase("alta")) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;                        
        }
        System.out.println("*** ordenDeProduccionForm.getAction():"+ordenDeProduccionDetalleForm.getAction());
                
        if(ordenDeProduccionDetalleForm.getAction().equalsIgnoreCase("add") || ordenDeProduccionDetalleForm.getAction().equalsIgnoreCase("edit")) {
            if(ordenDeProduccionDetalleForm.getIdCliente() == null || ordenDeProduccionDetalleForm.getIdCliente().equalsIgnoreCase("-1")) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: cliente incorrecto");
                return modelAndView;                                    
            }        
            if(ordenDeProduccionDetalleForm.getIdArticulo() == null || ordenDeProduccionDetalleForm.getIdArticulo().equalsIgnoreCase("-1")) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: articulo incorrecto");
                return modelAndView;                                    
            }
            if(ordenDeProduccionDetalleForm.getIdFichaTecnica() == null || ordenDeProduccionDetalleForm.getIdFichaTecnica().equalsIgnoreCase("-1")) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: ficha tecnica incorrecta");
                return modelAndView;                                    
            }
            if(ordenDeProduccionDetalleForm.getTipoDetalle().equalsIgnoreCase("bobina")) {
                if(ordenDeProduccionDetalleForm.getPesoConoBobina() == null || ordenDeProduccionDetalleForm.getPesoConoBobina().isEmpty()) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: peso cono bobina vacio");
                    return modelAndView;                                               
                }
                if(ordenDeProduccionDetalleForm.getPesoTotalBobina() == null || ordenDeProduccionDetalleForm.getPesoTotalBobina().isEmpty()) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: peso total bobina vacio");
                    return modelAndView;                                               
                }
                if(ordenDeProduccionDetalleForm.getPesoNetoBobina() == null || ordenDeProduccionDetalleForm.getPesoNetoBobina().isEmpty()) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: peso neto bobina vacio");
                    return modelAndView;                                               
                }
            }            
            if(ordenDeProduccionDetalleForm.getTipoDetalle().equalsIgnoreCase("bulto")) {
                if(ordenDeProduccionDetalleForm.getIdBobinaSelected() == null || ordenDeProduccionDetalleForm.getIdBobinaSelected().equalsIgnoreCase("-1")) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: Debe seleccionar una bobina");
                    return modelAndView;                                               
                }
            }                        
            if(ordenDeProduccionDetalleForm.getTipoDetalle().equalsIgnoreCase("scrap")) {
                if(ordenDeProduccionDetalleForm.getPesoTotalScrap() == null || ordenDeProduccionDetalleForm.getPesoTotalScrap().isEmpty()) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: peso total scrap vacio");
                    return modelAndView;                                               
                }
            }                        
        }
        
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();        
        OrdenDeProduccionModel ordenDeProduccionModel = ordenDeProduccionService.getByPk(Integer.valueOf(ordenDeProduccionDetalleForm.getIdOrdenProduccion()));
        
        if(ordenDeProduccionModel == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: orden de produccion no existe");
            return modelAndView;                                                
        }
        
        String sessionId = req.getSession().getId();
        String id = ordenDeProduccionDetalleForm.getPk();
            
        if(id.equalsIgnoreCase("-1")) {            
            if(user.getRol() != Utils.ROL_PLANTA && !ordenDeProduccionDetalleForm.getTipoDetalle().equalsIgnoreCase("scrap")) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: no es posible crear un detalle de orden de produccion con ese rol.");
                return modelAndView;                        
            }             
        }
        
        Integer pk = -1;
        if(ordenDeProduccionDetalleForm.getTipoDetalle().equalsIgnoreCase("bobina")) {
            System.out.println("*** Paso 1");
            OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();     
            
            OrdenDeProduccionBobinaModel ordenDeProduccionBobinaModel = null;
            if(id.equalsIgnoreCase("-1")) {            
                ordenDeProduccionBobinaModel = new OrdenDeProduccionBobinaModel();
                ordenDeProduccionBobinaModel.setIdOrdenDeProduccion(ordenDeProduccionModel.getId());                
                ordenDeProduccionBobinaModel.setFechaAlta(new Date());
                ordenDeProduccionBobinaModel.setEstaEnBulto(Boolean.FALSE);
                this.cantidadDeBobinasQueNoEstanEnBulto = this.cantidadDeBobinasQueNoEstanEnBulto + 1;
                this.cantidadBobinasEnProduccion = this.cantidadBobinasEnProduccion + 1;
                ordenDeProduccionBobinaModel.setIdUsuarioAlta(Integer.valueOf(Utils.getUserLoggedId(req)));
                ordenDeProduccionBobinaModel.setEstaDisponibleParaRemito(Boolean.TRUE);
            } else {
                ordenDeProduccionBobinaModel = ordenDeProduccionBobinaService.getByPk(Integer.valueOf(id));
                if(ordenDeProduccionBobinaModel == null) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: id de bobina de orden de produccion inválido.");
                    return modelAndView;                
                } 
                System.out.println("*** Paso 2");
            }
            
            if(ordenDeProduccionDetalleForm.getPesoConoBobina() != null && !ordenDeProduccionDetalleForm.getPesoConoBobina().trim().equals("")) {
                ordenDeProduccionBobinaModel.setPesoCono(Double.valueOf(ordenDeProduccionDetalleForm.getPesoConoBobina()));
            } else {
                ordenDeProduccionBobinaModel.setPesoCono(null);
            }                            
            if(ordenDeProduccionDetalleForm.getPesoTotalBobina() != null && !ordenDeProduccionDetalleForm.getPesoTotalBobina().trim().equals("")) {
                ordenDeProduccionBobinaModel.setPesoTotal(Double.valueOf(ordenDeProduccionDetalleForm.getPesoTotalBobina()));
            } else {
                ordenDeProduccionBobinaModel.setPesoTotal(null);
            }                            
            if(ordenDeProduccionDetalleForm.getPesoNetoBobina() != null && !ordenDeProduccionDetalleForm.getPesoNetoBobina().trim().equals("")) {
                ordenDeProduccionBobinaModel.setPesoNeto(Double.valueOf(ordenDeProduccionDetalleForm.getPesoNetoBobina()));
            } else {
                ordenDeProduccionBobinaModel.setPesoNeto(null);
            }                            
            if(ordenDeProduccionDetalleForm.getEstadoBobina() != null && !ordenDeProduccionDetalleForm.getEstadoBobina().trim().equals("-1")) {
                ordenDeProduccionBobinaModel.setEstado(ordenDeProduccionDetalleForm.getEstadoBobina());
            } else {
                ordenDeProduccionBobinaModel.setEstado(null);
            }                            
            if(ordenDeProduccionDetalleForm.getObservacionesBobina() != null && !ordenDeProduccionDetalleForm.getObservacionesBobina().trim().equals("")) {
                ordenDeProduccionBobinaModel.setObservaciones(ordenDeProduccionDetalleForm.getObservacionesBobina());
            } else {
                ordenDeProduccionBobinaModel.setObservaciones(null);
            }                            
            
            System.out.println("*** Paso 3");
            if(ordenDeProduccionDetalleForm.getAction().equalsIgnoreCase("add") || ordenDeProduccionDetalleForm.getAction().equalsIgnoreCase("edit")) {
                System.out.println("*** Paso 4");
                ordenDeProduccionBobinaService.save(ordenDeProduccionBobinaModel);
                if(id.equalsIgnoreCase("-1")) {                                
                    ordenDeProduccionBobinaModel.setCodigo("B"+ordenDeProduccionBobinaModel.getId());
                    ordenDeProduccionBobinaService.save(ordenDeProduccionBobinaModel);
                    pk = ordenDeProduccionBobinaModel.getId();
                }
            } else {
                if(ordenDeProduccionDetalleForm.getAction().equalsIgnoreCase("remove")) {
                    if(ordenDeProduccionBobinaModel.getId() == null) {
                        modelAndView.setViewName("error");
                        modelAndView.addObject("errorMessage", "Error: id de ordenDeCompraItem inválida.");
                        return modelAndView;                                    
                    }

                    ordenDeProduccionBobinaService.delete(ordenDeProduccionBobinaModel);
                } else {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: operación invlida.");
                    return modelAndView;                                
                }
            }
            if(ordenDeProduccionDetalleForm.getAction().equalsIgnoreCase("edit")){
                             pk = ordenDeProduccionBobinaModel.getId();
                            }            
        }
        
        if(ordenDeProduccionDetalleForm.getTipoDetalle().equalsIgnoreCase("bulto")) {
            
            System.out.println("*** Paso 1");
            OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();     
            OrdenDeProduccionBobinaModel bobinaSelected = ordenDeProduccionBobinaService.getByPk(Integer.valueOf(ordenDeProduccionDetalleForm.getIdBobinaSelected()));
            if(bobinaSelected == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: bobina seleccionada inválida.");
                return modelAndView;                                
            }
            
            OrdenDeProduccionBultoService ordenDeProduccionBultoService = new OrdenDeProduccionBultoServiceImpl();     
            
            OrdenDeProduccionBultoModel ordenDeProduccionBultoModel = null;
            if(id.equalsIgnoreCase("-1")) {            
                ordenDeProduccionBultoModel = new OrdenDeProduccionBultoModel();
                ordenDeProduccionBultoModel.setIdOrdenDeProduccion(ordenDeProduccionModel.getId());                
                ordenDeProduccionBultoModel.setIdOrdenDeProduccionBobina(bobinaSelected.getId());
                ordenDeProduccionBultoModel.setFechaAlta(new Date());
                ordenDeProduccionBultoModel.setIdUsuarioAlta(Integer.valueOf(Utils.getUserLoggedId(req)));
                ordenDeProduccionBultoModel.setEstaEnPallet(Boolean.FALSE);
                
                ordenDeProduccionBultoModel.setEstaDisponibleParaRemito(Boolean.TRUE);
            } else {
                ordenDeProduccionBultoModel = ordenDeProduccionBultoService.getByPk(Integer.valueOf(id));
                if(ordenDeProduccionBultoModel == null) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: id de bobina de orden de produccion inválido.");
                    return modelAndView;                
                } 
                System.out.println("*** Paso 2");
            }
            
            if(ordenDeProduccionDetalleForm.getIdPlegadora() != null && !ordenDeProduccionDetalleForm.getIdPlegadora().trim().equals("-1")) {
                ordenDeProduccionBultoModel.setIdPlegadora(Integer.valueOf(ordenDeProduccionDetalleForm.getIdPlegadora()));
            } else {
                ordenDeProduccionBultoModel.setIdPlegadora(null);
            }
            if(ordenDeProduccionDetalleForm.getEstadoBulto() != null && !ordenDeProduccionDetalleForm.getEstadoBulto().trim().equalsIgnoreCase("-1")) {
                ordenDeProduccionBultoModel.setEstado(ordenDeProduccionDetalleForm.getEstadoBulto());
            } else {
                ordenDeProduccionBultoModel.setEstado(null);
            }                            
            if(ordenDeProduccionDetalleForm.getObservacionesBulto() != null && !ordenDeProduccionDetalleForm.getObservacionesBulto().trim().isEmpty()) {
                ordenDeProduccionBultoModel.setObservaciones(ordenDeProduccionDetalleForm.getObservacionesBulto());
            } else {
                ordenDeProduccionBultoModel.setObservaciones(null);
            }
            
            System.out.println("*** Paso 3");

            OrdenDeProduccionBultoModel existe = ordenDeProduccionBultoService.getByOrdenDeProduccionBobina(bobinaSelected.getId());
            
            if(existe != null && !ordenDeProduccionDetalleForm.getAction().equalsIgnoreCase("edit")) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: La bobina ya se encuentra registrada");
                return modelAndView;                                    
            }
            
            if(ordenDeProduccionDetalleForm.getAction().equalsIgnoreCase("add") || ordenDeProduccionDetalleForm.getAction().equalsIgnoreCase("edit")) {
                System.out.println("*** Paso 4");
                ordenDeProduccionBultoService.save(ordenDeProduccionBultoModel);
                if(id.equalsIgnoreCase("-1")) {                                
                    ordenDeProduccionBultoModel.setCodigo("R"+bobinaSelected.getId());
                    ordenDeProduccionBultoModel.setIdOrdenDeProduccionBobina(bobinaSelected.getId());
                    ordenDeProduccionBultoService.save(ordenDeProduccionBultoModel);      
                    pk = ordenDeProduccionBultoModel.getId();
                }
                bobinaSelected.setEstaEnBulto(Boolean.TRUE);
                if(!ordenDeProduccionDetalleForm.getAction().equalsIgnoreCase("edit")){
                    this.cantidadDeBobinasQueNoEstanEnBulto = this.cantidadDeBobinasQueNoEstanEnBulto - 1;
                    this.cantidadDeBultosQueNoEstanEnPallet = this.cantidadDeBultosQueNoEstanEnPallet + 1;
                }
                
                
                ordenDeProduccionBobinaService.save(bobinaSelected);
            } else {
                if(ordenDeProduccionDetalleForm.getAction().equalsIgnoreCase("remove")) {
                    if(ordenDeProduccionBultoModel.getId() == null || ordenDeProduccionBultoModel.getEstaEnPallet() || ordenDeProduccionModel.getEstado().equalsIgnoreCase("Completado")) {
                        modelAndView.setViewName("error");
                        modelAndView.addObject("errorMessage", "Error: No es posible eliminar la bobina.");
                        return modelAndView;                                    
                    }

                    OrdenDeProduccionBobinaModel bobinaModel = ordenDeProduccionBobinaService.getByPk(ordenDeProduccionBultoModel.getIdOrdenDeProduccionBobina());
                    if(bobinaModel == null) {
                        modelAndView.setViewName("error");
                        modelAndView.addObject("errorMessage", "Error: No se encuentra bulto para bobina.");
                        return modelAndView;                                                            
                    }                    
                    ordenDeProduccionBultoService.delete(ordenDeProduccionBultoModel);
                    
                    bobinaModel.setEstaEnBulto(false);
                    ordenDeProduccionBobinaService.save(bobinaModel);
                    
                } else {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: operación inválida.");
                    return modelAndView;                                
                }
            }
             if(ordenDeProduccionDetalleForm.getAction().equalsIgnoreCase("edit")){
                             pk = ordenDeProduccionBultoModel.getId();
                            }             
        }
        
        if(ordenDeProduccionDetalleForm.getTipoDetalle().equalsIgnoreCase("pallet")) {
            
            System.out.println("*** Paso 1");
            OrdenDeProduccionBultoService ordenDeProduccionBultoService = new OrdenDeProduccionBultoServiceImpl();     
            List<String> bultosSelected = ordenDeProduccionDetalleForm.getBultosSelected();
            System.out.println("**** bultosSelected:"+bultosSelected);
            if(bultosSelected == null || bultosSelected.isEmpty()) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: no exiten bultos para agregar.");
                return modelAndView;                                                
            }

            for(String idBulto :bultosSelected) {                
                OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(Integer.valueOf(idBulto));
                if(bulto == null) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: No ha sido encontrado bulto "+idBulto+".");
                    return modelAndView;                                                                    
                }
            }
            
            OrdenDeProduccionPalletBultoService ordenDeProduccionPalletBultoService = new OrdenDeProduccionPalletBultoServiceImpl();     
            OrdenDeProduccionPalletService ordenDeProduccionPalletService = new OrdenDeProduccionPalletServiceImpl();     
            
            OrdenDeProduccionPalletModel ordenDeProduccionPalletModel = null;
            if(id.equalsIgnoreCase("-1")) {            
                ordenDeProduccionPalletModel = new OrdenDeProduccionPalletModel();
                ordenDeProduccionPalletModel.setIdOrdenDeProduccion(ordenDeProduccionModel.getId());                
                ordenDeProduccionPalletModel.setFechaAlta(new Date());
                ordenDeProduccionPalletModel.setIdUsuarioAlta(Integer.valueOf(Utils.getUserLoggedId(req)));
                ordenDeProduccionPalletModel.setEstaDisponibleParaRemito(Boolean.TRUE);                
            } else {
                ordenDeProduccionPalletModel = ordenDeProduccionPalletService.getByPk(Integer.valueOf(id));
                if(ordenDeProduccionPalletModel == null) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: id de bulto de orden de produccion inválido.");
                    return modelAndView;                
                } 
                System.out.println("*** Paso 2");
            }
            
            if(ordenDeProduccionDetalleForm.getEstadoPallet() != null && !ordenDeProduccionDetalleForm.getEstadoPallet().trim().equalsIgnoreCase("-1")) {
                ordenDeProduccionPalletModel.setEstado(ordenDeProduccionDetalleForm.getEstadoPallet());
            } else {
                ordenDeProduccionPalletModel.setEstado(null);
            }                            
            if(ordenDeProduccionDetalleForm.getObservacionesPallet() != null && !ordenDeProduccionDetalleForm.getObservacionesPallet().trim().isEmpty()) {
                ordenDeProduccionPalletModel.setObservaciones(ordenDeProduccionDetalleForm.getObservacionesPallet());
            } else {
                ordenDeProduccionPalletModel.setObservaciones(null);
            }                            
                        
            System.out.println("*** Paso 3");
            if(ordenDeProduccionDetalleForm.getAction().equalsIgnoreCase("add") || ordenDeProduccionDetalleForm.getAction().equalsIgnoreCase("edit")) {
                System.out.println("*** Paso 4");
                ordenDeProduccionPalletService.save(ordenDeProduccionPalletModel);
                if(id.equalsIgnoreCase("-1")) {                                
                    ordenDeProduccionPalletModel.setCodigo("P"+ordenDeProduccionPalletModel.getId());
                    ordenDeProduccionPalletService.save(ordenDeProduccionPalletModel);  
                    pk = ordenDeProduccionPalletModel.getId();
                }
                List<OrdenDeProduccionPalletBultoModel> palletBultoList = ordenDeProduccionPalletBultoService.getAllByOrdenDeProduccionPallet(ordenDeProduccionPalletModel.getId());
                
                List<String> idBultosAlreadyExist = new ArrayList<String>();
                List<String> idBultosToRemove = new ArrayList<String>();
                
                for(OrdenDeProduccionPalletBultoModel palletBulto: palletBultoList) {
                    idBultosAlreadyExist.add(palletBulto.getIdOrdenDeProduccionBulto().toString());
                }                
                for(OrdenDeProduccionPalletBultoModel palletBulto: palletBultoList) {
                    idBultosToRemove.add(palletBulto.getIdOrdenDeProduccionBulto().toString());
                }       
                int cantBultos = 0;
                for(String idBulto :bultosSelected) {
                    if(!idBultosAlreadyExist.contains(idBulto)) {
                        OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(Integer.valueOf(idBulto));
                        
                        OrdenDeProduccionPalletBultoModel palletBulto = new OrdenDeProduccionPalletBultoModel();
                        palletBulto.setIdOrdenDeProduccionPallet(ordenDeProduccionPalletModel.getId());
                        palletBulto.setIdOrdenDeProduccionBulto(Integer.valueOf(idBulto));
                        palletBulto.setFechaAlta(new Date());
                        palletBulto.setIdUsuarioAlta(Integer.valueOf(Utils.getUserLoggedId(req)));
                        
                        ordenDeProduccionPalletBultoService.save(palletBulto);
                        
                        bulto.setEstaEnPallet(Boolean.TRUE);
                        cantBultos = cantBultos + 1;
                        this.cantidadDeBultosQueNoEstanEnPallet = this.cantidadDeBultosQueNoEstanEnPallet - 1;
                        ordenDeProduccionBultoService.save(bulto);
                    }
                    idBultosToRemove.remove(idBulto);
                }
                
                if(ordenDeProduccionDetalleForm.getAction().equalsIgnoreCase("edit")){
                 pk = ordenDeProduccionPalletModel.getId();
                }
   

                if(!ordenDeProduccionDetalleForm.getAction().equalsIgnoreCase("edit")){
                 this.cantidadPallet = this.cantidadPallet + 1;
                }
                
                if(!idBultosToRemove.isEmpty()) {
                    for(OrdenDeProduccionPalletBultoModel palletBulto: palletBultoList) {
                        for(String idBulto :idBultosToRemove) {
                            if(palletBulto.getIdOrdenDeProduccionBulto().toString().equals(idBulto)) {
                                OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(Integer.valueOf(idBulto));

                                ordenDeProduccionPalletBultoService.delete(palletBulto);
                                
                                bulto.setEstaEnPallet(Boolean.FALSE);
                                this.cantidadDeBultosQueNoEstanEnPallet = this.cantidadDeBultosQueNoEstanEnPallet + 1;
                                ordenDeProduccionBultoService.save(bulto);
                                
                            }
                        }
                    }
                }
            } else {
                if(ordenDeProduccionDetalleForm.getAction().equalsIgnoreCase("remove")) {
                    if(ordenDeProduccionPalletModel.getId() == null) {
                        modelAndView.setViewName("error");
                        modelAndView.addObject("errorMessage", "Error: id de ordenDeCompraItem inválido.");
                        return modelAndView;                                    
                    }

                    ordenDeProduccionPalletService.delete(ordenDeProduccionPalletModel);
                } else {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: operación inválida.");
                    return modelAndView;                                
                }
            }            
        }
        
        if(ordenDeProduccionDetalleForm.getTipoDetalle().equalsIgnoreCase("scrap")) {
            System.out.println("*** Paso 1 - Scrap");
            OrdenDeProduccionScrapService ordenDeProduccionScrapService = new OrdenDeProduccionScrapServiceImpl();     
            
            OrdenDeProduccionScrapModel ordenDeProduccionScrapModel = null;
            if(id.equalsIgnoreCase("-1")) {            
                ordenDeProduccionScrapModel = new OrdenDeProduccionScrapModel();
                ordenDeProduccionScrapModel.setIdOrdenDeProduccion(ordenDeProduccionModel.getId());                
                ordenDeProduccionScrapModel.setFechaAlta(new Date());
                ordenDeProduccionScrapModel.setEstado("Nuevo");                
                ordenDeProduccionScrapModel.setIdUsuarioAlta(Integer.valueOf(Utils.getUserLoggedId(req)));                
            } else {
                ordenDeProduccionScrapModel = ordenDeProduccionScrapService.getByPk(Integer.valueOf(id));
                if(ordenDeProduccionScrapModel == null) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: id de scrap de orden de produccion inválido.");
                    return modelAndView;                
                } 
                System.out.println("*** Paso 2");
            }
            
            if(ordenDeProduccionDetalleForm.getIdOrigenScrap() != null && !ordenDeProduccionDetalleForm.getIdOrigenScrap().trim().equals("")) {
                ordenDeProduccionScrapModel.setIdOrigen(Integer.valueOf(ordenDeProduccionDetalleForm.getIdOrigenScrap()));
            } else {
                ordenDeProduccionScrapModel.setIdOrigen(null);
            }                            
            if(ordenDeProduccionDetalleForm.getIdTipoMaterialScrap() != null && !ordenDeProduccionDetalleForm.getIdTipoMaterialScrap().trim().equals("")) {
                ordenDeProduccionScrapModel.setIdTipoMaterial(Integer.valueOf(ordenDeProduccionDetalleForm.getIdTipoMaterialScrap()));
            } else {
                ordenDeProduccionScrapModel.setIdTipoMaterial(null);
            }                            
            if(ordenDeProduccionDetalleForm.getIdMotivoScrap() != null && !ordenDeProduccionDetalleForm.getIdMotivoScrap().trim().equals("")) {
                ordenDeProduccionScrapModel.setIdMotivo(Integer.valueOf(ordenDeProduccionDetalleForm.getIdMotivoScrap()));
            } else {
                ordenDeProduccionScrapModel.setIdMotivo(null);
            }                            
            if(ordenDeProduccionDetalleForm.getIdFormatoScrap() != null && !ordenDeProduccionDetalleForm.getIdFormatoScrap().trim().equals("")) {
                ordenDeProduccionScrapModel.setIdFormato(Integer.valueOf(ordenDeProduccionDetalleForm.getIdFormatoScrap()));
            } else {
                ordenDeProduccionScrapModel.setIdFormato(null);
            }                            
            if(ordenDeProduccionDetalleForm.getEsRecuperableScrap() != null) {
                if(ordenDeProduccionDetalleForm.getEsRecuperableScrap().equals("1")) {
                    ordenDeProduccionScrapModel.setEsRecuperable(Boolean.TRUE);
                } else {
                    ordenDeProduccionScrapModel.setEsRecuperable(Boolean.FALSE);
                }
            } else {
                ordenDeProduccionScrapModel.setEsRecuperable(Boolean.FALSE);
            }            
            if(ordenDeProduccionDetalleForm.getMaterialImpresoScrap() != null) {
                if(ordenDeProduccionDetalleForm.getMaterialImpresoScrap().equals("1")) {
                    ordenDeProduccionScrapModel.setMaterialImpreso(Boolean.TRUE);
                } else {
                    ordenDeProduccionScrapModel.setMaterialImpreso(Boolean.FALSE);
                }
            } else {
                ordenDeProduccionScrapModel.setMaterialImpreso(Boolean.FALSE);
            }                        
            if(ordenDeProduccionDetalleForm.getPesoTotalScrap() != null && !ordenDeProduccionDetalleForm.getPesoTotalScrap().trim().equals("")) {
                ordenDeProduccionScrapModel.setPesoTotal(Double.valueOf(ordenDeProduccionDetalleForm.getPesoTotalScrap()));
            } else {
                ordenDeProduccionScrapModel.setPesoTotal(null);
            }                                                           
            if(ordenDeProduccionDetalleForm.getObservacionesScrap() != null && !ordenDeProduccionDetalleForm.getObservacionesScrap().trim().equals("")) {
                ordenDeProduccionScrapModel.setObservaciones(ordenDeProduccionDetalleForm.getObservacionesScrap());
            } else {
                ordenDeProduccionScrapModel.setObservaciones(null);
            }                            
            
            ordenDeProduccionScrapModel.setCantidadUtilizada(0.0);
            
            System.out.println("*** Paso 3");
            if(ordenDeProduccionDetalleForm.getAction().equalsIgnoreCase("add") || ordenDeProduccionDetalleForm.getAction().equalsIgnoreCase("edit")) {
                System.out.println("*** Paso 4");
                ordenDeProduccionScrapService.save(ordenDeProduccionScrapModel);
                if(id.equalsIgnoreCase("-1")) {                                
                    ordenDeProduccionScrapModel.setCodigo("S"+ordenDeProduccionScrapModel.getId());
                    ordenDeProduccionScrapService.save(ordenDeProduccionScrapModel);
                    pk = ordenDeProduccionScrapModel.getId();
                }
            } else {
                if(ordenDeProduccionDetalleForm.getAction().equalsIgnoreCase("remove")) {
                    if(ordenDeProduccionScrapModel.getId() == null) {
                        modelAndView.setViewName("error");
                        modelAndView.addObject("errorMessage", "Error: id de orden de producción Scrap inválido.");
                        return modelAndView;                                    
                    }

                    ordenDeProduccionScrapService.delete(ordenDeProduccionScrapModel);
                } else {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: operación inválida.");
                    return modelAndView;                                
                }
            }            
        }
        
        
        OrdenDeProduccionBultoService ordenDeProduccionBultoService = new OrdenDeProduccionBultoServiceImpl();
        OrdenDeProduccionBultoModel ordenDeProduccionBultoModel = ordenDeProduccionBultoService.getByPk(Integer.valueOf(id));
        OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();
        if(ordenDeProduccionDetalleForm.getPesoTotalBobina() != null) {
            OrdenDeProduccionBobinaModel bobinaModel = ordenDeProduccionBobinaService.getByPk(ordenDeProduccionBultoModel.getIdOrdenDeProduccionBobina());
            if(bobinaModel == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: No se encuentra bulto para bobina.");
                return modelAndView;                                                            
            }                    


            String test = ordenDeProduccionDetalleForm.getPesoTotalBobinaBulto();


            bobinaModel.setPesoTotal(Double.parseDouble(ordenDeProduccionDetalleForm.getPesoTotalBobinaBulto()));

            ordenDeProduccionBobinaService.save(bobinaModel);

        }
        
        String tipo = ordenDeProduccionDetalleForm.getTipoDetalle();
        
        if(!ordenDeProduccionDetalleForm.getAction().equalsIgnoreCase("remove")) {
            modelAndView.setViewName("redirect:/ordenDeProduccionDetalle/"+ordenDeProduccionDetalleForm.getIdOrdenProduccion()+"/tipo/"+tipo+"/pk/"+pk);
        } else {
            modelAndView.setViewName("redirect:/"
                    + "ordenDeProduccionDetalle/"+ordenDeProduccionDetalleForm.getIdOrdenProduccion());
        }
        model.addAttribute("cantidadDeBobinasQueNoEstanEnBulto", this.cantidadDeBobinasQueNoEstanEnBulto);
        model.addAttribute("cantidadDeBultosQueNoEstanEnPallet", this.cantidadDeBultosQueNoEstanEnPallet);
        model.addAttribute("cantidadPallet", this.cantidadPallet);
        model.addAttribute("cantidadBobinasEnProduccion", this.cantidadBobinasEnProduccion);
        

        return modelAndView; 
    }

    
    @RequestMapping(value = "/ordenDeProduccionDetalle/editbobina/{ordenDeProduccionBobinaPk}", method = RequestMethod.GET)
    public String editOrdenDeProduccion(@PathVariable String ordenDeProduccionBobinaPk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeProduccionBobinaPk == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido");         
            return "/error";                
        }
        
        String operacion = "edit";                
        String displayActionButton = "block";
        String displayActionButtonScrap = "none";

        OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();   
        OrdenDeProduccionBobinaModel ordenDeProduccionBobina = ordenDeProduccionBobinaService.getByPk(Integer.valueOf(ordenDeProduccionBobinaPk));
        if(ordenDeProduccionBobina == null) {
            model.addAttribute("errorMessage", "Error: Orden De Produccion Bobina inválido. No ha sido encontrado.");         
            return "/error";    
        }
        
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();   
        OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(Integer.valueOf(ordenDeProduccionBobina.getIdOrdenDeProduccion()));
        if(ordenDeProduccion == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido. No ha sido encontrado.");         
            return "/error";    
        }
                 
        //if(!ordenDeProduccion.getEstado().equalsIgnoreCase("Abierto") && !ordenDeProduccion.getEstado().equalsIgnoreCase("Fabricacion")) {
            //model.addAttribute("errorMessage", "Error: estado de orden incorrecto.");         
            //return "/error";                            
        //}
        
        ClienteService clienteService = new ClienteServiceImpl();
        ClienteModel cliente = clienteService.getByPk(ordenDeProduccion.getIdCliente());
        if(cliente == null) {
            model.addAttribute("errorMessage", "Error: cliente no encontrado");         
            return "/error";                            
        }

        ArticuloService articuloService = new ArticuloServiceImpl();
        ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
        if(articulo == null) {
            model.addAttribute("errorMessage", "Error: articulo no encontrado");         
            return "/error";                            
        }
        
        ArticuloFichaTecnicaService articuloFichaTecnicaService = new ArticuloFichaTecnicaServiceImpl();
        ArticuloFichaTecnicaModel articuloFichaTecnica = articuloFichaTecnicaService.getByPk(ordenDeProduccion.getIdFichaTecnica());
        if(articuloFichaTecnica == null) {
            model.addAttribute("errorMessage", "Error: ficha tecnica no encontrada");         
            return "/error";                            
        }
        
        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(user.getRol() != Utils.ROL_PLANTA && user.getRol() != Utils.ROL_OFICINA) {
            model.addAttribute("errorMessage", "Error: usuario no permite esta operación.");         
            return "/error";                                                                
        }
        
        String rol = "";
        if(user.getRol() == Utils.ROL_PLANTA) {
            rol = "planta";
        }

        
        OrdenDeProduccionDetalleForm ordenDeProduccionDetalleForm = new OrdenDeProduccionDetalleForm();
        ordenDeProduccionDetalleForm.setPk(ordenDeProduccionBobina.getId().toString());
        ordenDeProduccionDetalleForm.setAction("edit");        
        ordenDeProduccionDetalleForm.setOperacion("edit");   
        ordenDeProduccionDetalleForm.setTipoDetalle("bobina");
        ordenDeProduccionDetalleForm.setImprimir("false");

        ordenDeProduccionDetalleForm.setFechaAltaOrdenDeProduccion(ordenDeProduccion.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        ordenDeProduccionDetalleForm.setEstadoOrdenDeProduccion(ordenDeProduccion.getEstado());
        ordenDeProduccionDetalleForm.setIdOrdenProduccion(ordenDeProduccion.getId().toString());
        ordenDeProduccionDetalleForm.setIdCliente(ordenDeProduccion.getIdCliente().toString());
        ordenDeProduccionDetalleForm.setIdArticulo(ordenDeProduccion.getIdArticulo().toString());
        ordenDeProduccionDetalleForm.setIdFichaTecnica(ordenDeProduccion.getIdFichaTecnica().toString());
        ordenDeProduccionDetalleForm.setPesoTecnicoArticulo(articuloFichaTecnica.getPeso().toString());
        ordenDeProduccionDetalleForm.setMetrosArticulo(articuloFichaTecnica.getMetros().toString());
        ordenDeProduccionDetalleForm.setAltoArticulo(articulo.getAlto().toString());
        ordenDeProduccionDetalleForm.setEspesorArticulo(articulo.getEspesor().toString());
        ordenDeProduccionDetalleForm.setAnchoArticulo(articulo.getAncho().toString());

        if(ordenDeProduccionBobina.getPesoCono() != null) {
            ordenDeProduccionDetalleForm.setPesoConoBobina(ordenDeProduccionBobina.getPesoCono().toString());
        }
        if(ordenDeProduccionBobina.getPesoTotal() != null) {
            ordenDeProduccionDetalleForm.setPesoTotalBobina(ordenDeProduccionBobina.getPesoTotal().toString());
        }
        if(ordenDeProduccionBobina.getPesoNeto() != null) {
            ordenDeProduccionDetalleForm.setPesoNetoBobina(ordenDeProduccionBobina.getPesoNeto().toString());
        }
        if(articulo.getEspesor() != null) {
            ordenDeProduccionDetalleForm.setEspesorBobina(articulo.getEspesor().toString());
        }
        if(ordenDeProduccionBobina.getEstado() != null) {
            ordenDeProduccionDetalleForm.setEstadoBobina(ordenDeProduccionBobina.getEstado().toString());
        }
        if(ordenDeProduccionBobina.getObservaciones() != null) {
            ordenDeProduccionDetalleForm.setObservacionesBobina(ordenDeProduccionBobina.getObservaciones().toString());
        }
        if(ordenDeProduccionBobina.getEstaEnBulto() != null) {
            ordenDeProduccionDetalleForm.setEstaEnBultoBobina(ordenDeProduccionBobina.getEstaEnBulto().toString());
        }
                

        model.addAttribute("ordenDeProduccionDetalleForm", ordenDeProduccionDetalleForm);  
        model.addAttribute("titleOrdenDeProduccion", "Editar Orden de Producción Bobina");
        model.addAttribute("buttonLabel", "Guardar");        
        model.addAttribute("ordenDeProduccionBobinaName", "Editando Bobina " + ordenDeProduccionBobina.getCodigo());        
                        
        List<OrdenDeProduccionBobinaModel> ordenDeProduccionBobinasModel = ordenDeProduccionBobinaService.getAllByOrdenDeProduccion(ordenDeProduccion.getId());       
        List<OrdenDeProduccionBobinaDto> ordenDeProduccionBobinasDtos = new ArrayList<OrdenDeProduccionBobinaDto>();
        
        if(ordenDeProduccionBobinasModel != null && !ordenDeProduccionBobinasModel.isEmpty()) {
            for(OrdenDeProduccionBobinaModel ordenDeProduccionBobinaModel: ordenDeProduccionBobinasModel) {
                OrdenDeProduccionBobinaDto ordenDeProduccionBobinaDto = new OrdenDeProduccionBobinaDto();
                ordenDeProduccionBobinaDto.setPk(ordenDeProduccionBobinaModel.getId().toString());
                ordenDeProduccionBobinaDto.setFechaAlta(ordenDeProduccionBobinaModel.getFechaAlta().toString());
                if(ordenDeProduccionBobinaModel.getEstado() != null) {
                    ordenDeProduccionBobinaDto.setEstado(ordenDeProduccionBobinaModel.getEstado());
                    if(ordenDeProduccionBobinaModel.getEstado().equals("ok")) {
                        ordenDeProduccionBobinaDto.setEstadoLabel("OK");
                    }
                    if(ordenDeProduccionBobinaModel.getEstado().equals("observado")) {
                        ordenDeProduccionBobinaDto.setEstadoLabel("Observado");
                    }
                    if(ordenDeProduccionBobinaModel.getEstado().equals("rechazado")) {
                        ordenDeProduccionBobinaDto.setEstadoLabel("Rechazado");
                    }
                    if(ordenDeProduccionBobinaModel.getEstado().equals("sinmesurar")) {
                        ordenDeProduccionBobinaDto.setEstadoLabel("Sin Mesurar");
                    }         
                }
                ordenDeProduccionBobinaDto.setCodigo(ordenDeProduccionBobinaModel.getCodigo());
                ordenDeProduccionBobinaDto.setPesoTotal(ordenDeProduccionBobinaModel.getPesoTotal().toString());
                ordenDeProduccionBobinaDto.setPesoNeto(ordenDeProduccionBobinaModel.getPesoNeto().toString());
                ordenDeProduccionBobinaDto.setEspesor(articulo.getEspesor().toString());
                ordenDeProduccionBobinaDto.setEstaEnBulto(ordenDeProduccionBobinaModel.getEstaEnBulto().toString());
                if(ordenDeProduccionBobinaModel.getEstaEnBulto()) {
                    ordenDeProduccionBobinaDto.setEstaEnBultoLabel("Si");
                } else {
                    ordenDeProduccionBobinaDto.setEstaEnBultoLabel("No");
                }
                
                if(ordenDeProduccionBobinaModel.getIdDeposito() != null) {
                    ordenDeProduccionBobinaDto.setEstaEnDeposito(true);
                } else {
                    ordenDeProduccionBobinaDto.setEstaEnDeposito(false);
                } 

                ordenDeProduccionBobinasDtos.add(ordenDeProduccionBobinaDto);

            }
        }
        
        

        model.addAttribute("displayButtonCambiarEstadoFabricacion", "none");        
        model.addAttribute("displayButtonCambiarEstadoEmpaque", "none");    
        model.addAttribute("displayButtonCambiarEstadoPallet", "none");    
        model.addAttribute("displayButtonCambiarEstadoCompletado", "none");    
        
        model.addAttribute("estaEnBulto", ordenDeProduccionBobina.getEstaEnBulto());
        model.addAttribute("tipoDetalle", "bobina");
        model.addAttribute("idOrdenDeProduccion", ordenDeProduccion.getId().toString());        
        model.addAttribute("clienteLabel", cliente.getRazonSocial());
        model.addAttribute("idArticulo", articulo.getId());
        model.addAttribute("articuloLabel", articulo.getDenominacion());
        model.addAttribute("fichaTecnicaVersionLabel", articuloFichaTecnica.getVersion());
        
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "edit");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("displayActionButtonScrap", displayActionButtonScrap);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("ordenDeProduccionBobinas", ordenDeProduccionBobinasDtos);
        
        model.addAttribute("cantidadDeBobinasQueNoEstanEnBulto", this.cantidadDeBobinasQueNoEstanEnBulto);
        model.addAttribute("cantidadDeBultosQueNoEstanEnPallet", this.cantidadDeBultosQueNoEstanEnPallet);
        model.addAttribute("cantidadPallet", this.cantidadPallet);
        model.addAttribute("cantidadBobinasEnProduccion", this.cantidadBobinasEnProduccion);
                                                                                                              
        return "/ordendeproduccion/ordendeproducciondetalle";
    }
    
    /*
    @RequestMapping(value = "/ordenDeProduccionDetalle/editObservaciones/{ordenDeProduccionpk}", method = RequestMethod.GET)
    public String editObservacionesOrdenDeProduccion(@PathVariable String ordenDeProduccionpk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeProduccionpk == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion invï¿½lido");         
            return "/error";                
        }
        
        String operacion = "alta";        
        String displayActionButton = "block";
        
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();   
        OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(Integer.valueOf(ordenDeProduccionpk));
        if(ordenDeProduccion == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion invï¿½lido. No ha sido encontrado.");         
            return "/error";    
        }
                 
        if(!ordenDeProduccion.getEstado().equalsIgnoreCase("Completado")) {
            model.addAttribute("errorMessage", "Error: estado de orden incorrecto.");         
            return "/error";                            
        }
        
        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(user.getRol() != Utils.ROL_OFICINA) {
            model.addAttribute("errorMessage", "Error: usuario no permite esta operaciï¿½n.");         
            return "/error";                                                                
        }
        
        OrdenDeProduccionForm ordenDeProduccionForm = new OrdenDeProduccionForm();
        ordenDeProduccionForm.setPk(ordenDeProduccion.getId().toString());
        if(ordenDeProduccion.getFechaAlta() != null) {
            ordenDeProduccionForm.setFechaAlta(ordenDeProduccion.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(ordenDeProduccion.getFechaEntrega() != null) {
            ordenDeProduccionForm.setFechaEntrega(ordenDeProduccion.getFechaEntrega().toString().replace(" 00:00:00.0", ""));
        }                
        if(ordenDeProduccion.getIdProveedor() != null) {
            ordenDeProduccionForm.setIdProveedor(ordenDeProduccion.getIdProveedor().toString());
        }        
        if(ordenDeProduccion.getObservaciones() != null && !ordenDeProduccion.getObservaciones().isEmpty()) {
            ordenDeProduccionForm.setObservaciones(ordenDeProduccion.getObservaciones());
        }        
        if(ordenDeProduccion.getReferenciaAdministrativa() != null && !ordenDeProduccion.getReferenciaAdministrativa().isEmpty()) {
            ordenDeProduccionForm.setReferenciaAdministrativa(ordenDeProduccion.getReferenciaAdministrativa());
        }                        
        if(ordenDeProduccion.getEstado() != null && !ordenDeProduccion.getEstado().isEmpty()) {
            ordenDeProduccionForm.setEstado(ordenDeProduccion.getEstado());
        }        
        
        ordenDeProduccionForm.setOperacion(operacion);
        ordenDeProduccionForm.setEditObservaciones("true");
                
        ordenDeProduccionForm.setAction("edit");
        model.addAttribute("ordenDeProduccionForm", ordenDeProduccionForm);  
        model.addAttribute("titleOrdenDeProduccion", "Editar Orden de Compra");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("ordenDeProduccionName", ordenDeProduccion.getId() + " - " + ordenDeProduccion.getIdProveedor());        
        
        List<OrdenDeProduccionModel> ordenDeProducciones = ordenDeProduccionService.getAll();

        ProveedorService proveedorService = new ProveedorServiceImpl();   
        Map<String,String> proveedores = new LinkedHashMap<String,String>();
        List<ProveedorModel> proveedoresModel = proveedorService.getAll();

        if(proveedoresModel != null && !proveedoresModel.isEmpty()){
            for(ProveedorModel proveedorModel :proveedoresModel) {
                proveedores.put(proveedorModel.getId().toString(), proveedorModel.getRazonSocial());
            }
        }
                                
        List<OrdenDeProduccionDto> ordenDeProduccionesDtos = new ArrayList<OrdenDeProduccionDto>();
        for(OrdenDeProduccionModel ordenDeProduccionModel: ordenDeProducciones) {
            OrdenDeProduccionDto ordenDeProduccionDto = new OrdenDeProduccionDto();
            ordenDeProduccionDto.setPk(ordenDeProduccionModel.getId().toString());
            ordenDeProduccionDto.setFechaAlta(ordenDeProduccionModel.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            ordenDeProduccionDto.setEstado(ordenDeProduccionModel.getEstado());
            ordenDeProduccionDto.setProveedor(proveedores.get(ordenDeProduccionModel.getIdProveedor().toString()));
            
            ordenDeProduccionesDtos.add(ordenDeProduccionDto);
        }
                               
        model.addAttribute("displayUser", "none");
                
        model.addAttribute("action", "editObservaciones");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("proveedorList", proveedores);        
        model.addAttribute("ordenDeProducciones", ordenDeProduccionesDtos);        
                                                        
        return "/ordendecompra/ordendecompra";        
    }
    */
    
    @RequestMapping(value = "/ordenDeProduccionDetalle/remove/{ordenDeProduccionpk}", method = RequestMethod.GET)
    public String removeOrdenDeProduccion(@PathVariable String ordenDeProduccionpk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeProduccionpk == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido");         
            return "/error";                
        }
        
        String operacion = "alta";        
        String displayActionButton = "block";
        String displayActionButtonScrap = "none";
        
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();   
        OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(Integer.valueOf(ordenDeProduccionpk));
        if(ordenDeProduccion == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválida. No ha sido encontrado.");         
            return "/error";    
        }
                 
        if(!ordenDeProduccion.getEstado().equalsIgnoreCase("Nuevo")) {
            model.addAttribute("errorMessage", "Error: estado de orden incorrecto.");         
            return "/error";                            
        }
        
        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(user.getRol() != Utils.ROL_OFICINA) {
            model.addAttribute("errorMessage", "Error: usuario no permite esta operación.");         
            return "/error";                                                                
        }
        
        String rol = "";
        if(user.getRol() == Utils.ROL_OFICINA) {
            rol = "oficina";
        }
        if(user.getRol() == Utils.ROL_PLANTA) {
            rol = "planta";
        }

        
        OrdenDeProduccionForm ordenDeProduccionForm = new OrdenDeProduccionForm();
        ordenDeProduccionForm.setPk(ordenDeProduccion.getId().toString());
        if(ordenDeProduccion.getFechaAlta() != null) {
            ordenDeProduccionForm.setFechaAlta(ordenDeProduccion.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(ordenDeProduccion.getFechaPedido() != null) {
            ordenDeProduccionForm.setFechaPedido(ordenDeProduccion.getFechaPedido().toString().replace(" 00:00:00.0", ""));
        }        
        if(ordenDeProduccion.getFechaCierre() != null) {
            ordenDeProduccionForm.setFechaCierre(ordenDeProduccion.getFechaCierre().toString().replace(" 00:00:00.0", ""));
        }                
        if(ordenDeProduccion.getIdCliente() != null) {
            ordenDeProduccionForm.setIdCliente(ordenDeProduccion.getIdCliente().toString());
            ordenDeProduccionForm.setIdClienteAux(ordenDeProduccion.getIdCliente().toString());
        }        
        if(ordenDeProduccion.getIdArticulo() != null) {
            ordenDeProduccionForm.setIdArticulo(ordenDeProduccion.getIdArticulo().toString());
            ordenDeProduccionForm.setIdArticuloAux(ordenDeProduccion.getIdArticulo().toString());
        }        
        if(ordenDeProduccion.getIdFichaTecnica() != null) {
            ordenDeProduccionForm.setIdFichaTecnica(ordenDeProduccion.getIdFichaTecnica().toString());
            ordenDeProduccionForm.setIdFichaTecnicaAux(ordenDeProduccion.getIdFichaTecnica().toString());
        }                
        if(ordenDeProduccion.getIdUnidadVenta() != null) {
            ordenDeProduccionForm.setIdUnidadVenta(ordenDeProduccion.getIdUnidadVenta().toString());
        }                
        if(ordenDeProduccion.getIdUsuarioAlta() != null) {
            ordenDeProduccionForm.setIdUsuarioAlta(ordenDeProduccion.getIdUsuarioAlta().toString());
        }                
        if(ordenDeProduccion.getIdUsuarioCierre() != null) {
            ordenDeProduccionForm.setIdUsuarioCierre(ordenDeProduccion.getIdUsuarioCierre().toString());
        }                        
        if(ordenDeProduccion.getObservaciones() != null && !ordenDeProduccion.getObservaciones().isEmpty()) {
            ordenDeProduccionForm.setObservaciones(ordenDeProduccion.getObservaciones());
        }        
        if(ordenDeProduccion.getEstado() != null && !ordenDeProduccion.getEstado().isEmpty()) {
            ordenDeProduccionForm.setEstado(ordenDeProduccion.getEstado());
        }        
        if(ordenDeProduccion.getCantidadAProducir() != null) {
            ordenDeProduccionForm.setCantidadAProducir(ordenDeProduccion.getCantidadAProducir().toString());
        }        
        
        ordenDeProduccionForm.setOperacion(operacion);
        
        ordenDeProduccionForm.setAction("remove");
        model.addAttribute("ordenDeProduccionForm", ordenDeProduccionForm);  
        model.addAttribute("titleOrdenDeProduccion", "Eliminar Orden de Producción");
        model.addAttribute("buttonLabel", "Eliminar");
        model.addAttribute("ordenDeProduccionName", ordenDeProduccion.getId() + " - " + ordenDeProduccion.getIdCliente() + " - " + ordenDeProduccion.getIdArticulo());        
        
        List<OrdenDeProduccionModel> ordenDeProducciones = ordenDeProduccionService.getAll();

        TipoService tipoService = new TipoServiceImpl();   
        Map<String,String> unidadVentas = new LinkedHashMap<String,String>();
        List<TipoModel> unidadVentasModel = tipoService.getByType("ordenDeProduccionUnidadVenta");

        if(unidadVentasModel != null && !unidadVentasModel.isEmpty()){
            for(TipoModel tipoModel :unidadVentasModel) {
                unidadVentas.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        
        ClienteService clienteService = new ClienteServiceImpl();   
        Map<String,String> clientes = new LinkedHashMap<String,String>();
        List<ClienteModel> clientesModel = clienteService.getAll();

        if(clientesModel != null && !clientesModel.isEmpty()){
            for(ClienteModel clienteModel :clientesModel) {
                clientes.put(clienteModel.getId().toString(), clienteModel.getRazonSocial());
            }
        }

        ArticuloService articuloService = new ArticuloServiceImpl();   
        Map<String,String> articulos = new LinkedHashMap<String,String>();
        List<ArticuloModel> articulosModel = articuloService.getAll();

        if(articulosModel != null && !articulosModel.isEmpty()){
            for(ArticuloModel articuloModel :articulosModel) {
                articulos.put(articuloModel.getId().toString(), articuloModel.getDenominacion());
            }
        }

        ArticuloFichaTecnicaService articuloFichaTecnicaService = new ArticuloFichaTecnicaServiceImpl();   
        Map<String,String> articulosFichaTecnica = new LinkedHashMap<String,String>();
        List<ArticuloFichaTecnicaModel> articulosFichaTecnicaModel = articuloFichaTecnicaService.getAll();

        if(articulosFichaTecnicaModel != null && !articulosFichaTecnicaModel.isEmpty()){
            for(ArticuloFichaTecnicaModel articuloFichaTecnicaModel :articulosFichaTecnicaModel) {
                articulosFichaTecnica.put(articuloFichaTecnicaModel.getId().toString(), articuloFichaTecnicaModel.getVersion().toString());
            }
        }
        
        List<OrdenDeProduccionDto> ordenDeProduccionesDtos = new ArrayList<OrdenDeProduccionDto>();
        for(OrdenDeProduccionModel ordenDeProduccionModel: ordenDeProducciones) {
            OrdenDeProduccionDto ordenDeProduccionDto = new OrdenDeProduccionDto();
            ordenDeProduccionDto.setPk(ordenDeProduccionModel.getId().toString());
            ordenDeProduccionDto.setFechaAlta(ordenDeProduccionModel.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            ordenDeProduccionDto.setEstado(ordenDeProduccionModel.getEstado());
            if(ordenDeProduccionModel.getIdCliente() != null) {
                ordenDeProduccionDto.setCliente(clientes.get(ordenDeProduccionModel.getIdCliente().toString()));
            }
            if(ordenDeProduccionModel.getIdArticulo() != null) {
                ordenDeProduccionDto.setArticulo(articulos.get(ordenDeProduccionModel.getIdArticulo().toString()));
            }
            if(ordenDeProduccionModel.getIdFichaTecnica() != null) {
                ordenDeProduccionDto.setVersionFichaTecnica(articulosFichaTecnica.get(ordenDeProduccionModel.getIdFichaTecnica().toString()));
            }
            if(ordenDeProduccionModel.getCantidadAProducir() != null) {
                ordenDeProduccionDto.setCantidadAProducir(ordenDeProduccionModel.getCantidadAProducir().toString());
            }
            if(ordenDeProduccionModel.getEstado().equalsIgnoreCase("Nuevo")) {
                ordenDeProduccionDto.setSePuedeCambiarEstadoAbierto("true");    
            } else {
                ordenDeProduccionDto.setSePuedeCambiarEstadoAbierto("false");    
            }
            
            ordenDeProduccionesDtos.add(ordenDeProduccionDto);
            
            
        }
                       
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "remove");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("displayActionButtonScrap", displayActionButtonScrap);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("clienteList", clientes);        
        model.addAttribute("unidadVentaList", unidadVentas);        
        model.addAttribute("ordenDeProducciones", ordenDeProduccionesDtos);
                                                                                       
        return "/ordendeproduccion/ordendeproduccion";               
             
    }    
    
    @RequestMapping(value = "/ordenDeProduccionDetalle/viewbobina/{ordenDeProduccionBobinaPk}", method = RequestMethod.GET)
    public String viewOrdenDeProduccion(@PathVariable String ordenDeProduccionBobinaPk, HttpServletRequest req, ModelMap model) throws Exception {
        
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeProduccionBobinaPk == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido");         
            return "/error";                
        }
        
        String operacion = "view";                
        String displayActionButton = "none";
        String displayActionButtonScrap = "none";

        OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();   
        OrdenDeProduccionBobinaModel ordenDeProduccionBobina = ordenDeProduccionBobinaService.getByPk(Integer.valueOf(ordenDeProduccionBobinaPk));
        if(ordenDeProduccionBobina == null) {
            model.addAttribute("errorMessage", "Error: Orden De Produccion Bobina inválido. No ha sido encontrado.");         
            return "/error";    
        }
        
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();   
        OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(Integer.valueOf(ordenDeProduccionBobina.getIdOrdenDeProduccion()));
        if(ordenDeProduccion == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido. No ha sido encontrado.");         
            return "/error";    
        }
        
        /*
        if(!ordenDeProduccion.getEstado().equalsIgnoreCase("Abierto") && !ordenDeProduccion.getEstado().equalsIgnoreCase("Fabricacion")) {
            model.addAttribute("errorMessage", "Error: estado de orden incorrecto.");         
            return "/error";                            
        }
        */
        
        ClienteService clienteService = new ClienteServiceImpl();
        ClienteModel cliente = clienteService.getByPk(ordenDeProduccion.getIdCliente());
        if(cliente == null) {
            model.addAttribute("errorMessage", "Error: cliente no encontrado");         
            return "/error";                            
        }

        ArticuloService articuloService = new ArticuloServiceImpl();
        ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
        if(articulo == null) {
            model.addAttribute("errorMessage", "Error: articulo no encontrado");         
            return "/error";                            
        }
        
        ArticuloFichaTecnicaService articuloFichaTecnicaService = new ArticuloFichaTecnicaServiceImpl();
        ArticuloFichaTecnicaModel articuloFichaTecnica = articuloFichaTecnicaService.getByPk(ordenDeProduccion.getIdFichaTecnica());
        if(articuloFichaTecnica == null) {
            model.addAttribute("errorMessage", "Error: ficha tecnica no encontrada");         
            return "/error";                            
        }
        
        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(user.getRol() != Utils.ROL_OFICINA && user.getRol() != Utils.ROL_PLANTA && user.getRol() != Utils.ROL_DEPOSITO) {
            model.addAttribute("errorMessage", "Error: usuario no permite esta operación.");         
            return "/error";                                                                
        }
        
        String rol = "";
        if(user.getRol() == Utils.ROL_PLANTA) {
            rol = "planta";
        }
        if(user.getRol() == Utils.ROL_OFICINA) {
            rol = "oficina";
        }
        if(user.getRol() == Utils.ROL_DEPOSITO) {
            rol = "deposito";
        }

        
        OrdenDeProduccionDetalleForm ordenDeProduccionDetalleForm = new OrdenDeProduccionDetalleForm();
        ordenDeProduccionDetalleForm.setPk(ordenDeProduccionBobina.getId().toString());
        ordenDeProduccionDetalleForm.setAction("view");        
        ordenDeProduccionDetalleForm.setOperacion("view");   
        ordenDeProduccionDetalleForm.setTipoDetalle("bobina");
        ordenDeProduccionDetalleForm.setImprimir("false");
        
        ordenDeProduccionDetalleForm.setFechaAltaOrdenDeProduccion(ordenDeProduccion.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        ordenDeProduccionDetalleForm.setEstadoOrdenDeProduccion(ordenDeProduccion.getEstado());
        ordenDeProduccionDetalleForm.setIdOrdenProduccion(ordenDeProduccion.getId().toString());
        ordenDeProduccionDetalleForm.setIdCliente(ordenDeProduccion.getIdCliente().toString());
        ordenDeProduccionDetalleForm.setIdArticulo(ordenDeProduccion.getIdArticulo().toString());
        ordenDeProduccionDetalleForm.setIdFichaTecnica(ordenDeProduccion.getIdFichaTecnica().toString());
        ordenDeProduccionDetalleForm.setPesoTecnicoArticulo(articuloFichaTecnica.getPeso().toString());
        ordenDeProduccionDetalleForm.setMetrosArticulo(articuloFichaTecnica.getMetros().toString());
        ordenDeProduccionDetalleForm.setAltoArticulo(articulo.getAlto().toString());
        ordenDeProduccionDetalleForm.setEspesorArticulo(articulo.getEspesor().toString());
        ordenDeProduccionDetalleForm.setAnchoArticulo(articulo.getAncho().toString());

        if(ordenDeProduccionBobina.getPesoCono() != null) {
            ordenDeProduccionDetalleForm.setPesoConoBobina(ordenDeProduccionBobina.getPesoCono().toString());
        }
        if(ordenDeProduccionBobina.getPesoTotal() != null) {
            ordenDeProduccionDetalleForm.setPesoTotalBobina(ordenDeProduccionBobina.getPesoTotal().toString());
        }
        if(ordenDeProduccionBobina.getPesoNeto() != null) {
            ordenDeProduccionDetalleForm.setPesoNetoBobina(ordenDeProduccionBobina.getPesoNeto().toString());
        }
        if(articulo.getEspesor() != null) {
            ordenDeProduccionDetalleForm.setEspesorBobina(articulo.getEspesor().toString());
        }        
        if(ordenDeProduccionBobina.getEstado() != null) {
            ordenDeProduccionDetalleForm.setEstadoBobina(ordenDeProduccionBobina.getEstado().toString());
        }
        if(ordenDeProduccionBobina.getObservaciones() != null) {
            ordenDeProduccionDetalleForm.setObservacionesBobina(ordenDeProduccionBobina.getObservaciones().toString());
        }
        if(ordenDeProduccionBobina.getEstaEnBulto() != null) {
            ordenDeProduccionDetalleForm.setEstaEnBultoBobina(ordenDeProduccionBobina.getEstaEnBulto().toString());
        }
                

        model.addAttribute("ordenDeProduccionDetalleForm", ordenDeProduccionDetalleForm);  
        model.addAttribute("titleOrdenDeProduccion", "Ver Orden de Producción Bobina");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("ordenDeProduccionBobinaName", "Ver Bobina " + ordenDeProduccionBobina.getCodigo());        
                        
        List<OrdenDeProduccionBobinaModel> ordenDeProduccionBobinasModel = ordenDeProduccionBobinaService.getAllByOrdenDeProduccion(ordenDeProduccion.getId());       
        List<OrdenDeProduccionBobinaDto> ordenDeProduccionBobinasDtos = new ArrayList<OrdenDeProduccionBobinaDto>();
        Integer cantidadDeBobinasQueNoEstanEnBulto = 0;
        
        if(ordenDeProduccionBobinasModel != null && !ordenDeProduccionBobinasModel.isEmpty()) {
            for(OrdenDeProduccionBobinaModel ordenDeProduccionBobinaModel: ordenDeProduccionBobinasModel) {
                OrdenDeProduccionBobinaDto ordenDeProduccionBobinaDto = new OrdenDeProduccionBobinaDto();
                ordenDeProduccionBobinaDto.setPk(ordenDeProduccionBobinaModel.getId().toString());
                ordenDeProduccionBobinaDto.setFechaAlta(ordenDeProduccionBobinaModel.getFechaAlta().toString());
                if(ordenDeProduccionBobinaModel.getEstado() != null) {
                    ordenDeProduccionBobinaDto.setEstado(ordenDeProduccionBobinaModel.getEstado());
                    if(ordenDeProduccionBobinaModel.getEstado().equals("ok")) {
                        ordenDeProduccionBobinaDto.setEstadoLabel("OK");
                    }
                    if(ordenDeProduccionBobinaModel.getEstado().equals("observado")) {
                        ordenDeProduccionBobinaDto.setEstadoLabel("Observado");
                    }
                    if(ordenDeProduccionBobinaModel.getEstado().equals("rechazado")) {
                        ordenDeProduccionBobinaDto.setEstadoLabel("Rechazado");
                    }
                    if(ordenDeProduccionBobinaModel.getEstado().equals("sinmesurar")) {
                        ordenDeProduccionBobinaDto.setEstadoLabel("Sin Mesurar");
                    } 
                }
                ordenDeProduccionBobinaDto.setCodigo(ordenDeProduccionBobinaModel.getCodigo());
                ordenDeProduccionBobinaDto.setPesoTotal(ordenDeProduccionBobinaModel.getPesoTotal().toString());
                ordenDeProduccionBobinaDto.setPesoNeto(ordenDeProduccionBobinaModel.getPesoNeto().toString());
                ordenDeProduccionBobinaDto.setEstaEnBulto(ordenDeProduccionBobinaModel.getEstaEnBulto().toString());
                if(ordenDeProduccionBobinaModel.getEstaEnBulto()) {
                    ordenDeProduccionBobinaDto.setEstaEnBultoLabel("Si");
                } else {
                    ordenDeProduccionBobinaDto.setEstaEnBultoLabel("No");
                    cantidadDeBobinasQueNoEstanEnBulto = cantidadDeBobinasQueNoEstanEnBulto + 1;
                }
        
                ordenDeProduccionBobinasDtos.add(ordenDeProduccionBobinaDto);
               

            }
        }
             
        model.addAttribute("displayButtonCambiarEstadoFabricacion", "none");        
        model.addAttribute("displayButtonCambiarEstadoEmpaque", "none");    
        model.addAttribute("displayButtonCambiarEstadoPallet", "none");    
        model.addAttribute("displayButtonCambiarEstadoCompletado", "none");    
        
        model.addAttribute("estaEnBulto", ordenDeProduccionBobina.getEstaEnBulto());
        model.addAttribute("tipoDetalle", "bobina");
        model.addAttribute("idOrdenDeProduccion", ordenDeProduccion.getId().toString());        
        model.addAttribute("clienteLabel", cliente.getRazonSocial());
        model.addAttribute("idArticulo", articulo.getId());
        model.addAttribute("articuloLabel", articulo.getDenominacion());
        model.addAttribute("fichaTecnicaVersionLabel", articuloFichaTecnica.getVersion());
        
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "view");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("displayActionButtonScrap", displayActionButtonScrap);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("ordenDeProduccionBobinas", ordenDeProduccionBobinasDtos);
        
        model.addAttribute("cantidadDeBobinasQueNoEstanEnBulto", this.cantidadDeBobinasQueNoEstanEnBulto);
        model.addAttribute("cantidadDeBultosQueNoEstanEnPallet", this.cantidadDeBultosQueNoEstanEnPallet);
        model.addAttribute("cantidadPallet", this.cantidadPallet);
        model.addAttribute("cantidadBobinasEnProduccion", this.cantidadBobinasEnProduccion);
            
                                                                                                              
        return "/ordendeproduccion/ordendeproducciondetalle";     
    }
        
    @RequestMapping(value = "/ordenDeProduccionDetalle/setStatusOpenOrdenProduccion/{ordenDeProduccionPk}", method = RequestMethod.GET)
    public String setStatusOpenOrdenProduccion(@PathVariable String ordenDeProduccionPk, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeProduccionPk == null || ordenDeProduccionPk.isEmpty()) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido");         
            return "/error";                
        }
        
        if(user.getRol() != Utils.ROL_OFICINA) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
                
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();        
        OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(Integer.valueOf(ordenDeProduccionPk));

        if(ordenDeProduccion == null) {            
            model.addAttribute("errorMessage", "Error: orden de compra no encontrada");
            return "/error";
        }
        
        if(!ordenDeProduccion.getEstado().equalsIgnoreCase("Nuevo")) {            
            model.addAttribute("errorMessage", "Error: no es posible cambiar estado abierto a orden de compra con estado "+ordenDeProduccion.getEstado());
            return "/error";
        }            
                
        ordenDeProduccion.setEstado("Abierto");
        ordenDeProduccion.setFechaAbierta(new Date());
        ordenDeProduccion.setIdUsuarioAbierta(user.getId());        
        
        ordenDeProduccionService.save(ordenDeProduccion);
        
        return "redirect:/ordenDeProduccion";                         
        
    }       
    
@RequestMapping(value = "/ordenDeProduccionDetalle/setStatusFabricacionOrdenProduccion/{ordenDeProduccionPk}", method = RequestMethod.GET)
    public String setStatusFabricacionOrdenProduccion(@PathVariable String ordenDeProduccionPk, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeProduccionPk == null || ordenDeProduccionPk.isEmpty()) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido");         
            return "/error";                
        }
        
        if(user.getRol() != Utils.ROL_PLANTA) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
                
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();        
        OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(Integer.valueOf(ordenDeProduccionPk));

        if(ordenDeProduccion == null) {            
            model.addAttribute("errorMessage", "Error: orden de compra no encontrada");
            return "/error";
        }
        
        if(!ordenDeProduccion.getEstado().equalsIgnoreCase("Abierto")) {            
            model.addAttribute("errorMessage", "Error: no es posible cambiar estado abierto a orden de compra con estado "+ordenDeProduccion.getEstado());
            return "/error";
        }            
                
        ordenDeProduccion.setEstado("Fabricacion");
        ordenDeProduccion.setFechaFabricacion(new Date());
        ordenDeProduccion.setIdUsuarioFabricacion(user.getId());
        
        ordenDeProduccionService.save(ordenDeProduccion);
        
        return "redirect:/ordenDeProduccionDetalle/"+ordenDeProduccionPk;                         
        
    }       
        
    @RequestMapping(value = "/ordenDeProduccionDetalle/setStatusEmpaqueOrdenProduccion/{ordenDeProduccionPk}", method = RequestMethod.GET)
    public String setStatusEmpaqueOrdenProduccion(@PathVariable String ordenDeProduccionPk, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeProduccionPk == null || ordenDeProduccionPk.isEmpty()) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido");         
            return "/error";                
        }
        
        if(user.getRol() != Utils.ROL_PLANTA) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
                
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();        
        OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(Integer.valueOf(ordenDeProduccionPk));

        if(ordenDeProduccion == null) {            
            model.addAttribute("errorMessage", "Error: orden de compra no encontrada");
            return "/error";
        }
        
        if(!ordenDeProduccion.getEstado().equalsIgnoreCase("Fabricacion")) {            
            model.addAttribute("errorMessage", "Error: no es posible cambiar estado abierto a orden de compra con estado "+ordenDeProduccion.getEstado());
            return "/error";
        }            
                
        ordenDeProduccion.setEstado("Empaque");
        ordenDeProduccion.setFechaEmpaque(new Date());
        ordenDeProduccion.setIdUsuarioEmpaque(user.getId());
        
        ordenDeProduccionService.save(ordenDeProduccion);
        
        return "redirect:/ordenDeProduccionDetalle/"+ordenDeProduccionPk;                         
        
    }       

    @RequestMapping(value = "/ordenDeProduccionDetalle/setStatusPalletOrdenProduccion/{ordenDeProduccionPk}", method = RequestMethod.GET)
    public String setStatusPalletOrdenProduccion(@PathVariable String ordenDeProduccionPk, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeProduccionPk == null || ordenDeProduccionPk.isEmpty()) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido");         
            return "/error";                
        }
        
        if(user.getRol() != Utils.ROL_PLANTA) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
                
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();        
        OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(Integer.valueOf(ordenDeProduccionPk));

        if(ordenDeProduccion == null) {            
            model.addAttribute("errorMessage", "Error: orden de compra no encontrada");
            return "/error";
        }
        
        if(!ordenDeProduccion.getEstado().equalsIgnoreCase("Empaque")) {            
            model.addAttribute("errorMessage", "Error: no es posible cambiar estado abierto a orden de compra con estado "+ordenDeProduccion.getEstado());
            return "/error";
        }            
                
        ordenDeProduccion.setEstado("Pallet");
        ordenDeProduccion.setFechaPallet(new Date());
        ordenDeProduccion.setIdUsuarioPallet(user.getId());
        
        ordenDeProduccionService.save(ordenDeProduccion);
        
        return "redirect:/ordenDeProduccionDetalle/"+ordenDeProduccionPk;                         
        
    }       
    
    @RequestMapping(value = "/ordenDeProduccionDetalle/setStatusCompletadoOrdenProduccion/{ordenDeProduccionPk}", method = RequestMethod.GET)
    public String setStatusCompletadoOrdenProduccion(@PathVariable String ordenDeProduccionPk, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeProduccionPk == null || ordenDeProduccionPk.isEmpty()) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido");         
            return "/error";                
        }
        
        if(user.getRol() != Utils.ROL_PLANTA) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
                
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();        
        OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(Integer.valueOf(ordenDeProduccionPk));

        if(ordenDeProduccion == null) {            
            model.addAttribute("errorMessage", "Error: orden de compra no encontrada");
            return "/error";
        }
        
        if(!ordenDeProduccion.getEstado().equalsIgnoreCase("Pallet")) {            
            model.addAttribute("errorMessage", "Error: no es posible cambiar estado abierto a orden de compra con estado "+ordenDeProduccion.getEstado());
            return "/error";
        }            
                
        ordenDeProduccion.setEstado("Completado");
        ordenDeProduccion.setFechaCierre(new Date());
        ordenDeProduccion.setIdUsuarioCierre(user.getId());
        //ordenDeProduccion.setStockActual(ordenDeProduccion.getCantidadAProducir());
        
        ordenDeProduccionService.save(ordenDeProduccion);
        
        //Se calcula stock de articulo
        ArticuloService articuloService = new ArticuloServiceImpl();
        ArticuloModel articulo =  articuloService.getByPk(ordenDeProduccion.getIdArticulo());
        if(articulo != null && ordenDeProduccion.getCantidadAProducir() != null) {
            Integer actualStock = articulo.getStock();
            Double actualStockPeso = articulo.getStockPeso();
            articulo.setStock(actualStock + ordenDeProduccion.getCantidadAProducir());
            articulo.setStockPeso(actualStockPeso + ordenDeProduccion.getCantidadAProducir());
            
            articuloService.save(articulo);
        }
        
        //Se calcula stock de materia prima
        Map<Integer, Double> porcentajes = new HashMap<Integer, Double>();
        ArticuloFichaTecnicaService articuloFichaTecnicaService = new ArticuloFichaTecnicaServiceImpl();
        ArticuloFichaTecnicaModel fichaTecnica = articuloFichaTecnicaService.getByPk(ordenDeProduccion.getIdFichaTecnica());        
        if(fichaTecnica != null) {
            //CAPA A
            if(fichaTecnica.getIdMateriaPrima1CapaA() != null && fichaTecnica.getMateriaPrimaPorcentage1CapaA() != null && fichaTecnica.getResumenCapaA() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima1CapaA());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage1CapaA() * fichaTecnica.getResumenCapaA() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima1CapaA(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima2CapaA() != null && fichaTecnica.getMateriaPrimaPorcentage2CapaA() != null && fichaTecnica.getResumenCapaA() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima2CapaA());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage2CapaA() * fichaTecnica.getResumenCapaA() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima2CapaA(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima3CapaA() != null && fichaTecnica.getMateriaPrimaPorcentage3CapaA() != null && fichaTecnica.getResumenCapaA() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima3CapaA());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage3CapaA() * fichaTecnica.getResumenCapaA() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima3CapaA(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima4CapaA() != null && fichaTecnica.getMateriaPrimaPorcentage4CapaA() != null && fichaTecnica.getResumenCapaA() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima4CapaA());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage4CapaA() * fichaTecnica.getResumenCapaA() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima4CapaA(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima5CapaA() != null && fichaTecnica.getMateriaPrimaPorcentage5CapaA() != null && fichaTecnica.getResumenCapaA() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima5CapaA());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage5CapaA() * fichaTecnica.getResumenCapaA() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima5CapaA(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima6CapaA() != null && fichaTecnica.getMateriaPrimaPorcentage6CapaA() != null && fichaTecnica.getResumenCapaA() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima6CapaA());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage6CapaA() * fichaTecnica.getResumenCapaA() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima6CapaA(), newValue);
            }

            //CAPA B
            if(fichaTecnica.getIdMateriaPrima1CapaB() != null && fichaTecnica.getMateriaPrimaPorcentage1CapaB() != null && fichaTecnica.getResumenCapaB() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima1CapaB());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage1CapaB() * fichaTecnica.getResumenCapaB() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima1CapaB(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima2CapaB() != null && fichaTecnica.getMateriaPrimaPorcentage2CapaB() != null && fichaTecnica.getResumenCapaB() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima2CapaB());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage2CapaB() * fichaTecnica.getResumenCapaB() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima2CapaB(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima3CapaB() != null && fichaTecnica.getMateriaPrimaPorcentage3CapaB() != null && fichaTecnica.getResumenCapaB() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima3CapaB());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage3CapaB() * fichaTecnica.getResumenCapaB() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima3CapaB(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima4CapaB() != null && fichaTecnica.getMateriaPrimaPorcentage4CapaB() != null && fichaTecnica.getResumenCapaB() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima4CapaB());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage4CapaB() * fichaTecnica.getResumenCapaB() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima4CapaB(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima5CapaB() != null && fichaTecnica.getMateriaPrimaPorcentage5CapaB() != null && fichaTecnica.getResumenCapaB() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima5CapaB());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage5CapaB() * fichaTecnica.getResumenCapaB() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima5CapaB(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima6CapaB() != null && fichaTecnica.getMateriaPrimaPorcentage6CapaB() != null && fichaTecnica.getResumenCapaB() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima6CapaB());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage6CapaB() * fichaTecnica.getResumenCapaB() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima6CapaB(), newValue);
            }

            //CAPA C
             if(fichaTecnica.getIdMateriaPrima1CapaC() != null && fichaTecnica.getMateriaPrimaPorcentage1CapaC() != null && fichaTecnica.getResumenCapaC() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima1CapaC());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage1CapaC() * fichaTecnica.getResumenCapaC() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima1CapaC(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima2CapaC() != null && fichaTecnica.getMateriaPrimaPorcentage2CapaC() != null && fichaTecnica.getResumenCapaC() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima2CapaC());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage2CapaC() * fichaTecnica.getResumenCapaC() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima2CapaC(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima3CapaC() != null && fichaTecnica.getMateriaPrimaPorcentage3CapaC() != null && fichaTecnica.getResumenCapaC() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima3CapaC());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage3CapaC() * fichaTecnica.getResumenCapaC() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima3CapaC(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima4CapaC() != null && fichaTecnica.getMateriaPrimaPorcentage4CapaC() != null && fichaTecnica.getResumenCapaC() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima4CapaC());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage4CapaC() * fichaTecnica.getResumenCapaC() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima4CapaC(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima5CapaC() != null && fichaTecnica.getMateriaPrimaPorcentage5CapaC() != null && fichaTecnica.getResumenCapaC() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima5CapaC());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage5CapaC() * fichaTecnica.getResumenCapaC() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima5CapaC(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima6CapaC() != null && fichaTecnica.getMateriaPrimaPorcentage6CapaC() != null && fichaTecnica.getResumenCapaC() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima6CapaC());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage6CapaC() * fichaTecnica.getResumenCapaC() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima6CapaC(), newValue);
            }

            //CAPA D
            if(fichaTecnica.getIdMateriaPrima1CapaD() != null && fichaTecnica.getMateriaPrimaPorcentage1CapaD() != null && fichaTecnica.getResumenCapaD() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima1CapaD());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage1CapaD() * fichaTecnica.getResumenCapaD() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima1CapaD(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima2CapaD() != null && fichaTecnica.getMateriaPrimaPorcentage2CapaD() != null && fichaTecnica.getResumenCapaD() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima2CapaD());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage2CapaD() * fichaTecnica.getResumenCapaD() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima2CapaD(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima3CapaD() != null && fichaTecnica.getMateriaPrimaPorcentage3CapaD() != null && fichaTecnica.getResumenCapaD() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima3CapaD());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage3CapaD() * fichaTecnica.getResumenCapaD() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima3CapaD(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima4CapaD() != null && fichaTecnica.getMateriaPrimaPorcentage4CapaD() != null && fichaTecnica.getResumenCapaD() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima4CapaD());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage4CapaD() * fichaTecnica.getResumenCapaD() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima4CapaD(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima5CapaD() != null && fichaTecnica.getMateriaPrimaPorcentage5CapaD() != null && fichaTecnica.getResumenCapaD() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima5CapaD());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage5CapaD() * fichaTecnica.getResumenCapaD() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima5CapaD(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima6CapaD() != null && fichaTecnica.getMateriaPrimaPorcentage6CapaD() != null && fichaTecnica.getResumenCapaD() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima6CapaD());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage6CapaD() * fichaTecnica.getResumenCapaD() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima6CapaD(), newValue);
            }

            //CAPA E
            if(fichaTecnica.getIdMateriaPrima1CapaE() != null && fichaTecnica.getMateriaPrimaPorcentage1CapaE() != null && fichaTecnica.getResumenCapaE() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima1CapaE());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage1CapaE() * fichaTecnica.getResumenCapaE() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima1CapaE(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima2CapaE() != null && fichaTecnica.getMateriaPrimaPorcentage2CapaE() != null && fichaTecnica.getResumenCapaE() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima2CapaE());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage2CapaE() * fichaTecnica.getResumenCapaE() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima2CapaE(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima3CapaE() != null && fichaTecnica.getMateriaPrimaPorcentage3CapaE() != null && fichaTecnica.getResumenCapaE() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima3CapaE());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage3CapaE() * fichaTecnica.getResumenCapaE() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima3CapaE(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima4CapaE() != null && fichaTecnica.getMateriaPrimaPorcentage4CapaE() != null && fichaTecnica.getResumenCapaE() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima4CapaE());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage4CapaE() * fichaTecnica.getResumenCapaE() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima4CapaE(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima5CapaE() != null && fichaTecnica.getMateriaPrimaPorcentage5CapaE() != null && fichaTecnica.getResumenCapaE() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima5CapaE());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage5CapaE() * fichaTecnica.getResumenCapaE() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima5CapaE(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima6CapaE() != null && fichaTecnica.getMateriaPrimaPorcentage6CapaE() != null && fichaTecnica.getResumenCapaE() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima6CapaE());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage6CapaE() * fichaTecnica.getResumenCapaE() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima6CapaE(), newValue);
            }

            //CAPA F
            if(fichaTecnica.getIdMateriaPrima1CapaF() != null && fichaTecnica.getMateriaPrimaPorcentage1CapaF() != null && fichaTecnica.getResumenCapaF() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima1CapaF());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage1CapaF() * fichaTecnica.getResumenCapaF() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima1CapaF(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima2CapaF() != null && fichaTecnica.getMateriaPrimaPorcentage2CapaF() != null && fichaTecnica.getResumenCapaF() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima2CapaF());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage2CapaF() * fichaTecnica.getResumenCapaF() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima2CapaF(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima3CapaF() != null && fichaTecnica.getMateriaPrimaPorcentage3CapaF() != null && fichaTecnica.getResumenCapaF() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima3CapaF());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage3CapaF() * fichaTecnica.getResumenCapaF() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima3CapaF(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima4CapaF() != null && fichaTecnica.getMateriaPrimaPorcentage4CapaF() != null && fichaTecnica.getResumenCapaF() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima4CapaF());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage4CapaF() * fichaTecnica.getResumenCapaF() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima4CapaF(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima5CapaF() != null && fichaTecnica.getMateriaPrimaPorcentage5CapaF() != null && fichaTecnica.getResumenCapaF() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima5CapaF());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage5CapaF() * fichaTecnica.getResumenCapaF() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima5CapaF(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima6CapaF() != null && fichaTecnica.getMateriaPrimaPorcentage6CapaF() != null && fichaTecnica.getResumenCapaF() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima6CapaF());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage6CapaF() * fichaTecnica.getResumenCapaF() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima6CapaF(), newValue);
            }
            
            //CAPA G
            if(fichaTecnica.getIdMateriaPrima1CapaG() != null && fichaTecnica.getMateriaPrimaPorcentage1CapaG() != null && fichaTecnica.getResumenCapaG() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima1CapaG());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage1CapaG() * fichaTecnica.getResumenCapaG() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima1CapaG(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima2CapaG() != null && fichaTecnica.getMateriaPrimaPorcentage2CapaG() != null && fichaTecnica.getResumenCapaG() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima2CapaG());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage2CapaG() * fichaTecnica.getResumenCapaG() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima2CapaG(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima3CapaG() != null && fichaTecnica.getMateriaPrimaPorcentage3CapaG() != null && fichaTecnica.getResumenCapaG() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima3CapaG());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage3CapaG() * fichaTecnica.getResumenCapaG() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima3CapaG(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima4CapaG() != null && fichaTecnica.getMateriaPrimaPorcentage4CapaG() != null && fichaTecnica.getResumenCapaG() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima4CapaG());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage4CapaG() * fichaTecnica.getResumenCapaG() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima4CapaG(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima5CapaG() != null && fichaTecnica.getMateriaPrimaPorcentage5CapaG() != null && fichaTecnica.getResumenCapaG() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima5CapaG());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage5CapaG() * fichaTecnica.getResumenCapaG() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima5CapaG(), newValue);
            }
            if(fichaTecnica.getIdMateriaPrima6CapaG() != null && fichaTecnica.getMateriaPrimaPorcentage6CapaG() != null && fichaTecnica.getResumenCapaG() != null){
                Double value = porcentajes.get(fichaTecnica.getIdMateriaPrima6CapaG());
                if(value == null) {
                    value = 0.0;                    
                }
                Double newValue = (fichaTecnica.getMateriaPrimaPorcentage6CapaG() * fichaTecnica.getResumenCapaG() / 100) + value;
                porcentajes.put(fichaTecnica.getIdMateriaPrima6CapaG(), newValue);
            }            
         
            MateriaPrimaService materiaPrimaService = new MateriaPrimaServiceImpl();
            Double peso = fichaTecnica.getPeso();
            Integer cantidadProducida = ordenDeProduccion.getCantidadAProducir();
            for(Integer idMateriaPrima :porcentajes.keySet()) {
                Double porcentaje = porcentajes.get(idMateriaPrima);
                Double cantidad = peso * porcentaje / 100 * cantidadProducida;
                
                MateriaPrimaModel materiaPrima = materiaPrimaService.getByPk(idMateriaPrima);
                if(materiaPrima != null) {
                    Integer stock = 0;
                    Double stockPeso = 0.0;
                    if(materiaPrima.getStock() != null) {
                        stock = materiaPrima.getStock();
                    }
                    if(materiaPrima.getStockPeso()!= null) {
                        stockPeso = materiaPrima.getStockPeso();
                    }
                    stock = stock - cantidad.intValue();
                    stockPeso = stockPeso - cantidad;
                    
                    materiaPrima.setStock(stock);
                    materiaPrima.setStockPeso(stockPeso);
                    
                    materiaPrimaService.save(materiaPrima);
                }
            }
        }
        
        return "redirect:/ordenDeProduccionDetalle/"+ordenDeProduccionPk;                         
        
    }       
    
    @RequestMapping(value = "/ordenDeProduccionDetalle/print/bobina/{ordendeproduccionbobinapk}", method = RequestMethod.GET)
    public String printBobina(@PathVariable String ordendeproduccionbobinapk, HttpServletRequest req, ModelMap model) throws Exception {
                                
        String ordenDeProduccion = "Sin información";
        String fechaAltaOrdenDeProduccion = "Sin información";
        String cliente = "Sin información";
        String articulo = "Sin información";
        String etiquetaArticulo = "Sin información";
        String articuloCodigo = "Sin información";
        String fichaTecnica = "Sin información";
        String codigoBobina = "Sin información";
        String pesoTotal = "Sin información";
        String fechaAltaBobina = "Sin información";
        String fechaAltaLote = "Sin información";
        String fechaActual = "Sin información";
        String url = "http://localhost:8080/thyssenplastic/ordenDeProduccionDetalle/viewbobina/xxxx";
        
        Calendar c = Calendar.getInstance();        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        fechaActual = formato.format(c.getTime());
        
        if(ordendeproduccionbobinapk != null && !ordendeproduccionbobinapk.isEmpty()) {
            
            OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();   
            OrdenDeProduccionBobinaModel ordenDeProduccionBobina = ordenDeProduccionBobinaService.getByPk(Integer.valueOf(ordendeproduccionbobinapk));

            if(ordenDeProduccionBobina != null) {                
                codigoBobina = ordenDeProduccionBobina.getCodigo();
                pesoTotal = ordenDeProduccionBobina.getPesoTotal().toString();
                fechaAltaBobina = ordenDeProduccionBobina.getFechaAlta().toString();
                url = "https://www.thyssenplastic.com/index";
                
                OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();   
                OrdenDeProduccionModel ordenDeProduccionModel = ordenDeProduccionService.getByPk(Integer.valueOf(ordenDeProduccionBobina.getIdOrdenDeProduccion()));
                if(ordenDeProduccionModel != null) {

                    ordenDeProduccion = ordenDeProduccionModel.getId().toString();
                    fechaAltaOrdenDeProduccion = ordenDeProduccionModel.getFechaAlta().toString().replace("00:00:00.0", "");
                    
                    fechaAltaLote =formato.format(ordenDeProduccionModel.getFechaAltaImpresion());
                    
                    ClienteService clienteService = new ClienteServiceImpl();
                    ClienteModel clienteModel = clienteService.getByPk(ordenDeProduccionModel.getIdCliente());
                    if(clienteModel != null) {
                        cliente = clienteModel.getRazonSocial();
                    }

                    ArticuloService articuloService = new ArticuloServiceImpl();
                    ArticuloModel articuloModel = articuloService.getByPk(ordenDeProduccionModel.getIdArticulo());
                    if(articuloModel != null) {
                        articulo = articuloModel.getDenominacion();                        
                        etiquetaArticulo = articuloModel.getDenominacion();                        
                        articuloCodigo = articuloModel.getId().toString();
                    }

                    ArticuloFichaTecnicaService articuloFichaTecnicaService = new ArticuloFichaTecnicaServiceImpl();
                    ArticuloFichaTecnicaModel articuloFichaTecnica = articuloFichaTecnicaService.getByPk(ordenDeProduccionModel.getIdFichaTecnica());
                    if(articuloFichaTecnica != null) {
                        fichaTecnica = articuloFichaTecnica.getVersion().toString();
                    }
                    
                    ArticuloEtiquetaService articuloEtiquetaService = new ArticuloEtiquetaServiceImpl();
                    List<ArticuloEtiquetaModel> articulosEtiquetaModel = articuloEtiquetaService.getAllByArticulo(articuloModel.getId());
                    if(articulosEtiquetaModel != null && !articulosEtiquetaModel.isEmpty()) {
                        ArticuloEtiquetaModel articuloEtiquetaModel = articulosEtiquetaModel.get(0);
                        if(articuloEtiquetaModel.getLinea1() != null && !articuloEtiquetaModel.getLinea1().isEmpty()) {
                            etiquetaArticulo = articuloEtiquetaModel.getLinea1();
                        } 
                    }
                    
                }
            }
        } 
        
        model.addAttribute("ordenDeProduccion", ordenDeProduccion);
        model.addAttribute("fechaAltaOrdenDeProduccion", fechaAltaOrdenDeProduccion);
        model.addAttribute("cliente", cliente);
        model.addAttribute("articulo", articulo);
        model.addAttribute("etiquetaArticulo", etiquetaArticulo);
        model.addAttribute("articuloCodigo", articuloCodigo);
        model.addAttribute("fichaTecnica", fichaTecnica);
        model.addAttribute("codigoBobina", codigoBobina);
        model.addAttribute("pesoTotal", pesoTotal);        
        model.addAttribute("fechaAltaBobina", fechaAltaBobina);        
        model.addAttribute("fechaAltaLote", fechaAltaLote);        
        model.addAttribute("fechaActual", fechaActual);                
        model.addAttribute("url", url);
        
        return "/ordendeproduccion/ordendeproduccionbobinaprint"; 
    }    
    
    
    @ResponseBody        
    @RequestMapping(value = "/ordenDeProduccionDetalle/setBobinaSelected/{idOrdenProduccion}/{bobinaCode}/{idBobina}", method = RequestMethod.GET)
    public List<OrdenDeProduccionBobinaSelectedDto> setBobinaSelected(@PathVariable String idOrdenProduccion, @PathVariable String bobinaCode, @PathVariable String idBobina, HttpServletRequest req, ModelMap model) throws Exception {
        
        List<OrdenDeProduccionBobinaSelectedDto> response = new ArrayList<OrdenDeProduccionBobinaSelectedDto>();
        
        OrdenDeProduccionBobinaSelectedDto dto = new OrdenDeProduccionBobinaSelectedDto();
        dto.setError("NOK");
        dto.setErrorMessage("Bobina no encontrada.");
        
        if(idOrdenProduccion != null && !idOrdenProduccion.isEmpty()) {
            
            OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl(); 
            OrdenDeProduccionModel ordenDeProduccionModel = ordenDeProduccionService.getByPk(Integer.valueOf(idOrdenProduccion));
            if(ordenDeProduccionModel != null) {
        
                if(bobinaCode != null && !bobinaCode.isEmpty() && !bobinaCode.equalsIgnoreCase("-1")) {

                    OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();   
                    OrdenDeProduccionBobinaModel ordenDeProduccionBobina = ordenDeProduccionBobinaService.getByCode(bobinaCode);

                    if(ordenDeProduccionBobina != null && ordenDeProduccionBobina.getIdOrdenDeProduccion() == ordenDeProduccionModel.getId()) {                
                        dto.setError("OK");
                        dto.setPk(ordenDeProduccionBobina.getId().toString());
                        dto.setCodigo(ordenDeProduccionBobina.getCodigo());
                        dto.setPesoTotal(ordenDeProduccionBobina.getPesoTotal().toString());
                        dto.setPesoNeto(ordenDeProduccionBobina.getPesoNeto().toString());
                        if(ordenDeProduccionBobina.getEstado() != null && !ordenDeProduccionBobina.getEstado().equalsIgnoreCase("-1")) {                    
                            if(ordenDeProduccionBobina.getEstado().equals("ok")) {
                                dto.setEstado("OK");
                            }
                            if(ordenDeProduccionBobina.getEstado().equals("observado")) {
                                dto.setEstado("Observado");
                            }
                            if(ordenDeProduccionBobina.getEstado().equals("rechazado")) {
                                dto.setEstado("Rechazado");
                            }
                            if(ordenDeProduccionBobina.getEstado().equals("sinmesurar")) {
                                dto.setEstado("Sin Mesurar");
                            }                
                        }                
                    }
                } else {
                    OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();   
                    OrdenDeProduccionBobinaModel ordenDeProduccionBobina = ordenDeProduccionBobinaService.getByPk(Integer.valueOf(idBobina));

                    if(ordenDeProduccionBobina != null && ordenDeProduccionBobina.getIdOrdenDeProduccion() == ordenDeProduccionModel.getId()) {                
                        dto.setError("OK");
                        dto.setPk(ordenDeProduccionBobina.getId().toString());
                        dto.setCodigo(ordenDeProduccionBobina.getCodigo());
                        dto.setPesoTotal(ordenDeProduccionBobina.getPesoTotal().toString());
                        dto.setPesoNeto(ordenDeProduccionBobina.getPesoNeto().toString());
                        if(ordenDeProduccionBobina.getEstado() != null && !ordenDeProduccionBobina.getEstado().equalsIgnoreCase("-1")) {                    
                            if(ordenDeProduccionBobina.getEstado().equals("ok")) {
                                dto.setEstado("OK");
                            }
                            if(ordenDeProduccionBobina.getEstado().equals("observado")) {
                                dto.setEstado("Observado");
                            }
                            if(ordenDeProduccionBobina.getEstado().equals("rechazado")) {
                                dto.setEstado("Rechazado");
                            }
                            if(ordenDeProduccionBobina.getEstado().equals("sinmesurar")) {
                                dto.setEstado("Sin Mesurar");
                            }                
                        }                
                    }            
                }
            }
        }
        response.add(dto);
        
        return response; 
    }
  
    @RequestMapping(value = "/ordenDeProduccionDetalle/editbulto/{ordenDeProduccionBultoPk}", method = RequestMethod.GET)
    public String editOrdenDeProduccionBulto(@PathVariable String ordenDeProduccionBultoPk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeProduccionBultoPk == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido");         
            return "/error";                
        }
        
        String operacion = "edit";                
        String displayActionButton = "block";
        String displaySearchBobinaButton = "none";

        OrdenDeProduccionBultoService ordenDeProduccionBultoService = new OrdenDeProduccionBultoServiceImpl();   
        OrdenDeProduccionBultoModel ordenDeProduccionBulto = ordenDeProduccionBultoService.getByPk(Integer.valueOf(ordenDeProduccionBultoPk));
        if(ordenDeProduccionBulto == null) {
            model.addAttribute("errorMessage", "Error: Orden De Produccion Bulto inválido. No ha sido encontrado.");         
            return "/error";    
        }
        
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();   
        OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(Integer.valueOf(ordenDeProduccionBulto.getIdOrdenDeProduccion()));
        if(ordenDeProduccion == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido. No ha sido encontrado.");         
            return "/error";    
        }
                 
        if(ordenDeProduccion.getEstado().equalsIgnoreCase("Abierto")) {
            model.addAttribute("errorMessage", "Error: estado de orden incorrecto.");         
            return "/error";                            
        }
        
        ClienteService clienteService = new ClienteServiceImpl();
        ClienteModel cliente = clienteService.getByPk(ordenDeProduccion.getIdCliente());
        if(cliente == null) {
            model.addAttribute("errorMessage", "Error: cliente no encontrado");         
            return "/error";                            
        }

        ArticuloService articuloService = new ArticuloServiceImpl();
        ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
        if(articulo == null) {
            model.addAttribute("errorMessage", "Error: articulo no encontrado");         
            return "/error";                            
        }
        
        ArticuloFichaTecnicaService articuloFichaTecnicaService = new ArticuloFichaTecnicaServiceImpl();
        ArticuloFichaTecnicaModel articuloFichaTecnica = articuloFichaTecnicaService.getByPk(ordenDeProduccion.getIdFichaTecnica());
        if(articuloFichaTecnica == null) {
            model.addAttribute("errorMessage", "Error: ficha tecnica no encontrada");         
            return "/error";                            
        }
        
        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(user.getRol() != Utils.ROL_PLANTA && user.getRol() != Utils.ROL_OFICINA ) {
            model.addAttribute("errorMessage", "Error: usuario no permite esta operación.");         
            return "/error";                                                                
        }
        
        String rol = "";
        if(user.getRol() == Utils.ROL_PLANTA) {
            rol = "planta";
        }

        
        OrdenDeProduccionDetalleForm ordenDeProduccionDetalleForm = new OrdenDeProduccionDetalleForm();
        ordenDeProduccionDetalleForm.setPk(ordenDeProduccionBulto.getId().toString());
        ordenDeProduccionDetalleForm.setAction("edit");        
        ordenDeProduccionDetalleForm.setOperacion("edit");   
        ordenDeProduccionDetalleForm.setTipoDetalle("bulto");
        ordenDeProduccionDetalleForm.setImprimir("false");
        
        ordenDeProduccionDetalleForm.setFechaAltaOrdenDeProduccion(ordenDeProduccion.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        ordenDeProduccionDetalleForm.setEstadoOrdenDeProduccion(ordenDeProduccion.getEstado());
        ordenDeProduccionDetalleForm.setIdOrdenProduccion(ordenDeProduccion.getId().toString());
        ordenDeProduccionDetalleForm.setIdCliente(ordenDeProduccion.getIdCliente().toString());
        ordenDeProduccionDetalleForm.setIdArticulo(ordenDeProduccion.getIdArticulo().toString());
        ordenDeProduccionDetalleForm.setIdFichaTecnica(ordenDeProduccion.getIdFichaTecnica().toString());
        ordenDeProduccionDetalleForm.setPesoTecnicoArticulo(articuloFichaTecnica.getPeso().toString());
        ordenDeProduccionDetalleForm.setMetrosArticulo(articuloFichaTecnica.getMetros().toString());
        ordenDeProduccionDetalleForm.setAltoArticulo(articulo.getAlto().toString());
        ordenDeProduccionDetalleForm.setEspesorArticulo(articulo.getEspesor().toString());
        ordenDeProduccionDetalleForm.setAnchoArticulo(articulo.getAncho().toString());

        if(ordenDeProduccionBulto.getIdPlegadora() != null) {
            ordenDeProduccionDetalleForm.setIdPlegadora(ordenDeProduccionBulto.getIdPlegadora().toString());
        }
        if(ordenDeProduccionBulto.getEstado() != null) {
            ordenDeProduccionDetalleForm.setEstadoBulto(ordenDeProduccionBulto.getEstado().toString());
        }
        if(ordenDeProduccionBulto.getObservaciones() != null) {
            ordenDeProduccionDetalleForm.setObservacionesBulto(ordenDeProduccionBulto.getObservaciones().toString());
        }
        if(ordenDeProduccionBulto.getIdOrdenDeProduccionBobina() != null) {
            ordenDeProduccionDetalleForm.setIdBobinaSelected(ordenDeProduccionBulto.getIdOrdenDeProduccionBobina().toString());   
        }        
        ordenDeProduccionDetalleForm.setCodigoBultoLabel(ordenDeProduccionBulto.getCodigo());
        
        OrdenDeProduccionBobinaService ordenDeProduccionBobinaService2 = new OrdenDeProduccionBobinaServiceImpl();
        OrdenDeProduccionBobinaModel ordenDeProduccionBobina = ordenDeProduccionBobinaService2.getByPk(ordenDeProduccionBulto.getIdOrdenDeProduccionBobina());
        
        ordenDeProduccionDetalleForm.setPesoTotalBobinaBulto(ordenDeProduccionBobina.getPesoTotal().toString());
        //
        model.addAttribute("ordenDeProduccionDetalleForm", ordenDeProduccionDetalleForm);  
        model.addAttribute("titleOrdenDeProduccion", "Editar Orden de Producción Bulto");
        model.addAttribute("buttonLabel", "Guardar");        
        model.addAttribute("ordenDeProduccionBultoName", "Editando Bulto " + ordenDeProduccionBulto.getCodigo());        
        model.addAttribute("buttonSearchBobinaLabel", "Buscar");
        
        TipoService tipoService = new TipoServiceImpl();
        Map<String,String> plegadoraList = new LinkedHashMap<String,String>();
        List<TipoModel> plegadorasModel = tipoService.getByType("plegadoraBobina");       
        if(plegadorasModel != null && !plegadorasModel.isEmpty()){
            for(TipoModel tipoModel :plegadorasModel) {
                plegadoraList.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        
        Map<String,String> origenScrapList = new LinkedHashMap<String,String>();
        List<TipoModel> origenScrapModel = tipoService.getByType("origenScrap");       
        if(origenScrapModel != null && !origenScrapModel.isEmpty()){
            for(TipoModel tipoModel :origenScrapModel) {
                origenScrapList.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        Map<String,String> tipoMaterialScrapList = new LinkedHashMap<String,String>();
        List<TipoModel> tipoMaterialScrapModel = tipoService.getByType("tipoMaterialScrap");       
        if(tipoMaterialScrapModel != null && !tipoMaterialScrapModel.isEmpty()){
            for(TipoModel tipoModel :tipoMaterialScrapModel) {
                tipoMaterialScrapList.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        Map<String,String> motivoScrapList = new LinkedHashMap<String,String>();
        List<TipoModel> motivoScrapModel = tipoService.getByType("motivoScrap");       
        if(motivoScrapModel != null && !motivoScrapModel.isEmpty()){
            for(TipoModel tipoModel :motivoScrapModel) {
                motivoScrapList.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        Map<String,String> formatoScrapList = new LinkedHashMap<String,String>();
        List<TipoModel> formatoScrapModel = tipoService.getByType("formatoScrap");       
        if(formatoScrapModel != null && !formatoScrapModel.isEmpty()){
            for(TipoModel tipoModel :formatoScrapModel) {
                formatoScrapList.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        
        OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();
        List<OrdenDeProduccionBultoModel> ordenDeProduccionBultosModel = ordenDeProduccionBultoService.getAllByOrdenDeProduccion(ordenDeProduccion.getId());       
        List<OrdenDeProduccionBultoDto> ordenDeProduccionBultosDtos = new ArrayList<OrdenDeProduccionBultoDto>();
                       
        if(ordenDeProduccionBultosModel != null && !ordenDeProduccionBultosModel.isEmpty()) {
            for(OrdenDeProduccionBultoModel ordenDeProduccionBultoModel: ordenDeProduccionBultosModel) {
                OrdenDeProduccionBultoDto ordenDeProduccionBultoDto = new OrdenDeProduccionBultoDto();
                ordenDeProduccionBultoDto.setPk(ordenDeProduccionBultoModel.getId().toString());
                ordenDeProduccionBultoDto.setFechaAlta(ordenDeProduccionBultoModel.getFechaAlta().toString());
                if(ordenDeProduccionBultoModel.getEstado() != null && !ordenDeProduccionBultoModel.getEstado().equalsIgnoreCase("-1")) {
                    ordenDeProduccionBultoDto.setEstado(ordenDeProduccionBultoModel.getEstado());
                    if(ordenDeProduccionBultoModel.getEstado().equals("ok")) {
                        ordenDeProduccionBultoDto.setEstadoLabel("OK");
                    }
                    if(ordenDeProduccionBultoModel.getEstado().equals("observado")) {
                        ordenDeProduccionBultoDto.setEstadoLabel("Observado");
                    }
                    if(ordenDeProduccionBultoModel.getEstado().equals("rechazado")) {
                        ordenDeProduccionBultoDto.setEstadoLabel("Rechazado");
                    }
                    if(ordenDeProduccionBultoModel.getEstado().equals("sinmesurar")) {
                        ordenDeProduccionBultoDto.setEstadoLabel("Sin Mesurar");
                    }                
                }
                ordenDeProduccionBultoDto.setCodigo(ordenDeProduccionBultoModel.getCodigo());
                ordenDeProduccionBultoDto.setEstaEnPallet(ordenDeProduccionBultoModel.getEstaEnPallet().toString());                
                if(ordenDeProduccionBultoModel.getEstaEnPallet()) {
                    ordenDeProduccionBultoDto.setEstaEnPalletLabel("Si");                
                } else {
                    ordenDeProduccionBultoDto.setEstaEnPalletLabel("No");                
                }

                String plegadora = "";
                if(ordenDeProduccionBultoModel.getIdPlegadora() != null) {
                    TipoModel plegadoraModel = tipoService.getByPk(ordenDeProduccionBultoModel.getIdPlegadora());
                    if(plegadora != null) {
                        plegadora = plegadoraModel.getValor();
                    }
                }        
                ordenDeProduccionBultoDto.setPlegadora(plegadora);
                
                OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(ordenDeProduccionBultoModel.getIdOrdenDeProduccionBobina());
                if(bobina != null) {
                    ordenDeProduccionBultoDto.setPesoTotal(bobina.getPesoTotal().toString());
                    ordenDeProduccionBultoDto.setPesoNeto(bobina.getPesoNeto().toString());
                }
                
                if (ordenDeProduccionBultoModel.getIdOrdenDeProduccionBobina() != null) {
                    ordenDeProduccionBultoDto.setIdBobina(ordenDeProduccionBultoModel.getIdOrdenDeProduccionBobina().toString());
                }

                ordenDeProduccionBultosDtos.add(ordenDeProduccionBultoDto);

            }
        }

        OrdenDeProduccionPalletBultoService ordenDeProduccionPalletBultoService = new OrdenDeProduccionPalletBultoServiceImpl();
        OrdenDeProduccionPalletService ordenDeProduccionPalletService = new OrdenDeProduccionPalletServiceImpl();
        List<OrdenDeProduccionPalletModel> ordenDeProduccionPalletsModel = ordenDeProduccionPalletService.getAllByOrdenDeProduccion(ordenDeProduccion.getId());       
        List<OrdenDeProduccionPalletDto> ordenDeProduccionPalletsDtos = new ArrayList<OrdenDeProduccionPalletDto>();
        
        if(ordenDeProduccionPalletsModel != null && !ordenDeProduccionPalletsModel.isEmpty()) {
            for(OrdenDeProduccionPalletModel ordenDeProduccionPalletModel: ordenDeProduccionPalletsModel) {
                OrdenDeProduccionPalletDto ordenDeProduccionPalletDto = new OrdenDeProduccionPalletDto();
                ordenDeProduccionPalletDto.setPk(ordenDeProduccionPalletModel.getId().toString());
                ordenDeProduccionPalletDto.setFechaAlta(ordenDeProduccionPalletModel.getFechaAlta().toString());
                if(ordenDeProduccionPalletModel.getEstado() != null && !ordenDeProduccionPalletModel.getEstado().equalsIgnoreCase("-1")) {
                    ordenDeProduccionPalletDto.setEstado(ordenDeProduccionPalletModel.getEstado());
                    if(ordenDeProduccionPalletModel.getEstado().equals("ok")) {
                        ordenDeProduccionPalletDto.setEstadoLabel("OK");
                    }
                    if(ordenDeProduccionPalletModel.getEstado().equals("observado")) {
                        ordenDeProduccionPalletDto.setEstadoLabel("Observado");
                    }
                    if(ordenDeProduccionPalletModel.getEstado().equals("rechazado")) {
                        ordenDeProduccionPalletDto.setEstadoLabel("Rechazado");
                    }
                    if(ordenDeProduccionPalletModel.getEstado().equals("sinmesurar")) {
                        ordenDeProduccionPalletDto.setEstadoLabel("Sin Mesurar");
                    }                
                }
                ordenDeProduccionPalletDto.setCodigo(ordenDeProduccionPalletModel.getCodigo());

                List<OrdenDeProduccionPalletBultoModel> palletbultoList = ordenDeProduccionPalletBultoService.getAllByOrdenDeProduccionPallet(ordenDeProduccionPalletModel.getId());
                ordenDeProduccionPalletDto.setCantidadBultos(String.valueOf(palletbultoList.size()));
                
                String listaCodigos = "";
               for(OrdenDeProduccionPalletBultoModel item :palletbultoList) {
                OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(item.getIdOrdenDeProduccionBulto());
                if(bulto != null) {
                 listaCodigos += bulto.getCodigo() + " ";
                }
               }
               ordenDeProduccionPalletDto.setListaCodigoBultos(listaCodigos);

                Double pesoTotal = 0.0;
                if(!palletbultoList.isEmpty()) {
                    for(OrdenDeProduccionPalletBultoModel palletbulto: palletbultoList) {
                        OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(palletbulto.getIdOrdenDeProduccionBulto());
                        if(bulto != null) {
                            OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(bulto.getIdOrdenDeProduccionBobina());
                            if(bobina != null) {
                                pesoTotal += bobina.getPesoTotal();
                            }
                        }
                    }
                }
                ordenDeProduccionPalletDto.setPesoTotal(pesoTotal.toString());

                ordenDeProduccionPalletsDtos.add(ordenDeProduccionPalletDto);
                System.out.println("*** ordenDeProduccionPalletsDtos size:"+ordenDeProduccionPalletsDtos.size());
            }
        }
        
        model.addAttribute("displayButtonCambiarEstadoFabricacion", "none");        
        model.addAttribute("displayButtonCambiarEstadoEmpaque", "none");    
        model.addAttribute("displayButtonCambiarEstadoPallet", "none");    
        model.addAttribute("displayButtonCambiarEstadoCompletado", "none");    
                
        model.addAttribute("estaEnPallet", ordenDeProduccionBulto.getEstaEnPallet());
        model.addAttribute("tipoDetalle", "bulto");
        model.addAttribute("idOrdenDeProduccion", ordenDeProduccion.getId().toString());        
        model.addAttribute("clienteLabel", cliente.getRazonSocial());
        model.addAttribute("idArticulo", articulo.getId());
        model.addAttribute("articuloLabel", articulo.getDenominacion());
        model.addAttribute("fichaTecnicaVersionLabel", articuloFichaTecnica.getVersion());
        
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "edit");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("displaySearchBobinaButton", displaySearchBobinaButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("ordenDeProduccionBultos", ordenDeProduccionBultosDtos);      
        model.addAttribute("ordenDeProduccionPallets", ordenDeProduccionPalletsDtos);
        model.addAttribute("plegadoraList", plegadoraList);
        model.addAttribute("origenScrapList", origenScrapList);
        model.addAttribute("tipoMaterialScrapList", tipoMaterialScrapList);
        model.addAttribute("motivoScrapList", motivoScrapList);
        model.addAttribute("formatoScrapList", formatoScrapList);
        
        model.addAttribute("cantidadDeBobinasQueNoEstanEnBulto", this.cantidadDeBobinasQueNoEstanEnBulto);
        model.addAttribute("cantidadDeBultosQueNoEstanEnPallet", this.cantidadDeBultosQueNoEstanEnPallet);
        model.addAttribute("cantidadPallet", this.cantidadPallet);
        model.addAttribute("cantidadBobinasEnProduccion", this.cantidadBobinasEnProduccion);
        
        return "/ordendeproduccion/ordendeproducciondetalle";
    }    
    
    @RequestMapping(value = "/ordenDeProduccionDetalle/viewbulto/{ordenDeProduccionBultoPk}", method = RequestMethod.GET)
    public String viewOrdenDeProduccionBulto(@PathVariable String ordenDeProduccionBultoPk, HttpServletRequest req, ModelMap model) throws Exception {
        
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeProduccionBultoPk == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido");         
            return "/error";                
        }
        
        String operacion = "view";                
        String displayActionButton = "none";
        String displaySearchBobinaButton = "none";

        OrdenDeProduccionBultoService ordenDeProduccionBultoService = new OrdenDeProduccionBultoServiceImpl();   
        OrdenDeProduccionBultoModel ordenDeProduccionBulto = ordenDeProduccionBultoService.getByPk(Integer.valueOf(ordenDeProduccionBultoPk));
        if(ordenDeProduccionBulto == null) {
            model.addAttribute("errorMessage", "Error: Orden De Produccion Bulto inválido. No ha sido encontrado.");         
            return "/error";    
        }
        
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();   
        OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(Integer.valueOf(ordenDeProduccionBulto.getIdOrdenDeProduccion()));
        if(ordenDeProduccion == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido. No ha sido encontrado.");         
            return "/error";    
        }
         
        /*
        if(!ordenDeProduccion.getEstado().equalsIgnoreCase("Abierto") && !ordenDeProduccion.getEstado().equalsIgnoreCase("Fabricacion")) {
            model.addAttribute("errorMessage", "Error: estado de orden incorrecto.");         
            return "/error";                            
        }
        */
        
        ClienteService clienteService = new ClienteServiceImpl();
        ClienteModel cliente = clienteService.getByPk(ordenDeProduccion.getIdCliente());
        if(cliente == null) {
            model.addAttribute("errorMessage", "Error: cliente no encontrado");         
            return "/error";                            
        }

        ArticuloService articuloService = new ArticuloServiceImpl();
        ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
        if(articulo == null) {
            model.addAttribute("errorMessage", "Error: articulo no encontrado");         
            return "/error";                            
        }
        
        ArticuloFichaTecnicaService articuloFichaTecnicaService = new ArticuloFichaTecnicaServiceImpl();
        ArticuloFichaTecnicaModel articuloFichaTecnica = articuloFichaTecnicaService.getByPk(ordenDeProduccion.getIdFichaTecnica());
        if(articuloFichaTecnica == null) {
            model.addAttribute("errorMessage", "Error: ficha tecnica no encontrada");         
            return "/error";                            
        }
        
        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(user.getRol() != Utils.ROL_OFICINA && user.getRol() != Utils.ROL_PLANTA && user.getRol() != Utils.ROL_DEPOSITO) {
            model.addAttribute("errorMessage", "Error: usuario no permite esta operación.");         
            return "/error";                                                                
        }
        
        String rol = "";
        if(user.getRol() == Utils.ROL_PLANTA) {
            rol = "planta";
        }
        if(user.getRol() == Utils.ROL_OFICINA) {
            rol = "oficina";
        }
        if(user.getRol() == Utils.ROL_DEPOSITO) {
            rol = "deposito";
        }

        
        OrdenDeProduccionDetalleForm ordenDeProduccionDetalleForm = new OrdenDeProduccionDetalleForm();
        ordenDeProduccionDetalleForm.setPk(ordenDeProduccionBulto.getId().toString());
        ordenDeProduccionDetalleForm.setAction("view");        
        ordenDeProduccionDetalleForm.setOperacion("view");   
        ordenDeProduccionDetalleForm.setTipoDetalle("bulto");
        ordenDeProduccionDetalleForm.setImprimir("false");
        
        ordenDeProduccionDetalleForm.setFechaAltaOrdenDeProduccion(ordenDeProduccion.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        ordenDeProduccionDetalleForm.setEstadoOrdenDeProduccion(ordenDeProduccion.getEstado());
        ordenDeProduccionDetalleForm.setIdOrdenProduccion(ordenDeProduccion.getId().toString());
        ordenDeProduccionDetalleForm.setIdCliente(ordenDeProduccion.getIdCliente().toString());
        ordenDeProduccionDetalleForm.setIdArticulo(ordenDeProduccion.getIdArticulo().toString());
        ordenDeProduccionDetalleForm.setIdFichaTecnica(ordenDeProduccion.getIdFichaTecnica().toString());
        ordenDeProduccionDetalleForm.setPesoTecnicoArticulo(articuloFichaTecnica.getPeso().toString());
        ordenDeProduccionDetalleForm.setMetrosArticulo(articuloFichaTecnica.getMetros().toString());
        ordenDeProduccionDetalleForm.setAltoArticulo(articulo.getAlto().toString());
        ordenDeProduccionDetalleForm.setEspesorArticulo(articulo.getEspesor().toString());
        ordenDeProduccionDetalleForm.setAnchoArticulo(articulo.getAncho().toString());

        if(ordenDeProduccionBulto.getIdPlegadora() != null) {
            ordenDeProduccionDetalleForm.setIdPlegadora(ordenDeProduccionBulto.getIdPlegadora().toString());
        }
        if(ordenDeProduccionBulto.getEstado() != null) {
            ordenDeProduccionDetalleForm.setEstadoBulto(ordenDeProduccionBulto.getEstado().toString());
        }
        if(ordenDeProduccionBulto.getObservaciones() != null) {
            ordenDeProduccionDetalleForm.setObservacionesBulto(ordenDeProduccionBulto.getObservaciones().toString());
        }
        if(ordenDeProduccionBulto.getIdOrdenDeProduccionBobina() != null) {
            ordenDeProduccionDetalleForm.setIdBobinaSelected(ordenDeProduccionBulto.getIdOrdenDeProduccionBobina().toString());   
        }
        
        ordenDeProduccionDetalleForm.setCodigoBultoLabel(ordenDeProduccionBulto.getCodigo());

        model.addAttribute("ordenDeProduccionDetalleForm", ordenDeProduccionDetalleForm);  
        model.addAttribute("titleOrdenDeProduccion", "Ver Orden de Producción Bulto");
        model.addAttribute("buttonLabel", "Ver");        
        model.addAttribute("ordenDeProduccionBultoName", "Ver Bulto " + ordenDeProduccionBulto.getCodigo());        
        model.addAttribute("buttonSearchBobinaLabel", "Buscar");
        
        TipoService tipoService = new TipoServiceImpl();
        Map<String,String> plegadoraList = new LinkedHashMap<String,String>();
        List<TipoModel> plegadorasModel = tipoService.getByType("plegadoraBobina");       
        if(plegadorasModel != null && !plegadorasModel.isEmpty()){
            for(TipoModel tipoModel :plegadorasModel) {
                plegadoraList.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        
        Map<String,String> origenScrapList = new LinkedHashMap<String,String>();
        List<TipoModel> origenScrapModel = tipoService.getByType("origenScrap");       
        if(origenScrapModel != null && !origenScrapModel.isEmpty()){
            for(TipoModel tipoModel :origenScrapModel) {
                origenScrapList.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        Map<String,String> tipoMaterialScrapList = new LinkedHashMap<String,String>();
        List<TipoModel> tipoMaterialScrapModel = tipoService.getByType("tipoMaterialScrap");       
        if(tipoMaterialScrapModel != null && !tipoMaterialScrapModel.isEmpty()){
            for(TipoModel tipoModel :tipoMaterialScrapModel) {
                tipoMaterialScrapList.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        Map<String,String> motivoScrapList = new LinkedHashMap<String,String>();
        List<TipoModel> motivoScrapModel = tipoService.getByType("motivoScrap");       
        if(motivoScrapModel != null && !motivoScrapModel.isEmpty()){
            for(TipoModel tipoModel :motivoScrapModel) {
                motivoScrapList.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        Map<String,String> formatoScrapList = new LinkedHashMap<String,String>();
        List<TipoModel> formatoScrapModel = tipoService.getByType("formatoScrap");       
        if(formatoScrapModel != null && !formatoScrapModel.isEmpty()){
            for(TipoModel tipoModel :formatoScrapModel) {
                formatoScrapList.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        
        OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();
        List<OrdenDeProduccionBultoModel> ordenDeProduccionBultosModel = ordenDeProduccionBultoService.getAllByOrdenDeProduccion(ordenDeProduccion.getId());       
        List<OrdenDeProduccionBultoDto> ordenDeProduccionBultosDtos = new ArrayList<OrdenDeProduccionBultoDto>();
                       
        if(ordenDeProduccionBultosModel != null && !ordenDeProduccionBultosModel.isEmpty()) {
            for(OrdenDeProduccionBultoModel ordenDeProduccionBultoModel: ordenDeProduccionBultosModel) {
                OrdenDeProduccionBultoDto ordenDeProduccionBultoDto = new OrdenDeProduccionBultoDto();
                ordenDeProduccionBultoDto.setPk(ordenDeProduccionBultoModel.getId().toString());
                ordenDeProduccionBultoDto.setFechaAlta(ordenDeProduccionBultoModel.getFechaAlta().toString());
                if(ordenDeProduccionBultoModel.getEstado() != null && !ordenDeProduccionBultoModel.getEstado().equalsIgnoreCase("-1")) {
                    ordenDeProduccionBultoDto.setEstado(ordenDeProduccionBultoModel.getEstado());
                    if(ordenDeProduccionBultoModel.getEstado().equals("ok")) {
                        ordenDeProduccionBultoDto.setEstadoLabel("OK");
                    }
                    if(ordenDeProduccionBultoModel.getEstado().equals("observado")) {
                        ordenDeProduccionBultoDto.setEstadoLabel("Observado");
                    }
                    if(ordenDeProduccionBultoModel.getEstado().equals("rechazado")) {
                        ordenDeProduccionBultoDto.setEstadoLabel("Rechazado");
                    }
                    if(ordenDeProduccionBultoModel.getEstado().equals("sinmesurar")) {
                        ordenDeProduccionBultoDto.setEstadoLabel("Sin Mesurar");
                    }                
                }
                ordenDeProduccionBultoDto.setCodigo(ordenDeProduccionBultoModel.getCodigo());
                ordenDeProduccionBultoDto.setEstaEnPallet(ordenDeProduccionBultoModel.getEstaEnPallet().toString());                
                if(ordenDeProduccionBultoModel.getEstaEnPallet()) {
                    ordenDeProduccionBultoDto.setEstaEnPalletLabel("Si");                
                } else {
                    ordenDeProduccionBultoDto.setEstaEnPalletLabel("No");                
                }

                String plegadora = "";
                if(ordenDeProduccionBultoModel.getIdPlegadora() != null) {
                    TipoModel plegadoraModel = tipoService.getByPk(ordenDeProduccionBultoModel.getIdPlegadora());
                    if(plegadora != null) {
                        plegadora = plegadoraModel.getValor();
                    }
                }        
                ordenDeProduccionBultoDto.setPlegadora(plegadora);
                
                OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(ordenDeProduccionBultoModel.getIdOrdenDeProduccionBobina());
                if(bobina != null) {
                    ordenDeProduccionBultoDto.setPesoTotal(bobina.getPesoTotal().toString());
                    ordenDeProduccionBultoDto.setPesoNeto(bobina.getPesoNeto().toString());
                }
                if (ordenDeProduccionBultoModel.getIdOrdenDeProduccionBobina() != null) {
                    ordenDeProduccionBultoDto.setIdBobina(ordenDeProduccionBultoModel.getIdOrdenDeProduccionBobina().toString());
                }

                ordenDeProduccionBultosDtos.add(ordenDeProduccionBultoDto);

            }
        }

        OrdenDeProduccionPalletBultoService ordenDeProduccionPalletBultoService = new OrdenDeProduccionPalletBultoServiceImpl();
        OrdenDeProduccionPalletService ordenDeProduccionPalletService = new OrdenDeProduccionPalletServiceImpl();
        List<OrdenDeProduccionPalletModel> ordenDeProduccionPalletsModel = ordenDeProduccionPalletService.getAllByOrdenDeProduccion(ordenDeProduccion.getId());       
        List<OrdenDeProduccionPalletDto> ordenDeProduccionPalletsDtos = new ArrayList<OrdenDeProduccionPalletDto>();
        
        if(ordenDeProduccionPalletsModel != null && !ordenDeProduccionPalletsModel.isEmpty()) {
            for(OrdenDeProduccionPalletModel ordenDeProduccionPalletModel: ordenDeProduccionPalletsModel) {
                OrdenDeProduccionPalletDto ordenDeProduccionPalletDto = new OrdenDeProduccionPalletDto();
                ordenDeProduccionPalletDto.setPk(ordenDeProduccionPalletModel.getId().toString());
                ordenDeProduccionPalletDto.setFechaAlta(ordenDeProduccionPalletModel.getFechaAlta().toString());
                if(ordenDeProduccionPalletModel.getEstado() != null && !ordenDeProduccionPalletModel.getEstado().equalsIgnoreCase("-1")) {
                    ordenDeProduccionPalletDto.setEstado(ordenDeProduccionPalletModel.getEstado());
                    if(ordenDeProduccionPalletModel.getEstado().equals("ok")) {
                        ordenDeProduccionPalletDto.setEstadoLabel("OK");
                    }
                    if(ordenDeProduccionPalletModel.getEstado().equals("observado")) {
                        ordenDeProduccionPalletDto.setEstadoLabel("Observado");
                    }
                    if(ordenDeProduccionPalletModel.getEstado().equals("rechazado")) {
                        ordenDeProduccionPalletDto.setEstadoLabel("Rechazado");
                    }
                    if(ordenDeProduccionPalletModel.getEstado().equals("sinmesurar")) {
                        ordenDeProduccionPalletDto.setEstadoLabel("Sin Mesurar");
                    }                
                }
                ordenDeProduccionPalletDto.setCodigo(ordenDeProduccionPalletModel.getCodigo());

                List<OrdenDeProduccionPalletBultoModel> palletbultoList = ordenDeProduccionPalletBultoService.getAllByOrdenDeProduccionPallet(ordenDeProduccionPalletModel.getId());
                ordenDeProduccionPalletDto.setCantidadBultos(String.valueOf(palletbultoList.size()));
                
                String listaCodigos = "";
               for(OrdenDeProduccionPalletBultoModel item :palletbultoList) {
                OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(item.getIdOrdenDeProduccionBulto());
                if(bulto != null) {
                 listaCodigos += bulto.getCodigo() + " ";
                }
               }
               ordenDeProduccionPalletDto.setListaCodigoBultos(listaCodigos);

                Double pesoTotal = 0.0;
                if(!palletbultoList.isEmpty()) {
                    for(OrdenDeProduccionPalletBultoModel palletbulto: palletbultoList) {
                        OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(palletbulto.getIdOrdenDeProduccionBulto());
                        if(bulto != null) {
                            OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(bulto.getIdOrdenDeProduccionBobina());
                            if(bobina != null) {
                                pesoTotal += bobina.getPesoTotal();
                            }
                        }
                    }
                }
                ordenDeProduccionPalletDto.setPesoTotal(pesoTotal.toString());
                
                ordenDeProduccionPalletsDtos.add(ordenDeProduccionPalletDto);
                System.out.println("*** ordenDeProduccionPalletsDtos size:"+ordenDeProduccionPalletsDtos.size());
            }
        }
        
        model.addAttribute("displayButtonCambiarEstadoFabricacion", "none");        
        model.addAttribute("displayButtonCambiarEstadoEmpaque", "none");    
        model.addAttribute("displayButtonCambiarEstadoPallet", "none");    
        model.addAttribute("displayButtonCambiarEstadoCompletado", "none");    

        model.addAttribute("estaEnPallet", ordenDeProduccionBulto.getEstaEnPallet());
        model.addAttribute("tipoDetalle", "bulto");
        model.addAttribute("idOrdenDeProduccion", ordenDeProduccion.getId().toString());        
        model.addAttribute("clienteLabel", cliente.getRazonSocial());
        model.addAttribute("idArticulo", articulo.getId());
        model.addAttribute("articuloLabel", articulo.getDenominacion());
        model.addAttribute("fichaTecnicaVersionLabel", articuloFichaTecnica.getVersion());
        
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "edit");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("displaySearchBobinaButton", displaySearchBobinaButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("ordenDeProduccionBultos", ordenDeProduccionBultosDtos);    
        model.addAttribute("ordenDeProduccionPallets", ordenDeProduccionPalletsDtos);
        model.addAttribute("plegadoraList", plegadoraList);
        model.addAttribute("origenScrapList", origenScrapList);
        model.addAttribute("tipoMaterialScrapList", tipoMaterialScrapList);
        model.addAttribute("motivoScrapList", motivoScrapList);
        model.addAttribute("formatoScrapList", formatoScrapList);
        
        model.addAttribute("cantidadDeBobinasQueNoEstanEnBulto", this.cantidadDeBobinasQueNoEstanEnBulto);
        model.addAttribute("cantidadDeBultosQueNoEstanEnPallet", this.cantidadDeBultosQueNoEstanEnPallet);
        model.addAttribute("cantidadPallet", this.cantidadPallet);
        model.addAttribute("cantidadBobinasEnProduccion", this.cantidadBobinasEnProduccion);
        
        return "/ordendeproduccion/ordendeproducciondetalle";
        
    }
    
    @RequestMapping(value = "/ordenDeProduccionDetalle/removeBulto/{ordenDeProduccionBultoPk}", method = RequestMethod.GET)
    public String removeOrdenDeProduccionBulto(@PathVariable String ordenDeProduccionBultoPk, HttpServletRequest req, ModelMap model) throws Exception {
        
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeProduccionBultoPk == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido");         
            return "/error";                
        }
        
        String operacion = "remove";                
        String displayActionButton = "block";
        String displayActionButtonScrap = "none";
        String displaySearchBobinaButton = "none";

        OrdenDeProduccionBultoService ordenDeProduccionBultoService = new OrdenDeProduccionBultoServiceImpl();   
        OrdenDeProduccionBultoModel ordenDeProduccionBulto = ordenDeProduccionBultoService.getByPk(Integer.valueOf(ordenDeProduccionBultoPk));
        if(ordenDeProduccionBulto == null) {
            model.addAttribute("errorMessage", "Error: Orden De Produccion Bulto inválido. No ha sido encontrado.");         
            return "/error";    
        }
        
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();   
        OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(Integer.valueOf(ordenDeProduccionBulto.getIdOrdenDeProduccion()));
        if(ordenDeProduccion == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido. No ha sido encontrado.");         
            return "/error";    
        }
         
        /*
        if(!ordenDeProduccion.getEstado().equalsIgnoreCase("Abierto") && !ordenDeProduccion.getEstado().equalsIgnoreCase("Fabricacion")) {
            model.addAttribute("errorMessage", "Error: estado de orden incorrecto.");         
            return "/error";                            
        }
        */
        
        ClienteService clienteService = new ClienteServiceImpl();
        ClienteModel cliente = clienteService.getByPk(ordenDeProduccion.getIdCliente());
        if(cliente == null) {
            model.addAttribute("errorMessage", "Error: cliente no encontrado");         
            return "/error";                            
        }

        ArticuloService articuloService = new ArticuloServiceImpl();
        ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
        if(articulo == null) {
            model.addAttribute("errorMessage", "Error: articulo no encontrado");         
            return "/error";                            
        }
        
        ArticuloFichaTecnicaService articuloFichaTecnicaService = new ArticuloFichaTecnicaServiceImpl();
        ArticuloFichaTecnicaModel articuloFichaTecnica = articuloFichaTecnicaService.getByPk(ordenDeProduccion.getIdFichaTecnica());
        if(articuloFichaTecnica == null) {
            model.addAttribute("errorMessage", "Error: ficha tecnica no encontrada");         
            return "/error";                            
        }
        
        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(user.getRol() != Utils.ROL_OFICINA) {
            model.addAttribute("errorMessage", "Error: usuario no permite esta operación.");         
            return "/error";                                                                
        }
        
        String rol = "";
        if(user.getRol() == Utils.ROL_OFICINA) {
            rol = "oficina";
        }
        
        OrdenDeProduccionDetalleForm ordenDeProduccionDetalleForm = new OrdenDeProduccionDetalleForm();
        ordenDeProduccionDetalleForm.setPk(ordenDeProduccionBulto.getId().toString());
        ordenDeProduccionDetalleForm.setAction("remove");        
        ordenDeProduccionDetalleForm.setOperacion("remove");   
        ordenDeProduccionDetalleForm.setTipoDetalle("bulto");
        ordenDeProduccionDetalleForm.setImprimir("false");
        
        ordenDeProduccionDetalleForm.setFechaAltaOrdenDeProduccion(ordenDeProduccion.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        ordenDeProduccionDetalleForm.setEstadoOrdenDeProduccion(ordenDeProduccion.getEstado());
        ordenDeProduccionDetalleForm.setIdOrdenProduccion(ordenDeProduccion.getId().toString());
        ordenDeProduccionDetalleForm.setIdCliente(ordenDeProduccion.getIdCliente().toString());
        ordenDeProduccionDetalleForm.setIdArticulo(ordenDeProduccion.getIdArticulo().toString());
        ordenDeProduccionDetalleForm.setIdFichaTecnica(ordenDeProduccion.getIdFichaTecnica().toString());
        ordenDeProduccionDetalleForm.setPesoTecnicoArticulo(articuloFichaTecnica.getPeso().toString());
        ordenDeProduccionDetalleForm.setMetrosArticulo(articuloFichaTecnica.getMetros().toString());
        ordenDeProduccionDetalleForm.setAltoArticulo(articulo.getAlto().toString());
        ordenDeProduccionDetalleForm.setEspesorArticulo(articulo.getEspesor().toString());
        ordenDeProduccionDetalleForm.setAnchoArticulo(articulo.getAncho().toString());

        if(ordenDeProduccionBulto.getIdPlegadora() != null) {
            ordenDeProduccionDetalleForm.setIdPlegadora(ordenDeProduccionBulto.getIdPlegadora().toString());
        }
        if(ordenDeProduccionBulto.getEstado() != null) {
            ordenDeProduccionDetalleForm.setEstadoBulto(ordenDeProduccionBulto.getEstado().toString());
        }
        if(ordenDeProduccionBulto.getObservaciones() != null) {
            ordenDeProduccionDetalleForm.setObservacionesBulto(ordenDeProduccionBulto.getObservaciones().toString());
        }
        if(ordenDeProduccionBulto.getIdOrdenDeProduccionBobina() != null) {
            ordenDeProduccionDetalleForm.setIdBobinaSelected(ordenDeProduccionBulto.getIdOrdenDeProduccionBobina().toString());   
        }        
        ordenDeProduccionDetalleForm.setCodigoBultoLabel(ordenDeProduccionBulto.getCodigo());

        model.addAttribute("ordenDeProduccionDetalleForm", ordenDeProduccionDetalleForm);  
        model.addAttribute("titleOrdenDeProduccion", "Elinminar Orden de Producción Bulto");
        model.addAttribute("buttonLabel", "Eliminar");        
        model.addAttribute("ordenDeProduccionBultoName", "Ver Bulto " + ordenDeProduccionBulto.getCodigo());        
        model.addAttribute("buttonSearchBobinaLabel", "Buscar");
        
        TipoService tipoService = new TipoServiceImpl();
        Map<String,String> plegadoraList = new LinkedHashMap<String,String>();
        List<TipoModel> plegadorasModel = tipoService.getByType("plegadoraBobina");       
        if(plegadorasModel != null && !plegadorasModel.isEmpty()){
            for(TipoModel tipoModel :plegadorasModel) {
                plegadoraList.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        
        Map<String,String> origenScrapList = new LinkedHashMap<String,String>();
        List<TipoModel> origenScrapModel = tipoService.getByType("origenScrap");       
        if(origenScrapModel != null && !origenScrapModel.isEmpty()){
            for(TipoModel tipoModel :origenScrapModel) {
                origenScrapList.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        Map<String,String> tipoMaterialScrapList = new LinkedHashMap<String,String>();
        List<TipoModel> tipoMaterialScrapModel = tipoService.getByType("tipoMaterialScrap");       
        if(tipoMaterialScrapModel != null && !tipoMaterialScrapModel.isEmpty()){
            for(TipoModel tipoModel :tipoMaterialScrapModel) {
                tipoMaterialScrapList.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        Map<String,String> motivoScrapList = new LinkedHashMap<String,String>();
        List<TipoModel> motivoScrapModel = tipoService.getByType("motivoScrap");       
        if(motivoScrapModel != null && !motivoScrapModel.isEmpty()){
            for(TipoModel tipoModel :motivoScrapModel) {
                motivoScrapList.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        Map<String,String> formatoScrapList = new LinkedHashMap<String,String>();
        List<TipoModel> formatoScrapModel = tipoService.getByType("formatoScrap");       
        if(formatoScrapModel != null && !formatoScrapModel.isEmpty()){
            for(TipoModel tipoModel :formatoScrapModel) {
                formatoScrapList.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        
        OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();
        List<OrdenDeProduccionBultoModel> ordenDeProduccionBultosModel = ordenDeProduccionBultoService.getAllByOrdenDeProduccion(ordenDeProduccion.getId());       
        List<OrdenDeProduccionBultoDto> ordenDeProduccionBultosDtos = new ArrayList<OrdenDeProduccionBultoDto>();
                       
        if(ordenDeProduccionBultosModel != null && !ordenDeProduccionBultosModel.isEmpty()) {
            for(OrdenDeProduccionBultoModel ordenDeProduccionBultoModel: ordenDeProduccionBultosModel) {
                OrdenDeProduccionBultoDto ordenDeProduccionBultoDto = new OrdenDeProduccionBultoDto();
                ordenDeProduccionBultoDto.setPk(ordenDeProduccionBultoModel.getId().toString());
                ordenDeProduccionBultoDto.setFechaAlta(ordenDeProduccionBultoModel.getFechaAlta().toString());
                if(ordenDeProduccionBultoModel.getEstado() != null && !ordenDeProduccionBultoModel.getEstado().equalsIgnoreCase("-1")) {
                    ordenDeProduccionBultoDto.setEstado(ordenDeProduccionBultoModel.getEstado());
                    if(ordenDeProduccionBultoModel.getEstado().equals("ok")) {
                        ordenDeProduccionBultoDto.setEstadoLabel("OK");
                    }
                    if(ordenDeProduccionBultoModel.getEstado().equals("observado")) {
                        ordenDeProduccionBultoDto.setEstadoLabel("Observado");
                    }
                    if(ordenDeProduccionBultoModel.getEstado().equals("rechazado")) {
                        ordenDeProduccionBultoDto.setEstadoLabel("Rechazado");
                    }
                    if(ordenDeProduccionBultoModel.getEstado().equals("sinmesurar")) {
                        ordenDeProduccionBultoDto.setEstadoLabel("Sin Mesurar");
                    }                
                }
                ordenDeProduccionBultoDto.setCodigo(ordenDeProduccionBultoModel.getCodigo());
                ordenDeProduccionBultoDto.setEstaEnPallet(ordenDeProduccionBultoModel.getEstaEnPallet().toString());                
                if(ordenDeProduccionBultoModel.getEstaEnPallet()) {
                    ordenDeProduccionBultoDto.setEstaEnPalletLabel("Si");                
                } else {
                    ordenDeProduccionBultoDto.setEstaEnPalletLabel("No");                
                }

                String plegadora = "";
                if(ordenDeProduccionBultoModel.getIdPlegadora() != null) {
                    TipoModel plegadoraModel = tipoService.getByPk(ordenDeProduccionBultoModel.getIdPlegadora());
                    if(plegadora != null) {
                        plegadora = plegadoraModel.getValor();
                    }
                }        
                ordenDeProduccionBultoDto.setPlegadora(plegadora);
                
                OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(ordenDeProduccionBultoModel.getIdOrdenDeProduccionBobina());
                if(bobina != null) {
                    ordenDeProduccionBultoDto.setPesoTotal(bobina.getPesoTotal().toString());
                    ordenDeProduccionBultoDto.setPesoNeto(bobina.getPesoNeto().toString());
                }

                ordenDeProduccionBultosDtos.add(ordenDeProduccionBultoDto);

            }
        }

        OrdenDeProduccionPalletBultoService ordenDeProduccionPalletBultoService = new OrdenDeProduccionPalletBultoServiceImpl();
        OrdenDeProduccionPalletService ordenDeProduccionPalletService = new OrdenDeProduccionPalletServiceImpl();
        List<OrdenDeProduccionPalletModel> ordenDeProduccionPalletsModel = ordenDeProduccionPalletService.getAllByOrdenDeProduccion(ordenDeProduccion.getId());       
        List<OrdenDeProduccionPalletDto> ordenDeProduccionPalletsDtos = new ArrayList<OrdenDeProduccionPalletDto>();
        
        if(ordenDeProduccionPalletsModel != null && !ordenDeProduccionPalletsModel.isEmpty()) {
            for(OrdenDeProduccionPalletModel ordenDeProduccionPalletModel: ordenDeProduccionPalletsModel) {
                OrdenDeProduccionPalletDto ordenDeProduccionPalletDto = new OrdenDeProduccionPalletDto();
                ordenDeProduccionPalletDto.setPk(ordenDeProduccionPalletModel.getId().toString());
                ordenDeProduccionPalletDto.setFechaAlta(ordenDeProduccionPalletModel.getFechaAlta().toString());
                if(ordenDeProduccionPalletModel.getEstado() != null && !ordenDeProduccionPalletModel.getEstado().equalsIgnoreCase("-1")) {
                    ordenDeProduccionPalletDto.setEstado(ordenDeProduccionPalletModel.getEstado());
                    if(ordenDeProduccionPalletModel.getEstado().equals("ok")) {
                        ordenDeProduccionPalletDto.setEstadoLabel("OK");
                    }
                    if(ordenDeProduccionPalletModel.getEstado().equals("observado")) {
                        ordenDeProduccionPalletDto.setEstadoLabel("Observado");
                    }
                    if(ordenDeProduccionPalletModel.getEstado().equals("rechazado")) {
                        ordenDeProduccionPalletDto.setEstadoLabel("Rechazado");
                    }
                    if(ordenDeProduccionPalletModel.getEstado().equals("sinmesurar")) {
                        ordenDeProduccionPalletDto.setEstadoLabel("Sin Mesurar");
                    }                
                }
                ordenDeProduccionPalletDto.setCodigo(ordenDeProduccionPalletModel.getCodigo());

                List<OrdenDeProduccionPalletBultoModel> palletbultoList = ordenDeProduccionPalletBultoService.getAllByOrdenDeProduccionPallet(ordenDeProduccionPalletModel.getId());
                ordenDeProduccionPalletDto.setCantidadBultos(String.valueOf(palletbultoList.size()));

                Double pesoTotal = 0.0;
                if(!palletbultoList.isEmpty()) {
                    for(OrdenDeProduccionPalletBultoModel palletbulto: palletbultoList) {
                        OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(palletbulto.getIdOrdenDeProduccionBulto());
                        if(bulto != null) {
                            OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(bulto.getIdOrdenDeProduccionBobina());
                            if(bobina != null) {
                                pesoTotal += bobina.getPesoTotal();
                            }
                        }
                    }
                }
                ordenDeProduccionPalletDto.setPesoTotal(pesoTotal.toString());
                
                ordenDeProduccionPalletsDtos.add(ordenDeProduccionPalletDto);
                System.out.println("*** ordenDeProduccionPalletsDtos size:"+ordenDeProduccionPalletsDtos.size());
            }
        }
        
        model.addAttribute("displayButtonCambiarEstadoFabricacion", "none");        
        model.addAttribute("displayButtonCambiarEstadoEmpaque", "none");    
        model.addAttribute("displayButtonCambiarEstadoPallet", "none");    
        model.addAttribute("displayButtonCambiarEstadoCompletado", "none");    

        model.addAttribute("estaEnPallet", ordenDeProduccionBulto.getEstaEnPallet());
        model.addAttribute("tipoDetalle", "bulto");
        model.addAttribute("idOrdenDeProduccion", ordenDeProduccion.getId().toString());        
        model.addAttribute("clienteLabel", cliente.getRazonSocial());
        model.addAttribute("idArticulo", articulo.getId());
        model.addAttribute("articuloLabel", articulo.getDenominacion());
        model.addAttribute("fichaTecnicaVersionLabel", articuloFichaTecnica.getVersion());
        
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "remove");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("displayActionButtonScrap", displayActionButtonScrap);
        model.addAttribute("displaySearchBobinaButton", displaySearchBobinaButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("ordenDeProduccionBultos", ordenDeProduccionBultosDtos);    
        model.addAttribute("ordenDeProduccionPallets", ordenDeProduccionPalletsDtos);
        model.addAttribute("plegadoraList", plegadoraList);
        model.addAttribute("origenScrapList", origenScrapList);
        model.addAttribute("tipoMaterialScrapList", tipoMaterialScrapList);
        model.addAttribute("motivoScrapList", motivoScrapList);
        model.addAttribute("formatoScrapList", formatoScrapList);
        
        return "/ordendeproduccion/ordendeproducciondetalle";
        
    }
    
    @RequestMapping(value = "/ordenDeProduccionDetalle/print/bulto/{ordendeproduccionbultopk}", method = RequestMethod.GET)
    public String printBulto(@PathVariable String ordendeproduccionbultopk, HttpServletRequest req, ModelMap model) throws Exception {
                                
        String ordenDeProduccion = "Sin información";
        String fechaAltaOrdenDeProduccion = "Sin información";
        String cliente = "Sin información";
        String articulo = "Sin información";
        String etiquetaArticulo = "Sin información";
        String articuloCodigo = "Sin información";
        String fichaTecnica = "Sin información";
        String codigoBulto = "Sin información";
        String plegadora = "Sin información";
        String fechaAltaLote = "Sin información";
        String fechaActual = "Sin información";
        String fechaAltaBulto = "Sin información";
        String url = "http://localhost:8080/thyssenplastic/ordenDeProduccionDetalle/viewbulto/xxxx";
        
        Calendar c = Calendar.getInstance();        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        fechaActual = formato.format(c.getTime());
        
        if(ordendeproduccionbultopk != null && !ordendeproduccionbultopk.isEmpty()) {
            
            OrdenDeProduccionBultoService ordenDeProduccionBultoService = new OrdenDeProduccionBultoServiceImpl();   
            OrdenDeProduccionBultoModel ordenDeProduccionBulto = ordenDeProduccionBultoService.getByPk(Integer.valueOf(ordendeproduccionbultopk));

            if(ordenDeProduccionBulto != null) {                
                codigoBulto = ordenDeProduccionBulto.getCodigo();
                fechaAltaBulto = formato.format(ordenDeProduccionBulto.getFechaAlta());
                url = "http://localhost:8080/thyssenplastic/ordenDeProduccionDetalle/viewbulto/"+ordenDeProduccionBulto.getId();
                
                OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();   
                OrdenDeProduccionModel ordenDeProduccionModel = ordenDeProduccionService.getByPk(Integer.valueOf(ordenDeProduccionBulto.getIdOrdenDeProduccion()));
                if(ordenDeProduccionModel != null) {

                    ordenDeProduccion = ordenDeProduccionModel.getId().toString();
                    fechaAltaOrdenDeProduccion = ordenDeProduccionModel.getFechaAlta().toString().replace("00:00:00.0", "");
                    
                    fechaActual = formato.format(ordenDeProduccionModel.getFechaAltaImpresion());
                    
                    ClienteService clienteService = new ClienteServiceImpl();
                    ClienteModel clienteModel = clienteService.getByPk(ordenDeProduccionModel.getIdCliente());
                    if(clienteModel != null) {
                        cliente = clienteModel.getRazonSocial();
                    }

                    ArticuloService articuloService = new ArticuloServiceImpl();
                    ArticuloModel articuloModel = articuloService.getByPk(ordenDeProduccionModel.getIdArticulo());
                    if(articuloModel != null) {
                        articulo = articuloModel.getDenominacion();
                        etiquetaArticulo = articuloModel.getDenominacion();
                        articuloCodigo = articuloModel.getId().toString();
                    }

                    ArticuloFichaTecnicaService articuloFichaTecnicaService = new ArticuloFichaTecnicaServiceImpl();
                    ArticuloFichaTecnicaModel articuloFichaTecnica = articuloFichaTecnicaService.getByPk(ordenDeProduccionModel.getIdFichaTecnica());
                    if(articuloFichaTecnica != null) {
                        fichaTecnica = articuloFichaTecnica.getVersion().toString();
                    }
                    
                    ArticuloEtiquetaService articuloEtiquetaService = new ArticuloEtiquetaServiceImpl();
                    List<ArticuloEtiquetaModel> articulosEtiquetaModel = articuloEtiquetaService.getAllByArticulo(articuloModel.getId());
                    if(articulosEtiquetaModel != null && !articulosEtiquetaModel.isEmpty()) {
                        ArticuloEtiquetaModel articuloEtiquetaModel = articulosEtiquetaModel.get(0);
                        if(articuloEtiquetaModel.getLinea1() != null && !articuloEtiquetaModel.getLinea1().isEmpty()) {
                            etiquetaArticulo = articuloEtiquetaModel.getLinea1();
                        } 
                    }                    
                }
                
                if(ordenDeProduccionBulto.getIdPlegadora() != null) {
                    TipoService tipoService = new TipoServiceImpl();   
                    TipoModel tipoModel = tipoService.getByPk(ordenDeProduccionBulto.getIdPlegadora());
                    if(tipoModel != null) {
                        plegadora = tipoModel.getValor();
                    }
                }
            }
        } 
        
        model.addAttribute("ordenDeProduccion", ordenDeProduccion);
        model.addAttribute("fechaAltaOrdenDeProduccion", fechaAltaOrdenDeProduccion);
        model.addAttribute("cliente", cliente);
        model.addAttribute("articulo", articulo);
        model.addAttribute("etiquetaArticulo", etiquetaArticulo);
        model.addAttribute("articuloCodigo", articuloCodigo);
        model.addAttribute("fichaTecnica", fichaTecnica);
        model.addAttribute("codigoBulto", codigoBulto);
        model.addAttribute("plegadora", plegadora);        
        model.addAttribute("fechaAltaBulto", fechaAltaBulto);        
        model.addAttribute("fechaAltaLote", fechaAltaLote);        
        model.addAttribute("fechaActual", fechaActual);        
        model.addAttribute("url", url);
        
        return "/ordendeproduccion/ordendeproduccionbultoprint"; 
    }  
    
    @ResponseBody        
    @RequestMapping(value = "/ordenDeProduccionDetalle/setBultoSelected/{idOrdenProduccion}/{bultoCode}/{idBulto}", method = RequestMethod.GET)
    public List<OrdenDeProduccionBultoSelectedDto> setBultoSelected(@PathVariable String idOrdenProduccion, @PathVariable String bultoCode, @PathVariable String idBulto, HttpServletRequest req, ModelMap model) throws Exception {
        
        List<OrdenDeProduccionBultoSelectedDto> response = new ArrayList<OrdenDeProduccionBultoSelectedDto>();
        
        OrdenDeProduccionBultoSelectedDto dto = new OrdenDeProduccionBultoSelectedDto();
        dto.setError("NOK");
        dto.setErrorMessage("Bulto no encontrado.");
        
        if(idOrdenProduccion != null && !idOrdenProduccion.isEmpty()) {
            
            OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl(); 
            OrdenDeProduccionModel ordenDeProduccionModel = ordenDeProduccionService.getByPk(Integer.valueOf(idOrdenProduccion));
            if(ordenDeProduccionModel != null) {

                if(bultoCode != null && !bultoCode.isEmpty() && !bultoCode.equalsIgnoreCase("-1")) {

                    OrdenDeProduccionBultoService ordenDeProduccionBultoService = new OrdenDeProduccionBultoServiceImpl();   
                    OrdenDeProduccionBultoModel ordenDeProduccionBulto = ordenDeProduccionBultoService.getByCode(bultoCode);

                    if(ordenDeProduccionBulto != null && ordenDeProduccionBulto.getIdOrdenDeProduccion() == ordenDeProduccionModel.getId()) {                
                        if(!ordenDeProduccionBulto.getEstaEnPallet()) {
                            dto.setError("OK");
                            dto.setPk(ordenDeProduccionBulto.getId().toString());
                            dto.setCodigo(ordenDeProduccionBulto.getCodigo());
                            if(ordenDeProduccionBulto.getEstado() != null && !ordenDeProduccionBulto.getEstado().equalsIgnoreCase("-1")) {                    
                                if(ordenDeProduccionBulto.getEstado().equals("ok")) {
                                    dto.setEstado("OK");
                                }
                                if(ordenDeProduccionBulto.getEstado().equals("observado")) {
                                    dto.setEstado("Observado");
                                }
                                if(ordenDeProduccionBulto.getEstado().equals("rechazado")) {
                                    dto.setEstado("Rechazado");
                                }
                                if(ordenDeProduccionBulto.getEstado().equals("sinmesurar")) {
                                    dto.setEstado("Sin Mesurar");
                                }                
                            }                
                        } else {
                            dto.setErrorMessage("El Bulto ya se encuentra en un pallet.");
                        }                
                    }
                } else {
                    OrdenDeProduccionBultoService ordenDeProduccionBultoService = new OrdenDeProduccionBultoServiceImpl();   
                    OrdenDeProduccionBultoModel ordenDeProduccionBulto = ordenDeProduccionBultoService.getByPk(Integer.valueOf(idBulto));

                    if(ordenDeProduccionBulto != null && ordenDeProduccionBulto.getIdOrdenDeProduccion() == ordenDeProduccionModel.getId()) {                
                        if(!ordenDeProduccionBulto.getEstaEnPallet()) {
                            dto.setError("OK");
                            dto.setPk(ordenDeProduccionBulto.getId().toString());
                            dto.setCodigo(ordenDeProduccionBulto.getCodigo());
                            if(ordenDeProduccionBulto.getEstado() != null && !ordenDeProduccionBulto.getEstado().equalsIgnoreCase("-1")) {                    
                                if(ordenDeProduccionBulto.getEstado().equals("ok")) {
                                    dto.setEstado("OK");
                                }
                                if(ordenDeProduccionBulto.getEstado().equals("observado")) {
                                    dto.setEstado("Observado");
                                }
                                if(ordenDeProduccionBulto.getEstado().equals("rechazado")) {
                                    dto.setEstado("Rechazado");
                                }
                                if(ordenDeProduccionBulto.getEstado().equals("sinmesurar")) {
                                    dto.setEstado("Sin Mesurar");
                                }                
                            }                
                        } else {
                            dto.setErrorMessage("El Bulto ya se encuentra en un pallet.");
                        }
                    }            
                }
            }
        }
        response.add(dto);
        
        return response; 
    }    
    
    @RequestMapping(value = "/ordenDeProduccionDetalle/editpallet/{ordenDeProduccionPalletPk}", method = RequestMethod.GET)
    public String editOrdenDeProduccionPallet(@PathVariable String ordenDeProduccionPalletPk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeProduccionPalletPk == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido");         
            return "/error";                
        }
        
        String operacion = "edit";                
        String displayActionButton = "block";
        String displayActionButtonScrap = "none";
        String displaySearchBultoButton = "block";

        OrdenDeProduccionPalletService ordenDeProduccionPalletService = new OrdenDeProduccionPalletServiceImpl();   
        OrdenDeProduccionPalletModel ordenDeProduccionPallet = ordenDeProduccionPalletService.getByPk(Integer.valueOf(ordenDeProduccionPalletPk));
        if(ordenDeProduccionPallet == null) {
            model.addAttribute("errorMessage", "Error: Orden De Produccion Pallet inválido. No ha sido encontrado.");         
            return "/error";    
        }
        
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();   
        OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(Integer.valueOf(ordenDeProduccionPallet.getIdOrdenDeProduccion()));
        if(ordenDeProduccion == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido. No ha sido encontrado.");         
            return "/error";    
        }
                 
        if(ordenDeProduccion.getEstado().equalsIgnoreCase("Abierto")) {
            model.addAttribute("errorMessage", "Error: estado de orden incorrecto.");         
            return "/error";                            
        }
        
        ClienteService clienteService = new ClienteServiceImpl();
        ClienteModel cliente = clienteService.getByPk(ordenDeProduccion.getIdCliente());
        if(cliente == null) {
            model.addAttribute("errorMessage", "Error: cliente no encontrado");         
            return "/error";                            
        }

        ArticuloService articuloService = new ArticuloServiceImpl();
        ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
        if(articulo == null) {
            model.addAttribute("errorMessage", "Error: articulo no encontrado");         
            return "/error";                            
        }
        
        ArticuloFichaTecnicaService articuloFichaTecnicaService = new ArticuloFichaTecnicaServiceImpl();
        ArticuloFichaTecnicaModel articuloFichaTecnica = articuloFichaTecnicaService.getByPk(ordenDeProduccion.getIdFichaTecnica());
        if(articuloFichaTecnica == null) {
            model.addAttribute("errorMessage", "Error: ficha tecnica no encontrada");         
            return "/error";                            
        }
        
        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(user.getRol() != Utils.ROL_PLANTA  && user.getRol() != Utils.ROL_OFICINA) {
            model.addAttribute("errorMessage", "Error: usuario no permite esta operación.");         
            return "/error";                                                                
        }
        
        String rol = "";
        if(user.getRol() == Utils.ROL_PLANTA) {
            rol = "planta";
        }

        
        OrdenDeProduccionDetalleForm ordenDeProduccionDetalleForm = new OrdenDeProduccionDetalleForm();
        ordenDeProduccionDetalleForm.setPk(ordenDeProduccionPallet.getId().toString());
        ordenDeProduccionDetalleForm.setAction("edit");        
        ordenDeProduccionDetalleForm.setOperacion("edit");   
        ordenDeProduccionDetalleForm.setTipoDetalle("pallet");
        ordenDeProduccionDetalleForm.setImprimir("false");
        
        ordenDeProduccionDetalleForm.setFechaAltaOrdenDeProduccion(ordenDeProduccion.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        ordenDeProduccionDetalleForm.setEstadoOrdenDeProduccion(ordenDeProduccion.getEstado());
        ordenDeProduccionDetalleForm.setIdOrdenProduccion(ordenDeProduccion.getId().toString());
        ordenDeProduccionDetalleForm.setIdCliente(ordenDeProduccion.getIdCliente().toString());
        ordenDeProduccionDetalleForm.setIdArticulo(ordenDeProduccion.getIdArticulo().toString());
        ordenDeProduccionDetalleForm.setIdFichaTecnica(ordenDeProduccion.getIdFichaTecnica().toString());
        ordenDeProduccionDetalleForm.setPesoTecnicoArticulo(articuloFichaTecnica.getPeso().toString());
        ordenDeProduccionDetalleForm.setMetrosArticulo(articuloFichaTecnica.getMetros().toString());
        ordenDeProduccionDetalleForm.setAltoArticulo(articulo.getAlto().toString());
        ordenDeProduccionDetalleForm.setEspesorArticulo(articulo.getEspesor().toString());
        ordenDeProduccionDetalleForm.setAnchoArticulo(articulo.getAncho().toString());

        //bultosSelected        
        Map<String,String> bultoSelectedList = new LinkedHashMap<String,String>();
        OrdenDeProduccionPalletBultoService ordenDeProduccionPalletBultoService = new OrdenDeProduccionPalletBultoServiceImpl();
        OrdenDeProduccionBultoService ordenDeProduccionBultoService = new OrdenDeProduccionBultoServiceImpl();
        List<OrdenDeProduccionPalletBultoModel> palletBultos = ordenDeProduccionPalletBultoService.getAllByOrdenDeProduccionPallet(ordenDeProduccionPallet.getId());
        if(!palletBultos.isEmpty()) {
            for(OrdenDeProduccionPalletBultoModel palletBulto :palletBultos) {
                OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(palletBulto.getIdOrdenDeProduccionBulto());
                if(bulto != null) {
                    if(bulto.getEstado() != null) {
                        bultoSelectedList.put(bulto.getId().toString(), bulto.getCodigo() + " (" + bulto.getEstado() + ")");
                    } else {
                        bultoSelectedList.put(bulto.getId().toString(), bulto.getCodigo());
                    }
                }
            }
        }
        
        if(ordenDeProduccionPallet.getEstado() != null) {
            ordenDeProduccionDetalleForm.setEstadoPallet(ordenDeProduccionPallet.getEstado().toString());
        }
        if(ordenDeProduccionPallet.getObservaciones() != null) {
            ordenDeProduccionDetalleForm.setObservacionesPallet(ordenDeProduccionPallet.getObservaciones().toString());
        }
        ordenDeProduccionDetalleForm.setCodigoPalletLabel(ordenDeProduccionPallet.getCodigo());

        model.addAttribute("ordenDeProduccionDetalleForm", ordenDeProduccionDetalleForm);  
        model.addAttribute("titleOrdenDeProduccion", "Editar Orden de Producción Pallet");
        model.addAttribute("buttonLabel", "Guardar");        
        model.addAttribute("ordenDeProduccionPalletName", "Editando Pallet " + ordenDeProduccionPallet.getCodigo());        
        model.addAttribute("buttonSearchBultoLabel", "Agregar");
                                
        OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();
        List<OrdenDeProduccionPalletModel> ordenDeProduccionPalletsModel = ordenDeProduccionPalletService.getAllByOrdenDeProduccion(ordenDeProduccion.getId());       
        List<OrdenDeProduccionPalletDto> ordenDeProduccionPalletsDtos = new ArrayList<OrdenDeProduccionPalletDto>();
        
        Map<String,String> bultoDisponibleList = new LinkedHashMap<String,String>();
        List<OrdenDeProduccionBultoModel> ordenDeProduccionBultoAvailablesModel = ordenDeProduccionBultoService.getAllAvailableByOrdenDeProduccion(ordenDeProduccion.getId());       
        if(ordenDeProduccionBultoAvailablesModel != null && !ordenDeProduccionBultoAvailablesModel.isEmpty()){
            for(OrdenDeProduccionBultoModel bultoModel :ordenDeProduccionBultoAvailablesModel) {
                if(bultoModel.getEstado() != null) {
                    bultoDisponibleList.put(bultoModel.getId().toString(), bultoModel.getCodigo() + " (" + bultoModel.getEstado() + ")");
                } else {
                    bultoDisponibleList.put(bultoModel.getId().toString(), bultoModel.getCodigo());
                }
            }
        }
        
        if(ordenDeProduccionPalletsModel != null && !ordenDeProduccionPalletsModel.isEmpty()) {
            for(OrdenDeProduccionPalletModel ordenDeProduccionPalletModel: ordenDeProduccionPalletsModel) {
                OrdenDeProduccionPalletDto ordenDeProduccionPalletDto = new OrdenDeProduccionPalletDto();
                ordenDeProduccionPalletDto.setPk(ordenDeProduccionPalletModel.getId().toString());
                ordenDeProduccionPalletDto.setFechaAlta(ordenDeProduccionPalletModel.getFechaAlta().toString());
                if(ordenDeProduccionPalletModel.getEstado() != null && !ordenDeProduccionPalletModel.getEstado().equalsIgnoreCase("-1")) {
                    ordenDeProduccionPalletDto.setEstado(ordenDeProduccionPalletModel.getEstado());
                    if(ordenDeProduccionPalletModel.getEstado().equals("ok")) {
                        ordenDeProduccionPalletDto.setEstadoLabel("OK");
                    }
                    if(ordenDeProduccionPalletModel.getEstado().equals("observado")) {
                        ordenDeProduccionPalletDto.setEstadoLabel("Observado");
                    }
                    if(ordenDeProduccionPalletModel.getEstado().equals("rechazado")) {
                        ordenDeProduccionPalletDto.setEstadoLabel("Rechazado");
                    }
                    if(ordenDeProduccionPalletModel.getEstado().equals("sinmesurar")) {
                        ordenDeProduccionPalletDto.setEstadoLabel("Sin Mesurar");
                    }                
                }
                ordenDeProduccionPalletDto.setCodigo(ordenDeProduccionPalletModel.getCodigo());
                List<OrdenDeProduccionPalletBultoModel> palletbultoList = ordenDeProduccionPalletBultoService.getAllByOrdenDeProduccionPallet(ordenDeProduccionPalletModel.getId());
                ordenDeProduccionPalletDto.setCantidadBultos(String.valueOf(palletbultoList.size()));
                
                String listaCodigos = "";
               Map<String,String> mapaBultos = new LinkedHashMap<String,String>();
                for(OrdenDeProduccionPalletBultoModel item :palletbultoList) {
                OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(item.getIdOrdenDeProduccionBulto());
                if(bulto != null) {
                 
                 mapaBultos.put(bulto.getIdOrdenDeProduccionBobina().toString(), bulto.getCodigo());
                 listaCodigos += bulto.getCodigo() + " ";
                }
               }
               ordenDeProduccionPalletDto.setMapaBultos(mapaBultos);
               ordenDeProduccionPalletDto.setListaCodigoBultos(listaCodigos);
               

                Double pesoTotal = 0.0;
                if(!palletbultoList.isEmpty()) {
                    for(OrdenDeProduccionPalletBultoModel palletbulto: palletbultoList) {
                        OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(palletbulto.getIdOrdenDeProduccionBulto());
                        if(bulto != null) {
                            OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(bulto.getIdOrdenDeProduccionBobina());
                            if(bobina != null) {
                                pesoTotal += bobina.getPesoTotal();
                            }
                        }
                    }
                }
                ordenDeProduccionPalletDto.setPesoTotal(pesoTotal.toString());
                
                ordenDeProduccionPalletsDtos.add(ordenDeProduccionPalletDto);
                System.out.println("*** ordenDeProduccionPalletsDtos size:"+ordenDeProduccionPalletsDtos.size());
            }
        }

        ActivacionManualService activacionManualService = new ActivacionManualServiceImpl();
        ActivacionManualModel activacionManual = activacionManualService.getByPk(1);        
        
        model.addAttribute("displayButtonCambiarEstadoFabricacion", "none");        
        model.addAttribute("displayButtonCambiarEstadoEmpaque", "none");    
        model.addAttribute("displayButtonCambiarEstadoPallet", "none");    
        model.addAttribute("displayButtonCambiarEstadoCompletado", "none");    

        model.addAttribute("bultoSelectedList", bultoSelectedList);
        model.addAttribute("bultoDisponibleList", bultoDisponibleList);
        model.addAttribute("tipoDetalle", "pallet");
        model.addAttribute("idOrdenDeProduccion", ordenDeProduccion.getId().toString());        
        model.addAttribute("clienteLabel", cliente.getRazonSocial());
        model.addAttribute("idArticulo", articulo.getId());
        model.addAttribute("articuloLabel", articulo.getDenominacion());
        model.addAttribute("fichaTecnicaVersionLabel", articuloFichaTecnica.getVersion());
        
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "edit");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("displayActionButtonScrap", displayActionButtonScrap);
        model.addAttribute("displaySearchBultoButton", displaySearchBultoButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("ordenDeProduccionPallets", ordenDeProduccionPalletsDtos);
        
        model.addAttribute("cantidadDeBobinasQueNoEstanEnBulto", this.cantidadDeBobinasQueNoEstanEnBulto);
        model.addAttribute("cantidadDeBultosQueNoEstanEnPallet", this.cantidadDeBultosQueNoEstanEnPallet);
        model.addAttribute("cantidadPallet", this.cantidadPallet);
        model.addAttribute("cantidadBobinasEnProduccion", this.cantidadBobinasEnProduccion);
        
        model.addAttribute("statusAct", activacionManual.getActivacionManual() || rol.equalsIgnoreCase("oficina"));
        
        return "/ordendeproduccion/ordendeproducciondetalle";
    }    
    
    @RequestMapping(value = "/ordenDeProduccionDetalle/viewpallet/{ordenDeProduccionPalletPk}", method = RequestMethod.GET)
    public String viewOrdenDeProduccionPallet(@PathVariable String ordenDeProduccionPalletPk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeProduccionPalletPk == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido");         
            return "/error";                
        }
        
        String operacion = "view";                
        String displayActionButton = "none";
        String displayActionButtonScrap = "none";
        String displaySearchBultoButton = "none";

        OrdenDeProduccionPalletService ordenDeProduccionPalletService = new OrdenDeProduccionPalletServiceImpl();   
        OrdenDeProduccionPalletModel ordenDeProduccionPallet = ordenDeProduccionPalletService.getByPk(Integer.valueOf(ordenDeProduccionPalletPk));
        if(ordenDeProduccionPallet == null) {
            model.addAttribute("errorMessage", "Error: Orden De Produccion Pallet inválido. No ha sido encontrado.");         
            return "/error";    
        }
        
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();   
        OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(Integer.valueOf(ordenDeProduccionPallet.getIdOrdenDeProduccion()));
        if(ordenDeProduccion == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido. No ha sido encontrado.");         
            return "/error";    
        }

        /*
        if(!ordenDeProduccion.getEstado().equalsIgnoreCase("Abierto") && !ordenDeProduccion.getEstado().equalsIgnoreCase("Fabricacion")) {
            model.addAttribute("errorMessage", "Error: estado de orden incorrecto.");         
            return "/error";                            
        }
        */
        
        ClienteService clienteService = new ClienteServiceImpl();
        ClienteModel cliente = clienteService.getByPk(ordenDeProduccion.getIdCliente());
        if(cliente == null) {
            model.addAttribute("errorMessage", "Error: cliente no encontrado");         
            return "/error";                            
        }

        ArticuloService articuloService = new ArticuloServiceImpl();
        ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
        if(articulo == null) {
            model.addAttribute("errorMessage", "Error: articulo no encontrado");         
            return "/error";                            
        }
        
        ArticuloFichaTecnicaService articuloFichaTecnicaService = new ArticuloFichaTecnicaServiceImpl();
        ArticuloFichaTecnicaModel articuloFichaTecnica = articuloFichaTecnicaService.getByPk(ordenDeProduccion.getIdFichaTecnica());
        if(articuloFichaTecnica == null) {
            model.addAttribute("errorMessage", "Error: ficha tecnica no encontrada");         
            return "/error";                            
        }
        
        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(user.getRol() != Utils.ROL_OFICINA && user.getRol() != Utils.ROL_PLANTA && user.getRol() != Utils.ROL_DEPOSITO) {
            model.addAttribute("errorMessage", "Error: usuario no permite esta operación.");         
            return "/error";                                                                
        }
        
        String rol = "";
        if(user.getRol() == Utils.ROL_PLANTA) {
            rol = "planta";
        }
        if(user.getRol() == Utils.ROL_OFICINA) {
            rol = "oficina";
        }
        if(user.getRol() == Utils.ROL_DEPOSITO) {
            rol = "deposito";
        }

        
        OrdenDeProduccionDetalleForm ordenDeProduccionDetalleForm = new OrdenDeProduccionDetalleForm();
        ordenDeProduccionDetalleForm.setPk(ordenDeProduccionPallet.getId().toString());
        ordenDeProduccionDetalleForm.setAction("view");        
        ordenDeProduccionDetalleForm.setOperacion("view");   
        ordenDeProduccionDetalleForm.setTipoDetalle("pallet");
        ordenDeProduccionDetalleForm.setImprimir("false");
        
        ordenDeProduccionDetalleForm.setFechaAltaOrdenDeProduccion(ordenDeProduccion.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        ordenDeProduccionDetalleForm.setEstadoOrdenDeProduccion(ordenDeProduccion.getEstado());
        ordenDeProduccionDetalleForm.setIdOrdenProduccion(ordenDeProduccion.getId().toString());
        ordenDeProduccionDetalleForm.setIdCliente(ordenDeProduccion.getIdCliente().toString());
        ordenDeProduccionDetalleForm.setIdArticulo(ordenDeProduccion.getIdArticulo().toString());
        ordenDeProduccionDetalleForm.setIdFichaTecnica(ordenDeProduccion.getIdFichaTecnica().toString());
        ordenDeProduccionDetalleForm.setPesoTecnicoArticulo(articuloFichaTecnica.getPeso().toString());
        ordenDeProduccionDetalleForm.setMetrosArticulo(articuloFichaTecnica.getMetros().toString());
        ordenDeProduccionDetalleForm.setAltoArticulo(articulo.getAlto().toString());
        ordenDeProduccionDetalleForm.setEspesorArticulo(articulo.getEspesor().toString());
        ordenDeProduccionDetalleForm.setAnchoArticulo(articulo.getAncho().toString());

        //bultosSelected        
        Map<String,String> bultoSelectedList = new LinkedHashMap<String,String>();
        OrdenDeProduccionPalletBultoService ordenDeProduccionPalletBultoService = new OrdenDeProduccionPalletBultoServiceImpl();
        OrdenDeProduccionBultoService ordenDeProduccionBultoService = new OrdenDeProduccionBultoServiceImpl();
        List<OrdenDeProduccionPalletBultoModel> palletBultos = ordenDeProduccionPalletBultoService.getAllByOrdenDeProduccionPallet(ordenDeProduccionPallet.getId());
        if(!palletBultos.isEmpty()) {
            for(OrdenDeProduccionPalletBultoModel palletBulto :palletBultos) {
                OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(palletBulto.getIdOrdenDeProduccionBulto());
                if(bulto != null) {
                    if(bulto.getEstado() != null) {
                        bultoSelectedList.put(bulto.getId().toString(), bulto.getCodigo() + " (" + bulto.getEstado() + ")");
                    } else {
                        bultoSelectedList.put(bulto.getId().toString(), bulto.getCodigo());
                    }
                }
            }
        }
        
        if(ordenDeProduccionPallet.getEstado() != null) {
            ordenDeProduccionDetalleForm.setEstadoPallet(ordenDeProduccionPallet.getEstado().toString());
        }
        if(ordenDeProduccionPallet.getObservaciones() != null) {
            ordenDeProduccionDetalleForm.setObservacionesPallet(ordenDeProduccionPallet.getObservaciones().toString());
        }
        ordenDeProduccionDetalleForm.setCodigoPalletLabel(ordenDeProduccionPallet.getCodigo());

        model.addAttribute("ordenDeProduccionDetalleForm", ordenDeProduccionDetalleForm);  
        model.addAttribute("titleOrdenDeProduccion", "Ver Orden de Producción Pallet");
        model.addAttribute("buttonLabel", "Guardar");        
        model.addAttribute("ordenDeProduccionPalletName", "Ver Pallet " + ordenDeProduccionPallet.getCodigo());        
        model.addAttribute("buttonSearchBultoLabel", "Agregar");
                                
        OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();
        List<OrdenDeProduccionPalletModel> ordenDeProduccionPalletsModel = ordenDeProduccionPalletService.getAllByOrdenDeProduccion(ordenDeProduccion.getId());       
        List<OrdenDeProduccionPalletDto> ordenDeProduccionPalletsDtos = new ArrayList<OrdenDeProduccionPalletDto>();
        
        Map<String,String> bultoDisponibleList = new LinkedHashMap<String,String>();
        List<OrdenDeProduccionBultoModel> ordenDeProduccionBultoAvailablesModel = ordenDeProduccionBultoService.getAllAvailableByOrdenDeProduccion(ordenDeProduccion.getId());       
        if(ordenDeProduccionBultoAvailablesModel != null && !ordenDeProduccionBultoAvailablesModel.isEmpty()){
            for(OrdenDeProduccionBultoModel bultoModel :ordenDeProduccionBultoAvailablesModel) {
                if(bultoModel.getEstado() != null) {
                    bultoDisponibleList.put(bultoModel.getId().toString(), bultoModel.getCodigo() + " (" + bultoModel.getEstado() + ")");
                } else {
                    bultoDisponibleList.put(bultoModel.getId().toString(), bultoModel.getCodigo());
                }
            }
        }
        
        if(ordenDeProduccionPalletsModel != null && !ordenDeProduccionPalletsModel.isEmpty()) {
            for(OrdenDeProduccionPalletModel ordenDeProduccionPalletModel: ordenDeProduccionPalletsModel) {
                OrdenDeProduccionPalletDto ordenDeProduccionPalletDto = new OrdenDeProduccionPalletDto();
                ordenDeProduccionPalletDto.setPk(ordenDeProduccionPalletModel.getId().toString());
                ordenDeProduccionPalletDto.setFechaAlta(ordenDeProduccionPalletModel.getFechaAlta().toString());
                if(ordenDeProduccionPalletModel.getEstado() != null && !ordenDeProduccionPalletModel.getEstado().equalsIgnoreCase("-1")) {
                    ordenDeProduccionPalletDto.setEstado(ordenDeProduccionPalletModel.getEstado());
                    if(ordenDeProduccionPalletModel.getEstado().equals("ok")) {
                        ordenDeProduccionPalletDto.setEstadoLabel("OK");
                    }
                    if(ordenDeProduccionPalletModel.getEstado().equals("observado")) {
                        ordenDeProduccionPalletDto.setEstadoLabel("Observado");
                    }
                    if(ordenDeProduccionPalletModel.getEstado().equals("rechazado")) {
                        ordenDeProduccionPalletDto.setEstadoLabel("Rechazado");
                    }
                    if(ordenDeProduccionPalletModel.getEstado().equals("sinmesurar")) {
                        ordenDeProduccionPalletDto.setEstadoLabel("Sin Mesurar");
                    }                
                }
                ordenDeProduccionPalletDto.setCodigo(ordenDeProduccionPalletModel.getCodigo());
                List<OrdenDeProduccionPalletBultoModel> palletbultoList = ordenDeProduccionPalletBultoService.getAllByOrdenDeProduccionPallet(ordenDeProduccionPalletModel.getId());
                ordenDeProduccionPalletDto.setCantidadBultos(String.valueOf(palletbultoList.size()));
                
                String listaCodigos = "";
               
               
               Map<String,String> mapaBultos = new LinkedHashMap<String,String>();
                for(OrdenDeProduccionPalletBultoModel item :palletbultoList) {
                OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(item.getIdOrdenDeProduccionBulto());
                if(bulto != null) {
                 
                 mapaBultos.put(bulto.getIdOrdenDeProduccionBobina().toString(), bulto.getCodigo());
                 listaCodigos += bulto.getCodigo() + " ";
                }
               }
               ordenDeProduccionPalletDto.setMapaBultos(mapaBultos);
               ordenDeProduccionPalletDto.setListaCodigoBultos(listaCodigos);

                Double pesoTotal = 0.0;
                if(!palletbultoList.isEmpty()) {
                    for(OrdenDeProduccionPalletBultoModel palletbulto: palletbultoList) {
                        OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(palletbulto.getIdOrdenDeProduccionBulto());
                        if(bulto != null) {
                            OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(bulto.getIdOrdenDeProduccionBobina());
                            if(bobina != null) {
                                pesoTotal += bobina.getPesoTotal();
                            }
                        }
                    }
                }
                ordenDeProduccionPalletDto.setPesoTotal(pesoTotal.toString());
                
                ordenDeProduccionPalletsDtos.add(ordenDeProduccionPalletDto);
                System.out.println("*** ordenDeProduccionPalletsDtos size:"+ordenDeProduccionPalletsDtos.size());
            }
        }        
        
        model.addAttribute("displayButtonCambiarEstadoFabricacion", "none");        
        model.addAttribute("displayButtonCambiarEstadoEmpaque", "none");    
        model.addAttribute("displayButtonCambiarEstadoPallet", "none");    
        model.addAttribute("displayButtonCambiarEstadoCompletado", "none");    

        model.addAttribute("bultoSelectedList", bultoSelectedList);
        model.addAttribute("bultoDisponibleList", bultoDisponibleList);
        model.addAttribute("tipoDetalle", "pallet");
        model.addAttribute("idOrdenDeProduccion", ordenDeProduccion.getId().toString());        
        model.addAttribute("clienteLabel", cliente.getRazonSocial());
        model.addAttribute("idArticulo", articulo.getId());
        model.addAttribute("articuloLabel", articulo.getDenominacion());
        model.addAttribute("fichaTecnicaVersionLabel", articuloFichaTecnica.getVersion());
        
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "edit");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("displayActionButtonScrap", displayActionButtonScrap);
        model.addAttribute("displaySearchBultoButton", displaySearchBultoButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("ordenDeProduccionPallets", ordenDeProduccionPalletsDtos);
        
        model.addAttribute("cantidadDeBobinasQueNoEstanEnBulto", this.cantidadDeBobinasQueNoEstanEnBulto);
        model.addAttribute("cantidadDeBultosQueNoEstanEnPallet", this.cantidadDeBultosQueNoEstanEnPallet);
        model.addAttribute("cantidadPallet", this.cantidadPallet);
        model.addAttribute("cantidadBobinasEnProduccion", this.cantidadBobinasEnProduccion);
        
        return "/ordendeproduccion/ordendeproducciondetalle";
    }        
    
    @RequestMapping(value = "/ordenDeProduccionDetalle/print/pallet/{ordendeproduccionpalletpk}", method = RequestMethod.GET)
    public String printPallet(@PathVariable String ordendeproduccionpalletpk, HttpServletRequest req, ModelMap model) throws Exception {
                                
        String ordenDeProduccion = "Sin información";
        String fechaAltaOrdenDeProduccion = "Sin información";
        String cliente = "Sin información";
        String articulo = "Sin información";
        String etiquetaArticulo = "Sin información";
        String articuloCodigo = "Sin información";
        String fichaTecnica = "Sin información";
        String codigoPallet = "Sin información";
        String pesoTotal = "Sin información";
        String cantidadBultos = "Sin información";
        String fechaAltaPallet = "Sin información";
        String fechaAltaLote = "Sin información";
        String fechaActual = "Sin información";
        String url = "http://localhost:8080/thyssenplastic/ordenDeProduccionDetalle/viewbobina/xxxx";
        
        Calendar c = Calendar.getInstance();        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        fechaActual = formato.format(c.getTime());
        
        if(ordendeproduccionpalletpk != null && !ordendeproduccionpalletpk.isEmpty()) {
            
            OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();
            OrdenDeProduccionBultoService ordenDeProduccionBultoService = new OrdenDeProduccionBultoServiceImpl();
            OrdenDeProduccionPalletBultoService ordenDeProduccionPalletBultoService = new OrdenDeProduccionPalletBultoServiceImpl();
            OrdenDeProduccionPalletService ordenDeProduccionPalletService = new OrdenDeProduccionPalletServiceImpl();   
            OrdenDeProduccionPalletModel ordenDeProduccionPallet = ordenDeProduccionPalletService.getByPk(Integer.valueOf(ordendeproduccionpalletpk));

            if(ordenDeProduccionPallet != null) {                
                codigoPallet = ordenDeProduccionPallet.getCodigo();
                fechaAltaPallet = formato.format(ordenDeProduccionPallet.getFechaAlta());
                url = "http://localhost:8080/thyssenplastic/ordenDeProduccionDetalle/viewpallet/"+ordenDeProduccionPallet.getId();
                
                List<OrdenDeProduccionPalletBultoModel> ordenDeProduccionPalletBultos = ordenDeProduccionPalletBultoService.getAllByOrdenDeProduccionPallet(ordenDeProduccionPallet.getId());
                if(ordenDeProduccionPalletBultos != null && !ordenDeProduccionPalletBultos.isEmpty()) {
                    Double pesoTotalDouble = 0.0;
                    cantidadBultos = String.valueOf(ordenDeProduccionPalletBultos.size());
                    for(OrdenDeProduccionPalletBultoModel ordenDeProduccionPalletBulto :ordenDeProduccionPalletBultos) {
                        OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(ordenDeProduccionPalletBulto.getIdOrdenDeProduccionBulto());                        
                        if(bulto != null) {
                            OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(bulto.getIdOrdenDeProduccionBobina());
                            if(bobina != null) {
                                pesoTotalDouble += bobina.getPesoTotal();
                            }
                        }
                    }
                    pesoTotal = pesoTotalDouble.toString();
                }
                        
                OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();   
                OrdenDeProduccionModel ordenDeProduccionModel = ordenDeProduccionService.getByPk(Integer.valueOf(ordenDeProduccionPallet.getIdOrdenDeProduccion()));
                if(ordenDeProduccionModel != null) {

                    ordenDeProduccion = ordenDeProduccionModel.getId().toString();
                    fechaAltaOrdenDeProduccion = ordenDeProduccionModel.getFechaAlta().toString().replace("00:00:00.0", "");
                    fechaAltaLote = formato.format(ordenDeProduccionModel.getFechaAltaImpresion());                    
                    
                    ClienteService clienteService = new ClienteServiceImpl();
                    ClienteModel clienteModel = clienteService.getByPk(ordenDeProduccionModel.getIdCliente());
                    if(clienteModel != null) {
                        cliente = clienteModel.getRazonSocial();
                    }

                    ArticuloService articuloService = new ArticuloServiceImpl();
                    ArticuloModel articuloModel = articuloService.getByPk(ordenDeProduccionModel.getIdArticulo());
                    if(articuloModel != null) {
                        articulo = articuloModel.getDenominacion();
                        articuloCodigo = articuloModel.getId().toString();
                    }

                    ArticuloFichaTecnicaService articuloFichaTecnicaService = new ArticuloFichaTecnicaServiceImpl();
                    ArticuloFichaTecnicaModel articuloFichaTecnica = articuloFichaTecnicaService.getByPk(ordenDeProduccionModel.getIdFichaTecnica());
                    if(articuloFichaTecnica != null) {
                        fichaTecnica = articuloFichaTecnica.getVersion().toString();
                    }
                    
                    ArticuloEtiquetaService articuloEtiquetaService = new ArticuloEtiquetaServiceImpl();
                    List<ArticuloEtiquetaModel> articulosEtiquetaModel = articuloEtiquetaService.getAllByArticulo(articuloModel.getId());
                    if(articulosEtiquetaModel != null && !articulosEtiquetaModel.isEmpty()) {
                        ArticuloEtiquetaModel articuloEtiquetaModel = articulosEtiquetaModel.get(0);
                        if(articuloEtiquetaModel.getLinea1() != null && !articuloEtiquetaModel.getLinea1().isEmpty()) {
                            etiquetaArticulo = articuloEtiquetaModel.getLinea1();
                        } 
                    }                                        
                }
            }
        } 
        
        model.addAttribute("ordenDeProduccion", ordenDeProduccion);
        model.addAttribute("fechaAltaOrdenDeProduccion", fechaAltaOrdenDeProduccion);
        model.addAttribute("cliente", cliente);
        model.addAttribute("articulo", articulo);
        model.addAttribute("etiquetaArticulo", etiquetaArticulo);
        model.addAttribute("articuloCodigo", articuloCodigo);
        model.addAttribute("fichaTecnica", fichaTecnica);
        model.addAttribute("codigoPallet", codigoPallet);
        model.addAttribute("pesoTotal", pesoTotal);        
        model.addAttribute("cantidadBultos", cantidadBultos);        
        model.addAttribute("fechaAltaLote", fechaAltaLote);        
        model.addAttribute("fechaAltaPallet", fechaAltaPallet);        
        model.addAttribute("fechaActual", fechaActual);        
        model.addAttribute("url", url);
        
        return "/ordendeproduccion/ordendeproduccionpalletprint"; 
    }    
    
    @RequestMapping(value = "/ordenDeProduccionDetalle/viewscrap/{ordenDeProduccionScrapPk}", method = RequestMethod.GET)
    public String viewOrdenDeProduccionScrap(@PathVariable String ordenDeProduccionScrapPk, HttpServletRequest req, ModelMap model) throws Exception {
        
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeProduccionScrapPk == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido");         
            return "/error";                
        }
        
        String operacion = "view";                
        String displayActionButton = "none";
        String displayActionButtonScrap = "none";

        OrdenDeProduccionScrapService ordenDeProduccionScrapService = new OrdenDeProduccionScrapServiceImpl();   
        OrdenDeProduccionScrapModel ordenDeProduccionScrap = ordenDeProduccionScrapService.getByPk(Integer.valueOf(ordenDeProduccionScrapPk));
        if(ordenDeProduccionScrap == null) {
            model.addAttribute("errorMessage", "Error: Orden De Produccion Scrap inválido. No ha sido encontrado.");         
            return "/error";    
        }
        
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();   
        OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(Integer.valueOf(ordenDeProduccionScrap.getIdOrdenDeProduccion()));
        if(ordenDeProduccion == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido. No ha sido encontrado.");         
            return "/error";    
        }
        
        /*
        if(!ordenDeProduccion.getEstado().equalsIgnoreCase("Abierto") && !ordenDeProduccion.getEstado().equalsIgnoreCase("Fabricacion")) {
            model.addAttribute("errorMessage", "Error: estado de orden incorrecto.");         
            return "/error";                            
        }
        */
        
        ClienteService clienteService = new ClienteServiceImpl();
        ClienteModel cliente = clienteService.getByPk(ordenDeProduccion.getIdCliente());
        if(cliente == null) {
            model.addAttribute("errorMessage", "Error: cliente no encontrado");         
            return "/error";                            
        }

        ArticuloService articuloService = new ArticuloServiceImpl();
        ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
        if(articulo == null) {
            model.addAttribute("errorMessage", "Error: articulo no encontrado");         
            return "/error";                            
        }
        
        ArticuloFichaTecnicaService articuloFichaTecnicaService = new ArticuloFichaTecnicaServiceImpl();
        ArticuloFichaTecnicaModel articuloFichaTecnica = articuloFichaTecnicaService.getByPk(ordenDeProduccion.getIdFichaTecnica());
        if(articuloFichaTecnica == null) {
            model.addAttribute("errorMessage", "Error: ficha tecnica no encontrada");         
            return "/error";                            
        }
        
        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(user.getRol() != Utils.ROL_OFICINA && user.getRol() != Utils.ROL_PLANTA && user.getRol() != Utils.ROL_DEPOSITO) {
            model.addAttribute("errorMessage", "Error: usuario no permite esta operación.");         
            return "/error";                                                                
        }
        
        String rol = "";
        if(user.getRol() == Utils.ROL_PLANTA) {
            rol = "planta";
        }
        if(user.getRol() == Utils.ROL_OFICINA) {
            rol = "oficina";
        }
        if(user.getRol() == Utils.ROL_DEPOSITO) {
            rol = "deposito";
        }

        
        OrdenDeProduccionDetalleForm ordenDeProduccionDetalleForm = new OrdenDeProduccionDetalleForm();
        ordenDeProduccionDetalleForm.setPk(ordenDeProduccionScrap.getId().toString());
        ordenDeProduccionDetalleForm.setAction("view");        
        ordenDeProduccionDetalleForm.setOperacion("view");   
        ordenDeProduccionDetalleForm.setTipoDetalle("scrap");
        ordenDeProduccionDetalleForm.setImprimir("false");
        
        ordenDeProduccionDetalleForm.setFechaAltaOrdenDeProduccion(ordenDeProduccion.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        ordenDeProduccionDetalleForm.setEstadoOrdenDeProduccion(ordenDeProduccion.getEstado());
        ordenDeProduccionDetalleForm.setIdOrdenProduccion(ordenDeProduccion.getId().toString());
        ordenDeProduccionDetalleForm.setIdCliente(ordenDeProduccion.getIdCliente().toString());
        ordenDeProduccionDetalleForm.setIdArticulo(ordenDeProduccion.getIdArticulo().toString());
        ordenDeProduccionDetalleForm.setIdFichaTecnica(ordenDeProduccion.getIdFichaTecnica().toString());
        ordenDeProduccionDetalleForm.setPesoTecnicoArticulo(articuloFichaTecnica.getPeso().toString());
        ordenDeProduccionDetalleForm.setMetrosArticulo(articuloFichaTecnica.getMetros().toString());
        ordenDeProduccionDetalleForm.setAltoArticulo(articulo.getAlto().toString());
        ordenDeProduccionDetalleForm.setEspesorArticulo(articulo.getEspesor().toString());
        ordenDeProduccionDetalleForm.setAnchoArticulo(articulo.getAncho().toString());

        TipoService tipoService = new TipoServiceImpl();
        
        String origen = "";
        String tipoMaterial = "";
        String motivo = "";
        String formato = "";        
        if(ordenDeProduccionScrap.getIdOrigen() != null) {
            TipoModel origenModel = tipoService.getByPk(ordenDeProduccionScrap.getIdOrigen());
            if(origenModel != null) {
                origen = origenModel.getId().toString();
            }
        }       
        ordenDeProduccionDetalleForm.setIdOrigenScrap(origen);
        if(ordenDeProduccionScrap.getIdTipoMaterial() != null) {
            TipoModel origenModel = tipoService.getByPk(ordenDeProduccionScrap.getIdTipoMaterial());
            if(origenModel != null) {
                tipoMaterial = origenModel.getId().toString();
            }
        }                        
        ordenDeProduccionDetalleForm.setIdTipoMaterialScrap(tipoMaterial);
        if(ordenDeProduccionScrap.getIdMotivo() != null) {
            TipoModel origenModel = tipoService.getByPk(ordenDeProduccionScrap.getIdMotivo());
            if(origenModel != null) {
                motivo = origenModel.getId().toString();
            }
        }                        
        ordenDeProduccionDetalleForm.setIdMotivoScrap(motivo);
        if(ordenDeProduccionScrap.getIdFormato() != null) {
            TipoModel origenModel = tipoService.getByPk(ordenDeProduccionScrap.getIdFormato());
            if(origenModel != null) {
                formato = origenModel.getId().toString();
            }
        }             
        ordenDeProduccionDetalleForm.setIdFormatoScrap(formato);
        if(ordenDeProduccionScrap.getEsRecuperable() != null && ordenDeProduccionScrap.getEsRecuperable()) {
            ordenDeProduccionDetalleForm.setEsRecuperableScrap("1");
        }else {
            ordenDeProduccionDetalleForm.setEsRecuperableScrap("0");
        }
        if(ordenDeProduccionScrap.getMaterialImpreso() != null && ordenDeProduccionScrap.getMaterialImpreso()) {
            ordenDeProduccionDetalleForm.setMaterialImpresoScrap("1");
        }else {
            ordenDeProduccionDetalleForm.setMaterialImpresoScrap("0");
        }
        if(ordenDeProduccionScrap.getPesoTotal() != null) {
            ordenDeProduccionDetalleForm.setPesoTotalScrap(ordenDeProduccionScrap.getPesoTotal().toString());
        }
        if(ordenDeProduccionScrap.getObservaciones() != null) {
            ordenDeProduccionDetalleForm.setObservacionesScrap(ordenDeProduccionScrap.getObservaciones().toString());
        }
                

        model.addAttribute("ordenDeProduccionDetalleForm", ordenDeProduccionDetalleForm);  
        model.addAttribute("titleOrdenDeProduccion", "Ver Orden de Producción Scrap");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("ordenDeProduccionScrapName", "Ver Scrap " + ordenDeProduccionScrap.getCodigo());        

        Map<String,String> origenScrapList = new LinkedHashMap<String,String>();
        List<TipoModel> origenScrapModel = tipoService.getByType("origenScrap");       
        if(origenScrapModel != null && !origenScrapModel.isEmpty()){
            for(TipoModel tipoModel :origenScrapModel) {
                origenScrapList.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        Map<String,String> tipoMaterialScrapList = new LinkedHashMap<String,String>();
        List<TipoModel> tipoMaterialScrapModel = tipoService.getByType("tipoMaterialScrap");       
        if(tipoMaterialScrapModel != null && !tipoMaterialScrapModel.isEmpty()){
            for(TipoModel tipoModel :tipoMaterialScrapModel) {
                tipoMaterialScrapList.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        Map<String,String> motivoScrapList = new LinkedHashMap<String,String>();
        List<TipoModel> motivoScrapModel = tipoService.getByType("motivoScrap");       
        if(motivoScrapModel != null && !motivoScrapModel.isEmpty()){
            for(TipoModel tipoModel :motivoScrapModel) {
                motivoScrapList.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        Map<String,String> formatoScrapList = new LinkedHashMap<String,String>();
        List<TipoModel> formatoScrapModel = tipoService.getByType("formatoScrap");       
        if(formatoScrapModel != null && !formatoScrapModel.isEmpty()){
            for(TipoModel tipoModel :formatoScrapModel) {
                formatoScrapList.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        
        List<OrdenDeProduccionScrapModel> ordenDeProduccionScrapsModel = ordenDeProduccionScrapService.getAllByOrdenDeProduccion(ordenDeProduccion.getId());       
        List<OrdenDeProduccionScrapDto> ordenDeProduccionScrapsDtos = new ArrayList<OrdenDeProduccionScrapDto>();        
        
        if(ordenDeProduccionScrapsModel != null && !ordenDeProduccionScrapsModel.isEmpty()) {
            for(OrdenDeProduccionScrapModel ordenDeProduccionScrapModel: ordenDeProduccionScrapsModel) {
                OrdenDeProduccionScrapDto ordenDeProduccionScrapDto = new OrdenDeProduccionScrapDto();
                ordenDeProduccionScrapDto.setPk(ordenDeProduccionScrapModel.getId().toString());
                ordenDeProduccionScrapDto.setFechaAlta(ordenDeProduccionScrapModel.getFechaAlta().toString().replace(".0", ""));
                ordenDeProduccionScrapDto.setCodigo(ordenDeProduccionScrapModel.getCodigo());
                String origen2 = "";
                if(ordenDeProduccionScrapModel.getIdOrigen() != null) {
                    TipoModel origenModel = tipoService.getByPk(ordenDeProduccionScrapModel.getIdOrigen());
                    if(origenModel != null) {
                        origen2 = origenModel.getValor();
                    }
                }                        
                ordenDeProduccionScrapDto.setOrigen(origen2);
                String tipoMaterial2 = "";
                if(ordenDeProduccionScrapModel.getIdTipoMaterial() != null) {
                    TipoModel tipoMaterialModel = tipoService.getByPk(ordenDeProduccionScrapModel.getIdTipoMaterial());
                    if(tipoMaterialModel != null) {
                        tipoMaterial2 = tipoMaterialModel.getValor();
                    }
                }                                        
                ordenDeProduccionScrapDto.setTipoMaterial(tipoMaterial2);
                String motivo2 = "";
                if(ordenDeProduccionScrapModel.getIdMotivo() != null) {
                    TipoModel motivoModel = tipoService.getByPk(ordenDeProduccionScrapModel.getIdMotivo());
                    if(motivoModel != null) {
                        motivo2 = motivoModel.getValor();
                    }
                }                                                        
                ordenDeProduccionScrapDto.setMotivo(motivo2);
                String formato2 = "";
                if(ordenDeProduccionScrapModel.getIdFormato() != null) {
                    TipoModel formatoModel = tipoService.getByPk(ordenDeProduccionScrapModel.getIdFormato());
                    if(formatoModel != null) {
                        formato2 = formatoModel.getValor();
                    }
                }                                                                        
                ordenDeProduccionScrapDto.setFormato(formato2);
                if(ordenDeProduccionScrapModel.getEsRecuperable()) {
                    ordenDeProduccionScrapDto.setEsRecuperable("Si");
                } else {
                    ordenDeProduccionScrapDto.setEsRecuperable("No");
                }
                if(ordenDeProduccionScrapModel.getMaterialImpreso()) {
                    ordenDeProduccionScrapDto.setMaterialImpreso("Si");
                } else {
                    ordenDeProduccionScrapDto.setMaterialImpreso("No");
                }                
                ordenDeProduccionScrapDto.setPesoTotal(ordenDeProduccionScrapModel.getPesoTotal().toString());
                
                RemitoDetalleScrapService remitoDetalleScrapService = new RemitoDetalleScrapServiceImpl();
                List<RemitoDetalleScrapModel> remitosScrapDetalles = remitoDetalleScrapService.getAllByIdOrdenDeProduccionScrap(ordenDeProduccionScrapModel.getId());
                
                boolean puedoEliminarScrap = (remitosScrapDetalles.isEmpty() && ordenDeProduccionScrapModel.getIdBulto() == null && ordenDeProduccionScrapModel.getIdBobina() == null);
                
                ordenDeProduccionScrapDto.setPuedoBorrarlo(puedoEliminarScrap);
                
                ordenDeProduccionScrapsDtos.add(ordenDeProduccionScrapDto);
                
               
                this.cantidadScrap = ordenDeProduccionScrapsDtos.size();
               
                
                System.out.println("*** ordenDeProduccionScrapsDtos size:"+ordenDeProduccionScrapsDtos.size());
               

            }
        }
             
        model.addAttribute("displayButtonCambiarEstadoFabricacion", "none");        
        model.addAttribute("displayButtonCambiarEstadoEmpaque", "none");    
        model.addAttribute("displayButtonCambiarEstadoPallet", "none");    
        model.addAttribute("displayButtonCambiarEstadoCompletado", "none");    

        model.addAttribute("origenScrapList", origenScrapList);
        model.addAttribute("tipoMaterialScrapList", tipoMaterialScrapList);
        model.addAttribute("motivoScrapList", motivoScrapList);
        model.addAttribute("formatoScrapList", formatoScrapList);
        
        model.addAttribute("tipoDetalle", "scrap");
        model.addAttribute("idOrdenDeProduccion", ordenDeProduccion.getId().toString());        
        model.addAttribute("clienteLabel", cliente.getRazonSocial());
        model.addAttribute("idArticulo", articulo.getId());
        model.addAttribute("articuloLabel", articulo.getDenominacion());
        model.addAttribute("fichaTecnicaVersionLabel", articuloFichaTecnica.getVersion());
        
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "view");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("displayActionButtonScrap", displayActionButtonScrap);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("ordenDeProduccionScraps", ordenDeProduccionScrapsDtos);
        
        model.addAttribute("cantidadScrap", this.cantidadScrap);
            
                                                                                                              
        return "/ordendeproduccion/ordendeproducciondetalle";     
    }    
    
    @RequestMapping(value = "/ordenDeProduccionDetalle/print/scrap/{ordendeproduccionscrappk}", method = RequestMethod.GET)
    public String printScrap(@PathVariable String ordendeproduccionscrappk, HttpServletRequest req, ModelMap model) throws Exception {
                                
        String ordenDeProduccion = "Sin información";
        String fechaAltaOrdenDeProduccion = "Sin información";
        String cliente = "Sin información";
        String articulo = "Sin información";
        String etiquetaArticulo = "Sin información";
        String articuloCodigo = "Sin información";
        String fichaTecnica = "Sin información";
        String codigoScrap = "Sin información";
        String pesoTotal = "Sin información";
        String fechaAltaScrap = "Sin información";
        String fechaAltaLote = "Sin información";
        String fechaActual = "Sin información";
        String url = "http://localhost:8080/thyssenplastic/ordenDeProduccionDetalle/viewbobina/xxxx";
        
        Calendar c = Calendar.getInstance();        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        fechaActual = formato.format(c.getTime());
        
        if(ordendeproduccionscrappk != null && !ordendeproduccionscrappk.isEmpty()) {
            
            OrdenDeProduccionScrapService ordenDeProduccionScrapService = new OrdenDeProduccionScrapServiceImpl();   
            OrdenDeProduccionScrapModel ordenDeProduccionScrap = ordenDeProduccionScrapService.getByPk(Integer.valueOf(ordendeproduccionscrappk));

            if(ordenDeProduccionScrap != null) {                
                codigoScrap = ordenDeProduccionScrap.getCodigo();
                pesoTotal = ordenDeProduccionScrap.getPesoTotal().toString();
                fechaAltaScrap = ordenDeProduccionScrap.getFechaAlta().toString();
                url = "http://localhost:8080/thyssenplastic/ordenDeProduccionDetalle/viewscrap/"+ordenDeProduccionScrap.getId();
                
                OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();   
                OrdenDeProduccionModel ordenDeProduccionModel = ordenDeProduccionService.getByPk(Integer.valueOf(ordenDeProduccionScrap.getIdOrdenDeProduccion()));
                if(ordenDeProduccionModel != null) {

                    ordenDeProduccion = ordenDeProduccionModel.getId().toString();
                    fechaAltaOrdenDeProduccion = ordenDeProduccionModel.getFechaAlta().toString().replace("00:00:00.0", "");
                    
                    fechaAltaLote =formato.format(ordenDeProduccionModel.getFechaAltaImpresion());
                    
                    ClienteService clienteService = new ClienteServiceImpl();
                    ClienteModel clienteModel = clienteService.getByPk(ordenDeProduccionModel.getIdCliente());
                    if(clienteModel != null) {
                        cliente = clienteModel.getRazonSocial();
                    }

                    ArticuloService articuloService = new ArticuloServiceImpl();
                    ArticuloModel articuloModel = articuloService.getByPk(ordenDeProduccionModel.getIdArticulo());
                    if(articuloModel != null) {
                        articulo = articuloModel.getDenominacion();                        
                        etiquetaArticulo = articuloModel.getDenominacion();                        
                        articuloCodigo = articuloModel.getId().toString();
                    }

                    ArticuloFichaTecnicaService articuloFichaTecnicaService = new ArticuloFichaTecnicaServiceImpl();
                    ArticuloFichaTecnicaModel articuloFichaTecnica = articuloFichaTecnicaService.getByPk(ordenDeProduccionModel.getIdFichaTecnica());
                    if(articuloFichaTecnica != null) {
                        fichaTecnica = articuloFichaTecnica.getVersion().toString();
                    }
                    
                    ArticuloEtiquetaService articuloEtiquetaService = new ArticuloEtiquetaServiceImpl();
                    List<ArticuloEtiquetaModel> articulosEtiquetaModel = articuloEtiquetaService.getAllByArticulo(articuloModel.getId());
                    if(articulosEtiquetaModel != null && !articulosEtiquetaModel.isEmpty()) {
                        ArticuloEtiquetaModel articuloEtiquetaModel = articulosEtiquetaModel.get(0);
                        if(articuloEtiquetaModel.getLinea1() != null && !articuloEtiquetaModel.getLinea1().isEmpty()) {
                            etiquetaArticulo = articuloEtiquetaModel.getLinea1();
                        } 
                    }
                    
                }
            }
        } 
        
        model.addAttribute("ordenDeProduccion", ordenDeProduccion);
        model.addAttribute("fechaAltaOrdenDeProduccion", fechaAltaOrdenDeProduccion);
        model.addAttribute("cliente", cliente);
        model.addAttribute("articulo", articulo);
        model.addAttribute("etiquetaArticulo", etiquetaArticulo);
        model.addAttribute("articuloCodigo", articuloCodigo);
        model.addAttribute("fichaTecnica", fichaTecnica);
        model.addAttribute("codigoScrap", codigoScrap);
        model.addAttribute("pesoTotal", pesoTotal);        
        model.addAttribute("fechaAltaScrap", fechaAltaScrap);        
        model.addAttribute("fechaAltaLote", fechaAltaLote);        
        model.addAttribute("fechaActual", fechaActual);                
        model.addAttribute("url", url);
        
        return "/ordendeproduccion/ordendeproduccionscrapprint"; 
    }    
    
    @RequestMapping(value = "/ordenDeProduccionDetalle/removeScrap/{ordenDeProduccionScrapPk}", method = RequestMethod.GET)
    public String removeOrdenDeProduccionScrap(@PathVariable String ordenDeProduccionScrapPk, HttpServletRequest req, ModelMap model) throws Exception {
        
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(ordenDeProduccionScrapPk == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido");         
            return "/error";                
        }
        
        String operacion = "remove";                
        String displayActionButton = "none";
        String displayActionButtonScrap = "block";
        String displaySearchBobinaButton = "none";

        OrdenDeProduccionScrapService ordenDeProduccionScrapService = new OrdenDeProduccionScrapServiceImpl();   
        OrdenDeProduccionScrapModel ordenDeProduccionScrap = ordenDeProduccionScrapService.getByPk(Integer.valueOf(ordenDeProduccionScrapPk));
        if(ordenDeProduccionScrap == null) {
            model.addAttribute("errorMessage", "Error: Orden De Produccion Scrap inválido. No ha sido encontrado.");         
            return "/error";    
        }
        
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();   
        OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(Integer.valueOf(ordenDeProduccionScrap.getIdOrdenDeProduccion()));
        if(ordenDeProduccion == null) {
            model.addAttribute("errorMessage", "Error: OrdenDeProduccion inválido. No ha sido encontrado.");         
            return "/error";    
        }
         
        /*
        if(!ordenDeProduccion.getEstado().equalsIgnoreCase("Abierto") && !ordenDeProduccion.getEstado().equalsIgnoreCase("Fabricacion")) {
            model.addAttribute("errorMessage", "Error: estado de orden incorrecto.");         
            return "/error";                            
        }
        */
        
        ClienteService clienteService = new ClienteServiceImpl();
        ClienteModel cliente = clienteService.getByPk(ordenDeProduccion.getIdCliente());
        if(cliente == null) {
            model.addAttribute("errorMessage", "Error: cliente no encontrado");         
            return "/error";                            
        }

        ArticuloService articuloService = new ArticuloServiceImpl();
        ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
        if(articulo == null) {
            model.addAttribute("errorMessage", "Error: articulo no encontrado");         
            return "/error";                            
        }
        
        ArticuloFichaTecnicaService articuloFichaTecnicaService = new ArticuloFichaTecnicaServiceImpl();
        ArticuloFichaTecnicaModel articuloFichaTecnica = articuloFichaTecnicaService.getByPk(ordenDeProduccion.getIdFichaTecnica());
        if(articuloFichaTecnica == null) {
            model.addAttribute("errorMessage", "Error: ficha tecnica no encontrada");         
            return "/error";                            
        }
        
        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(user.getRol() != Utils.ROL_OFICINA && user.getRol() != Utils.ROL_PLANTA) {
            model.addAttribute("errorMessage", "Error: usuario no permite esta operación.");         
            return "/error";                                                                
        }
        
        String rol = "";
        if(user.getRol() == Utils.ROL_OFICINA) {
            rol = "oficina";
        }
        if(user.getRol() == Utils.ROL_PLANTA) {
            rol = "planta";
        }
        
        OrdenDeProduccionDetalleForm ordenDeProduccionDetalleForm = new OrdenDeProduccionDetalleForm();
        ordenDeProduccionDetalleForm.setPk(ordenDeProduccionScrap.getId().toString());
        ordenDeProduccionDetalleForm.setAction("remove");        
        ordenDeProduccionDetalleForm.setOperacion("remove");   
        ordenDeProduccionDetalleForm.setTipoDetalle("scrap");
        ordenDeProduccionDetalleForm.setImprimir("false");
        
        ordenDeProduccionDetalleForm.setFechaAltaOrdenDeProduccion(ordenDeProduccion.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        ordenDeProduccionDetalleForm.setEstadoOrdenDeProduccion(ordenDeProduccion.getEstado());
        ordenDeProduccionDetalleForm.setIdOrdenProduccion(ordenDeProduccion.getId().toString());
        ordenDeProduccionDetalleForm.setIdCliente(ordenDeProduccion.getIdCliente().toString());
        ordenDeProduccionDetalleForm.setIdArticulo(ordenDeProduccion.getIdArticulo().toString());
        ordenDeProduccionDetalleForm.setIdFichaTecnica(ordenDeProduccion.getIdFichaTecnica().toString());
        ordenDeProduccionDetalleForm.setPesoTecnicoArticulo(articuloFichaTecnica.getPeso().toString());
        ordenDeProduccionDetalleForm.setMetrosArticulo(articuloFichaTecnica.getMetros().toString());
        ordenDeProduccionDetalleForm.setAltoArticulo(articulo.getAlto().toString());
        ordenDeProduccionDetalleForm.setEspesorArticulo(articulo.getEspesor().toString());
        ordenDeProduccionDetalleForm.setAnchoArticulo(articulo.getAncho().toString());

        TipoService tipoService = new TipoServiceImpl();
        
        String origen = "";
        String tipoMaterial = "";
        String motivo = "";
        String formato = "";        
        if(ordenDeProduccionScrap.getIdOrigen() != null) {
            TipoModel origenModel = tipoService.getByPk(ordenDeProduccionScrap.getIdOrigen());
            if(origenModel != null) {
                origen = origenModel.getId().toString();
            }
        }       
        ordenDeProduccionDetalleForm.setIdOrigenScrap(origen);
        if(ordenDeProduccionScrap.getIdTipoMaterial() != null) {
            TipoModel origenModel = tipoService.getByPk(ordenDeProduccionScrap.getIdTipoMaterial());
            if(origenModel != null) {
                tipoMaterial = origenModel.getId().toString();
            }
        }                        
        ordenDeProduccionDetalleForm.setIdTipoMaterialScrap(tipoMaterial);
        if(ordenDeProduccionScrap.getIdMotivo() != null) {
            TipoModel origenModel = tipoService.getByPk(ordenDeProduccionScrap.getIdMotivo());
            if(origenModel != null) {
                motivo = origenModel.getId().toString();
            }
        }                        
        ordenDeProduccionDetalleForm.setIdMotivoScrap(motivo);
        if(ordenDeProduccionScrap.getIdFormato() != null) {
            TipoModel origenModel = tipoService.getByPk(ordenDeProduccionScrap.getIdFormato());
            if(origenModel != null) {
                formato = origenModel.getId().toString();
            }
        }             
        ordenDeProduccionDetalleForm.setIdFormatoScrap(formato);
        if(ordenDeProduccionScrap.getEsRecuperable() != null && ordenDeProduccionScrap.getEsRecuperable()) {
            ordenDeProduccionDetalleForm.setEsRecuperableScrap("1");
        }else {
            ordenDeProduccionDetalleForm.setEsRecuperableScrap("0");
        }
        if(ordenDeProduccionScrap.getMaterialImpreso() != null && ordenDeProduccionScrap.getMaterialImpreso()) {
            ordenDeProduccionDetalleForm.setMaterialImpresoScrap("1");
        }else {
            ordenDeProduccionDetalleForm.setMaterialImpresoScrap("0");
        }
        if(ordenDeProduccionScrap.getPesoTotal() != null) {
            ordenDeProduccionDetalleForm.setPesoTotalScrap(ordenDeProduccionScrap.getPesoTotal().toString());
        }
        if(ordenDeProduccionScrap.getObservaciones() != null) {
            ordenDeProduccionDetalleForm.setObservacionesScrap(ordenDeProduccionScrap.getObservaciones().toString());
        }
                
        model.addAttribute("ordenDeProduccionDetalleForm", ordenDeProduccionDetalleForm);  
        model.addAttribute("titleOrdenDeProduccion", "Elinminar Orden de Producción Scrap");
        model.addAttribute("buttonLabel", "Eliminar");        
        model.addAttribute("ordenDeProduccionScrapName", "Ver Scrap " + ordenDeProduccionScrap.getCodigo());               
                
        Map<String,String> origenScrapList = new LinkedHashMap<String,String>();
        List<TipoModel> origenScrapModel = tipoService.getByType("origenScrap");       
        if(origenScrapModel != null && !origenScrapModel.isEmpty()){
            for(TipoModel tipoModel :origenScrapModel) {
                origenScrapList.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        Map<String,String> tipoMaterialScrapList = new LinkedHashMap<String,String>();
        List<TipoModel> tipoMaterialScrapModel = tipoService.getByType("tipoMaterialScrap");       
        if(tipoMaterialScrapModel != null && !tipoMaterialScrapModel.isEmpty()){
            for(TipoModel tipoModel :tipoMaterialScrapModel) {
                tipoMaterialScrapList.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        Map<String,String> motivoScrapList = new LinkedHashMap<String,String>();
        List<TipoModel> motivoScrapModel = tipoService.getByType("motivoScrap");       
        if(motivoScrapModel != null && !motivoScrapModel.isEmpty()){
            for(TipoModel tipoModel :motivoScrapModel) {
                motivoScrapList.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        Map<String,String> formatoScrapList = new LinkedHashMap<String,String>();
        List<TipoModel> formatoScrapModel = tipoService.getByType("formatoScrap");       
        if(formatoScrapModel != null && !formatoScrapModel.isEmpty()){
            for(TipoModel tipoModel :formatoScrapModel) {
                formatoScrapList.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }        
        List<OrdenDeProduccionScrapModel> ordenDeProduccionScrapsModel = ordenDeProduccionScrapService.getAllByOrdenDeProduccion(ordenDeProduccion.getId());       
        List<OrdenDeProduccionScrapDto> ordenDeProduccionScrapsDtos = new ArrayList<OrdenDeProduccionScrapDto>();        
        
        if(ordenDeProduccionScrapsModel != null && !ordenDeProduccionScrapsModel.isEmpty()) {
            for(OrdenDeProduccionScrapModel ordenDeProduccionScrapModel: ordenDeProduccionScrapsModel) {
                OrdenDeProduccionScrapDto ordenDeProduccionScrapDto = new OrdenDeProduccionScrapDto();
                ordenDeProduccionScrapDto.setPk(ordenDeProduccionScrapModel.getId().toString());
                ordenDeProduccionScrapDto.setFechaAlta(ordenDeProduccionScrapModel.getFechaAlta().toString().replace(".0", ""));
                ordenDeProduccionScrapDto.setCodigo(ordenDeProduccionScrapModel.getCodigo());
                String origen2 = "";
                if(ordenDeProduccionScrapModel.getIdOrigen() != null) {
                    TipoModel origenModel = tipoService.getByPk(ordenDeProduccionScrapModel.getIdOrigen());
                    if(origenModel != null) {
                        origen2 = origenModel.getValor();
                    }
                }                        
                ordenDeProduccionScrapDto.setOrigen(origen2);
                String tipoMaterial2 = "";
                if(ordenDeProduccionScrapModel.getIdTipoMaterial() != null) {
                    TipoModel tipoMaterialModel = tipoService.getByPk(ordenDeProduccionScrapModel.getIdTipoMaterial());
                    if(tipoMaterialModel != null) {
                        tipoMaterial2 = tipoMaterialModel.getValor();
                    }
                }                                        
                ordenDeProduccionScrapDto.setTipoMaterial(tipoMaterial2);
                String motivo2 = "";
                if(ordenDeProduccionScrapModel.getIdMotivo() != null) {
                    TipoModel motivoModel = tipoService.getByPk(ordenDeProduccionScrapModel.getIdMotivo());
                    if(motivoModel != null) {
                        motivo2 = motivoModel.getValor();
                    }
                }                                                        
                ordenDeProduccionScrapDto.setMotivo(motivo2);
                String formato2 = "";
                if(ordenDeProduccionScrapModel.getIdFormato() != null) {
                    TipoModel formatoModel = tipoService.getByPk(ordenDeProduccionScrapModel.getIdFormato());
                    if(formatoModel != null) {
                        formato2 = formatoModel.getValor();
                    }
                }                                                                        
                ordenDeProduccionScrapDto.setFormato(formato2);
                if(ordenDeProduccionScrapModel.getEsRecuperable()) {
                    ordenDeProduccionScrapDto.setEsRecuperable("Si");
                } else {
                    ordenDeProduccionScrapDto.setEsRecuperable("No");
                }
                if(ordenDeProduccionScrapModel.getMaterialImpreso()) {
                    ordenDeProduccionScrapDto.setMaterialImpreso("Si");
                } else {
                    ordenDeProduccionScrapDto.setMaterialImpreso("No");
                }                
                ordenDeProduccionScrapDto.setPesoTotal(ordenDeProduccionScrapModel.getPesoTotal().toString());
                
                RemitoDetalleScrapService remitoDetalleScrapService = new RemitoDetalleScrapServiceImpl();
                List<RemitoDetalleScrapModel> remitosScrapDetalles = remitoDetalleScrapService.getAllByIdOrdenDeProduccionScrap(ordenDeProduccionScrapModel.getId());
                
                boolean puedoEliminarScrap = (remitosScrapDetalles.isEmpty() && ordenDeProduccionScrapModel.getIdBulto() == null && ordenDeProduccionScrapModel.getIdBobina() == null);
                
                ordenDeProduccionScrapDto.setPuedoBorrarlo(puedoEliminarScrap);
                
                ordenDeProduccionScrapsDtos.add(ordenDeProduccionScrapDto);
                
               
                this.cantidadScrap = ordenDeProduccionScrapsDtos.size();
               
                
                System.out.println("*** ordenDeProduccionScrapsDtos size:"+ordenDeProduccionScrapsDtos.size());
               

            }
        }
        
        model.addAttribute("displayButtonCambiarEstadoFabricacion", "none");        
        model.addAttribute("displayButtonCambiarEstadoEmpaque", "none");    
        model.addAttribute("displayButtonCambiarEstadoPallet", "none");    
        model.addAttribute("displayButtonCambiarEstadoCompletado", "none");    

        model.addAttribute("tipoDetalle", "scrap");
        model.addAttribute("idOrdenDeProduccion", ordenDeProduccion.getId().toString());        
        model.addAttribute("clienteLabel", cliente.getRazonSocial());
        model.addAttribute("idArticulo", articulo.getId());
        model.addAttribute("articuloLabel", articulo.getDenominacion());
        model.addAttribute("fichaTecnicaVersionLabel", articuloFichaTecnica.getVersion());
        
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "remove");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("displayActionButtonScrap", displayActionButtonScrap);
        model.addAttribute("displaySearchBobinaButton", displaySearchBobinaButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("ordenDeProduccionScraps", ordenDeProduccionScrapsDtos);    
        model.addAttribute("origenScrapList", origenScrapList);
        model.addAttribute("tipoMaterialScrapList", tipoMaterialScrapList);
        model.addAttribute("motivoScrapList", motivoScrapList);
        model.addAttribute("formatoScrapList", formatoScrapList);
        
        return "/ordendeproduccion/ordendeproducciondetalle";
        
    }    
}
