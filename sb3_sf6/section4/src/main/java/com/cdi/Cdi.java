package com.cdi;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

//@Component
@Named
class BusinessService {
    private DataService dataService;

    public DataService getDataService() {
        System.out.println("Setter injection");
        return dataService;
    }

//    @Autowired
    @Inject
    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }
}

//@Component
@Named
class DataService {}

@Configuration
@ComponentScan
public class Cdi {

    public static void main(String[] args) {
        try (
                var context = new AnnotationConfigApplicationContext(Cdi.class);
        ) {
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

            System.out.println(context.getBean(BusinessService.class).getDataService());
        }
    }
}
