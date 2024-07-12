<%-- 
    Document   : index
    Created on : 9 ago. 2022, 17:43:42
    Author     : waltergustavorojo
--%>
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
                                <li class="breadcrumb-item active">${titleCliente}</li>
                            </ol>
                        </div>
                        <div class="card-body">
                            <div class="container">

                                <form:form class="form-horizontal" method="post" action="/thyssenplastic/cliente/addOrEditOrRemove" modelAttribute="clienteForm" id="myForm">

                                    <p></p>
                                    ${clienteName}
                                    <p></p>

                                    <form:hidden path="pk" class="form-control"/>
                                    <form:hidden path="action" class="form-control"/>
                                    
                                    <div class="form-row row">
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputFecha_alta">Fecha Alta</label>
                                            <form:input type="date" path="fechaAlta" class="form-control" placeholder="" id="holderDateOfBirth"/>                                            
                                        </div>
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputRubro">Tipo</label>
                                            <form:select  path="tipoDocumento" class="form-control rubro" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')">                                                
                                                <form:option value="cuil">CUIL</form:option>                                                
                                                <form:option value="cuit">CUIT</form:option>
                                                <form:option value="dni">DNI</form:option>                                                
                                            </form:select>                                            
                                        </div>                                                                                                                    
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputCuit">CUIL/CUIT/DNI</label>                                                        
                                            <form:input path="cuit" class="form-control" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                        </div>
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputRazonSocial">Razón Social</label>                                                        
                                            <form:input path="razonSocial" class="form-control" placeholder="" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                        </div>                                                    
                                    </div>

                                    <div class="form-row row">
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputTelefono">Teléfono</label>                                                        
                                            <form:input type="string" path="telefono" class="form-control" placeholder=""/>
                                        </div>
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputEmail">Email</label>
                                            <form:input path="mail" class="form-control" placeholder=""/>
                                        </div>
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputHorario">Horario</label>
                                            <form:input path="horario" class="form-control" placeholder=""/>
                                        </div>
                                    </div>

                                    <div class="form-row row">
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputRubro">Rubro</label>
                                            <form:select  path="idRubro" class="form-control rubro">                                                
                                                <form:options items="${rubroList}"/>
                                              </form:select>                                            
                                        </div>
                                    </div>

                                    <div class="form-row row">
                                          <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputObservaciones">Observaciones</label>
                                            <form:textarea path="observaciones" class="form-control"/>
                                        </div>
                                    </div>

                                    <div class="form-row row">
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <button type="button" class="btn btn-primary" onClick="callController()">${buttonLabel}</button>
                                            <a href="/thyssenplastic/cliente"><button type="button" class="btn btn-secondary">Cancelar</button></a>
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
                                    <h4 class="modal-title" id="myModalLabel">Eliminar Cliente!</h4>
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
                        <table id="clientesTable" class="display table table-striped table-hover cell-border">
                            <thead>
                                <tr>
                                    <th>CODIGO</th>
                                    <th class="hidden-xs hidden-sm">CUIT</th>
                                    <th>RAZON SOCIAL</th>
                                    <th class="hidden-xs hidden-sm">TELEFONO</th>
                                    <th class="hidden-xs hidden-sm">EMAIL</th>
                                    <th style="text-align: center">ACCIONES</th>
                                </tr>
                            </thead>

                            <tbody>

                                <c:forEach  items="${clientes}" var="cliente">
                                    <tr>
                                        <td><c:out value="${cliente.pk}" /></td>
                                        <td class="hidden-xs hidden-sm"><c:out value="${cliente.cuit}" /></td>
                                        <td><c:out value="${cliente.razonSocial}" /></td>
                                        <td class="hidden-xs hidden-sm"><c:out value="${cliente.telefono}" /></td>
                                        <td><c:out value="${cliente.mail}" /></td>
                                        <td>                                                    
                                            <a class="nav-link active fa fa-pencil-square-o fa-lg" href="/thyssenplastic/cliente/edit/${cliente.pk}" data-toggle="tooltip" data-placement="top" title="Editar"></a>
                                            <a class="nav-link active fa fa-trash fa-lg" href="/thyssenplastic/cliente/remove/${cliente.pk}" data-toggle="tooltip" data-placement="top" title="Eliminar"></a>
                                            <a class="nav-link active fa fa-users fa-lg" href="/thyssenplastic/contacto/clientepk/${cliente.pk}" data-toggle="tooltip" data-placement="top" title="Contactos"></a>
                                            <a class="nav-link active fa fa-building fa-lg" href="/thyssenplastic/domicilio/clientepk/${cliente.pk}" data-toggle="tooltip" data-placement="top" title="Domicilios"></a>
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
        
        $('#clientesTable').DataTable({
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




