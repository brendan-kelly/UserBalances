package com.userbalances.controllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.table.TableUtils;
import com.j256.ormlite.stmt.QueryBuilder;
import com.userbalances.Database;
import com.userbalances.models.Balance;
import com.userbalances.models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;


public class UserController {
    private Dao<User, UUID> userDao;

    public UserController() {
        try {
            this.userDao = DaoManager.createDao(Database.getConnectionSource(), User.class);
        } catch (SQLException e) {
            throw new RuntimeException("exception", e);
        }
        try {
            TableUtils.createTableIfNotExists(Database.getConnectionSource(), User.class);
        } catch (SQLException e) {
            throw new RuntimeException("exception", e);
        }
    }

    public User createUser(String email, String password) throws SQLException {
        User user = new User(email, password);
        userDao.create(user);
        return user;
    }

    public int updateUserEmail(User user, String email) throws SQLException {
        user.setEmail(email);
        return userDao.update(user);
    }

    public int updateUserPassword(User user, String password) throws SQLException {
        user.setPassword(password);
        return userDao.update(user);
    }

    public int deleteUser(User user) throws SQLException {
        return userDao.delete(user);
    }

    public User getUserById(UUID id) throws SQLException {
        return userDao.queryForId(id);
    }

    public User getUserByEmail(String email) throws SQLException {
        QueryBuilder<User, UUID> qb = userDao.queryBuilder();
        PreparedQuery<User> query = qb.where().eq("email", email).prepare();
        return userDao.queryForFirst(query);
    }

    public List<User> getUsersByEmail(String email) throws SQLException {
        QueryBuilder<User, UUID> qb = userDao.queryBuilder();
        return qb.where().eq("email", email).query();
    }

    public List<Balance> getBalancesByUserEmail(String email) throws SQLException {
        QueryBuilder<User, UUID> userQb = userDao.queryBuilder();
        userQb.where().eq("email", email);

        BalanceController balanceController = new BalanceController();
        QueryBuilder<Balance, UUID> balanceQb = balanceController.getBalanceDao().queryBuilder();
        return balanceQb.join(userQb).query();
    }
}
