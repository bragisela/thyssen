<%@include file = "/WEB-INF/views/jsp/includes/header.jsp" %>


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
                                <li class="breadcrumb-item active">Grafico Polar</li>
                            </ol>
                    </div>
                </ol>
              <div class="row col-xs-9 col-sm-3 col-xl-4">
                <label for="inputRol">Seleccione Orden de Producción</label>
                    <input type="text" id="provinciaInput" class="form-control" list="provinciaList" placeholder="Buscar orden de producción...">
                    <datalist id="provinciaList">
                    <c:forEach items="${provinciaList}" var="provincia" >
                     <option id="provincia" value="${provincia}" />
                         </c:forEach>
                      </datalist>
                      <button type="submit" class="btn btn-primary"  onclick="buscar()">Buscar</button>
              </div>
            </div>
        </div>
    </section>                                                
</div>                   

<!-- ./wrapper -->

<script>
   
    function callController() {
                
        var action = $( "#action" ).val();
        
        var idCliente = $( "#idCliente option:selected" ).val();

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
    
    function filtrarOpciones() {
            var input, filter, select, options, option, i, txtValue;
            input = document.getElementById("busqueda");
            filter = input.value.toUpperCase();
            select = document.getElementById("provincia");
            options = document.getElementById("opcionesProvincia").getElementsByTagName("option");

            for (i = 0; i < options.length; i++) {
                option = options[i];
                txtValue = option.textContent || option.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    option.style.display = "";
                } else {
                    option.style.display = "none";
                }
            }
        }
    function buscar() {
        var selectedValue = document.getElementById("provinciaInput").value;
        var redirectUrl = "/thyssenplastic/accesoDirectoGraficoPolarDetalle/" + selectedValue;
        window.location.href = redirectUrl;
    }
    
    $(document).ready(function() {
        $("#provinciaInput").on("input", function() {
            var searchTerm = $(this).val().toLowerCase();
            // Puedes realizar acciones adicionales aquí si es necesario
        });
    });
</script>

<%@include file = "/WEB-INF/views/jsp/includes/footer.jsp" %>




