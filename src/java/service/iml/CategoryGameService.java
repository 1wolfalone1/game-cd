/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.iml;

import dao.ICategoryGameDAO;
import dao.iml.CategoryGameDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import model.dto.CategoryGameModel;
import model.dto.CategoryModel;
import model.entity.CategoryEntity;
import model.entity.CategoryGameEntity;
import service.ICategoryGameService;
import service.ICategoryService;

/**
 *
 * @author ASUS
 */
public class CategoryGameService extends AbstractServiceModel<CategoryGameEntity, CategoryGameModel> implements ICategoryGameService {

    private CategoryService cateSer = new CategoryService();
    private ICategoryGameDAO cateGameDao = new CategoryGameDAO();

    public CategoryGameService() {
        this.modelType = CategoryGameModel.class;
        this.entityType = CategoryGameEntity.class;
    }

    @Override
    public CategoryGameModel getOne(int id) throws SQLException, ClassNotFoundException {
        CategoryGameEntity e = cateGameDao.getOne(id);
        if (e != null) {
            return this.toModel(e);
        }
        return null;
    }

    @Override
    public List<CategoryModel> filterByGameID(int id) throws SQLException, ClassNotFoundException {
        List<CategoryEntity> listE = cateGameDao.filterByGameId(id);
        if (listE != null) {
            return listE.stream().map(e -> cateSer.toModel(e)).collect(Collectors.toList());
        }
        return null;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        CategoryGameService ser = new CategoryGameService();
        System.out.println(ser.filterByGameID(0));
    }

}
