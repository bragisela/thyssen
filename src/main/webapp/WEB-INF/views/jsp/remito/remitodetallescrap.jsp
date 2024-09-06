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
                                    <li class="breadcrumb-item"><a href="">Remito Detalle Scrap</a></li>
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
                         
                                
                                <div class="row col-xs-12 col-sm-12 col-xl-12">
                                    <div class="modal fade rounded" id="miModalEgreso" tabindex="-1" role="dialog" aria-labelledby="miModalLabel" aria-hidden="true" data-backdrop="static">
                                        <div class="modal-dialog modal-lg modal-dialog-centered">
                                            <div class="modal-content">
                                                <div class="modal-header bg-info text-white">
                                                    <h4 class="modal-title text-center" id="miModalLabel">Remito Scrap </h4>
                                                </div>
                                                
                                                <div class="modal-body">
                                                    <div class="row">
                                                        <div class="col-xs-6 col-sm-6 col-xl-6">
                                                            <div class="articulo-section border rounded p-3 mb-3 bg-light">
                                                                <h5 class="text-center text-info">Artículo</h5>
                                                                <ul class="list-group">
                                                                    <!-- Datos del artículo (puedes reemplazar estos ejemplos con tus propios datos) -->
                                                                   
                                                                    <li class="list-group-item">Lote: <span id="deposito" class="font-weight-bold text-primary"></span></li>
                                                                    <li class="list-group-item">Deposito: Scrap<span id="valorEnModal" class="font-weight-bold text-primary"></span></li>
                                                                    <form:form class="form-horizontal" method="post" action="/thyssenplastic/egresoDepositoScrap/addOrEditOrRemove" modelAttribute="egresoScrapForm" id="myFormm">
                                                                    <form:hidden path="pk" class="form-control"/>
                                                                    <form:hidden path="action" class="form-control"/>
                                                                
                                                                  
                                                                    <form:hidden path="idRemito" class="form-control"/>
                                                            
                                                                    <form:hidden path="tipo" class="form-control"/>
                                                                   <form:hidden path="idOrdenDeProduccionScrap" class="form-control"/>
                                                                   <form:hidden path="idOrdenDeProduccionE" class="form-control"/>
                                                                </ul>
                                                            </div>
                                                            
                                                            <div class="articulo-section border rounded p-3 mb-3 bg-light" style="max-height: 200px; overflow-y: auto;">
                                                                <h5 class="text-center text-info">Datos</h5>
                                                                <ul class="list-group">
                                                                    <!-- Datos del artículo (puedes reemplazar estos ejemplos con tus propios datos) -->
                                                                    <li class="list-group-item">Peso Disponible: <span id="pesoDisp"></span><span class="font-weight-bold text-primary"> Kgrs</span></li>
                                                                    <li class="list-group-item">Origen: <span id="origen"></span></li>
                                                                    <li class="list-group-item">Tipo Material: <span id="tipoMaterial"></span></li>
                                                                    <li class="list-group-item">Motivo: <span id="motivo"></span></li>
                                                                    <li class="list-group-item">Formato: <span id="formato"></span></li>
                                                                   <li class="list-group-item">Recuperable: <span id="recuperable"></span></li>
                                                                   <li class="list-group-item">Material Impreso: <span id="matImpreso"></span></li>
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
                                                            <div style="height: 200px; overflow-y: hidden;">
                                                                </c:if>
                                                                
                                                                  <div>
                                                                <label for="inputIsScrap">Utilizar al 100%</label>
                                                                <form:select id="inputIsScrap" path="idOrdenDeProduccionE" class="form-control" onchange="toggleInputField()">
                                                                     <form:option value="true" label="Sí" />
                                                                    <form:option value="false" label="No" />

                                                                </form:select>
                                                            </div>
                                                            
                                                            <div id="inputContainer" style="display: none; margin-top: 10px;">
                                                                <label for="inputPartialUsage">Cantidad kgrs a utilizar</label>
                                                                <input type="string" path="idOrdenDeProduccionE" id="inputIsScrap" class="form-control" min="0" max="100" placeholder="Ingrese ls cantidad kgrs a utilizar">
                                                            </div>
                                                                
                                                            
                                                                
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <ul class="list-group">
                                                        <h3 class="text-center text-danger"><span id="listCodigosOrdenProduccion"></span></h3>
                                                    </ul>
                                                </div>

                                                <div class="modal-footer bg-light">
                                                    <button id="miBoton" type="button" class="btn btn-primary" onclick="callController2()">Agregar</button>
                                                    </form:form>
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar/Cancelar</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                <hr>
                
                <table class="display table table-striped table-hover cell-border">
                <thead>
                    <tr>
                  
                        <th>Orden de produccion</th>
                      
                        <th>Peso Total (kg)</th>
                        <th>Acciones</th>
                        <!-- Agrega más columnas según las propiedades de OrdenDeProduccionScrapModel -->
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="scrap" items="${depositoList}">
                        <tr>
                            <td>${scrap.idOrdenDeProduccion}</td>
                            <td>${scrap.pesoSuma}</td>

                            <td>
                             <button type="button"  class="btn btn-primary btn-agregarE" data-toggle="modal"  data-target="#miModalEgreso"  data-deposito-idB="${scrap.idOrdenDeProduccion}" data-loteB="5" data-remito="5">Seleccionar</button>
                            </td>
                         
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
                
                <div class="pagination">
                    <c:if test="${currentPage > 1}">
                        <a href="/thyssenplastic/remitoDetalleScrap/${idRemito}?page=${currentPage - 1}&size=${pageSize}">Anterior</a>
                    </c:if>
                        <span>${currentPage}</span>
                    <c:if test="${depositoList.size() == pageSize}">
                        <a href="/thyssenplastic/remitoDetalleScrap/${idRemito}?page=${currentPage + 1}&size=${pageSize}">Siguiente</a>
                    </c:if>
                </div>
                
            </div>
        </section>
    </div>
                  

<!-- ./wrapper -->
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
    
    .pagination {
    display: flex;
    justify-content: center;
    margin: 20px 0;
}

.pagination a {
    color: #009688;
    padding: 10px 15px;
    margin: 0 5px;
    border: 1px solid #ddd;
    text-decoration: none;
    border-radius: 5px;
    background-color: #fff;
}

.pagination a:hover {
    background-color: #009688;
    color: white;
}

.pagination span {
    padding: 10px 15px;
    margin: 0 5px;
    border: 1px solid #ddd;
    border-radius: 5px;
    background-color: #009688;
    color: white;
}

    
    </style>
    
    <script>
    
    //select
    function toggleInputField() {
        const selectElement = document.getElementById('inputIsScrap');
        const inputContainer = document.getElementById('inputContainer');
        
        if (selectElement.value === 'false') {
            inputContainer.style.display = 'block'; // Mostrar el input
        } else {
            inputContainer.style.display = 'none'; // Ocultar el input
        }
    }
    
    //limpiar modal cuando se cierra
    
    
    
    //buscar codigo de scrap
    
    
    
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
    
    
    $('#miModalEgreso').on('hidden.bs.modal', function (event) {
        console.log('Evento hidden.bs.modal ejecutado');
        // Vacía el contenido del modal
        $("#pesoDisp").empty();
        $("#origen").empty();
        $("#motivo").empty();
        $("#formato").empty();
        $("#recuperable").empty();
        $("#matImpreso").empty();
        
        
        $('#myFormm')[0].reset();
    });
    
        $('.btn-agregarE').on('click', function () {
            const depositoIdE = $(this).attr('data-deposito-idB');
            
            $('#idOrdenDeProduccionE').val(depositoIdE);
       
            $('#deposito').text(depositoIdE);
        });
    
    function callController2() {
                       
        const codigoScrap = $("#codigo").val();
                
        if((codigoScrap === '-1' || codigoScrap === '')) {
            alert('Debe ingresar codigo de scrap');
            return;
        }

        if(action == 'remove') {
            if(confirm('Desea eliminarlo')) {
                var formm = document.getElementById("myFormm");
                formm.submit();
            }
        } else {
            if($("#myFormm")[0].checkValidity()) {
                var formm = document.getElementById("myFormm");
                //console.log(formm);
                formm.submit();
            } else {
                $("#myFormm")[0].reportValidity();
            }        
        }        
    }
    
    
    //busco codigo
    
    function buscarCodigo() {
        
        const codigoScrap = $("#codigo").val();
        const ordenProduccionScrap = $("#idOrdenDeProduccionE").val();
   
        if(codigoScrap == '' || codigoScrap == null || codigoScrap == undefined) {
            alert('Debe ingresar un código');
            return;
        }
 
        var sUrl = '/thyssenplastic/depositoScrap/findScrap/'+codigoScrap+'/'+ordenProduccionScrap;
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

                if(item.tipo === 'scrap') {
 
                    $("#pesoDisp").text(item.pesoTotal);
                     $("#origen").text(item.origen);
                      $("#motivo").text(item.motivo);
                       $("#formato").text(item.formato);
                     $("#recuperable").text(item.recuperable);
                     $("#matImpreso").text(item.materialImpreso);
 
                } 
                                
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

<%@include file = "/WEB-INF/views/jsp/includes/footer.jsp" %>




