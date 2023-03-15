/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.entity.CategoryEntity;
import model.entity.CategoryGameEntity;
import model.entity.GameEntity;

/**
 *
 * @author ASUS
 */
public interface ICategoryGameDAO {

    public CategoryGameEntity getOne(int id) throws SQLException, ClassNotFoundException;

    public List<CategoryEntity> filterByGameId(int id) throws SQLException, ClassNotFoundException;

    public List<GameEntity> filterByCateId(int id) throws SQLException, ClassNotFoundException;
    
    public boolean updateListCategory(int gameId, List<Integer> listCateId) throws SQLException, ClassNotFoundException;
}
