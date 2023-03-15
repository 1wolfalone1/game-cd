/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.iml;

import annotation.model.ManyToMany;
import annotation.model.ManyToOne;
import annotation.model.MapToEntity;
import annotation.model.OneToMany;
import annotation.model.OneToOne;
import factory.entity.EntityFactory;
import factory.model.ModelFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dto.IModel;
import model.dto.OrderModel;
import model.entity.IEntity;
import org.apache.commons.lang3.reflect.FieldUtils;
import utils.MyUtils;

/**
 *
 * @author ASUS
 */
public abstract class AbstractServiceModel<Entity extends IEntity, Model extends IModel> {

    protected EntityFactory facE = EntityFactory.getInstance();
    protected ModelFactory facM = ModelFactory.getInstance();
    protected Class modelType;
    protected Class entityType;

    public Model toModel(Entity e) {
        final Model m = facM.getModel(modelType);
        int idM = m.getId();
        int idE = e.getId();
        FieldUtils.getFieldsListWithAnnotation(modelType, MapToEntity.class).
                stream().
                forEach(field -> {

                    if (field.isAnnotationPresent(OneToMany.class) || field.isAnnotationPresent(ManyToMany.class)) {

                    } else if (field.isAnnotationPresent(ManyToOne.class) || field.isAnnotationPresent(OneToOne.class)) {

                        String k = field.getAnnotation(MapToEntity.class).mapId();
                        Class oto = null;
                        if (field.isAnnotationPresent(ManyToOne.class)) {
                            oto = field.getAnnotation(ManyToOne.class).getAction();
                        } else {
                            oto = field.getAnnotation(OneToOne.class).getAction();
                        }

                        try {

                            Object newService = oto.getConstructor().newInstance();
                            Object value = FieldUtils.readDeclaredField(e, k, true);
                            Method method = oto.getDeclaredMethod("getOne", int.class);
                            field.setAccessible(true);
                            field.set(m, method.invoke(newService, (int) value));
                        } catch (IllegalAccessException ex) {
                            Logger.getLogger(AbstractServiceModel.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IllegalArgumentException ex) {
                            Logger.getLogger(AbstractServiceModel.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InvocationTargetException ex) {
                            Logger.getLogger(AbstractServiceModel.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (NoSuchMethodException ex) {
                            Logger.getLogger(AbstractServiceModel.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SecurityException ex) {
                            Logger.getLogger(AbstractServiceModel.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InstantiationException ex) {
                            Logger.getLogger(AbstractServiceModel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {

                        String k = field.getAnnotation(MapToEntity.class).mapId();

                        try {

                            Object value = FieldUtils.readDeclaredField(e, k, true);
                            if (field.getType().equals(java.util.Date.class)) {
                                value = MyUtils.sqlDateToUtilDate((java.sql.Date) value);
                            }
                            FieldUtils.writeDeclaredField(m, field.getName(), value,
                                    true);
                        } catch (IllegalAccessException ex) {
                            Logger.getLogger(AbstractServiceModel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

        return m;
    }

    public Entity toEntity(Model m) {
        final Entity e = facE.getEntity(entityType);
        FieldUtils.getFieldsListWithAnnotation(modelType, MapToEntity.class).
                stream().
                forEach(
                        field -> {

                            if (field.isAnnotationPresent(OneToMany.class) || field.isAnnotationPresent(ManyToMany.class)) {
                            } else if (field.isAnnotationPresent(ManyToOne.class) || field.isAnnotationPresent(OneToOne.class)) {
                                field.setAccessible(true);
                                String k = field.getAnnotation(MapToEntity.class).mapId();
                                try {
                                    if (field.get(m) != null) {
                                        int value = ((Model) field.get(m)).getId();
                                        FieldUtils.writeDeclaredField(e, k, value, true);
                                    }

                                } catch (IllegalArgumentException ex) {
                                    Logger.getLogger(AbstractServiceModel.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IllegalAccessException ex) {
                                    Logger.getLogger(AbstractServiceModel.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                String k = field.getAnnotation(MapToEntity.class).mapId();
                                try {
                                    Object value = FieldUtils.readDeclaredField(m, field.getName(), true);
                                    if (field.getType().equals(java.util.Date.class)) {
                                        value = MyUtils.utilDateToSqlDate((java.util.Date) value);
                                    }
                                    FieldUtils.writeDeclaredField(e, k, value,
                                            true);
                                } catch (IllegalAccessException ex) {
                                    Logger.getLogger(AbstractServiceModel.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });

        return e;
    }

    public List<Model> getManyToMany() {
        List<Model> listModel = new ArrayList<>();

        return listModel;
    }

}
