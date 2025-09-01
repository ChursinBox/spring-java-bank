package ru.chursin;

import ru.chursin.operations.ConsoleOperationType;
import ru.chursin.operations.OperationCommandProcessor;

import java.util.Map;
import java.util.Scanner;

public class OperationConsoleListener {

    private final Scanner scanner;
    private final Map<ConsoleOperationType, OperationCommandProcessor> processorMap;

    public OperationConsoleListener(
            Scanner scanner,
            Map<ConsoleOperationType, OperationCommandProcessor> processorMap
    ) {
        this.scanner = scanner;
        this.processorMap = processorMap;
    }

    public void listenUpdates() {
        System.out.println("type operation");

        while(true) {
            var operationType = listenNextOperation();
            processNextOperation(operationType);
        }
    }

    private ConsoleOperationType listenNextOperation() {
        System.out.println("type next operation: ");

        while(true) {
            var nextOperation = scanner.nextLine();
            try {
                return ConsoleOperationType.valueOf(nextOperation);
            } catch (IllegalArgumentException e) {
                System.out.println("no such common find");
            }
        }
    }

    private void processNextOperation(ConsoleOperationType operation) {
        try {
            var processor = processorMap.get(operation);
            processor.processOperation();
        } catch (Exception e) {
            System.out.printf(
                    "Error command %s: error=%s%n",
                    operation,
                    e.getMessage()
            );
        }
    }
}
