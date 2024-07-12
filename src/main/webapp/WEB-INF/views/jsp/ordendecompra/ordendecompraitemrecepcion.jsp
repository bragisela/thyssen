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
                                    <li class="breadcrumb-item"><a href="">Recepción de Item de Orden De Compra</a></li>
                                    <li class="breadcrumb-item active">${titleOrdenDeCompraItemRecepcion}</li>
                                </ol>
                            </div>

                            <div class="card-body">
                                <form:form class="form-horizontal" method="post"
                                        action="/thyssenplastic/ordenDeCompraItemRecepcion/addOrEditOrRemove"
                                        modelAttribute="ordenDeCompraItemRecepcionForm" id="myForm">

                                    <div class="container">


                                        <c:set var = "disabledAlta" value = "true"/>
                                        <c:set var = "disabledRecepcion" value = "true"/>                                        
                                        <c:if test = "${ordenDeCompraStatus == 'Abierto'}">
                                            <c:if test = "${operacion == 'recepcion'}">                                                
                                                <c:if test = "${itemCompletado == 'false'}">
                                                    <c:set var = "disabledAlta" value = "false"/>
                                                    <c:set var = "disabledRecepcion" value = "true"/>                                            
                                                </c:if>
                                            </c:if>
                                            <c:if test = "${operacion == 'view'}">
                                                <c:set var = "disabledAlta" value = "true"/>
                                                <c:set var = "disabledRecepcion" value = "true"/>                                            
                                            </c:if>
                                        </c:if>
                                        
                                        ${action}
                                        
                                        <p></p>
                                        ${ordenDeCompraItemRecepcionName}
                                        
                                        <p></p>

                                        <form:hidden path="pk" class="form-control"/>
                                        <form:hidden path="idOrdenDeCompra" class="form-control"/>
                                        <form:hidden path="idOrdenDeCompraItem" class="form-control"/>
                                        <form:hidden path="action" class="form-control"/>
                                        <form:hidden path="operacion" class="form-control"/>
                                        <form:hidden path="tipo" class="form-control"/>

                                        <div class="tab-content">

                                            <c:if test = "${viewQr == 'false'}">
                                            <div class="form-row row">                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <a href="/thyssenplastic/ordenDeCompraItem/${idOrdenDeCompra}" id="ordcomp">Atrás</a>
                                                </div>                                                                                                    
                                            </div>
                                            </c:if>
                                            
                                            <p>
                                                
                                            <div class="form-row row">                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Orden de Compra - ${idOrdenDeCompra}</label>
                                                </div>                                                                                                    
                                            </div>
                                                
                                            <p>
                                                
                                            <div class="form-row row">
                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Fecha Alta</label>
                                                    <form:input type="date" path="fechaAltaOrdenDeCompra" class="form-control" placeholder=""
                                                                id="holderDateOfBirth" required="required" disabled="true"/>
                                                </div>                                                    

                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Fecha Entrega</label>
                                                    <form:input type="date" path="fechaEntregaOrdenDeCompra" class="form-control" placeholder=""
                                                                id="holderDateOfBirth" required="required" disabled="true"/>
                                                </div>                                                    
                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Estado</label>
                                                    <form:input type="text" path="estado" class="form-control" placeholder=""
                                                                id="holderDateOfBirth" required="required" disabled="true"/>
                                                </div>

                                            </div>

                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-3 col-xl-4" >
                                                    <label for="inputArticulo">Proveedor</label>
                                                    <form:input type="text" path="proveedor" class="form-control" placeholder=""
                                                                id="holderDateOfBirth" required="required" disabled="true"/>                                                    
                                                </div>
                                            </div>

                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputRubro">Observaciones</label>
                                                    <form:textarea type="text" path="observaciones" class="form-control" disabled="true"/>
                                                </div>
                                            </div>
  
                                            <p></p>
                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-1 col-xl-1" style="display:${displayButtonCambiarEstadoCompletado}">
                                                    <button type="button" class="btn btn-primary" onclick="window.location.href='/thyssenplastic/ordenDeCompraItemRecepcion/setStatusCompletedOrdenCompra/${idOrdenDeCompra}/ordenDeCompraItem/${idOrdenDeCompraItem}'">Cambiar Estado Completado</button>                                                                                                                                                    
                                                </div>
                                            </div>
                                        
                                            <p></p>        
                                            <hr>
                                            <p></p>

                                            <div class="form-row row">                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Item de Orden de Compra - ${idOrdenDeCompraItem}</label>
                                                </div>                                                                                                    
                                            </div>
                                                
                                            <p>
                                                
                                            <div class="form-row row">                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Fecha Alta</label>
                                                    <form:input type="date" path="fechaAltaOrdenDeCompra" class="form-control" placeholder=""
                                                                id="holderDateOfBirth" required="required" disabled="true"/>
                                                </div>                                                    
                                            </div>
                                            
                                            <div class="form-row row">                                                
                                                <c:if test = "${tipo == 'materiaPrima'}">
                                                    <c:set var = "materiaPrimaVisible" value = "block"/>
                                                    <c:set var = "insumoVisible" value = "none"/>
                                                </c:if>
                                                <c:if test = "${tipo == 'insumo'}">
                                                    <c:set var = "materiaPrimaVisible" value = "none"/>
                                                    <c:set var = "insumoVisible" value = "block"/>                                                    
                                                </c:if>
                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4" style="display:${materiaPrimaVisible}">
                                                    <label for="inputArticulo">Materia Prima</label>
                                                    <form:input type="text" path="materiaPrima" class="form-control" placeholder=""
                                                                id="holderDateOfBirth" required="required" disabled="true" />                                                    
                                                </div>                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4"  style="display:${insumoVisible}">
                                                    <label for="inputArticulo">Insumo</label>
                                                    <form:input type="text" path="insumo" class="form-control" placeholder=""
                                                                id="holderDateOfBirth" required="required" disabled="true"/>                                                    
                                                </div>                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Cantidad Pedida</label>
                                                    <form:input type="number" path="cantidad" class="form-control" placeholder=""
                                                                id="holderDateOfBirth" required="required" disabled="true"/>
                                                </div>                                                                                                    

                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Stock Actual</label>
                                                    <form:input type="number" path="stock" class="form-control" placeholder=""
                                                                id="holderDateOfBirth" required="required" disabled="true"/>
                                                </div>                                                                                                    
                                                
                                                <c:if test = "${itemCompletado == 'true'}">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">                                                        
                                                        <label for="inputFecha" style="color: green" ><b>*** Item Completado ***</b></label>
                                                    </div>                                                                                                    
                                                </c:if>                                                
                                                
                                            </div>
                                                
                                        </div>                                        
                                        
                                        <p></p>
                                        <hr>
                                        <p></p>

                                        <div class="tab-content">
                                                                                    
                                            <div class="form-row row">                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Recepción de Item de Orden de Compra</label>
                                                </div>                                                                                                    
                                            </div>
                                                
                                            <p>
                                                
                                            <div class="form-row row">                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Fecha Recepción</label>
                                                    <form:input type="datetime-local" path="fechaRecepcion" class="form-control" placeholder=""
                                                                id="holderDateOfBirth" required="required" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                                </div>                                                    
                                            </div>
                                            
                                            <div class="form-row row">                                                
                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Cantidad Recepcionada</label>
                                                    <form:input type="number" path="cantidadRecepcionada" class="form-control" placeholder="" 
                                                                id="holderDateOfBirth" required="required" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                                </div>                                                                                                    
                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Lote</label>
                                                    <form:input type="text" path="lote" class="form-control" placeholder=""
                                                                id="holderDateOfBirth" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')" maxlength="45"/>
                                                </div>                                                                                                    

                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Referencia Adminsitrativa</label>
                                                    <form:input type="text" path="referenciaAdministrativa" class="form-control" placeholder=""
                                                                id="holderDateOfBirth" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')" maxlength="45"/>
                                                </div>                                                                                                    
                                                
                                            </div>
  
                                        </div>
                                                
                                        <p></p> 
                                        
                                        <c:if test = "${viewQr == 'false'}">
                                        <div class="form-row row">
                                            <div class="row col-xs-9 col-sm-1 col-xl-1" style="display:${displayActionButton}">
                                                <button type="button" class="btn btn-primary" onclick="callController()">${buttonLabel}</button>                                            
                                            </div>
                                            <div class="row col-xs-9 col-sm-1 col-xl-1">
                                                <a href="/thyssenplastic/ordenDeCompraItemRecepcion/${idOrdenDeCompraItem}"><button type="button" class="btn btn-secondary">Cancelar</button></a>
                                            </div>    
                                        </div>
                                        </c:if>
                                        
                                    </div>
                                
                                </form:form>
                            </div>
                        </div>
                    </ol>
                </div>
                

                <hr>

                <c:if test = "${viewQr == 'false'}">
                <div class="card">

                    <div class="card-body">

                        <div class="row col-xs-12 col-sm-12 col-xl-12">
                            <table id="ordendecompraitemsrecepcionTable" class="display table table-striped table-hover cell-border">
                                <thead>
                                    <tr>
                                        <th>NRO RECEPCION</th>
                                        <th>FECHA RECEPCION</th>
                                        <th>CANTIDAD RECEPCIONADA</th>
                                        <th>LOTE</th>                                        
                                        <th>REF. ADMINISTRATIVA</th>                 
                                        <th style="text-align: center">ACCIONES</th>
                                    </tr>
                                </thead>

                                <tbody>

                                    <c:forEach items="${ordenDeCompraItemsRecepcion}" var="ordendecompraitemrecepcion">
                                        <tr>
                                            <td>
                                                <c:out value="${ordendecompraitemrecepcion.pk}" />
                                            </td>                                            
                                            <td>
                                                <c:out value="${ordendecompraitemrecepcion.fechaRecepcion}" />
                                            </td>                                            
                                            <td>
                                                <c:out value="${ordendecompraitemrecepcion.cantidadRecepcionada}" />
                                            </td>                                                                                        
                                            <td>
                                                <c:out value="${ordendecompraitemrecepcion.lote}" />
                                            </td>                                                      
                                            <td>
                                                <c:out value="${ordendecompraitemrecepcion.referenciaAdministrativa}" />
                                            </td>                                                                                                  
                                            <td>
                                                <c:if test = "${rol == 'deposito'}">
                                                    <a class="nav-link active fa fa-eye fa-lg"
                                                        href="/thyssenplastic/ordenDeCompraItemRecepcion/view/${ordendecompraitemrecepcion.pk}"
                                                        data-toggle="tooltip" data-placement="top" title="Ver"></a>                                                        
                                                </c:if>                                                
                                                <a class="nav-link active fa fa-print fa-lg"
                                                   href="javascript:void(0);" onclick="printQR(${ordendecompraitemrecepcion.pk})"
                                                    data-toggle="tooltip" data-placement="top" title="Imprimir"></a>
                                                
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>                        
                    </div>
                </div>
                </c:if>
            </div>
        </section>
    </div>
                  

<!-- ./wrapper -->

<script>
    $(document).ready(function () {
        
        $('#ordendecompraitemsrecepcionTable').DataTable({
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
            },           
            order: [[1, 'desc']]            
        });
    
    });
    
    
    function callController() {
                                
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
   
    function printQR(ordendecompraitemrecepcionpk) {
        window.open("/thyssenplastic/ordenDeCompraItemRecepcion/print/"+ordendecompraitemrecepcionpk, "Imprimir Etiqueta Recepción", "popup,width=1380,height=800");
    }
</script>

<%@include file = "/WEB-INF/views/jsp/includes/footer.jsp" %>




