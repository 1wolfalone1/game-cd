<%-- 
    Document   : admin_user_list
    Created on : Mar 14, 2023, 8:58:27 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/lib.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User list</title>
        <link rel="stylesheet" href="${css}admin_user_list.css" />

    </head>
    <body>
        <div id="header_space"></div>

        <div class="content">
            <%@include file="/common/admin/user_left.jsp" %>
            <div class="content_product">
                <h1 class="header_product">View user</h1>

                <div class="table">
                    <div class="table_header">
                        <h3>ID</h3>
                        <h3>Full Name</h3>
                        <h3>Phone</h3>
                        <h3>Address</h3>
                        <h3>View orders</h3>
                    </div>

                    <div class="table_content">
                        <c:if test="${requestScope.find_one != null}">
                            <c:set var="user" value="${requestScope.user_profile}"/>
                            <c:if test="${user != null}">
                                <form action="admin-user-action">
                                    <div>
                                        <span>${user.id}</span>
                                    </div>
                                    <div>
                                        <span>${user.fullName}</span>
                                    </div>
                                    <div>
                                        <span>${user.phone}</span>
                                    </div>
                                    <div>
                                        <span>${user.address}</span>
                                    </div>
                                    <div>
                                        <input type="hidden" name="user_id" value="${user.id}">
                                        <button class="button-54 filter_game_button" name="button" value="view_all_orders">
                                            View
                                        </button>
                                    </div>
                                </form>
                            </c:if>
                        </c:if>
                        <c:if test="${requestScope.listUsers != null}">
                            <c:set var="list" value="${requestScope.listUsers}"/>
                            <c:forEach var="user" items="${list}">
                                <form action="admin-user-action">
                                    <div>
                                        <span>${user.id}</span>
                                    </div>
                                    <div>
                                        <span>${user.fullName}</span>
                                    </div>
                                    <div>
                                        <span>${user.phone}</span>
                                    </div>
                                    <div>
                                        <span>${user.address}</span>
                                    </div>
                                    <div>
                                        <input type="hidden" name="user_id" value="${user.id}">
                                        <button class="button-54 filter_game_button" name="button" value="view_all_orders">
                                            View
                                        </button>
                                    </div>
                                </form>
                            </c:forEach>

                        </c:if>
                    </div>
                    <form class="content_slider" action="admin-user-search" method="Post">

                    </form>
                </div>
            </div>
        </div>
        <script src="${js}admin.js">
        </script>
        <script>
            <c:if test="${pagingUser != null && pagingUser.isPage()}">
            Slider({
                sliderSelector: '.content_slider',
                maxItems: ${pagingUser.maxItems},
                maxSlides: ${pagingUser.maxSlides},
                current: ${pagingUser.currentPage}
            });
            </c:if>
            ${sessionScope.pagingUser.setPage(false)}
        </script>
    </body>
</html>
