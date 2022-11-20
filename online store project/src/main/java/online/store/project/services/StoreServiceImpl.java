package online.store.project.services;
import online.store.project.domain.Store;
import online.store.project.factory.ProductEntityFactory;
import online.store.project.factory.StoreEntityFactory;
import online.store.project.repositories.ProductRepository;
import online.store.project.repositories.StoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;

@Component
@Service
public class StoreServiceImpl implements StoreService {
    private static final Logger logger = LoggerFactory.getLogger(StoreServiceImpl.class.getSimpleName());

    private StoreRepository storeRepository;
    private ProductEntityFactory productEntityFactory;
    private StoreEntityFactory storeEntityFactory;
    private ProductRepository productRepository;
    @Autowired
    public void setStoreRepository(StoreRepository storeRepository, StoreEntityFactory storeEntityFactory,
                                   ProductEntityFactory productEntityFactory, ProductRepository productRepository) {
        this.storeRepository = storeRepository;
        this.storeEntityFactory=storeEntityFactory;

        this.productEntityFactory=productEntityFactory;
        this.productRepository=productRepository;
    }
    @Override
    public ResponseEntity<CollectionModel<EntityModel<Store>>> listOfAllStores() {
        try {
            return ResponseEntity.ok().body(storeEntityFactory.toCollectionModel(storeRepository.findAll()));
        }catch(RuntimeException e){
            throw new RuntimeException("an unexpected error occurred on the server, please try again");
        }
    }

    @Override
    public ResponseEntity<EntityModel<Store>> saveStore(Store store) {
        try {
            return ResponseEntity.ok().body(storeEntityFactory.toModel(storeRepository.save(store)));
        }catch(RuntimeException e){
            throw new RuntimeException("an unexpected error occurred on the server, please try again");
        }
    }
    @Override
    public ResponseEntity<EntityModel<Store>> getStore(Integer id) {
        try {
            return ResponseEntity.ok().body(storeEntityFactory.toModel(storeRepository.findById(id).orElseThrow(()->new EntityNotFoundException("store was not found"))));
        }catch(RuntimeException e){
            throw new RuntimeException("an unexpected error occurred on the server, please try again");
        }
    }

    @Override
    public ResponseEntity<String> removeStore(Integer id) {
        storeRepository.findById(id).orElseThrow(()->new EntityNotFoundException("No store with the given ID was found"));
        storeRepository.deleteById(id);
        return ResponseEntity.ok().body("The store was successfully deleted");
    }

    @Override
    public ResponseEntity<EntityModel<Store>> updateStore(Store store) {

        Store storeFromDb=storeRepository.findById(store.getStoreId()).orElseThrow(()->new EntityNotFoundException("store not found"));
        storeFromDb.setStoreName(store.getStoreName());
        storeFromDb.setStoreOwner(store.getStoreOwner());
        storeFromDb.setCategory(store.getCategory());
        return ResponseEntity.ok().body(storeEntityFactory.toModel(storeRepository.saveAndFlush(storeFromDb)));
    }

    @Override
    public ResponseEntity<CollectionModel<EntityModel<Store>>> getAllStoreInCategory(String category) {
        return ResponseEntity.ok().body(storeEntityFactory.toCollectionModel(storeRepository.returnAllStoresInCategory(category)));
    }
}
