package online.store.project.factory;
import online.store.project.controllers.StoreController;
import online.store.project.domain.Store;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StoreEntityFactory implements SimpleRepresentationModelAssembler<Store> {

    @Override
    public void addLinks(EntityModel<Store> resource) {
        resource.add(
                WebMvcLinkBuilder.linkTo(methodOn(StoreController.class).getStore(resource.getContent().getStoreId())).withSelfRel());
        resource.add(linkTo(methodOn(StoreController.class).getAllStores()).withRel("store information"));
    }
    @Override
    public void addLinks(CollectionModel<EntityModel<Store>> resources) {
        resources.add(linkTo(methodOn(StoreController.class).getAllStores()).withSelfRel());
    }
}
