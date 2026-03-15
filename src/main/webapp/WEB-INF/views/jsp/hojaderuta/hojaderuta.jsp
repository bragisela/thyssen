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
                                    <li class="breadcrumb-item"><a href="">Hoja de Ruta</a></li>
                                    <li class="breadcrumb-item active">${titleHojaDeRuta}</li>
                                </ol>
                            </div>

                            <div class="card-body">
                                <form:form class="form-horizontal" method="post"
                                        action="/thyssenplastic/hojaDeRuta/addOrEditOrRemove"
                                        modelAttribute="hojaDeRutaForm" id="myForm">

                                    <div class="container">


                                        <c:set var = "disabledAlta" value = "true"/>
                                        <c:set var = "disabledCierre" value = "true"/>
                                        <c:if test = "${operacion == 'alta'}">
                                            <c:set var = "disabledAlta" value = "false"/>
                                            <c:set var = "disabledCierre" value = "true"/>
                                        </c:if>
                                        <c:if test = "${operacion == 'view'}">
                                            <c:set var = "disabledAlta" value = "true"/>
                                            <c:set var = "disabledCierre" value = "true"/>
                                        </c:if>
                                        <c:if test = "${operacion == 'remove'}">
                                            <c:set var = "disabledAlta" value = "true"/>
                                            <c:set var = "disabledCierre" value = "true"/>
                                        </c:if>

                                        ${action}
                                        <p></p>
                                        ${hojaDeRutaName}
                                        
                                        <p></p>

                                        <form:hidden path="pk" class="form-control"/>
                                        <form:hidden path="action" class="form-control"/>
                                        <form:hidden path="operacion" class="form-control"/>
                                        <form:hidden path="idContacto" class="form-control"/>
                                        <form:hidden path="idProveedor" class="form-control"/>

                                        <div class="tab-content">
                                            <!--CONTENIDO ABIERTO-->
                                        
                                            <div class="form-row row">                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Fecha</label>
                                                    <form:input type="date" path="fecha" class="form-control" placeholder=""
                                                                id="holderDateOfBirth" required="required" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                                </div>                                                    
                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Estado</label>
                                                    <form:input type="text" path="estadoLabel" class="form-control" placeholder="" disabled="true"/>
                                                </div>
                                            </div>

                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Fecha Carga</label>
                                                    <form:input type="date" path="fechaCarga" class="form-control" placeholder=""
                                                                id="holderDateOfBirth" required="required" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                                </div>                                        
                                                <!--
                                                <div class="row col-xs-3 col-sm-3 col-xl-3">
                                                    <label for="inputFecha">Hora Carga</label>
                                                    <form:input type="time" path="horaCarga" class="form-control" placeholder=""
                                                                id="holderDateOfBirth" required="required" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                                </div>
                                                -->
                                            </div>                                                
                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Fecha Salida</label>
                                                    <form:input type="date" path="fechaSalida" class="form-control" placeholder=""
                                                                id="holderDateOfBirth" required="required" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                                </div>                                                    
                                                <!--
                                                <div class="row col-xs-3 col-sm-3 col-xl-3">
                                                    <label for="inputFecha">Hora Salida</label>
                                                    <form:input type="time" path="horaSalida" class="form-control" placeholder=""
                                                                id="holderDateOfBirth" required="required" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                                </div>                                                    
                                                -->
                                            </div>

                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">                                                        
                                                    <label for="inputRubro">Transporte</label>
                                                    <form:select path="idTransporte" class="form-control rubro" required="required" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')" onchange="hideDisplayDivs()" onChange="loadChofer()">                                                        
                                                        <form:options items="${transportesList}" />                                                        
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
                                                    <label for="inputRubro">Observaciones</label>
                                                    <form:textarea type="text" path="observaciones" class="form-control" placeholder="" disabled="${disabledAlta}" maxlength="4000" />
                                                </div>
                                            </div>
                                                
                                        </div>                                                    
                                                    
                                        <p></p>
                                                                                
                                        <div class="form-row row">
                                            <div class="row col-xs-9 col-sm-1 col-xl-1" style="display:${displayActionButton}">
                                                <button type="button" class="btn btn-primary" onclick="callController()">${buttonLabel}</button>                                            
                                            </div>
                                            <div class="row col-xs-9 col-sm-1 col-xl-1">
                                                <a href="/thyssenplastic/hojaDeRuta"><button type="button" class="btn btn-secondary">Cancelar</button></a>
                                            </div>    
                                        </div>
                                        
                                    </div>
                                
                                </form:form>
                            </div>
                        </div>
                </div>
                </ol>
                                
                                <!-- Modal Cancelar Hoja de Ruta -->
                <div class="modal fade" id="modalCancelarHojaDeRuta" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered" role="document" style="max-width: 400px;">
                        <div class="modal-content" style="border: none; border-radius: 10px; box-shadow: 0 8px 24px rgba(0,0,0,0.12); overflow: hidden;">
                            <div style="background: #fcebeb; border-bottom: 0.5px solid #f7c1c1; padding: 16px 20px; display: flex; align-items: center; gap: 10px;">
                                <i class="fa fa-ban" style="color: #a32d2d; font-size: 15px;"></i>
                                <span style="font-size: 14px; font-weight: 600; color: #a32d2d;">Cancelar Hoja de Ruta</span>
                            </div>
                            <div style="padding: 20px; background: white;">
                                <p style="font-size: 13px; color: #555; margin: 0 0 14px; line-height: 1.5;">
                                    La Hoja de Ruta pasará a estado <strong>Cancelada</strong>. Los remitos vinculados serán liberados automáticamente.
                                </p>
                                <div style="border-left: 3px solid #ef9f27; border-radius: 0; background: #faeeda; padding: 9px 12px; margin-bottom: 18px; font-size: 12px; color: #633806; line-height: 1.5;">
                                    <i class="fa fa-exclamation-triangle" style="margin-right: 6px; font-size: 12px;"></i>
                                    Los remitos vinculados quedarán disponibles para ser asignados a otra Hoja de Ruta.
                                </div>
                                <label style="font-size: 12px; font-weight: 600; color: #444; display: block; margin-bottom: 6px; text-transform: uppercase; letter-spacing: 0.4px;">
                                    Motivo <span style="color: #a32d2d;">*</span>
                                </label>
                                <textarea id="motivoCancelacionHdr" rows="3" maxlength="500" placeholder="Ingresá el motivo..."
                                    style="width: 100%; border: 0.5px solid #ccc; border-radius: 8px; padding: 9px 11px; font-size: 13px; color: #333; resize: none; outline: none; box-sizing: border-box; font-family: inherit; background: #fafafa;"
                                    onfocus="this.style.borderColor='#a32d2d'; this.style.background='#fff';"
                                    onblur="this.style.borderColor='#ccc'; this.style.background='#fafafa';"></textarea>
                            </div>
                            <div style="padding: 0 20px 18px; background: white; display: flex; gap: 8px; justify-content: flex-end;">
                                <button type="button" data-dismiss="modal" style="padding: 8px 16px; border-radius: 6px; border: 0.5px solid #ccc; background: white; color: #555; font-size: 13px; font-weight: 500; cursor: pointer;">Cerrar</button>
                                
                               <button type="button" id="btnConfirmarCancelacionHdr" 
                            onclick="this.disabled=true; this.style.opacity='0.6'; this.style.cursor='not-allowed'; this.innerHTML='<span class=\'spinner-border spinner-border-sm\' style=\'margin-right:6px;\'></span>Cancelando...'; confirmarCancelacionHdr();" 
                            style="padding: 8px 16px; border-radius: 6px; border: 0.5px solid #a32d2d; background: #a32d2d; color: white; font-size: 13px; font-weight: 600;">Confirmar</button>
                            </div>
                        </div>
                    </div>
                </div>

                <form id="formCancelarHojaDeRuta" method="post">
                    <input type="hidden" id="motivoCancelacionHdrHdn" name="motivoCancelacion" />
                </form>

                <hr>

                <div class="card">

                    <div class="card-body">

                        <div class="row col-xs-12 col-sm-12 col-xl-12">
                            <table id="hojaDeRutaTable" class="display table table-striped table-hover cell-border">
                                <thead>
                                    <tr>
                                        <th>NRO HOJA DE RUTA</th>
                                        <th>FECHA</th>
                                        <th>ESTADO</th>
               
                                        <th style="text-align: center">ACCIONES</th>
                                    </tr>
                                </thead>

                                <tbody>

                                    <c:forEach items="${hojasDeRuta}" var="hojaDeRuta">
                                        <tr>
                                            <td>
                                                <c:out value="${hojaDeRuta.pk}" />
                                            </td>                                            
                                            <td>
                                                <c:out value="${hojaDeRuta.fecha}" />
                                            </td>                                            
                                            <td>
                                                <c:out value="${hojaDeRuta.estado}" />
                                            </td>  
                
                                            <td>
                                                <c:if test="${rol == 'oficina' && operacion == 'alta' && hojaDeRuta.estado == 'Abierto'}">
                                                    <a class="nav-link active fa fa-ban fa-lg" href="javascript:void(0);"
                                                       onclick="abrirModalCancelacionHdr(${hojaDeRuta.pk})"
                                                       data-toggle="tooltip" data-placement="top" title="Cancelar Hoja de Ruta"></a>
                                                </c:if>
                                                <c:if test = "${operacion == 'alta' && hojaDeRuta.estado == 'Nuevo'}">
                                                    <a class="nav-link active fa fa-pencil-square-o fa-lg"
                                                        href="/thyssenplastic/hojaDeRuta/edit/${hojaDeRuta.pk}"
                                                        data-toggle="tooltip" data-placement="top" title="Editar"></a>
                                                </c:if>                                                        
                                                <c:if test = "${operacion == 'alta' && hojaDeRuta.estado == 'Nuevo'}">
                                                    <a class="nav-link active fa fa-trash-o fa-lg"
                                                        href="/thyssenplastic/hojaDeRuta/remove/${hojaDeRuta.pk}"
                                                        data-toggle="tooltip" data-placement="top" title="Eliminar"></a>
                                                </c:if>                                                                                                        
                                                <a class="nav-link active fa fa-eye fa-lg"
                                                    href="/thyssenplastic/hojaDeRuta/view/${hojaDeRuta.pk}"
                                                    data-toggle="tooltip" data-placement="top" title="Ver"></a>
                                                <a class="nav-link active fa fa-navicon fa-lg"
                                                    href="/thyssenplastic/hojaDeRutaDetalle/${hojaDeRuta.pk}"
                                                    data-toggle="tooltip" data-placement="top" title="Items"></a>                                                    
                                                <c:if test = "${rol == 'oficina' && operacion == 'alta' && hojaDeRuta.estado == 'Nuevo'}">
                                                    <a class="nav-link active fa fa-clock-o fa-lg"
                                                        href="/thyssenplastic/hojaDeRuta/setStatusOpenHojaDeRuta/${hojaDeRuta.pk}"
                                                        data-toggle="tooltip" data-placement="top" title="Cambiar a estado Abierto"></a>
                                                </c:if>               
                                              
                                                <c:if test = "${rol == 'oficina' && operacion == 'alta' && (hojaDeRuta.puedeDarBaja == true) && hojaDeRuta.estado == 'Abierto'}">
                                                    <a class="nav-link active fa fa-clock-o fa-lg"
                                                        href="/thyssenplastic/hojaDeRuta/setStatusCloseHojaDeRuta/${hojaDeRuta.pk}"
                                                        data-toggle="tooltip" data-placement="top" title="Cambiar a estado Cerrada"></a>
                                                </c:if>                                                                                                                                                                                                                                                                    
                                                <c:if test = "${rol == 'oficina'}">
                                                    <a class="nav-link active fa fa-print fa-lg"
                                                       href="javascript:void(0);" onclick="printHojaDeRuta(${hojaDeRuta.pk})"
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
        
        $('#hojaDeRutaTable').DataTable({
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
            },
            order: [[0, 'desc']]  
        });
        
        var action = $( "#action" ).val();            
        var idProveedor = $( "#idProveedor" ).val();            
        if((action == 'edit' || action == 'view' || action == 'remove') && idProveedor != undefined && idProveedor != '-1') {
            $('#idTransporte option[value="'+idProveedor+'"]').attr("selected", true);
        }
        
        loadChofer();
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
    
    function loadChofer() {

        var idTransporte = $( "#idTransporte option:selected" ).val();            

        if(idTransporte == '-1') {
            $("#idChofer").empty();
            $("#idChofer").append( new Option("Seleccionar...","-1"));
            
            return;
        }
        
        var sUrl = '/thyssenplastic/hojaDeRuta/getChofer/'+idTransporte;
        $.ajax({
            async: true,
            type: 'GET',
            dataType : 'json',
            url: sUrl
        })
        .done( function (responseText) {
            $("#idChofer").empty();
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
                
            } else {
                $("#idChofer").append( new Option("Seleccionar...","-1"));
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
    
    function printHojaDeRuta(hojaDeRutapk) {
        window.open("/thyssenplastic/hojaDeRuta/print/"+hojaDeRutapk, "Imprimir Hoja De Ruta", "popup,width=1280,height=800");
    }
    
        function abrirModalCancelacionHdr(hojaDeRutaPk) {
        $('#motivoCancelacionHdr').val('');
        $('#formCancelarHojaDeRuta').attr('action', '/thyssenplastic/hojaDeRuta/cancelarHojaDeRuta/' + hojaDeRutaPk);
        $('#modalCancelarHojaDeRuta').modal('show');
    }

    function confirmarCancelacionHdr() {
        var motivo = $('#motivoCancelacionHdr').val().trim();
        if (motivo === '') {
            alert('Debe ingresar un motivo de cancelación.');
            return;
        };

        $('#motivoCancelacionHdrHdn').val(motivo);
        $('#formCancelarHojaDeRuta').submit();
    }
    
</script>

<%@include file = "/WEB-INF/views/jsp/includes/footer.jsp" %>




