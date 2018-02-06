package org.chsi.rest.endpoint;

import org.chsi.model.Author;
import org.chsi.model.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {
    private Logger logger = LoggerFactory.getLogger(AuthorController.class);

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/authors")
    public Author getAuthor(@RequestParam(value = "name") String name) {
        logger.info("get author by name : " + name);
        return authorRepository.get(name);
    }

}
