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
import model.dto.GameModel;
import service.IGameService;
import service.iml.GameService;
import utils.MyUtils;

/**
 *
 * @author ASUS
 */
public class AdminGamePagingController extends HttpServlet {

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
        String url = ResourceEnum.ADMIN_GAME_LIST_PAGE.getResource();
        String button = request.getParameter("button");
        int maxItems = 0;
        int maxSlides = 0;
        int currentPage = 1;
        List<GameModel> listGames = new ArrayList<>();
        try {
            if (button.equals("view_all")) {
                maxItems = gameSer.countGame();
                maxSlides = (int) Math.ceil((double) maxItems / SystemConstant.MAX_PAGE_ADMIN_LIST);

                listGames = gameSer.getPage(0, SystemConstant.MAX_PAGE_ADMIN_LIST);
                request.setAttribute("action_list", "view_all");
            } else if (button.equals("filter")) {
                int categoryId
                        = MyUtils.getInteger(request.getParameter("categorySearch"));
                int id = MyUtils.getInteger(request.getParameter("idSearch"));
                String name = request.getParameter("nameSearch");
                maxItems = gameSer.countGame(name, id, categoryId);
                maxSlides = (int) Math.ceil((double) maxItems / SystemConstant.MAX_PAGE_ADMIN_LIST);
                listGames = gameSer.filterGameByAdminAndPaging(name, id, categoryId, (currentPage - 1) * (SystemConstant.MAX_PAGE_ADMIN_LIST), SystemConstant.MAX_PAGE_ADMIN_LIST);
               
                request.setAttribute("action_list", "filter");
                request.setAttribute("searchId", id);
                request.setAttribute("searchName", name);
                request.setAttribute("searchCategory", categoryId);
            }
            request.setAttribute("maxItems", maxItems);
            request.setAttribute("maxSlides", maxSlides);
            request.setAttribute("isPage", true);
            request.setAttribute("listGames", listGames);
            request.setAttribute("currentPage", currentPage);
            
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
        String url = ResourceEnum.ADMIN_GAME_LIST_PAGE.getResource();
        try {
            int currentPage = MyUtils.getInteger(request.getParameter("current_page"));

            int maxItems = request.getParameter("max_items")
                    .trim()
                    .equals("") ? 0 : Integer.parseInt(request.getParameter("max_items"));

            int maxSlides = request.getParameter("max_items")
                    .trim()
                    .equals("") ? 0 : Integer.parseInt(request.getParameter("max_slides"));
            String action = request.getParameter("action_list");
            List<GameModel> listGames = new ArrayList<>();
            String button = request.getParameter("button");
            String isForward = (String) request.getAttribute("forward");
            
            if (isForward == null) {
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
            }
            if (action.equals("view_all")) {

                listGames = gameSer.getPage((currentPage - 1)
                        * (SystemConstant.MAX_PAGE_ADMIN_LIST), SystemConstant.MAX_PAGE_ADMIN_LIST);
                request.setAttribute("isPage", true);
                request.setAttribute("action_list", "view_all");
            } else if (action.equals("filter")) {
                int categoryId = MyUtils.getInteger(request.getParameter("categorySearch"));
                int id = MyUtils.getInteger(request.getParameter("idSearch"));
                String name = request.getParameter("nameSearch");
                listGames = gameSer.filterGameByAdminAndPaging(name, id, categoryId, (currentPage - 1)
                        * (SystemConstant.MAX_PAGE_ADMIN_LIST), SystemConstant.MAX_PAGE_ADMIN_LIST);
                request.setAttribute("searchId", id);
                request.setAttribute("searchName", name);
                request.setAttribute("searchCategory", categoryId);
                request.setAttribute("action_list", "filter");

            }

            request.setAttribute("action_list", action);
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
