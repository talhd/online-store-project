package online.store.project.repositories;
import online.store.project.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.persistence.EntityNotFoundException;

@Component
public class CustomersRepositoryImpl{
    @Autowired
    private CustomersRepository customersRepository;
    public Product returnCustomerProductInIndex(Integer customerId, Integer productIndex){
        return customersRepository.findById(customerId).orElseThrow(()->new EntityNotFoundException("there is no customer with the given ID"))
                .getProductList().get(productIndex);
    }
}
