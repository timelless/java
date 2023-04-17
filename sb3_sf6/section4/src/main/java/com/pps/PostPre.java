package com.pps;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class SomeClass {
    private DependencyClass dependencyClass;

    public SomeClass(DependencyClass dependencyClass) {
        this.dependencyClass = dependencyClass;
        System.out.println("Ready");
    }

    @PostConstruct
    public void init() {
        dependencyClass.getReady();
    }

    @PreDestroy
    public void cleanUp() {
        System.out.println("Clean");
    }
}

@Component
class DependencyClass {
    public void getReady() {
        System.out.println("Dependency ready");
    }
}

@Configuration
@ComponentScan
public class PostPre {

    public static void main(String[] args) {
        try (
                var context = new AnnotationConfigApplicationContext(PostPre.class);
        ) {
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
        }
    }
}
