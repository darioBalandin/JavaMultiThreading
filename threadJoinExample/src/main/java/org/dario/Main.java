package org.dario;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {


        List<Long> inputNumbers = Arrays.asList(0L, 3434354L, 23423L, 2324L, 3247L, 324L, 7865L);

        List<FactorialThread> factorialThreadList = new ArrayList<>();

        for (Long inputNumber : inputNumbers) {
            FactorialThread factorialThread = new FactorialThread(inputNumber);
            factorialThreadList.add(factorialThread);
        }

        for (Thread thread : factorialThreadList) {
            thread.setDaemon(true);
            thread.start();
        }
        for (Thread thread: factorialThreadList){
            thread.join(2000);
        }

        for (int i = 0; i < inputNumbers.size(); i++) {
            FactorialThread factorialThread = factorialThreadList.get(i);
            if (factorialThread.isFinished()) {
                System.out.println("Factorial of " + inputNumbers.get(i) + " is " + factorialThread.getResult());
            }else {
                System.out.println("Calculation for " + inputNumbers.get(i) + " is still in progress");
            }
        }


        System.out.println("Hello world!");
    }


    public static class FactorialThread extends Thread {
        private long inputNumber;
        private BigInteger result = BigInteger.ZERO;
        private boolean isFinished = false;

        public FactorialThread(long inputNumber) {
            this.inputNumber = inputNumber;
        }

        @Override
        public void run() {
            this.result = factorial(inputNumber);
            this.isFinished = true;
        }

        public BigInteger factorial(long n) {
            BigInteger tempResult = BigInteger.ONE;
            for (int i = 1; i <= n; i++) {
                tempResult = tempResult.multiply(BigInteger.valueOf(i));
            }
            return tempResult;
        }

        public boolean isFinished() {
            return isFinished;
        }

        public BigInteger getResult() {
            return result;
        }
    }

}