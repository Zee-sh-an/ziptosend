package com.ratingrest.models;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
public class Error {

    private final String message;

    private String code;

}
