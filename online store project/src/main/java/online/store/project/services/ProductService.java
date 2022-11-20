package online.store.project.services;
import online.store.project.domain.Product;
import online.store.project.dto.ProductDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

@Service
public interface ProductService {
    ResponseEntity<CollectionModel<EntityModel<Product>>> listAllProducts();

    ResponseEntity<EntityModel<Product>> getProductById(Integer id);

    ResponseEntity<EntityModel<Product>> saveProduct(Product product);

    ResponseEntity<String> deleteProduct(Integer id);

    ResponseEntity<EntityModel<Product>> updateProduct(Product product);
    ResponseEntity<EntityModel<ProductDTO>> getProductDTO(Integer id);

    ResponseEntity<CollectionModel<EntityModel<Product>>> getProductsInRange(BigDecimal min, BigDecimal max);
    CompletableFuture<ResponseEntity<EntityModel<Product>>> updatePrice(Integer ProductId, BigDecimal newPrice);
}
