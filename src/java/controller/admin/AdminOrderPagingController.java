/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import constant.action.ActionEnum;
import constant.action.ResourceEnum;
import constant.service.SystemConstant;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.dto.OrderModel;
import model.dto.PagingDTO;
import model.dto.SearchOrderDTO;
import service.IOrderService;
import service.iml.OrderService;
import utils.MyUtils;

/**
 *
 * @author ASUS
 */
public class AdminOrderPagingController extends HttpServlet {

    private IOrderService ordSer = new OrderService();

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
        response.setContentType("text/html;charset=UTF-8");
        HttpSession ses = request.getSession();
        String url = ResourceEnum.ADMIN_ORDERS_LIST_PAGE.getResource();

        try (PrintWriter out = response.getWriter()) {
            String button = request.getParameter("button");
            int maxItems = 0;
            int maxSlides = 0;
            int currentPage = 1;

            List<OrderModel> listOrder = new ArrayList<>();
            SearchOrderDTO searchInfo = (SearchOrderDTO) ses.getAttribute("searchInfo");
            if (searchInfo == null) {
                listOrder = ordSer.getPage((currentPage - 1) * (SystemConstant.MAX_PAGE_ADMIN_LIST), SystemConstant.MAX_PAGE_ADMIN_LIST);
                maxItems = ordSer.countOrder();
                maxSlides = (int) Math.ceil((double) maxItems / SystemConstant.MAX_PAGE_ADMIN_LIST);
            } else {
                maxItems = ordSer.countOrder(searchInfo.getTypeSearchDate(),
                        searchInfo.getFrom(), searchInfo.getTo(),
                        searchInfo.getUserId(), searchInfo.getOrderId(), searchInfo.getStatus());
                maxSlides = (int) Math.ceil((double) maxItems / SystemConstant.MAX_PAGE_ADMIN_LIST);
                listOrder = listOrder = ordSer.filterByAdminAndPaging(searchInfo.getTypeSearchDate(),
                        searchInfo.getFrom(), searchInfo.getTo(),
                        searchInfo.getUserId(), searchInfo.getOrderId(), searchInfo.getStatus(),
                        (currentPage - 1) * (SystemConstant.MAX_PAGE_ADMIN_LIST), SystemConstant.MAX_PAGE_ADMIN_LIST);
            }
            PagingDTO paging = new PagingDTO(maxItems, maxSlides, currentPage, true);
            request.setAttribute("listOrder", listOrder);
            ses.setAttribute("paging", paging);
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(ActionEnum.SOMETHING_WRONG.getKey());
            return;
        }
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
        response.setContentType("text/html;charset=UTF-8");
        HttpSession ses = request.getSession();
        String url = ResourceEnum.ADMIN_ORDERS_LIST_PAGE.getResource();
        try (PrintWriter out = response.getWriter()) {
            PagingDTO paging = (PagingDTO) ses.getAttribute("paging");
            List<OrderModel> listOrder = new ArrayList<>();
            String button = request.getParameter("button");
            if (paging != null) {
                int currentPage = paging.getCurrentPage();
                if (button.equals("0")) {
                    System.out.println("0 neeeeeeeeee");
                    if (!(currentPage == 1)) {
                        currentPage = currentPage - 1;
                        paging.setCurrentPage(currentPage);
                    }
                } else if (button.equals(String.valueOf(paging.getMaxSlides() + 1))) {
                    if (!(currentPage == paging.getMaxSlides())) {
                        currentPage = currentPage + 1;
                        paging.setCurrentPage(currentPage);
                    }
                } else {
                    currentPage = MyUtils.getInteger(button) == 0 ? currentPage : MyUtils.getInteger(button);
                    paging.setCurrentPage(currentPage);

                }
                SearchOrderDTO searchInfo = (SearchOrderDTO) ses.getAttribute("searchInfo");
                if (searchInfo == null) {
                    listOrder = ordSer.getPage((currentPage - 1) * (SystemConstant.MAX_PAGE_ADMIN_LIST), SystemConstant.MAX_PAGE_ADMIN_LIST);
                } else {
                    listOrder = listOrder = ordSer.filterByAdminAndPaging(searchInfo.getTypeSearchDate(),
                            searchInfo.getFrom(), searchInfo.getTo(),
                            searchInfo.getUserId(), searchInfo.getOrderId(), searchInfo.getStatus(),
                            (currentPage - 1) * (SystemConstant.MAX_PAGE_ADMIN_LIST), SystemConstant.MAX_PAGE_ADMIN_LIST);
                }
            }
            
            paging.setPage(true);
            request.setAttribute("listOrder", listOrder);
            ses.setAttribute("paging", paging);
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(ActionEnum.SOMETHING_WRONG.getKey());
            return;
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
