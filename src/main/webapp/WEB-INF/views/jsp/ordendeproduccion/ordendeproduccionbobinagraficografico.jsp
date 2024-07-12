<%@include file = "/WEB-INF/views/jsp/includes/header.jsp" %>
    
<script src="https://cdn.zingchart.com/zingchart.min.js"></script>

<style>
    
    .zc-body {
      background-color: #fff;
    }
 
    .chart--container {
      width: 100%;
      height: 100%;
      min-height: 530px;
    }
 
    .zc-ref {
      display: none;
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
                                    <li class="breadcrumb-item"><a href="">Gráfico Bobina</a></li>
                                    <li class="breadcrumb-item active">${titleGraficoBobina}</li>
                                </ol>
                            </div>

                            <div class="card-body">
                                <form:form class="form-horizontal" method="post"
                                        action="/thyssenplastic/graficoBobina/addOrEditOrRemove"
                                        modelAttribute="graficoBobinaForm" id="myForm">

                                    <div class="container">

                                        
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
                                                    <a href="/thyssenplastic/graficoBobina/${idOrdenDeProduccionBobina}" id="ordprod">Atrás</a>
                                                </div>                                                                                                    
                                            </div>
                                            
                                            <p>
                                                
                                            <div class="form-row row">             
                                                <div class="row col-xs-12 col-sm-12 col-xl-12">
                                                    <label for="inputFecha">Carga Código <i>${idGraficoBobina} - Fecha ${graficoBobinaFecha}</i></label>
                                                </div>                                                                                                
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

                                            <div class="form-row row">
                                                
                                                <div class="row col-xs-9 col-sm-9 col-xl-9">
                                                    <label for="inputFecha">Cantidad de mediciones</label>
                                                    <form:input type="number" path="cantidadMuestras" class="form-control" placeholder="" disabled="true"/>
                                                </div>                                                    

                                            </div>                                                
                                            <div class="form-row row">
                                                <div class="row col-xs-4 col-sm-4 col-xl-4">
                                                    <label for="inputFecha">Valor Nominal</label>
                                                    <form:input type="number" path="valorNominal" class="form-control" placeholder="" disabled="true"/>
                                                </div>                                                    
                                                
                                                <div class="row col-xs-4 col-sm-4 col-xl-4">
                                                    <label for="inputFecha">Máximo</label>
                                                    <form:input type="number" path="valorNominalMas" class="form-control" placeholder="" disabled="true"/>
                                                </div>                                                    

                                                <div class="row col-xs-4 col-sm-4 col-xl-4">
                                                    <label for="inputFecha">Mínimo</label>
                                                    <form:input type="number" path="valorNominalMenos" class="form-control" placeholder="" disabled="true"/>
                                                </div>                                                    

                                            </div>                                                
                                            <div class="form-row row">
                                                
                                                <div class="row col-xs-4 col-sm-4 col-xl-4">
                                                    <label for="inputFecha">Valor Promedio</label>
                                                    <form:input type="string" path="valorPromedio" class="form-control" placeholder="" disabled="true"/>
                                                </div>                                                    

                                                <div class="row col-xs-4 col-sm-4 col-xl-4">
                                                    <label for="inputFecha">Valor Maximo</label>
                                                    <form:input type="number" path="valorMaximo" class="form-control" placeholder="" disabled="true"/>
                                                </div>                                                    
                                                
                                                <div class="row col-xs-4 col-sm-4 col-xl-4">
                                                    <label for="inputFecha">Valor Minimo</label>
                                                    <form:input type="number" path="valorMinimo" class="form-control" placeholder="" disabled="true"/>
                                                </div>                                                    
                                                
                                            </div>          
                                            
                                        </div>                                                                              
                                                
                                        <p></p> 
                                            
                                    </div>
                                
                                </form:form>
                            </div>
                        </div>
                    </ol>
                </div>
                
                <hr>

                <div class="card">

                    <div class="card-body">

                        <div class="form-row row">
                            <div class="row col-xs-9 col-sm-9 col-xl-9">
                                <div id="myChart" class="chart--container">        
                                </div>
                            </div>                        
                            <div class="row col-xs-3 col-sm-3 col-xl-3" style="padding-left: 50px">
                                Referencia:
                                    <div class="form-row row">
                                        <p style="background:lightblue;width: 50px;">Real</p>
                                    </div>
                                    <div class="form-row row">
                                        <p style="background:red;width: 50px;">Máximo</p>
                                    </div>
                                    <div class="form-row row">
                                        <p style="background:greenyellow;width: 50px;">Mínimo</p>
                                    </div>
                                    <div class="form-row row">
                                        <p style="background:orange;width: 50px;">Teórico</p>
                                    </div>
                            </div>                                                    
                        </div>                                                    
                    </div>
                </div>
            </div>
        </section>
    </div>
                  

<!-- ./wrapper -->

<script>
    
    $(document).ready(function () {
        

        var vreal = [];
        var vmaximo = [];
        var vminimo = [];
        var vteorico = [];
        var pk = $("#pk").val();
        
        const idOrdenDeProduccion = `${idOrdenDeProduccion}`;
        const nroBobina = `${idBobina} `;
        const idArticulo = `${idArticulo} `;
        const articuloLabel = `${articuloLabel} `;
        const cantidadMuestras = `${cantidadMuestras} `;
        const promedio = `${promedio} `;
        const valorMinimo = `${valorMinimo} `;
        const valorMaximo = `${valorMaximo} `;
        
        var sUrl = '/thyssenplastic/graficoBobina/getValues/'+pk;
        $.ajax({
            async: true,
            type: 'GET',
            dataType : 'json',
            url: sUrl
        })
        .done( function (responseText) {            
            if(responseText.length > 0) {       
                for (i = 0; i < responseText.length; i++) {
                    var item = responseText[i];
                    vreal.push(item.valorReal);
                    vmaximo.push(item.valorMaximo);
                    vminimo.push(item.valorMinimo);
                    vteorico.push(item.valorTeorico);
                }                
                
                let chartConfig = {
                    type: 'radar',
                    series: [{
                        values: vreal
                    },
                    {
                        values: vmaximo
                    },
                    {
                        values: vminimo                   
                    },        
                    {
                        values: vteorico                   
                    }                    
                  ],
                  labels: [{
                    text: 'Orden de produccion: ' + idOrdenDeProduccion,
                    x: '1%',
                    y: '5%',
                    'font-size': '15px',
                    'data-pdf-text': 'Producto A'
                  },
                  {
                    text: 'Nro Bobina: ' + nroBobina,
                    x: '1%',
                    y: '10%',
                    'font-size': '15px'
                    },
                    {
                    text: 'Articulo' + idArticulo + `(${articuloLabel}) `,
                    x: '1%',
                    y: '15%',
                    'font-size': '15px'
                    },
                    {
                    text: 'Datos ingresados: ',
                    x: '2%',
                    y: '30%',
                    'font-size': '15px',
                    'font-weight': 'bold'
                    },
                    {
                    text: 'Valor Nominal: ' + vteorico[0],
                    x: '1%',
                    y: '35%',
                    'font-size': '15px',
                    'color': 'orange',
                    'font-weight': 'bold'
                    },
                    {
                    text: 'Maximo: ' + vmaximo[0],
                    x: '1%',
                    y: '40%',
                    'font-size': '15px',
                    'color' : 'red',
                    'font-weight': 'bold'
                    },
                    {
                    text: 'Minimo: ' + vminimo[0],
                    x: '1%',
                    y: '45%',
                    'font-size': '15px',
                    'color' : 'green',
                    'font-weight': 'bold'
                    },
                    {
                    text: 'Resultado: ',
                    x: '2%',
                    y: '55%',
                    'font-size': '15px',
                    'font-weight': 'bold',
                    },
                    {
                    text: 'Cant. mediciones: '+ cantidadMuestras,
                    x: '1%',
                    y: '60%',
                    'font-size': '15px'
                    },
                    {
                    text: 'Valor Promedio: '+ promedio,
                    x: '1%',
                    y: '65%',
                    'font-size': '15px'
                    },
                    {
                    text: 'Valor Maximo: ' + valorMaximo,
                    x: '1%',
                    y: '70%',
                    'font-size': '15px'
                    },
                    {
                    text: 'Valor Minimo: ' + valorMinimo,
                    x: '1%',
                    y: '75%',
                    'font-size': '15px'
                    }
                ]    
          
                };

                zingchart.render({
                  id: 'myChart',
                  data: chartConfig,
                  height: '100%',
                  width: '100%'
                });
                
            }
        })
        .fail( function (jqXHR, status, error) {
            // Triggered if response status code is NOT 200 (OK)
            alert(jqXHR.responseText);
        })
        .always( function() {
            // Always run after .done() or .fail()
        });
                
                
    });
    
    
</script>
    
<%@include file = "/WEB-INF/views/jsp/includes/footer.jsp" %>




