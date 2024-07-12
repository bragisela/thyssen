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
                                <form:form class="form-horizontal" method="post" action="/thyssenplastic/reporte/searchplegado" modelAttribute="planillaPlegadoForm">
                                    <p></p>
                                    ${reporteName}
                                    <p></p>
                                    
                                    <form:hidden path="action" class="form-control"/>
                                    
                                    <div class="form-row row">
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputFecha_alta">Fecha </label>
                                            <form:input type="date" path="fechaDesde" class="form-control" placeholder="" id="holderDateOfBirth" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>                                            
                                        </div>
                                        
                                    </div>
                                        
                                   <div class="row col-xs-12 col-sm-12 col-xl-4 text-center">
                                           <br />
                                        </div>
                                        
                                    <div class="row col-xs-6 col-sm-6 col-xl-4 text-center">
                                            <button type="submit" onclick="buscar()" style="text-decoration: none; color: #fff; padding: 9px 15px; border: 1px solid #fff; border-radius: 5px; background: #367fa9">${buttonLabel}</button>
                                            <a href="/thyssenplastic/reporte/planillaPlegado" style="text-decoration: none; color: #367fa9; padding: 10px 5px; border: 1px solid #367fa9; border-radius: 5px; transition: background-color 0.3s, color 0.3s;" onmouseover="this.style.backgroundColor='#367fa9'; this.style.color='#fff';" onmouseout="this.style.backgroundColor='#fff'; this.style.color='#367fa9';">Limpiar Filtros</a>
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
                    <th>LOTE</th>
                    <th>NRO BULTO</th>
                    <th>FECHA ALTA</th>
                    <th>PLEGADORA</th>                                                                                            
                    <th>CALIDAD</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${ordenDeProduccionBultos}" var="ordendeproduccionbulto">
                <c:if test = "${ordendeproduccionbulto.estado == 'ok'}">             
                <tr style="color: green; font-weight:bold">
                </c:if>
                <c:if test = "${ordendeproduccionbulto.estado == 'observado'}">             
                <tr style="color: #89B00F; font-weight:bold">
                </c:if>
                <c:if test = "${ordendeproduccionbulto.estado == 'rechazado'}">             
                <tr style="color: red; font-weight:bold">
                </c:if>
                <c:if test = "${ordendeproduccionbulto.estado == 'sinmesurar'}">             
                    <tr style="color: orange; font-weight:bold">
                </c:if>
                <c:if test = "${ordendeproduccionbulto.estado == null || ordendeproduccionbulto.estado == ''}">             
                <tr>
                </c:if>   
                 <td>
                <c:out value="${ordendeproduccionbulto.idOrdenDeProduccion}" />
                </td>  
                <td>
                <c:out value="${ordendeproduccionbulto.codigo}" />
                </td>                                            
                <td>
                    <c:out value="${ordendeproduccionbulto.fechaAlta}" />
                </td>                                                                                                                                            
                                                                                                                                                                                                                                           
                <td>
                    <c:out value="${ordendeproduccionbulto.plegadora}" />
                </td>                                                                                                                                                                                                                                            
                <td>
                    <c:out value="${ordendeproduccionbulto.estadoLabel}" />
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
                <div class="card">
                    <div class="card-body">
                        <div class="container">
                            <div id="plegadora1">
                            <c:forEach var="ordenEntry" items="${ordenDeProduccionBultosAgrupadosDoble}">
                                <c:set var="ordenProduccion" value="${ordenEntry.key}" />
                                <c:set var="plegadorasMap" value="${ordenEntry.value}" />
                                <c:set var="denominacion" value="${denominacionPorOrden[ordenProduccion]}" />
                                <div class="row col-xs-12 col-sm-12 col-xl-12">
                                        <table style="width: 90%; border-collapse: collapse;">
                                            <tr>
                                                 <th rowspan="4" style="background-color: #fff; border: 1px solid #000000; text-align: center; padding: 8px; max-width: 70px; max-height: 50px">
                                                    <img style="max-width: 50px; max-height: 50px; vertical-align: middle;" src="<c:url value='/resources/assets/logoTPS.png'/>">
                                                    <br>
                                                    Thyssen Plastic Solutions
                                                </th>
                                                <th style="background-color: #fff; border: 1px solid #000000; text-align: center; padding: 8px;" rowspan="3">PLANILLA DE PLEGADO</th>
                                                <th style="background-color: #fff; border: 1px solid #000000; text-align: left; padding: 8px;">Codigo: </th>
                                            </tr>
                                            <tr>
                                                <th style="background-color: #fff; border: 1px solid #000000; text-align: left; padding: 8px;">Revision: </th>
                                            </tr>
                                            <tr>
                                               <th style="background-color: #fff; border: 1px solid #000000; text-align: left; padding: 8px;">Vigencia: </th>
                                            </tr>
                                        </table>
                                        <br />
                                        <table style="width: 90%; border-collapse: collapse;">
                                             <tr>
                                                <th style="background-color: #fff; border: 1px solid #000000; text-align: left; padding: 8px;">Fecha: ${fecha}</th>
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
                                                <th style="background-color: #fff; border: 1px solid #000000; text-align: left; padding: 8px;">Lote: ${ordenProduccion} </th>
                                            </tr>
                                        </table>
                                        <br />
                                    </div>
                                    <div class="row col-xs-12 col-sm-12 col-xl-12">
                                     <c:forEach var="plegadoraEntry" items="${plegadorasMap}">
                                    <c:set var="plegadora" value="${plegadoraEntry.key}" />
                                    <c:set var="plegadoraDtos" value="${plegadoraEntry.value}" />
                                    <table id="reportesTable" style="width: 90%; border-collapse: collapse;">
                                        <thead>
                                            <tr>
                                              <th colspan="4" style="font-weight: bold; padding: 8px; background-color: #fff; color: #000000; text-align: center; border: 1px solid #000000">${plegadora}</th>
                                            </tr>
                                            <tr style="background-color: #4CAF50; color: #ffffff;">
                                                <th style="border: 1px solid #000000; padding: 8px;">Nro Etiqueta</th>
                                                <th style="border: 1px solid #000000; padding: 8px;">Calidad</th>
                                                <th style="border: 1px solid #000000; padding: 8px;">Fecha y Hora</th>
                                                <th style="border: 1px solid #000000; padding: 8px;">Observaciones</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="dto" items="${plegadoraDtos}">
                                            <tr>
                                                <td style="border: 1px solid #000000; padding: 8px; background-color: #fff;">${dto.codigo}</td>
                                                <td style="border: 1px solid #000000; padding: 8px; background-color: #fff;">${dto.estadoLabel}</td>
                                                <td style="border: 1px solid #000000; padding: 8px; background-color: #fff;">${dto.fechaAlta}</td>
                                                <td style="border: 1px solid #000000; padding: 8px; background-color: #fff;">${dto.observaciones}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    <br />
                                    </c:forEach>   
                                </div>
                             </c:forEach>               
                            </div>
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
