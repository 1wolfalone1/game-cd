<%-- 
    Document   : web
    Created on : Feb 23, 2023, 8:49:38 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="dec" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<%@include file="/common/lib.jsp" %>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title><dec:title default="GameCDDDDD" /></title>
        <link rel="icon" href="${img}xbox1.png"/>

        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
            integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
            />
        <link rel="stylesheet" href="${css}header copy.css" />
        <link rel="stylesheet" href="${css}footer.css" />
        <dec:head/>

    </head>
    <body>


        <%@ include file="/common/web/header.jsp" %>

        <dec:body/>


        <%@ include file="/common/web/footer.jsp" %>
        <script src="${js}header.js"></script>
    </body>
</html>