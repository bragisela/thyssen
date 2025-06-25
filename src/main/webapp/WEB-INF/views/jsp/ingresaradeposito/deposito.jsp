<%@include file = "/WEB-INF/views/jsp/includes/header.jsp" %>

<style>
        table {
        width: 80%;
        border-collapse: collapse;
        margin-top: 20px;
        }

    th, td {
        border: 1px solid #AED581; /* Utilizar un color de borde más claro */
        background: #fff;
        padding: 1px;
        text-align: left;
        font-size: 14px;
    }

    th {
        background-color: #DCECCB;
        font-size: 16px;
    }
    </style>


<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <section class="content">
        <div id="cuerpo" class="container">
            <div class="container">
        
                    <div class="card">
                        
                        <div class="card-header">
                           
                    
                            <ol class="breadcrumb mb-1 mt-1">
                                <li class="breadcrumb-item"><a href="">Deposito</a></li>
                                
                            </ol>
                        </div>
<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
    <div class="form-inline">
        <label for="filtroDeposito" class="mr-2 mb-2">Filtrar por Denominación:</label>
        <br>
        <select id="filtroDeposito" onchange="filtrarTabla()" class="form-control mr-3 mb-2" style="width: 70%;">
            <option value="todos">Todas las denominaciones</option>
            <c:forEach var="entry" items="${articuloList}">
                <option value="${entry.key}">${entry.value}</option>
            </c:forEach>
        </select>
        <input type="checkbox" class="form-check-input" id="filtroSinDisponibilidad" onchange="filtrarTabla()"> Filtrar sin disponibilidad
    </div>
</div>

                    <div class="row col-xs-12 col-sm-12 col-xl-12" style="height: 400px; overflow-y: auto;">
                   
                    <div style="margin-top: 2em;"></div>
                    <table id="reportesTable" style="width: 90%">
                        <thead>
                            <tr>
                                <th style="display:none;">Codigo Articulo</th>
                                <th>Nro Lote</th>
                                <th>Depósito</th>
                                <th>Unidades</th>
                                <th>Recepcionado(KG)</th>
                                <th>Disponible(KG)</th>
                                <th>Consumido(KG)</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="deposito" items="${depositos}">
                                <tr data-deposito-id="${deposito.idArticulo}">
                                    <td style="display:none;">${deposito.idArticulo}</td>
                                    <td>${deposito.idOrdenDeProduccion}</td>
                                    <td>${deposito.nombreDeposito}</td>
                                    
                                    <td class="custom-icon-container" style="--remito-detalle: ${remitoDetalle};">
                                         <div style="float: left;">${deposito.unidad}</div>
                                    </td>
                                        
                                   <td>${deposito.sumapeso}</td>
                                   <td>${deposito.sumapeso - deposito.pesoConsumido}</td>
                                   <td>${deposito.pesoConsumido}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
               
                        

                    </div>    
                            
                </div>
               
            </div>
        </div>
    </section>                                                
</div>                   
<script>
    $(document).ready(function () {
        
        $('#reportesTable').DataTable({
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
            },
            order: [[1, 'desc']],
            paging: false,
            dom: 'Bfrtip',
            buttons: [
                'excel', 'pdf', 'print'
            ]       
        });
    });
    
 /*   function filtrarTabla() {
        var filtro = document.getElementById("filtroDeposito").value;
        var filas = document.querySelectorAll("#reportesTable tbody tr");

        filas.forEach(function (fila) {
            if (filtro === "todos" || fila.getAttribute("data-deposito-id") === filtro) {
                fila.style.display = "";
            } else {
                fila.style.display = "none";
            }
        });
    }
   */
  
 function filtrarTabla() {
    var filtro = document.getElementById("filtroDeposito").value;
    var sinDisponibilidad = document.getElementById("filtroSinDisponibilidad").checked;
    var filas = document.querySelectorAll("#reportesTable tbody tr");

    filas.forEach(function (fila) {
        var idDeposito = fila.getAttribute("data-deposito-id");
        var disponibleKG = parseFloat(fila.children[5].textContent); // columna Disponible(KG)

        var coincideFiltro = (filtro === "todos" || idDeposito === filtro);
        var tieneDisponibilidad = (disponibleKG > 0);

        // Mostrar solo si coincide con filtro y (si el checkbox está activado, además tiene disponibilidad)
        if (coincideFiltro && (!sinDisponibilidad || tieneDisponibilidad)) {
            fila.style.display = "";
        } else {
            fila.style.display = "none";
        }
    });
}
 

</script>
<style>
 .custom-icon-color {
    color: #0D47A1;
    position: relative;
    cursor: pointer;
  }
  .custom-icon-color:hover::before {
    content: "En remitos: " var(--remito-detalle);
    position: absolute;
    background-color: #f0f0f0;
    white-space: nowrap; /* Evita saltos de línea */
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 18px;
    visibility: hidden;
    font-weight: 700;
  }
  .custom-icon-color:hover::before {
    visibility: visible;
  }
  
  
</style>
<%@include file = "/WEB-INF/views/jsp/includes/footer.jsp" %>