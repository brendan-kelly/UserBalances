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


public class BalanceController {
    private Dao<Balance, UUID> balanceDao;

    public BalanceController() {
        try {
            balanceDao = DaoManager.createDao(Database.getConnectionSource(), Balance.class);
        } catch (SQLException e) {
            throw new RuntimeException("exception", e);
        }
        try {
            TableUtils.createTableIfNotExists(Database.getConnectionSource(), Balance.class);
        } catch (SQLException e) {
            throw new RuntimeException("exception", e);
        }
    }

    Dao<Balance, UUID> getBalanceDao() {
        return balanceDao;
    }

    public Balance createBalance(Double value, User user) throws SQLException {
        Balance balance = new Balance(value, user);
        balanceDao.create(balance);
        return balance;
    }

    public int updateBalanceValue(Balance balance, Double value) throws SQLException {
        balance.setValue(value);
        return balanceDao.update(balance);
    }

    public int updateBalanceUserId(Balance balance, User user) throws SQLException {
        balance.setUserId(user);
        return balanceDao.update(balance);
    }

    public int deleteBalance(Balance balance) throws SQLException {
        return balanceDao.delete(balance);
    }

    public Balance getBalanceById(UUID id) throws SQLException {
        return balanceDao.queryForId(id);
    }
}
