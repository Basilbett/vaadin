package com.example.application.views.list;

import com.example.application.data.Contact;
import com.example.application.services.CrmService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("list")
@Route(value = "")
public class ListView extends VerticalLayout {

    private final CrmService service;
    Grid<Contact> grid=new Grid<> (Contact.class);
    TextField filterText=new TextField();

         ContactForm form;
    public ListView( CrmService service) {
        this.service=service;
        addClassName("list-view");
        setSizeFull();

        configureGrid();
        ConfigureForm();

       add(
               getToolbar(),

               GetContent()

               

       );
       updateList();
    }

    private void updateList() {
        grid.setItems(service.findAllContacts(filterText.getValue()));
    }

    private Component GetContent() {
        HorizontalLayout content =new HorizontalLayout(grid,form);
        content.setFlexGrow(2,grid);
        content.setFlexGrow(1,form);
        content.addClassName("content");
        content.setSizeFull();

        return content;

    }

    private void ConfigureForm() {

        form= new ContactForm(service.findAllCompanies(),service.findAllStatues());
        form.setWidth("25em");
    }

    private Component getToolbar() {


        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        filterText.addValueChangeListener(e ->updateList());

        Button addContactButton =new Button("add Contact");
        HorizontalLayout toolbar=new HorizontalLayout(filterText,addContactButton);
        toolbar.addClassName("toolbar");



        return toolbar;




    }

    private void configureGrid() {
        addClassName("contact-grid");
        setSizeFull();

        grid.setColumns("firstName" ,"lastName","email");
        grid.addColumn(contact->contact.getCompany().getName()).setHeader("Company");
        grid.addColumn(contact->contact.getStatus().getName()).setHeader("Status");

        grid.getColumns().forEach(column ->column.setAutoWidth(true));
    }

}
