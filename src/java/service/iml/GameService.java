/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.iml;

import dao.ICategoryGameDAO;
import dao.IGameDAO;
import dao.iml.CategoryGameDAO;
import dao.iml.GameDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.dto.CartMessageDTO;
import model.dto.CategoryModel;
import model.dto.GameModel;
import model.entity.GameEntity;
import service.IGameService;

/**
 *
 * @author ASUS
 */
public class GameService extends AbstractServiceModel<GameEntity, GameModel> implements IGameService {

    private CategoryGameService cateGameService = new CategoryGameService();
    private IGameDAO gameDao = new GameDAO();
    private ICategoryGameDAO cateGameDao = new CategoryGameDAO();
    public GameService() {
        this.modelType = GameModel.class;
        this.entityType = GameEntity.class;
    }

    @Override
    public GameModel toModel(GameEntity e) {
        GameModel m = facM.getModel(modelType);
        m = super.toModel(e);
        m.setListImg();
        return m;
    }

    @Override
    public GameModel getOne(int id) throws SQLException, ClassNotFoundException {
        GameEntity e = facE.getEntity(entityType);
        e = gameDao.getOne(id);
        if (e != null) {
            return this.toModel(e);
        } else {
            return null;
        }
    }

    @Override
    public List<GameModel> getAll() throws SQLException, ClassNotFoundException {
        final List<GameModel> listModels = new ArrayList<>();
        for (GameEntity e : gameDao.getAll()) {
            GameModel k = this.toModel(e);
            this.setListCate(k);
            listModels.add(k);
        }
        return listModels;
    }

    @Override
    public void setListCate(GameModel game) throws SQLException, ClassNotFoundException {
        List<CategoryModel> listCate = cateGameService.filterByGameID(game.getId());
        game.setListCategory(listCate);
    }

    @Override
    public List<GameModel> getPage(int from, int max) throws SQLException, ClassNotFoundException {
        List<GameModel> listM = new ArrayList<>();
        for (GameEntity gameEntity : gameDao.getPage(from, max)) {
            GameModel k = this.toModel(gameEntity);
            this.setListCate(k);
            listM.add(k);
        }
        return listM;
    }

    @Override
    public int countGame(String name, int categoryId) throws SQLException, ClassNotFoundException {
        return gameDao.countGame(name, categoryId);
    }

    @Override
    public int countGame() throws SQLException, ClassNotFoundException {
        return gameDao.countGame();
    }

    @Override
    public List<GameModel> filterByGameNameAndCategoryIdAndPaging(String name, int id, int from, int to)
            throws SQLException, ClassNotFoundException {
        List<GameModel> listM = new ArrayList<>();
        for (GameEntity gameEntity : gameDao.filterByGameIdAndCateIdAndPaging(name, id, from, to)) {
            GameModel k = this.toModel(gameEntity);
            this.setListCate(k);
            listM.add(k);
        }
        return listM;
    }

    
    @Override
    public int countGame(String name, int id, int categoryId) throws SQLException, ClassNotFoundException {
       return gameDao.countGame(name, id, categoryId);
    }

    @Override
    public List<GameModel> filterGameByAdminAndPaging(String name, int id, int categoryId, int from, int to) throws SQLException, ClassNotFoundException {
        List<GameEntity> listE = gameDao.filterGameByAdminAndPaging(name, id,categoryId, from, to);
        return listE.stream().map(e -> {
            GameModel gameM = this.toModel(e);
            return gameM;
        }).collect(Collectors.toList());
    }

    @Override
    public void updateGame(int id, String name, int price, int quantity) throws SQLException, ClassNotFoundException {
        gameDao.updateGame(id, name, price, quantity);
    }

    @Override
    public void updateGame(int id, String name, List<Integer> listCate, int price, int quantity, String description) throws SQLException, ClassNotFoundException {
        cateGameDao.updateListCategory(id, listCate);
        gameDao.updateGame(id, name, price, quantity, description);
    }

    @Override
    public void updateImg(int id, String imgPath) throws SQLException, ClassNotFoundException {
        gameDao.updateImg(id, imgPath);
    }

    @Override
    public void appendImg(int id, String imgPath) throws SQLException, ClassNotFoundException {
        gameDao.appendImg(id, imgPath);
    }

    @Override
    public int createGame(GameModel model, List<Integer> listCategory) throws SQLException, ClassNotFoundException {
        return gameDao.createGame(this.toEntity(model), listCategory);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        GameService gSer = new GameService();
        GameModel model = gSer.getOne(1);
        gSer.setListCate(model);
        System.out.println(gSer.filterGameByAdminAndPaging("", 0,1,  10, 1000));
    }

}
