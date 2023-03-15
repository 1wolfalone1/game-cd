<%-- 
    Document   : user_order_details
    Created on : Mar 4, 2023, 4:01:41 PM
    Author     : ASUS
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/lib.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Details order</title>
        <link rel="stylesheet" href="${css}invoice.css" />
    </head>
    <body>
        <div id="header_space"></div>
        <c:set var="listOrderDetail" value="${requestScope.listOrderDetail}"/>
        <c:set var="account" value="${sessionScope.account}"/>
        <div class="content">
            <div class="order_content user_order_detail">
                <h3 class="order_title">Order #${listOrderDetail[0].order.id}</h3>
                <div class="user_information2">
                    <div class="from_order">
                        <span>From:</span>
                        <img src="${img}xbox1.png" alt="" />
                        <span>GameCD</span>
                    </div>
                    <div class="to_order">
                        <span>To:</span>
                        <div>
                            <div class="title_to">
                                <span>FullName:</span>
                                <span>${account.user.fullName}</span>
                            </div>
                            <div class="title_to">
                                <span>Phone:</span>
                                <span>${account.user.phone}</span>
                            </div>
                            <div class="title_to">
                                <span>Address:</span>
                                <span>${account.user.address}</span>
                            </div>
                            <div class="title_to">
                                <span>Order date:</span>
                                <span>${listOrderDetail[0].order.getStringDate(listOrderDetail[0].order.ordDate)}</span>
                            </div>
                            <div class="title_to">
                                <span>Ship date:</span>
                                <c:if test="${listOrderDetail[0].order.shipDate == null}">
                                    <span>...</span>
                                </c:if>
                                <c:if test="${listOrderDetail[0].order.shipDate != null}">
                                    <span>${listOrderDetail[0].order.getStringDate(listOrderDetail[0].order.shipDate)}</span>
                                </c:if>
                            </div>
                            <div class="title_to">
                                <span>Status</span>
                                <c:if test="${listOrderDetail[0].order.status == 1}">
                                    <span>Processing</span>
                                </c:if>
                                <c:if test="${listOrderDetail[0].order.status == 2}">
                                    <span>Completed</span>
                                </c:if>
                                <c:if test="${listOrderDetail[0].order.status == 3}">
                                    <span>Canceled</span>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="table_items">
                    <div class="table_header">
                        <span>Product</span>
                        <span>Unit</span>
                        <span>Quantity</span>
                        <span>Total</span>
                    </div>
                    <c:set var="totalPrice" value="${0 + 0}" />
                    <div class="table_content">
                        <c:forEach var="orderDetail" items="${listOrderDetail}">
                            <div class="table_item">
                                <div class="content_product">${orderDetail.game.name}</div>
                                <div class="content_unit_price">${orderDetail.game.price}$</div>
                                <div class="content_quantity">${orderDetail.quantity}</div>
                                <c:set var="totalPrice" value="${totalPrice + orderDetail.quantity * orderDetail.game.price}" />
                                <div class="content_total">${orderDetail.quantity * orderDetail.game.price}$</div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="table_footer">
                        <div class="footer_total_price">
                            <span>Total price:</span>
                            <span>${totalPrice}$</span>
                        </div>
                        <div class="footer_payment_method">
                            Payment after arrival of goods
                        </div>
                    </div>
                </div>
            </div>
            <form class="order_user_check_div" action="order-details">
                <input type="hidden" name="type_date_search" value="${param.type_date_search}">
                <input type="hidden" name="from_date" value="${param.from_date}">
                <input type="hidden" name="to_date" value="${param.to_date}">
                <input type="hidden" name="order_id" value="${listOrderDetail[0].order.id}">
                <button class="btn_action glow-on-hover order_user_check_button" style="width: 325px;" name="button" value="back">Back to list order</button>
                <c:if test="${listOrderDetail[0].order.status == 1}">
                    <button class="btn_action glow-on-hover order_user_check_button" name="button" value="cancel_order">Cancel order</button>
                </c:if>
                <c:if test="${listOrderDetail[0].order.status == 3}">
                    <button class="btn_action glow-on-hover order_user_check_button" name="button" value="reorder">Reorder</button>
                </c:if>
            </form>
        </div>
        <script>
            document.getElementById("orderss").style.pointerEvents = "none";
            document.getElementById("orderss").style.backgroundColor = "rgba(43, 4, 57, 0.514)";
            document.getElementById("orderss").style.color = "rgb(97, 206, 97)";
        </script>
    </body>
</html>
