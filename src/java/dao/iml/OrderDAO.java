/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.iml;

import dao.IOrderDAO;
import dao.IOrderDetailDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import mapper.Mapper;
import model.dto.CheckQuantityGameDTO;
import model.entity.OrderDetailEntity;
import model.entity.OrderEntity;
import utils.DBHelper;

/**
 *
 * @author ASUS
 */
public class OrderDAO extends AbstractDAO<OrderEntity> implements IOrderDAO {

    private IOrderDetailDAO detailDAO;

    public OrderDAO() {
        this.genericType = OrderEntity.class;
    }

    @Override
    public CheckQuantityGameDTO createOrders(int userId, List<OrderDetailEntity> listDetails) throws SQLException, ClassNotFoundException {
        detailDAO = new OrderDetailDAO();
        String sql = "insert into Orders(OrdDate, UserId, status) values(GETDATE(), ?, 1)";
        Connection con = null;
        CheckQuantityGameDTO checkQuantity = null;
        try {
            con = DBHelper.makeConnectDB();
            con.setAutoCommit(false);
            int orderId = post(con, sql, userId);

            checkQuantity = detailDAO.createListDetail(con, listDetails, orderId);

            if (checkQuantity.isValidQuantity()) {
                con.commit();

            } else {
                con.rollback();
            }

        } catch (Exception e) {
            con.rollback();
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
            return checkQuantity;
        }

    }

    @Override
    public List<OrderEntity> getOrdersByUserId(int userId) throws SQLException, ClassNotFoundException {
        String sql = "select Id, OrdDate, shipdate, status, UserId  from Orders where UserId = ?";
        OrderEntity entity = facE.getEntity(genericType);
        List<OrderEntity> list = new ArrayList<>();
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            list = get(con, sql, Mapper.getInstance(), userId);
        } catch (Exception e) {
            list = null;
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
            return list;
        }
    }

    @Override
    public OrderEntity getOne(int id) throws SQLException, ClassNotFoundException {
        String sql = "select Id, OrdDate, shipdate, status, UserId  from Orders where Id = ?";
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            List<OrderEntity> list = get(con, sql, Mapper.getInstance(), id);
            if (list.isEmpty()) {
                return null;
            } else {
                return list.get(0);
            }
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public void updateStatus(int id, int status) throws SQLException, ClassNotFoundException {
        String sql = "Update orders set status = ? where id = ?";
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            put(con, sql, status, id);

        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public void updateOrdate(int id) throws SQLException, ClassNotFoundException {
        String sql = "Update orders set ordDate = getDate() where id = ?";
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            put(con, sql, id);

        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public List<OrderEntity> getPage(int from, int max) throws SQLException, ClassNotFoundException {
        List<OrderEntity> list = new ArrayList<>();
        String sql = "select Id, OrdDate, shipdate, status, UserId  from Orders ORDER BY Id ASC "
                + "OFFSET  ? ROWS "
                + "FETCH NEXT ? ROWS ONLY ";
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            list = get(con, sql, Mapper.getInstance(), from, max);
            return list;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public int countOrder() throws SQLException, ClassNotFoundException {
        int row = 0;
        String sql = "select count(*) from Orders";
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            row = count(con, sql);
            return row;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public List<OrderEntity> filterByAdminAndPaging(int typeSearchDate, Date fromDate, Date toDate,
            int userId, int orderId, int status, int from, int max) throws SQLException, ClassNotFoundException {
        List<Object> listParam = new ArrayList<>();
        String sql = "select Id, OrdDate, shipdate, status, UserId from Orders where 1 = 1";
        if (typeSearchDate != 0) {
            if (typeSearchDate == 1) {
                sql = sql + " and OrdDate between ? and ? ";
            } else if (typeSearchDate == 2) {
                sql = sql + " and shipdate between ? and ? ";
            }
            listParam.add(fromDate);
            listParam.add(toDate);
        }
        if (userId != 0) {
            sql = sql + " and UserId = ? ";
            listParam.add(userId);
        }
        if (orderId != 0) {
            sql = sql + " and Id = ? ";
            listParam.add(orderId);
        }
        if (status != 0) {
            sql = sql + " and status = ? ";
            listParam.add(status);
        }
        Connection con = null;
        List<OrderEntity> list = new ArrayList<>();
        sql = sql + " ORDER BY Id ASC "
                + "                OFFSET  ? ROWS "
                + "                FETCH NEXT ? ROWS ONLY ";
        listParam.add(from);
        listParam.add(max);

        try {
            con = DBHelper.makeConnectDB();
            list = get(con, sql, Mapper.getInstance(), listParam.toArray());
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return list;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public int countOrder(int typeSearchDate, Date fromDate, Date toDate, int userId, int orderId, int status) throws SQLException, ClassNotFoundException {
        int row = 0;
        List<Object> listParam = new ArrayList<>();
        String sql = "select count(*) from Orders where 1 = 1";
        if (typeSearchDate != 0) {
            if (typeSearchDate == 1) {
                sql = sql + " and OrdDate between ? and ? ";
            } else if (typeSearchDate == 2) {
                sql = sql + " and shipdate between ? and ? ";
            }
            listParam.add(fromDate);
            listParam.add(toDate);
        }
        if (userId != 0) {
            sql = sql + " and UserId = ? ";
            listParam.add(userId);
        }
        if (orderId != 0) {
            sql = sql + " and Id = ? ";
            listParam.add(orderId);
        }
        if (status != 0) {
            sql = sql + " and status = ? ";
            listParam.add(status);
        }
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            row = count(con, sql, listParam.toArray());
            return row;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public void update(int id, Date shipDate, int status) throws SQLException, ClassNotFoundException {
        String sql = "Update orders set shipDate = ?, status  = ? where id = ?";
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            put(con, sql, shipDate, status, id);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public List<OrderEntity> getOrderModelByUser(int id, String type, Date fromDate, Date toDate)
            throws SQLException, ClassNotFoundException {
        String sql = "select Id, OrdDate, shipdate, status, UserId  from"
                + " Orders where +" + type + " between ? and ? and UserId = ?";
        OrderEntity entity = facE.getEntity(genericType);
        List<OrderEntity> list = new ArrayList<>();
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            list = get(con, sql, Mapper.getInstance(), fromDate, toDate, id);
        } catch (Exception e) {
            list = null;
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
            return list;
        }
    }

}
