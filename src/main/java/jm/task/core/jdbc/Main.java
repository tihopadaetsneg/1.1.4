package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ленонардо", "Сплинтерович", (byte)15);
        userService.saveUser("Рафаэль", "Сплинтерович", (byte)15);
        userService.saveUser("Донателло", "Сплинтерович", (byte)15);
        userService.saveUser("Миккиланджело", "Сплинтерович", (byte)15);
        List<User> list = userService.getAllUsers();
        for (User l: list
        ) {
            System.out.println(l);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
