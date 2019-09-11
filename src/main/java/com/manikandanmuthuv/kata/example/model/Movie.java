package com.manikandanmuthuv.kata.example.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private String id;
    private String title;
    private String genre;    
}