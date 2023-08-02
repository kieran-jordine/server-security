package com.kyzen.resource_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ResourceServer {
    public static void main(String[] args) {
        SpringApplication.run(ResourceServer.class, args);
    }
}

@RestController
@RequestMapping("/")
class MyController {
    private final MyService myService;

    MyController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping
    public Object home(@AuthenticationPrincipal Jwt jwt) {
        return jwt;
    }

    @GetMapping("/method-security")
    public Object ms() {
        return myService.home();
    }
}

@Service
@EnableMethodSecurity
class MyService {
    @PreAuthorize("hasAuthority('SCOPE_user.read')")
    public Object home() {
        var jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return jwt.getSubject();
    }
}
