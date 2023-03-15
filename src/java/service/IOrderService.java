/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.dto.CartDTO;
import model.dto.OrderModel;

/**
 *
 * @author ASUS
 */
public interface IOrderService extends IServiceModel {

    public boolean createOrder(int userId, CartDTO cart, HttpServletRequest request) throws SQLException, ClassNotFoundException;

    public List<OrderModel> getOrderModelByUser(int id) throws SQLException, ClassNotFoundException;

    public OrderModel getOne(int id) throws SQLException, ClassNotFoundException;

    public void setTotalPrice(OrderModel model) throws SQLException, ClassNotFoundException;

    public void cancelOrder(int id) throws SQLException, ClassNotFoundException;

    public List<OrderModel> getPage(int from, int max) throws SQLException, ClassNotFoundException;

    public int countOrder() throws SQLException, ClassNotFoundException;

    public int countOrder(int typeSearchDate, String fromDate, String toDate, int userId, int orderId, int status) throws SQLException, ClassNotFoundException;

    public List<OrderModel> filterByAdminAndPaging(int typeSearchDate, String fromDate, String toDate,
            int userId, int orderId, int status,
            int from, int max) throws SQLException, ClassNotFoundException;

    public void update(int id, String shipDate, int status) throws SQLException, ClassNotFoundException;

    public List<OrderModel> getOrderModelByUser(int id, int type, String fromDate, String toDate) throws SQLException, ClassNotFoundException;

    public boolean reorder(int id, HttpServletRequest request) throws SQLException, ClassNotFoundException;
}
