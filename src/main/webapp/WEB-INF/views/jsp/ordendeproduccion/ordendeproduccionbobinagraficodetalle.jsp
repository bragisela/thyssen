<%@include file = "/WEB-INF/views/jsp/includes/header.jsp" %>
    
<style>
    
    .nav-tabs li.disabled a {
        pointer-events: none;
    }
</style>

   
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
                                    <li class="breadcrumb-item"><a href="">Gráfico Bobina Detalle</a></li>
                                    <li class="breadcrumb-item active">${titleGraficoBobinaDetalle}</li>
                                </ol>
                            </div>

                            <div class="card-body">
                                <form:form class="form-horizontal" method="post"
                                        action="/thyssenplastic/graficoBobinaDetalle/addOrEditOrRemove"
                                        modelAttribute="graficoBobinaDetalleForm" id="myForm">

                                    <div class="container">


                                        <c:set var = "disabledAlta" value = "true"/>
                                        <c:if test = "${operacion == 'alta'}">
                                            <c:set var = "disabledAlta" value = "false"/>
                                        </c:if>
                                        <c:if test = "${operacion == 'view'}">
                                            <c:set var = "disabledAlta" value = "true"/>                                                                                  
                                        </c:if>
                                        <c:if test = "${operacion == 'edit'}">
                                            <c:set var = "disabledAlta" value = "false"/>                                                                                     
                                        </c:if>
                                        <c:if test = "${operacion == 'remove'}">
                                            <c:set var = "disabledAlta" value = "true"/>                                                                                  
                                        </c:if>

                                        
                                        ${action}
                                        
                                        <p></p>
                                        ${graficoBobinaDetalleName}
                                        
                                        <p></p>

                                        <form:hidden path="pk" class="form-control"/>
                                        <form:hidden path="action" class="form-control"/>
                                        <form:hidden path="operacion" class="form-control"/>
                                        <form:hidden path="idGraficoBobina" class="form-control"/>
                                        <form:hidden path="idOrdenDeProduccion" class="form-control"/>
                                        <form:hidden path="idBobina" class="form-control"/>

                                        <div class="tab-content">

                                            <div class="form-row row">                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <a href="/thyssenplastic/graficoBobina/${idOrdenDeProduccionBobina}" id="ordprod">Atrás</a>
                                                </div>                                                                                                    
                                            </div>
                                            
                                            <p>
                                                
                                            <div class="form-row row">                                                
                                                <div class="row col-xs-12 col-sm-12 col-xl-12">
                                                    <label for="inputFecha">Carga Código <i>${idGraficoBobina} - Fecha ${graficoBobinaFecha}</i></label>
                                                </div>                                                
                                                <div class="row col-xs-12 col-sm-12 col-xl-12">
                                                    <label for="inputFecha">Bobina <i>${idBobina}</i></label>
                                                </div>
                                                <div class="row col-xs-12 col-sm-12 col-xl-12">
                                                    <label for="inputFecha">Orden de Producción <i>${idOrdenDeProduccion}</i> - Cliente <i>${clienteLabel}</i> - Artículo <i>${idArticulo} (${articuloLabel})</i> - Ficha Técnica Versión <i>${fichaTecnicaVersionLabel}</i></label>
                                                </div>                                                                                                    
                                            </div>
                                                
                                            <p>
                                                
                                            <div class="form-row row">
                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Peso Cono (kg)</label>
                                                    <form:input type="number" path="pesoCono" class="form-control" placeholder="" disabled="true"/>
                                                </div>                                                    

                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Peso Total (kg)</label>
                                                    <form:input type="number" path="pesoTotal" class="form-control" placeholder="" disabled="true"/>
                                                </div>                                                    
                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Peso Neto (kg)</label>
                                                    <form:input type="number" path="pesoNeto" class="form-control" placeholder="" disabled="true"/>
                                                </div>                                                    

                                            </div>          
                                            
                                            <p>&nbsp;</p>
                                                                                            
                                        </div>                                        
                                        
                                        <p></p>
                                        <hr>
                                        <p></p>

                                        <div class="tab-content">
                                                                                    
                                            <div class="form-row row">                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Carga Valores Detalle</label>
                                                </div>                                                                                                    
                                            </div>
                                                
                                            <p>
                                                                                            
                                            <div class="form-row row">                       

                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputRubro">Valor</label>
                                                    <form:input type="number" path="valor" step="0.001" class="form-control" placeholder="" required="required" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                                </div>
                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Tipo Ingreso</label>
                                                    <form:select path="formato" class="form-control rubro" required="required" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')">                                                    
                                                        <form:option value="manual">Manual</form:option>
                                                        <form:option value="scanner">Scanner</form:option>
                                                    </form:select>
                                                </div>
                                                
                                                <!--
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputRubro">Angulo</label>
                                                    <form:input type="number" path="angulo" step="0.01" class="form-control" placeholder="" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                                </div>
                                                -->
                                            </div>
  
                                        </div>
                                                
                                        <p></p> 

                                        <div class="form-row row">
                                            <div class="row col-xs-9 col-sm-1 col-xl-1" style="display:${displayActionButton}">
                                                <button type="button" class="btn btn-primary" onclick="callController()">${buttonLabel}</button>                                            
                                            </div>
                                            <div class="row col-xs-9 col-sm-1 col-xl-1">
                                                <a href="/thyssenplastic/graficoBobinaDetalle/${idGraficoBobina}/tipoIngreso/manual"><button type="button" class="btn btn-secondary">Cancelar</button></a>
                                            </div>    
                                        </div>
                                            
                                    </div>
                                
                                </form:form>
                            </div>
                        </div>
                    </ol>
                </div>
                
                <hr>

                <div class="card">

                    <div class="card-body">

                        <div class="row col-xs-12 col-sm-12 col-xl-12">
                            <table id="graficosBobinaDetalleTable" class="display table table-striped table-hover cell-border">
                                <thead>
                                    <tr>
                                        <th><input type="checkbox" id="selectAll" /></th> 
                                        <th>CODIGO</th>
                                        <th>VALOR</th>
                                        <th>ANGULO</th>
                                        <th style="text-align: center">ACCIONES</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${graficoBobinaDetalles}" var="graficoBobinaDetalle">
                                        <tr>
                                            <td>
                                                <input type="checkbox" class="selectItem" value="${graficoBobinaDetalle.pk}" />
                                            </td>
                                            <td><c:out value="${graficoBobinaDetalle.medicion}" /></td>
                                            <td><c:out value="${graficoBobinaDetalle.valor}" /></td>
                                            <td><c:out value="${graficoBobinaDetalle.angulo}" /></td>
                                            <td>
                                                <c:if test = "${rol == 'oficina' && operacion == 'alta'}">
                                                    <a class="nav-link active fa fa-pencil-square-o fa-lg"
                                                        href="/thyssenplastic/graficoBobinaDetalle/edit/${graficoBobinaDetalle.pk}"
                                                        data-toggle="tooltip" data-placement="top" title="Editar"></a>
                                                </c:if>                                                        
                                                <c:if test = "${rol == 'oficina' && operacion == 'alta'}">
                                                    <a class="nav-link active fa fa-trash fa-lg"
                                                        href="/thyssenplastic/graficoBobinaDetalle/remove/${graficoBobinaDetalle.pk}"
                                                        data-toggle="tooltip" data-placement="top" title="Eliminar"></a>
                                                </c:if>                                                                                                      
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>

                        </div>
                        <div class="row mt-3">
                            <div class="col text-center">
                                <button id="deleteSelectedBtn" type="button" class="btn btn-danger">Eliminar seleccionados</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
                  

<!-- ./wrapper -->

<script>
    $(document).ready(function () {

        $('#graficosBobinaDetalleTable').DataTable({
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
            },
            order: [[0, 'desc']]
        });

        // Checkbox: Seleccionar/Deseleccionar todos
        $("#selectAll").click(function () {
            $(".selectItem").prop('checked', $(this).prop('checked'));
        });

        // Botón Eliminar Seleccionados
        $("#deleteSelectedBtn").click(function () {
            let selected = [];
            $(".selectItem:checked").each(function () {
                selected.push($(this).val());
            });

            if (selected.length === 0) {
                alert("Debe seleccionar al menos un registro.");
                return;
            }

            if (confirm('¿Está seguro de eliminar los registros seleccionados?')) {
                // Ids a enviar al nuevo metodo del back deletedSelected
                $.ajax({
                    url: '/thyssenplastic/graficoBobinaDetalle/deleteSelected',
                    method: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify({ ids: selected }),
                    success: function (response) {
                        alert(response); 
                        location.reload();
                    },
                    error: function () {
                        alert('Ocurrió un error al intentar eliminar los registros.');
                    }
                });
            }
        });
    });
</script>
   
<%@include file = "/WEB-INF/views/jsp/includes/footer.jsp" %>




