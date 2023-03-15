<%-- 
    Document   : list-order
    Created on : Mar 4, 2023, 4:01:55 PM
    Author     : ASUS
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/lib.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check order</title>
        <link rel="stylesheet" href="${css}check_order.css?id=1234" />
    </head>
    <body>
        <c:set var="list" value="${requestScope.listOrder}"/>
        <div id="header_space"></div>
        <div class="content">
            <c:if test="${empty list && requestScope.isFilter == null}">
                <div class="empty_list">
                    <img src="${img}emptycart2.png" alt="">
                    <h3 style="text-align: center;">There are none of order.</h3>
                </div> 

            </c:if>

            <c:if test="${not empty list || requestScope.isFilter != null}">
                <h3 class="title_order">You have had <span>${list.size()}</span> orders</h3>

                <form action="order-details-filter" class="filter_order_control">
                    <select name="type_date_search" id="type_search">
                        <option value="0" ${param.type_date_search == 0? 'selected': ''}>All</option>
                        <option value="1" ${param.type_date_search == 1? 'selected': ''}>Order date</option>
                        <option value="2" ${param.type_date_search == 2? 'selected': ''}>Ship date</option>
                    </select>
                    <div class="input_date_filter">
                        From:
                        <input type="date" name="from_date" value="${param.from_date}">
                        To:
                        <input type="date" name="to_date" value="${param.to_date}">
                        <input type="hidden" name="button" value="filter_by_date">
                        <button class="glow-on-hover"
                                style="margin: 20px; font-size: 20px; width: 150px;">Filter</button>
                    </div>
                </form>
                <div class="list_orders">
                    <div class="order_item_header" action="https://getcssscan.com/css-buttons-examples">
                        <h4>Order ID</h4>
                        <h4>Status</h4>
                        <h4>Total</h4>
                        <h4>Order Date</h4>
                        <h4>Ship Date</h4>
                        <h4>Cancel order</h4>
                    </div>
                    <c:forEach var="order" items="${list}">
                        <form class="order_item" action="order-details">

                            <span>${order.id}</span>
                            <c:if test="${order.status == 1}">
                                <span>Processing</span>
                            </c:if>
                            <c:if test="${order.status == 2}">
                                <span>Completed</span>
                            </c:if>
                            <c:if test="${order.status == 3}">
                                <span>Canceled</span>
                            </c:if>
                            <span>${order.totalPrice}</span>
                            <span>${order.getStringDate(order.ordDate)}</span>
                            <c:if test="${order.shipDate == null}">
                                <span>...</span>
                            </c:if>
                            <c:if test="${order.shipDate != null}">
                                <span>${order.getStringDate(order.shipDate)}</span>
                            </c:if>
                            <div style="position: relative;">
                                <input type="hidden" name="order_id" value="${order.id}">
                                <c:if test="${order.status == 1}">
                                    <button class="button-83" name="button" value="cancel_order"
                                            style="background-color: rgba(18, 36, 39, 0.867) !important; width: 80%;">Cancel order</button>
                                </c:if>
                                <c:if test="${order.status == 2}">
                                    <p style="color: #00a7fc;">Shipped</p>
                                </c:if>
                                <c:if test="${order.status == 3}">
                                    <button class="button-83" name="button" value="reorder"
                                            style="background-color: rgba(18, 36, 39, 0.867) !important; width: 80%;">Reorder</button>
                                </c:if>
                                <c:if test="${order.id == param.order_id}">
                                    <h3 style="top: 50%; transform: translateY: -100%;
                                        text-align: center; color: greenyellow; margin-top: -20px; position: absolute; right: -100%;
                                         transition: 2s linear;"
                                        id="set_time_text"
                                        >${requestScope.success}</h3>
                                </c:if>
                            </div>
                            <input type="hidden" name="type_date_search" value="${param.type_date_search}">
                            <input type="hidden" name="from_date" value="${param.from_date}">
                            <input type="hidden" name="to_date" value="${param.to_date}">
                        </form>
                    </c:forEach>


                </div>
            </c:if>
        </div>
        <script>
            document.querySelectorAll('.order_item').forEach((el) => {
                el.onclick = (e) => {
                    e.target.submit();
                }
            });
            document.getElementById("orderss").style.pointerEvents = "none";
            document.getElementById("orderss").style.backgroundColor = "rgba(43, 4, 57, 0.514)";
            document.getElementById("orderss").style.color = "rgb(97, 206, 97)";
            (() => {
                if (document.querySelector("#type_search").value != 0) {
                    document.querySelector('.input_date_filter').style = "max-height: 600px";
                } else {
                    document.querySelector('.input_date_filter').style = "max-height: 0px";

                }
            })();
            document.querySelector("#type_search").onchange = (e) => {
                if (e.target.value != 0) {
                    document.querySelector('.input_date_filter').style = "max-height: 600px";
                } else {
                    document.querySelector('.input_date_filter').style = "max-height: 0px";
                    setTimeout(() => {
                        document.querySelector('.filter_order_control').submit();
                    }, 300);

                }
            }
            setTimeout(() => {
                document.querySelector("#set_time_text").remove();
            }, 3000);
        </script>
    </body>
</html>
