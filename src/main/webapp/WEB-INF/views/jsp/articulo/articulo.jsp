<%-- 
    Document   : index
    Created on : 9 ago. 2022, 17:43:42
    Author     : waltergustavorojo
--%>
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
                                    <li class="breadcrumb-item"><a href="">Artículos</a></li>
                                    <li class="breadcrumb-item active">${titleArticulo}</li>
                                </ol>
                            </div>

                            <div class="card-body">
                                <div class="container">

                                    <form:form class="form-horizontal" method="post"
                                        action="/thyssenplastic/articulo/addOrEditOrRemove"
                                        modelAttribute="articuloForm" id="myForm">

                                        <p></p>
                                        ${articuloName}
                                        <p></p>

                                        <form:hidden path="pk" class="form-control"/>
                                        <form:hidden path="action" class="form-control"/>
 
                                        <div class="form-row row">
                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                <label for="inputFecha">Fecha Alta</label>
                                                <form:input type="date" path="fechaAlta" class="form-control" placeholder=""
                                                    id="holderDateOfBirth" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                            </div>
                                        </div>
                                        <div class="form-row row">
                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                <label for="inputRubro">Cliente</label>
                                                <form:select path="idCliente" class="form-control rubro" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')">
                                                    <form:options items="${clienteList}" />
                                                </form:select>
                                            </div>
                                        </div>
                                        <div class="form-row row">
                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                <label for="inputDenominacion">Denominación</label>
                                                <form:input type="text" path="denominacion" class="form-control"
                                                    placeholder="" id="holderDenominacion" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                            </div>
                                        </div>

                                        <div class="form-row row">
                                            <!--
                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                <label for="inputCodigo">Código</label>
                                                <form:input type="text" path="codigo" class="form-control"
                                                    placeholder="" id="holderCodigo" />
                                            </div>
                                            -->
                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                <label for="inputRubro">Rubro</label>
                                                <form:select path="idRubro" class="form-control rubro">
                                                    <form:options items="${rubroList}" />
                                                </form:select>
                                            </div>
                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                <!-- Default checked -->
                                                <label class="bs-switch">
                                                    <input type="checkbox" checked>
                                                    <span class="slider round">Activo</span>
                                                </label>
                                                <form:select path="activo" class="form-control rubro">
                                                    <form:option value="0">No</form:option>
                                                    <form:option value="1">Si</form:option>                                                                                                                    
                                                </form:select>                                                                                           
                                            </div>
                                        </div>
                                        <p></p>

                                        <!--Características-->
                                        <div class="form-row row">
                                            <div class="card row col-xs-6 col-sm-6 col-xl-6">
                                                <div class="card-header">
                                                    <ol class="breadcrumb mb-1 mt-1">
                                                        <li class="breadcrumb-item"><a href="">Características</a></li>
                                                    </ol>
                                                </div>
                                                <div class="card-body">
                                                    <div class="form-row row">
                                                        <div class="row col-xs-6 col-sm-6 col-xl-6">
                                                            <label for="inputUnidadesVenta">Unidades de Venta</label>
                                                            <form:select path="idUnidadesVenta"
                                                                class="form-control unidadesVenta">
                                                                <form:options items="${unidadesVentaList}" />
                                                            </form:select>
                                                        </div>
                                                    </div>
                                                    <div class="form-row row">
                                                        <div class="row col-xs-6 col-sm-6 col-xl-6">
                                                            <label for="inputTipoMaterial">Tipo de Material</label>
                                                            <form:select path="idTipoMaterial"
                                                                class="form-control tipoMaterial">
                                                                <form:options items="${tipoMaterialList}" />
                                                            </form:select>
                                                        </div>
                                                    </div>
                                                    <div class="form-row row">
                                                        <div class="row col-xs-6 col-sm-6 col-xl-6">
                                                            <label for="inputScrap">Scrap (%)</label>
                                                            <form:input type="number" step="any" path="scrap" class="form-control"
                                                                placeholder="" id="holderScrap" />
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!--Detalles Material-->
                                            <div class="card row col-xs-6 col-sm-6 col-xl-6">
                                                <div class="card-header">
                                                    <ol class="breadcrumb mb-1 mt-1">
                                                        <li class="breadcrumb-item"><a href="">Detalles</a></li>
                                                    </ol>
                                                </div>
                                                <div class="card-body">
                                                    <div class="form-row row">
                                                        <div class="row col-xs-6 col-sm-6 col-xl-6">
                                                            <label for="inputScrap">Espesor (micrones)</label>
                                                            <form:input type="number" step="any" path="espesor" class="form-control"
                                                                placeholder="" id="holderScrap" />
                                                        </div>
                                                    </div>
                                                    <div class="form-row row">
                                                        <div class="row col-xs-6 col-sm-6 col-xl-6">
                                                            <label for="inputScrap">Ancho (mm)</label>
                                                            <form:input type="number" step="any" path="ancho" class="form-control"
                                                                placeholder="" id="holderScrap" />
                                                        </div>
                                                    </div>

                                                    <div class="form-row row">
                                                        <div class="row col-xs-6 col-sm-6 col-xl-6">
                                                            <label for="inputScrap">Alto (mm)</label>
                                                            <form:input type="text" step="any" path="alto" class="form-control"
                                                                placeholder="" id="holderScrap" />
                                                        </div>
                                                    </div>

                                                    <div class="form-row row">
                                                        <div class="row col-xs-6 col-sm-6 col-xl-6">
                                                            <!-- Default checked -->
                                                            <label class="bs-switch">
                                                                <input type="checkbox" checked>
                                                                <span class="slider round">Tubo</span>
                                                            </label>
                                                            <form:select path="tubo" class="form-control rubro">
                                                                <form:option value="0">No</form:option>
                                                                <form:option value="1">Si</form:option>                                                                
                                                            </form:select>                                                                                                                        
                                                        </div>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>

                                </div>
                                
                                <div class="form-row row">
                                    <div class="row col-xs-9 col-sm-12 col-xl-4">
                                        <label for="inputObservaciones">Observaciones</label>
                                        <form:input type="text" path="observaciones" class="form-control" placeholder=""
                                            id="holderObservaciones" />
                                    </div>
                                </div>
                                <div class="form-row row">
                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                        <label for="inputReferenciaAdmin">Referencia Administrativa</label>
                                        <form:input type="text" path="referenciaAdministrativa" class="form-control" placeholder=""
                                            id="holderReferenciaAdmin" />
                                    </div>
                                </div>
                                <p></p>
                                <div class="form-row row">
                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                        <button type="button" class="btn btn-primary" onClick="callController()">${buttonLabel}</button>
                                        <a href="/thyssenplastic/articulo"><button type="button"
                                                class="btn btn-secondary">Cancelar</button></a>
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

                        <!-- <h4 style="text-align: center;">Reparación Máquina XXX desde XXX hasta XXX</h4> -->
                        <div class="row col-xs-12 col-sm-12 col-xl-12">
                            <table id="articulosTable" class="display table table-striped table-hover cell-border">
                                <thead>
                                    <tr>
                                        <th>CODIGO</th>
                                        <th>CLIENTE</th>
                                        <th>DENOMINACION</th>
                                        <th>RUBRO</th>
                                        <th>ANCHO(mm)</th>
                                        <th>ALTO(mm)</th>
                                        <th>ESPESOR(mic)</th>
                                        <th>SCRAP(%)</th>
                                        <th>REF ADMIN</th>
                                        <th style="text-align: center">ACCIONES</th>
                                    </tr>
                                </thead>

                                <tbody>

                                    <c:forEach items="${articulos}" var="articulo">
                                        <tr>
                                            <td>
                                                <c:out value="${articulo.pk}" />
                                            </td>                                            
                                            <td>
                                                <c:out value="${articulo.cliente}" />
                                            </td>
                                            <td>
                                                <c:out value="${articulo.denominacion}" />
                                            </td>
                                            <td>
                                                <c:out value="${articulo.rubro}" />
                                            </td>                                            
                                            <td>
                                                <c:out value="${articulo.ancho}" />
                                            </td>
                                            <td>
                                                <c:out value="${articulo.alto}" />
                                            </td>
                                            <td>
                                                <c:out value="${articulo.espesor}" />
                                            </td>
                                            <td>
                                                <c:out value="${articulo.scrap}" />
                                            </td>
                                            <td>
                                                <c:out value="${articulo.referenciaAdministrativa}" />
                                            </td>                                            
                                            <td>
                                                <a class="nav-link active fa fa-pencil-square-o fa-lg"
                                                    href="/thyssenplastic/articulo/edit/${articulo.pk}"
                                                    data-toggle="tooltip" data-placement="top" title="Editar"></a>
                                                <a class="nav-link active fa fa-trash fa-lg"
                                                    href="/thyssenplastic/articulo/remove/${articulo.pk}"
                                                    data-toggle="tooltip" data-placement="top" title="Eliminar"></a>
                                                <a class="nav-link active fa fa-tag fa-lg"
                                                    href="/thyssenplastic/articulo/articuloetiqueta/${articulo.pk}"
                                                    data-toggle="tooltip" data-placement="top" title="Etiqueta"></a>
                                                <a class="nav-link active fa fa-umbrella fa-lg"
                                                    href="/thyssenplastic/articulo/articuloprevision/${articulo.pk}"
                                                    data-toggle="tooltip" data-placement="top" title="Prevision"></a>
                                                <a class="nav-link active fa fa-file-text fa-lg"
                                                    href="/thyssenplastic/articulofichatecnica/${articulo.pk}"
                                                    data-toggle="tooltip" data-placement="top" title="Ficha"></a>                                                    
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
        
        $('#articulosTable').DataTable({
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




