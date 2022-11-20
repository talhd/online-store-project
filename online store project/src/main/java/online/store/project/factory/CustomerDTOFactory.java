package online.store.project.factory;

import online.store.project.controllers.CustomersController;
import online.store.project.dto.CustomerDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CustomerDTOFactory implements SimpleRepresentationModelAssembler<CustomerDTO> {

    @Override
    public void addLinks(EntityModel<CustomerDTO> resource) {
        resource.add(
                WebMvcLinkBuilder.linkTo(methodOn(CustomersController.class).getCustomer(resource.getContent().getCustomer().getCustomerId())).withSelfRel());
        resource.add(linkTo(methodOn(CustomersController.class).getCustomers()).withRel("Customer information"));
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<CustomerDTO>> resources) {
        resources.add(linkTo(methodOn(CustomersController.class).getCustomers()).withSelfRel());
    }
}
