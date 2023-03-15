/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.iml;

import dao.IOrderDetailDAO;
import dao.iml.OrderDetailDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dto.OrderDetailModel;
import model.entity.OrderDetailEntity;
import service.IOrderDetailService;

/**
 *
 * @author ASUS
 */
public class OrderDetailService extends AbstractServiceModel<OrderDetailEntity, OrderDetailModel> implements IOrderDetailService{
    private IOrderDetailDAO orderDetailDao = new OrderDetailDAO();
    public OrderDetailService() {
        this.modelType = OrderDetailModel.class;
        this.entityType = OrderDetailEntity.class;
    }

    @Override
    public List<OrderDetailModel> filterByOrderId(int orderId) throws SQLException, ClassNotFoundException {
        List<OrderDetailModel> listM = new ArrayList<>();
        List<OrderDetailEntity> listE = orderDetailDao.filterGameByOrderId(orderId);
        for (OrderDetailEntity orderDetailEntity : listE) {
            listM.add(toModel(orderDetailEntity));
        }
        return listM;
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        OrderDetailService s = new OrderDetailService();
        System.out.println(s.filterByOrderId(6));
    }
}
