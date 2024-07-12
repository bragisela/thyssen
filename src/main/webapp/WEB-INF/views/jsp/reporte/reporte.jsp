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

                                <form:form class="form-horizontal" method="post" action="/thyssenplastic/reporte/search" modelAttribute="reporteForm">

                                    <p></p>
                                    ${reporteName}
                                    <p></p>
                                    
                                    <form:hidden path="action" class="form-control"/>
                                    
                                    <div class="form-row row">
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputFecha_alta">Fecha Desde</label>
                                            <form:input type="date" path="fechaDesde" class="form-control" placeholder="" id="holderDateOfBirth" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>                                            
                                        </div>
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputFecha_alta">Fecha Hasta</label>
                                            <form:input type="date" path="fechaHasta" class="form-control" placeholder="" id="holderDateOf"/>                                            
                                        </div>                                                    
                                    </div>

                                    <div class="form-row row">
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputRubro">Maquina</label>
                                            <form:select  path="idMaquina" class="form-control rubro">                                                
                                                <form:options items="${maquinaList}"/>
                                              </form:select>                                            
                                        </div>
                                    </div>

                                    <div class="form-row row">
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <button type="submit" class="btn btn-primary">${buttonLabel}</button>                                            
                                        </div>
                                    </div>
                                        
                                </form:form>
                            </div>
                        </div>
                    </div>                
                </ol>
            </div>

            <div class="card">

                <div class="card-body">
                    
                    <div class="row col-xs-12 col-sm-12 col-xl-12">
                        <table id="reportesTable" class="display table table-striped table-hover cell-border">
                            <thead>
                                <tr>
                                    <th>FECHA ALTA</th>
                                    <th>PROBLEMA</th>                                    
                                    <th>MAQUINA</th>
                                    <th>HORA PARADA</th>
                                    <th>HORA ARRANQUE</th>
                                    <th>TIEMPO REPARACION</th>
                                    <th>REPUESTO</th>
                                    <th>ESTADO</th>
                                </tr>
                            </thead>

                            <tbody>

                                <c:forEach  items="${reportes}" var="reporte">
                                    <tr>
                                        <td><c:out value="${reporte.fechaAlta}" /></td>
                                        <td><c:out value="${reporte.problema}" /></td>
                                        <td><c:out value="${reporte.maquina}" /></td>
                                        <td><c:out value="${reporte.horaParada}" /></td>
                                        <td><c:out value="${reporte.horaAranque}" /></td>
                                        <td><c:out value="${reporte.tiempoReparacion}" /></td>
                                        <td><c:out value="${reporte.repuesto}" /></td>
                                        <td><c:out value="${reporte.estado}" /></td>
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
        
        $('#reportesTable').DataTable({
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
            },      
            "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "Todos"]],
            dom: 'Bfrtip',
            buttons: [
                'pageLength','excel', 'pdf', 'print'
            ]       
        });
    });
    

</script>

<%@include file = "/WEB-INF/views/jsp/includes/footer.jsp" %>
