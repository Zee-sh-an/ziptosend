package com.restTemplate.serivce;

import com.restTemplate.models.Error;
import com.restTemplate.models.Ratings;
import com.restTemplate.models.User;
import com.restTemplate.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger =  LoggerFactory.getLogger(UserService.class);

    public ResponseEntity addUser(User user) {
        try {
            User savingUser = userRepository.save(user);
            return new ResponseEntity<>(savingUser, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            Error error = Error.builder().code(HttpStatus.BAD_REQUEST.getReasonPhrase()).message("Error while adding the user").build();
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity getAll(){
        try {
            List<User> allUser = userRepository.findAll();
            if (allUser.isEmpty()){
                Error error = Error.builder().code(HttpStatus.NOT_FOUND.getReasonPhrase()).message("There is no data in the DB").build();
                return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(allUser,HttpStatus.OK);

        }catch (Exception e){
            Error error = Error.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).message("Error while retrieving the user details").build();
            return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity getUser(long userId){
        try {
            Optional<User> userbyId = userRepository.findById(userId);
            if (userbyId.isEmpty()){
                Error error = Error.builder().code(HttpStatus.NOT_FOUND.getReasonPhrase()).message("There is no user in the DB with this id").build();
                return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
            }


            ArrayList<Ratings> ratingservice = restTemplate.getForObject("http://localhost:8081/getRatingByUserId/"+ userbyId.get().getUserId() , ArrayList.class);
            System.out.println(ratingservice);
            logger.info("{}", ratingservice);

            userbyId.get().setRatings(ratingservice);

            return new ResponseEntity<>(userbyId,HttpStatus.OK);

        }catch (Exception e){
            Error error = Error.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).message("Error while retrieving the user details").build();
            return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
