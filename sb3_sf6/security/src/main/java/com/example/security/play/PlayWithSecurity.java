package com.example.security.play;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayWithSecurity {
    @GetMapping(path = "/csrf-token")
    public CsrfToken getCsrftoken(HttpServletRequest request) {
        // See csrf header name and use it in post requests
        // CSRF is not applicable for stateless apps
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
