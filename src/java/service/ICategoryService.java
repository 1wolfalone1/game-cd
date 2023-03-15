/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.SQLException;
import java.util.List;
import model.dto.CategoryModel;

/**
 *
 * @author ASUS
 */
public interface ICategoryService extends IServiceModel {

    public CategoryModel getOne(int id) throws SQLException, ClassNotFoundException;

    public List<CategoryModel> getAll() throws SQLException, ClassNotFoundException;

    public void update(int id, String name) throws SQLException, ClassNotFoundException;

    public void create(String name) throws SQLException, ClassNotFoundException;
}
