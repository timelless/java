package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.acme.entity.Category;

@ApplicationScoped // makes the class CDI bean (with jakarta CDI)
public class CategoriesService extends CrudService<Category> {

    public CategoriesService() {
        // For CDI needs
        super(null);
    }

    @Inject
    public CategoriesService(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<Category> getEntityClass() {
        return Category.class;
    }
}
