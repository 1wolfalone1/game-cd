/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.iml;

import dao.ICategoryGameDAO;
import dao.IGameDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mapper.Mapper;
import model.entity.AccountEntity;
import model.entity.GameEntity;
import utils.DBHelper;

/**
 *
 * @author ASUS
 */
public class GameDAO extends AbstractDAO<GameEntity> implements IGameDAO {

    private ICategoryGameDAO cateGameDao;

    public GameDAO() {
        this.genericType = GameEntity.class;
    }

    @Override
    public List<GameEntity> getAll() throws SQLException, ClassNotFoundException {
        List<GameEntity> list = new ArrayList<>();
        String sql = "select Id, Name, price, imgPath, description, quantity, status from Games";

        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            list = get(con, sql, Mapper.getInstance());
            return list;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public GameEntity getOne(int id) throws SQLException, ClassNotFoundException {
        String sql = "select Id, Name, price, imgPath, description, quantity, status from Games where id = ?";
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            List<GameEntity> list = get(con, sql, Mapper.getInstance(), id);
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
    public List<GameEntity> getPage(int from, int max) throws SQLException, ClassNotFoundException {
        List<GameEntity> list = new ArrayList<>();
        String sql = "select Id, Name, price, imgPath, description, quantity, status from Games ORDER BY Id ASC "
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
    public int countGame(String name, int categoryId) throws SQLException, ClassNotFoundException {
        int row = 0;
        String sql = "select COUNT(*) from Games g "
                + "join CategoryGames cg on cg.GameId = g.Id where cg.CategoryId = ? and g.Name like ?";
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            if (categoryId == 0) {
                sql = "select COUNT(*) from Games g where g.Name like ?";
                row = count(con, sql, "%" + name + "%");
            } else {
                row = count(con, sql, categoryId, "%" + name + "%");
            }

            return row;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public int countGame() throws SQLException, ClassNotFoundException {
        int row = 0;
        String sql = "select count(*) from games";
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
    public List<GameEntity> filterByGameIdAndCateIdAndPaging(String name, int id, int from, int to)
            throws SQLException, ClassNotFoundException {
        List<GameEntity> list = new ArrayList<>();

        String sql = "select g.Id as Id, Name, price, imgPath, description, quantity, status from Games g "
                + "join CategoryGames cg on  g.Id = cg.GameId where g.Name like ? and cg.CategoryId = ? "
                + "ORDER BY g.Id ASC "
                + "                OFFSET  ? ROWS "
                + "                FETCH NEXT ? ROWS ONLY ";
        if (id == 0) {
            sql = "select g.Id as Id, Name, price, imgPath, description, quantity, status from Games g "
                    + "where g.Name like ? "
                    + "ORDER BY g.Id ASC "
                    + "                OFFSET  ? ROWS "
                    + "                FETCH NEXT ? ROWS ONLY ";
        }
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            if (id == 0) {
                list = get(con, sql, Mapper.getInstance(), "%" + name + "%", from, to);
            } else {
                list = get(con, sql, Mapper.getInstance(), "%" + name + "%", id, from, to);
            }
            return list;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public void decreaseQuantity(Connection con, int id, int quantity) throws SQLException, ClassNotFoundException {
        String sql = "update Games set quantity = ? where Id = ?";
        String sql1 = "select Id, Name, price, imgPath, description, quantity, status from games where id = ?";

        GameEntity game = (GameEntity) get(con, sql1, Mapper.getInstance(), id).get(0);

        put(con, sql, game.getQuantity() - quantity, id);

    }

    @Override
    public void updateQuantityAfterCancelOrderId(int orderId) throws SQLException, ClassNotFoundException {
        int row = 0;
        String sql = "Update Games set quantity = g.quantity + od.quantity from "
                + "Orders o join OrderDetails od on o.Id = od.OrderID "
                + "join Games g on g.Id = od.GameId "
                + "where OrderID = ?";
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            put(con, sql, orderId);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public int countGame(String name, int id, int categoryId) throws SQLException, ClassNotFoundException {
        int row = 0;
        List<Object> listParam = new ArrayList<>();
        String sql = "select g.id from Games g "
                + "join CategoryGames cg on cg.GameId = g.Id where g.Name like ? ";
        listParam.add("%" + name + "%");
        if (id != 0) {
            sql = sql + " and g.id = ?";
            listParam.add(id);
        }
        if (categoryId != 0) {
            sql = sql + " and cg.CategoryId = ?";
            listParam.add(categoryId);
        }
        Connection con = null;
        sql = "select count(*) from (" + sql + " group by g.id) as a";
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
    public List<GameEntity> filterGameByAdminAndPaging(String name, int id, int categoryId, int from, int to) throws SQLException, ClassNotFoundException {
        int row = 0;

        List<Object> listParam = new ArrayList<>();
        String sql = "select g.Id from Games g "
                + "join CategoryGames cg on cg.GameId = g.Id where g.Name like ?";
        listParam.add("%" + name + "%");

        if (id != 0) {
            sql = sql + " and g.id = ? ";
            listParam.add(id);
        }
        if (categoryId != 0) {
            sql = sql + " and cg.CategoryId = ? ";
            listParam.add(categoryId);
        }
        Connection con = null;
        List<GameEntity> list = new ArrayList<>();
        sql = "select Id, Name, price, imgPath, description, quantity, status from Games where id in ("
                + " " + sql + " group by g.id) ";
        sql = sql + " ORDER BY Id ASC "
                + "                OFFSET  ? ROWS "
                + "                FETCH NEXT ? ROWS ONLY ";
        listParam.add(from);
        listParam.add(to);

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

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        GameDAO dao = new GameDAO();
        System.out.println(dao.filterGameByAdminAndPaging("", 0, 1, 1 * 10, 10).size());
    }

    @Override
    public void updateGame(int id, String name, int price, int quantity) throws SQLException, ClassNotFoundException {
        String sql = "Update games set Name = ?, price = ? , quantity = ? where Id = ?";
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            put(con, sql, name, price, quantity, id);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public void updateGame(int id, String name, int price, int quantity, String description)
            throws SQLException, ClassNotFoundException {
        String sql = "Update games set Name = ?, price = ? , quantity = ? , description = ? where Id = ?";
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            put(con, sql, name, price, quantity, description, id);
        } finally {
            if (con != null) {
                con.close();
            }
        }

    }

    @Override
    public void updateImg(int id, String imgPath) throws SQLException, ClassNotFoundException {
        String sql = "update games set imgPath = ? where Id = ?";
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            put(con, sql, imgPath, id);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public void appendImg(int id, String imgPath) throws SQLException, ClassNotFoundException {
        String sql = "update Games set imgPath = imgPath + ? where Id = ?";
        Connection con = null;
        String sql2 = "select Id, Name, price, imgPath, description, quantity, status from games where id = ?";
        try {
            con = DBHelper.makeConnectDB();
            List<GameEntity> list = get(con, sql2, Mapper.getInstance(), id);
            GameEntity gameEntity = list == null || list.isEmpty() ? null : list.get(0);
            if (gameEntity.getImgPath().trim().isEmpty()) {
                put(con, sql, imgPath, id);
            } else {
                put(con, sql, ":" + imgPath, id);
            }
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public int createGame(GameEntity entity, List<Integer> listCategory) throws SQLException, ClassNotFoundException {
        cateGameDao = new CategoryGameDAO();
        String sql = "insert into Games(Name, price, imgPath, description, quantity, status)"
                + " values(?, ?, ?, ?, ?, ?)";
        int status = 1;
        if (entity.getQuantity() == 0) {
            status = 0;
        }
        int id = 0;
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            con.setAutoCommit(false);
            id = post(con, sql, entity.getName(), entity.getPrice(),
                    entity.getImgPath(), entity.getDescription(), entity.getQuantity(), status);
            con.commit();
            con.close();
            cateGameDao.updateListCategory(id, listCategory);
            return id;
        } catch (Exception e) {
            con.rollback();
            return 0;
        } finally {
            if (con != null) {
                con.close();
            }
        }

    }

    @Override
    public int getQuantity(Connection con, int id) throws SQLException, ClassNotFoundException {
        String sql = "select quantity from games where id = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        int result = 0;
        try {
            stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if(rs.next()){
                result = rs.getInt("quantity");
            }
        } finally {
            if(stm != null){
                stm.close();
            }
            if(rs != null){
                rs.close();
            }
            return result;
        }
            
    }

}
