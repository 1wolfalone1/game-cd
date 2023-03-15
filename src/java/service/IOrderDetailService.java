/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.SQLException;
import java.util.List;
import model.dto.OrderDetailModel;

/**
 *
 * @author ASUS
 */
public interface IOrderDetailService extends IServiceModel {
    public List<OrderDetailModel> filterByOrderId(int orderId) throws SQLException, ClassNotFoundException;
}
