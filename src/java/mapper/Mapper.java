/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import annotation.entity.Column;
import factory.entity.EntityFactory;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import model.entity.AccountEntity;

/**
 *
 * @author ASUS
 */
public class Mapper<T> {

    private Mapper() {
    }

    private static Mapper instance;

    public static synchronized Mapper getInstance() {
        if (instance == null) {
            instance = new Mapper();
        }
        return instance;
    }

    public T map(ResultSet rs, Class type) {

        T entity = EntityFactory.getInstance().getEntity(type);
        try {
            Field fields[] = entity.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getAnnotation(Column.class) != null) {
                    field.set(entity, rs.getObject(field.getAnnotation(Column.class).value()));
                }
            }
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
