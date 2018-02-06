package org.chsi.model;

import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by chsi on 19/01/2018.
 */
@Repository
public class BookInMemoryRepository implements BookRepository {

    private static Logger logger = LoggerFactory.getLogger(BookInMemoryRepository.class);

    public BookInMemoryRepository() {
        data = new HashSet<>(); // No duplicates
        data.add(new Book("1","Effective Java", Author.of("Josh","Bloch"),Category.Java, 45.5));
        data.add(new Book("2","Release it", Author.of("Michael","Nygaard"),Category.System, 40.0));
        data.add(new Book("3","Scala in action", Author.of("Martin","Odersky"), Category.JVM, 25.5));
    }

    private Collection<Book> data;

    @Override
    public Optional<Book> find(String isbn) {
        return findSingleByPredicate(b -> b.getIsbn().equalsIgnoreCase(isbn));
    }

    public Collection<Book> findByTitle(String title) {
        logger.info("Lookup for : " + title);
        return findMultipleByPredicate(b -> b.getTitle().equalsIgnoreCase(title));
    }

    @Override
    public Collection<Book> findByAuthor(String author) {
        return  findMultipleByPredicate(b ->  b.getAuthor().getName().equalsIgnoreCase(author) || b.getAuthor().getFirstname().equalsIgnoreCase(author));
    }

    @Override
    public Book add(@NonNull Book book) {
        data.add(book);
        return book;
    }

    private Optional<Book> findSingleByPredicate(Predicate<Book> filter) {
        return data.stream().filter(filter).findFirst();
    }

    private Collection<Book> findMultipleByPredicate(Predicate<Book> filter) {
        return data.stream().filter(filter).collect(Collectors.toList());
    }
}
