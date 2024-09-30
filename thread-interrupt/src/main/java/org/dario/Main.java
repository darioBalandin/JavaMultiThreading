package org.dario;

public class Main {
    public static void main(String[] args) {

        Thread t = new Thread(new BlockintTask());

        t.start();
        t.interrupt();
    }



    private static class BlockintTask implements Runnable{
        @Override
        public void run() {
            try {
                Thread.sleep(5000000);
            } catch (InterruptedException e) {
                System.out.println("Exiting blocking thread");
            }
        }
    }

}