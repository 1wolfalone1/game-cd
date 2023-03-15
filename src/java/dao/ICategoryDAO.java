/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.entity.AccountEntity;
import model.entity.CategoryEntity;

/**
 *
 * @author ASUS
 */
public interface ICategoryDAO {
    public List<CategoryEntity> getAll() throws SQLException, ClassNotFoundException ;
    public CategoryEntity getOne(int id) throws SQLException, ClassNotFoundException ;
    public void update(int id, String name) throws SQLException, ClassNotFoundException;
    public void create(String name) throws SQLException, ClassNotFoundException;
}
