package com.practice.library.service;

import com.practice.library.dto.BookRequest;
import com.practice.library.model.Book;
import com.practice.library.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class BookService {

    private final BookRepository bookRepository;
    private Logger logger;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.logger = LoggerFactory.getLogger(this.getClass());
        this.bookRepository = bookRepository;

    }

    public List<Book> getAllBooks(){
       logger.info("Getting all books from the repository.");
        List<Book> books = bookRepository.findAll();

        if (books.isEmpty()) {
            throw new NoSuchElementException("No books were found.");
        }
        return books;
    }

    public Optional<Book> getBookById(Long id){
        logger.info("GET_BOOK_BY_ID: {} ", id);
        return bookRepository.findById(id);
    }

    public Book createBook(Book book) throws Exception {
        logger.info("CREATE_BOOK " + book);
        Optional<Book> existingBook = bookRepository.findByIsbn(book.getIsbn());
        if (existingBook.isPresent()){
            throw new DataIntegrityViolationException("A book with the same name already exists");}
        bookRepository.save(book);
        return book;
    }

    public Book updateBook(Long id, BookRequest bookRequest){
        logger.info("UPDATE_BOOK " + bookRequest);
        Optional<Book> existingBook = bookRepository.findById(id);

        if (existingBook.isPresent()) {
            Book foundBook = existingBook.get();
            foundBook.setName(bookRequest.getName());
            foundBook.setIsbn(bookRequest.getIsbn());
            foundBook.setAuthor(bookRequest.getAuthor());
            bookRepository.save(foundBook);

            return foundBook;
        }else {
            // Book not found, throw an exception or handle it accordingly
            throw new NoSuchElementException("Book not found by ID: " + id);
        }

    }

    public void deleteBook(Long id){
        logger.info("DELETE_BOOK " + id);
        Optional<Book> existingBook = bookRepository.findById(id);

        if (existingBook.isPresent()) {
            Book foundBook = existingBook.get();
            bookRepository.delete(foundBook);
            logger.info("DELETE_BOOK " + "SUCCESS");
        }else {
            throw new NoSuchElementException();
        }
    }


}
