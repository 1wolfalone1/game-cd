<%-- 
    Document   : details
    Created on : Feb 23, 2023, 9:32:32 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/lib.jsp" %>
<!DOCTYPE html>
<c:set var="game" value="${requestScope.game}"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${game.name}</title>
        <link rel="stylesheet" href="${css}details.css" />
        <link rel="stylesheet" href="${css}toast.css" />
    </head>
    <body>
        <div id="header_space"></div>
        <div id="toast"></div>
        <div class="content">
            <div class="details_product">
                <div class="slider" id="slider">
                    <div class="img" id="img">
                        <div class="img_repos"></div>
                        <div class="control_right">
                            <i class="fa-solid fa-chevron-right"></i>
                        </div>
                        <div class="control_left">
                            <i class="fa-solid fa-chevron-left"></i>
                        </div>
                    </div>

                    <div class="slider_bottom"></div>
                </div>
                <div class="introduction">
                    <h1 class="title">${game.name}</h1>
                    <h2 class="price"><span>Price</span> <span>${game.price}$</span></h2>
                    <h2 class="status">
                        <span>Status</span> 
                        <c:if test="${game.status == 1}">
                            <span>Availability</span>
                        </c:if>
                        <c:if test="${game.status != 1}">
                            <span class="unavailability">Unavailability</span>
                        </c:if>
                    </h2>
                    <h2 class="type">
                        <span>Category</span>
                        <div class="list_type">
                            <c:forEach var="category" items="${game.listCategory}">
                                <span>${category.name}</span>
                            </c:forEach>
                        </div>
                    </h2>
                    <div class="quantity">
                        <h4>Quantity</h4>
                        <button type="submit" class="btnn decrease_item">
                            <i class="fa-solid fa-minus">
                            </i>
                        </button>
                        <input type="number" min="0" max="${game.quantity}" class="input_quantities" id="quantity_to_cart"/>
                        <button type="submit" class="btnn increase_item">
                            <i class="fa-solid fa-plus"></i>
                        </button>
                        <span>${game.quantity} CDs available</span>
                    </div>
                    <form class="control" action="view-cart">
                        <button type="submit" class="add_to_cart glow-on-hover" id="add_to_cart">
                            Add to cart
                        </button>
                        <button type="submit" class="order glow-on-hover">
                            Order
                        </button>
                    </form>
                </div>
                <div class="details">
                    <h2>Product description</h2>
                    ${game.description}
                </div>
            </div>
        </div>
        <script src="${js}details.js"></script>
        <script>

            addToCart(${game.id});
            ButtonChangeNumber(
                    '.quantity',
                    ".decrease_item",
                    '.increase_item',
                    '.input_quantities'
                    )
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

            var listImg = [

            <c:forEach var="img" items="${game.imgPaths}"  varStatus="i">
                {
                    link: "${imgproduct}${img}",
                    index: ${i.index}
                },
            </c:forEach>
//                {
//                    link: "${imgproduct}Srekuk.png",
//                    index: 0,
//                },
//                {
//                    link: "${imgproduct}Srekuk2.png",
//                    index: 1,
//                },
//                {
//                    link: "${imgproduct}Srekuk-v2 (2).png",
//                    index: 2,
//                },
//                {
//                    link: "${imgproduct}Srekuk3.png",
//                    index: 3,
//                },
            ];
            Slider(listImg);
        </script>
    </body>
</html>
