/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.model;

import model.dto.AccountModel;
import model.dto.CategoryGameModel;
import model.dto.CategoryModel;
import model.dto.GameModel;
import model.dto.OrderDetailModel;
import model.dto.OrderModel;
import model.dto.UserModel;

/**
 *
 * @author ASUS
 */
public class ModelFactory {
    private ModelFactory() {
    }
    
    
    private static ModelFactory instance;

    public static synchronized ModelFactory getInstance() {
        if (instance == null) {
            instance = new ModelFactory();
        }
        return instance;
    }

    public <T> T getModel(Class type) {
        if (type.equals(AccountModel.class)) {
            return (T) new AccountModel();
        } else if (type.equals(CategoryModel.class)) {
            return (T) new CategoryModel();
        } else if (type.equals(CategoryGameModel.class)) {
            return (T) new CategoryGameModel();
        } else if (type.equals(OrderModel.class)) {
            return (T) new OrderModel();
        } else if (type.equals(OrderDetailModel.class)) {
            return (T) new OrderDetailModel();
        } else if (type.equals(GameModel.class)) {
            return (T) new GameModel();
        } else if (type.equals(UserModel.class)) {
            return (T) new UserModel();
        }
        return null;

    }
}
