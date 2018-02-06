package org.chsi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;

/**
 * Created by chsi on 19/01/2018.
 */
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"title","author", "price", "description"})
@ToString(includeFieldNames = true)
public class Book extends ResourceSupport implements Serializable {

    @Getter
    private String isbn;

    @Getter
    private String title;

    @JsonIgnore
    @Getter
    private Author author;

    @Getter
    private Category category;

    @Getter
    private Double price;

    @Getter @Setter
    private String description;

    public Book(String isbn, String title, Author author,Category category, Double price) {
        this.isbn = isbn;
        this.title = title;
        this.category = category;
        this.author = author;
        this.price = price;
    }

}
