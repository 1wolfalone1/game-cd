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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dto.OrderDetailModel;
import model.dto.SearchOrderDTO;
import service.IOrderDetailService;
import service.IOrderService;
import service.iml.OrderDetailService;
import service.iml.OrderService;
import utils.MyUtils;

/**
 *
 * @author ASUS
 */
public class AdminOrderActionController extends HttpServlet {

    private IOrderService ordSer = new OrderService();
    private IOrderDetailService ordDetailSer = new OrderDetailService();
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
        String url = ResourceEnum.ADMIN_ORDER_PAGING.getResource();
        try (PrintWriter out = response.getWriter()) {
            String button = request.getParameter("button");
            if (button.equals("filter")) {
                int typeSearhDate = MyUtils.getInteger(request.getParameter("type_search_date"));
                String from = request.getParameter("form_date");
                String to = request.getParameter("to_date");
                int userId = MyUtils.getInteger(request.getParameter("user_id"));
                int orderId = MyUtils.getInteger(request.getParameter("order_id"));
                int status = MyUtils.getInteger(request.getParameter("status"));
                SearchOrderDTO searchInfo = new SearchOrderDTO(typeSearhDate, from, to, userId, orderId, status);
                request.getSession().setAttribute("searchInfo", searchInfo);
                System.out.println(searchInfo + " searhinfo");
            } else if (button.equals("view_all")) {
                request.getSession().setAttribute("searchInfo", null);
            } else if (button.equals("update")) {
                int id = MyUtils.getInteger(request.getParameter("id_order"));
                String shipDate = request.getParameter("order_ship_date");
                int status = MyUtils.getInteger(request.getParameter("order_status"));
                System.out.println(status + "-------------------" + request.getParameter("order_status"));
                ordSer.update(id, shipDate, status);
            } else if (button.equals("view_order_details")) {
                url = ResourceEnum.ADMIN_ORDER_DETAILS_PAGE.getResource();
                int id = MyUtils.getInteger(request.getParameter("id_order"));
                List<OrderDetailModel> listDetails = ordDetailSer.filterByOrderId(id);
                request.setAttribute("listDetails", listDetails);
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
