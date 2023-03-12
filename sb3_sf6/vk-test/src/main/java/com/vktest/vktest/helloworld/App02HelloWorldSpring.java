package com.vktest.vktest.helloworld;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class App02HelloWorldSpring {
    public static void main(String[] args) {
        // 1. Launch spring context
        // 1. Configure the things we want Spring to manage - @Configuration class

        // Create Spring context
        // Try with resources
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class)) {
            // Retrieve Bean managed by Spring
            System.out.println(context.getBean("name"));
            System.out.println(context.getBean("age"));
            System.out.println(context.getBean("person"));
            System.out.println(context.getBean("person2MethodCall"));
            System.out.println(context.getBean("person3Parameters"));
            System.out.println(context.getBean("address2"));

            System.out.println(context.getBean(Person.class));
            System.out.println(context.getBean(Address.class));
            System.out.println(context.getBean("person5Qualifier"));

            // Print all java beans
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
        }
    }
}
