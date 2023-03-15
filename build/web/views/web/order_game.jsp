<%-- 
    Document   : order_game
    Created on : Mar 2, 2023, 4:36:40 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/lib.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order</title>
        <link rel="stylesheet" href="${css}invoice.css" />
    </head>
    <body>
        <div id="header_space"></div>
        <form class="content" action="order" method="POST">
            <div class="progress_order">
                <div class="progress_title">
                    <span> Check info </span>
                    <span> Confirm </span>
                    <span> Order successfully </span>
                </div>
                <progress id="file" value="${(requestScope.status - 1) * 50}" max="100"></progress>
            </div>
            <c:set var="listGame" value="${sessionScope.cartGame}"/>
            <c:set var="total" value="${0 + 0}"/>
            <div class="order_content">
                <h3 class="order_title">Order</h3>
                <c:set var="account" value="${sessionScope.account}"/>
                <c:if test="${requestScope.status == null || requestScope.status == 1}">
                    <div class="user_information">
                        <div class="header_order">
                            <h4>Check your information</h4>
                            <label class="input_information" for="name">
                                <span>FullName</span>
                                <input type="text" placeholder="FullName" name="name"
                                       value="${account==null?param.name:account.user.fullName}"
                                       id="name" />
                            </label>
                            <span class="error_message">${requestScope.errorName}</span>
                            <label class="input_information" for="phone">
                                <span>Phone</span>
                                <input type="text" placeholder="Phone" name="phone"
                                       value="${account==null?param.phone :account.user.phone}"
                                       id="phone" />

                            </label>
                            <span class="error_message">${requestScope.errorPhone}</span>
                            <label class="input_information" for="address">
                                <span>Address</span>
                                <input type="text" placeholder="Address" name="address"
                                       value="${account==null?param.address :account.user.address}"
                                       id="address" />
                            </label>
                            <span class="error_message">${requestScope.errorAddress}</span>
                        </div>
                    </div> 
                </c:if>
                <c:if test="${requestScope.status >= 2}">
                    <div  class="user_information2">
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
                                    <span>${account==null? param.name:account.user.fullName}</span>
                                </div>
                                <div class="title_to">
                                    <span>Phone:</span>    
                                    <span>${account==null? param.phone :account.user.phone}</span>

                                </div>
                                <div class="title_to">
                                    <span>Address:</span>
                                    <span>${account==null? param.address :account.user.address}</span>
                                </div>
                                <div class="title_to">
                                    <span>Order date:</span>
                                    <span>${requestScope.orderDate}</span>
                                </div>
                                <div class="title_to">
                                    <span>Ship date:</span>
                                    <span>...</span>
                                </div>
                                <div class="title_to">
                                    <span>Status</span>
                                    <span>Processing</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>

                <div class="table_items">
                    <div class="table_header">
                        <span>Product</span>
                        <span>Unit</span>
                        <span>Quantity</span>
                        <span>Total</span>
                    </div>
                    <div class="table_content">
                        <c:forEach var="game" items="${listGame}">
                            <div class="table_item">
                                <div class="content_product">${game.key.name}</div>
                                <div class="content_unit_price">${game.key.price}$</div>
                                <div class="content_quantity">${game.value}</div>
                                <div class="content_total">${game.value*game.key.price}$</div>
                                <c:set var="totalPrice" value="${total = total + game.key.price*game.value}" />
                            </div>
                        </c:forEach>

                        <div class="table_footer">
                            <div class="footer_total_price">
                                <span>Total price:</span>
                                <span>${total}$</span>
                            </div>
                            <div class="footer_payment_method">
                                Payment after arrival of goods
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <input type="hidden" name="step" value="${requestScope.status}">
            <c:if test="${requestScope.status == null || requestScope.status == 1}">
                <button class="btn_action glow-on-hover">Next step</button>
            </c:if>
            <c:if test="${requestScope.status == 2}">
                <input type="hidden" name="name_final" value="${account==null? param.name:account.user.fullName}">
                <input type="hidden" name="phone_final" value="${account==null? param.phone :account.user.phone}">
                <input type="hidden" name="address_final" value="${account==null? param.address :account.user.address}">
                <button class="btn_action glow-on-hover">Confirm info</button>
            </c:if>
            <c:if test="${requestScope.status == 3}">
                <%
                    HttpSession ses = request.getSession();
                    ses.removeAttribute("cartGame");
                %>
                <div class="message_final">
                    <p>Dear <span style="font-style: italic;">${account==null? param.name:account.user.fullName}</span>,</p>
                    <p>Thank you for purchasing our game CDs. 
                        We appreciate your business and hope that you enjoy the games.
                        Our staff will be in touch with you shortly to provide any further assistance or support you may need.</p>
                    <p>Thank you again for choosing our products, and please don't
                        hesitate to contact us if you have any questions or concerns.</p>
                    <p>Best regards,</p>
                    <p>GameCD</p>
                </div>
                <button class="btn_action glow-on-hover" style="width: 260px; height: 90px;">Continue shopping</button>
            </c:if>
        </form>

    </body>
</html>
