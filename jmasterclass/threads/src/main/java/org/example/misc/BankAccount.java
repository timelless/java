package org.example.misc;

public class BankAccount {

    private double balance;
    private String name;

    private final Object lockName = new Object();
    private final Object lockBalance = new Object();

    public double getBalance() {
        return balance;
    }

    public BankAccount(String name, double balance) {
        this.balance = balance;
        this.name = name;
    }

    public String getName() {
        System.out.println("Updated name: " + name);
        return name;
    }

    public void setName(String name) {
        synchronized (lockName) {
            this.name = name;
            System.out.println("Updated name: " + name);
        }
    }

    public void deposit(double amount) {
        try {
            System.out.println("Talking with the teller...\n");
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (lockBalance) {
            double origBalance = balance;
            balance += amount;
            System.out.printf("Starting balance: %.0f, Deposit (%.0f): New balance = %.0f%n", origBalance, amount, balance);

            addExtra(amount);
        }
    }

    private void addExtra(double amount) {
        if(amount >= 5000) {
            synchronized (lockBalance) {
                balance += 25;
                System.out.println("Extra was added");
            }
        }
    }

    public synchronized void withdrawal(double amount) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        double origBalance = balance;
        if(amount < balance) {
            balance -= amount;
            System.out.printf("Starting balance: %.0f, Withdrawal (%.0f): New balance = %.0f%n", origBalance, amount, balance);
        }
        else {
            System.out.printf("Starting balance: %.0f, Withdrawal (%.0f): INSUFFICIENT FUNDS!\n", origBalance, amount);
        }
    }
}
