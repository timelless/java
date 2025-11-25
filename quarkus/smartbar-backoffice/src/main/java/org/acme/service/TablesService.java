package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Table;

@ApplicationScoped // makes the class CDI bean (with jakarta CDI)
public class TablesService {
    public Table get() {
        return new Table().name("table");
    };
}
