<%-- Document : login Created on : Feb 23, 2023, 10:20:15 PM Author : ASUS --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@include file="/common/lib.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Login</title>
        <link rel="stylesheet" href="${css}login.css" />
    </head>
    <body>
        <div id="header_space"></div>

        <form action="login" class="login_form" method="POST">
            <div class="form_login_group form_login_group-title">
                <h1>Welcome back,</h1>
                <h2>please login to your account</h2>
            </div>
            <div class="form_login_group form_login_group-email">
                <label for="email-login" class="login_input-title"> Email</label>
                <input
                    type="email"
                    class="login_email"
                    id="email-login"
                    name="txtemail"
                    placeholder="Use your email..."
                    value="${requestScope.remainUserName}"
                    />
            </div>
            <div class="form_login_group form_login_group-password">
                <label for="login_password" class="login_input-title"
                       >Password</label
                >
                <input
                    type="password"
                    class="login_password"
                    name="txtpassword"
                    placeholder="Use your password..."
                    />
                <h6 class="error_message">${requestScope.errorlogin}</h6>
                <h6 class="error_message" style="font-size: 15px !important;">${requestScope.ban_message}</h6>
            </div>
            <div class="form_login_group form_login_group-check">
                <label for="remember_me">
                    <input type="checkbox" class="login_checkbox" id="remember_me" name="remember_password"/>
                    <span> remember me</span>
                </label>
                <a href="#"><span>Forgot password</span></a>
            </div>
            <div class="form_login_group form_login_group-link"></div>
            <div class="form_login_group form_login_group-button">
                <button
                    type="submit"
                    class="login_button glow-on-hover"
                    name="button"
                    value="signin"
                    >
                    Sign in
                </button>
                <button
                    type="submit"
                    class="signup_button glow-on-hover"
                    name="button"
                    value="signup"
                    >
                    Create account
                </button>

            </div>
            <div>
                <h3 style="text-align: center; color: orangered; font-size: 25px; margin-top: 10px;">${sessionScope.tmp_message}</h3>

            </div>
            <%
                request.getSession().removeAttribute("tmp_message");
            %>
        </form>
        <script>
            document.getElementById("login").style.pointerEvents = "none";
            document.getElementById("login").style.backgroundColor =
                    "rgba(43, 4, 57, 0.514)";
            document.getElementById("login").style.color = "rgb(97, 206, 97)";

        </script>
    </body>
</html>
