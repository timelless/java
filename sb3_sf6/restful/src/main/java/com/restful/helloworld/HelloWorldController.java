package com.restful.helloworld;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HelloWorldController {

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private MessageSource messageSource;

//    @RequestMapping(path = "hello-world", method = RequestMethod.GET)
    @GetMapping(path = "hello-world")
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping(path = "hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World!");
    }

    @GetMapping(path = "hello-world/variable/{name}")
    public HelloWorldBean helloWorldVaraible(@PathVariable String name) {
        return new HelloWorldBean(
            String.format("Hello World, %s!", name)
        );
    }

    @GetMapping(path = "good-morning")
    public String goodMorning() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default message", locale);
    }
}
