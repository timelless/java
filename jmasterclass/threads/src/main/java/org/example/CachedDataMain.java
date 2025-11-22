package org.example;

import org.example.misc.CachedData;

import java.util.concurrent.TimeUnit;

public class CachedDataMain
{

    public static void main( String[] args ) {
        CachedData cachedData = new CachedData();

        Thread write = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            cachedData.toggleFlag();
            System.out.println("A. flag set to " + cachedData.isReady());
        });

        Thread reader = new Thread(() -> {
            while (!cachedData.isReady()) {
                // busy wait until flag becomes true
            }

            System.out.println("B. flag is " + cachedData.isReady());
        });

        write.start();
        reader.start();
    }

}
