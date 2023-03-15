<%-- 
    Document   : header
    Created on : Feb 23, 2023, 8:46:45 PM
    Author     : ASUS
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header class="navbar">
    <div class="navbar_logo">
        <img src="${img}xbox1.png" alt="logo" />
        <div class="logoText"><span id="logoText"></span></div>
        <style>
            a.none{
                text-decoration:  none;
            }
        </style>
        
    </div>
    <div class="navbar__right">
        <div class="navbar_actions">
            <ul>
                <a href="${r}" id="aboutus">

                    <li>
                        <span>About us</span>
                        <i class="fa-solid fa-door-open"></i>
                    </li>
                </a>


                <a href="${r}market-page" id="marketplace">
                    <li class="market">
                        <span>Maket place</span>
                        <i class="fas fa-store"></i>
                    </li>
                </a>



                <a href="${r}view-cart" id="viewcart">
                    <li>
                        <span>View cart</span>
                        <i class="fas fa-shopping-cart"></i>
                    </li>
                </a>

                <c:if test="${sessionScope.account == null }">
                    <a href="${r}login-page" id="login">
                        <li>
                            <span>Sign in</span>
                            <i class="fa-solid fa-arrow-right-to-bracket"></i>
                        </li>
                    </a>

                    <a href="${r}sign-up-page" id="signup">
                        <li>
                            <span>Sign up</span>
                            <i class="fa-sharp fa-solid fa-user-plus"></i>
                        </li>
                    </a>
                </c:if>
                <c:if test="${sessionScope.account != null }">
                    <a href="${r}order-list" id="orderss">
                        <li>
                            <span>Check orders</span>
                            <i class="fa-solid fa-truck-fast"></i>
                        </li>
                    </a>
                    <a href="${r}profile" id="viewprofile">
                        <li>
                            <span>View Profile</span>
                            <i class="fa-solid fa-address-card"></i>
                        </li>
                    </a>
                    <a href="${r}logout" class="logout_button">
                        <li>
                            <span>Logout</span>
                            <i class="fa-solid fa-right-from-bracket"></i>
                        </li>
                    </a>
                </c:if>  
            </ul>
        </div>
          
        <form
            action="search-game"
            class="navbar_search webflow-style-input"
            method="GET"
            >
            <c:if test="${requestScope.listCate != null}">
                <div class="custom-select" style="width:200px;">
                    <select name="category">
                        <option value="0">--Category--</option>
                        <option value="0" ${requestScope.searchCategory == 0 ? 'selected' : ''}>All categories</option>
                        <c:forEach var="cate" items="${requestScope.listCate}">
                            <option value="${cate.id}" ${requestScope.searchCategory == cate.id ? 'selected' : ''}>${cate.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </c:if>

            <input
                class=""
                type="text"
                placeholder="Search game here"
                name="search_game"
                id="input_search"
                value="${requestScope.searchName}"
                />
            <button type="submit" id="submit_search">
                <i class="fa-brands fa-searchengin"></i>
            </button>
        </form>
        <c:if test="${sessionScope.account != null }">
            <div class="user_profile">
                <span>${sessionScope.account.user.fullName}</span>
                <c:if test="${sessionScope.account.avatarPath == null}"> 
                    <img src="${avatar}default.png" alt="" class="user_avatar"/>
                </c:if>
                <c:if test="${sessionScope.account.avatarPath != null}"> 
                    <img src="${avatar}${sessionScope.account.avatarPath}" alt="" class="user_avatar"/>
                </c:if>
            </div>

        </c:if>        
    </div>
</header>