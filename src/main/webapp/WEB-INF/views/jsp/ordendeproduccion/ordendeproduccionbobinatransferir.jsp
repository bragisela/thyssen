<%@include file = "/WEB-INF/views/jsp/includes/header.jsp" %>
    
<style>
    
    .nav-tabs li.disabled a {
        pointer-events: none;
    }
</style>

   
<!-- Content Wrapper. Contains page content -->
<!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <section class="content">
            <div id="cuerpo" class="container">                
                <div class="container">
                    <ol>
                        <div class="card">
                            <div class="card-header">
                                <ol class="breadcrumb mb-1 mt-1">
                                    <li class="breadcrumb-item"><a href="">Transferir Bobina</a></li>
                                    <li class="breadcrumb-item active">${titleTransferirBobina}</li>
                                </ol>
                            </div>

                            <div class="card-body">
                                <form:form class="form-horizontal" method="post"
                                        action="/thyssenplastic/transferirBobina/addOrEditOrRemove"
                                        modelAttribute="transferirBobinaForm" id="myForm">

                                    <div class="container">


                                        <c:set var = "disabledAlta" value = "true"/>
                                        <c:if test = "${operacion == 'alta'}">
                                            <c:set var = "disabledAlta" value = "false"/>
                                        </c:if>
                                        <c:if test = "${operacion == 'view'}">
                                            <c:set var = "disabledAlta" value = "true"/>                                                                                  
                                        </c:if>
                                        <c:if test = "${operacion == 'edit'}">
                                            <c:set var = "disabledAlta" value = "false"/>                                                                                     
                                        </c:if>

                                        
                                        ${action}
                                        
                                        <p></p>
                                        ${graficoBobinaName}
                                        
                                        <p></p>

                                        <form:hidden path="pk" class="form-control"/>
                                        <form:hidden path="action" class="form-control"/>
                                        <form:hidden path="operacion" class="form-control"/>
                                        <form:hidden path="idOrdenDeProduccion" class="form-control"/>
                                        <form:hidden path="idBobina" class="form-control"/>

                                        <div class="tab-content">

                                            <div class="form-row row">                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <a href="/thyssenplastic/ordenDeProduccionDetalle/${idOrdenDeProduccion}" id="ordprod">Atrás</a>
                                                </div>                                                                                                    
                                            </div>
                                            
                                            <p>
                                                
                                            <div class="form-row row">                                                
                                                <div class="row col-xs-12 col-sm-12 col-xl-12">
                                                    <label for="inputFecha">Bobina <i>${idBobina}</i></i></label>
                                                </div>
                                                <div class="row col-xs-12 col-sm-12 col-xl-12">
                                                    <label for="inputFecha">Orden de Producción <i>${idOrdenDeProduccion}</i> - Cliente <i>${clienteLabel}</i> - Artículo <i>${idArticulo} (${articuloLabel})</i> - Ficha Técnica Versión <i>${fichaTecnicaVersionLabel}</i></label>
                                                </div>                                                                                                    
                                            </div>
                                                
                                            <p>
                                                
                                            <div class="form-row row">
                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Peso Cono (kg)</label>
                                                    <form:input type="number" path="pesoCono" class="form-control" placeholder="" disabled="true"/>
                                                </div>                                                    

                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Peso Total (kg)</label>
                                                    <form:input type="number" path="pesoTotal" class="form-control" placeholder="" disabled="true"/>
                                                </div>                                                    
                                                
                                                <div class="row col-xs-9 col-sm-3 col-xl-4">
                                                    <label for="inputFecha">Peso Neto (kg)</label>
                                                    <form:input type="number" path="pesoNeto" class="form-control" placeholder="" disabled="true"/>
                                                </div>                                                    

                                            </div>          
                                            
                                            <p>&nbsp;</p>
                                                                                            
                                        </div>                                        
                                        
                                        <p></p>
                                        <hr>
                                        <p></p>

                                        <div class="tab-content">

                                            <div class="form-row row">
                                                <div class="row col-xs-9 col-sm-3 col-xl-4" >
                                                    <label for="inputArticulo">Orden de Producción a Transferir</label>
                                                    <form:select path="idOrdenDeProduccionATransferir" class="form-control rubro" disabled="${disabledAlta}">                                                    
                                                        <form:option value="-1">Seleccionar...</form:option>
                                                        <form:options items="${ordenesDeProduccionList}" />
                                                    </form:select>
                                                </div>
                                            </div>
                                        </div>
                                                
                                        <p></p>                         
                                        &nbsp;
                                        <p></p>
                                        

                                        <div class="form-row row">
                                            <div class="row col-xs-9 col-sm-1 col-xl-1" style="display:${displayActionButton}">
                                                <button type="button" class="btn btn-primary" onclick="callController()">${buttonLabel}</button>                                            
                                            </div>
                                            <div class="row col-xs-9 col-sm-1 col-xl-1">
                                                <a href="/thyssenplastic/ordenDeProduccionDetalle/${idOrdenDeProduccion}/"><button type="button" class="btn btn-secondary">Cancelar</button></a>
                                            </div>    
                                        </div>
                                            
                                    </div>
                                
                                </form:form>
                            </div>
                        </div>
                    </ol>
                </div>
                
                <hr>

            </div>
        </section>
    </div>
                  

<!-- ./wrapper -->

<script>
    $(document).ready(function () {
        
    });
    
    function callController() {
               
        var action = $( "#action" ).val();
        var ordenDeProduccionATransferir = $( "#idOrdenDeProduccionATransferir" ).val();
        

        if(action == 'transferir') {
            
            if(ordenDeProduccionATransferir == undefined || ordenDeProduccionATransferir == '' || ordenDeProduccionATransferir == '-1') {
                alert('Debe seleccionar una orden de producción.');
            }
            else { 
                if($("#myForm")[0].checkValidity()) {
                    if(confirm('Desea transferirlo?')) {
                        var form = document.getElementById("myForm");
                        form.submit();
                    }
                } else {
                    $("#myForm")[0].reportValidity();
                }
            }
        }
               
    }
    
</script>
    
<%@include file = "/WEB-INF/views/jsp/includes/footer.jsp" %>




