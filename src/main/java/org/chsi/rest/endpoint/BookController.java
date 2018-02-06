package org.chsi.rest.endpoint;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.chsi.model.Book;
import org.chsi.model.BookInMemoryRepository;
import org.chsi.model.BookRepository;
import org.chsi.model.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by chsi on 19/01/2018.
 */
@RestController
public class BookController {

    private Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookRepository bookRepository;

    @GetMapping(value = "/books/{isbn}")
    public Book getBookByIsbn(@PathVariable("isbn") String isbn) {
        Book found = bookRepository.find(isbn).orElseThrow(NotFoundException::new);
        found.add(linkTo(methodOn(BookController.class).getBookByIsbn(isbn)).withSelfRel());
        found.add(linkTo(methodOn(AuthorController.class).getAuthor(found.getAuthor().getName())).withRel("author"));
        return found;
    }

    @GetMapping("/books")
    public Collection<Book> findByTitle(@RequestParam(value = "title", required =  false) String title, @RequestParam(value = "author", required = false) String author) {
        if(!StringUtils.isEmpty(title))
            return bookRepository.findByTitle(title);
        if(!StringUtils.isEmpty(author))
            return bookRepository.findByAuthor(author);
        return Collections.emptyList();
    }


    @PostMapping(value = "/books")
    public ResponseEntity addBook(@RequestBody Book book) {
        book = bookRepository.add(book);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{isbn}")
                .buildAndExpand(book.getIsbn()).toUri();
        return ResponseEntity.created(location).build();
    }
}
