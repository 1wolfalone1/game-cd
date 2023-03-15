<%-- 
    Document   : order_left
    Created on : Mar 13, 2023, 7:46:53 AM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/lib.jsp" %>
<div class="content_control">
    <c:set var="searchInfo" value="${sessionScope.searchInfo}"/>
    <div class="content_control-fixed" id="divFixed">
        <div class="game_filter_control control">
            <h1 class="title">Filter order</h1>
            <form class="action" action="admin-order-action" method="GET">
                <div class="type_search search_control top_search_date">
                    Type search date:
                    <select
                        class="select_category"
                        style="margin-left: auto"
                        name="type_search_date"
                        >
                        <option value="0" ${searchInfo.typeSearchDate == 0? 'selected' : ''}>None</option>
                        <option value="1" ${searchInfo.typeSearchDate == 1? 'selected' : ''}>Order date</option>
                        <option value="2" ${searchInfo.typeSearchDate == 2? 'selected' : ''}>Ship date</option>
                    </select>
                </div>
                <div class="search_control">
                    <span>From:</span>
                    <input type="date" style="margin-left: auto" name="form_date" value="${searchInfo.from}"/>
                </div>
                <div class="search_control bot_search_date">
                    <span>To:</span>
                    <input type="date" style="margin-left: auto" name="to_date" value="${searchInfo.to}"/>
                </div>
                <div class="search_control">
                    Search by user id:
                    <input type="text" style="margin-left: auto" name="user_id" value="${searchInfo.userId == 0 ? '' : searchInfo.userId}"/>
                </div>
                <div class="search_control">
                    Search by order id:
                    <input type="text" style="margin-left: auto" name="order_id" value="${searchInfo.orderId == 0 ? '' : searchInfo.userId}"/>
                </div>
                <div class="search_control">
                    Status:
                    <select class="select_category" style="margin-left: auto" name="status">
                        <option value="0"  ${searchInfo.status == 0? 'selected' : ''}>All</option>
                        <option value="1" ${searchInfo.status == 1? 'selected' : ''}>Processing</option>
                        <option value="2" ${searchInfo.status == 2? 'selected' : ''}>Completed</option>
                        <option value="3" ${searchInfo.status == 3? 'selected' : ''}>Cancel</option>
                    </select>
                </div>
                <button class="button-54 filter_game_button" name="button" value="filter">
                    Filter
                </button>
            </form>
        </div>
        <form class="game_filter_control control"  action="admin-order-action" method="GET">
            <h1 class="title">
                <button class="button-54 add_game_button" role="button" name="button" value="view_all">
                    View all order
                </button>
            </h1>
        </form>
    </div>
</div>

<script>
    document.getElementById("order_management").style.pointerEvents = "none";
    document.getElementById("order_management").style.backgroundColor = "rgba(43, 4, 57, 0.514)";
    document.getElementById("order_management").style.color = "rgb(97, 206, 97)";
</script>        