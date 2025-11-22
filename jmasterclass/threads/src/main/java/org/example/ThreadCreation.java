package org.example;

import org.example.misc.CustomThread;

import java.util.concurrent.TimeUnit;

public class ThreadCreation
{
    public static void main( String[] args ) {
        CustomThread customThread = new CustomThread();
        // run concurrently
        customThread.start();


        Runnable myRunnable = () -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println("2");
                try {
                    TimeUnit.MILLISECONDS.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread myThread = new Thread(myRunnable);
        myThread.start();


        // check concurrency in output
        // check run() vs start() in readme
        for (int i = 1; i <= 3; i++) {
            System.out.println("0");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
