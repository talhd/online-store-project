package online.store.project.repositories;
import online.store.project.domain.Customer;
import online.store.project.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CustomersRepository extends JpaRepository<Customer, Integer> {
    Product returnCustomerProductInIndex(Integer CustomerId, Integer ProductIndex);
}
