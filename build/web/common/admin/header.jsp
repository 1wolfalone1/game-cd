<%-- 
    Document   : header
    Created on : Mar 6, 2023, 7:35:16 AM
    Author     : ASUS
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<header class="navbar">
    <div class="navbar_logo">
        <img src="./asset/img/xbox1.png" alt="logo" />
        <div class="logoText"><span id="logoText"></span></div>
    </div>
    <c:set var="admin" value="${sessionScope.account}"/>
    <div class="navbar__right">
        <div class="top_admin_header">
            <form action="profile" class="toggle_status" id="form_change_mode">
                <label class="switch">
                    <input type="checkbox" checked id="change_mode"/>
                    <span class="slider round"></span>
                </label>
                <a href="" style="width: 1px; height: 1px"></a>
                <span>ADMIN MODE</span>
            </form>
            <h2 class="name_admin" style="width: 450px; display: flex; justify-content: center; margin: 0; padding: 0;">
                <span style="color:rgb(157, 142, 123);">ADMIN:</span> <span>${account.user.fullName}</span>
                <div style="width: 40px; display: flex; justify-content: center; align-items: center;">
                    <img src="${avatar}${account.avatarPath}" alt="" style="width: 100%; margin-left: 20px; border-radius: 50%;" />
                </div>
            </h2>
            <form action="logout" class="action_admin_account">
                <button class="button-54" role="button">Log out</button>
            </form>
        </div>
        <form class="navbar_actions">
            <ul>
                <a href="${r}user-management" class="button-54" role="button" id="user_management"> 
                    <span>User management</span>
                    <i class="fa-solid fa-plane-departure"></i>
                </a>
                <a href="${r}orders" class="button-54" role="button" id="order_management">
                    <span>Order management</span>
                    <i class="fas fa-store"></i>
                </a>
                <a href="${r}admin" class="button-54" role="button" id="game_management">
                    <span>Game management</span>
                    <i class="fas fa-shopping-cart"></i>
                </a>
            </ul>
        </form>
    </div>
</header>
<script>
    document.querySelector("#change_mode").onclick = (e) => {
        setTimeout(() => {
            document.querySelector("#form_change_mode").submit();
        }, 500);
    };
</script>
