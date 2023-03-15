/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web;

import constant.action.ActionEnum;
import constant.action.ResourceEnum;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.dto.AccountModel;
import model.dto.OrderModel;
import service.IOrderService;
import service.IUserService;
import service.iml.UserService;
import utils.MyUtils;

/**
 *
 * @author ASUS
 */
public class OrderDetailsFilterController extends HttpServlet {

    private IUserService userSer = new UserService();

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
        String url = ResourceEnum.LIST_ORDERS_PAGE.getResource();
        try (PrintWriter out = response.getWriter()) {
            String button = request.getParameter("button");
            HttpSession ses = ((HttpServletRequest) request).getSession();
            AccountModel acc = (AccountModel) ses.getAttribute("account");
            List<OrderModel> listOrder = new ArrayList<>();
            if (button.equals("filter_by_date")) {
                int type = MyUtils.getInteger(request.getParameter("type_date_search"));
                String fromDate = request.getParameter("from_date");
                String toDate = request.getParameter("to_date");

                userSer.setListOrder(acc.getUser(), type, fromDate, toDate);
                request.setAttribute("listOrder", acc.getUser().getListOrder());
                request.setAttribute("isFilter", "");
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
