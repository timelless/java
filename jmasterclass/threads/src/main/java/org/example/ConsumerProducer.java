package org.example;

import java.util.Random;

class MessageRepository {
    private String message;
    private boolean hasMessage;

    public synchronized  String read() {
        while(!hasMessage) {
            try {
                // solves deadlock
                // wait should be in the loop
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        hasMessage = false;
        // solves deadlock
        // suitable for our case, sometimes notify all is not good
        notifyAll();
        return message;
    }

    public synchronized  void write(String message) {
        while(hasMessage) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        hasMessage = true;
        notifyAll();
        this.message = message;
    }
}

class MessageWriter implements Runnable {

    private MessageRepositoryWl outgoingMessage;
    private final String text = "Line 1\nLine 2\nLine 3";

    public MessageWriter(MessageRepositoryWl outgoingMessage) {
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

class MessageReader implements Runnable {

    private MessageRepositoryWl incomingMessage;

    public MessageReader(MessageRepositoryWl incomingMessage) {
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

public class ConsumerProducer
{
    public static void main( String[] args ) {
        MessageRepositoryWl repository = new MessageRepositoryWl();

        Thread reader = new Thread(new MessageReader(repository));
        Thread writer = new Thread(new MessageWriter(repository));

        reader.start();
        writer.start();
    }
}
