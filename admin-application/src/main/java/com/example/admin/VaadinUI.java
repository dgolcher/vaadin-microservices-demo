package com.example.admin;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.vaadin.crudui.crud.impl.GridCrud;

@SpringUI
public class VaadinUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        GridCrud<Company> crud = new GridCrud<>(Company.class);
        crud.getGrid().setColumns("name", "phone", "email", "address");
        crud.getCrudFormFactory().setVisibleProperties("name", "phone", "email", "address");
        crud.setOperations(
                () -> Services.getCompanyService().findAll().getContent(),
                company -> Services.getCompanyService().add(company),
                company -> Services.getCompanyService().update(company.getId(), company),
                company -> Services.getCompanyService().delete(company.getId())
        );

        setContent(crud);
    }

}
