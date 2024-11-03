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
                                    <li class="breadcrumb-item"><a href="">Mantenimiento Correctivo</a></li>
                                    <li class="breadcrumb-item active">${titleMantenimientoCorrectivo}</li>
                                </ol>
                            </div>

                            <div class="card-body">
                                <form:form class="form-horizontal" method="post"
                                        action="/thyssenplastic/mantenimientoCorrectivo/addOrEditOrRemove"
                                        modelAttribute="mantenimientoCorrectivoForm" id="myForm">

                                    <div class="container">


                                        <c:set var = "disabledAlta" value = "true"/>
                                        <c:set var = "disabledCierre" value = "true"/>
                                        <c:set var = "cerradoTab" value = "#cerradoTab"/>
                                        <c:set var = "disabledHoraArranque" value = "true"/>
                                        <c:set var = "disabledIntervaloReparacion" value = "true"/>
                                        <c:if test = "${operacion == 'alta'}">
                                            <c:set var = "disabledHoraArranque" value = "false"/>                                            
                                            <c:set var = "disabledAlta" value = "false"/>
                                            <c:set var = "disabledCierre" value = "true"/>
                                            <c:set var = "cerradoTab" value = "#"/>
                                        </c:if>
                                        <c:if test = "${operacion == 'cierre'}">
                                            <c:set var = "disabledAlta" value = "true"/>
                                            <c:if test = "${action == 'new'}">
                                                <c:set var = "disabledCierre" value = "true"/>
                                            </c:if>        
                                            <c:if test = "${action == 'edit'}">
                                                <c:set var = "disabledCierre" value = "false"/>
                                            </c:if>                                                    
                                        </c:if>
                                        <c:if test = "${operacion == 'view'}">
                                            <c:set var = "disabledAlta" value = "true"/>
                                            <c:set var = "disabledCierre" value = "true"/>
                                            <c:set var = "cerradoTab" value = "#cerradoTab"/>
                                        </c:if>
                                        
                                        <c:if test = "${action == 'edit'}">
                                            <c:if test = "${operacion != 'cierre'}">
                                                <c:set var = "disabledHoraArranque" value = "false"/>
                                                <c:set var = "disabledIntervaloReparacion" value = "false"/>
                                            </c:if>
                                        </c:if>
                                        
                                        ${action}
                                        
                                        <ul class="nav nav-tabs">
                                            <!--PESTAÑA ABIERTO-->
                                            <li class="active"><a data-toggle="tab" href="#abiertoTab">Abierto</a></li>
                                            <!--PESTAÑA CERRADO-->
                                            <li><a data-toggle="tab" href="${cerradoTab}">Cerrado</a></li>
                                        </ul>
                                        
                                        <p></p>
                                        ${mantenimientoCorrectivoName}
                                        
                                        <p></p>

                                        <form:hidden path="pk" class="form-control"/>
                                        <form:hidden path="action" class="form-control"/>
                                        <form:hidden path="operacion" class="form-control"/>

                                        <div class="tab-content">
                                            <!--CONTENIDO ABIERTO-->
                                            <div id="abiertoTab" class="tab-pane fade in active">
                                        
                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Estado</label>
                                                        <form:input type="text" path="estadoLabel" class="form-control" placeholder="" disabled="true" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                                    </div>
                                                    
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Fecha Alta</label>
                                                        <form:input type="date" path="fechaAlta" class="form-control" placeholder=""
                                                                    id="holderDateOfBirth" required="required" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                                    </div>                                                    
                                                </div>
                                                
                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4" >
                                                        <label for="inputArticulo">Máquina</label>
                                                        <form:select path="idMaquina" class="form-control rubro" required="required" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')">                                                    
                                                            <form:options items="${maquinaList}" />
                                                        </form:select>
                                                    </div>
                                                    
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Componente</label>
                                                        <form:input type="text" path="componente" class="form-control" required="required" placeholder="" disabled="${disabledAlta}" maxlength="255" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                                    </div>                                                    
                                                </div>

                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4" >
                                                        <label for="inputArticulo">Hora Parada</label>
                                                        <form:input type="time" path="horaParada" class="form-control" required="required" placeholder="" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>                                                        
                                                   </div>
                                                    
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">                                                        
                                                        <label for="inputRubro">Prioridad</label>
                                                        <form:select path="prioridad" class="form-control rubro" required="required" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')" onchange="hideDisplayDivs()">
                                                            <form:option value="alta">Alta</form:option>
                                                            <form:option value="media">Media</form:option>
                                                            <form:option value="baja">Baja</form:option>
                                                        </form:select>
                                                    </div>

                                                    </div>                                                    
                                                </div>

                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputRubro">Problema</label>
                                                        <form:textarea type="text" path="problema" class="form-control" required="required" placeholder="" disabled="${disabledAlta}" maxlength="4000" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                                    </div>
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4" >
                                                        <label for="inputArticulo">Hora de arranque</label>
                                                        <form:input type="time" path="horaArranque" class="form-control" placeholder="" disabled="${disabledHoraArranque}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>                 
                                                    </div>
                                                </div>
                                                    
                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Usuario Alta</label>
                                                        <form:input type="text" path="userAlta" class="form-control" placeholder="" disabled="true"/>
                                                    </div>
                                                </div>    
                                            </div>                                        
                                                    
                                            <!--CONTENIDO CERRADO-->
                                            <div id="cerradoTab" class="tab-pane fade">
                                                
                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4" >
                                                        <label for="inputArticulo">Repuesto</label>
                                                        <form:select path="idRepuesto" class="form-control rubro" required="required" disabled="${disabledCierre}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')">                                                    
                                                            <form:options items="${repuestoList}" />
                                                        </form:select>
                                                    </div>
                                                    
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Fecha Cierre</label>
                                                        <form:input type="date" path="fechaFin" class="form-control" placeholder=""
                                                                    id="holderDateOfBirth" required="required" disabled="${disabledCierre}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                                    </div>                                                    
                                                </div>
                                                
                                                <!--    
                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4" >
                                                        <label for="inputArticulo">Hora Arranque</label>
                                                        <form:input type="time" path="horaArranque" class="form-control" required="required" placeholder="" disabled="${disabledCierre}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                                    </div>
                                                </div>
                                                -->
                                                
                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-3" >
                                                        <label for="inputArticulo">Fecha Reparación Desde</label>
                                                        <form:input type="datetime-local" path="fechaDeReparacionDesde" id="fechaDeReparacionDesde" class="form-control" required="required" placeholder="" disabled="${disabledCierre}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>                                                        
                                                    </div>
                                                    <div class="row col-xs-9 col-sm-3 col-xl-3" >
                                                        <label for="inputArticulo">Fecha Reparación Hasta</label>
                                                        <form:input type="datetime-local" path="fechaDeReparacionHasta" id="fechaDeReparacionHasta" class="form-control" required="required" placeholder="" disabled="${disabledCierre}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')" onfocusout="calculateDiffrence()"/>                                                        
                                                    </div>                                                                                                        
                                                </div>                                                    
                                                    
                                                <p>    
                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-3" >
                                                        <label for="inputArticulo">Tiempo de reparación (min)</label>
                                                        <form:input type="number" path="intervaloReparacion" id="intervaloReparacion" class="form-control" required="required" placeholder="" disabled="${disabledIntervaloReparacion}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')" onfocusout="calculateDiffrence()"/>
                                                    </div>                                                    
                                                </div>
                                                <p>    
                                                    
                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4" >
                                                        <label for="inputArticulo">Actividad Realizada</label>
                                                        <form:textarea type="text" path="actividadRealizada" class="form-control" required="required" placeholder="" disabled="${disabledCierre}" maxlength="4000" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>                                                        
                                                    </div>
                                                </div>
                                                    
                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputRubro">Observación</label>
                                                        <form:textarea type="text" path="observacion" class="form-control" placeholder="" disabled="${disabledCierre}" maxlength="4000" />
                                                    </div>
                                                </div>

                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Usuario Cierre</label>
                                                        <form:input type="text" path="userFin" class="form-control" placeholder="" disabled="true"/>
                                                    </div>
                                                </div>    
                                                    
                                            </div>
                                                    
                                        </div>                                                    
                                                    
                                        <p></p>
                                                                                
                                        <div class="form-row row">
                                            <div class="row col-xs-9 col-sm-1 col-xl-1" style="display:${displayActionButton}">
                                                <button type="button" class="btn btn-primary" onclick="callController()">${buttonLabel}</button>                                            
                                            </div>
                                            <div class="row col-xs-9 col-sm-1 col-xl-1">
                                                <a href="/thyssenplastic/mantenimientoCorrectivo"><button type="button" class="btn btn-secondary">Cancelar</button></a>
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
                            <table id="mantenimientoscorrectivoTable" class="display table table-striped table-hover cell-border">
                                <thead>
                                    <tr>
                                        <th>CODIGO</th>
                                        <th>FECHA ALTA</th>
                                        <th>MAQUINA</th>
                                        <th>HORA PARADA</th>
                                        <th>FECHA REPARACION DESDE</th>
                                        <th>FECHA REPARACION HASTA</th>
                                        <th>TIEMPO DE REPARACION</th>
                                        <th>ESTADO</th>
                                        <th style="text-align: center">ACCIONES</th>
                                    </tr>
                                </thead>

                                <tbody>

                                    <c:forEach items="${mantenimientosCorrectivo}" var="mantenimientocorrectivo">
                                        <tr>
                                            <td>
                                                <c:out value="${mantenimientocorrectivo.pk}" />
                                            </td>                                            
                                            <td>
                                                <c:out value="${mantenimientocorrectivo.fechaAlta}" />
                                            </td>                                            
                                            <td>
                                                <c:out value="${mantenimientocorrectivo.maquina}" />
                                            </td>                                                                                        
                                            <td>
                                                <c:out value="${mantenimientocorrectivo.horaParada}" />
                                            </td>                                                                                                                                    
                                            <td>
                                                <c:out value="${mantenimientocorrectivo.fechaDeReparacionDesde}" />
                                            </td>                                                                                                                                                                                
                                            <td>
                                                <c:out value="${mantenimientocorrectivo.fechaDeReparacionHasta}" />
                                            </td>                                                                                                                                                                                
                                            <td>
                                                <c:out value="${mantenimientocorrectivo.intervaloReparacion}" />
                                            </td>                                                                                                                                                                                                                            
                                            <td>
                                                <c:out value="${mantenimientocorrectivo.estado}" />
                                            </td>                                                                                                                                                                                                                            
                                            <td>
                                                <c:if test = "${(operacion == 'cierre' || operacion == 'view' )&& mantenimientocorrectivo.estado == 'Abierto'}">
                                                    <a class="nav-link active fa fa-pencil-square-o fa-lg"
                                                        href="/thyssenplastic/mantenimientoCorrectivo/edit/${mantenimientocorrectivo.pk}"
                                                        data-toggle="tooltip" data-placement="top" title="Editar"></a>
                                                </c:if>                                                        
                                                <a class="nav-link active fa fa-eye fa-lg"
                                                    href="/thyssenplastic/mantenimientoCorrectivo/view/${mantenimientocorrectivo.pk}"
                                                    data-toggle="tooltip" data-placement="top" title="Ver"></a>
                                                    
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
        
        $('#mantenimientoscorrectivoTable').DataTable({
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
            },
            order: [[0, 'desc']]
        });
        
        var tipo = $("#tipo").val();
        if(tipo == 'articulos') {
           document.getElementById("articuloPanel").style.display="block"; 
           document.getElementById("insumoPanel").style.display="none"; 
           document.getElementById("materiaPrimaPanel").style.display="none"; 
        }
        if(tipo == 'insumos') {
           document.getElementById("articuloPanel").style.display="none"; 
           document.getElementById("insumoPanel").style.display="block"; 
           document.getElementById("materiaPrimaPanel").style.display="none"; 
        }        
        if(tipo == 'materiaPrima') {
           document.getElementById("articuloPanel").style.display="none"; 
           document.getElementById("insumoPanel").style.display="none"; 
           document.getElementById("materiaPrimaPanel").style.display="block"; 
        }        
        
    });
    
    function hideDisplayDivs() {
        var tipo = $("#tipo").val();
        if(tipo == 'articulos') {
           document.getElementById("articuloPanel").style.display="block"; 
           document.getElementById("insumoPanel").style.display="none"; 
           document.getElementById("materiaPrimaPanel").style.display="none"; 
        }
        if(tipo == 'insumos') {
           document.getElementById("articuloPanel").style.display="none"; 
           document.getElementById("insumoPanel").style.display="block"; 
           document.getElementById("materiaPrimaPanel").style.display="none"; 
        }        
        if(tipo == 'materiaPrima') {
           document.getElementById("articuloPanel").style.display="none"; 
           document.getElementById("insumoPanel").style.display="none"; 
           document.getElementById("materiaPrimaPanel").style.display="block"; 
        }        
        
    }
    
    function callController() {
        
        var tipo = $("#tipo").val();
        var idMateriaPrima = $("#idMateriaPrima").val();
        var idInsumo = $("#idInsumo").val();
        var idArticulo = $("#idArticulo").val();
        if(tipo == 'materiaPrima' && idMateriaPrima == '-1') {
            alert('Debe seleccinar una Materia Prima');
            return;
        }
        if(tipo == 'insumos' && idInsumo == '-1') {
            alert('Debe seleccinar un Insumo');
            return;
        }        
        if(tipo == 'articulos' && idArticulo == '-1') {
            alert('Debe seleccinar un Articulo');
            return;
        }                
        if($("#myForm")[0].checkValidity()) {
            var form = document.getElementById("myForm");
            form.submit();
	} else {
            console.log("sin submit");
            $("#myForm")[0].reportValidity();
	}        
    }
    
    function calculateDiffrence() {
        
        var desde = $("#fechaDeReparacionDesde").val();
        var hasta = $("#fechaDeReparacionHasta").val();
        

        if (desde == "" || desde == null || hasta == "" || hasta == null) {
            
        } else {
            const date1 = new Date(desde);
            const date2 = new Date(hasta);
            const diffTime = Math.abs(date2 - date1);
            const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
            
            $("#intervaloReparacion").val(diffDays);            
        }        
    }
    
</script>

<%@include file = "/WEB-INF/views/jsp/includes/footer.jsp" %>




