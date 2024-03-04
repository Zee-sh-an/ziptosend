package com.ratingrest.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ratings {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long ratingId;

    private String ratings;

    private long userId;

}
