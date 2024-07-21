<%@include file = "/WEB-INF/views/jsp/includes/header.jsp" %>
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
                                    <li class="breadcrumb-item"><a href="">Acceso Directo</a></li>
                                    <li class="breadcrumb-item active">${titleOrdenDeProduccion}</li>
                                </ol>
                            </div>
                                <h1 style="color: #008080; border: 2px solid #008080; padding: 20px; border-radius: 8px; margin-top: 20px; margin-bottom: 30px; display: inline-block; text-decoration: underline;">Diagramas Polares para la Orden de Producción Nro ${ordenDeProduccionPk}</h1>
                                <div class="card">
                                <div class="card-body">
                                <div class="row col-xs-12 col-sm-12 col-xl-12">
                                <table id="ordendeproduccionbobinasTable" class="display table table-striped table-hover cell-border">
                                    <thead>
                                        <tr>
                                            <th>CODIGO</th>
                                            <th>#MEDICIONES</th>
                                             <th>Espesor Nominal</th>
                                            <th style="text-align: center">ACCIONES</th>
                                        </tr>
                                    </thead>
                                        <tbody>
                                            <c:forEach items="${graficoBobinas}" var="graficoBobina">
                                                <tr>
                                                    <td>
                                                        <c:out value="${graficoBobina.codigo}" />
                                                    </td>  
                                                    
                                                    <td>
                                                        <c:out value="${graficoBobina.mediciones}" />
                                                    </td> 
                                                    <td>
                                                        <c:out value="${graficoBobina.espesorNominal}" />
                                                    </td> 
                                                    <td>
                                                        <a class="nav-link active fa fa-pie-chart fa-lg"
                                                            href="/thyssenplastic/graficoBobinaGrafico/${graficoBobina.pk}"
                                                            data-toggle="tooltip" data-placement="top" title="Gráfico Polar"></a>
                                                        <a class="nav-link active fa fa-print fa-lg"
                                                            href="javascript:void(0);" onclick="printEtiquetaBobina(${graficoBobina.pk})"
                                                            data-toggle="tooltip" data-placement="top" title="Imprimir" id="imprimirBobina${graficoBobina.pk}"></a>                                                   
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
                    </ol>
                </div>
                

            </div>
        </section>
    </div>
                  

<!-- ./wrapper -->
<script>
     // Escucha el evento "show.bs.modal" que se dispara cuando se muestra el modal
    $('#miModal').on('show.bs.modal', function (event) {
        // Obtiene el botón que disparó el evento
        var button = $(event.relatedTarget);

        // Obtiene el valor del atributo de datos "data-codigo" del botón
        var codigoOrdenProduccion = button.data('codigo');
        var listCodigosOrdenProduccion = button.data('lista');

        // Actualiza el contenido del span con el código en el modal
        $('#codigoOrdenProduccion').text(codigoOrdenProduccion);
        $('#listCodigosOrdenProduccion').text(listCodigosOrdenProduccion);
    });
   
    $(document).ready(function () {
        
        $('#ordendeproduccionbobinasTable').DataTable({
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
            },           
            order: [[1, 'desc']]            
        });

        $('#ordendeproduccionbultosTable').DataTable({
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
            },           
            order: [[1, 'desc']]            
        });

        $('#ordendeproduccionpalletsTable').DataTable({
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
            },           
            order: [[1, 'desc']]            
        });

        $('#ordendeproduccionscrapsTable').DataTable({
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
            },           
            order: [[1, 'desc']]            
        });

        var operacion = $('#operacion').val();
        var tipoDetalle = $('#tipoDetalle').val();
        
        if(operacion == 'edit' && tipoDetalle == 'bobina') {
            var pesoNetoBobina = $('#pesoNetoBobina').val();
            $('#pesoNetoBobinaCalculated').val(pesoNetoBobina); 
        }
        
        $('#myTab a').on('click', function (e) {
            
            var href = $(this).attr('href');

            if(href == '#bobinas'){
                $('#tipoDetalle').val('bobina');
            }
            if(href == '#bultos'){
                $('#tipoDetalle').val('bulto');
            }
            if(href == '#pallets'){
                $('#tipoDetalle').val('pallet');
            }
            if(href == '#scraps'){
                $('#tipoDetalle').val('scrap');
            }
            
        });
        
        if(operacion == 'edit' && tipoDetalle == 'bulto') {            
            $('#myTab a[href="#bultos"]').tab('show');
            $('#bultos').tab('show');
        }
        
        /*
        var action = $('#action').val();
        if(action = 'edit') {
            loadArticulos();
        }
        */
        var imprimir = $('#imprimir').val();        
        if(imprimir == 'true') {
            var imprimirTipo = $('#imprimirTipo').val();
            var imprimirPk = $('#imprimirPk').val();
            if(imprimirTipo != '' && imprimirTipo != null && imprimirTipo != undefined && imprimirPk != '' && imprimirPk != null && imprimirPk != undefined) {                                
                if(imprimirTipo == 'bobina') {
                    printEtiquetaBobina(imprimirPk);
                }
                if(imprimirTipo == 'bulto') {
                    printEtiquetaBulto(imprimirPk);
                }
                if(imprimirTipo == 'pallet') {
                    printEtiquetaPallet(imprimirPk);
                }                
                if(imprimirTipo == 'scrap') {
                    printEtiquetaScrap(imprimirPk);
                }                                
            }
        }
        
    });
    
    function callBobinaSave() {
        var tipo = $("#tipoDetalle").val('bobina');

        var pesoConoBobina = $( "#pesoConoBobina" ).val();
        var pesoTotalBobina = $( "#pesoTotalBobina" ).val();
                
        if(pesoConoBobina == '') {
            const input = document.getElementById("pesoConoBobina");
            input.setCustomValidity('Complete este campo');
            $("#myForm")[0].reportValidity();            
        } 

        if(pesoTotalBobina == '') {
            const input2 = document.getElementById("pesoTotalBobina");
            input2.setCustomValidity('Complete este campo');
            $("#myForm")[0].reportValidity();            
        } 
        
        var action = $( "#action" ).val();

        if(action == 'remove') {
            if(confirm('Desea eliminarlo')) {
                var form = document.getElementById("myForm");
                form.submit();
            }
        } else {                        
            if($("#myForm")[0].checkValidity()) {
                var continueSubmit = true;
                var estadoBobina = $("#estadoBobina").val();
                if(estadoBobina == '' || estadoBobina == '-1') {
                    continueSubmit = false;
                    if(confirm('No ha ingresado Estado (Calidad). Desea continuar?')) {
                        continueSubmit = true;
                    }
                }
                if(continueSubmit) {
                    var form = document.getElementById("myForm");
                    form.submit();
                }
            } else {
                $("#myForm")[0].reportValidity();
            }                                    
        }
                
    }

    function calculatePesoNetoBobina() {
        
        var pesoConoBobina = $("#pesoConoBobina").val();
        var pesoTotalBobina = $("#pesoTotalBobina").val();
        
        try {
            if(!isNaN(pesoConoBobina) && parseFloat(pesoConoBobina) > 0 && !isNaN(pesoTotalBobina) && parseFloat(pesoTotalBobina) > 0) {
                var value = parseFloat(pesoTotalBobina) - parseFloat(pesoConoBobina);
                $("#pesoNetoBobina").val(value);
                $("#pesoNetoBobinaCalculated").val(value);                
            }
        } catch(error) {
            console.error(error);
        }

    }
       
    function printEtiquetaBobina(ordendeproduccionbobinapk) {        
        window.open("/thyssenplastic/ordenDeProduccionDetalle/print/bobina/"+ordendeproduccionbobinapk, "Imprimir Etiqueta Bobina", "popup,width=1280,height=800");
    }

    function callBultoSave() {
        var tipo = $("#tipoDetalle").val('bulto');

        var idBobinaSelected = $( "#idBobinaSelected" ).val();

        if(idBobinaSelected == '-1') {
            alert('Debe seleccionar una bobina.')
        } else {
            
            var continueSubmit = true;
            var estadoBulto = $("#estadoBulto").val();
            if(estadoBulto == '' || estadoBulto == '-1') {
                continueSubmit = false;
                if(confirm('No ha ingresado Estado (Calidad). Desea continuar?')) {
                    continueSubmit = true;
                }
            }
            if(continueSubmit) {
                var form = document.getElementById("myForm");
                form.submit();
            }
	}        
        
    }
    
    function searchBobina() {
       
        var idOrdenProduccion = $("#idOrdenProduccion").val();
        var searchCodigoBobina = $("#searchCodigoBobina").val();
        var bobinaDisponibleSelected = $("#bobinasDisponibles option:selected").val();
        var code = '-1';
        var idBobina = '-1';
        
        if(searchCodigoBobina != '') {
            code = searchCodigoBobina;
        } else if (bobinaDisponibleSelected != '-1') {
            idBobina = bobinaDisponibleSelected;
        }
                
        var sUrl = '/thyssenplastic/ordenDeProduccionDetalle/setBobinaSelected/'+idOrdenProduccion+'/'+code+'/'+idBobina;
        $.ajax({
            async: true,
            type: 'GET',
            dataType : 'json',
            url: sUrl
        })
        .done( function (responseText) {            
            if(responseText.length > 0) {       
                
                var bobina = responseText[0];                
                if(bobina.error == 'OK') {                    
                    $("#bobinaSelectedLabel").val(bobina.codigo + ' (Peso Total: ' + bobina.pesoTotal + ' | Peso Neto: ' + bobina.pesoNeto + ')');
                    if(bobina.estado != null) {
                        $("#estadoBobinaSelectedLabel").val(bobina.estado);
                        if(bobina.estado == "OK") {
                            $("#estadoBobinaSelectedLabel").attr('style','color:green;font-weight: bold;');
                        }
                        if(bobina.estado == "Observado") {
                            $("#estadoBobinaSelectedLabel").attr('style','color:#89B00F;font-weight: bold;');
                        }
                        if(bobina.estado == "Rechazado") {
                            $("#estadoBobinaSelectedLabel").attr('style','color:red;font-weight: bold;');
                        }
                        if(bobina.estado == "Sin Mesurar") {
                            $("#estadoBobinaSelectedLabel").attr('style','color:orange;font-weight: bold;');
                        }
                        
                    } else {
                        $("#estadoBobinaSelectedLabel").val('');
                    }
                    $("#idBobinaSelected").val(bobina.pk);
                    $("#codigoBultoLabel").val('R'+bobina.pk);
                } else {
                    $("#bobinaSelectedLabel").val(bobina.errorMessage);
                    $("#estadoBobinaSelectedLabel").val('');
                    $("#idBobinaSelected").val('-1');
                    $("#codigoBultoLabel").val('');                    
                }
            } else {
                $("#bobinaSelectedLabel").val('No existe bobina seleccionada.');
                $("#idBobinaSelected").val('-1');
                $("#codigoBultoLabel").val('');
            }
        })
        .fail( function (jqXHR, status, error) {
            // Triggered if response status code is NOT 200 (OK)
            alert(jqXHR.responseText);
            $("#bobinaSelectedLabel").val('No existe bobina seleccionada.');
            $("#idBobinaSelected").val('-1');
            $("#codigoBultoLabel").val('');
        })
        .always( function() {
            // Always run after .done() or .fail()
        });
        
    }
    
    function printEtiquetaBulto(ordendeproduccionbultopk) {
        window.open("/thyssenplastic/ordenDeProduccionDetalle/print/bulto/"+ordendeproduccionbultopk, "Imprimir Etiqueta Bulto", "popup,width=1280,height=800");
    }

    function callPalletSave() {
        var tipo = $("#tipoDetalle").val('pallet');
        var sizeBultosSelected = $('#bultosSelected option').length;
                
        if(sizeBultosSelected > 0) {
            
            $('#bultosSelected option').each(function () {
                $(this).attr('selected', true);
            });

            var continueSubmit = true;
            var estadoPallet = $("#estadoPallet").val();
            if(estadoPallet == '' || estadoPallet == '-1') {
                continueSubmit = false;
                if(confirm('No ha ingresado Estado (Calidad). Desea continuar?')) {
                    continueSubmit = true;
                }
            }
            if(continueSubmit) {
                var form = document.getElementById("myForm");
                form.submit();
            }

        
        } else {
            alert('Debe agregar al menos un bulto a la lista.')
        }
        
    }
    
    function searchBulto() {
       
        var idOrdenProduccion = $("#idOrdenProduccion").val();
        var searchCodigoBulto = $("#searchCodigoBulto").val();
        var bultoDisponibleSelected = $("#bultosDisponibles option:selected").val();
        var code = '-1';
        var idBulto = '-1';
        
        if(searchCodigoBulto != '') {
            code = searchCodigoBulto;
        } else if (bultoDisponibleSelected != '-1') {
            idBulto = bultoDisponibleSelected;
        }
                
        var sUrl = '/thyssenplastic/ordenDeProduccionDetalle/setBultoSelected/'+idOrdenProduccion+'/'+code+'/'+idBulto;
        $.ajax({
            async: true,
            type: 'GET',
            dataType : 'json',
            url: sUrl
        })
        .done( function (responseText) {            
            if(responseText.length > 0) {       
                
                var bulto = responseText[0];                
                if(bulto.error == 'OK') {      
                    
                    var existeItem = false;
                    $("#bultosSelected option").each(function()
                    {                        
                        if($(this).val() == bulto.pk) {
                            existeItem = true;
                        }
                    });                    
                    
                    if(!existeItem) {
                        $("#bultosSelected").append('<option value='+bulto.pk+'>' + bulto.codigo + ' ('+ bulto.estado + ')</option>');    
                    } else {
                        alert('El bulto ya ha sido agregado a la lista.');
                    }
                    /*
                    $("#bultoSelectedLabel").val(bulto.codigo + ' (Peso Total ' + bulto.pesoTotal + ')');
                    if(bulto.estado != null) {
                        $("#estadoBultoSelectedLabel").val(bulto.estado);
                        if(bulto.estado == "OK") {
                            $("#estadoBultoSelectedLabel").attr('style','color:green;font-weight: bold;');
                        }
                        if(bulto.estado == "Observado") {
                            $("#estadoBultoSelectedLabel").attr('style','color:#89B00F;font-weight: bold;');
                        }
                        if(bulto.estado == "Rechazado") {
                            $("#estadoBultoSelectedLabel").attr('style','color:red;font-weight: bold;');
                        }
                        if(bulto.estado == "Sin Mesurar") {
                            $("#estadoBultoSelectedLabel").attr('style','color:orange;font-weight: bold;');
                        }
                        
                    } else {
                        $("#estadoBultoSelectedLabel").val('');
                    }
                    $("#idBultoSelected").val(bulto.pk);
                    $("#codigoBultoLabel").val('R'+bulto.pk);                 
                     */
                } else {
                    alert(bulto.errorMessage);
                    /*
                    $("#bultoSelectedLabel").val(bulto.errorMessage);
                    $("#estadoBultoSelectedLabel").val('');
                    $("#idBultoSelected").val('-1');
                    $("#codigoBultoLabel").val('');                                     
                     */
                }
            } else {
                $("#bultoSelectedLabel").val('No existe bulto seleccionado.');
                $("#idBultoSelected").val('-1');
                $("#codigoBultoLabel").val('');
            }
        })
        .fail( function (jqXHR, status, error) {
            // Triggered if response status code is NOT 200 (OK)
            alert(jqXHR.responseText);
            $("#bultoSelectedLabel").val('No existe bulto seleccionado.');
            $("#idBultoSelected").val('-1');
            $("#codigoBultoLabel").val('');
        })
        .always( function() {
            // Always run after .done() or .fail()
        });
        
    }
    
    function eliminarBulto() {
        
        var bultoSelected = $("#bultosSelected option:selected").val();
        $("#bultosSelected option[value='"+bultoSelected+"']").remove();
        
    }    
    
    function printEtiquetaPallet(ordendeproduccionpalletpk) {
        window.open("/thyssenplastic/ordenDeProduccionDetalle/print/pallet/"+ordendeproduccionpalletpk, "Imprimir Etiqueta Pallet", "popup,width=1280,height=800");
    }
        
    
    function callScrapSave() {
        var tipo = $("#tipoDetalle").val('scrap');

        var pesoTotalScrap = $( "#pesoTotalScrap" ).val();
                
        if(pesoTotalScrap == '') {
            const input = document.getElementById("pesoTotalScrap");
            input.setCustomValidity('Complete este campo');
            $("#myForm")[0].reportValidity();            
        } 
        
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
    
    function printEtiquetaScrap(ordendeproduccionscrappk) {
        window.open("/thyssenplastic/ordenDeProduccionDetalle/print/scrap/"+ordendeproduccionscrappk, "Imprimir Etiqueta Scrap", "popup,width=1280,height=800");
    }
    
</script>

    
<%@include file = "/WEB-INF/views/jsp/includes/footer.jsp" %>




