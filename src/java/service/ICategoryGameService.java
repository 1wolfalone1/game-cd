/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.SQLException;
import java.util.List;
import model.dto.CategoryGameModel;
import model.dto.CategoryModel;

/**
 *
 * @author ASUS
 */
public interface ICategoryGameService extends IServiceModel {

    public CategoryGameModel getOne(int id) throws SQLException, ClassNotFoundException;
    public List<CategoryModel> filterByGameID(int id)  throws SQLException, ClassNotFoundException;
}
