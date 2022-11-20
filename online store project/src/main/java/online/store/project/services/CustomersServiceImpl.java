package online.store.project.services;
import online.store.project.domain.Customer;
import online.store.project.domain.Product;
import online.store.project.dto.CustomerDTO;
import online.store.project.factory.CustomerDTOFactory;
import online.store.project.factory.CustomerEntityFactory;
import online.store.project.factory.ProductEntityFactory;
import online.store.project.repositories.CustomersRepository;
import online.store.project.repositories.StoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
@Slf4j
@Component
@Service
public class CustomersServiceImpl implements CustomersService {
    private CustomersRepository customersRepository;
    private CustomerEntityFactory customerEntityFactory;
    private StoreRepository storeRepository;
    private CustomerDTOFactory customerDTOFactory;
    private ProductEntityFactory productEntityFactory;

    @Autowired
    public void setCustomersRepository(CustomersRepository customersRepository,CustomerEntityFactory customerEntityFactory,
                                    StoreRepository storeRepository,CustomerDTOFactory customerDTOFactory,
                                    ProductEntityFactory productEntityFactory) {
        this.storeRepository=storeRepository;
        this.customersRepository = customersRepository;
        this.customerEntityFactory=customerEntityFactory;
        this.customerDTOFactory=customerDTOFactory;
        this.productEntityFactory=productEntityFactory;
    }

    @Override
    public ResponseEntity<CollectionModel<EntityModel<Customer>>> listOfAllCustomers() {
        return ResponseEntity.ok().body(customerEntityFactory.toCollectionModel(customersRepository.findAll()));
    }

    @Override
    public ResponseEntity<EntityModel<Customer>> saveCustomer(Customer customer) {
        if(customer.getStore().getStoreId() != null){
            List<Product> customerProducts =customer.getProductList();
            List<Product> storeProducts=storeRepository.findById(customer.getStore().getStoreId()).orElseThrow(()->new EntityNotFoundException("store was not found")).getProductsList();
            for (Product customerProduct:customerProducts){
                if (!containsId(storeProducts,customerProduct.getProductId())){
                    throw new RuntimeException("Only products belonging to the store to which the customer belongs can be added");
                }
            }
            return ResponseEntity.ok().body(customerEntityFactory.toModel(customersRepository.save(customer)));
        }else {
            throw new RuntimeException("The customer is not linked to any store, so the customer can not pick up products");
        }
    }
    @Override
    public ResponseEntity<EntityModel<Customer>> getCustomer(Integer customerId) {
       return ResponseEntity.ok().body(customerEntityFactory.toModel(customersRepository.findById(customerId).orElseThrow(()->(new EntityNotFoundException("Customer not found")))));
    }

    @Override
    public ResponseEntity<String> deleteCustomer(Integer id) {
        customersRepository.findById(id).orElseThrow(()->new EntityNotFoundException("a customer that has never existed can not be deleted"));
        customersRepository.deleteById(id);
        return ResponseEntity.ok().body("customer was successfully deleted");

    }

    @Override
    public ResponseEntity<EntityModel<Customer>> updateCustomer(Customer customer) {
       Customer customerFromDb=customersRepository.findById(customer.getCustomerId()).orElseThrow(()->new EntityNotFoundException("Customer not found"));
       customerFromDb.setCustomerName(customer.getCustomerName());
       return ResponseEntity.ok().body(customerEntityFactory.toModel(customersRepository.saveAndFlush(customerFromDb)));
    }

    @Override
    public ResponseEntity<EntityModel<CustomerDTO>> getProducts(Integer id) {
        return customersRepository.findById(id)
                .map(CustomerDTO::new).map(customerDTOFactory::toModel)
                .map(ResponseEntity::ok)
                .orElseThrow(()->new EntityNotFoundException("customer not found"));
    }


    public boolean containsId(final List<Product> list, final Integer id){
        return list.stream().anyMatch(o -> o.getProductId().equals(id));
    }
    @Override
    public ResponseEntity<EntityModel<Product>> getCustomerProductInIndex(Integer customerId, Integer productIndex) {
        try {
            return ResponseEntity.ok().body(productEntityFactory.toModel(customersRepository.returnCustomerProductInIndex(customerId,productIndex)));
        }catch (RuntimeException e){
            throw new RuntimeException("The server failed to fulfill the request, please try again");
        }
    }


}
