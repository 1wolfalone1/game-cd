/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.iml;

import dao.IGameDAO;
import dao.IOrderDetailDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import mapper.Mapper;
import model.dto.CheckQuantityGameDTO;
import model.entity.GameInOrderEntity;
import model.entity.OrderDetailEntity;
import utils.DBHelper;

/**
 *
 * @author ASUS
 */
public class OrderDetailDAO extends AbstractDAO<OrderDetailEntity> implements IOrderDetailDAO {

    private IGameDAO gameDao;

    public OrderDetailDAO() {
        this.genericType = OrderDetailEntity.class;
    }

    @Override
    public CheckQuantityGameDTO createListDetail(Connection con, List<OrderDetailEntity> list, int orderId) throws SQLException, ClassNotFoundException {
        CheckQuantityGameDTO checkQuantity = new CheckQuantityGameDTO(new HashMap<Integer, String>(), true);
        gameDao = new GameDAO();
        String sql = "insert into OrderDetails(OrderID, GameId, quantity) values(?, ?, ?)";
        for (OrderDetailEntity e : list) {
            post(con, sql, orderId, e.getGameId(), e.getQuantity());
            int currentQuantity = gameDao.getQuantity(con, e.getGameId());
            if (e.getQuantity() <= currentQuantity) {
                gameDao.decreaseQuantity(con, e.getGameId(), e.getQuantity());
            } else {
                checkQuantity.setValidQuantity(false);
                checkQuantity.getErrorMap().put(e.getGameId(),
                        "It seems that the requested quantity of " + e.getQuantity()
                        + " units exceeds our current stock of " + currentQuantity + " units");
            }
        }
        return checkQuantity;
    }

    @Override
    public int getTotalPriceOrder(int orderId) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int total = 0;
        try {
            con = DBHelper.makeConnectDB();
            String sql = "select SUM(g.price * od.quantity) as total from OrderDetails od "
                    + "join Games g on od.GameId = g.Id "
                    + "where od.OrderID = ?";
            stm = con.prepareStatement(sql);
            stm.setInt(1, orderId);
            rs = stm.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
            }
        } finally {
            return total;
        }
    }

    @Override
    public List<OrderDetailEntity> filterGameByOrderId(int orderId) throws SQLException, ClassNotFoundException {
        String sql = "select Id, OrderID, GameId, quantity from OrderDetails where OrderID = ?";
        Connection con = null;
        List<OrderDetailEntity> listOrder = new ArrayList<>();
        try {
            con = DBHelper.makeConnectDB();

            listOrder = get(con, sql, Mapper.getInstance(), orderId);

        } finally {
            return listOrder;
        }
    }

    @Override
    public CheckQuantityGameDTO reorder(int orderId) throws SQLException, ClassNotFoundException {
        CheckQuantityGameDTO checkQuantity = new CheckQuantityGameDTO(new HashMap<Integer, String>(), true);
        List<OrderDetailEntity> detailEntitys = filterGameByOrderId(orderId);
        gameDao = new GameDAO();
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            con.setAutoCommit(false);
            for (OrderDetailEntity e : detailEntitys) {
                int currentQuantity = gameDao.getQuantity(con, e.getGameId());
                if (e.getQuantity() <= currentQuantity) {
                    gameDao.decreaseQuantity(con, e.getGameId(), e.getQuantity());
                } else {
                    checkQuantity.setValidQuantity(false);
                    checkQuantity.getErrorMap().put(e.getGameId(),
                            "It seems that the requested quantity of " + e.getQuantity()
                            + " units exceeds our current stock of " + currentQuantity + " units");
                }
            }
            if (!checkQuantity.isValidQuantity()) {
                con.rollback();
            }  else {
                con.commit();
            }
        } finally {
            if (con != null) {
                con.close();
            }
            return checkQuantity;
        }

    }

}
