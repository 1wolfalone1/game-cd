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
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.dto.AccountModel;
import model.dto.CartDTO;
import service.IOrderService;
import service.IUserService;
import service.iml.OrderService;
import service.iml.UserService;
import utils.MyUtils;

/**
 *
 * @author ASUS
 */
public class OrderGameController extends HttpServlet {

    private IUserService userSer = new UserService();
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
        String url = ResourceEnum.ORDER_PAGE.getResource();
        HttpSession ses = request.getSession();

        try {
            if (ses.getAttribute("account") != null) {
                if (((AccountModel) ses.getAttribute("account")).getUser().getAddress() == null
                        || ((AccountModel) ses.getAttribute("account")).getUser().getAddress().trim().isEmpty()) {
                    request.setAttribute("updateAddressMeg", "Please update your address, before ordering.");
                    url = ResourceEnum.USER_PROFILE_PAGE.getResource();
                } else {
                    request.setAttribute("status", 2);
                    Date now = new Date();
                    request.setAttribute("orderDate", MyUtils.convertDateToString(now, "MM/dd/yyyy"));
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
        String url = ResourceEnum.ORDER_PAGE.getResource();
        try (PrintWriter out = response.getWriter()) {
            int step
                    = request.getParameter("step") == null || request.getParameter("step").trim().isEmpty()
                    ? 1
                    : Integer.parseInt(request.getParameter("step"));
            HttpSession ses = request.getSession();

            if (step == 1) {
                step = processStepOne(request);
            } else if (step == 2) {
                step = processStepTwo(request, ses);
                if(step == 0){
                    url = ResourceEnum.VIEW_CART.getResource();
                }
            } else if (step == 3) {
                url = ActionEnum.MARKET_PAGE.getKey();
                response.sendRedirect(url);
                return;
            }
            Date now = new Date();
            request.setAttribute("orderDate", MyUtils.convertDateToString(now, "MM/dd/yyyy"));
            request.setAttribute("status", step);
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            response.sendRedirect(ActionEnum.PAGE_NOT_FOUND.getKey());
            e.printStackTrace();
            return;
        }
    }

    public int processStepOne(HttpServletRequest request) throws Exception {
        int step = 1;
        try {
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            boolean test = true;
            if (name.trim().isEmpty()) {
                request.setAttribute("errorName", "Name is required!!!");
                test = false;
            }
            if (!phone.trim().matches("(84|0[3|5|7|8|9])+([0-9]{7,11})\\b")) {
                request.setAttribute("errorPhone", "Not a valid phone number!");
                test = false;
            }
            if (phone.trim().isEmpty()) {
                request.setAttribute("errorPhone", "Phone is required!!!");
                test = false;

            }
            if (address.trim().isEmpty()) {
                request.setAttribute("errorAddress", "Address is required!!!");
                test = false;
            }
            if (test) {
                step++;
            }
        } finally {
            return step;
        }
    }

    public int processStepTwo(HttpServletRequest request, HttpSession ses) throws Exception {
        int step = 2;
        boolean hasCreateUser = false;
        int id = 0;

        try {
            CartDTO cart = (CartDTO) ses.getAttribute("cartGame");
            if (cart == null) {
                throw new Exception();
            }
            if (ses.getAttribute("account") != null) {
                id = ((AccountModel) ses.getAttribute("account")).getUser().getId();
            } else {
                String name = request.getParameter("name_final");
                String phone = request.getParameter("phone_final");
                String address = request.getParameter("address_final");
                id = userSer.createUser(name, phone, address);
                if (id == 0) {
                    return 0;
                }
                hasCreateUser = true;
            }
            if (orderService.createOrder(id, cart, request)) {
                step++;
            } else {
                step = 0;
            }
        } catch (Exception e) {
            if (hasCreateUser) {
                userSer.deleteGuest(id);
            }
            e.printStackTrace();

        } finally {
            return step;

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
