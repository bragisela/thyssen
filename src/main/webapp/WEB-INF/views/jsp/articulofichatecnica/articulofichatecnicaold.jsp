<%-- 
    Document   : index
    Created on : 9 ago. 2022, 17:43:42
    Author     : waltergustavorojo
--%>
<%@include file = "/WEB-INF/views/jsp/includes/header.jsp" %>

<!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <section class="content">
            <div id="cuerpo" class="container">
                <div class="container">
                    <ol>
                        <form:form class="form-horizontal" method="post"
                                action="/thyssenplastic/articulofichatecnica/addOrEditOrRemove"
                                modelAttribute="articuloFichaTecnicaForm">

                        <div class="card">
                            
                            <div class="card-header">
                                <ol class="breadcrumb mb-1 mt-1">
                                    <li class="breadcrumb-item"><a href="">Principal</a></li>
                                    <li class="breadcrumb-item active">${titlearticulo}</li>
                                </ol>
                                <h4 style="text-align: center;">Ficha Técnica del Artículo XXXXX Código XX</h4>
                            </div>

                            <div class="card-body">
                                <div class="container">

                                    <p></p>
                                    ${articuloName}
                                    <p></p>

                                    <form:hidden path="pk" class="form-control"/>
                                    <form:hidden path="action" class="form-control"/>

                                    <div class="form-row row">
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputFecha">Fecha</label>
                                            <form:input type="date" path="fechaAlta" class="form-control" placeholder=""
                                                id="holderDateOfBirth" />
                                        </div>

                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputDenominacion">Versión</label>
                                            <form:input type="text" path="version" class="form-control"
                                                placeholder="" id="holderDenominacion" />
                                        </div>
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputRubro">Cliente</label>
                                            <form:input type="text" path="cliente" class="form-control"
                                                placeholder="" id="holderDenominacion" />
                                        </div>

                                    </div>
                                    <div class="form-row row">
                                        <div class="row col-xs-9 col-sm-6 col-xl-4">
                                            <label for="inputDenominacion">Observaciones</label>
                                            <form:input type="text" path="observaciones" class="form-control"
                                                placeholder="" id="holderDenominacion" />
                                        </div>
                                    </div>

                                    <div class="form-row row">
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputRubro">Especificación</label>
                                            <form:input type="text" path="especificacion" class="form-control"
                                                placeholder="" id="holderDenominacion" />                                                
                                        </div>
                                        <!--Seleccionar Arhcivo-->
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <input id="file" type="file" class="findDocumentOnboarding">
                                            <div id="messageUploadFile" style="display:none">None</div>
                                        </div>
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <input type="button" value="Subir Archivo"
                                                class="btn btn-xs btn-primary uploadDocumentOnboarding">
                                        </div>
                                        <div id="panelRefArchivo" class="row col-xs-9 col-sm-3 col-xl-4"
                                            style="display:none">
                                            <a href="#" id="refArchivo" download>#</a>
                                        </div>
                                    </div>

                                    <p></p>

                                    <p></p>
                                    <div class="form-row row">
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <button type="submit" class="btn btn-primary">${buttonLabel}</button>
                                            <a href="/thyssenplastic/articulofichatecnica"><button type="button"
                                                    class="btn btn-secondary">Cancelar</button></a>
                                        </div>
                                    </div>
                                    <p></p>
                                    <!--EDITAR OCULTO-->
                                    <div class="editar hidden">
                                        <div class="card-header">
                                            <ol class="breadcrumb mb-1 mt-1">
                                                <li class="breadcrumb-item"><a href="">Ultima Modificación</a></li>
                                                <li class="breadcrumb-item active">${titlearticulo}</li>
                                            </ol>
                                        </div>
                                        <div class="card-body">
                                            <div class="container">
                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputUsuario">Usuario</label>
                                                        <form:input type="text" path="usuarioModificacion" class="form-control"
                                                            placeholder="" id="holderUsuario" />
                                                    </div>
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label for="inputFecha">Fecha</label>
                                                        <form:input type="date" path="fechaModificacion" class="form-control"
                                                            placeholder="" id="holderDateOfBirth" />
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>                                    
                                </div>
                            </div>
                        </div>

                        <div>
                            <p></p>
                        </div>

                        <!--TABS VERTICALES extrusion Impresion Empaque-->
                        <div class="row">
                            <div class="col-xs-2">
                                <ul class="nav nav-tabs tabs-left">
                                    <li class="active">
                                        <a href="#extrusion" data-toggle="tab">Extrusion</a>
                                    </li>
                                    <li>
                                        <a href="#impresion" data-toggle="tab">Impresion</a>
                                    </li>
                                    <li>
                                        <a href="#empaque" data-toggle="tab">Empaque</a>
                                    </li>
                                </ul>
                            </div>

                            <div class="col-xs-10">
                                <div class="tab-content">
                                    <!--CONTENIDO TAB EXTRUSION-->
                                    <div class="tab-pane active" id="extrusion">
                                        <div class="row">
                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                <!--TAB HORIZONTAL material formulasion bobina-->
                                                <div class="container">
                                                    <ul class="nav nav-tabs">
                                                        <!--PESTAÑA MATERIAL-->
                                                        <li class="active"><a data-toggle="tab"
                                                                href="#material">MATERIAL</a></li>
                                                        <!--PESTAÑA FORMULACION-->
                                                        <li><a data-toggle="tab" href="#formulacion">FORMULACION</a>
                                                        </li>
                                                        <!--PESTAÑA BOBINA-->
                                                        <li><a data-toggle="tab" href="#bobina">BOBINA</a></li>
                                                    </ul>

                                                    <div class="tab-content">

                                                        <div id="material" class="tab-pane fade in active">
                                                            <!--CONTENIDO MATERIAL-->
                                                            <div class="container">
                                                                <div class="row">
                                                                    <div class="col-xs-5 col-sm-5 col-md-5">
                                                                        <p>
                                                                            <!-- form start -->
                                                                        <form role="form">
                                                                            <div class="box-body">
                                                                                <!-- select -->
                                                                                <div class="form-group">
                                                                                    <label>Material</label>
                                                                                    <select class="form-control">
                                                                                        <option>option 1</option>
                                                                                        <option>option 2</option>
                                                                                        <option>option 3</option>
                                                                                        <option>option 4</option>
                                                                                        <option>option 5</option>
                                                                                    </select>
                                                                                </div>
                                                                                <!--Cantidad-->
                                                                                <div class="form-group">
                                                                                    <label>Cantidad de Capas</label>
                                                                                    <div class="qty mt-5">
                                                                                        <input type="number"
                                                                                            class="count" name="qty"
                                                                                            value="1">
                                                                                    </div>
                                                                                </div>

                                                                                <div class="row">
                                                                                    <div class="col-xs-3">
                                                                                        <!-- Select multiple-->
                                                                                        <div class="form-group">
                                                                                            <label>Capa</label>
                                                                                            <select multiple
                                                                                                class="form-control">
                                                                                                <option>A</option>
                                                                                                <option>B</option>
                                                                                                <option>C</option>
                                                                                                <option>D</option>
                                                                                                <option>E</option>
                                                                                            </select>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="col-xs-5">
                                                                                        <!-- select -->
                                                                                        <div class="form-group">
                                                                                            <label>Material</label>
                                                                                            <select
                                                                                                class="form-control">
                                                                                                <option>option 1
                                                                                                </option>
                                                                                                <option>option 2
                                                                                                </option>
                                                                                                <option>option 3
                                                                                                </option>
                                                                                                <option>option 4
                                                                                                </option>
                                                                                                <option>option 5
                                                                                                </option>
                                                                                            </select>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div><!-- /.box-body -->
                                                                        </form>
                                                                        </p>
                                                                    </div>

                                                                    <div class="col-xs-6 col-sm-6 col-md-6">
                                                                        <div class="checkbox">
                                                                            <label>
                                                                                <input type="checkbox"> Lamina
                                                                            </label>
                                                                            <label>
                                                                                <input type="checkbox"> Bobina
                                                                            </label>
                                                                        </div>
                                                                        <ol class="breadcrumb mb-1 mt-1">
                                                                            <li class="breadcrumb-item"><a
                                                                                    href="">Medidas</a></li>
                                                                            <li class="breadcrumb-item active">
                                                                                ${titlemedidas}</li>

                                                                            <p>
                                                                                <!-- form start -->
                                                                            <form role="form">
                                                                                <div class="box-body">
                                                                                    <!--text input-->
                                                                                    <div class="form-group">
                                                                                        <label>Ancho</label>
                                                                                        <input type="text"
                                                                                            class="form-control"
                                                                                            placeholder="Ancho ..." />
                                                                                    </div>
                                                                                    <!--text input-->
                                                                                    <div class="form-group">
                                                                                        <label>Fuelle Izquierdo</label>
                                                                                        <input type="text"
                                                                                            class="form-control"
                                                                                            placeholder="F Izq ..." />
                                                                                    </div>
                                                                                    <!--text input-->
                                                                                    <div class="form-group">
                                                                                        <label>Fuelle Derecho</label>
                                                                                        <input type="text"
                                                                                            class="form-control"
                                                                                            placeholder="F Der ..." />
                                                                                    </div>
                                                                                </div>
                                                                            </form>
                                                                            </p>
                                                                        </ol>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div id="formulacion" class="tab-pane fade">
                                                            <!--CONTENIDO FORMULACION-->
                                                            <div class="row">
                                                                <!--Panel 1-->
                                                                <div class="col-xs-8 col-sm-8 col-md-8">
                                                                    <!--TAB HORIZONTAL-->
                                                                    <div class="card-body">
                                                                        <div class="container">
                                                                            <ul class="nav nav-tabs">
                                                                                <!--PESTAÑA MATERIAL-->
                                                                                <li class="active"><a data-toggle="tab"
                                                                                        href="#capa-a">A</a></li>
                                                                                <!--PESTAÑA FORMULACION-->
                                                                                <li><a data-toggle="tab"
                                                                                        href="#capa-b">B</a></li>
                                                                                <!--PESTAÑA BOBINA-->
                                                                                <li><a data-toggle="tab"
                                                                                        href="#capa-c">C</a></li>
                                                                            </ul>

                                                                            <div class="tab-content">
                                                                                <!--CONTENIDO MATERIAL-->
                                                                                <div id="capa-a"
                                                                                    class="tab-pane fade in active">
                                                                                    <h3>Capa A</h3>

                                                                                </div>
                                                                                <!--CONTENIDO FORMULACION-->
                                                                                <div id="capa-b" class="tab-pane fade">
                                                                                    <h3>Capa B</h3>

                                                                                </div>
                                                                                <!--CONTENIDO BOBINA-->
                                                                                <div id="capa-c" class="tab-pane fade">
                                                                                    <h3>Capa C</h3>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <!--Panel 2-->
                                                                <div class="col-xs-4 col-sm-4 col-md-4">
                                                                    <ol class="breadcrumb mb-1 mt-1">
                                                                        <li class="breadcrumb-item"><a href="">Resumen
                                                                                de Capas</a></li>
                                                                        <li class="breadcrumb-item active">
                                                                            ${titlebobinaone}</li>
                                                                    </ol>
                                                                    <p>
                                                                    <div class="row">

                                                                        <div
                                                                            class="form-group col-xs-4 col-sm-4 col-md-4">
                                                                            <label>Capa A</label>
                                                                            <input type="text" class="form-control"
                                                                                placeholder="" />
                                                                        </div>

                                                                        <div
                                                                            class="form-group col-xs-4 col-sm-4 col-md-4">
                                                                            <label>Capa B</label>
                                                                            <input type="text" class="form-control"
                                                                                placeholder="" />
                                                                        </div>

                                                                        <div
                                                                            class="form-group col-xs-4 col-sm-4 col-md-4">
                                                                            <label>Capa C</label>
                                                                            <input type="text" class="form-control"
                                                                                placeholder="" />
                                                                        </div>

                                                                        <div
                                                                            class="form-group col-xs-4 col-sm-4 col-md-4">
                                                                            <label>Capa D</label>
                                                                            <input type="text" class="form-control"
                                                                                placeholder="" />
                                                                        </div>

                                                                        <div
                                                                            class="form-group col-xs-4 col-sm-4 col-md-4">
                                                                            <label>Capa E</label>
                                                                            <input type="text" class="form-control"
                                                                                placeholder="" />
                                                                        </div>

                                                                        <div
                                                                            class="form-group col-xs-4 col-sm-4 col-md-4">
                                                                            <label>Capa F</label>
                                                                            <input type="text" class="form-control"
                                                                                placeholder="" />
                                                                        </div>

                                                                    </div>
                                                                    </p>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <!--Panel 3-->
                                                                <div class="col-xs-6 col-sm-6 col-md-6">
                                                                    <div class="form-group">
                                                                        <label>Total Capa</label>
                                                                        <input type="text" class="form-control"
                                                                            placeholder="Pesos Específico ..." />
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label>Densidad</label>
                                                                        <input type="text" class="form-control"
                                                                            placeholder="Pesos Específico ..." />
                                                                    </div>
                                                                </div>
                                                                <!--Panel 4-->
                                                                <div class="col-xs-6 col-sm-6 col-md-6">
                                                                    <ol class="breadcrumb mb-1 mt-1">
                                                                        <li class="breadcrumb-item"><a
                                                                                href="">Materiales Utilizados</a></li>
                                                                        <li class="breadcrumb-item active">
                                                                            ${titlebobinaone}</li>
                                                                    </ol>
                                                                    <!--GRILLA-->
                                                                    <div class="card">
                                                                        <div class="card-body">
                                                                            <!-- <h4 style="text-align: center;">Reparación Máquina XXX desde XXX hasta XXX</h4> -->
                                                                            <div
                                                                                class="row col-xs-12 col-sm-12 col-xl-12">
                                                                                <table id="materialesutilizadosTable"
                                                                                    class="display table table-striped table-hover cell-border">
                                                                                    <thead>
                                                                                        <tr>
                                                                                            <th>CODIGO</th>
                                                                                            <th>PORC.</th>
                                                                                        </tr>
                                                                                    </thead>

                                                                                    <tbody>

                                                                                        <c:forEach
                                                                                            items="${materialesutilizados}"
                                                                                            var="articulo">
                                                                                            <tr>
                                                                                                <td>
                                                                                                    <c:out
                                                                                                        value="${materialesutilizados.codigo}" />
                                                                                                </td>
                                                                                                <td>
                                                                                                    <c:out
                                                                                                        value="${materialesutilizados.porcentaje}" />
                                                                                                </td>

                                                                                            </tr>
                                                                                        </c:forEach>
                                                                                    </tbody>
                                                                                </table>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-xs-6 col-sm-6 col-md-6"></div>
                                                                <div class="col-xs-6 col-sm-6 col-md-6">
                                                                    <div class="form-group col-xs-4">
                                                                        <label>Peso Específico</label>
                                                                        <input type="text" class="form-control"
                                                                            placeholder="Pesos Específico ..." />
                                                                    </div>
                                                                </div>

                                                            </div>
                                                        </div>

                                                        <div id="bobina" class="tab-pane fade">
                                                            <!--CONTENIDO BOBINA-->
                                                            <div class="container">
                                                                <div class="col-xs-9 col-sm-9 col-md-9">
                                                                    <ol class="breadcrumb mb-1 mt-1">
                                                                        <li class="breadcrumb-item"><a
                                                                                href="">Bobina</a></li>
                                                                        <li class="breadcrumb-item active">
                                                                            ${titlebobinaone}</li>
                                                                    </ol>
                                                                    <p>
                                                                        <!-- form start -->
                                                                    <form role="form">
                                                                        <div class="box-body">
                                                                            <div class="row">
                                                                                <div class="col-xs-4">
                                                                                    <div class="form-group">
                                                                                        <label>Peso Específico</label>
                                                                                        <input type="text"
                                                                                            class="form-control"
                                                                                            placeholder="Largo ..." />
                                                                                    </div>
                                                                                </div>
                                                                                <div class="col-xs-4">
                                                                                    <div class="form-group">
                                                                                        <label>Ancho Bruto</label>
                                                                                        <input type="text"
                                                                                            class="form-control"
                                                                                            placeholder="Espesor ..." />
                                                                                    </div>
                                                                                </div>

                                                                            </div>
                                                                            <div class="row">

                                                                                <div class="col-xs-4">
                                                                                    <div class="form-group">
                                                                                        <label>Bobinado por
                                                                                            Barras</label>
                                                                                        <input type="text"
                                                                                            class="form-control"
                                                                                            placeholder="Espesor ..." />
                                                                                    </div>
                                                                                </div>
                                                                                <div class="col-xs-4">
                                                                                    <div class="form-group">
                                                                                        <label>Metros</label>
                                                                                        <input type="text"
                                                                                            class="form-control"
                                                                                            placeholder="Peso ..." />
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                            <div class="row">
                                                                                <div class="col-xs-4">
                                                                                    <div class="form-group">
                                                                                        <label>Peso</label>
                                                                                        <input type="text"
                                                                                            class="form-control"
                                                                                            placeholder="Largo ..." />
                                                                                    </div>
                                                                                </div>
                                                                                <div class="col-xs-4">
                                                                                    <div class="form-group">
                                                                                        <label>Diámetro</label>
                                                                                        <input type="text"
                                                                                            class="form-control"
                                                                                            placeholder="Espesor ..." />
                                                                                    </div>
                                                                                </div>
                                                                                <div class="col-xs-4">
                                                                                    <div class="form-group">
                                                                                        <label>Empalmes</label>
                                                                                        <input type="text"
                                                                                            class="form-control"
                                                                                            placeholder="Peso ..." />
                                                                                    </div>
                                                                                </div>
                                                                            </div>


                                                                        </div><!-- /.box-body -->
                                                                    </form>
                                                                    </p>


                                                                </div>
                                                                <div class="col-xs-9 col-sm-9 col-md-9">
                                                                    <ol class="breadcrumb mb-1 mt-1">
                                                                        <li class="breadcrumb-item"><a
                                                                                href="">Bobina</a></li>
                                                                        <li class="breadcrumb-item active">
                                                                            ${titlebobinatwo}</li>
                                                                    </ol>
                                                                    <p>
                                                                        <!-- form start -->
                                                                    <form role="form">
                                                                        <div class="box-body">
                                                                            <div class="row">
                                                                                <div class="col-xs-4">
                                                                                    <div class="form-group">
                                                                                        <label>Tipo</label>
                                                                                        <select class="form-control">
                                                                                            <option>option 1
                                                                                            </option>
                                                                                            <option>option 2
                                                                                            </option>
                                                                                            <option>option 3
                                                                                            </option>
                                                                                            <option>option 4
                                                                                            </option>
                                                                                            <option>option 5
                                                                                            </option>
                                                                                        </select>
                                                                                    </div>
                                                                                </div>

                                                                                <div class="col-xs-4">
                                                                                    <div class="form-group">
                                                                                        <label>Diámetro</label>
                                                                                        <input type="text"
                                                                                            class="form-control"
                                                                                            placeholder="Diámetro ..." />
                                                                                    </div>
                                                                                </div>
                                                                            </div>

                                                                            <div class="row">
                                                                                <div class="col-xs-4">
                                                                                    <div class="form-group">
                                                                                        <label>Largo</label>
                                                                                        <input type="text"
                                                                                            class="form-control"
                                                                                            placeholder="Largo ..." />
                                                                                    </div>
                                                                                </div>
                                                                                <div class="col-xs-4">
                                                                                    <div class="form-group">
                                                                                        <label>Espesor</label>
                                                                                        <input type="text"
                                                                                            class="form-control"
                                                                                            placeholder="Espesor ..." />
                                                                                    </div>
                                                                                </div>
                                                                                <div class="col-xs-4">
                                                                                    <div class="form-group">
                                                                                        <label>Peso</label>
                                                                                        <input type="text"
                                                                                            class="form-control"
                                                                                            placeholder="Peso ..." />
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div><!-- /.box-body -->
                                                                    </form>
                                                                    </p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!--CONTENIDO TAB IMPRESION-->
                                    <div class="tab-pane" id="impresion">
                                        <div class="row">
                                            <div class="row col-xs-9 col-sm-12 col-xl-4">
                                                <ol class="breadcrumb mb-1 mt-1">
                                                    <li class="breadcrumb-item"><a href="">Impresion</a></li>
                                                    <li class="breadcrumb-item active">${titlearticulofichatecnica}</li>
                                                </ol>
                                                <h4>IMPRESION TAB</h4>
                                            </div>
                                        </div>
                                    </div>
                                    <!--CONTENIDO TAB EMPAQUE-->
                                    <div class="tab-pane" id="empaque">
                                        <div class="row">
                                            <div class="col-xs-9 col-sm-9 col-md-9">
                                                <ol class="breadcrumb mb-1 mt-1">
                                                    <li class="breadcrumb-item"><a
                                                            href="">Empaque</a></li>
                                                    <li class="breadcrumb-item active">
                                                        ${titleempaque}</li>
                                                </ol>
                                                <p>
                                                    <!-- form start -->
                                                <form role="form">
                                                    <div class="box-body">
                                                        <div class="row">
                                                            <div class="col-xs-4">
                                                                <div class="form-group">
                                                                    <label>Bultos en</label>
                                                                    <select class="form-control">
                                                                        <option>Cajas
                                                                        </option>
                                                                        <option>Bolsas
                                                                        </option>
                                                                        <option>Bobinas
                                                                        </option>
                                                                    </select>
                                                                </div>
                                                            </div>

                                                            <div class="col-xs-4">
                                                                <div class="form-group">
                                                                    <label>Bultos por Pallets</label>
                                                                    <input type="text"
                                                                        class="form-control"
                                                                        placeholder="" />
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="row">
                                                            <div class="checkbox">
                                                                <label>Posición del</label>
                                                                <label>
                                                                    <input type="checkbox"> Horizontal
                                                                </label>
                                                                <label>
                                                                    <input type="checkbox"> Vertical
                                                                </label>
                                                            </div>
                                                        </div>

                                                        <div class="row">
                                                            <div class="col-xs-4">
                                                                <div class="form-group">
                                                                    <label>Pallets</label>
                                                                    <select class="form-control">
                                                                        <option>Pallet 1
                                                                        </option>
                                                                        <option>Pallet 2
                                                                        </option>
                                                                        <option>Pallet 3
                                                                        </option>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div><!-- /.box-body -->
                                                </form>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!--GRILLA-->
                        <div class="card">
                            <div class="card-body">
                                <!-- <h4 style="text-align: center;">Reparación Máquina XXX desde XXX hasta XXX</h4> -->
                                <div class="row col-xs-12 col-sm-12 col-xl-12">
                                    <table id="articulosfichatecnicaTable"
                                        class="display table table-striped table-hover cell-border">
                                        <thead>
                                            <tr>
                                                <th>FECHA</th>
                                                <th>VERSION</th>
                                                <th style="text-align: center">ACCIONES</th>
                                            </tr>
                                        </thead>

                                        <tbody>

                                            <c:forEach items="${articulofichastecnica}" var="articulofichatecnica">
                                                <tr>
                                                    <td>
                                                        <c:out value="${articulofichatecnica.fecha}" />
                                                    </td>
                                                    <td>
                                                        <c:out value="${articulofichatecnica.version}" />
                                                    </td>
                                                    <td>
                                                        <a class="nav-link active fa fa-pencil-square-o fa-lg"
                                                            href="/thyssenplastic/articulofichatecnica/edit/${articulofichatecnica.pk}"
                                                            data-toggle="tooltip" data-placement="top"
                                                            title="Editar"></a>
                                                        <a class="nav-link active fa fa-trash fa-lg"
                                                            href="/thyssenplastic/articulofichatecnica/remove/${articulofichatecnica.pk}"
                                                            data-toggle="tooltip" data-placement="top"
                                                            title="Eliminar"></a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </form:form>
            </ol>

        </section>
    </div>

    <!-- ./wrapper -->

    <script>
        $(document).ready(function () {

            $('#articulosfichatecnicaTable').DataTable({
                language: {
                    "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
                }
            });
            $('#materialesutilizadosTable').DataTable({
                language: {
                    "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
                }
            });
        });


    </script>
<%@include file = "/WEB-INF/views/jsp/includes/footer.jsp" %>




