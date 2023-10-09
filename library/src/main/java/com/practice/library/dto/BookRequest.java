package com.practice.library.dto;

import com.practice.library.model.Book;
import lombok.Data;

@Data
public class BookRequest {

    private String isbn;
    private String name;
    private String author;

    public static Book toBook(BookRequest bookRequest) {
        Book book =new Book();
        book.setName(bookRequest.getName());
        book.setIsbn(bookRequest.getIsbn());
        book.setAuthor(bookRequest.getAuthor());
        return book;
    }
}