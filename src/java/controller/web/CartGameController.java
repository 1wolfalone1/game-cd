/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web;

import com.google.gson.Gson;
import constant.action.ActionEnum;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.dto.CartDTO;
import model.dto.CartMessageDTO;
import model.dto.GameModel;
import service.IGameService;
import service.iml.GameService;

/**
 *
 * @author ASUS
 */
public class CartGameController extends HttpServlet {

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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String tmp = request.getParameter("id");
            int id = tmp == null || tmp.trim().isEmpty()
                    ? 0
                    : Integer.parseInt(tmp);
            String button = request.getParameter("button");
            HttpSession ses = request.getSession();
            CartDTO cart = (CartDTO) ses.getAttribute("cartGame");
            CartMessageDTO meg = new CartMessageDTO();
            System.out.println(button + " " + id);
            if (cart == null) {
                cart = new CartDTO();
            }
            GameModel game = gameSer.getOne(id);
            if (game == null) {
                meg.setWrong("Something wrong!!! Game doesn't exist in repository.");
            } else if (game.getStatus() == 1) {
                meg.setMegSuccessFul("Game added to cart successfully.");
                if (button.equals("take")) {
                    cart.addTooCart(game);
                } else if (button.equals("increase")) {
                    cart.increaseIndexItem(game);
                    meg.setMegSuccessFul("");

                } else if (button.equals("change")) {
                    String tmpQuantity = request.getParameter("quantity");
                    if (tmpQuantity == null || tmpQuantity.trim().isEmpty()) {

                    } else {
                        int quantity = Integer.parseInt(tmpQuantity);
                        cart.changeQuantity(game, quantity);
                        meg.setMegSuccessFul("Add to cart " + quantity + " CDs " + game.getName() + ".");

                    }
                }
            } else {
                meg.setReject("Game is not currently available!!!");
            }
            if (button.equals("decrease")) {
                cart.decreaseIndexItem(game);
                meg.setMegSuccessFul("");

            } else if (button.equals("remove")) {
                System.out.println(game);
                cart.remove(game);
                meg.setMegSuccessFul("");
            }

            ses.setAttribute("cartGame", cart);
            String megJson = new Gson().toJson(meg);
            out.print(megJson);
        } catch (Exception e) {
            CartMessageDTO meg = new CartMessageDTO();
            meg.setWrong("Something wrong!!! Please try again.");
            String megJson = new Gson().toJson(meg);
            out.print(megJson);
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
