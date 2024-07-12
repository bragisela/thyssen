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
                                <li class="breadcrumb-item"><a href="">Configuración</a></li>
                                <li class="breadcrumb-item active">${titleTipo}</li>
                            </ol>
                    </div>
                            
                    <body style="font-family: Arial, sans-serif; background-color: #f4f4f4; display: flex; align-items: center; justify-content: center; height: 100vh; margin: 0;">
         

    <div style="background-color: #fff; padding: 20px; border-radius: 8px; text-align: center; border: 2px solid #8BC34A; 
    box-shadow: ${status ? '0 0 10px rgba(255, 0, 0, 0.1)' : '0 0 10px rgba(255, 0, 0, 0.5)'};">

         <c:choose>
            <c:when test="${status}">
            <h3 style="color: #333; margin-bottom: 20px;">La activación manual se encuentra Activada</h3>
            <a href="/thyssenplastic/activacionmanual/setActivacionManual/1" style="display: inline-block; padding: 10px 15px; text-decoration: none; color: #fff; background-color: #008000; border-radius: 5px; font-size: 20px">Desactivar</a>
              </c:when>
             <c:otherwise>
                 <h3 style="color: #333; margin-bottom: 20px;">La activación manual se encuentra Desactivada</h3>
             <a href="/thyssenplastic/activacionmanual/setActivacionManual/1" style="display: inline-block; padding: 10px 15px; text-decoration: none; color: #fff; background-color: #ff0000; border-radius: 5px; font-size: 20px">Activar</a>
            </c:otherwise>
        </c:choose>
        
      
    </div>

</body>
                </ol>
            </div>
        </div>
    </section>                                                
</div>                   

<!-- ./wrapper -->

<script>
   
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




