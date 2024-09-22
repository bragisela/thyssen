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
        <title>Imprimir Remito</title>
        
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
                <div class="row col-xs-2 col-sm-2 col-xl-2" style="padding-left: 10px">
                    <img style="margin-top: 5%" src="<c:url value="/resources/assets/logoTPS.png"/>">    
                </div>
                <div class="row col-xs-9 col-sm-9 col-xl-9" style="text-align: center;">
                    <h1 style="font-size: 40px"><b>${nombreRemito}</b></h1>
                </div>                
            </div>    
            <div class="card-body" style="padding-left: 15px">
                <form:form class="form-horizontal" method="post"
                        action="#"
                        modelAttribute="remitoDetalleForm" id="myForm">

                    <div class="container">

                        <div class="tab-content">
                            
                           

                            <div class="col-md-12">
                                <div class="containerr">
                                    <div class="row">
                                        <div class="col-md-6 col-xs-6">
                                            <div class="data">
                                                <label class="labelClass">Remito:</label>
                                                <span class="spanClass">${remitoDetalleForm.codigoRemito}</span>
                                            </div>
                                            <div class="data">
                                                <label class="labelClass">Tipo:</label>
                                                <span class="spanClass">${remitoDetalleForm.tipoRemito}</span>
                                            </div>
                                            <div class="data">
                                                <label class="labelClass">Fecha Remito:</label>
                                                <span class="spanClass">${remitoDetalleForm.fechaRemito}</span>
                                            </div>
                                            <div class="data">
                                                <label class="labelClass">Estado:</label>
                                                <span class="spanClass">${remitoDetalleForm.estadoRemito}</span>
                                            </div>
                                            <div class="data">
                                                <label class="labelClass">Localidad:</label>
                                                <span class="spanClass">${remitoDetalleForm.localidad}</span>
                                            </div>
                                            <div class="data">
                                                <label class="labelClass">Provincia</label>
                                                <span class="spanClass">${remitoDetalleForm.provincia}</span>
                                            </div>
                                            <div class="data">
                                                <label class="labelClass">Scrap</label>
                                                <span class="spanClass">Si</span>
                                            </div>
                                        </div>
                                        <div class="col-md-6 col-xs-6">
                                            <div class="data">
                                                <label class="labelClass">Cliente:</label>
                                                <span class="spanClass">${remitoDetalleForm.cliente}</span>
                                            </div>
                                            <div class="data">
                                                <label class="labelClass">Contacto:</label>
                                                <span class="spanClass">${remitoDetalleForm.nombreContacto}</span>
                                            </div>
                                            <div class="data">
                                                <label class="labelClass">Telefono Contacto:</label>
                                                <span class="spanClass">${remitoDetalleForm.telefonoContacto}</span>
                                            </div>
                                            <div class="data">
                                                <label class="labelClass">Domicilio:</label>
                                                <span class="spanClass">${remitoDetalleForm.domicilio}</span>
                                            </div>
                                            <div class="data">
                                                <label class="labelClass">Transporte:</label>
                                                <span class="spanClass">${remitoDetalleForm.transporte}</span>
                                            </div>
                                            <div class="data">
                                                <label class="labelClass">Chofer:</label>
                                                <span class="spanClass">${remitoDetalleForm.idChofer}</span>
                                            </div>
                                            <div class="data">
                                                <label class="labelClass">Ref. Administrativa:</label>
                                                <span class="spanClass">${remitoDetalleForm.referenciaAdministrativa}</span>
                                            </div>
                                            

                                        </div>
                                            <div class="col-md-12 col-xs-12">
                                                <div class="data">
                                                <label class="labelClass">Punto GPS:</label>
                                                <span class="spanClass spanClassN">${remitoDetalleForm.puntoGps}</span>
                                            </div>
                                             <div class="data">
                                                <label class="labelClass">Observaciones:</label>
                                                <span class="spanClass">${remitoDetalleForm.observaciones}</span>
                                            </div>
                                            </div>
                                    </div>
                                </div>
                            </div>
                                     

                        <p></p>

                    </div>

                </form:form>
            </div>            

            <p>&nbsp;</p>                
            
            <div class="card">

                <div class="card-body">

                    <div class="row col-xs-12 col-sm-12 col-xl-12">
                        <table id="remitodDetallesTable" class="display table table-striped table-hover cell-border">
                            <thead>
                                <tr>
                                    <th style="text-align: center">CODIGO</th>
                                    <th style="text-align: center">ARTICULO</th>
                                    <th style="text-align: center">LOTE</th>
                                    <th style="text-align: center">DEPOSITO</th>
                                    <th style="text-align: center">PESO</th>
                                </tr>
                            </thead>

                            <tbody>

                                <c:forEach items="${remitoDetalles}" var="remitoDetalle">
                                    <tr>
                                        <td style="text-align: center">
                                            <c:out value="${remitoDetalle.pk}" />
                                        </td>   
                                         <td style="text-align: center">
                                            <c:out value="${remitoDetalle.articulo}" />
                                        </td>
                                        <td style="text-align: center">
                                            <c:out value="${remitoDetalle.lote}" />
                                        </td>
                                         <td style="text-align: center">
                                            <c:out value="${remitoDetalle.deposito}" />
                                        </td>
                                        <td style="text-align: center">
                                            <c:out value="${remitoDetalle.cantidad}" />
                                        </td>                                                                                                                                                                                                                 
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
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
