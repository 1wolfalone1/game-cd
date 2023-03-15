/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.iml;

import dao.IUserDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mapper.Mapper;
import model.entity.OrderEntity;
import model.entity.UserEntity;
import utils.DBHelper;

/**
 *
 * @author ASUS
 */
public class UserDAO extends AbstractDAO<UserEntity> implements IUserDAO {

    public UserDAO() {
        this.genericType = UserEntity.class;
    }

    @Override
    public List<UserEntity> getAll() throws SQLException, ClassNotFoundException {
        List<UserEntity> list = new ArrayList<>();
        String sql = "select Id, fullname, phone, address from Users";
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
    public int createUser(UserEntity uEntity) throws SQLException, ClassNotFoundException {
        String sql = "insert into Users( fullname, phone, address) values(?, ?, ?)";
        Connection con = null;
        int k = 0;
        try {
            con = DBHelper.makeConnectDB();
            con.setAutoCommit(false);
            k = post(con, sql, uEntity.getFullName(), uEntity.getPhone(), uEntity.getAddress());
            con.commit();
            return k;
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
            return k;
        }
    }

    @Override
    public UserEntity getOne(int id) throws SQLException, ClassNotFoundException {
        String sql = "select Id, fullname, phone, address from Users where id = ?";
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            List<UserEntity> list = get(con, sql, Mapper.getInstance(), id);
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

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDAO e = new UserDAO();
        System.out.println(e.getOne(111));
    }

    @Override
    public boolean updateProfile(int id, String name, String phone, String address) throws SQLException, ClassNotFoundException {
        String sql = "update Users set fullname = ? ,phone = ?, address = ? where Id = ?";
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            put(con, sql, name, phone, address, id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public void deleteGuest(int id) throws SQLException, ClassNotFoundException {
         String sql = "delete Users where Id = ?";
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            delete(con, sql,  id);
        } catch (Exception e) {
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public int count() throws SQLException, ClassNotFoundException {
        int row = 0;
        String sql = "select count(*) from Users";
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
    public List<UserEntity> getPage(int from, int max) throws SQLException, ClassNotFoundException {
        List<UserEntity> list = new ArrayList<>();
        String sql = "select Id, fullname, phone, address from Users ORDER BY Id ASC "
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
}
