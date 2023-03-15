/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import model.entity.AccountEntity;
import model.entity.UserEntity;

/**
 *
 * @author ASUS
 */
public interface IAccountDAO {

    public List<AccountEntity> getAll() throws SQLException, ClassNotFoundException;

    public int createAccount(AccountEntity aEntity, UserEntity uEntity) throws SQLException, ClassNotFoundException;

    public AccountEntity getOne(int id) throws SQLException, ClassNotFoundException;

    public AccountEntity checkLogin(String email, String password) throws SQLException, ClassNotFoundException;

    public void updateToken(int id, String token) throws SQLException, ClassNotFoundException;

    public boolean checkEmail(String email) throws SQLException, ClassNotFoundException;

    public List<AccountEntity> checkToken(String token) throws SQLException, ClassNotFoundException;

    public void updatePass(int id, String pass) throws SQLException, ClassNotFoundException;
    public void updateStatus(int id, int status) throws SQLException, ClassNotFoundException;

    public void updateAvatar(int id, String imgPath) throws SQLException, ClassNotFoundException;

    public int count(int status) throws SQLException, ClassNotFoundException;

    public List<AccountEntity> getPage(int from, int max, int status) throws SQLException, ClassNotFoundException;
}
