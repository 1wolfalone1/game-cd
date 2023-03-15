/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.iml;

import dao.ICategoryDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mapper.Mapper;
import model.entity.CategoryEntity;
import model.entity.GameEntity;
import utils.DBHelper;

/**
 *
 * @author ASUS
 */
public class CategoryDAO extends AbstractDAO<CategoryEntity> implements ICategoryDAO{
     public CategoryDAO() {
        this.genericType = CategoryEntity.class;
    }

    @Override
    public List<CategoryEntity> getAll() throws SQLException, ClassNotFoundException {
        List<CategoryEntity> list = new ArrayList<>();
        String sql = "select Id, name from Categories";

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
    public CategoryEntity getOne(int id) throws SQLException, ClassNotFoundException {
         String sql = "select Id, name from Categories where id = ?";
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            List<CategoryEntity> list = get(con, sql, Mapper.getInstance(), id);
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
        CategoryDAO dao = new CategoryDAO();
        System.out.println(dao.getAll());
    }

    @Override
    public void update(int id, String name) throws SQLException, ClassNotFoundException {
        String sql = "update Categories set name = ? where Id = ?";
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            put(con, sql, name, id);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public void create(String name) throws SQLException, ClassNotFoundException {
        String sql = "insert Categories(name) values(?)";
        Connection con = null;
        try {
            con = DBHelper.makeConnectDB();
            post(con, sql, name);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
}
