package com.userbalances.models;

import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.field.DatabaseField;

import java.util.UUID;

@DatabaseTable(tableName = "balances")
public class Balance {

    @DatabaseField(id = true)
    private UUID id;
    @DatabaseField
    private Double value;
    @DatabaseField(foreign = true)
    private User user;

    public Balance() {
        // ORMLite needs a no-arg constructor
    }

    public Balance(Double value, User user) {
        this.id = UUID.randomUUID();
        this.value = value;
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public Double getValue() {
        return value;
    }

    public User getUser() {
        return user;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setUserId(User user) {
        this.user = user;
    }
}