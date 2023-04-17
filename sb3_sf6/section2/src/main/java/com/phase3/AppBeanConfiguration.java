package com.phase3;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class AppBeanConfiguration {
    public static void main(String[] args) {
        // try with resources
        try (
            var context =  new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);
        )
        {

            // By bean name
            System.out.println("Name: " + context.getBean("name"));
            System.out.println("Age: " + context.getBean("age"));
            System.out.println("Person: " + context.getBean("person"));
            System.out.println("PersonByMethodCall: " + context.getBean("personByMethodCall"));
            System.out.println("PersonByParameters: " + context.getBean("personByParameters"));
            System.out.println("Address2: " + context.getBean("address2"));

            // By bean class
            System.out.println(context.getBean(Address.class));
            System.out.println("Person: " + context.getBean(Person.class));
            System.out.println("Person 5: " + context.getBean("person5"));

            System.out.println("ALL BEANS >>");
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
        }
    }
}
