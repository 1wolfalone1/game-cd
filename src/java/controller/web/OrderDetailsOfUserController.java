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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.dto.AccountModel;
import model.dto.OrderDetailModel;
import service.IOrderDetailService;
import service.IOrderService;
import service.iml.OrderDetailService;
import service.iml.OrderService;
import utils.MyUtils;

/**
 *
 * @author ASUS
 */
public class OrderDetailsOfUserController extends HttpServlet {

    private IOrderDetailService orderDetailSer = new OrderDetailService();
    private IOrderService orderService = new OrderService();

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
            String orderIdString = request.getParameter("order_id");
            int orderId = orderIdString == null || orderIdString.trim().isEmpty()
                    ? 0
                    : Integer.parseInt(orderIdString);
            if (button == null) {
                url = ResourceEnum.USER_ORDER_DETAILS_PAGE.getResource();
                System.out.println(orderId);
                List<OrderDetailModel> listOrderDetail = orderDetailSer.filterByOrderId(orderId);
                request.setAttribute("listOrderDetail", listOrderDetail);
            } else if (button.equals("back")) {
                url = ResourceEnum.LIST_ORDERS_PAGE.getResource();
                HttpSession ses = ((HttpServletRequest) request).getSession();
                AccountModel acc = (AccountModel) ses.getAttribute("account");
                request.setAttribute("listOrder", acc.getUser().getListOrder());
                request.setAttribute("isFilter", "");
            } else if (button.equals("cancel_order")) {
                orderService.cancelOrder(orderId);
                request.setAttribute("success", "Canceled!!!");
            } else if (button.equals("reorder")) {
                int id = MyUtils.getInteger(request.getParameter("order_id"));
                HttpSession ses = ((HttpServletRequest) request).getSession();
                AccountModel acc = (AccountModel) ses.getAttribute("account");
                if (acc == null) {

                } else {
                    if (acc.getUser().getAddress() == null || acc.getUser().getAddress().trim().isEmpty()) {
                        
                        url = ResourceEnum.USER_PROFILE_PAGE.getResource();
                        request.setAttribute("updateAddressMeg", "Please update your address to continue!!!");
                    } else {
                        boolean result = orderService.reorder(id, request);
                        if (result) {
                            request.setAttribute("success", "Reorder succesfully!!!");

                        } else {
                            url = ResourceEnum.VIEW_CART.getResource();
                        }
                    }
                }

            }
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {

            e.printStackTrace();
            response.sendRedirect(ActionEnum.SOMETHING_WRONG.getKey());

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
