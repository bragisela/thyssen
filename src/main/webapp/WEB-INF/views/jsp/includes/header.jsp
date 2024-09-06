<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>
    <head>                        
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <title>Sistema | Inicio</title>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <meta charset="UTF-8">
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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/printThis/1.15.0/printThis.min.js"></script>

                

        <!-- Bootstrap 3.3.7 -->
        <script src="<c:url value="/resources/core/js/bootstrap.min.js"/>"></script>
        <!-- AdminLTE App -->
        <script src="<c:url value="/resources/core/js/adminlte.min.js"/>"></script>
        
        <script src="<c:url value="/resources/core/js/qrcode.js"/>"></script>
        
        <script src="<c:url value="/resources/core/js/JsBarcode.all.min.js"/>"></script>
        
    </head>
    
    <body class="skin-green sidebar-mini" style="height: auto; min-height: 100%;">    
        <div class="wrapper">

            <!-- Main Header -->
            <header class="main-header">
                <a href="/thyssenplastic/home" class="logo">
                    <!-- mini logo for sidebar mini 50x50 pixels -->
                    <span class="logo-mini"><b>T</b>P</span>
                    <!-- logo for regular state and mobile devices -->
                    <span class="logo-lg"><b>Thyssen </b>Plastic</span>
                </a>

                <!-- Header Navbar -->
                <nav class="navbar navbar-static-top" role="navigation">
                    <!-- Sidebar toggle button-->
                    <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                        <span class="sr-only">Toggle navigation</span>
                    </a>
                    <!-- Navbar Right Menu -->
                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">
                            <!-- User Account Menu -->
                            <li class="user user-menu">
                                <!-- Menu Toggle Button -->

                                <a href="/thyssenplastic/logout"  data-open="modal" class="submit" type="submit">Cerrar Sesion</a>              
                                <!-- The user image in the navbar-->                                
                                <img src="<c:url value="/resources/assets/user2-160x160.jpg"/>" class="user-image" alt="User Image">
                                <!-- hidden-xs hides the username on small devices so only the image appears. -->
                                <!--<span class="hidden-xs">Cerrar Sesión</span>-->
                            </li>
                        </ul>
                    </div>
                </nav>
            </header>
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="main-sidebar">

                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">

                    <!-- Sidebar user panel (optional) -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="<c:url value="/resources/assets/user2-160x160.jpg"/>" class="img-circle" alt="User Image">
                        </div>
                        <div class="pull-left info">
                            <p>${sessionInformation.emailUser}</p>
                            <!-- Status -->
                            <a href="#"><i class="fa fa-circle text-success"></i> En línea</a>
                        </div>
                    </div>

                    <!-- Sidebar Menu -->
                    <ul class="sidebar-menu" data-widget="tree">
                        <!-- Optionally, you can add icons to the links -->
                        <!--<li class="active"><a href="#"><i class="fa fa-link"></i> <span>Panel Administrativo</span></a></li>-->
                        
                        <c:if test="${sessionInformation.rolUser == '1' || sessionInformation.rolUser == '2'}">
                            <li class="treeview">
                                <a href="#"><i class="fa fa-cart-arrow-down"></i> <span>Compras</span>
                                    <span class="pull-right-container">
                                        <i class="fa fa-angle-left pull-right"></i>
                                    </span>
                                </a>
                                <ul class="treeview-menu">
                                    <c:if test="${sessionInformation.rolUser == '1'}">
                                        <li><a href="/thyssenplastic/proveedor/" id="prov"><i class="fa fa-users ml-3"></i>Proveedores</a></li>                                    
                                        <li><a href="/thyssenplastic/ordenDeCompra/" id="ordcomp"><i class="fa fa-usd ml-3"></i>Ordenes de Compra</a></li>
                                    </c:if>
                                    <c:if test="${sessionInformation.rolUser == '2'}">
                                        <li><a href="/thyssenplastic/ordenDeCompra/" id="recep"><i class="fa fa-truck ml-3"></i>Recepción de...</a></li>                                
                                    </c:if>
                                </ul>
                            </li>
                        </c:if>
                        
                        <c:if test="${sessionInformation.rolUser == '1'}">
                            <li class="treeview">
                                <a href="#"><i class="fa fa-dashboard"></i> <span>Ventas</span>
                                    <span class="pull-right-container">
                                        <i class="fa fa-angle-left pull-right"></i>
                                    </span>
                                </a>
                                <ul class="treeview-menu">
                                    <li><a href="/thyssenplastic/cliente/"><i class="fa fa-user-circle-o ml-3"></i>Clientes</a></li>
                                </ul>
                            </li>
                        </c:if>
                        <c:if test="${sessionInformation.rolUser == '1' || sessionInformation.rolUser == '3' || sessionInformation.rolUser == '2'}">
                            <!-- Por definir -->
                            <li class="treeview">
                                <a href="#"><i class="fa fa-truck"></i> <span>Logística</span>
                                    <span class="pull-right-container">
                                        <i class="fa fa-angle-left pull-right"></i>
                                    </span>
                                    <ul class="treeview-menu">
                                        <c:if test="${sessionInformation.rolUser == '1' || sessionInformation.rolUser == '2'}">
                                            <li style="font-size: 5px"><a href="/thyssenplastic/remito/"><i class="fa fa-address-card ml-3"></i> Remito</a></li>
                                        </c:if>                                        
                                        <c:if test="${sessionInformation.rolUser == '1'}">
                                            <li style="font-size: 5px"><a href="/thyssenplastic/hojaDeRuta/"><i class="fa fa-truck ml-3"></i> Hoja De Ruta</a></li>
                                        </c:if>                                                                                                                        
                                    </ul>                                    
                                </a>
                        </c:if>
                        <c:if test="${sessionInformation.rolUser == '1'}">
                            <li class="treeview">
                                <a href="#"><i class="fa fa-pencil-square-o"></i> <span>Inventario</span>
                                    <span class="pull-right-container">
                                        <i class="fa fa-angle-left pull-right"></i>
                                    </span>
                                </a>
                                <ul class="treeview-menu">
                                    <li style="font-size: 5px"><a href="/thyssenplastic/materiaprima/"><i class="fa fa-cubes ml-3"></i> Materia Prima</a></li>
                                    <li><a href="/thyssenplastic/insumo/"><i class="fa fa-barcode ml-3"></i>Insumos</a></li>
                                    <li><a href="/thyssenplastic/articulo/"><i class="fa fa-cube ml-3"></i>Artículos</a></li>
                                    <li><a href="/thyssenplastic/ajusteDeInventario/" id="ajinv"><i class="fa fa-tags ml-3"></i>Ajuste de Inventario</a></li>
                                    <!-- 
                                        <li><a href="/thyssenplastic/configuracion/"><i class="fa fa-gear ml-3"></i>Configuración</a></li>
                                    -->
                                </ul>
                            </li>
                        </c:if>
                        <c:if test="${sessionInformation.rolUser == '1' || sessionInformation.rolUser == '3' || sessionInformation.rolUser == '2'}">
                            <li class="treeview">
                                <a href="#"><i class="fa fa-exchange"></i> <span>Producción</span>
                                    <span class="pull-right-container">
                                        <i class="fa fa-angle-left pull-right"></i>
                                    </span>
                                </a>
                                <ul class="treeview-menu">
                                    <c:if test="${sessionInformation.rolUser == '1' || sessionInformation.rolUser == '3' || sessionInformation.rolUser == '2'}">
                                        <li style="font-size: 5px"><a href="/thyssenplastic/ordenDeProduccion/"><i class="fa fa-list-ol ml-3"></i> Orden de Producción</a></li>
                                    </c:if>
                                    <c:if test="${sessionInformation.rolUser == '1' || sessionInformation.rolUser == '3'}">
                                        <li style="font-size: 5px"><a href="/thyssenplastic/trazabilidad/"><i class="fa fa-search ml-3"></i> Trazabilidad</a></li>
                                    </c:if>                                        
                                    <c:if test="${sessionInformation.rolUser == '1' || sessionInformation.rolUser == '2'}">
                                        <li style="font-size: 5px"><a href="/thyssenplastic/ingresarDeposito/"><i class="fa fa-address-card ml-3"></i> Ingresar a Depósito</a></li>
                                    </c:if> 
                                    <c:if test="${sessionInformation.rolUser == '1' || sessionInformation.rolUser == '2'}">
                                        <li style="font-size: 5px"><a href="/thyssenplastic/ingresarDepositoScrap/"><i class="fa fa-address-card ml-3"></i>Depósito Scrap</a></li>
                                    </c:if>
                                </ul>                                
                            </li>
                        </c:if>                            
                        <c:if test="${sessionInformation.rolUser == '1' || sessionInformation.rolUser == '3' || sessionInformation.rolUser == '4'}">
                            <li class="treeview">
                                <a href="#"><i class="fa fa-cogs"></i> <span>Mantenimiento</span>
                                    <span class="pull-right-container">
                                        <i class="fa fa-angle-left pull-right"></i>
                                    </span>
                                </a>
                                <ul class="treeview-menu">
                                    <c:if test="${sessionInformation.rolUser == '3' || sessionInformation.rolUser == '4'}">
                                        <li style="font-size: 5px"><a href="/thyssenplastic/mantenimientoCorrectivo/"><i class="fa fa-list-ol ml-3"></i> Alta Mantenimiento</a></li>
                                    </c:if>
                                    <c:if test="${sessionInformation.rolUser == '4'}">
                                        <li style="font-size: 5px"><a href="/thyssenplastic/repuesto/"><i class="fa fa-cubes ml-3"></i> Repuestos</a></li>
                                    </c:if>
                                    <c:if test="${sessionInformation.rolUser == '1'}">
                                        <li style="font-size: 5px"><a href="/thyssenplastic/reporte/"><i class="fa fa-list-ol ml-3"></i> Reportes</a></li>
                                    </c:if>
                                </ul>
                            </li>
                        </c:if>
                        <c:if test="${sessionInformation.rolUser == '1'}">
                            <li class="treeview">
                                <a href="#"><i class="fa fa-wrench"></i> <span>Configuración</span>
                                    <span class="pull-right-container">
                                        <i class="fa fa-angle-left pull-right"></i>
                                    </span>
                                </a>
                                <ul class="treeview-menu">
                                    <li><a href="/thyssenplastic/tipo"><i class="fa fa-sitemap ml-3"></i>Configuración</a></li>
                                    <!--
                                    <li><a href="../configuracion/roles.jsp"><i class="fa fa-sort-amount-desc ml-3"></i>Roles</a></li>
                                    <li><a href="../configuracion/rubros.jsp"><i class="fa fa-sitemap ml-3"></i>Rubros</a></li>
                                    <li><a href="../configuracion/depositos.jsp"><i class="fa fa-archive ml-3"></i>Depósitos</a></li>                                
                                    -->
                                    <li><a href="../configuracion/usuarios.jsp"><i class="fa fa-users ml-3"></i>Usuarios</a></li>
                                    <li><a href="/thyssenplastic/activacionmanual"><i class="fa fa-users ml-3"></i>Activacion Manual</a></li>   
                                </ul>
                            </li>
                        </c:if>
                        <c:if test="${sessionInformation.rolUser == '1' || sessionInformation.rolUser == '2' || sessionInformation.rolUser == '3'}">
                        <li class="treeview">
                                <a href="#"><i class="fa fa-key"></i><span>Reportes</span>
                                    <span class="pull-right-container">
                                        <i class="fa fa-angle-left pull-right"></i>
                                    </span>
                                </a>
                                <ul class="treeview-menu">
                                    <li><a href="/thyssenplastic/accesodirectograficopolar"><i class="fa fa-line-chart"></i>Grafico polar</a></li>
                                    <li><a href="/thyssenplastic/reporte/planillaPlegado"><i class="fa fa-angle-double-down"></i>Planilla Plegado</a></li>
                                    <li><a href="/thyssenplastic/reporte/planillaPallet"><i class="fa fa-angle-double-down"></i>Planilla Pallet</a></li>
                                    <li><a href="/thyssenplastic/deposito"><i class="fa fa-angle-double-down"></i>Deposito</a></li>
                                </ul>
                           </li>
                           </c:if>
                    </ul>
                    <!-- /.sidebar-menu -->
                </section>
                <!-- /.sidebar -->
            </aside>
        </section>
        <!-- /.content -->
    </div>

    <!-- modal -->
    <div class="modal" tabindex="-1" role="dialog" id="modal">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Salir del Sistema</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p>Desea cerrar la ventana</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                    <button type="button" class="btn btn-primary" >Si</button>
                </div>
            </div>
        </div>
    </div>
    <!-- /.content-wrapper -->
    <!-- Main Footer -->

    <div class="control-sidebar-bg"></div>

                        