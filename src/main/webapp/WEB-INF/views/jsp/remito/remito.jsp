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
                                    <li class="breadcrumb-item"><a href="">Remito</a></li>
                                    <li class="breadcrumb-item active">${titleRemito}</li>
                                </ol>
                            </div>

                            <div class="card-body" style="display: ${displayActionButton == 'block' ? 'block' : 'none'};">
                                
                                <form:form class="form-horizontal" method="post"
                                        action="/thyssenplastic/remito/addOrEditOrRemove"
                                        modelAttribute="remitoForm" id="myForm">

                                    <div class="container">


                                        <c:set var = "disabledAlta" value = "true"/>
                                        <c:if test = "${operacion == 'alta'}">
                                            <c:set var = "disabledAlta" value = "false"/>
                                        </c:if>
                                        <c:if test = "${operacion == 'view'}">
                                            <c:set var = "disabledAlta" value = "true"/>
                                        </c:if>

                                        ${action}
                                        
                                        <p></p>
                                        ${remitoName}
                                        
                                        <p></p>

                                        <form:hidden path="pk" class="form-control"/>
                                        <form:hidden path="action" class="form-control"/>
                                        <form:hidden path="operacion" class="form-control"/>
                                        <form:hidden path="existingDomicilio" class="form-control"/>                                        
                                        <form:hidden path="existingContacto" class="form-control"/>  

                                        <div class="tab-content">
                                        
                                            <div class="form-row row">
                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4" >
                                                    <label for="inputArticulo">Tipo</label>
                                                    <form:select path="tipoRemito" class="form-control rubro" required="required" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')">
                                                        <form:option value="Salida">Salida</form:option>
                                                        <form:option value="Entrada">Entrada</form:option>
                                                        
                                                    </form:select>
                                                </div>                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Fecha Remito</label>
                                                    <form:input type="date" path="fechaRemito" class="form-control" placeholder=""
                                                                required="required" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                                </div>                                                    
                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Estado</label>
                                                    <form:input type="text" path="estado" class="form-control" disabled="true"/>                                                    
                                                </div>

                                            </div>

                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-3 col-xl-4" >
                                                    <label for="inputArticulo">Cliente</label>
                                                    <form:select path="idCliente" class="form-control rubro" required="required" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')" onChange="loadDomiclio()">                                                    
                                                        <form:options items="${clienteList}" />
                                                    </form:select>
                                                </div>
                                                <div class="row col-xs-9 col-sm-4 col-xl-4" >
                                                    <label for="inputArticulo">Domicilio</label>
                                                    <form:select path="idClienteDomicilio" class="form-control rubro" required="required" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')">                                                    
                                                        <form:option value="-1">Seleccionar...</form:option>
                                                    </form:select>
                                                </div>
                                              
                                                 
                                                                                                                                            
                                            </div>

                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-3 col-xl-4" >
                                                    <label for="inputArticulo">Transporte</label>
                                                    <form:select path="idTransporte" class="form-control rubro" required="required" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')" onChange="loadChofer()">   
                                                         <form:option value="-1">Seleccionar...</form:option>
                                                        <form:options items="${transporteList}" />
                                                    </form:select>
                                                </div>
                                                <div class="row col-xs-3 col-sm-3 col-xl-3">                                                        
                                                    <label for="inputRubro">Chofer</label>
                                                   
                                                    <form:select path="idChofer" class="form-control rubro" disabled="${disabledAlta}" >                                                        
                                                         <form:option value="-1">Seleccionar...</form:option>
                                                    </form:select>
                                                </div>  
                                                
                                            </div>
                                                
                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Referencia Administrativa</label>
                                                    <form:input type="text" path="referenciaAdministrativa" class="form-control" placeholder="" maxlength="100" disabled="${disabledAlta}"/>
                                                </div> 
                                                 <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Observaciones</label>
                                                    <form:input type="text" path="observaciones" class="form-control" placeholder="" maxlength="100" disabled="${disabledAlta}"/>
                                                </div> 
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputIsScrap">Es Scrap</label>
                                                    <form:select path="isScrap" class="form-control" disabled="${action == 'edit'}">
                                                        <form:option value="false" label="No" />
                                                        <form:option value="true" label="Sí" />
                                                    </form:select>
                                                </div>
                                                
                                          
                                            
                                                 
                                            </div>
                                                
                            
                                                
  
                                        </div>                                        
                                                                                                                                                                                               
                                                    
                                        <p></p>
                                                                                
                                        <div class="form-row row">
                                            <div class="row col-xs-9 col-sm-1 col-xl-1" style="display:${displayActionButton}">
                                                <button type="button" class="btn btn-primary" onclick="callController()">${buttonLabel}</button>                                            
                                            </div>
                                            <div class="row col-xs-9 col-sm-1 col-xl-1">
                                                <a href="/thyssenplastic/remito"><button type="button" class="btn btn-secondary">Cancelar</button></a>
                                            </div>    
                                        </div>
                                        
                                    </div>
                                
                                </form:form>
                            </div>
                        </div>
                    </ol>
                </div>
                

                <hr style="display: ${displayActionButton == 'block' ? 'block' : 'none'};">

                <div class="card">

                    <div class="card-body">

                        <div class="row col-xs-12 col-sm-12 col-xl-12">
                            <table id="remitosTable" class="display table table-striped table-hover cell-border">
                                <thead>
                                    <tr>
                                        <th>NRO REMITO</th>
                                        <th>FECHA</th>
                                        <th>TIPO</th>
                                        <th>CLIENTE</th>
                                        <th>REF ADMINISTRATIVA</th>
                                        <th>ESTADO</th>
                                        <th>ES SCRAP</th>
                                        <th style="text-align: center">ACCIONES</th>
                                    </tr>
                                </thead>

                                <tbody>

                                    <c:forEach items="${remitos}" var="remito">
                                        <tr>
                                            <td>
                                                <c:out value="${remito.pk}" />
                                            </td>                                            
                                            <td>
                                                <c:out value="${remito.fechaRemito}" />                                                
                                            </td>                                      
                                            <td>
                                                <c:out value="${remito.tipoRemito}" />                                                
                                            </td>                                                                                  
                                            <td>
                                                <c:out value="${remito.cliente}" />
                                            </td>                                                                                                                                    
                                            <td>
                                                <c:out value="${remito.referenciaAdministrativa}" />
                                            </td>                                                                                                                                    
                                            <td>
                                                <c:out value="${remito.estado}" />
                                            </td>                                                                                                                                                                                
                                            <td>
                                                <c:choose>
                                                    <c:when test="${remito.isScrap == true}">
                                                        Sí
                                                    </c:when>
                                                    <c:otherwise>
                                                        No
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <c:if test = "${rol == 'oficina' && operacion == 'alta' && remito.estado == 'Nuevo'}">
                                                    <a class="nav-link active fa fa-pencil-square-o fa-lg"
                                                        href="/thyssenplastic/remito/edit/${remito.pk}"
                                                        data-toggle="tooltip" data-placement="top" title="Editar"></a>
                                                </c:if>                                                        
                                                <c:if test = "${rol == 'oficina' && operacion == 'alta' && remito.estado == 'Nuevo' && remito.canDelete == 'true' && remito.canDeleteScrap == 'true'}">
                                                    <a class="nav-link active fa fa-trash fa-lg"
                                                        href="/thyssenplastic/remito/remove/${remito.pk}"
                                                        data-toggle="tooltip" data-placement="top" title="Eliminar"></a>
                                                </c:if>                                                                                                        
                                               
                                                <c:if test = "${(rol == 'oficina' || (rol == 'deposito' &&  remito.estado == 'Abierto')) && (operacion == 'alta' || operacion == 'recepcion')}">
                                                    
                                                        
                                                         <c:choose>
                                                    <c:when test="${remito.isScrap == true}">
                                                        <a class="nav-link active fa fa-navicon fa-lg"
                                                        href="/thyssenplastic/remitoDetalleScrap/${remito.pk}"
                                                        data-toggle="tooltip" data-placement="top" title="Items"></a>
                                                        
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a class="nav-link active fa fa-navicon fa-lg"
                                                        href="/thyssenplastic/remitoDetalle/${remito.pk}"
                                                        data-toggle="tooltip" data-placement="top" title="Items"></a>
                                                        
                                                    </c:otherwise>
                                                </c:choose>
                                                </c:if>                                                                                                                                                            
                                                <c:if test = "${rol == 'oficina' && operacion == 'alta' && remito.estado == 'Nuevo'}">
                                                    <a class="nav-link active fa fa-clock-o fa-lg"
                                                        href="/thyssenplastic/remito/setStatusOpenRemito/${remito.pk}"
                                                        data-toggle="tooltip" data-placement="top" title="Cambiar a estado Abierto"></a>
                                                </c:if>                                                                                                                                                                                                                
                                                
                                                                                                  
                                                <c:if test = "${(rol == 'deposito' || rol == 'oficina')}">
                                                    <c:choose>
                                                        <c:when test="${remito.isScrap}">
                                                            <a class="nav-link active fa fa-print fa-lg"
                                                               href="javascript:void(0);" onclick="printRemitoScrap(${remito.pk})"
                                                               data-toggle="tooltip" data-placement="top" title="Imprimir"></a>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <a class="nav-link active fa fa-print fa-lg"
                                                               href="javascript:void(0);" onclick="printRemito(${remito.pk})"
                                                               data-toggle="tooltip" data-placement="top" title="Imprimir"></a>
                                                        </c:otherwise>
                                                    </c:choose>

                                                </c:if>
                                                
                                                <c:if test = "${rol == 'oficina' && operacion == 'alta' && remito.estado == 'Abierto' && (remito.cantidadTotalBaja >= remito.cantidadTotal)}">
                                                    <a class="nav-link active fa fa-clock-o fa-lg"
                                                       onclick="cerrarRemito(${remito.pk})"
                                                        data-toggle="tooltip" data-placement="top" title="Cambiar a estado Cerrado"></a>
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
        
        $('#remitosTable').DataTable({
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
            },           
            order: [[0, 'desc']]            
        });
        
        loadChofer();
        loadDomiclio(); 
    });
    
    
    function callController() {
        
        var idClienteDomicilio = $("#idClienteDomicilio").val();
        if(idClienteDomicilio == '-1') {
            alert('Debe seleccinar un domicilio.');
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
            
    function loadDomiclio() {

        var idCliente = $( "#idCliente option:selected" ).val();            

        var sUrl = '/thyssenplastic/remito/getDomcilio/'+idCliente;
        $.ajax({
            async: true,
            type: 'GET',
            dataType : 'json',
            url: sUrl
        })
        .done( function (responseText) {
            $("#idClienteDomicilio").empty();
            if(responseText.length > 0) {                    
                for (i = 0; i < responseText.length; i++) {
                    var domicilio = responseText[i];
                    $("#idClienteDomicilio").append( new Option(domicilio.valor,domicilio.id));
                }
                
                var action = $( "#action" ).val();            
                var existingDomicilio = $( "#existingDomicilio" ).val();            
                if((action == 'edit' || action == 'view') && existingDomicilio != undefined && existingDomicilio != '-1') {
                    $('#idClienteDomicilio option[value="'+existingDomicilio+'"]').attr("selected", true);
                }
                
            } else {
                $("#idClienteDomicilio").append( new Option("Seleccionar...","-1"));
            }            
        })
        .fail( function (jqXHR, status, error) {
            // Triggered if response status code is NOT 200 (OK)
            alert(jqXHR.responseText);
        })
        .always( function() {
            // Always run after .done() or .fail()
        });

        loadContacto();
    }
    
    function loadChofer() {

        var idTransporte = $( "#idTransporte option:selected" ).val();
        

        if(idTransporte == '-1') {
            $("#idChofer").empty();
            $("#idChofer").append( new Option("Seleccionar...","-1"));
            console.log("entro")
            
            return;
        }
        
        var sUrl = '/thyssenplastic/remito/getChofer/'+idTransporte;
        $.ajax({
            async: true,
            type: 'GET',
            dataType : 'json',
            url: sUrl
        })
        .done( function (responseText) {
            $("#idChofer").empty();
            $("#idChofer").append(new Option("Seleccionar...", "-1"));
            if(responseText.length > 0) {                    
                for (i = 0; i < responseText.length; i++) {
                    var chofer = responseText[i];
                    $("#idChofer").append( new Option(chofer.nombre,chofer.id));
                }
                
                var action = $( "#action" ).val();            
                var idContacto = $( "#idContacto" ).val();            
                if((action == 'edit' || action == 'view' || action == 'remove') && idContacto != undefined && idContacto != '-1') {
                    $('#idChofer option[value="'+idContacto+'"]').attr("selected", true);
                }
                
            } 
            //else {
              //  $("#idChofer").append( new Option("No exiten choferes...","-1"));
            //}            
        })
        .fail( function (jqXHR, status, error) {
            // Triggered if response status code is NOT 200 (OK)
            alert(jqXHR.responseText);
        })
        .always( function() {
            // Always run after .done() or .fail()
        });
    
    }

    function loadContacto() {

        var idCliente = $( "#idCliente option:selected" ).val();            

        var sUrl = '/thyssenplastic/remito/getContacto/'+idCliente;
        $.ajax({
            async: true,
            type: 'GET',
            dataType : 'json',
            url: sUrl
        })
        .done( function (responseText) {
            $("#idContacto").empty();
            if(responseText.length > 0) {                    
                for (i = 0; i < responseText.length; i++) {
                    var contacto = responseText[i];
                    $("#idContacto").append( new Option(contacto.nombre,contacto.id));
                }
                
                var action = $( "#action" ).val();            
                var existingContacto = $( "#existingContacto" ).val();            
                if((action == 'edit' || action == 'view') && existingContacto != undefined && existingContacto != '-1') {
                    $('#idContacto option[value="'+existingContacto+'"]').attr("selected", true);
                }
                
            } else {
                $("#idContacto").append( new Option("Seleccionar...","-1"));
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
    
    function cerrarRemito(remitopk) {
        
        var opcion = confirm("Desea Cerrar el Remito?");
        if (opcion == true) {
            window.location.href = "/thyssenplastic/remito/setStatusCloseRemito/"+remitopk, "Cerrar Remito"; 
	}         
    }

    function printRemito(remitopk) {
        window.open("/thyssenplastic/remito/print/"+remitopk, "Imprimir Remito", "popup,width=1024,height=800");
    }
    
    function printRemitoScrap(remitopk) {
        window.open("/thyssenplastic/remito2/print/"+remitopk, "Imprimir Remito", "popup,width=1024,height=800");
    }


</script>

<%@include file = "/WEB-INF/views/jsp/includes/footer.jsp" %>




