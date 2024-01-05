package com.example.movies.controllers;

import com.example.movies.dtos.MovieRecordDTO;
import com.example.movies.dtos.UserRecordDTO;
import com.example.movies.models.MovieModel;
import com.example.movies.models.UserModel;
import com.example.movies.repositories.UserRepository;
import com.example.movies.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;



    @GetMapping (value="/users")
   public List<UserRecordDTO> findAll(){
        return userService.findAll();

    }

    @PostMapping(value="/users")
    public ResponseEntity<UserModel> newUser(@RequestBody UserRecordDTO userDTO){
        UserModel createdUser = userService.newUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);

    }
}
