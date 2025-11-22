package org.example;

import java.util.concurrent.TimeUnit;

public class RunningThreads
{
    public static void main( String[] args ) {
        System.out.println("Main thread running");

        try {
            System.out.println("Main thread paused of 1 second");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread = new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " should take 10 dots to run...");

            for (int i = 1; i <= 10; i++) {
                System.out.println(".");
                try {
                    Thread.sleep(500);
                    System.out.println("A. State =" + Thread.currentThread().getState());
                } catch (InterruptedException e) {
                    System.out.println("\nOops! " + threadName + " thread interrupted");
                    System.out.println("A1. State =" + Thread.currentThread().getState());
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            System.out.println("\n" + threadName + " thread completed successfully");
        });

        Thread installThread = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    Thread.sleep(250);
                    System.out.println("Installation step " + (i + 1) + " is completed");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "InstallThread");

        Thread threadMonitor = new Thread(() -> {
            long now = System.currentTimeMillis();
            while(thread.isAlive()) {
                try {
                    Thread.sleep(1000);

                    if(System.currentTimeMillis() - now > 8000) {
                        thread.interrupt();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println(thread.getName() + " starting...");
        thread.start();
        threadMonitor.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if(!thread.isInterrupted()) {
            installThread.start();
        }
        else {
            System.out.println("Previous thread was interrupted" + installThread.getName() + " cannot start");
        }

        System.out.println("C. State =" + thread.getState());

//        System.out.println("Main thread will continue here");
//
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        thread.interrupt();
    }
}
