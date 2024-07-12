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
                                <li class="breadcrumb-item"><a href="">Inventario</a></li>
                                <li class="breadcrumb-item active">${titleInsumo}</li>
                            </ol>
                        </div>
                        <div class="card-body">
                            <div class="container">

                                <form:form class="form-horizontal" method="post" action="/thyssenplastic/insumo/addOrEditOrRemove" modelAttribute="insumoForm" id="myForm">

                                    <p></p>
                                    ${insumoName}
                                    <p></p>

                                    <form:hidden path="pk" class="form-control"/>
                                    <form:hidden path="action" class="form-control"/>
                                    
                                    <div class="form-row row">
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputFecha_alta">Fecha Alta</label>
                                            <form:input type="date" path="fechaAlta" class="form-control" placeholder="" id="holderDateOfBirth"/>                                            
                                        </div>
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputDescripcion">Descripcion</label>                                                        
                                            <form:input path="descripcion" class="form-control" placeholder="" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                        </div>                                                    
                                    </div>

                                    <div class="form-row row">
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputUnidad">Unidad</label>
                                            <form:input path="unidad" class="form-control" placeholder=""/>
                                        </div>
                                    </div>

                                    <div class="form-row row">
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputRubro">Proveedor</label>
                                            <form:select  path="idProveedor" class="form-control rubro">                                                
                                                <form:options items="${proveedorList}"/>
                                              </form:select>                                            
                                        </div>
                                    </div>

                                    <div class="form-row row">
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <button type="button" class="btn btn-primary" onClick="callController()">${buttonLabel}</button>
                                            <a href="/thyssenplastic/insumo"><button type="button" class="btn btn-secondary">Cancelar</button></a>
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
                                    <h4 class="modal-title" id="myModalLabel">Eliminar Insumo!</h4>
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
                        <table id="insumosTable" class="display table table-striped table-hover cell-border">
                            <thead>
                                <tr>
                                    <th>CODIGO</th>
                                    <th>PROVEEDOR</th>
                                    <th>DESCRIPCION</th>                                    
                                    <th class="hidden-xs hidden-sm">UNIDAD</th>
                                    <th class="hidden-xs hidden-sm">STOCK</th>
                                    <th style="text-align: center">ACCIONES</th>
                                </tr>
                            </thead>

                            <tbody>

                                <c:forEach  items="${insumos}" var="insumo">
                                    <tr>
                                        <td><c:out value="${insumo.pk}" /></td>
                                        <td><c:out value="${insumo.proveedor}" /></td>
                                        <td><c:out value="${insumo.descripcion}" /></td>
                                        <td class="hidden-xs hidden-sm"><c:out value="${insumo.unidad}" /></td>
                                        <td class="hidden-xs hidden-sm"><c:out value="${insumo.stock}" /></td>                                        
                                        <td>                                                    
                                            <a class="nav-link active fa fa-pencil-square-o fa-lg" href="/thyssenplastic/insumo/edit/${insumo.pk}" data-toggle="tooltip" data-placement="top" title="Editar"></a>
                                            <a class="nav-link active fa fa-trash fa-lg" href="/thyssenplastic/insumo/remove/${insumo.pk}" data-toggle="tooltip" data-placement="top" title="Eliminar"></a>
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
        
        $('#insumosTable').DataTable({
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




