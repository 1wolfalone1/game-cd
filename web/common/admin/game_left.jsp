<%-- 
    Document   : game
    Created on : Mar 9, 2023, 10:03:48 AM
    Author     : ASUS
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/lib.jsp" %>
<div class="content_control">
    <div class="content_control-fixed" id="divFixed">
        <form class="game_filter_control control" action="admin-game-control">
            <h1 class="title">
                <button class="button-54 add_game_button" role="button" name="button" value="view_all">
                    View all games
                </button>
            </h1>
        </form>
        <div class="game_filter_control control">
            <h1 class="title">Filter game</h1>
            <form class="action" action="admin-game-control">
                <div class="select_filter_category" style="display: flex; justify-content: space-between;">
                    Category:
                    <select class="select_category" name="categorySearch">
                        <option value="0">--Category--</option>
                        <option value="0" ${requestScope.searchCategory == 0 ? 'selected' : ''}>All categories</option>
                        <c:forEach var="cate" items="${requestScope.listCate}">
                            <option value="${cate.id}" ${requestScope.searchCategory == cate.id ? 'selected' : ''}>${cate.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="search_control" style="display: flex; justify-content: space-between;">
                    <span>Search by name:</span><input type="text" name="nameSearch" value="${requestScope.searchName}"/>
                </div>
                <div class="search_control"  style="display: flex; justify-content: space-between;">
                    <span>Search by id:</span> <input type="text" name="idSearch" 
                                                      value="${requestScope.searchId == 0? '' : requestScope.searchId}"/>
                </div>
                <div class="search_control"  style="display: flex; justify-content: center;">
                    <button class="button-54 filter_game_button" name="button" value="filter">
                        Filter
                    </button>
                </div>
                <!--info-->

            </form>
        </div>
        <form class="game_filter_control control" action="admin-game-control">
            <h1 class="title">
                <button class="button-54 add_game_button" role="button" name="button" value="add">
                    Add more game
                </button>
            </h1>
        </form>
        <div class="game_filter_control control">
            <h1 class="title">Category</h1>
            <form
                class="action"
                style="display: flex; justify-content: center"
                action="admin-game-control"
                >
                <button class="button-54 filter_game_button" name="button" value="view_category">
                    Manage category
                </button>
            </form>
        </div>
    </div>
</div>
<script>
    document.getElementById("game_management").style.pointerEvents = "none";
    document.getElementById("game_management").style.backgroundColor = "rgba(43, 4, 57, 0.514)";
    document.getElementById("game_management").style.color = "rgb(97, 206, 97)";
</script>             