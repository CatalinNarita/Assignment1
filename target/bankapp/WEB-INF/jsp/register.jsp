<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Register a new account</title>

    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/common.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respwebapp.resources.jsd.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container">

    <form class = "form-signin" action = "register.htm" method = "POST">
        <h2 class="form-heading">Register</h2>

        <div class="form-group">
            <input name="name" type="text" class="form-control" placeholder="Your name" autofocus="true"/>
            <input name="username" type="text" class="form-control" placeholder="Username" autofocus="true"/>
            <input name="password1" type="password" class="form-control" placeholder="Password" autofocus="true"/>
            <input name="password2" type="password" class="form-control" placeholder="Repeat password" autofocus="true"/>

            <c:out value="${message}"/>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>

        </div>
    </form>


</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/webapp/resources/js/bootstrap.min.js"></script>
</body>
</html>

