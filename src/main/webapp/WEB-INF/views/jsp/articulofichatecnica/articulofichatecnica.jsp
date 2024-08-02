<%-- 
    Document   : index
    Created on : 9 ago. 2022, 17:43:42
    Author     : waltergustavorojo
--%>
<%@include file = "/WEB-INF/views/jsp/includes/header.jsp" %>

<style>
    
.tabs-left>li.active>a, .tabs-left>li.active>a:hover, .tabs-left>li.active>a:focus {
    background-color: lightblue;
}
.tabs-left, .tabs-right {
  border-bottom: none;
  padding-top: 2px;
}
.tabs-left {
  border-right: 1px solid #ddd;
}
.tabs-right {
  border-left: 1px solid #ddd;
}
.tabs-left>li, .tabs-right>li {
  float: none;
  margin-bottom: 2px;
}
.tabs-left>li {
  margin-right: -1px;
}
.tabs-right>li {
  margin-left: -1px;
}
.tabs-left>li.active>a,
.tabs-left>li.active>a:hover,
.tabs-left>li.active>a:focus {
  border-bottom-color: #ddd;
  border-right-color: transparent;
}

.tabs-right>li.active>a,
.tabs-right>li.active>a:hover,
.tabs-right>li.active>a:focus {
  border-bottom: 1px solid #ddd;
  border-left-color: transparent;
}
.tabs-left>li>a {
  border-radius: 4px 0 0 4px;
  margin-right: 0;
  display:block;
}
.tabs-right>li>a {
  border-radius: 0 4px 4px 0;
  margin-right: 0;
}
.vertical-text {
  margin-top:50px;
  border: none;
  position: relative;
}
.vertical-text>li {
  height: 20px;
  width: 120px;
  margin-bottom: 100px;
}
.vertical-text>li>a {
  border-bottom: 1px solid #ddd;
  border-right-color: transparent;
  text-align: center;
  border-radius: 4px 4px 0px 0px;
}
.vertical-text>li.active>a,
.vertical-text>li.active>a:hover,
.vertical-text>li.active>a:focus {
  border-bottom-color: transparent;
  border-right-color: #ddd;
  border-left-color: #ddd;
}
.vertical-text.tabs-left {
  left: -50px;
}
.vertical-text.tabs-right {
  right: -50px;
}
.vertical-text.tabs-right>li {
  -webkit-transform: rotate(90deg);
  -moz-transform: rotate(90deg);
  -ms-transform: rotate(90deg);
  -o-transform: rotate(90deg);
  transform: rotate(90deg);
}
.vertical-text.tabs-left>li {
  -webkit-transform: rotate(-90deg);
  -moz-transform: rotate(-90deg);
  -ms-transform: rotate(-90deg);
  -o-transform: rotate(-90deg);
  transform: rotate(-90deg);
}

</style>

<!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <section class="content">
            <div id="cuerpo" class="container">
                <div class="container">
                    <ol>
                        <form:form class="form-horizontal" method="post"
                                action="/thyssenplastic/articulofichatecnica/addOrEditOrRemove"
                                modelAttribute="articuloFichaTecnicaForm" id="myForm">

                        <div class="card">
                            
                            <div class="card-header">
                                <ol class="breadcrumb mb-1 mt-1">
                                    <li class="breadcrumb-item"><a href="#">Ficha Técnica</a></li>
                                    <li class="breadcrumb-item active">${titleArticuloFichaTecnica}</li>
                                </ol>
                                    <h4 style="text-align: center;">Ficha Técnica del Artículo <b>${articulo}</b> Código <b>${articuloPk}</b></h4>
                            </div>
                            
                            
                            <div class="card-body">
                                <div class="container">

                                    <p></p>
                                    ${articuloFichaTecnicaName}
                                    <p></p>

                                    <form:hidden path="pk" class="form-control"/>
                                    <form:hidden path="action" class="form-control"/>
                                    <form:hidden path="idArticulo" class="form-control"/>
                                    <form:hidden path="urlFichaTecnica" class="form-control"/>

                                    <c:set var = "disabledAlta" value = "true"/>
                                    <c:set var = "disabledEdit" value = "true"/>                      
                                    <c:set var = "displayUsuarioPanel" value = "none"/>                                                          
                                    <c:set var = "displayFilePanel" value = "none"/>                                                          
                                    <c:set var = "displaySaveButton" value = "none"/>           
                                    <c:set var = "buttonDisabled" value = ""/>          
                                    <c:if test = "${operacion == 'new'}">
                                        <c:set var = "disabledAlta" value = "false"/>
                                        <c:set var = "disabledEdit" value = "true"/>                                            
                                        <c:set var = "displayUsuarioPanel" value = "none"/>                                                          
                                        <c:set var = "displayFilePanel" value = "block"/>                                                          
                                        <c:set var = "displaySaveButton" value = "block"/> 
                                        <c:set var = "buttonDisabled" value = ""/>    
                                    </c:if>
                                    <c:if test = "${operacion == 'edit'}">
                                        <c:set var = "disabledAlta" value = "false"/>
                                        <c:set var = "disabledEdit" value = "true"/>                                            
                                        <c:set var = "displayUsuarioPanel" value = "block"/>    
                                        <c:set var = "displayFilePanel" value = "block"/>                                                          
                                        <c:set var = "displaySaveButton" value = "block"/> 
                                        <c:set var = "buttonDisabled" value = ""/>    
                                    </c:if>
                                    <c:if test = "${operacion == 'remove'}">
                                        <c:set var = "disabledAlta" value = "true"/>
                                        <c:set var = "disabledEdit" value = "true"/>                                            
                                        <c:set var = "displayUsuarioPanel" value = "block"/>   
                                        <c:set var = "displayFilePanel" value = "none"/>   
                                        <c:set var = "displaySaveButton" value = "block"/> 
                                        <c:set var = "buttonDisabled" value = "disabled"/>    
                                    </c:if>                                    
                                    <c:if test = "${operacion == 'view'}">
                                        <c:set var = "disabledAlta" value = "true"/>
                                        <c:set var = "disabledEdit" value = "true"/>                                            
                                        <c:set var = "displayUsuarioPanel" value = "block"/>   
                                        <c:set var = "displayFilePanel" value = "none"/>    
                                        <c:set var = "displaySaveButton" value = "none"/> 
                                        <c:set var = "buttonDisabled" value = "disabled"/>    
                                    </c:if>

                                    <div class="form-row row">
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputFecha">Fecha</label>
                                            <form:input type="date" path="fechaAlta" class="form-control" placeholder="" required="required" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                        </div>

                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputDenominacion">Versión</label>
                                            <form:input type="text" path="version" class="form-control" placeholder="" required="required" disabled="${disabledEdit}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                        </div>
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputRubro">Cliente</label>
                                            <form:input type="text" path="cliente" class="form-control" placeholder="" disabled="true"/>
                                        </div>

                                    </div>
                                    <div class="form-row row">
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputDenominacion">Observaciones</label>
                                            <form:textarea type="text" maxlength="4000" rows="3" path="observaciones" class="form-control" placeholder="" disabled="${disabledAlta}"/>
                                        </div>

                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputRubro">Especificación</label>
                                            <form:textarea type="text" maxlength="4000" rows="3" path="especificacion" class="form-control" placeholder="" required="required" disabled="${disabledAlta}" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')" />                                                
                                        </div>
                                    </div>
                                                                                
                                    <p></p>
                                    
                                    <div class="form-row row">
                                        <!--Seleccionar Arhcivo-->
                                        <div class="row col-xs-9 col-sm-3 col-xl-4" style="display: ${displayFilePanel}">
                                            <input id="file" type="file" class="findDocumentOnboarding">
                                            <div id="messageUploadFile" style="display:none">None</div>
                                        </div>
                                        <div class="row col-xs-9 col-sm-3 col-xl-4" style="display: ${displayFilePanel}">
                                            <input type="button" value="Subir Archivo" class="btn btn-xs btn-primary uploadDocumentOnboarding">
                                        </div>
                                        <div id="panelRefArchivo" class="row col-xs-9 col-sm-3 col-xl-4" style="display:none">
                                            Archivo: <a href="#" id="refArchivo" download>#</a>
                                        </div>
                                    </div>

                                    <p></p>

                                    <p></p>
                                    <div class="form-row row">
                                        <div class="row col-xs-9 col-sm-1 col-xl-1" style="display:${displaySaveButton}">
                                            <button type="button" class="btn btn-primary" onClick="callController()">${buttonLabel}</button>                                            
                                        </div>
                                        <div class="row col-xs-9 col-sm-1 col-xl-1">
                                            <a href="/thyssenplastic/articulofichatecnica/${articuloPk}"><button type="button" class="btn btn-secondary">Cancelar</button></a>
                                        </div>                                            
                                    </div>
                                                    
                                    <p></p>                                    
                                    
                                    <!--EDITAR OCULTO-->
                                    <div class="editar form-row row" style="display: ${displayUsuarioPanel}">   
                                        <hr>
                                        <div class="row">
                                            <label>Ultima Modificación</label>                                            
                                        </div>    
                                        <p>
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputUsuario">Usuario</label>
                                            <form:input type="text" path="usuarioModificacion" class="form-control" placeholder="" id="holderUsuario" disabled="true"/>
                                        </div>
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputFecha">Fecha</label>
                                            <form:input type="date" path="fechaModificacion" class="form-control" placeholder="" id="holderDateOfBirth" disabled="true"/>                                        
                                        </div>                                        
                                    </div>                                    
                                </div>
                            </div>
                        </div>

                        <div>
                            <p>&nbsp;</p>
                            <p>&nbsp;</p>
                        </div>

                        <!--TABS VERTICALES extrusion Impresion Empaque-->
                        <div class="row">
                            <div class="col-xs-1 col-sm-1 col-xl-1">
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
                            
                            <div class="col-xs-11 col-sm-11 col-xl-11">
                                <div class="tab-content">
                                    <!--CONTENIDO TAB EXTRUSION-->
                                    <div class="tab-pane active" id="extrusion">
                                        <div class="row">
                                            <div class="row col-xs-12 col-sm-12 col-xl-12">
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
                                                        <li><a data-toggle="tab" href="#bobinaPanel">BOBINA</a></li>
                                                    </ul>

                                                    <div class="tab-content">

                                                        <div id="material" class="tab-pane fade in active">
                                                            <!--CONTENIDO MATERIAL-->
                                                            <div class="container">
                                                                <p>&nbsp;</p>
                                                                <div class="form-row row">
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                        <label>Material</label>      
                                                                        <form:select path="idMaterial" class="form-control rubro" disabled="${disabledAlta}">                                                    
                                                                            <form:options items="${materialList}" />
                                                                        </form:select>                                                                        
                                                                    </div>
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                        <label>Lamina</label>
                                                                        <form:select path="lamina" class="form-control rubro" disabled="${disabledAlta}">                                                    
                                                                            <form:option value="0">No</form:option>
                                                                            <form:option value="1">Si</form:option>                                                                            
                                                                        </form:select>                                                                                                                                                
                                                                    </div>                                                                            
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                        <label>Bobina</label>                                                                        
                                                                        <form:select path="bobina" class="form-control rubro" disabled="${disabledAlta}">                                                    
                                                                            <form:option value="0">No</form:option>
                                                                            <form:option value="1">Si</form:option>                                                                            
                                                                        </form:select>                                                                                                                                                                                                                        
                                                                    </div>                                            
                                                                </div>

                                                                <div class="form-row row">
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                        <label>Cantidad de Capas</label>
                                                                        <form:input type="number" path="cantidadCapas" class="form-control" id="cantidadCapas" min="1" max="7" onChange="hideOrDisplayColorDivs()" disabled="${disabledAlta}"/>
                                                                    </div>
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4" style="display: none">
                                                                        <label>Capa</label>
                                                                        <select multiple
                                                                            class="form-control" id="capa">
                                                                            <option>A</option>
                                                                            <option>B</option>
                                                                            <option>C</option>
                                                                            <option>D</option>
                                                                            <option>E</option>
                                                                            <option>F</option>
                                                                        </select>                                                                        
                                                                    </div> 
                                                                </div>                                                                    
                                                                <div class="form-row row">
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4" id="colorCapaA">                                                                    
                                                                        <label>Color Capa A</label>
                                                                        <form:select path="colorA" class="form-control rubro" disabled="${disabledAlta}">                                                    
                                                                            <form:option value="-1">Seleccionar...</form:option>
                                                                            <form:options items="${colorList}" />
                                                                        </form:select>                                                                        
                                                                    </div>                                                                        
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4" id="colorCapaB" style="display: none">                                                                                                                                            
                                                                        <label>Color Capa B</label>
                                                                        <form:select path="colorB" class="form-control rubro" disabled="${disabledAlta}">                                                    
                                                                            <form:option value="-1">Seleccionar...</form:option>
                                                                            <form:options items="${colorList}" />
                                                                        </form:select>                                                                        
                                                                    </div>
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4"  id="colorCapaC" style="display: none">
                                                                        <label>Color Capa C</label>
                                                                        <form:select path="colorC" class="form-control rubro" disabled="${disabledAlta}">                                                    
                                                                            <form:option value="-1">Seleccionar...</form:option>
                                                                            <form:options items="${colorList}" />
                                                                        </form:select>                                                                        
                                                                    </div>
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4" id="colorCapaD" style="display: none">                                                                    
                                                                        <label>Color Capa D</label>
                                                                        <form:select path="colorD" class="form-control rubro" disabled="${disabledAlta}">                                                    
                                                                            <form:option value="-1">Seleccionar...</form:option>
                                                                            <form:options items="${colorList}" />
                                                                        </form:select>                                                                        
                                                                    </div>                                                                                                                                            
                                                                </div>
                                                                <div class="form-row row">
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4" id="colorCapaE" style="display: none">                                                                    
                                                                        <label>Color Capa E</label>
                                                                        <form:select path="colorE" class="form-control rubro" disabled="${disabledAlta}">                                                    
                                                                            <form:option value="-1">Seleccionar...</form:option>
                                                                            <form:options items="${colorList}" />
                                                                        </form:select>                                                                        
                                                                    </div>                                                                        
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4" id="colorCapaF" style="display: none">                                                                                                                                            
                                                                        <label>Color Capa F</label>
                                                                        <form:select path="colorF" class="form-control rubro" disabled="${disabledAlta}">                                                    
                                                                            <form:option value="-1">Seleccionar...</form:option>
                                                                            <form:options items="${colorList}" />
                                                                        </form:select>                                                                        
                                                                    </div>
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4" id="colorCapaG" style="display: none">                                                                                                                                                                                                                    
                                                                        <label>Color Capa G</label>
                                                                        <form:select path="colorG" class="form-control rubro" disabled="${disabledAlta}">                                                    
                                                                            <form:option value="-1">Seleccionar...</form:option>
                                                                            <form:options items="${colorList}" />
                                                                        </form:select>                                                                        
                                                                    </div>
                                                                </div>                                                                
                                                                <p>&nbsp;</p>
                                                                <div class="form-row row">
                                                                    <ol class="breadcrumb mb-1 mt-1">
                                                                        <li class="breadcrumb-item"><a href="">Medidas</a></li>
                                                                    </ol>
                                                                </div>
                                                                <div class="form-row row">
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                        <label>Ancho (mm)</label>
                                                                        <form:input type="number" path="ancho" step="0.01" class="form-control" placeholder="Ancho ..." disabled="${disabledAlta}"/>
                                                                    </div>
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                        <label>Fuelle Izquierdo (mm)</label>
                                                                        <form:input type="number" path="fuelleIzquierdo" step="0.01" class="form-control" placeholder="F Izq ..." disabled="${disabledAlta}"/>
                                                                    </div>
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                        <label>Fuelle Derecho (mm)</label>
                                                                        <form:input type="number" path="fuelleDerecho" step="0.01" class="form-control" placeholder="F Der ..." disabled="${disabledAlta}"/>                                                                        
                                                                    </div>                                                                    
                                                                </div>                                                                                                                                
                                                                <div class="form-row row">
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                        <label>Espesor Nominal (mic)</label>
                                                                        <form:input type="number" path="espesorNominal" step="0.01" class="form-control" placeholder="Espesor N ..." disabled="${disabledAlta}"/>
                                                                    </div>
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                        <label>Espesor Promedio (mic)</label>
                                                                        <form:input type="number" path="espesorPromedio" step="0.01" class="form-control" placeholder="Espesor P..." disabled="${disabledAlta}"/>
                                                                    </div>
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                        <label>Espesor del Artículo (mic)</label>
                                                                        <form:input type="number" path="espesorArticulo" step="0.01" class="form-control" placeholder="Espesor del A ..." disabled="${disabledAlta}"/>                                                                        
                                                                    </div>                                                                    
                                                                </div>                                                                                                                                                                                                
                                                            </div>
                                                        </div>

                                                        <div id="formulacion" class="tab-pane fade formulacion">                                                            
                                                            <!--CONTENIDO FORMULACION-->                                                            
                                                            <div class="container">
                                                                <p>&nbsp;</p>
                                                                <div class="form-row row">
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                        <label>Proveedor</label>
                                                                        <form:select path="idProveedor" class="form-control rubro" disabled="${disabledAlta}" onChange="loadMateriaPrima()">                                                    
                                                                            <form:option value="-1">Seleccionar...</form:option>
                                                                            <form:options items="${proveedorList}" />
                                                                        </form:select>                                                                        
                                                                    </div>                                                                                                                                        
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                        <label>Materia Prima</label>
                                                                        <form:select path="idMateriaPrima" class="form-control rubro" disabled="${disabledAlta}">                                                    
                                                                            <form:option value="-1">Seleccionar...</form:option>
                                                                        </form:select>                                                                        
                                                                    </div>                                                                                                                                        
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                        <label>Tolva</label>
                                                                        <form:select path="item" class="form-control rubro" disabled="${disabledAlta}">                                                    
                                                                            <form:option value="1">1</form:option>
                                                                            <form:option value="2">2</form:option>
                                                                            <form:option value="3">3</form:option>
                                                                            <form:option value="4">4</form:option>
                                                                            <form:option value="5">5</form:option>
                                                                            <form:option value="6">6</form:option>
                                                                        </form:select>                                                                        
                                                                    </div>                                                                                                                                                                                                            
                                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                        <label>&nbsp;</label>
                                                                        <input type="button" class="btn btn-primary" value="Agregar" onclick="agregarMateriaPrima()" style="margin-top: 6%;" ${buttonDisabled}/>
                                                                    </div>                                                                                                                          
                                                                </div>                                                                
                                                                <p>&nbsp;</p>
                                                                <ul class="nav nav-tabs">                                                                    
                                                                    <li id="tabCapaA" class="active"><a data-toggle="tab" href="#capa-a">Capa A</a></li>
                                                                    <li id="tabCapaB" style="display: none"><a data-toggle="tab" href="#capa-b">Capa B</a></li>                                                                    
                                                                    <li id="tabCapaC" style="display: none"><a data-toggle="tab" href="#capa-c">Capa C</a></li>
                                                                    <li id="tabCapaD" style="display: none"><a data-toggle="tab" href="#capa-d">Capa D</a></li>
                                                                    <li id="tabCapaE" style="display: none"><a data-toggle="tab" href="#capa-e">Capa E</a></li>
                                                                    <li id="tabCapaF" style="display: none"><a data-toggle="tab" href="#capa-f">Capa F</a></li>
                                                                    <li id="tabCapaG" style="display: none"><a data-toggle="tab" href="#capa-g">Capa G</a></li>
                                                                </ul>

                                                                <div class="tab-content">
                                                                    <div id="capa-a" class="tab-pane fade in active">                                                                        
                                                                        <p>&nbsp;</p>
                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 1)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima1CapaA"/>
                                                                                <form:input type="text" path="materiaPrima1CapaA" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage1CapaA" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('A','1')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 2)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima2CapaA"/>
                                                                                <form:input type="text" path="materiaPrima2CapaA" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage2CapaA" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('A','2')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 3)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima3CapaA"/>
                                                                                <form:input type="text" path="materiaPrima3CapaA" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage3CapaA" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('A','3')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 4)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima4CapaA"/>
                                                                                <form:input type="text" path="materiaPrima4CapaA" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage4CapaA" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('A','4')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 5)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima5CapaA"/>
                                                                                <form:input type="text" path="materiaPrima5CapaA" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage5CapaA" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('A','5')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 6)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima6CapaA"/>
                                                                                <form:input type="text" path="materiaPrima6CapaA" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage6CapaA" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('A','6')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>                                                                        
                                                                    </div>
                                                                    <div id="capa-b" class="tab-pane fade">
                                                                        <p>&nbsp;</p>
                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 1)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima1CapaB"/>
                                                                                <form:input type="text" path="materiaPrima1CapaB" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage1CapaB" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('B','1')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 2)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima2CapaB"/>
                                                                                <form:input type="text" path="materiaPrima2CapaB" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage2CapaB" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('B','2')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 3)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima3CapaB"/>
                                                                                <form:input type="text" path="materiaPrima3CapaB" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage3CapaB" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('B','3')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 4)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima4CapaB"/>
                                                                                <form:input type="text" path="materiaPrima4CapaB" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage4CapaB" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('B','4')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 5)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima5CapaB"/>
                                                                                <form:input type="text" path="materiaPrima5CapaB" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage5CapaB" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('B','5')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 6)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima6CapaB"/>
                                                                                <form:input type="text" path="materiaPrima6CapaB" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage6CapaB" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('B','6')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>                                                                           
                                                                    </div>                                                                                 
                                                                    <div id="capa-c" class="tab-pane fade">
                                                                        <p>&nbsp;</p>
                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 1)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima1CapaC"/>
                                                                                <form:input type="text" path="materiaPrima1CapaC" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage1CapaC" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('C','1')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 2)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima2CapaC"/>
                                                                                <form:input type="text" path="materiaPrima2CapaC" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage2CapaC" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('C','2')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 3)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima3CapaC"/>
                                                                                <form:input type="text" path="materiaPrima3CapaC" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage3CapaC" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('C','3')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 4)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima4CapaC"/>
                                                                                <form:input type="text" path="materiaPrima4CapaC" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage4CapaC" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('C','4')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 5)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima5CapaC"/>
                                                                                <form:input type="text" path="materiaPrima5CapaC" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage5CapaC" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('C','5')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 6)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima6CapaC"/>
                                                                                <form:input type="text" path="materiaPrima6CapaC" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage6CapaC" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('C','6')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>                                                                                                                                                   
                                                                    </div>
                                                                    <div id="capa-d" class="tab-pane fade">
                                                                        <p>&nbsp;</p>
                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 1)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima1CapaD"/>
                                                                                <form:input type="text" path="materiaPrima1CapaD" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage1CapaD" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('D','1')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 2)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima2CapaD"/>
                                                                                <form:input type="text" path="materiaPrima2CapaD" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage2CapaD" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('D','2')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 3)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima3CapaD"/>
                                                                                <form:input type="text" path="materiaPrima3CapaD" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage3CapaD" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('D','3')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 4)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima4CapaD"/>
                                                                                <form:input type="text" path="materiaPrima4CapaD" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage4CapaD" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('D','4')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 5)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima5CapaD"/>
                                                                                <form:input type="text" path="materiaPrima5CapaD" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage5CapaD" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('D','5')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 6)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima6CapaD"/>
                                                                                <form:input type="text" path="materiaPrima6CapaD" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage6CapaD" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('D','6')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>
                                                                    </div>
                                                                    <div id="capa-e" class="tab-pane fade">
                                                                        <p>&nbsp;</p>
                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 1)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima1CapaE"/>
                                                                                <form:input type="text" path="materiaPrima1CapaE" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage1CapaE" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('E','1')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 2)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima2CapaE"/>
                                                                                <form:input type="text" path="materiaPrima2CapaE" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage2CapaE" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('E','2')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 3)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima3CapaE"/>
                                                                                <form:input type="text" path="materiaPrima3CapaE" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage3CapaE" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('E','3')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 4)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima4CapaE"/>
                                                                                <form:input type="text" path="materiaPrima4CapaE" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage4CapaE" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('E','4')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 5)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima5CapaE"/>
                                                                                <form:input type="text" path="materiaPrima5CapaE" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage5CapaE" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('E','5')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 6)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima6CapaE"/>
                                                                                <form:input type="text" path="materiaPrima6CapaE" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage6CapaE" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('E','6')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>                                                                        
                                                                    </div>                                                                            
                                                                    <div id="capa-f" class="tab-pane fade">
                                                                        <p>&nbsp;</p>
                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 1)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima1CapaF"/>
                                                                                <form:input type="text" path="materiaPrima1CapaF" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage1CapaF" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('F','1')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 2)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima2CapaF"/>
                                                                                <form:input type="text" path="materiaPrima2CapaF" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage2CapaF" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('F','2')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 3)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima3CapaF"/>
                                                                                <form:input type="text" path="materiaPrima3CapaF" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage3CapaF" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('F','3')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 4)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima4CapaF"/>
                                                                                <form:input type="text" path="materiaPrima4CapaF" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage4CapaF" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('F','4')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 5)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima5CapaF"/>
                                                                                <form:input type="text" path="materiaPrima5CapaF" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage5CapaF" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('F','5')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 6)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima6CapaF"/>
                                                                                <form:input type="text" path="materiaPrima6CapaF" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage6CapaF" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('F','6')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>                                                                        
                                                                    </div>
                                                                    <div id="capa-g" class="tab-pane fade">
                                                                        <p>&nbsp;</p>
                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 1)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima1CapaG"/>
                                                                                <form:input type="text" path="materiaPrima1CapaG" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage1CapaG" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('G','1')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 2)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima2CapaG"/>
                                                                                <form:input type="text" path="materiaPrima2CapaG" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage2CapaG" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('G','2')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 3)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima3CapaG"/>
                                                                                <form:input type="text" path="materiaPrima3CapaG" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage3CapaG" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('G','3')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 4)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima4CapaG"/>
                                                                                <form:input type="text" path="materiaPrima4CapaG" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage4CapaG" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('G','4')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 5)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima5CapaG"/>
                                                                                <form:input type="text" path="materiaPrima5CapaG" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage5CapaG" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('G','5')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>

                                                                        <div class="form-row row">
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Materia Prima (Tolva 6)</label>
                                                                                <form:input type="hidden" path="idMateriaPrima6CapaG"/>
                                                                                <form:input type="text" path="materiaPrima6CapaG" class="form-control" placeholder="" disabled="true"/>
                                                                            </div>                                                                    
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>Porcentage (%)</label>
                                                                                <form:input type="number" path="materiaPrimaPorcentage6CapaG" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                            </div>                                                                                                                                        
                                                                            <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                                                <label>&nbsp;</label>
                                                                                <button class="btn" onclick="eliminarMateriaPrima('G','6')" style="margin-top: 6%;" ${buttonDisabled}><i class="fa fa-trash fa-lg"></i></button>
                                                                            </div>                                                                                                                                                                                                            
                                                                        </div>
                                                                    </div>
                                                                            
                                                                </div>    
                                                                            
                                                                <p>&nbsp;</p>
                                                                <div class="form-row row">
                                                                    <ol class="breadcrumb mb-1 mt-1">
                                                                        <li class="breadcrumb-item"><a href="">Resumen de Capas</a></li>
                                                                    </ol>
                                                                </div>
                                                                <div class="form-row row">
                                                                    <div id="panelResumenCapaA" class="row col-xs-9 col-sm-3 col-xl-4">
                                                                        <label>Capa A</label>
                                                                        <form:input type="number" path="resumenCapaA" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                    </div>                                                                                                                                        
                                                                    <div id="panelResumenCapaB" class="row col-xs-9 col-sm-3 col-xl-4" style="display: none">
                                                                        <label>Capa B</label>
                                                                        <form:input type="number" path="resumenCapaB" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                    </div>                                                                                                                                        
                                                                    <div id="panelResumenCapaC" class="row col-xs-9 col-sm-3 col-xl-4" style="display: none">
                                                                        <label>Capa C</label>
                                                                        <form:input type="number" path="resumenCapaC" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                    </div>                                                                                                                                        
                                                                    <div id="panelResumenCapaD" class="row col-xs-9 col-sm-3 col-xl-4" style="display: none">
                                                                        <label>Capa D</label>
                                                                        <form:input type="number" path="resumenCapaD" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                    </div>
                                                                </div>            
                                                                <div class="form-row row">
                                                                    <div id="panelResumenCapaE" class="row col-xs-9 col-sm-3 col-xl-4" style="display: none">
                                                                        <label>Capa E</label>
                                                                        <form:input type="number" path="resumenCapaE" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                    </div>                                                                                                                                        
                                                                    <div id="panelResumenCapaF" class="row col-xs-9 col-sm-3 col-xl-4" style="display: none">
                                                                        <label>Capa F</label>
                                                                        <form:input type="number" path="resumenCapaF" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                    </div>                                                                                                                                        
                                                                    <div id="panelResumenCapaG" class="row col-xs-9 col-sm-3 col-xl-4" style="display: none">
                                                                        <label>Capa G</label>
                                                                        <form:input type="number" path="resumenCapaG" step="0.01" min="0" class="form-control" placeholder="" disabled="${disabledAlta}" onChange="calcularMaterialesUtilizados()"/>
                                                                    </div>                                                                                                                                        
                                                                </div>     
                                                                    
                                                                <p>&nbsp;</p>
                                                                <div class="form-row row">
                                                                    <ol class="breadcrumb mb-1 mt-1">
                                                                        <li class="breadcrumb-item"><a href="">Materiales Utilizados</a></li>
                                                                    </ol>
                                                                </div>
                                                                <div id="panelMaterialesUtilizados" class="form-row row">
                                                                </div>    
                                                            </div>
                                                        </div>

                                                        <div id="bobinaPanel" class="tab-pane fade">
                                                            <!--CONTENIDO BOBINA-->
                                                            <div class="container">
                                                                <div class="col-xs-9 col-sm-9 col-md-9">
                                                                    <ol class="breadcrumb mb-1 mt-1">
                                                                        <li class="breadcrumb-item"><a
                                                                                href="">Bobina</a></li>
                                                                    </ol>
                                                                    <p>

                                                                    <div class="box-body">
                                                                        <div class="row ">
                                                                            <div class="row col-xs-9 col-sm-4 col-xl-4">
                                                                                <div class="form-group">
                                                                                    <label>Peso Específico (g/cm3)</label>
                                                                                    <form:input type="number" path="pesoEspecifico" step="0.01" class="form-control" placeholder="Peso E..." disabled="${disabledAlta}"/>
                                                                                </div>
                                                                            </div>
                                                                            <div class="row col-xs-9 col-sm-4 col-xl-4">
                                                                                <div class="form-group">
                                                                                    <label>Ancho Bruto (mm)</label>
                                                                                    <form:input type="number" path="anchoBruto" step="0.01" class="form-control" placeholder="Ancho B..." disabled="${disabledAlta}"/>
                                                                                </div>
                                                                            </div>

                                                                        </div>
                                                                        <div class="row">

                                                                            <div class="row col-xs-9 col-sm-4 col-xl-4">
                                                                                <div class="form-group">
                                                                                    <label>Bobinado por Barras</label>
                                                                                    <form:input type="number" path="bobinadoBarras" step="0.01" class="form-control" placeholder="Espesor ..." disabled="${disabledAlta}"/>
                                                                                </div>
                                                                            </div>
                                                                            <div class="row col-xs-9 col-sm-4 col-xl-4">
                                                                                <div class="form-group">
                                                                                    <label>Metros (mts)</label>
                                                                                    <form:input type="number" path="metros" step="0.01" class="form-control" placeholder="Metros ..." disabled="${disabledAlta}"/>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <div class="row">
                                                                            <div class="row col-xs-9 col-sm-4 col-xl-4">
                                                                                <div class="form-group">
                                                                                    <label>Peso (kgrs)</label>
                                                                                    <form:input type="number" path="peso" step="0.01" class="form-control" placeholder="Peso ..." disabled="${disabledAlta}"/>
                                                                                </div>
                                                                            </div>
                                                                            <div class="row col-xs-9 col-sm-4 col-xl-4">
                                                                                <div class="form-group">
                                                                                    <label>Diámetro (mm)</label>
                                                                                    <form:input type="number" path="diametro" step="0.01" class="form-control" placeholder="Diametro ..." disabled="${disabledAlta}"/>
                                                                                </div>
                                                                            </div>
                                                                            <div class="row col-xs-9 col-sm-4 col-xl-4">
                                                                                <div class="form-group">
                                                                                    <label>Empalmes</label>
                                                                                    <form:input type="number" path="empalmes" step="0.01" class="form-control" placeholder="Empalmes ..." disabled="${disabledAlta}"/>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    
                                                                    </p>


                                                                </div>
                                                                <div class="col-xs-9 col-sm-9 col-md-9">
                                                                    <ol class="breadcrumb mb-1 mt-1">
                                                                        <li class="breadcrumb-item"><a
                                                                                href="">Buje</a></li>
                                                                    </ol>
                                                                    <p>
                                                                        
                                                                    <div class="box-body">
                                                                        <div class="row">
                                                                            <div class="row col-xs-9 col-sm-4 col-xl-4">
                                                                                <div class="form-group">
                                                                                    <label>Tipo</label>
                                                                                    <form:select type="number" path="idTipoBobina" step="0.01" class="form-control" disabled="${disabledAlta}">
                                                                                        <form:option value="-1">Seleccionar...</form:option>
                                                                                        <form:options items="${tipoBobinaList}" />
                                                                                    </form:select>
                                                                                </div>
                                                                            </div>

                                                                            <div class="row col-xs-9 col-sm-4 col-xl-4">
                                                                                <div class="form-group">
                                                                                    <label>Diámetro (mm)</label>
                                                                                    <form:input type="number" path="diametro2" step="0.01" class="form-control" placeholder="Diámetro ..." disabled="${disabledAlta}"/>
                                                                                </div>
                                                                            </div>
                                                                        </div>

                                                                        <div class="row">
                                                                            <div class="row col-xs-9 col-sm-4 col-xl-4">
                                                                                <div class="form-group">
                                                                                    <label>Largo (mm)</label>
                                                                                    <form:input type="number" path="largo" step="0.01" class="form-control" placeholder="Largo ..." disabled="${disabledAlta}"/>
                                                                                </div>
                                                                            </div>
                                                                            <div class="row col-xs-9 col-sm-4 col-xl-4">
                                                                                <div class="form-group">
                                                                                    <label>Espesor (mic)</label>
                                                                                    <form:input type="number" path="espesor" step="0.01" class="form-control" placeholder="Espesor ..." disabled="${disabledAlta}"/>
                                                                                </div>
                                                                            </div>
                                                                            <div class="row col-xs-9 col-sm-4 col-xl-4">
                                                                                <div class="form-group">
                                                                                    <label>Peso (kgrs)</label>
                                                                                    <form:input type="number" path="peso2" step="0.01" class="form-control" placeholder="Peso ..." disabled="${disabledAlta}"/>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>                                                                    
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
                                        <p>&nbsp;</p>
                                        <div class="card-body">
                                            <div class="container">                                        
                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label>Línea de tintas</label>
                                                        <form:select path="idLineaTintas" class="form-control rubro" disabled="${disabledAlta}">                                                    
                                                            <form:option value="-1">Seleccionar...</form:option>
                                                            <form:options items="${lineaTintasList}" />
                                                        </form:select>                                                
                                                    </div>    
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label>Línea de solventes</label>
                                                        <form:select  path="idLineaSolventes" class="form-control rubro" disabled="${disabledAlta}">                                                    
                                                            <form:option value="-1">Seleccionar...</form:option>
                                                            <form:options items="${lineaSolventesList}" />
                                                        </form:select>                                                
                                                    </div>                                                        
                                                </div>
                                            </div>
                                        </div>                                            
                                    </div>
                                    <!--CONTENIDO TAB EMPAQUE-->
                                    <div class="tab-pane" id="empaque">
                                        <p>&nbsp;</p>
                                        <div class="card-body">
                                            <div class="container">                                        
                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                        <label>Bultos en</label>
                                                        <form:select path="idBultosEn" class="form-control rubro" disabled="${disabledAlta}">                                                    
                                                            <form:option value="-1">Seleccionar...</form:option>
                                                            <form:options items="${bultoEnList}" />
                                                        </form:select>
                                                    </div>
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">                                                    
                                                        <label>Bultos por Pallets</label>
                                                        <form:input type="number" path="bultosPorPalet" step="0.01" class="form-control" placeholder="Bultos por..." disabled="${disabledAlta}" />
                                                    </div>
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">                                                                                                        
                                                        <label>Pallets</label>
                                                        <form:select path="idPalet" class="form-control rubro" disabled="${disabledAlta}">                                                    
                                                            <form:option value="-1">Seleccionar...</form:option>
                                                            <form:options items="${paletList}" />
                                                        </form:select>
                                                    </div>
                                                </div>
                                                <p>&nbsp;</p>
                                                <div class="form-row row">
                                                    <div class="row col-xs-9 col-sm-3 col-xl-4">                                                    
                                                        <label>Posición del Bultos</label>
                                                        <form:select path="posicionPalet" class="form-control rubro" disabled="${disabledAlta}">                                                    
                                                            <form:option value="-1">Seleccionar...</form:option>
                                                            <form:option value="horizontal">Horizontal</form:option>
                                                            <form:option value="vertical">Vertical</form:option>
                                                        </form:select>                                                     
                                                    </div>                                                    
                                                </div>                                                    
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <p>&nbsp;</p>
                        <hr>
                        <p>&nbsp;</p>
                        
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

                                            <c:forEach items="${articuloFichasTecnica}" var="articulofichatecnica">
                                                <tr>
                                                    <td>
                                                        <c:out value="${articulofichatecnica.fechaAlta}" />
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
                                                        <a class="nav-link active fa fa-clone fa-lg"
                                                            href="/thyssenplastic/articulofichatecnica/clone/${articulofichatecnica.pk}"
                                                            data-toggle="tooltip" data-placement="top" title="Clonar"></a>                                                                                                                                                                                
                                                        <a class="nav-link active fa fa-eye fa-lg"
                                                            href="/thyssenplastic/articulofichatecnica/view/${articulofichatecnica.pk}"
                                                            data-toggle="tooltip" data-placement="top" title="Ver"></a>                                                        
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

    <script charset="utf-8">
        $(document).ready(function () {

            
            $('#articulosfichatecnicaTable').DataTable({
                language: {
                    "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
                },
                order: [[1, 'desc']]
            });
            $('#materialesutilizadosTable').DataTable({
                language: {
                    "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
                }
            });
            
            var pk = $('#pk').val();
            if (pk != null && pk != '-1') {
                var fileName = $('#urlFichaTecnica').val();
                if (fileName != null && fileName != '') {
                    var fileNames = fileName.split('/');
                    var index = fileNames.length - 1;
                    $("#panelRefArchivo").css("display", "block");
                    $('#refArchivo').attr("href", fileName.replace('/', '\\'));
                    $('#refArchivo').text(fileNames[index]);
                }
            }
            
            hideOrDisplayColorDivs();
            
            calcularMaterialesUtilizados();
        });


        async function uploadFile() {
            let formData = new FormData();
            formData.append("file", ajaxfile.files[0]);
            await fetch('fileuploadservlet', {
                method: "POST",
                body: formData
            });
            alert('The file upload with Ajax and Java was a success!');
        }

        $(".uploadDocumentOnboarding").on("click", function (evt) {

            var documentData = new FormData();
            documentData.append('file', $('input#file.findDocumentOnboarding')[0].files[0]);
            $.ajax({
                url: '/thyssenplastic/uploadfile',
                type: 'POST',
                data: documentData,
                cache: false,
                contentType: false,
                processData: false,
                success: function (response) {
                    if (response.length > 0) {
                        var lines = response.split('|');
                        if (lines[0] == 'Successful') {
                            $('#urlFichaTecnica').val(lines[1]);
                            $("#messageUploadFile").css("display", "block");
                            $('#messageUploadFile').text('El archivo fue subido satisfactoriamente.');
                            alert("El archivo fue subido satisfactoriamente.");
                        } else {
                            $("#messageUploadFile").css("display", "block");
                            $('#messageUploadFile').text('Error, no se pudo subir el archivo.');
                            alert("Error, no se pudo subir el archivo.");
                        }
                    }
                }
            });
        });

        function hideOrDisplayColorDivs() {
                        
            var cantidadCapas = $('#cantidadCapas').val();
            var cantidadCapasInt = parseInt(cantidadCapas);
            if(cantidadCapasInt > 1) {
                document.getElementById("colorCapaB").style.display="block";
                document.getElementById("tabCapaB").style.display="block";
                document.getElementById("panelResumenCapaB").style.display="block";
            } else {
                document.getElementById("colorCapaB").style.display="none";
                document.getElementById("tabCapaB").style.display="none";
                document.getElementById("panelResumenCapaB").style.display="none";
            }
            if(cantidadCapasInt > 2) {
                document.getElementById("colorCapaC").style.display="block";
                document.getElementById("tabCapaC").style.display="block";
                document.getElementById("panelResumenCapaC").style.display="block";
            } else {
                document.getElementById("colorCapaC").style.display="none";
                document.getElementById("tabCapaC").style.display="none";
                document.getElementById("panelResumenCapaC").style.display="none";
            }
            if(cantidadCapasInt > 3) {
                document.getElementById("colorCapaD").style.display="block";
                document.getElementById("tabCapaD").style.display="block";
                document.getElementById("panelResumenCapaD").style.display="block";
            } else {
                document.getElementById("colorCapaD").style.display="none";
                document.getElementById("tabCapaD").style.display="none";
                document.getElementById("panelResumenCapaD").style.display="none";
            }
            if(cantidadCapasInt > 4) {
                document.getElementById("colorCapaE").style.display="block";
                document.getElementById("tabCapaE").style.display="block";
                document.getElementById("panelResumenCapaE").style.display="block";
            } else {
                document.getElementById("colorCapaE").style.display="none";
                document.getElementById("tabCapaE").style.display="none";
                document.getElementById("panelResumenCapaE").style.display="none";
            }
            if(cantidadCapasInt > 5) {
                document.getElementById("colorCapaF").style.display="block";
                document.getElementById("tabCapaF").style.display="block";
                document.getElementById("panelResumenCapaF").style.display="block";
            } else {
                document.getElementById("colorCapaF").style.display="none";
                document.getElementById("tabCapaF").style.display="none";
                document.getElementById("panelResumenCapaF").style.display="none";
            }
            if(cantidadCapasInt > 6) {
                document.getElementById("colorCapaG").style.display="block";
                document.getElementById("tabCapaG").style.display="block";
                document.getElementById("panelResumenCapaG").style.display="block";
            } else {
                document.getElementById("colorCapaG").style.display="none";
                document.getElementById("tabCapaG").style.display="none";
                document.getElementById("panelResumenCapaG").style.display="none";
            }
            
        }
    
        function loadMateriaPrima() {
            
            var idProveedor = $( "#idProveedor option:selected" ).val();            

            var sUrl = '/thyssenplastic/articulofichatecnica/getMateriaPrimaByProveedor/'+idProveedor;
            $.ajax({
                async: true,
                type: 'GET',
                dataType : 'json',
                url: sUrl
            })
            .done( function (responseText) {
                $("#idMateriaPrima").empty();
                if(responseText.length > 0) {                    
                    for (i = 0; i < responseText.length; i++) {
                        var materiaPrima = responseText[i];
                        $("#idMateriaPrima").append( new Option(materiaPrima.nombre,materiaPrima.id));
                    }
                } else {
                    $("#idMateriaPrima").append( new Option("Seleccionar...","-1"));
                }
            })
            .fail( function (jqXHR, status, error) {
                // Triggered if response status code is NOT 200 (OK)
                alert(jqXHR.responseText);
            })
            .always( function() {
                // Always run after .done() or .fail()
            });
             
          
        }
        
        function agregarMateriaPrima() {
            
            var divName = $('.formulacion .nav-tabs .active a').attr('href');  
            alert(divName);
            
            var idMateriaPrima = $( "#idMateriaPrima option:selected" ).val();  
            var materiaPrimaText = $( "#idMateriaPrima option:selected" ).text();  
            
            if(divName == '#capa-a') {
                if(idMateriaPrima != '-1') {
                    var item = $( "#item option:selected" ).val();
                    if(item == '1') {
                        $( "#idMateriaPrima1CapaA" ).val(idMateriaPrima);  
                        $( "#materiaPrima1CapaA" ).val(materiaPrimaText);  
                    }
                    if(item == '2') {
                        $( "#idMateriaPrima2CapaA" ).val(idMateriaPrima);  
                        $( "#materiaPrima2CapaA" ).val(materiaPrimaText);  
                    }
                    if(item == '3') {
                        $( "#idMateriaPrima3CapaA" ).val(idMateriaPrima);  
                        $( "#materiaPrima3CapaA" ).val(materiaPrimaText);  
                    }
                    if(item == '4') {
                        $( "#idMateriaPrima4CapaA" ).val(idMateriaPrima);  
                        $( "#materiaPrima4CapaA" ).val(materiaPrimaText);  
                    }
                    if(item == '5') {
                        $( "#idMateriaPrima5CapaA" ).val(idMateriaPrima);  
                        $( "#materiaPrima5CapaA" ).val(materiaPrimaText);  
                    }
                    if(item == '6') {
                        $( "#idMateriaPrima6CapaA" ).val(idMateriaPrima);  
                        $( "#materiaPrima6CapaA" ).val(materiaPrimaText);  
                    }                
                }
            }
            if(divName == '#capa-b') {
                if(idMateriaPrima != '-1') {
                    var item = $( "#item option:selected" ).val();
                    if(item == '1') {
                        $( "#idMateriaPrima1CapaB" ).val(idMateriaPrima);  
                        $( "#materiaPrima1CapaB" ).val(materiaPrimaText);  
                    }
                    if(item == '2') {
                        $( "#idMateriaPrima2CapaB" ).val(idMateriaPrima);  
                        $( "#materiaPrima2CapaB" ).val(materiaPrimaText);  
                    }
                    if(item == '3') {
                        $( "#idMateriaPrima3CapaB" ).val(idMateriaPrima);  
                        $( "#materiaPrima3CapaB" ).val(materiaPrimaText);  
                    }
                    if(item == '4') {
                        $( "#idMateriaPrima4CapaB" ).val(idMateriaPrima);  
                        $( "#materiaPrima4CapaB" ).val(materiaPrimaText);  
                    }
                    if(item == '5') {
                        $( "#idMateriaPrima5CapaB" ).val(idMateriaPrima);  
                        $( "#materiaPrima5CapaB" ).val(materiaPrimaText);  
                    }
                    if(item == '6') {
                        $( "#idMateriaPrima6CapaB" ).val(idMateriaPrima);  
                        $( "#materiaPrima6CapaB" ).val(materiaPrimaText);  
                    }                
                }
            }
            if(divName == '#capa-c') {
                if(idMateriaPrima != '-1') {
                    var item = $( "#item option:selected" ).val();
                    if(item == '1') {
                        $( "#idMateriaPrima1CapaC" ).val(idMateriaPrima);  
                        $( "#materiaPrima1CapaC" ).val(materiaPrimaText);  
                    }
                    if(item == '2') {
                        $( "#idMateriaPrima2CapaC" ).val(idMateriaPrima);  
                        $( "#materiaPrima2CapaC" ).val(materiaPrimaText);  
                    }
                    if(item == '3') {
                        $( "#idMateriaPrima3CapaC" ).val(idMateriaPrima);  
                        $( "#materiaPrima3CapaC" ).val(materiaPrimaText);  
                    }
                    if(item == '4') {
                        $( "#idMateriaPrima4CapaC" ).val(idMateriaPrima);  
                        $( "#materiaPrima4CapaC" ).val(materiaPrimaText);  
                    }
                    if(item == '5') {
                        $( "#idMateriaPrima5CapaC" ).val(idMateriaPrima);  
                        $( "#materiaPrima5CapaC" ).val(materiaPrimaText);  
                    }
                    if(item == '6') {
                        $( "#idMateriaPrima6CapaC" ).val(idMateriaPrima);  
                        $( "#materiaPrima6CapaC" ).val(materiaPrimaText);  
                    }                
                }
            }
            if(divName == '#capa-d') {
                if(idMateriaPrima != '-1') {
                    var item = $( "#item option:selected" ).val();
                    if(item == '1') {
                        $( "#idMateriaPrima1CapaD" ).val(idMateriaPrima);  
                        $( "#materiaPrima1CapaD" ).val(materiaPrimaText);  
                    }
                    if(item == '2') {
                        $( "#idMateriaPrima2CapaD" ).val(idMateriaPrima);  
                        $( "#materiaPrima2CapaD" ).val(materiaPrimaText);  
                    }
                    if(item == '3') {
                        $( "#idMateriaPrima3CapaD" ).val(idMateriaPrima);  
                        $( "#materiaPrima3CapaD" ).val(materiaPrimaText);  
                    }
                    if(item == '4') {
                        $( "#idMateriaPrima4CapaD" ).val(idMateriaPrima);  
                        $( "#materiaPrima4CapaD" ).val(materiaPrimaText);  
                    }
                    if(item == '5') {
                        $( "#idMateriaPrima5CapaD" ).val(idMateriaPrima);  
                        $( "#materiaPrima5CapaD" ).val(materiaPrimaText);  
                    }
                    if(item == '6') {
                        $( "#idMateriaPrima6CapaD" ).val(idMateriaPrima);  
                        $( "#materiaPrima6CapaD" ).val(materiaPrimaText);  
                    }                
                }
            }
            if(divName == '#capa-e') {
                if(idMateriaPrima != '-1') {
                    var item = $( "#item option:selected" ).val();
                    if(item == '1') {
                        $( "#idMateriaPrima1CapaE" ).val(idMateriaPrima);  
                        $( "#materiaPrima1CapaE" ).val(materiaPrimaText);  
                    }
                    if(item == '2') {
                        $( "#idMateriaPrima2CapaE" ).val(idMateriaPrima);  
                        $( "#materiaPrima2CapaE" ).val(materiaPrimaText);  
                    }
                    if(item == '3') {
                        $( "#idMateriaPrima3CapaE" ).val(idMateriaPrima);  
                        $( "#materiaPrima3CapaE" ).val(materiaPrimaText);  
                    }
                    if(item == '4') {
                        $( "#idMateriaPrima4CapaE" ).val(idMateriaPrima);  
                        $( "#materiaPrima4CapaE" ).val(materiaPrimaText);  
                    }
                    if(item == '5') {
                        $( "#idMateriaPrima5CapaE" ).val(idMateriaPrima);  
                        $( "#materiaPrima5CapaE" ).val(materiaPrimaText);  
                    }
                    if(item == '6') {
                        $( "#idMateriaPrima6CapaE" ).val(idMateriaPrima);  
                        $( "#materiaPrima6CapaE" ).val(materiaPrimaText);  
                    }                
                }
            }
            if(divName == '#capa-f') {
                if(idMateriaPrima != '-1') {
                    var item = $( "#item option:selected" ).val();
                    if(item == '1') {
                        $( "#idMateriaPrima1CapaF" ).val(idMateriaPrima);  
                        $( "#materiaPrima1CapaF" ).val(materiaPrimaText);  
                    }
                    if(item == '2') {
                        $( "#idMateriaPrima2CapaF" ).val(idMateriaPrima);  
                        $( "#materiaPrima2CapaF" ).val(materiaPrimaText);  
                    }
                    if(item == '3') {
                        $( "#idMateriaPrima3CapaF" ).val(idMateriaPrima);  
                        $( "#materiaPrima3CapaF" ).val(materiaPrimaText);  
                    }
                    if(item == '4') {
                        $( "#idMateriaPrima4CapaF" ).val(idMateriaPrima);  
                        $( "#materiaPrima4CapaF" ).val(materiaPrimaText);  
                    }
                    if(item == '5') {
                        $( "#idMateriaPrima5CapaF" ).val(idMateriaPrima);  
                        $( "#materiaPrima5CapaF" ).val(materiaPrimaText);  
                    }
                    if(item == '6') {
                        $( "#idMateriaPrima6CapaF" ).val(idMateriaPrima);  
                        $( "#materiaPrima6CapaF" ).val(materiaPrimaText);  
                    }                
                }
            }
            if(divName == '#capa-g') {
                if(idMateriaPrima != '-1') {
                    var item = $( "#item option:selected" ).val();
                    if(item == '1') {
                        $( "#idMateriaPrima1CapaG" ).val(idMateriaPrima);  
                        $( "#materiaPrima1CapaG" ).val(materiaPrimaText);  
                    }
                    if(item == '2') {
                        $( "#idMateriaPrima2CapaG" ).val(idMateriaPrima);  
                        $( "#materiaPrima2CapaG" ).val(materiaPrimaText);  
                    }
                    if(item == '3') {
                        $( "#idMateriaPrima3CapaG" ).val(idMateriaPrima);  
                        $( "#materiaPrima3CapaG" ).val(materiaPrimaText);  
                    }
                    if(item == '4') {
                        $( "#idMateriaPrima4CapaG" ).val(idMateriaPrima);  
                        $( "#materiaPrima4CapaG" ).val(materiaPrimaText);  
                    }
                    if(item == '5') {
                        $( "#idMateriaPrima5CapaG" ).val(idMateriaPrima);  
                        $( "#materiaPrima5CapaG" ).val(materiaPrimaText);  
                    }
                    if(item == '6') {
                        $( "#idMateriaPrima6CapaG" ).val(idMateriaPrima);  
                        $( "#materiaPrima6CapaG" ).val(materiaPrimaText);  
                    }                
                }
            }            
        }
        
        function eliminarMateriaPrima(capa, item) {
            
            if(capa == 'A') {
                if(item == '1') {
                    $( "#idMateriaPrima1CapaA" ).val('-1');  
                    $( "#materiaPrima1CapaA" ).val('');  
                    $( "#materiaPrimaPorcentage1CapaA" ).val(''); 
                }            
                if(item == '2') {
                    $( "#idMateriaPrima2CapaA" ).val('-1');  
                    $( "#materiaPrima2CapaA" ).val('');  
                    $( "#materiaPrimaPorcentage2CapaA" ).val(''); 
                }            
                if(item == '3') {
                    $( "#idMateriaPrima3CapaA" ).val('-1');  
                    $( "#materiaPrima3CapaA" ).val('');  
                    $( "#materiaPrimaPorcentage3CapaA" ).val(''); 
                }            
                if(item == '4') {
                    $( "#idMateriaPrima4CapaA" ).val('-1');  
                    $( "#materiaPrima4CapaA" ).val('');  
                    $( "#materiaPrimaPorcentage4CapaA" ).val(''); 
                }            
                if(item == '5') {
                    $( "#idMateriaPrima5CapaA" ).val('-1');  
                    $( "#materiaPrima5CapaA" ).val('');  
                    $( "#materiaPrimaPorcentage5CapaA" ).val(''); 
                }            
                if(item == '6') {
                    $( "#idMateriaPrima6CapaA" ).val('-1');  
                    $( "#materiaPrima6CapaA" ).val('');  
                    $( "#materiaPrimaPorcentage6CapaA" ).val(''); 
                }            
            }
            if(capa == 'B') {
                if(item == '1') {
                    $( "#idMateriaPrima1CapaB" ).val('-1');  
                    $( "#materiaPrima1CapaB" ).val('');  
                    $( "#materiaPrimaPorcentage1CapaB" ).val(''); 
                }            
                if(item == '2') {
                    $( "#idMateriaPrima2CapaB" ).val('-1');  
                    $( "#materiaPrima2CapaB" ).val('');  
                    $( "#materiaPrimaPorcentage2CapaB" ).val(''); 
                }            
                if(item == '3') {
                    $( "#idMateriaPrima3CapaB" ).val('-1');  
                    $( "#materiaPrima3CapaB" ).val('');  
                    $( "#materiaPrimaPorcentage3CapaB" ).val(''); 
                }            
                if(item == '4') {
                    $( "#idMateriaPrima4CapaB" ).val('-1');  
                    $( "#materiaPrima4CapaB" ).val('');  
                    $( "#materiaPrimaPorcentage4CapaB" ).val(''); 
                }            
                if(item == '5') {
                    $( "#idMateriaPrima5CapaB" ).val('-1');  
                    $( "#materiaPrima5CapaB" ).val('');  
                    $( "#materiaPrimaPorcentage5CapaB" ).val(''); 
                }            
                if(item == '6') {
                    $( "#idMateriaPrima6CapaB" ).val('-1');  
                    $( "#materiaPrima6CapaB" ).val('');  
                    $( "#materiaPrimaPorcentage6CapaB" ).val(''); 
                }            
            }
            if(capa == 'C') {
                if(item == '1') {
                    $( "#idMateriaPrima1CapaC" ).val('-1');  
                    $( "#materiaPrima1CapaC" ).val('');  
                    $( "#materiaPrimaPorcentage1CapaC" ).val(''); 
                }            
                if(item == '2') {
                    $( "#idMateriaPrima2CapaC" ).val('-1');  
                    $( "#materiaPrima2CapaC" ).val('');  
                    $( "#materiaPrimaPorcentage2CapaC" ).val(''); 
                }            
                if(item == '3') {
                    $( "#idMateriaPrima3CapaC" ).val('-1');  
                    $( "#materiaPrima3CapaC" ).val('');  
                    $( "#materiaPrimaPorcentage3CapaC" ).val(''); 
                }            
                if(item == '4') {
                    $( "#idMateriaPrima4CapaC" ).val('-1');  
                    $( "#materiaPrima4CapaC" ).val('');  
                    $( "#materiaPrimaPorcentage4CapaC" ).val(''); 
                }            
                if(item == '5') {
                    $( "#idMateriaPrima5CapaC" ).val('-1');  
                    $( "#materiaPrima5CapaC" ).val('');  
                    $( "#materiaPrimaPorcentage5CapaC" ).val(''); 
                }            
                if(item == '6') {
                    $( "#idMateriaPrima6CapaC" ).val('-1');  
                    $( "#materiaPrima6CapaC" ).val('');  
                    $( "#materiaPrimaPorcentage6CapaC" ).val(''); 
                }            
            }
            if(capa == 'D') {
                if(item == '1') {
                    $( "#idMateriaPrima1CapaD" ).val('-1');  
                    $( "#materiaPrima1CapaD" ).val('');  
                    $( "#materiaPrimaPorcentage1CapaD" ).val(''); 
                }            
                if(item == '2') {
                    $( "#idMateriaPrima2CapaD" ).val('-1');  
                    $( "#materiaPrima2CapaD" ).val('');  
                    $( "#materiaPrimaPorcentage2CapaD" ).val(''); 
                }            
                if(item == '3') {
                    $( "#idMateriaPrima3CapaD" ).val('-1');  
                    $( "#materiaPrima3CapaD" ).val('');  
                    $( "#materiaPrimaPorcentage3CapaD" ).val(''); 
                }            
                if(item == '4') {
                    $( "#idMateriaPrima4CapaD" ).val('-1');  
                    $( "#materiaPrima4CapaD" ).val('');  
                    $( "#materiaPrimaPorcentage4CapaD" ).val(''); 
                }            
                if(item == '5') {
                    $( "#idMateriaPrima5CapaD" ).val('-1');  
                    $( "#materiaPrima5CapaD" ).val('');  
                    $( "#materiaPrimaPorcentage5CapaD" ).val(''); 
                }            
                if(item == '6') {
                    $( "#idMateriaPrima6CapaD" ).val('-1');  
                    $( "#materiaPrima6CapaD" ).val('');  
                    $( "#materiaPrimaPorcentage6CapaD" ).val(''); 
                }            
            }
            if(capa == 'E') {
                if(item == '1') {
                    $( "#idMateriaPrima1CapaE" ).val('-1');  
                    $( "#materiaPrima1CapaE" ).val('');  
                    $( "#materiaPrimaPorcentage1CapaE" ).val(''); 
                }            
                if(item == '2') {
                    $( "#idMateriaPrima2CapaE" ).val('-1');  
                    $( "#materiaPrima2CapaE" ).val('');  
                    $( "#materiaPrimaPorcentage2CapaE" ).val(''); 
                }            
                if(item == '3') {
                    $( "#idMateriaPrima3CapaE" ).val('-1');  
                    $( "#materiaPrima3CapaE" ).val('');  
                    $( "#materiaPrimaPorcentage3CapaE" ).val(''); 
                }            
                if(item == '4') {
                    $( "#idMateriaPrima4CapaE" ).val('-1');  
                    $( "#materiaPrima4CapaE" ).val('');  
                    $( "#materiaPrimaPorcentage4CapaE" ).val(''); 
                }            
                if(item == '5') {
                    $( "#idMateriaPrima5CapaE" ).val('-1');  
                    $( "#materiaPrima5CapaE" ).val('');  
                    $( "#materiaPrimaPorcentage5CapaE" ).val(''); 
                }            
                if(item == '6') {
                    $( "#idMateriaPrima6CapaE" ).val('-1');  
                    $( "#materiaPrima6CapaE" ).val('');  
                    $( "#materiaPrimaPorcentage6CapaE" ).val(''); 
                }            
            }
            if(capa == 'F') {
                if(item == '1') {
                    $( "#idMateriaPrima1CapaF" ).val('-1');  
                    $( "#materiaPrima1CapaF" ).val('');  
                    $( "#materiaPrimaPorcentage1CapaF" ).val(''); 
                }            
                if(item == '2') {
                    $( "#idMateriaPrima2CapaF" ).val('-1');  
                    $( "#materiaPrima2CapaF" ).val('');  
                    $( "#materiaPrimaPorcentage2CapaF" ).val(''); 
                }            
                if(item == '3') {
                    $( "#idMateriaPrima3CapaF" ).val('-1');  
                    $( "#materiaPrima3CapaF" ).val('');  
                    $( "#materiaPrimaPorcentage3CapaF" ).val(''); 
                }            
                if(item == '4') {
                    $( "#idMateriaPrima4CapaF" ).val('-1');  
                    $( "#materiaPrima4CapaF" ).val('');  
                    $( "#materiaPrimaPorcentage4CapaF" ).val(''); 
                }            
                if(item == '5') {
                    $( "#idMateriaPrima5CapaF" ).val('-1');  
                    $( "#materiaPrima5CapaF" ).val('');  
                    $( "#materiaPrimaPorcentage5CapaF" ).val(''); 
                }            
                if(item == '6') {
                    $( "#idMateriaPrima6CapaF" ).val('-1');  
                    $( "#materiaPrima6CapaF" ).val('');  
                    $( "#materiaPrimaPorcentage6CapaF" ).val(''); 
                }            
            }
            if(capa == 'G') {
                if(item == '1') {
                    $( "#idMateriaPrima1CapaG" ).val('-1');  
                    $( "#materiaPrima1CapaG" ).val('');  
                    $( "#materiaPrimaPorcentage1CapaG" ).val(''); 
                }            
                if(item == '2') {
                    $( "#idMateriaPrima2CapaG" ).val('-1');  
                    $( "#materiaPrima2CapaG" ).val('');  
                    $( "#materiaPrimaPorcentage2CapaG" ).val(''); 
                }            
                if(item == '3') {
                    $( "#idMateriaPrima3CapaG" ).val('-1');  
                    $( "#materiaPrima3CapaG" ).val('');  
                    $( "#materiaPrimaPorcentage3CapaG" ).val(''); 
                }            
                if(item == '4') {
                    $( "#idMateriaPrima4CapaG" ).val('-1');  
                    $( "#materiaPrima4CapaG" ).val('');  
                    $( "#materiaPrimaPorcentage4CapaG" ).val(''); 
                }            
                if(item == '5') {
                    $( "#idMateriaPrima5CapaG" ).val('-1');  
                    $( "#materiaPrima5CapaG" ).val('');  
                    $( "#materiaPrimaPorcentage5CapaG" ).val(''); 
                }            
                if(item == '6') {
                    $( "#idMateriaPrima6CapaG" ).val('-1');  
                    $( "#materiaPrima6CapaG" ).val('');  
                    $( "#materiaPrimaPorcentage6CapaG" ).val(''); 
                }            
            }            
            event.preventDefault();
        }
        
        function calcularMaterialesUtilizados(){
            
            const materiales = new Map();
            const nombreMateriales = new Map();
                        
            //CAPA A
            var idMateriaPrima1CapaA = $( "#idMateriaPrima1CapaA" ).val();
            if(idMateriaPrima1CapaA != undefined && idMateriaPrima1CapaA != '' && idMateriaPrima1CapaA != -1) {
                var materiaPrima1CapaA = $( "#materiaPrima1CapaA" ).val();
                nombreMateriales.set(idMateriaPrima1CapaA, materiaPrima1CapaA);
                var materiaPrimaPorcentage1CapaA = $( "#materiaPrimaPorcentage1CapaA" ).val();
                try {
                    if(!isNaN(materiaPrimaPorcentage1CapaA) && parseFloat(materiaPrimaPorcentage1CapaA) > 0) {
                        var resumenCapaA = $( "#resumenCapaA" ).val();
                        if(!isNaN(resumenCapaA) && parseFloat(resumenCapaA) > 0) {
                            var p = 0.0;
                            if(materiales.get(idMateriaPrima1CapaA) != undefined) {
                                p = materiales.get(idMateriaPrima1CapaA);
                            }
                            var value = (parseFloat(materiaPrimaPorcentage1CapaA) * parseFloat(resumenCapaA) / 100.0) + p;
                            materiales.set(idMateriaPrima1CapaA, value);
                        }
                    }
                } catch(error) {
                    console.error(error);
                }
            }            
            
            var idMateriaPrima2CapaA = $( "#idMateriaPrima2CapaA" ).val();
            if(idMateriaPrima2CapaA != undefined && idMateriaPrima2CapaA != '' && idMateriaPrima2CapaA != -1) {
                var materiaPrima2CapaA = $( "#materiaPrima2CapaA" ).val();
                nombreMateriales.set(idMateriaPrima2CapaA, materiaPrima2CapaA);                
                var materiaPrimaPorcentage2CapaA = $( "#materiaPrimaPorcentage2CapaA" ).val();
                try {
                    if(!isNaN(materiaPrimaPorcentage2CapaA) && parseFloat(materiaPrimaPorcentage2CapaA) > 0) {
                        var resumenCapaA = $( "#resumenCapaA" ).val();
                        if(!isNaN(resumenCapaA) && parseFloat(resumenCapaA) > 0) {
                            var p = 0.0;
                            if(materiales.get(idMateriaPrima2CapaA) != undefined) {
                                p = materiales.get(idMateriaPrima2CapaA);
                            }
                            var value = (parseFloat(materiaPrimaPorcentage2CapaA) * parseFloat(resumenCapaA) / 100.0) + p;
                            materiales.set(idMateriaPrima2CapaA, value);
                        }
                    }
                } catch(error) {
                    console.error(error);
                }
            }
            
            var idMateriaPrima3CapaA = $( "#idMateriaPrima3CapaA" ).val();
            if(idMateriaPrima3CapaA != undefined && idMateriaPrima3CapaA != '' && idMateriaPrima3CapaA != -1) {
                var materiaPrima3CapaA = $( "#materiaPrima3CapaA" ).val();
                nombreMateriales.set(idMateriaPrima3CapaA, materiaPrima3CapaA);                
                var materiaPrimaPorcentage3CapaA = $( "#materiaPrimaPorcentage3CapaA" ).val();
                try {
                    if(!isNaN(materiaPrimaPorcentage3CapaA) && parseFloat(materiaPrimaPorcentage3CapaA) > 0) {
                        var resumenCapaA = $( "#resumenCapaA" ).val();
                        if(!isNaN(resumenCapaA) && parseFloat(resumenCapaA) > 0) {
                            var p = 0.0;
                            if(materiales.get(idMateriaPrima3CapaA) != undefined) {
                                p = materiales.get(idMateriaPrima3CapaA);
                            }
                            var value = (parseFloat(materiaPrimaPorcentage3CapaA) * parseFloat(resumenCapaA) / 100.0) + p;
                            materiales.set(idMateriaPrima3CapaA, value);
                        }
                    }
                } catch(error) {
                    console.error(error);
                }
            }

            var idMateriaPrima4CapaA = $( "#idMateriaPrima4CapaA" ).val();
            if(idMateriaPrima4CapaA != undefined && idMateriaPrima4CapaA != '' && idMateriaPrima4CapaA != -1) {
                var materiaPrima4CapaA = $( "#materiaPrima4CapaA" ).val();
                nombreMateriales.set(idMateriaPrima4CapaA, materiaPrima4CapaA);                
                var materiaPrimaPorcentage4CapaA = $( "#materiaPrimaPorcentage4CapaA" ).val();
                try {
                    if(!isNaN(materiaPrimaPorcentage4CapaA) && parseFloat(materiaPrimaPorcentage4CapaA) > 0) {
                        var resumenCapaA = $( "#resumenCapaA" ).val();
                        if(!isNaN(resumenCapaA) && parseFloat(resumenCapaA) > 0) { 
                            var p = 0.0;
                            if(materiales.get(idMateriaPrima4CapaA) != undefined) {
                                p = materiales.get(idMateriaPrima4CapaA);
                            }
                            var value = (parseFloat(materiaPrimaPorcentage4CapaA) * parseFloat(resumenCapaA) / 100.0) + p;
                            materiales.set(idMateriaPrima4CapaA, value);
                        }
                    }
                } catch(error) {
                    console.error(error);
                }
            }

            var idMateriaPrima5CapaA = $( "#idMateriaPrima5CapaA" ).val();
            if(idMateriaPrima5CapaA != undefined && idMateriaPrima5CapaA != '' && idMateriaPrima5CapaA != -1) {
                var materiaPrima5CapaA = $( "#materiaPrima5CapaA" ).val();
                nombreMateriales.set(idMateriaPrima5CapaA, materiaPrima5CapaA);                                
                var materiaPrimaPorcentage5CapaA = $( "#materiaPrimaPorcentage5CapaA" ).val();
                try {
                    if(!isNaN(materiaPrimaPorcentage5CapaA) && parseFloat(materiaPrimaPorcentage5CapaA) > 0) {
                        var resumenCapaA = $( "#resumenCapaA" ).val();
                        if(!isNaN(resumenCapaA) && parseFloat(resumenCapaA) > 0) {
                            var p = 0.0;
                            if(materiales.get(idMateriaPrima5CapaA) != undefined) {
                                p = materiales.get(idMateriaPrima5CapaA);
                            }
                            var value = (parseFloat(materiaPrimaPorcentage5CapaA) * parseFloat(resumenCapaA) / 100.0) + p;
                            materiales.set(idMateriaPrima5CapaA, value);
                        }
                    }
                } catch(error) {
                    console.error(error);
                }
            }

            var idMateriaPrima6CapaA = $( "#idMateriaPrima6CapaA" ).val();
            if(idMateriaPrima6CapaA != undefined && idMateriaPrima6CapaA != '' && idMateriaPrima6CapaA != -1) {
                var materiaPrima6CapaA = $( "#materiaPrima6CapaA" ).val();
                nombreMateriales.set(idMateriaPrima6CapaA, materiaPrima6CapaA);                
                var materiaPrimaPorcentage6CapaA = $( "#materiaPrimaPorcentage6CapaA" ).val();
                try {
                    if(!isNaN(materiaPrimaPorcentage6CapaA) && parseFloat(materiaPrimaPorcentage6CapaA) > 0) {
                        var resumenCapaA = $( "#resumenCapaA" ).val();
                        if(!isNaN(resumenCapaA) && parseFloat(resumenCapaA) > 0) {
                            var p = 0.0;
                            if(materiales.get(idMateriaPrima6CapaA) != undefined) {
                                p = materiales.get(idMateriaPrima6CapaA);
                            }
                            var value = (parseFloat(materiaPrimaPorcentage6CapaA) * parseFloat(resumenCapaA) / 100.0) + p;
                            materiales.set(idMateriaPrima6CapaA, value);
                        }
                    }
                } catch(error) {
                    console.error(error);
                }
            }
        
            var cantidadCapas = $('#cantidadCapas').val();
            var cantidadCapasInt = parseInt(cantidadCapas);
            
            if(cantidadCapasInt > 1) {        
                //CAPA B
                var idMateriaPrima1CapaB = $( "#idMateriaPrima1CapaB" ).val();
                if(idMateriaPrima1CapaB != undefined && idMateriaPrima1CapaB != '' && idMateriaPrima1CapaB != -1) {
                    var materiaPrima1CapaB = $( "#materiaPrima1CapaB" ).val();
                    nombreMateriales.set(idMateriaPrima1CapaB, materiaPrima1CapaB);                                    
                    var materiaPrimaPorcentage1CapaB = $( "#materiaPrimaPorcentage1CapaB" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage1CapaB) && parseFloat(materiaPrimaPorcentage1CapaB) > 0) {
                            var resumenCapaB = $( "#resumenCapaB" ).val();
                            alert('resumenCapaB');
                            if(!isNaN(resumenCapaB) && parseFloat(resumenCapaB) > 0) {
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima1CapaB) != undefined) {
                                    p = materiales.get(idMateriaPrima1CapaB);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage1CapaB) * parseFloat(resumenCapaB) / 100.0) + p;
                                materiales.set(idMateriaPrima1CapaB, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }            

                var idMateriaPrima2CapaB = $( "#idMateriaPrima2CapaB" ).val();
                if(idMateriaPrima2CapaB != undefined && idMateriaPrima2CapaB != '' && idMateriaPrima2CapaB != -1) {
                    var materiaPrima2CapaB = $( "#materiaPrima2CapaB" ).val();
                    nombreMateriales.set(idMateriaPrima2CapaB, materiaPrima2CapaB);                                                        
                    var materiaPrimaPorcentage2CapaB = $( "#materiaPrimaPorcentage2CapaB" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage2CapaB) && parseFloat(materiaPrimaPorcentage2CapaB) > 0) {
                            var resumenCapaB = $( "#resumenCapaB" ).val();
                            if(!isNaN(resumenCapaB) && parseFloat(resumenCapaB) > 0) {
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima2CapaB) != undefined) {
                                    p = materiales.get(idMateriaPrima2CapaB);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage2CapaB) * parseFloat(resumenCapaB) / 100.0) + p;
                                materiales.set(idMateriaPrima2CapaB, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }

                var idMateriaPrima3CapaB = $( "#idMateriaPrima3CapaB" ).val();
                if(idMateriaPrima3CapaB != undefined && idMateriaPrima3CapaB != '' && idMateriaPrima3CapaB != -1) {
                    var materiaPrima3CapaB = $( "#materiaPrima3CapaB" ).val();
                    nombreMateriales.set(idMateriaPrima3CapaB, materiaPrima3CapaB);                                                        
                    var materiaPrimaPorcentage3CapaB = $( "#materiaPrimaPorcentage3CapaB" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage3CapaB) && parseFloat(materiaPrimaPorcentage3CapaB) > 0) {
                            var resumenCapaB = $( "#resumenCapaB" ).val();
                            if(!isNaN(resumenCapaB) && parseFloat(resumenCapaB) > 0) {
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima3CapaB) != undefined) {
                                    p = materiales.get(idMateriaPrima3CapaB);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage3CapaB) * parseFloat(resumenCapaB) / 100.0) + p;
                                materiales.set(idMateriaPrima3CapaB, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }

                var idMateriaPrima4CapaB = $( "#idMateriaPrima4CapaB" ).val();
                if(idMateriaPrima4CapaB != undefined && idMateriaPrima4CapaB != '' && idMateriaPrima4CapaB != -1) {
                    var materiaPrima4CapaB = $( "#materiaPrima4CapaB" ).val();
                    nombreMateriales.set(idMateriaPrima4CapaB, materiaPrima4CapaB);                                                                            
                    var materiaPrimaPorcentage4CapaB = $( "#materiaPrimaPorcentage4CapaB" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage4CapaB) && parseFloat(materiaPrimaPorcentage4CapaB) > 0) {
                            var resumenCapaB = $( "#resumenCapaB" ).val();
                            if(!isNaN(resumenCapaB) && parseFloat(resumenCapaB) > 0) { 
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima4CapaB) != undefined) {
                                    p = materiales.get(idMateriaPrima4CapaB);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage4CapaB) * parseFloat(resumenCapaB) / 100.0) + p;
                                materiales.set(idMateriaPrima4CapaB, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }

                var idMateriaPrima5CapaB = $( "#idMateriaPrima5CapaB" ).val();
                if(idMateriaPrima5CapaB != undefined && idMateriaPrima5CapaB != '' && idMateriaPrima5CapaB != -1) {
                    var materiaPrima5CapaB = $( "#materiaPrima5CapaB" ).val();
                    nombreMateriales.set(idMateriaPrima5CapaB, materiaPrima5CapaB);                                                                                                
                    var materiaPrimaPorcentage5CapaB = $( "#materiaPrimaPorcentage5CapaB" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage5CapaB) && parseFloat(materiaPrimaPorcentage5CapaB) > 0) {
                            var resumenCapaB = $( "#resumenCapaB" ).val();
                            if(!isNaN(resumenCapaB) && parseFloat(resumenCapaB) > 0) {
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima5CapaB) != undefined) {
                                    p = materiales.get(idMateriaPrima5CapaB);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage5CapaB) * parseFloat(resumenCapaB) / 100.0) + p;
                                materiales.set(idMateriaPrima5CapaB, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }

                var idMateriaPrima6CapaB = $( "#idMateriaPrima6CapaB" ).val();
                if(idMateriaPrima6CapaB != undefined && idMateriaPrima6CapaB != '' && idMateriaPrima6CapaB != -1) {
                    var materiaPrima6CapaB = $( "#materiaPrima6CapaB" ).val();
                    nombreMateriales.set(idMateriaPrima6CapaB, materiaPrima6CapaB);                                                                                                
                    var materiaPrimaPorcentage6CapaB = $( "#materiaPrimaPorcentage6CapaB" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage6CapaB) && parseFloat(materiaPrimaPorcentage6CapaB) > 0) {
                            var resumenCapaB = $( "#resumenCapaB" ).val();
                            if(!isNaN(resumenCapaB) && parseFloat(resumenCapaB) > 0) {
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima6CapaB) != undefined) {
                                    p = materiales.get(idMateriaPrima6CapaB);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage6CapaB) * parseFloat(resumenCapaB) / 100.0) + p;
                                materiales.set(idMateriaPrima6CapaB, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }
            }
            
            if(cantidadCapasInt > 2) {        
                //CAPA C
                var idMateriaPrima1CapaC = $( "#idMateriaPrima1CapaC" ).val();
                if(idMateriaPrima1CapaC != undefined && idMateriaPrima1CapaC != '' && idMateriaPrima1CapaC != -1) {
                    var materiaPrima1CapaC = $( "#materiaPrima1CapaC" ).val();
                    nombreMateriales.set(idMateriaPrima1CapaC, materiaPrima1CapaC);                                                                                                
                    var materiaPrimaPorcentage1CapaC = $( "#materiaPrimaPorcentage1CapaC" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage1CapaC) && parseFloat(materiaPrimaPorcentage1CapaC) > 0) {
                            var resumenCapaC = $( "#resumenCapaC" ).val();
                            if(!isNaN(resumenCapaC) && parseFloat(resumenCapaC) > 0) {
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima1CapaC) != undefined) {
                                    p = materiales.get(idMateriaPrima1CapaC);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage1CapaC) * parseFloat(resumenCapaC) / 100.0) + p;
                                materiales.set(idMateriaPrima1CapaC, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }            

                var idMateriaPrima2CapaC = $( "#idMateriaPrima2CapaC" ).val();
                if(idMateriaPrima2CapaC != undefined && idMateriaPrima2CapaC != '' && idMateriaPrima2CapaC != -1) {
                    var materiaPrima2CapaC = $( "#materiaPrima2CapaC" ).val();
                    nombreMateriales.set(idMateriaPrima2CapaC, materiaPrima2CapaC);                                                                                                                    
                    var materiaPrimaPorcentage2CapaC = $( "#materiaPrimaPorcentage2CapaC" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage2CapaC) && parseFloat(materiaPrimaPorcentage2CapaC) > 0) {
                            var resumenCapaC = $( "#resumenCapaC" ).val();
                            if(!isNaN(resumenCapaC) && parseFloat(resumenCapaC) > 0) {
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima2CapaC) != undefined) {
                                    p = materiales.get(idMateriaPrima2CapaC);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage2CapaC) * parseFloat(resumenCapaC) / 100.0) + p;
                                materiales.set(idMateriaPrima2CapaC, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }

                var idMateriaPrima3CapaC = $( "#idMateriaPrima3CapaC" ).val();
                if(idMateriaPrima3CapaC != undefined && idMateriaPrima3CapaC != '' && idMateriaPrima3CapaC != -1) {
                    var materiaPrima3CapaC = $( "#materiaPrima3CapaC" ).val();
                    nombreMateriales.set(idMateriaPrima3CapaC, materiaPrima3CapaC);                                                                                                                    
                    var materiaPrimaPorcentage3CapaC = $( "#materiaPrimaPorcentage3CapaC" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage3CapaC) && parseFloat(materiaPrimaPorcentage3CapaC) > 0) {
                            var resumenCapaC = $( "#resumenCapaC" ).val();
                            if(!isNaN(resumenCapaC) && parseFloat(resumenCapaC) > 0) {
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima3CapaC) != undefined) {
                                    p = materiales.get(idMateriaPrima3CapaC);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage3CapaC) * parseFloat(resumenCapaC) / 100.0) + p;
                                materiales.set(idMateriaPrima3CapaC, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }

                var idMateriaPrima4CapaC = $( "#idMateriaPrima4CapaC" ).val();
                if(idMateriaPrima4CapaC != undefined && idMateriaPrima4CapaC != '' && idMateriaPrima4CapaC != -1) {
                    var materiaPrima4CapaC = $( "#materiaPrima4CapaC" ).val();
                    nombreMateriales.set(idMateriaPrima4CapaC, materiaPrima4CapaC);                                                                                                                    
                    var materiaPrimaPorcentage4CapaC = $( "#materiaPrimaPorcentage4CapaC" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage4CapaC) && parseFloat(materiaPrimaPorcentage4CapaC) > 0) {
                            var resumenCapaC = $( "#resumenCapaC" ).val();
                            if(!isNaN(resumenCapaC) && parseFloat(resumenCapaC) > 0) { 
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima4CapaC) != undefined) {
                                    p = materiales.get(idMateriaPrima4CapaC);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage4CapaC) * parseFloat(resumenCapaC) / 100.0) + p;
                                materiales.set(idMateriaPrima4CapaC, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }

                var idMateriaPrima5CapaC = $( "#idMateriaPrima5CapaC" ).val();
                if(idMateriaPrima5CapaC != undefined && idMateriaPrima5CapaC != '' && idMateriaPrima5CapaC != -1) {
                    var materiaPrima5CapaC = $( "#materiaPrima5CapaC" ).val();
                    nombreMateriales.set(idMateriaPrima5CapaC, materiaPrima5CapaC);                                                                                                                                        
                    var materiaPrimaPorcentage5CapaC = $( "#materiaPrimaPorcentage5CapaC" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage5CapaC) && parseFloat(materiaPrimaPorcentage5CapaC) > 0) {
                            var resumenCapaC = $( "#resumenCapaC" ).val();
                            if(!isNaN(resumenCapaC) && parseFloat(resumenCapaC) > 0) {
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima5CapaC) != undefined) {
                                    p = materiales.get(idMateriaPrima5CapaC);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage5CapaC) * parseFloat(resumenCapaC) / 100.0) + p;
                                materiales.set(idMateriaPrima5CapaC, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }

                var idMateriaPrima6CapaC = $( "#idMateriaPrima6CapaC" ).val();
                if(idMateriaPrima6CapaC != undefined && idMateriaPrima6CapaC != '' && idMateriaPrima6CapaC != -1) {
                    var materiaPrima6CapaC = $( "#materiaPrima6CapaC" ).val();
                    nombreMateriales.set(idMateriaPrima6CapaC, materiaPrima6CapaC);                                                                                                                                                            
                    var materiaPrimaPorcentage6CapaC = $( "#materiaPrimaPorcentage6CapaC" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage6CapaC) && parseFloat(materiaPrimaPorcentage6CapaC) > 0) {
                            var resumenCapaC = $( "#resumenCapaC" ).val();
                            if(!isNaN(resumenCapaC) && parseFloat(resumenCapaC) > 0) {
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima6CapaC) != undefined) {
                                    p = materiales.get(idMateriaPrima6CapaC);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage6CapaC) * parseFloat(resumenCapaC) / 100.0) + p;
                                materiales.set(idMateriaPrima6CapaC, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }
            } 
            
            if(cantidadCapasInt > 3) {        
                //CAPA D
                var idMateriaPrima1CapaD = $( "#idMateriaPrima1CapaD" ).val();
                if(idMateriaPrima1CapaD != undefined && idMateriaPrima1CapaD != '' && idMateriaPrima1CapaD != -1) {
                    var materiaPrima1CapaD = $( "#materiaPrima1CapaD" ).val();
                    nombreMateriales.set(idMateriaPrima1CapaD, materiaPrima1CapaD);                                                                                                                                                            
                    var materiaPrimaPorcentage1CapaD = $( "#materiaPrimaPorcentage1CapaD" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage1CapaD) && parseFloat(materiaPrimaPorcentage1CapaD) > 0) {
                            var resumenCapaD = $( "#resumenCapaD" ).val();
                            if(!isNaN(resumenCapaD) && parseFloat(resumenCapaD) > 0) {
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima1CapaD) != undefined) {
                                    p = materiales.get(idMateriaPrima1CapaD);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage1CapaD) * parseFloat(resumenCapaD) / 100.0) + p;
                                materiales.set(idMateriaPrima1CapaD, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }            

                var idMateriaPrima2CapaD = $( "#idMateriaPrima2CapaD" ).val();
                if(idMateriaPrima2CapaD != undefined && idMateriaPrima2CapaD != '' && idMateriaPrima2CapaD != -1) {
                    var materiaPrima2CapaD = $( "#materiaPrima2CapaD" ).val();
                    nombreMateriales.set(idMateriaPrima2CapaD, materiaPrima2CapaD);                                                                                                                                                                                
                    var materiaPrimaPorcentage2CapaD = $( "#materiaPrimaPorcentage2CapaD" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage2CapaD) && parseFloat(materiaPrimaPorcentage2CapaD) > 0) {
                            var resumenCapaD = $( "#resumenCapaD" ).val();
                            if(!isNaN(resumenCapaD) && parseFloat(resumenCapaD) > 0) {
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima2CapaD) != undefined) {
                                    p = materiales.get(idMateriaPrima2CapaD);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage2CapaD) * parseFloat(resumenCapaD) / 100.0) + p;
                                materiales.set(idMateriaPrima2CapaD, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }

                var idMateriaPrima3CapaD = $( "#idMateriaPrima3CapaD" ).val();
                if(idMateriaPrima3CapaD != undefined && idMateriaPrima3CapaD != '' && idMateriaPrima3CapaD != -1) {
                    var materiaPrima3CapaD = $( "#materiaPrima3CapaD" ).val();
                    nombreMateriales.set(idMateriaPrima3CapaD, materiaPrima3CapaD);                                                                                                                                                                                                    
                    var materiaPrimaPorcentage3CapaD = $( "#materiaPrimaPorcentage3CapaD" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage3CapaD) && parseFloat(materiaPrimaPorcentage3CapaD) > 0) {
                            var resumenCapaD = $( "#resumenCapaD" ).val();
                            if(!isNaN(resumenCapaD) && parseFloat(resumenCapaD) > 0) {
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima3CapaD) != undefined) {
                                    p = materiales.get(idMateriaPrima3CapaD);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage3CapaD) * parseFloat(resumenCapaD) / 100.0) + p;
                                materiales.set(idMateriaPrima3CapaD, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }

                var idMateriaPrima4CapaD = $( "#idMateriaPrima4CapaD" ).val();
                if(idMateriaPrima4CapaD != undefined && idMateriaPrima4CapaD != '' && idMateriaPrima4CapaD != -1) {
                    var materiaPrima4CapaD = $( "#materiaPrima4CapaD" ).val();
                    nombreMateriales.set(idMateriaPrima4CapaD, materiaPrima4CapaD);                                                                                                                                                                                                    
                    var materiaPrimaPorcentage4CapaD = $( "#materiaPrimaPorcentage4CapaD" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage4CapaD) && parseFloat(materiaPrimaPorcentage4CapaD) > 0) {
                            var resumenCapaD = $( "#resumenCapaD" ).val();
                            if(!isNaN(resumenCapaD) && parseFloat(resumenCapaD) > 0) { 
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima4CapaD) != undefined) {
                                    p = materiales.get(idMateriaPrima4CapaD);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage4CapaD) * parseFloat(resumenCapaD) / 100.0) + p;
                                materiales.set(idMateriaPrima4CapaD, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }

                var idMateriaPrima5CapaD = $( "#idMateriaPrima5CapaD" ).val();
                if(idMateriaPrima5CapaD != undefined && idMateriaPrima5CapaD != '' && idMateriaPrima5CapaD != -1) {
                    var materiaPrima5CapaD = $( "#materiaPrima5CapaD" ).val();
                    nombreMateriales.set(idMateriaPrima5CapaD, materiaPrima5CapaD);                                                                                                                                                                                                    
                    var materiaPrimaPorcentage5CapaD = $( "#materiaPrimaPorcentage5CapaD" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage5CapaD) && parseFloat(materiaPrimaPorcentage5CapaD) > 0) {
                            var resumenCapaD = $( "#resumenCapaD" ).val();
                            if(!isNaN(resumenCapaD) && parseFloat(resumenCapaD) > 0) {
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima5CapaD) != undefined) {
                                    p = materiales.get(idMateriaPrima5CapaD);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage5CapaD) * parseFloat(resumenCapaD) / 100.0) + p;
                                materiales.set(idMateriaPrima5CapaD, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }

                var idMateriaPrima6CapaD = $( "#idMateriaPrima6CapaD" ).val();
                if(idMateriaPrima6CapaD != undefined && idMateriaPrima6CapaD != '' && idMateriaPrima6CapaD != -1) {
                    var materiaPrima6CapaD = $( "#materiaPrima6CapaD" ).val();
                    nombreMateriales.set(idMateriaPrima6CapaD, materiaPrima6CapaD);                    
                    var materiaPrimaPorcentage6CapaD = $( "#materiaPrimaPorcentage6CapaD" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage6CapaD) && parseFloat(materiaPrimaPorcentage6CapaD) > 0) {
                            var resumenCapaD = $( "#resumenCapaD" ).val();
                            if(!isNaN(resumenCapaD) && parseFloat(resumenCapaD) > 0) {
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima6CapaD) != undefined) {
                                    p = materiales.get(idMateriaPrima6CapaD);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage6CapaD) * parseFloat(resumenCapaD) / 100.0) + p;
                                materiales.set(idMateriaPrima6CapaD, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }
            } 
            
            if(cantidadCapasInt > 4) {        
                //CAPA E
                var idMateriaPrima1CapaE = $( "#idMateriaPrima1CapaE" ).val();
                if(idMateriaPrima1CapaE != undefined && idMateriaPrima1CapaE != '' && idMateriaPrima1CapaE != -1) {
                    var materiaPrima1CapaE = $( "#materiaPrima1CapaE" ).val();
                    nombreMateriales.set(idMateriaPrima1CapaE, materiaPrima1CapaE);
                    var materiaPrimaPorcentage1CapaE = $( "#materiaPrimaPorcentage1CapaE" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage1CapaE) && parseFloat(materiaPrimaPorcentage1CapaE) > 0) {
                            var resumenCapaE = $( "#resumenCapaE" ).val();
                            if(!isNaN(resumenCapaE) && parseFloat(resumenCapaE) > 0) {
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima1CapaE) != undefined) {
                                    p = materiales.get(idMateriaPrima1CapaE);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage1CapaE) * parseFloat(resumenCapaE) / 100.0) + p;
                                materiales.set(idMateriaPrima1CapaE, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }            

                var idMateriaPrima2CapaE = $( "#idMateriaPrima2CapaE" ).val();
                if(idMateriaPrima2CapaE != undefined && idMateriaPrima2CapaE != '' && idMateriaPrima2CapaE != -1) {
                    var materiaPrima2CapaE = $( "#materiaPrima2CapaE" ).val();
                    nombreMateriales.set(idMateriaPrima2CapaE, materiaPrima2CapaE);                    
                    var materiaPrimaPorcentage2CapaE = $( "#materiaPrimaPorcentage2CapaE" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage2CapaE) && parseFloat(materiaPrimaPorcentage2CapaE) > 0) {
                            var resumenCapaE = $( "#resumenCapaE" ).val();
                            if(!isNaN(resumenCapaE) && parseFloat(resumenCapaE) > 0) {
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima2CapaE) != undefined) {
                                    p = materiales.get(idMateriaPrima2CapaE);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage2CapaE) * parseFloat(resumenCapaE) / 100.0) + p;
                                materiales.set(idMateriaPrima2CapaE, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }

                var idMateriaPrima3CapaE = $( "#idMateriaPrima3CapaE" ).val();
                if(idMateriaPrima3CapaE != undefined && idMateriaPrima3CapaE != '' && idMateriaPrima3CapaE != -1) {
                    var materiaPrima3CapaE = $( "#materiaPrima3CapaE" ).val();
                    nombreMateriales.set(idMateriaPrima3CapaE, materiaPrima3CapaE);                    
                    var materiaPrimaPorcentage3CapaE = $( "#materiaPrimaPorcentage3CapaE" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage3CapaE) && parseFloat(materiaPrimaPorcentage3CapaE) > 0) {
                            var resumenCapaE = $( "#resumenCapaE" ).val();
                            if(!isNaN(resumenCapaE) && parseFloat(resumenCapaE) > 0) {
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima3CapaE) != undefined) {
                                    p = materiales.get(idMateriaPrima3CapaE);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage3CapaE) * parseFloat(resumenCapaE) / 100.0) + p;
                                materiales.set(idMateriaPrima3CapaE, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }

                var idMateriaPrima4CapaE = $( "#idMateriaPrima4CapaE" ).val();
                if(idMateriaPrima4CapaE != undefined && idMateriaPrima4CapaE != '' && idMateriaPrima4CapaE != -1) {
                    var materiaPrima4CapaE = $( "#materiaPrima4CapaE" ).val();
                    nombreMateriales.set(idMateriaPrima4CapaE, materiaPrima4CapaE);                    
                    var materiaPrimaPorcentage4CapaE = $( "#materiaPrimaPorcentage4CapaE" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage4CapaE) && parseFloat(materiaPrimaPorcentage4CapaE) > 0) {
                            var resumenCapaE = $( "#resumenCapaE" ).val();
                            if(!isNaN(resumenCapaE) && parseFloat(resumenCapaE) > 0) { 
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima4CapaE) != undefined) {
                                    p = materiales.get(idMateriaPrima4CapaE);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage4CapaE) * parseFloat(resumenCapaE) / 100.0) + p;
                                materiales.set(idMateriaPrima4CapaE, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }

                var idMateriaPrima5CapaE = $( "#idMateriaPrima5CapaE" ).val();
                if(idMateriaPrima5CapaE != undefined && idMateriaPrima5CapaE != '' && idMateriaPrima5CapaE != -1) {
                    var materiaPrima5CapaE = $( "#materiaPrima5CapaE" ).val();
                    nombreMateriales.set(idMateriaPrima5CapaE, materiaPrima5CapaE);                                        
                    var materiaPrimaPorcentage5CapaE = $( "#materiaPrimaPorcentage5CapaE" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage5CapaE) && parseFloat(materiaPrimaPorcentage5CapaE) > 0) {
                            var resumenCapaE = $( "#resumenCapaE" ).val();
                            if(!isNaN(resumenCapaE) && parseFloat(resumenCapaE) > 0) {
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima5CapaE) != undefined) {
                                    p = materiales.get(idMateriaPrima5CapaE);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage5CapaE) * parseFloat(resumenCapaE) / 100.0) + p;
                                materiales.set(idMateriaPrima5CapaE, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }

                var idMateriaPrima6CapaE = $( "#idMateriaPrima6CapaE" ).val();
                if(idMateriaPrima6CapaE != undefined && idMateriaPrima6CapaE != '' && idMateriaPrima6CapaE != -1) {
                    var materiaPrima6CapaE = $( "#materiaPrima6CapaE" ).val();
                    nombreMateriales.set(idMateriaPrima6CapaE, materiaPrima6CapaE);                                                            
                    var materiaPrimaPorcentage6CapaE = $( "#materiaPrimaPorcentage6CapaE" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage6CapaE) && parseFloat(materiaPrimaPorcentage6CapaE) > 0) {
                            var resumenCapaE = $( "#resumenCapaE" ).val();
                            if(!isNaN(resumenCapaE) && parseFloat(resumenCapaE) > 0) {
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima6CapaE) != undefined) {
                                    p = materiales.get(idMateriaPrima6CapaE);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage6CapaE) * parseFloat(resumenCapaE) / 100.0) + p;
                                materiales.set(idMateriaPrima6CapaE, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }
            }
            
            if(cantidadCapasInt > 5) {        
                //CAPA F
                var idMateriaPrima1CapaF = $( "#idMateriaPrima1CapaF" ).val();
                if(idMateriaPrima1CapaF != undefined && idMateriaPrima1CapaF != '' && idMateriaPrima1CapaF != -1) {
                    var materiaPrima1CapaF = $( "#materiaPrima1CapaF" ).val();
                    nombreMateriales.set(idMateriaPrima1CapaF, materiaPrima1CapaF);                                                            
                    var materiaPrimaPorcentage1CapaF = $( "#materiaPrimaPorcentage1CapaF" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage1CapaF) && parseFloat(materiaPrimaPorcentage1CapaF) > 0) {
                            var resumenCapaF = $( "#resumenCapaF" ).val();
                            if(!isNaN(resumenCapaF) && parseFloat(resumenCapaF) > 0) {
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima1CapaF) != undefined) {
                                    p = materiales.get(idMateriaPrima1CapaF);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage1CapaF) * parseFloat(resumenCapaF) / 100.0) + p;
                                materiales.set(idMateriaPrima1CapaF, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }            

                var idMateriaPrima2CapaF = $( "#idMateriaPrima2CapaF" ).val();
                if(idMateriaPrima2CapaF != undefined && idMateriaPrima2CapaF != '' && idMateriaPrima2CapaF != -1) {
                    var materiaPrima2CapaF = $( "#materiaPrima2CapaF" ).val();
                    nombreMateriales.set(idMateriaPrima2CapaF, materiaPrima2CapaF);                                                                                
                    var materiaPrimaPorcentage2CapaF = $( "#materiaPrimaPorcentage2CapaF" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage2CapaF) && parseFloat(materiaPrimaPorcentage2CapaF) > 0) {
                            var resumenCapaF = $( "#resumenCapaF" ).val();
                            if(!isNaN(resumenCapaF) && parseFloat(resumenCapaF) > 0) {
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima2CapaF) != undefined) {
                                    p = materiales.get(idMateriaPrima2CapaF);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage2CapaF) * parseFloat(resumenCapaF) / 100.0) + p;
                                materiales.set(idMateriaPrima2CapaF, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }

                var idMateriaPrima3CapaF = $( "#idMateriaPrima3CapaF" ).val();
                if(idMateriaPrima3CapaF != undefined && idMateriaPrima3CapaF != '' && idMateriaPrima3CapaF != -1) {
                    var materiaPrima3CapaF = $( "#materiaPrima3CapaF" ).val();
                    nombreMateriales.set(idMateriaPrima3CapaF, materiaPrima3CapaF);                                                                                                    
                    var materiaPrimaPorcentage3CapaF = $( "#materiaPrimaPorcentage3CapaF" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage3CapaF) && parseFloat(materiaPrimaPorcentage3CapaF) > 0) {
                            var resumenCapaF = $( "#resumenCapaF" ).val();
                            if(!isNaN(resumenCapaF) && parseFloat(resumenCapaF) > 0) {
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima3CapaF) != undefined) {
                                    p = materiales.get(idMateriaPrima3CapaF);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage3CapaF) * parseFloat(resumenCapaF) / 100.0) + p;
                                materiales.set(idMateriaPrima3CapaF, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }

                var idMateriaPrima4CapaF = $( "#idMateriaPrima4CapaF" ).val();
                if(idMateriaPrima4CapaF != undefined && idMateriaPrima4CapaF != '' && idMateriaPrima4CapaF != -1) {
                    var materiaPrima4CapaF = $( "#materiaPrima4CapaF" ).val();
                    nombreMateriales.set(idMateriaPrima4CapaF, materiaPrima4CapaF);                                                                                                                        
                    var materiaPrimaPorcentage4CapaF = $( "#materiaPrimaPorcentage4CapaF" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage4CapaF) && parseFloat(materiaPrimaPorcentage4CapaF) > 0) {
                            var resumenCapaF = $( "#resumenCapaF" ).val();
                            if(!isNaN(resumenCapaF) && parseFloat(resumenCapaF) > 0) { 
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima4CapaF) != undefined) {
                                    p = materiales.get(idMateriaPrima4CapaF);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage4CapaF) * parseFloat(resumenCapaF) / 100.0) + p;
                                materiales.set(idMateriaPrima4CapaF, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }

                var idMateriaPrima5CapaF = $( "#idMateriaPrima5CapaF" ).val();
                if(idMateriaPrima5CapaF != undefined && idMateriaPrima5CapaF != '' && idMateriaPrima5CapaF != -1) {
                    var materiaPrima5CapaF = $( "#materiaPrima5CapaF" ).val();
                    nombreMateriales.set(idMateriaPrima5CapaF, materiaPrima5CapaF);                                                                                                                                            
                    var materiaPrimaPorcentage5CapaF = $( "#materiaPrimaPorcentage5CapaF" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage5CapaF) && parseFloat(materiaPrimaPorcentage5CapaF) > 0) {
                            var resumenCapaF = $( "#resumenCapaF" ).val();
                            if(!isNaN(resumenCapaF) && parseFloat(resumenCapaF) > 0) {
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima5CapaF) != undefined) {
                                    p = materiales.get(idMateriaPrima5CapaF);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage5CapaF) * parseFloat(resumenCapaF) / 100.0) + p;
                                materiales.set(idMateriaPrima5CapaF, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }

                var idMateriaPrima6CapaF = $( "#idMateriaPrima6CapaF" ).val();
                if(idMateriaPrima6CapaF != undefined && idMateriaPrima6CapaF != '' && idMateriaPrima6CapaF != -1) {
                    var materiaPrima6CapaF = $( "#materiaPrima6CapaF" ).val();
                    nombreMateriales.set(idMateriaPrima6CapaF, materiaPrima6CapaF);                                                                                                                                            
                    var materiaPrimaPorcentage6CapaF = $( "#materiaPrimaPorcentage6CapaF" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage6CapaF) && parseFloat(materiaPrimaPorcentage6CapaF) > 0) {
                            var resumenCapaF = $( "#resumenCapaF" ).val();
                            if(!isNaN(resumenCapaF) && parseFloat(resumenCapaF) > 0) {
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima6CapaF) != undefined) {
                                    p = materiales.get(idMateriaPrima6CapaF);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage6CapaF) * parseFloat(resumenCapaF) / 100.0) + p;
                                materiales.set(idMateriaPrima6CapaF, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }
            } 
            
            if(cantidadCapasInt > 6) {        
                //CAPA G
                var idMateriaPrima1CapaG = $( "#idMateriaPrima1CapaG" ).val();
                if(idMateriaPrima1CapaG != undefined && idMateriaPrima1CapaG != '' && idMateriaPrima1CapaG != -1) {
                    var materiaPrima1CapaG = $( "#materiaPrima1CapaG" ).val();
                    nombreMateriales.set(idMateriaPrima1CapaG, materiaPrima1CapaG); 
                    var materiaPrimaPorcentage1CapaG = $( "#materiaPrimaPorcentage1CapaG" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage1CapaG) && parseFloat(materiaPrimaPorcentage1CapaG) > 0) {
                            var resumenCapaG = $( "#resumenCapaG" ).val();
                            if(!isNaN(resumenCapaG) && parseFloat(resumenCapaG) > 0) {
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima1CapaG) != undefined) {
                                    p = materiales.get(idMateriaPrima1CapaG);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage1CapaG) * parseFloat(resumenCapaG) / 100.0) + p;
                                materiales.set(idMateriaPrima1CapaG, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }            

                var idMateriaPrima2CapaG = $( "#idMateriaPrima2CapaG" ).val();
                if(idMateriaPrima2CapaG != undefined && idMateriaPrima2CapaG != '' && idMateriaPrima2CapaG != -1) {
                    var materiaPrima2CapaG = $( "#materiaPrima2CapaG" ).val();
                    nombreMateriales.set(idMateriaPrima2CapaG, materiaPrima2CapaG);                     
                    var materiaPrimaPorcentage2CapaG = $( "#materiaPrimaPorcentage2CapaG" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage2CapaG) && parseFloat(materiaPrimaPorcentage2CapaG) > 0) {
                            var resumenCapaG = $( "#resumenCapaG" ).val();
                            if(!isNaN(resumenCapaG) && parseFloat(resumenCapaG) > 0) {
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima2CapaG) != undefined) {
                                    p = materiales.get(idMateriaPrima2CapaG);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage2CapaG) * parseFloat(resumenCapaG) / 100.0) + p;
                                materiales.set(idMateriaPrima2CapaG, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }

                var idMateriaPrima3CapaG = $( "#idMateriaPrima3CapaG" ).val();
                if(idMateriaPrima3CapaG != undefined && idMateriaPrima3CapaG != '' && idMateriaPrima3CapaG != -1) {
                    var materiaPrima3CapaG = $( "#materiaPrima3CapaG" ).val();
                    nombreMateriales.set(idMateriaPrima3CapaG, materiaPrima3CapaG);                                         
                    var materiaPrimaPorcentage3CapaG = $( "#materiaPrimaPorcentage3CapaG" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage3CapaG) && parseFloat(materiaPrimaPorcentage3CapaG) > 0) {
                            var resumenCapaG = $( "#resumenCapaG" ).val();
                            if(!isNaN(resumenCapaG) && parseFloat(resumenCapaG) > 0) {
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima3CapaG) != undefined) {
                                    p = materiales.get(idMateriaPrima3CapaG);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage3CapaG) * parseFloat(resumenCapaG) / 100.0) + p;
                                materiales.set(idMateriaPrima3CapaG, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }

                var idMateriaPrima4CapaG = $( "#idMateriaPrima4CapaG" ).val();
                if(idMateriaPrima4CapaG != undefined && idMateriaPrima4CapaG != '' && idMateriaPrima4CapaG != -1) {
                    var materiaPrima4CapaG = $( "#materiaPrima4CapaG" ).val();
                    nombreMateriales.set(idMateriaPrima4CapaG, materiaPrima4CapaG);                                                             
                    var materiaPrimaPorcentage4CapaG = $( "#materiaPrimaPorcentage4CapaG" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage4CapaG) && parseFloat(materiaPrimaPorcentage4CapaG) > 0) {
                            var resumenCapaG = $( "#resumenCapaG" ).val();
                            if(!isNaN(resumenCapaG) && parseFloat(resumenCapaG) > 0) { 
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima4CapaG) != undefined) {
                                    p = materiales.get(idMateriaPrima4CapaG);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage4CapaG) * parseFloat(resumenCapaG) / 100.0) + p;
                                materiales.set(idMateriaPrima4CapaG, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }

                var idMateriaPrima5CapaG = $( "#idMateriaPrima5CapaG" ).val();
                if(idMateriaPrima5CapaG != undefined && idMateriaPrima5CapaG != '' && idMateriaPrima5CapaG != -1) {
                    var materiaPrima5CapaG = $( "#materiaPrima5CapaG" ).val();
                    nombreMateriales.set(idMateriaPrima5CapaG, materiaPrima5CapaG);                                                             
                    var materiaPrimaPorcentage5CapaG = $( "#materiaPrimaPorcentage5CapaG" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage5CapaG) && parseFloat(materiaPrimaPorcentage5CapaG) > 0) {
                            var resumenCapaG = $( "#resumenCapaG" ).val();
                            if(!isNaN(resumenCapaG) && parseFloat(resumenCapaG) > 0) {
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima5CapaG) != undefined) {
                                    p = materiales.get(idMateriaPrima5CapaG);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage5CapaG) * parseFloat(resumenCapaG) / 100.0) + p;
                                materiales.set(idMateriaPrima5CapaG, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }

                var idMateriaPrima6CapaG = $( "#idMateriaPrima6CapaG" ).val();
                if(idMateriaPrima6CapaG != undefined && idMateriaPrima6CapaG != '' && idMateriaPrima6CapaG != -1) {
                    var materiaPrima6CapaG = $( "#materiaPrima6CapaG" ).val();
                    nombreMateriales.set(idMateriaPrima6CapaG, materiaPrima6CapaG);                                                             
                    var materiaPrimaPorcentage6CapaG = $( "#materiaPrimaPorcentage6CapaG" ).val();
                    try {
                        if(!isNaN(materiaPrimaPorcentage6CapaG) && parseFloat(materiaPrimaPorcentage6CapaG) > 0) {
                            var resumenCapaG = $( "#resumenCapaG" ).val();
                            if(!isNaN(resumenCapaG) && parseFloat(resumenCapaG) > 0) {
                                var p = 0.0;
                                if(materiales.get(idMateriaPrima6CapaG) != undefined) {
                                    p = materiales.get(idMateriaPrima6CapaG);
                                }
                                var value = (parseFloat(materiaPrimaPorcentage6CapaG) * parseFloat(resumenCapaG) / 100.0) + p;
                                materiales.set(idMateriaPrima6CapaG, value);
                            }
                        }
                    } catch(error) {
                        console.error(error);
                    }
                }
            }     
                        
            var panelMaterialesUtilizados = document.getElementById('panelMaterialesUtilizados');
            panelMaterialesUtilizados.innerHTML='';
            
            for (const key of materiales.keys()) {
                var nombreMaterial = nombreMateriales.get(key);
                panelMaterialesUtilizados.innerHTML+='    <div class="form-row row"><div class="row col-xs-12 col-sm-12 col-xl-12"><label>'+nombreMaterial+' ('+key+')</label>&nbsp;<input type="number" value="'+materiales.get(key)+'" class="form-control" placeholder="" disabled="true"/></div></div>';
                
            }
        }
        
        
        function callController() {

            var action = $( "#action" ).val();

            if(action == 'remove') {
                if(confirm('Desea eliminarlo')) {
                    var form = document.getElementById("myForm");
                    form.submit();
                }
            } else {
                if($("#myForm")[0].checkValidity()) {
                    var form = document.getElementById("myForm");
                    form.submit();
                } else {
                    $("#myForm")[0].reportValidity();
                }        
            }
        }
        
    </script>
<%@include file = "/WEB-INF/views/jsp/includes/footer.jsp" %>




