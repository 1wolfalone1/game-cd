<%-- 
    Document   : admin_category_management
    Created on : Mar 11, 2023, 7:17:10 AM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Category management</title>
        <link rel="stylesheet" href="${css}admin_category_content.css" />

    </head>
    <body>
        <div id="header_space"></div>
        <div class="content">
            <%@include file="/common/admin/game_left.jsp" %>
            <div class="content_product">
                <div class="content_product">
                    <h1 class="header_product">View all categories</h1>
                    <div class="table">
                        <div class="table_header">
                            <h3>ID</h3>
                            <h3>Name</h3>
                            <h3>Save</h3>
                            <h3>Cancel</h3>
                        </div>
                        <div class="table_content">
                            <c:forEach var="category" items="${requestScope.listCate}">
                                <form action="admin-category">
                                    <span>${category.id}</span>
                                    <input type="hidden" name="id_category" value="${category.id}">
                                    <input type="text" name="name" value="${category.name}" />
                                    <button class="button-54 filter_game_button"
                                            style="color: #127772; font-size: 15px; width: 100px;"
                                            name="button"
                                            value="change">Save</button>
                                    <button type="reset" class="button-54 filter_game_button" 
                                            style="color: #127772; font-size: 15px; width: 100px;">
                                        Cancel
                                    </button>
                                </form>
                            </c:forEach>

                        </div>
                        <div style="display: flex; width: 100%; justify-content: center">
                            <button
                                class="button-54"
                                style="color: rgb(137, 127, 127); font-size: 20px"
                                id="add_button"
                                >
                                Add+
                            </button>
                        </div>
                        <h3 style="text-align: center; margin-top: 10px; color: orange;">${requestScope.status_cate}</h3>
                    </div>
                </div>
            </div>
        </div>
        <script>
            document.querySelector('#add_button').onclick = (e) => {
                if (!document.querySelector('#new_category')) {
                    let newForm = document.createElement('form');
                    newForm.id = 'new_category';
                    newForm.action = 'admin-category';
                    newForm.innerHTML = `
              <span>ID</span>
              <input type="text" name="name" value="" placeholder="Name category"/>
              <button class="button-54 filter_game_button" name="button" value="add">Save</button>
              <button type="reset" class="button-54 filter_game_button">
                 Cancel
              </button>
                        `;
                    document.querySelector('.table_content').appendChild(newForm);
                    e.target.innerHTML = 'Cancel';
                } else {
                    document.querySelector('#new_category').remove();
                    e.target.innerHTML = 'Add+';
                }
            };
        </script>              
    </body>
</html>
