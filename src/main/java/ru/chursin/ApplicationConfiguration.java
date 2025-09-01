package ru.chursin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.chursin.accaunt.AccountService;
import ru.chursin.operations.ConsoleOperationType;
import ru.chursin.operations.OperationCommandProcessor;
import ru.chursin.operations.processors.AccountCreateProcessor;
import ru.chursin.operations.processors.CreateUserProcessor;
import ru.chursin.operations.processors.ShowAllUserProcessor;
import ru.chursin.user.UserService;

import java.util.Map;
import java.util.Scanner;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public OperationConsoleListener operationConsoleListener(
            Scanner scanner,
            CreateUserProcessor createUserProcessor,
            AccountCreateProcessor accountCreateProcessor,
            ShowAllUserProcessor showAllUserProcessor
            ) {
        Map<ConsoleOperationType, OperationCommandProcessor> map =
                Map.of(
                        ConsoleOperationType.USER_CREATE, createUserProcessor,
                        ConsoleOperationType.ACCOUNT_CREATE, accountCreateProcessor,
                        ConsoleOperationType.SHOW_ALL_USERS, showAllUserProcessor
                );
        return new OperationConsoleListener(scanner, map);
    }

    @Bean
    public UserService userService(AccountService accountService) {

        return new UserService(accountService);
    }

    @Bean
    public AccountService accountService() {
        return new AccountService();
    }
}
