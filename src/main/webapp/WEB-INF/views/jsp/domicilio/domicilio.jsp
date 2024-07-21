<%-- 
    Document   : index
    Created on : 9 ago. 2022, 17:43:42
    Author     : waltergustavorojo
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@include file = "/WEB-INF/views/jsp/includes/header.jsp" %>

            <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <section class="content">
            <div id="cuerpo" class="container">
                <div class="container">
                    <ol>
                        <div class="card">
                            <div class="card-header">
                                <ol class="breadcrumb mb-1 mt-1">
                                    <li class="breadcrumb-item"><a href="">Ventas</a></li>
                                    <li class="breadcrumb-item active">${titleDomicilio}</li>
                                </ol>
                            </div>
                            <div class="card-body">
                                <div class="container">

                                    <form:form class="form-horizontal" method="post" action="/thyssenplastic/domicilio/addOrEditOrRemove" modelAttribute="domicilioForm" id="myForm">

                                        <form:hidden path="pk" class="form-control"/>
                                        <form:hidden path="action" class="form-control"/>

                                        <div class="form-row row">
                                            <a href="/thyssenplastic/${prefixUrl}/" title="${labelBase}">Ir a ${labelBase}</a>
                                        </div>
                                        <p></p>
                                        <div class="form-row row">
                                            ${externalName}
                                        </div>
                                        <input id="pkExternal" name="pkExternal" type="hidden" value="${pkExternal}">
                                        <input id="tipoDomicilio" name="tipoDomicilio" type="hidden" value="${tipoDomicilio}">
                                        <p></p>

                                        <div class="form-row row">                                        
                                            ${domicilioName}
                                        </div>

                                        <div class="form-row row">
                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                <label for="inputNombre">Nombre</label>
                                                <form:input path="nombre" class="form-control" placeholder="" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                            </div>
                                        </div>

                                        <div class="form-row row">                                            
                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                <label for="inputTelefono">Ubicación</label>
                                                <form:input type="text" path="ubicacion" class="form-control" placeholder="" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                                
                                            </div>
                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                <label for="inputRol">Pais</label>
                                                <form:select  path="idPais" class="form-control rubro" id="pais" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')">                                                
                                                    <form:options items="${paisList}"/>
                                                </form:select>                                            
                                            </div>                                            
                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                <label for="inputRol">Provincia</label>
                                                <form:select  path="idProvincia" class="form-control rubro" id="provincia" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')">                                                
                                                    <form:options items="${provinciaList}"/>
                                                </form:select>                                            
                                            </div>
                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                <label for="inputRol">Localidad</label>
                                                <form:select  path="idLocalidad" class="form-control rubro" id="localidad" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')">                                                                                                    
                                                    <form:options items="${localidadList}"/>
                                                </form:select>                                            
                                            </div>                                            
                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                <label for="inputRol">Tipo</label>
                                                <form:select  path="idTipo" class="form-control rubro">                                                
                                                    <form:options items="${tipoList}"/>
                                                </form:select>                                            
                                            </div>
                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                <label for="inputTelefono">punto GPS</label>
                                                <form:input path="puntoGps" class="form-control" placeholder="" />
                                            </div>
                                        </div>
                                        <div class="form-row row">
                                            <div class="row col-xs-12 col-sm-12 col-xl-12">
                                                <h4><ins><strong>Datos de Contacto</strong></ins></h4>
                                            </div>                                              
                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                <label for="inputNombreContacto">Nombre</label>
                                                <form:input path="nombreContacto" class="form-control" placeholder=""/>
                                            </div>
                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                <label for="inputTelefonoContacto">Telefono</label>
                                                <form:input path="telefonoContacto" class="form-control" placeholder=""/>
                                            </div>   
                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                <label for="inputHorario">Horario</label>
                                                <form:input path="horarioContacto" class="form-control" placeholder=""/>
                                            </div>
                                            <div class="row col-xs-9 col-sm-12 col-xl-12">
                                                <label for="inputobservacionesContacto">Observaciones</label>
                                                <form:textarea path="observacionesContacto" class="form-control"/>
                                            </div>
                                                                                                                      
                                        </div>

                                        <div class="form-row row">                                           
                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                <button type="button" class="btn btn-primary" onClick="callController()">${buttonLabel}</button>
                                                <a href="/thyssenplastic/domicilio/${prefixUrl}pk/${pkExternal}"><button type="button" class="btn btn-secondary">Cancelar</button></a>
                                            </div>
                                        </div>                                                

                                    </form:form>
                                </div>
                            </div>
                        </div>                        
                    </ol>
                </div>

                <hr>
                
                <div class="card">
                    <div class="card-body">
                        <!--Modal-->
                        <div id="GSCCModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                        <h4 class="modal-title" id="myModalLabel">Eliminar Domicilio!</h4>
                                    </div>
                                    <div class="modal-body">
                                        Desea Eliminar?
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary">Si</button>
                                        <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <p></p>

                        <div class="row col-xs-12 col-sm-12 col-xl-12">
                            <table id="domiciliosTable" class="display table table-striped table-hover cell-border" style="width:100%">

                                <thead>
                                    <tr>                                        
                                        <!--  <th class="hidden-xs hidden-sm">Nombre</th> -->
                                        <th>UBICACION</th>
                                        <th class="hidden-xs hidden-sm">TIPO</th>
                                        <th class="hidden-xs hidden-sm">Pais</th>
                                        <th class="hidden-xs hidden-sm">Provincia</th>
                                        <th class="hidden-xs hidden-sm">Localidad</th>
                                        <th class="hidden-xs hidden-sm">gps</th>
                                        <th class="hidden-xs hidden-sm">Nombre Contacto</th>
                                        <th class="hidden-xs hidden-sm">Telefono</th>
                                        <th class="hidden-xs hidden-sm">Horario</th>
                                        <th class="hidden-xs hidden-sm">Observaciones</th>
                                        <th style="text-align: center">ACCIONES</th>

                                    </tr>
                                </thead>          
                                <tbody>
                                    <c:forEach  items="${domicilios}" var="domicilio">
                                        <tr>
                                            <!-- <td class="hidden-xs hidden-sm"><c:out value="${domicilio.nombre}" /></td> -->
                                            <td><c:out value="${domicilio.ubicacion}" /></td>
                                            <td class="hidden-xs hidden-sm">${domicilio.tipo}</td>
                                            <td><c:out value="${domicilio.pais}" /></td>
                                            <td><c:out value="${domicilio.provincia}" /></td>
                                            <td><c:out value="${domicilio.localidad}" /></td>
                                            <td><c:out value="${domicilio.puntoGps}" /></td>
                                            <td><c:out value="${domicilio.nombreContacto}" /></td>
                                            <td><c:out value="${domicilio.telefonoContacto}" /></td>
                                            <td><c:out value="${domicilio.horarioContacto}" /></td>
                                            <td><c:out value="${domicilio.observacionesContacto}" /></td>
                                            <!--acciones-->
                                            <td align="center">
                                                <a class="nav-link active fa fa-pencil-square-o fa-lg" href="/thyssenplastic/domicilio/${prefixUrl}pk/${pkExternal}/edit/${domicilio.id}" data-toggle="tooltip" data-placement="top" title="Editar"></a>
                                                <a class="nav-link active fa fa-trash fa-lg" href="/thyssenplastic/domicilio/${prefixUrl}pk/${pkExternal}/remove/${domicilio.id}" data-toggle="tooltip" data-placement="top" title="Eliminar"></a>
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
        
    <script>
        $(document).ready(function () {

            $('#domiciliosTable').DataTable({
                language: {
                    "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
                }            
            });
        });
        /*

        $( "#pais" ).change(function() {

            if($('#pais option:selected').val() != '-1') {
                
                var baseUrl = "/thyssenplastic/domicilio/getprovincias/";
                var sUrl = baseUrl;
                var idPais = $( "#pais option:selected" ).val();
                sUrl = sUrl + idPais;
                                
                $.ajax({
                    async: true,
                    type: 'GET',
                    dataType : 'json',
                    url: sUrl
                })
                    .done( function (responseText) {
                        if(responseText.length > 0) {
                            $("#provincia").empty();
                            for (i = 0; i < responseText.length; i++) {
                                var provincia = responseText[i];
                                $("#provincia").append( new Option(provincia.nombre,provincia.id));
                            }
                            $( "#provincia" ).change();
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
        });
        
        
        $( "#provincia" ).change(function() {

            if($('#provincia option:selected').val() != '-1') {
                
                var baseUrl = "/thyssenplastic/domicilio/getlocalidades/";
                var sUrl = baseUrl;
                var idProvincia = $( "#provincia option:selected" ).val();
                sUrl = sUrl + idProvincia;
                                
                $.ajax({
                    async: true,
                    type: 'GET',
                    dataType : 'json',
                    url: sUrl
                })
                    .done( function (responseText) {
                        if(responseText.length > 0) {
                            $("#localidad").empty();
                            for (i = 0; i < responseText.length; i++) {
                                var localidad = responseText[i];
                                $("#localidad").append( new Option(localidad.nombre,localidad.id));
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
        }); */

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

    </script>                            
    
    <%@include file = "/WEB-INF/views/jsp/includes/footer.jsp" %>












