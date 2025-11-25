package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Category;

@ApplicationScoped // makes the class CDI bean (with jakarta CDI)
public class CategoriesService {
    public Category get() {
        return new Category().name("drinks");
    };
}
