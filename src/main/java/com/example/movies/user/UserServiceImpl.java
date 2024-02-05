package com.example.movies.user;


import com.example.movies.config.JwtUtil;
import com.example.movies.models.Review;
import com.example.movies.models.Role;
import com.example.movies.repositories.ReviewRepository;
import com.example.movies.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private  RoleRepository roleRepository;

    @Autowired
    private ReviewRepository reviewRepository;
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    UserRepository userRepository;
    @Override
    public User create(User user){
        User existUser = userRepository.findByUsername(user.getUsername());

        if(existUser != null){
            throw new Error("user already exists");
        }
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        Long id = Long.valueOf(1);
        Optional<Role> role = roleRepository.findById(id);
        List<Role> roles = new ArrayList<>();
        roles.add(role.get());
        user.setRoles(roles);
        User createdUser = userRepository.save(user);
        return createdUser;
    }

    @Override
    public String login(String username, String password){
        User existUser = this.userRepository.findByUsername(username);
        if(existUser == null){
            throw new Error("user doesn't exists");
        }
        if(passwordEncoder().matches(password, existUser.getPassword())){
            return JwtUtil.generateToken(username);
        }
        else {
            throw new RuntimeException("Invalid password");
        }
    }

    @Override
    @Transactional
    public boolean deleteUser(Long id){
        Optional<User>existUser = this.userRepository.findById(id);
        if(existUser.isPresent()){
            User user = existUser.get();

            if(user.getRoles() != null){
                user.getRoles().clear();
            }
            reviewRepository.deleteByUserId(user.getId());

            userRepository.delete(user);
            return true;
        }
        return false;
    }


}
