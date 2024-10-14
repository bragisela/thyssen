<%-- 
    Document   : index
    Created on : 9 ago. 2022, 17:43:42
    Author     : waltergustavorojo
--%>
<%@include file = "/WEB-INF/views/jsp/includes/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!-- Content Wrapper. Contains page content -->
<style>
    /* Agrega este CSS en tu archivo de estilos o en una etiqueta <style> en tu JSP */

.table {
    width: 90%;
    border-collapse: collapse;
    margin: 5px 0;
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
            <h1>Deposito Scrap</h1>

            <table class="display table table-striped table-hover cell-border">
                <thead>
                    <tr>
                        <th>Orden de produccion</th>
                        <th>Fecha</th>
                        <th>Codigo</th>
                        <th>Peso Inical(kg)</th>
                        <th>Peso Utilizado(kg)</th>
                        <th>Peso Disponible(kg)</th>
                        <th>Tipo</th>
                        <th>Acciones</th>
                        <!-- Agrega más columnas según las propiedades de OrdenDeProduccionScrapModel -->
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="scrap" items="${depositoList}">
                        <tr>
                            <td>${scrap.idOrdenDeProduccion}</td>
                            <td>
                                <fmt:formatDate value="${scrap.fechaAlta}" pattern="dd/MM/yyyy" />
                            </td>
                            <td>${scrap.codigo}</td>
                            <td>${scrap.pesoTotal}</td>
                            <td>${scrap.cantidadUtilizada}</td>
                            <td>${scrap.pesoTotal - scrap.cantidadUtilizada}</td>
                             <td>
                                <c:choose>
                                    <c:when test="${scrap.idBulto != null}">
                                        Bulto rechazado
                                    </c:when>
                                    <c:when test="${scrap.idBobina != null}">
                                        Bobina rechazada
                                    </c:when>
                                    <c:otherwise>
                                        Scrap
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            
                            <td>
                                
                                <a class="nav-link active fa fa-eye fa-lg" href="/thyssenplastic/verMovimientos/${scrap.id}" data-toggle="tooltip" data-placement="top" title="Ver movimientos"></a>
                                <a class="nav-link active fa fa-print fa-lg"
                                    href="javascript:void(0);" onclick="printEtiquetaScrap(${scrap.id})"
                                    data-toggle="tooltip" data-placement="top" title="Imprimir" id="imprimirScrap${scrap.id}"></a>  

                            </td>

                            <!-- Muestra más datos según las propiedades de OrdenDeProduccionScrapModel -->
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        
        <div class="pagination">
            <c:if test="${currentPage > 1}">
                <a href="/thyssenplastic/ingresarDepositoScrap?page=${currentPage - 1}&size=${pageSize}">Anterior</a>
            </c:if>
                <span>${currentPage}</span>
            <c:if test="${depositoList.size() == pageSize}">
                <a href="/thyssenplastic/ingresarDepositoScrap?page=${currentPage + 1}&size=${pageSize}">Siguiente</a>
            </c:if>
        </div>
        
    </section>                                                
</div>              
            
            <script>
                function printEtiquetaScrap(ordendeproduccionscrappk) {
        window.open("/thyssenplastic/ordenDeProduccionDetalle/print/scrap/"+ordendeproduccionscrappk, "Imprimir Etiqueta Scrap", "popup,width=1280,height=800");
    }
                
                </script>
           
<!-- ./wrapper -->

<%@include file = "/WEB-INF/views/jsp/includes/footer.jsp" %>




