package ru.chursin.operations.processors;

import ru.chursin.operations.ConsoleOperationType;
import ru.chursin.operations.OperationCommandProcessor;

public class CloseAccountProcessor implements OperationCommandProcessor {
    @Override
    public void processOperation() {

    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_CLOSE;
    }
}
