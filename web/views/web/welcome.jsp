<%-- 
    Document   : welcome
    Created on : Feb 23, 2023, 8:31:43 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<%@include file="/common/lib.jsp" %>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>GameCD</title>
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
            integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
            />
        <link rel="stylesheet" href="${css}top_product.css" />
    </head>
    <body>

        <div class="content">
            <div class="thumbnail">
                <img src="${img}4.png" alt="" />

                <div class="about_us">
                    <h1>About us</h1>
                    <p>
                        Welcome to our online store for video game enthusiasts! We
                        specialize in selling high-quality game CDs for popular
                        consoles such as Xbox and PlayStation. Our website offers a
                        wide range of titles for gamers of all ages and interests,
                        from action-packed shooters to immersive role-playing games.
                    </p>
                    <p>
                        Browse through our collection and you'll find the latest and
                        greatest releases, as well as classic titles that are sure to
                        bring back nostalgic memories. Our games are all brand new and
                        come in their original packaging, ensuring that you receive a
                        product that is in top condition.
                    </p>
                    <p>
                        Our website is designed to be user-friendly, making it easy to
                        search for specific games or to browse through different
                        categories. We also provide detailed information about each
                        game, including ratings, reviews, and system requirements, so
                        that you can make an informed decision before making a
                        purchase.
                    </p>
                    <p>
                        We pride ourselves on our excellent customer service and fast
                        shipping times. Once you've placed an order, we'll get your
                        game CD to you as quickly as possible, so that you can start
                        playing and enjoying your new purchase right away.
                    </p>
                    <p>
                        Thank you for choosing our online store as your go-to
                        destination for video game CDs. We look forward to serving you
                        and helping you find your next favorite game!
                    </p>
                </div>
                <form action="market-page">
                    <button type="submit" class="btn-13">Get started</button>

                </form>
            </div>

            <div class="content_seller">
                <h1>Featured products</h1>
                <c:set var="game1" value="${requestScope.topGames[0]}"/>
                <c:set var="game2" value="${requestScope.topGames[1]}"/>
                <div class="content_details">
                    <div class="best_seller">
                        <h3 class="title">Best seller</h3>
                        <img src="${imgproduct}${game1.imgPaths[0]}" alt="${game1.name}" />
                        <h4 class="name_product">${game1.name}</h4>
                        <span class="short_decription"
                              >
                            ${game1.description}
                        </span
                        >
                        <form action="details-game" class="action">
                            <input type="hidden" name="id_game" value="${game1.id}"/>
                            <button type="submit" class="glow-on-hover" name="button" value="view_details">
                                View details
                            </button>
                        </form>
                    </div>

                    <div class="hot_product">
                        <h3 class="title">Hot product</h3>
                        <img src="${imgproduct}${game2.imgPaths[0]}" alt="${game2.name}" />
                        <h4 class="name_product">${game2.name}</h4>
                        <span class="short_decription">
                            ${game2.description}
                        </span>
                        <form action="details-game" class="action">
                            <input type="hidden" name="id_game" value="${game2.id}"/>
                            <button type="submit" class="glow-on-hover" name="button" value="view_details">
                                View details
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>


        <script src="${js}top_product.js"></script>
        <script>
            document.getElementById("aboutus").style.pointerEvents = "none";
            document.getElementById("aboutus").style.backgroundColor = "rgba(43, 4, 57, 0.514)";
            document.getElementById("aboutus").style.color = "rgb(97, 206, 97)";
        </script>
    </body>
</html>
