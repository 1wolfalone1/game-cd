<%-- 
    Document   : market
    Created on : Feb 23, 2023, 9:26:34 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/lib.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Market place</title>
        <link rel="stylesheet" href="${css}market.css">
        <link rel="stylesheet" href="${css}toast.css">
    </head>
    <body>
        <div id="header_space"></div>
        <div id="toast"></div>
        <div class="content">
           
            <c:set var="listG" value="${requestScope.listGames}"/>
            <div class="content_product">
                <c:if test="${listG.size() == 0}">
                    <div class="content_not_found">
                        <img src="${img}empty-search (1).png" class="img_not_found"/>
                        <h2 class="title_not_found_item">Item not found</h2>
                    </div>
                </c:if>
                <c:forEach var="game" items="${listG}">
                    <div class="content_product-item">
                        <div class="product-item-img">
                            <img src="${imgproduct}${game.imgPaths[0]}" alt="CyborG" />
                        </div>
                        <div class="product-item-content">
                            <h3 class="product-item-title">${game.name}</h3>
                            <div class="product-item-status">
                                <div class="type_container">
                                    <c:forEach var="category" items="${game.listCategory}">
                                        <span>${category.name}</span>
                                    </c:forEach>

                                </div>
                                <span class="product-item-price">${game.price}$</span>
                                <c:if test="${game.status == 1}">
                                    <span class="product-item-avaible">Availability</span>
                                </c:if>
                                <c:if test="${game.status != 1}">
                                    <span class="product-item-avaible unavailability">Unavailability</span>
                                </c:if>  
                            </div>
                            <form action="details-game" class="product-item-control">
                                <input type="hidden" name="id_game" value="${game.id}">
                                <button type="submit" class="glow-on-hover cart_action"  name="button" value="${game.id}">
                                    Take to cart
                                </button>
                                <button
                                    type="submit"
                                    class="glow-on-hover"
                                    name="button" value="view_details"
                                    >
                                    View details
                                </button>
                            </form>
                        </div>
                    </div>
                </c:forEach>

                <!--
                                    <div class="content_product-item">
                                        <div class="product-item-img">
                                            <img src="${imgproduct}DraMou.png" alt="CyborG" />
                                        </div>
                                        <div class="product-item-content">
                                            <h3 class="product-item-title">
                                                CyborG-The new city CyborG-The new cityCyborG-The
                                            </h3>
                                            <div class="product-item-status">
                                                <div class="type_container">
                                                    <span>Action</span>
                                                </div>
                                                <span class="product-item-price">19$</span>
                                                <span class="product-item-avaible">Availability</span>
                                            </div>
                                            <form action="#" class="product-item-control">
                                                <button type="submit" class="glow-on-hover" name="order">
                                                    Take to cart
                                                </button>
                                                <button
                                                    type="submit"
                                                    class="glow-on-hover"
                                                    name="view_details"
                                                    >
                                                    View details
                                                </button>
                                            </form>
                                        </div>
                                    </div> -->
                <c:if test="${listG.size() != 0}">
                    <form class="content_slider" action="search-game" method="Post">
                        <input type="hidden" name="current_page" value="${requestScope.currentPage}">          
                        <input type="hidden" name="search_name" value="${requestScope.searchName}">
                        <input type="hidden" name="search_category" value="${requestScope.searchCategory}">
                        <input type="hidden" name="max_items" value="${requestScope.maxItems}">
                        <input type="hidden" name="max_slides" value="${requestScope.maxSlides}">
                        
                    </form>
                </c:if>

            </div>
        </div>

        <script>
            document.getElementById("marketplace").style.pointerEvents = "none";
            document.getElementById("marketplace").style.backgroundColor = "rgba(43, 4, 57, 0.514)";
            document.getElementById("marketplace").style.color = "rgb(97, 206, 97)";
        </script>
        <script src="${js}market.js"></script>
        <script src="${js}cart.js">
        </script>
        <script>
            Slider({
                sliderSelector: '.content_slider',
                maxItems: ${requestScope.maxItems},
                maxSlides: ${requestScope.maxSlides},
                current: ${requestScope.currentPage}
            });
        </script>
    </body>
</html>
