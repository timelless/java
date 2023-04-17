package com.lazy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
class ClassA {
}

@Component
@Lazy
class ClassB {
    private ClassA classA;

    public ClassB(ClassA classA) {
        this.classA = classA;

        System.out.println("Init");
    }

    public void doS() {
        System.out.println("doS");
    }
}

@Configuration
@ComponentScan
public class LazyExample {

    public static void main(String[] args) {
        try (
                var context = new AnnotationConfigApplicationContext(LazyExample.class);
        ) {
//            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
//            context.getBean(ClassB.class).doS();
        }
    }
}
