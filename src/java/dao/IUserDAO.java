/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.entity.UserEntity;

/**
 *
 * @author ASUS
 */
public interface IUserDAO {

    public List<UserEntity> getAll() throws SQLException, ClassNotFoundException;

    public int createUser(UserEntity uEntity) throws SQLException, ClassNotFoundException;

    public UserEntity getOne(int id) throws SQLException, ClassNotFoundException;

    public boolean updateProfile(int id, String name, String phone, String address) throws SQLException, ClassNotFoundException;

    public void deleteGuest(int id) throws SQLException, ClassNotFoundException;

    public int count() throws SQLException, ClassNotFoundException;

    public List<UserEntity> getPage(int from, int max) throws SQLException, ClassNotFoundException;
}
