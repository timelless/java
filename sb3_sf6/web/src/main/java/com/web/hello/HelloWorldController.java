package com.web.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {
    @RequestMapping("say-hello")
    @ResponseBody
    public String helloWorld() {
        return "Hello <b>world</b>!";
    }

    @RequestMapping("say-html")
    @ResponseBody
    public String helloHtml() {
        StringBuffer sb = new StringBuffer();
        // StringBuffer.append()
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>Hello html</title>");
        sb.append("</head>");
        sb.append("<body>Hello <b>html</b>!</body>");
        sb.append("</html>");

        return  sb.toString();
    }

    @RequestMapping("say-jsp")
    public String helloJsp() {
        return "hello";
    }
}
