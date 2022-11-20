package online.store.project.factory;
import online.store.project.controllers.ProductController;
import online.store.project.dto.ProductDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class ProductDTOFactory implements SimpleRepresentationModelAssembler<ProductDTO> {

    @Override
    public void addLinks(EntityModel<ProductDTO> resource) {
        resource.add(
        linkTo(methodOn(ProductController.class).getProductPrice(resource.getContent().getId())).withSelfRel());
        resource.add(linkTo(methodOn(ProductController.class).getAllProduct()).withRel("products information"));
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<ProductDTO>> resources) {
        resources.add(linkTo(methodOn(ProductController.class).getAllProduct()).withSelfRel());
    }
}
