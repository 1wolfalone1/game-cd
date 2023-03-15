<%-- 
    Document   : admin_game_list
    Created on : Mar 6, 2023, 7:38:56 AM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/lib.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Game management</title>
        <link rel="stylesheet" href="${css}admin_game_control.css" />
    </head>
    <body>
        <div id="header_space"></div>
        <div class="content">
            <%@include file="/common/admin/game_left.jsp" %>
            <div class="content_product">
                <h1 class="header_product">View all games</h1>
                <div class="table">
                    <div class="table_header">
                        <h3>View</h3>
                        <h3>ID</h3>
                        <h3>Name</h3>
                        <h3>Price</h3>
                        <h3>Quantity</h3>
                        <h3>Status</h3>
                        <h3>Save</h3>
                        <h3>Cancel</h3>
                    </div>
                    <div class="table_content">
                        <c:forEach var="game" items="${requestScope.listGames}">
                            <form action="game-management" method="POST">
                                <input type="hidden" name="id_game" value="${game.id}">
                                <button class="button-54 filter_game_button"name="button" value="view">View</button>
                                <span>${game.id}</span>
                                <input type="text" name="name" value="${game.name}" />
                                <input type="text" name="price" value="${game.price}" />
                                <input type="text" name="quantity" value="${game.quantity}" />
                                <input type="text" name="status" value="${game.status}" disabled style="border: transparent;"/>
                                <button class="button-54 filter_game_button" name="button" value="save_game">Save</button>
                                <button type="reset" class="button-54 filter_game_button">
                                    Cancel
                                </button>
                                <!--info paging-->
                                <input type="hidden" name="current_page" value="${requestScope.currentPage}">          
                                <input type="hidden" name="max_items" value="${requestScope.maxItems}">
                                <input type="hidden" name="max_slides" value="${requestScope.maxSlides}">
                                <input type="hidden" name="action_list" value="${requestScope.action_list}">
                                <input type="hidden" name="nameSearch" value="${requestScope.searchName}">
                                <input type="hidden" name="categorySearch" value="${requestScope.searchCategory}">
                                <input type="hidden" name="idSearch" value="${requestScope.searchId}">
                            </form>
                        </c:forEach>
                    </div>
                    <form class="content_slider" action="admin-game-paging" method="Post">
                        <input type="hidden" name="current_page" value="${requestScope.currentPage}">          
                        <input type="hidden" name="max_items" value="${requestScope.maxItems}">
                        <input type="hidden" name="max_slides" value="${requestScope.maxSlides}">
                        <input type="hidden" name="action_list" value="${requestScope.action_list}">
                        <input type="hidden" name="nameSearch" value="${requestScope.searchName}">
                        <input type="hidden" name="categorySearch" value="${requestScope.searchCategory}">
                        <input type="hidden" name="idSearch" value="${requestScope.searchId}">
                    </form>
                </div>
            </div>
        </div>
        <script src="${js}admin.js">
        </script>
        <script>
            Slider({
                sliderSelector: '.content_slider',
                maxItems: ${requestScope.maxItems},
                maxSlides: ${requestScope.maxSlides},
                current: ${requestScope.currentPage}
            });
        </script>
    </body>
</html>
