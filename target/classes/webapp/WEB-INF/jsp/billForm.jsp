

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="count" value="0" scope="page" />


<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Cache-Control" content="no-store" />
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700|Lato:400,100,300,700,900' rel='stylesheet' type='text/css'>
    <link href="${pageContext.request.contextPath}/resources/css/editor.css" rel="stylesheet" >
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Users</title>
    <!-- BOOTSTRAP STYLES-->
    <link href="${contextPath}/resources/assets/css/bootstrap.css" rel="stylesheet" />
    <!-- FONTAWESOME STYLES-->
    <link href="${contextPath}/resources/assets/css/font-awesome.css" rel="stylesheet" />
    <!-- CUSTOM STYLES-->
    <link href="${contextPath}/resources/assets/css/custom.css" rel="stylesheet" />
    <!-- GOOGLE FONTS-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />

</head>
<body>


<div id="wrapper">
    <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="adjust-nav">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#"><i class="fa fa-square-o "></i>&nbsp;TWO PAGE</a>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li></li>
                    <li></li>
                    <li>
                        <form method="GET" action = "logout.htm">
                            <button class="btn btn-lg btn-primary btn-block" type="submit">Logout</button>
                        </form>
                    </li>
                </ul>
            </div>

        </div>
    </div>
    <!-- /. NAV TOP  -->
    <nav class="navbar-default navbar-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav" id="main-menu">
                <li class="text-center user-image-back">
                    <img src="${contextPath}/resources/assets/img/find_user.png" class="img-responsive" />

                </li>



                <li>
                    <a href="#"><i class="fa fa-edit "></i>Users<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href = "viewUsers.htm">View Users</a>
                        </li>
                        <li>
                            <a href="addNewUser.htm">Add new user</a>
                        </li>
                        <li>
                            <a href="updateUser.htm">Update user</a>
                        </li>
                        <li>
                            <a href="accountList.htm">Accounts</a>
                        </li>
                    </ul>
                </li>

                <li>
                    <a href="transferMoneyClientList.htm"><i class="fa fa-table "></i>Transfer Money</a>
                </li>
                <li>
                    <a href="viewBills.htm"><i class="fa fa-edit "></i>Process bills</a>
                </li>



            </ul>

        </div>

    </nav>
    <!-- /. NAV SIDE  -->
    <div id="page-wrapper">
        <div id="page-inner">
            <div class="row">
                <div class="col-md-12">
                    <h2>Employee Dashboard</h2>
                </div>
            </div>
            <!-- /. ROW  -->
            <hr />

            <div class="row">
                <div class="col-md-6">
                    <h5>Registered users</h5>
                    <form action="bill.htm" method="POST" class="form-signin">
                        Processing this bill will need ${needed}.
                        <br>
                        <div class="form-group">
                            <label>Account Number</label>
                            <input name="accNumber" type="text" class="form-control" autofocus="true"/>
                        </div>
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Process bill</button>
                    </form>
                    <c:out value="${message}"/>
                </div>
            </div>


            <!-- /. ROW  -->
            <hr />





        </div>
        <!-- /. PAGE INNER  -->
    </div>
    <!-- /. PAGE WRAPPER  -->
</div>
<!-- /. WRAPPER  -->
<!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
<!-- JQUERY SCRIPTS -->
<script src="${contextPath}/resources/assets/js/jquery-1.10.2.js"></script>
<!-- BOOTSTRAP SCRIPTS -->
<script src="${contextPath}/resources/assets/js/bootstrap.min.js"></script>
<!-- METISMENU SCRIPTS -->
<script src="${contextPath}/resources/assets/js/jquery.metisMenu.js"></script>
<!-- CUSTOM SCRIPTS -->
<script src="${contextPath}/resources/assets/js/custom.js"></script>


</body>
</html>

