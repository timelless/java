package com.vktest.vktest.exmples.h1;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.Arrays;


public class XmlConfigurationAppLauncherApplication {

    public static void main(String[] args) {
        try(ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("contextConfiguration.xml")) {
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
            System.out.println(context.getBean("name"));
            System.out.println(context.getBean("age"));
        }
    }
}
