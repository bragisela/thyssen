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
                                <li class="breadcrumb-item"><a href="">Mantenimiento</a></li>
                                <li class="breadcrumb-item active">${titleRepuesto}</li>
                            </ol>
                        </div>
                            <div class="card-body" >
                            <div class="container">

                                <form:form class="form-horizontal" method="post" action="/thyssenplastic/repuesto/addOrEditOrRemove" modelAttribute="repuestoForm" id="myForm">

                                    <p></p>
                                    ${repuestoName}
                                    <p></p>

                                    <form:hidden path="pk" class="form-control"/>
                                    <form:hidden path="action" class="form-control"/>
                                    
                                    <div class="form-row row">

                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputDescripcion">Código Repuesto</label>
                                            <form:input path="codigo" class="form-control" placeholder="" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                        </div>
                                        
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputDescripcion">Descripción</label>
                                            <form:input path="descripcion" class="form-control" placeholder="" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                        </div>
                                        
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputStock">Stock</label>
                                            <form:input type="number" path="stock" class="form-control" placeholder="" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                        </div>
                                    
                                    </div>

                                    <div class="form-row row">
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputStockMinimo">Stock Mínimo</label>
                                            <form:input type="number" path="stockMinimo" class="form-control" placeholder="" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                        </div>
                                    </div>

                                    <div class="form-row row">
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <button type="button" class="btn btn-primary" onclick="callController()">${buttonLabel}</button>
                                            <a href="/thyssenplastic/repuesto"><button type="button" class="btn btn-secondary">Cancelar</button></a>
                                        </div>
                                    </div>
                                        
                                </form:form>
                            </div>
                        </div>
                    </div>                
                </ol>
            </div>

            <div class="card">

                <div class="card-body">

                    <!--Modal-->
                    <div id="GSCCModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                    <h4 class="modal-title" id="myModalLabel">Eliminar Repuesto!</h4>
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
                        <table id="repuestoTable" class="display table table-striped table-hover cell-border">
                            <thead>
                                <tr>
                                    <th>CODIGO</th>
                                    <th>CODIGO REPUESTO</th>
                                    <th>DESCRIPCION</th>                                    
                                    <th class="hidden-xs hidden-sm">STOCK</th>
                                    <th class="hidden-xs hidden-sm">STOCK MINIMO</th>
                                    <th style="text-align: center">ACCIONES</th>
                                </tr>
                            </thead>

                            <tbody>

                                <c:forEach  items="${repuestos}" var="repuesto">
                                    <tr>
                                        <td><c:out value="${repuesto.pk}" /></td>
                                        <td><c:out value="${repuesto.codigo}" /></td>
                                        <td><c:out value="${repuesto.descripcion}" /></td>
                                        <td><c:out value="${repuesto.stock}" /></td><!-- comment -->
                                        <td><c:out value="${repuesto.stockMinimo}" /></td>
    
                                        <td>                                                    
                                            <a class="nav-link active fa fa-trash fa-lg" href="/thyssenplastic/repuesto/remove/${repuesto.pk}" data-toggle="tooltip" data-placement="top" title="Eliminar"></a>
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
        
        $('#repuestoTable').DataTable({
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




