package com.userbalances;

import com.userbalances.controllers.BalanceController;
import com.userbalances.models.*;
import com.userbalances.controllers.UserController;

import java.util.Arrays;

public class Main {
    private static UserController usersController = new UserController();
    private static BalanceController balanceController = new BalanceController();

    private static User runUsers() throws Exception {
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
        //int deleteUser = usersController.deleteUser(user2);
        //System.out.println("Delete user: " + deleteUser);

        // get users with a certain email
        for (User element : usersController.getUsersByEmail("bk@gmail.com")) {
            System.out.println("User by Email: " + element.getEmail());
        }

        return user2;
    }

    private static void runBalances(User user) throws Exception {
        Balance balance = balanceController.createBalance(10.00, user);
        Balance balance3 = balanceController.createBalance(20.00, user);

        // get a balance
        Balance balance2 = balanceController.getBalanceById(balance.getId());
        System.out.println("User: " + balance.getUser().getEmail());
        System.out.println("Password: " + balance.getValue());

        // update a balance
        int updateValue = balanceController.updateBalanceValue(balance2, 10.01);
        System.out.println("Update balance: " + updateValue);
        System.out.println("Balance after value update: " + balance.getValue());

        // get balances with a certain user email
        for (Balance element : usersController.getBalancesByUserEmail("bk@gmail.com")) {
            System.out.println("Email: " + usersController.getUserById(element.getUser().getId()).getEmail());
            //System.out.println("User: " + element.getUser().getEmail());
            System.out.println("Balance: " + element.getValue());
        }

        // delete a balance
        int deleteBalance = balanceController.deleteBalance(balance);
        System.out.println("Delete balance: " + deleteBalance);
        int deleteBalance2 = balanceController.deleteBalance(balance2);
        System.out.println("Delete balance: " + deleteBalance2);
        int deleteBalance3 = balanceController.deleteBalance(balance3);
        System.out.println("Delete balance: " + deleteBalance3);

        int deleteUser = usersController.deleteUser(user);
        System.out.println("Delete user: " + deleteUser);
    }

    public static void main(String[] args) throws Exception {
        if (Arrays.asList(args).contains("users")) {
            User user = runUsers();
            runBalances(user);
        }
    }
}