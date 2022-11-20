package online.store.project.repositories;
import online.store.project.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
public class ProductRepositoryImpl {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProductsInRange(BigDecimal min, BigDecimal max){
        return productRepository.findAll().stream().filter((product)->(product.getPrice().compareTo(min)>= 0  &&
                product.getPrice().compareTo(max)<=0)).collect(Collectors.toList());
    }



}
