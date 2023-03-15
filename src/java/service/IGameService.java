/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.SQLException;
import java.util.List;
import model.dto.CartMessageDTO;
import model.dto.GameModel;
import model.entity.GameEntity;

/**
 *
 * @author ASUS
 */
public interface IGameService extends IServiceModel {

    public GameModel getOne(int id) throws SQLException, ClassNotFoundException;

    public List<GameModel> getAll() throws SQLException, ClassNotFoundException;

    public void setListCate(GameModel game) throws SQLException, ClassNotFoundException;

    public List<GameModel> getPage(int from, int max) throws SQLException, ClassNotFoundException;

    public int countGame(String name, int categoryId) throws SQLException, ClassNotFoundException;

    public int countGame() throws SQLException, ClassNotFoundException;

    public List<GameModel> filterByGameNameAndCategoryIdAndPaging(String name, int id, int from, int to) throws SQLException, ClassNotFoundException;

    public int countGame(String name, int id, int categoryId) throws SQLException, ClassNotFoundException;

    public List<GameModel> filterGameByAdminAndPaging(String name, int id, int categoryId, int from, int to) throws SQLException, ClassNotFoundException;

    public void updateGame(int id, String name, int price, int quantity) throws SQLException, ClassNotFoundException;

    public void updateGame(int id, String name, List<Integer> listCate, int price, int quantity, String description) throws SQLException, ClassNotFoundException;

    public void updateImg(int id, String imgPath) throws SQLException, ClassNotFoundException;
    public void appendImg(int id, String imgPath) throws SQLException, ClassNotFoundException;
    
     public int createGame(GameModel model, List<Integer> listCategory) throws SQLException, ClassNotFoundException;
}
