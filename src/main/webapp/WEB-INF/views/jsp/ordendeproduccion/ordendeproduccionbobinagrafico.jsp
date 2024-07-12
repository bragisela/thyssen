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
                                    <li class="breadcrumb-item"><a href="">Gráfico Bobina</a></li>
                                    <li class="breadcrumb-item active">${titleGraficoBobina}</li>
                                </ol>
                            </div>

                            <div class="card-body">
                                <form:form class="form-horizontal" method="post"
                                        action="/thyssenplastic/graficoBobina/addOrEditOrRemove"
                                        modelAttribute="graficoBobinaForm" id="myForm">

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

                                        
                                        ${action}
                                        
                                        <p></p>
                                        ${graficoBobinaName}
                                        
                                        <p></p>

                                        <form:hidden path="pk" class="form-control"/>
                                        <form:hidden path="action" class="form-control"/>
                                        <form:hidden path="operacion" class="form-control"/>
                                        <form:hidden path="idOrdenDeProduccion" class="form-control"/>
                                        <form:hidden path="idBobina" class="form-control"/>

                                        <div class="tab-content">

                                            <div class="form-row row">                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <a href="/thyssenplastic/ordenDeProduccionDetalle/${idOrdenDeProduccion}" id="ordprod">Atrás</a>
                                                </div>                                                                                                    
                                            </div>
                                            
                                            <p>
                                                
                                            <div class="form-row row">                                                
                                                <div class="row col-xs-12 col-sm-12 col-xl-12">
                                                    <label for="inputFecha">Bobina <i>${idBobina}</i></i></label>
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
                                                    <label for="inputFecha">Carga Valores</label>
                                                </div>                                                                                                    
                                            </div>
                                                
                                            <p>
                                                                                            
                                            <div class="form-row row">                       

                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputRubro">Espesor Nominal</label>
                                                    <form:input type="number" path="espesorNominal" step="0.01" class="form-control" placeholder="" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                                </div>
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputRubro">Máximo</label>
                                                    <form:input type="number" path="espesorNominalMas" step="0.01" class="form-control" placeholder="" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                                </div>
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputRubro">Mínimo</label>
                                                    <form:input type="number" path="espesorNominalMenos" step="0.01" class="form-control" placeholder="" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                                </div>                                                                                                                                                                                                  
                                            </div>
  
                                        </div>
                                                
                                        <p></p> 

                                        <div class="form-row row">
                                            <div class="row col-xs-9 col-sm-1 col-xl-1" style="display:${displayActionButton}">
                                                <button type="button" class="btn btn-primary" onclick="callController()">${buttonLabel}</button>                                            
                                            </div>
                                            <div class="row col-xs-9 col-sm-1 col-xl-1">
                                                <a href="/thyssenplastic/graficoBobina/${idOrdenDeProduccionBobina}/"><button type="button" class="btn btn-secondary">Cancelar</button></a>
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
                            <table id="graficosBobinaTable" class="display table table-striped table-hover cell-border">
                                <thead>
                                    <tr>
                                        <th>CODIGO</th>
                                        <th>FECHA</th>
                                        <th>#MEDICIONES</th>
                                        <th>ESPESOR NOMINAL</th>
                                        <th style="text-align: center">ACCIONES</th>
                                    </tr>
                                </thead>

                                <tbody>

                                    <c:forEach items="${graficoBobinas}" var="graficoBobina">
                                        <tr>
                                            <td>
                                                <c:out value="${graficoBobina.pk}" />
                                            </td>                                            
                                            <td>
                                                <c:out value="${graficoBobina.fechaAlta}" />
                                            </td>                                            
                                            <td>
                                                <c:out value="${graficoBobina.mediciones}" />
                                            </td>                                                                                        
                                            <td>
                                                <c:out value="${graficoBobina.espesorNominal}" />
                                            </td>                                                                                                                                                                                                                                                                                                                 
                                            <td>
                                                <c:if test = "${rol == 'oficina' && operacion == 'alta'}">
                                                    <a class="nav-link active fa fa-pencil-square-o fa-lg"
                                                        href="/thyssenplastic/graficoBobina/edit/${graficoBobina.pk}"
                                                        data-toggle="tooltip" data-placement="top" title="Editar"></a>
                                                </c:if>                                                        
                                                <c:if test = "${rol == 'oficina'}">
                                                    <a class="nav-link active fa fa-navicon fa-lg"
                                                        href="/thyssenplastic/graficoBobinaDetalle/${graficoBobina.pk}/tipoIngreso/manual"
                                                        data-toggle="tooltip" data-placement="top" title="Detalle"></a>
                                                </c:if>                                   
                                                <c:if test = "${rol == 'oficina' && graficoBobina.tieneMediciones == 'true'}">
                                                    <a class="nav-link active fa fa-pie-chart fa-lg"
                                                       href="/thyssenplastic/graficoBobinaGrafico/${graficoBobina.pk}"
                                                        data-toggle="tooltip" data-placement="top" title="Gráfico Polar"></a>
                                                </c:if>
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
        
        $('#graficosBobinaTable').DataTable({
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
            },           
            order: [[1, 'desc']]            
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




