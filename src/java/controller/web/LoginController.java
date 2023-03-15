/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web;

import constant.action.ActionEnum;
import constant.action.ResourceEnum;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.dto.AccountModel;
import service.IAccountService;
import service.iml.AccountService;
import utils.Encode;

/**
 *
 * @author ASUS
 */
public class LoginController extends HttpServlet {

    private IAccountService accountService = new AccountService();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = ActionEnum.LOGIN_PAGE.getKey();
        response.sendRedirect(url);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String url = ResourceEnum.LOGIN_PAGE.getResource();
        try {
            //-----
            String button = request.getParameter("button");
            if (button.equals("signup")) {
                url = ActionEnum.SIGN_UP_PAGE.getKey();
                response.sendRedirect(url);
                return;
            }
            //-----
            String email = request.getParameter("txtemail");
            String password = (String) request.getAttribute("hashPassword");
            log(password);
            HttpSession ses = request.getSession();

            AccountModel model = accountService.checkLogin(email, password);
            if (model == null) {
                request.setAttribute("remainUserName", email);
                request.setAttribute("errorlogin", "Invalid email or password!!! Try again.");
            } else if(model.getStatus() != 1){
                request.setAttribute("remainUserName", email);
                request.setAttribute("ban_message"
                        , "We're sorry, but your account has been banned. Please contact our support team for further assistance.");
            }else{
                String[] rememberPass = request.getParameterValues("remember_password");
                ses.setAttribute("account", model);

                if (rememberPass != null) {
                    Cookie cookies[] = request.getCookies();
                    String token = accountService.updateToken(model.getId());
                    response.addCookie(new Cookie("token", token));
                }

//                url = ResourceEnum.WELCOME_PAGE.getResource();
                if (model.getRole() == 1) {
                    response.sendRedirect(ActionEnum.FIRST_PAGE.getKey());
                    return;
                } else {
                    response.sendRedirect(ActionEnum.MARKET_PAGE.getKey());
                    return;
                }
            }
            log("-----------------------------------------");
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(ActionEnum.SOMETHING_WRONG.getKey());
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
