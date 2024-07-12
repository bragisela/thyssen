/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

 
import com.empresa.thyssenplastic.controller.beans.ItemBean;
import com.empresa.thyssenplastic.controller.beans.OrdenDeCompraBean;
import com.empresa.thyssenplastic.controller.beans.StockOrdenDeCompraBean;
import com.empresa.thyssenplastic.controller.form.EgresoDepositoForm;
import com.empresa.thyssenplastic.controller.form.RemitoDetalleForm;
import com.empresa.thyssenplastic.controller.form.UserForm;
import com.empresa.thyssenplastic.dto.OrdenDepositoDto;
import com.empresa.thyssenplastic.dto.RemitoDetalleDto;
import com.empresa.thyssenplastic.model.ArticuloModel;
import com.empresa.thyssenplastic.model.ClienteDomicilioModel;
import com.empresa.thyssenplastic.service.impl.ActivacionManualServiceImpl;
import com.empresa.thyssenplastic.model.ActivacionManualModel;
import com.empresa.thyssenplastic.service.ActivacionManualService;
import com.empresa.thyssenplastic.model.ClienteModel;
import com.empresa.thyssenplastic.model.ContactoModel;
import com.empresa.thyssenplastic.model.DomicilioModel;
import com.empresa.thyssenplastic.model.LocalidadModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionBobinaModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionBultoModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionPalletModel;
import com.empresa.thyssenplastic.model.ProveedorModel;
import com.empresa.thyssenplastic.model.RemitoDetalleModel;
import com.empresa.thyssenplastic.model.RemitoModel;
import com.empresa.thyssenplastic.model.TipoModel;
import com.empresa.thyssenplastic.model.UserModel;
import com.empresa.thyssenplastic.service.ArticuloService;
import com.empresa.thyssenplastic.service.ClienteDomicilioService;
import com.empresa.thyssenplastic.service.ClienteService;
import com.empresa.thyssenplastic.service.ContactoService;
import com.empresa.thyssenplastic.service.DomicilioService;
import com.empresa.thyssenplastic.service.LocalidadService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionBobinaService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionBultoService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionPalletService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionService;
import com.empresa.thyssenplastic.service.ProveedorService;
import com.empresa.thyssenplastic.service.RemitoDetalleService;
import com.empresa.thyssenplastic.service.RemitoService;
import com.empresa.thyssenplastic.service.TipoService;
import com.empresa.thyssenplastic.service.UserService;
import com.empresa.thyssenplastic.service.impl.ArticuloServiceImpl;
import com.empresa.thyssenplastic.service.impl.ClienteDomicilioServiceImpl;
import com.empresa.thyssenplastic.service.impl.ClienteServiceImpl;
import com.empresa.thyssenplastic.service.impl.ContactoServiceImpl;
import com.empresa.thyssenplastic.service.impl.DomicilioServiceImpl;
import com.empresa.thyssenplastic.service.impl.LocalidadServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionBobinaServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionBultoServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionPalletServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionServiceImpl;
import com.empresa.thyssenplastic.service.impl.ProveedorServiceImpl;
import com.empresa.thyssenplastic.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.empresa.thyssenplastic.service.impl.RemitoDetalleServiceImpl;
import com.empresa.thyssenplastic.service.impl.RemitoServiceImpl;
import com.empresa.thyssenplastic.service.impl.TipoServiceImpl;
import com.empresa.thyssenplastic.service.impl.UserServiceImpl;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author gusta
 */
@Controller
public class RemitoDetalleController {
    

    @RequestMapping(value = "/remitoDetalle/{idRemito}", method = RequestMethod.GET)
    public String getHomeRemitoDetalle(@PathVariable String idRemito, HttpServletRequest req, ModelMap model) {

        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);
        
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(idRemito == null) {
            model.addAttribute("errorMessage", "Error: id remito inválido");         
            return "/error";                            
        }
        
        if(user.getRol() != Utils.ROL_OFICINA && user.getRol() != Utils.ROL_DEPOSITO) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
        
        RemitoService remitoService = new RemitoServiceImpl();   
        RemitoModel remito = remitoService.getByPk(Integer.valueOf(idRemito));

        if(remito == null) {
            model.addAttribute("errorMessage", "Error: remito no encontrado");         
            return "/error";                
        }
        
        ClienteService clienteService = new ClienteServiceImpl();   
        ClienteDomicilioService clienteDomicilioService = new ClienteDomicilioServiceImpl();   
        DomicilioService domicilioService = new DomicilioServiceImpl();   
        LocalidadService localidadService = new LocalidadServiceImpl();   
        TipoService tipoService = new TipoServiceImpl();   
        
        String rol = "";    
        String operacion = "";        
        String displayActionButton = "block";
        
        EgresoDepositoForm egresoDepositoForm = new EgresoDepositoForm();
        egresoDepositoForm.setPk("-1");
        egresoDepositoForm.setAction("add");
        egresoDepositoForm.setIdBobina("-1");
        egresoDepositoForm.setIdBulto("-1");
        egresoDepositoForm.setIdPallet("-1");
        egresoDepositoForm.setIdOrdenDeProduccionE("-1");
                        
        RemitoDetalleForm remitoDetalleForm = new RemitoDetalleForm();
        remitoDetalleForm.setPk("-1");
        remitoDetalleForm.setAction("add");
        remitoDetalleForm.setIdRemito(remito.getId().toString());
        remitoDetalleForm.setCodigoRemito(remito.getId().toString());
        remitoDetalleForm.setFechaRemito(remito.getFechaRemito().toString().replace("00:00:00.0", ""));
        remitoDetalleForm.setTipoRemito(remito.getTipoRemito());
        remitoDetalleForm.setEstadoRemito(remito.getEstado());        
        if(remito.getReferenciaAdministrativa() != null) {
            remitoDetalleForm.setReferenciaAdministrativa(remito.getReferenciaAdministrativa());
        }
        ClienteModel cliente = null;
        if(remito.getIdCliente() != null) {
            cliente = clienteService.getByPk(remito.getIdCliente());
            if(cliente != null) {
                remitoDetalleForm.setCliente("(" + cliente.getId() + ") " + cliente.getRazonSocial());
            }
        }
        if(remito.getIdDomicilio() != null) {
            DomicilioModel domicilioModel = domicilioService.getByPk(remito.getIdDomicilio());
            if(domicilioModel != null) {
                String loc = "SN";
                String prov = "SN";
                        
                        TipoService tipoServicee = new TipoServiceImpl();
                        TipoModel localidaad = null;
                        TipoModel provincia = null;
                        
                        if (domicilioModel.getIdLocalidad() != null){
                            localidaad = tipoServicee.getByPk(domicilioModel.getIdLocalidad());
                         }
                        
                        if (domicilioModel.getIdProvincia() != null){
                            provincia = tipoServicee.getByPk(domicilioModel.getIdProvincia());
                         }
                        
                       if (localidaad != null){
                           loc =localidaad.getValor(); 
                       }
                       
                       if (provincia != null){
                           prov = provincia.getValor(); 
                       }

                remitoDetalleForm.setDomicilio(domicilioModel.getUbicacion() + " (" + loc + ", " + prov + ")");
            }
        }        
        
        if(remito.getIdTransporte() != null) {
            ProveedorService proveedorService = new ProveedorServiceImpl();
            ProveedorModel proveedor = proveedorService.getByPk(remito.getIdTransporte());
            remitoDetalleForm.setTransporte(proveedor.getRazonSocial());
            
        }
        if(remito.getIdChofer() != null) {
            ContactoService contactoService = new ContactoServiceImpl();
            ContactoModel c = contactoService.getByPk(remito.getIdChofer());
           remitoDetalleForm.setIdChofer(c.getNombre());
        }
        Calendar c = Calendar.getInstance();        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
        String fecha = formato.format(c.getTime());

        remitoDetalleForm.setFechaAlta(fecha);

        if(user.getRol() == Utils.ROL_OFICINA) {
            operacion = "alta";
            displayActionButton = "block";
            rol = "oficina";
            
            if(!remito.getEstado().equalsIgnoreCase("Nuevo")) {
                displayActionButton = "none";
            }
            
        }
        if(user.getRol() == Utils.ROL_DEPOSITO) {
            operacion = "recepcion";
            displayActionButton = "none";            
            rol = "deposito";
        }
                
        remitoDetalleForm.setOperacion(operacion);
        
        model.addAttribute("remitoDetalleForm", remitoDetalleForm);  
        model.addAttribute("titleRemitoDetalle", "Nuevo Remito Detalle");  
        model.addAttribute("buttonLabel", "Guardar");
        
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();   
        ArticuloService articuloService = new ArticuloServiceImpl();   
        Map<String,String> articulos = new LinkedHashMap<String,String>();
        List<ArticuloModel> articulosModel = articuloService.getAll();

        List<OrdenDeProduccionModel> ordenDeProducciones = ordenDeProduccionService.getAllWithStock();
        Set<String> articulosSet = new HashSet<String>();
        
        for(OrdenDeProduccionModel ordenDeProduccionCompleted :ordenDeProducciones) {
            articulosSet.add(ordenDeProduccionCompleted.getIdArticulo().toString());
        }
        
       if(articulosModel != null && !articulosModel.isEmpty()){
           for(ArticuloModel articuloModel :articulosModel) {
                if(articulosSet.contains(articuloModel.getId().toString())) {
                    articulos.put(articuloModel.getId().toString(), articuloModel.getDenominacion());
               }
            }
        }
                 
        //OrdenDeProduccionBobinaService bobinaService = new OrdenDeProduccionBobinaServiceImpl();   
        //OrdenDeProduccionBultoService bultoService = new OrdenDeProduccionBultoServiceImpl();   
        //OrdenDeProduccionPalletService palletService = new OrdenDeProduccionPalletServiceImpl();           
        RemitoDetalleService remitoDetalleService = new RemitoDetalleServiceImpl();   
        List<RemitoDetalleModel> remitoDetalles = remitoDetalleService.getAllByRemito(remito.getId());
        System.out.println("**** remitoid:"+remito.getId());
        
        
        Boolean itemsRecibidos = true;
        List<RemitoDetalleDto> remitoDetallesDtos = new ArrayList<RemitoDetalleDto>();
        for(RemitoDetalleModel remitoDetalle: remitoDetalles) {
            RemitoDetalleDto remitoDetalleDto = new RemitoDetalleDto();
            remitoDetalleDto.setPk(remitoDetalle.getId().toString());
            remitoDetalleDto.setFechaAlta(remitoDetalle.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            
            remitoDetalleDto.setLote(remitoDetalle.getIdOrdenDeProduccion().toString());
            remitoDetalleDto.setDeposito(remitoDetalle.getIdDeposito().toString());
            remitoDetalleDto.setCantidad(remitoDetalle.getCantidad().toString());
            remitoDetalleDto.setCantidadBaja(remitoDetalle.getCantidadBaja().toString());
            
            TipoModel deposito = tipoService.getByPk(remitoDetalle.getIdDeposito());
                if(deposito != null) {
                    remitoDetalleDto.setDeposito(deposito.getValor());
                } 
            OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(remitoDetalle.getIdOrdenDeProduccion());
            ArticuloModel articulo = null;
            if(ordenDeProduccion != null){
               articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
            }
            if(articulo != null) {
                remitoDetalleDto.setArticulo(articulo.getDenominacion());
            }
            
            //if(!remitoDetalle.getIngresoDeposito()){
              //  itemsRecibidos = false;
           // }
            //if(remitoDetalle.getIngresoDeposito()) {
              //  remitoDetalleDto.setIngresoDeposito("Si");
            //} else {
              //  remitoDetalleDto.setIngresoDeposito("No");
            //}
            
            //if(remitoDetalle.getIdBobina() != null) {
                
               // OrdenDeProduccionBobinaModel bobina = bobinaService.getByPk(remitoDetalle.getIdBobina());
                //if(bobina != null) {
                    //remitoDetalleDto.setDeposito("Sin información");
                    //if(bobina.getIdDeposito() != null) {
                       // TipoModel deposito = tipoService.getByPk(bobina.getIdDeposito());
                       // if(deposito != null) {
                          //  remitoDetalleDto.setDeposito(deposito.getValor());        
                        //}
                   // }
                   // OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(bobina.getIdOrdenDeProduccion());
                   // if(ordenDeProduccion != null) {
                   //     remitoDetalleDto.setLote("L"+ordenDeProduccion.getId());                        
                        
                   //     ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
                   //     if(articulo != null) {
                    //        remitoDetalleDto.setArticulo("(" + articulo.getId() + ") " + articulo.getDenominacion());
                    //    }
                    //}
                    //remitoDetalleDto.setCodigo(bobina.getCodigo());
                    //remitoDetalleDto.setTieneBultoOPallet("true");
                //}
           // }
            //if(remitoDetalle.getIdBulto() != null) {
                
                //OrdenDeProduccionBultoModel bulto = bultoService.getByPk(remitoDetalle.getIdBulto());
                //if(bulto != null) {
                   // remitoDetalleDto.setDeposito("Sin información");
                    //if(bulto.getIdDeposito() != null) {
                      //  TipoModel deposito = tipoService.getByPk(bulto.getIdDeposito());
                       // if(deposito != null) {
                         //   remitoDetalleDto.setDeposito(deposito.getValor());        
                        //}
                    //}                    
                    //OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(bulto.getIdOrdenDeProduccion());
                    //if(ordenDeProduccion != null) {
                      //  remitoDetalleDto.setLote("L"+ordenDeProduccion.getId());
                        
                      //  ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
                      //  if(articulo != null) {
                        //    remitoDetalleDto.setArticulo("(" + articulo.getId() + ") " + articulo.getDenominacion());
                        //}                        
                   // }
                   // remitoDetalleDto.setCodigo(bulto.getCodigo());
                   // remitoDetalleDto.setTieneBultoOPallet("true");
                //}
            //}
            //if(remitoDetalle.getIdPallet() != null) {
                
              //  OrdenDeProduccionPalletModel pallet = palletService.getByPk(remitoDetalle.getIdPallet());
              //  if(pallet != null) {
                //    remitoDetalleDto.setDeposito("Sin información");
                 //   if(pallet.getIdDeposito() != null) {
                 //       TipoModel deposito = tipoService.getByPk(pallet.getIdDeposito());
                 //       if(deposito != null) {
                   //         remitoDetalleDto.setDeposito(deposito.getValor());        
                   //     }
                    //}                    
                    //OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(pallet.getIdOrdenDeProduccion());
                    //if(ordenDeProduccion != null) {
                       // remitoDetalleDto.setLote("L"+ordenDeProduccion.getId());
                        
                        //ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
                        //if(articulo != null) {
                          //  remitoDetalleDto.setArticulo("(" + articulo.getId() + ") " + articulo.getDenominacion());
                        //}                        
                    //}
                    //remitoDetalleDto.setCodigo(pallet.getCodigo());
                    //remitoDetalleDto.setTieneBultoOPallet("true");
                //}
           // }
           // if(remitoDetalle.getIdBobina() == null && remitoDetalle.getIdBulto() == null && remitoDetalle.getIdPallet() == null) {
                //remitoDetalleDto.setDeposito("Sin información");   
                //if(remitoDetalle.getIdOrdenDeProduccion() != null) {
                 //   OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(remitoDetalle.getIdOrdenDeProduccion());
                 //   if(ordenDeProduccion != null) {
                  //      remitoDetalleDto.setLote("L"+ordenDeProduccion.getId());
                  //  }                
               // }
                //if(remitoDetalle.getIdArticulo() != null) {
                  //  ArticuloModel articulo = articuloService.getByPk(remitoDetalle.getIdArticulo());
                  //  if(articulo != null) {
                    //    remitoDetalleDto.setArticulo("(" + articulo.getId() + ") " + articulo.getDenominacion());
                    //}                        
                //}                
            //}
            
            remitoDetallesDtos.add(remitoDetalleDto);
            
            
        }
        
        OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();   
        List<OrdenDepositoDto> ordenDepositoDto = ordenDeProduccionBobinaService.getAllByDeposito();
        System.out.println(ordenDepositoDto.size());
        
        //ArticuloService articuloService = new ArticuloServiceImpl();
        List<OrdenDepositoDto> ordenDepositoDto2 = ordenDeProduccionBobinaService.getAllByDeposito();
        List<ArticuloModel> articulosModel2 = articuloService.getAll();
        Map<String, String> articuloss = new LinkedHashMap<String, String>();

        if (articulosModel2 != null && !articulosModel2.isEmpty()) {
            for (ArticuloModel articuloModel : articulosModel) {
                articuloss.put(articuloModel.getId().toString(), articuloModel.getDenominacion());
            }
       }
        
        ActivacionManualService activacionManualService = new ActivacionManualServiceImpl();
        ActivacionManualModel activacionManual = activacionManualService.getByPk(1);
       
        System.out.println("**** idOrdenDeProduccion:"+remitoDetallesDtos);
        
        model.addAttribute("statusAct", activacionManual.getActivacionManual() || rol.equalsIgnoreCase("oficina"));
                
        model.addAttribute("remitoPk", remito.getId().toString());
        model.addAttribute("estadoRemito", remito.getEstado().toLowerCase());
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "new");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("articulosList", articulos);        
        model.addAttribute("remitoDetalles", remitoDetallesDtos);
        model.addAttribute("articuloList2", articuloss);
        model.addAttribute("depositos", ordenDepositoDto2);
        model.addAttribute("remito", remitoDetalleForm);
        model.addAttribute("egresoDepositoForm", egresoDepositoForm);  
        return "/remito/remitodetalle";
    }
    
    @ResponseBody
    @RequestMapping(value = "/remitoDetalle/getOrdenesDeProduccion/{idArticulo}/remito/{idRemito}", method = RequestMethod.GET)
    public List<OrdenDeCompraBean> getOrdenesDeProduccion(@PathVariable String idArticulo, @PathVariable String idRemito, HttpServletRequest req, ModelMap model) throws Exception {
        
        List<OrdenDeCompraBean> result = new ArrayList<OrdenDeCompraBean>();
        
        if(idArticulo != null && !idArticulo.isEmpty() && idRemito != null && !idRemito.isEmpty()) {
            
            RemitoService remitoService = new RemitoServiceImpl();
            RemitoModel remito = remitoService.getByPk(Integer.valueOf(idRemito));
            
            if(remito != null) {
                OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();               
                List<OrdenDeProduccionModel> ordenDeProducciones = null;
                if(remito.getTipoRemito().equalsIgnoreCase("Salida")) {
                    ordenDeProducciones = ordenDeProduccionService.getAllWithStock();
                } else {
                    ordenDeProducciones = ordenDeProduccionService.getAllCompleted();
                }
                if(!ordenDeProducciones.isEmpty()) {
                    for(OrdenDeProduccionModel ordenDeProduccion :ordenDeProducciones) {
                        if(ordenDeProduccion.getIdArticulo().toString().equalsIgnoreCase(idArticulo)) {
                            OrdenDeCompraBean bean = new OrdenDeCompraBean();
                            bean.setId(ordenDeProduccion.getId().toString());
                            bean.setCodigo("L"+ordenDeProduccion.getId().toString());

                            result.add(bean);
                        }
                    }                            
                }
            }
        }
        
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/remitoDetalle/getBultos/{idOrdenDeProduccion}/remito/{idRemito}", method = RequestMethod.GET)
    public List<ItemBean> getBultos(@PathVariable String idOrdenDeProduccion, @PathVariable String idRemito, HttpServletRequest req, ModelMap model) throws Exception {
        
        List<ItemBean> result = new ArrayList<ItemBean>();
        
        System.out.println("**** idOrdenDeProduccion:"+idOrdenDeProduccion);
        
        if(idOrdenDeProduccion != null && !idOrdenDeProduccion.isEmpty() && idRemito != null && !idRemito.isEmpty()) {
            
            RemitoService remitoService = new RemitoServiceImpl();   
            RemitoModel remito = remitoService.getByPk(Integer.valueOf(idRemito));
            
            OrdenDeProduccionBultoService ordenDeProduccionBultoService = new OrdenDeProduccionBultoServiceImpl();   
            
            if(remito != null) {
                List<OrdenDeProduccionBultoModel> bultos = null;
                if(remito.getTipoRemito().equalsIgnoreCase("Salida")) {
                    bultos = ordenDeProduccionBultoService.getAllAvailableForRemitoByOrdenDeProduccion(Integer.valueOf(idOrdenDeProduccion));
                }
                if(remito.getTipoRemito().equalsIgnoreCase("Entrada")) {
                    bultos = ordenDeProduccionBultoService.getAllNotAvailableForRemitoByOrdenDeProduccion(Integer.valueOf(idOrdenDeProduccion));
                }                

                if(bultos != null && !bultos.isEmpty()) {
                    for(OrdenDeProduccionBultoModel bulto :bultos) {

                        //RemitoDetalleModel remitoDetalle = remitoDetalleService.getByBulto(bulto.getId());
                        //if(remitoDetalle == null) {
                            ItemBean bean = new ItemBean();
                            bean.setId(bulto.getId().toString());
                            bean.setCodigo(bulto.getCodigo());
                            bean.setEstado(bulto.getEstado());

                            result.add(bean);
                        //}
                    }   
                }
            }
        }
        
        System.out.println("**** result size:"+result.size());
        
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/remitoDetalle/getPallets/{idOrdenDeProduccion}/remito/{idRemito}", method = RequestMethod.GET)
    public List<ItemBean> getPallets(@PathVariable String idOrdenDeProduccion, @PathVariable String idRemito, HttpServletRequest req, ModelMap model) throws Exception {
        
        List<ItemBean> result = new ArrayList<ItemBean>();
        
        if(idOrdenDeProduccion != null && !idOrdenDeProduccion.isEmpty() && idRemito != null && !idRemito.isEmpty()) {
            
            
            RemitoService remitoService = new RemitoServiceImpl();   
            RemitoModel remito = remitoService.getByPk(Integer.valueOf(idRemito));
            
            OrdenDeProduccionPalletService ordenDeProduccionPalletService = new OrdenDeProduccionPalletServiceImpl();   
            
            if(remito != null) {            
                List<OrdenDeProduccionPalletModel> pallets = null;
                if(remito.getTipoRemito().equalsIgnoreCase("Salida")) {
                    pallets = ordenDeProduccionPalletService.getAllAvailableForRemitoByOrdenDeProduccion(Integer.valueOf(idOrdenDeProduccion));
                }                
                if(remito.getTipoRemito().equalsIgnoreCase("Entrada")) {
                    pallets = ordenDeProduccionPalletService.getAllNotAvailableForRemitoByOrdenDeProduccion(Integer.valueOf(idOrdenDeProduccion));
                }
                
                if(pallets != null && !pallets.isEmpty()) {
                    for(OrdenDeProduccionPalletModel pallet :pallets) {

                        //RemitoDetalleModel remitoDetalle = remitoDetalleService.getByBulto(pallet.getId());
                        //if(remitoDetalle == null) {
                            ItemBean bean = new ItemBean();
                            bean.setId(pallet.getId().toString());
                            bean.setCodigo(pallet.getCodigo());
                            bean.setEstado(pallet.getEstado());

                            result.add(bean);
                        //}
                    }   
                }
            }
        }
        
        return result;
    }
        
    @ResponseBody
    @RequestMapping(value = "/remitoDetalle/getEstadoBulto/{idBulto}", method = RequestMethod.GET)
    public List<ItemBean> getEstadoBulto(@PathVariable String idBulto, HttpServletRequest req, ModelMap model) throws Exception {
        
        List<ItemBean> result = new ArrayList<ItemBean>();
        
        if(idBulto != null && !idBulto.isEmpty()) {
            
            TipoService tipoService = new TipoServiceImpl();   
            OrdenDeProduccionBultoService bultoDetalleService = new OrdenDeProduccionBultoServiceImpl();   
            OrdenDeProduccionBultoModel bulto = bultoDetalleService.getByPk(Integer.valueOf(idBulto));
            if(bulto !=  null) {
                ItemBean bean = new ItemBean();
                bean.setId(bulto.getId().toString());
                bean.setCodigo(bulto.getCodigo());
                bean.setEstado(bulto.getEstado());
                bean.setDepositoActual("Sin información");
                if(bulto.getIdDeposito() != null) {
                    TipoModel deposito = tipoService.getByPk(bulto.getIdDeposito());
                    if(deposito != null) {
                        bean.setDepositoActual(deposito.getValor());
                    }
                } 
                
                result.add(bean);                
            }                    
        }
        
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/remitoDetalle/getEstadoPallet/{idPallet}", method = RequestMethod.GET)
    public List<ItemBean> getEstadoPallet(@PathVariable String idPallet, HttpServletRequest req, ModelMap model) throws Exception {
        
        List<ItemBean> result = new ArrayList<ItemBean>();
        
        if(idPallet != null && !idPallet.isEmpty()) {
            
            TipoService tipoService = new TipoServiceImpl();   
            OrdenDeProduccionPalletService palletDetalleService = new OrdenDeProduccionPalletServiceImpl();   
            OrdenDeProduccionPalletModel pallet = palletDetalleService.getByPk(Integer.valueOf(idPallet));
            if(pallet !=  null) {
                ItemBean bean = new ItemBean();
                bean.setId(pallet.getId().toString());
                bean.setCodigo(pallet.getCodigo());
                bean.setEstado(pallet.getEstado());
                bean.setDepositoActual("Sin información");
                if(pallet.getIdDeposito() != null) {
                    TipoModel deposito = tipoService.getByPk(pallet.getIdDeposito());
                    if(deposito != null) {
                        bean.setDepositoActual(deposito.getValor());
                    }
                } 

                result.add(bean);                
            }                    
        }
        
        return result;
    }
    
    @ResponseBody
    @RequestMapping(value = "/remitoDetalle/getStockOrdenesDeProduccion/{idOrdenDeProduccion}", method = RequestMethod.GET)
    public List<StockOrdenDeCompraBean> getStockOrdenesDeProduccion(@PathVariable String idOrdenDeProduccion, HttpServletRequest req, ModelMap model) throws Exception {
        
        List<StockOrdenDeCompraBean> result = new ArrayList<StockOrdenDeCompraBean>();
        
        if(idOrdenDeProduccion != null && !idOrdenDeProduccion.isEmpty()) {
            
            OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();   
            OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(Integer.valueOf(idOrdenDeProduccion));
            if(ordenDeProduccion != null) {
                    StockOrdenDeCompraBean bean = new StockOrdenDeCompraBean();
                    bean.setUnidadesProducidas(ordenDeProduccion.getCantidadAProducir().toString());
                    bean.setStockActual(ordenDeProduccion.getStockActual().toString());                
                    
                    result.add(bean);
            }
        }
        
        return result;
    }
    
    @RequestMapping(value = "/remitoDetalle/addOrEditOrRemove", method = RequestMethod.POST)
    public ModelAndView addOrEditOrRemoveRemitoDetalle(@ModelAttribute("remitoDetalleForm")RemitoDetalleForm remitoDetalleForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        ModelAndView modelAndView = new ModelAndView();
        
        if (result.hasErrors()) {            
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error 01");
            return modelAndView;
        }
        
        if(!Utils.isAutenticated(req)) {            
            modelAndView.setViewName("/login/login");    
            model.addAttribute("userForm", new UserForm());         
            return modelAndView;
        }
        
        if(remitoDetalleForm.getAction() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error 02");
            return modelAndView;            
        }
        
        String operacion = remitoDetalleForm.getOperacion();
        
        System.out.println("o*******"+ operacion);
        
        if(operacion == null || (!operacion.equalsIgnoreCase("alta") && !operacion.equalsIgnoreCase("edit") && !operacion.equalsIgnoreCase("remove"))) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: operación inválida.");
            return modelAndView;                        
        }        
        if(remitoDetalleForm.getPk().equalsIgnoreCase("-1") && !operacion.equalsIgnoreCase("alta")) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error 03");
            return modelAndView;                        
        }
        if(remitoDetalleForm.getIdRemito() == null || remitoDetalleForm.getIdRemito().isEmpty()) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: remito id incorrecto.");
            return modelAndView;                        
        }
        
        if(user.getRol() != Utils.ROL_OFICINA) {        
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: no puede realizar esta operación.");
            return modelAndView;                                    
        }
        
//        if((remitoDetalleForm.getTipo() == null || remitoDetalleForm.getTipo().isEmpty()) && (operacion.equalsIgnoreCase("alta") || operacion.equalsIgnoreCase("edit"))){
//            modelAndView.setViewName("error");
//            modelAndView.addObject("errorMessage", "Error: no existe tipo.");
//            return modelAndView;                                                
//        }
        /*
        if((operacion.equalsIgnoreCase("alta") || operacion.equalsIgnoreCase("edit")) && remitoDetalleForm.getTipo().equalsIgnoreCase("bulto")) {
            if(remitoDetalleForm.getIdBulto() == null || remitoDetalleForm.getIdBulto().equalsIgnoreCase("-1")) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: id bulto incorrecto.");
                return modelAndView;                                                                        
            }
        }
        if((operacion.equalsIgnoreCase("alta") || operacion.equalsIgnoreCase("edit")) && remitoDetalleForm.getTipo().equalsIgnoreCase("pallet")) {
            if(remitoDetalleForm.getIdPallet() == null || remitoDetalleForm.getIdPallet().equalsIgnoreCase("-1")) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: id pallet incorrecto.");
                return modelAndView;                                                                        
            }
        }
        */

//        if((operacion.equalsIgnoreCase("alta") || operacion.equalsIgnoreCase("edit")) && remitoDetalleForm.getCantidadItem() == null && remitoDetalleForm.getCantidadItem().isEmpty()) {
//            modelAndView.setViewName("error");
//            modelAndView.addObject("errorMessage", "Error: cantidad incorrecta.");
//            return modelAndView;                                                                        
//        }
//        
//        if((operacion.equalsIgnoreCase("alta") || operacion.equalsIgnoreCase("edit")) && Double.valueOf(remitoDetalleForm.getCantidadItem()) > Double.valueOf(remitoDetalleForm.getStock())) {
//            modelAndView.setViewName("error");
//            modelAndView.addObject("errorMessage", "Error: la cantidad debe ser menor que el stock.");
//            return modelAndView;                                                                        
//        }
        
        RemitoService remitoService = new RemitoServiceImpl();        
        RemitoDetalleService remitoDetalleService = new RemitoDetalleServiceImpl();        
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();        
        OrdenDeProduccionBultoService ordenDeProduccionBultoService = new OrdenDeProduccionBultoServiceImpl();        
        OrdenDeProduccionPalletService ordenDeProduccionPalletService = new OrdenDeProduccionPalletServiceImpl();        
        
        OrdenDeProduccionModel ordenDeProduccion = null;
//        if(operacion.equalsIgnoreCase("alta") || operacion.equalsIgnoreCase("edit")) {
//            ordenDeProduccion = ordenDeProduccionService.getByPk(Integer.valueOf(remitoDetalleForm.getIdOrdenDeProduccion()));
//            if(ordenDeProduccion == null){
//                modelAndView.setViewName("error");
//                modelAndView.addObject("errorMessage", "Error: orden de producción inválida.");
//                return modelAndView;                                    
//            }
//        }
        
//        OrdenDeProduccionBultoModel bulto = null;
//        if((operacion.equalsIgnoreCase("alta") || operacion.equalsIgnoreCase("edit")) && remitoDetalleForm.getTipo().equalsIgnoreCase("bulto") && !remitoDetalleForm.getIdBulto().equals("-1")) {
//            bulto = ordenDeProduccionBultoService.getByPk(Integer.valueOf(remitoDetalleForm.getIdBulto()));
//            if(bulto == null) {
//                modelAndView.setViewName("error");
//                modelAndView.addObject("errorMessage", "Error: bulto inexistente.");
//                return modelAndView;                                                                                        
//            }
//            if(bulto.getIdOrdenDeProduccion() != ordenDeProduccion.getId()) { 
//                modelAndView.setViewName("error");
//                modelAndView.addObject("errorMessage", "Error: conexión errónea entre bulto y orden de produccion.");
//                return modelAndView;                                                                                                        
//            }
//        }
//
//        OrdenDeProduccionPalletModel pallet = null;
//        if((operacion.equalsIgnoreCase("alta") || operacion.equalsIgnoreCase("edit")) && remitoDetalleForm.getTipo().equalsIgnoreCase("pallet") && !remitoDetalleForm.getIdPallet().equals("-1")) {
//            pallet = ordenDeProduccionPalletService.getByPk(Integer.valueOf(remitoDetalleForm.getIdPallet()));
//            if(pallet == null) {
//                modelAndView.setViewName("error");
//                modelAndView.addObject("errorMessage", "Error: pallet inexistente.");
//                return modelAndView;                                                                                        
//            }
//            if(pallet.getIdOrdenDeProduccion() != ordenDeProduccion.getId()) { 
//                modelAndView.setViewName("error");
//                modelAndView.addObject("errorMessage", "Error: conexión errónea entre pallet y orden de produccion.");
//                return modelAndView;                                                                                                        
//            }            
//        }
        
        RemitoModel remito = remitoService.getByPk(Integer.valueOf(remitoDetalleForm.getIdRemito()));
        if(remito == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: remito incorrecto.");
            return modelAndView;                        
        }

        String sessionId = req.getSession().getId();
        String id = remitoDetalleForm.getPk();
        String idDeposito = remitoDetalleForm.getIdDeposito();
        String idOrden = remitoDetalleForm.getIdOrdenDeProduccion();
        String cantidad = remitoDetalleForm.getCantidad();
        String numeroSinComa = idOrden.replace(",", "");
   
            
        RemitoDetalleModel remitoDetalleModel = null;
        TipoService tipoService = new TipoServiceImpl();

            TipoModel tipo = tipoService.getByValor(idDeposito);
            int idDeposito0 = -1;

            if (tipo != null) {
                idDeposito0 = tipo.getId();
              
            } else {
                
            }
        if(id.equalsIgnoreCase("-1")) {
            remitoDetalleModel = new RemitoDetalleModel();
            remitoDetalleModel.setFechaAlta(new Date());
            remitoDetalleModel.setIdUsuarioAlta(Integer.valueOf(Utils.getUserLoggedId(req)));            
            remitoDetalleModel.setIdRemito(remito.getId());
            
            remitoDetalleModel.setIdDeposito(idDeposito0);
            remitoDetalleModel.setIdOrdenDeProduccion((Integer.parseInt(numeroSinComa)));
            remitoDetalleModel.setCantidad(Integer.valueOf(cantidad));
            remitoDetalleModel.setCantidadBaja(0);
                       
        } else {
            remitoDetalleModel = remitoDetalleService.getByPk(Integer.valueOf(id));
            if(remitoDetalleModel == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: id de remito detalle inválido.");
                return modelAndView;                
            } 
        }        
        
//        if(operacion.equalsIgnoreCase("alta") && (remitoDetalleForm.getAction().equalsIgnoreCase("add") || remitoDetalleForm.getAction().equalsIgnoreCase("edit"))) {
//            if(remitoDetalleForm.getTipo().equalsIgnoreCase("bulto")) {
//                remitoDetalleModel.setIdBobina(null);
//                remitoDetalleModel.setIdPallet(null);
//                if(bulto != null) {
//                    remitoDetalleModel.setIdBulto(bulto.getId());
//                    remitoDetalleModel.setTieneBultoOPallet(Boolean.TRUE);
//                } else {
//                    remitoDetalleModel.setIdBulto(null);
//                    remitoDetalleModel.setTieneBultoOPallet(Boolean.FALSE);
//                }
//                if(bulto != null) {
//                    if(remito.getTipoRemito().equalsIgnoreCase("Salida")) {
//                        bulto.setEstaDisponibleParaRemito(Boolean.FALSE);
//                    } else {
//                        bulto.setEstaDisponibleParaRemito(Boolean.TRUE);
//                    }
//                }
//                ordenDeProduccionBultoService.save(bulto);
//            }
//            if(remitoDetalleForm.getTipo().equalsIgnoreCase("pallet")) {
//                remitoDetalleModel.setIdBobina(null);
//                if(pallet != null) {
//                    remitoDetalleModel.setIdPallet(pallet.getId());
//                    remitoDetalleModel.setTieneBultoOPallet(Boolean.TRUE);
//                } else {
//                    remitoDetalleModel.setIdPallet(null);
//                    remitoDetalleModel.setTieneBultoOPallet(Boolean.FALSE);
//                }
//                remitoDetalleModel.setIdBulto(null);
//                
//                if(pallet != null) {
//                    if(remito.getTipoRemito().equalsIgnoreCase("Salida")) {
//                        pallet.setEstaDisponibleParaRemito(Boolean.FALSE);
//                    } else {
//                        pallet.setEstaDisponibleParaRemito(Boolean.TRUE);
//                    }
//                }
//                ordenDeProduccionPalletService.save(pallet);                
//            }
//            remitoDetalleModel.setCantidad(Integer.valueOf(remitoDetalleForm.getCantidadItem()));
//            
//        }

        
        if(remitoDetalleForm.getAction().equalsIgnoreCase("add") || remitoDetalleForm.getAction().equalsIgnoreCase("edit")) {
            RemitoDetalleModel remitoDetalleExistente = remitoDetalleService.getByCompositeIds(remito.getId(), idDeposito0, (Integer.parseInt(numeroSinComa)));
            
            if (remitoDetalleExistente != null) {
               
                
                int nuevaCantidad = remitoDetalleExistente.getCantidad() + Integer.valueOf(cantidad);
                remitoDetalleExistente.setCantidad(nuevaCantidad);
               
                remitoDetalleService.save(remitoDetalleExistente);
            } else {
               
                remitoDetalleService.save(remitoDetalleModel);
            }
     
            
            
            //Integer stockActual = ordenDeProduccion.getStockActual();
//            if(remito.getTipoRemito().equalsIgnoreCase("Salida")) {
//                stockActual = stockActual - remitoDetalleModel.getCantidad();
//            }
//            if(remito.getTipoRemito().equalsIgnoreCase("Entrada")) {
//                stockActual = stockActual + remitoDetalleModel.getCantidad();
//            }
            
//            ordenDeProduccion.setStockActual(stockActual);
//            ordenDeProduccionService.save(ordenDeProduccion);
        } else {
            if(remitoDetalleForm.getAction().equalsIgnoreCase("remove")) {
                if(remitoDetalleModel.getId() == null) {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("errorMessage", "Error: id de remitoDetalle inválido.");
                    return modelAndView;                                    
                }
                
                System.out.println("*** REMOVE ***");
                 remitoDetalleService.delete(remitoDetalleModel);
                
//                if(remitoDetalleForm.getViewTipoHdn().equalsIgnoreCase("Bulto")) {
//                    if(remito.getTipoRemito().equalsIgnoreCase("Salida")){                        
//                        OrdenDeProduccionBultoModel bulto2 = ordenDeProduccionBultoService.getByCode(remitoDetalleForm.getViewCodigoHdn());
//                                                
//                        //if(bulto2 == null) {
//                        //    modelAndView.setViewName("error");
//                        //    modelAndView.addObject("errorMessage", "Error: bulto no encontrado.");
//                        //    return modelAndView;                                                                
//                        //}
//                        if(bulto2 != null) {
//                            bulto2.setEstaDisponibleParaRemito(Boolean.TRUE);
//                             ordenDeProduccion = ordenDeProduccionService.getByPk(bulto2.getIdOrdenDeProduccion());
//                        } else {
//                             ordenDeProduccion = ordenDeProduccionService.getByPk(Integer.valueOf(remitoDetalleForm.getIdOrdenDeProduccionRemove()));
//                        }
//                                                
//                        if(ordenDeProduccion == null) {
//                            modelAndView.setViewName("error");
//                            modelAndView.addObject("errorMessage", "Error: orden de produccion no encontrado.");
//                            return modelAndView;                                                                                            
//                        }
//                        Integer stockActual = ordenDeProduccion.getStockActual();
//                        stockActual = stockActual + remitoDetalleModel.getCantidad();
//                        ordenDeProduccion.setStockActual(stockActual);                        
//                        
//                        if(bulto2 != null) {
//                            ordenDeProduccionBultoService.save(bulto2);
//                        }
//                        ordenDeProduccionService.save(ordenDeProduccion);
//                    }
//                }
//                if(remitoDetalleForm.getViewTipoHdn().equalsIgnoreCase("Pallet")) {
//                    if(remito.getTipoRemito().equalsIgnoreCase("Salida")){
//                        OrdenDeProduccionPalletModel pallet2 = ordenDeProduccionPalletService.getByCode(remitoDetalleForm.getViewCodigoHdn());
//                        
//                        //if(pallet2 == null) {
//                        //    modelAndView.setViewName("error");
//                        //    modelAndView.addObject("errorMessage", "Error: pallet no encontrado.");
//                        //    return modelAndView;                                                                
//                        //}
//                        if(pallet2 != null) {
//                            pallet2.setEstaDisponibleParaRemito(Boolean.TRUE);
//                            ordenDeProduccion = ordenDeProduccionService.getByPk(pallet2.getIdOrdenDeProduccion());
//                        }else {
//                             ordenDeProduccion = ordenDeProduccionService.getByPk(Integer.valueOf(remitoDetalleForm.getIdOrdenDeProduccionRemove()));
//                        }
//                                                
//                        if(ordenDeProduccion == null) {
//                            modelAndView.setViewName("error");
//                            modelAndView.addObject("errorMessage", "Error: orden de produccion no encontrado.");
//                            return modelAndView;                                                                                            
//                        }
//                        Integer stockActual = ordenDeProduccion.getStockActual();
//                        stockActual = stockActual + remitoDetalleModel.getCantidad();
//                        ordenDeProduccion.setStockActual(stockActual);                        
//                        
//                        if(pallet2 != null) {
//                            ordenDeProduccionPalletService.save(pallet2);
//                        }
//                        ordenDeProduccionService.save(ordenDeProduccion);
//                    }
//                }
//                if(remitoDetalleForm.getViewTipoHdn().equalsIgnoreCase("Bulto")) {
//                    if(remito.getTipoRemito().equalsIgnoreCase("Entrada")){
//                        OrdenDeProduccionBultoModel bulto2 = ordenDeProduccionBultoService.getByCode(remitoDetalleForm.getViewCodigoHdn());
//                        
//                        //if(bulto == null) {
//                        //    modelAndView.setViewName("error");
//                        //    modelAndView.addObject("errorMessage", "Error: bulto no encontrado.");
//                        //    return modelAndView;                                                                
//                        //}
//                        if(bulto2 != null) {
//                            bulto2.setEstaDisponibleParaRemito(Boolean.TRUE);
//                            ordenDeProduccion = ordenDeProduccionService.getByPk(bulto2.getIdOrdenDeProduccion());
//                        } else {
//                            ordenDeProduccion = ordenDeProduccionService.getByPk(Integer.valueOf(remitoDetalleForm.getIdOrdenDeProduccionRemove()));
//                        }
//                                                
//                        if(ordenDeProduccion == null) {
//                            modelAndView.setViewName("error");
//                            modelAndView.addObject("errorMessage", "Error: orden de produccion no encontrado.");
//                            return modelAndView;                                                                                            
//                        }
//                        Integer stockActual = ordenDeProduccion.getStockActual();
//                        stockActual = stockActual - remitoDetalleModel.getCantidad();
//                        ordenDeProduccion.setStockActual(stockActual);                        
//                        
//                        if(bulto2 != null) {
//                            ordenDeProduccionBultoService.save(bulto2);
//                        }
//                        ordenDeProduccionService.save(ordenDeProduccion);
//                    }
//                }
//                if(remitoDetalleForm.getViewTipoHdn().equalsIgnoreCase("Pallet")) {
//                    if(remito.getTipoRemito().equalsIgnoreCase("Entrada")){
//                        OrdenDeProduccionPalletModel pallet2 = ordenDeProduccionPalletService.getByCode(remitoDetalleForm.getViewCodigoHdn());
//                        
//                        //if(pallet2 == null) {
//                        //    modelAndView.setViewName("error");
//                        //    modelAndView.addObject("errorMessage", "Error: pallet no encontrado.");
//                        //    return modelAndView;                                                                
//                        //}
//                        if(pallet2 != null) {
//                            pallet2.setEstaDisponibleParaRemito(Boolean.TRUE);
//                            ordenDeProduccion = ordenDeProduccionService.getByPk(pallet2.getIdOrdenDeProduccion());
//                        } else {
//                            ordenDeProduccion = ordenDeProduccionService.getByPk(Integer.valueOf(remitoDetalleForm.getIdOrdenDeProduccionRemove()));
//                        }
//                                                
//                        if(ordenDeProduccion == null) {
//                            modelAndView.setViewName("error");
//                            modelAndView.addObject("errorMessage", "Error: orden de produccion no encontrado.");
//                            return modelAndView;                                                                                            
//                        }
//                        Integer stockActual = ordenDeProduccion.getStockActual();
//                        stockActual = stockActual - remitoDetalleModel.getCantidad();
//                        ordenDeProduccion.setStockActual(stockActual);                        
//                        
//                        if(pallet2 != null) {
//                            ordenDeProduccionPalletService.save(pallet2);
//                        }
//                        ordenDeProduccionService.save(ordenDeProduccion);
//                    }
//                }
//                
//                remitoDetalleService.delete(remitoDetalleModel);
                                
            } else {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: operación inválida.");
                return modelAndView;                                
            }
        }
                        
        modelAndView.setViewName("redirect:/remitoDetalle/"+remito.getId().toString());

        return modelAndView; 
    }

    /*
    @RequestMapping(value = "/remitoDetalle/edit/{remitoDetallepk}", method = RequestMethod.GET)
    public String editRemitoDetalle(@PathVariable String remitoDetallepk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(remitoDetallepk == null) {
            model.addAttribute("errorMessage", "Error: RemitoDetalle inválido");         
            return "/error";                
        }
        
        String operacion = "alta";        
        String displayActionButton = "block";
        
        RemitoDetalleService remitoDetalleService = new RemitoDetalleServiceImpl();   
        RemitoDetalleModel remitoDetalle = remitoDetalleService.getByPk(Integer.valueOf(remitoDetallepk));
        if(remitoDetalle == null) {
            model.addAttribute("errorMessage", "Error: RemitoDetalle inválido. No ha sido encontrado.");         
            return "/error";    
        }

        if(remitoDetalle.getIdCliente() == null) {
            model.addAttribute("errorMessage", "Error: RemitoDetalle inválido. No posee cliente.");         
            return "/error";    
        }
        
        if(!remitoDetalle.getEstado().equalsIgnoreCase("Nuevo")) {
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
        
        RemitoDetalleForm remitoDetalleForm = new RemitoDetalleForm();
        remitoDetalleForm.setPk(remitoDetalle.getId().toString());
        if(remitoDetalle.getFechaAlta() != null) {
            remitoDetalleForm.setFechaAlta(remitoDetalle.getFechaAlta().toString().replace(".0", ""));
        }
        if(remitoDetalle.getFechaRemitoDetalle() != null) {
            remitoDetalleForm.setFechaRemitoDetalle(remitoDetalle.getFechaRemitoDetalle().toString().replace(" 00:00:00.0", ""));
        }        
        if(remitoDetalle.getTipoRemitoDetalle() != null) {
            remitoDetalleForm.setTipoRemitoDetalle(remitoDetalle.getTipoRemitoDetalle());
        }                
        if(remitoDetalle.getIdCliente() != null) {
            remitoDetalleForm.setIdCliente(remitoDetalle.getIdCliente().toString());
        }        
        if(remitoDetalle.getIdClienteDomicilio() != null) {
            remitoDetalleForm.setIdClienteDomicilio(remitoDetalle.getIdClienteDomicilio().toString());
            remitoDetalleForm.setExistingDomicilio(remitoDetalle.getIdClienteDomicilio().toString());
        }        
        if(remitoDetalle.getIdTransporte() != null) {
            remitoDetalleForm.setIdTransporte(remitoDetalle.getIdTransporte().toString());
        }                
        if(remitoDetalle.getReferenciaAdministrativa() != null && !remitoDetalle.getReferenciaAdministrativa().isEmpty()) {
            remitoDetalleForm.setReferenciaAdministrativa(remitoDetalle.getReferenciaAdministrativa());
        }                
        if(remitoDetalle.getEstado() != null && !remitoDetalle.getEstado().isEmpty()) {
            remitoDetalleForm.setEstado(remitoDetalle.getEstado());
        }        
        
        remitoDetalleForm.setOperacion(operacion);        
        
        ClienteService clienteService = new ClienteServiceImpl();   
        ClienteModel cliente = clienteService.getByPk(remitoDetalle.getIdCliente());
        
        if(cliente == null) {
            model.addAttribute("errorMessage", "Error: No se encuentra el cliente.");         
            return "/error";                                                                
        }

        
        remitoDetalleForm.setAction("edit");
        model.addAttribute("remitoDetalleForm", remitoDetalleForm);  
        model.addAttribute("titleRemitoDetalle", "Editar RemitoDetalle");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("remitoDetalleName", "RemitoDetalle: " + remitoDetalle.getId() + " - Cliente :" + cliente.getRazonSocial());        
        
        List<RemitoDetalleModel> remitoDetalles = remitoDetalleService.getAll();

        Map<String,String> clientes = new LinkedHashMap<String,String>();
        List<ClienteModel> clientesModel = clienteService.getAll();

        if(clientesModel != null && !clientesModel.isEmpty()){
            for(ClienteModel clienteModel :clientesModel) {
                clientes.put(clienteModel.getId().toString(), clienteModel.getRazonSocial());
            }
        }

        TipoService tipoService = new TipoServiceImpl();   
        Map<String,String> transportes = new LinkedHashMap<String,String>();
        List<TipoModel> transportesModel = tipoService.getByType("transporte");

        if(transportesModel != null && !transportesModel.isEmpty()){
            for(TipoModel tipoModel :transportesModel) {
                transportes.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        List<RemitoDetalleDto> remitoDetallesDtos = new ArrayList<RemitoDetalleDto>();
        for(RemitoDetalleModel remitoDetalleModel: remitoDetalles) {
            RemitoDetalleDto remitoDetalleDto = new RemitoDetalleDto();
            remitoDetalleDto.setPk(remitoDetalleModel.getId().toString());
            remitoDetalleDto.setFechaAlta(remitoDetalleModel.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            if(remitoDetalleModel.getFechaRemitoDetalle() != null) {
                remitoDetalleDto.setFechaRemitoDetalle(remitoDetalleModel.getFechaRemitoDetalle().toString().replace(" 00:00:00.0", ""));
            }
            if(remitoDetalleModel.getReferenciaAdministrativa() != null) {
                remitoDetalleDto.setReferenciaAdministrativa(remitoDetalleModel.getReferenciaAdministrativa());
            }            
            remitoDetalleDto.setEstado(remitoDetalleModel.getEstado());
            remitoDetalleDto.setCliente(clientes.get(remitoDetalleModel.getIdCliente().toString()));
            
            remitoDetallesDtos.add(remitoDetalleDto);
            
            
        }

        String rol = "";
        if(user.getRol() == Utils.ROL_OFICINA) {
            rol = "oficina";
        }
        
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "edit");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("clienteList", clientes);        
        model.addAttribute("transporteList", transportes);        
        model.addAttribute("remitoDetalles", remitoDetallesDtos);
                                                        
        return "/remitoDetalle/remitoDetalle";        
    }
    
    */
    
    @RequestMapping(value = "/remitoDetalle/edit/{remitoDetallepk}", method = RequestMethod.GET)
    public String editRemitoDetalle(@PathVariable String remitoDetallepk, HttpServletRequest req, ModelMap model) throws Exception {
    
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(remitoDetallepk == null) {
            model.addAttribute("errorMessage", "Error: RemitoDetalle inválido");         
            return "/error";                
        }
        
        String operacion = "alta";        
        String displayActionButton = "block";
        
        RemitoDetalleService remitoDetalleService = new RemitoDetalleServiceImpl();   
        RemitoDetalleModel remitoDetalle = remitoDetalleService.getByPk(Integer.valueOf(remitoDetallepk));
        if(remitoDetalle == null) {
            model.addAttribute("errorMessage", "Error: RemitoDetalle inválido. No ha sido encontrado.");         
            return "/error";    
        }

        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(user.getRol() != Utils.ROL_OFICINA) {
            model.addAttribute("errorMessage", "Error: usuario no permite esta operación.");         
            return "/error";                                                                
        }

        RemitoService remitoService = new RemitoServiceImpl();   
        RemitoModel remito = remitoService.getByPk(Integer.valueOf(remitoDetalle.getIdRemito()));
        if(remito == null) {
            model.addAttribute("errorMessage", "Error: Remito inválido. No ha sido encontrado.");         
            return "/error";    
        }

        if(remito.getEstado().equalsIgnoreCase("Completado")) {
            model.addAttribute("errorMessage", "Error: Remito con estado inválido. No es posible realizar este operación.");         
            return "/error";    
        }
                
        ClienteService clienteService = new ClienteServiceImpl();   
        ClienteDomicilioService clienteDomicilioService = new ClienteDomicilioServiceImpl();   
        DomicilioService domicilioService = new DomicilioServiceImpl();   
        LocalidadService localidadService = new LocalidadServiceImpl();   
        TipoService tipoService = new TipoServiceImpl();   
        
                
        RemitoDetalleForm remitoDetalleForm = new RemitoDetalleForm();
        remitoDetalleForm.setPk(remitoDetalle.getId().toString());
        remitoDetalleForm.setAction("edit");
        remitoDetalleForm.setIdRemito(remito.getId().toString());
        remitoDetalleForm.setCodigoRemito(remito.getId().toString());
        remitoDetalleForm.setFechaRemito(remito.getFechaRemito().toString().replace("00:00:00.0", ""));
        remitoDetalleForm.setTipoRemito(remito.getTipoRemito());
        remitoDetalleForm.setEstadoRemito(remito.getEstado());        
        if(remitoDetalle.getCantidad() != null) {
            remitoDetalleForm.setCantidadItem(remitoDetalle.getCantidad().toString());
        }
        //if(remitoDetalle.getIdArticulo() != null) {
          //  remitoDetalleForm.setIdArticulo(remitoDetalle.getIdArticulo().toString());
        //}
//        if(remitoDetalle.getTieneBultoOPallet() != null && remitoDetalle.getTieneBultoOPallet()) {
//            remitoDetalleForm.setTieneBultoOPallet("true");
//        } else {
//            remitoDetalleForm.setTieneBultoOPallet("false");
//            if(remitoDetalle.getIdOrdenDeProduccion() != null) {
//                remitoDetalleForm.setIdOrdenDeProduccionEdit(remitoDetalle.getIdOrdenDeProduccion().toString());
//            }
//            if(remitoDetalle.getIdArticulo() != null) {
//                remitoDetalleForm.setIdArticuloEdit(remitoDetalle.getIdArticulo().toString());
//            }
//            if(remitoDetalle.getTipo() != null) {
//                remitoDetalleForm.setTipoEdit(remitoDetalle.getTipo());
//            }            
//        }
        if(remito.getReferenciaAdministrativa() != null) {
            remitoDetalleForm.setReferenciaAdministrativa(remito.getReferenciaAdministrativa());
        }
        ClienteModel cliente = null;
        if(remito.getIdCliente() != null) {
            cliente = clienteService.getByPk(remito.getIdCliente());
            if(cliente != null) {
                remitoDetalleForm.setCliente("(" + cliente.getId() + ") " + cliente.getRazonSocial());
            }
        }
        if(remito.getIdDomicilio() != null) {
            DomicilioModel domicilioModel = domicilioService.getByPk(remito.getIdDomicilio());
            if(domicilioModel != null) {
                String localidad = "";
                String provincia = "";
                LocalidadModel localidadModel = localidadService.getByPk(domicilioModel.getIdLocalidad());
                if(localidadModel != null) {
                    localidad = localidadModel.getNombre();

                    TipoModel tipo = tipoService.getByPk(localidadModel.getIdProvincia());
                    if(tipo != null) {
                        provincia = tipo.getValor();
                    }
                }
                remitoDetalleForm.setDomicilio(domicilioModel.getUbicacion() + ", " + localidad + ", " + provincia);
            }
        }        
        if(remito.getIdTransporte() != null) {
            TipoModel tipo = tipoService.getByPk(remito.getIdTransporte());
            if(tipo != null) {
                remitoDetalleForm.setTransporte(tipo.getValor());
            }
        }

        Calendar c = Calendar.getInstance();        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
        String fecha = formato.format(c.getTime());

        remitoDetalleForm.setFechaAlta(fecha);

        String rol = "";
        
        if(user.getRol() == Utils.ROL_OFICINA) {
            rol = "oficina";
        }
                
        remitoDetalleForm.setOperacion(operacion);
                
        OrdenDeProduccionBultoService ordenDeProduccionBultoService = new OrdenDeProduccionBultoServiceImpl();
        OrdenDeProduccionPalletService ordenDeProduccionPalletService = new OrdenDeProduccionPalletServiceImpl();
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();
        ArticuloService articuloService = new ArticuloServiceImpl();
        
//        if(remitoDetalle.getIdBulto() != null) {
//            OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(remitoDetalle.getIdBulto());
//            if(bulto != null) {
//                OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(bulto.getIdOrdenDeProduccion());
//                if(ordenDeProduccion != null) {
//                    
//                    remitoDetalleForm.setIdOrdenDeProduccion(ordenDeProduccion.getId().toString());
//                    ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());                    
//                    remitoDetalleForm.setViewLote("L"+ordenDeProduccion.getId());
//                    if(articulo != null) {
//                        remitoDetalleForm.setViewArticulo("("+articulo.getId()+") "+articulo.getDenominacion());
//                    }
//                    remitoDetalleForm.setViewCodigo(bulto.getCodigo());
//                    remitoDetalleForm.setViewCodigoHdn(bulto.getCodigo());
//                    remitoDetalleForm.setViewTipo("Bulto");
//                    remitoDetalleForm.setViewTipoHdn("Bulto");
//                    remitoDetalleForm.setViewCantidad(remitoDetalle.getCantidad().toString());
//                }
//            }
//        }
//        if(remitoDetalle.getIdPallet() != null) {
//            OrdenDeProduccionPalletModel pallet = ordenDeProduccionPalletService.getByPk(remitoDetalle.getIdPallet());
//            if(pallet != null) {
//                OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(pallet.getIdOrdenDeProduccion());
//                if(ordenDeProduccion != null) {
//                    remitoDetalleForm.setIdOrdenDeProduccion(ordenDeProduccion.getId().toString());
//                    ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());                    
//                    remitoDetalleForm.setViewLote("L"+ordenDeProduccion.getId());
//                    if(articulo != null) {
//                        remitoDetalleForm.setViewArticulo("("+articulo.getId()+") "+articulo.getDenominacion());
//                    }
//                    remitoDetalleForm.setViewCodigo(pallet.getCodigo());
//                    remitoDetalleForm.setViewCodigoHdn(pallet.getCodigo());
//                    remitoDetalleForm.setViewTipo("Pallet");
//                    remitoDetalleForm.setViewTipoHdn("Pallet");
//                    remitoDetalleForm.setViewCantidad(remitoDetalle.getCantidad().toString());                    
//                }
//            }
//        }
//                
        model.addAttribute("remitoDetalleForm", remitoDetalleForm);  
        model.addAttribute("titleRemitoDetalle", "Editar Remito Detalle");  
        model.addAttribute("buttonLabel", "Guardar");
        
        Map<String,String> articulos = new LinkedHashMap<String,String>();
        List<ArticuloModel> articulosModel = articuloService.getAll();

        List<OrdenDeProduccionModel> ordenDeProducciones = ordenDeProduccionService.getAllWithStock();
        Set<String> articulosSet = new HashSet<String>();
        
        for(OrdenDeProduccionModel ordenDeProduccionCompleted :ordenDeProducciones) {
            articulosSet.add(ordenDeProduccionCompleted.getIdArticulo().toString());
        }
        
        if(articulosModel != null && !articulosModel.isEmpty()){
            for(ArticuloModel articuloModel :articulosModel) {
                if(articulosSet.contains(articuloModel.getId().toString())) {
                    articulos.put(articuloModel.getId().toString(), articuloModel.getDenominacion());
                }
            }
        }
        
        OrdenDeProduccionBobinaService bobinaService = new OrdenDeProduccionBobinaServiceImpl();   
        OrdenDeProduccionBultoService bultoService = new OrdenDeProduccionBultoServiceImpl();   
        OrdenDeProduccionPalletService palletService = new OrdenDeProduccionPalletServiceImpl();                   
        List<RemitoDetalleModel> remitoDetalles = remitoDetalleService.getAllByRemito(remito.getId());
        
        List<RemitoDetalleDto> remitoDetallesDtos = new ArrayList<RemitoDetalleDto>();
        for(RemitoDetalleModel remitoDetalleModel: remitoDetalles) {
            RemitoDetalleDto remitoDetalleDto = new RemitoDetalleDto();
            remitoDetalleDto.setPk(remitoDetalleModel.getId().toString());
            remitoDetalleDto.setFechaAlta(remitoDetalleModel.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            remitoDetalleDto.setCantidad(remitoDetalleModel.getCantidad().toString());
            remitoDetalleDto.setTieneBultoOPallet("false");
            
            //if(remitoDetalleModel.getIngresoDeposito()) {
                //remitoDetalleDto.setIngresoDeposito("Si");
            //} else {
                //remitoDetalleDto.setIngresoDeposito("No");
            //}
            
//            if(remitoDetalleModel.getIdBobina() != null) {
//                
//                OrdenDeProduccionBobinaModel bobina = bobinaService.getByPk(remitoDetalleModel.getIdBobina());
//                if(bobina != null) {
//                    remitoDetalleDto.setDeposito("Sin información");
//                    if(bobina.getIdDeposito() != null) {
//                        TipoModel deposito = tipoService.getByPk(bobina.getIdDeposito());
//                        if(deposito != null) {
//                            remitoDetalleDto.setDeposito(deposito.getValor());        
//                        }
//                    }                    
//
//                    OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(bobina.getIdOrdenDeProduccion());
//                    if(ordenDeProduccion != null) {
//                        remitoDetalleDto.setLote("L"+ordenDeProduccion.getId());                        
//                        
//                        ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
//                        if(articulo != null) {
//                            remitoDetalleDto.setArticulo("(" + articulo.getId() + ") " + articulo.getDenominacion());
//                        }
//                    }
//                    remitoDetalleDto.setCodigo(bobina.getCodigo());
//                    remitoDetalleDto.setTieneBultoOPallet("true");
//                }
//            }
//            if(remitoDetalleModel.getIdBulto() != null) {
//                
//                OrdenDeProduccionBultoModel bulto = bultoService.getByPk(remitoDetalleModel.getIdBulto());
//                if(bulto != null) {
//                    remitoDetalleDto.setDeposito("Sin información");
//                    if(bulto.getIdDeposito() != null) {
//                        TipoModel deposito = tipoService.getByPk(bulto.getIdDeposito());
//                        if(deposito != null) {
//                            remitoDetalleDto.setDeposito(deposito.getValor());        
//                        }
//                    }                    
//                    
//                    OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(bulto.getIdOrdenDeProduccion());
//                    if(ordenDeProduccion != null) {
//                        remitoDetalleDto.setLote("L"+ordenDeProduccion.getId());
//                        
//                        ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
//                        if(articulo != null) {
//                            remitoDetalleDto.setArticulo("(" + articulo.getId() + ") " + articulo.getDenominacion());
//                        }                        
//                    }
//                    remitoDetalleDto.setCodigo(bulto.getCodigo());
//                    remitoDetalleDto.setTieneBultoOPallet("true");
//                }
//            }
//            if(remitoDetalleModel.getIdPallet() != null) {
//                
//                OrdenDeProduccionPalletModel pallet = palletService.getByPk(remitoDetalleModel.getIdPallet());
//                if(pallet != null) {
//                    remitoDetalleDto.setDeposito("Sin información");
//                    if(pallet.getIdDeposito() != null) {
//                        TipoModel deposito = tipoService.getByPk(pallet.getIdDeposito());
//                        if(deposito != null) {
//                            remitoDetalleDto.setDeposito(deposito.getValor());        
//                        }
//                    }                    
//                    
//                    OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(pallet.getIdOrdenDeProduccion());
//                    if(ordenDeProduccion != null) {
//                        remitoDetalleDto.setLote("L"+ordenDeProduccion.getId());
//                        
//                        ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
//                        if(articulo != null) {
//                            remitoDetalleDto.setArticulo("(" + articulo.getId() + ") " + articulo.getDenominacion());
//                        }                        
//                    }
//                    remitoDetalleDto.setCodigo(pallet.getCodigo());
//                    remitoDetalleDto.setTieneBultoOPallet("true");
//                }
//            }
//            if(remitoDetalle.getIdBobina() == null && remitoDetalle.getIdBulto() == null && remitoDetalle.getIdPallet() == null) {
//                remitoDetalleDto.setDeposito("Sin información");   
//                if(remitoDetalle.getIdOrdenDeProduccion() != null) {
//                    OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(remitoDetalle.getIdOrdenDeProduccion());
//                    if(ordenDeProduccion != null) {
//                        remitoDetalleDto.setLote("L"+ordenDeProduccion.getId());
//                    }                
//                }
//                if(remitoDetalle.getIdArticulo() != null) {
//                    ArticuloModel articulo = articuloService.getByPk(remitoDetalle.getIdArticulo());
//                    if(articulo != null) {
//                        remitoDetalleDto.setArticulo("(" + articulo.getId() + ") " + articulo.getDenominacion());
//                    }                        
//                }                
//            }
            
            remitoDetallesDtos.add(remitoDetalleDto);
            
            
        }
        
        model.addAttribute("remitoPk", remito.getId().toString());
        model.addAttribute("estadoRemito", remito.getEstado().toLowerCase());
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "edit");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("articulosList", articulos);        
        model.addAttribute("remitoDetalles", remitoDetallesDtos);        
                
        return "/remito/remitodetalle";
        
    }    
    
    @RequestMapping(value = "/remitoDetalle/remove/{remitoDetallepk}", method = RequestMethod.GET)
    public ModelAndView removeRemitoDetalle(@PathVariable String remitoDetallepk, HttpServletRequest req, ModelMap model) throws Exception {
        
        ModelAndView modelAndView = new ModelAndView();
        
         if(!Utils.isAutenticated(req)) {            
            modelAndView.setViewName("/login/login");    
            model.addAttribute("userForm", new UserForm());         
            return modelAndView;
        }
         
        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(user.getRol() != Utils.ROL_OFICINA) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error:  usuario no permite esta operación.");
            return modelAndView;                                   
        }
 

        if(remitoDetallepk == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: RemitoDetalle inválido.");
            return modelAndView;            
        }
        
        String operacion = "remove";        
        String displayActionButton = "block";
        
        EgresoDepositoForm egresoDepositoForm = new EgresoDepositoForm();
        egresoDepositoForm.setPk("-1");
        egresoDepositoForm.setAction("add");
        egresoDepositoForm.setIdBobina("-1");
        egresoDepositoForm.setIdBulto("-1");
        egresoDepositoForm.setIdPallet("-1");
        egresoDepositoForm.setIdOrdenDeProduccionE("-1");
        
        RemitoDetalleService remitoDetalleService = new RemitoDetalleServiceImpl();   
        RemitoDetalleModel remitoDetalle = remitoDetalleService.getByPk(Integer.valueOf(remitoDetallepk));
        if(remitoDetalle == null) {

            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: RemitoDetalle inválido. No ha sido encontrado.");
            return modelAndView; 
            
        }else{ 
            remitoDetalleService.delete(remitoDetalle);
        }


        RemitoService remitoService = new RemitoServiceImpl();   
        RemitoModel remito = remitoService.getByPk(Integer.valueOf(remitoDetalle.getIdRemito()));
        if(remito == null) {
            
             modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error:  Remito inválido. No ha sido encontrado.");
            return modelAndView; 
        
        }

        if(!remito.getEstado().equalsIgnoreCase("Nuevo")) {
          
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error:  Remito con estado inválido. No es posible realizar este operación.");
            return modelAndView;   
        }
                
        
        
        modelAndView.setViewName("redirect:/remitoDetalle/"+remito.getId().toString());

        return modelAndView;
        
    }    
   
    @RequestMapping(value = "/remitoDetalle/recepcionar/{remitoDetallepk}", method = RequestMethod.GET)
    public String recepcionarRemitoDetalle(@PathVariable String remitoDetallepk, HttpServletRequest req, ModelMap model) throws Exception {
    
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(remitoDetallepk == null) {
            model.addAttribute("errorMessage", "Error: RemitoDetalle inválido");         
            return "/error";                
        }
        
        String operacion = "recepcion";        
        String displayActionButton = "block";
        
        RemitoDetalleService remitoDetalleService = new RemitoDetalleServiceImpl();   
        RemitoDetalleModel remitoDetalle = remitoDetalleService.getByPk(Integer.valueOf(remitoDetallepk));
        if(remitoDetalle == null) {
            model.addAttribute("errorMessage", "Error: RemitoDetalle inválido. No ha sido encontrado.");         
            return "/error";    
        }

        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(user.getRol() != Utils.ROL_DEPOSITO) {
            model.addAttribute("errorMessage", "Error: usuario no permite esta operación.");         
            return "/error";                                                                
        }

        RemitoService remitoService = new RemitoServiceImpl();   
        RemitoModel remito = remitoService.getByPk(Integer.valueOf(remitoDetalle.getIdRemito()));
        if(remito == null) {
            model.addAttribute("errorMessage", "Error: Remito inválido. No ha sido encontrado.");         
            return "/error";    
        }

        if(!remito.getEstado().equalsIgnoreCase("Abierto")) {
            model.addAttribute("errorMessage", "Error: Remito con estado inválido. No es posible realizar este operación.");         
            return "/error";    
        }
                
        ClienteService clienteService = new ClienteServiceImpl();   
        ClienteDomicilioService clienteDomicilioService = new ClienteDomicilioServiceImpl();   
        DomicilioService domicilioService = new DomicilioServiceImpl();   
        LocalidadService localidadService = new LocalidadServiceImpl();   
        TipoService tipoService = new TipoServiceImpl();   
        
                
        RemitoDetalleForm remitoDetalleForm = new RemitoDetalleForm();
        remitoDetalleForm.setPk(remitoDetalle.getId().toString());
        remitoDetalleForm.setAction("recepcion");
        remitoDetalleForm.setIdRemito(remito.getId().toString());
        remitoDetalleForm.setCodigoRemito(remito.getId().toString());
        remitoDetalleForm.setFechaRemito(remito.getFechaRemito().toString().replace("00:00:00.0", ""));
        remitoDetalleForm.setTipoRemito(remito.getTipoRemito());
        remitoDetalleForm.setEstadoRemito(remito.getEstado());        
        if(remito.getReferenciaAdministrativa() != null) {
            remitoDetalleForm.setReferenciaAdministrativa(remito.getReferenciaAdministrativa());
        }
        ClienteModel cliente = null;
        if(remito.getIdCliente() != null) {
            cliente = clienteService.getByPk(remito.getIdCliente());
            if(cliente != null) {
                remitoDetalleForm.setCliente("(" + cliente.getId() + ") " + cliente.getRazonSocial());
            }
        }
        if(remito.getIdDomicilio() != null) {
            DomicilioModel domicilioModel = domicilioService.getByPk(remito.getIdDomicilio());
            if(domicilioModel != null) {
                String localidad = "";
                String provincia = "";
                LocalidadModel localidadModel = localidadService.getByPk(domicilioModel.getIdLocalidad());
                if(localidadModel != null) {
                    localidad = localidadModel.getNombre();

                    TipoModel tipo = tipoService.getByPk(localidadModel.getIdProvincia());
                    if(tipo != null) {
                        provincia = tipo.getValor();
                    }
                }
                remitoDetalleForm.setDomicilio(domicilioModel.getUbicacion() + ", " + localidad + ", " + provincia);
            }
        }        
        if(remito.getIdTransporte() != null) {
            TipoModel tipo = tipoService.getByPk(remito.getIdTransporte());
            if(tipo != null) {
                remitoDetalleForm.setTransporte(tipo.getValor());
            }
        }

        Calendar c = Calendar.getInstance();        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
        String fecha = formato.format(c.getTime());

        remitoDetalleForm.setFechaAlta(fecha);

        String rol = "";
        
        if(user.getRol() == Utils.ROL_DEPOSITO) {
            rol = "deposito";
        }
                
        remitoDetalleForm.setOperacion(operacion);
                
        OrdenDeProduccionBultoService ordenDeProduccionBultoService = new OrdenDeProduccionBultoServiceImpl();
        OrdenDeProduccionPalletService ordenDeProduccionPalletService = new OrdenDeProduccionPalletServiceImpl();
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();
        ArticuloService articuloService = new ArticuloServiceImpl();
        
//        if(remitoDetalle.getIdBulto() != null) {
//            OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(remitoDetalle.getIdBulto());
//            if(bulto != null) {
//                OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(bulto.getIdOrdenDeProduccion());
//                if(ordenDeProduccion != null) {
//                    
//                    remitoDetalleForm.setIdOrdenDeProduccion(ordenDeProduccion.getId().toString());
//                    ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());                    
//                    remitoDetalleForm.setViewLote("L"+ordenDeProduccion.getId());
//                    if(articulo != null) {
//                        remitoDetalleForm.setViewArticulo("("+articulo.getId()+") "+articulo.getDenominacion());
//                    }
//                    remitoDetalleForm.setViewCodigo(bulto.getCodigo());
//                    remitoDetalleForm.setViewCodigoHdn(bulto.getCodigo());
//                    remitoDetalleForm.setViewTipo("Bulto");
//                    remitoDetalleForm.setViewTipoHdn("Bulto");
//                    remitoDetalleForm.setViewCantidad(remitoDetalle.getCantidad().toString());
//                }
//            }
//        }
//        if(remitoDetalle.getIdPallet() != null) {
//            OrdenDeProduccionPalletModel pallet = ordenDeProduccionPalletService.getByPk(remitoDetalle.getIdPallet());
//            if(pallet != null) {
//                OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(pallet.getIdOrdenDeProduccion());
//                if(ordenDeProduccion != null) {
//                    remitoDetalleForm.setIdOrdenDeProduccion(ordenDeProduccion.getId().toString());
//                    ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());                    
//                    remitoDetalleForm.setViewLote("L"+ordenDeProduccion.getId());
//                    if(articulo != null) {
//                        remitoDetalleForm.setViewArticulo("("+articulo.getId()+") "+articulo.getDenominacion());
//                    }
//                    remitoDetalleForm.setViewCodigo(pallet.getCodigo());
//                    remitoDetalleForm.setViewCodigoHdn(pallet.getCodigo());
//                    remitoDetalleForm.setViewTipo("Pallet");
//                    remitoDetalleForm.setViewTipoHdn("Pallet");
//                    remitoDetalleForm.setViewCantidad(remitoDetalle.getCantidad().toString());                    
//                }
//            }
//        }
                
        model.addAttribute("remitoDetalleForm", remitoDetalleForm);  
        model.addAttribute("titleRemitoDetalle", "Recepcionar Remito Detalle");  
        model.addAttribute("buttonLabel", "Recepcionar");
        
        Map<String,String> articulos = new LinkedHashMap<String,String>();
        List<ArticuloModel> articulosModel = articuloService.getAll();

        List<OrdenDeProduccionModel> ordenDeProducciones = ordenDeProduccionService.getAllWithStock();
        Set<String> articulosSet = new HashSet<String>();
        
        for(OrdenDeProduccionModel ordenDeProduccionCompleted :ordenDeProducciones) {
            articulosSet.add(ordenDeProduccionCompleted.getIdArticulo().toString());
        }
        
        if(articulosModel != null && !articulosModel.isEmpty()){
            for(ArticuloModel articuloModel :articulosModel) {
                if(articulosSet.contains(articuloModel.getId().toString())) {
                    articulos.put(articuloModel.getId().toString(), articuloModel.getDenominacion());
                }
            }
        }
        
        OrdenDeProduccionBobinaService bobinaService = new OrdenDeProduccionBobinaServiceImpl();   
        OrdenDeProduccionBultoService bultoService = new OrdenDeProduccionBultoServiceImpl();   
        OrdenDeProduccionPalletService palletService = new OrdenDeProduccionPalletServiceImpl();                   
        List<RemitoDetalleModel> remitoDetalles = remitoDetalleService.getAllByRemito(remito.getId());
        
        List<RemitoDetalleDto> remitoDetallesDtos = new ArrayList<RemitoDetalleDto>();
        for(RemitoDetalleModel remitoDetalleModel: remitoDetalles) {
            RemitoDetalleDto remitoDetalleDto = new RemitoDetalleDto();
            remitoDetalleDto.setPk(remitoDetalleModel.getId().toString());
            remitoDetalleDto.setFechaAlta(remitoDetalleModel.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            remitoDetalleDto.setCantidad(remitoDetalleModel.getCantidad().toString());
            remitoDetalleDto.setTieneBultoOPallet("false");
            
            if(remitoDetalle.getIngresoDeposito()) {
                remitoDetalleDto.setIngresoDeposito("Si");
            } else {
                remitoDetalleDto.setIngresoDeposito("No");
            }
            
//            if(remitoDetalleModel.getIdBobina() != null) {
//                
//                OrdenDeProduccionBobinaModel bobina = bobinaService.getByPk(remitoDetalleModel.getIdBobina());
//                if(bobina != null) {
//                    remitoDetalleDto.setDeposito("Sin información");
//                    if(bobina.getIdDeposito() != null) {
//                        TipoModel deposito = tipoService.getByPk(bobina.getIdDeposito());
//                        if(deposito != null) {
//                            remitoDetalleDto.setDeposito(deposito.getValor());        
//                        }
//                    }                    
//                    
//                    OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(bobina.getIdOrdenDeProduccion());
//                    if(ordenDeProduccion != null) {
//                        remitoDetalleDto.setLote("L"+ordenDeProduccion.getId());                        
//                        
//                        ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
//                        if(articulo != null) {
//                            remitoDetalleDto.setArticulo("(" + articulo.getId() + ") " + articulo.getDenominacion());
//                        }
//                    }
//                    remitoDetalleDto.setCodigo(bobina.getCodigo());
//                    remitoDetalleDto.setTieneBultoOPallet("true");
//                }
//            }
//            if(remitoDetalleModel.getIdBulto() != null) {
//                
//                OrdenDeProduccionBultoModel bulto = bultoService.getByPk(remitoDetalleModel.getIdBulto());
//                if(bulto != null) {
//                    remitoDetalleDto.setDeposito("Sin información");
//                    if(bulto.getIdDeposito() != null) {
//                        TipoModel deposito = tipoService.getByPk(bulto.getIdDeposito());
//                        if(deposito != null) {
//                            remitoDetalleDto.setDeposito(deposito.getValor());        
//                        }
//                    }                    
//                    
//                    OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(bulto.getIdOrdenDeProduccion());
//                    if(ordenDeProduccion != null) {
//                        remitoDetalleDto.setLote("L"+ordenDeProduccion.getId());
//                        
//                        ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
//                        if(articulo != null) {
//                            remitoDetalleDto.setArticulo("(" + articulo.getId() + ") " + articulo.getDenominacion());
//                        }                        
//                    }
//                    remitoDetalleDto.setCodigo(bulto.getCodigo());
//                    remitoDetalleDto.setTieneBultoOPallet("true");
//                }
//            }
//            if(remitoDetalleModel.getIdPallet() != null) {
//                
//                OrdenDeProduccionPalletModel pallet = palletService.getByPk(remitoDetalleModel.getIdPallet());
//                if(pallet != null) {
//                    remitoDetalleDto.setDeposito("Sin información");
//                    if(pallet.getIdDeposito() != null) {
//                        TipoModel deposito = tipoService.getByPk(pallet.getIdDeposito());
//                        if(deposito != null) {
//                            remitoDetalleDto.setDeposito(deposito.getValor());        
//                        }
//                    }                    
//                    
//                    OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(pallet.getIdOrdenDeProduccion());
//                    if(ordenDeProduccion != null) {
//                        remitoDetalleDto.setLote("L"+ordenDeProduccion.getId());
//                        
//                        ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
//                        if(articulo != null) {
//                            remitoDetalleDto.setArticulo("(" + articulo.getId() + ") " + articulo.getDenominacion());
//                        }                        
//                    }
//                    remitoDetalleDto.setCodigo(pallet.getCodigo());
//                    remitoDetalleDto.setTieneBultoOPallet("true");
//                }
//            }
//            if(remitoDetalle.getIdBobina() == null && remitoDetalle.getIdBulto() == null && remitoDetalle.getIdPallet() == null) {
//                remitoDetalleDto.setDeposito("Sin información");   
//                if(remitoDetalle.getIdOrdenDeProduccion() != null) {
//                    OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(remitoDetalle.getIdOrdenDeProduccion());
//                    if(ordenDeProduccion != null) {
//                        remitoDetalleDto.setLote("L"+ordenDeProduccion.getId());
//                    }                
//                }
//                if(remitoDetalle.getIdArticulo() != null) {
//                    ArticuloModel articulo = articuloService.getByPk(remitoDetalle.getIdArticulo());
//                    if(articulo != null) {
//                        remitoDetalleDto.setArticulo("(" + articulo.getId() + ") " + articulo.getDenominacion());
//                    }                        
//                }                
//            }
            
            remitoDetallesDtos.add(remitoDetalleDto);
            
            
        }
        
        model.addAttribute("remitoPk", remito.getId().toString());
        model.addAttribute("estadoRemito", remito.getEstado().toLowerCase());
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "recepcion");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("articulosList", articulos);        
        model.addAttribute("remitoDetalles", remitoDetallesDtos);        
                
        return "/remito/remitodetalle";
        
    }   
    
    /*
    @RequestMapping(value = "/remitoDetalle/view/{remitoDetallepk}", method = RequestMethod.GET)
    public String viewRemitoDetalle(@PathVariable String remitoDetallepk, HttpServletRequest req, ModelMap model) throws Exception {
                
    
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(remitoDetallepk == null) {
            model.addAttribute("errorMessage", "Error: RemitoDetalle inválido");         
            return "/error";                
        }
        
        String operacion = "view";        
        String displayActionButton = "none";
        
        RemitoDetalleService remitoDetalleService = new RemitoDetalleServiceImpl();   
        RemitoDetalleModel remitoDetalle = remitoDetalleService.getByPk(Integer.valueOf(remitoDetallepk));
        if(remitoDetalle == null) {
            model.addAttribute("errorMessage", "Error: RemitoDetalle inválido. No ha sido encontrado.");         
            return "/error";    
        }

        if(remitoDetalle.getIdCliente() == null) {
            model.addAttribute("errorMessage", "Error: RemitoDetalle inválido. No posee cliente.");         
            return "/error";    
        }
        
        if(!remitoDetalle.getEstado().equalsIgnoreCase("Nuevo")) {
            model.addAttribute("errorMessage", "Error: estado de orden incorrecto.");         
            return "/error";                            
        }
        
        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(user.getRol() != Utils.ROL_OFICINA && user.getRol() != Utils.ROL_DEPOSITO) {
            model.addAttribute("errorMessage", "Error: usuario no permite esta operación.");         
            return "/error";                                                                
        }
        
        RemitoDetalleForm remitoDetalleForm = new RemitoDetalleForm();
        remitoDetalleForm.setPk(remitoDetalle.getId().toString());
        if(remitoDetalle.getFechaAlta() != null) {
            remitoDetalleForm.setFechaAlta(remitoDetalle.getFechaAlta().toString().replace(".0", ""));
        }
        if(remitoDetalle.getFechaRemitoDetalle() != null) {
            remitoDetalleForm.setFechaRemitoDetalle(remitoDetalle.getFechaRemitoDetalle().toString().replace(" 00:00:00.0", ""));
        }        
        if(remitoDetalle.getTipoRemitoDetalle() != null) {
            remitoDetalleForm.setTipoRemitoDetalle(remitoDetalle.getTipoRemitoDetalle());
        }                
        if(remitoDetalle.getIdCliente() != null) {
            remitoDetalleForm.setIdCliente(remitoDetalle.getIdCliente().toString());
        }        
        if(remitoDetalle.getIdClienteDomicilio() != null) {
            remitoDetalleForm.setIdClienteDomicilio(remitoDetalle.getIdClienteDomicilio().toString());
            remitoDetalleForm.setExistingDomicilio(remitoDetalle.getIdClienteDomicilio().toString());
        }        
        if(remitoDetalle.getIdTransporte() != null) {
            remitoDetalleForm.setIdTransporte(remitoDetalle.getIdTransporte().toString());
        }                
        if(remitoDetalle.getReferenciaAdministrativa() != null && !remitoDetalle.getReferenciaAdministrativa().isEmpty()) {
            remitoDetalleForm.setReferenciaAdministrativa(remitoDetalle.getReferenciaAdministrativa());
        }                
        if(remitoDetalle.getEstado() != null && !remitoDetalle.getEstado().isEmpty()) {
            remitoDetalleForm.setEstado(remitoDetalle.getEstado());
        }        
        
        remitoDetalleForm.setOperacion(operacion);
        
        ClienteService clienteService = new ClienteServiceImpl();   
        ClienteModel cliente = clienteService.getByPk(remitoDetalle.getIdCliente());
        
        if(cliente == null) {
            model.addAttribute("errorMessage", "Error: No se encuentra el cliente.");         
            return "/error";                                                                
        }

        
        remitoDetalleForm.setAction("view");
        model.addAttribute("remitoDetalleForm", remitoDetalleForm);  
        model.addAttribute("titleRemitoDetalle", "Ver RemitoDetalle");
        model.addAttribute("buttonLabel", "Ver");
        model.addAttribute("remitoDetalleName", "RemitoDetalle: " + remitoDetalle.getId() + " - Cliente :" + cliente.getRazonSocial());        
        
        List<RemitoDetalleModel> remitoDetalles = remitoDetalleService.getAll();

        Map<String,String> clientes = new LinkedHashMap<String,String>();
        List<ClienteModel> clientesModel = clienteService.getAll();

        if(clientesModel != null && !clientesModel.isEmpty()){
            for(ClienteModel clienteModel :clientesModel) {
                clientes.put(clienteModel.getId().toString(), clienteModel.getRazonSocial());
            }
        }

        TipoService tipoService = new TipoServiceImpl();   
        Map<String,String> transportes = new LinkedHashMap<String,String>();
        List<TipoModel> transportesModel = tipoService.getByType("transporte");

        if(transportesModel != null && !transportesModel.isEmpty()){
            for(TipoModel tipoModel :transportesModel) {
                transportes.put(tipoModel.getId().toString(), tipoModel.getValor());
            }
        }

        List<RemitoDetalleDto> remitoDetallesDtos = new ArrayList<RemitoDetalleDto>();
        for(RemitoDetalleModel remitoDetalleModel: remitoDetalles) {
            RemitoDetalleDto remitoDetalleDto = new RemitoDetalleDto();
            remitoDetalleDto.setPk(remitoDetalleModel.getId().toString());
            remitoDetalleDto.setFechaAlta(remitoDetalleModel.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            if(remitoDetalleModel.getFechaRemitoDetalle() != null) {
                remitoDetalleDto.setFechaRemitoDetalle(remitoDetalleModel.getFechaRemitoDetalle().toString().replace(" 00:00:00.0", ""));
            }
            if(remitoDetalleModel.getReferenciaAdministrativa() != null) {
                remitoDetalleDto.setReferenciaAdministrativa(remitoDetalleModel.getReferenciaAdministrativa());
            }            
            remitoDetalleDto.setEstado(remitoDetalleModel.getEstado());
            remitoDetalleDto.setCliente(clientes.get(remitoDetalleModel.getIdCliente().toString()));
            
            remitoDetallesDtos.add(remitoDetalleDto);
            
            
        }

        String rol = "";
        if(user.getRol() == Utils.ROL_OFICINA) {
            rol = "oficina";
        }
        
        model.addAttribute("displayUser", "none");
        model.addAttribute("rol", rol);
        model.addAttribute("action", "view");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("clienteList", clientes);        
        model.addAttribute("transporteList", transportes);        
        model.addAttribute("remitoDetalles", remitoDetallesDtos);
                                                        
        return "/remitoDetalle/remitoDetalle"; 
            
    }    

    @ResponseBody
    @RequestMapping(value = "/remitoDetalle/getDomcilio/{idCliente}", method = RequestMethod.GET)
    public List<ClienteDomicilioBean> getDomicilio(@PathVariable String idCliente, HttpServletRequest req, ModelMap model) throws Exception {
        
        List<ClienteDomicilioBean> result = new ArrayList<ClienteDomicilioBean>();
        
        List<ClienteDomicilioBean> resultLegal = new ArrayList<ClienteDomicilioBean>();
        List<ClienteDomicilioBean> resultComun = new ArrayList<ClienteDomicilioBean>();
        
        if(idCliente != null && !idCliente.isEmpty()) {
            DomicilioService domicilioService = new DomicilioServiceImpl();
            TipoService tipoService = new TipoServiceImpl();
            LocalidadService localidadService = new LocalidadServiceImpl();
            ClienteDomicilioService clienteDomicilioService = new ClienteDomicilioServiceImpl();
            List<ClienteDomicilioModel> domiciliosCliente = clienteDomicilioService.getByPkCliente(Integer.valueOf(idCliente));
            if(!domiciliosCliente.isEmpty()) {
                for(ClienteDomicilioModel domicilioCliente: domiciliosCliente) {
                    
                    DomicilioModel domicilio = domicilioService.getByPk(domicilioCliente.getIdDomicilio());
                    if(domicilio != null) {
                        
                        LocalidadModel localidad = localidadService.getByPk(domicilio.getIdLocalidad());
                        TipoModel tipoDomicilio = tipoService.getByPk(domicilio.getIdTipo());                        
                       
                        ClienteDomicilioBean bean = new ClienteDomicilioBean();
                        bean.setId(domicilio.getId().toString());
                        if(localidad != null) {
                            bean.setValor(domicilio.getUbicacion() + " (" + localidad.getNombre() + ")");
                        } else {
                            bean.setValor(domicilio.getUbicacion());
                        }

                        if(tipoDomicilio.getValor().equalsIgnoreCase("Legal")) {
                            resultLegal.add(bean);    
                        } else {
                            resultComun.add(bean);    
                        } 
                    }
                }
                result.addAll(resultComun);
                result.addAll(0, resultLegal);
            }
        }
        
        return result;
    }
    
    /*
    @RequestMapping(value = "/remitoDetalle/setStatusOpenOrdenCompra/{remitoDetallePk}", method = RequestMethod.GET)
    public String setStatusOpenOrdenCompra(@PathVariable String remitoDetallePk, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(remitoDetallePk == null || remitoDetallePk.isEmpty()) {
            model.addAttribute("errorMessage", "Error: RemitoDetalle inválido");         
            return "/error";                
        }
        
        if(user.getRol() != Utils.ROL_OFICINA) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
                
        RemitoDetalleService remitoDetalleService = new RemitoDetalleServiceImpl();        
        RemitoDetalleModel remitoDetalle = remitoDetalleService.getByPk(Integer.valueOf(remitoDetallePk));

        if(remitoDetalle == null) {            
            model.addAttribute("errorMessage", "Error: orden de compra no encontrada");
            return "/error";
        }
        
        if(!remitoDetalle.getEstado().equalsIgnoreCase("Nuevo")) {            
            model.addAttribute("errorMessage", "Error: no es posible cambiar estado abierto a orden de compra con estado "+remitoDetalle.getEstado());
            return "/error";
        }            
        
        if(user.getRol() != Utils.ROL_OFICINA) {            
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");
            return "/error";              
        }
        
        RemitoDetalleItemService remitoDetalleItemService = new RemitoDetalleItemServiceImpl();        
        List<RemitoDetalleItemModel> items = remitoDetalleItemService.getAllByRemitoDetalle(remitoDetalle.getId());
        
        if(items == null || items.isEmpty()) {            
            model.addAttribute("errorMessage", "Error: no es posible pasar una orden a abierta cuando no tiene items");
            return "/error";              
        }
        
        remitoDetalle.setEstado("Abierto");
        remitoDetalleService.save(remitoDetalle);
        
        return "redirect:/remitoDetalle";                         
        
    }       
    
    @RequestMapping(value = "/remitoDetalle/setStatusCloseOrdenCompra/{remitoDetallePk}", method = RequestMethod.GET)
    public String setStatusCoseOrdenCompra(@PathVariable String remitoDetallePk, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(remitoDetallePk == null || remitoDetallePk.isEmpty()) {
            model.addAttribute("errorMessage", "Error: RemitoDetalle inválido");         
            return "/error";                
        }
        
        if(user.getRol() != Utils.ROL_OFICINA) {
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");         
            return "/error";                
        }
                
        RemitoDetalleService remitoDetalleService = new RemitoDetalleServiceImpl();        
        RemitoDetalleModel remitoDetalle = remitoDetalleService.getByPk(Integer.valueOf(remitoDetallePk));

        if(remitoDetalle == null) {            
            model.addAttribute("errorMessage", "Error: orden de compra no encontrada");
            return "/error";
        }
        
        if(!remitoDetalle.getEstado().equalsIgnoreCase("Completado")) {            
            model.addAttribute("errorMessage", "Error: no es posible cambiar estado abierto a orden de compra con estado "+remitoDetalle.getEstado());
            return "/error";
        }            
        
        if(user.getRol() != Utils.ROL_OFICINA) {            
            model.addAttribute("errorMessage", "Error: usuario no tiene rol para este funcionalidad");
            return "/error";              
        }
        
        RemitoDetalleItemService remitoDetalleItemService = new RemitoDetalleItemServiceImpl();        
        List<RemitoDetalleItemModel> items = remitoDetalleItemService.getAllByRemitoDetalle(remitoDetalle.getId());
        
        if(items == null || items.isEmpty()) {            
            model.addAttribute("errorMessage", "Error: no es posible pasar una orden a abierta cuando no tiene items");
            return "/error";              
        }
        
        Date today = new Date();
        
        remitoDetalle.setFechaCierreOrden(today);
        remitoDetalle.setIdUsuarioCierre(user.getId());
        remitoDetalle.setEstado("Cerrado");
        remitoDetalleService.save(remitoDetalle);
        
        return "redirect:/remitoDetalle";                         
        
    }    
    
    @RequestMapping(value = "/remitoDetalle/completarOrden/{remitoDetallepk}", method = RequestMethod.GET)
    public String completarOrdenObservacionesRemitoDetalle(@PathVariable String remitoDetallepk, HttpServletRequest req, ModelMap model) throws Exception {
                
        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }

        if(remitoDetallepk == null) {
            model.addAttribute("errorMessage", "Error: RemitoDetalle inválido");         
            return "/error";                
        }
        
        String operacion = "completar";        
        String displayActionButton = "block";
        
        RemitoDetalleService remitoDetalleService = new RemitoDetalleServiceImpl();   
        RemitoDetalleModel remitoDetalle = remitoDetalleService.getByPk(Integer.valueOf(remitoDetallepk));
        if(remitoDetalle == null) {
            model.addAttribute("errorMessage", "Error: RemitoDetalle inválido. No ha sido encontrado.");         
            return "/error";    
        }
                 
        if(!remitoDetalle.getEstado().equalsIgnoreCase("Abierto")) {
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
        
        RemitoDetalleForm remitoDetalleForm = new RemitoDetalleForm();
        remitoDetalleForm.setPk(remitoDetalle.getId().toString());
        if(remitoDetalle.getFechaAlta() != null) {
            remitoDetalleForm.setFechaAlta(remitoDetalle.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        }
        if(remitoDetalle.getIdProveedor() != null) {
            remitoDetalleForm.setIdProveedor(remitoDetalle.getIdProveedor().toString());
        }        
        if(remitoDetalle.getObservaciones() != null && !remitoDetalle.getObservaciones().isEmpty()) {
            remitoDetalleForm.setObservaciones(remitoDetalle.getObservaciones());
        }        
        if(remitoDetalle.getReferenciaAdministrativa() != null && !remitoDetalle.getReferenciaAdministrativa().isEmpty()) {
            remitoDetalleForm.setReferenciaAdministrativa(remitoDetalle.getReferenciaAdministrativa());
        }                        
        if(remitoDetalle.getEstado() != null && !remitoDetalle.getEstado().isEmpty()) {
            remitoDetalleForm.setEstado(remitoDetalle.getEstado());
        }        
        
        remitoDetalleForm.setOperacion(operacion);
        remitoDetalleForm.setEditObservaciones("true");
                
        remitoDetalleForm.setAction("edit");
        model.addAttribute("remitoDetalleForm", remitoDetalleForm);  
        model.addAttribute("titleRemitoDetalle", "Completar Orden de Compra");
        model.addAttribute("buttonLabel", "Guardar");
        model.addAttribute("remitoDetalleName", remitoDetalle.getId() + " - " + remitoDetalle.getIdProveedor());        
        
        List<RemitoDetalleModel> remitoDetalles = remitoDetalleService.getAll();

        ProveedorService proveedorService = new ProveedorServiceImpl();   
        Map<String,String> proveedores = new LinkedHashMap<String,String>();
        List<ProveedorModel> proveedoresModel = proveedorService.getAll();

        if(proveedoresModel != null && !proveedoresModel.isEmpty()){
            for(ProveedorModel proveedorModel :proveedoresModel) {
                proveedores.put(proveedorModel.getId().toString(), proveedorModel.getRazonSocial());
            }
        }
                                
        List<RemitoDetalleDto> remitoDetallesDtos = new ArrayList<RemitoDetalleDto>();
        for(RemitoDetalleModel remitoDetalleModel: remitoDetalles) {
            RemitoDetalleDto remitoDetalleDto = new RemitoDetalleDto();
            remitoDetalleDto.setPk(remitoDetalleModel.getId().toString());
            remitoDetalleDto.setFechaAlta(remitoDetalleModel.getFechaAlta().toString().replace(" 00:00:00.0", ""));
            if(remitoDetalle.getFechaEntrega() != null) {
                remitoDetalleDto.setFechaEntrega(remitoDetalle.getFechaEntrega().toString().replace(" 00:00:00.0", ""));
            }
            if(remitoDetalle.getReferenciaAdministrativa() != null) {
                remitoDetalleDto.setReferenciaAdministrativa(remitoDetalle.getReferenciaAdministrativa());
            }                        
            remitoDetalleDto.setEstado(remitoDetalleModel.getEstado());
            remitoDetalleDto.setProveedor(proveedores.get(remitoDetalleModel.getIdProveedor().toString()));
            
            remitoDetallesDtos.add(remitoDetalleDto);
        }
                               
        model.addAttribute("displayUser", "none");
                
        model.addAttribute("action", "editObservaciones");
        model.addAttribute("displayActionButton", displayActionButton);
        model.addAttribute("operacion", operacion);        
        model.addAttribute("proveedorList", proveedores);        
        model.addAttribute("remitoDetalles", remitoDetallesDtos);        
                                                        
        return "/ordendecompra/ordendecompra";        
    }    
    */
    
    @RequestMapping(value = "/remitoDetalle/applyRecepcion", method = RequestMethod.POST)
    public ModelAndView applyRecepcion(@ModelAttribute("remitoDetalleForm")RemitoDetalleForm remitoDetalleForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {

        UserService userService = new UserServiceImpl();   
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);

        ModelAndView modelAndView = new ModelAndView();
        
        if (result.hasErrors()) {            
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error 01");
            return modelAndView;
        }
        
        if(!Utils.isAutenticated(req)) {            
            modelAndView.setViewName("/login/login");    
            model.addAttribute("userForm", new UserForm());         
            return modelAndView;
        }
        
        if(remitoDetalleForm.getAction() == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error 02");
            return modelAndView;            
        }
        
        String operacion = remitoDetalleForm.getOperacion();
        
        if(operacion == null || !operacion.equalsIgnoreCase("recepcion")) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: operación inválida.");
            return modelAndView;                        
        }        
        if(remitoDetalleForm.getPk().equalsIgnoreCase("-1")) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error 03");
            return modelAndView;                        
        }
        if(remitoDetalleForm.getIdRemito() == null || remitoDetalleForm.getIdRemito().isEmpty()) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: remito id incorrecto.");
            return modelAndView;                        
        }
        
        if(user.getRol() != Utils.ROL_DEPOSITO) {        
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: no puede realizar esta operación.");
            return modelAndView;                                    
        }
        
        if(remitoDetalleForm.getViewTipoHdn() == null || remitoDetalleForm.getViewTipoHdn().isEmpty()){
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: tipo no existe tipo.");
            return modelAndView;                                                
        }
        if(remitoDetalleForm.getViewCodigoHdn() == null || remitoDetalleForm.getViewCodigoHdn().isEmpty()){
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: codigo no existe tipo.");
            return modelAndView;                                                
        }
               
        RemitoService remitoService = new RemitoServiceImpl();        
        RemitoDetalleService remitoDetalleService = new RemitoDetalleServiceImpl();        
                        
        RemitoModel remito = remitoService.getByPk(Integer.valueOf(remitoDetalleForm.getIdRemito()));
        if(remito == null) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: remito incorrecto.");
            return modelAndView;                        
        }

        if(!remito.getEstado().equalsIgnoreCase("Abierto")) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Error: remito con estado incorrecto.");
            return modelAndView;                                    
        }
        
        String sessionId = req.getSession().getId();
        String id = remitoDetalleForm.getPk();
            
        RemitoDetalleModel remitoDetalleModel = null;
        if(!id.equalsIgnoreCase("-1")) {
            remitoDetalleModel = remitoDetalleService.getByPk(Integer.valueOf(id));
            if(remitoDetalleModel == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: id de remito detalle inválido.");
                return modelAndView;                
            } 
        }        
        
        if(remitoDetalleForm.getAction().equalsIgnoreCase("recepcion")) {
            if(remitoDetalleModel.getId() == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Error: id de remitoDetalle inválido.");
                return modelAndView;                                    
            }
            
            remitoDetalleModel.setIngresoDeposito(Boolean.TRUE);
            remitoDetalleService.save(remitoDetalleModel);
            
        }
                        
        modelAndView.setViewName("redirect:/remitoDetalle/"+remito.getId().toString());

        return modelAndView; 
    }    
 
}
