<%-- 
    Document   : user_profile
    Created on : Mar 2, 2023, 4:36:29 PM
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
        <link rel="stylesheet" href="${css}user_profile.css" />

    </head>
    <body>
        <div id="header_space"></div>
        <c:set var="acc" value="${sessionScope.account}"/>
        <div class="content">
            <div
                class="profile_user"
                >
                <form class="left_info"
                      action="change-profile"
                      method="POST"
                      enctype="multipart/form-data"
                      >
                    <c:if test="${acc.avatarPath == null}"> 
                        <img src="${avatar}default.png" alt="" />
                    </c:if>
                    <c:if test="${acc.avatarPath != null}"> 
                        <img src="${avatar}${acc.avatarPath}" alt="" />
                    </c:if>
                    <div class="custom-file-upload" id="input_avatar">
                        <input type="file" id="file_input" name="avatar"/>
                        <button class="updateavatar glow-on-hover" name="button" value="update">update</button>
                    </div>
                    <span class="${requestScope.classCss}  error_info" style="margin: 10px auto;">${requestScope.status}</span>
                    <button class="change_profile glow-on-hover" id="change_info_button">Change Info</button>
                </form>
                <c:if test="${sessionScope.account != null && sessionScope.account.role == 1}">
                    <form action="admin" class="toggle_status" style="position: absolute; right: 0;" id="form_change_mode">
                        <label class="switch">
                            <input type="checkbox" id="change_mode"/>
                            <span class="slider round"></span>
                        </label>
                        <a href="" style="width: 1px; height: 1px"></a>
                        <span>ADMIN MODE</span>
                    </form>
                </c:if>  
                <form class="right_info"  action="change-profile"
                      method="POST">
                    <h3 class="right_title">Personal Information

                    </h3>

                    <div class="info_user info_change">
                        <span>Full Name</span>
                        <input
                            type="text"
                            name="name"
                            placeholder="FullName"
                            value="${requestScope.openChange  && !requestScope.changeAva? requestScope.userTmp.fullName.trim() :acc.user.fullName.trim()}"
                            disabled
                            />

                    </div>
                    <span class="error_info">${requestScope.error.fullName}</span>
                    <div class="info_user">
                        <span>Email</span>
                        <input
                            type="text"
                            name="email"
                            placeholder="Email"
                            value="${acc.email}"
                            disabled
                            />

                    </div>

                    <div class="info_user info_change">
                        <span>Address</span>
                        <input
                            type="text"
                            name="address"
                            placeholder="Address"
                            value="${requestScope.openChange && !requestScope.changeAva? requestScope.userTmp.address:acc.user.address.trim()}"
                            disabled
                            />

                    </div>

                    <div class="info_user info_change">
                        <span>Phone</span>
                        <input
                            type="text"
                            name="phone"
                            placeholder="Phone"
                            value="${requestScope.openChange  && !requestScope.changeAva? requestScope.userTmp.phone :acc.user.phone.trim()}"
                            disabled
                            />

                    </div>
                    <span class="error_info">${requestScope.error.phone}</span>

                    <div class="info_user info_change">
                        <span>Password</span>
                        <input
                            type="password"
                            name="password"
                            placeholder="Leave it empty, if don't want to change."
                            value=""
                            disabled
                            />
                    </div>
                    <span class="error_info">${requestScope.error.oldPass}</span>

                    <div class="info_user password_change change info_change">
                        <span>New password</span>
                        <input
                            type="password"
                            name="newPassword"
                            placeholder="New password"
                            value=""
                            disabled
                            />

                    </div>
                    <span class="error_info">${requestScope.error.newPass}</span>

                    <div class="info_user password_change change info_change">
                        <span>Confirm</span>
                        <input
                            type="password"
                            name="conFirmPass"
                            placeholder="Confirmed password"
                            value=""
                            disabled
                            />

                    </div>
                    <span class="error_info">${requestScope.error.conPass}</span>

                    <div class="action_info">
                        <button class="cancel_antion glow-on-hover">Cancel</button>
                        <button class="save_action glow-on-hover" name="button">Save</button>
                    </div>
                    <span style="color : rgb(106, 255, 47);"  class="error_info">${requestScope.statusInfo}</span>
                    <span style="color : orangered"  class="error_info">${requestScope.updateAddressMeg}</span>
                </form>
            </div>
        </div>
        <script src="${js}user_profile.js"></script>
        <script>
            ChangeInfo(
                    ".profile_user",
                    ".info_change",
                    ".change_profile",
                    ".action_info"
                    );
            Cancel(
                    ".profile_user",
                    ".info_change",
                    ".cancel_antion",
                    ".action_info"
                    );
            OpenChangeInfo(${requestScope.openChange});

            document.getElementById("viewprofile").style.pointerEvents = "none";
            document.getElementById("viewprofile").style.backgroundColor = "rgba(43, 4, 57, 0.514)";
            document.getElementById("viewprofile").style.color = "rgb(97, 206, 97)";
            document.querySelector("#change_mode").onclick = (e) => {
                setTimeout(() => {
                    document.querySelector("#form_change_mode").submit();
                }, 500);
            };
        </script>
    </body>
</html>
