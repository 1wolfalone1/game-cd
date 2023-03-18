<%-- 
    Document   : user_left
    Created on : Mar 14, 2023, 8:58:04 PM
    Author     : ASUS
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/lib.jsp" %>
<div class="content_control">
    <div class="content_control-fixed" id="divFixed">
        <div class="game_filter_control control">
            <h1 class="title">Filter user</h1>
            <form class="action" action="admin-user-search" method="GET">
                <div class="search_control">
                    Search by user id:
                    <input type="text" style="margin-left: auto" name="user_id" value="${param.user_id}"/>
                </div>
                <div style="display: flex; justify-content: center">
                    <button class="button-54 filter_game_button" name="button" value="find">
                        Find
                    </button>
                    <button class="button-54 filter_game_button" name="button" value="view_all">
                        View all
                    </button>
                </div>
            </form>
        </div>
        <div class="game_filter_control control">
            <h1 class="title">Filter account</h1>
            <form class="action" action="admin-account-search" method="GET">
                <div class="search_control">
                    Search by account id:
                    <input type="text" style="margin-left: auto" name="acc_id"  value="${param.acc_id}"/>
                </div>
                <div class="search_control" style="margin: 10px; display: flex; justify-content: space-between; align-items: center;">
                    Status:
                    <select name="status" style="padding: 5px; width: 200px;font-size: 20px; background-color: rgba(103, 92, 79, 0.285); color: greenyellow;">
                        <option value="2" ${param.status == 2 ? 'selected': ''} style="background-color: rgba(36, 58, 58, 0.982);">All</option>
                        <option value="1" ${param.status == 1 ? 'selected': ''} style="background-color: rgba(36, 58, 58, 0.982);">Active</option>
                        <option value="0" ${param.status == 0 ? 'selected': ''} style="background-color: rgba(36, 58, 58, 0.982);">Inactive</option>
                    </select>
                </div>
                <div style="display: flex; justify-content: center">
                    <button class="button-54 filter_game_button" name="button" value="find">
                        Find
                    </button>
                    <button class="button-54 filter_game_button" name="button" value="view_all">
                        View all
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    document.getElementById("user_management").style.pointerEvents = "none";
    document.getElementById("user_management").style.backgroundColor = "rgba(43, 4, 57, 0.514)";
    document.getElementById("user_management").style.color = "rgb(97, 206, 97)";
</script>    