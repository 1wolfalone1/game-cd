<%-- 
    Document   : view-cart
    Created on : Feb 26, 2023, 11:22:17 PM
    Author     : ASUS
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/lib.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping cart</title>
        <link rel="stylesheet" href="${css}view_cart.css" />
        <link rel="stylesheet" href="${css}toast.css">
    </head>
    <body>
        <div id="header_space"></div>
        <div class="content">
            <div class="table_cart_game">
                <div class="header_cart">
                    <h1 class="shopping_cart_title">Shopping cart</h1>
                    <c:if test="${empty sessionScope.cartGame}">
                        <h3 class="status_cart">None of game CD in your cart</h3>
                    </c:if>
                    <c:if test="${not empty sessionScope.cartGame}">
                        <h3 class="status_cart"><span class="status_header">${sessionScope.cartGame.size()}</span>
                            game CDs in your cart</h3>
                        </c:if>
                </div>
                <c:if test="${empty sessionScope.cartGame}">
                    <div>
                        <img src="${img}empty-cart.png" alt="">
                    </div>
                </c:if>
                <div id="toast"></div>
                <c:if test="${not empty sessionScope.cartGame}">
                    <div class="header_table">
                        <span class="product_title">Product</span>
                        <span class="product_status">Status</span>
                        <span class="product_unit">Unit</span>
                        <span class="product_quantity">Quantity</span>
                        <span class="product_total">Total</span>
                        <span class="product_remove">Remove</span>
                    </div>
                    <c:set var="totalPrice" value="${0 + 0}" />
                    <c:forEach var="game" items="${sessionScope.cartGame}">
                        <form action="#" class="cart_item">
                            <div class="item_img"></div>
                            <div class="item_content">
                                <div class="item_title">
                                    <img src="${imgproduct}${game.key.imgPaths[0]}" alt="" />
                                    <a href="${r}details-game?id_game=${game.key.id}&button=view_details" class="name_item" title="View details">
                                        ${game.key.name}</a
                                    >
                                </div>
                                <c:if test="${game.key.status == 1}">
                                    <span class="item_availble">In stock<br>${game.key.quantity} CDs</span>
                                    </c:if>
                                    <c:if test="${game.key.status == 0}">
                                    <span class="item_availble">Out stock</span>
                                </c:if>
                                <span class="item_price">${game.key.price}$ per</span>
                                <div class="quantity_items">
                                    <input type="hidden" class="id" value="${game.key.id}">
                                    <input type="hidden" class="price" value="${game.key.price}">
                                    <button class="action_quantity decrease_item">
                                        <i class="fa-solid fa-minus"></i>
                                    </button>
                                    <input type="number" name="" class="input_quantities" id="input_quantities"
                                           value="${game.value}" min="0" max="${game.key.quantity}"/>
                                    <button class="action_quantity increase_items">
                                        <i class="fa-solid fa-plus"></i>
                                    </button>
                                </div>
                                <span class="total_price">
                                    <c:set var="totalPrice" value="${totalPrice = totalPrice + game.key.price*game.value}" />
                                    <span class="total_price_number">${game.key.price*game.value}</span>$
                                </span>
                                <button class="remove_item">
                                    <input type="hidden" class="id" value="${game.key.id}">
                                    <i class="fa-solid fa-trash-can"></i>
                                </button>
                            </div>

                        </form>
                        <c:set var="error" value="${requestScope.errorEntity}"/>
                        <c:if test="${not empty error}">
                            <h2 style="margin: 10px; color: orange;" id="error_quantity_${game.key.id}">${error[game.key.id]}</h2>
                        </c:if>
                    </c:forEach>
                    <form class="table_bottom" action="order">
                        <div class="container_title_bottom">
                            <h3 class="total_title">Total price</h3>
                            <span class="tota_price_meg"
                                  ><span id="total_price_order">${totalPrice}</span>$</span
                            >
                            <button type="submit" class="glow-on-hover order_button">
                                Order now
                            </button>
                        </div>
                    </form>
                </c:if>
            </div>
        </div>
        <script src="${js}view_cart.js?id=1">
        </script>
        <script>
            ButtonChangeNumber('.quantity_items', '.decrease_item', '.increase_items', '.input_quantities');
            RemoveItem('.remove_item', '.cart_item', ${sessionScope.cartGame.size()});
            Array.from(document.getElementsByClassName('input_quantities')).forEach(element => {
                element.onkeypress =
                        function (e) {
                            var ev = e || window.event;
                            if (ev.charCode < 48 || ev.charCode > 57) {
                                return false; // not a digit
                            } else if (this.value * 10 + ev.charCode - 48 > this.max) {
                                return false;
                            } else {
                                return true;
                            }
                        };
            })

            document.getElementById("viewcart").style.pointerEvents = "none";
            document.getElementById("viewcart").style.backgroundColor = "rgba(43, 4, 57, 0.514)";
            document.getElementById("viewcart").style.color = "rgb(97, 206, 97)";
        </script>
    </body>
</html>
