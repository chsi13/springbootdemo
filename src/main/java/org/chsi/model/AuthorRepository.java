package org.chsi.model;

public interface AuthorRepository {

    void add(Author author);

    Author get(String name);
}
