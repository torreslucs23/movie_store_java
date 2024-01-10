package com.example.movies.user;

import com.example.movies.config.JwtUtil;
import com.example.movies.dtos.LoginDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor

public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping
    public User create(@RequestBody User user){
        return userService.create(user);

    }


    @PostMapping("/login")
    public String login(@RequestBody User request){
        String username = request.getUsername();
        System.out.println(username);
        String password = request.getPassword();
        String token = userService.login(username, password);

        return token;
    }
}
