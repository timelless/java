package com.realworld;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@ComponentScan
public class RealWorldExample {

    public static void main(String[] args) {
        try (
                var context = new AnnotationConfigApplicationContext(RealWorldExample.class);
        ) {
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

            int max = context.getBean(BusinessCalculationService.class).findMax();
            System.out.println(max);
        }
    }
}
