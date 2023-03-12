package com.vktest.vktest.exmples.g1;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jdk.jfr.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
@Named
class BusinessService {
    private DataService dataService;

    public DataService getDataService() {
        return dataService;
    }

//    @Autowired
    @Inject
    public void setDataService(DataService dataService) {
        System.out.println("Setter injection performed");
        this.dataService = dataService;
    }
}

//@Component
@Named
class DataService {
}

@Configuration
@ComponentScan
public class CdiContextAppLauncherApplication {

    public static void main(String[] args) {
        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CdiContextAppLauncherApplication.class)) {
            System.out.println(context.getBean(BusinessService.class).getDataService());
        }
    }
}
