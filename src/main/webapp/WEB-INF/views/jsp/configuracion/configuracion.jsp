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
                                <li class="breadcrumb-item"><a href="">Configuración</a></li>
                                <li class="breadcrumb-item active">${titleTipo}</li>
                            </ol>
                        </div>
                            <div class="card-body" >
                            <div class="container">

                                <form:form class="form-horizontal" method="post" action="/thyssenplastic/tipo/addOrEditOrRemove" modelAttribute="tipoForm" id="myForm">

                                    <p></p>
                                    ${tipoName}
                                    <p></p>

                                    <form:hidden path="pk" class="form-control"/>
                                    <form:hidden path="action" class="form-control"/>
                                    
                                    <div class="form-row row">
                                        
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputRubro">Tipo</label>
                                            <form:select  path="tipo" id="tipo" class="form-control dato" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')" onchange="hideDisplayDivs()" onChange="verificarDatos()">                                                                                                                                                
                                                <form:option value="chofer">Chofer</form:option>                                                                                                
                                                <form:option value="denominacion">Denominación</form:option>                                                
                                                <form:option value="maquinaMantenimiento">Máquina Mantenimiento</form:option>                                                                                                
                                                <form:option value="tipodomicilio">Tipo Domicilio</form:option>                                                
                                                <form:option value="tipoMaterialesArticulo">Tipo Materiales Artículo</form:option>                                                
                                                <form:option value="tipomateriaprima">Tipo Materia Prima</form:option>                                                                                                
                                                <form:option value="tipoReparacionMantenimiento">Tipo Reparacion Mantenimiento</form:option>                                                                                                
                                                <form:option value="petroquimica">Petroquímica</form:option>                                                                                                
                                                <form:option value="rol">Rol</form:option>
                                                <form:option value="rubroArticulo">Rubro Artículo</form:option>                                                                                                
                                                <form:option value="rubroCliente">Rubro Cliente</form:option>
                                                <form:option value="rubroProveedor">Rubro Proovedor</form:option>                                                                                                
                                                <form:option value="unidadVentaArticulo">Unidad Venta Artículo</form:option>
                                                <form:option value="pais">País</form:option>
                                                <form:option value="provincia">Provincia</form:option>
                                                <form:option value="localidad">Localidad</form:option>
                                                <form:option value="motivoScrap">Motivo Scrap</form:option>
                                                <form:option value="origenScrap">Origen Scrap</form:option>
                                                <form:option value="tipoMaterialScrap">Tipo Material Scrap</form:option>
                                                <form:option value="formatoScrap">Formato Scrap</form:option>
                                                <form:option value="plegadoraBobina">Plegadora</form:option>
                                            </form:select>                                            
                                        </div>
                                         <div class="form-row row">
                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                <label for="inputUnidad">Valor</label>
                                                <form:input path="valor" class="form-control" placeholder="" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')" onchange="hideDisplayDivs()"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-row row">
                                        <div class="row col-xs-6 col-sm-6 col-xl-6" style="display:none" id="panelDendidad">
                                            <label for="inputScrap">Densidad</label>
                                            <form:input type="number" step="any" path="densidad" class="form-control"
                                                placeholder="" id="holderScrap" />
                                        </div>
                                        <div class="row col-xs-6 col-sm-6 col-xl-6" style="display:none" id="panelFluidez">
                                            <label for="inputScrap">Indice de fluidez</label>
                                            <form:input type="number" step="any" path="indiceDeFluidez" class="form-control"
                                                placeholder="" id="holderScrap" />
                                        </div>                                        
                                    </div>
                                            
                                    <div class="form-row row">
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <button type="button" class="btn btn-primary" onClick="callController()">${buttonLabel}</button>
                                            <a href="/thyssenplastic/tipo"><button type="button" class="btn btn-secondary">Cancelar</button></a>
                                        </div>
                                    </div>
                                        
                                </form:form>
                            </div>
                        </div>
                    </div>                
                </ol>
            </div>

            <hr>           
                            
            <p></p>
            
            <div class="card">

                <div class="card-body">

                    <!--Modal-->
                    <div id="GSCCModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                    <h4 class="modal-title" id="myModalLabel">Eliminar Tipo!</h4>
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
                        <table id="tiposTable" class="display table table-striped table-hover cell-border">
                            <thead>
                                <tr>
                                    <th>CODIGO</th>
                                    <th>TIPO</th>                                    
                                    <th class="hidden-xs hidden-sm">VALOR</th>
                                    <th style="text-align: center">ACCIONES</th>
                                </tr>
                            </thead>

                            <tbody>

                                <c:forEach  items="${tipos}" var="tipo">
                                    <tr>
                                        <td><c:out value="${tipo.pk}" /></td>
                                        <td><c:out value="${tipo.tipo}" /></td>
                                        <td class="hidden-xs hidden-sm"><c:out value="${tipo.valor}" /></td>
                                        <td>                    
                                            <a class="nav-link active fa fa-pencil-square-o fa-lg" href="/thyssenplastic/tipo/edit/${tipo.pk}" data-toggle="tooltip" data-placement="top" title="Editar"></a>
                                            <a class="nav-link active fa fa-trash fa-lg" href="/thyssenplastic/tipo/remove/${tipo.pk}" data-toggle="tooltip" data-placement="top" title="Eliminar"></a>
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

        var tipo = $("#tipo").val();
        if(tipo == 'tipomateriaprima') {
            document.getElementById("panelDendidad").style.display="block"; 
            document.getElementById("panelFluidez").style.display="block"; 
        } else {
            document.getElementById("panelDendidad").style.display="none";    
            document.getElementById("panelFluidez").style.display="none"; 
        }
        
        $('#tiposTable').DataTable({
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
            },           
            order: [[1, 'asc'], [2, 'asc']]
        });
    });
    
    function verificarDatos(){
        
        var tipo = $("#tipo").val();
        if(tipo == 'tipomateriaprima') {
            document.getElementById("panelDendidad").style.display="block"; 
            document.getElementById("panelFluidez").style.display="block"; 
        } else {
            document.getElementById("panelDendidad").style.display="none";    
            document.getElementById("panelFluidez").style.display="none"; 
        }
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




