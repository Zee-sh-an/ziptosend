package com.ratingrest.repo;

import com.ratingrest.models.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingRepository extends JpaRepository<Ratings,Long> {
    @Query("select r from Ratings as r where r.userId = :userId")
    public List<Ratings> getRatingByUserId(@Param("userId") long userId);

}
