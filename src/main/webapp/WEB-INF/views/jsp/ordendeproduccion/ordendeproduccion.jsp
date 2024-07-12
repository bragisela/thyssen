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
                                    <li class="breadcrumb-item"><a href="">Orden De Producción</a></li>
                                    <li class="breadcrumb-item active">${titleOrdenDeProduccion}</li>
                                </ol>
                            </div>

                            <div class="card-body">
                                <form:form class="form-horizontal" method="post"
                                        action="/thyssenplastic/ordenDeProduccion/addOrEditOrRemove"
                                        modelAttribute="ordenDeProduccionForm" id="myForm">

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
                                            <c:if test = "${action == 'remove'}">                                                
                                                <c:set var = "disabledAlta" value = "true"/>
                                                <c:set var = "disabledCierre" value = "true"/>                                                      
                                                <c:set var = "disabledObservaciones" value = "true"/>
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
                                        ${ordenDeProduccionName}
                                        
                                        <p></p>

                                        <form:hidden path="pk" class="form-control"/>
                                        <form:hidden path="action" class="form-control"/>
                                        <form:hidden path="operacion" class="form-control"/>
                                        <form:hidden path="editObservaciones" class="form-control"/>                                        
                                        <form:hidden path="idClienteAux" class="form-control"/>
                                        <form:hidden path="idArticuloAux" class="form-control"/>
                                        <form:hidden path="idFichaTecnicaAux" class="form-control"/>

                                        <c:if test = "${rol == 'planta' && action == 'new'}">
                                            <div class="tab-content">

                                                <div class="form-row row">
                                                    No hay datos para visualizar en este rol.
                                                </div>                                                    
                                            </div>                                                    
                                        </c:if>
                                        <c:if test = "${rol != 'planta' || action != 'new'}">
                                            <div class="tab-content">

                                                <div class="form-row row">

                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Fecha Alta</label>
                                                        <form:input type="date" path="fechaAlta" class="form-control" placeholder=""
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
                                                        <label for="inputArticulo">Cliente</label>
                                                        <form:select path="idCliente" class="form-control rubro" disabled="${disabledAlta}" onChange="loadArticulos()">                                                    
                                                            <form:option value="-1">Seleccionar...</form:option>
                                                            <form:options items="${clienteList}" />
                                                        </form:select>
                                                    </div>
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4" >
                                                        <label for="inputArticulo">Articulo</label>
                                                        <form:select path="idArticulo" class="form-control rubro" required="required" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')" onChange="loadFichaTecnica()">                                                    
                                                            <form:option value="-1">Seleccionar...</form:option>
                                                            <form:options items="${articuloList}" />
                                                        </form:select>
                                                    </div>                                                
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4" >
                                                        <label for="inputArticulo">Ficha Técnica</label>
                                                        <form:select path="idFichaTecnica" class="form-control rubro" required="required" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')">                                                    
                                                            <form:option value="-1">Seleccionar...</form:option>
                                                        </form:select>
                                                    </div>

                                                </div>

                                                <div class="form-row row">

                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Fecha Pedido</label>
                                                        <form:input type="date" path="fechaPedido" class="form-control" placeholder=""
                                                                    id="holderDateOfBirth" required="required" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                                    </div>                                                    

                                                    <div class="row col-xs-9 col-sm-3 col-xl-4" >
                                                        <label for="inputArticulo">Unidad Venta</label>
                                                        <form:select path="idUnidadVenta" class="form-control rubro" required="required" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')">                                                    
                                                            <form:options items="${unidadVentaList}" />
                                                        </form:select>
                                                    </div>

                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label>Cantidad a Producir</label>
                                                        <form:input type="number" path="cantidadAProducir" class="form-control" placeholder="Cantidad..." disabled="${disabledAlta}"/>
                                                    </div>

                                                </div>

                                                <div class="form-row row">

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
                                                    <a href="/thyssenplastic/ordenDeProduccion"><button type="button" class="btn btn-secondary">Cancelar</button></a>
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

                <div class="card">

                    <div class="card-body">

                        <div class="row col-xs-12 col-sm-12 col-xl-12">
                            <table id="ordendeproduccionesTable" class="display table table-striped table-hover cell-border">
                                <thead>
                                    <tr>
                                        <th>ORDEN DE TRABAJO</th>
                                        <th>FECHA ALTA</th>
                                        <c:if test = "${rol == 'oficina'}">
                                        <th>CLIENTE</th>
                                        </c:if>
                                        <th>ARTICULO</th>
                                        <th>VERSION FICHA TECNICA</th>
                                        <th>CANTIDAD A PRODUCIR</th>
                                        <th>ESTADO</th>
                                        <th style="text-align: center">ACCIONES</th>
                                    </tr>
                                </thead>

                                <tbody>

                                    <c:forEach items="${ordenDeProducciones}" var="ordendeproduccion">
                                        <c:if test = "${(rol == 'oficina' || (rol == 'planta' && ordendeproduccion.estado != 'Completado')) || (rol == 'deposito' && ordendeproduccion.estado == 'Completado')}">
                                            <tr>
                                                <td>
                                                    <c:out value="${ordendeproduccion.pk}" />
                                                </td>                                            
                                                <td>
                                                    <c:out value="${ordendeproduccion.fechaAlta}" />
                                                </td>                                        
                                                <c:if test = "${rol == 'oficina'}">
                                                    <td>
                                                        <c:out value="${ordendeproduccion.cliente}" />
                                                    </td>                              
                                                </c:if>
                                                <td>
                                                    <c:out value="${ordendeproduccion.articulo}" />
                                                </td>                                                                                                                                    
                                                <td>
                                                    <c:out value="${ordendeproduccion.versionFichaTecnica}" />
                                                </td>                                                                                                                                                                                
                                                <td>
                                                    <c:out value="${ordendeproduccion.cantidadAProducir}" />
                                                </td>                                                                                                                                                                                                                            
                                                <td>
                                                    <c:out value="${ordendeproduccion.estado}" />
                                                </td>                                                                                                                                                                                
                                                <td>
                                                    <c:if test = "${rol == 'oficina' && operacion == 'alta' && ordendeproduccion.estado == 'Nuevo'}">
                                                        <a class="nav-link active fa fa-pencil-square-o fa-lg"
                                                            href="/thyssenplastic/ordenDeProduccion/edit/${ordendeproduccion.pk}"
                                                            data-toggle="tooltip" data-placement="top" title="Editar"></a>
                                                    </c:if>                                                        
                                                    <c:if test = "${rol == 'oficina' && operacion == 'alta' && ordendeproduccion.estado == 'Nuevo'}">
                                                        <a class="nav-link active fa fa-trash fa-lg"
                                                            href="/thyssenplastic/ordenDeProduccion/remove/${ordendeproduccion.pk}"
                                                            data-toggle="tooltip" data-placement="top" title="Eliminar"></a>
                                                    </c:if>                                                                                                        
                                                    <a class="nav-link active fa fa-eye fa-lg"
                                                        href="/thyssenplastic/ordenDeProduccion/view/${ordendeproduccion.pk}"
                                                        data-toggle="tooltip" data-placement="top" title="Ver"></a>
                                                    <c:if test = "${(rol == 'oficina' || rol == 'planta') && operacion == 'alta' && ordendeproduccion.estado == 'Nuevo'}">
                                                        <a class="nav-link active fa fa-play"
                                                            href="/thyssenplastic/ordenDeProduccion/setStatusOpenOrdenProduccion/${ordendeproduccion.pk}"
                                                            data-toggle="tooltip" data-placement="top" title="Cambiar a estado Abierto"></a>
                                                    </c:if>                                                                                 
                                                    <c:if test = "${(rol == 'oficina' || rol == 'planta' || rol == 'deposito')}">
                                                        <a class="nav-link active fa fa-navicon fa-lg"
                                                            href="/thyssenplastic/ordenDeProduccionDetalle/${ordendeproduccion.pk}"
                                                            data-toggle="tooltip" data-placement="top" title="Detalle"></a>
                                                    </c:if>                                                                                                                                                                                                                
                                                </td>
                                            </tr>
                                        </c:if>
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
        
        $('#ordendeproduccionesTable').DataTable({
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
            },           
            order: [[1, 'desc']]            
        });
                
        var action = $('#action').val();
        if(action == 'edit' || action == 'remove' || action == 'view') {
            loadArticulos();
        }
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
        
        var idCliente = $( "#idCliente option:selected" ).val();
        var idArticulo = $( "#idArticulo option:selected" ).val();
        var idFichaTecnica = $( "#idFichaTecnica option:selected" ).val();
        
        /*
        if(idCliente == '-1') {
            const input = document.getElementById("idCliente");
            input.setCustomValidity('Complete este campo');
            $("#myForm")[0].reportValidity();
        } 
        */
       
        if(idArticulo == '-1') {
            const input2 = document.getElementById("idArticulo");
            input2.setCustomValidity('Complete este campo');
            $("#myForm")[0].reportValidity();
        }                
        
        if(idFichaTecnica == '-1') {
            const input3 = document.getElementById("idFichaTecnica");
            input3.setCustomValidity('Complete este campo');
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
    
    function cerrarOrden(ordendeproduccionpk) {
        
        var opcion = confirm("Desea Cerrar la Orden?");
        if (opcion == true) {
            window.open("/thyssenplastic/ordenDeProduccion/setStatusCloseOrdenCompra/"+ordendeproduccionpk, "Cerrar Orden");   
	}         
    }
        
    function loadArticulos() {

        var idCliente = $( "#idCliente option:selected" ).val();            
        var action = $( "#action" ).val();            

        var sUrl = '/thyssenplastic/ordenDeProduccion/getArticulosByCliente/'+idCliente;
        $.ajax({
            async: true,
            type: 'GET',
            dataType : 'json',
            url: sUrl
        })
        .done( function (responseText) {
            $("#idArticulo").empty();
            $("#idArticulo").append( new Option("Seleccionar...","-1"));                                
            $("#idFichaTecnica").empty();
            $("#idFichaTecnica").append( new Option("Seleccionar...","-1"));                
            if(responseText.length > 0) {                    
                for (i = 0; i < responseText.length; i++) {
                    var articulo = responseText[i];
                    $("#idArticulo").append( new Option(articulo.nombre,articulo.id));
                }
                if(action = 'edit') {
                    var idArticulo = $('#idArticuloAux').val();    
                    $('#idArticulo option[value="'+idArticulo+'"]').attr("selected", true);
                    
                    loadFichaTecnica();
                }                
            }
        })
        .fail( function (jqXHR, status, error) {
            // Triggered if response status code is NOT 200 (OK)
            alert(jqXHR.responseText);
        })
        .always( function() {
            // Always run after .done() or .fail()
        });

    }

    function loadFichaTecnica() {

        var idArticulo = $( "#idArticulo option:selected" ).val();            

        var sUrl = '/thyssenplastic/ordenDeProduccion/getFichaTecnicaByArticulo/'+idArticulo;
        $.ajax({
            async: true,
            type: 'GET',
            dataType : 'json',
            url: sUrl
        })
        .done( function (responseText) {            
            $("#idFichaTecnica").empty();
            $("#idFichaTecnica").append( new Option("Seleccionar...","-1"));                                
            if(responseText.length > 0) {                    
                for (i = 0; i < responseText.length; i++) {
                    var fichaTecnica = responseText[i];
                    $("#idFichaTecnica").append( new Option(fichaTecnica.nombre,fichaTecnica.id));
                }

                if(action = 'edit') {
                    var idFichaTecnica = $('#idFichaTecnicaAux').val();    
                    $('#idFichaTecnica option[value="'+idFichaTecnica+'"]').attr("selected", true);
                }                            
            }
        })
        .fail( function (jqXHR, status, error) {
            // Triggered if response status code is NOT 200 (OK)
            alert(jqXHR.responseText);
        })
        .always( function() {
            // Always run after .done() or .fail()
        });

    }

</script>

<%@include file = "/WEB-INF/views/jsp/includes/footer.jsp" %>




