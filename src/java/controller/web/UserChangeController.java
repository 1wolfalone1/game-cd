/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.web;

import constant.action.ActionEnum;
import constant.action.ResourceEnum;
import constant.service.SystemConstant;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.dto.AccountModel;
import model.dto.UserCheckErrorUpdateDTO;
import model.dto.UserModel;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.IAccountService;
import service.IUserService;
import service.iml.AccountService;
import service.iml.UserService;

/**
 *
 * @author ASUS
 */
public class UserChangeController extends HttpServlet {

    private IUserService user = new UserService();
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String url = ResourceEnum.USER_PROFILE_PAGE.getResource();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            UserCheckErrorUpdateDTO status = new UserCheckErrorUpdateDTO();
            String button = request.getParameter("button");
            HttpSession ses = request.getSession();
            AccountModel acc = (AccountModel) ses.getAttribute("account");
            if (acc != null) {
                if (button != null) {
                    String address = request.getParameter("address");
                    String name = request.getParameter("name");
                    String phone = request.getParameter("phone");
                    String oldPass = request.getParameter("password");
                    String newPass = request.getParameter("newPassword");
                    String conFirm = request.getParameter("conFirmPass");
                    UserCheckErrorUpdateDTO error = user.checkChangeProfile(acc, name, phone, oldPass, newPass, conFirm);
                    if (error == null) {
                        if (!oldPass.trim().isEmpty()) {
                            accSer.updatePass(acc.getId(), newPass);
                            acc.setPassword(newPass.trim());
                        }
                        acc.getUser().setFullName(name.trim());
                        acc.getUser().setAddress(address.trim());
                        acc.getUser().setPhone(phone.trim());
                        request.setAttribute("statusInfo", "Update successful.");

                    } else {
                        UserModel userTmp = new UserModel(0, name.trim(), phone.trim(), address.trim(), null);
                        System.out.println(userTmp);
                        request.setAttribute("userTmp", userTmp);
                        request.setAttribute("error", error);
                        request.setAttribute("statusInfo", "Update fail!!! Try again.");
                        request.setAttribute("openChange", true);
                    }
                } else {
                    boolean check = uploadAvatar(request, acc);
                    if (!check) {
                        request.setAttribute("classCSS", "invalid");
                        request.setAttribute("status", "Can't resolve your image!!! Try again.");
                        request.setAttribute("openChange", true);
                    } else {
                        accSer.updateAvatar(acc.getId(), acc.getAvatarPath());
                        request.setAttribute("status", "Update avatar successful.");
                        request.setAttribute("openChange", true);
                        request.setAttribute("changeAva", true);
                        request.setAttribute("classCSS", "");
                    }
                }
            } else {
                url = ActionEnum.LOGIN_PAGE.getKey();
                response.sendRedirect(url);
                return;
            }
            ses.setAttribute("account", acc);
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(ActionEnum.SOMETHING_WRONG.getKey());
        }
    }

    private boolean uploadAvatar(HttpServletRequest request, AccountModel acc) throws Exception {
        String folder = getServletContext().getRealPath("asset/img/useravatar");
        System.out.println(folder);
        int maxFileSize = 50 * 1024 * 1024;
        int maxMemsize = 50 * 1024 * 1024;
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(maxMemsize);
        //configure a reposittory
        ServletContext servletContext = this.getServletConfig().getServletContext();
//        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
//        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        String folder2 = SystemConstant.IMG_PATH + "useravatar";
        upload.setSizeMax(maxMemsize);
        upload.setHeaderEncoding("UTF-8");
        try {
            List<FileItem> items = upload.parseRequest(request);
            for (FileItem item : items) {
                if (item.getFieldName().equals("avatar")) {
                    String originalFileName = item.getName();
                    System.out.println(originalFileName);
                    int index = originalFileName.lastIndexOf(".");
                    String ext = originalFileName.substring(index + 1);
                    String fileName = System.currentTimeMillis() + "." + ext;
                    String folder1 = folder + "/" + fileName;
                    System.out.println(item);
                    System.out.println(folder2 + "folder2 ne");
                    folder2 = folder2 + "/" + fileName;
                    File file = new File(folder1);
                    File file2 = new File(folder2);
                    item.write(file2);
                    item.write(file);
                    if (ext.equals("png") || ext.equals("jpg") || ext.equals("svg")) {
                        acc.setAvatarPath(fileName);
                    } else {
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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
