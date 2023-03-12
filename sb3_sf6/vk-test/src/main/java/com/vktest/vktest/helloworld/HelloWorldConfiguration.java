package com.vktest.vktest.helloworld;

// import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

// Getters and setters are automatically created for records
record Person(String name, int age, Address address) { };
record Address(String firstLine, String city) { };

@Configuration
public class HelloWorldConfiguration {
    // Define here Spring beans

    // Produce a bean and this bean will be managed by Spring container
    @Bean
    public String name() {
        return "Random name";
    }

    @Bean
    public Integer age() {
        return 26;
    }

    @Bean
    public Person person() {
        Person person = new Person("Another random name", 28, new Address("Main street", "Sofia"));
        return person;
    }

    @Bean(name="address2")
    @Primary
    public Address address() {
        Address address = new Address("Random street name", "Plovdiv");
        return address;
    }

    @Bean(name="address3")
    @Qualifier("address3Qualifier")
    public Address address3() {
        Address address = new Address("Random street name 3", "Plovdiv");
        return address;
    }

    @Bean
    public Person person2MethodCall() {
        Person person = new Person(name(), age(), address());
        return person;
    }

    @Bean
    public Person person3Parameters(String name, int age, Address address3) {
        Person person = new Person(name, age, address3);
        return person;
    }

    @Bean
    @Primary
    public Person person4Parameters(String name, int age, Address address) {
        Person person = new Person(name, age, address);
        return person;
    }

    @Bean
    public Person person5Qualifier(String name, int age,  @Qualifier("address3Qualifier") Address address) {
        Person person = new Person(name, age, address);
        return person;
    }
}
