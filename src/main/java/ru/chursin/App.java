package ru.chursin;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("ru.chursin");
        context.getBean(OperationConsoleListener.class).listenUpdates();
    }

}
