package com.example.movies.ping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "ping")
public class PingController {

    @GetMapping(value = "/pong")
    public String ping(){
        return "pong";
    }
}
