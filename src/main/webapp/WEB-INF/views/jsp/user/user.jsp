<%-- 
    Document   : user.jsp
    Created on : Aug 12, 2022, 10:22:29 AM
    Author     : gusta
--%>


<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/views/jsp/includes/header.jsp" flush="true" />

<div>
        
        Welcome!
        ${sessionInformation.emailUser}
        
        ${sessionInformation.isAutenticated}
        
        <p>          
        </p>
        
        Usuarios:
        <p></p>
        
        <table>
            <td>Username</td>
            <td>Nombre</td>
            <td>Apellido</td>
            <c:forEach  items="${users}" var="user">
                <tr>
                    <td><c:out value="${user.userName}" /></td>
                    <td><c:out value="${user.nombre}" /></td>
                    <td><c:out value="${user.apellido}" /></td>
                </tr>
            </c:forEach>
        </table>
</div>

<jsp:include page="/WEB-INF/views/jsp/includes/footer.jsp" flush="true" />