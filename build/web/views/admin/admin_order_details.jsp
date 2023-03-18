<%-- 
    Document   : admin_order_details
    Created on : Mar 13, 2023, 12:34:33 PM
    Author     : ASUS
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/lib.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order details</title>
        <link rel="stylesheet" href="${css}admin_order_detail.css" />
    </head>
    <body>
        <c:set var="order" value="${requestScope.listDetails[0].order}"/>
        <c:set var="details" value="${requestScope.listDetails}"/>
        <div id="header_space"></div>
        <div class="content">
            <c:set var="paging" value="${sessionScope.paging}"/>
            <%@include file="/common/admin/order_left.jsp" %>
            <div class="content_product">
                <form action="admin-order-paging" method="POST">
                    <button
                        class="button-54"
                        name="button"
                        value="${paging.currentPage}"
                        style="color: #0ba713"
                        id="back_page"
                        >
                        Back
                    </button>
                </form>
                <div class="order_content user_order_detail">
                    <h3 class="order_title">Order #${order.id}</h3>
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
                                    <span>${order.user.fullName}</span>
                                </div>
                                <div class="title_to">
                                    <span>Phone:</span>
                                    <span>${order.user.phone}</span>
                                </div>
                                <div class="title_to">
                                    <span>Address:</span>
                                    <span>${order.user.address}</span>
                                </div>
                                <div class="title_to">
                                    <span>Order date:</span>
                                    <span>${order.getStringDate(order.ordDate)}</span>
                                </div>
                                <div class="title_to">
                                    <span>Ship date:</span>
                                    <span>${order.getStringDate(order.shipDate)}</span>
                                </div>
                                <div class="title_to">
                                    <span>Status</span>
                                    <span>${order.getStringStatus()}</span>
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
                        <div class="table_content">
                            <c:set var="total" value="${0 + 0}"/>
                            <c:forEach var="detail" items="${details}">
                                <div class="table_item">
                                    <div class="">${detail.game.name}</div>
                                    <div class="content_unit_price">${detail.game.price}$</div>
                                    <div class="content_quantity">${detail.quantity}</div>
                                    <div class="content_total">${detail.quantity * detail.game.price}$</div>
                                    <c:set var="totalPrice" value="${total = total + detail.quantity * detail.game.price}" />
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
            </div>
        </div>
    </body>
</html>
