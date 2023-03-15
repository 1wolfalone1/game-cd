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
import model.dto.CategoryModel;
import service.ICategoryService;
import service.iml.CategoryService;

/**
 *
 * @author ASUS
 */
public class AdminGameControlController extends HttpServlet {

    ICategoryService cateSer = new CategoryService();

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
        String url = ResourceEnum.ADMIN_GAME_LIST_PAGE.getResource();
        try (PrintWriter out = response.getWriter()) {
            String button = request.getParameter("button");
            if (button.equals("view_all") || button.equals("filter")) {
                url = ResourceEnum.ADMIN_GAME_PAGING.getResource();
            } else if (button.equals("add")) {
                url = ResourceEnum.ADMIN_NEW_GAME_PAGE.getResource();
                List<CategoryModel> listCate = cateSer.getAll();
                request.setAttribute("listCate", listCate);
            } else if (button.equals("view_category")) {
                url = ResourceEnum.ADMIN_NEW_CATEGORY_PAGE.getResource();
                List<CategoryModel> listCate = cateSer.getAll();
                request.setAttribute("listCate", listCate);
            }
            
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(ActionEnum.SOMETHING_WRONG.getKey());
        }
    }

    public void ViewAllGame() {

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
