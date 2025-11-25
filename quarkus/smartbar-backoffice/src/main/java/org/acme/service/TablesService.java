package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.acme.entity.Table;

@ApplicationScoped // makes the class CDI bean (with jakarta CDI)
public class TablesService extends CrudService<Table> {

    public TablesService() {
        // Just for CDI requirements
        super(null);
    }

    @Inject
    public TablesService(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<Table> getEntityClass() {
        return Table.class;
    }
}
