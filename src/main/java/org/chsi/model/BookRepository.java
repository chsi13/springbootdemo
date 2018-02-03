package org.chsi.model;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by chsi on 19/01/2018.
 */
public interface BookRepository {

    Optional<Book> find(String isbn);
    Collection<Book> findByTitle(String title);
    Collection<Book> findByAuthor(String author);
    Book add(Book book);
}
