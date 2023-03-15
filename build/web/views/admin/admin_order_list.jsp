<%-- 
    Document   : admin_order_list
    Created on : Mar 13, 2023, 7:41:46 AM
    Author     : ASUS
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/lib.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Orders</title>
        <link rel="stylesheet" href="${css}admin_order_list.css" />
    </head>
    <body>
        <div id="header_space"></div>
        <c:set var="listOrders" value="${requestScope.listOrder}"/>
        <c:set var="paging" value="${sessionScope.paging}"/>
        <div class="content">
            <%@include file="/common/admin/order_left.jsp" %>

            <div class="content_product">
                <h1 class="header_product">View order list</h1>

                <div class="table">
                    <div class="table_header">
                        <h3>View detail</h3>
                        <h3>ID</h3>
                        <h3>User ID</h3>
                        <h3>Order date</h3>
                        <h3>Ship date</h3>
                        <h3>Status</h3>
                        <h3>Save</h3>
                        <h3>Cancel</h3>
                    </div>
                    <div class="table_content">
                        <c:forEach var="order" items="${listOrders}">
                            <form action="admin-order-action" method="POST">
                                <input type="hidden" name="id_order" value="${order.id}">
                                <div>
                                    <button class="button-54 filter_game_button" name="button" value="view_order_details">
                                        View
                                    </button>
                                </div>
                                <div>
                                    <span>${order.id}</span>
                                </div>
                                <div>
                                    <span>${order.user.id}</span>
                                </div>
                                <div>
                                    <span>${order.getStringDate(order.ordDate)}</span>
                                </div>
                                <div>
                                    <input type="date" name="order_ship_date" value="${order.getStringBrowserDate(order.shipDate)}">
                                </div>
                                <div>
                                    <select style="width: 60%;" name="order_status">
                                        <option value="1" ${order.status == 1? 'selected' : ''}>processing</option>
                                        <option value="2" ${order.status == 2? 'selected' : ''}>completed</option>
                                        <option value="3" ${order.status == 3? 'selected' : ''}>cancel</option>
                                    </select>
                                </div>
                                <div>
                                    <button type="submit"  class="button-54 filter_game_button" name="button" value="update">
                                        Save
                                    </button>
                                </div>
                                <div>
                                    <button
                                        type="reset"
                                        class="button-54 filter_game_button"
                                        >
                                        Cancel
                                    </button>
                                </div>
                            </form>
                        </c:forEach>
                    </div>
                    <form class="content_slider" action="admin-order-paging" method="Post">

                    </form>
                </div>

            </div>
        </div>
        <script src="${js}admin.js">
        </script>
        <script>


            <c:if test="${paging != null && paging.isPage()}">
            Slider({
                sliderSelector: '.content_slider',
                maxItems: ${paging.maxItems},
                maxSlides: ${paging.maxSlides},
                current: ${paging.currentPage}
            });
            </c:if>
            ${sessionScope.paging.setPage(false)}
        </script>
    </body>
</html>
