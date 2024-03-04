package com.restTemplate.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Ratings {

    private long ratingId;

    private String ratings;

}