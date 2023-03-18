/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.iml;

import factory.entity.EntityFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mapper.Mapper;
import utils.DBHelper;

/**
 *
 * @author ASUS
 */
public abstract class AbstractDAO<T> {

    protected EntityFactory facE = EntityFactory.getInstance();
    protected Class genericType;

    public List<T> get(Connection con, String sql, Mapper<T> mapper, Object... parameters) throws SQLException, ClassNotFoundException {
        List<T> result = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = con.prepareStatement(sql);
            setParameter(stm, parameters);
            rs = stm.executeQuery();
            while (rs.next()) {
                result.add(mapper.map(rs, genericType));
            }
            return result;
        } finally {
            try {
                close(stm, rs);
            } catch (Exception e) {
                return null;
            }
        }

    }

    public synchronized void put(Connection con, String sql, Object... parameters) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = null;
        try {

//            con = DBHelper.makeConnectDB();
            stm = con.prepareStatement(sql);
            setParameter(stm, parameters);
            stm.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                close(stm, null);
            } catch (Exception e) {
                return;
            }
        }
    }

    public synchronized int post(Connection con, String sql, Object... parameters) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            setParameter(stm, parameters);
            stm.executeUpdate();
            rs = stm.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } finally {
            try {
                close(stm, rs);
            } catch (Exception e) {
                return 0;
            }
        }
        return 0;
    }

    public synchronized int delete(Connection con, String sql, Object... parameters) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = null;
        int result = 0;
        try {
            con.setAutoCommit(false);
            stm = con.prepareStatement(sql);
            setParameter(stm, parameters);
            result = stm.executeUpdate();
            return result;
        } finally {
            try {
                close(stm, null);
            } catch (Exception e) {
                return result;
            }
        }
    }

    public int count(Connection con, String sql, Object... parameters) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = null;
        ResultSet result = null;
        try {
//            con = DBHelper.makeConnectDB();
            con.setAutoCommit(false);
            stm = con.prepareStatement(sql);
            setParameter(stm, parameters);
            result = stm.executeQuery();
            if (result.next()) {
                return result.getInt(1);
            }
        } finally {
            try {
                close(stm, null);
            } catch (Exception e) {
                return 0;
            }
        }
        return 0;
    }

    private void setParameter(PreparedStatement statement, Object... parameters) {
        try {

            for (int i = 0; i < parameters.length; i++) {
                Object parameter = parameters[i];
                int index = i + 1;
                if (parameter instanceof Long) {
                    statement.setLong(index, (Long) parameter);
                } else if (parameter instanceof String) {
                    statement.setString(index, (String) parameter);
                } else if (parameter instanceof Integer) {
                    statement.setInt(index, (Integer) parameter);
                } else if (parameter instanceof Date) {
                    statement.setDate(index, (Date) parameter);
                } else if (parameter instanceof Double) {
                    statement.setDouble(index, (Double) parameter);
                } else {
                    statement.setObject(index, parameter);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void close(PreparedStatement stm, ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }

    }
}
