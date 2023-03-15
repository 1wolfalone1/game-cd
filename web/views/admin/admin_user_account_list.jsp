<%-- 
    Document   : admin_user_account_list
    Created on : Mar 14, 2023, 8:59:04 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/lib.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="${css}admin_user_account_list.css" />

    </head>
    <body>
        <div id="header_space"></div>

        <div class="content">
            <%@include file="/common/admin/user_left.jsp" %>
            <div class="content_product">
                <h1 class="header_product">View account</h1>

                <div class="table">
                    <div class="table_header">
                        <h3>ID</h3>
                        <h3>User ID</h3>
                        <h3>User Detail</h3>
                        <h3>Email</h3>
                        <h3>Status</h3>
                        <h3>Role</h3>
                        <h3>Save</h3>
                        <h3>Cancel</h3>
                    </div>
                    <div class="table_content">
                        <c:if test="${requestScope.find_one != null}">
                            <c:set var="account" value="${requestScope.account_profile}"/>
                            <c:if test="${account != null}">
                                <form action="admin-user-action">
                                    <div>
                                        <span>${account.id}</span>
                                    </div>
                                    <div>
                                        <span>${account.user.id}</span>
                                    </div>
                                    <div>
                                        <button class="button-54 filter_game_button"  name="button" value="find">
                                            View user
                                        </button>
                                    </div>
                                    <div>
                                        <span>${account.email}</span>
                                    </div>
                                    <div>
                                        <select name="status_account" style="width: 80%; font-size: 25px !important;">
                                            <option value="1" ${account.status == 1? 'selected' : ''}>Active</option>
                                            <option value="0" ${account.status == 0? 'selected' : ''}>Inactive</option>
                                        </select>
                                    </div>
                                    <div>
                                        <span>${account.role}</span>
                                    </div>
                                    <div>
                                        <button class="button-54 filter_game_button"  name="button" value="save_change">
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
                            </c:if>
                        </c:if>
                        <c:if test="${requestScope.listAccounts != null}">
                            <c:set var="list" value="${requestScope.listAccounts}"/>
                            <c:forEach var="account" items="${list}">
                                <form action="admin-user-action">
                                    <input type="hidden" name="account_id" value="${account.id}">
                                    <input type="hidden" name="user_id" value="${account.user.id}">
                                    <div>
                                        <span>${account.id}</span>
                                    </div>
                                    <div>
                                        <span>${account.user.id}</span>
                                    </div>
                                    <div>
                                        <button class="button-54 filter_game_button" name="button" value="find">
                                            View user
                                        </button>
                                    </div>
                                    <div>
                                        <span>${account.email}</span>
                                    </div>
                                    <div>
                                        <select name="status_account" style="width: 80%; font-size: 25px !important;">
                                            <option value="1" ${account.status == 1? 'selected' : ''}>Active</option>
                                            <option value="0" ${account.status == 0? 'selected' : ''}>Inactive</option>
                                        </select>
                                    </div>
                                    <div>
                                        <c:if test="${account.role != 1}">
                                            <span>USER</span>
                                        </c:if>
                                        <c:if test="${account.role == 1}">
                                            <span>ADMIN</span>
                                        </c:if>
                                    </div>
                                    <div>
                                        <button class="button-54 filter_game_button" name="button" value="save_change">
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
                        </c:if>
                    </div>
                    <form class="content_slider" action="admin-account-search" method="Post">
                        <input type="hidden" name="status_search" value="${requestScope.status_search}">
                    </form>
                </div>
            </div>
        </div>
        <script src="${js}admin.js">
        </script>
        <script>
            <c:if test="${pagingAccount != null && pagingAccount.isPage()}">
            Slider({
                sliderSelector: '.content_slider',
                maxItems: ${pagingAccount.maxItems},
                maxSlides: ${pagingAccount.maxSlides},
                current: ${pagingAccount.currentPage}
            });
            </c:if>
            ${sessionScope.pagingAccount.setPage(false)}
        </script>
    </body>
</html>
