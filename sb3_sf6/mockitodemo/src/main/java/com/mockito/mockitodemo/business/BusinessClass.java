package com.mockito.mockitodemo.business;

public class BusinessClass {
    private DataService dataService;

    public BusinessClass(DataService dataService) {
        this.dataService = dataService;
    }

    public int findGreatest() {
        int[] data = dataService.retrieveAllData();

        int greatest = Integer.MIN_VALUE;
        for (int value:data) {
            if(value > greatest) {
                greatest = value;
            }
        }

        return greatest;
    }
}

interface DataService {
    int[] retrieveAllData();
}