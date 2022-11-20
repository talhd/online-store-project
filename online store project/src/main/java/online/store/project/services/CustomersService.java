package online.store.project.services;


import online.store.project.domain.Customer;
import online.store.project.domain.Product;
import online.store.project.dto.CustomerDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

public interface CustomersService{
    ResponseEntity<CollectionModel<EntityModel<Customer>>> listOfAllCustomers();
    ResponseEntity<EntityModel<Customer>> saveCustomer(Customer customer);
    ResponseEntity<EntityModel<Customer>> getCustomer(Integer customerId);
    ResponseEntity<String> deleteCustomer(Integer id);
    ResponseEntity<EntityModel<Customer>> updateCustomer(Customer customer);
    ResponseEntity<EntityModel<CustomerDTO>> getProducts(Integer id);
    ResponseEntity<EntityModel<Product>> getCustomerProductInIndex(Integer customerId, Integer productIndex);



}
