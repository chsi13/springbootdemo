package org.chsi.model;

import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

@Repository
public class AuthorInMemoryRepository implements AuthorRepository{

    public AuthorInMemoryRepository() {
        data = new HashSet<>(); // No duplicates
        data.add(Author.of("Josh","Bloch"));
        data.add(Author.of("Michael","Nygaard"));
        data.add(Author.of("Martin","Odersky"));
    }

    private Collection<Author> data;
    @Override
    public void add(final Author author) {
        data.add(author);
    }

    @Override
    public Author get(final String name) {
       Optional<Author> found =  data.stream().filter(a -> a.getName().equalsIgnoreCase(name)).findAny();
       if(found.isPresent())
           return found.get();
       return null;
    }
}
