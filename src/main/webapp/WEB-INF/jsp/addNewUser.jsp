

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Add user</title>
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
                            <a href="addUsers.htm">Add new user</a>
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
                    <a href="#"><i class="fa fa-edit "></i>Process bills</a>
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
                <div class="col-md-3">
                    <h5>Add a new user</h5>
                <form method="POST" action="addNewUser.htm" class="form-signin">


                    <div class="form-group">
                        <label>Name</label>
                        <input name="name" type="text" class="form-control" placeholder="Name" autofocus="true"/>
                        <label>IC number</label>
                        <input name="icNumber" type="text" class="form-control" placeholder="IC Number"/>
                        <label>CNP</label>
                        <input name="cnp" type="text" class="form-control" placeholder="CNP"/>
                        <label>Address</label>
                        <input name="address" type="text" class="form-control" placeholder="Address"/>
                        <label>Age</label>
                        <input name="age" type="text" class="form-control" placeholder="Age"/>

                        <h5>Account information</h5>

                        <label>Account number</label>
                        <input name="accNumber" type="text" class="form-control" placeholder="Account number" autofocus="true"/>
                        <label>Amount</label>
                        <input name="amount" type="text" class="form-control" placeholder="Initial amount"/>
                        <label>Account type</label>
                        <input name="type" type="text" class="form-control" placeholder="Type: spending or saving" autofocus="true"/>
                        <br>

                        <c:out value="${message}"/>

                        <button class="btn btn-lg btn-primary btn-block" type="submit">Add user</button>
                    </div>

                </form>
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

