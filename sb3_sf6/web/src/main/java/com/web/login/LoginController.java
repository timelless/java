package com.web.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LoginController {
//    private Logger logger = LoggerFactory.getLogger(getClass());
//    private AuthService authService;
//
//    public LoginController(AuthService authService) {
//        this.authService = authService;
//    }
//
//    /**@RequestMapping("login")
//    public String loginPage(@RequestParam String name, ModelMap model) {
//        model.put("name", name);
//        logger.debug("Test debug message, name is: {}", name);
//
//        return "login";
//    }*/
//
//    @RequestMapping(value = "login", method = RequestMethod.GET)
//    public String loginPage() {
//        return "login";
//    }
//
//    @RequestMapping(value = "login", method = RequestMethod.POST)
//    public String welcomePAge(@RequestParam String name, @RequestParam String password, ModelMap model) {
//        if(authService.authenticate(name, password)) {
//            model.put("name", name);
//            return "welcome";
//        }
//
//        model.put("errorMessage", "Invalid credentials");
//        return "login";
//    }
}
