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
                                    <li class="breadcrumb-item"><a href="">Hoja de Ruta Detalle</a></li>
                                    <li class="breadcrumb-item active">${titleHojaDeRutaDetalle}</li>
                                </ol>
                            </div>

                            <div class="card-body">
                                <form:form class="form-horizontal" method="post"
                                        action="/thyssenplastic/hojaDeRutaDetalle/addOrEditOrRemove"
                                        modelAttribute="hojaDeRutaDetalleForm" id="myForm">

                                    <div class="container">


                                        <c:set var = "disabledAlta" value = "true"/>
                                        <c:set var = "disabledCierre" value = "true"/>
                                        <c:if test = "${operacion == 'alta'}">
                                            <c:set var = "disabledAlta" value = "false"/>
                                            <c:set var = "disabledCierre" value = "true"/>
                                        </c:if>
                                        <c:if test = "${operacion == 'edit'}">
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
                                        <c:if test = "${estadoHojaDeRuta != 'Nuevo'}">
                                            <c:set var = "disabledAlta" value = "true"/>
                                            <c:set var = "disabledCierre" value = "true"/>
                                        </c:if>

                                        ${action}
                                        <p></p>
                                        ${hojaDeRutaDetalleName}
                                        
                                        <p></p>

                                        <form:hidden path="pk" class="form-control"/>
                                        <form:hidden path="action" class="form-control"/>
                                        <form:hidden path="operacion" class="form-control"/>
                                        <form:hidden path="idHojaDeRutaHdn" class="form-control"/>                                        
                                        <form:hidden path="idRemitoHdn" class="form-control"/> 

                                        <div class="tab-content">
                                            <!--CONTENIDO ABIERTO-->

                                            <div class="form-row row">                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <a href="/thyssenplastic/hojaDeRuta/" id="ordcomp">Atrás</a>
                                                </div>                                                                                                    
                                            </div>
                                            
                                            <p>

                                            <div class="form-row row">                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Nro. Hoja De Ruta</label>
                                                    <form:input type="text" path="idHojaDeRuta" class="form-control" placeholder=""
                                                                id="holderDateOfBirth" required="required" disabled="true"/>
                                                </div>                                                    
                                            </div>
                                                
                                            <div class="form-row row">                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Fecha Hoja De Ruta</label>
                                                    <form:input type="text" path="fechaHojaDeRuta" class="form-control" placeholder=""
                                                                id="holderDateOfBirth" required="required" disabled="true"/>
                                                </div>                                                    
                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Estado</label>
                                                    <form:input type="text" path="estadoHojaDeRuta" class="form-control" placeholder="" disabled="true"/>
                                                </div>
                                            </div>

                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Fecha Carga</label>
                                                    <form:input type="date" path="fechaCargaHojaDeRuta" class="form-control" placeholder=""
                                                                id="holderDateOfBirth" required="required" disabled="true"/>
                                                </div>                                                    
                                            </div>                                                
                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Fecha Salida</label>
                                                    <form:input type="date" path="fechaSalidaHojaDeRuta" class="form-control" placeholder=""
                                                                id="holderDateOfBirth" required="required" disabled="true"/>
                                                </div>                                                    
                                            </div>
                                            
                                            <p></p>    
                                            <hr>
                                            <p></p>
                                            
                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">                                                        
                                                    <label for="inputRubro">Remito</label>
                                                    <form:select path="idRemito" class="form-control rubro" required="required" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')" onChange="setIdRemitoHdn()">
                                                        <form:option value="-1">Seleccionar...</form:option>
                                                        <form:options items="${remitosList}" />                                                        
                                                    </form:select>
                                                </div>                                                    
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Km.</label>
                                                    <form:input type="number" path="km" step="0.01" min="0" class="form-control" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                                </div>
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Orden</label>
                                                    <form:input type="number" path="orden" min="0" class="form-control" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                                </div>
                                                
                                            </div>
                                            
                                        </div>                                                    
                                                    
                                        <p></p>
                                                                                
                                        <div class="form-row row">
                                            <div class="row col-xs-9 col-sm-1 col-xl-1" style="display:${displayActionButton}">
                                                <button type="button" class="btn btn-primary" onclick="callController()">${buttonLabel}</button>                                            
                                            </div>
                                            <div class="row col-xs-9 col-sm-1 col-xl-1">
                                                <a href="/thyssenplastic/hojaDeRutaDetalle/${idHojaDeRuta}"><button type="button" class="btn btn-secondary">Cancelar</button></a>
                                            </div>    
                                        </div>
                                        
                                    </div>
                                
                                </form:form>
                            </div>
                        </div>
                </div>
                </ol>

                <hr>

                <div class="card">

                    <div class="card-body">

                        <div class="row col-xs-12 col-sm-12 col-xl-12">
                            <table id="hojaDeRutaDetalleTable" class="display table table-striped table-hover cell-border">
                                <thead>
                                    <tr>
                                        <th>NRO REMITO</th>
                                        <th>ORDEN</th>
                                       
                                        <th>DOMICILIO</th>
                                        <th>LOCALIDAD</th>
                                        <th>PROVINCIA</th>
                                        <th>CONTACTO</th>
                                        <th>TEL.CONTACTO</th>                                        
                                        <th>HORARIO</th>
                                        <th>KM</th>
                                        <th>KM ACUMULADO</th>
                                         <th>ESTADO</th>                                         
                                        <th style="text-align: center">ACCIONES</th>
                                    </tr>
                                </thead>

                                <tbody>

                                    <c:forEach items="${hojasDeRutaDetalle}" var="hojaDeRutaDetalle">
                                        <tr>
                                            <td>
                                                <c:out value="${hojaDeRutaDetalle.idRemito}" />
                                            </td>                                            
                                            <td>
                                                <c:out value="${hojaDeRutaDetalle.orden}" />
                                            </td>                                                                                        
                                                                             
                                            <td>
                                                <c:out value="${hojaDeRutaDetalle.domicilio}" />
                                            </td>
                                             <td>
                                                <c:out value="${hojaDeRutaDetalle.localidad}" />
                                            </td> 
                                             <td>
                                                <c:out value="${hojaDeRutaDetalle.provincia}" />
                                            </td> 
                                            <td>
                                                <c:out value="${hojaDeRutaDetalle.contacto}" />
                                            </td> 
                                            <td>
                                                <c:out value="${hojaDeRutaDetalle.telefono}" />
                                            </td>                                                                                        
                                            <td>
                                                <c:out value="${hojaDeRutaDetalle.horario}" />
                                            </td>                                                                                        
                                            <td>
                                                <c:out value="${hojaDeRutaDetalle.km}" />
                                            </td>                                                                                        
                                            <td>
                                                <c:out value="${hojaDeRutaDetalle.kmAcumulado}" />
                                            </td>
                                            <td>
                                                <c:out value="${hojaDeRutaDetalle.estado}" />
                                            </td> 
                                            
                                            <td>
                                                <c:if test = "${operacion == 'alta' && (hojaDeRutaDetalle.estadoHojaDeRuta == 'Nuevo' || hojaDeRutaDetalle.estadoHojaDeRuta == 'Abierto')}">
                                                    <a class="nav-link active fa fa-pencil-square-o fa-lg"
                                                        href="/thyssenplastic/hojaDeRutaDetalle/edit/${hojaDeRutaDetalle.pk}"
                                                        data-toggle="tooltip" data-placement="top" title="Editar"></a>
                                                </c:if>                                                                                                                                                                                                            
                                                <c:if test = "${operacion == 'alta' && hojaDeRutaDetalle.estadoHojaDeRuta == 'Nuevo'}">
                                                    <a class="nav-link active fa fa-trash-o fa-lg"
                                                        href="/thyssenplastic/hojaDeRutaDetalle/remove/${hojaDeRutaDetalle.pk}"
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
        </section>
    </div>
                  

<!-- ./wrapper -->

<script>
    $(document).ready(function () {
        
        $('#hojaDeRutaDetalleTable').DataTable({
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
            }            
        });
        
    });
        
    function callController() {
        
        var idRemito = $( "#idRemito option:selected" ).val();
        var km = $( "#km" ).val();
        
        if(idRemito == '-1') {
            alert('Debe seleccionar un remito.');
            return;
        }
        if(km == '') {
            alert('Debe completar los km.');
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
    
    function setIdRemitoHdn(){
        var idRemito = $( "#idRemito option:selected" ).val();
        $( "#idRemitoHdn" ).val(idRemito);
    }
    
</script>

<%@include file = "/WEB-INF/views/jsp/includes/footer.jsp" %>




