package com.kyzen.auth_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication
public class AuthServer {
    public static void main(String[] args) {
        SpringApplication.run(AuthServer.class, args);
    }

    @Bean
    InMemoryUserDetailsManager userDetailsManager() {
        UserDetails one = User.withUsername("user").password("{noop}secret").roles("user").build();
        UserDetails two = User.withUsername("admin").password("{noop}secret").roles("admin").build();
        return new InMemoryUserDetailsManager(one, two);
    }

}
