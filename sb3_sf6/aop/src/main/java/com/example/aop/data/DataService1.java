package com.example.aop.data;

import org.springframework.stereotype.Service;

@Service
public class DataService1 {
    public int[] retrieveData() {
        return new int[] {11,22,33,44,55};
    }
}
