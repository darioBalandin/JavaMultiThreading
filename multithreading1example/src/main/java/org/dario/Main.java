package org.dario;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {


        Callable<String> callable1 = ()-> "callable1";

        Callable<String> callable2 = ()-> "callable2";

        Runnable runnable = () -> {

            System.out.println("hola");
            System.out.println("We are in thread: " + Thread.currentThread().getName());

        };


        Thread thread = new Thread(runnable);
        thread.setName("New worker thread");
        thread.setPriority(Thread.MAX_PRIORITY);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        String s = executorService.invokeAny(Collections.singleton(callable1));

        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(callable1, callable2));
        System.out.println("We are in thread: "+ Thread.currentThread().getName());
        thread.start();


        new NewThread().start();

//         futures.stream()
//                .map(Future::resultNow)
//                .forEach(System.out::println);
//
//
//        System.out.println(s);

    }

    private static class NewThread extends Thread{
        @Override
        public void run() {
            System.out.println("Hello from "+currentThread().getName());
        }
    }
}