package org.example.misc;

public class CachedData {
    private volatile boolean flag = false;

    public void toggleFlag() {
        flag = !flag;
    }

    public boolean isReady() {
        return flag;
    }
}