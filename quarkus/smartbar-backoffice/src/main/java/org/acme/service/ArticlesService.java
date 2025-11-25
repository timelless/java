package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Article;

@ApplicationScoped // makes the class CDI bean (with jakarta CDI)
public class ArticlesService {
    public Article get() {
        return new Article().name("cola");
    };
}
