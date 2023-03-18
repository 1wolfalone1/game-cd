/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import constant.action.ActionEnum;
import constant.action.ResourceEnum;
import constant.service.SystemConstant;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dto.AccountModel;
import model.dto.CategoryModel;
import model.dto.GameModel;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.ICategoryService;
import service.IGameService;
import service.iml.CategoryService;
import service.iml.GameService;
import utils.MyUtils;

/**
 *
 * @author ASUS
 */
public class AdminGameChangeImageController extends HttpServlet {

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

        //back ward page
        int currentPage = MyUtils.getInteger(request.getParameter("current_page"));

        int maxItems = MyUtils.getInteger(request.getParameter("max_items"));

        int maxSlides = MyUtils.getInteger(request.getParameter("max_slides"));
        String action = request.getParameter("action_list");
        int categoryIdSearch = MyUtils.getInteger(request.getParameter("categorySearch"));
        int idSearch = MyUtils.getInteger(request.getParameter("idSearch"));
        String nameSearch = request.getParameter("nameSearch");
        //
//            Change info

        request.setAttribute("searchId", idSearch);
        request.setAttribute("searchName", nameSearch);
        request.setAttribute("searchCategory", categoryIdSearch);
        request.setAttribute("action_list", action);
        request.setAttribute("maxItems", maxItems);
        request.setAttribute("maxSlides", maxSlides);
        request.setAttribute("currentPage", currentPage);

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
        String url = ResourceEnum.ADMIN_VIEW_GAME_PAGE.getResource();
        try {
            processRequest(request, response);
            int id = MyUtils.getInteger(request.getParameter("id_game"));
            String imgPath = request.getParameter("button");
            GameModel game = gameSer.getOne(id);

            if (game != null) {
                game.removeImg(imgPath);
                gameSer.updateImg(id, game.getImgPath());
                game.setImgPaths(new ArrayList<>());
                game.setListImg();
            }
            List<CategoryModel> listCate = cateSer.getAll();
            request.setAttribute("allCate", listCate);
            gameSer.setListCate(game);
            request.setAttribute("game", game);
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
        String url = ResourceEnum.ADMIN_VIEW_GAME_PAGE.getResource();
        try {
            GameModel game = new GameModel();
            if (game != null) {
                boolean check = uploadAvatar(request, game);
                if (check) {
                    gameSer.appendImg(game.getId(), game.getImgPath());
                }
                game = gameSer.getOne(game.getId());
                game.setImgPaths(new ArrayList<>());
                game.setListImg();
            }
            List<CategoryModel> listCate = cateSer.getAll();
            request.setAttribute("allCate", listCate);
            gameSer.setListCate(game);
            request.setAttribute("game", game);
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {

            e.printStackTrace();
            response.sendRedirect(ActionEnum.SOMETHING_WRONG.getKey());
            return;

        }
    }

    private boolean uploadAvatar(HttpServletRequest request, GameModel game) throws Exception {
        String folder = getServletContext().getRealPath("asset/img/img-product");

        int maxFileSize = 50 * 1024 * 1024;
        int maxMemsize = 50 * 1024 * 1024;
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(maxMemsize);
        //configure a reposittory
        ServletContext servletContext = this.getServletConfig().getServletContext();
//        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
//        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        String folder2 = SystemConstant.IMG_PATH + "img-product";
        upload.setSizeMax(maxMemsize);
        upload.setHeaderEncoding("UTF-8");
        boolean result = false;
        try {
            List<FileItem> items = upload.parseRequest(request);
            for (FileItem item : items) {
                if (item.getFieldName().equals("img")) {
                    String originalFileName = item.getName();

                    int index = originalFileName.lastIndexOf(".");
                    String ext = originalFileName.substring(index + 1);
                    String fileName = System.currentTimeMillis() + "." + ext;
                    String folder1 = folder + "/" + fileName;
                    folder2 = folder2 + "/" + fileName;
                    File file = new File(folder1);
                    File file2 = new File(folder2);
                    item.write(file2);
                    item.write(file);
                    if (ext.equals("png") || ext.equals("jpg") || ext.equals("svg")) {
                        game.setImgPath(fileName);
                        request.setAttribute("status_add_img", "Add successfully!!!");
                        result = true;
                    } else {
                        request.setAttribute("status_add_img", "Add failed!!!");
                        result = false;
                    }
                } else if (item.getFieldName().equals("id_game")) {
                    game.setId(MyUtils.getInteger(item.getString()));
                } else if (item.getFieldName().equals("current_page")) {
                    request.setAttribute("currentPage", item.getString().trim());
                } else if (item.getFieldName().equals("max_items")) {
                    request.setAttribute("maxItems", item.getString());
                } else if (item.getFieldName().equals("max_slides")) {
                    request.setAttribute("maxSlides", item.getString());
                } else if (item.getFieldName().equals("action_list")) {
                    request.setAttribute("action_list", item.getString());
                } else if (item.getFieldName().equals("categorySearch")) {
                    request.setAttribute("searchCategory", item.getString());
                } else if (item.getFieldName().equals("idSearch")) {
                    request.setAttribute("searchId", item.getString());
                } else if (item.getFieldName().equals("nameSearch")) {
                    request.setAttribute("searchName", item.getString());
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("status_add_img", "Add failed!!!");
            return result;
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
