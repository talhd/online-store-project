package online.store.project.factory;
import online.store.project.controllers.CustomersController;
import online.store.project.domain.Customer;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class CustomerEntityFactory implements SimpleRepresentationModelAssembler<Customer> {

    @Override
    public void addLinks(EntityModel<Customer> resource) {
        resource.add(
                WebMvcLinkBuilder.linkTo(methodOn(CustomersController.class).getCustomer(resource.getContent().getCustomerId())).withSelfRel());
        resource.add(linkTo(methodOn(CustomersController.class).getCustomers()).withRel("Customer information"));
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Customer>> resources) {
        resources.add(linkTo(methodOn(CustomersController.class).getCustomers()).withSelfRel());
    }
}
