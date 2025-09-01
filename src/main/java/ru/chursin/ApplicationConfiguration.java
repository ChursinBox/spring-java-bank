package ru.chursin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.chursin.accaunt.AccountService;
import ru.chursin.user.UserService;

import java.util.Scanner;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public OperationCosoleListener operationCosoleListener(
            Scanner scanner,
            UserService userService,
            AccountService accountService
    ) {
        return new OperationCosoleListener(scanner, accountService, userService);
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
