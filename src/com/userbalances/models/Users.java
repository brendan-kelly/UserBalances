package com.userbalances.models;

import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.field.DatabaseField;

@DatabaseTable(tableName = "users")
public class Users {

    @DatabaseField(columnName = "id", generatedId = true, allowGeneratedIdInsert = true)
    private int id;
    @DatabaseField(unique = true)
    private String email;
    @DatabaseField
    private String password;

    public Users() {
        // ORMLite needs a no-arg constructor
    }
    public Users(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}