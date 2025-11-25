package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.acme.entity.Article;

@ApplicationScoped // makes the class CDI bean (with jakarta CDI)
public class ArticlesService  extends CrudService<Article> {

    public ArticlesService() {
        // For CDI needs
        super(null);
    }

    @Inject
    public ArticlesService(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<Article> getEntityClass() {
        return Article.class;
    }
}