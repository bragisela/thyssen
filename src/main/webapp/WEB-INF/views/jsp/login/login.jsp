<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>

<link rel="stylesheet" href="<c:url value="/resources/core/css/bootstrap.min.css"/>">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="<c:url value="/resources/core/css/ionicons.min.css"/>">
<link href="<c:url value="/resources/core/css/AdminLTE.min.css"/>" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="<c:url value="/resources/core/css/skins/skin-green.min.css"/>">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

<link rel="icon" type="image" href="assets/thyssenplastic_logo.png" />

<style>

body { 
 background: url('assets/banner-thyssenplastic.png') no-repeat center center fixed;
 
 -webkit-background-size: cover;
 -moz-background-size: cover;
 -o-background-size: cover;
 background-size: cover;
 
}

.panel-default {
 opacity: 0.9;
 margin-top:30px;
}
.form-group.last {
 margin-bottom:0px;
}

.btn.btn-signin {
    /*background-color: #4d90fe; */
    background-color: rgb(104, 145, 162);
    /* background-color: linear-gradient(rgb(104, 145, 162), rgb(12, 97, 33));*/
}

.btn.btn-signin:hover,
.btn.btn-signin:active,
.btn.btn-signin:focus {
    background-color: rgb(12, 97, 33);
}



</style>


</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                  <strong>Login</strong>
                </div>
                <div class="panel-body">
                    <form:form  class="form-horizontal" method="post" action="/thyssenplastic/login/user" modelAttribute="userForm">
                        <div class="row">
                            <div class="col-sm-12" style="color: red">
                                <center>${errorMessage}</center>
                            </div>
                        </div>                        
                        <p></p>
                        <div class="form-group">
                            <label for="usuario" class="col-sm-3 control-label">Usuario</label>
                            <div class="col-sm-9">
                                <form:input path="userName"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-sm-3 control-label">Password</label>
                            <div class="col-sm-9">
                                <form:password path="password"/>
                            </div>
                        </div>
                        <div class="form-group last">
                            <div class="col-sm-offset-3 col-sm-9">                                                        
                                <button type="submit" class="btn btn-lg btn-primary btn-block btn-signin">Ingresar</button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
    
</body>

<script>

    function ingresar() {
        alert('ingresar');
        var actionUrl = 'http://localhost:8080/thyssenplastic/authenticateLogin';
       
        $.ajax({
            url: actionUrl,            
            type: "get",
            success: function (response)
            {
                //alert(response);
                console.log(response);
                alert(response);
            }
        });
    }
    

</script>

</html>