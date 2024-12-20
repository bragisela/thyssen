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
                                    <li class="breadcrumb-item"><a href="">Remito Detalle</a></li>
                                    <li class="breadcrumb-item active">${titleRemitoDetalle}</li>
                                </ol>
                            </div>
                            <div class="form-row row">                                                
                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                    <a href="/thyssenplastic/remito/" id="ordcomp">Atrás</a>
                                </div>                                                                                                    
                            </div>
                        </div>
                    </ol>
                </div>
                                
                <div class="col-md-12">
                    <div class="containerr">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="col-md-6">
                                    <div class="data">
                                        <label class="labelClass" >Remito:</label><span class="spanClass">${remito.codigoRemito}</span>
                                    </div>
                                    <div class="data">
                                        <label class="labelClass">Tipo: </label><span class="spanClass">${remito.tipoRemito}</span>
                                    </div>
                                    <div class="data">
                                        <label class="labelClass">Fecha Remito:</label><span class="spanClass">${remito.fechaRemito}</span>
                                    </div>
                                    <div class="data">
                                        <label class="labelClass">Estado:</label><span class="spanClass">${remito.estadoRemito}</span>
                                    </div>
                                </div>
                            <div class="col-md-6">
                                <div class="data">
                                    <label class="labelClass">Cliente: </label><span class="spanClass">${remito.cliente}</span>
                                </div>
                                <div class="data">
                                    <label class="labelClass">Domicilio: </label><span class="spanClass">${remito.domicilio}</span>
                                </div>
                                <div class="data">
                                    <label class="labelClass">Transporte: </label><span class="spanClass">${remito.transporte}</span>
                                </div>
                                 <div class="data">
                                    <label class="labelClass">Chofer: </label><span class="spanClass">${remito.idChofer}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <hr>
                <div class="card-body">
                            <div class="container">
                                
                                <div class="row col-xs-12 col-sm-12 col-xl-12">
                                    <div class="modal fade rounded" id="miModalEgreso" tabindex="-1" role="dialog" aria-labelledby="miModalLabel" aria-hidden="true" data-backdrop="static">
                                        <div class="modal-dialog modal-lg modal-dialog-centered">
                                            <div class="modal-content">
                                                <div class="modal-header bg-info text-white">
                                                    <h4 class="modal-title text-center" id="miModalLabel">Egreso </h4>
                                                </div>

                                                <div class="modal-body">
                                                    <div class="row">
                                                        <div class="col-xs-6 col-sm-6 col-xl-6">
                                                            <div class="articulo-section border rounded p-3 mb-3 bg-light">
                                                                <h5 class="text-center text-info">Artículo</h5>
                                                                <ul class="list-group">
                                                                    <!-- Datos del artículo (puedes reemplazar estos ejemplos con tus propios datos) -->
                                                                    <li class="list-group-item">Articulo 2<span id="denominacion" class="font-weight-bold text-success"></span></li>
                                                                    <li class="list-group-item">Lote: <span id="codigoOrdenProduccion" class="font-weight-bold text-primary"></span></li>
                                                                    <li class="list-group-item">Deposito: <span id="deposito" class="font-weight-bold text-primary"></span></li>
                                                                    <form:form class="form-horizontal" method="post" action="/thyssenplastic/egresoDeposito/addOrEditOrRemove" modelAttribute="egresoDepositoForm" id="myFormm">
                                                                    <form:hidden path="pk" class="form-control"/>
                                                                    <form:hidden path="action" class="form-control"/>
                                                                    <form:hidden path="idBulto" class="form-control"/>
                                                                    <form:hidden path="idBobina" class="form-control"/>
                                                                    <form:hidden path="idPallet" class="form-control"/>
                                                                    <form:hidden path="idRemito" class="form-control"/>
                                                                    <form:hidden path="depositoActual" class="form-control"/>
                                                                    <form:hidden path="tipo" class="form-control"/>
                                                                   <form:hidden path="idOrdenDeProduccionE" class="form-control"/>
                                                                   <form:hidden path="codigos" id="codigos" class="form-control" />
                                                                </ul>
                                                            </div>
                                                            
                                                            <div class="articulo-section border rounded p-3 mb-3 bg-light">
                                                                <h5 class="text-center text-info">Datos</h5>
                                                                <ul class="list-group">
                                                                    <!-- Datos del artículo (puedes reemplazar estos ejemplos con tus propios datos) -->
                                                                    <li class="list-group-item">Peso: <span id="pesoTotal"> 0 </span><span class="font-weight-bold text-primary"> Kgrs</span></li>
                                                                    <li class="list-group-item">Cantidad: <span id="unidades"> 0 </span><span id="dato2" class="font-weight-bold text-primary"> Unidades</span></li>
                                                                </ul>
                                                            </div>
                                                        </div>

                                                        <div class="col-xs-6 col-sm-6 col-xl-6">
                                                            <div class="articulo-section border rounded p-3 mb-3 bg-light"> 
                                                                <c:choose>
                                                                    <c:when test="${statusAct ne 'none'}">
                                                                        <div class="form-row row">
                                                                            <div class="col-xs-12 col-sm-6 col-md-6">
                                                                                <label for="inputFecha_alta" style="padding-top: 10px; margin-left: 20px;">Código</label>
                                                                                <form:input type="text" path="codigo" style="margin-left: 20px; padding: 8px; flex: 1; border: 1px solid #ddd; border-radius: 4px; margin-right: 8px; box-sizing: border-box;" placeholder=""/>
                                                                            </div>
                                                                            <div class="col-xs-12 col-sm-4 col-md-4" style="margin-top: 33px">
                                                                                <button type="button" style="padding: 8px; flex: 1; border: 1px solid #ddd; border-radius: 4px; box-sizing: border-box; background-color: #3FBFBF; color: #fff" onClick="buscarCodigo()">Buscar</button>
                                                                            </div>
                                                                        </div>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <div>
                                                                            <div class="row col-xs-9 col-sm-4 col-xl-12"style="margin-right: 20px;">
                                                                        
                                                                                <button style="padding: 10px 20px; background-color: #4CAF50; color: white; border: none; border-radius: 5px; cursor: pointer;" onclick="simularFocus(event)">Habilitar Lector</button>
                                                                            </div>
                                                                        
                                                                      
                                                                        
                                                                        <div class="row col-xs-9 col-sm-3 col-xl-12">
                                                                            <label>Codigo</label>
                                                                                <div class="input-container">
                                                                                    <p class="styled-input" id="codigoBobinaResultado"></p>
                                                                                </div>
                                                                                <div id="cargando" class="spinner-container">
                                                                                    <div class="spinner"></div>
                                                                                        <p class="loading-text">Buscando Codigo....</p>
                                                                                </div>
                                                                        </div>
                                                                        
                                                                        <div class="campo-oculto">
                                                                            <form:input type="text" id="codigo" path="codigo" class="form-control" placeholder="Codigo B..." onChange="searchBobinaAutomatically()" />
                                                                        </div>
                                                                        </div>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                                
                                                                <br />
                                                                
                                                            </div>
                                                            <br />
                                                            <c:if test="${!statusAct ne 'none'}">
                                                            <div style="height: 250px; padding-top: 40px; overflow-y: scroll; width: 100%">
                                                                </c:if>
                                                             <c:if test="${statusAct ne 'none'}">
                                                            <div style="height: 200px; overflow-y: scroll;">
                                                                </c:if>
                                                                <table class="display table table-striped table-hover cell-border" style="width: 100%;">
                                                                    <thead>
                                                                        <tr>
                                                                            <th>#pallet/#bulto</th>
                                                                            <th>Bobina/bulto</th>
                                                                            <th>#Serie</th>
                                                                            <th>Peso</th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody id="dinamic">
                                                                        
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <ul class="list-group">
                                                        <h3 class="text-center text-danger"><span id="listCodigosOrdenProduccion"></span></h3>
                                                    </ul>
                                                </div>

                                                <div class="modal-footer bg-light">
                                                    <button id="miBoton" type="button" class="btn btn-primary" onclick="callController2()">
                                                        <span id="botonTexto">Dar de baja</span>
                                                        <span id="loadingSpinner" class="spinner-border spinner-border-sm" style="display: none;"></span>
                                                    </button> 
                                                    
                                                    
                                                    </form:form>
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar/Cancelar</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                              
                <!-- Modal para agregar cantidad -->
                <div class="modal fade" id="agregarModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Agregar Cantidad</h5>
                                
                            </div>
                            <form:form class="form-horizontal" method="post" action="/thyssenplastic/remitoDetalle/addOrEditOrRemove" modelAttribute="remitoDetalleForm" id="myForm">
                                
                                <h4 style="margin-left: 5px">Deposito: <snan style="margin-left: 3px" id="valorEnModal"></snan><span>,</span><span> Lote: <span style="margin-left: 3px" id="valorEnModalLote"></span></span></h4>
                                <div class="modal-body">
                                    <label for="cantidadInput">Cantidad:</label>
                                  
                                     <input type="hidden" id="depositoIdInput" class="form-control" />
                                      <input type="hidden" id="maximo" class="form-control" />
                                    
                                     <form:hidden path="idOrdenDeProduccion" class="form-control"/>
                                     <form:hidden path="idDeposito" class="form-control"/>
                                       <form:hidden path="pk" class="form-control"/>
                                        <form:hidden path="action" class="form-control"/>
                                        <form:hidden path="cantidad" class="form-control"/>
                                        <form:hidden path="operacion" class="form-control"/>
                                        <form:hidden path="idRemito" class="form-control"/>
                                        <form:hidden path="stock" class="form-control"/>
                                        <form:hidden path="viewTipoHdn" class="form-control"/>
                                        <form:hidden path="viewCodigoHdn" class="form-control"/>
                                        <form:hidden path="tieneBultoOPallet" class="form-control"/>
                                        <form:hidden path="idOrdenDeProduccionEdit" class="form-control"/>
                                        <form:hidden path="idOrdenDeProduccionRemove" class="form-control"/>
                                        <form:hidden path="idArticuloEdit" class="form-control"/>
                                        <form:hidden path="tipoEdit" class="form-control"/>
                                       
                                         <form:hidden path="idOrdenDeProduccion" class="form-control"/>
                                         <input id="cantidad2" name="cantidad2" class="form-control" />
                                        
                                      
                                         
                                    <p id="mensajeError" style="color: blue;">Cantidad disponible: <span id="valorEnModalCantidad"></span></p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                                     <button id="miBoton" type="button" class="btn btn-primary" onclick="callController()">Agregar</button> 
                                    
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
                <c:if test="${remito.estadoRemito eq 'Nuevo'}">
                    <div>
                        
                        <button id="toggleSectionBtn" class="btn btn-primary">Mostrar/Ocultar Sección de carga</button>
                    </div>
                
                <div id="seccionCargaItems">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12" style="height: 100px;">
                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xl-6" >
           
                            <label for="filtroDeposito">Filtrar por Denominación:</label>
                            <select id="filtroDeposito" onchange="filtrarTabla()" class="form-control">
                                <option value="todos">Todas las denominaciones</option>
                                <!-- Agrega opciones para cada depósito -->
                                <c:forEach var="entry" items="${articuloList2}">
                                    <option value="${entry.key}">${entry.value}</option>
                                </c:forEach>
                            </select>
                            </div>
                     
                        </div>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12"style="height: 400px; overflow-y: auto; margin-bottom: 10px">
                  
                    <table id="reportesTable" style="width: 90%">
                        <div style="background: #3FBFBF">
                        <thead>
                            <tr>
                                <th style="display:none;">Codigo Articulo</th>
                                <th>Nro Lote</th>
                                <th>Depósito</th>
                                <th>Unidades</th>
                                <th>Recepcionado(KG)</th>
                                <th>Disponible(KG)</th>
                                <th>Consumido(KG)</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="deposito" items="${depositos}">
                                <tr data-deposito-id="${deposito.idArticulo}">
                                    <td style="display:none;">${deposito.idArticulo}</td>
                                    <td>${deposito.idOrdenDeProduccion}</td>
                                    <td>${deposito.nombreDeposito}</td>
                                    
                                    <td>${deposito.unidad - deposito.remitoDetalle}
                                          <button class="btn btn-primary btn-agregar" 
                                            data-toggle="modal" 
                                            data-target="#agregarModal" 
                                            data-deposito-id="${deposito.nombreDeposito}"
                                            data-lote="${deposito.idOrdenDeProduccion}"
                                            data-cantidadm="${deposito.unidad - deposito.remitoDetalle}"
                                            >
                                        Agregar
                                    </button>
                                    </td>
                                  
                                    <td>${deposito.sumapeso}</td>
                                   <td>${deposito.sumapeso - deposito.pesoConsumido}</td>
                                   <td>${deposito.pesoConsumido}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        </div>
                    </table>
                        </div>
                     <hr>
                     
                </div>
                </c:if>
                      
                    

                <div class="card">
                    <div class="card-body">
                        <div class="row col-xs-12 col-sm-12 col-xl-12">
                            <table id="remitodDetallesTable" class="display table table-striped table-hover cell-border" style="width: 90%">
                                <thead>
                                    <tr>
                                        <th style="text-align: center">CODIGO</th>
                                        <th style="text-align: center">ARTICULO</th>
                                        <th style="text-align: center">LOTE</th>
                                        <th style="text-align: center">DEPOSITO</th>
                                        <th style="text-align: center">CANTIDAD</th>
                                        <th style="text-align: center">Estado Baja</th>
                                        
                                        <c:if test = "${estadoRemito == 'abierto' || estadoRemito == 'nuevo' || estadoRemito == 'cerrado'}">
                                           <th style="text-align: center">ACCIONES</th>
                                        </c:if>
                                      
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${remitoDetalles}" var="remitoDetalle">
                                        <tr> 
                                            <td style="text-align: center">
                                                <c:out value="${remitoDetalle.pk}" />
                                            </td>
                                            <td style="text-align: center">
                                                <c:out value="${remitoDetalle.articulo}" />
                                            </td>
                                            <td style="text-align: center">
                                                <c:out value="${remitoDetalle.lote}" />
                                            </td>

                                            <td style="text-align: center">
                                                <c:out value="${remitoDetalle.deposito}" />
                                            </td>                                                                                                                                                                                
                                            <td style="text-align: center">
                                                <c:out value="${remitoDetalle.cantidad}" />
                                            </td>
                                            <td style="text-align: center">
                                                <c:if test="${Integer.valueOf(remitoDetalle.cantidadBaja) >= Integer.valueOf(remitoDetalle.cantidad)}">
                                                    <span style="font-size: 20px; color: #49ad3a"; class="fa fa-check-circle text-success" title="Todos los ítems han sido dados de baja"></span>
                                                </c:if>
                                                <c:if test="${(Integer.valueOf(remitoDetalle.cantidadBaja)) < (Integer.valueOf(remitoDetalle.cantidad))}">
                                                    <span style="font-size: 20px; color: #a3a21b" class="fa fa-exclamation-circle text-warning icon-large" title="Quedan ítems por dar de baja"></span>
                                                </c:if>
                                            </td>
                                             <c:if test = "${rol == 'oficina' && estadoRemito == 'nuevo'}">
                                                <td style="text-align: center">
                                                    <a class="nav-link active fa fa-trash fa-lg"
                                                        href="/thyssenplastic/remitoDetalle/remove/${remitoDetalle.pk}"
                                                        data-toggle="tooltip" data-placement="top" title="Eliminar"></a>
                                                </td>
                                             </c:if>

                                               <c:if test="${(rol == 'oficina' || rol == 'deposito') && (Integer.valueOf(remitoDetalle.cantidadBaja) < Integer.valueOf(remitoDetalle.cantidad)) && !(estadoRemito == 'nuevo')}">
                                                     <td style="text-align: center">
                                                 
                                                    <button type="button" style=" background-color: transparent; border: none; text-decoration: none; color: #007bff; font-size: 18px; padding:0" 
                                                            class="btn btn-primary btn-agregarE"
                                                            data-toggle="modal" 
                                                            data-target="#miModalEgreso" 
                                                            data-deposito-idB="${remitoDetalle.deposito}"
                                                            data-loteB="${remitoDetalle.lote}"
                                                            data-remito="${remitoDetalle.pk}"
                                                            >

                                                           Nueva Baja
                                                    </button>
                                                    </td>
                                                </c:if> 

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
    $(document).ready(function() {
    $('#cantidad2').on('input', function() {
        
        $('#cantidad').val($(this).val());
    });

    
});
    $(document).ready(function () {
        
        $('#remitodDetallesTable').DataTable({
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
            },           
            order: [[1, 'desc']]            
        });
        
         $('#reportesTable').DataTable({
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
            },           
            order: [[1, 'desc']],
            paging: false,
            info: false
        });
        
        
           
        var tieneBultoOPallet = $( "#tieneBultoOPallet" ).val();
        var idOrdenDeProduccionEdit = $( "#idOrdenDeProduccionEdit" ).val();
        var tipoEdit = $( "#tipoEdit" ).val();
        if(tieneBultoOPallet == 'false') {
            loadOrdenDeCompra();
        }
    });
    
    function simularFocus(event) {
        event.preventDefault();
        
        document.getElementById('codigo').focus();
    }
    
    function searchBobinaAutomatically() {
    
        mostrarCargando();
        setTimeout(function() {
        
        ocultarCargando();
            searchBobinaAutomatically2();
            }, 3000);
        }
        
    function mostrarCargando() {
    
        document.getElementById('cargando').style.display = 'block';
    }

    function ocultarCargando() {

        document.getElementById('cargando').style.display = 'none';
    }
    
    function searchBobinaAutomatically2() {
       
       if (document.getElementById("codigo").value != ''){
        
        updateCodigoBobinaResultado();
        
        buscarCodigo();
       }
  }
  
   function updateCodigoBobinaResultado() {
        var codigoBobina = document.getElementById("codigo").value;
        document.getElementById("codigoBobinaResultado").textContent = "" + codigoBobina;
    }
    
    
    
    $(document).ready(function () {
        $('#seccionCargaItems').hide();
     
        
        $('#toggleSectionBtn').on('click', function () {
            $('#seccionCargaItems').toggle();
        });

  
        $('.btn-agregar').on('click', function () {
            
             var depositoId = $(this).data('deposito-id');
             console.log('Depósito ID:', depositoId);
             var lote = $(this).data('lote');
             var max1 = $(this).data('cantidadm');
            
            $('#idDeposito').val(depositoId);
            $('#idOrdenDeProduccion').val(lote);
            $('#valorEnModal').text(depositoId);
            $('#valorEnModalLote').text(lote);
            $('#maximo').val(max1);
            $('#valorEnModalCantidad').text(max1);
            
            
        });
        
        
    });
    
    
    $('#agregarModal').on('hidden.bs.modal', function (event) {
        
        $('#idDeposito').empty();
        $('#idOrdenDeProduccion').empty();
        $('#valorEnModal').empty();
        $('#valorEnModalLote').empty();
        $('#maximo').empty();
        $("#valorEnModalCantidad").empty();
        
        $('#myForm')[0].reset();
    });
    
    $('#miModalEgreso').on('hidden.bs.modal', function (event) {
        console.log('Evento hidden.bs.modal ejecutado');
        // Vacía el contenido del modal
        codigosIngresadosEgreso = [];
        cantidadAcumuladosEgreso = [];  // Para almacenar los pesos
        pesosAcumuladosEgreso = [];  // Para almacenar los pesos
        $('#codigo').empty();
        $('#listCodigosOrdenProduccion').empty();
        $('#denominacion').empty();
        $('#pesoTotal').empty();
        $('#unidades').empty();
        $("#dinamic").empty();
        
        $("#codigo").val('');
        $("#codigoBobinaResultado").text('');
        
        $('#myFormm')[0].reset();
    });
    
    function filtrarTabla() {
        var filtro = document.getElementById("filtroDeposito").value;
        var filas = document.querySelectorAll("#reportesTable tbody tr");

        filas.forEach(function (fila) {
            if (filtro === "todos" || fila.getAttribute("data-deposito-id") === filtro) {
                fila.style.display = "";
            } else {
                fila.style.display = "none";
            }
        });
    }
    
    // Escucha el evento "show.bs.modal" que se dispara cuando se muestra el modal
    $('#miModal').on('show.bs.modal', function (event) {
        // Obtiene el botón que disparó el evento
        var button = $(event.relatedTarget);
        
        //var denominacionPor = `${denominacionPorOrden}`;
       
       // var cadenaFormateada = denominacionPor.replace(/=/g, '": "').replace(/{/g, '{"').replace(/}/g, '"}').replace(/, /g, '", "');

        //var objetoJavaScript = JSON.parse(cadenaFormateada);

        //var codigoOrdenProduccion = button.data('codigo');
        //var listCodigosOrdenProduccion = button.data('lista');
       

        // Actualiza el contenido del span con el código en el modal
        //$('#codigoOrdenProduccion').text(codigoOrdenProduccion);
        //$('#listCodigosOrdenProduccion').text(listCodigosOrdenProduccion);
        //$('#denominacion').text(objetoJavaScript[codigoOrdenProduccion]);
    });
    
        $('#miModal').on('hidden.bs.modal', function (event) {
        console.log('Evento hidden.bs.modal ejecutado');
        // Vacía el contenido del modal
        // Vacía el contenido del modal
        $('#codigoOrdenProduccion').empty();
        $('#listCodigosOrdenProduccion').empty();
        $('#denominacion').empty();
        $('#pesoTotal').empty();
        $('#unidades').empty();
        $("#dinamic").empty();
       
        
        $('#myForm')[0].reset();
    });
    
    
     
        $('.btn-agregarE').on('click', function () {
            var depositoIdEEE = $(this).data('deposito-idB');
            var loteIdEEE = $(this).data('loteB');
            console.log("hola");
            
            var depositoIdE = $(this).attr('data-deposito-idB');
            var loteIdE = $(this).attr('data-loteB');
            var remitoId = $(this).attr('data-remito');
            $('#depositoActual').val(depositoIdE);
            $('#idOrdenDeProduccionE').val(loteIdE);
            $('#idRemito').val(remitoId);
            
            $('#codigoOrdenProduccion').text(loteIdE);

            $('#deposito').text(depositoIdE);
            
        });


  
    function callController() {
       
        var tipo = $("#tipo").val();
        var cantidad = $("#cantidad").val();

        var maximo = $('#maximo').val();

        // Convertir a números
        cantidad = parseFloat(cantidad);
        maximo = parseFloat(maximo);

        if (isNaN(cantidad) || cantidad < 0 || cantidad > maximo) {
           alert('Ingrese una cantidad válida (mayor o igual a cero y no mayor al disponible).');
           return;
        }
   
                
        var action = "alta";
        console.log(action);

        if(action == 'remove') {
            if(confirm('Desea eliminarlo')) {
                var form = document.getElementById("myForm");
                
                form.submit();
            }
        } else {
            if($("#myForm")[0].checkValidity()) {
                 console.log("Cantidad: " + $("#cantidad").val());

                var form = document.getElementById("myForm");
              

                form.submit();
            } else {
                $("#myForm")[0].reportValidity();
            }        
        }        
    }
    
    
    
    
    function callController2() {
        
        var boton = $("#miBoton");
        var botonTexto = $("#botonTexto");
        var loadingSpinner = $("#loadingSpinner");

        
        boton.prop("disabled", true);
        botonTexto.text("Realizando Baja...");
        loadingSpinner.show();
                        
        var idBobina = $("#idBobina").val();
        var idBulto = $("#idBulto").val();
        var idPallet = $("#idPallet").val();
        var tipo = $("#tipo").val();
                
        if((idBobina == '-1' || idBobina == '') && (idBulto == '-1' || idBulto == '') && (idPallet == '-1' || idPallet == '')) {
            alert('Debe buscar un bulto o pallet.');
            return;
        }
        
        var action = $( "#action" ).val();

        if(action == 'remove') {
            if(confirm('Desea eliminarlo')) {
                var formm = document.getElementById("myFormm");
                formm.submit();
            }
        } else {
            if($("#myFormm")[0].checkValidity()) {
                var formm = document.getElementById("myFormm");
                
                formm.submit();
            } else {
                $("#myFormm")[0].reportValidity();
            }        
        }        
    }
    
    let codigosIngresadosEgreso = [];
    let cantidadAcumuladosEgreso = [];
    let pesosAcumuladosEgreso = [];
    
    function actualizarCampoCodigos() {
        document.getElementById("codigos").value = codigosIngresadosEgreso.join(",");
    }
    
    function buscarCodigo() {

        var codigo = $("#codigo").val();
        var orden = $("#idOrdenDeProduccionE").val();
        
        //var orden = document.getElementById('idOrdenDeProduccionE').innerText;
        //var orden = 28;
        
        let sumaPesos = 0;
        let cantidadporunidad = 0;
        
        
        if(codigo == '' || codigo == null || codigo == undefined) {
            alert('Debe ingresar un código');
            return;
        }
        
        
        var sUrl = '/thyssenplastic/egresoDeposito/findBultoOPallet/'+codigo+'/'+orden;
        $.ajax({
            async: true,
            type: 'GET',
            dataType : 'json',
            url: sUrl
        })
        .done( function (responseText) {
            $("#idBobina").val('-1');
            $("#idBulto").val('-1');
            $("#idPallet").val('-1');
            $("#tipo").val('-1');
            
            
            if(responseText.length > 0) {                    
                var item = responseText[0];
                     
                if(item.tipo == '-1') {
                    alert('Código no encontrado.');
                    $("#codigo").val('');
                    $("#codigoBobinaResultado").text('');
                    return;
                }
                if(item.tipo == '-2') {
                    alert('El codigo no fue encontrado. Verifique que la orden de produccion sea la correcta');
                    $("#codigo").val('');
                    $("#codigoBobinaResultado").text('');
                    return;
                }
                if(item.tipo == '-3') {
                    alert('El codigo de bulto se encuentra en un pallet, si desea dar de baja ese bulto individual debe sacarlo del pallet');
                    $("#codigo").val('');
                    $("#codigoBobinaResultado").text('');
                    return;
                }
                if(item.tipo == '-4') {
                    alert('El codigo de bobina ingresado se encuentra en bulto, ingrese un codigo de bobina disponible o el bulto correspondiente');
                    $("#codigo").val('');
                    $("#codigoBobinaResultado").text('');
                    return;
                }
                if(item.tipo == '-5') {
                    alert('La bobina ingresada no se encuentra en el deposito');
                    $("#codigo").val('');
                    $("#codigoBobinaResultado").text('');
                   return;
                }
                if(item.tipo == '-6') {
                    alert('El bulto ingresado no se encuentra en el deposito');
                    $("#codigo").val('');
                    $("#codigoBobinaResultado").text('');
                    return;
                }
                if(item.tipo == '-7') {
                    alert('El pallet ingresado no se encuentra registrado en el deposito');
                    $("#codigo").val('');
                    $("#codigoBobinaResultado").text('');
                    return;
                }
                
                 if(item.tipo == '-8') {
                    alert('El pallet ya fue dado de baja');
                    $("#codigo").val('');
                    $("#codigoBobinaResultado").text('');
                    return;
                }
                if(item.tipo == '-9') {
                    alert('El bulto ya fue dado de baja');
                    $("#codigo").val('');
                    $("#codigoBobinaResultado").text('');
                    return;
                }
                 if(item.tipo == '-10') {
                    alert('La bobina ya fue dada de baja');
                    $("#codigo").val('');
                    $("#codigoBobinaResultado").text('');
                    return;
                }
                if(item.tipo == '-14') {
                    alert('El pallet ingresado no tiene bultos');
                    $("#codigo").val('');
                    $("#codigoBobinaResultado").text('');
                    return;
                }
                // Verifica si el código ya existe en el array
                if (codigosIngresadosEgreso.includes(codigo)) {
                    $("#codigo").val('');
                    $("#codigoBobinaResultado").text('');
                    $("#tipo").val(item.tipo);
                    alert("El código ya fue ingresado. Por favor, ingrese un código diferente.");
                    return; // No agrega el código duplicado
                }
                if(item.tipo == 'bulto') {
                    $("#idBulto").val(item.id);
                    $("#depositoActual").val(item.depositoActual);

                    var bultos = item.bultos;
                    if (bultos && bultos.length > 0) {
                        // NO vaciar la tabla aquí, solo agregar las filas
                        for (var i = 0; i < bultos.length; i++) {
                            var bulto = bultos[i];
                            sumaPesos += parseFloat(bulto.pesoNeto);  // Acumulamos el peso neto de los bultos
                            cantidadporunidad = cantidadporunidad + 1;  // Incrementamos la cantidad de unidades
                            cantidadAcumuladosEgreso.push(1); 
                            pesosAcumuladosEgreso.push(bulto.pesoNeto);
                            
                            
                            

                            // Agregar la fila a la tabla
                            $("#dinamic").append("<tr><td>-" + bulto.estaEnPalletLabel + "</td><td>" + "bulto" + "</td><td>" + bulto.codigo + "</td><td>" + bulto.pesoNeto + "</td></tr>");
                        }
                    }
                }
                if(item.tipo == 'pallet') {
                    $("#idPallet").val(item.id);
                    $("#depositoActual").val(item.depositoActual);

                    var bultos = item.bultos;
                    if (bultos && bultos.length > 0) {
                        // NO vaciar la tabla aquí, solo agregar las filas
                        cantidadAcumuladosEgreso.push(bultos.length); 
                        for (var i = 0; i < bultos.length; i++) {
                            var bulto = bultos[i];
                            sumaPesos += parseFloat(bulto.pesoNeto);  // Acumulamos el peso neto de los bultos
                            cantidadporunidad = cantidadporunidad + 1;  // Incrementamos la cantidad de unidades
                            pesosAcumuladosEgreso.push(bulto.pesoNeto);
                            

                            // Agregar la fila a la tabla
                            $("#dinamic").append("<tr><td>P" + item.id + "</td><td>" + "bulto" + "</td><td>" + bulto.codigo + "</td><td>" + bulto.pesoNeto + "</td></tr>");
                        }
                    }
                }
                
                if(item.tipo === 'bobina') {
                    $("#idBobina").val(item.id);
                    $("#depositoActual").val(item.depositoActual);
                    console.log(item.bobobina);

                    var bobina = item.bobobina;
                    if (bobina) {
                        sumaPesos += parseFloat(bobina.pesoNeto) || 0; // Acumulamos el peso neto de la bobina
                        cantidadporunidad = cantidadporunidad + 1; // Solo hay una bobina
                         cantidadAcumuladosEgreso.push(1); 
                         pesosAcumuladosEgreso.push(bobina.pesoNeto);

                        // Agregar fila a la tabla para la única bobina
                        $("#dinamic").append("<tr><td>" + bobina.estaEnBultoLabel + "</td><td>" + "bobina" + "</td><td>" + bobina.codigo + "</td><td>" + bobina.pesoNeto + "</td></tr>");
                    }
                }
                $("#tipo").val(item.tipo);   
                
                let cantidadporunidad2 = cantidadAcumuladosEgreso.reduce((total, valor) => total + valor, 0);
                $("#unidades").text(cantidadporunidad2); 
                
                let pesoTotal2 = pesosAcumuladosEgreso.reduce((total, valor) => total + parseFloat(valor), 0);
                
                $("#pesoTotal").text(pesoTotal2);

             
                if (codigosIngresadosEgreso.length === 0) {
                    $("#idDeposito").val(item.idDeposito);
                }
                
                codigosIngresadosEgreso.push(codigo);
                actualizarCampoCodigos();
                $("#codigo").val('');
                $("#codigoBobinaResultado").text('');
                $("#tipo").val(item.tipo);
                                
            }            
        })
        .fail( function (jqXHR, status, error) {
            // Triggered if response status code is NOT 200 (OK)
            alert(jqXHR.responseText);
        })
        .always( function() {
            // Always run after .done() or .fail()
        });

    }
    
</script>
<style>
        table {
        width: 80%;
        border-collapse: collapse;
        margin-top: 20px;
        }

    th, td {
        border: 1px solid #AED581; /* Utilizar un color de borde más claro */
        background: #fff;
        padding: 1px;
        text-align: left;
        font-size: 14px;
    }

    th {
        background-color: #DCECCB;
        font-size: 16px;
    }
    
     .containerr {
        background-color: #fff;
        padding: 15px;
        border-radius: 8px;
        box-shadow: 0 0 8px rgba(0, 0, 0, 0.1);
        margin-top: 20px;
        display: flex;
        justify-content: center;
        border-left: 8px solid green; 
    }

    .labelClass {
        font-weight: bold;
        color: #00a95f;
        font-size: x-large;
        margin-bottom: 5px;
    }

    .data {
        margin-bottom: 10px;
    }

    p {
        margin: 0;
    }

    /* Estilos para la tabla de resumen */
.resumen-container {
    width: 100%;
    margin-top: 20px;
}

.resumenItem {
    background-color: #f8f9fa;
    border: 1px solid #dee2e6;
    border-radius: 10px;
    padding: 10px;
    margin-bottom: 10px;
    display: flex;
    align-items: center;
}

.resumenItem p {
    margin: 0;
    font-size: 16px;
}

.btn-editar,
.btn-eliminar {
    margin-left: 10px;
}    

/* Estilos para el contenedor de resumen con scroll */
#resumenContainer {
    max-height: 300px; /* Ajusta el valor según tus necesidades */
    overflow-y: auto;
    border: 1px solid #ddd; /* Bordes opcionales para un aspecto visual */
    padding: 10px;
}

.spanClass {
    margin-left: 5px;
    font-size: large;
}

.spinner-container {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        
        border-radius: 20px;
        display: none; /* Por defecto oculto */
      }

      .spinner-container.active {
        display: flex; /* Muestra el contenedor cuando está activo */
        justify-content: center;
        align-items: center;
      }

      .spinner {
        width: 40px;
        height: 40px;
        border-radius: 50%;
        border: 4px solid rgba(0, 0, 0, 0.1);
        border-top-color: #8bc34a; /* Cambia el color a verde claro */
        animation: spin 1s linear infinite;
      }

      .loading-text {
        margin-left: 10px; /* Espacio entre el spinner y el texto */
      }

      .styled-input {
        border: 1px solid #ccc;
        padding: 5px 10px;
        display: inline-block;
        min-width: 150px;
        cursor: text;
        height: 35px;
        
      }

      .styled-input:focus {
        outline: none;
        border-color: #007bff;
      }

      @keyframes spin {
        to {
          transform: rotate(360deg);
        }
      }
      
    .campo-oculto {
        opacity: 0;
        position: absolute;
        left: -9999px; /* Mueve el elemento fuera de la pantalla */
    }
    
    </style>

<%@include file = "/WEB-INF/views/jsp/includes/footer.jsp" %>




