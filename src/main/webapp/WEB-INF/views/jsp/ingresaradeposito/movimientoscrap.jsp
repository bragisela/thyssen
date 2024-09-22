<%-- 
    Document   : index
    Created on : 9 ago. 2022, 17:43:42
    Author     : waltergustavorojo
--%>
<%@include file = "/WEB-INF/views/jsp/includes/header.jsp" %>


<!-- Content Wrapper. Contains page content -->
<style>
    /* Agrega este CSS en tu archivo de estilos o en una etiqueta <style> en tu JSP */

body {
    background-color: #f4f6f9;
    margin: 0;
    padding: 0;
}

.header-container {
    background-color: #ffffff;
    border-bottom: 1px solid #e0e0e0;
    padding: 4px;
    text-align: center;
    border-radius: 44px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.card-container {
    display: flex;
    flex-wrap: wrap;
    gap: 16px;
    justify-content: center;
    padding: 20px;
    max-height: 500px; /* Altura máxima para el contenedor */
    overflow-y: auto;  /* Agrega scroll vertical si hay muchas tarjetas */
}


.card {
    background: #fff;
    border-radius: 12px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    overflow: hidden;
    width: 320px;
    margin: 8px;
    transition: transform 0.2s, box-shadow 0.2s;
}

.card:hover {
    transform: translateY(-4px);
    box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
}

.card-header {
    background: linear-gradient(135deg, #00aaff, #0044ff);
    color: #fff;
    padding: 20px;
    display: flex;
    flex-direction: column;
    justify-content: center;
}

.card-title {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.card-title h3 {
    margin: 0;
    font-size: 16px;
}

.card-title .status {
    padding: 6px 12px;
    border-radius: 18px;
    font-size: 14px;
    text-transform: uppercase;
    font-weight: bold;
}

.card-title .status.abierto {
    background: #f39c12;
}

.card-title .status.Completed {
    background: #27ae60;
}

.card-subtitle p {
    margin: 4px 0 0;
    font-size: 16px;
    
}
.no-movements-card {
    text-align: center;
    background-color: #f8f8f8;
    border: 1px dashed #ccc;
}

.card-body {
    padding: 16px;
    font-size: 15px;
    color: #333;
}

.card-body p {
    margin: 10px 0;
}

.back-button {
    display: inline-block;
    margin: 10px;
    text-decoration: none;
    color: #0044ff; /* Color de la flecha */
    font-size: 20px; /* Tamaño del ícono */
}

.header-container {
    display: flex; /* Habilitar flexbox */
    align-items: center; /* Alinea verticalmente el contenido */
    justify-content: center; /* Centra los elementos horizontalmente */
    padding: 4px;
    border-bottom: 1px solid #e0e0e0;
    background-color: #ffffff;
    border-radius: 44px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.back-button {
    margin-right: auto; /* Empuja el botón hacia la izquierda */
}

.page-title {
    text-align: center; /* Centra el título */
    flex-grow: 1; /* Permite que el título ocupe el espacio disponible */
}




</style>
<div class="content-wrapper">
  
     
    <section class="content">
    <div class="contenedor">
        <div class="header-container">
            <a href="javascript:history.back()" class="back-button">&larr; Volver</a> <!-- Flecha hacia la izquierda -->
            <h3 class="page-title">Trazabilidad Scrap Codigo: S${codigoScrap}</h3>
        </div>


        <div class="card-container">
            <c:if test="${empty remitos}">
                    <div class="card no-movements-card">
                        <div class="card-body">
                            <h3>No hay movimientos registrados</h3>
                            <p>No se han encontrado movimientos para este código de Scrap.</p>
                        </div>
                    </div>
                </c:if>
            
            <c:forEach var="scrap" items="${remitos}">
                <div class="card">
                    <div class="card-header">
                        <div class="card-title">
                            <h3>Remito: ${scrap.codigoRemito}</h3>
                            <span class="status ${scrap.estadoRemito}">${scrap.estadoRemito}</span>
                        </div>
                        <div class="card-subtitle">
                            <p>${scrap.fechaAltaRemito}</p>
                        </div>
                    </div>
                    <div class="card-body">
                        <p><strong>Usuario Alta:</strong> ${scrap.usuarioAltaRemito}</p>
                        <p><strong>Fecha: </strong> ${scrap.fechaAltaDetalle}</p>
                        <p><strong>Peso (kg):</strong> ${scrap.cantidadUtilizadaRemito}</p>
                    </div>
                </div>
            </c:forEach>
        </div>


        

     
    </div>
</section>
                                             
</div>              

<!-- ./wrapper -->

<%@include file = "/WEB-INF/views/jsp/includes/footer.jsp" %>




