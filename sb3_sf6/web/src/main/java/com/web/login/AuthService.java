package com.web.login;

import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public boolean authenticate(String name, String password) {
        return name.equalsIgnoreCase("vasilk") && password.equalsIgnoreCase("dummy");
    }
}
