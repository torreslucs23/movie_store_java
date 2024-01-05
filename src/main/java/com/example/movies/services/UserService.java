package com.example.movies.services;

import com.example.movies.dtos.UserRecordDTO;
import com.example.movies.models.UserModel;
import com.example.movies.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<UserRecordDTO> findAll(){
        List<UserModel> result = userRepository.findAll();
        return result.stream()
                .map(user -> new UserRecordDTO(user.getName()))
                .toList();
    }
    @Transactional
    public UserModel newUser(UserRecordDTO userRecordDTO){
        UserModel user = new UserModel();
        user.setName(userRecordDTO.name());
        return userRepository.save(user);
    }
}
