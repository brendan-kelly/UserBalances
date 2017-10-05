package com.userbalances;

import com.userbalances.models.*;
import com.userbalances.controllers.UserController;

public class Main {
    private static UserController usersController = new UserController();

    public static void main(String[] args) throws Exception {
        // create a user
        User user = usersController.createUser("brendanhkelly@gmail.com", "password1");

        // get a user
        User user2 = usersController.getUserById(user.getId());
        System.out.println("User: " + user2.getEmail());
        System.out.println("Password: " + user2.getPassword());

        // update a user email
        int updateEmail = usersController.updateUserEmail(user2, "bk@gmail.com");
        System.out.println("Update email: " + updateEmail);
        System.out.println("User after email update: " + user2.getEmail());

        // update a user password
        int updatePassword = usersController.updateUserPassword(user2, "password2");
        System.out.println("Update password: " + updatePassword);
        System.out.println("User after password update: " + user2.getPassword());

        // delete a user
        int deleteUser = usersController.deleteUser(user2);
        System.out.println("Delete user: " + deleteUser);

        // get users with a certain email
        for (User element : usersController.getUsersByEmail("brendanhkelly@gmail.com")) {
            System.out.println("User by Email: " + element.getEmail());
        }
    }
}