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
                                <li class="breadcrumb-item"><a href="">Reporte</a></li>
                                <li class="breadcrumb-item active">${titleReporte}</li>
                            </ol>
                        </div>
                        <div class="card-body">
                            <div class="container">
                                <form:form class="form-horizontal" method="post" action="/thyssenplastic/reporte/searchpallet" modelAttribute="planillaPalletForm">
                                    <p></p>
                                    ${reporteName}
                                    <p></p>
                                    
                                    <form:hidden path="action" class="form-control"/>
                                    
                                    
                                    <div class="form-row">
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputFecha_alta">Fecha Desde </label>
                                            <form:input type="date" path="fechaDesde" class="form-control" placeholder="" id="holderDateOfBirth" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>                                            
                                        </div>
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputFecha_alta">Fecha Hasta</label>
                                            <form:input type="date" path="fechaHasta" class="form-control" placeholder="" id="holderDateOf" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>                                            
                                        </div>  
                                        
                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                <label for="inputRol">Seleccione Orden de Trabajo</label>
                                                <input type="hidden" id="idOrdenDeProduccionHidden" name="idOrdenDeProduccion" />
                                                <input type="text" id="provinciaInput" class="form-control" list="provinciaList" placeholder="Buscar orden de trabajo..." required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')">
                                                <datalist id="provinciaList">
                                                <c:forEach items="${provinciaList}" var="provincia" >
                                                 <option id="provincia" value="${provincia}" />
                                                </c:forEach>
                                                </datalist>
                                            </div>
                                         <div class="row col-xs-12 col-sm-12 col-xl-4 text-center">
                                           <br />
                                        </div>
                                        <div class="row col-xs-9 col-sm-9 col-xl-4 text-center" style="margin: 8px">
                                            <button type="submit" onclick="buscar()" style="text-decoration: none; color: #fff; padding: 9px 15px; border: 1px solid #fff; border-radius: 5px; background: #367fa9">${buttonLabel}</button>
                                           
                                            <a href="/thyssenplastic/reporte/planillaPallet" style="text-decoration: none; color: #367fa9; padding: 10px 5px; border: 1px solid #367fa9; border-radius: 5px; transition: background-color 0.3s, color 0.3s;" onmouseover="this.style.backgroundColor='#367fa9'; this.style.color='#fff';" onmouseout="this.style.backgroundColor='#fff'; this.style.color='#367fa9';">Limpiar Filtros</a>
                                        </div>
                                    </div>
                                        
                                </form:form>
                            </div>
                        </div>
                    </div>                
                </ol>
            </div>
                            <div class="row col-xs-12 col-sm-12 col-xl-12">
                            <table id="ordendeproduccionbultosTable" class="display table table-striped table-hover cell-border">
                    <thead>
                        <tr>
                            <th>NRO PALLET</th>
                            <th>FECHA ALTA</th>
                            <th>CANT. BULTOS</th>
                            <th>CÓDIGO DEL BULTO</th> <!-- Nueva columna para código de bulto -->
                            <th>PESO TOTAL</th>
                            <th>CALIDAD</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${ordenDeProduccionPallets}" var="ordendeproduccionpallet">
                            <c:if test="${ordendeproduccionpallet.estado == 'ok'}">
                                <tr style="color: green; font-weight:bold">
                            </c:if>
                            <!-- Resto de tus condiciones de estilo -->

                            <!-- Columnas comunes -->
                            <td>
                                <c:out value="${ordendeproduccionpallet.codigo}" />
                            </td>
                            <td>
                                <c:out value="${ordendeproduccionpallet.fechaAlta}" />
                            </td>

                            <!-- Columna de cantidad de bultos -->
                            <td>
                                <c:out value="${ordendeproduccionpallet.cantidadBultos}" />
                            </td>

                            <!-- Nueva columna para código de bulto -->
                            <td>
                                <c:forEach items="${ordendeproduccionpallet.listaCodigoBultos}" var="codigoBulto">
                                    <c:out value="${codigoBulto}" />
                                    <br/> <!-- Puedes ajustar el formato según tus necesidades -->
                                </c:forEach>
                                    
                            </td>

                            <!-- Columnas comunes restantes -->
                            <td>
                                <c:out value="${ordendeproduccionpallet.pesoTotal}" />
                            </td>
                            <td>
                                <c:out value="${ordendeproduccionpallet.estadoLabel}" />
                            </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                </div>
                <div class="row col-xs-12 col-sm-12 col-xl-12">
                    <br />
                   </div>
                                                 
            
            <div class="container">
                
                      
                            <div id="plegadora1">
                                <div class="row col-xs-12 col-sm-12 col-xl-12">
                                        <table style="width: 90%; border-collapse: collapse;">
                                            <tr>
                                                 <th rowspan="4" style="background-color: #fff; border: 1px solid #000000; text-align: center; padding: 8px; max-width: 70px; max-height: 50px">
                                                    <img style="max-width: 50px; max-height: 50px; vertical-align: middle;" src="<c:url value='/resources/assets/logoTPS.png'/>">
                                                    <br>
                                                    Thyssen Plastic Solutions
                                                </th>
                                                <th style="background-color: #fff; border: 1px solid #000000; text-align: center; padding: 8px;" rowspan="3">PLANILLA DE PALLET</th>
                                                <th style="background-color: #fff; border: 1px solid #000000; text-align: left; padding: 8px;">Codigo:   </th>
                                            </tr>
                                            <tr>
                                                
                                                <th style="background-color: #fff; border: 1px solid #000000; text-align: left; padding: 8px;">Revision:   </th>
                                            </tr>
                                            <tr>
                                               <th style="background-color: #fff; border: 1px solid #000000; text-align: left; padding: 8px;">Vigencia:   </th>
                                            </tr>
                                        </table>
                                        <br />
                                        <table style="width: 90%; border-collapse: collapse;">
                                             <tr>
                                                <th style="background-color: #fff; border: 1px solid #000000; text-align: left; padding: 8px;">Fecha: ${fecha} a ${fechah}</th>
                                                <th style="background-color: #fff; border: 1px solid #000000; text-align: left; padding: 8px;">Turno: </th>
                                            </tr>
                                        </table>
                                        
                                        <table style="width: 90%; border-collapse: collapse;">
                                             <tr>
                                                 <th style="background-color: #fff; border: 1px solid #000000; text-align: left; padding: 8px;">Operarios: </th>
                                            </tr>
                                        </table>
                                        
                                        <table style="width: 90%; border-collapse: collapse;">
                                             <tr>
                                                <th style="background-color: #fff; border: 1px solid #000000; text-align: left; padding: 8px;">Medida: ${denominacion} </th>
                                                <th style="background-color: #fff; border: 1px solid #000000; text-align: left; padding: 8px;">Lote: ${orden} </th>
                                            </tr>
                                        </table>
                                        <br />
                                    </tbody>
                                  </table>
                                </div>
                            
       
                               
                                 
                                <div style="margin-left: 60px;">
                                    <div class="row col-xs-12 col-sm-12 col-xl-12">
                                        <br />
                                    </div>
                                    <c:forEach items="${ordenDeProduccionPallets}" var="ordendeproduccionpallet" varStatus="loopStatus">
                                    
                                    <div class="row col-xs-4 col-sm-4 col-xl-4 ">
                                        <table border="1" style="width: 90%";>
                                         <thead>
                                           <tr>
                                               <tr>
                                               <th colspan="2" style="font-weight: bold; padding: 8px; background-color: #fff; color: #000000; border: 1px solid #000000">Fecha: ${ordendeproduccionpallet.fechaAlta} </th>
                                                </tr>

                                             <th style="font-size: 16px; color: #00a65a; text-align: center;">Pallet</th>
                                             <th style="font-size: 16px; color: #00a65a; text-align: center;">Códigos de Bultos</th>
                                           </tr>
                                         </thead>
                                         <tbody>
                                           <tr>
                                             <td><h2 style="color: #00a65a; text-align: center;">${ordendeproduccionpallet.codigo}</h2></td>
                                             <td>
                                                <c:forEach items="${ordendeproduccionpallet.listaBultos}" var="codigoBulto">
                                                    <p style="font-size: 16px; text-align: center; margin: auto">${codigoBulto}</>
                                                    <hr style="margin: 0"/>
                                                 </c:forEach>
                                             </td>
                                           </tr>
                                         </tbody>
                                       </table>
                                    </div>
                                             
                                             <!-- Verificar si es la tercera iteración y no es la última -->
                                    <c:if test="${(loopStatus.index + 1) % 3 == 0 }">
                                    <div class="row col-xs-12 col-sm-12 col-xl-12">
                                           <br />
                                       </div>
                                        </c:if>
                                  </c:forEach>
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
    $('#ordendeproduccionbultosTable').DataTable({
        language: {
            "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
        },      
        dom: 'Bfrtip',
        "bPaginate": false,
        "bInfo": false,
        "bFilter": false,
        buttons: [
            'excel', {
                text: 'Imprimir',
                action: function (e, dt, node, config) {
                    $('#plegadora1, #plegadora2').printThis({
                        debug: false,
                        importCSS: true,
                        importStyle: true,
                        printContainer: true,
                        pageTitle: "thyssenplastic",
                        removeInline: false
                    });
                },
                exportOptions: {
                    columns: ':not(.no-export)'
                }
            }
        ]
    });
});

 function buscar() {
        var selectedValue = $('#provinciaInput').val();
        $('#idOrdenDeProduccionHidden').val(selectedValue);

        // Submit the form
        $('#submitButton').click();
        
    }
</script>
<style type="text/css" media="print">
    .buttons-print, .dt-buttons {
        display: none !important;
    }
</style>

<%@include file = "/WEB-INF/views/jsp/includes/footer.jsp" %>
