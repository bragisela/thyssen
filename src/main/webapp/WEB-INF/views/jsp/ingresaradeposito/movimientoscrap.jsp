<%-- 
    Document   : index
    Created on : 9 ago. 2022, 17:43:42
    Author     : waltergustavorojo
--%>
<%@include file = "/WEB-INF/views/jsp/includes/header.jsp" %>


<!-- Content Wrapper. Contains page content -->
<style>
    /* Agrega este CSS en tu archivo de estilos o en una etiqueta <style> en tu JSP */

.table {
    width: 90%;
    border-collapse: collapse;
    margin: 20px 0;
    font-size: 16px;
    color: #333;
    justify-content: center;
}

.table thead {
    background-color: #f4f4f4;
}

.table th, .table td {
    padding: 10px;
    text-align: left;
    border-bottom: 1px solid #ddd;
}

.table tbody tr:hover {
    background-color: #f1f1f1;
}

.table th {
    background-color: #009688;
    color: white;
}

.table td {
    background-color: white;
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

.contenedor{
    display: flex;
    flex-direction: column;
    align-items: center;
}

</style>
<div class="content-wrapper">
  
     
    <section class="content">
    <div class="contenedor">
        <h1>Trazabilidad Scrap</h1>
        <h1>Codigo: S23</h1>

        <table class="display table table-striped table-hover cell-border">
                <thead>
                    <tr>
                        <th>Codigo</th>
                        <th>Estado</th>
                        <th>Fecha Alta Remito</th>
                        <th>Usuario Alta Remito</th>
                        <th>Fecha</th>
                        <th>Peso(kg)</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="scrap" items="${remitos}">
                        <tr>
                            <td>${scrap.codigoRemito}</td>
                            <td>${scrap.estadoRemito}</td>
                            <td>${scrap.fechaAltaRemito}</td>
                            <td>${scrap.usuarioAltaRemito}</td>
                            
                            <td>${scrap.fechaAltaDetalle}</td>
                            <td>${scrap.cantidadUtilizadaRemito}</td>
                            
                            <!-- Muestra más datos según las propiedades de OrdenDeProduccionScrapModel -->
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        

     
    </div>
</section>
                                             
</div>              

<!-- ./wrapper -->

<%@include file = "/WEB-INF/views/jsp/includes/footer.jsp" %>




