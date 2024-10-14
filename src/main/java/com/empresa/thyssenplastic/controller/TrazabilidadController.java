/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.empresa.thyssenplastic.controller;

 
import com.empresa.thyssenplastic.controller.form.OrdenDeProduccionForm;
import com.empresa.thyssenplastic.controller.form.TrazabilidadForm;
import com.empresa.thyssenplastic.controller.form.UserForm;
import com.empresa.thyssenplastic.model.ArticuloFichaTecnicaModel;
import com.empresa.thyssenplastic.model.ArticuloModel;
import com.empresa.thyssenplastic.model.ClienteModel;
import com.empresa.thyssenplastic.model.ContactoModel;
import com.empresa.thyssenplastic.model.EgresoDepositoModel;
import com.empresa.thyssenplastic.model.HojaDeRutaDetalleModel;
import com.empresa.thyssenplastic.model.HojaDeRutaModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionBobinaModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionBultoModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionPalletBultoModel;
import com.empresa.thyssenplastic.model.OrdenDeProduccionPalletModel;
import com.empresa.thyssenplastic.model.ProveedorModel;
import com.empresa.thyssenplastic.model.RemitoDetalleModel;
import com.empresa.thyssenplastic.model.RemitoModel;
import com.empresa.thyssenplastic.model.TipoModel;
import com.empresa.thyssenplastic.model.UserModel;
import com.empresa.thyssenplastic.service.ArticuloFichaTecnicaService;
import com.empresa.thyssenplastic.service.ArticuloService;
import com.empresa.thyssenplastic.service.ClienteService;
import com.empresa.thyssenplastic.service.ContactoService;
import com.empresa.thyssenplastic.service.EgresoDepositoService;
import com.empresa.thyssenplastic.service.HojaDeRutaDetalleService;
import com.empresa.thyssenplastic.service.HojaDeRutaService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionBobinaService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionBultoService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionPalletBultoService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionPalletService;
import com.empresa.thyssenplastic.service.OrdenDeProduccionService;
import com.empresa.thyssenplastic.service.ProveedorService;
import com.empresa.thyssenplastic.service.RemitoDetalleService;
import com.empresa.thyssenplastic.service.RemitoService;
import com.empresa.thyssenplastic.service.TipoService;
import com.empresa.thyssenplastic.service.UserService;
import com.empresa.thyssenplastic.service.impl.ArticuloFichaTecnicaServiceImpl;
import com.empresa.thyssenplastic.service.impl.ArticuloServiceImpl;
import com.empresa.thyssenplastic.service.impl.ClienteServiceImpl;
import com.empresa.thyssenplastic.service.impl.ContactoServiceImpl;
import com.empresa.thyssenplastic.service.impl.EgresoDepositoServiceImpl;
import com.empresa.thyssenplastic.service.impl.HojaDeRutaDetalleServiceImpl;
import com.empresa.thyssenplastic.service.impl.HojaDeRutaServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionBobinaServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionBultoServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionPalletBultoServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionPalletServiceImpl;
import com.empresa.thyssenplastic.service.impl.OrdenDeProduccionServiceImpl;
import com.empresa.thyssenplastic.service.impl.ProveedorServiceImpl;
import com.empresa.thyssenplastic.service.impl.RemitoDetalleServiceImpl;
import com.empresa.thyssenplastic.service.impl.RemitoServiceImpl;
import com.empresa.thyssenplastic.service.impl.TipoServiceImpl;
import com.empresa.thyssenplastic.utils.Utils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.empresa.thyssenplastic.service.impl.UserServiceImpl;
import java.util.List;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author gusta
 */
@Controller
public class TrazabilidadController {
    

    @RequestMapping(value = "/trazabilidad", method = RequestMethod.GET)
    public String getHomeTrazabilidad(HttpServletRequest req, ModelMap model) {

        if(!Utils.isAutenticated(req)) {
            model.addAttribute("userForm", new UserForm());         
            return "/login/login";            
        }
        
        UserService userService = new UserServiceImpl(); 
        Integer userId = Integer.valueOf(Utils.getUserLoggedId(req));
        UserModel user = userService.getUserById(userId);
        
        TrazabilidadForm trazabilidadForm = new TrazabilidadForm();

        if(user.getRol() != Utils.ROL_OFICINA && user.getRol() != Utils.ROL_PLANTA) {
            model.addAttribute("errorMessage", "Error: El usuario no puede realidad esta operación.");         
            return "/error";                            
        }
        
        model.addAttribute("trazabilidadForm", trazabilidadForm);          
                              
        return "/ordendeproduccion/trazabilidad";
    }

    @RequestMapping(value = "/trazabilidad/search", method = RequestMethod.POST)
    public ModelAndView search(@ModelAttribute("trazabilidadForm")TrazabilidadForm trazabilidadForm, BindingResult result, HttpServletRequest req, ModelMap model) throws Exception {

        ModelAndView modelAndView = new ModelAndView();        
        modelAndView.setViewName("/ordendeproduccion/trazabilidad");
        
        String searchText = trazabilidadForm.getCodigoSearch();
        if(searchText == null || searchText.isEmpty()) {
            model.addAttribute("displayError","true");
            model.addAttribute("errorMessage","Código vacio.");
            
            return modelAndView;
        }
        
        OrdenDeProduccionService ordenDeProduccionService = new OrdenDeProduccionServiceImpl();
        OrdenDeProduccionBobinaService ordenDeProduccionBobinaService = new OrdenDeProduccionBobinaServiceImpl();
        OrdenDeProduccionBultoService ordenDeProduccionBultoService = new OrdenDeProduccionBultoServiceImpl();
        OrdenDeProduccionPalletService ordenDeProduccionPalletService = new OrdenDeProduccionPalletServiceImpl();
        OrdenDeProduccionPalletBultoService ordenDeProduccionPalletBultoService = new OrdenDeProduccionPalletBultoServiceImpl();
        TipoService tipoService = new TipoServiceImpl();
        
        ClienteService clienteService = new ClienteServiceImpl();
        ArticuloService articuloService = new ArticuloServiceImpl();
        ArticuloFichaTecnicaService articuloFichaTecnicaService = new ArticuloFichaTecnicaServiceImpl();
        UserService userService = new UserServiceImpl();
        
        String letra = searchText.substring(0, 1);
        String codigo = searchText.substring(1, searchText.length());
        
        System.out.println("*** letra:"+letra);
        System.out.println("*** codigo:"+codigo);
        
        //TODO:LIMPIAR DATOS DEL FORM
        trazabilidadForm.setIdOrdenDeProduccion("");
        trazabilidadForm.setFechaAltaOrdenDeProduccion("");
        trazabilidadForm.setFechaCierreOrdenDeProduccion("");
        trazabilidadForm.setFechaAltaImpresionOrdenDeProduccion("");
        trazabilidadForm.setFechaAbiertaOrdenDeProduccion("");
        trazabilidadForm.setFechaFabricacionOrdenDeProduccion("");
        trazabilidadForm.setFechaEmpaqueOrdenDeProduccion("");
        trazabilidadForm.setFechaPalletOrdenDeProduccion("");
        trazabilidadForm.setUsuarioAltaOrdenDeProduccion("");
        trazabilidadForm.setUsuarioCierreOrdenDeProduccion("");
        trazabilidadForm.setUsuarioAbiertaOrdenDeProduccion("");
        trazabilidadForm.setUsuarioFabricacionOrdenDeProduccion("");
        trazabilidadForm.setUsuarioEmpaqueOrdenDeProduccion("");
        trazabilidadForm.setUsuarioPalletOrdenDeProduccion("");
        trazabilidadForm.setEstadoOrdenDeProduccion("");
        trazabilidadForm.setCliente("");
        trazabilidadForm.setIdCliente("");
        trazabilidadForm.setArticulo("");
        trazabilidadForm.setIdArticulo("");
        trazabilidadForm.setVersion("");
        trazabilidadForm.setCantidadAProducir("");
        trazabilidadForm.setFechaPedido("");
        
        trazabilidadForm.setCodigoBobina("");
        trazabilidadForm.setPesoCono("");
        trazabilidadForm.setPesoNeto("");
        trazabilidadForm.setPesoTotal("");
        trazabilidadForm.setEstadoBobina("");
        trazabilidadForm.setUsuarioAltaBobina("");
        trazabilidadForm.setFechaAltaBobina("");
        
        trazabilidadForm.setCodigoBulto("");
        trazabilidadForm.setEstadoBulto("");
        trazabilidadForm.setPlegadora("");
        trazabilidadForm.setUsuarioAltaBulto("");
        trazabilidadForm.setFechaAltaBulto("");
        
        trazabilidadForm.setCodigoPallet("");
        trazabilidadForm.setEstadoPallet("");
        trazabilidadForm.setUsuarioAltaPallet("");
        trazabilidadForm.setFechaAltaPallet("");
        trazabilidadForm.setCantidadBultos("");
        trazabilidadForm.setListaCodigoBultos("");
        
        
        trazabilidadForm.setFechaBaja("");
        trazabilidadForm.setUsuarioBaja("");
        
        model.addAttribute("displayBobina","false");
        model.addAttribute("displayBulto","false");
        model.addAttribute("displayPallet","false");
        model.addAttribute("displayRemito","false");
        model.addAttribute("estaEnDeposito","false");
        model.addAttribute("infoBaja","false");
        model.addAttribute("infoDeposito","false");
        
         model.addAttribute("infoLeyendaBobina","false");
        model.addAttribute("infoLeyendaBulto","false");
        if(letra.equalsIgnoreCase("L")) {
            //ORDEN DE PRODUCCION
            OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(Integer.valueOf(codigo));
            if(ordenDeProduccion == null) {
                model.addAttribute("displayError","true");
                model.addAttribute("errorMessage","Orden de producción no encontrada.");
            
                return modelAndView;                
            }             
            ClienteModel cliente = clienteService.getByPk(ordenDeProduccion.getIdCliente());
            if(cliente == null) {
                model.addAttribute("displayError","true");
                model.addAttribute("errorMessage","Cliente no encontrado.");
            
                return modelAndView;                
            }
            
            ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
            if(articulo == null) {
                model.addAttribute("displayError","true");
                model.addAttribute("errorMessage","Artículo no encontrado.");
            
                return modelAndView;                
            }
            
            ArticuloFichaTecnicaModel articuloFichaTecnica = articuloFichaTecnicaService.getByPk(ordenDeProduccion.getIdFichaTecnica());
            if(articuloFichaTecnica == null) {
                model.addAttribute("displayError","true");
                model.addAttribute("errorMessage","Ficha Técnica no encontrada.");
            
                return modelAndView;                
            }
            
            setDatosOrdenDeProduccion(trazabilidadForm, ordenDeProduccion, cliente, articulo, articuloFichaTecnica, userService);
            
        } else if(letra.equalsIgnoreCase("B")) {
            //BOBINA
                        
            OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(Integer.valueOf(codigo));
            if(bobina == null) {
                model.addAttribute("displayError","true");
                model.addAttribute("errorMessage","Bobina no encontrada.");
            
                return modelAndView;                
            }             
            
            OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(bobina.getIdOrdenDeProduccion());
            if(ordenDeProduccion == null) {
                model.addAttribute("displayError","true");
                model.addAttribute("errorMessage","Orden de producción no encontrada.");
            
                return modelAndView;                
            }             
            ClienteModel cliente = clienteService.getByPk(ordenDeProduccion.getIdCliente());
            if(cliente == null) {
                model.addAttribute("displayError","true");
                model.addAttribute("errorMessage","Cliente no encontrado.");
            
                return modelAndView;                
            }
            
            ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
            if(articulo == null) {
                model.addAttribute("displayError","true");
                model.addAttribute("errorMessage","Artículo no encontrado.");
            
                return modelAndView;                
            }
            
            ArticuloFichaTecnicaModel articuloFichaTecnica = articuloFichaTecnicaService.getByPk(ordenDeProduccion.getIdFichaTecnica());
            if(articuloFichaTecnica == null) {
                model.addAttribute("displayError","true");
                model.addAttribute("errorMessage","Ficha Técnica no encontrada.");
            
                return modelAndView;                
            }
            
            model.addAttribute("displayBobina","true");
            model.addAttribute("infoDeposito","true");
            
            if ((bobina.getIdRemito() == null) && (bobina.getIdDeposito() != null)) {
                    
                    model.addAttribute("estaEnDeposito","true");
                }
            
            setDatosOrdenDeProduccion(trazabilidadForm, ordenDeProduccion, cliente, articulo, articuloFichaTecnica, userService);            
            setDatosBobina(trazabilidadForm, bobina, userService);            
            
            OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByOrdenDeProduccionBobina(bobina.getId());            
            if(bulto != null) {
                model.addAttribute("displayBulto","true");
                setDatosBulto(trazabilidadForm, bulto, userService, tipoService);                
                    
                List<OrdenDeProduccionPalletBultoModel> palletsBulto = ordenDeProduccionPalletBultoService.getByOrdenDeProduccionBulto(bulto.getId());
                if(palletsBulto != null && !palletsBulto.isEmpty()) {
                    OrdenDeProduccionPalletBultoModel palletBulto = palletsBulto.get(0);
                    OrdenDeProduccionPalletModel pallet = ordenDeProduccionPalletService.getByPk(palletBulto.getIdOrdenDeProduccionPallet());
                    
                    if(pallet != null) {
                        model.addAttribute("displayPallet","true");
                        setDatosPallet(trazabilidadForm, pallet, userService, ordenDeProduccionPalletBultoService, ordenDeProduccionBultoService);                                    
                    }
                }
               
               
//                RemitoDetalleModel remitoDetalle = remitoDetalleService.getByBulto(bulto.getId());
//                if(remitoDetalle != null) {
//                    RemitoService remitoService = new RemitoServiceImpl();
//                    RemitoModel remito = remitoService.getByPk(remitoDetalle.getIdRemito());
//                    if(remito != null) {
//                        model.addAttribute("displayRemito","true");
//                        setDatosRemito(trazabilidadForm, remito, userService);
//                        
//                        HojaDeRutaDetalleService hojaDeRutaDetalleService = new HojaDeRutaDetalleServiceImpl();
//                        HojaDeRutaDetalleModel hojaDeRutaDetalle = hojaDeRutaDetalleService.getByRemito(remito.getId());
//                        if(hojaDeRutaDetalle != null) {
//                            HojaDeRutaService hojaDeRutaService = new HojaDeRutaServiceImpl();
//                            HojaDeRutaModel hojaDeRuta = hojaDeRutaService.getByPk(hojaDeRutaDetalle.getIdHojaDeRuta());
//                            if(hojaDeRuta != null) {
//                                model.addAttribute("displayHojaDeRuta","true");
//                                setDatosHojaDeRuta(trazabilidadForm, hojaDeRuta, userService);
//                            }
//                        }
//                        
//                    }
//                }
                                
            }
            
            RemitoDetalleService remitoDetalleService = new RemitoDetalleServiceImpl();
                RemitoDetalleModel remitoDetalle = remitoDetalleService.getByPk(bobina.getIdRemito());

                if(remitoDetalle != null) {
                     RemitoService remitoService = new RemitoServiceImpl();
                     RemitoModel remito = remitoService.getByPk(remitoDetalle.getIdRemito());
                     
                     if(remito != null) {
                        model.addAttribute("displayRemito","true");
                        setDatosRemito(trazabilidadForm, remito, userService);
                        
                        EgresoDepositoService egresoDepositoService = new EgresoDepositoServiceImpl();  
                        EgresoDepositoModel egresoDepositoModel = egresoDepositoService.getByIdBobina(bobina.getId());
                        if(egresoDepositoModel != null) {
                            model.addAttribute("infoBaja","true");
                            setDatosBaja(trazabilidadForm, egresoDepositoModel, userService);
                        }else{ 
                            model.addAttribute("infoLeyendaBobina","true");
                        }
                        
                        
                         HojaDeRutaDetalleService hojaDeRutaDetalleService = new HojaDeRutaDetalleServiceImpl();
                        HojaDeRutaDetalleModel hojaDeRutaDetalle = hojaDeRutaDetalleService.getByRemito(remitoDetalle.getIdRemito());
                        if(hojaDeRutaDetalle != null) {
                            HojaDeRutaService hojaDeRutaService = new HojaDeRutaServiceImpl();
                            HojaDeRutaModel hojaDeRuta = hojaDeRutaService.getByPk(hojaDeRutaDetalle.getIdHojaDeRuta());
                            if(hojaDeRuta != null) {
                                model.addAttribute("displayHojaDeRuta","true");
                                setDatosHojaDeRuta(trazabilidadForm, hojaDeRuta, userService);
                            }
                        }
                     }
                
                }
            
        } else if(letra.equalsIgnoreCase("R")) {
            //BULTO
            OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByCode(searchText);
            if(bulto == null) {
                model.addAttribute("displayError","true");
                model.addAttribute("errorMessage","Bulto no encontrado.");
            
                return modelAndView;                
            }             
            
            if ((bulto.getIdRemito() == null) && (bulto.getIdDeposito() != null)) {
                    
                    model.addAttribute("estaEnDeposito","true");
                }
            
            model.addAttribute("displayBulto","true");
            model.addAttribute("infoDeposito","true");
            setDatosBulto(trazabilidadForm, bulto, userService, tipoService);
            
            OrdenDeProduccionBobinaModel bobina = ordenDeProduccionBobinaService.getByPk(bulto.getIdOrdenDeProduccionBobina());
            if(bobina != null) {
                model.addAttribute("displayBobina","true");
                setDatosBobina(trazabilidadForm, bobina, userService);
            }            

            OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(bulto.getIdOrdenDeProduccion());
            if(ordenDeProduccion != null) {
                model.addAttribute("displayOrdenDeProduccion","true");
                
                ClienteModel cliente = clienteService.getByPk(ordenDeProduccion.getIdCliente());
                if(cliente == null) {
                    model.addAttribute("displayError","true");
                    model.addAttribute("errorMessage","Cliente no encontrado.");

                    return modelAndView;                
                }

                ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
                if(articulo == null) {
                    model.addAttribute("displayError","true");
                    model.addAttribute("errorMessage","Artículo no encontrado.");

                    return modelAndView;                
                }

                ArticuloFichaTecnicaModel articuloFichaTecnica = articuloFichaTecnicaService.getByPk(ordenDeProduccion.getIdFichaTecnica());
                if(articuloFichaTecnica == null) {
                    model.addAttribute("displayError","true");
                    model.addAttribute("errorMessage","Ficha Técnica no encontrada.");

                    return modelAndView;                
                }
                
                setDatosOrdenDeProduccion(trazabilidadForm, ordenDeProduccion, cliente, articulo, articuloFichaTecnica, userService);
            }            
            
            List<OrdenDeProduccionPalletBultoModel> palletsBulto = ordenDeProduccionPalletBultoService.getByOrdenDeProduccionBulto(bulto.getId());
            if(palletsBulto != null && !palletsBulto.isEmpty()) {
                OrdenDeProduccionPalletBultoModel palletBulto = palletsBulto.get(0);
                OrdenDeProduccionPalletModel pallet = ordenDeProduccionPalletService.getByPk(palletBulto.getIdOrdenDeProduccionPallet());

                if(pallet != null) {
                    model.addAttribute("displayPallet","true");
                    setDatosPallet(trazabilidadForm, pallet, userService, ordenDeProduccionPalletBultoService, ordenDeProduccionBultoService);                                    
                }
            }
            
             RemitoDetalleService remitoDetalleService = new RemitoDetalleServiceImpl();
                RemitoDetalleModel remitoDetalle = remitoDetalleService.getByPk(bulto.getIdRemito());
                if(remitoDetalle != null) {
                     RemitoService remitoService = new RemitoServiceImpl();
                     RemitoModel remito = remitoService.getByPk(remitoDetalle.getIdRemito());
                     if(remito != null) {
                        model.addAttribute("displayRemito","true");
                        setDatosRemito(trazabilidadForm, remito, userService);
                        
                        EgresoDepositoService egresoDepositoService = new EgresoDepositoServiceImpl();  
                        EgresoDepositoModel egresoDepositoModel = egresoDepositoService.getByIdBulto(bulto.getId());
                        if(egresoDepositoModel != null) {
                            model.addAttribute("infoBaja","true");
                            setDatosBaja(trazabilidadForm, egresoDepositoModel, userService);
                        }else{ 
                            model.addAttribute("infoLeyendaBulto","true");
                        }

                    HojaDeRutaDetalleService hojaDeRutaDetalleService = new HojaDeRutaDetalleServiceImpl();
                    HojaDeRutaDetalleModel hojaDeRutaDetalle = hojaDeRutaDetalleService.getByRemito(remitoDetalle.getIdRemito());
                    if(hojaDeRutaDetalle != null) {
                        HojaDeRutaService hojaDeRutaService = new HojaDeRutaServiceImpl();
                        HojaDeRutaModel hojaDeRuta = hojaDeRutaService.getByPk(hojaDeRutaDetalle.getIdHojaDeRuta());
                        if(hojaDeRuta != null) {
                            model.addAttribute("displayHojaDeRuta","true");
                            setDatosHojaDeRuta(trazabilidadForm, hojaDeRuta, userService);
                        }
                    }
                        
                     }
                
                }
                        
//            RemitoDetalleService remitoDetalleService = new RemitoDetalleServiceImpl();
//            RemitoDetalleModel remitoDetalle = remitoDetalleService.getByBulto(bulto.getId());
//            if(remitoDetalle != null) {
//                RemitoService remitoService = new RemitoServiceImpl();
//                RemitoModel remito = remitoService.getByPk(remitoDetalle.getIdRemito());
//                if(remito != null) {
//                    model.addAttribute("displayRemito","true");
//                    setDatosRemito(trazabilidadForm, remito, userService);
//                    
//                    HojaDeRutaDetalleService hojaDeRutaDetalleService = new HojaDeRutaDetalleServiceImpl();
//                    HojaDeRutaDetalleModel hojaDeRutaDetalle = hojaDeRutaDetalleService.getByRemito(remito.getId());
//                    if(hojaDeRutaDetalle != null) {
//                        HojaDeRutaService hojaDeRutaService = new HojaDeRutaServiceImpl();
//                        HojaDeRutaModel hojaDeRuta = hojaDeRutaService.getByPk(hojaDeRutaDetalle.getIdHojaDeRuta());
//                        if(hojaDeRuta != null) {
//                            model.addAttribute("displayHojaDeRuta","true");
//                            setDatosHojaDeRuta(trazabilidadForm, hojaDeRuta, userService);
//                        }
//                    }
//                    
//                }
//            }
            
        } else if(letra.equalsIgnoreCase("P")) {
            //PALLET
            OrdenDeProduccionPalletModel pallet = ordenDeProduccionPalletService.getByCode(searchText);
            if(pallet == null) {
                model.addAttribute("displayError","true");
                model.addAttribute("errorMessage","Pallet no encontrado.");
            
                return modelAndView;                
            }             
            
            if ((pallet.getIdRemito() == null) && (pallet.getIdDeposito() != null)) {
                
                    model.addAttribute("estaEnDeposito","true");
                }
                        
            model.addAttribute("displayPallet","true");
            model.addAttribute("infoDeposito","true");
            setDatosPallet(trazabilidadForm, pallet, userService, ordenDeProduccionPalletBultoService, ordenDeProduccionBultoService);                                                

            OrdenDeProduccionModel ordenDeProduccion = ordenDeProduccionService.getByPk(pallet.getIdOrdenDeProduccion());
            if(ordenDeProduccion != null) {
                model.addAttribute("displayOrdenDeProduccion","true");
                
                ClienteModel cliente = clienteService.getByPk(ordenDeProduccion.getIdCliente());
                if(cliente == null) {
                    model.addAttribute("displayError","true");
                    model.addAttribute("errorMessage","Cliente no encontrado.");

                    return modelAndView;                
                }

                ArticuloModel articulo = articuloService.getByPk(ordenDeProduccion.getIdArticulo());
                if(articulo == null) {
                    model.addAttribute("displayError","true");
                    model.addAttribute("errorMessage","Artículo no encontrado.");

                    return modelAndView;                
                }

                ArticuloFichaTecnicaModel articuloFichaTecnica = articuloFichaTecnicaService.getByPk(ordenDeProduccion.getIdFichaTecnica());
                if(articuloFichaTecnica == null) {
                    model.addAttribute("displayError","true");
                    model.addAttribute("errorMessage","Ficha Técnica no encontrada.");

                    return modelAndView;                
                }
                
                setDatosOrdenDeProduccion(trazabilidadForm, ordenDeProduccion, cliente, articulo, articuloFichaTecnica, userService);
            }     
            //RemitoDetalleService remitoDetalleService = new RemitoDetalleServiceImpl();
             RemitoDetalleService remitoDetalleService = new RemitoDetalleServiceImpl();
                RemitoDetalleModel remitoDetalle = remitoDetalleService.getByPk(pallet.getIdRemito());
                if(remitoDetalle != null) {
                     RemitoService remitoService = new RemitoServiceImpl();
                     RemitoModel remito = remitoService.getByPk(remitoDetalle.getIdRemito());
                     if(remito != null) {
                        model.addAttribute("displayRemito","true");
                        setDatosRemito(trazabilidadForm, remito, userService);
                        
                        
                        EgresoDepositoService egresoDepositoService = new EgresoDepositoServiceImpl();  
                        EgresoDepositoModel egresoDepositoModel = egresoDepositoService.getByIdPallet(pallet.getId());
                        if(egresoDepositoModel != null) {
                            model.addAttribute("infoBaja","true");
                            setDatosBaja(trazabilidadForm, egresoDepositoModel, userService);
                        }
                        
                        HojaDeRutaDetalleService hojaDeRutaDetalleService = new HojaDeRutaDetalleServiceImpl();
                        HojaDeRutaDetalleModel hojaDeRutaDetalle = hojaDeRutaDetalleService.getByRemito(remitoDetalle.getIdRemito());
                        if(hojaDeRutaDetalle != null) {
                            HojaDeRutaService hojaDeRutaService = new HojaDeRutaServiceImpl();
                            HojaDeRutaModel hojaDeRuta = hojaDeRutaService.getByPk(hojaDeRutaDetalle.getIdHojaDeRuta());
                            if(hojaDeRuta != null) {
                                model.addAttribute("displayHojaDeRuta","true");
                                setDatosHojaDeRuta(trazabilidadForm, hojaDeRuta, userService);
                            }
                        }
                    }
                }
                
//            RemitoDetalleModel remitoDetalle = remitoDetalleService.getByPallet(pallet.getId());
//            if(remitoDetalle != null) {
//                RemitoService remitoService = new RemitoServiceImpl();
//                RemitoModel remito = remitoService.getByPk(remitoDetalle.getIdRemito());
//                if(remito != null) {
//                    model.addAttribute("displayRemito","true");
//                    setDatosRemito(trazabilidadForm, remito, userService);
//                    
//                    HojaDeRutaDetalleService hojaDeRutaDetalleService = new HojaDeRutaDetalleServiceImpl();
//                    HojaDeRutaDetalleModel hojaDeRutaDetalle = hojaDeRutaDetalleService.getByRemito(remito.getId());
//                    if(hojaDeRutaDetalle != null) {
//                        HojaDeRutaService hojaDeRutaService = new HojaDeRutaServiceImpl();
//                        HojaDeRutaModel hojaDeRuta = hojaDeRutaService.getByPk(hojaDeRutaDetalle.getIdHojaDeRuta());
//                        if(hojaDeRuta != null) {
//                            model.addAttribute("displayHojaDeRuta","true");
//                            setDatosHojaDeRuta(trazabilidadForm, hojaDeRuta, userService);
//                        }
//                    }
//                    
//                }
//            }            
            
        } else {
            model.addAttribute("displayError","true");
            model.addAttribute("errorMessage","Código no encontrado. Debe comenzar con L, B, R o P.");

            return modelAndView;                            
        }                        
        
        model.addAttribute("displayError","false");
        
        return modelAndView;
    }
    
    private void setDatosOrdenDeProduccion(TrazabilidadForm trazabilidadForm, OrdenDeProduccionModel ordenDeProduccion, ClienteModel cliente, ArticuloModel articulo, ArticuloFichaTecnicaModel articuloFichaTecnica, UserService userService ) {
        
        trazabilidadForm.setIdOrdenDeProduccion(ordenDeProduccion.getId().toString());
        trazabilidadForm.setFechaAltaOrdenDeProduccion(ordenDeProduccion.getFechaAlta().toString().replace(" 00:00:00.0", ""));
        if(ordenDeProduccion.getFechaCierre() != null) {
            trazabilidadForm.setFechaCierreOrdenDeProduccion(ordenDeProduccion.getFechaCierre().toString());
        } 
        if(ordenDeProduccion.getFechaAltaImpresion() != null) {
            trazabilidadForm.setFechaAltaImpresionOrdenDeProduccion(ordenDeProduccion.getFechaAltaImpresion().toString());
        } 
        if(ordenDeProduccion.getFechaAbierta() != null) {
            trazabilidadForm.setFechaAbiertaOrdenDeProduccion(ordenDeProduccion.getFechaAbierta().toString());
        } 
        if(ordenDeProduccion.getFechaFabricacion() != null) {
            trazabilidadForm.setFechaFabricacionOrdenDeProduccion(ordenDeProduccion.getFechaFabricacion().toString());
        } 
        if(ordenDeProduccion.getFechaEmpaque() != null) {
            trazabilidadForm.setFechaEmpaqueOrdenDeProduccion(ordenDeProduccion.getFechaEmpaque().toString());
        } 
        if(ordenDeProduccion.getFechaPallet() != null) {
            trazabilidadForm.setFechaPalletOrdenDeProduccion(ordenDeProduccion.getFechaPallet().toString());
        } 
        UserModel user = userService.getUserById(ordenDeProduccion.getIdUsuarioAlta());
        if(user != null) {
            trazabilidadForm.setUsuarioAltaOrdenDeProduccion(user.getUsername());
        }
        if(ordenDeProduccion.getIdUsuarioCierre() != null) {
            user = userService.getUserById(ordenDeProduccion.getIdUsuarioCierre());
            if(user != null) {
                trazabilidadForm.setUsuarioCierreOrdenDeProduccion(user.getUsername());
            }                
        }
        if(ordenDeProduccion.getIdUsuarioAbierta() != null) {
            user = userService.getUserById(ordenDeProduccion.getIdUsuarioAbierta());
            if(user != null) {
                trazabilidadForm.setUsuarioAbiertaOrdenDeProduccion(user.getUsername());
            }                
        }
        if(ordenDeProduccion.getIdUsuarioFabricacion() != null) {
            user = userService.getUserById(ordenDeProduccion.getIdUsuarioFabricacion());
            if(user != null) {
                trazabilidadForm.setUsuarioFabricacionOrdenDeProduccion(user.getUsername());
            }                
        }
        if(ordenDeProduccion.getIdUsuarioEmpaque() != null) {
            user = userService.getUserById(ordenDeProduccion.getIdUsuarioEmpaque());
            if(user != null) {
                trazabilidadForm.setUsuarioEmpaqueOrdenDeProduccion(user.getUsername());
            }                
        }
        if(ordenDeProduccion.getIdUsuarioPallet() != null) {
            user = userService.getUserById(ordenDeProduccion.getIdUsuarioPallet());
            if(user != null) {
                trazabilidadForm.setUsuarioPalletOrdenDeProduccion(user.getUsername());
            }                
        }
        if(ordenDeProduccion.getEstado() != null) {
            trazabilidadForm.setEstadoOrdenDeProduccion(ordenDeProduccion.getEstado());
        }
        trazabilidadForm.setCliente(cliente.getRazonSocial());
        trazabilidadForm.setIdCliente(cliente.getId().toString());
        trazabilidadForm.setArticulo(articulo.getDenominacion());
        trazabilidadForm.setIdArticulo(articulo.getId().toString());
        trazabilidadForm.setVersion(articuloFichaTecnica.getVersion().toString());
        if(ordenDeProduccion.getCantidadAProducir() != null) {
            trazabilidadForm.setCantidadAProducir(ordenDeProduccion.getCantidadAProducir().toString());
        }
        if(ordenDeProduccion.getFechaPedido() != null) {
            trazabilidadForm.setFechaPedido(ordenDeProduccion.getFechaPedido().toString().replace(" 00:00:00.0", ""));
        }        
    }
    
    private void setDatosBobina(TrazabilidadForm trazabilidadForm, OrdenDeProduccionBobinaModel bobina, UserService userService ) {
        
        trazabilidadForm.setCodigoBobina(bobina.getCodigo());
        if(bobina.getPesoCono() != null) {
            trazabilidadForm.setPesoCono(bobina.getPesoCono().toString());
        }
        if(bobina.getPesoNeto() != null) {
            trazabilidadForm.setPesoNeto(bobina.getPesoNeto().toString());
        }
        if(bobina.getPesoTotal() != null) {
            trazabilidadForm.setPesoTotal(bobina.getPesoTotal().toString());
        }
        if(bobina.getEstado() != null) {
            trazabilidadForm.setEstadoBobina(bobina.getEstado());
        }
        if(bobina.getIdUsuarioAlta() != null) {
            UserModel user = userService.getUserById(Integer.valueOf(bobina.getIdUsuarioAlta()));
            if(user != null) {
                trazabilidadForm.setUsuarioAltaBobina(user.getUsername());
            }        
        }
        if(bobina.getFechaAlta() != null) {
            trazabilidadForm.setFechaAltaBobina(bobina.getFechaAlta().toString());
        }        
        if(bobina.getObservaciones() != null) {
            trazabilidadForm.setObsevacionesBobina(bobina.getObservaciones());
        } 
    }    

    private void setDatosBulto(TrazabilidadForm trazabilidadForm, OrdenDeProduccionBultoModel bulto, UserService userService, TipoService tipoService) {
        trazabilidadForm.setCodigoBulto(bulto.getCodigo());
        if(bulto.getEstado() != null) {
            trazabilidadForm.setEstadoBulto(bulto.getEstado());
        }
        if(bulto.getIdPlegadora() != null) {
            TipoModel plegadora = tipoService.getByPk(bulto.getIdPlegadora());
            if(plegadora != null) {
                trazabilidadForm.setPlegadora(plegadora.getValor());
            }
        }        
        if(bulto.getIdUsuarioAlta() != null) {
            UserModel user = userService.getUserById(Integer.valueOf(bulto.getIdUsuarioAlta()));
            if(user != null) {
                trazabilidadForm.setUsuarioAltaBulto(user.getUsername());
            }        
        }
        if(bulto.getFechaAlta() != null) {
            trazabilidadForm.setFechaAltaBulto(bulto.getFechaAlta().toString());
        }        
        
        if(bulto.getObservaciones() != null) {
            trazabilidadForm.setObsevacionesBulto(bulto.getObservaciones());
        } 
        
    }
    
    private void setDatosPallet(TrazabilidadForm trazabilidadForm, OrdenDeProduccionPalletModel pallet, UserService userService, OrdenDeProduccionPalletBultoService ordenDeProduccionPalletBultoService, OrdenDeProduccionBultoService ordenDeProduccionBultoService) {
        trazabilidadForm.setCodigoPallet(pallet.getCodigo());
        if(pallet.getEstado() != null) {
            trazabilidadForm.setEstadoPallet(pallet.getEstado());
        }
        if(pallet.getIdUsuarioAlta() != null) {
            UserModel user = userService.getUserById(Integer.valueOf(pallet.getIdUsuarioAlta()));
            if(user != null) {
                trazabilidadForm.setUsuarioAltaPallet(user.getUsername());
            }        
        }
        if(pallet.getFechaAlta() != null) {
            trazabilidadForm.setFechaAltaPallet(pallet.getFechaAlta().toString());
        }        
        List<OrdenDeProduccionPalletBultoModel> items = ordenDeProduccionPalletBultoService.getAllByOrdenDeProduccionPallet(pallet.getId());
        trazabilidadForm.setCantidadBultos(String.valueOf(items.size()));
        
        String listaCodigos = "";
        for(OrdenDeProduccionPalletBultoModel item :items) {
            OrdenDeProduccionBultoModel bulto = ordenDeProduccionBultoService.getByPk(item.getIdOrdenDeProduccionBulto());
            if(bulto != null) {
                listaCodigos += bulto.getCodigo() + " ";
            }
        }
        trazabilidadForm.setListaCodigoBultos(listaCodigos);
        if(pallet.getObservaciones() != null) {
            trazabilidadForm.setObsevacionesPallet(pallet.getObservaciones());
        } 
    }
    
    private void setDatosRemito(TrazabilidadForm trazabilidadForm, RemitoModel remito, UserService userService) {
        trazabilidadForm.setCodigoRemito(remito.getId().toString());
        if(remito.getFechaAlta() != null) {
            trazabilidadForm.setFechaAltaRemito(remito.getFechaAlta().toString());
        }                
        if(remito.getEstado() != null) {
            trazabilidadForm.setEstadoRemito(remito.getEstado());
        }
        if(remito.getIdUsuarioAlta() != null) {
            UserModel user = userService.getUserById(remito.getIdUsuarioAlta());
            if(user != null) {
                trazabilidadForm.setUsuarioAltaRemito(user.getUsername());
            }        
        }
        if(remito.getTipoRemito() != null) {
            trazabilidadForm.setTipoRemito(remito.getTipoRemito());
        }
        if(remito.getIdTransporte() != null) {
            ProveedorService proveedorService = new ProveedorServiceImpl();
            ProveedorModel proveedor = proveedorService.getByPk(remito.getIdTransporte());
            if(proveedor != null) {
                trazabilidadForm.setTransporteRemito(proveedor.getRazonSocial());
            }
        }
        
    }    
    
    
    private void setDatosHojaDeRuta(TrazabilidadForm trazabilidadForm, HojaDeRutaModel hojaDeRuta, UserService userService) {
        trazabilidadForm.setCodigoHojaDeRuta(hojaDeRuta.getId().toString());
        if(hojaDeRuta.getFecha() != null) {
            trazabilidadForm.setFechaAltaHojaDeRuta(hojaDeRuta.getFecha().toString().replace("00:00:00.0", ""));
        }                
        if(hojaDeRuta.getEstado() != null) {
            trazabilidadForm.setEstadoHojaDeRuta(hojaDeRuta.getEstado());
        }
        if(hojaDeRuta.getIdUsuarioAlta() != null) {
            UserModel user = userService.getUserById(hojaDeRuta.getIdUsuarioAlta());
            if(user != null) {
                trazabilidadForm.setUsuarioAltaHojaDeRuta(user.getUsername());
            }        
        }
        if(hojaDeRuta.getFechaCarga() != null) {
            trazabilidadForm.setFechaCarga(hojaDeRuta.getFechaCarga().toString().replace("00:00:00.0", "") + " " + hojaDeRuta.getHoraCarga());
        }                
        if(hojaDeRuta.getFechaSalida() != null) {
            trazabilidadForm.setFechaSalida(hojaDeRuta.getFechaSalida().toString().replace("00:00:00.0", "") + " " + hojaDeRuta.getHoraSalida());
        }                
        if(hojaDeRuta.getIdChofer() != null) {
            ContactoService contactoService = new ContactoServiceImpl();
            ContactoModel chofer = contactoService.getByPk(hojaDeRuta.getIdChofer());
            if(chofer != null) {
                trazabilidadForm.setChofer(chofer.getNombre());
            }
        }
        
    }    
    
    private void setDatosBaja(TrazabilidadForm trazabilidadForm, EgresoDepositoModel egresoDeposito, UserService userService) {
        trazabilidadForm.setFechaBaja(egresoDeposito.getFechaBaja().toString());
         if(egresoDeposito.getIdUsuarioBaja() != null) {
            UserModel user = userService.getUserById(egresoDeposito.getIdUsuarioBaja());
            if(user != null) {
                trazabilidadForm.setUsuarioBaja(user.getUsername());
            }        
        }
    }  

    
}

