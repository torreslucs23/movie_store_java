package com.example.movies.user;

import com.example.movies.config.JwtUtil;
import com.example.movies.dtos.LoginDto;
import com.example.movies.dtos.ResponseDto;
import com.example.movies.dtos.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor


public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private  UserRepository userRepository;


    @PostMapping
    public User create(@RequestBody User user){
        return userService.create(user);

    }


    @PostMapping("/login")
    public UserDto login(@RequestBody User request){
        String username = request.getUsername();
        String password = request.getPassword();
        String token = userService.login(username, password);
        User optUser = userRepository.findByUsername(username);
        UserDto user = new UserDto(optUser.getId(), optUser.getUsername(), token );

        return user;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted){
            ResponseDto response = new ResponseDto("success", "User deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
        ResponseDto response = new ResponseDto("error", "User not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
