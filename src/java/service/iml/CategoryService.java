/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.iml;

import dao.ICategoryDAO;
import dao.iml.CategoryDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dto.AccountModel;
import model.dto.CategoryModel;
import model.entity.AccountEntity;
import model.entity.CategoryEntity;
import service.ICategoryService;

/**
 *
 * @author ASUS
 */
public class CategoryService extends AbstractServiceModel<CategoryEntity, CategoryModel> implements ICategoryService {

    private ICategoryDAO categoryDao = new CategoryDAO();

    public CategoryService() {
        this.modelType = CategoryModel.class;
        this.entityType = CategoryEntity.class;
    }

    @Override
    public CategoryModel getOne(int id) throws SQLException, ClassNotFoundException {
        CategoryModel model = facM.getModel(modelType);
        CategoryEntity entity = categoryDao.getOne(id);
        if (entity == null) {
            return null;
        } else {
            model = toModel(entity);
            return model;

        }
    }

    @Override
    public List<CategoryModel> getAll() throws SQLException, ClassNotFoundException {
        final List<CategoryModel> listModels = new ArrayList<>();
        categoryDao.getAll().stream().forEach(e -> {
            listModels.add(super.toModel(e));
        });
        return listModels;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        CategoryService ser = new CategoryService();
        System.out.println(ser.getOne(5));
    }

    @Override
    public void update(int id, String name) throws SQLException, ClassNotFoundException {
        categoryDao.update(id, name);
    }

    @Override
    public void create(String name) throws SQLException, ClassNotFoundException {
        categoryDao.create(name);
    }
}
