/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

 
import com.empresa.thyssenplastic.controller.beans.ItemBean;
import com.empresa.thyssenplastic.controller.form.IngresarDepositoForm;
import com.empresa.thyssenplastic.controller.form.EgresoDepositoForm;
import com.empresa.thyssenplastic.controller.form.UserForm;
import com.empresa.thyssenplastic.dto.IngresarDepositoDto;
import com.empresa.thyssenplastic.dto.OrdenDeProduccionBobinaDto;
import com.empresa.thyssenplastic.dto.OrdenDeProduccionBultoDto;
import com.empresa.thyssenplastic.model.ActivacionManualModel;
import com.empresa.thyssenplastic.model.ArticuloModel;
import com.empresa.thyssenplastic.model.EgresoDepositoModel;
import com.empresa.thyssenplastic.model.IngresarDepositoModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionBobinaModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionBultoModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionPalletBultoModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionPalletModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionScrapModel;
import com.empresa.thyssenplastic.model.RemitoDetalleModel;
import com.empresa.thyssenplastic.model.RemitoModel;
import com.empresa.thyssenplastic.model.TipoModel;
import com.empresa.thyssenplastic.model.UserModel;
import com.empresa.thyssenplastic.service.ActivacionManualService;
import com.empresa.thyssenplastic.service.ArticuloService;
import com.empresa.thyssenplastic.service.EgresoDepositoService;
import com.empresa.thyssenplastic.service.IngresarDepositoService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionBobinaService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionBultoService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionPalletBultoService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionPalletService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionScrapService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionService;
import com.empresa.thyssenplastic.service.RemitoDetalleService;
import com.empresa.thyssenplastic.service.RemitoService;
import com.empresa.thyssenplastic.service.TipoService;
import com.empresa.thyssenplastic.service.UserService;
import com.empresa.thyssenplastic.service.impl.ActivacionManualServiceImpl;
import com.empresa.thyssenplastic.service.impl.ArticuloServiceImpl;
import com.empresa.thyssenplastic.service.impl.EgresoDepositoServiceImpl;
import com.empresa.thyssenplastic.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.empresa.thyssenplastic.service.impl.IngresarDepositoServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionBobinaServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionBultoServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionPalletBultoServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionPalletServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionScrapServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionServiceImpl;
import com.empresa.thyssenplastic.service.impl.RemitoDetalleServiceImpl;
import com.empresa.thyssenplastic.service.impl.RemitoServiceImpl;
import com.empresa.thyssenplastic.service.impl.TipoServiceImpl;
import com.empresa.thyssenplastic.service.impl.UserServiceImpl;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author gusta
 */
@Controller
public class IngresarDepositoController {
    

    @RequestMapping(value = "/ingresarDeposito", method = RequestMethod.GET)
    public String getHomeIngresarDeposito(HttpServletRequest req, ModelMap model) {

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        IngresarDepositoForm ingresarDepositoForm = new IngresarDepositoForm();
        ingresarDepositoForm.setPk("-1");
        ingresarDepositoForm.setAction("add");
        ingresarDepositoForm.setIdBobina("-1");
        ingresarDepositoForm.setIdBulto("-1");
        ingresarDepositoForm.setIdPallet("-1");
        ingresarDepositoForm.setTipo("-1");
        ingresarDepositoForm.setIdOrdenDeProduccion("-1");
        
        
        model.addAttribute("ingresarDepositoForm", ingresarDepositoForm);  
        model.addAttribute("titleIngresarDeposito", "Nuevo Ingresar a Deposito");  
        model.addAttribute("buttonLabel", "Guardar");
        
        OrdenDeProduccionBultoService ordenDeProduccionBultoService = new OrdenDeProduccionBultoServiceImpl();   
        OrdenDeProduccionPalletService ordenDeProduccionPalletService = new OrdenDeProduccionPalletServiceImpl(); 
        OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();   
        IngresarDepositoService ingresarDepositoService = new IngresarDepositoServiceImpl();   
        List<IngresarDepositoModel> ingresarDepositos = ingresarDepositoService.getAll();
        TipoService tipoService = new TipoServiceImpl();   
        
        List<IngresarDepositoDto> ingresarDepositosDtos = new ArrayList<IngresarDepositoDto>();
        for(IngresarDepositoModel ingresarDeposito: ingresarDepositos) {
            IngresarDepositoDto ingresarDepositoDto = new IngresarDepositoDto();
            ingresarDepositoDto.setPk(ingresarDeposito.getId().toString());
            ingresarDepositoDto.setFecha(ingresarDeposito.getFechaAlta().toString());
            if(ingresarDeposito.getIdBobina() != null) {
                OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(ingresarDeposito.getIdBobina());
                if(bobina != null) {
                    ingresarDepositoDto.setCodigo(bobina.getCodigo());
                    ingresarDepositoDto.setIdOrdenDeProduccion(bobina.getIdOrdenDeProduccion().toString());
                    ingresarDepositoDto.setUnidades(1);
                    if(bobina.getEstado() != null && !bobina.getEstado().equalsIgnoreCase("-1")) {
                        //bobina.setEstado(bobina.getEstado());
                        if(bobina.getEstado().equals("ok")) {
                            ingresarDepositoDto.setEstado("OK");
                        }
                        if(bobina.getEstado().equals("observado")) {
                            ingresarDepositoDto.setEstado("Observado");
                        }
                        if(bobina.getEstado().equals("rechazado")) {
                            ingresarDepositoDto.setEstado("Rechazado");
                        }
                        if(bobina.getEstado().equals("sinmesurar")) {
                            ingresarDepositoDto.setEstado("Sin Mesurar");
                        }                
                    }
                }
            }
            if(ingresarDeposito.getIdBulto() != null) {
                OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(ingresarDeposito.getIdBulto());
                if(bulto != null) {
                    ingresarDepositoDto.setCodigo(bulto.getCodigo());
                    ingresarDepositoDto.setIdOrdenDeProduccion(bulto.getIdOrdenDeProduccion().toString());
                    ingresarDepositoDto.setUnidades(1);
                    if(bulto.getEstado() != null && !bulto.getEstado().equalsIgnoreCase("-1")) {
                        //bobina.setEstado(bobina.getEstado());
                        if(bulto.getEstado().equals("ok")) {
                            ingresarDepositoDto.setEstado("OK");
                        }
                        if(bulto.getEstado().equals("observado")) {
                            ingresarDepositoDto.setEstado("Observado");
                        }
                        if(bulto.getEstado().equals("rechazado")) {
                            ingresarDepositoDto.setEstado("Rechazado");
                        }
                        if(bulto.getEstado().equals("sinmesurar")) {
                            ingresarDepositoDto.setEstado("Sin Mesurar");
                        }                
                    }
                }
            }
            if(ingresarDeposito.getIdPallet() != null) {
                OrdenDeProduccionPalletModel pallet = ordenDeProduccionPalletService.getByPk(ingresarDeposito.getIdPallet());
                OrdenDeProduccionPalletBultoService ordenDeProduccionPalletBultoService = new OrdenDeProduccionPalletBultoServiceImpl();
                if(pallet != null) {
                    ingresarDepositoDto.setCodigo(pallet.getCodigo());
                    ingresarDepositoDto.setIdOrdenDeProduccion(pallet.getIdOrdenDeProduccion().toString());
                    List<OrdenDeProduccionPalletBultoModel> palletbultoList = ordenDeProduccionPalletBultoService.getAllByOrdenDeProduccionPallet(pallet.getId());
                    ingresarDepositoDto.setUnidades(palletbultoList.size());
                    
                    String listaCodigos = "";
              
                    Map<String,String> mapaBultos = new LinkedHashMap<String,String>();
                    for(OrdenDeProduccionPalletBultoModel item :palletbultoList) {
                    OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(item.getIdOrdenDeProduccionBulto());
                    if(bulto != null) {
                     
                     mapaBultos.put(bulto.getIdOrdenDeProduccionBobina().toString(), bulto.getCodigo());
                     listaCodigos += bulto.getCodigo() + " ";
                    }
                   }
                    ingresarDepositoDto.setMapaBultos(mapaBultos);
                    ingresarDepositoDto.setListaBultos(listaCodigos);
      
                    if(pallet.getEstado() != null && !pallet.getEstado().equalsIgnoreCase("-1")) {
                        //bobina.setEstado(bobina.getEstado());
                        if(pallet.getEstado().equals("ok")) {
                            ingresarDepositoDto.setEstado("OK");
                        }
                        if(pallet.getEstado().equals("observado")) {
                            ingresarDepositoDto.setEstado("Observado");
                        }
                        if(pallet.getEstado().equals("rechazado")) {
                            ingresarDepositoDto.setEstado("Rechazado");
                        }
                        if(pallet.getEstado().equals("sinmesurar")) {
                            ingresarDepositoDto.setEstado("Sin Mesurar");
                        }                
                    }
                }                
            }            
            if(ingresarDeposito.getIdDeposito() != null) {
                TipoModel deposito = tipoService.getByPk(ingresarDeposito.getIdDeposito());
                if(deposito != null) {
                    ingresarDepositoDto.setDeposito(deposito.getValor());
                }
            }

            ingresarDepositosDtos.add(ingresarDepositoDto);
        }
                               
        Map<String,String> depositos = new LinkedHashMap<String,String>();
        List<TipoModel> tiposModel = tipoService.getByType("deposito");
        if(tiposModel != null && !tiposModel.isEmpty()){
            for(TipoModel tipoModel :tiposModel) {
                depositos.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }
        model.addAttribute("depositoList", depositos);
        model.addAttribute("idDeposito", "-1");        
        model.addAttribute("ingresarDepositos", ingresarDepositosDtos);

        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();
        
        List<OrdenDeProduccionModel> ordenesDeProduccionModel = ordenDeProduccionService.getAll();
        
        List<String> ordeness = new ArrayList<String>();
        Map<String, String> denominacionPorOrden = new Hashtable<String, String>();
        
        
        if(ordenesDeProduccionModel != null && !ordenesDeProduccionModel.isEmpty()){
            for(OrdenDeProduccionModel ordenDeProduccionModel :ordenesDeProduccionModel) {
                ordeness.add(ordenDeProduccionModel.getId().toString());
                // Verifica si ya existe la asociación para evitar reemplazar denominaciones
                if (!denominacionPorOrden.containsKey(ordenDeProduccionModel.getId().toString())) {
                   
                    OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(ordenDeProduccionModel.getId());

                    ArticuloService articuloService = new ArticuloServiceImpl();
                    ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());

                    String denominacion = (articulo != null) ? articulo.getDenominacion() : "-";
                    denominacionPorOrden.put(ordenDeProduccionModel.getId().toString(), denominacion);
                }
            }
        }
        
        Collections.reverse(ordeness);
        
        ActivacionManualService activacionManualService = new ActivacionManualServiceImpl();
        ActivacionManualModel activacionManual = activacionManualService.getByPk(1);
        UserService userService = new UserServiceImpl(); 
        
        String rol = ""; 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);
        
        if(user.getRol() == Utils.ROL_OFICINA) {
          
            rol = "oficina";
        }
        if(user.getRol() == Utils.ROL_PLANTA) {
          
            
            rol = "planta";
           
        }
        if(user.getRol() == Utils.ROL_DEPOSITO) {
           
            rol = "deposito";
        }
        
        model.addAttribute("statusAct", activacionManual.getActivacionManual() || rol.equalsIgnoreCase("oficina"));
       
        model.addAttribute("provinciaList", ordeness);
        model.addAttribute("denominacionPorOrden", denominacionPorOrden);

        model.addAttribute("rol", rol);        
                
        return "/ingresaradeposito/ingresaradeposito";
    }
    
    @RequestMapping(value = "/ingresarDeposito/addOrEditOrRemove", method = RequestMethod.POST)
    public ModelAndView addOrEditOrRemoveIngresarDeposito(@ModelAttribute("ingresarDepositoForm")IngresarDepositoForm ingresarDepositoForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {
        
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
        
        if(ingresarDepositoForm.getAction() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;            
        }
        
         List<String> codigos = ingresarDepositoForm.getCodigos();
         
        // Imprimir los códigos en consola
        if (codigos != null && !codigos.isEmpty()) {
            System.out.println("Lista de códigos recibida:");
            for (String codigo : codigos) {
                System.out.println(codigo);
            }
        } else {
            System.out.println("No se recibieron códigos.");
        }

        
        IngresarDepositoService ingresarDepositoService = new IngresarDepositoServiceImpl();        
        OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();
        OrdenDeProduccionBultoService ordenDeProduccionBultoService = new OrdenDeProduccionBultoServiceImpl();
        OrdenDeProduccionPalletService ordenDeProduccionPalletService = new OrdenDeProduccionPalletServiceImpl();
        OrdenDeProduccionPalletBultoService ordenDeProduccionPalletBultoService = new OrdenDeProduccionPalletBultoServiceImpl();
        TipoService tipoService = new TipoServiceImpl();
        
        String sessionId = req.getSession().getId();
        String id = ingresarDepositoForm.getPk();
            
        IngresarDepositoModel ingresarDepositoModel = null;
        
        TipoModel verificarDeposito = tipoService.getByPk(Integer.parseInt(ingresarDepositoForm.getIdDeposito()));
        
        boolean esDepositoScrap = false;
        
        if (verificarDeposito.getValor().equalsIgnoreCase("Scrap")){
            esDepositoScrap = true;
        }
        
        if (codigos != null && !codigos.isEmpty()) {
            System.out.println("Lista de códigos recibida:");
            for (String codigo : codigos) {
                ingresarDepositoModel = new IngresarDepositoModel();
                ingresarDepositoModel.setFechaAlta(new Date());
                ingresarDepositoModel.setIdUsuarioAlta(user.getId());
                if(ingresarDepositoForm.getAction().equalsIgnoreCase("add") || ingresarDepositoForm.getAction().equalsIgnoreCase("edit")) {
                    if (codigo.startsWith("B")) {
                        System.out.println("ENTRO POR BOBINA:");
                        OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByCode(codigo);
                        if(bobina != null) {
                           ingresarDepositoModel.setIdBobina(bobina.getId());
                           ingresarDepositoModel.setIdDeposito(Integer.valueOf(ingresarDepositoForm.getIdDeposito()));
                           bobina.setIdDeposito(Integer.valueOf(ingresarDepositoForm.getIdDeposito()));
                           ingresarDepositoService.save(ingresarDepositoModel);
                           ordenDeProduccionBobinaService.save(bobina);
                        }
                        if(bobina != null && esDepositoScrap ) {
                            OrdenDeProduccionScrapService ordenDeProduccionScrapService = new OrdenDeProduccionScrapServiceImpl();     
                            OrdenDeProduccionScrapModel ordenDeProduccionScrapModel = null;
                            ordenDeProduccionScrapModel = new OrdenDeProduccionScrapModel();

                            // Por ejemplo, asignar el ID de la Orden de Producción
                            ordenDeProduccionScrapModel.setId(null);
                            ordenDeProduccionScrapModel.setIdOrdenDeProduccion(bobina.getIdOrdenDeProduccion()); // Asegúrate de tener el ID disponible
                            ordenDeProduccionScrapModel.setFechaAlta(new Date()); // Asignar la fecha actual
                            ordenDeProduccionScrapModel.setEstado("Nuevo"); // Estado inicial
                            ordenDeProduccionScrapModel.setIdUsuarioAlta(Integer.valueOf(Utils.getUserLoggedId(req))); // Obtener el usuario logueado

                            // Asignar valores manualmente a los campos del Scrap
                            ordenDeProduccionScrapModel.setIdOrigen(null); // Ejemplo: Origen del Scrap, si tienes el valor
                            ordenDeProduccionScrapModel.setIdTipoMaterial(null); // Ejemplo: Tipo de material del Scrap
                            ordenDeProduccionScrapModel.setIdMotivo(null); // Ejemplo: Motivo del Scrap
                            ordenDeProduccionScrapModel.setIdFormato(null); // Ejemplo: Formato del Scrap

                            // Si es recuperable
                            ordenDeProduccionScrapModel.setEsRecuperable(Boolean.FALSE); // O asignarlo manualmente según la lógica

                            // Si es material impreso
                            ordenDeProduccionScrapModel.setMaterialImpreso(Boolean.FALSE); // O asignarlo manualmente

                            // Peso total del Scrap (valor simulado)
                            ordenDeProduccionScrapModel.setPesoTotal(bobina.getPesoNeto()); //Asignar el peso total manualmente
                            ordenDeProduccionScrapModel.setCantidadUtilizada(0.0);

                            ordenDeProduccionScrapModel.setIdBobina(bobina.getId());

                            // Observaciones
                            ordenDeProduccionScrapModel.setObservaciones("Bobina rechazada");
                            System.out.println("ID antes de persistir: " + ordenDeProduccionScrapModel.getId());

                            // Paso 3: Guardar el Scrap
                            ordenDeProduccionScrapService.save(ordenDeProduccionScrapModel);
                            //        // Asignar un código único basado en su ID, si es un nuevo Scrap
                            if (ordenDeProduccionScrapModel.getId() != null) {
                                ordenDeProduccionScrapModel.setCodigo("S" + ordenDeProduccionScrapModel.getId());
                                ordenDeProduccionScrapService.save(ordenDeProduccionScrapModel); // Guardar nuevamente con el código generado
                            }
                        }
                    }
                    if (codigo.startsWith("R")) {
                        System.out.println("ENTRO POR BULTO:");
                        OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByCode(codigo);
                        if(bulto != null) {
                           ingresarDepositoModel.setIdBulto(bulto.getId());
                           ingresarDepositoModel.setIdDeposito(Integer.valueOf(ingresarDepositoForm.getIdDeposito()));
                           bulto.setIdDeposito(Integer.valueOf(ingresarDepositoForm.getIdDeposito()));
                           ingresarDepositoService.save(ingresarDepositoModel);
                           ordenDeProduccionBultoService.save(bulto);
                    
                           OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(bulto.getIdOrdenDeProduccionBobina());
                           if(bobina != null) {
                             bobina.setIdDeposito(Integer.valueOf(ingresarDepositoForm.getIdDeposito()));
                             ordenDeProduccionBobinaService.save(bobina);                        
                           }
                           if(esDepositoScrap && bobina != null) {
                                OrdenDeProduccionScrapService ordenDeProduccionScrapService = new OrdenDeProduccionScrapServiceImpl();     
                                OrdenDeProduccionScrapModel ordenDeProduccionScrapModel = null;
                                ordenDeProduccionScrapModel = new OrdenDeProduccionScrapModel();

                                // Por ejemplo, asignar el ID de la Orden de Producción
                                ordenDeProduccionScrapModel.setId(null);
                                ordenDeProduccionScrapModel.setIdOrdenDeProduccion(bulto.getIdOrdenDeProduccion()); // Asegúrate de tener el ID disponible
                                ordenDeProduccionScrapModel.setFechaAlta(new Date()); // Asignar la fecha actual
                                ordenDeProduccionScrapModel.setEstado("Nuevo"); // Estado inicial
                                ordenDeProduccionScrapModel.setIdUsuarioAlta(Integer.valueOf(Utils.getUserLoggedId(req))); // Obtener el usuario logueado

                                // Asignar valores manualmente a los campos del Scrap
                                ordenDeProduccionScrapModel.setIdOrigen(null); // Ejemplo: Origen del Scrap, si tienes el valor
                                ordenDeProduccionScrapModel.setIdTipoMaterial(null); // Ejemplo: Tipo de material del Scrap
                                ordenDeProduccionScrapModel.setIdMotivo(null); // Ejemplo: Motivo del Scrap
                                ordenDeProduccionScrapModel.setIdFormato(null); // Ejemplo: Formato del Scrap

                                // Si es recuperable
                                ordenDeProduccionScrapModel.setEsRecuperable(Boolean.FALSE); // O asignarlo manualmente según la lógica

                                // Si es material impreso
                                ordenDeProduccionScrapModel.setMaterialImpreso(Boolean.FALSE); // O asignarlo manualmente

                                // Peso total del Scrap (valor simulado)
                                ordenDeProduccionScrapModel.setPesoTotal(bobina.getPesoNeto()); //Asignar el peso total manualmente
                                ordenDeProduccionScrapModel.setCantidadUtilizada(0.0);

                                ordenDeProduccionScrapModel.setIdBulto(bulto.getId());

                                // Observaciones
                                ordenDeProduccionScrapModel.setObservaciones("Bulto Rechazado");
                                System.out.println("ID antes de persistir: " + ordenDeProduccionScrapModel.getId());

                                // Paso 3: Guardar el Scrap
                                ordenDeProduccionScrapService.save(ordenDeProduccionScrapModel);

                                if (ordenDeProduccionScrapModel.getId() != null) {
                                    ordenDeProduccionScrapModel.setCodigo("S" + ordenDeProduccionScrapModel.getId());
                                    ordenDeProduccionScrapService.save(ordenDeProduccionScrapModel); // Guardar nuevamente con el código generado
                                }
                            }
                        }
                    }
                    
                    if (codigo.startsWith("P")) {
                        System.out.println("ENTRO POR PALLET:");
                        OrdenDeProduccionPalletModel pallet = ordenDeProduccionPalletService.getByCode(codigo);
                        if(pallet != null) {
                           pallet.setIdDeposito(Integer.valueOf(ingresarDepositoForm.getIdDeposito()));
                           ingresarDepositoModel.setIdPallet(pallet.getId());
                           ingresarDepositoModel.setIdDeposito(Integer.valueOf(ingresarDepositoForm.getIdDeposito()));
                           ingresarDepositoService.save(ingresarDepositoModel);
                           ordenDeProduccionPalletService.save(pallet);
                           List<OrdenDeProduccionPalletBultoModel> palletBultos = ordenDeProduccionPalletBultoService.getAllByOrdenDeProduccionPallet(pallet.getId());
                            if(palletBultos != null && !palletBultos.isEmpty()) {
                                for(OrdenDeProduccionPalletBultoModel palletBulto :palletBultos) {                            
                                    OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(palletBulto.getIdOrdenDeProduccionBulto());
                                    if(bulto != null) {
                                        bulto.setIdDeposito(Integer.valueOf(ingresarDepositoForm.getIdDeposito()));

                                        ordenDeProduccionBultoService.save(bulto);

                                       OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(bulto.getIdOrdenDeProduccionBobina());
                                        if(bobina != null) {
                                          bobina.setIdDeposito(Integer.valueOf(ingresarDepositoForm.getIdDeposito()));
                                          ordenDeProduccionBobinaService.save(bobina);                        
                                       }
                                   }
                                }
                            }

                        }
                    }
               
                } else {
                    if(ingresarDepositoForm.getAction().equalsIgnoreCase("remove")) {
                        if(ingresarDepositoModel.getId() == null) {
                            modelAndView.setViewName("error");
                            modelAndView.addObject("errorMessage", "Error: id de ingresarDeposito inválido.");
                            return modelAndView;                                    
                        }

                        ingresarDepositoService.delete(ingresarDepositoModel);
                    } else {
                        modelAndView.setViewName("error");
                        modelAndView.addObject("errorMessage", "Error: operación inválida.");
                        return modelAndView;                                
                    }
                }
            
                }
            } else {
                System.out.println("No se recibieron códigos.");
            }

        
        if(id.equalsIgnoreCase("-1")) {
            ingresarDepositoModel = new IngresarDepositoModel();
            ingresarDepositoModel.setFechaAlta(new Date());
            ingresarDepositoModel.setIdUsuarioAlta(user.getId());
        } else {
            ingresarDepositoModel = ingresarDepositoService.getByPk(Integer.valueOf(id));
            if(ingresarDepositoModel == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: id de ingresarDeposito inválido.");
                return modelAndView;                
            } 
        } 
         
//        if(ingresarDepositoForm.getTipo().equalsIgnoreCase("bulto")) {
//            ingresarDepositoModel.setIdBulto(Integer.valueOf(ingresarDepositoForm.getIdBulto()));
//            
//        } 
//        if(ingresarDepositoForm.getTipo().equalsIgnoreCase("pallet")) {
//            ingresarDepositoModel.setIdPallet(Integer.valueOf(ingresarDepositoForm.getIdPallet()));
//        }
//        
//        if(ingresarDepositoForm.getTipo().equalsIgnoreCase("bobina")) {
//            ingresarDepositoModel.setIdBobina(Integer.valueOf(ingresarDepositoForm.getIdBobina()));
//        }
//        
//        ingresarDepositoModel.setIdDeposito(Integer.valueOf(ingresarDepositoForm.getIdDeposito()));
//        
//        
//        System.out.print("DEPOSITOOOO" + ingresarDepositoForm.getIdDeposito());
//        
//        TipoModel verificarDeposito = tipoService.getByPk(Integer.parseInt(ingresarDepositoForm.getIdDeposito()));
//        
//        boolean esDepositoScrap = false;
//        
//        if (verificarDeposito.getValor().equalsIgnoreCase("Scrap")){
//            esDepositoScrap = true;
//        }
       
       
//        if(ingresarDepositoForm.getAction().equalsIgnoreCase("add") || ingresarDepositoForm.getAction().equalsIgnoreCase("edit")) {
//            ingresarDepositoService.save(ingresarDepositoModel);
//            
//            if(ingresarDepositoForm.getTipo().equalsIgnoreCase("bobina")) {
//                OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(Integer.valueOf(ingresarDepositoForm.getIdBobina()));
//                if(bobina != null) {
//                    bobina.setIdDeposito(Integer.valueOf(ingresarDepositoForm.getIdDeposito()));
//                    ordenDeProduccionBobinaService.save(bobina); 
//                }
//                
//                 if(bobina != null && esDepositoScrap ) {
//                    OrdenDeProduccionScrapService ordenDeProduccionScrapService = new OrdenDeProduccionScrapServiceImpl();     
//                    OrdenDeProduccionScrapModel ordenDeProduccionScrapModel = null;
//                    ordenDeProduccionScrapModel = new OrdenDeProduccionScrapModel();
//                    
//                    // Por ejemplo, asignar el ID de la Orden de Producción
//                    ordenDeProduccionScrapModel.setId(null);
//                    ordenDeProduccionScrapModel.setIdOrdenDeProduccion(bobina.getIdOrdenDeProduccion()); // Asegúrate de tener el ID disponible
//                    ordenDeProduccionScrapModel.setFechaAlta(new Date()); // Asignar la fecha actual
//                    ordenDeProduccionScrapModel.setEstado("Nuevo"); // Estado inicial
//                    ordenDeProduccionScrapModel.setIdUsuarioAlta(Integer.valueOf(Utils.getUserLoggedId(req))); // Obtener el usuario logueado
//
//                    // Asignar valores manualmente a los campos del Scrap
//                    ordenDeProduccionScrapModel.setIdOrigen(null); // Ejemplo: Origen del Scrap, si tienes el valor
//                    ordenDeProduccionScrapModel.setIdTipoMaterial(null); // Ejemplo: Tipo de material del Scrap
//                    ordenDeProduccionScrapModel.setIdMotivo(null); // Ejemplo: Motivo del Scrap
//                    ordenDeProduccionScrapModel.setIdFormato(null); // Ejemplo: Formato del Scrap
//
//                    // Si es recuperable
//                    ordenDeProduccionScrapModel.setEsRecuperable(Boolean.FALSE); // O asignarlo manualmente según la lógica
//
//                    // Si es material impreso
//                    ordenDeProduccionScrapModel.setMaterialImpreso(Boolean.FALSE); // O asignarlo manualmente
//
//                    // Peso total del Scrap (valor simulado)
//                    ordenDeProduccionScrapModel.setPesoTotal(bobina.getPesoNeto()); //Asignar el peso total manualmente
//                    ordenDeProduccionScrapModel.setCantidadUtilizada(0.0);
//
//                    ordenDeProduccionScrapModel.setIdBobina(bobina.getId());
//
//                    // Observaciones
//                    ordenDeProduccionScrapModel.setObservaciones("Scrap creado manualmente sin formulario");
//                    System.out.println("ID antes de persistir: " + ordenDeProduccionScrapModel.getId());
//
//                    // Paso 3: Guardar el Scrap
//                    ordenDeProduccionScrapService.save(ordenDeProduccionScrapModel);
//                    //        // Asignar un código único basado en su ID, si es un nuevo Scrap
//                    if (ordenDeProduccionScrapModel.getId() != null) {
//                        ordenDeProduccionScrapModel.setCodigo("S" + ordenDeProduccionScrapModel.getId());
//                        ordenDeProduccionScrapService.save(ordenDeProduccionScrapModel); // Guardar nuevamente con el código generado
//                    }
//                }
//            }
//            
//            if(ingresarDepositoForm.getTipo().equalsIgnoreCase("bulto")) {
//                OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(Integer.valueOf(ingresarDepositoForm.getIdBulto()));
//                if(bulto != null) {
//                    bulto.setIdDeposito(Integer.valueOf(ingresarDepositoForm.getIdDeposito()));
//                    
//                    ordenDeProduccionBultoService.save(bulto);
//                    
//                    OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(bulto.getIdOrdenDeProduccionBobina());
//                    if(bobina != null) {
//                      bobina.setIdDeposito(Integer.valueOf(ingresarDepositoForm.getIdDeposito()));
//                    
//                      ordenDeProduccionBobinaService.save(bobina);                        
//                    }
//                    if(esDepositoScrap && bobina != null) {
//                        OrdenDeProduccionScrapService ordenDeProduccionScrapService = new OrdenDeProduccionScrapServiceImpl();     
//                        OrdenDeProduccionScrapModel ordenDeProduccionScrapModel = null;
//                        ordenDeProduccionScrapModel = new OrdenDeProduccionScrapModel();
//
//                        // Por ejemplo, asignar el ID de la Orden de Producción
//                        ordenDeProduccionScrapModel.setId(null);
//                        ordenDeProduccionScrapModel.setIdOrdenDeProduccion(bulto.getIdOrdenDeProduccion()); // Asegúrate de tener el ID disponible
//                        ordenDeProduccionScrapModel.setFechaAlta(new Date()); // Asignar la fecha actual
//                        ordenDeProduccionScrapModel.setEstado("Nuevo"); // Estado inicial
//                        ordenDeProduccionScrapModel.setIdUsuarioAlta(Integer.valueOf(Utils.getUserLoggedId(req))); // Obtener el usuario logueado
//
//                        // Asignar valores manualmente a los campos del Scrap
//                        ordenDeProduccionScrapModel.setIdOrigen(null); // Ejemplo: Origen del Scrap, si tienes el valor
//                        ordenDeProduccionScrapModel.setIdTipoMaterial(null); // Ejemplo: Tipo de material del Scrap
//                        ordenDeProduccionScrapModel.setIdMotivo(null); // Ejemplo: Motivo del Scrap
//                        ordenDeProduccionScrapModel.setIdFormato(null); // Ejemplo: Formato del Scrap
//
//                        // Si es recuperable
//                        ordenDeProduccionScrapModel.setEsRecuperable(Boolean.FALSE); // O asignarlo manualmente según la lógica
//
//                        // Si es material impreso
//                        ordenDeProduccionScrapModel.setMaterialImpreso(Boolean.FALSE); // O asignarlo manualmente
//
//                        // Peso total del Scrap (valor simulado)
//                        ordenDeProduccionScrapModel.setPesoTotal(bobina.getPesoNeto()); //Asignar el peso total manualmente
//                        ordenDeProduccionScrapModel.setCantidadUtilizada(0.0);
//
//                        ordenDeProduccionScrapModel.setIdBulto(bulto.getId());
//
//                        // Observaciones
//                        ordenDeProduccionScrapModel.setObservaciones("Scrap creado manualmente sin formulario");
//                        System.out.println("ID antes de persistir: " + ordenDeProduccionScrapModel.getId());
//
//                        // Paso 3: Guardar el Scrap
//                        ordenDeProduccionScrapService.save(ordenDeProduccionScrapModel);
//                        
//                        if (ordenDeProduccionScrapModel.getId() != null) {
//                            ordenDeProduccionScrapModel.setCodigo("S" + ordenDeProduccionScrapModel.getId());
//                            ordenDeProduccionScrapService.save(ordenDeProduccionScrapModel); // Guardar nuevamente con el código generado
//                        }
//                    }
//                }
//                 
//            }
//            if(ingresarDepositoForm.getTipo().equalsIgnoreCase("pallet")) {
//                OrdenDeProduccionPalletModel pallet = ordenDeProduccionPalletService.getByPk(Integer.valueOf(ingresarDepositoForm.getIdPallet()));
//                if(pallet != null) {
//                    pallet.setIdDeposito(Integer.valueOf(ingresarDepositoForm.getIdDeposito()));
//                    
//                    ordenDeProduccionPalletService.save(pallet);
//                    
//                    List<OrdenDeProduccionPalletBultoModel> palletBultos = ordenDeProduccionPalletBultoService.getAllByOrdenDeProduccionPallet(pallet.getId());
//                    if(palletBultos != null && !palletBultos.isEmpty()) {
//                        for(OrdenDeProduccionPalletBultoModel palletBulto :palletBultos) {                            
//                            OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(palletBulto.getIdOrdenDeProduccionBulto());
//                            if(bulto != null) {
//                                bulto.setIdDeposito(Integer.valueOf(ingresarDepositoForm.getIdDeposito()));
//
//                                ordenDeProduccionBultoService.save(bulto);
//
//                               OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(bulto.getIdOrdenDeProduccionBobina());
//                               if(bobina != null) {
//                                    bobina.setIdDeposito(Integer.valueOf(ingresarDepositoForm.getIdDeposito()));
//
//                                    ordenDeProduccionBobinaService.save(bobina);                        
//                               }
//                           }
//                            
//                        }
//                    }
//                }                
//            }
//        } else {
//            if(ingresarDepositoForm.getAction().equalsIgnoreCase("remove")) {
//                if(ingresarDepositoModel.getId() == null) {
//                    modelAndView.setViewName("error");
//                    modelAndView.addObject("errorMessage", "Error: id de ingresarDeposito inválido.");
//                    return modelAndView;                                    
//                }
//                                            
//                ingresarDepositoService.delete(ingresarDepositoModel);
//            } else {
//                modelAndView.setViewName("error");
//                modelAndView.addObject("errorMessage", "Error: operación inválida.");
//                return modelAndView;                                
//            }
//        }
                        
        modelAndView.setViewName("redirect:/ingresarDeposito");

        return modelAndView; 
    }
    
    @RequestMapping(value = "/egresoDeposito/addOrEditOrRemove", method = RequestMethod.POST)
    public ModelAndView addOrEditOrRemoveEgresoDeposito(@ModelAttribute("egresoDepositoForm")EgresoDepositoForm egresoDepositoForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {
        
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
        
        if(egresoDepositoForm.getAction() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;            
        }
        
        
        
        EgresoDepositoService egresoDepositoService = new EgresoDepositoServiceImpl();        
        OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();
        OrdenDeProduccionBultoService ordenDeProduccionBultoService = new OrdenDeProduccionBultoServiceImpl();
        OrdenDeProduccionPalletService ordenDeProduccionPalletService = new OrdenDeProduccionPalletServiceImpl();
        OrdenDeProduccionPalletBultoService ordenDeProduccionPalletBultoService = new OrdenDeProduccionPalletBultoServiceImpl();
        TipoService tipoService = new TipoServiceImpl();
        
        String sessionId = req.getSession().getId();
        String id = egresoDepositoForm.getPk();
            
        EgresoDepositoModel egresoDepositoModel = null;
        
        if(id.equalsIgnoreCase("-1")) {
            egresoDepositoModel = new EgresoDepositoModel();
            egresoDepositoModel.setFechaBaja(new Date());
            egresoDepositoModel.setIdUsuarioBaja(user.getId());
        } else {
            egresoDepositoModel = egresoDepositoService.getByPk(Integer.valueOf(id));
            if(egresoDepositoModel == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: id de ingresarDeposito inválido.");
                return modelAndView;                
            } 
        } 
         
        if(egresoDepositoForm.getTipo().equalsIgnoreCase("bulto")) {
            egresoDepositoModel.setIdBulto(Integer.valueOf(egresoDepositoForm.getIdBulto()));
            
        } 
        if(egresoDepositoForm.getTipo().equalsIgnoreCase("pallet")) {
            egresoDepositoModel.setIdPallet(Integer.valueOf(egresoDepositoForm.getIdPallet()));
        }
        
        if(egresoDepositoForm.getTipo().equalsIgnoreCase("bobina")) {
            egresoDepositoModel.setIdBobina(Integer.valueOf(egresoDepositoForm.getIdBobina()));
        }
        
        String valor=null;
        if(egresoDepositoForm.getDepositoActual() != null) {
            valor = egresoDepositoForm.getDepositoActual();
        }
        
        //TipoService tipoService = new TipoServiceImpl();

            TipoModel tipo = tipoService.getByValor(valor);
            int idDeposito = -1;
            if (tipo != null) {
                idDeposito = tipo.getId();
            } else { 
            }
           
     
        //if(egresoDepositoForm.getIdRemito() != null) {
            //remitoid = Integer.parseInt(egresoDepositoForm.getIdRemito());
        //}
        
        
        int numeroEntero = Integer.parseInt(egresoDepositoForm.getIdRemito());
        
        egresoDepositoModel.setIdRemito(numeroEntero);
        
        egresoDepositoModel.setIdDeposito(idDeposito);
        
        RemitoDetalleService remitoDetalleService = new RemitoDetalleServiceImpl();  
        RemitoDetalleModel remitoAmodificar = remitoDetalleService.getByPk(numeroEntero);
        
        RemitoService remitoService = new RemitoServiceImpl();  
        RemitoModel remitoAmodificarNoDetalle = remitoService.getByPk(remitoAmodificar.getIdRemito());
        

        if(egresoDepositoForm.getAction().equalsIgnoreCase("add") || egresoDepositoForm.getAction().equalsIgnoreCase("edit")) {
            egresoDepositoService.save(egresoDepositoModel);
            
            if(egresoDepositoForm.getTipo().equalsIgnoreCase("bobina")) {
                OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(Integer.valueOf(egresoDepositoForm.getIdBobina()));
                if(bobina != null) {
                    bobina.setIdRemito(numeroEntero);
                    //bobina.setIdDeposito(Integer.valueOf(egresoDepositoForm.getIdDeposito()));
                    ordenDeProduccionBobinaService.save(bobina); 
                    remitoAmodificar.setCantidadBaja(remitoAmodificar.getCantidadBaja() +  1);
                    remitoDetalleService.save(remitoAmodificar);
                    
                    remitoAmodificarNoDetalle.setCantidadTotalBaja(remitoAmodificarNoDetalle.getCantidadTotalBaja() +  1);
                    remitoService.save(remitoAmodificarNoDetalle);
                }
            }
            
            if(egresoDepositoForm.getTipo().equalsIgnoreCase("bulto")) {
                OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(Integer.valueOf(egresoDepositoForm.getIdBulto()));
                if(bulto != null) {
                    bulto.setIdRemito(numeroEntero);
                    //bulto.setIdDeposito(Integer.valueOf(egresoDepositoForm.getIdDeposito()));
                    
                    ordenDeProduccionBultoService.save(bulto);
                    
                    remitoAmodificar.setCantidadBaja(remitoAmodificar.getCantidadBaja() +  1);
                    remitoDetalleService.save(remitoAmodificar);
                    
                    remitoAmodificarNoDetalle.setCantidadTotalBaja(remitoAmodificarNoDetalle.getCantidadTotalBaja() +  1);
                    remitoService.save(remitoAmodificarNoDetalle);
                    
                    OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(bulto.getIdOrdenDeProduccionBobina());
                    if(bobina != null) {
                      bobina.setIdRemito(numeroEntero);
                    
                      ordenDeProduccionBobinaService.save(bobina);                        
                    }
                }
            }
            if(egresoDepositoForm.getTipo().equalsIgnoreCase("pallet")) {
                OrdenDeProduccionPalletModel pallet = ordenDeProduccionPalletService.getByPk(Integer.valueOf(egresoDepositoForm.getIdPallet()));
                if(pallet != null) {
              
                    pallet.setIdRemito(numeroEntero);
                    
                    ordenDeProduccionPalletService.save(pallet);
                    List<OrdenDeProduccionPalletBultoModel> palletBultos = ordenDeProduccionPalletBultoService.getAllByOrdenDeProduccionPalletAndNotRemito(pallet.getId());
                    remitoAmodificar.setCantidadBaja(remitoAmodificar.getCantidadBaja() +  palletBultos.size());
                    remitoDetalleService.save(remitoAmodificar);
                    
                    remitoAmodificarNoDetalle.setCantidadTotalBaja(remitoAmodificarNoDetalle.getCantidadTotalBaja() +  palletBultos.size());
                    remitoService.save(remitoAmodificarNoDetalle);
                     
                    if(palletBultos != null && !palletBultos.isEmpty()) {
                        for(OrdenDeProduccionPalletBultoModel palletBulto :palletBultos) {                            
                            OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(palletBulto.getIdOrdenDeProduccionBulto());
                            if(bulto != null) {
                                //bulto.setIdDeposito(Integer.valueOf(egresoDepositoForm.getIdDeposito()));
                                bulto.setIdRemito(numeroEntero);

                                ordenDeProduccionBultoService.save(bulto);

                               OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(bulto.getIdOrdenDeProduccionBobina());
                               if(bobina != null) {
                                    //bobina.setIdDeposito(Integer.valueOf(egresoDepositoForm.getIdDeposito()));
                                    bobina.setIdRemito(numeroEntero);

                                    ordenDeProduccionBobinaService.save(bobina);                        
                               }
                           }
                            
                        }
                    }
                }                
            }
        } else {
            if(egresoDepositoForm.getAction().equalsIgnoreCase("remove")) {
                if(egresoDepositoModel.getId() == null) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: id de ingresarDeposito inválido.");
                    return modelAndView;                                    
                }
                                            
                egresoDepositoService.delete(egresoDepositoModel);
            } else {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: operación inválida.");
                return modelAndView;                                
            }
        }
        
        //RemitoDetalleService remitoDetalleService = new RemitoDetalleServiceImpl();   
        RemitoDetalleModel remito = remitoDetalleService.getByPk(Integer.valueOf(egresoDepositoForm.getIdRemito()));
                        
        modelAndView.setViewName("redirect:/remitoDetalle/"+remito.getIdRemito());
        //model.addAttribute("egresoDepositoForm", egresoDepositoForm);  

        return modelAndView; 
    }

    @ResponseBody
    @RequestMapping(value = "/ingresarDeposito/findBultoOPallet/{codigo}/{orden}/{dep}", method = RequestMethod.GET)
    public List<ItemBean> getMateriaPrimaByProveedor(@PathVariable String codigo,@PathVariable String orden, @PathVariable String dep, HttpServletRequest req, ModelMap model) throws Exception {
        
        OrdenDeProduccionBultoService ordenDeProduccionBultoService = new OrdenDeProduccionBultoServiceImpl();
        OrdenDeProduccionPalletService ordenDeProduccionPalletService = new OrdenDeProduccionPalletServiceImpl();
        TipoService tipoService = new TipoServiceImpl();
        List<OrdenDeProduccionBultoDto> ordenDeProduccionBultosDtos = new ArrayList<OrdenDeProduccionBultoDto>();
       
        List<ItemBean> result = new ArrayList<ItemBean>();
        if(codigo != null && !codigo.isEmpty()) {
            System.out.println(dep);
            String tipo = codigo.substring(0,1);
            boolean estaEnPallet = false;
            boolean estaEnDeposito = false;
            String depositoDelBultoIngresado = "";
            String depositoDelBobinaIngresado = "";
            String depositoDelPalletIngresado = "";
            if(tipo.equalsIgnoreCase("R")){
                ItemBean bean = new ItemBean();        
                bean.setTipo("bulto");                
                OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByCode(codigo);
                
                OrdenDeProduccionBultoDto ordenDeProduccionBultoDto = new OrdenDeProduccionBultoDto();
                
                if (bulto != null && !bulto.getIdOrdenDeProduccion().equals(Integer.valueOf(orden))) {
                   bean.setId("-2");
                   bean.setTipo("-2");
                } else {
                if(bulto != null) {
                    OrdenDeProduccionBultoModel bultoCompleto = ordenDeProduccionBultoService.getByPk(bulto.getId());
                    bean.setCodigo(bulto.getCodigo());
                    bean.setId(bulto.getId().toString());
                    bean.setTipo("bulto");
                    bean.setDepositoActual("No disponible");
                    bean.setIdDeposito("-1");
                    
                    ordenDeProduccionBultoDto.setPk(bultoCompleto.getId().toString());
                   
                    ordenDeProduccionBultoDto.setCodigo(bultoCompleto.getCodigo());
                                    if (bultoCompleto.getIdOrdenDeProduccionBobina() != null) {
                                            ordenDeProduccionBultoDto.setIdBobina(bultoCompleto.getIdOrdenDeProduccionBobina().toString());
                                    }
                                    ordenDeProduccionBultoDto.setEstaEnPallet(bultoCompleto.getEstaEnPallet().toString());
                                    if(bultoCompleto.getEstaEnPallet()) {
                                        ordenDeProduccionBultoDto.setEstaEnPalletLabel("Si");
                                        estaEnPallet = true;
                                    } else {
                                         ordenDeProduccionBultoDto.setEstaEnPalletLabel("No");
                                    }
                                    
                                    OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();

                                    OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(bultoCompleto.getIdOrdenDeProduccionBobina());
                                    if(bobina != null) {
                                        ordenDeProduccionBultoDto.setPesoTotal(bobina.getPesoTotal().toString());
                                        ordenDeProduccionBultoDto.setPesoNeto(bobina.getPesoNeto().toString());
                                    }
                ordenDeProduccionBultosDtos.add(ordenDeProduccionBultoDto);
                bean.setBultos(ordenDeProduccionBultosDtos);
                if(bulto.getIdDeposito() != null) {
                        estaEnDeposito = true;
                        TipoModel deposito = tipoService.getByPk(bulto.getIdDeposito());
                        if(deposito != null) {
                            bean.setDepositoActual(deposito.getValor());
                        }
                    }
                if(bulto.getEstado() != null && !bulto.getEstado().equalsIgnoreCase("-1")) {
                        //bobina.setEstado(bobina.getEstado());
                        if(bulto.getEstado().equals("ok")) {
                            TipoModel tipo2 = tipoService.getByValor("Principal");
                            if (tipo2 != null) {
                                bean.setIdDeposito(tipo2.getId().toString());
                                depositoDelBultoIngresado = tipo2.getId().toString();
                            } else { 
                                bean.setIdDeposito("-1");
                            }
                        }
                        if(bulto.getEstado().equals("observado")) {
                            TipoModel tipo3 = tipoService.getByValor("Fuera de standard");
                            if (tipo3 != null) {
                                bean.setIdDeposito(tipo3.getId().toString());
                                depositoDelBultoIngresado = tipo3.getId().toString();
                            } else { 
                                bean.setIdDeposito("-1");
                            }
                        }
                        if(bulto.getEstado().equals("rechazado")) {
                             TipoModel tipo4 = tipoService.getByValor("Scrap");
                            if (tipo4 != null) {
                                bean.setIdDeposito(tipo4.getId().toString());
                                depositoDelBultoIngresado = tipo4.getId().toString();
                            } else { 
                                bean.setIdDeposito("-1");
                            }
                        }
                        if(bulto.getEstado().equals("sinmesurar")) {
                            TipoModel tipo5 = tipoService.getByValor("Campo");
                            if (tipo5 != null) {
                                bean.setIdDeposito(tipo5.getId().toString());
                                depositoDelBultoIngresado = tipo5.getId().toString();
                            } else { 
                                bean.setIdDeposito("-1");
                            }
                        }                
                    }
                } else {
                    bean.setId("-1");
                    bean.setTipo("-1");
                }
                
                    if(estaEnPallet){
                        bean.setId("-3");
                        bean.setTipo("-3");
                    }
                    if(estaEnDeposito){
                        bean.setId("-6");
                        bean.setTipo("-6");
                    }
                    if (!"-1".equals(dep) && !dep.equals(depositoDelBultoIngresado)) {
                        bean.setId("-8");
                        bean.setTipo("-8");
                    }
                }
                
                result.add(bean);
                
            } else if(tipo.equalsIgnoreCase("P")){
                ItemBean bean = new ItemBean();        
                bean.setTipo("pallet");
                OrdenDeProduccionPalletBultoService ordenDeProduccionPalletBultoService = new OrdenDeProduccionPalletBultoServiceImpl();                
                OrdenDeProduccionPalletModel pallet = ordenDeProduccionPalletService.getByCode(codigo);
                if (pallet != null && !pallet.getIdOrdenDeProduccion().equals(Integer.valueOf(orden))) {
                   bean.setId("-2");
                   bean.setTipo("-2");
                } else {
                if(pallet != null) {  
                    boolean estaEnDepositoPallet = false;
                    List<OrdenDeProduccionPalletBultoModel> palletbultoList = ordenDeProduccionPalletBultoService.getAllByOrdenDeProduccionPallet(pallet.getId());
                    bean.setCodigo(pallet.getCodigo());
                    bean.setId(pallet.getId().toString());
                    bean.setTipo("pallet");
                    bean.setDepositoActual("No disponible");
                    
                    for(OrdenDeProduccionPalletBultoModel item :palletbultoList) {
                           OrdenDeProduccionBultoModel ordenDeProduccionBultosModel = ordenDeProduccionBultoService.getByPk(item.getIdOrdenDeProduccionBulto());
                           if(ordenDeProduccionBultosModel != null) {
                                  OrdenDeProduccionBultoDto ordenDeProduccionBultoDto = new OrdenDeProduccionBultoDto();
                                  ordenDeProduccionBultoDto.setPk(ordenDeProduccionBultosModel.getId().toString());
                                  ordenDeProduccionBultoDto.setFechaAlta(ordenDeProduccionBultosModel.getFechaAlta().toString().replace(".0", ""));
                                   if(ordenDeProduccionBultosModel.getEstado() != null && !ordenDeProduccionBultosModel.getEstado().equalsIgnoreCase("-1")) {
                                      ordenDeProduccionBultoDto.setEstado(ordenDeProduccionBultosModel.getEstado());
                                                    if(ordenDeProduccionBultosModel.getEstado().equals("ok")) {
                                                        ordenDeProduccionBultoDto.setEstadoLabel("OK");
                                                    }
                                                    if(ordenDeProduccionBultosModel.getEstado().equals("observado")) {
                                                        ordenDeProduccionBultoDto.setEstadoLabel("Observado");
                                                    }
                                                    if(ordenDeProduccionBultosModel.getEstado().equals("rechazado")) {
                                                         ordenDeProduccionBultoDto.setEstadoLabel("Rechazado");
                                                    }
                                                    if(ordenDeProduccionBultosModel.getEstado().equals("sinmesurar")) {
                                                        ordenDeProduccionBultoDto.setEstadoLabel("Sin Mesurar");
                                                    }                
                                    }
                                    ordenDeProduccionBultoDto.setCodigo(ordenDeProduccionBultosModel.getCodigo());
                                    if (ordenDeProduccionBultosModel.getIdOrdenDeProduccionBobina() != null) {
                                            ordenDeProduccionBultoDto.setIdBobina(ordenDeProduccionBultosModel.getIdOrdenDeProduccionBobina().toString());
                                    }
                                    ordenDeProduccionBultoDto.setEstaEnPallet(ordenDeProduccionBultosModel.getEstaEnPallet().toString());
                                    if(ordenDeProduccionBultosModel.getEstaEnPallet()) {
                                        ordenDeProduccionBultoDto.setEstaEnPalletLabel("Si");
                                    } else {
                                         ordenDeProduccionBultoDto.setEstaEnPalletLabel("No");
                                    }
                                    String plegadora = "";
                                    if(ordenDeProduccionBultosModel.getIdPlegadora() != null) {
                                        TipoModel plegadoraModel = tipoService.getByPk(ordenDeProduccionBultosModel.getIdPlegadora());
                                        if(plegadora != null) {
                                            plegadora = plegadoraModel.getValor();
                                        }
                                    }        
                                    ordenDeProduccionBultoDto.setPlegadora(plegadora);
                                    OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();

                                    OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(ordenDeProduccionBultosModel.getIdOrdenDeProduccionBobina());
                                    if(bobina != null) {
                                        ordenDeProduccionBultoDto.setPesoTotal(bobina.getPesoTotal().toString());
                                        ordenDeProduccionBultoDto.setPesoNeto(bobina.getPesoNeto().toString());
                                    }
                                   ordenDeProduccionBultosDtos.add(ordenDeProduccionBultoDto);
                                                        
                           }
                           
                    }
                    bean.setBultos(ordenDeProduccionBultosDtos);
                    if(pallet.getIdDeposito() != null) {
                        estaEnDepositoPallet = true;
                        TipoModel deposito = tipoService.getByPk(pallet.getIdDeposito());
                        if(deposito != null) {
                            bean.setDepositoActual(deposito.getValor());
                        }
                    }
                    
                    if(pallet.getEstado() != null && !pallet.getEstado().equalsIgnoreCase("-1")) {
                        //bobina.setEstado(bobina.getEstado());
                        if(pallet.getEstado().equals("ok")) {
                            TipoModel tipo2 = tipoService.getByValor("Principal");
                            if (tipo2 != null) {
                                bean.setIdDeposito(tipo2.getId().toString());
                                depositoDelPalletIngresado = tipo2.getId().toString();
                            } else { 
                                bean.setIdDeposito("-1");
                            }
                        }
                        if(pallet.getEstado().equals("observado")) {
                            TipoModel tipo3 = tipoService.getByValor("Fuera de standard");
                            if (tipo3 != null) {
                                bean.setIdDeposito(tipo3.getId().toString());
                                depositoDelPalletIngresado = tipo3.getId().toString();
                            } else { 
                                bean.setIdDeposito("-1");
                            }
                        }
                        if(pallet.getEstado().equals("rechazado")) {
                             TipoModel tipo4 = tipoService.getByValor("Scrap");
                            if (tipo4 != null) {
                                bean.setIdDeposito(tipo4.getId().toString());
                                depositoDelPalletIngresado = tipo4.getId().toString();
                            } else { 
                                bean.setIdDeposito("-1");
                            }
                        }
                        if(pallet.getEstado().equals("sinmesurar")) {
                            TipoModel tipo5 = tipoService.getByValor("Campo");
                            if (tipo5 != null) {
                                bean.setIdDeposito(tipo5.getId().toString());
                                depositoDelPalletIngresado = tipo5.getId().toString();
                            } else { 
                                bean.setIdDeposito("-1");
                            }
                        }                
                    }
                    
                    if(estaEnDepositoPallet){
                        bean.setId("-7");
                        bean.setTipo("-7");
                    }
                    if (!"-1".equals(dep) && !dep.equals(depositoDelPalletIngresado)) {
                        bean.setId("-10");
                        bean.setTipo("-10");
                    }
                } else {
                    bean.setId("-1");
                    bean.setTipo("-1");
                }
                }
                result.add(bean);
            } else if(tipo.equalsIgnoreCase("B")){
                ItemBean bean = new ItemBean();        
                bean.setTipo("bobina");
                 OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();
                 OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByCode(codigo);
                 OrdenDeProduccionBobinaDto ordenDeProduccionBobinaDto = new OrdenDeProduccionBobinaDto();
         
                if (bobina != null && !bobina.getIdOrdenDeProduccion().equals(Integer.valueOf(orden))) {
                   bean.setId("-2");
                   bean.setTipo("-2");
                } else {
                if(bobina != null) {  
                    boolean estaEnBulto = false;
                    boolean estaEnDepositoBobina = false;
                    bean.setCodigo(bobina.getCodigo());
                    bean.setId(bobina.getId().toString());
                    bean.setTipo("bobina");
                    bean.setDepositoActual("No disponible");
                    ordenDeProduccionBobinaDto.setCodigo(codigo);
                    ordenDeProduccionBobinaDto.setPesoNeto(bobina.getPesoNeto().toString());
                    ordenDeProduccionBobinaDto.setEstaEnBultoLabel(bobina.getEstaEnBulto().toString());
                    if(bobina.getEstaEnBulto()) {
                        ordenDeProduccionBobinaDto.setEstaEnBultoLabel("Si");
                        estaEnBulto = true;
                    } else {
                        ordenDeProduccionBobinaDto.setEstaEnBultoLabel("No");
                    }
                    
                    
                    if(bobina.getEstado() != null && !bobina.getEstado().equalsIgnoreCase("-1")) {
                        //bobina.setEstado(bobina.getEstado());
                        if(bobina.getEstado().equals("ok")) {
                            TipoModel tipo2 = tipoService.getByValor("Principal");
                            if (tipo2 != null) {
                                bean.setIdDeposito(tipo2.getId().toString());
                                depositoDelBobinaIngresado = tipo2.getId().toString();
                            } else { 
                                bean.setIdDeposito("-1");
                            }
                        }
                        if(bobina.getEstado().equals("observado")) {
                            TipoModel tipo3 = tipoService.getByValor("Fuera de standard");
                            if (tipo3 != null) {
                                bean.setIdDeposito(tipo3.getId().toString());
                                depositoDelBobinaIngresado = tipo3.getId().toString();
                            } else { 
                                bean.setIdDeposito("-1");
                            }
                        }
                        if(bobina.getEstado().equals("rechazado")) {
                             TipoModel tipo4 = tipoService.getByValor("Scrap");
                            if (tipo4 != null) {
                                bean.setIdDeposito(tipo4.getId().toString());
                                depositoDelBobinaIngresado = tipo4.getId().toString();
                            } else { 
                                bean.setIdDeposito("-1");
                            }
                        }
                        if(bobina.getEstado().equals("sinmesurar")) {
                            TipoModel tipo5 = tipoService.getByValor("Campo");
                            if (tipo5 != null) {
                                bean.setIdDeposito(tipo5.getId().toString());
                                depositoDelBobinaIngresado = tipo5.getId().toString();
                            } else { 
                                bean.setIdDeposito("-1");
                            }
                        }                
                    }
                    
                   
                    if(bobina.getIdDeposito() != null) {
                        estaEnDepositoBobina = true;
                        TipoModel deposito = tipoService.getByPk(bobina.getIdDeposito());
                        if(deposito != null) {
                            bean.setDepositoActual(deposito.getValor());
                        }
                    }
                    
                    if(estaEnBulto){
                        bean.setId("-4");
                        bean.setTipo("-4");
                    }
                    
                    if(estaEnDepositoBobina){
                        bean.setId("-5");
                        bean.setTipo("-5");
                    }
                    
                    if (!"-1".equals(dep) && !dep.equals(depositoDelBobinaIngresado)) {
                        bean.setId("-9");
                        bean.setTipo("-9");
                    }
                
 
                    bean.setBobina(ordenDeProduccionBobinaDto);
                    result.add(bean);
                } else {
                    bean.setId("-1");
                    bean.setTipo("-1");
                  }
                }
                result.add(bean);
            }
            else {
                ItemBean bean = new ItemBean();        
                bean.setTipo("-1");
                bean.setId("-1");
                result.add(bean);
            }
        }
        
        return result;
    }
    
    
    
    
    @ResponseBody
    @RequestMapping(value = "/egresoDeposito/findBultoOPallet/{codigo}/{orden}", method = RequestMethod.GET)
    public List<ItemBean> getMateriaPrimaByProveedorE(@PathVariable String codigo,@PathVariable String orden, HttpServletRequest req, ModelMap model) throws Exception {
        
        OrdenDeProduccionBultoService ordenDeProduccionBultoService = new OrdenDeProduccionBultoServiceImpl();
        OrdenDeProduccionPalletService ordenDeProduccionPalletService = new OrdenDeProduccionPalletServiceImpl();
        TipoService tipoService = new TipoServiceImpl();
        List<OrdenDeProduccionBultoDto> ordenDeProduccionBultosDtos = new ArrayList<OrdenDeProduccionBultoDto>();
       
        List<ItemBean> result = new ArrayList<ItemBean>();
        if(codigo != null && !codigo.isEmpty()) {
            String tipo = codigo.substring(0,1);
            boolean estaEnPallet = false;
            boolean estaEnDeposito = false;
            boolean estaEnRemitoBulto = false;
            if(tipo.equalsIgnoreCase("R")){
                ItemBean bean = new ItemBean();        
                bean.setTipo("bulto");                
                OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByCode(codigo);
                
                OrdenDeProduccionBultoDto ordenDeProduccionBultoDto = new OrdenDeProduccionBultoDto();
                
                if (bulto != null && !bulto.getIdOrdenDeProduccion().equals(Integer.valueOf(orden))) {
                   bean.setId("-2");
                   bean.setTipo("-2");
                } else {
                if(bulto != null) {
                    OrdenDeProduccionBultoModel bultoCompleto = ordenDeProduccionBultoService.getByPk(bulto.getId());
                    bean.setCodigo(bulto.getCodigo());
                    bean.setId(bulto.getId().toString());
                    bean.setTipo("bulto");
                    bean.setDepositoActual("No disponible");
                    
                    ordenDeProduccionBultoDto.setPk(bultoCompleto.getId().toString());
                   
                    ordenDeProduccionBultoDto.setCodigo(bultoCompleto.getCodigo());
                                    if (bultoCompleto.getIdOrdenDeProduccionBobina() != null) {
                                            ordenDeProduccionBultoDto.setIdBobina(bultoCompleto.getIdOrdenDeProduccionBobina().toString());
                                    }
                                    ordenDeProduccionBultoDto.setEstaEnPallet(bultoCompleto.getEstaEnPallet().toString());
                                    if(bultoCompleto.getEstaEnPallet()) {
                                        ordenDeProduccionBultoDto.setEstaEnPalletLabel("Si");
                                        estaEnPallet = true;
                                    } else {
                                         ordenDeProduccionBultoDto.setEstaEnPalletLabel("No");
                                    }
                                    
                                    OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();

                                    OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(bultoCompleto.getIdOrdenDeProduccionBobina());
                                    if(bobina != null) {
                                        ordenDeProduccionBultoDto.setPesoTotal(bobina.getPesoTotal().toString());
                                        ordenDeProduccionBultoDto.setPesoNeto(bobina.getPesoNeto().toString());
                                    }
                ordenDeProduccionBultosDtos.add(ordenDeProduccionBultoDto);
                bean.setBultos(ordenDeProduccionBultosDtos);
                if(bulto.getIdDeposito() != null) {
                        estaEnDeposito = true;
                        TipoModel deposito = tipoService.getByPk(bulto.getIdDeposito());
                        if(deposito != null) {
                            bean.setDepositoActual(deposito.getValor());
                        }
                    }
                } else {
                    bean.setId("-1");
                    bean.setTipo("-1");
                }
                
                    if(estaEnPallet){
                        //bean.setId("-3");
                        //bean.setTipo("-3");
                    }
                    if(!estaEnDeposito){
                        bean.setId("-6");
                        bean.setTipo("-6");
                    }
                    if(bulto.getIdRemito() != null) {
                        estaEnRemitoBulto = true;
                    }
                    if(estaEnRemitoBulto){
                        bean.setId("-9");
                        bean.setTipo("-9");
                    }
                }
                
                result.add(bean);
                
            } else if(tipo.equalsIgnoreCase("P")){
                ItemBean bean = new ItemBean();        
                bean.setTipo("pallet");
                OrdenDeProduccionPalletBultoService ordenDeProduccionPalletBultoService = new OrdenDeProduccionPalletBultoServiceImpl();                
                OrdenDeProduccionPalletModel pallet = ordenDeProduccionPalletService.getByCode(codigo);
                if (pallet != null && !pallet.getIdOrdenDeProduccion().equals(Integer.valueOf(orden))) {
                   bean.setId("-2");
                   bean.setTipo("-2");
                } else {
                if(pallet != null) {  
                    boolean estaEnDepositoPallet = false;
                    boolean estaEnRemitoPallet = false;
                    List<OrdenDeProduccionPalletBultoModel> palletbultoList = ordenDeProduccionPalletBultoService.getAllByOrdenDeProduccionPalletAndNotRemito(pallet.getId());
                    bean.setCodigo(pallet.getCodigo());
                    bean.setId(pallet.getId().toString());
                    bean.setTipo("pallet");
                    bean.setDepositoActual("No disponible");
                    
                    for(OrdenDeProduccionPalletBultoModel item :palletbultoList) {
                           OrdenDeProduccionBultoModel ordenDeProduccionBultosModel = ordenDeProduccionBultoService.getByPk(item.getIdOrdenDeProduccionBulto());
                           if(ordenDeProduccionBultosModel != null) {
                                  OrdenDeProduccionBultoDto ordenDeProduccionBultoDto = new OrdenDeProduccionBultoDto();
                                  ordenDeProduccionBultoDto.setPk(ordenDeProduccionBultosModel.getId().toString());
                                  ordenDeProduccionBultoDto.setFechaAlta(ordenDeProduccionBultosModel.getFechaAlta().toString().replace(".0", ""));
                                   if(ordenDeProduccionBultosModel.getEstado() != null && !ordenDeProduccionBultosModel.getEstado().equalsIgnoreCase("-1")) {
                                      ordenDeProduccionBultoDto.setEstado(ordenDeProduccionBultosModel.getEstado());
                                                    if(ordenDeProduccionBultosModel.getEstado().equals("ok")) {
                                                        ordenDeProduccionBultoDto.setEstadoLabel("OK");
                                                    }
                                                    if(ordenDeProduccionBultosModel.getEstado().equals("observado")) {
                                                        ordenDeProduccionBultoDto.setEstadoLabel("Observado");
                                                    }
                                                    if(ordenDeProduccionBultosModel.getEstado().equals("rechazado")) {
                                                         ordenDeProduccionBultoDto.setEstadoLabel("Rechazado");
                                                    }
                                                    if(ordenDeProduccionBultosModel.getEstado().equals("sinmesurar")) {
                                                        ordenDeProduccionBultoDto.setEstadoLabel("Sin Mesurar");
                                                    }                
                                    }
                                    ordenDeProduccionBultoDto.setCodigo(ordenDeProduccionBultosModel.getCodigo());
                                    if (ordenDeProduccionBultosModel.getIdOrdenDeProduccionBobina() != null) {
                                            ordenDeProduccionBultoDto.setIdBobina(ordenDeProduccionBultosModel.getIdOrdenDeProduccionBobina().toString());
                                    }
                                    ordenDeProduccionBultoDto.setEstaEnPallet(ordenDeProduccionBultosModel.getEstaEnPallet().toString());
                                    if(ordenDeProduccionBultosModel.getEstaEnPallet()) {
                                        ordenDeProduccionBultoDto.setEstaEnPalletLabel("Si");
                                    } else {
                                         ordenDeProduccionBultoDto.setEstaEnPalletLabel("No");
                                    }
                                    String plegadora = "";
                                    if(ordenDeProduccionBultosModel.getIdPlegadora() != null) {
                                        TipoModel plegadoraModel = tipoService.getByPk(ordenDeProduccionBultosModel.getIdPlegadora());
                                        if(plegadora != null) {
                                            plegadora = plegadoraModel.getValor();
                                        }
                                    }        
                                    ordenDeProduccionBultoDto.setPlegadora(plegadora);
                                    OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();

                                    OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(ordenDeProduccionBultosModel.getIdOrdenDeProduccionBobina());
                                    if(bobina != null) {
                                        ordenDeProduccionBultoDto.setPesoTotal(bobina.getPesoTotal().toString());
                                        ordenDeProduccionBultoDto.setPesoNeto(bobina.getPesoNeto().toString());
                                    }
                                   ordenDeProduccionBultosDtos.add(ordenDeProduccionBultoDto);
                                                        
                           }
                           
                    }
                    bean.setBultos(ordenDeProduccionBultosDtos);
                    if(pallet.getIdDeposito() != null) {
                        estaEnDepositoPallet = true;
                        TipoModel deposito = tipoService.getByPk(pallet.getIdDeposito());
                        if(deposito != null) {
                            bean.setDepositoActual(deposito.getValor());
                        }
                    }
                    if(!estaEnDepositoPallet){
                        bean.setId("-7");
                        bean.setTipo("-7");
                    }
                    if(pallet.getIdRemito() != null) {
                        estaEnRemitoPallet = true;
                    }
                   
                    if(estaEnRemitoPallet){
                        bean.setId("-8");
                        bean.setTipo("-8");
                    }
                    if(ordenDeProduccionBultosDtos.isEmpty()){
                        bean.setId("-14");
                        bean.setTipo("-14");
                    }
                } else {
                    bean.setId("-1");
                    bean.setTipo("-1");
                }
                }
                result.add(bean);
            } else if(tipo.equalsIgnoreCase("B")){
                ItemBean bean = new ItemBean();        
                bean.setTipo("bobina");
                 OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();
                 OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByCode(codigo);
                 OrdenDeProduccionBobinaDto ordenDeProduccionBobinaDto = new OrdenDeProduccionBobinaDto();
         
                if (bobina != null && !bobina.getIdOrdenDeProduccion().equals(Integer.valueOf(orden))) {
                   bean.setId("-2");
                   bean.setTipo("-2");
                } else {
                if(bobina != null) {  
                    boolean estaEnBulto = false;
                    boolean estaEnDepositoBobina = false;
                     boolean estaEnRemitoBobina = false;
                    bean.setCodigo(bobina.getCodigo());
                    bean.setId(bobina.getId().toString());
                    bean.setTipo("bobina");
                    bean.setDepositoActual("No disponible");
                    ordenDeProduccionBobinaDto.setCodigo(codigo);
                    ordenDeProduccionBobinaDto.setPesoNeto(bobina.getPesoNeto().toString());
                    ordenDeProduccionBobinaDto.setEstaEnBultoLabel(bobina.getEstaEnBulto().toString());
                    if(bobina.getEstaEnBulto()) {
                        ordenDeProduccionBobinaDto.setEstaEnBultoLabel("Si");
                        estaEnBulto = true;
                    } else {
                        ordenDeProduccionBobinaDto.setEstaEnBultoLabel("No");
                    }
                    
                    
                   
                    if(bobina.getIdDeposito() != null) {
                        estaEnDepositoBobina = true;
                        TipoModel deposito = tipoService.getByPk(bobina.getIdDeposito());
                        if(deposito != null) {
                            bean.setDepositoActual(deposito.getValor());
                        }
                    }
                    
                    if(estaEnBulto){
                        bean.setId("-4");
                        bean.setTipo("-4");
                    }
                    
                    if(!estaEnDepositoBobina){
                        bean.setId("-5");
                        bean.setTipo("-5");
                    }
                    if(bobina.getIdRemito() != null) {
                        estaEnRemitoBobina = true;
                    }
                     if(estaEnRemitoBobina){
                        bean.setId("-10");
                        bean.setTipo("-10");
                    }
                
 
                    bean.setBobina(ordenDeProduccionBobinaDto);
                    result.add(bean);
                } else {
                    bean.setId("-1");
                    bean.setTipo("-1");
                  }
                }
                result.add(bean);
            }
            else {
                ItemBean bean = new ItemBean();        
                bean.setTipo("-1");
                bean.setId("-1");
                result.add(bean);
            }
        }
        
        return result;
    }
    
    @RequestMapping(value = "/ingresarDeposito/editDeposito", method = RequestMethod.POST)
    public ModelAndView editIngresarDeposito(@ModelAttribute("ingresarDepositoForm")IngresarDepositoForm ingresarDepositoForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {
        
        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        ModelAndView modelAndView = new ModelAndView();
        
        if (result.hasErrors()) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error1");
            return modelAndView;
        }
        
        if(!Utils.isAutenticated(req)) {
            modelAndView.setViewName("/login/login");    
            model.addAttribute("userForm", new UserForm());         
            return modelAndView;
        }
        
        if(ingresarDepositoForm.getAction() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error");
            return modelAndView;            
        }
        ingresarDepositoForm.setAction("edit");
        IngresarDepositoService ingresarDepositoService = new IngresarDepositoServiceImpl();        
        OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();
        OrdenDeProduccionBultoService ordenDeProduccionBultoService = new OrdenDeProduccionBultoServiceImpl();
        OrdenDeProduccionPalletService ordenDeProduccionPalletService = new OrdenDeProduccionPalletServiceImpl();
        OrdenDeProduccionPalletBultoService ordenDeProduccionPalletBultoService = new OrdenDeProduccionPalletBultoServiceImpl();
        TipoService tipoService = new TipoServiceImpl();
        
        String sessionId = req.getSession().getId();
        String id = ingresarDepositoForm.getPkEditar();
            
        IngresarDepositoModel ingresarDepositoModel = null;
        
        if(id.equalsIgnoreCase("-1")) {
            ingresarDepositoModel = new IngresarDepositoModel();
            ingresarDepositoModel.setFechaAlta(new Date());
            ingresarDepositoModel.setIdUsuarioAlta(user.getId());
        } else {
            ingresarDepositoModel = ingresarDepositoService.getByPk(Integer.valueOf(id));
            if(ingresarDepositoModel == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: id de ingresarDeposito inválido.");
                return modelAndView;                
            } 
        } 
        
        if(ingresarDepositoModel.getIdBobina() != null) {
          ingresarDepositoForm.setTipo("bobina");
          ingresarDepositoForm.setIdBobina(ingresarDepositoModel.getIdBobina().toString());
        } 
        
        if(ingresarDepositoModel.getIdBulto() != null) {
          ingresarDepositoForm.setTipo("bulto");
          ingresarDepositoForm.setIdBulto(ingresarDepositoModel.getIdBulto().toString());
        } 
        
        if(ingresarDepositoModel.getIdPallet() != null) {
          ingresarDepositoForm.setTipo("pallet");
        } 
        
         OrdenDeProduccionScrapService ordenDeProduccionScrapService = new OrdenDeProduccionScrapServiceImpl();  
              
        
        if(ingresarDepositoForm.getAction().equalsIgnoreCase("add") || ingresarDepositoForm.getAction().equalsIgnoreCase("edit")) {
            int depositoNuevo = 61;
            if(ingresarDepositoForm.getTipo().equalsIgnoreCase("bobina")) {
                OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(Integer.valueOf(ingresarDepositoForm.getIdBobina()));
                
                String estadoActual = bobina.getEstado();
                
                        if ("rechazado".equalsIgnoreCase(estadoActual)) {
                        OrdenDeProduccionScrapModel ordenDeProduccionScrapModel = null;

                        // Obtener el objeto de scrap por su clave primaria (PK)
                        ordenDeProduccionScrapModel = ordenDeProduccionScrapService.getByIdBobina(bobina.getId());

                        // Eliminar el objeto de scrap
                        ordenDeProduccionScrapService.delete(ordenDeProduccionScrapModel);
                    }
                    
                    
                boolean esNuevoYScrapBobina = false;
                
                if(ingresarDepositoForm.getEstadoNuevo() != null) {
                    String valorNuevo = ingresarDepositoForm.getEstadoNuevo();
                    
            
                    if(ingresarDepositoForm.getEstadoNuevo().equals("ok")) {
                        TipoModel tipo15 = tipoService.getByValor("Pincipal");
                        if (tipo15 != null && bobina != null) {
                            depositoNuevo = tipo15.getId();
                           
                        }
                    }
                    if(ingresarDepositoForm.getEstadoNuevo().equals("observado")) {
                        TipoModel tipo14 = tipoService.getByValor("Fuera de standard");
                        if (tipo14 != null) {
                            depositoNuevo = tipo14.getId();
                           
                        }
                    }
                    if(ingresarDepositoForm.getEstadoNuevo().equals("rechazado")) {
                        TipoModel tipoNuevo12 = tipoService.getByValor("Scrap");
                        if (tipoNuevo12 != null) {
                            depositoNuevo = tipoNuevo12.getId();
                            esNuevoYScrapBobina = true;
                        }
                    }
                    if(ingresarDepositoForm.getEstadoNuevo().equals("sinmesurar")) {
                        TipoModel tipoNuevo13 = tipoService.getByValor("Campo");
                        if (tipoNuevo13 != null) {
                            depositoNuevo = tipoNuevo13.getId();
                        }
                    }     
                    
                }
               
                if(bobina != null && !ingresarDepositoForm.getEstadoNuevo().equalsIgnoreCase("-1")) {
                    bobina.setEstado(ingresarDepositoForm.getEstadoNuevo());
                    bobina.setIdDeposito(depositoNuevo);
                    ingresarDepositoModel.setIdDeposito(depositoNuevo);
                    ordenDeProduccionBobinaService.save(bobina);
                    ingresarDepositoService.save(ingresarDepositoModel);
                    
                    if (esNuevoYScrapBobina) {
                    //OrdenDeProduccionScrapService ordenDeProduccionScrapService = new OrdenDeProduccionScrapServiceImpl();     
                    OrdenDeProduccionScrapModel ordenDeProduccionScrapModel = null;
                    ordenDeProduccionScrapModel = new OrdenDeProduccionScrapModel();
                    
                    // Por ejemplo, asignar el ID de la Orden de Producción
                    ordenDeProduccionScrapModel.setId(null);
                    ordenDeProduccionScrapModel.setIdOrdenDeProduccion(bobina.getIdOrdenDeProduccion()); // Asegúrate de tener el ID disponible
                    ordenDeProduccionScrapModel.setFechaAlta(new Date()); // Asignar la fecha actual
                    ordenDeProduccionScrapModel.setEstado("Nuevo"); // Estado inicial
                    ordenDeProduccionScrapModel.setIdUsuarioAlta(Integer.valueOf(Utils.getUserLoggedId(req))); // Obtener el usuario logueado

                    // Asignar valores manualmente a los campos del Scrap
                    ordenDeProduccionScrapModel.setIdOrigen(null); // Ejemplo: Origen del Scrap, si tienes el valor
                    ordenDeProduccionScrapModel.setIdTipoMaterial(null); // Ejemplo: Tipo de material del Scrap
                    ordenDeProduccionScrapModel.setIdMotivo(null); // Ejemplo: Motivo del Scrap
                    ordenDeProduccionScrapModel.setIdFormato(null); // Ejemplo: Formato del Scrap

                    // Si es recuperable
                    ordenDeProduccionScrapModel.setEsRecuperable(Boolean.FALSE); // O asignarlo manualmente según la lógica

                    // Si es material impreso
                    ordenDeProduccionScrapModel.setMaterialImpreso(Boolean.FALSE); // O asignarlo manualmente

                    // Peso total del Scrap (valor simulado)
                    ordenDeProduccionScrapModel.setPesoTotal(bobina.getPesoNeto()); //Asignar el peso total manualmente
                    ordenDeProduccionScrapModel.setCantidadUtilizada(0.0);

                    ordenDeProduccionScrapModel.setIdBobina(bobina.getId());

                    // Observaciones
                    ordenDeProduccionScrapModel.setObservaciones("Scrap creado manualmente sin formulario");
                    System.out.println("ID antes de persistir: " + ordenDeProduccionScrapModel.getId());

                    // Paso 3: Guardar el Scrap
                    ordenDeProduccionScrapService.save(ordenDeProduccionScrapModel);
                    //        // Asignar un código único basado en su ID, si es un nuevo Scrap
                    if (ordenDeProduccionScrapModel.getId() != null) {
                        ordenDeProduccionScrapModel.setCodigo("S" + ordenDeProduccionScrapModel.getId());
                        ordenDeProduccionScrapService.save(ordenDeProduccionScrapModel); // Guardar nuevamente con el código generado
                    }
                        
                    }
                }
            }
            
            if(ingresarDepositoForm.getTipo().equalsIgnoreCase("bulto")) {
                OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(Integer.valueOf(ingresarDepositoForm.getIdBulto()));
                
                String estadoActual = bulto.getEstado();
                
                        if ("rechazado".equalsIgnoreCase(estadoActual)) {
                        OrdenDeProduccionScrapModel ordenDeProduccionScrapModel = null;

                        // Obtener el objeto de scrap por su clave primaria (PK)
                        ordenDeProduccionScrapModel = ordenDeProduccionScrapService.getByIdBulto(bulto.getId());
                        // Eliminar el objeto de scrap
                        ordenDeProduccionScrapService.delete(ordenDeProduccionScrapModel);
                    }
              
                boolean esNuevoYScrap = false;
                
                if(ingresarDepositoForm.getEstadoNuevo() != null) {
                    String valorNuevo = ingresarDepositoForm.getEstadoNuevo();
                    
                    
            
                    if(ingresarDepositoForm.getEstadoNuevo().equals("ok")) {
                        TipoModel tipo15 = tipoService.getByValor("Pincipal");
                        if (tipo15 != null && bulto != null) {
                            depositoNuevo = tipo15.getId();
                           
                        }
                    }
                    if(ingresarDepositoForm.getEstadoNuevo().equals("observado")) {
                        TipoModel tipo14 = tipoService.getByValor("Fuera de standard");
                        if (tipo14 != null) {
                            depositoNuevo = tipo14.getId();
                           
                        }
                    }
                    if(ingresarDepositoForm.getEstadoNuevo().equals("rechazado")) {
                        TipoModel tipoNuevo12 = tipoService.getByValor("Scrap");
                        if (tipoNuevo12 != null) {
                            depositoNuevo = tipoNuevo12.getId();
                            esNuevoYScrap = true;
                        }
                    }
                    if(ingresarDepositoForm.getEstadoNuevo().equals("sinmesurar")) {
                        TipoModel tipoNuevo13 = tipoService.getByValor("Campo");
                        if (tipoNuevo13 != null) {
                            depositoNuevo = tipoNuevo13.getId();
                        }
                    }     
                    
                }
                
                
                
                
                if(bulto != null && !ingresarDepositoForm.getEstadoNuevo().equalsIgnoreCase("-1")) {
                    
                    bulto.setEstado(ingresarDepositoForm.getEstadoNuevo());
                    bulto.setIdDeposito(depositoNuevo);
                    
                    ingresarDepositoModel.setIdDeposito(depositoNuevo);
                    ordenDeProduccionBultoService.save(bulto);
                    ingresarDepositoService.save(ingresarDepositoModel);
                    
                    
                    bulto.setIdDeposito(depositoNuevo);
                    ordenDeProduccionBultoService.save(bulto);
                    
                    OrdenDeProduccionBobinaModel bobina2 = ordenDeProduccionBobinaService.getByPk(bulto.getIdOrdenDeProduccionBobina());
                    if(bobina2 != null) {
                        bobina2.setEstado(ingresarDepositoForm.getEstadoNuevo());
                        bobina2.setIdDeposito(depositoNuevo);
                      
                    
                      ordenDeProduccionBobinaService.save(bobina2);                        
                      
                      if (esNuevoYScrap) {
                    //OrdenDeProduccionScrapService ordenDeProduccionScrapService = new OrdenDeProduccionScrapServiceImpl();     
                    OrdenDeProduccionScrapModel ordenDeProduccionScrapModel = null;
                    ordenDeProduccionScrapModel = new OrdenDeProduccionScrapModel();
                    
                    // Por ejemplo, asignar el ID de la Orden de Producción
                    ordenDeProduccionScrapModel.setId(null);
                    ordenDeProduccionScrapModel.setIdOrdenDeProduccion(bobina2.getIdOrdenDeProduccion()); // Asegúrate de tener el ID disponible
                    ordenDeProduccionScrapModel.setFechaAlta(new Date()); // Asignar la fecha actual
                    ordenDeProduccionScrapModel.setEstado("Nuevo"); // Estado inicial
                    ordenDeProduccionScrapModel.setIdUsuarioAlta(Integer.valueOf(Utils.getUserLoggedId(req))); // Obtener el usuario logueado

                    // Asignar valores manualmente a los campos del Scrap
                    ordenDeProduccionScrapModel.setIdOrigen(null); // Ejemplo: Origen del Scrap, si tienes el valor
                    ordenDeProduccionScrapModel.setIdTipoMaterial(null); // Ejemplo: Tipo de material del Scrap
                    ordenDeProduccionScrapModel.setIdMotivo(null); // Ejemplo: Motivo del Scrap
                    ordenDeProduccionScrapModel.setIdFormato(null); // Ejemplo: Formato del Scrap

                    // Si es recuperable
                    ordenDeProduccionScrapModel.setEsRecuperable(Boolean.FALSE); // O asignarlo manualmente según la lógica

                    // Si es material impreso
                    ordenDeProduccionScrapModel.setMaterialImpreso(Boolean.FALSE); // O asignarlo manualmente

                    // Peso total del Scrap (valor simulado)
                    ordenDeProduccionScrapModel.setPesoTotal(bobina2.getPesoNeto()); //Asignar el peso total manualmente
                    ordenDeProduccionScrapModel.setCantidadUtilizada(0.0);

                    ordenDeProduccionScrapModel.setIdBulto(bulto.getId());

                    // Observaciones
                    ordenDeProduccionScrapModel.setObservaciones("Scrap creado manualmente sin formulario");
                    System.out.println("ID antes de persistir: " + ordenDeProduccionScrapModel.getId());

                    // Paso 3: Guardar el Scrap
                    ordenDeProduccionScrapService.save(ordenDeProduccionScrapModel);
                    //        // Asignar un código único basado en su ID, si es un nuevo Scrap
                    if (ordenDeProduccionScrapModel.getId() != null) {
                        ordenDeProduccionScrapModel.setCodigo("S" + ordenDeProduccionScrapModel.getId());
                        ordenDeProduccionScrapService.save(ordenDeProduccionScrapModel); // Guardar nuevamente con el código generado
                    }
                        
                    }
                    }
                    
                 
                    
                }
            }
            if(ingresarDepositoForm.getTipo().equalsIgnoreCase("pallet")) {
                OrdenDeProduccionPalletModel pallet = ordenDeProduccionPalletService.getByPk(Integer.valueOf(ingresarDepositoForm.getIdPallet()));
                System.out.println("***************PAAAAAALETN*****" + ingresarDepositoForm.getIdBultoEditar());
                if (ingresarDepositoForm.getIdBultoEditar() != null ) {
                   OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByOrdenDeProduccionBobina(Integer.valueOf(ingresarDepositoForm.getIdBultoEditar()));
                   if (bulto != null && !ingresarDepositoForm.getEstadoNuevo().equalsIgnoreCase("-1")){
                   OrdenDeProduccionPalletBultoModel h = ordenDeProduccionPalletBultoService.getByOrdenDeProduccionBulto(bulto.getId()).get(0);

                   if(ingresarDepositoForm.getEstadoNuevo() != null) {
                       String valorNuevo = ingresarDepositoForm.getEstadoNuevo();


                       if(ingresarDepositoForm.getEstadoNuevo().equals("ok")) {
                           TipoModel tipo15 = tipoService.getByValor("Pincipal");
                           if (tipo15 != null) {
                               depositoNuevo = tipo15.getId();

                           }
                       }
                       if(ingresarDepositoForm.getEstadoNuevo().equals("observado")) {
                           TipoModel tipo14 = tipoService.getByValor("Fuera de standard");
                           if (tipo14 != null) {
                               depositoNuevo = tipo14.getId();

                           }
                       }
                       if(ingresarDepositoForm.getEstadoNuevo().equals("rechazado")) {
                           TipoModel tipoNuevo12 = tipoService.getByValor("Scrap");
                           if (tipoNuevo12 != null) {
                               depositoNuevo = tipoNuevo12.getId();
                           }
                       }
                       if(ingresarDepositoForm.getEstadoNuevo().equals("sinmesurar")) {
                           TipoModel tipoNuevo13 = tipoService.getByValor("Campo");
                           if (tipoNuevo13 != null) {
                               depositoNuevo = tipoNuevo13.getId();
                           }
                       }     
                   bulto.setEstaEnPallet(false);
                   bulto.setEstado(ingresarDepositoForm.getEstadoNuevo());
                   bulto.setIdDeposito(depositoNuevo);
                   ordenDeProduccionBultoService.save(bulto);
                   OrdenDeProduccionBobinaModel bobina3 = ordenDeProduccionBobinaService.getByPk(bulto.getIdOrdenDeProduccionBobina());
                   if(bobina3 != null) {
                       bobina3.setEstado(ingresarDepositoForm.getEstadoNuevo());
                       bobina3.setIdDeposito(depositoNuevo);
                       ordenDeProduccionBobinaService.save(bobina3);                        
                   }
                   ordenDeProduccionPalletBultoService.delete(h);
                   ingresarDepositoModel  = new IngresarDepositoModel();
                   ingresarDepositoModel.setFechaAlta(new Date());
                   ingresarDepositoModel.setIdUsuarioAlta(user.getId());
                   ingresarDepositoModel.setIdBulto(bulto.getId());
                   ingresarDepositoModel.setIdDeposito(depositoNuevo);
                   ingresarDepositoService.save(ingresarDepositoModel);
                   }
                   }
                }
//                if(pallet != null) {
//                    pallet.setIdDeposito(Integer.valueOf(ingresarDepositoForm.getIdDeposito()));
//                    
//                    ordenDeProduccionPalletService.save(pallet);
//                    
//                    List<OrdenDeProduccionPalletBultoModel> palletBultos = ordenDeProduccionPalletBultoService.getAllByOrdenDeProduccionPallet(pallet.getId());
//                    if(palletBultos != null && !palletBultos.isEmpty()) {
//                        for(OrdenDeProduccionPalletBultoModel palletBulto :palletBultos) {                            
//                            OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(palletBulto.getIdOrdenDeProduccionBulto());
//                            if(bulto != null) {
//                                bulto.setIdDeposito(Integer.valueOf(ingresarDepositoForm.getIdDeposito()));
//
//                                ordenDeProduccionBultoService.save(bulto);
//
//                               OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(bulto.getIdOrdenDeProduccionBobina());
//                               if(bobina != null) {
//                                    bobina.setIdDeposito(Integer.valueOf(ingresarDepositoForm.getIdDeposito()));
//
//                                    ordenDeProduccionBobinaService.save(bobina);                        
//                               }
//                           }
//                            
//                        }
//                    }
//                }                
            }
        }
                        
        modelAndView.setViewName("redirect:/ingresarDeposito");

        return modelAndView; 
    }
}
