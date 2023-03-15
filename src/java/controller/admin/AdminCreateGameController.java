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
public class AdminCreateGameController extends HttpServlet {

    private IGameService gameSer = new GameService();
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
        String url = ResourceEnum.ADMIN_VIEW_GAME_PAGE.getResource();
        try (PrintWriter out = response.getWriter()) {
            int id = createGame(request);
            List<CategoryModel> listCate = cateSer.getAll();

            if (id == 0) {
                url = ResourceEnum.ADMIN_NEW_GAME_PAGE.getResource();
                request.setAttribute("status_create_game", "Create game failed!!! Try again.");
                request.setAttribute("listCate", listCate);

            } else {
                GameModel game = gameSer.getOne(id);
                request.setAttribute("game", game);
                request.setAttribute("allCate", listCate);
                request.setAttribute("status_create_game", "Create game successful.");

            }
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(ActionEnum.SOMETHING_WRONG.getKey());

        }
    }

    private int createGame(HttpServletRequest request) throws Exception {
        int result = 0;
        GameModel game = new GameModel();
        List<Integer> listCategory = new ArrayList<>();
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
        try {
            List<FileItem> items = upload.parseRequest(request);
            game.setImgPath("");
            for (FileItem item : items) {
                if (item.getFieldName().equals("img_create")) {
                    String originalFileName = item.getName();
                    System.out.println(originalFileName);
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
                    } else {
                    }
                } else if (item.getFieldName().equals("name_create")) {
                    game.setName(item.getString());
                } else if (item.getFieldName().equals("category_create")) {
                    for (String string : item.getString().split("\n")) {
                        listCategory.add(MyUtils.getInteger(string));
                    }
                } else if (item.getFieldName().equals("price_create")) {
                    game.setPrice(MyUtils.getInteger(item.getString()));
                } else if (item.getFieldName().equals("quantity_create")) {
                    game.setQuantity(MyUtils.getInteger(item.getString()));
                } else if (item.getFieldName().equals("description_create")) {
                    game.setDescription(item.getString());
                }
            }
            result = gameSer.createGame(game, listCategory);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("status_create_game", "Create game failed!!! Try again.");
            return 0;
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
