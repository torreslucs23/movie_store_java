package com.example.movies.user;

public interface UserService {
    User create(User user);
    String login(String username, String password);
}
