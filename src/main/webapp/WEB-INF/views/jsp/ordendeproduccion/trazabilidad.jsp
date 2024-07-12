<%@include file = "/WEB-INF/views/jsp/includes/header.jsp" %>

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
                                    <li class="breadcrumb-item"><a href="">Trazabilidad</a></li>
                                    <li class="breadcrumb-item active">${titleTrazabilidad}</li>
                                </ol>
                            </div>

                            <div class="card-body">
                                <form:form class="form-horizontal" method="post"
                                        action="/thyssenplastic/trazabilidad/search"
                                        modelAttribute="trazabilidadForm" id="myForm">

                                    <div class="container">
                                                                                
                                        <p></p>
                                        
                                        <div class="tab-content">
                                        
                                            
                                            <c:if test = "${displayError == 'true'}">
                                                <div class="form-row row">
                                                    <div class="row col-xs-12 col-sm-12 col-xl-12 alert alert-danger" role="alert">
                                                        ${errorMessage}
                                                    </div>
                                                </div>
                                            </c:if>
                                            <div class="form-row row">
                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Ingrese Código</label>
                                                    <form:input type="text" path="codigoSearch" class="form-control" placeholder=""
                                                                id="codigoSearch" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                                </div>                                                    
                                                                                            
                                                <div class="row col-xs-3 col-sm-3 col-xl-3" style="top: 20px">
                                                    <button type="submit" class="btn btn-primary">Buscar</button>                                            
                                                </div>
                                                
                                            </div>

                                            <p></p>    
                                            <hr>
                                            <p></p>    

                                            <div class="form-row row">
                                                <ol class="breadcrumb mb-1 mt-1">
                                                    <li class="breadcrumb-item"><a href="#">Orden De Producción</a></li>
                                                </ol>
                                            </div>

                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Código</label>
                                                    <form:input type="text" path="idOrdenDeProduccion" class="form-control" disabled="true"/>
                                                </div>                                                    
                                            </div>                                                                                                
                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Fecha Pedido</label>
                                                    <form:input type="text" path="fechaPedido" class="form-control" disabled="true"/>
                                                </div>                                                    
                                            </div>     
                                            <p>&nbsp;</p>   
                                            <div class="form-row row">
                                                <ol class="breadcrumb mb-1 mt-1">
                                                    <li class="breadcrumb-item">Trazabilidad Estados</li>
                                                </ol>
                                            </div>                                                
                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Fecha Alta</label>
                                                    <form:input type="text" path="fechaAltaImpresionOrdenDeProduccion" class="form-control" disabled="true"/>
                                                </div>                                                    
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Usuario Alta</label>
                                                    <form:input type="text" path="usuarioAltaOrdenDeProduccion" class="form-control" disabled="true"/>
                                                </div>                                                    
                                                
                                            </div>                                                    
                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Fecha Abierta</label>
                                                    <form:input type="text" path="fechaAbiertaOrdenDeProduccion" class="form-control" disabled="true"/>
                                                </div>                                                    
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Usuario Abierta</label>
                                                    <form:input type="text" path="usuarioAbiertaOrdenDeProduccion" class="form-control" disabled="true"/>
                                                </div>                                                    
                                                
                                            </div>                                                    
                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Fecha Fabricación</label>
                                                    <form:input type="text" path="fechaFabricacionOrdenDeProduccion" class="form-control" disabled="true"/>
                                                </div>                                                    
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Usuario Fabricación</label>
                                                    <form:input type="text" path="usuarioFabricacionOrdenDeProduccion" class="form-control" disabled="true"/>
                                                </div>                                                    
                                                
                                            </div>                                                    
                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Fecha Empaque</label>
                                                    <form:input type="text" path="fechaEmpaqueOrdenDeProduccion" class="form-control" disabled="true"/>
                                                </div>                                                    
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Usuario Empaque</label>
                                                    <form:input type="text" path="usuarioEmpaqueOrdenDeProduccion" class="form-control" disabled="true"/>
                                                </div>                                                    
                                                
                                            </div>                                                    
                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Fecha Pallet</label>
                                                    <form:input type="text" path="fechaPalletOrdenDeProduccion" class="form-control" disabled="true"/>
                                                </div>                                                    
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Usuario Pallet</label>
                                                    <form:input type="text" path="usuarioPalletOrdenDeProduccion" class="form-control" disabled="true"/>
                                                </div>                                                    
                                                
                                            </div>                                                    
                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Fecha Completado</label>
                                                    <form:input type="text" path="fechaCierreOrdenDeProduccion" class="form-control" disabled="true"/>
                                                </div>                                                    
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Usuario Completado</label>
                                                    <form:input type="text" path="usuarioCierreOrdenDeProduccion" class="form-control" disabled="true"/>
                                                </div>                                                    
                                                
                                            </div>                                                    
                                            <p>&nbsp;</p>   
                                            <div class="form-row row">
                                                <ol class="breadcrumb mb-1 mt-1">
                                                    <li class="breadcrumb-item">Detalle</li>
                                                </ol>
                                            </div>                                                
                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Código Cliente</label>
                                                    <form:input type="text" path="idCliente" class="form-control" disabled="true"/>
                                                </div>                                                    
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Cliente</label>
                                                    <form:input type="text" path="cliente" class="form-control" disabled="true"/>
                                                </div>                                                    
                                                
                                            </div>                                                    
                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Código Artículo</label>
                                                    <form:input type="text" path="idArticulo" class="form-control" disabled="true"/>
                                                </div>                                                    
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Artículo</label>
                                                    <form:input type="text" path="articulo" class="form-control" disabled="true"/>
                                                </div>                                                    
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Versión</label>
                                                    <form:input type="text" path="version" class="form-control" disabled="true"/>
                                                </div>                                                    
                                                
                                            </div>                                                    
                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Cantidad a Producir</label>
                                                    <form:input type="text" path="cantidadAProducir" class="form-control" disabled="true"/>
                                                </div>                                                    
                                            </div>   

                                            <p>&nbsp;</p>                                                   
                                            
                                            <c:if test = "${displayBobina == 'true'}">
                                                <div class="form-row row">
                                                    <ol class="breadcrumb mb-1 mt-1">
                                                        <li class="breadcrumb-item"><a href="#">Bobina</a></li>
                                                    </ol>
                                                </div>

                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Código Bobina</label>
                                                        <form:input type="text" path="codigoBobina" class="form-control" disabled="true"/>
                                                    </div>                                                    
                                                </div>                                                                                                

                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Fecha Alta</label>
                                                        <form:input type="text" path="fechaAltaBobina" class="form-control" disabled="true"/>
                                                    </div>                                                    
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Usuario Alta</label>
                                                        <form:input type="text" path="usuarioAltaBobina" class="form-control" disabled="true"/>
                                                    </div>                                                    

                                                </div>                                                    

                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Peso Cono</label>
                                                        <form:input type="text" path="pesoCono" class="form-control" disabled="true"/>
                                                    </div>                                                    
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Peso Total</label>
                                                        <form:input type="text" path="pesoTotal" class="form-control" disabled="true"/>
                                                    </div>                                                    
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Peso Neto</label>
                                                        <form:input type="text" path="pesoNeto" class="form-control" disabled="true"/>
                                                    </div>                                                    

                                                </div>                                                    
                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Estado Bobina</label>
                                                        <form:input type="text" path="estadoBobina" class="form-control" disabled="true"/>
                                                    </div>                                                    
                                                </div>                                                        
                                            </c:if>                      

                                            <p>&nbsp;</p>                                                   
                                            
                                            <c:if test = "${displayBulto == 'true'}">
                                                <div class="form-row row">
                                                    <ol class="breadcrumb mb-1 mt-1">
                                                        <li class="breadcrumb-item"><a href="#">Bulto</a></li>
                                                    </ol>
                                                </div>

                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Código Bulto</label>
                                                        <form:input type="text" path="codigoBulto" class="form-control" disabled="true"/>
                                                    </div>                                                    
                                                </div>                                                                                                

                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Fecha Alta</label>
                                                        <form:input type="text" path="fechaAltaBulto" class="form-control" disabled="true"/>
                                                    </div>                                                    
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Usuario Alta</label>
                                                        <form:input type="text" path="usuarioAltaBulto" class="form-control" disabled="true"/>
                                                    </div>                                                    

                                                </div>                                                    

                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Plegadora</label>
                                                        <form:input type="text" path="plegadora" class="form-control" disabled="true"/>
                                                    </div>                                                    

                                                </div>                                                    
                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Estado Bulto</label>
                                                        <form:input type="text" path="estadoBulto" class="form-control" disabled="true"/>
                                                    </div>                                                    
                                                </div>                                                        
                                            </c:if>           
                                            
                                            <p>&nbsp;</p>                                                   
                                            
                                            <c:if test = "${displayPallet == 'true'}">
                                                <div class="form-row row">
                                                    <ol class="breadcrumb mb-1 mt-1">
                                                        <li class="breadcrumb-item"><a href="#">Pallet</a></li>
                                                    </ol>
                                                </div>

                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Código Pallet</label>
                                                        <form:input type="text" path="codigoPallet" class="form-control" disabled="true"/>
                                                    </div>                                                    
                                                </div>                                                                                                

                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Fecha Alta</label>
                                                        <form:input type="text" path="fechaAltaPallet" class="form-control" disabled="true"/>
                                                    </div>                                                    
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Usuario Alta</label>
                                                        <form:input type="text" path="usuarioAltaPallet" class="form-control" disabled="true"/>
                                                    </div>                                                    

                                                </div>                                                    

                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Cantidad de Bultos</label>
                                                        <form:input type="text" path="cantidadBultos" class="form-control" disabled="true"/>
                                                    </div>                                                    
                                                    
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Lista Códigos de Bultos</label>
                                                        <form:input type="text" path="listaCodigoBultos" class="form-control" disabled="true"/>
                                                    </div>                                                    

                                                </div>                                                    
                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Estado Pallet</label>
                                                        <form:input type="text" path="estadoPallet" class="form-control" disabled="true"/>
                                                    </div>                                                    
                                                </div>                                                        
                                            </c:if>     
                                            
                                            <p>&nbsp;</p>                                                   
                                            
                                            <c:if test = "${displayRemito == 'true'}">
                                                <div class="form-row row">
                                                    <ol class="breadcrumb mb-1 mt-1">
                                                        <li class="breadcrumb-item"><a href="#">Remito</a></li>
                                                    </ol>
                                                </div>

                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Código Remito</label>
                                                        <form:input type="text" path="codigoRemito" class="form-control" disabled="true"/>
                                                    </div>                                                    
                                                </div>                                                                                                

                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Fecha Alta</label>
                                                        <form:input type="text" path="fechaAltaRemito" class="form-control" disabled="true"/>
                                                    </div>                                                    
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Usuario Alta</label>
                                                        <form:input type="text" path="usuarioAltaRemito" class="form-control" disabled="true"/>
                                                    </div>                                                    

                                                </div>                                                    

                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Tipo</label>
                                                        <form:input type="text" path="tipoRemito" class="form-control" disabled="true"/>
                                                    </div>                                                    
                                                    
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Transporte</label>
                                                        <form:input type="text" path="transporteRemito" class="form-control" disabled="true"/>
                                                    </div>                                                    

                                                </div>                                                    
                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Estado Remito</label>
                                                        <form:input type="text" path="estadoRemito" class="form-control" disabled="true"/>
                                                    </div>                                                    
                                                </div>                                                        
                                            </c:if>   
                                            
                                            <p>&nbsp;</p>                                                   
                                            
                                            <c:if test = "${displayHojaDeRuta == 'true'}">
                                                <div class="form-row row">
                                                    <ol class="breadcrumb mb-1 mt-1">
                                                        <li class="breadcrumb-item"><a href="#">Hoja De Ruta</a></li>
                                                    </ol>
                                                </div>

                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Código Hoja De Ruta</label>
                                                        <form:input type="text" path="codigoHojaDeRuta" class="form-control" disabled="true"/>
                                                    </div>                                                    
                                                </div>                                                                                                

                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Fecha Alta</label>
                                                        <form:input type="text" path="fechaAltaHojaDeRuta" class="form-control" disabled="true"/>
                                                    </div>                                                    
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Usuario Alta</label>
                                                        <form:input type="text" path="usuarioAltaHojaDeRuta" class="form-control" disabled="true"/>
                                                    </div>                                                    

                                                </div>                                                    
                                                <div class="form-row row">                                                    
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Fecha Carga</label>
                                                        <form:input type="text" path="fechaCarga" class="form-control" disabled="true"/>
                                                    </div>                                                    
                                                    <div class="row col-xs-3 col-sm-3 col-xl-3">
                                                        <label for="inputFecha">Fecha Salida</label>
                                                        <form:input type="text" path="fechaSalida" class="form-control" disabled="true"/>
                                                    </div>                                                    
                                                </div>                                                                                                        
                                                <div class="form-row row">                                                    
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Chofer</label>
                                                        <form:input type="text" path="chofer" class="form-control" disabled="true"/>
                                                    </div>                                                    

                                                </div>                                                    
                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Estado Hoja De Ruta</label>
                                                        <form:input type="text" path="estadoHojaDeRuta" class="form-control" disabled="true"/>
                                                    </div>                                                    
                                                </div>                                                        
                                            </c:if>   
                                            
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


<%@include file = "/WEB-INF/views/jsp/includes/footer.jsp" %>




