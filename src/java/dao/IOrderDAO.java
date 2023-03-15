/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import model.dto.CheckQuantityGameDTO;
import model.dto.OrderModel;
import model.entity.OrderDetailEntity;
import model.entity.OrderEntity;

/**
 *
 * @author ASUS
 */
public interface IOrderDAO {

    public CheckQuantityGameDTO createOrders(int userId, List<OrderDetailEntity> listDetails) throws SQLException, ClassNotFoundException;

    public List<OrderEntity> getOrdersByUserId(int userId) throws SQLException, ClassNotFoundException;

    public OrderEntity getOne(int id) throws SQLException, ClassNotFoundException;

    public void updateStatus(int id, int status) throws SQLException, ClassNotFoundException;

    public List<OrderEntity> getPage(int from, int max) throws SQLException, ClassNotFoundException;

    public List<OrderEntity> filterByAdminAndPaging(int typeSearchDate, Date fromDate, Date toDate, int userId, int orderId, int status,
            int from, int max) throws SQLException, ClassNotFoundException;

    public int countOrder(int typeSearchDate, Date fromDate, Date toDate, int userId, int orderId, int status) throws SQLException, ClassNotFoundException;

    public int countOrder() throws SQLException, ClassNotFoundException;

    public void update(int id, Date shipDate, int status) throws SQLException, ClassNotFoundException;

    public List<OrderEntity> getOrderModelByUser(int id, String type, Date fromDate, Date toDate)
            throws SQLException, ClassNotFoundException;
     public void updateOrdate(int id) throws SQLException, ClassNotFoundException;

}
