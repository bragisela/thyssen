<%-- 
    Document   : user.jsp
    Created on : Aug 12, 2022, 10:22:29 AM
    Author     : gusta
--%>


<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


<jsp:include page="/WEB-INF/views/jsp/includes/header.jsp" flush="true" />

<div>
        
        Welcome!
        ${sessionInformation.emailUser}
        
        ${sessionInformation.isAutenticated}
        
        <p>          
        </p>
        
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                          <strong>Proveedor</strong>
                        </div>
                        <div class="panel-body">
                            <form:form class="form-horizontal" method="post" action="/thyssenplastic/proveedor/addOrEdit" modelAttribute="proveedorForm">
                                <div class="form-group">
                                    <label for="cuit" class="col-sm-3 control-label">Cuit</label>
                                    <div class="col-sm-9">
                                        <form:input path="cuit"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="razonSocial" class="col-sm-3 control-label">Razon Social</label>
                                    <div class="col-sm-9">
                                        <form:input path="razonSocial"/>
                                    </div>
                                </div>
                                <div class="form-group last">
                                    <div class="col-sm-offset-3 col-sm-9">                                                        
                                        <button type="submit" class="btn btn-lg btn-primary btn-block btn-signin">Crear</button>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        Proveedores:
        <p></p>
        
        <table>
            <td>pk</td>
            <td>cuit</td>
            <td>Razon Social</td>
            <td>Contacto</td>
            <c:forEach  items="${proveedores}" var="proveedor">
                <tr>
                    <td><c:out value="${proveedor.pk}" /></td>
                    <td><c:out value="${proveedor.cuit}" /></td>
                    <td><c:out value="${proveedor.razonSocial}" /></td>
                    <td><a href="/thyssenplastic/contacto/proveedorpk/${proveedor.pk}">Contacto</a></td>
                </tr>
            </c:forEach>
        </table>
</div>

<jsp:include page="/WEB-INF/views/jsp/includes/footer.jsp" flush="true" />