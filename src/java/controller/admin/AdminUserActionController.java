/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import constant.action.ActionEnum;
import constant.action.ResourceEnum;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.dto.SearchOrderDTO;
import service.IAccountService;
import service.iml.AccountService;
import utils.MyUtils;

/**
 *
 * @author ASUS
 */
public class AdminUserActionController extends HttpServlet {

    private IAccountService accService = new AccountService();
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
        response.setContentType("text/html;charset=UTF-8");
        String url = ResourceEnum.ADMIN_USER_LIST_PAGE.getResource();
        try (PrintWriter out = response.getWriter()) {
            String button = request.getParameter("button");
            if (button.equals("view_all_orders")) {
                int id = MyUtils.getInteger(request.getParameter("user_id"));
                SearchOrderDTO searchInfo = new SearchOrderDTO(0, null, null, id, 0, 0);
                HttpSession ses = request.getSession();
                ses.setAttribute("searchInfo", searchInfo);
                url = ActionEnum.ADMIN_ORDER_PAGING.getKey();
                response.sendRedirect(url);
                return;
            } else if (button.equals("find")) {
                url = ResourceEnum.ADMIN_USER_PAGING.getResource();
            } else if (button.equals("save_change")) {
                int accId = MyUtils.getInteger(request.getParameter("account_id"));
                int status = MyUtils.getInteger(request.getParameter("status_account"));
                accService.updateStatus(accId, status);
            }
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(ActionEnum.SOMETHING_WRONG.getKey());
            return;
        }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
