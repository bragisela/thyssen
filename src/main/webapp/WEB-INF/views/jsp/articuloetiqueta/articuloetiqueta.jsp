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
                                <li class="breadcrumb-item"><a href="">Artículos Etiqueta</a></li>
                                <li class="breadcrumb-item active">${titleArticuloEtiqueta}</li>
                            </ol>
                        </div>
                        <div class="card-body">
                            <div class="container">

                                <form:form class="form-horizontal" method="post" action="/thyssenplastic/articulo/articuloetiqueta/addOrEditOrRemove" modelAttribute="articuloEtiquetaForm" id="myForm">

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
                                    
                                    <div class="form-row row">                                        
                                        ${articuloEtiquetaName}
                                    </div>
                                    
                                    <br>
                                    
                                    <div class="form-row row">
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputFecha_alta">Fecha Alta</label>
                                            <form:input type="date" path="fechaAlta" class="form-control" placeholder="" id="holderDateOfBirth" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>                                            
                                        </div>                                                    
                                    </div>
                                    
                                    <div class="form-row row">
                                        <div class="row col-xs-12 col-sm-12 col-xl-12">
                                            <label for="inputDescripcion">Linea 1</label>                                                        
                                            <form:input path="linea1" class="form-control" placeholder="" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                        </div>
                                        <div class="row col-xs-12 col-sm-12 col-xl-12">
                                            <label for="inputDescripcion">Linea 2</label>                                                        
                                            <form:input path="linea2" class="form-control" placeholder="" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                        </div>
                                        <div class="row col-xs-12 col-sm-12 col-xl-12">
                                            <label for="inputDescripcion">Linea 3</label>                                                        
                                            <form:input path="linea3" class="form-control" placeholder="" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                        </div>
                                        <div class="row col-xs-12 col-sm-12 col-xl-12">
                                            <label for="inputDescripcion">Linea 4</label>                                                        
                                            <form:input path="linea4" class="form-control" placeholder="" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                        </div>
                                        <div class="row col-xs-12 col-sm-12 col-xl-12">
                                            <label for="inputDescripcion">Linea 5</label>                                                        
                                            <form:input path="linea5" class="form-control" placeholder="" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                        </div>
                                    </div>

                                    <p></p>    
                                    
                                    <div class="form-row row">
                                        <div class="row col-xs-9 col-sm-1 col-xl-1" style="display:${displayActionButton}">
                                            <button type="button" class="btn btn-primary" onClick="callController()">${buttonLabel}</button>                                            
                                        </div>
                                        <div class="row col-xs-9 col-sm-1 col-xl-1">
                                            <a href="/thyssenplastic/articulo/articuloetiqueta/${pkExternal}"><button type="button" class="btn btn-secondary">Cancelar</button></a>
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
                                    <h4 class="modal-title" id="myModalLabel">Eliminar Etiqueta!</h4>
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
                    
                    <hr>
                    
                    <div class="row col-xs-12 col-sm-12 col-xl-12">
                        <table id="articulosEtiquetasTable" class="display table table-striped table-hover cell-border">
                            <thead>
                                <tr>
                                    <th>CODIGO</th>
                                    <th>FECHA</th>                                    
                                    <th style="text-align: center">ACCIONES</th>
                                </tr>
                            </thead>

                            <tbody>

                                <c:forEach  items="${articulosetiqueta}" var="articuloetiqueta">
                                    <tr>
                                        <td><c:out value="${articuloetiqueta.pk}" /></td>
                                        <td><c:out value="${articuloetiqueta.fechaAlta}" /></td>
                                        <td>
                                            <a class="nav-link active fa fa-eye fa-lg" href="/thyssenplastic/articulo/articuloetiqueta/view/${articuloetiqueta.pk}" data-toggle="tooltip" data-placement="top" title="Ver"></a>
                                            <a class="nav-link active fa fa-trash fa-lg" href="/thyssenplastic/articulo/articuloetiqueta/remove/${articuloetiqueta.pk}" data-toggle="tooltip" data-placement="top" title="Eliminar"></a>
                                                <a class="nav-link active fa fa-print fa-lg" href="javascript:void(0);" onclick="printEtiqueta(${articuloetiqueta.pk})" data-toggle="tooltip" data-placement="top" title="Imprimir"></a>
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
        
        $('#articulosEtiquetasTable').DataTable({
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
            }            
        });
    });
    
    function printEtiqueta(articuloetiquetapk) {
        window.open("/thyssenplastic/articulo/articuloetiqueta/print/"+articuloetiquetapk, "Imprimir Etiqueta Artículo", "popup");
    }

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




