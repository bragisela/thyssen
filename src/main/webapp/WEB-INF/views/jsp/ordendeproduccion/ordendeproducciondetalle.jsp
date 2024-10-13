<%@include file = "/WEB-INF/views/jsp/includes/header.jsp" %>
    
<style>
    
    .nav-tabs li.disabled a {
        pointer-events: none;
    }
</style>

   
<!-- Content Wrapper. Contains page content -->
<!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <section class="content">
            <div id="cuerpo" class="container">                
                <div class="container">
                    <ol>
                        <div class="card">
                            <div class="card-header">
                                <ol class="breadcrumb mb-1 mt-1">
                                    <li class="breadcrumb-item"><a href="">Orden De Producción</a></li>
                                    <li class="breadcrumb-item active">${titleOrdenDeProduccion}</li>
                                </ol>
                            </div>

                            <div class="card-body">
                                <form:form class="form-horizontal" method="post"
                                        action="/thyssenplastic/ordenDeProduccionDetalle/addOrEditOrRemove"
                                        modelAttribute="ordenDeProduccionDetalleForm" id="myForm">

                                    <div class="container">


                                        <c:set var = "disabledAlta" value = "true"/>
                                        <c:set var = "disabledCierre" value = "true"/>      
                                        <c:set var = "tabBobinasActiva" value = "active"/>      
                                        <c:set var = "tabBultosActiva" value = ""/>  
                                        <c:set var = "tabPalletsActiva" value = ""/>  
                                        <c:set var = "tabScrapActiva" value = ""/>  
                                        <c:set var = "disabledObservacionesBobina" value = "true"/>
                                        <c:set var = "disabledEstadoBobina" value = "true"/>
                                        <c:set var = "disabledObservacionesBulto" value = "true"/>
                                        <c:set var = "disabledEstadoBulto" value = "true"/>                                        
                                        <c:set var = "disabledObservacionesPallet" value = "true"/>
                                        <c:set var = "disabledEstadoPallet" value = "true"/>                                                                                
                                        <c:set var = "disabledObservacionesScrap" value = "true"/>
                                        <c:set var = "disabledEstadoScrap" value = "true"/>                                                                                                                        
                                        <c:set var = "classDivBobina" value = ""/>
                                        <c:set var = "classDivBulto" value = ""/>
                                        <c:set var = "classDivPallet" value = ""/>
                                        <c:set var = "classDivScrap" value = ""/>
                                        <c:if test = "${estadoOrderProduccion != 'Completado'}">
                                            <c:if test = "${operacion == 'alta'}">
                                                <c:set var = "disabledAlta" value = "false"/>
                                                <c:set var = "disabledObservacionesBobina" value = "false"/>
                                                <c:set var = "disabledEstadoBobina" value = "false"/>
                                                <c:set var = "disabledObservacionesBulto" value = "false"/>
                                                <c:set var = "disabledEstadoBulto" value = "false"/>                                            
                                                <c:set var = "disabledObservacionesPallet" value = "false"/>
                                                <c:set var = "disabledEstadoPallet" value = "false"/>                                                                                        
                                                <c:set var = "disabledObservacionesScrap" value = "false"/>
                                                <c:set var = "disabledEstadoScrap" value = "false"/>                                                                                                                                        
                                                <c:set var = "disabledCierre" value = "true"/>                                            
                                                <c:set var = "classDivBobina" value = "in active"/>
                                                <c:set var = "classDivBulto" value = ""/>                                            
                                                <c:set var = "classDivPallet" value = ""/>
                                                <c:set var = "classDivScrap" value = ""/>
                                                <c:if test = "${estadoOrderProduccion == 'Nuevo' || estadoOrderProduccion == 'Abierto'}">
                                                    <c:set var = "tabBobinasDisabled" value = "disabled disabledTab"/>      
                                                    <c:set var = "tabBultosDisabled" value = "disabled disabledTab"/>  
                                                    <c:set var = "tabPalletsDisabled" value = "disabled disabledTab"/>                                                                                                                                                      
                                                    <c:set var = "tabScrapsDisabled" value = "disabled disabledTab"/>     
                                                </c:if>
                                                <c:if test = "${estadoOrderProduccion == 'Fabricacion'}">
                                                    <c:set var = "tabBobinasDisabled" value = ""/>      
                                                    <c:set var = "tabBultosDisabled" value = "disabled disabledTab"/>  
                                                    <c:set var = "tabPalletsDisabled" value = "disabled disabledTab"/>                                                                                                                                                      
                                                    <c:set var = "tabScrapsDisabled" value = "disabled disabledTab"/>                                                                                                                                                      
                                                </c:if>                                                
                                                <c:if test = "${estadoOrderProduccion == 'Empaque'}">
                                                    <c:set var = "tabBobinasDisabled" value = ""/>      
                                                    <c:set var = "tabBultosDisabled" value = ""/>  
                                                    <c:set var = "tabPalletsDisabled" value = "disabled disabledTab"/>                                                                                                                                                      
                                                    <c:set var = "tabScrapsDisabled" value = "disabled disabledTab"/>
                                                </c:if>                                                                                                
                                                <c:if test = "${estadoOrderProduccion == 'Pallet'}">
                                                    <c:set var = "tabBobinasDisabled" value = ""/>      
                                                    <c:set var = "tabBultosDisabled" value = ""/>  
                                                    <c:set var = "tabPalletsDisabled" value = ""/>                                                                                                                                                      
                                                    <c:set var = "tabScrapsDisabled" value = ""/>                                                    
                                                </c:if>                                                                                                                                                
                                            </c:if>
                                            <c:if test = "${operacion == 'view'}">
                                                <c:set var = "disabledAlta" value = "true"/>
                                                <c:set var = "disabledCierre" value = "true"/>   
                                                <c:if test = "${tipoDetalle == 'bobina'}">   
                                                    <c:set var = "tabBobinasActiva" value = "active"/>      
                                                    <c:set var = "tabBultosActiva" value = ""/>  
                                                    <c:set var = "tabPalletsActiva" value = ""/>  
                                                    <c:set var = "tabScrapsActiva" value = ""/>
                                                    <c:set var = "tabBobinasDisabled" value = ""/>      
                                                    <c:set var = "tabBultosDisabled" value = "disabled disabledTab"/>  
                                                    <c:set var = "tabPalletsDisabled" value = "disabled disabledTab"/>                                                                                                  
                                                    <c:set var = "tabScrapsDisabled" value = "disabled disabledTab"/>                                                                                                  
                                                    <c:set var = "classDivBobina" value = "in active"/>
                                                    <c:set var = "classDivBulto" value = ""/>      
                                                    <c:set var = "classDivPallet" value = ""/>
                                                    <c:set var = "classDivScrap" value = ""/>
                                                </c:if>
                                                <c:if test = "${tipoDetalle == 'bulto'}">   
                                                    <c:set var = "tabBobinasActiva" value = ""/>      
                                                    <c:set var = "tabBultosActiva" value = "active"/>  
                                                    <c:set var = "tabPalletsActiva" value = ""/>  
                                                    <c:set var = "tabScrapsActiva" value = ""/>  
                                                    <c:set var = "tabBobinasDisabled" value = "disabled disabledTab"/>      
                                                    <c:set var = "tabBultosDisabled" value = ""/>  
                                                    <c:set var = "tabPalletsDisabled" value = "disabled disabledTab"/>                                                                                                  
                                                    <c:set var = "tabScrapsDisabled" value = "disabled disabledTab"/>                                                                                                  
                                                    <c:set var = "classDivBobina" value = ""/>
                                                    <c:set var = "classDivBulto" value = "in active"/>    
                                                    <c:set var = "classDivPallet" value = ""/>
                                                    <c:set var = "classDivScrap" value = ""/>
                                                </c:if>                                            
                                                <c:if test = "${tipoDetalle == 'pallet'}">   
                                                    <c:set var = "tabBobinasActiva" value = ""/>      
                                                    <c:set var = "tabBultosActiva" value = ""/>  
                                                    <c:set var = "tabPalletsActiva" value = "active"/>  
                                                    <c:set var = "tabScrapsActiva" value = ""/>  
                                                    <c:set var = "tabBobinasDisabled" value = "disabled disabledTab"/>      
                                                    <c:set var = "tabBultosDisabled" value = "disabled disabledTab"/>  
                                                    <c:set var = "tabPalletsDisabled" value = ""/>                                                                                                  
                                                    <c:set var = "tabScrapsDisabled" value = "disabled disabledTab"/> 
                                                    <c:set var = "classDivBobina" value = ""/>
                                                    <c:set var = "classDivBulto" value = ""/>    
                                                    <c:set var = "classDivPallet" value = "in active"/>
                                                    <c:set var = "classDivScrap" value = ""/>
                                                </c:if>                                                                                        
                                                <c:if test = "${tipoDetalle == 'scrap'}">   
                                                    <c:set var = "tabBobinasActiva" value = ""/>      
                                                    <c:set var = "tabBultosActiva" value = ""/>  
                                                    <c:set var = "tabPalletsActiva" value = ""/>  
                                                    <c:set var = "tabScrapsActiva" value = "active"/>  
                                                    <c:set var = "tabBobinasDisabled" value = "disabled disabledTab"/>      
                                                    <c:set var = "tabBultosDisabled" value = "disabled disabledTab"/>  
                                                    <c:set var = "tabPalletsDisabled" value = "disabled disabledTab"/>                                                                                                  
                                                    <c:set var = "tabScrapsDisabled" value = ""/> 
                                                    <c:set var = "classDivBobina" value = ""/>
                                                    <c:set var = "classDivBulto" value = ""/>    
                                                    <c:set var = "classDivPallet" value = ""/>
                                                    <c:set var = "classDivScrap" value = "in active"/>
                                                </c:if>                                                                                                                                        
                                            </c:if>
                                            <c:if test = "${operacion == 'remove'}">
                                                <c:set var = "disabledAlta" value = "true"/>
                                                <c:set var = "disabledCierre" value = "true"/>   
                                                <c:if test = "${tipoDetalle == 'bulto'}">   
                                                    <c:set var = "tabBobinasActiva" value = ""/>      
                                                    <c:set var = "tabBultosActiva" value = "active"/>  
                                                    <c:set var = "tabPalletsActiva" value = ""/>  
                                                    <c:set var = "tabScrapsActiva" value = ""/>  
                                                    <c:set var = "tabBobinasDisabled" value = "disabled disabledTab"/>      
                                                    <c:set var = "tabBultosDisabled" value = ""/>  
                                                    <c:set var = "tabPalletsDisabled" value = "disabled disabledTab"/>                                                                                                  
                                                    <c:set var = "tabScrapsDisabled" value = "disabled disabledTab"/>                                                                                                  
                                                    <c:set var = "classDivBobina" value = ""/>
                                                    <c:set var = "classDivBulto" value = "in active"/>    
                                                    <c:set var = "classDivPallet" value = ""/>
                                                    <c:set var = "classDivScrap" value = ""/>
                                                </c:if>                                            
                                                <c:if test = "${tipoDetalle == 'scrap'}">   
                                                    <c:set var = "tabBobinasActiva" value = ""/>      
                                                    <c:set var = "tabBultosActiva" value = ""/>  
                                                    <c:set var = "tabPalletsActiva" value = ""/>  
                                                    <c:set var = "tabScrapsActiva" value = "active"/>  
                                                    <c:set var = "tabBobinasDisabled" value = "disabled disabledTab"/>      
                                                    <c:set var = "tabBultosDisabled" value = "disabled disabledTab"/>  
                                                    <c:set var = "tabPalletsDisabled" value = "disabled disabledTab"/>                                                                                                  
                                                    <c:set var = "tabScrapsDisabled" value = ""/>                                                                                                  
                                                    <c:set var = "classDivBobina" value = ""/>
                                                    <c:set var = "classDivBulto" value = ""/>    
                                                    <c:set var = "classDivPallet" value = ""/>
                                                    <c:set var = "classDivScrap" value = "in active"/>
                                                </c:if>                                                                                            
                                            </c:if>                                            
                                            <c:if test = "${operacion == 'completar'}">
                                                <c:set var = "disabledAlta" value = "true"/>
                                                <c:set var = "disabledCierre" value = "true"/>                                            
                                            </c:if>
                                            <c:if test = "${operacion == 'edit'}">
                                                <c:set var = "disabledAlta" value = "false"/>
                                                <c:set var = "disabledCierre" value = "true"/>                                            
                                                <c:if test = "${tipoDetalle == 'bobina'}">   
                                                    <c:set var = "tabBobinasActiva" value = "active"/>      
                                                    <c:set var = "tabBultosActiva" value = ""/>  
                                                    <c:set var = "tabPalletsActiva" value = ""/>  
                                                    <c:set var = "tabScrapsActiva" value = ""/>  
                                                    <c:set var = "tabBobinasDisabled" value = ""/>      
                                                    <c:set var = "tabBultosDisabled" value = "disabled disabledTab"/>  
                                                    <c:set var = "tabPalletsDisabled" value = "disabled disabledTab"/>                                                  
                                                    <c:set var = "tabScrapsDisabled" value = "disabled disabledTab"/>                                                  
                                                    <c:set var = "disabledObservacionesBobina" value = "false"/>
                                                    <c:set var = "disabledEstadoBobina" value = "false"/>
                                                    <c:set var = "disabledObservacionesBulto" value = "true"/>
                                                    <c:set var = "disabledEstadoBulto" value = "true"/>   
                                                    <c:set var = "disabledObservacionesPallet" value = "false"/>
                                                    <c:set var = "disabledEstadoPallet" value = "false"/>                                                                                            
                                                    <c:set var = "disabledObservacionesScrap" value = "false"/>
                                                    <c:set var = "disabledEstadoScrap" value = "false"/>                                                                                                                                                
                                                    <c:set var = "classDivBobina" value = "in active"/>
                                                    <c:if test = "${estaEnBulto == 'true'}">
                                                        <c:set var = "disabledAlta" value = "true"/>
                                                    </c:if>        
                                                </c:if>
                                                <c:if test = "${tipoDetalle == 'bulto'}">   
                                                    <c:set var = "tabBobinasActiva" value = ""/>      
                                                    <c:set var = "tabBultosActiva" value = "active"/>  
                                                    <c:set var = "tabPalletsActiva" value = ""/>  
                                                    <c:set var = "tabScrapsActiva" value = ""/>  
                                                    <c:set var = "tabBobinasDisabled" value = "disabled disabledTab"/>      
                                                    <c:set var = "tabBultosDisabled" value = ""/>  
                                                    <c:set var = "tabPalletsDisabled" value = "disabled disabledTab"/>                                                  
                                                    <c:set var = "tabScrapsDisabled" value = "disabled disabledTab"/>                                                  
                                                    <c:set var = "disabledObservacionesBobina" value = "true"/>
                                                    <c:set var = "disabledEstadoBobina" value = "true"/>
                                                    <c:set var = "disabledObservacionesBulto" value = "false"/>
                                                    <c:set var = "disabledEstadoBulto" value = "false"/>   
                                                    <c:set var = "disabledObservacionesPallet" value = "true"/>
                                                    <c:set var = "disabledEstadoPallet" value = "true"/>                                                                                            
                                                    <c:set var = "disabledObservacionesScrap" value = "true"/>
                                                    <c:set var = "disabledEstadoScrap" value = "true"/>                                                                                                                                                
                                                    <c:set var = "classDivBulto" value = "in active"/>
                                                    <c:if test = "${estaEnPallet == 'true'}">
                                                        <c:set var = "disabledAlta" value = "true"/>
                                                    </c:if>        
                                                </c:if>                                            
                                                <c:if test = "${tipoDetalle == 'pallet'}">   
                                                    <c:set var = "tabBobinasActiva" value = ""/>      
                                                    <c:set var = "tabBultosActiva" value = ""/>  
                                                    <c:set var = "tabPalletsActiva" value = "active"/>  
                                                    <c:set var = "tabScrapsActiva" value = ""/>  
                                                    <c:set var = "tabBobinasDisabled" value = "disabled disabledTab"/>      
                                                    <c:set var = "tabBultosDisabled" value = "disabled disabledTab"/>  
                                                    <c:set var = "tabPalletsDisabled" value = ""/>                                                  
                                                    <c:set var = "tabScrapsDisabled" value = "disabled disabledTab"/>  
                                                    <c:set var = "disabledObservacionesBobina" value = "true"/>
                                                    <c:set var = "disabledEstadoBobina" value = "true"/>
                                                    <c:set var = "disabledObservacionesBulto" value = "true"/>
                                                    <c:set var = "disabledEstadoBulto" value = "true"/>   
                                                    <c:set var = "disabledObservacionesPallet" value = "false"/>
                                                    <c:set var = "disabledEstadoPallet" value = "false"/>                                                                                            
                                                    <c:set var = "disabledObservacionesScrap" value = "true"/>
                                                    <c:set var = "disabledEstadoScrap" value = "true"/>                                                                                                                                                
                                                    <c:set var = "classDivPallet" value = "in active"/>
                                                    <c:if test = "${estaEnPallet == 'true'}">
                                                        <c:set var = "disabledAlta" value = "true"/>
                                                    </c:if>        
                                                </c:if>                                                                                        
                                                <c:if test = "${tipoDetalle == 'scrap'}">   
                                                    <c:set var = "tabBobinasActiva" value = ""/>      
                                                    <c:set var = "tabBultosActiva" value = ""/>  
                                                    <c:set var = "tabPalletsActiva" value = ""/>  
                                                    <c:set var = "tabScrapsActiva" value = "active"/>  
                                                    <c:set var = "tabBobinasDisabled" value = "disabled disabledTab"/>      
                                                    <c:set var = "tabBultosDisabled" value = "disabled disabledTab"/>  
                                                    <c:set var = "tabPalletsDisabled" value = "disabled disabledTab"/>                                                  
                                                    <c:set var = "tabScrapsDisabled" value = ""/>  
                                                    <c:set var = "disabledObservacionesBobina" value = "true"/>
                                                    <c:set var = "disabledEstadoBobina" value = "true"/>
                                                    <c:set var = "disabledObservacionesBulto" value = "true"/>
                                                    <c:set var = "disabledEstadoBulto" value = "true"/>   
                                                    <c:set var = "disabledObservacionesPallet" value = "true"/>
                                                    <c:set var = "disabledEstadoPallet" value = "true"/>                                                                                            
                                                    <c:set var = "disabledObservacionesScrap" value = "false"/>
                                                    <c:set var = "disabledEstadoScrap" value = "false"/>                                                                                                                                                
                                                    <c:set var = "classDivPallet" value = "in active"/>
                                                </c:if>                                                                                                                                        
                                            </c:if>
                                        </c:if>
                                        <c:if test = "${estadoOrderProduccion == 'Completado'}">
                                            <c:set var = "classDivBobina" value = "in active"/>
                                        </c:if>
                                        
                                        ${action}
                                        
                                        <p></p>
                                        ${ordenDeProduccionName}
                                        
                                        <p></p>

                                        <form:hidden path="pk" class="form-control"/>
                                        <form:hidden path="action" class="form-control"/>
                                        <form:hidden path="operacion" class="form-control"/>
                                        <form:hidden path="idOrdenProduccion" class="form-control"/>
                                        <form:hidden path="idCliente" class="form-control"/>
                                        <form:hidden path="idArticulo" class="form-control"/>
                                        <form:hidden path="idFichaTecnica" class="form-control"/>                                        
                                        <form:hidden path="tipoDetalle" class="form-control"/>
                                        <form:hidden path="pesoNetoBobina" class="form-control"/>
                                        <form:hidden path="imprimir" class="form-control"/>
                                        <form:hidden path="imprimirTipo" class="form-control"/>
                                        <form:hidden path="imprimirPk" class="form-control"/>

                                        <div class="tab-content">

                                            <div class="form-row row">                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <a href="/thyssenplastic/ordenDeProduccion" id="ordprod">Atrás</a>
                                                </div>                                                                                                    
                                            </div>
                                            
                                            <p>
                                                
                                            <div class="form-row row">                                                
                                                <div class="row col-xs-12 col-sm-12 col-xl-12" style="border: 2px solid black;background-color:aquamarine;padding-left: 10px;">
                                                    <label for="inputFecha" style="font-size:30px;">Orden de Producción <i>${idOrdenDeProduccion}</i> - Artículo <i>(${articuloLabel})</i> - Estado <i>${estadoOrderProduccion}</i></label>
                                                    <br> 
                                                    
                                                    <div class="row col-xs-24 col-sm-4 col-xl-4" style="background-color: white; padding: 2px; border-left: 8px solid green; border-radius: 5px; margin: 4px;">
                                                        <h4>Cantidad de bobinas: ${cantidadDeBobinasQueNoEstanEnBulto}</h4>
                                                    </div>
                                                    
                                                    <div class="row col-xs-24 col-sm-4 col-xl-4" style="background-color: white; padding: 2px; border-left: 8px solid green; border-radius: 5px; margin: 4px;">
                                                        <h4>Cantidad de bultos: ${cantidadDeBultosQueNoEstanEnPallet}</h4>
                                                    </div>
                                                    
                                                    <div class="row col-xs-24 col-sm-4 col-xl-4" style="background-color: white; padding: 2px; border-left: 8px solid green; border-radius: 5px; margin: 4px;" >
                                                        <h4>Cantidad de pallets: ${cantidadPallet}</h4>
                                                    </div>
                                                    
                                                    <div class="row col-xs-24 col-sm-4 col-xl-4" style="background-color: white; padding: 2px; border-left: 8px solid green; border-radius: 5px; margin: 4px;" >
                                                        <h4>Bobinas en orden de produccion: ${cantidadBobinasEnProduccion}</h4>
                                                    </div>  
                                                </div>
                                            </div>
                                                
                                            <p>

                                            <c:if test = "${rol == 'oficina'}">
                                                <div class="form-row row">

                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Fecha Alta</label>
                                                        <form:input type="date" path="fechaAltaOrdenDeProduccion" class="form-control" placeholder=""
                                                                    id="holderDateOfBirth" required="required" disabled="true"/>
                                                    </div>                                                    

                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Estado</label>
                                                        <form:input type="text" path="estadoOrdenDeProduccion" class="form-control" placeholder=""
                                                                    id="holderDateOfBirth" required="required" disabled="true"/>                                                                                                        
                                                    </div>
                                                </div>    
                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Peso Técnico (kg)</label>
                                                        <form:input type="text" path="pesoTecnicoArticulo" class="form-control" placeholder=""
                                                                    id="holderDateOfBirth" required="required" disabled="true"/>
                                                    </div>                                                    

                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Espesor (micrones)</label>
                                                        <form:input type="text" path="espesorArticulo" class="form-control" placeholder=""
                                                                    id="holderDateOfBirth" required="required" disabled="true"/>
                                                    </div>                                                    

                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Ancho (mm)</label>
                                                        <form:input type="text" path="anchoArticulo" class="form-control" placeholder=""
                                                                    id="holderDateOfBirth" required="required" disabled="true"/>
                                                    </div>                                                    

                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Alto (mm)</label>
                                                        <form:input type="text" path="altoArticulo" class="form-control" placeholder=""
                                                                    id="holderDateOfBirth" required="required" disabled="true"/>
                                                    </div>                                                    

                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Metros (mtrs)</label>
                                                        <form:input type="text" path="metrosArticulo" class="form-control" placeholder=""
                                                                    id="holderDateOfBirth" required="required" disabled="true"/>
                                                    </div>                                                                                                    
                                                </div>                 
                                            </c:if>                                                
                                            
                                            <p>&nbsp;</p>
                                            
                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-1 col-xl-1" style="display:${displayButtonCambiarEstadoFabricacion}">
                                                    <button type="button" class="btn btn-primary" onclick="window.location.href='/thyssenplastic/ordenDeProduccionDetalle/setStatusFabricacionOrdenProduccion/${idOrdenDeProduccion}'">Cambiar Estado Fabricación</button>                                                                                                                                                    
                                                </div>
                                            </div>

                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-1 col-xl-1" style="display:${displayButtonCambiarEstadoEmpaque}">
                                                    <button type="button" class="btn btn-primary" onclick="window.location.href='/thyssenplastic/ordenDeProduccionDetalle/setStatusEmpaqueOrdenProduccion/${idOrdenDeProduccion}'">Cambiar Estado Empaque</button>                                                                                                                                                    
                                                </div>
                                            </div>

                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-1 col-xl-1" style="display:${displayButtonCambiarEstadoPallet}">
                                                    <button type="button" class="btn btn-primary" onclick="window.location.href='/thyssenplastic/ordenDeProduccionDetalle/setStatusPalletOrdenProduccion/${idOrdenDeProduccion}'">Cambiar Estado Pallet</button>                                                                                                                                                    
                                                </div>
                                            </div>

                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-1 col-xl-1" style="display:${displayButtonCambiarEstadoCompletado}">
                                                    <button type="button" class="btn btn-primary" onclick="window.location.href='/thyssenplastic/ordenDeProduccionDetalle/setStatusCompletadoOrdenProduccion/${idOrdenDeProduccion}'">Cambiar Estado Completado</button>                                                                                                                                                    
                                                </div>
                                            </div>
                                                
                                        </div>                                        
                                        
                                        <p></p>
                                        <hr>
                                        <p></p>

                                        <div class="row">
                                            <div class="row col-xs-12 col-sm-12 col-xl-12">
                                                <!--TAB HORIZONTAL material formulasion bobina-->
                                                <div class="container">

                                                    <ul class="nav nav-tabs" id="myTab">
                                                        <li class="${tabBobinasActiva} ${tabBobinasDisabled}">
                                                            <a href="#bobinas" data-toggle="tab">Bobinas</a>
                                                        </li>
                                                        <li class="${tabBultosActiva} ${tabBultosDisabled}">
                                                            <a href="#bultos" data-toggle="tab" id="tab_aaa">Bultos</a>
                                                        </li>
                                                        <li class="${tabPalletsActiva} ${tabPalletsDisabled}">
                                                            <a href="#pallets" data-toggle="tab">Pallets</a>
                                                        </li>
                                                        <li class="${tabScrapsActiva} ${tabScrapsDisabled}">
                                                            <a href="#scraps" data-toggle="tab">Scraps</a>
                                                        </li>                                                        
                                                    </ul>


                                                    <div class="tab-content">
                                                        <div id="bobinas" class="tab-pane fade ${classDivBobina}">
                                                            <!--CONTENIDO BOBINA-->
                                                            <div class="container">
                                                                <p>&nbsp;</p>
                                                                
                                                                ${ordenDeProduccionBobinaName}

                                                                <p>&nbsp;</p>
                                                                
                                                                <div class="form-row row">                                                                                                                                
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                        <label>Peso Cono (kg)</label>
                                                                        <form:input type="number" path="pesoConoBobina" step="0.01" class="form-control" placeholder="Peso Cono ..." disabled="${disabledAlta}" onChange="calculatePesoNetoBobina()"/>
                                                                    </div>
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                        <label>Peso Total (kg)</label>
                                                                        <form:input type="number" path="pesoTotalBobina" step="0.01" class="form-control" placeholder="Peso Total ..." disabled="${disabledAlta}" onChange="calculatePesoNetoBobina()"/>
                                                                    </div>
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                        <label>Peso Neto (kg)</label>
                                                                        <input type="number" id="pesoNetoBobinaCalculated" step="0.01" class="form-control" placeholder="Peso Neto ..." disabled="true">
                                                                    </div>
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                        <label>Espesor (micrones)</label>
                                                                        <form:input type="text" path="espesorBobina" class="form-control" disabled="true" />
                                                                    </div>                                                                    
                                                                </div>

                                                                <p>&nbsp;</p>

                                                                <div class="form-row row">    
                                                                    <div class="row col-xs-12 col-sm-12 col-md-12">
                                                                        <ol class="breadcrumb mb-1 mt-1">
                                                                            <li class="breadcrumb-item"><a href="#">Resultado</a></li>
                                                                        </ol>
                                                                    </div>
                                                                </div>
                                                                                                                                
                                                                <div class="form-row row">
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">                                                                        
                                                                            <label>Estado</label>
                                                                            <form:select type="text" path="estadoBobina" class="form-control" disabled="${disabledEstadoBobina}">
                                                                                <form:option value="-1">Seleccionar...</form:option>
                                                                                <form:option value="ok">OK</form:option>
                                                                                <form:option value="observado">Observado</form:option>
                                                                                <form:option value="rechazado">Rechazado</form:option>
                                                                                <form:option value="sinmesurar">Sin Mesurar</form:option>
                                                                            </form:select>                                                                        
                                                                    </div>
                                                                                                                                                                                                
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                        <label for="observacionesBobina">Observaciones</label>
                                                                        <form:textarea type="text" maxlength="4000" rows="3" path="observacionesBobina" class="form-control" placeholder="" disabled="${disabledObservacionesBobina}" />
                                                                    </div>                                                                
                                                                </div>
                                                                    
                                                                <div class="form-row row">
                                                                    <div class="row col-xs-9 col-sm-1 col-xl-1" style="display:${displayActionButton}">
                                                                        <button type="button" class="btn btn-primary" onclick="callBobinaSave()">${buttonLabel}</button>                                            
                                                                    </div>
                                                                    <div class="row col-xs-9 col-sm-1 col-xl-1">
                                                                        <a href="/thyssenplastic/ordenDeProduccionDetalle/${idOrdenDeProduccion}"><button type="button" class="btn btn-secondary">Cancelar</button></a>
                                                                    </div>    
                                                                </div>

                                                                <p>&nbsp;</p>                                                                    
                                                                
                                                                <hr>

                                                                <div class="form-row row">
                                                                    <div class="card">

                                                                        <div class="card-body">

                                                                            <div class="row col-xs-12 col-sm-12 col-xl-12">
                                                                                <table id="ordendeproduccionbobinasTable" class="display table table-striped table-hover cell-border">
                                                                                    <thead>
                                                                                        <tr>
                                                                                            <th>NRO BOBINA</th>
                                                                                            <th>FECHA ALTA</th>
                                                                                            <th>PESO TOTAL</th>
                                                                                            <th>PESO NETO</th>
                                                                                            <th>ESPESOR</th>
                                                                                            <th>CALIDAD</th>
                                                                                            <th>ESTA EN BULTO</th>
                                                                                            <th style="text-align: center">ACCIONES</th>
                                                                                        </tr>
                                                                                    </thead>

                                                                                    <tbody>

                                                                                        <c:forEach items="${ordenDeProduccionBobinas}" var="ordendeproduccionbobina">
                                                                                            <c:if test = "${ordendeproduccionbobina.estado == 'ok'}">             
                                                                                                <tr style="color: green; font-weight:bold">
                                                                                            </c:if>
                                                                                            <c:if test = "${ordendeproduccionbobina.estado == 'observado'}">             
                                                                                                <tr style="color: #89B00F; font-weight:bold">
                                                                                            </c:if>
                                                                                            <c:if test = "${ordendeproduccionbobina.estado == 'rechazado'}">             
                                                                                                <tr style="color: red; font-weight:bold">
                                                                                            </c:if>
                                                                                            <c:if test = "${ordendeproduccionbobina.estado == 'sinmesurar'}">             
                                                                                                <tr style="color: orange; font-weight:bold">
                                                                                            </c:if>
                                                                                            <c:if test = "${ordendeproduccionbobina.estado == null || ordendeproduccionbobina.estado == ''}">             
                                                                                                <tr>
                                                                                            </c:if>                                                                                                    
                                                                                                <td>
                                                                                                    <c:out value="${ordendeproduccionbobina.codigo}" />
                                                                                                </td>                                            
                                                                                                <td>
                                                                                                    <c:out value="${ordendeproduccionbobina.fechaAlta}" />
                                                                                                </td>                                                                                                                                            
                                                                                                <td>
                                                                                                    <c:out value="${ordendeproduccionbobina.pesoTotal}" />
                                                                                                </td>                                            
                                                                                                <td>
                                                                                                    <c:out value="${ordendeproduccionbobina.pesoNeto}" />
                                                                                                </td>                                                                                                                                            
                                                                                                <td>
                                                                                                    <c:out value="${ordendeproduccionbobina.espesor}" />
                                                                                                </td>                                                                                                                                                                                                                                            
                                                                                                <td>
                                                                                                    <c:out value="${ordendeproduccionbobina.estadoLabel}" />
                                                                                                </td>                                                                                                                                                                                                                                                                       
                                                                                                <td>
                                                                                                    <c:out value="${ordendeproduccionbobina.estaEnBultoLabel}" />
                                                                                                </td>     
                                                                                                
                                                                                               
                                                                                                <td>                                                                                                    
                                                                                                    <c:if test = "${(rol == 'planta' && operacion == 'alta' && ordendeproduccionbobina.estaEnBulto == 'false' && estadoOrderProduccion != 'Completado' && ordendeproduccionbobina.estaEnDeposito == 'false') || (rol == 'oficina' && operacion == 'alta' && ordendeproduccionbobina.estaEnBulto == 'false' && estadoOrderProduccion != 'Completado')}">
                                                                                                        <a class="nav-link active fa fa-pencil-square-o fa-lg"
                                                                                                            href="/thyssenplastic/ordenDeProduccionDetalle/editbobina/${ordendeproduccionbobina.pk}"
                                                                                                            data-toggle="tooltip" data-placement="top" title="Editar"></a>
                                                                                                    </c:if>                                                        
                                                                                                    <a class="nav-link active fa fa-eye fa-lg"
                                                                                                        href="/thyssenplastic/ordenDeProduccionDetalle/viewbobina/${ordendeproduccionbobina.pk}"
                                                                                                        data-toggle="tooltip" data-placement="top" title="Ver"></a>
                                                                                                    <a class="nav-link active fa fa-print fa-lg"
                                                                                                       href="javascript:void(0);" onclick="printEtiquetaBobina(${ordendeproduccionbobina.pk})"
                                                                                                        data-toggle="tooltip" data-placement="top" title="Imprimir" id="imprimirBobina${ordendeproduccionbobina.pk}"></a>
                                                                                                    <c:if test = "${rol == 'oficina' || rol == 'planta'}">
                                                                                                        <a class="nav-link active fa fa-pie-chart fa-lg"
                                                                                                           href="/thyssenplastic/graficoBobina/${ordendeproduccionbobina.pk}"
                                                                                                            data-toggle="tooltip" data-placement="top" title="Gráfico Polar"></a>
                                                                                                    </c:if>                                                             
                                                                                                    <c:if test = "${rol == 'oficina' && estadoOrderProduccion != 'Completado'}">
                                                                                                        <a class="nav-link active fa fa-play fa-lg"
                                                                                                           href="/thyssenplastic/transferirBobina/${ordendeproduccionbobina.pk}"
                                                                                                            data-toggle="tooltip" data-placement="top" title="Transferir"></a>
                                                                                                    </c:if>                                                                                                            
                                                                                                        
                                                                                                </td>
                                                                                            </tr>
                                                                                        </c:forEach>
                                                                                    </tbody>
                                                                                </table>
                                                                            </div>
                                                                        </div>
                                                                    </div>     
                                                                </div>                                                                    
                                                            </div>
                                                        </div>

                                                        <div id="bultos" class="tab-pane fade ${classDivBulto}">
                                                            <!--CONTENIDO BULTO-->
                                                            <div class="container">
                                                                <p>&nbsp;</p>
                                                                
                                                                ${ordenDeProduccionBultoName}

                                                                <p>&nbsp;</p>
                                                                <div class="form-row row">
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                        <label>Código Bulto</label>
                                                                        <form:input type="text" path="codigoBultoLabel" class="form-control" placeholder="" disabled="true"/>
                                                                    </div>                                                                    
                                                                </div>                                                                
                                                                <div class="form-row row">                                                                                                                                
                                                             
                                                                     <c:choose>
                                                                        <c:when test="${statusAct ne 'none'}">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4" style="display:${displaySearchBultoButton}">
                                                                                <label>Bobina</label>
                                                                                <form:input type="text" path="searchCodigoBobina" class="form-control" placeholder="Codigo B..."   />
                                                                                
                                                                            </div>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <button style="padding: 10px 20px; background-color: #4CAF50; color: white; border: none; border-radius: 5px; cursor: pointer;" onclick="simularFocus(event)">Habilitar Lector</button>
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-12" style="display:${displaySearchBultoButton}">
                                                                                        <label>Bobina</label>
                                                                                        <div class="input-container">
                                                                                            <p class="styled-input" id="codigoBobinaResultado"></p>
                                                                                        </div>
                                                                                        <div id="cargando" class="spinner-container">
                                                                                            <div class="spinner"></div>
                                                                                            <p class="loading-text">Buscando Bobina....</p>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="campo-oculto">
                                                                            
                                                                                        <form:input type="text" id="searchCodigoBobina" path="searchCodigoBobina" class="form-control" placeholder="Codigo B..." onChange="searchBobinaAutomatically()" />
                                                                                    </div>
                                                                            

                                                                        </c:otherwise>
                                                                    </c:choose>

                                                                    
                                                                    <c:if test="${statusAct ne 'none'}">
                                                                        <div class="row col-xs-9 col-sm-3 col-xl-4" style="display:${displaySearchBobinaButton}">
                                                                            <label>Bobinas Disponibles</label>
                                                                            <form:select type="text" path="bobinasDisponibles" class="form-control" disabled="${disabledAlta}">
                                                                                <form:option value="-1">Seleccionar...</form:option>
                                                                                <form:options items="${bobinaDisponibleList}" />
                                                                            </form:select>
                                                                        </div>
                                                                        <div class="row col-xs-9 col-sm-3 col-xl-4" style="display:${displaySearchBobinaButton}">
                                                                            <button type="button" class="btn btn-primary" style="margin-top: 20px" onclick="searchBobina()">${buttonSearchBobinaLabel}</button>
                                                                        </div>
                                                                     </c:if>
                                                                    
                                                                        
                                                                </div>

                                                                <div class="form-row row">  
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4" style="display:${displaySearchBobinaButton}">
                                                                        <label>Bobina Seleccionada</label>
                                                                        <form:input type="text" path="bobinaSelectedLabel" class="form-control" placeholder="" disabled="true"/>
                                                                        <form:input type="hidden" path="idBobinaSelected" class="form-control"/>
                                                                    </div>
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4" style="display:${displaySearchBobinaButton}">
                                                                        <label>Estado Bobina Seleccionada</label>
                                                                        <form:input type="text" path="estadoBobinaSelectedLabel" class="form-control" placeholder="" disabled="true"/>                                                                        
                                                                    </div>                                                                    
                                                                </div>
                                                                
                                                                <div class="form-row row">
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                        <label>Plegadora</label>
                                                                        <form:select type="text" path="idPlegadora" class="form-control" disabled="${disabledAlta}">
                                                                            <form:options items="${plegadoraList}" />
                                                                        </form:select>
                                                                    </div>                                                                        
                                                                </div>
                                                                    
                                                                
                                                                    
                                                                <p>&nbsp;</p>

                                                                <div class="form-row row">    
                                                                    <div class="row col-xs-12 col-sm-12 col-md-12">
                                                                        <ol class="breadcrumb mb-1 mt-1">
                                                                            <li class="breadcrumb-item"><a href="#">Resultado</a></li>
                                                                        </ol>
                                                                    </div>
                                                                </div>
                                                                                                                                
                                                                <div class="form-row row">
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">                                                                        
                                                                            <label>Estado</label>
                                                                            <form:select type="text" path="estadoBulto" class="form-control" disabled="${disabledEstadoBulto}">
                                                                                <form:option value="-1">Seleccionar...</form:option>
                                                                                <form:option value="ok">OK</form:option>
                                                                                <form:option value="observado">Observado</form:option>
                                                                                <form:option value="rechazado">Rechazado</form:option>
                                                                                <form:option value="sinmesurar">Sin Mesurar</form:option>
                                                                            </form:select>                                                                        
                                                                    </div>
                                                                                                                                                                                                
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                        <label for="observacionesBobina">Observaciones</label>
                                                                        <form:textarea type="text" maxlength="4000" rows="3" path="observacionesBulto" class="form-control" placeholder="" disabled="${disabledObservacionesBulto}" />
                                                                    </div>                                                                
                                                                </div>
                                                                    
                                                                <div class="form-row row">
                                                                    <div class="row col-xs-9 col-sm-1 col-xl-1" style="display:${displayActionButton}">
                                                                        <button type="button" class="btn btn-primary" onclick="callBultoSave()">${buttonLabel}</button>                                            
                                                                    </div>
                                                                    <div class="row col-xs-9 col-sm-1 col-xl-1">
                                                                        <a href="/thyssenplastic/ordenDeProduccionDetalle/${idOrdenDeProduccion}"><button type="button" class="btn btn-secondary">Cancelar</button></a>
                                                                    </div>    
                                                                </div>

                                                                <p>&nbsp;</p>                                                                    
                                                                
                                                                <hr>

                                                                <div class="form-row row">
                                                                    <div class="card">

                                                                        <div class="card-body">

                                                                            <div class="row col-xs-12 col-sm-12 col-xl-12">
                                                                                <table id="ordendeproduccionbultosTable" class="display table table-striped table-hover cell-border">
                                                                                    <thead>
                                                                                        <tr>
                                                                                            <th>NRO BULTO</th>
                                                                                            <th>FECHA ALTA</th>
                                                                                            <th>PESO TOTAL</th>
                                                                                            <th>PESO NETO</th>
                                                                                            <th>PLEGADORA</th>                                                                                            
                                                                                            <th>CALIDAD</th>
                                                                                            <th>ESTA EN PALLET</th>
                                                                                            
                                                                                            <th style="text-align: center">ACCIONES</th>
                                                                                        </tr>
                                                                                    </thead>

                                                                                    <tbody>

                                                                                        <c:forEach items="${ordenDeProduccionBultos}" var="ordendeproduccionbulto">
                                                                                            <c:if test = "${ordendeproduccionbulto.estado == 'ok'}">             
                                                                                                <tr style="color: green; font-weight:bold">
                                                                                            </c:if>
                                                                                            <c:if test = "${ordendeproduccionbulto.estado == 'observado'}">             
                                                                                                <tr style="color: #89B00F; font-weight:bold">
                                                                                            </c:if>
                                                                                            <c:if test = "${ordendeproduccionbulto.estado == 'rechazado'}">             
                                                                                                <tr style="color: red; font-weight:bold">
                                                                                            </c:if>
                                                                                            <c:if test = "${ordendeproduccionbulto.estado == 'sinmesurar'}">             
                                                                                                <tr style="color: orange; font-weight:bold">
                                                                                            </c:if>
                                                                                            <c:if test = "${ordendeproduccionbulto.estado == null || ordendeproduccionbulto.estado == ''}">             
                                                                                                <tr>
                                                                                            </c:if>                                                                                                    
                                                                                                <td>
                                                                                                    <c:out value="${ordendeproduccionbulto.codigo}" />
                                                                                                </td>                                            
                                                                                                <td>
                                                                                                    <c:out value="${ordendeproduccionbulto.fechaAlta}" />
                                                                                                </td>                                                                                                                                            
                                                                                                <td>
                                                                                                    <c:out value="${ordendeproduccionbulto.pesoTotal}" />
                                                                                                </td>                                            
                                                                                                <td>
                                                                                                    <c:out value="${ordendeproduccionbulto.pesoNeto}" />
                                                                                                </td>                                                                                                                                            
                                                                                                <td>
                                                                                                    <c:out value="${ordendeproduccionbulto.plegadora}" />
                                                                                                </td>                                                                                                                                                                                                                                            
                                                                                                <td>
                                                                                                    <c:out value="${ordendeproduccionbulto.estadoLabel}" />
                                                                                                </td>                                                                                                                                                                                                                                                                       
                                                                                                <td>
                                                                                                    <c:out value="${ordendeproduccionbulto.estaEnPalletLabel}" />
                                                                                                </td>
                                                                                                
                                                                                                <td>
                                                                                                     <c:if test = "${(rol == 'planta' && ordendeproduccionbulto.estaEnDeposito == 'false' && operacion == 'alta' && estadoOrderProduccion != 'Completado') || (rol == 'oficina' && operacion == 'alta' && estadoOrderProduccion != 'Completado')}">
                                                                                                        <a class="nav-link active fa fa-pencil-square-o fa-lg"
                                                                                                            href="/thyssenplastic/ordenDeProduccionDetalle/editbulto/${ordendeproduccionbulto.pk}"
                                                                                                            data-toggle="tooltip" data-placement="top" title="Editar"></a>
                                                                                                    </c:if>  
                                                                                                   
                                                                                                    <a class="nav-link active fa fa-eye fa-lg"
                                                                                                        href="/thyssenplastic/ordenDeProduccionDetalle/viewbulto/${ordendeproduccionbulto.pk}"
                                                                                                        data-toggle="tooltip" data-placement="top" title="Ver"></a>
                                                                                               
                                                                                                    <a class="nav-link active fa fa-print fa-lg"
                                                                                                       href="javascript:void(0);" onclick="printEtiquetaBulto(${ordendeproduccionbulto.pk})"
                                                                                                        data-toggle="tooltip" data-placement="top" title="Imprimir"></a>
                                                                                                    <c:if test = "${rol == 'oficina' || rol == 'planta'}">
                                                                                                        <a class="nav-link active fa fa-pie-chart fa-lg"
                                                                                                           href="/thyssenplastic/graficoBobina/${ordendeproduccionbulto.idBobina}"
                                                                                                            data-toggle="tooltip" data-placement="top" title="Gráfico Polar"></a>
                                                                                                    </c:if>
                                                                                                    <c:if test = "${rol == 'oficina' && operacion == 'alta' && ordendeproduccionbulto.estaEnPallet == 'false' && estadoOrderProduccion != 'Completado'}">
                                                                                                        <a class="nav-link active fa fa-trash fa-lg"
                                                                                                            href="/thyssenplastic/ordenDeProduccionDetalle/removeBulto/${ordendeproduccionbulto.pk}"
                                                                                                            data-toggle="tooltip" data-placement="top" title="Eliminar"></a>
                                                                                                    </c:if>                                                                                                                                                                                                                                                                    
                                                                                                    <c:if test = "${rol == 'oficina' && estadoOrderProduccion != 'Completado'}">
                                                                                                        <a class="nav-link active fa fa-play fa-lg"
                                                                                                           href="/thyssenplastic/transferirBobina/${ordendeproduccionbulto.idBobina}"
                                                                                                            data-toggle="tooltip" data-placement="top" title="Transferir"></a>
                                                                                                    </c:if>    
                                                                                                </td>
                                                                                            </tr>
                                                                                        </c:forEach>
                                                                                    </tbody>
                                                                                </table>
                                                                            </div>
                                                                        </div>
                                                                    </div>     
                                                                </div>                                                                    
                                                            </div>                                                            
                                                        </div>

                                                        <div id="pallets" class="tab-pane fade ${classDivPallet}">
                                                            <!--CONTENIDO PALLET-->
                                                            <div class="container">
                                                                <p>&nbsp;</p>
                                                                
                                                                ${ordenDeProduccionPalletName}

                                                                <p>&nbsp;</p>
                                                                <div class="form-row row">
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                        <label>Código Pallet</label>
                                                                        <form:input type="text" path="codigoPalletLabel" class="form-control" placeholder="" disabled="true"/>
                                                                    </div>                                                                    
                                                                </div>                                                                
                                                                <div class="form-row row">                                                                                                                                
                                                                    
                                                                    <c:choose>
                                                                        <c:when test="${statusAct ne 'none'}">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4" style="display:${displaySearchBultoButton}">
                                                                                <label>Bulto</label>
                                                                                <form:input type="text" path="searchCodigoBulto" class="form-control" placeholder="Codigo B..." />
                                                                            </div>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4" style="display:${displaySearchBultoButton}">
                                                                                <label>Bulto</label>
                                                     
                                                                                    <div class="input-container">
                                                                                        <p class="styled-input" id="codigoBultoResultado"></p>
                                                                                    </div>
                                                                                    
                                                                                <div class="campo-oculto">       
                                                                                    <form:input type="text" path="searchCodigoBulto" id="searchCodigoBulto" class="form-control" placeholder="Codigo B..." onChange="searchBultoAutomatically()" />
                                                                                </div> 
                                                                                <button style="padding: 10px 20px; background-color: #4CAF50; color: white; border: none; border-radius: 5px; cursor: pointer;" onclick="simularFocusPallet(event)">Habilitar Lector</button>
                                                                            </div>
                                                                           <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                             <div id="cargandoPallet" class="spinner-container" style="padding: 10px 20px; margin-bottom: 20px">
                                                                                            <div class="spinner"></div>
                                                                                            <button style="padding: 5px; margin-bottom: 20px;" onclick="ocultarCargandoPallet(event)">Finalizar</button>
                                                                                            <span class="loading-text">Escaneando codigos....</span>
                                                                                            
                                                                                            
                                                                                        </div>
                                                                                        </div>
                                                                              
                                                                                    
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                        
                                                                        
                                                                        
                                                                    <c:if test="${statusAct ne 'none'}">
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4" style="display:${displaySearchBultoButton}">
                                                                        <label>Bultos Disponibles</label>
                                                                        <form:select type="text" path="bultosDisponibles" class="form-control" disabled="${disabledAlta}">
                                                                            <form:option value="-1">Seleccionar...</form:option>
                                                                            <form:options items="${bultoDisponibleList}" />
                                                                        </form:select>
                                                                    </div>
                                                                     <div class="row col-xs-9 col-sm-3 col-xl-4" style="display:${displaySearchBultoButton}">
                                                                        <button type="button" class="btn btn-primary" style="margin-top: 20px" onclick="searchBulto()">${buttonSearchBultoLabel}</button>
                                                                    </div>
                                                                     </c:if>
                                                                    
                                                                        
                                                                </div>

                                                                <div class="form-row row">  
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4" style="display:${displaySearchBultoButton}">
                                                                        <label>Bultos Seleccionados</label>
                                                                        <form:select type="text" path="bultosSelected" class="form-control" disabled="${disabledAlta}"  multiple="true" size="10">
                                                                            <form:options items="${bultoSelectedList}" />
                                                                        </form:select>                                                                        
                                                                    </div>
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4" style="display:${displaySearchBultoButton}">
                                                                        <button type="button" class="btn btn-primary" style="margin-top: 20px" onclick="eliminarBulto()">Eliminar Bulto</button>
                                                                    </div>
                                                                        
                                                                </div>
                                                                                                                                    
                                                                <p>&nbsp;</p>

                                                                <div class="form-row row">    
                                                                    <div class="row col-xs-12 col-sm-12 col-md-12">
                                                                        <ol class="breadcrumb mb-1 mt-1">
                                                                            <li class="breadcrumb-item"><a href="#">Resultado</a></li>
                                                                        </ol>
                                                                    </div>
                                                                </div>
                                                                                                                                
                                                                <div class="form-row row">
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">                                                                        
                                                                            <label>Estado</label>
                                                                            <form:select type="text" path="estadoPallet" class="form-control" disabled="${disabledEstadoPallet}">
                                                                                <form:option value="-1">Seleccionar...</form:option>
                                                                                <form:option value="ok">OK</form:option>
                                                                                <form:option value="observado">Observado</form:option>
                                                                                <form:option value="rechazado">Rechazado</form:option>
                                                                                <form:option value="sinmesurar">Sin Mesurar</form:option>
                                                                            </form:select>                                                                        
                                                                    </div>
                                                                                                                                                                                                
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                        <label for="observacionesBulto">Observaciones</label>
                                                                        <form:textarea type="text" maxlength="4000" rows="3" path="observacionesPallet" class="form-control" placeholder="" disabled="${disabledObservacionesPallet}" />
                                                                    </div>                                                                
                                                                </div>
                                                                    
                                                                <div class="form-row row">
                                                                    <div class="row col-xs-9 col-sm-1 col-xl-1" style="display:${displayActionButton}">
                                                                        <button type="button" class="btn btn-primary" onclick="callPalletSave()">${buttonLabel}</button>                                            
                                                                    </div>
                                                                    <div class="row col-xs-9 col-sm-1 col-xl-1">
                                                                        <a href="/thyssenplastic/ordenDeProduccionDetalle/${idOrdenDeProduccion}"><button type="button" class="btn btn-secondary">Cancelar</button></a>
                                                                    </div>    
                                                                </div>

                                                                <p>&nbsp;</p>                                                                    
                                                                
                                                                <hr>

                                                               

                                                                        <div class="card-body">

                                                                            <div class="row col-xs-12 col-sm-12 col-xl-12">
                                                                                <div class="modal fade rounded" id="miModal" tabindex="-1" role="dialog" aria-labelledby="miModalLabel" aria-hidden="true">
                                                                                    <div class="modal-dialog">
                                                                                        <div class="modal-content">
                                                                                            <div class="modal-header">
                                                                                                <h4 class="modal-title text-center" id="miModalLabel">Listado de bultos: <span id="codigoOrdenProduccion"></h4>
                                                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Cerrar" style="position: absolute; top: 10px; right: 10px;">
                                                                                                    <span aria-hidden="true">&times;</span>
                                                                                                </button>
                                                                                            </div>
                                                                                            <div class="modal-body">
                                                                                                <ul class="list-group">
                                                                                                    <h3 class="text-center"><span id="listCodigosOrdenProduccion"></span></h3>
                                                                                                </ul>
                                                                                            </div>
                                                                                            <div class="modal-footer">
                                                                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>

                                                                                <table id="ordendeproduccionpalletsTable" class="display table table-striped table-hover cell-border">
                                                                                    <thead>
                                                                                        <tr>
                                                                                            <th>NRO PALLET</th>
                                                                                            <th>FECHA ALTA</th>
                                                                                            <th>CANT. BULTOS</th>
                                                                                            <th>PESO TOTAL</th>
                                                                                            <th>CALIDAD</th>
                                                                                            
                                                                                            <th style="text-align: center">ACCIONES</th>
                                                                                        </tr>
                                                                                    </thead>

                                                                                    <tbody>

                                                                                        <c:forEach items="${ordenDeProduccionPallets}" var="ordendeproduccionpallet">
                                                                                            <c:if test = "${ordendeproduccionpallet.estado == 'ok'}">             
                                                                                                <tr style="color: green; font-weight:bold">
                                                                                            </c:if>
                                                                                            <c:if test = "${ordendeproduccionpallet.estado == 'observado'}">             
                                                                                                <tr style="color: #89B00F; font-weight:bold">
                                                                                            </c:if>
                                                                                            <c:if test = "${ordendeproduccionpallet.estado == 'rechazado'}">             
                                                                                                <tr style="color: red; font-weight:bold">
                                                                                            </c:if>
                                                                                            <c:if test = "${ordendeproduccionpallet.estado == 'sinmesurar'}">             
                                                                                                <tr style="color: orange; font-weight:bold">
                                                                                            </c:if>
                                                                                            <c:if test = "${ordendeproduccionpallet.estado == null || ordendeproduccionpallet.estado == ''}">             
                                                                                                <tr>
                                                                                            </c:if>                                                                                                    
                                                                                                <td>
                                                                                                    <c:out value="${ordendeproduccionpallet.codigo}" />
                                                                                                </td>                                            
                                                                                                <td>
                                                                                                    <c:out value="${ordendeproduccionpallet.fechaAlta}" />
                                                                                                </td>                                                                                                                                            
                                                                                                <td>
                                                                                                     <c:out value="${ordendeproduccionpallet.cantidadBultos}" />
                                                                 
                                                                                                    <button type="button" class="btn btn-info" data-toggle="modal" data-target="#miModal" data-codigo="${ordendeproduccionpallet.codigo}" data-lista="${ordendeproduccionpallet.mapaBultos}">
                                                                                                        Ver listado
                                                                                                    </button>
                                                                                                </td>                                                                                                                                            
                                                                                                <td>
                                                                                                    <c:out value="${ordendeproduccionpallet.pesoTotal}" />
                                                                                                </td>                                            
                                                                                                <td>
                                                                                                    <c:out value="${ordendeproduccionpallet.estadoLabel}" />
                                                                                                </td>
                                                                                               
                                                                                                <td>
                                                                                                    <c:if test = "${(rol == 'planta' && ordendeproduccionpallet.estaEnDeposito == 'false' && operacion == 'alta' && estadoOrderProduccion != 'Completado') || (rol == 'oficina' && operacion == 'alta' && estadoOrderProduccion != 'Completado')}">
                                                                                                        <a class="nav-link active fa fa-pencil-square-o fa-lg"
                                                                                                            href="/thyssenplastic/ordenDeProduccionDetalle/editpallet/${ordendeproduccionpallet.pk}"
                                                                                                            data-toggle="tooltip" data-placement="top" title="Editar"></a>
                                                                                                    </c:if>
                                                                                                    <a class="nav-link active fa fa-eye fa-lg"
                                                                                                        href="/thyssenplastic/ordenDeProduccionDetalle/viewpallet/${ordendeproduccionpallet.pk}"
                                                                                                        data-toggle="tooltip" data-placement="top" title="Ver"></a>
                                                                                                    <a class="nav-link active fa fa-print fa-lg"
                                                                                                       href="javascript:void(0);" onclick="printEtiquetaPallet(${ordendeproduccionpallet.pk})"
                                                                                                        data-toggle="tooltip" data-placement="top" title="Imprimir"></a>
                                                                                                        
                                                                                                </td>
                                                                                            </tr>
                                                                                        </c:forEach>
                                                                                    </tbody>
                                                                                </table>
                                                                            </div>
                                                                            
                                                                        </div>                                                                
                                                            </div>                                                                   
                                                        </div>  
                                                                    
                                                        <div id="scraps" class="tab-pane fade ${classDivScrap}">
                                                            <!--CONTENIDO SCRAP-->
                                                            <div class="container">
                                                                <p>&nbsp;</p>
                                                                
                                                                ${ordenDeProduccionScrapName}

                                                                <p>&nbsp;</p>
                                                                
                                                                <div class="form-row row">
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                        <label for="idOrigenScrap">Origen</label>
                                                                        <form:select  path="idOrigenScrap" class="form-control rubro" disabled="${disabledAlta}">                                                
                                                                            <form:options items="${origenScrapList}"/>
                                                                          </form:select>                                            
                                                                    </div>
                                                                </div>                                                                
                                                                
                                                                <div class="form-row row">
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                        <label for="idTipoMaterialScrap">Tipo Material</label>
                                                                        <form:select  path="idTipoMaterialScrap" class="form-control rubro" disabled="${disabledAlta}">                                                
                                                                            <form:options items="${tipoMaterialScrapList}"/>
                                                                          </form:select>                                            
                                                                    </div>
                                                                </div>                                                                

                                                                <div class="form-row row">
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                        <label for="idMotivoScrap">Motivo</label>
                                                                        <form:select  path="idMotivoScrap" class="form-control rubro" disabled="${disabledAlta}">                                                
                                                                            <form:options items="${motivoScrapList}"/>
                                                                          </form:select>                                            
                                                                    </div>
                                                                </div>                                                                

                                                                <div class="form-row row">
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                        <label for="idFormatoScrap">Formato</label>
                                                                        <form:select  path="idFormatoScrap" class="form-control rubro" disabled="${disabledAlta}">                                                
                                                                            <form:options items="${formatoScrapList}"/>
                                                                          </form:select>                                            
                                                                    </div>
                                                                </div>                                                                
                                                                
                                                                <div class="form-row row">
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                        <label for="esRecuperableScrap">Recuperable?</label>
                                                                        <form:select path="esRecuperableScrap" class="form-control rubro" disabled="${disabledAlta}">
                                                                            <form:option value="0">No</form:option>
                                                                            <form:option value="1">Si</form:option>                                                                                                                    
                                                                        </form:select>                                                                                           
                                                                    </div>
                                                                </div>                                                                

                                                                <div class="form-row row">
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                        <label for="materialImpresoScrap">Material Impreso?</label>
                                                                        <form:select path="materialImpresoScrap" class="form-control rubro" disabled="${disabledAlta}">
                                                                            <form:option value="0">No</form:option>
                                                                            <form:option value="1">Si</form:option>                                                                                                                    
                                                                        </form:select>                                                                                           
                                                                    </div>
                                                                </div>                                                                
                                                                
                                                                <div class="form-row row">                                                                                                                                
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                        <label>Peso Total (kg)</label>
                                                                        <form:input type="number" path="pesoTotalScrap" step="0.01" class="form-control" placeholder="Peso Total ..." disabled="${disabledAlta}"/>
                                                                    </div>
                                                                </div>
                                                                    
                                                                <p>&nbsp;</p>
                                                                                                                                
                                                                <div class="form-row row">
                                                                                                                                                                                                
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                        <label for="observacionesScrap">Observaciones</label>
                                                                        <form:textarea type="text" maxlength="4000" rows="3" path="observacionesScrap" class="form-control" placeholder="" disabled="${disabledObservacionesScrap}" />
                                                                    </div>                                                                
                                                                </div>
                                                                
                                                                <p>&nbsp;</p>
                                                                    
                                                                <div class="form-row row">
                                                                    <div class="row col-xs-9 col-sm-1 col-xl-1" style="display:${displayActionButtonScrap}">
                                                                        <button type="button" class="btn btn-primary" onclick="callScrapSave()">${buttonLabel}</button>                                            
                                                                    </div>
                                                                    <div class="row col-xs-9 col-sm-1 col-xl-1">
                                                                        <a href="/thyssenplastic/ordenDeProduccionDetalle/${idOrdenDeProduccion}"><button type="button" class="btn btn-secondary">Cancelar</button></a>
                                                                    </div>    
                                                                </div>

                                                                <p>&nbsp;</p>                                                                    
                                                                
                                                                <hr>

                                                                <div class="form-row row">
                                                                    <div class="card">

                                                                        <div class="card-body">

                                                                            <div class="row col-xs-12 col-sm-12 col-xl-12">
                                                                                <table id="ordendeproduccionscrapsTable" class="display table table-striped table-hover cell-border">
                                                                                    <thead>
                                                                                        <tr>
                                                                                            <th>NRO SCRAP</th>
                                                                                            <th>FECHA ALTA</th>
                                                                                            <th>ORIGEN</th>
                                                                                            <th>TIPO MATERIAL</th>
                                                                                            <th>MOTIVO</th>
                                                                                            <th>FORMATO</th>
                                                                                            <th>RECUPERABLE</th>
                                                                                            <th>MATERIAL IMPRESO</th>
                                                                                            <th>PESO TOTAL</th>
                                                                                            <th style="text-align: center">ACCIONES</th>
                                                                                        </tr>
                                                                                    </thead>

                                                                                    <tbody>

                                                                                        <c:forEach items="${ordenDeProduccionScraps}" var="ordendeproduccionscrap">
                                                                                            <tr>
                                                                                                <td>
                                                                                                    <c:out value="${ordendeproduccionscrap.codigo}" />
                                                                                                </td>                                            
                                                                                                <td>
                                                                                                    <c:out value="${ordendeproduccionscrap.fechaAlta}" />
                                                                                                </td>                                                                                                                                            
                                                                                                <td>
                                                                                                    <c:out value="${ordendeproduccionscrap.origen}" />
                                                                                                </td>                                                                                                                                            
                                                                                                <td>
                                                                                                    <c:out value="${ordendeproduccionscrap.tipoMaterial}" />
                                                                                                </td>                                                                                                                                            
                                                                                                <td>
                                                                                                    <c:out value="${ordendeproduccionscrap.motivo}" />
                                                                                                </td>                                                                                                                                            
                                                                                                <td>
                                                                                                    <c:out value="${ordendeproduccionscrap.formato}" />
                                                                                                </td>                                                                                                                                            
                                                                                                <td>
                                                                                                    <c:out value="${ordendeproduccionscrap.esRecuperable}" />
                                                                                                </td>                                                                                                                                            
                                                                                                <td>
                                                                                                    <c:out value="${ordendeproduccionscrap.materialImpreso}" />
                                                                                                </td>                                                                                                                                                                                                                                            
                                                                                                <td>
                                                                                                    <c:out value="${ordendeproduccionscrap.pesoTotal}" />
                                                                                                </td>                                            
                                                                                                <td>                                                                                                    
                                                                                                    <a class="nav-link active fa fa-eye fa-lg"
                                                                                                        href="/thyssenplastic/ordenDeProduccionDetalle/viewscrap/${ordendeproduccionscrap.pk}"
                                                                                                        data-toggle="tooltip" data-placement="top" title="Ver"></a>
                                                                                                    <a class="nav-link active fa fa-print fa-lg"
                                                                                                       href="javascript:void(0);" onclick="printEtiquetaScrap(${ordendeproduccionscrap.pk})"
                                                                                                        data-toggle="tooltip" data-placement="top" title="Imprimir" id="imprimirScrap${ordendeproduccionscrap.pk}"></a>                                                                                                        
                                                                                                    <c:if test = "${operacion == 'alta' && estadoOrderProduccion != 'Completado' && rol == 'oficina' && ordendeproduccionscrap.puedoBorrarlo}">
                                                                                                        <a class="nav-link active fa fa-trash fa-lg"
                                                                                                            href="/thyssenplastic/ordenDeProduccionDetalle/removeScrap/${ordendeproduccionscrap.pk}"
                                                                                                            data-toggle="tooltip" data-placement="top" title="Eliminar"></a>                                                                                                            
                                                                                                    </c:if>                                                                                                                                                                
                                                                                                </td>
                                                                                            </tr>
                                                                                        </c:forEach>
                                                                                    </tbody>
                                                                                </table>
                                                                            </div>
                                                                        </div>
                                                                    </div>     
                                                                </div>                                                                    
                                                            </div>
                                                        </div>                                                                    
                                                    </div>
                                                </div>
                                            </div>
                                        </div>                                                
                                    </div>
                                
                                </form:form>
                            </div>
                        </div>
                    </ol>
                </div>
                

            </div>
        </section>
    </div>
                  

<!-- ./wrapper -->

<script>
     // Escucha el evento "show.bs.modal" que se dispara cuando se muestra el modal
     
    
      
     $('#miModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var codigoOrdenProduccion = button.data('codigo');
        var mapaCodigos = button.data('lista'); // Ahora `mapaBultos` se recupera como un objeto en JavaScript
        console.log(mapaCodigos);
 
        // Elimina los caracteres '{' y '}' del principio y del final de la cadena
        var limpio = mapaCodigos.slice(1, -1);

        // Divide la cadena en pares clave-valor separados por coma y crea un array de estos pares
        var pares = limpio.split(", ");

        // Inicializa un objeto vacío para almacenar los resultados
        var nuevoObjeto = {};

        // Itera sobre los pares clave-valor y asigna las claves como IDs y los valores como códigos al nuevo objeto
        for (var i = 0; i < pares.length; i++) {
            var par = pares[i].split("=");
            var id = par[0];
            var codigo = par[1];
            nuevoObjeto[id] = codigo;
        }

        console.log(nuevoObjeto);
        var listaEnlacesHTML = "";
       
        // Itera sobre las propiedades del objeto y construye los elementos HTML
        for (var id in nuevoObjeto) {
            if (nuevoObjeto.hasOwnProperty(id)) {
                // Construye el enlace utilizando el ID y el código correspondiente
                var enlace = "<a href='/thyssenplastic/graficoBobina/" + id + "'><i class='fa fa-pie-chart'></i></a>";
                // Agrega el código y el enlace a la listaCodigosHTML
                listaEnlacesHTML += "<p>Código: " + nuevoObjeto[id] + " " + enlace + "</p>";
            }
        }

        
        $('#listCodigosOrdenProduccion').html(listaEnlacesHTML);
        $('#codigoOrdenProduccion').text(codigoOrdenProduccion);
        
    });
       
   
    $(document).ready(function () {
        
        $('#ordendeproduccionbobinasTable').DataTable({
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
            },           
            order: [[1, 'desc']]            
        });

        $('#ordendeproduccionbultosTable').DataTable({
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
            },           
            order: [[1, 'desc']]            
        });

        $('#ordendeproduccionpalletsTable').DataTable({
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
            },           
            order: [[1, 'desc']]            
        });

        $('#ordendeproduccionscrapsTable').DataTable({
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
            },           
            order: [[1, 'desc']]            
        });

        var operacion = $('#operacion').val();
        var tipoDetalle = $('#tipoDetalle').val();
        
        if(operacion == 'edit' && tipoDetalle == 'bobina') {
            var pesoNetoBobina = $('#pesoNetoBobina').val();
            $('#pesoNetoBobinaCalculated').val(pesoNetoBobina); 
        }
        
        $('#myTab a').on('click', function (e) {
            
            var href = $(this).attr('href');

            if(href == '#bobinas'){
                $('#tipoDetalle').val('bobina');
            }
            if(href == '#bultos'){
                $('#tipoDetalle').val('bulto');
            }
            if(href == '#pallets'){
                $('#tipoDetalle').val('pallet');
            }
            if(href == '#scraps'){
                $('#tipoDetalle').val('scrap');
            }
            
        });
        
        if(operacion == 'edit' && tipoDetalle == 'bulto') {            
            $('#myTab a[href="#bultos"]').tab('show');
            $('#bultos').tab('show');
        }
        
        /*
        var action = $('#action').val();
        if(action = 'edit') {
            loadArticulos();
        }
        */
        var imprimir = $('#imprimir').val();        
        if(imprimir == 'true') {
            var imprimirTipo = $('#imprimirTipo').val();
            var imprimirPk = $('#imprimirPk').val();
            if(imprimirTipo != '' && imprimirTipo != null && imprimirTipo != undefined && imprimirPk != '' && imprimirPk != null && imprimirPk != undefined) {                                
                if(imprimirTipo == 'bobina') {
                    printEtiquetaBobina(imprimirPk);
                }
                if(imprimirTipo == 'bulto') {
                    printEtiquetaBulto(imprimirPk);
                }
                if(imprimirTipo == 'pallet') {
                    printEtiquetaPallet(imprimirPk);
                }                
                if(imprimirTipo == 'scrap') {
                    printEtiquetaScrap(imprimirPk);
                }                                
            }
        }
        
    });
    
    function callBobinaSave() {
        var tipo = $("#tipoDetalle").val('bobina');

        var pesoConoBobina = $( "#pesoConoBobina" ).val();
        var pesoTotalBobina = $( "#pesoTotalBobina" ).val();
                
        if(pesoConoBobina == '') {
            const input = document.getElementById("pesoConoBobina");
            input.setCustomValidity('Complete este campo');
            $("#myForm")[0].reportValidity();            
        } 

        if(pesoTotalBobina == '') {
            const input2 = document.getElementById("pesoTotalBobina");
            input2.setCustomValidity('Complete este campo');
            $("#myForm")[0].reportValidity();            
        } 
        
        var action = $( "#action" ).val();

        if(action == 'remove') {
            if(confirm('Desea eliminarlo')) {
                var form = document.getElementById("myForm");
                form.submit();
            }
        } else {                        
            if($("#myForm")[0].checkValidity()) {
                var continueSubmit = true;
                var estadoBobina = $("#estadoBobina").val();
                if(estadoBobina == '' || estadoBobina == '-1') {
                    continueSubmit = false;
                    if(confirm('No ha ingresado Estado (Calidad). Desea continuar?')) {
                        continueSubmit = true;
                    }
                }
                if(continueSubmit) {
                    var form = document.getElementById("myForm");
                    form.submit();
                }
            } else {
                $("#myForm")[0].reportValidity();
            }                                    
        }
                
    }

    function calculatePesoNetoBobina() {
        
        var pesoConoBobina = $("#pesoConoBobina").val();
        var pesoTotalBobina = $("#pesoTotalBobina").val();
        
        try {
            if(!isNaN(pesoConoBobina) && parseFloat(pesoConoBobina) > 0 && !isNaN(pesoTotalBobina) && parseFloat(pesoTotalBobina) > 0) {
                var value = parseFloat(pesoTotalBobina) - parseFloat(pesoConoBobina);
                $("#pesoNetoBobina").val(value);
                $("#pesoNetoBobinaCalculated").val(value);                
            }
        } catch(error) {
            console.error(error);
        }

    }
       
    function printEtiquetaBobina(ordendeproduccionbobinapk) {        
        window.open("/thyssenplastic/ordenDeProduccionDetalle/print/bobina/"+ordendeproduccionbobinapk, "Imprimir Etiqueta Bobina", "popup,width=1280,height=800");
    }

    function callBultoSave() {
        var tipo = $("#tipoDetalle").val('bulto');

        var idBobinaSelected = $( "#idBobinaSelected" ).val();

        if(idBobinaSelected == '-1') {
            alert('Debe seleccionar una bobina.')
        } else {
            
            var continueSubmit = true;
            var estadoBulto = $("#estadoBulto").val();
            if(estadoBulto == '' || estadoBulto == '-1') {
                continueSubmit = false;
                if(confirm('No ha ingresado Estado (Calidad). Desea continuar?')) {
                    continueSubmit = true;
                }
            }
            if(continueSubmit) {
                var form = document.getElementById("myForm");
                form.submit();
            }
	}        
        
    }
    
    function searchBobina() {
       
        var idOrdenProduccion = $("#idOrdenProduccion").val();
        var searchCodigoBobina = $("#searchCodigoBobina").val();
        var bobinaDisponibleSelected = $("#bobinasDisponibles option:selected").val();
        var code = '-1';
        var idBobina = '-1';
        
        if(searchCodigoBobina != '') {
            code = searchCodigoBobina;
        } else if (bobinaDisponibleSelected != '-1') {
            idBobina = bobinaDisponibleSelected;
        }
        
        
                
        var sUrl = '/thyssenplastic/ordenDeProduccionDetalle/setBobinaSelected/'+idOrdenProduccion+'/'+code+'/'+idBobina;
        $.ajax({
            async: true,
            type: 'GET',
            dataType : 'json',
            url: sUrl
        })
        .done( function (responseText) {            
            if(responseText.length > 0) {       
                
                var bobina = responseText[0];                
                if(bobina.error == 'OK') {                    
                    $("#bobinaSelectedLabel").val(bobina.codigo + ' (Peso Total: ' + bobina.pesoTotal + ' | Peso Neto: ' + bobina.pesoNeto + ')');
                    if(bobina.estado != null) {
                        $("#estadoBobinaSelectedLabel").val(bobina.estado);
                        if(bobina.estado == "OK") {
                            $("#estadoBobinaSelectedLabel").attr('style','color:green;font-weight: bold;');
                        }
                        if(bobina.estado == "Observado") {
                            $("#estadoBobinaSelectedLabel").attr('style','color:#89B00F;font-weight: bold;');
                        }
                        if(bobina.estado == "Rechazado") {
                            $("#estadoBobinaSelectedLabel").attr('style','color:red;font-weight: bold;');
                        }
                        if(bobina.estado == "Sin Mesurar") {
                            $("#estadoBobinaSelectedLabel").attr('style','color:orange;font-weight: bold;');
                        }
                        
                    } else {
                        $("#estadoBobinaSelectedLabel").val('');
                    }
                    $("#idBobinaSelected").val(bobina.pk);
                    $("#codigoBultoLabel").val('R'+bobina.pk);
                } else {

                    $("#bobinaSelectedLabel").val(bobina.errorMessage);
                    $("#estadoBobinaSelectedLabel").val('');
                    $("#idBobinaSelected").val('-1');
                    $("#codigoBultoLabel").val('');
                    $("#searchCodigoBobina").val('');
                    //alert('Bobina no encontrada');
                }
            } else {
                $("#bobinaSelectedLabel").val('No existe bobina seleccionada.');
                $("#idBobinaSelected").val('-1');
                $("#codigoBultoLabel").val('');
            }
        })
        .fail( function (jqXHR, status, error) {
            // Triggered if response status code is NOT 200 (OK)
            alert(jqXHR.responseText);
            $("#bobinaSelectedLabel").val('No existe bobina seleccionada.');
            $("#idBobinaSelected").val('-1');
            $("#codigoBultoLabel").val('');
        })
        .always( function() {
            // Always run after .done() or .fail()
        });
        
    }
    
    function printEtiquetaBulto(ordendeproduccionbultopk) {
        window.open("/thyssenplastic/ordenDeProduccionDetalle/print/bulto/"+ordendeproduccionbultopk, "Imprimir Etiqueta Bulto", "popup,width=1280,height=800");
    }

    function callPalletSave() {
        var tipo = $("#tipoDetalle").val('pallet');
        var sizeBultosSelected = $('#bultosSelected option').length;
                
        if(sizeBultosSelected > 0) {
            
            $('#bultosSelected option').each(function () {
                $(this).attr('selected', true);
            });

            var continueSubmit = true;
            var estadoPallet = $("#estadoPallet").val();
            if(estadoPallet == '' || estadoPallet == '-1') {
                continueSubmit = false;
                if(confirm('No ha ingresado Estado (Calidad). Desea continuar?')) {
                    continueSubmit = true;
                }
            }
            if(continueSubmit) {
                var form = document.getElementById("myForm");
                form.submit();
            }

        
        } else {
            alert('Debe agregar al menos un bulto a la lista.')
        }
        
    }
    
     var statusActValue = "${statusAct}";
    
    function searchBulto() {
       
        var idOrdenProduccion = $("#idOrdenProduccion").val();
        var searchCodigoBulto = $("#searchCodigoBulto").val();
        var bultoDisponibleSelected = $("#bultosDisponibles option:selected").val();
        var code = '-1';
        var idBulto = '-1';
        
        if(searchCodigoBulto != '') {
            code = searchCodigoBulto;
        } else if (bultoDisponibleSelected != '-1') {
            idBulto = bultoDisponibleSelected;
        }
                
        var sUrl = '/thyssenplastic/ordenDeProduccionDetalle/setBultoSelected/'+idOrdenProduccion+'/'+code+'/'+idBulto;
        $.ajax({
            async: true,
            type: 'GET',
            dataType : 'json',
            url: sUrl
        })
        .done( function (responseText) {            
            if(responseText.length > 0) {       
                
                var bulto = responseText[0];                
                if(bulto.error == 'OK') {      
                    
                    var existeItem = false;
                    $("#bultosSelected option").each(function()
                    {                        
                        if($(this).val() == bulto.pk) {
                            existeItem = true;
                        }
                    });                    
                    
                    if(!existeItem) {
                        $("#bultosSelected").append('<option value='+bulto.pk+'>' + bulto.codigo + ' ('+ bulto.estado + ')</option>');    
                    } 
                    else {
                        alert('El bulto ya ha sido agregado a la lista.');
                    }
                    /*
                    $("#bultoSelectedLabel").val(bulto.codigo + ' (Peso Total ' + bulto.pesoTotal + ')');
                    if(bulto.estado != null) {
                        $("#estadoBultoSelectedLabel").val(bulto.estado);
                        if(bulto.estado == "OK") {
                            $("#estadoBultoSelectedLabel").attr('style','color:green;font-weight: bold;');
                        }
                        if(bulto.estado == "Observado") {
                            $("#estadoBultoSelectedLabel").attr('style','color:#89B00F;font-weight: bold;');
                        }
                        if(bulto.estado == "Rechazado") {
                            $("#estadoBultoSelectedLabel").attr('style','color:red;font-weight: bold;');
                        }
                        if(bulto.estado == "Sin Mesurar") {
                            $("#estadoBultoSelectedLabel").attr('style','color:orange;font-weight: bold;');
                        }
                        
                    } else {
                        $("#estadoBultoSelectedLabel").val('');
                    }
                    $("#idBultoSelected").val(bulto.pk);
                    $("#codigoBultoLabel").val('R'+bulto.pk);                 
                     */
                } else {
                    alert(bulto.errorMessage);
                    /*
                    $("#bultoSelectedLabel").val(bulto.errorMessage);
                    $("#estadoBultoSelectedLabel").val('');
                    $("#idBultoSelected").val('-1');
                    $("#codigoBultoLabel").val('');                                     
                     */
                }
            } else {
                $("#bultoSelectedLabel").val('No existe bulto seleccionado.');
                $("#idBultoSelected").val('-1');
                $("#codigoBultoLabel").val('');
               
            }
            document.getElementById('searchCodigoBulto').value = '';
        })
        .fail( function (jqXHR, status, error) {
            // Triggered if response status code is NOT 200 (OK)
            alert(jqXHR.responseText);
            $("#bultoSelectedLabel").val('No existe bulto seleccionado.');
            $("#idBultoSelected").val('-1');
            $("#codigoBultoLabel").val('');
        })
        .always( function() {
            // Always run after .done() or .fail()
        });
        
    }
    
    function eliminarBulto() {
        var bultoSelected = $("#bultosSelected option:selected").val();
       
        $("#bultosSelected option[value='"+bultoSelected+"']").remove();
        
        var valoresRestantes = $("#bultosSelected option").map(function() {
            return $(this).val();
        }).get();
        
        $("#bultosSelected").val(valoresRestantes);
    }    
    
    function printEtiquetaPallet(ordendeproduccionpalletpk) {
        window.open("/thyssenplastic/ordenDeProduccionDetalle/print/pallet/"+ordendeproduccionpalletpk, "Imprimir Etiqueta Pallet", "popup,width=1280,height=800");
    }
        
    
    function callScrapSave() {
        var tipo = $("#tipoDetalle").val('scrap');

        var pesoTotalScrap = $( "#pesoTotalScrap" ).val();
                
        if(pesoTotalScrap == '') {
            const input = document.getElementById("pesoTotalScrap");
            input.setCustomValidity('Complete este campo');
            $("#myForm")[0].reportValidity();            
        } 
        
        var action = $( "#action" ).val();

        if(action == 'remove') {
            if(confirm('Desea eliminarlo')) {
                var form = document.getElementById("myForm");
                form.submit();
            }
        } else {                        
            if($("#myForm")[0].checkValidity()) {
                var form = document.getElementById("myForm");
                form.submit();
            } else {
                $("#myForm")[0].reportValidity();
            }                                    
        }
                
    }
    
    
   
    
    function searchBultoAutomatically() {
       
        searchBulto();

        document.getElementById('searchCodigoBulto').value = '';
    }
    
   
   function searchBobinaAutomatically2() {
       
       if (document.getElementById("searchCodigoBobina").value != ''){
           console.log("entro");
        updateCodigoBobinaResultado();
        
        searchBobina();
       }
  }
    
    function searchBobinaAutomatically() {
    
    mostrarCargando();

    
    setTimeout(function() {
        
        ocultarCargando();

        
        searchBobinaAutomatically2();
    }, 3000);
}
    
    function updateCodigoBobinaResultado() {
        
        var codigoBobina = document.getElementById("searchCodigoBobina").value;

        
        document.getElementById("codigoBobinaResultado").textContent = "" + codigoBobina;
    }
    
    function mostrarCargando() {
    
        document.getElementById('cargando').style.display = 'block';
    }

    function ocultarCargando() {

        document.getElementById('cargando').style.display = 'none';
    }
    
    function mostrarCargandoListado() {
    
        document.getElementById('cargandoPallet').style.display = 'block';
    }
    
    function ocultarCargandoPallet() {
        event.preventDefault();
        document.getElementById('cargandoPallet').style.display = 'none';
    }
    
    
    function printEtiquetaScrap(ordendeproduccionscrappk) {
        window.open("/thyssenplastic/ordenDeProduccionDetalle/print/scrap/"+ordendeproduccionscrappk, "Imprimir Etiqueta Scrap", "popup,width=1280,height=800");
    }
    
    function simularFocus(event) {
        event.preventDefault();
        document.getElementById('searchCodigoBobina').focus()
    }
    
    function simularFocusPallet(event) {
        event.preventDefault();
        document.getElementById('searchCodigoBulto').focus();
        mostrarCargandoListado();
    }
    
</script>
<style>
    .spinner-container {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        
        border-radius: 20px;
        display: none; /* Por defecto oculto */
      }

      .spinner-container.active {
        display: flex; /* Muestra el contenedor cuando está activo */
        justify-content: center;
        align-items: center;
      }

      .spinner {
        width: 40px;
        height: 40px;
        border-radius: 50%;
        border: 4px solid rgba(0, 0, 0, 0.1);
        border-top-color: #8bc34a; /* Cambia el color a verde claro */
        animation: spin 1s linear infinite;
      }

      .loading-text {
        margin-left: 10px; /* Espacio entre el spinner y el texto */
      }

      .styled-input {
        border: 1px solid #ccc;
        padding: 5px 10px;
        display: inline-block;
        min-width: 150px;
        cursor: text;
        height: 35px;
        
      }

      .styled-input:focus {
        outline: none;
        border-color: #007bff;
      }

      @keyframes spin {
        to {
          transform: rotate(360deg);
        }
      }
      
    .campo-oculto {
        opacity: 0;
        position: absolute;
        left: -9999px; /* Mueve el elemento fuera de la pantalla */
    }
</style>
    
<%@include file = "/WEB-INF/views/jsp/includes/footer.jsp" %>




