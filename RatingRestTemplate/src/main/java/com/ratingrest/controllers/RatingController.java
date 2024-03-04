package com.ratingrest.controllers;

import com.ratingrest.models.Ratings;
import com.ratingrest.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/addRating")
    public ResponseEntity addRating(@RequestBody Ratings ratings){
        return ratingService.addRating(ratings);
    }

    @GetMapping("/getAllRatings")
    public ResponseEntity getAllUser(){
        return ratingService.getAllRatings();
    }

    @GetMapping("/getRatingsById/{ratingId}")
    public ResponseEntity getById(@PathVariable long ratingId){
        return ratingService.getRating(ratingId);
    }

    @GetMapping("/getRatingByUserId/{userId}")
    public ResponseEntity getratingByUserId(@PathVariable long userId){
        return ratingService.getRatingByUserId(userId);
    }

}
