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
                                    <li class="breadcrumb-item"><a href="">Orden De Compra Item</a></li>
                                    <li class="breadcrumb-item active">${titleOrdenDeCompraItem}</li>
                                </ol>
                            </div>

                            <div class="card-body">
                                <form:form class="form-horizontal" method="post"
                                        action="/thyssenplastic/ordenDeCompraItem/addOrEditOrRemove"
                                        modelAttribute="ordenDeCompraItemForm" id="myForm">

                                    <div class="container">


                                        <c:set var = "disabledAlta" value = "true"/>
                                        <c:set var = "disabledRecepcion" value = "true"/>                                        
                                        <c:if test = "${ordenDeCompraStatus == 'Nuevo'}">
                                            <c:if test = "${operacion == 'alta'}">
                                                <c:set var = "disabledAlta" value = "false"/>
                                                <c:set var = "disabledRecepcion" value = "true"/>                                            
                                            </c:if>
                                            <c:if test = "${operacion == 'view'}">
                                                <c:set var = "disabledAlta" value = "true"/>
                                                <c:set var = "disabledRecepcion" value = "true"/>                                            
                                            </c:if>
                                        </c:if>
                                        <c:if test = "${ordenDeCompraStatus == 'Abierto'}">
                                            <c:if test = "${operacion == 'recepcion'}">
                                                <c:set var = "disabledAlta" value = "true"/>
                                                <c:set var = "disabledRecepcion" value = "false"/>                                            
                                            </c:if>
                                        </c:if>
                                        
                                        ${action}
                                        
                                        <p></p>
                                        ${ordenDeCompraItemName}
                                        
                                        <p></p>

                                        <form:hidden path="pk" class="form-control"/>
                                        <form:hidden path="idOrdenDeCompra" class="form-control"/>
                                        <form:hidden path="action" class="form-control"/>
                                        <form:hidden path="operacion" class="form-control"/>

                                        <div class="tab-content">

                                            <div class="form-row row">                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <a href="/thyssenplastic/ordenDeCompra/" id="ordcomp">Atrás</a>
                                                </div>                                                                                                    
                                            </div>
                                            
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
                                                <div class="row col-xs-9 col-sm-1 col-xl-1" style="display:${displayButtonCambiarEstadoAbierto}">
                                                    <button type="button" class="btn btn-primary" onclick="window.location.href='/thyssenplastic/ordenDeCompraItem/setStatusOpenOrdenCompra/${idOrdenDeCompra}'">Cambiar Estado Abierto</button>                                                                                                                                                    
                                                </div>
                                            </div>
                                                
                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-1 col-xl-1" style="display:${displayButtonCambiarEstadoCompletado}">
                                                    <button type="button" class="btn btn-primary" onclick="window.location.href='/thyssenplastic/ordenDeCompraItem/setStatusCompletedOrdenCompra/${idOrdenDeCompra}'">Cambiar Estado Completado</button>                                                                                                                                                    
                                                </div>
                                            </div>
                                                    
                                        </div>                                        
                                        
                                        <p></p>
                                        <hr>
                                        <p></p>

                                        <div class="tab-content">
                                                                                    
                                            <div class="form-row row">                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Item de Orden de Compra</label>
                                                </div>                                                                                                    
                                            </div>
                                                
                                            <p>
                                                
                                            <div class="form-row row">                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Fecha Alta</label>
                                                    <form:input type="date" path="fechaAlta" class="form-control" placeholder=""
                                                                id="holderDateOfBirth" required="required" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                                </div>                                                    
                                            </div>
                                            
                                            <div class="form-row row">                       

                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputRubro">Tipo</label>
                                                    <form:select path="tipo" class="form-control rubro" required="required" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')" onchange="hideDisplayDivs()">
                                                        <form:option value="materiaPrima">Materia Prima</form:option>
                                                        <form:option value="insumo">Insumos</form:option>                                                        
                                                    </form:select>
                                                </div>
                                                                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4" style="display: block" id="materiaPrimaPanel">
                                                    <label for="inputArticulo">Materia Prima</label>                                                    
                                                    <form:select path="idMateriaPrima" class="form-control rubro" required="required" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')">                                                    
                                                        <form:option value="-1">Seleccionar...</form:option>
                                                        <form:options items="${materiaPrimaList}" />
                                                    </form:select>
                                                </div>
                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4" style="display: none" id="insumoPanel">
                                                    <label for="inputInsumo">Insumo</label>
                                                    <form:select path="idInsumo" class="form-control rubro" required="required" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')" onChange="loadStock('insumo')">
                                                        <form:option value="-1">Seleccionar...</form:option>
                                                        <form:options items="${insumoList}" />
                                                    </form:select>
                                                </div>
                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Cantidad</label>
                                                    <form:input type="number" path="cantidad" class="form-control" placeholder=""
                                                                id="holderDateOfBirth" required="required" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                                </div>                                                                                                    
                                            </div>
  
                                        </div>
                                                
                                        <p></p> 
                                        
                                        <div class="form-row row">
                                            <div class="row col-xs-9 col-sm-1 col-xl-1" style="display:${displayActionButton}">
                                                <button type="button" class="btn btn-primary" onclick="callController()">${buttonLabel}</button>                                            
                                            </div>
                                            <div class="row col-xs-9 col-sm-1 col-xl-1">
                                                <a href="/thyssenplastic/ordenDeCompraItem/${idOrdenDeCompra}"><button type="button" class="btn btn-secondary">Cancelar</button></a>
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
                            <table id="ordendecompraitemsTable" class="display table table-striped table-hover cell-border">
                                <thead>
                                    <tr>
                                        <th>NRO ITEM</th>
                                        <th>FECHA ALTA</th>
                                        <th>MATERIA PRIMA</th>
                                        <th>INSUMO</th>
                                        <th>TIPO</th>
                                        <th>STOCK</th>
                                        <th>CANTIDAD SOLICITADA</th>
                                        <th>CANTIDAD RECEPCIONADA</th>                                                                                
                                        <th style="text-align: center">ACCIONES</th>
                                    </tr>
                                </thead>

                                <tbody>

                                    <c:forEach items="${ordenDeCompraItems}" var="ordendecompraitem">
                                        <tr>
                                            <td>
                                                <c:out value="${ordendecompraitem.pk}" />
                                            </td>                                            
                                            <td>
                                                <c:out value="${ordendecompraitem.fechaAlta}" />
                                            </td>                                            
                                            <td>
                                                <c:out value="${ordendecompraitem.materiaPrima}" />
                                            </td>                                                                                        
                                            <td>
                                                <c:out value="${ordendecompraitem.insumo}" />
                                            </td>                                                                                                                                    
                                            <td>
                                                <c:out value="${ordendecompraitem.tipo}" />
                                            </td>                                                                                                                                                                                
                                            <td>
                                                <c:out value="${ordendecompraitem.stock}" />
                                            </td>                                                                                                                                                                                                                            
                                            <td>
                                                <c:out value="${ordendecompraitem.cantidadSolicitada}" />
                                            </td>
                                            <td>
                                                <c:out value="${ordendecompraitem.cantidadRecepcionada}" />
                                            </td>                                            
                                            <td>
                                                <c:if test = "${rol == 'oficina' && operacion == 'alta' && ordenDeCompraStatus == 'Nuevo'}">
                                                    <a class="nav-link active fa fa-pencil-square-o fa-lg"
                                                        href="/thyssenplastic/ordenDeCompraItem/edit/${ordendecompraitem.pk}"
                                                        data-toggle="tooltip" data-placement="top" title="Editar"></a>
                                                </c:if>                                                        
                                                <c:if test = "${rol == 'oficina' && operacion == 'alta' && ordenDeCompraStatus == 'Nuevo'}">
                                                    <a class="nav-link active fa fa-trash fa-lg"
                                                        href="/thyssenplastic/ordenDeCompraItem/remove/${ordendecompraitem.pk}"
                                                        data-toggle="tooltip" data-placement="top" title="Eliminar"></a>
                                                </c:if>                                                                                                        
                                                <a class="nav-link active fa fa-eye fa-lg"
                                                    href="/thyssenplastic/ordenDeCompraItem/view/${ordendecompraitem.pk}"
                                                    data-toggle="tooltip" data-placement="top" title="Ver"></a>
                                                <c:if test = "${rol == 'deposito' && operacion == 'recepcion' && ordenDeCompraStatus == 'Abierto'}">
                                                    <a class="nav-link active fa fa-truck fa-lg"
                                                        href="/thyssenplastic/ordenDeCompraItemRecepcion/${ordendecompraitem.pk}"
                                                        data-toggle="tooltip" data-placement="top" title="Recepción Item"></a>
                                                </c:if>                                                                                                                                                            
                                                <c:if test = "${rol == 'oficina' && operacion == 'alta' && (ordenDeCompraStatus == 'Completado' || ordenDeCompraStatus == 'Cerrado')}">
                                                    <a class="nav-link active fa fa-truck fa-lg"
                                                        href="/thyssenplastic/ordenDeCompraItemRecepcion/${ordendecompraitem.pk}"
                                                        data-toggle="tooltip" data-placement="top" title="Ver Recepción Item"></a>
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
        
        $('#ordendecompraitemsTable').DataTable({
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
            },           
            order: [[1, 'desc']]            
        });

        var tipo = $("#tipo").val();
        if(tipo == 'insumo') {
           document.getElementById("insumoPanel").style.display="block"; 
           document.getElementById("materiaPrimaPanel").style.display="none"; 
        }        
        if(tipo == 'materiaPrima') {
           document.getElementById("insumoPanel").style.display="none"; 
           document.getElementById("materiaPrimaPanel").style.display="block"; 
        }        
        
    });
           
    function hideDisplayDivs() {
        var tipo = $("#tipo").val();
        if(tipo == 'insumo') {           
           document.getElementById("insumoPanel").style.display="block"; 
           document.getElementById("materiaPrimaPanel").style.display="none"; 
        }        
        if(tipo == 'materiaPrima') {          
           document.getElementById("insumoPanel").style.display="none"; 
           document.getElementById("materiaPrimaPanel").style.display="block"; 
        }        
        $("#idMateriaPrima").val("-1");        
        $("#idInsumo").val("-1");
    }
    
    function callController() {
        
        var tipo = $("#tipo").val();
        var idMateriaPrima = $("#idMateriaPrima").val();
        var idInsumo = $("#idInsumo").val();
        if(tipo == 'materiaPrima' && idMateriaPrima == '-1') {
            alert('Debe seleccinar una Materia Prima');
            return;
        }
        if(tipo == 'insumo' && idInsumo == '-1') {
            alert('Debe seleccinar un Insumo');
            return;
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
    
</script>

<%@include file = "/WEB-INF/views/jsp/includes/footer.jsp" %>




