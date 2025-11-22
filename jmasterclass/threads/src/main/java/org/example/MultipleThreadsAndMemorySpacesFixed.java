package org.example;

import org.example.misc.ThreadColor;

import java.util.concurrent.TimeUnit;

public class MultipleThreadsAndMemorySpacesFixed
{
    public static void main( String[] args ) {
        StopWatchFixed gWatch = new StopWatchFixed(TimeUnit.SECONDS);
        StopWatchFixed pWatch = new StopWatchFixed(TimeUnit.SECONDS);
        StopWatchFixed rWatch = new StopWatchFixed(TimeUnit.SECONDS);

        Thread green = new Thread(gWatch::countDown, ThreadColor.ANSI_GREEN.name());
        Thread purple = new Thread(() -> {pWatch.countDown(7);}, ThreadColor.ANSI_PURPLE.name());
        Thread red = new Thread(rWatch::countDown, ThreadColor.ANSI_RED.name());

        green.start();
        purple.start();
        red.start();
    }
}

class StopWatchFixed {

    private  TimeUnit timeUnit;
    private int i;

    public StopWatchFixed(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public void countDown() {
        countDown(5);
    }

    public void countDown(int unitCount) {
        String  threadName = Thread.currentThread().getName();
        ThreadColor threadColor = ThreadColor.ANSI_RESET;

        try {
            threadColor = ThreadColor.valueOf(threadName);
        } catch (IllegalArgumentException e) {
        }

        String color = threadColor.getColor();

        for (/*int*/ i = unitCount; i > 0; i--) {
            try {
                timeUnit.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.printf("%s%s Thread : i = %d%n", color, threadName, i);
        }
    }
}