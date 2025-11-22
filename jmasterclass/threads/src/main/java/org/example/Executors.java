package org.example;

import org.example.misc.ThreadColor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

class ColorThreadFactory implements ThreadFactory {

    private String threadName;
    private int colorValue = 1;

    public ColorThreadFactory(ThreadColor color) {
        this.threadName = color.name();
    }

    public ColorThreadFactory() {
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        String name = threadName;

        if(name == null) {
            name = ThreadColor.values()[colorValue].name();
        }

        if(++colorValue > (ThreadColor.values().length -1 )) {
            colorValue = 1;
        }

        thread.setName(name);
        return thread;
    }
}

public class Executors
{
    public static void main( String[] args ) {
        int count = 3;
        ExecutorService multiExecutor = java.util.concurrent.Executors.newFixedThreadPool(count, new ColorThreadFactory());

        for(int i = 0; i < count; i++) {
            multiExecutor.execute(Executors::countDown);
        }

        multiExecutor.shutdown();

    }
    public static void singlemain( String[] args ) {
        ExecutorService blueExecutor = java.util.concurrent.Executors.newSingleThreadExecutor(
                new ColorThreadFactory(ThreadColor.ANSI_BLUE)
        );
        blueExecutor.execute(Executors::countDown);
        blueExecutor.shutdown();

        boolean isDone = false;
        try {
            isDone = blueExecutor.awaitTermination(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // makes one async thread at a time
        if(isDone) {
            System.out.println("Blue has finished");

            ExecutorService yellowExecutor = java.util.concurrent.Executors.newSingleThreadExecutor(
                    new ColorThreadFactory(ThreadColor.ANSI_YELLOW)
            );
            yellowExecutor.execute(Executors::countDown);
            yellowExecutor.shutdown();

            ExecutorService redExecutor = java.util.concurrent.Executors.newSingleThreadExecutor(
                    new ColorThreadFactory(ThreadColor.ANSI_RED)
            );
            redExecutor.execute(Executors::countDown);
            redExecutor.shutdown();
        }
    }

    public static void notmain( String[] args ) {
        Thread blue = new Thread(Executors::countDown, ThreadColor.ANSI_BLUE.name());
        Thread yellow = new Thread(Executors::countDown, ThreadColor.ANSI_YELLOW.name());
        Thread red = new Thread(Executors::countDown, ThreadColor.ANSI_RED.name());

        blue.start();
        try {
            blue.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        yellow.start();
        try {
            yellow.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        red.start();
        try {
            red.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void countDown() {
        String threadName = Thread.currentThread().getName();
        ThreadColor threadColor = ThreadColor.ANSI_RESET;

        try {
            threadColor = ThreadColor.valueOf(threadName.toUpperCase());
        } catch (IllegalArgumentException e) {
        }

        String color = threadColor.getColor();
        for(int i = 20; i >= 0; i--) {
            System.out.println(color + " " + threadName.replace("ANSI_", "") + " " + i);
        }
    }
}
