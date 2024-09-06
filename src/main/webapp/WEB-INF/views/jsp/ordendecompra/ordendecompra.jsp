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
                                    <li class="breadcrumb-item"><a href="">Orden De Compra</a></li>
                                    <li class="breadcrumb-item active">${titleOrdenDeCompra}</li>
                                </ol>
                            </div>

                            <div class="card-body">
                                <form:form class="form-horizontal" method="post"
                                        action="/thyssenplastic/ordenDeCompra/addOrEditOrRemove"
                                        modelAttribute="ordenDeCompraForm" id="myForm">

                                    <div class="container">


                                        <c:set var = "disabledAlta" value = "true"/>
                                        <c:set var = "disabledCierre" value = "true"/>      
                                        <c:set var = "disabledObservaciones" value = "true"/>      
                                        <c:if test = "${operacion == 'alta'}">
                                            <c:set var = "disabledAlta" value = "false"/>
                                            <c:set var = "disabledObservaciones" value = "false"/>
                                            <c:set var = "disabledCierre" value = "true"/>                                            
                                            <c:if test = "${action == 'editObservaciones'}">                                                
                                                <c:set var = "disabledAlta" value = "true"/>
                                                <c:set var = "disabledCierre" value = "true"/>                                                      
                                                <c:set var = "disabledObservaciones" value = "false"/>
                                            </c:if>                                                                                                                                            
                                        </c:if>
                                        <c:if test = "${operacion == 'cierre'}">
                                            <c:set var = "disabledAlta" value = "true"/>
                                            <c:set var = "disabledObservaciones" value = "true"/>
                                            <c:if test = "${action == 'new'}">
                                                <c:set var = "disabledCierre" value = "true"/>
                                            </c:if>        
                                            <c:if test = "${action == 'edit'}">                                                
                                                <c:set var = "disabledCierre" value = "false"/>
                                            </c:if>                                                    
                                        </c:if>
                                        <c:if test = "${operacion == 'view'}">
                                            <c:set var = "disabledAlta" value = "true"/>
                                            <c:set var = "disabledCierre" value = "true"/>                                            
                                            <c:set var = "disabledObservaciones" value = "true"/>                                                  
                                        </c:if>
                                        <c:if test = "${operacion == 'completar'}">
                                            <c:set var = "disabledAlta" value = "true"/>
                                            <c:set var = "disabledCierre" value = "true"/>                                            
                                            <c:set var = "disabledObservaciones" value = "false"/>                                                  
                                        </c:if>

                                        ${action}
                                        
                                        <p></p>
                                        ${ordenDeCompraName}
                                        
                                        <p></p>

                                        <form:hidden path="pk" class="form-control"/>
                                        <form:hidden path="action" class="form-control"/>
                                        <form:hidden path="operacion" class="form-control"/>
                                        <form:hidden path="editObservaciones" class="form-control"/>                                        

                                        <div class="tab-content">
                                        
                                            <div class="form-row row">
                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Fecha Alta</label>
                                                    <form:input type="date" path="fechaAlta" class="form-control" placeholder=""
                                                                id="holderDateOfBirth" required="required" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                                </div>                                                    

                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Fecha Entrega</label>
                                                    <form:input type="date" path="fechaEntrega" class="form-control" placeholder=""
                                                                id="holderDateOfBirth" required="required" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                                </div>                                                    
                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Estado</label>
                                                    <form:select path="estado" class="form-control rubro" required="required" disabled="true" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')">                                                    
                                                        <form:option value="Nuevo">Nuevo</form:option>
                                                        <form:option value="Abierto">Abierto</form:option>
                                                        <form:option value="Completado">Completado</form:option>                                                        
                                                        <form:option value="Cerrado">Cerrado</form:option>                                                        
                                                    </form:select>
                                                </div>

                                            </div>

                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-3 col-xl-4" >
                                                    <label for="inputArticulo">Proveedor</label>
                                                    <form:select path="idProveedor" class="form-control rubro" required="required" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')">                                                    
                                                        <form:options items="${proveedorList}" />
                                                    </form:select>
                                                </div>
                                            </div>
                                                
                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Referencia Administrativa</label>
                                                    <form:input type="text" path="referenciaAdministrativa" class="form-control" placeholder="" maxlength="255" required="required" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                                </div>                                                                                                    
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputRubro">Observaciones</label>
                                                    <form:textarea type="text" path="observaciones" class="form-control" placeholder="" disabled="${disabledObservaciones}" maxlength="4000" />
                                                </div>
                                            </div>
  
                                        </div>                                        
                                                                                                                                                                                               
                                                    
                                        <p></p>
                                                                                
                                        <div class="form-row row">
                                            <div class="row col-xs-9 col-sm-1 col-xl-1" style="display:${displayActionButton}">
                                                <button type="button" class="btn btn-primary" onclick="callController()">${buttonLabel}</button>                                            
                                            </div>
                                            <div class="row col-xs-9 col-sm-1 col-xl-1">
                                                <a href="/thyssenplastic/ordenDeCompra"><button type="button" class="btn btn-secondary">Cancelar</button></a>
                                            </div>    
                                        </div>
                                        
                                    </div>
                                
                                </form:form>
                            </div>
                        </div>
                    </ol>
                </div>
                

                <hr>

                <div class="card">

                    <div class="card-body">

                        <div class="row col-xs-12 col-sm-12 col-xl-12">
                            <table id="ordendecomprasTable" class="display table table-striped table-hover cell-border">
                                <thead>
                                    <tr>
                                        <th>NRO ORDEN</th>
                                        <th>FECHA ALTA</th>
                                        <th>FECHA ENTREGA</th>
                                        <th>R. ADMIN.</th>
                                        <th>PROVEEDOR</th>
                                        <th>ESTADO</th>
                                        <th style="text-align: center">ACCIONES</th>
                                    </tr>
                                </thead>

                                <tbody>

                                    <c:forEach items="${ordenDeCompras}" var="ordendecompra">
                                        <tr>
                                            <td>
                                                <c:out value="${ordendecompra.pk}" />
                                            </td>                                            
                                            <td>
                                                <c:out value="${ordendecompra.fechaAlta}" />
                                            </td>                                            
                                            <td>
                                                <c:out value="${ordendecompra.fechaEntrega}" />
                                            </td>                                                                                        
                                            <td>
                                                <c:out value="${ordendecompra.referenciaAdministrativa}" />
                                            </td>                                                                                                                                    
                                            <td>
                                                <c:out value="${ordendecompra.proveedor}" />
                                            </td>                                                                                        
                                            <td>
                                                <c:out value="${ordendecompra.estado}" />
                                            </td>                                                                                                                                                                                
                                            <td>
                                                <c:if test = "${rol == 'oficina' && operacion == 'alta' && ordendecompra.estado == 'Nuevo'}">
                                                    <a class="nav-link active fa fa-pencil-square-o fa-lg"
                                                        href="/thyssenplastic/ordenDeCompra/edit/${ordendecompra.pk}"
                                                        data-toggle="tooltip" data-placement="top" title="Editar"></a>
                                                </c:if>                                                        
                                                <c:if test = "${rol == 'oficina' && operacion == 'alta' && ordendecompra.estado == 'Nuevo'}">
                                                    <a class="nav-link active fa fa-trash fa-lg"
                                                        href="/thyssenplastic/ordenDeCompra/remove/${ordendecompra.pk}"
                                                        data-toggle="tooltip" data-placement="top" title="Eliminar"></a>
                                                </c:if>                                                                                                        
                                                <a class="nav-link active fa fa-eye fa-lg"
                                                    href="/thyssenplastic/ordenDeCompra/view/${ordendecompra.pk}"
                                                    data-toggle="tooltip" data-placement="top" title="Ver"></a>
                                                <c:if test = "${(rol == 'oficina' || rol == 'deposito') && (operacion == 'alta' || operacion == 'recepcion')}">
                                                    <a class="nav-link active fa fa-navicon fa-lg"
                                                        href="/thyssenplastic/ordenDeCompraItem/${ordendecompra.pk}"
                                                        data-toggle="tooltip" data-placement="top" title="Items"></a>
                                                </c:if>                                                                                                                                                            
                                                <c:if test = "${rol == 'oficina' && operacion == 'alta' && ordendecompra.sePuedeCambiarEstadoAbierto == 'true'}">
                                                    <a class="nav-link active fa fa-clock-o fa-lg"
                                                        href="/thyssenplastic/ordenDeCompra/setStatusOpenOrdenCompra/${ordendecompra.pk}"
                                                        data-toggle="tooltip" data-placement="top" title="Cambiar a estado Abierto"></a>
                                                </c:if>                                                                                                                                                                                                                
                                                <c:if test = "${rol == 'oficina' && operacion == 'alta' && ordendecompra.estado == 'Completado' && (ordendecompra.superaCantidad == 'true' || ordendecompra.faltaCantidad == 'true')}">
                                                    <a class="nav-link active fa fa-exclamation-triangle fa-lg"
                                                        href="/thyssenplastic/ordenDeCompra/editObservaciones/${ordendecompra.pk}"
                                                        data-toggle="tooltip" data-placement="top" title="Editar Observaciones"></a>
                                                </c:if>                                                                                                                                                                                                                                                                    
                                                <c:if test = "${rol == 'oficina' && operacion == 'alta' && ordendecompra.estado == 'Completado'}">
                                                    <a class="nav-link active fa fa-clock-o fa-lg"
                                                       href="#" onclick="cerrarOrden(${ordendecompra.pk})"
                                                        data-toggle="tooltip" data-placement="top" title="Cambiar a estado Cerrado"></a>
                                                </c:if>
                                                <c:if test = "${rol == 'oficina' && operacion == 'alta' && ordendecompra.estado == 'Abierto'}">
                                                    <a class="nav-link active fa fa-wrench fa-lg"
                                                       href="/thyssenplastic/ordenDeCompra/completarOrden/${ordendecompra.pk}"
                                                        data-toggle="tooltip" data-placement="top" title="Cambiar a estado Completado"></a>
                                                </c:if>        
                                                <c:if test = "${rol == 'oficina' && operacion == 'alta' && ordendecompra.estado == 'Completado' && ordendecompra.superaCantidad == 'true'}">
                                                    <span style="font-size: 12px; color: red">(Posee items que supera la cantidad solicitada)</span>
                                                </c:if>    
                                                <c:if test = "${rol == 'oficina' && operacion == 'alta' && ordendecompra.estado == 'Completado' && ordendecompra.faltaCantidad == 'true'}">
                                                    <span style="font-size: 12px; color: green">(Posee items donde la cantidad recepcionada es menor a la cantidad solicitada)</span>
                                                </c:if>                                                        
                                                <c:if test = "${(rol == 'deposito' || rol == 'oficina')}">
                                                    <a class="nav-link active fa fa-print fa-lg"
                                                       href="javascript:void(0);" onclick="printOrdenDeCompra(${ordendecompra.pk})"
                                                        data-toggle="tooltip" data-placement="top" title="Imprimir"></a>
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
        </section>
    </div>
                  

<!-- ./wrapper -->

<script>
    $(document).ready(function () {
        
        $('#ordendecomprasTable').DataTable({
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
            },           
            order: [[0, 'desc']]            
        });
                
    });
    
    
    function callController() {
        
        /*
        var tipo = $("#tipo").val();
        var idMateriaPrima = $("#idMateriaPrima").val();
        var idInsumo = $("#idInsumo").val();
        var idArticulo = $("#idArticulo").val();
        if(tipo == 'materiaPrima' && idMateriaPrima == '-1') {
            alert('Debe seleccinar una Materia Prima');
            return;
        }
        if(tipo == 'insumos' && idInsumo == '-1') {
            alert('Debe seleccinar un Insumo');
            return;
        }        
        if(tipo == 'articulos' && idArticulo == '-1') {
            alert('Debe seleccinar un Articulo');
            return;
        }          
         */               

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
    
    function cerrarOrden(ordendecomprapk) {
        
        var opcion = confirm("Desea Cerrar la Orden?");
        if (opcion == true) {
            window.open("/thyssenplastic/ordenDeCompra/setStatusCloseOrdenCompra/"+ordendecomprapk, "Cerrar Orden");   
	}         
    }

    
     function printOrdenDeCompra(ordendecomprapk) {
         console.log("va a disparar la ventana");
        window.open("/thyssenplastic/ordenDeCompra/print/"+ordendecomprapk, "Imprimir Orden", "popup,width=1024,height=800");
    }
</script>

<%@include file = "/WEB-INF/views/jsp/includes/footer.jsp" %>




