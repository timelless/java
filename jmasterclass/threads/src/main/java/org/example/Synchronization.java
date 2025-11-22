package org.example;

import org.example.misc.BankAccount;
import org.example.misc.CachedData;

import java.util.concurrent.TimeUnit;

public class Synchronization
{

    public static void main( String[] args ) {
        BankAccount companyAccount = new BankAccount("Tom", 10000);

        Thread thread1 = new Thread(() -> companyAccount.withdrawal(2500));
        Thread thread2 = new Thread(() -> companyAccount.deposit(5000));
        Thread thread3 = new Thread(() -> companyAccount.setName("Tim"));
        Thread thread4 = new Thread(() -> companyAccount.withdrawal(2000));

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Final balance is: " + companyAccount.getBalance());
    }
}
