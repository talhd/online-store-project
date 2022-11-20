package online.store.project.services;
import online.store.project.factory.ProductDTOFactory;
import online.store.project.domain.Product;
import online.store.project.dto.ProductDTO;
import online.store.project.factory.ProductEntityFactory;
import online.store.project.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

@Component
@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private ProductDTOFactory productDTOFactory;
    private ProductEntityFactory productEntityFactory;
    @Autowired
    public void setProductRepository(ProductRepository productRepository,
                                     ProductDTOFactory productDTOFactory,
                                     ProductEntityFactory productEntityFactory) {
        this.productRepository = productRepository;
        this.productDTOFactory=productDTOFactory;
        this.productEntityFactory=productEntityFactory;
    }

    @Override
    public ResponseEntity<CollectionModel<EntityModel<Product>>> listAllProducts() {
        try {
            return ResponseEntity.ok().body(productEntityFactory.toCollectionModel(productRepository.findAll()));
        }catch(RuntimeException e){
            throw new RuntimeException("an unexpected error occurred on the server, please try again");
        }
    }

    @Override
    public ResponseEntity<EntityModel<Product>> getProductById(Integer id) {
       return ResponseEntity.ok().body(productEntityFactory.toModel(productRepository.findById(id).orElseThrow(()->(new EntityNotFoundException("product not found")))));
    }

    @Override
    public ResponseEntity<EntityModel<Product>> saveProduct(Product product) {
        try {
            return ResponseEntity.ok().body(productEntityFactory.toModel(productRepository.save(product)));
        }catch(RuntimeException e){
            throw new RuntimeException("an unexpected error occurred on the server, please try again");
        }
    }

    @Override
    public ResponseEntity<String> deleteProduct(Integer id) {
       boolean exist=productRepository.existsById(id);
       if(exist){
           productRepository.deleteById(id);
           return ResponseEntity.ok().body("Product successfully deleted");
       }else {
           throw new EntityNotFoundException("product not found,");
       }
    }

    @Override
    public ResponseEntity<EntityModel<Product>> updateProduct(Product product) {
        Product productFromDb=productRepository.findById(product.getProductId()).orElseThrow(()->new EntityNotFoundException("product not found"));
        productFromDb.setCatalogNumber(product.getCatalogNumber());
        productFromDb.setDescription(product.getDescription());
        productFromDb.setImageUrl(product.getImageUrl());
        productFromDb.setDescription(product.getDescription());
        productFromDb.setPrice(product.getPrice());
        productFromDb.setVersion(product.getVersion());
        productRepository.saveAndFlush(productFromDb);
        return ResponseEntity.ok().body(productEntityFactory.toModel(productFromDb));
    }

    @Override
    public ResponseEntity<EntityModel<ProductDTO>> getProductDTO(Integer id) {
        return productRepository.findById(id)
                .map(ProductDTO::new).map(productDTOFactory::toModel)
                .map(ResponseEntity::ok)
                .orElseThrow(()->new EntityNotFoundException("product not found"));
    }

    @Override
    public ResponseEntity<CollectionModel<EntityModel<Product>>> getProductsInRange(BigDecimal min, BigDecimal max) {
       return ResponseEntity.ok().body(productEntityFactory.toCollectionModel(productRepository.getProductsInRange(min,max)));
    }

    @Override
    @Async("threadPoolTaskExecutor")
    public CompletableFuture<ResponseEntity<EntityModel<Product>>> updatePrice(Integer productId, BigDecimal newPrice) {
        Product product=productRepository.findById(productId).orElseThrow(()->(new EntityNotFoundException("Product not found")));
        product.setPrice(newPrice);
        productRepository.save(product);
        return CompletableFuture.completedFuture(ResponseEntity.ok().body(productEntityFactory.toModel(product)));
    }
}
