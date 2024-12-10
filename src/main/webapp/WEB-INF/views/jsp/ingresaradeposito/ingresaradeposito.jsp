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
              <div>
                <div class="card">
                    <div class="card-header">
                        <ol class="breadcrumb mb-1 mt-1">
                            <li class="breadcrumb-item"><a href="">Producción</a></li>
                            <li class="breadcrumb-item active">${titleIngresarDeposito}</li>
                        </ol>
                    </div>
                    <div>
                        <div style="margin-bottom: 20px;">
                            <input type="hidden" id="idOrdenDeProduccionHidden" name="idOrdenDeProduccion" />
                            <input type="text" id="searchInput" style="padding: 8px; flex: 1; border: 1px solid #ddd; border-radius: 4px; margin-right: 8px; box-sizing: border-box;" list="provinciaList" placeholder="Buscar orden de producción..." required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')">
                            <datalist id="provinciaList">
                                <c:forEach items="${provinciaList}" var="provincia" >
                                    <option id="provincia" value="${provincia}" />
                                </c:forEach>
                            </datalist>  
                            <button onclick="searchOrders()" style="padding: 8px; flex: 1; border: 1px solid #ddd; border-radius: 4px; margin-right: 8px; box-sizing: border-box; background-color: #3FBFBF; color: #fff">Buscar</button>
                        </div>
                        <div style="height: 300px; overflow-y: auto;">
                            <div style="display: flex; flex-wrap: wrap; justify-content: center; align-items: center; border: 2px solid #3FBFBF; border-radius: 12px;">
                                <c:forEach items="${provinciaList}" var="ordenprod" >
                                    <div class="row col-xs-12 col-sm-3 col-xl-3 order-card" style="whidth: 100%; margin: 10px; box-sizing: border-box; border: 1px solid #ddd; border-radius: 8px; background-color: #fff; padding: 10px; text-align: center; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);">
                                    <h6 style="margin-bottom: 5px; font-size: 18px">Orden ${ordenprod}</h6>
                                    <button type="button" style=" background-color: transparent; border: none; text-decoration: none; color: #007bff; font-size: 18px" data-toggle="modal" data-target="#miModal" data-codigo="${ordenprod}" data-lista="${ordendeproduccionpallet.listaCodigoBultos}">
                                        Seleccionar
                                    </button>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>

                        <div class="card-body">
                            <div class="container">                                                                         
                               <div class="row col-xs-12 col-sm-12 col-xl-12">
                                    <div class="modal fade rounded" id="miModal" tabindex="-1" role="dialog" aria-labelledby="miModalLabel" aria-hidden="true" data-backdrop="static">
                                        <div class="modal-dialog modal-lg modal-dialog-centered">
                                            <div class="modal-content">
                                                <div class="modal-header bg-info text-white">
                                                    <h4 class="modal-title text-center" id="miModalLabel">Ingreso a depósito </h4>
                                                </div>

                                                <div class="modal-body">
                                                    <div class="row">
                                                        <div class="col-xs-6 col-sm-6 col-xl-6">
                                                            <div class="articulo-section border rounded p-3 mb-3 bg-light">
                                                                <h5 class="text-center text-info">Artículo</h5>
                                                                <ul class="list-group">
                                                                    <!-- Datos del artículo (puedes reemplazar estos ejemplos con tus propios datos) -->
                                                                    <li class="list-group-item"><span id="denominacion" class="font-weight-bold text-success"></span></li>
                                                                    <li class="list-group-item">Lote:  <span id="codigoOrdenProduccion" class="font-weight-bold text-primary"></span></li>
                                                                    <form:form class="form-horizontal" method="post" action="/thyssenplastic/ingresarDeposito/addOrEditOrRemove" modelAttribute="ingresarDepositoForm" id="myForm">
                                                                    <form:hidden path="pk" class="form-control"/>
                                                                    <form:hidden path="action" class="form-control"/>
                                                                    <form:hidden path="idBulto" class="form-control"/>
                                                                    <form:hidden path="idBobina" class="form-control"/>
                                                                    <form:hidden path="idPallet" class="form-control"/>
                                                                    <form:hidden path="tipo" class="form-control"/>
                                                                    <form:hidden path="codigos" id="codigos" class="form-control" />
                                                                    <li class="list-group-item">
                                                                        <label for="inputRubro" class="text-info p-3">Depósito</label>
                                                                        <form:select path="idDeposito" id="idDeposito" class="form-control" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')" readonly="true">
                                                                            <option value="-1">Seleccionar Deposito</option>
                                                                            <c:forEach var="entry" items="${depositoList}">
                                                                                <c:choose>
                                                                                    <c:when test="${entry.key == idDeposito}">
                                                                                        <option value="${entry.key}" selected>${entry.value}</option>
                                                                                    </c:when>
                                                                                    <c:otherwise>
                                                                                        <option value="${entry.key}">${entry.value}</option>
                                                                                    </c:otherwise>
                                                                                </c:choose>
                                                                            </c:forEach>
                                                                        </form:select>


                                                                    </li>
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
                                                                                <form:input type="text" path="codigo" style="margin-left: 20px; padding: 8px; flex: 1; border: 1px solid #ddd; border-radius: 4px; margin-right: 8px; box-sizing: border-box;" placeholder="" />
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
                                                                    </c:otherwise>
                                                                </c:choose>
                                                                
                                                                <br />
                                                                <c:if test="${!statusAct ne 'none'}">
                                                                <div style="margin-top: 80px;">
                                                                  
                                                                </c:if>
                                                                    <c:if test="${statusAct ne 'none'}">
                                                                          <div>
                                                                     </c:if>
                                                                   
                                                                <label for="inputFecha_alta" style="margin-left: 10px;">Depósito Actual</label>
                                                                <form:input type="text" style="margin-left: 10px;" path="depositoActual" class="form-control" placeholder="" disabled="true"/>
                                                                </div>
                                                            </div>
                                                            <br />
                                                            <div style="height: 200px; overflow-y: auto;">
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
                                                    <button id="miBoton" type="button" class="btn btn-primary" onclick="callController()">
                                                        <span id="botonTexto">Guardar</span>
                                                        <span id="loadingSpinner" class="spinner-border spinner-border-sm" style="display: none;"></span>
                                                    </button> 
                                                    </form:form>
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar/Cancelar</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                </div>
                            </div>
                        </div> 
                    </div>
              </div>
                
            </div>
            
                        <div class="modal fade" id="miModal3" tabindex="-1" role="dialog" aria-labelledby="miModalLabel2" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                              <div class="modal-contentt">
                                <div class="modal-header">
                                  <h5 class="modal-title" id="miModalLabel2">Cambiar estado de calidad</h5>
                                  
                                </div>
                                <div class="modal-bodyy" style="margin-top: 5px">
                                    
                                    <div style="display: flex;">
                                        <div style="flex: 1;">
                                            <p style="font-size: 14px"><strong>Código: </strong><span id="codigoDeIngresoADeposito" class="font-weight-bold text-primary" style="font-size: 16px"></span></p>
                                            <p style="font-size: 14px"><strong>Estado actual: </strong><span id="estadoActual" class="font-weight-bold text-primary" style="font-size: 16px"></span></p>
                                            <p style="font-size: 14px"><strong>Depósito: </strong> <span id="estadoActualDeposito" class="font-weight-bold text-primary" style="font-size: 16px"></span></p>
                                        </div>
                                        <div style="flex: 1;">
                                            <div class="form-group">
                                                
                                                <form:form class="form-horizontal" method="post" action="/thyssenplastic/ingresarDeposito/editDeposito" modelAttribute="ingresarDepositoForm" id="myForm2">
                                                    <form:hidden path="pkEditar" id="pkEditar" class="form-control"/>
                                                    <form:hidden path="action" value="edit" class="form-control"/>
                                                    <form:hidden path="idBulto" class="form-control"/>
                                                    <form:hidden path="idBobina" class="form-control"/>
                                                    <form:hidden path="idPallet" class="form-control"/>
                                                    <form:hidden path="tipo" class="form-control"/>
                                                   
                                                    
                                                   <div id="divBulto">
                                                    <label>Seleccione el bulto:</label>
                                                    
                                                    <form:select type="text" path="idBultoEditar" class="form-control" id="idBultoEditar">
                                                    </form:select>
                                                    </div>
                                                  <label for="estadoCalidadSelect">Seleccione el nuevo estado de calidad:</label>
                                                                                                
                                                    <form:select type="text" path="estadoNuevo" class="form-control" id="estadoCalidadSelect" required="true">
                                                       <form:option value="-1">Seleccionar...</form:option>
                                                       <form:option value="ok">OK</form:option>
                                                       <form:option value="observado">Observado</form:option>
                                                       <form:option value="rechazado">Rechazado</form:option>
                                                       <form:option value="sinmesurar">Sin Mesurar</form:option>
                                                   </form:select>
                                                       
                                                
                                               
                                            </div>
                                        </div>
                                    </div>

                                     <div id="textoAdicional" style="display:none;">
                                         <div style="background-color: white; padding: 2px; border-left: 8px solid #CCCC00; outline: 1px solid #CCCC00; border-radius: 5px; margin: 4px;">
                                            <p id="textoReparacion" style="display:none;">
                                                <div class="nav-link active fa fa-exclamation-triangle fa-lg text-warning"></div> Al cambiar el estado de calidad tambien se movera al deposito corresponiente
                                            </p>
                                        </div>
                                      </div>
                                    
                                    <div id="textoAdicionalPallet" style="display:none;">
                                         <div style="background-color: white; padding: 2px; border-left: 8px solid #CCCC00; outline: 1px solid #CCCC00; border-radius: 5px; margin: 4px;">
                                            <p id="textoReparacion" style="display:none;">
                                                <div class="nav-link active fa fa-exclamation-triangle fa-lg text-warning"></div> El bulto seleccionado no va a pertenecer mas al pallet y se movera al deposito que corresponiente
                                            </p>
                                        </div>
                                    </div>
                                  
                                   
                                    
                                </div>
                                <div class="modal-footer">
                                  
                                  <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                                  </form:form>    
                                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                </div>
                                                
                              </div>
                            </div>
                          </div>

            <hr>
            
            <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xl-6" style="height: 100px;">
           
                            <label for="filtroDeposito">Filtrar por lote:</label>
                            <select id="filtroDeposito" onchange="filtrarTabla()" class="form-control">
                                <option value="todos">Todos</option>
                                
                                <c:forEach var="provincia" items="${provinciaList}">
                                    <option value="${provincia}">${provincia}</option>
                                </c:forEach>
                            </select>
                     
                        </div>
           
            <div class="card">
                <div class="card-body">
                    
                    
                    <div class="row col-xs-12 col-sm-12 col-xl-12">
                        <div class="modal fade rounded" id="miModal2" tabindex="-1" role="dialog" aria-labelledby="miModalLabel2" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title text-center" id="miModalLabel2">Listado de bultos: <span id="codigoOrdenProduccionn"></h4>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Cerrar" style="position: absolute; top: 10px; right: 10px;">
                                               <span aria-hidden="true">&times;</span>
                                            </button>
                                    </div>
                                <div class="modal-body">
                                    <ul class="list-group">
                                        <h3 class="text-center"><span id="listCodigosOrdenProduccionn"></span></h3>
                                    </ul>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                                </div>
                           </div>
                        </div>
                    </div>

                    <div class="row col-xs-12 col-sm-12 col-xl-12" style="height: 400px; overflow-y: auto;">
                        <table id="ingresarDepositosTable" class="display table table-striped table-hover cell-border">
                            <thead>
                                <tr>
                                    <th>LOTE</th>
                                    <th>CODIGO</th>
                                    <th class="hidden-xs hidden-sm">FECHA</th>
                                    <th>CODIGO</th>
                                    <th>UNIDADES</th>
                                    <th>CALIDAD</th>
                                    <th class="hidden-xs hidden-sm">DEPOSITO</th>
                                    
                                </tr>
                            </thead>

                            <tbody>

                                <c:forEach  items="${ingresarDepositos}" var="ingresarDeposito">
                                    <tr data-deposito-id="${ingresarDeposito.idOrdenDeProduccion}">
                                        <td><c:out value="${ingresarDeposito.idOrdenDeProduccion}" /></td>
                                        <td><c:out value="${ingresarDeposito.pk}" /></td>
                                        <td class="hidden-xs hidden-sm"><c:out value="${ingresarDeposito.fecha}" /></td>
                                        <td><c:out value="${ingresarDeposito.codigo}" /></td>
                                        <td><c:out value="${ingresarDeposito.unidades}" />
                                           <c:if test="${ingresarDeposito.codigo.startsWith('P')}">
                                            <i title="Ver bultos" class="nav-link active fa fa-eye fa-lg" data-toggle="modal" data-target="#miModal2" data-codigo="${ingresarDeposito.codigo}" data-lista="${ingresarDeposito.mapaBultos}" style="cursor: pointer;"></i>
                                            </c:if>
                                        </td>
                                        <td><c:out value="${ingresarDeposito.estado}" />
                                            <c:if test = "${rol == 'oficina'}">
                                            
                                                <i title="Cambiar" class="nav-link active fa fa-cog fa-lg" data-toggle="modal" data-target="#miModal3" data-pk="${ingresarDeposito.pk}" data-lista="${ingresarDeposito.mapaBultos}" data-codigo="${ingresarDeposito.codigo}" data-estadoactual="${ingresarDeposito.estado}" data-estadoactualDeposito="${ingresarDeposito.deposito}" style="cursor: pointer;"></i>
                                            </c:if>
                                        </td>
                                        <td class="hidden-xs hidden-sm"><c:out value="${ingresarDeposito.deposito}" /></td>
                                        
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
        $('#ingresarDepositosTable').DataTable({
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
            },
            paging: false
            
        });
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
    
    function callController() {
        
        
        var boton = $("#miBoton");
        var botonTexto = $("#botonTexto");
        var loadingSpinner = $("#loadingSpinner");

        
        boton.prop("disabled", true);
        botonTexto.text("Guardando...");
        loadingSpinner.show();
                        
        var idBobina = $("#idBobina").val();
        var idBulto = $("#idBulto").val();
        var idPallet = $("#idPallet").val();
        var tipo = $("#tipo").val();
        
                
//        if((idBobina == '-1' || idBobina == '') && (idBulto == '-1' || idBulto == '') && (idPallet == '-1' || idPallet == '')) {
//            alert('Debe buscar un bulto o pallet.');
//            return;
//        }
        
        var action = $( "#action" ).val();

        if(action == 'remove') {
            if(confirm('Desea eliminarlo')) {
                var form = document.getElementById("myForm");
                form.submit();
            }
        } else {
            if($("#myForm")[0].checkValidity()) {
                var form = document.getElementById("myForm");
                console.log(form);
                form.submit();
            } else {
                $("#myForm")[0].reportValidity();
            }        
        }        
    }
    
    let codigosIngresados = [];
    let cantidadAcumulados = [];
    let pesosAcumulados = [];
    
    function actualizarCampoCodigos() {
        document.getElementById("codigos").value = codigosIngresados.join(",");
    }

    
    function buscarCodigo() {

        var codigo = $("#codigo").val();
        
        let sumaPesos = 0;
        let cantidadporunidad = 0;
             
        var orden = document.getElementById('codigoOrdenProduccion').innerText;
        
        
        if(codigo == '' || codigo == null || codigo == undefined) {
            alert('Debe ingresar un código');
            return;
        }
        
               
        var dep = $("#idDeposito").val();

        // Construir la URL
        var sUrl = '/thyssenplastic/ingresarDeposito/findBultoOPallet/' + codigo + '/' + orden;

        // Si hay un depósito almacenado, lo agregamos a la URL
        if (depositoActual) {
            sUrl += '/' + dep;
        }
        
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
                    alert('El codigo de bulto se encuentra en un pallet, si desea ingresar ese bulto a deposito debe sacarlo del pallet');
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
                    alert('La bobina ingresada ya se encuentra en el deposito');
                     $("#codigo").val('');
                     $("#codigoBobinaResultado").text('');
                    return;
                }
                if(item.tipo == '-6') {
                    alert('La bulto ingresado ya se encuentra en el deposito');
                    $("#codigo").val('');
                    $("#codigoBobinaResultado").text('');
                    return;
                }
                if(item.tipo == '-7') {
                    alert('El pallet ingresado ya se encuentra en el deposito');
                    $("#codigo").val('');
                    $("#codigoBobinaResultado").text('');
                    return;
                }
                if(item.tipo == '-8') {
                    alert('El código de bulto ingresado pertenece a otro depósito');
                    $("#codigo").val('');
                    $("#codigoBobinaResultado").text('');
                    return;
                }
                if(item.tipo == '-9') {
                    alert('El código de bobina ingresado pertenece a otro depósito');
                    $("#codigo").val('');
                    $("#codigoBobinaResultado").text('');
                    return;
                }
                if(item.tipo == '-10') {
                    alert('El código de pallet ingresado pertenece a otro depósito');
                    $("#codigo").val('');
                    $("#codigoBobinaResultado").text('');
                    return;
                }
                // Verifica si el código ya existe en el array
                if (codigosIngresados.includes(codigo)) {
                    $("#codigo").val('');
                    $("#codigoBobinaResultado").text('');
                    $("#tipo").val(item.tipo);
                    alert("El código ya fue ingresado. Por favor, ingrese un código diferente.");
                    return; // No agrega el código duplicado
                }
                

                if(item.tipo == 'pallet') {
                    console.log("Ejecutando código para pallet");
                    $("#idPallet").val(item.id);
                    $("#depositoActual").val(item.depositoActual);

                    var bultos = item.bultos;
                    if (bultos && bultos.length > 0) {
                        // NO vaciar la tabla aquí, solo agregar las filas
                        cantidadAcumulados.push(bultos.length); 
                        for (var i = 0; i < bultos.length; i++) {
                            var bulto = bultos[i];
                            sumaPesos += parseFloat(bulto.pesoNeto);  // Acumulamos el peso neto de los bultos
                            cantidadporunidad = cantidadporunidad + 1;  // Incrementamos la cantidad de unidades
                            pesosAcumulados.push(bulto.pesoNeto);
                            console.log(pesosAcumulados);
                            

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
                         cantidadAcumulados.push(1); 
                         pesosAcumulados.push(bobina.pesoNeto);

                        // Agregar fila a la tabla para la única bobina
                        $("#dinamic").append("<tr><td>" + bobina.estaEnBultoLabel + "</td><td>" + "bobina" + "</td><td>" + bobina.codigo + "</td><td>" + bobina.pesoNeto + "</td></tr>");
                    }
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
                            cantidadAcumulados.push(1); 
                            pesosAcumulados.push(bulto.pesoNeto);
                            
                            
                            

                            // Agregar la fila a la tabla
                            $("#dinamic").append("<tr><td>-" + bulto.estaEnPalletLabel + "</td><td>" + "bulto" + "</td><td>" + bulto.codigo + "</td><td>" + bulto.pesoNeto + "</td></tr>");
                        }
                    }
                }

         
                
                let cantidadporunidad2 = cantidadAcumulados.reduce((total, valor) => total + valor, 0);
                $("#unidades").text(cantidadporunidad2); 
                
                let pesoTotal2 = pesosAcumulados.reduce((total, valor) => total + parseFloat(valor), 0);
                
                $("#pesoTotal").text(pesoTotal2);

             
                if (codigosIngresados.length === 0) {
                    $("#idDeposito").val(item.idDeposito);
                }
                
                codigosIngresados.push(codigo);
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
    
    // Escucha el evento "show.bs.modal" que se dispara cuando se muestra el modal
    $('#miModal').on('show.bs.modal', function (event) {
        // Obtiene el botón que disparó el evento
        var button = $(event.relatedTarget);
        
        var denominacionPor = `${denominacionPorOrden}`;
       
        var cadenaFormateada = denominacionPor.replace(/=/g, '": "').replace(/{/g, '{"').replace(/}/g, '"}').replace(/, /g, '", "');

        var objetoJavaScript = JSON.parse(cadenaFormateada);

        var codigoOrdenProduccion = button.data('codigo');
        var listCodigosOrdenProduccion = button.data('lista');
        
       

        // Actualiza el contenido del span con el código en el modal
        $('#codigoOrdenProduccion').text(codigoOrdenProduccion);
        $('#listCodigosOrdenProduccion').text(listCodigosOrdenProduccion);
        $('#denominacion').text(objetoJavaScript[codigoOrdenProduccion]);
    });
    
        $('#miModal').on('hidden.bs.modal', function (event) {
        console.log('Evento hidden.bs.modal ejecutado');
        // Vacía el contenido del modal
         codigosIngresados = [];
         cantidadAcumulados = [];  // Para almacenar los pesos
         pesosAcumulados = [];  // Para almacenar los pesos
        $('#codigoOrdenProduccion').empty();
        $('#listCodigosOrdenProduccion').empty();
        $('#denominacion').empty();
        $('#pesoTotal').empty();
        $('#unidades').empty();
        $("#dinamic").empty();
        $("#codigo").val('');
        $("#codigoBobinaResultado").empty();
        
        $('#myForm')[0].reset();
    });
    
    $('#miModal2').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var codigoOrdenProduccionn = button.data('codigo');
        var mapaCodigos = button.data('lista'); // Ahora `mapaBultos` se recupera como un objeto en JavaScript
        console.log(mapaCodigos);
 
        // Elimina los caracteres '{' y '}' del principio y del final de la cadena
        var limpio = mapaCodigos.slice(1, -1);

        // Divide la cadena en pares clave-valor separados por coma y crea un array de estos pares
        var pares = limpio.split(", ");

        // Inicializa un objeto vacío para almacenar los resultados
        var nuevoObjeto = {};

        // Itera sobre los pares clave-valor y asigna las claves como IDs y los valores como códigos al nuevo objeto
        for (var i = 0; i < pares.length; i++) {
            var par = pares[i].split("=");
            var id = par[0];
            var codigo2 = par[1];
            nuevoObjeto[id] = codigo2;
        }

        console.log(nuevoObjeto);
        var listaEnlacesHTML = "";
       
        // Itera sobre las propiedades del objeto y construye los elementos HTML
        for (var id in nuevoObjeto) {
            if (nuevoObjeto.hasOwnProperty(id)) {
                // Construye el enlace utilizando el ID y el código correspondiente
                //var enlace = "<a href='/thyssenplastic/graficoBobina/" + id + "'><i class='fa fa-pie-chart'></i></a>";
                // Agrega el código y el enlace a la listaCodigosHTML
                //listaEnlacesHTML += "<p>Código: " + nuevoObjeto[id] + " " + enlace + "</p>";
                listaEnlacesHTML += "<p>Código: " + nuevoObjeto[id] + "</p>";
            }
        }

        
        $('#listCodigosOrdenProduccionn').html(listaEnlacesHTML);
        $('#codigoOrdenProduccionn').text(codigoOrdenProduccionn);
        
    });
    
    $('#miModal3').on('show.bs.modal', function (event) {
        let button = $(event.relatedTarget);
        let codigoDeIngresoADeposito = button.data('codigo');
        let estadoActual = button.data('estadoactual');
        let estadoActualDeposito = button.data('estadoactualdeposito');
        let valoPk = button.data('pk');
        var opcionesBuultos = button.data('lista');
        
        

        document.getElementById('codigoDeIngresoADeposito').textContent = codigoDeIngresoADeposito;
        document.getElementById('estadoActual').textContent = estadoActual;
        document.getElementById('estadoActualDeposito').textContent = estadoActualDeposito;
        
        // Obtener el elemento del campo oculto por su ID
       var pkInput = document.getElementById("pkEditar");

       // Establecer el valor deseado
       pkInput.value = valoPk;
        
        // Elimina los caracteres '{' y '}' del principio y del final de la cadena
        var limpio = opcionesBuultos.slice(1, -1);

        // Divide la cadena en pares clave-valor separados por coma y crea un array de estos pares
        var pares = limpio.split(", ");

        // Inicializa un objeto vacío para almacenar los resultados
        var nuevoObjeto2 = {};

        // Itera sobre los pares clave-valor y asigna las claves como IDs y los valores como códigos al nuevo objeto
        for (var i = 0; i < pares.length; i++) {
            var par = pares[i].split("=");
            var id = par[0];
            var codigo2 = par[1];
            nuevoObjeto2[id] = codigo2;
        }

        // Limpiar el select antes de agregar las nuevas opciones
        $('#idBultoEditar').empty();

        // Agregar la opción por defecto
        $('#idBultoEditar').append($('<option>', {
            value: -1,
            text: 'Seleccionar...'
        }));

        // Iterar sobre las claves y agregar las opciones al select
        for (var key in nuevoObjeto2) {
            if (nuevoObjeto2.hasOwnProperty(key)) { // Aquí corregí el nombre del objeto
                $('#idBultoEditar').append($('<option>', {
                    value: key,
                    text: nuevoObjeto2[key]
                }));
            }
        }
        
        // Mostrar el div si el estadoActualDeposito contiene una 'P', ocultarlo en caso contrario
        if (codigoDeIngresoADeposito.includes('P')) {
            $('#divBulto').show();
        } else {
            $('#divBulto').hide();
        }

        
        
    });
    
     $('#miModal3').on('hidden.bs.modal', function (event) {
        console.log('Evento hidden.bs.modal ejecutado');
        // Vacía el contenido del modal
        $('#codigoDeIngresoADeposito').empty();
        $('#estadoActual').empty();
        $('#estadoActualDeposito').empty();
        $('#textoAdicional').hide();
        $('#textoAdicionalPallet').hide();
        
        $('#estadoCalidadSelect').val(-1);
  
    });
     
    
    // Función para realizar la búsqueda
    function searchOrders() {
        var input, filter, cards, card, i, txtValue;
        input = document.getElementById("searchInput");
        filter = input.value.toUpperCase();
        cards = document.getElementsByClassName("order-card");

        for (i = 0; i < cards.length; i++) {
            card = cards[i];
            txtValue = card.textContent || card.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                card.style.display = "";
            } else {
                card.style.display = "none";
            }
        }
    }
    
    function filtrarTabla() {
        var filtro = document.getElementById("filtroDeposito").value;
        var filas = document.querySelectorAll("#ingresarDepositosTable tbody tr");

        filas.forEach(function (fila) {
            if (filtro === "todos" || fila.getAttribute("data-deposito-id") === filtro) {
                fila.style.display = "";
            } else {
                fila.style.display = "none";
            }
        });
    }
    
    // Bloquea la interacción con el elemento select
    document.getElementById('idDeposito').addEventListener('mousedown', function(event) {
        event.preventDefault(); // Evita que se abra el menú de selección
        this.blur(); // Quita el foco del elemento para evitar que se active con la tecla Enter
    });

    // Evita que se cambie el valor del select con las teclas de flecha
    document.getElementById('idDeposito').addEventListener('keydown', function(event) {
        event.preventDefault();
    });
    
    $(document).ready(function(){
    $('#estadoCalidadSelect').change(function(){
        var selectedOption = $(this).val();
        console.log(selectedOption);
        if(selectedOption !== '-1') {
            $('#textoReparacion').show();
            if ($('#codigoDeIngresoADeposito').text().startsWith('P')) {
                $('#textoAdicionalPallet').show();
                $('#textoAdicional').hide();
            } else {
                $('#textoAdicional').show();
                $('#textoAdicionalPallet').hide();
            }
        } else {
            $('#textoAdicional').hide();
            $('#textoAdicionalPallet').hide();
        }
    });
});
</script>
<style>
    /* Estilos adicionales para el modal */
  .modal-contentt {
    background-color: #fefefe;
    padding: 8px;
    border-radius: 10px;
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




