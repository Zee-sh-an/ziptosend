package com.ratingrest.service;

import com.ratingrest.models.Error;
import com.ratingrest.models.Ratings;
import com.ratingrest.repo.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public ResponseEntity addRating(Ratings ratings) {
        try {

            Ratings saveratings = ratingRepository.save(ratings);

            return new ResponseEntity(saveratings, HttpStatus.OK);

        } catch (Exception e) {
            Error error = Error.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).message("Error while Adding user").build();
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity getAllRatings() {
        try {
            List<Ratings> allRatings = ratingRepository.findAll();
            if (allRatings.isEmpty()) {
                Error error = Error.builder().code(HttpStatus.NOT_FOUND.getReasonPhrase()).message("There is no user in the DB with this id").build();
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(allRatings, HttpStatus.OK);
        } catch (Exception e) {
            Error error = Error.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).message("Error while retrieving the ratings details").build();
            return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        }

        public ResponseEntity<List<Ratings>> getRating(long ratingId){
        try {
            List ratingbyId = Collections.singletonList(ratingRepository.findById(ratingId));
            if (ratingbyId.isEmpty()){
                Error error = Error.builder().code(HttpStatus.NOT_FOUND.getReasonPhrase()).message("There is no rating in the DB with this id").build();
                return new ResponseEntity<List<Ratings>>((List<Ratings>) error,HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<List<Ratings>>(ratingbyId,HttpStatus.OK);

        }catch (Exception e){
            Error error = Error.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).message("Error while retrieving the rating details").build();
            return new ResponseEntity<List<Ratings>>((List<Ratings>) error,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity getRatingByUserId(long userId){
        try {
            List<Ratings> ratingByUserId = (ratingRepository.getRatingByUserId(userId));
            if (ratingByUserId.isEmpty()){
                Error error = Error.builder().code(HttpStatus.NOT_FOUND.getReasonPhrase()).message("There is no rating in the DB with this user id").build();
                return new ResponseEntity<>( error,HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(ratingByUserId,HttpStatus.OK);

        }catch (Exception e){
            Error error = Error.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).message("Error while retrieving the rating details").build();
            return new ResponseEntity<>( error,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
