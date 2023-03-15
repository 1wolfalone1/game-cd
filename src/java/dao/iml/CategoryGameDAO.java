/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.iml;

import dao.ICategoryGameDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mapper.Mapper;
import model.entity.CategoryEntity;
import model.entity.CategoryGameEntity;
import model.entity.GameEntity;
import utils.DBHelper;

/**
 *
 * @author ASUS
 */
public class CategoryGameDAO extends AbstractDAO<CategoryGameEntity> implements ICategoryGameDAO {

    private CategoryDAO categoryDao = new CategoryDAO();
    private GameDAO gameDao = new GameDAO();

    public CategoryGameDAO() {
        this.genericType = CategoryGameEntity.class;
    }

    @Override
    public CategoryGameEntity getOne(int id) throws SQLException, ClassNotFoundException {
        String sql = "select Id, GameId, CategoryId from CategoryGames where id = ?";
        Connection con = null;

        try {
            con = DBHelper.makeConnectDB();
            List<CategoryGameEntity> list = get(con, sql, Mapper.getInstance(), id);
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
    public List<CategoryEntity> filterByGameId(int id) throws SQLException, ClassNotFoundException {
        String sql = "select c.Id as Id, c.name as name from Categories c join CategoryGames \n"
                + "cg on c.Id = cg.CategoryId where cg.GameId = ?";
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            List<CategoryEntity> list = categoryDao.get(con, sql, Mapper.getInstance(), id);
            if (list.isEmpty()) {
                return null;
            } else {
                return list;
            }
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public List<GameEntity> filterByCateId(int id) throws SQLException, ClassNotFoundException {
        String sql = "select g.Id, g.Name, g.price, g.imgPath, g.description, g.quantity, g.status from Games g "
                + "join CategoryGames cg on cg.GameId = g.Id where cg.CategoryId = ?";
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            List<GameEntity> list = gameDao.get(con, sql, Mapper.getInstance(), id);
            if (list.isEmpty()) {
                return null;
            } else {
                return list;
            }
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        CategoryGameDAO dao = new CategoryGameDAO();
        List<Integer> a = new ArrayList<>();
        a.add(2);
        a.add(6);
        a.add(9);
        dao.updateListCategory(3, a);

    }

    @Override
    public boolean updateListCategory(int gameId, List<Integer> listCateId) throws SQLException, ClassNotFoundException {
        String sqlDelete = "delete CategoryGames where GameId = ?";
        String sqlUpdate = "insert into CategoryGames(GameId, CategoryId) values(?, ?)";
        Connection con = null;
        boolean result = false;
        try {
            con = DBHelper.makeConnectDB();
            con.setAutoCommit(false);
            delete(con, sqlDelete, gameId);
            for (Integer id : listCateId) {
                post(con, sqlUpdate, gameId, id);
            }
            con.commit();

            result = true;
        } catch (Exception e) {
            con.rollback();
        } finally {
            if (con != null) {
                con.close();
            }
            return result;
        }
    }
}
