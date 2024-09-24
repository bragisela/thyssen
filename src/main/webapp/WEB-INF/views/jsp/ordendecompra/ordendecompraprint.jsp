<%-- 
    Document   : ordendecompraitemrecepcionprint
    Created on : Feb 3, 2023, 4:51:12 PM
    Author     : gusta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Imprimir OrdenDeCompra</title>
        
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="stylesheet" href="<c:url value="/resources/core/css/bootstrap.min.css"/>">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="<c:url value="/resources/core/css/ionicons.min.css"/>">
        <link href="<c:url value="/resources/core/css/AdminLTE.min.css"/>" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="<c:url value="/resources/core/css/skins/skin-green.min.css"/>">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.12.1/css/jquery.dataTables.min.css">
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/scroller/2.0.7/css/scroller.dataTables.min.css">
        
        <script src="https://code.jquery.com/jquery-3.5.1.js" />
        <script src="https://cdn.datatables.net/scroller/2.0.7/js/dataTables.scroller.min.js"></script>
        <script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/buttons/2.3.2/js/dataTables.buttons.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
        <script src="https://cdn.datatables.net/buttons/2.3.2/js/buttons.html5.min.js"></script>
        <script src="https://cdn.datatables.net/buttons/2.3.2/js/buttons.print.min.js"></script>

        <style>
        .date-status { display: flex; justify-content: space-between; }

        .pdf-image {
            margin-top: 15%;
        }
        .pdf-heading {
            font-size: 12px;
            font-weight: bold;
        }
        .pdf-subheading {
            font-size: 10px;
        }
        .pdf-center {
            text-align: center;
        }
        .pdf-hr {
            margin: 10px 0;
            border: 0;
            border-top: 1px solid #000;
        }
        .pdf-date-status {
            font-size: 10px;
            margin: 5px 0;
        }
        .pdf-date-status b {
            font-weight: bold;
        }
        .pdf-info {
            margin-left: 25px;
        }
        </style>
                

        <!-- Bootstrap 3.3.7 -->
        <script src="<c:url value="/resources/core/js/bootstrap.min.js"/>"></script>
        <!-- AdminLTE App -->
        <script src="<c:url value="/resources/core/js/adminlte.min.js"/>"></script>        
        <script src="<c:url value="/resources/core/js/qrcode.js"/>"></script>
        <script src="<c:url value="/resources/core/js/JsBarcode.all.min.js"/>"></script>
    </head>
    <body>
        <div class="row" >
                <div class="row col-xs-2 col-sm-2 col-xl-2" style="padding-left: 30px"">
                    <img style="margin-top: 15%" src="<c:url value="/resources/assets/logoTPS.png"/>">    
                </div>
                <div class="row col-xs-9 col-sm-9 col-xl-9" style="text-align: left;">
                    <h4 style="font-size: 30px"><b>Thyssen Plastic Solutions</b></h4>
                    <h4 style="font-size: 12px"> Ruta 205 Km 187.5 </h4>
                    <h4 style="font-size: 12px"> 7260 - Saladillo - Bs. As.</h4>
                    <h4 style="font-size: 12px"> Ruta 205 Km 187.5 </h4>
                </div>                
                <div class="row col-xs-12 col-sm-12 col-xl-12" style="text-align: center;">
                    <h4 style="font-size: 20px"><b>Orden de Compra Nro.:</b> ${ordenDeCompraNro}</h4>
                    <hr>
                </div>
            </div>
            <div class="row">
                <div class="row col-xs-12 col-sm-12 col-xl-12" style="padding-left: 25px">
                    <div class="date-status">
                        <div><b>Estado:</b> ${estado}</div>
                         <div><b>Fecha:</b> ${fechaHoy}</div>
                    </div>
                    <div class="date-status">
                        <div><b>Ref. Administrativa:</b> ${ordenDeCompraRefAdministrativa}</div>
                        <div><b>Fecha de entrega:</b> ${fechaEntrega}</div>
                    </div>
                    <hr>
                    <div style="text-align: left;padding-left: 10px">
                        <div><b>Proveedor:</b> ${proveedorRazonSocial}</div>
                        <br>
                        <hr>
                    </div>

                </div>
            </div>
         <div class="row">
            <div style="text-align: left;padding-left: 25px">
               <b>Detalle</b>
            </div>

            <div class="row col-xs-12 col-sm-12 col-xl-12"   style="padding-left: 35px">
            <hr>
                <table id="remitodDetallesTable" class="display  table-striped table-hover cell-border" style="margin: 0 auto;">
                            <thead>
                                <tr>
                                    <th style="text-align: center">Tipo</th>
                                    <th style="text-align: center">Suministro</th>
                                    <th style="text-align: center">Unidad</th>
                                    <th style="text-align: center">Cantidad</th>
                                    <th style="text-align: center">Cant.Recep</th>
                                </tr>
                            </thead>

                            <tbody>

                                <c:forEach items="${ordenDeCompraItems}" var="ordenDeCompraItem">
                                    <tr>
                                        <td style="text-align: center">
                                            <c:out value="${ordenDeCompraItem.tipo}" />
                                        </td>   
                                         <td style="text-align: center">
                                            <c:out value="${ordenDeCompraItem.materiaPrima}" />
                                        </td>
                                        <td style="text-align: center">
                                            <c:out value="${ordenDeCompraItem.unidad}" />
                                        </td>
                                         <td style="text-align: center">
                                            <c:out value="${ordenDeCompraItem.cantidadSolicitada}" />
                                        </td>
                                        <td style="text-align: center">
                                            <c:out value="${ordenDeCompraItem.cantidadRecepcionada}" />
                                        </td>                                                                                                                                                                                                                 
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                <div style="text-align: left;">
                    <b>Observaciones</b>
                    <hr>
                </div>
                </div>
             </div>
             <br>
             <div class="row">

            <div class="row col-xs-12 col-sm-12 col-xl-12"   style="padding-left: 35px">
            
            <div style="text-align: left;padding-left: 25px">
               <b>Detalle de recepciones de compra</b>
               <hr>
            </div>
            <table id="remitodDetallesTable" class="display table-striped table-hover cell-border" style="margin: 0 auto;">
    <thead>
        <tr>
            
            <th style="text-align: center">TipoSuministro</th>
            <th style="text-align: center">Suministro</th>
            <th style="text-align: center">Unidad</th>
            <th style="text-align: center">Recepcionado</th>
        </tr>
    </thead>

    <tbody>
        <c:set var="previousItem" value="" />

        <c:forEach items="${ordenDeCompraItemsRecepcion}" var="ordendecompraitemrecepcion">
            <c:choose>
                <c:when test="${previousItem != ordendecompraitemrecepcion.item}">
                    <tr>
                        <td colspan="5" style="text-align: left; font-weight: bold; background-color: #f2f2f2;">
                            <!-- Mostrar el Item como un encabezado agrupado -->
                            <c:out value="Recepcion de compra Nro: ${ordendecompraitemrecepcion.item}" />
                        </td>
                    </tr>
                </c:when>
            </c:choose>

            <!-- Contenido de la tabla -->
            <tr>
                <td style="text-align: center">
                    <c:out value="${ordendecompraitemrecepcion.tipo}" />
                </td>
                <td style="text-align: center">
                    <c:out value="${ordendecompraitemrecepcion.suministro}" />
                </td>
                <td style="text-align: center">
                    <c:out value="${ordendecompraitemrecepcion.unidad}" />
                </td>
                <td style="text-align: center">
                    <c:out value="${ordendecompraitemrecepcion.cantidadRecepcionada}" />
                </td>
            </tr>

            <!-- Actualizar el valor de previousItem al final de cada iteración -->
            <c:set var="previousItem" value="${ordendecompraitemrecepcion.item}" />
        </c:forEach>
    </tbody>
</table>
                </div>
             </div>
            
            
            <p>&nbsp;</p>
            <p>&nbsp;</p>  
            <p>&nbsp;</p>  
            
            
        </div>
        <div class="footer">
                <h5>Thyssen Plastic Solutions - Ruta 205 Km 187.5 - Saladillo - Bs. As. - ${fechaActual}</h5>
            </div>
    </body>
    
<script>
    $(document).ready(function () {
    });
       
</script>
<style>
        .footer {
            position: fixed;
            bottom: 0;
            width: 100%;
            text-align: center;
        }
        .containerr {
        background-color: #fff;
        padding: 4px;
        border-radius: 8px;
        box-shadow: 0 0 8px rgba(1, 1, 1, 0.1);
        margin-top: 20px;
        border: 1px solid green;
        
        border-left: 8px solid green; 
    }
    .spanClass {
    margin-left: 5px;
    font-size: 12px;
}
 .spanClassN {
    margin-left: 45px;
    max-width: 700px;
}
    </style>

</html>
