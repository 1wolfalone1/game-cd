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
import model.dto.GameModel;
import service.ICategoryService;
import service.IGameService;
import service.iml.CategoryService;
import service.iml.GameService;
import utils.MyUtils;

/**
 *
 * @author ASUS
 */
public class AdminGameController extends HttpServlet {

    private IGameService gameSer = new GameService();
    private ICategoryService cateSer = new CategoryService();

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
            if (button.equals("view")) {

                ///paging info
                int currentPage = MyUtils.getInteger(request.getParameter("current_page"));

                int maxItems = MyUtils.getInteger(request.getParameter("max_items"));

                int maxSlides = MyUtils.getInteger(request.getParameter("max_slides"));
                String action = request.getParameter("action_list");
                int categoryIdSearch = MyUtils.getInteger(request.getParameter("categorySearch"));
                int idSearch = MyUtils.getInteger(request.getParameter("idSearch"));
                String nameSearch = request.getParameter("nameSearch");
                request.setAttribute("searchId", idSearch);
                request.setAttribute("searchName", nameSearch);
                request.setAttribute("searchCategory", categoryIdSearch);
                request.setAttribute("action_list", action);
                request.setAttribute("maxItems", maxItems);
                request.setAttribute("maxSlides", maxSlides);
                request.setAttribute("currentPage", currentPage);
                ///
                String id = request.getParameter("id_game");
                GameModel game = gameSer.getOne(Integer.parseInt(id));
                gameSer.setListCate(game);
                game.setImgPaths(game.getImgPaths());
                request.setAttribute("game", game);
                List<CategoryModel> listCate = cateSer.getAll();
                request.setAttribute("allCate", listCate);
                url = ResourceEnum.ADMIN_VIEW_GAME_PAGE.getResource();

            } else if (button.equals("save_game")) {
                int id = MyUtils.getInteger(request.getParameter("id_game"));
                String name = request.getParameter("name");
                int price = MyUtils.getInteger(request.getParameter("price"));
                int quantity = MyUtils.getInteger(request.getParameter("quantity"));
                int status = MyUtils.getInteger(request.getParameter("status"));
                gameSer.updateGame(id, name, price, quantity);
                request.setAttribute("forward", "");
                url = ResourceEnum.ADMIN_GAME_PAGING.getResource();
            }
            System.out.println("aaaaaaaaaaaaaaaaaaa");
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
