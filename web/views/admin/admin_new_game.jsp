<%-- 
    Document   : admin_new_game
    Created on : Mar 10, 2023, 8:46:07 PM
    Author     : ASUS
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@include file="/common/lib.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create game</title>
        <link rel="stylesheet" href="${css}admin_game_view.css" />

    </head>
    <body>
        <c:set var="allCate" value="${requestScope.listCate}" />
        <div id="header_space"></div>
        <div class="content">
            <%@include file="/common/admin/game_left.jsp" %>
            <div class="content_product">
                <h1 class="header_product">Create Game</h1>
                <div class="game_view">
                    <form class="content_game" 
                          action="admin-game-create-game"
                          method="POST"
                          enctype="multipart/form-data"
                          id="usrform"
                          >
                        <div class="control_change_game">
                            <span>Name:</span> <input type="text" value="" name="name_create"/>
                        </div>
                        <div class="control_change_game">
                            <div id="list1" class="dropdown-check-list" tabindex="100">
                                <span class="anchor">Select category</span>
                                <ul class="items">
                                    <c:forEach var="cate" items="${allCate}">
                                        <li>
                                            <input type="checkbox" name="category_create"
                                                   value="${cate.id}"
                                                   />${cate.name}
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                        <div class="control_change_game">
                            <span>Price:</span> <input type="text" value="" name="price_create"/>
                        </div>
                        <div class="control_change_game">
                            <span>Quantity:</span> <input type="text" value="" name="quantity_create"/>
                        </div>
                        <div
                            style="border: 1px rgb(180, 123, 123) solid; width: 500px"
                            >
                            <h2 style="padding: 10px">Add image</h2>
                            <input type="file" name="img_create"/>
                        </div>
                        <div class="control_change_game">
                            <span>Description: </span>
                            <textarea
                                style="
                                background-color: #1e464558;
                                font-size: 15px;
                                color: rgb(157, 227, 51);
                                padding: 5px;
                                "
                                name="description_create"
                                form="usrform"
                                cols="70"
                                rows="25"
                                id="description_game"
                                ></textarea>
                        </div>
                        <div style="color:rgb(157, 227, 51);">
                            <button  style="color:rgb(157, 227, 51);" id="create_game_infor" type="submit" class="button-54">Save</button>
                            <button  style="color:rgb(154, 182, 112);" type="reset" class="button-54">Reset</button>
                        </div>
                        <h3 style="color: olive; padding: 20px; text-align: center;">${requestScope.status_create_game}</h3>
                    </form>
                </div>
            </div>
        </div>
        <script src="${js}select.js"></script>
        <script>
            var checkList = document.getElementById("list1");
            checkList.getElementsByClassName("anchor")[0].onclick = function (e) {
                if (checkList.classList.contains("visible"))
                    checkList.classList.remove("visible");
                else
                    checkList.classList.add("visible");
            };
            
            document.querySelector("#create_game_infor").onclick = (e) => {
                document.querySelector("#description_game").value = document
                        .querySelector("#description_game")
                        .value.split(/\n+/)
                        .map((e) => {
                            return "<p>" + e + "</p>";
                        })
                        .join("");
            };
        </script>
    </body>
</html>
