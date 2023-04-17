package com.phase3;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

// Getters and setters are automatically created for record
record Person (String name, int age, Address address) {};

record Address (String firstLine, String city) {};

@Configuration
public class HelloWorldConfiguration {
    @Bean
    public String name() {
        return "Vasil";
    }

    @Bean
    public int age() {
        return 15;
    }

    @Bean
    public Person person() {
        return new Person("Vasil0", 22, new Address("Freedom 65", "Plovdiv"));
    }

    // relation by method call
    @Bean
    public Person personByMethodCall() {
        return new Person(name(), age(), address());
    }

    // relation by method call
    @Bean
    public Person personByParameters(String name, int age, Address address3) {
        return new Person(name, age, address3);
    }

    // 4
    @Bean
    @Primary
    public Person person4(String name, int age, Address address) {
        return new Person(name, age, address);
    }

    // 5
    @Bean
    public Person person5(String name, int age, @Qualifier("address3quailifier") Address address) {
        return new Person(name, age, address);
    }

    // configure bean names
    @Bean(name="address2")
    @Primary
    public Address address() {
        return new Address("Freedom 63", "Plovdiv");
    }

    @Bean(name="address3")
    @Qualifier("address3quailifier")
    public Address address3() {
        return new Address("Freedom 63", "Sofia");
    }
}
