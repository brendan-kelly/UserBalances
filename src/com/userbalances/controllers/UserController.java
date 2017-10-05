package com.userbalances.controllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.table.TableUtils;
import com.j256.ormlite.stmt.QueryBuilder;
import com.userbalances.Database;
import com.userbalances.models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;


public class UserController {
    private Dao<User, UUID> usersDao;

    public UserController() {
        try {
            this.usersDao = DaoManager.createDao(Database.getConnectionSource(), User.class);
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
        usersDao.create(user);
        return user;
    }

    public int updateUserEmail(User user, String email) throws SQLException {
        user.setEmail(email);
        return usersDao.update(user);
    }

    public int updateUserPassword(User user, String password) throws SQLException {
        user.setPassword(password);
        return usersDao.update(user);
    }

    public int deleteUser(User user) throws SQLException {
        return usersDao.delete(user);
    }

    public User getUserById(UUID id) throws SQLException {
        return usersDao.queryForId(id);
    }

    public User getUserByEmail(String email) throws SQLException {
        QueryBuilder<User, UUID> qb = usersDao.queryBuilder();
        PreparedQuery<User> query = qb.where().eq("email", email).prepare();
        return usersDao.queryForFirst(query);
    }

    public List<User> getUsersByEmail(String email) throws SQLException {
        QueryBuilder<User, UUID> qb = usersDao.queryBuilder();
        return qb.where().eq("email", email).query();
    }
}
