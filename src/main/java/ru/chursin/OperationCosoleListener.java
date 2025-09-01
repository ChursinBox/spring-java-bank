package ru.chursin;

import ru.chursin.accaunt.AccountService;
import ru.chursin.user.User;
import ru.chursin.user.UserService;

import java.util.List;
import java.util.Scanner;

public class OperationCosoleListener {

    private final Scanner scanner;
    private final AccountService accountService;
    private final UserService userService;


    public OperationCosoleListener(
            Scanner scanner,
            AccountService accountService,
            UserService userService
    ) {
        this.scanner = scanner;
        this.accountService = accountService;
        this.userService = userService;
    }

    public void listenUpdates() {
        System.out.println("type operation");

        while(true) {
            var operationType = listenNextOperation();
            try {
                processNextOperation(operationType);
            } catch (Exception e) {
                System.out.printf(
                        "Error command %s: error=%s%n",
                        operationType,
                        e.getMessage()
                );
            }

        }
    }

    private String listenNextOperation() {
        System.out.println("type next operation");
        return scanner.nextLine();
    }

    private void processNextOperation(String operation) {

        if (operation.equals("USER_CREATE")) {

            System.out.println("Enter login for new user:");
            String login = scanner.nextLine();
            User user = userService.createUser(login);
            System.out.println("User created" + user.toString());

        } else if (operation.equals("SHOW_ALL_USERS")) {

            List<User> users = userService.getAllUsers();
            System.out.println("List of all users:");
            users.forEach(System.out::println);

        } else if (operation.equals("ACCOUNT_CREATE")) {

            System.out.println("Enter user id for which to create an account:");
            int userId = Integer.parseInt(scanner.nextLine());
            var user = userService.findUserById(userId)
                            .orElseThrow(() -> new IllegalArgumentException("No such user with id = %s"
                                    .formatted(userId)));

            var account = accountService.createAccount(user);
            user.getAccountList().add(account);

            System.out.println("New account created with id =%s for user: %s"
                    .formatted(account.getId(), user.getLogin()));

        } else {
            System.out.println("not found");
        }
    }
}
