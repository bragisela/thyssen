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
        <title>Etiqueta Bulto</title>
        
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

                

        <!-- Bootstrap 3.3.7 -->
        <script src="<c:url value="/resources/core/js/bootstrap.min.js"/>"></script>
        <!-- AdminLTE App -->
        <script src="<c:url value="/resources/core/js/adminlte.min.js"/>"></script>        
        <script src="<c:url value="/resources/core/js/qrcode.js"/>"></script>
        <script src="<c:url value="/resources/core/js/JsBarcode.all.min.js"/>"></script>
    </head>
    <body>
        <div style="text-align: center;">
            <div class="row">
                <div class="row col-xs-3 col-sm-3 col-xl-3">
                    <img style="margin-top: 5%;height: 80%;width: 80%;" src="<c:url value="/resources/assets/logoTPS.png"/>">    
                </div>
                <div class="row col-xs-6 col-sm-6 col-xl-6" style="text-align: center;">
                    <h1 style="font-size: 80px"><b>${etiquetaArticulo}</b></h1>
                    <h1 style="font-size: 70px">Art. ${codigoarticulo} ${articulo}</h1>                    
                    <h2 style="font-size: 60px">Lote Nro.: L${ordenDeProduccion}&nbsp; | Bulto: ${codigoBulto}</h2>
                </div>                
            </div>    
            <div class="row">
                <div class="row col-xs-6 col-sm-6 col-xl-6">                    
                    <div id="qrcode"></div>
                </div>
                <div class="row col-xs-6 col-sm-6 col-xl-6">                    
                    <svg id="barcode"></svg>                                
                </div>    
            </div>    
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <div class="row">
                <h2>Fabricado por Thyssen Plastic Solutions - Ruta 205 Km 187.5 - Saladillo - Bs. As. - ${fechaActual}</h2>
            </div>
                        
        </div>
    </body>
    
<script>
    $(document).ready(function () {
        
        var qrcode = new QRCode("qrcode",  {
                text: "${url}",
                width: 256,
                height: 256
            });        
        
        $("#qrcode > img").css({"margin":"auto"});
        
        JsBarcode("#barcode", "${codigoBulto}", {
            width: 6,
            height: 160});
    });
       
</script>

</html>
