<%-- 
    Document   : index
    Created on : 9 ago. 2022, 17:43:42
    Author     : waltergustavorojo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@include file = "/WEB-INF/views/jsp/includes/header.jsp" %>
    
            <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <section class="content">
            <div id="cuerpo" class="container">
                <ol>
                    <div class="card">
                        <div class="card-header">
                            <ol class="breadcrumb mb-1 mt-1">
                                <li class="breadcrumb-item"><a href="">Compras</a></li>
                                <li class="breadcrumb-item active">${titleContacto}</li>
                            </ol>
                        </div>
                        <div class="card-body">
                            <div class="container">

                                <form:form class="form-horizontal" method="post" action="/thyssenplastic/contacto/addOrEditOrRemove" modelAttribute="contactoForm" id="myForm">

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
                                    <input id="tipoContacto" name="tipoContacto" type="hidden" value="${tipoContacto}">
                                    <p></p>

                                    <div class="form-row row">                                        
                                        ${contactoName}
                                    </div>

                                    <div class="form-row row">
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputRubro">Tipo</label>
                                            <form:select  path="tipoDocumento" class="form-control rubro" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')">                                                
                                                <form:option value="cuit">CUIT</form:option>
                                                <form:option value="dni">DNI</form:option>
                                            </form:select>                                            
                                        </div>                                                                                                                    
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputCuit">CUIT</label>
                                            <form:input path="cuit" class="form-control" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                        </div>                                        
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputNombre">Nombre</label>
                                            <form:input path="nombre" class="form-control" placeholder="" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                        </div>
                                    </div>

                                    <div class="form-row row">
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputTelefono">Teléfono</label>
                                            <form:input path="telefono" class="form-control" placeholder=""/>
                                        </div>
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputEmail">Email</label>
                                            <form:input path="mail" class="form-control" placeholder=""/>
                                        </div>
                                        <div class="form-row row col-xs-9 col-sm-3 col-xl-4">                                            
                                            <label for="inputRubro">Rol</label>
                                            <form:select  path="idRol" class="form-control rubro">                                                
                                                <form:options items="${rolList}"/>
                                              </form:select>                                            
                                        </div>                                        
                                    </div>

                                    <div class="form-row row">
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <button type="button" class="btn btn-primary" onClick="callController()">${buttonLabel}</button>
                                            <a href="/thyssenplastic/contacto/${prefixUrl}pk/${pkExternal}"><button type="button" class="btn btn-secondary">Cancelar</button></a>
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
                                    <h4 class="modal-title" id="myModalLabel">Eliminar Contacto!</h4>
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
                        <table id="contactosTable" class="display table table-striped table-hover cell-border" style="width:100%">

                            <thead>
                                <tr>
                                    <th>CODIGO</th>                                        
                                    <th class="hidden-xs hidden-sm">CUIT</th>
                                    <th>NOMBRE</th>
                                    <th class="hidden-xs hidden-sm">TELEFONO</th>
                                    <th class="hidden-xs hidden-sm">EMAIL</th>
                                    <th class="hidden-xs hidden-sm">ROL</th>
                                    <th style="text-align: center">ACCIONES</th>

                                </tr>
                            </thead>          
                            <tbody>
                                <c:forEach  items="${contactos}" var="contacto">
                                    <tr>
                                        <td><c:out value="${contacto.id}" /></td>
                                        <td class="hidden-xs hidden-sm"><c:out value="${contacto.cuit}" /></td>
                                        <td><c:out value="${contacto.nombre}" /></td>
                                        <td class="hidden-xs hidden-sm"><c:out value="${contacto.telefono}" /></td>
                                        <td class="hidden-xs hidden-sm"><c:out value="${contacto.mail}" /></td>
                                        <td class="hidden-xs hidden-sm"><c:out value="${contacto.rol}" /></td>
                                        <!--acciones-->
                                        <td align="center">
                                            <a class="nav-link active fa fa-pencil-square-o fa-lg" href="/thyssenplastic/contacto/${prefixUrl}pk/${pkExternal}/edit/${contacto.id}" data-toggle="tooltip" data-placement="top" title="Editar"></a>
                                            <a class="nav-link active fa fa-trash fa-lg" href="/thyssenplastic/contacto/${prefixUrl}pk/${pkExternal}/remove/${contacto.id}" data-toggle="tooltip" data-placement="top" title="Eliminar"></a>
                                        </td>
                                    </tr>
                                </c:forEach>                                                                  
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </section>
    </div>                   
        
    <script>
        $(document).ready(function () {

            $('#contactosTable').DataTable({
                language: {
                    "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
                }            
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
                            
    </script>

    <%@include file = "/WEB-INF/views/jsp/includes/footer.jsp" %>












