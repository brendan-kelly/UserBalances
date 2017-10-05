package com.userbalances.models;

import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.field.DatabaseField;

import java.util.UUID;

@DatabaseTable(tableName = "users")
public class User {

    @DatabaseField(columnName = "id", id = true)
    private UUID id;
    @DatabaseField(unique = true)
    private String email;
    @DatabaseField
    private String password;

    public User() {
        // ORMLite needs a no-arg constructor
    }

    public User(String email, String password) {
        this.id = UUID.randomUUID();
        this.email = email;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}