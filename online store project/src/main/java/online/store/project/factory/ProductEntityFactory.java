package online.store.project.factory;
import online.store.project.controllers.ProductController;
import online.store.project.domain.Product;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component

public class ProductEntityFactory implements SimpleRepresentationModelAssembler<Product> {

    @Override
    public void addLinks(EntityModel<Product> resource) {
        resource.add(
                WebMvcLinkBuilder.linkTo(methodOn(ProductController.class).showProduct(resource.getContent().getProductId())).withSelfRel());
        resource.add(linkTo(methodOn(ProductController.class).getAllProduct()).withRel("products information"));
    }
    @Override
    public void addLinks(CollectionModel<EntityModel<Product>> resources) {
        resources.add(linkTo(methodOn(ProductController.class).getAllProduct()).withSelfRel());
    }
}
