package com.userbalances.controllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.table.TableUtils;
import com.j256.ormlite.stmt.QueryBuilder;
import com.userbalances.Database;
import com.userbalances.models.Users;

import java.sql.SQLException;
import java.util.List;

public class UsersController {
    private Dao<Users, String> usersDao;
    private Users user;

    public UsersController() {
        try {
            this.usersDao = DaoManager.createDao(Database.getConnectionSource(), Users.class);
        } catch(SQLException e) {
            throw new RuntimeException("exception", e);
        }
        this.user = new Users();
    }

    public void createUserTable() throws SQLException {
        TableUtils.createTable(Database.getConnectionSource(), Users.class);
    }

    public void createUser(String email, String password) throws SQLException {
        user.setEmail(email);
        user.setPassword(password);
        usersDao.create(user);
    }

    public void deleteUser(Users user) throws SQLException {
        usersDao.delete(user);
    }

    public Users getUserById(String id) throws SQLException {

        return usersDao.queryForId(id);
    }

    public Users getUserByEmail(String email) throws SQLException {
        QueryBuilder<Users, String> qb = usersDao.queryBuilder();
        PreparedQuery<Users> query = qb.where().eq("email", email).prepare();
        return usersDao.queryForFirst(query);
    }

    public List<Users> getUsersByEmail(String email) throws SQLException {
        QueryBuilder<Users, String> qb = usersDao.queryBuilder();
        return qb.where().eq("email", email).query();
    }
}
