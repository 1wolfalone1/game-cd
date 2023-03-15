/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.iml;

import dao.IUserDAO;
import dao.iml.UserDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dto.AccountModel;
import model.dto.OrderModel;
import model.dto.UserCheckErrorUpdateDTO;
import model.dto.UserModel;
import model.entity.UserEntity;
import service.IOrderService;
import service.IUserService;
import utils.Encode;
import utils.MyUtils;

/**
 *
 * @author ASUS
 */
public class UserService extends AbstractServiceModel<UserEntity, UserModel> implements IUserService {

    private IUserDAO userDao = new UserDAO();
    private IOrderService orderService;

    public UserService() {
        this.modelType = UserModel.class;
        this.entityType = UserEntity.class;
    }

    public List<UserModel> getAll() throws SQLException, ClassNotFoundException {
        final List<UserModel> listModels = new ArrayList<>();
        userDao.getAll().stream().forEach(e -> {
            listModels.add(super.toModel(e));
        });
        return listModels;
    }

    @Override
    public UserModel getOne(int id) throws SQLException, ClassNotFoundException {
        UserModel model = facM.getModel(modelType);
        UserEntity entity = userDao.getOne(id);
        if (entity != null) {
            model = toModel(entity);
            return model;

        } else {
            return null;
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserService news = new UserService();
        UserModel m = new UserModel();
        m = news.getOne(2);
        news.setListOrder(m);
        System.out.println(m);

    }

    @Override
    public UserCheckErrorUpdateDTO checkChangeProfile(AccountModel test, String name, String phone, String password, String newPass, String conFirm)
            throws SQLException, ClassNotFoundException {
        UserCheckErrorUpdateDTO error = new UserCheckErrorUpdateDTO();
        boolean result = true;
        if (name.trim().isEmpty()) {
            error.setFullName("Name must not be empty!!!");
            result = false;

        }
        if (!phone.trim().matches("(84|0[3|5|7|8|9])+([0-9]{7,11})\\b")) {
            error.setPhone("Not a valid phone number!");
            result = false;
        }
        if (phone.trim().isEmpty()) {
            error.setFullName("Phone must not be empty!!!");
            result = false;
        }
        if (!password.trim().isEmpty()) {
            String passHas = Encode.toSHA1(password);
            if (!test.getPassword().equals(passHas)) {
                error.setOldPass("Wrong password!!!");
                return error;
            }
            if (!newPass.matches("(?!.*[\\s]).{5,20}")) {
                error.setNewPass("Password's length must be between 5 and 20 inclusive!!!");
                result = false;
            } else if (!newPass.equals(conFirm)) {
                error.setConPass("Not match with password!!!");
                result = false;
            }

        }
        if (result) {
            return null;
        } else {
            return error;
        }
    }

    @Override
    public boolean updateProfile(int id, String name, String phone, String address) throws SQLException, ClassNotFoundException {
        return userDao.updateProfile(id, name, phone, address);
    }

    @Override
    public int createUser(String name, String phone, String address) throws SQLException, ClassNotFoundException {
        UserEntity uEntity = facE.getEntity(entityType);
        uEntity.setFullName(name);
        uEntity.setPhone(phone);
        uEntity.setAddress(address);
        return userDao.createUser(uEntity);
    }

    @Override
    public void deleteGuest(int id) throws SQLException, ClassNotFoundException {
        userDao.deleteGuest(id);
    }

    @Override
    public void setListOrder(UserModel user) throws SQLException, ClassNotFoundException {
        orderService = new OrderService();

        List<OrderModel> list = orderService.getOrderModelByUser(user.getId());
        user.setListOrder(list);
    }

    @Override
    public void setListOrder(UserModel user, int type, String fromDate, String toDate) throws SQLException, ClassNotFoundException {
        orderService = new OrderService();
        List<OrderModel> list = new ArrayList<>();
        if (type == 0) {
            list = orderService.getOrderModelByUser(user.getId());
        } else {
            list = orderService.getOrderModelByUser(user.getId(), type, fromDate, toDate);
        }
        user.setListOrder(list);
    }

    @Override
    public int count() throws SQLException, ClassNotFoundException {
        return userDao.count();
    }

    @Override
    public List<UserModel> getPage(int from, int max) throws SQLException, ClassNotFoundException {
        List<UserModel> listM = new ArrayList();
        List<UserEntity> listE = userDao.getPage(from, max);
        listE.stream().forEach(e -> {
            UserModel m = toModel(e);
            listM.add(m);
        });
        return listM;
    }
}
