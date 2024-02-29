package com.example.movies.ping;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "ping")
@SecurityRequirement(name = "Bearer Authentication")

public class PingController {

    @GetMapping(value = "/pong")
    @PreAuthorize("hasRole('default_user')")
    public String ping(){
        return "pong";
    }
}
