<%-- Document : admin_view_game Created on : Mar 6, 2023, 7:58:48 AM Author :
ASUS --%> <%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@include file="/common/lib.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Game</title>
        <link rel="stylesheet" href="${css}admin_game_view.css" />
    </head>
    <body>
        <c:set var="game" value="${requestScope.game}" />
        <c:set var="listCate" value="${requestScope.game.listCategory}" />
        <c:set var="allCate" value="${requestScope.allCate}" />
        <c:set var="imgs" value="${requestScope.game.imgPaths}"/>
        <div id="header_space"></div>
        <div class="content">
            <%@include file="/common/admin/game_left.jsp" %>
            <div class="content_product">
                <form action="admin-game-paging" method="POST">
                    <button
                        class="button-54"
                        name="button"
                        value="${requestScope.currentPage}"
                        style="color: #0ba713"
                        id="back_page"
                        >
                        Back
                    </button>
                    <h1 class="header_product">Game #${game.id}</h1>
                    <input
                        type="hidden"
                        name="current_page"
                        value="${requestScope.currentPage}"
                        id="back_page_current"
                        />
                    <input
                        type="hidden"
                        name="max_items"
                        value="${requestScope.maxItems}"
                        />
                    <input
                        type="hidden"
                        name="max_slides"
                        value="${requestScope.maxSlides}"
                        />
                    <input
                        type="hidden"
                        name="action_list"
                        value="${requestScope.action_list}"
                        />
                    <input
                        type="hidden"
                        name="nameSearch"
                        value="${requestScope.searchName}"
                        />
                    <input
                        type="hidden"
                        name="categorySearch"
                        value="${requestScope.searchCategory}"
                        />
                    <input
                        type="hidden"
                        name="idSearch"
                        value="${requestScope.searchId}"
                        />
                </form>

                <div class="game_view">
                    <div class="add_img">
                        <div id="img_show">
                            <img src="${imgproduct}${imgs[0]}" alt="" />
                        </div>
                        <div
                            style="border: 1px rgb(180, 123, 123) solid; width: 100%"
                            class="img_list"
                            >
                            <h2 style="padding: 10px; color: rgb(140, 124, 104)">
                                List image
                            </h2>
                            <div class="img_items">

                                <c:forEach var="img" items="${imgs}">
                                    <form action="admin-game-change-img" method="GET"
                                          style="display: flex; justify-content: space-between; align-items: center;">
                                        <input type="hidden" name="id_game" value="${game.id}">
                                        <button class="button-54 view_img" value="${imgproduct}${img}">view</button>
                                        <span style="width: 280px; display:-webkit-box;
                                              -webkit-line-clamp:1;
                                              -webkit-box-orient: vertical;
                                              overflow: hidden;
                                              text-overflow: ellipsis;
                                              word-break: break-word;">${img}</span>
                                        <button class="button-54" name="button" value="${img}">remove</button>
                                        <!--paging-->
                                        <input
                                            type="hidden"
                                            name="current_page"
                                            value="${requestScope.currentPage}"
                                            />
                                        <input
                                            type="hidden"
                                            name="max_items"
                                            value="${requestScope.maxItems}"
                                            />
                                        <input
                                            type="hidden"
                                            name="max_slides"
                                            value="${requestScope.maxSlides}"
                                            />
                                        <input
                                            type="hidden"
                                            name="action_list"
                                            value="${requestScope.action_list}"
                                            />
                                        <input
                                            type="hidden"
                                            name="nameSearch"
                                            value="${requestScope.searchName}"
                                            />
                                        <input
                                            type="hidden"
                                            name="categorySearch"
                                            value="${requestScope.searchCategory}"
                                            />
                                        <input
                                            type="hidden"
                                            name="idSearch"
                                            value="${requestScope.searchId}"
                                            />
                                        <!--/paging-->
                                    </form>
                                </c:forEach>


                            </div>
                        </div>
                        <form
                            style="border: 1px rgb(180, 123, 123) solid; width: 100%"
                            method="POST"
                            enctype="multipart/form-data"
                            action="admin-game-change-img"
                            >
                            <input type="hidden" name="id_game" value="${game.id}">
                            <h2 style="padding: 10px">Add image</h2>
                            <input type="file" name="img"/>
                            <button type="submit" class="button-54">Save</button>
                            <button type="reset" class="button-54">Reset</button>
                            <!--paging-->
                            <input
                                type="hidden"
                                name="current_page"
                                value="${requestScope.currentPage}"
                                />
                            <input
                                type="hidden"
                                name="max_items"
                                value="${requestScope.maxItems}"
                                />
                            <input
                                type="hidden"
                                name="max_slides"
                                value="${requestScope.maxSlides}"
                                />
                            <input
                                type="hidden"
                                name="action_list"
                                value="${requestScope.action_list}"
                                />
                            <input
                                type="hidden"
                                name="nameSearch"
                                value="${requestScope.searchName}"
                                />
                            <input
                                type="hidden"
                                name="categorySearch"
                                value="${requestScope.searchCategory}"
                                />
                            <input
                                type="hidden"
                                name="idSearch"
                                value="${requestScope.searchId}"
                                />
                            <!--/paging-->

                        </form>
                        <h4 style="color: orange; text-align: center; font-size: 25px;">${requestScope.status_add_img}</h4>
                        <h4 style="color: yellowgreen; text-align: center; font-size: 25px;">${requestScope.status_create_game}</h4>

                    </div>

                    <form
                        class="content_game"
                        id="usrform"
                        action="admin-game-change-info"
                        style="border: 1px solid #00ffd5"
                        method="POST"
                        >
                        <input type="hidden" name="id_game" value="${game.id}" />
                        <div class="control_change_game">
                            <span>Name:</span>
                            <input type="text" name="name" value="${game.name}" />
                        </div>
                        <div class="control_change_game">
                            <div id="list1" class="dropdown-check-list" tabindex="100">
                                <span class="anchor">Select Fruits</span>
                                <ul class="items">
                                    <c:forEach var="cate" items="${allCate}">
                                        <li>
                                            <input type="checkbox" name="category"
                                                   value="${cate.id}" ${listCate.contains(cate) ?
                                                            'checked' : ''} />${cate.name}
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                        <div class="control_change_game">
                            <span>Price:</span>
                            <input type="text" value="${game.price}" name="price" />
                        </div>
                        <div class="control_change_game">
                            <span>Quantity:</span>
                            <input
                                type="text"
                                value="${game.quantity}"
                                name="quantity"
                                />
                        </div>
                        <div class="control_change_game">
                            <span>Status:</span>
                            <input
                                type="text"
                                value="${game.status}"
                                name="status"
                                disabled
                                style="border: transparent"
                                />
                        </div>
                        <div class="control_change_game">
                            <span>Description: </span>
                            <textarea
                                style="
                                background-color: #1e464558;
                                font-size: 15px;
                                color: rgb(157, 227, 51);
                                "
                                name="description"
                                form="usrform"
                                cols="70"
                                rows="25"
                                id="description_game"
                                ></textarea>
                        </div>
                        <input type="hidden" name="button" value="save_game" />
                        <button
                            type="submit"
                            class="button-54"
                            style="color: #05d0cc"
                            id="save_game_infor"
                            >
                            Save
                        </button>
                        <button type="reset" class="button-54" style="color: #05d0cc">
                            Reset
                        </button>
                        <span style="color: olive">${requestScope.status}</span>
                        <!--paging-->
                        <input
                            type="hidden"
                            name="current_page"
                            value="${requestScope.currentPage}"
                            />
                        <input
                            type="hidden"
                            name="max_items"
                            value="${requestScope.maxItems}"
                            />
                        <input
                            type="hidden"
                            name="max_slides"
                            value="${requestScope.maxSlides}"
                            />
                        <input
                            type="hidden"
                            name="action_list"
                            value="${requestScope.action_list}"
                            />
                        <input
                            type="hidden"
                            name="nameSearch"
                            value="${requestScope.searchName}"
                            />
                        <input
                            type="hidden"
                            name="categorySearch"
                            value="${requestScope.searchCategory}"
                            />
                        <input
                            type="hidden"
                            name="idSearch"
                            value="${requestScope.searchId}"
                            />
                        <!--/paging-->
                    </form>
                </div>
            </div>
        </div>
        <script src="${js}admin_info_details.js"></script>
        <script src="${js}select.js"></script>
        <script>
            var checkList = document.getElementById("list1");
            checkList.getElementsByClassName("anchor")[0].onclick = function (e) {
                if (checkList.classList.contains("visible"))
                    checkList.classList.remove("visible");
                else
                    checkList.classList.add("visible");
            };
            var text = `${game.description}`;
            text = text.replace(/\s\s+/g, " ");
            text = text.replace(/\n/g, "");
            text = text.replace(/<p>/g, "");
            document.querySelector("#description_game").value = text.replace(
                    /<[/]p>/g,
                    "\n"
                    );
            document.querySelector("#save_game_infor").onclick = (e) => {
                document.querySelector("#description_game").value = document
                        .querySelector("#description_game")
                        .value.split(/\n+/)
                        .map((e) => {
                            return "<p>" + e + "</p>";
                        })
                        .join("");
            };

            ViewImg(".view_img", "#img_show");
            document.querySelector("#back_page").onclick = (e) => {
                if (!document.querySelector("#back_page_current").value) {
                    e.preventDefault();
                }
            }
        </script>
    </body>
</html>
