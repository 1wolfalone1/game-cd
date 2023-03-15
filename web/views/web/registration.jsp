<%-- Document : registration Created on : Feb 23, 2023, 10:12:04 PM Author :
ASUS --%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/lib.jsp" %>
<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
      <title>Create account</title>
      <link rel="stylesheet" href="${css}registration.css" />
   </head>
   <body>
      <div class="signup">
         <h2 class="signup_heading">Create account</h2>
         <p class="signup_already">
            Already have an account?
            <a class="signup_signin form_link" href="${r}login-page">Sign in</a>
         </p>
         <form action="sign-up" class="form_main" id="form" method="POST">
            <div class="form_group control_item">
               <input
                  id="fullName_regis"
                  type="text"
                  name="full_name"
                  class="form_input data"
                  placeholder="Full name"
                  value="${param.full_name}"
               />
               <span class="control_status">${requestScope.errorForm.errorName}</span>
            </div>
            <div class="form_group control_item">
               <input
                  id="email_regis"
                  type="text"
                  name="email"
                  class="form_input data"
                  placeholder="E-mail"
                  value="${param.email}"
               />
               <span class="control_status">${requestScope.errorForm.errorEmail}</span>
            </div>
            <div class="form_group control_item">
               <input
                  id="phone_regis"
                  type="text"
                  name="phone"
                  class="form_input data"
                  placeholder="Phone"
                  value="${param.phone}"
               />
               <span class="control_status">${requestScope.errorForm.errorPhone}</span>
            </div>
            <div class="form_group control_item">
               <input
                  id="password_regis"
                  type="password"
                  name="password"
                  class="form_input data"
                  placeholder="Password"
                  value="${param.password}"
               />
               <span class="control_status">${requestScope.errorForm.errorPassword}</span>
            </div>
            <div class="form_group control_item">
               <input
                  id="confirmPassword_regis"
                  type="password"
                  name="confirm_passowrd"
                  class="form_input data"
                  placeholder="Confirmed password"
                  value="${param.confirm_passowrd}"
               />
               <span class="control_status">${requestScope.errorForm.errorConfirm}</span>
            </div>

            <div class="form_group form_submit-far">
               <button type="submit" class="form_submit glow-on-hover">
                  <span class="form_submit-text" id="submit"> Sign up</span>
                  <i class="fa-sharp fa-solid fa-right-to-bracket"></i>
               </button>
            </div>
            <div class="form_tos">
               <label for="check-tos" id="label_check">
                  <input type="checkbox" name="" id="check-tos" />
                  <span class="check-tos-text">
                     I have read and agree to the</span
                  >
                  <a href="#" class="signup_tos form_link"
                     >Term of services!</a
                  ></label
               >
               <h2 id="checkstatus"></h2>
            </div>
         </form>
      </div>
      <script src="${js}validator.js"></script>
      <script>
         document.getElementById("signup").style.pointerEvents = "none";
         document.getElementById("signup").style.backgroundColor =
            "rgba(43, 4, 57, 0.514)";
         document.getElementById("signup").style.color = "rgb(97, 206, 97)";
      </script>
      <script>
         var k = new Validator({
            form: "form",
            formElement: ".control_item",
            formStatus: ".control_status",
            formDataSelector: ".data",
            rules: [
               Validator.isRequired("#fullName_regis", "Name is required!"),
               Validator.isEmail("#email_regis", "Not a valid email!"),
               Validator.isRequired("#email_regis", "Email is required!"),
               Validator.isPhoneNumber(
                  "#phone_regis",
                  "Not a valid phone number!"
               ),
               Validator.isRequired("#phone_regis", "Phone is required!"),
               Validator.isTextInIntervalRange(
                  "#password_regis",
                  5,
                  20,
                  "Password's length must be between 5 and 20 inclusive!"
               ),
               Validator.isRequired("#password_regis", "Password is required!"),
               Validator.isRequired(
                  "#confirmPassword_regis",
                  "Need to be confirmed!"
               ),
            ],
            data: {},
         });

         document.getElementById("label_check").onclick = (e) => {
            document.getElementById("checkstatus").innerHTML = "";
         };
      </script>
   </body>
</html>
