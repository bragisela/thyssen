/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

 
import com.empresa.thyssenplastic.controller.form.TransferirBobinaForm;
import com.empresa.thyssenplastic.controller.form.UserForm;
import com.empresa.thyssenplastic.model.ArticuloFichaTecnicaModel;
import com.empresa.thyssenplastic.model.ArticuloModel;
import com.empresa.thyssenplastic.model.ClienteModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionBobinaModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionModel;
import com.empresa.thyssenplastic.model.UserModel;
import com.empresa.thyssenplastic.service.ArticuloFichaTecnicaService;
import com.empresa.thyssenplastic.service.ArticuloService;
import com.empresa.thyssenplastic.service.ClienteService;
import com.empresa.thyssenplastic.service.OrdenDeCompraService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionBobinaService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionService;
import com.empresa.thyssenplastic.service.UserService;
import com.empresa.thyssenplastic.service.impl.ArticuloFichaTecnicaServiceImpl;
import com.empresa.thyssenplastic.service.impl.ArticuloServiceImpl;
import com.empresa.thyssenplastic.service.impl.ClienteServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeCompraServiceImpl;
import com.empresa.thyssenplastic.utils.Utils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionBobinaServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionServiceImpl;
import com.empresa.thyssenplastic.service.impl.UserServiceImpl;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author gusta
 */
@Controller
public class TransferirBobinaController {
    

    @RequestMapping(value = "/transferirBobina/{idOrdenDeProduccionBobina}", method = RequestMethod.GET)
    public String getHomeTransferirBobina(@PathVariable String idOrdenDeProduccionBobina, HttpServletRequest req, ModelMap model) {

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(idOrdenDeProduccionBobina == null && idOrdenDeProduccionBobina.isEmpty()) {
            model.addAttribute("errorMessage", "Error:  bobina inválida");         
            return "/error";                
        }

        if(user.getRol() != Utils.ROL_OFICINA) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
        
        OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();   
        OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(Integer.valueOf(idOrdenDeProduccionBobina));

        if(bobina == null) {
            model.addAttribute("errorMessage", "Error: bobina no encontrada");         
            return "/error";                
        }

        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();   
        OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(bobina.getIdOrdenDeProduccion());
        
        if(ordenDeProduccion == null) {
            model.addAttribute("errorMessage", "Error: bobina no encontrada");         
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
        String displayActionButton = "none";
        
        TransferirBobinaForm transferirBobinaForm = new TransferirBobinaForm();
        transferirBobinaForm.setPk("-1");
        transferirBobinaForm.setAction("transferir");
        transferirBobinaForm.setIdBobina(bobina.getId().toString());
        transferirBobinaForm.setIdOrdenDeProduccion(ordenDeProduccion.getId().toString());
        transferirBobinaForm.setIdCliente(ordenDeProduccion.getIdCliente().toString());
        transferirBobinaForm.setIdArticulo(ordenDeProduccion.getIdArticulo().toString());
        transferirBobinaForm.setIdFichaTecnica(ordenDeProduccion.getIdFichaTecnica().toString());        
        transferirBobinaForm.setPesoCono(bobina.getPesoCono().toString());
        transferirBobinaForm.setPesoTotal(bobina.getPesoTotal().toString());
        transferirBobinaForm.setPesoNeto(bobina.getPesoNeto().toString());
        
        if(user.getRol() == Utils.ROL_OFICINA) {
            operacion = "alta";
            displayActionButton = "block";
            rol = "oficina";
        }
                
        transferirBobinaForm.setOperacion(operacion);
        
        Map<String,String> ordenesDeProduccion = new LinkedHashMap<String,String>();        
        List<OrdenDeProduccionModel> ordenesDeProduccionNotCompleted = ordenDeProduccionService.getNotCompleted();
        if(ordenesDeProduccionNotCompleted != null && !ordenesDeProduccionNotCompleted.isEmpty()) {
            for(OrdenDeProduccionModel ordenDeProduccionModel :ordenesDeProduccionNotCompleted) {
                if(ordenDeProduccionModel.getId() != ordenDeProduccion.getId()) {
                    ordenesDeProduccion.put(ordenDeProduccionModel.getId().toString(), "orden de produccion Nro. " + ordenDeProduccionModel.getId());
                }
            }            
        }
        
        model.addAttribute("transferirBobinaForm", transferirBobinaForm); 
        model.addAttribute("titleTransferirBobina", "Transferir Bobina");
        model.addAttribute("buttonLabel", "Transferir");
        
                
        model.addAttribute("idBobina", bobina.getCodigo());                
        model.addAttribute("idOrdenDeProduccion", ordenDeProduccion.getId().toString());                
        model.addAttribute("idArticulo", articulo.getId());        
        model.addAttribute("clienteLabel", cliente.getRazonSocial());        
        model.addAttribute("articuloLabel", articulo.getDenominacion());
        model.addAttribute("fichaTecnicaVersionLabel", articuloFichaTecnica.getVersion());
                       
        model.addAttribute("rol", rol);
        model.addAttribute("action", "transferir");
        model.addAttribute("operacion", operacion);
        model.addAttribute("displayActionButton", displayActionButton);        
        model.addAttribute("ordenesDeProduccionList", ordenesDeProduccion);                
                
        return "/ordendeproduccion/ordendeproduccionbobinatransferir";
    }
    
    @RequestMapping(value = "/transferirBobina/addOrEditOrRemove", method = RequestMethod.POST)
    public ModelAndView addOrEditOrRemoveTransferirBobina(@ModelAttribute("transferirBobinaForm")TransferirBobinaForm transferirBobinaForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {
        
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
        
        if(transferirBobinaForm.getAction() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;            
        }
        
        if(user.getRol() != Utils.ROL_OFICINA) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: usuario no puede realizar esta operacion");
            return modelAndView;            
        }
        
        if(transferirBobinaForm.getIdOrdenDeProduccionATransferir() == null || transferirBobinaForm.getIdOrdenDeProduccionATransferir().equalsIgnoreCase("-1")) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: orden de produccion a transferir inválida");
            return modelAndView;                        
        }
                
        String operacion = transferirBobinaForm.getOperacion();
        
        if(operacion == null || !operacion.equalsIgnoreCase("alta")) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;                        
        }        
        if(transferirBobinaForm.getPk().equalsIgnoreCase("-1") && !operacion.equalsIgnoreCase("alta")) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;                        
        }
        
        if(transferirBobinaForm.getIdBobina() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error bobina null");
            return modelAndView;                                    
        }
        
        OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();        
        OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(Integer.valueOf(transferirBobinaForm.getIdBobina()));
        if(bobina == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error bobina no encontrada");
            return modelAndView;                                                
        }
        
        if(bobina.getEstaEnBulto()) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error bobina se encuentra eb bulto");
            return modelAndView;                                                            
        }

        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();   
        OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(bobina.getIdOrdenDeProduccion());
        
        if(ordenDeProduccion == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error orden de produccion de bobina no encontrada");
            return modelAndView;                                                                        
        }

        if(ordenDeProduccion.getEstado().equalsIgnoreCase("Completado")) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error orden de produccion de bobina en estado completado");
            return modelAndView;                                                                                    
        }
        
        OrdenDeProduccionModel ordenDeProduccionAtransferir = ordenDeProduccionService.getByPk(Integer.valueOf(transferirBobinaForm.getIdOrdenDeProduccionATransferir()));
        
        if(ordenDeProduccionAtransferir == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error orden de produccion a transferir no encontrada");
            return modelAndView;                                                                        
        }

        if(ordenDeProduccionAtransferir.getEstado().equalsIgnoreCase("Completado")) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error orden de produccion a transferir en estado completado");
            return modelAndView;                                                                                    
        }
        
        
        bobina.setIdOrdenDeProduccion(ordenDeProduccionAtransferir.getId());
        ordenDeProduccionBobinaService.save(bobina);
        
        /*
        TransferirBobinaService transferirBobinaService = new TransferirBobinaServiceImpl();        
        String id = transferirBobinaForm.getPk();
            
        TransferirBobinaModel transferirBobinaModel = null;
        if(id.equalsIgnoreCase("-1")) {
            transferirBobinaModel = new TransferirBobinaModel();
            transferirBobinaModel.setIdOrdenDeProduccionBobina(bobina.getId());
            transferirBobinaModel.setFechaAlta(new Date());
            transferirBobinaModel.setIdUsuario(user.getId());
        } else {
            transferirBobinaModel = transferirBobinaService.getByPk(Integer.valueOf(id));
            if(transferirBobinaModel == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: id de transferirBobina inválido.");
                return modelAndView;                
            } 
        }        
        if(transferirBobinaForm.getEspesorNominal() != null) {
            transferirBobinaModel.setEspesorNominal(Double.valueOf(transferirBobinaForm.getEspesorNominal()));
        } else {
            transferirBobinaModel.setEspesorNominal(null);
        }
        if(transferirBobinaForm.getEspesorNominalMas() != null) {
            transferirBobinaModel.setEspesorNominalMas(Double.valueOf(transferirBobinaForm.getEspesorNominalMas()));
        } else {
            transferirBobinaModel.setEspesorNominalMas(null);
        }
        if(transferirBobinaForm.getEspesorNominalMenos() != null) {
            transferirBobinaModel.setEspesorNominalMenos(Double.valueOf(transferirBobinaForm.getEspesorNominalMenos()));
        } else {
            transferirBobinaModel.setEspesorNominalMenos(null);
        }
        
        if(transferirBobinaForm.getAction().equalsIgnoreCase("add") || transferirBobinaForm.getAction().equalsIgnoreCase("edit")) {
            transferirBobinaService.save(transferirBobinaModel);
        } else {
            if(transferirBobinaForm.getAction().equalsIgnoreCase("remove")) {
                if(transferirBobinaModel.getId() == null) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: id de transferirBobina inválido.");
                    return modelAndView;                                    
                }                
                transferirBobinaService.delete(transferirBobinaModel);
            } else {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: operación inválida.");
                return modelAndView;                                
            }
        }
        */
        
        modelAndView.setViewName("redirect:/ordenDeProduccionDetalle/"+ordenDeProduccionAtransferir.getId());

        return modelAndView; 
    }


}
