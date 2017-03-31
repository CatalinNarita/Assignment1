

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

    <script>
        function toggle(source) {
            checkboxes = document.getElementsByName('checked');
            for(var i=0, n=checkboxes.length;i<n;i++) {
                checkboxes[i].checked = source.checked;
            }
        }
    </script>
    <script>

        var ALERT_TITLE = "Confirm delete";
        var ALERT_OK_BUTTON_TEXT = "Ok";
        var ALERT_CANCEL_BUTTON_TEXT = "Cancel";

        if(document.getElementById) {
            window.alert = function(txt) {
                createCustomAlert(txt);
            }
        }

        function createCustomAlert(txt) {
            d = document;

            if(d.getElementById("modalContainer")) return;

            mObj = d.getElementsByTagName("body")[0].appendChild(d.createElement("div"));
            mObj.id = "modalContainer";
            mObj.style.height = d.documentElement.scrollHeight + "px";

            alertObj = mObj.appendChild(d.createElement("div"));
            alertObj.id = "alertBox";
            if(d.all && !window.opera) alertObj.style.top = document.documentElement.scrollTop + "px";
            alertObj.style.left = (d.documentElement.scrollWidth - alertObj.offsetWidth)/2 + "px";
            alertObj.style.visiblity="visible";

            h1 = alertObj.appendChild(d.createElement("h1"));
            h1.appendChild(d.createTextNode(ALERT_TITLE));

            msg = alertObj.appendChild(d.createElement("p"));
            msg.appendChild(d.createTextNode(txt));
            msg.innerHTML = txt;

            btn1 = alertObj.appendChild(d.createElement("a"));
            btn1.id = "closebtn";
            btn1.appendChild(d.createTextNode(ALERT_OK_BUTTON_TEXT));
            btn1.href = "#";
            btn1.focus();
            btn1.onclick = function() {document.getElementById("ask").submit();}

            btn2 = alertObj.appendChild(d.createElement("a"));
            btn2.id = "cancelbtn";
            btn2.appendChild(d.createTextNode(ALERT_CANCEL_BUTTON_TEXT));
            btn2.href = "#";
            btn2.focus();
            btn2.onclick = function() { removeCustomAlert();return false; }

            alertObj.style.display = "block";

        }

        function removeCustomAlert() {
            document.getElementsByTagName("body")[0].removeChild(document.getElementById("modalContainer"));
        }


    </script>

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
                    <form id="ask" action="account.htm" method="POST">
                    <table class="table table-striped table-bordered table-hover">

                        <thead>
                        <tr>
                            <th>Account Name</th>
                            <th>Account Number</th>
                            <th>Type</th>
                            <th>Amount</th>
                            <th>Creation Date</th>
                            <th><input type="checkbox" onClick="toggle(this)" name="selectAll">&nbsp&nbspSelectAll </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items = "${accountList}" var = "account">
                            <tr>
                                <c:set var="count" value="${count + 1}" scope="page"/>
                                <td><a href = "editAccount.htm?accountId=${account.accountId}"><c:out value = "Account ${count}"></c:out></a></td>
                                <td><c:out value = "${account.accNumber}"></c:out></td>
                                <td><c:out value = "${account.type}"></c:out></td>
                                <td><c:out value = "${account.amount}"></c:out></td>
                                <td><c:out value = "${account.creationDate}"></c:out></td>
                                <td><input id="editButton" type="checkbox" name="checked" value="${account.accountId}"></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    </form>
                    <c:out value="${message}"/>
                    <!--form method="GET" action="newAccount.htm" class="form-signin">
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Add account</button>
                        <submit type="hidden" name="accountId" value="${account.accountId}"></submit>
                    </form-->



                    <a href = "newAccount.htm?">Add new account</a>
                    <br>


                </div>


            </div>


            <!-- /. ROW  -->
            <hr />
            <div class="row">
                <div class="col-md-3">
                    <input class="btn btn-lg btn-primary btn-block" type="button" value="Delete selected accounts" name="delete" onclick="alert('Are you sure?');">
                </div>
            </div>




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

