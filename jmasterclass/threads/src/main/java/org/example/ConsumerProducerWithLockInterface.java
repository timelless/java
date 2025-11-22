package org.example;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MessageRepositoryWl {
    private String message;
    private boolean hasMessage;
    private final Lock lock = new ReentrantLock();

    public String read() {
        // explicit lock

        if(lock.tryLock()) {
            //lock.lock();

            try {
                while (!hasMessage) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                hasMessage = false;
            } finally {
                lock.unlock();
            }
        }
        else {
            System.out.println("** read blocked" + lock);
            hasMessage = false;
        }

        return message;
    }

    public void write(String message) {
        try {
            if(lock.tryLock(3, TimeUnit.SECONDS)) {
                try {
                    while (hasMessage) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    hasMessage = true;
                } finally {
                    lock.unlock();
                }
            }
            else {
                System.out.println("** write blocked" + lock);
                hasMessage = true;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        this.message = message;
    }
}

class MessageWriterWl implements Runnable {

    private MessageRepositoryWl outgoingMessage;
    private final String text = "Line 1\nLine 2\nLine 3";

    public MessageWriterWl(MessageRepositoryWl outgoingMessage) {
        this.outgoingMessage = outgoingMessage;
    }

    @Override
    public void run() {
        Random random = new Random();
        String[] lines = text.split("\n");

        for (int i = 0; i < lines.length; i++) {
            outgoingMessage.write(lines[i]);
            try {
                Thread.sleep(random.nextInt(500, 2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        outgoingMessage.write("FINISHED");
    }
}

class MessageReaderWl implements Runnable {

    private MessageRepositoryWl incomingMessage;

    public MessageReaderWl(MessageRepositoryWl incomingMessage) {
        this.incomingMessage = incomingMessage;
    }

    @Override
    public void run() {
        Random random = new Random();
        String lastMessage = "";

        do {
            try {
                Thread.sleep(random.nextInt(500, 2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            lastMessage = incomingMessage.read();
            System.out.println(lastMessage);
        } while (!lastMessage.equals("FINISHED"));
    }
}

public class ConsumerProducerWithLockInterface
{
    public static void main( String[] args ) {
        MessageRepositoryWl repository = new MessageRepositoryWl();

        Thread reader = new Thread(new MessageReaderWl(repository));
        Thread writer = new Thread(new MessageWriterWl(repository));

        writer.setUncaughtExceptionHandler((thread, ex) -> {
            System.out.println("Writer exception" + ex);
            if(reader.isAlive()) {
                System.out.println("Going to interrupt the reader");
                reader.interrupt();
            }
        });

        reader.setUncaughtExceptionHandler((thread, ex) -> {
            System.out.println("Reader exception" + ex);
            if(writer.isAlive()) {
                System.out.println("Going to interrupt the writer");
                writer.interrupt();
            }
        });

        reader.start();
        writer.start();
    }
}
