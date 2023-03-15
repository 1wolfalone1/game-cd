/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.iml;

import dao.IGameDAO;
import dao.IOrderDAO;
import dao.IOrderDetailDAO;
import dao.iml.GameDAO;
import dao.iml.OrderDAO;
import dao.iml.OrderDetailDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.dto.CartDTO;
import model.dto.CheckQuantityGameDTO;
import model.dto.OrderDetailModel;
import model.dto.OrderModel;
import model.entity.OrderDetailEntity;
import model.entity.OrderEntity;
import service.IOrderDetailService;
import service.IOrderService;
import utils.MyUtils;

/**
 *
 * @author ASUS
 */
public class OrderService extends AbstractServiceModel<OrderEntity, OrderModel> implements IOrderService {

    private IOrderDetailDAO orderDetailDao = new OrderDetailDAO();
    private IOrderDAO orderDAO = new OrderDAO();
    private IGameDAO gameDao = new GameDAO();
    private IOrderDetailService orderDetailsSer;
    public OrderService() {
        this.modelType = OrderModel.class;
        this.entityType = OrderEntity.class;
    }

    @Override
    public boolean createOrder(int userId, CartDTO cart, HttpServletRequest request) throws SQLException, ClassNotFoundException {
        List<OrderDetailEntity> listOrderDetails = cart.entrySet().stream().map(e -> {
            return new OrderDetailEntity(0, 0, e.getKey().getId(), e.getValue());
        }).collect(Collectors.toList());
        CheckQuantityGameDTO checkQuantity = orderDAO.createOrders(userId, listOrderDetails);
        if (!checkQuantity.isValidQuantity()) {
            request.setAttribute("errorEntity", checkQuantity.getErrorMap());
        }
        return checkQuantity.isValidQuantity();
    }

    @Override
    public List<OrderModel> getOrderModelByUser(int id) throws SQLException, ClassNotFoundException {
        List<OrderModel> listM = new ArrayList<>();
        List<OrderEntity> listE = orderDAO.getOrdersByUserId(id);
        for (OrderEntity orderEntity : listE) {
            OrderModel orderModel = toModel(orderEntity);

            this.setTotalPrice(orderModel);
            listM.add(orderModel);
        }
        return listM;
    }

    @Override
    public List<OrderModel> getOrderModelByUser(int id, int type, String fromDate, String toDate)
            throws SQLException, ClassNotFoundException {
        List<OrderModel> listM = new ArrayList<>();
        if (fromDate == null || fromDate.trim().isEmpty()) {
            fromDate = "1-1-1";
        }
        if (toDate == null || toDate.trim().isEmpty()) {
            toDate = "9999-1-1";
        }
        String typeSearch = "";
        if(type == 1){
            typeSearch = "ordDate";
        } else if(type == 2){
            typeSearch = "shipDate";
        }
        java.sql.Date fromDateSql = MyUtils.utilDateToSqlDate(MyUtils.convertToDate(fromDate, "yyyy-MM-dd"));
        java.sql.Date toDateSql = MyUtils.utilDateToSqlDate(MyUtils.convertToDate(toDate, "yyyy-MM-dd"));
        List<OrderEntity> listE = orderDAO.getOrderModelByUser(id, typeSearch, fromDateSql, toDateSql);
        for (OrderEntity orderEntity : listE) {
            OrderModel orderModel = toModel(orderEntity);
            this.setTotalPrice(orderModel);
            listM.add(orderModel);
        }
        return listM;
    }
    @Override
    public OrderModel getOne(int id) throws SQLException, ClassNotFoundException {
        OrderEntity entity = orderDAO.getOne(id);
        if (entity == null) {
            return null;
        } else {
            return toModel(entity);
        }
    }

    @Override
    public void setTotalPrice(OrderModel model) throws SQLException, ClassNotFoundException {
        if (model != null) {
            int id = model.getId();
            int total = orderDetailDao.getTotalPriceOrder(id);
            model.setTotalPrice(total);
        }

    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        OrderService ser = new OrderService();
        OrderEntity order = new OrderEntity();
        IOrderDetailDAO orderDetailDao = new OrderDetailDAO();
        System.out.println(orderDetailDao.getTotalPriceOrder(6));
    }

    @Override
    public void cancelOrder(int id) throws SQLException, ClassNotFoundException {
        orderDAO.updateStatus(id, 3);
        gameDao.updateQuantityAfterCancelOrderId(id);
    }

    @Override
    public List<OrderModel> getPage(int from, int max) throws SQLException, ClassNotFoundException {
        List<OrderModel> listM = new ArrayList<>();
        for (OrderEntity orderEntity : orderDAO.getPage(from, max)) {
            OrderModel k = this.toModel(orderEntity);
            listM.add(k);
        }
        return listM;
    }

    @Override
    public int countOrder() throws SQLException, ClassNotFoundException {
        return orderDAO.countOrder();
    }

    @Override
    public int countOrder(int typeSearchDate, String fromDate, String toDate, int userId, int orderId, int status) throws SQLException, ClassNotFoundException {
        List<OrderModel> listM = new ArrayList<>();
        if (fromDate == null || fromDate.trim().isEmpty()) {
            fromDate = "1-1-1";
        }
        if (toDate == null || toDate.trim().isEmpty()) {
            toDate = "9999-1-1";
        }
        java.sql.Date fromDateSql = MyUtils.utilDateToSqlDate(MyUtils.convertToDate(fromDate, "yyyy-MM-dd"));
        java.sql.Date toDateSql = MyUtils.utilDateToSqlDate(MyUtils.convertToDate(toDate, "yyyy-MM-dd"));
        return orderDAO.countOrder(typeSearchDate, fromDateSql, toDateSql, userId, orderId, status);
    }

    @Override
    public List<OrderModel> filterByAdminAndPaging(int typeSearchDate, String fromDate, String toDate, int userId, int orderId, int status, int from, int max) throws SQLException, ClassNotFoundException {
        List<OrderModel> listM = new ArrayList<>();
        if (fromDate == null || fromDate.trim().isEmpty()) {
            fromDate = "1-1-1";
        }
        if (toDate == null || toDate.trim().isEmpty()) {
            toDate = "9999-1-1";
        }
        java.sql.Date fromDateSql = MyUtils.utilDateToSqlDate(MyUtils.convertToDate(fromDate, "yyyy-MM-dd"));
        java.sql.Date toDateSql = MyUtils.utilDateToSqlDate(MyUtils.convertToDate(toDate, "yyyy-MM-dd"));
        List<OrderEntity> listE = orderDAO.filterByAdminAndPaging(typeSearchDate, fromDateSql, toDateSql, userId, orderId, status, from, max);
        for (OrderEntity orderEntity : listE) {
            OrderModel k = this.toModel(orderEntity);
            listM.add(k);
        }
        return listM;
    }

    @Override
    public void update(int id, String shipDate, int status) throws SQLException, ClassNotFoundException {
        java.sql.Date sqlDate = null;
        if (!(shipDate == null) && !(shipDate.trim().isEmpty())) {
            sqlDate = MyUtils.utilDateToSqlDate(MyUtils.convertToDate(shipDate, "yyyy-MM-dd"));
        }
        orderDAO.update(id, sqlDate, status);
    }

    @Override
    public boolean reorder(int id, HttpServletRequest request) throws SQLException, ClassNotFoundException {
        orderDetailsSer = new OrderDetailService();
        boolean result = false;
        CheckQuantityGameDTO checkQuantity = orderDetailDao.reorder(id);
        if(!checkQuantity.isValidQuantity()){
            HttpSession ses = request.getSession();
            List<OrderDetailModel> listDetails = orderDetailsSer.filterByOrderId(id);
            CartDTO cartReorder = new CartDTO();
            listDetails.forEach(detail -> cartReorder.put(detail.getGame(), detail.getQuantity()));
            ses.setAttribute("cartGame", cartReorder);
            request.setAttribute("errorEntity", checkQuantity.getErrorMap());
        } else {
            orderDAO.updateStatus(id, 1);
            
            orderDAO.updateOrdate(id);
        }
        return checkQuantity.isValidQuantity();
    }
}
