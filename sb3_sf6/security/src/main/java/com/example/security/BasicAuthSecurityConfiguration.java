package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

//@Configuration
//@EnableMethodSecurity
public class BasicAuthSecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(auth -> { auth.anyRequest().authenticated(); });
        ((
                AuthorizeHttpRequestsConfigurer.AuthorizedUrl)http.authorizeHttpRequests()
//                .requestMatchers("/users").hasRole("USER")
                .anyRequest()
        ).authenticated();

//        http.formLogin();
        http.httpBasic();
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.csrf().disable();

        // allow h2db frames
        http.headers().frameOptions().sameOrigin();

        return (SecurityFilterChain)http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        // multiple in-memory users
//        var user = User.withUsername("admin")
//                .password("{noop}admin")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }

    @Bean
    public DataSource dataSource() {
        // create needed tables with default schema queries
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
                .build();
    }

    @Bean
    public JdbcUserDetailsManager userDetailsService(DataSource dataSource) {
        // using jdbc not in-memory storage
        var user = User.withUsername("admin")
                .password("{noop}admin")
                .roles("USER")
                .build();

        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.createUser(user);
        return userDetailsManager;
    }
}
