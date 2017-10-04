package com.userbalances;

import com.userbalances.models.*;
import com.userbalances.controllers.UsersController;

public class Main {
    public static void main(String[] args) throws Exception {
        UsersController usersController = new UsersController();

        usersController.createUserTable();

        usersController.createUser("brendanhkelly@gmail.com", "password");

        Users user = usersController.getUserById("1");
        System.out.println("User by ID: " + user.getEmail());

        for (Users element : usersController.getUsersByEmail("brendanhkelly@gmail.com")) {
            System.out.println("User by Email: " + element.getEmail());
        }
    }
}