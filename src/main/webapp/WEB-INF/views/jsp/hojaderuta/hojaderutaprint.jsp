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
        <title>Imprimir Hoja De Ruta</title>
        
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
        <style>
                    /* Opcional: ajustes para el diseño */
        .dataTables_wrapper {
            overflow: auto;
        }
        .obscell {
            vertical-align: top;
            padding-top: 20px; /* Ajusta el espaciado según sea necesario */
        }
        
        .multiline-cell {
        white-space: pre-wrap; /* respeta \n y hace wrap de texto */
        word-wrap: break-word;
        }
        </style>
        
        
        
    </head>
    <body>
        <div style="text-align: center;">
            <div class="row">
                <div class="row col-xs-2 col-sm-2 col-xl-2">
                    <img style="margin-top: 5%" src="<c:url value="/resources/assets/logoTPS.png"/>">    
                </div>
                <div class="row col-xs-9 col-sm-9 col-xl-9" style="text-align: center;">
                    <h1 style="font-size: 40px"><b>${nombreHojaDeRuta}</b></h1>
                </div>                
            </div>    
            <div class="card-body" style="padding-left: 15px">
                <form:form class="form-horizontal" method="post"
                        action="#"
                        modelAttribute="hojaDeRutaDetalleForm" id="myForm">

                    <div class="container">

                        <div class="tab-content">

                            <div class="form-row row">

                                <div class="row col-xs-6 col-sm-3 col-xl-4" >
                                    <label for="inputArticulo">Hoja De Ruta</label>
                                    <form:input type="text" path="codigoHojaDeRuta" class="form-control" disabled="true"/>                                                    
                                </div>                                                
                                <div class="row col-xs-6 col-sm-3 col-xl-4">
                                    <label for="inputFecha">Estado</label>
                                    <form:input type="text" path="estadoHojaDeRuta" class="form-control" disabled="true"/>                                                    
                                </div>                                
                            </div>
                            

                            <div class="form-row row">
                                <div class="row col-xs-6 col-sm-3 col-xl-4" >
                                    <label for="inputArticulo">Fecha Carga</label>
                                    <form:input type="text" path="fechaCargaHojaDeRuta" class="form-control" disabled="true"/>                                                    
                                </div>  
                                <div class="row col-xs-6 col-sm-3 col-xl-4" >
                                    <label for="inputArticulo">Fecha Salida</label>
                                    <form:input type="text" path="fechaSalidaHojaDeRuta" class="form-control" disabled="true"/>                                                    
                                </div>  
                                
                            </div>                                
                            
                                
                            <div class="form-row row">
                                <div class="row col-xs-6 col-sm-3 col-xl-4" >
                                    <label for="inputArticulo">Transporte</label>
                                    <form:input type="text" path="transporteHojaDeRuta" class="form-control" disabled="true"/>
                                </div>
                                <div class="row col-xs-6 col-sm-3 col-xl-4">
                              <label for="inputFecha">Chofer</label>
                                 <form:input type="text" path="idChofer" class="form-control" disabled="true"/>                                                    
                            </div> 
                            <div class="row col-xs-12 col-sm-12 col-xl-12" >
                                    <label for="inputArticulo">Observaciones</label>
                                    <form:textarea type="text" path="observaciones" class="form-control" disabled="true"/>
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

                    <div class="row">
                        <div class="row col-xs-12 col-sm-12 col-xl-12">
                            <table class="display table table-striped table-hover cell-border" style="width: 90%;max-width: 90%; margin-left: 30px ">
                                <thead>
                                    <tr>                                    
                                        <th>NRO. REMITO</th>
                                        <th>ORDEN</th>
                                        <th>CLIENTE</th>
                                        <th>DOMICILIO</th>
                                        <th>LOCALIDAD</th>
                                        <th>PROVINCIA</th>
                                        <th>CONTACTO</th>
                                        <th>TEL. CONTACTO</th>
                                        <th>HORARIO</th>                                        
                                        <th>KM</th>         
                                        <th>KM ACUMULADOS</th>         
                                        <th>REFERENCIA ADMINISTRATIVA</th> 
                                    </tr>
                                    <tr>
                                        <th colspan='13'>OBSERVACIONES</th>
                                    </tr>
                                </thead>

                                <tbody>

                                    <c:forEach items="${hojasDeRutaDetalle}" var="hojaDeRutaDetalle">
                                        <tr>
                                            <td>
                                                <c:out value="${hojaDeRutaDetalle.idRemito}" />                                                
                                            </td>                                      
                                            <td>
                                                <c:out value="${hojaDeRutaDetalle.orden}" />                                                
                                            </td>                                                                                  
                                            <td>
                                                <c:out value="${hojaDeRutaDetalle.cliente}" />
                                            </td>                                                                                                                                    
                                            <td>
                                                <c:out value="${hojaDeRutaDetalle.domicilio}" />
                                            </td>
                                            <td>
                                                <c:out value="${hojaDeRutaDetalle.localidad}" />
                                            </td> 
                                             <td>
                                                <c:out value="${hojaDeRutaDetalle.provincia}" />
                                            </td> 
                                            <td>
                                                <c:out value="${hojaDeRutaDetalle.contacto}" />
                                            </td>
                                            <td>
                                                <c:out value="${hojaDeRutaDetalle.telefono}" />
                                            </td>  
                                            <td>
                                                <c:out value="${hojaDeRutaDetalle.horario}" />
                                            </td>                                                                                                                                                                                
                                            <td>
                                                <c:out value="${hojaDeRutaDetalle.km}" />
                                            </td>                                                                                                                                                                                                                            
                                            <td>
                                                <c:out value="${hojaDeRutaDetalle.kmAcumulado}" />
                                            </td>
                                            <td>
                                                <c:out value="${hojaDeRutaDetalle.referenciaAdministrativa}" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan='13'  class='multiline-cell'>
                                                <c:out value="${hojaDeRutaDetalle.observacionesRemito}" />
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>                        
                </div>
            </div>
                
            <p>&nbsp;</p>
            <p>&nbsp;</p>  
            
            <img style="margin-top: 5%" src="<c:url value="/resources/assets/camion.png"/>">   

            <p>&nbsp;</p>
            <p>&nbsp;</p>  
            <p>&nbsp;</p>  
            
            <div class="footer">
                <h5>Thyssen Plastic Solutions - Ruta 205 Km 187.5 - Saladillo - Bs. As. - ${fechaActual}</h5>
            </div>
        </div>
    </body>
    
<script>
    $(document).ready(function () {
        ('.table').DataTable({

        });
        
    });
       
</script>
<style>
        .footer {
            position: fixed;
            bottom: 0;
            width: 100%;
            text-align: center;
        }
    </style>

</html>
