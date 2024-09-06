/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

 
import com.empresa.thyssenplastic.controller.beans.ItemBean;
import com.empresa.thyssenplastic.controller.beans.OrdenDeCompraBean;
import com.empresa.thyssenplastic.controller.beans.StockOrdenDeCompraBean;
import com.empresa.thyssenplastic.controller.form.EgresoDepositoForm;
import com.empresa.thyssenplastic.controller.form.EgresoScrapForm;
import com.empresa.thyssenplastic.controller.form.RemitoDetalleForm;
import com.empresa.thyssenplastic.controller.form.UserForm;
import com.empresa.thyssenplastic.dto.DepositoScrapDto;
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
import com.empresa.thyssenplastic.model.OrdenDeProduccionScrapModel;
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
import com.empresa.thyssenplastic.service.OrdenDeProduccionScrapService;
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
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionScrapServiceImpl;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author gusta
 */
@Controller
public class RemitoDetalleScrapController {
    

    @RequestMapping(value = "/remitoDetalleScrap/{idRemito}", method = RequestMethod.GET)
    public String getHomeRemitoDetalleScrap(@PathVariable String idRemito, @RequestParam(value = "page", defaultValue = "1") int pageNumber,
    @RequestParam(value = "size", defaultValue = "2") int pageSize, HttpServletRequest req, ModelMap model) {
      
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
        
        EgresoScrapForm egresoScrapForm = new EgresoScrapForm();
       
                        
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
        model.addAttribute("egresoScrapForm", egresoScrapForm); 
        
        
        
        
        ////////SCRAP
        OrdenDeProduccionScrapService ordenDeProduccionScrapService = new OrdenDeProduccionScrapServiceImpl();   
        List<DepositoScrapDto> listadoScrap = ordenDeProduccionScrapService.getResumenPorOrdenDeProduccion(pageNumber, pageSize);
        
      
        model.addAttribute("depositoList", listadoScrap);  
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("idRemito", idRemito);
        
        return "/remito/remitodetallescrap";
    }  
 
}
