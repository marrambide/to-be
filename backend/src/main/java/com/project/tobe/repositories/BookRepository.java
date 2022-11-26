package com.project.tobe.repositories;

import com.project.tobe.models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {

    @Query("{name: '?0'}")
    Book findBookByName(String name);

    @Query("{_id: '?0'}")
    Book findBookByISBN(String isbn);

    @Query(value = "{author:'?0'}", fields="{'name' : 1}")
    List<Book> findBooksByAuthor(String author);

    @Query(value = "{genre:'?0'}", fields="{'name' : 1}")
    List<Book> findBooksByGenre(String genre);

    public long count();
}
