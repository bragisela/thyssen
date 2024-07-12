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
                                    <li class="breadcrumb-item"><a href="">Ajustes de Inventario</a></li>
                                    <li class="breadcrumb-item active">${titleAjusteDeInventario}</li>
                                </ol>
                            </div>

                            <div class="card-body">
                                <form:form class="form-horizontal" method="post"
                                        action="/thyssenplastic/ajusteDeInventario/addOrEditOrRemove"
                                        modelAttribute="ajusteDeInventarioForm" id="myForm">

                                    <div class="container">


                                        <p></p>
                                        ${ajustedeinventarioName}
                                        <p></p>

                                        <form:hidden path="pk" class="form-control"/>
                                        <form:hidden path="action" class="form-control"/>
                                        <form:hidden path="resultadoCantidadHdn" class="form-control"/>
                                        <form:hidden path="resultadoPesoHdn" class="form-control"/>
                                        <form:hidden path="existenteCantidadHdn" class="form-control"/>
                                        <form:hidden path="existentePesoHdn" class="form-control"/>
                                        
 
                                        <div class="form-row row">
                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                <label for="inputFecha">Fecha Alta</label>
                                                <form:input type="date" path="fechaAlta" class="form-control" placeholder=""
                                                    id="holderDateOfBirth" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                            </div>
                                        </div>
                                        <div class="form-row row">
                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                <label for="inputRubro">Tipo</label>
                                                <form:select path="tipo" class="form-control rubro" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')" onchange="hideDisplayDivs()">
                                                    <form:option value="materiaPrima">Materia Prima</form:option>
                                                    <form:option value="insumos">Insumos</form:option>
                                                    <form:option value="articulos">Artículos</form:option>
                                                </form:select>
                                            </div>
                                        </div>
                                        
                                        <div class="form-row row">
                                            <div class="row col-xs-9 col-sm-3 col-xl-4" style="display: none" id="articuloPanel">
                                                <label for="inputArticulo">Artículo</label>
                                                <form:select path="idArticulo" class="form-control rubro" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')" onChange="loadStock('articulo')">
                                                    <form:option value="-1">Seleccionar...</form:option>
                                                    <form:options items="${articuloList}" />
                                                </form:select>
                                            </div>
                                            <div class="row col-xs-9 col-sm-3 col-xl-4" style="display: block" id="materiaPrimaPanel">
                                                <label for="inputMateriaPrima">Materia Prima</label>
                                                <form:select path="idMateriaPrima" class="form-control rubro" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')" onChange="loadStock('materiaprima')">
                                                    <form:option value="-1">Seleccionar...</form:option>
                                                    <form:options items="${materiaPrimaList}" />
                                                </form:select>
                                            </div>
                                            <div class="row col-xs-9 col-sm-3 col-xl-4" style="display: none" id="insumoPanel">
                                                <label for="inputInsumo">Insumo</label>
                                                <form:select path="idInsumo" class="form-control rubro" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')" onChange="loadStock('insumo')">
                                                    <form:option value="-1">Seleccionar...</form:option>
                                                    <form:options items="${insumoList}" />
                                                </form:select>
                                            </div>
                                            
                                        </div>
                                            
                                        <div class="form-row row">
                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                <label for="inputExistentePeso">Existente (Peso - kg)</label>
                                                <form:input type="text" path="existentePeso" class="form-control" placeholder="" id="existentePeso" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')" disabled="true"/>                                                                                                
                                            </div>
                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                <label for="inputExistenteCantidad">Existente (Cantidad - unidades)</label>
                                                <form:input type="text" path="existenteCantidad" class="form-control" placeholder="" id="existenteCantidad" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')" disabled="true"/>                                                
                                            </div>                                            
                                        </div>

                                        <div class="form-row row">
                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                <label for="inputAjustePeso">Ajuste (Peso - kg)</label>
                                                <form:input type="number" step="any" path="ajustePeso" class="form-control" placeholder="" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')" onChange="calcularResultadoPeso()"/>                                                
                                            </div>
                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                <label for="inputAjusteCantidad">Ajuste (Cantidad - unidades)</label>
                                                <form:input type="number" step="any" path="ajusteCantidad" class="form-control" placeholder="" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')" onChange="calcularResultadoCantidad()"/>                                                
                                            </div>                                            
                                        </div>

                                        <div class="form-row row">
                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                <label for="inputResultadoPeso">Resultado (Peso - kg)</label>
                                                <form:input type="text" path="resultadoPeso" class="form-control" placeholder="" id="resultadoPeso" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')" disabled="true"/>                                                
                                            </div>
                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                <label for="inputResultadoCantidad">Resultado (Cantidad - unidades)</label>
                                                <form:input type="text" path="resultadoCantidad" class="form-control" placeholder="" id="resultadoCantidad" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')" disabled="true"/>                                                
                                            </div>                                            
                                        </div>

                                        <div class="form-row row">
                                            <div class="row col-xs-9 col-sm-12 col-xl-4">
                                                <label for="inputObservaciones">Motivo del Ajuste</label>
                                                <form:input type="text" path="motivo" class="form-control" placeholder=""
                                                    id="holderMotivo" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                            </div>
                                        </div>

                                        <p></p>
                                        
                                        <div class="form-row row" style="display:${displayUser}">
                                            <div class="row col-xs-9 col-sm-12 col-xl-4">
                                                <label for="inputObservaciones">Usuario</label>
                                                ${userName}
                                                                                                
                                            </div>
                                        </div>
                                            
                                        <p></p>

                                        <div class="form-row row">
                                            <div class="row col-xs-9 col-sm-1 col-xl-1" style="display:${displayActionButton}">
                                                <button type="button" class="btn btn-primary" onclick="callController()">${buttonLabel}</button>                                            
                                            </div>
                                            <div class="row col-xs-9 col-sm-1 col-xl-1">
                                                <a href="/thyssenplastic/ajusteDeInventario"><button type="button" class="btn btn-secondary">Cancelar</button></a>
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
                            <table id="ajustesdeinventarioTable" class="display table table-striped table-hover cell-border">
                                <thead>
                                    <tr>
                                        <th>CODIGO</th>
                                        <th>FECHA ALTA</th>
                                        <th>TIPO</th>
                                        <th>DESCRIPCION</th>
                                        <th>EXISTENTE</th>
                                        <th>AJUSTE</th>
                                        <th>RESULTADO</th>
                                        <th style="text-align: center">ACCIONES</th>
                                    </tr>
                                </thead>

                                <tbody>

                                    <c:forEach items="${ajustesDeInventario}" var="ajustedeinventario">
                                        <tr>
                                            <td>
                                                <c:out value="${ajustedeinventario.pk}" />
                                            </td>                                            
                                            <td>
                                                <c:out value="${ajustedeinventario.fechaAlta}" />
                                            </td>
                                            <td>
                                                <c:out value="${ajustedeinventario.tipo}" />
                                            </td>
                                            <td>
                                                <c:out value="${ajustedeinventario.descripcion}" />
                                            </td>
                                            <td>
                                                <c:out value="${ajustedeinventario.existentePeso} (kg) - ${ajustedeinventario.existenteCantidad} (unid.)" />
                                            </td>                                            
                                            <td>
                                                <c:out value="${ajustedeinventario.ajustePeso} (kg) - ${ajustedeinventario.ajusteCantidad} (unid.)" />
                                            </td>                                            
                                            <td>
                                                <c:out value="${ajustedeinventario.resultadoPeso} (kg) - ${ajustedeinventario.resultadoCantidad} (unid.)" />
                                            </td>                                                                                        
                                            <td>
                                                <a class="nav-link active fa fa-eye fa-lg"
                                                    href="/thyssenplastic/ajusteDeInventario/view/${ajustedeinventario.pk}"
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
                
        $('#ajustesdeinventarioTable').DataTable({
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
            }            
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
        $("#idMateriaPrima").val("-1");
        $("#idArticulo").val("-1");
        $("#idInsumo").val("-1");
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
            $("#myForm")[0].reportValidity();
	}        
    }
    
    function loadStock(tipo) {
        
        var idTipo = '-1';
        if(tipo == 'materiaprima') {
            idTipo = $('#idMateriaPrima').val();
        }
        if(tipo == 'articulo') {
            idTipo = $('#idArticulo').val();
        }
        if(tipo == 'insumo') {
            idTipo = $('#idInsumo').val();
        }                
        
        $.ajax({
            url: '/thyssenplastic/ajusteDeInventario/loadStock/'+tipo+'/id/'+idTipo,
            type: 'GET',
            cache: false,
            contentType: false,
            processData: false,
            success: function (response) {
                if (response.length > 0) {
                    var lines = response.split('|');

                    if(lines != 'nn|nn') {
                        $('#existentePeso').val(lines[1]);
                        $('#existenteCantidad').val(lines[0]);
                        $('#existentePesoHdn').val(lines[1]);
                        $('#existenteCantidadHdn').val(lines[0]);

                        calcularResultadoPeso(tipo, idTipo);
                        calcularResultadoCantidad(tipo, idTipo);
                    } else {
                        alert('No es posible calcular stock.');
                        $('#existentePesoHdn').val('');
                        $('#existenteCantidadHdn').val('');                        
                    }
                }
            }
        });        
    }
    
    function calcularResultadoPeso(tipo, idTipo) {

        var resultado = '';
        if($('#existentePeso').val() != null && $('#existentePeso').val() != '' && $('#ajustePeso').val() != null && $('#ajustePeso').val() != '') {
            var existentepeso = parseFloat($('#existentePeso').val());
            var ajustepeso = parseFloat($('#ajustePeso').val());
            resultado = existentepeso + ajustepeso;
            $('#resultadoPeso').val(resultado);
            $('#resultadoPesoHdn').val(resultado);
        } else {
            $('#resultadoPeso').val('');
            $('#resultadoPesoHdn').val('');
        }        
    }

    function calcularResultadoCantidad(tipo, idTipo) {
        
        var resultado = '';
        if($('#existenteCantidad').val() != null && $('#existenteCantidad').val() != '' && $('#ajusteCantidad').val() != null && $('#ajusteCantidad').val() != '') {
            var existentepeso = parseFloat($('#existenteCantidad').val());
            var ajustepeso = parseFloat($('#ajusteCantidad').val());
            resultado = existentepeso + ajustepeso;
            $('#resultadoCantidad').val(resultado);
            $('#resultadoCantidadHdn').val(resultado);
        } else {
            $('#resultadoCantidad').val('');
            $('#resultadoCantidadHdn').val('');
        }        

        /*
        $.ajax({
            url: '/thyssenplastic/ajusteDeInventario/setResultadoCantidad/tipo/'+tipo+'/idTipo/'+idTipo+'/resultado/'+resultado,
            type: 'GET',
            cache: false,
            contentType: false,
            processData: false,
            success: function (response) {
                if (response.length > 0) {
                }
            }
        });                                 
         */
    }

</script>

<%@include file = "/WEB-INF/views/jsp/includes/footer.jsp" %>




