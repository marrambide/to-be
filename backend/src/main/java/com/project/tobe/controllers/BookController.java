package com.project.tobe.controllers;

import com.project.tobe.models.Book;
import com.project.tobe.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BookController {

    private final Logger log = LoggerFactory.getLogger(BookController.class);
    private BookRepository bookRepo;

    public BookController(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    @GetMapping("/books")
    Collection<Book> books() {
        return bookRepo.findAll();
    }

    @GetMapping("/book/{id}")
    ResponseEntity<?> getBook(@PathVariable String id) {
        Optional<Book> book = bookRepo.findById(id);
        return book.map(response -> ResponseEntity.ok().body(response)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
