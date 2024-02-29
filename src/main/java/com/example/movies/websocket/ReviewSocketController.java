package com.example.movies.websocket;

import com.example.movies.dtos.NewMovieMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ReviewSocketController {

    @MessageMapping("/notificationsocket")
    @SendTo("/notification/movie")
    public NewMovieMessage sendReview(NewMovieMessage message){
        return message;
    }
}
