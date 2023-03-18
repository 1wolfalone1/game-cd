/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web;

import constant.action.ActionEnum;
import constant.action.ResourceEnum;
import constant.service.SystemConstant;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dto.GameModel;
import service.IGameService;
import service.iml.GameService;

/**
 *
 * @author ASUS
 */
public class SearchGameController extends HttpServlet {

    private IGameService gameSer = new GameService();

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
        try {

        } catch (Exception e) {

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
        String url = ResourceEnum.MARKET_PAGE.getResource();
        try {

            int category = request.getParameter("category") == null || request.getParameter("category").trim().isEmpty()
                    ? 0
                    : Integer.parseInt(request.getParameter("category").trim());
            String name = request.getParameter("search_game");

            int maxItems = gameSer.countGame(name, category);
            int maxSlides = (int) Math.ceil((double) maxItems / SystemConstant.MAX_PAGE_ITEMS);
            int currentPage = 1;
            List<GameModel> listGames = gameSer.filterByGameNameAndCategoryIdAndPaging(name, category,
                    (1 - 1) * (SystemConstant.MAX_PAGE_ITEMS), SystemConstant.MAX_PAGE_ITEMS);
            request.setAttribute("isPage", true);
            request.setAttribute("listGames", listGames);
            request.setAttribute("maxItems", maxItems);
            request.setAttribute("maxSlides", maxSlides);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("searchCategory", category);
            request.setAttribute("searchName", name);
            request.getRequestDispatcher(url).forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();

            response.sendRedirect(ActionEnum.SOMETHING_WRONG.getKey());

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
        String url = ResourceEnum.MARKET_PAGE.getResource();
        try {
            int currentPage = request.getParameter("current_page")
                    .trim()
                    .equals("") ? 0 : Integer.parseInt(request.getParameter("current_page"));

            int searchCategory = request.getParameter("search_category")
                    .trim()
                    .equals("") ? 0 : Integer.parseInt(request.getParameter("search_category"));

            String searchName = request.getParameter("search_name");

            int maxItems = request.getParameter("max_items")
                    .trim()
                    .equals("") ? 0 : Integer.parseInt(request.getParameter("max_items"));

            int maxSlides = request.getParameter("max_items")
                    .trim()
                    .equals("") ? 0 : Integer.parseInt(request.getParameter("max_slides"));

            String button = request.getParameter("button");
            if (button.equals("0")) {
                System.out.println("0 neeeeeeeeee");
                if (!(currentPage == 1)) {
                    currentPage = currentPage - 1;
                }
            } else if (button.equals(String.valueOf(maxSlides + 1))) {
                if (!(currentPage == maxSlides)) {
                    currentPage = currentPage + 1;
                }

            } else {
                currentPage = Integer.parseInt(button);
            }
            List<GameModel> listGames = new ArrayList<>();
            if (searchName.trim().isEmpty() && searchCategory == 0) {

                listGames = gameSer.getPage((currentPage - 1)
                        * (SystemConstant.MAX_PAGE_ITEMS), SystemConstant.MAX_PAGE_ITEMS);
                request.setAttribute("searchCategory", 0);
                request.setAttribute("searchName", "");
                request.setAttribute("isPage", true);
            } else {
                listGames = gameSer.filterByGameNameAndCategoryIdAndPaging(searchName, searchCategory,
                        (currentPage - 1) * (SystemConstant.MAX_PAGE_ITEMS), SystemConstant.MAX_PAGE_ITEMS);
                request.setAttribute("searchCategory", searchCategory);
                request.setAttribute("searchName", searchName);
            }

            request.setAttribute("listGames", listGames);
            request.setAttribute("isPage", true);
            request.setAttribute("maxItems", maxItems);
            request.setAttribute("maxSlides", maxSlides);
            request.setAttribute("currentPage", currentPage);

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
