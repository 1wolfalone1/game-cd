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
import model.dto.AccountModel;
import model.dto.PagingDTO;
import model.dto.UserModel;
import service.IAccountService;
import service.iml.AccountService;
import utils.MyUtils;

/**
 *
 * @author ASUS
 */
public class AdminUserAccountPaging extends HttpServlet {

    private IAccountService accSer = new AccountService();

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
        try (PrintWriter out = response.getWriter()) {

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
        String url = ResourceEnum.ADMIN_ACOUNT_LIST_PAGE.getResource();
        String button = request.getParameter("button");
        try {
            if (button.equals("find")) {
                int id = MyUtils.getInteger(request.getParameter("acc_id"));
                AccountModel accout = accSer.getOne(id);
                
                request.setAttribute("find_one", "");
                request.setAttribute("account_profileeeeeee", accout);
                
            } else if (button.equals("view_all")) {
                int status = MyUtils.getInteger(request.getParameter("status"));

                int maxItems = accSer.count(status);
                int maxSlides = (int) Math.ceil((double) maxItems / SystemConstant.MAX_PAGE_ADMIN_LIST);
                int currentPage = 1;
                
                HttpSession ses = request.getSession();
                
                List<AccountModel> listAccounts = accSer.getPage((currentPage - 1) * SystemConstant.MAX_PAGE_ADMIN_LIST,
                        SystemConstant.MAX_PAGE_ADMIN_LIST, status);
                
                PagingDTO paging = new PagingDTO(maxItems, maxSlides, currentPage, true);
                
                ses.setAttribute("pagingAccount", paging);
                request.setAttribute("status_search", status);
                request.setAttribute("listAccounts", listAccounts);
            }
            request.getRequestDispatcher(url).forward(request, response);
            return;
        } catch (Exception e) {
            e.printStackTrace();
//            response.sendRedirect(ActionEnum.SOMETHING_WRONG.getKey());
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
        HttpSession ses = request.getSession();
        String url = ResourceEnum.ADMIN_ACOUNT_LIST_PAGE.getResource();
        try (PrintWriter out = response.getWriter()) {
            int status = MyUtils.getInteger(request.getParameter("status_search"));

            PagingDTO paging = (PagingDTO) ses.getAttribute("pagingAccount");
            List<AccountModel> list = new ArrayList<>();
            String button = request.getParameter("button");
            
            if (paging != null) {
                int currentPage = paging.getCurrentPage();
                
                if (button.equals("0")) {
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
                
                list = accSer.getPage((currentPage - 1) * SystemConstant.MAX_PAGE_ADMIN_LIST,
                        SystemConstant.MAX_PAGE_ADMIN_LIST, status);
            }

            paging.setPage(true);
            
            request.setAttribute("status_search", status);
            request.setAttribute("listAccounts", list);
            ses.setAttribute("pagingAccount", paging);
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
