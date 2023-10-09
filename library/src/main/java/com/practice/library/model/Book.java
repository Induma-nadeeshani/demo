package com.practice.library.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String isbn;
    private String name;
    private String author;

}
