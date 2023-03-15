<%-- 
    Document   : something_wrong
    Created on : Mar 11, 2023, 10:51:00 AM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/lib.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Something wrong</title>
    </head>
    <body>
        <div id="header_space"></div>
        <div class="content" style="display: flex; justify-content: center;">
            <div>
                <img src="${img}error-icon-32.png" alt="">
                <h1 style="color:rgb(180, 86, 57)">Some thing wrong!!!</h1>
            </div>
        </div>
    </body>
</html>
