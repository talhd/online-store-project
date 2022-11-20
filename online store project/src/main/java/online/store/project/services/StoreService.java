package online.store.project.services;
import online.store.project.domain.Store;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

public interface StoreService{
    ResponseEntity<CollectionModel<EntityModel<Store>>> listOfAllStores();
    ResponseEntity<EntityModel<Store>> saveStore(Store store);

    ResponseEntity<EntityModel<Store>> getStore(Integer id);

    ResponseEntity<String> removeStore(Integer id);
    ResponseEntity<EntityModel<Store>> updateStore(Store store);

    ResponseEntity<CollectionModel<EntityModel<Store>>> getAllStoreInCategory(String category);

}
