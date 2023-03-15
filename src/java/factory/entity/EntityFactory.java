/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.entity;

import model.entity.AccountEntity;
import model.entity.CategoryEntity;
import model.entity.CategoryGameEntity;
import model.entity.GameEntity;
import model.entity.OrderDetailEntity;
import model.entity.OrderEntity;
import model.entity.UserEntity;

/**
 *
 * @author ASUS
 */
public class EntityFactory {

    private EntityFactory() {
    }
    
    
    private static EntityFactory instance;

    public static synchronized EntityFactory getInstance() {
        if (instance == null) {
            instance = new EntityFactory();
        }
        return instance;
    }

    public <T> T getEntity(Class type) {
        if (type.equals(AccountEntity.class)) {
            return (T) new AccountEntity();
        } else if (type.equals(CategoryEntity.class)) {
            return (T) new CategoryEntity();
        } else if (type.equals(CategoryGameEntity.class)) {
            return (T) new CategoryGameEntity();
        } else if (type.equals(OrderEntity.class)) {
            return (T) new OrderEntity();
        } else if (type.equals(OrderDetailEntity.class)) {
            return (T) new OrderDetailEntity();
        } else if (type.equals(GameEntity.class)) {
            return (T) new GameEntity();
        } else if (type.equals(UserEntity.class)) {
            return (T) new UserEntity();
        }
        return null;

    }
}
