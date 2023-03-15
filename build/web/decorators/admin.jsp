<%-- 
    Document   : admin
    Created on : Mar 6, 2023, 7:42:23 AM
    Author     : ASUS
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="dec" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@include file="/common/lib.jsp" %>
<!DOCTYPE html>
<html>
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
        <link rel="stylesheet" href="${css}admin_header.css" />
        <link rel="stylesheet" href="${css}footer.css" />       
        <link rel="stylesheet" href="${css}admin_left_control.css" />
        <link rel="stylesheet" href="${css}admin_workspace.css" />
        <dec:head/>

    </head>
    <body>
        <%@ include file="/common/admin/header.jsp" %>

        <dec:body/>

        <%@ include file="/common/admin/footer.jsp" %>
    </body>
</html>
