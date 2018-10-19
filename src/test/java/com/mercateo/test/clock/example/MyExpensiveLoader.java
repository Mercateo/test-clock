package com.mercateo.test.clock.example;

import java.util.concurrent.Callable;

public class MyExpensiveLoader implements Callable<Object> {

    public Object call() throws Exception {
        System.out.println("doing some expensive stuff");
        return new Object();
    }

}
