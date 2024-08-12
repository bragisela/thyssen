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
                    <div class="card">
                        <div class="card-header">
                            <ol class="breadcrumb mb-1 mt-1">
                                <li class="breadcrumb-item"><a href="">Inventario</a></li>
                                <li class="breadcrumb-item active">${titleMateriaPrima}</li>
                            </ol>
                        </div>
                        <div class="card-body">
                            <div class="container">

                                <form:form class="form-horizontal" method="post" action="/thyssenplastic/materiaprima/addOrEditOrRemove" modelAttribute="materiaprimaForm" id="myForm">

                                    <p></p>
                                    ${materiaprimaName}
                                    <p></p>

                                    <form:hidden path="pk" class="form-control"/>
                                    <form:hidden path="action" class="form-control"/>
                                    <form:hidden path="urlFichaTecnica" class="form-control"/>

                                    <div class="form-row row">
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputFecha_alta">Fecha Alta</label>
                                            <form:input type="date" path="fechaAlta" class="form-control" placeholder="" id="holderDateOfBirth"/>                                            
                                        </div>
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputRubro">Proveedor</label>
                                            <form:select  path="idProveedor" class="form-control rubro">                                                
                                                <form:options items="${proveedorList}"/>
                                            </form:select>                                            
                                        </div>
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputDescripcion">Descripción</label>                                                        
                                            <form:input path="descripcion" class="form-control" placeholder="" required="required" oninvalid="this.setCustomValidity('Complete este campo')" oninput="setCustomValidity('')"/>
                                        </div>                                                    
                                    </div>

                                    <div class="form-row row">

                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputRubro">Tipo De Material</label>
                                            <form:select  path="idTipo" class="form-control rubro">                                                
                                                <form:options items="${tipoList}"/>
                                            </form:select>                                            
                                        </div>
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputRubro">Den. Genérica</label>
                                            <form:select  path="idDenominacion" class="form-control rubro">                                                
                                                <form:options items="${denominacionList}"/>
                                            </form:select>                                            
                                        </div>
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputRubro">Petroquimica</label>
                                            <form:select  path="idPetroquimica" class="form-control rubro">                                                
                                                <form:options items="${petroquimicaList}"/>
                                            </form:select>                                            
                                        </div>
                                    </div>
                                        <div class="form-row row">
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputDescripcion">Densidad</label> 
                                            <form:input path="densidad" class="form-control" placeholder="" />
                                        </div>                                                    
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <label for="inputDescripcion">Indice de Fluidez</label> 
                                            <form:input path="indiceDeFluidez" class="form-control" placeholder="" />
                                        </div>                                                    
                                            
                                        </div>
                                    <div class="form-row row">
                                        <div class="row col-xs-9 col-sm-12 col-xl-4">
                                            <label for="inputObservaciones">Observaciones</label>
                                            <form:textarea path="observaciones" class="form-control"/>
                                        </div>
                                    </div>

                                    <p></p>

                                    <div class="form-row row">
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">                                              
                                            <input id="file" type="file" class="findDocumentOnboarding">                                                                                                                                
                                            <div id="messageUploadFile" style="display:none">None</div>
                                        </div>
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">                                            
                                            <input type="button" value="Subir Archivo" class="btn btn-xs btn-primary uploadDocumentOnboarding">
                                        </div>
                                        <div id="panelRefArchivo" class="row col-xs-9 col-sm-3 col-xl-4" style="display:none">                                            
                                            <a href="#" id="refArchivo" download>#</a> 
                                        </div>                                                                                
                                    </div>                                                                            

                                    <p></p>

                                    <div class="form-row row">
                                        <div class="row col-xs-9 col-sm-3 col-xl-4">
                                            <button type="button" class="btn btn-primary" onclick="callController()">${buttonLabel}</button>
                                            <a href="/thyssenplastic/materiaprima"><button type="button" class="btn btn-secondary">Cancelar</button></a>
                                        </div>
                                    </div>

                                </form:form>
                            </div>
                        </div>
                    </div>                
                </ol>
            </div>

            <hr>
            
            <div class="card">
                <div class="card-body">

                    <!--Modal-->
                    <div id="GSCCModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                                    <h4 class="modal-title" id="myModalLabel">Eliminar MateriaPrima!</h4>
                                </div>
                                <div class="modal-body">
                                    Desea Eliminar?
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary">Si</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <p></p>
                    <div class="row col-xs-12 col-sm-12 col-xl-12">
                        <table id="materiasprimaTable" class="display table table-striped table-hover cell-border">
                            <thead>
                                <tr>
                                    <th>CODIGO</th>
                                    <th>DESCRIPCION</th>                                    
                                    <th class="hidden-xs hidden-sm">PROVEEDOR</th>
                                    <th class="hidden-xs hidden-sm">TIPO</th>
                                    <th class="hidden-xs hidden-sm">DENSIDAD</th>
                                    <th class="hidden-xs hidden-sm">INDICE DE FLUIDEZ</th>
                                    <th class="hidden-xs hidden-sm">DENOMINACION GENERICA</th>
                                    <th class="hidden-xs hidden-sm">PETROQUIMICA</th>
                                    <th class="hidden-xs hidden-sm">STOCK</th>
                                    <th style="text-align: center">ACCIONES</th>
                                </tr>
                            </thead>

                            <tbody>

                                <c:forEach  items="${materiasprima}" var="materiaprima">
                                    <tr>
                                        <td><c:out value="${materiaprima.pk}" /></td>
                                        <td><c:out value="${materiaprima.descripcion}" /></td>
                                        <td class="hidden-xs hidden-sm"><c:out value="${materiaprima.idProveedor}" /></td>
                                        <td class="hidden-xs hidden-sm"><c:out value="${materiaprima.idTipo}" /></td>
                                        <td class="hidden-xs hidden-sm"><c:out value="${materiaprima.densidad}" /></td>
                                        <td class="hidden-xs hidden-sm"><c:out value="${materiaprima.indiceDeFluidez}" /></td>
                                        <td class="hidden-xs hidden-sm"><c:out value="${materiaprima.idDenominacion}" /></td>
                                        <td class="hidden-xs hidden-sm"><c:out value="${materiaprima.idPetroquimica}" /></td>
                                        <td class="hidden-xs hidden-sm"><c:out value="${materiaprima.stock}" /></td>
                                        <td>                                                    
                                            <a class="nav-link active fa fa-pencil-square-o fa-lg" href="/thyssenplastic/materiaprima/edit/${materiaprima.pk}" data-toggle="tooltip" data-placement="top" title="Editar"></a>
                                            <a class="nav-link active fa fa-trash fa-lg" href="/thyssenplastic/materiaprima/remove/${materiaprima.pk}" data-toggle="tooltip" data-placement="top" title="Eliminar"></a>
                                        </td>                                    
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

<!-- ./wrapper -->

<script>
    $(document).ready(function () {


        var table = $('#materiasprimaTable').DataTable({
            language: {
                "url": "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
            }
        });
       
        var action = $( "#action" ).val();
        
        if(action == 'edit') {
            $('#materiasprimaTable').hide();
        }

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

    function callController() {
                
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




