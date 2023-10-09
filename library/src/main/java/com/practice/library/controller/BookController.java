package com.practice.library.controller;

import com.practice.library.dto.BookRequest;
import com.practice.library.dto.ResponseHandler;
import com.practice.library.model.Book;
import com.practice.library.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@CrossOrigin(origins = "http://127.0.0.1:3000")
@RestController
@RequestMapping("api/v1")
public class BookController {

    private final BookService bookService;
    private Logger logger;

    @Autowired
    public BookController(BookService bookService) {
        this.logger = LoggerFactory.getLogger(this.getClass());
        this.bookService = bookService;
    }

    @GetMapping("/book")
    public ResponseEntity<Object> getAllBooks() {
        logger.info("BOOK: " + "GET_ALL_BOOKS");

        try {
            List<Book> books = bookService.getAllBooks();
            return ResponseHandler.generateResponse("Books retrieved successfully", HttpStatus.OK, books);
        } catch (NoSuchElementException e) {
            return ResponseHandler.generateResponse("No books found", HttpStatus.NOT_FOUND, null);
        }catch (Exception ex) {
            return ResponseHandler.generateResponse("RETRIEVED_FAILED", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Object> getBookById(@PathVariable Long id){
        Optional<Book> bookOptional = bookService.getBookById(id);
        if (bookOptional.isPresent()) {
            return ResponseHandler.generateResponse("RETRIEVED_SUCCESSFULLY", HttpStatus.OK, bookOptional.get());
        } else {
            return ResponseHandler.generateResponse("Book not found", HttpStatus.NOT_FOUND, null);
        }
    }

    @PostMapping("/book")
    public ResponseEntity<Object> createBook(@RequestBody BookRequest bookRequest){
        logger.info("BOOK: " + "CREATE_BOOK: " + bookRequest);
        if(bookRequest.getName() == null || bookRequest.getIsbn() == null){
            return ResponseHandler.generateResponse("SOME_FIELDS_ARE_MISSING", HttpStatus.BAD_REQUEST, null);
        }
        try {
            System.out.println("AAAAAAAA");
            Book book = bookService.createBook(BookRequest.toBook(bookRequest));
            logger.info("ADD_BOOK: "+ "SUCCESS");
            return ResponseHandler.generateResponse("SAVED_SUCCESSFULLY", HttpStatus.CREATED, book);

        } catch (DataIntegrityViolationException e) {
            logger.error("ADD_BOOK: "+ "FAILED" + e.getMessage());
            return ResponseHandler.generateResponse("SAVED_FAILED", HttpStatus.BAD_REQUEST, null);
        }catch (Exception ex) {
            logger.error("ADD_BOOK: "+ "FAILED" + ex.getMessage());
            return ResponseHandler.generateResponse("SAVED_FAILED", HttpStatus.BAD_REQUEST, null);
        }
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable Long id,@RequestBody BookRequest bookRequest){
        logger.info("BOOK: " + "EDIT_BOOK: " + bookRequest);

        try {
            Book book = bookService.updateBook(id, bookRequest);
            logger.info("UPDATE_BOOK: "+ "SUCCESS");
            return ResponseHandler.generateResponse("UPDATED_SUCCESSFULLY", HttpStatus.OK, book);

        } catch (NoSuchElementException ex) {
            logger.error("UPDATE_BOOK: " + "FAILED" + ex.getMessage());
            return ResponseHandler.generateResponse("SAVED_FAILED", HttpStatus.NOT_FOUND, null);

        }catch (Exception e) {
            logger.error("UPDATE_BOOK: "+ "FAILED" + e.getMessage());
            return ResponseHandler.generateResponse("SAVED_FAILED", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable Long id){

        try {
            bookService.deleteBook(id);
            logger.info("DELETE_BOOK: "+ "SUCCESS");
            return ResponseHandler.generateResponse("DELETED_SUCCESSFULLY", HttpStatus.OK, id);

        }catch (NoSuchElementException ex) {
            logger.error("DELETE_BOOK: " + "FAILED" + ex.getMessage());
            return ResponseHandler.generateResponse("DELETE_FAILED", HttpStatus.NOT_FOUND, null);
        }catch (Exception e){
            logger.error("DELETE_BOOK: " + "FAILED " + e.getMessage());
            return ResponseHandler.generateResponse("DELETE_BOOK", HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

}
