/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

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
import utils.MyUtils;

/**
 *
 * @author ASUS
 */
public class AdminCategoryController extends HttpServlet {

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
        String url = ResourceEnum.ADMIN_NEW_CATEGORY_PAGE.getResource();
        try (PrintWriter out = response.getWriter()) {
            String button = request.getParameter("button");
            String name = request.getParameter("name");
            if (button.equals("change")) {
                int id = MyUtils.getInteger(request.getParameter("id_category"));
                cateSer.update(id, name);
                request.setAttribute("status_cate", "Change successfully.");

            } else if (button.equals("add")) {
                cateSer.create(name);
                request.setAttribute("status_cate", "Create successfully.");
            }

            List<CategoryModel> listCate = cateSer.getAll();
            request.setAttribute("listCate", listCate);
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            request.setAttribute("status_cate", "Action failed!!! Try again.");
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
