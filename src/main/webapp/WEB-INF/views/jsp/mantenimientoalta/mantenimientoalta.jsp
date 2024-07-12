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
                                <li class="breadcrumb-item"><a href="">Mantenimiento Alta</a></li>
                                <li class="breadcrumb-item active">${titleMantenimientoAlta}</li>
                            </ol>
                        </div>
                        <div class="card-body">
                            <div class="container">
                                <ul class="nav nav-tabs">
                                    <!--PESTAÑA ABIERTO-->
                                    <li class="active"><a data-toggle="tab" href="#home">Abierto</a></li>
                                    <!--PESTAÑA CERRADO-->
                                    <li><a data-toggle="tab" href="#menu1">Cerrado</a></li>
                                </ul>

                                <div class="tab-content">
                                    <!--CONTENIDO ABIERTO-->
                                    <div id="home" class="tab-pane fade in active">
                                        <h3>Alta Mantenimiento Correctivo</h3>
                                        <form:form class="form-horizontal" method="post"
                                                   action="/thyssenplastic/mantenimientoalta/addOrEditOrRemove" modelAttribute="mantenimientoAltaForm" id="myForm">

                                            <p></p>
                                            ${mantenimientoAltaName}
                                            <p></p>

                                            <form:hidden path="pk" class="form-control" />
                                            <form:hidden path="action" class="form-control" />

                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputDescripcion">Código</label>
                                                    <form:input path="codigo" class="form-control" placeholder="" required="required"
                                                                oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')" />
                                                </div>
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputDescripcion">Estado</label>
                                                    <form:input path="estado" class="form-control" placeholder="Abierto" required="required"
                                                                oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')" />
                                                </div>
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha_alta">Fecha</label>
                                                    <form:input type="date" path="fechaDesde" class="form-control" placeholder=""
                                                                id="holderDateOfBirth" />
                                                </div>
                                            </div>

                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputRubro">Maquina</label>
                                                    <form:select path="idMaquina" class="form-control rubro">
                                                        <form:options items="${maquinaList}" />
                                                    </form:select>
                                                </div>
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputDescripcion">Componente</label>
                                                    <form:input path="estado" class="form-control" placeholder="Componente" required="required"
                                                                oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')" />
                                                </div>
                                            </div>

                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputDescripcion">Hora Parada</label>
                                                    <form:input path="estado" class="form-control" placeholder="Hora Parada" required="required"
                                                                oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')" />
                                                </div>
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputRubro">Prioridad</label>
                                                    <form:select path="idPrioridad" class="form-control rubro">
                                                        <form:options items="${prioridadList}" />
                                                    </form:select>
                                                </div>
                                            </div>
                                            <div class="form-row row">
                                                <div class="row col-xs-12 col-sm-12 col-xl-12">
                                                    <label for="inputStockMinimo">Stock Mínimo</label>
                                                    <form:input path="stockMinimo" class="form-control" placeholder="" />
                                                </div>
                                            </div>
                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <button type="submit" class="btn btn-primary">${buttonLabel}</button>
                                                    <a href="/thyssenplastic/mantenimientoalta"><button type="button"
                                                                                                        class="btn btn-secondary">Cancelar</button></a>
                                                </div>
                                            </div>

                                        </form:form>
                                    </div>
                                    <!--CONTENIDO CERRADO-->
                                    <div id="menu1" class="tab-pane fade">
                                        <h3>Alta Mantenimiento Correctivo</h3>
                                        <form:form class="form-horizontal" method="post"
                                                   action="/thyssenplastic/mantenimientoalta/addOrEditOrRemove" modelAttribute="mantenimientoAltaForm">

                                            <p></p>
                                            ${mantenimientoAltaName}
                                            <p></p>

                                            <form:hidden path="pk" class="form-control" />
                                            <form:hidden path="action" class="form-control" />

                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">

                                                </div>
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputDescripcion">Estado</label>
                                                    <form:input path="estado" class="form-control" placeholder="Cerrado" required="required"
                                                                oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')" />
                                                </div>
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha_alta">Fecha</label>
                                                    <form:input type="date" path="fecha" class="form-control" placeholder=""
                                                                id="holderDateOfBirth" />
                                                </div>
                                            </div>

                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputDescripcion">Hora Arranque</label>
                                                    <form:input path="horaArranque" class="form-control" placeholder="" required="required"
                                                                oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')" />
                                                </div>
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputRubro">Repuesto</label>
                                                    <form:select path="idRepuesto" class="form-control rubro">
                                                        <form:options items="${repuestoList}" />
                                                    </form:select>
                                                </div>
                                            </div>


                                            <div class="form-row row">
                                                <div class="row col-xs-12 col-sm-12 col-xl-12">
                                                    <label for="inputStockMinimo">Actividad Realizada</label>
                                                    <form:input path="actividadRealizada" class="form-control" placeholder="" />
                                                </div>
                                            </div>
                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputStockMinimo">Tiempo Reparacion</label>
                                                    <form:input path="tiempoReparacion" class="form-control" placeholder="" />
                                                </div>
                                            </div>
                                            <div class="form-row row">
                                                <div class="row col-xs-12 col-sm-12 col-xl-12">
                                                    <label for="inputStockMinimo">Observación</label>
                                                    <form:input path="observacion" class="form-control" placeholder="" />
                                                </div>
                                            </div>
                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <button type="button" class="btn btn-primary" onclick="callController()">${buttonLabel}</button>
                                                    <a href="/thyssenplastic/mantenimientoalta"><button type="button"
                                                                                                        class="btn btn-secondary">Cancelar</button></a>
                                                </div>
                                            </div>

                                        </form:form>
                                    </div>
                                </div>

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
                                    <h4 class="modal-title" id="myModalLabel">Eliminar Máquina!</h4>
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
                        <table id="mantenimientoaltaTable" class="display table table-striped table-hover cell-border">
                            <thead>
                                <tr>
                                    <th>FECHA</th>
                                    <th>CODIGO</th>                                    
                                    <th>MAQUINA</th>
                                    <th>ESTADO</th>
                                    <th style="text-align: center">ACCIONES</th>
                                </tr>
                            </thead>

                            <tbody>

                                <c:forEach  items="${mantenimientoaltas}" var="mantenimientoalta">
                                    <tr>
                                        <td><c:out value="${mantenimientoalta.fecha}" /></td>
                                        <td><c:out value="${mantenimientoalta.codigo}" /></td>
                                        <td><c:out value="${mantenimientoalta.maquina}" /></td>
                                        <td><c:out value="${mantenimientoalta.estado}" /></td>
                                        <td><c:out value="${mantenimientoalta.acciones}" /></td>
                                        <td>                                                    
                                            <a class="nav-link active fa fa-pencil-square-o fa-lg" href="/thyssenplastic/mantenimientoalta/edit/${mantenimientoalta.pk}" data-toggle="tooltip" data-placement="top" title="Editar"></a>
                                            <a class="nav-link active fa fa-trash fa-lg" href="/thyssenplastic/mantenimientoalta/remove/${mantenimientoalta.pk}" data-toggle="tooltip" data-placement="top" title="Eliminar"></a>
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

        $('#mantenimientoaltaTable').DataTable({
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




