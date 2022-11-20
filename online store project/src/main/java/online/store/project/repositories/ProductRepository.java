package online.store.project.repositories;
import online.store.project.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> getProductsInRange(BigDecimal min, BigDecimal max);

}
