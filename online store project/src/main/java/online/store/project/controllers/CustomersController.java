package online.store.project.controllers;
import online.store.project.domain.Customer;
import online.store.project.domain.Product;
import online.store.project.dto.CustomerDTO;
import online.store.project.services.CustomersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@Api(value="onlinestore", description="Operations pertaining to products in Online Store")
public class CustomersController {

    private CustomersService customersService;
    @Autowired
    public void setCustomerService(CustomersService customersService) {
        this.customersService = customersService;
    }
    /*
    *add new customer,the customerId is autogenerate,even if customerId is determined,the system will ignore this information.
    * @RequestBody-customer object required
    * */
    @ApiOperation(value = "Add a customer")
    @RequestMapping(value = "/addCustomer", method= RequestMethod.POST, produces = "application/json")
    public ResponseEntity<EntityModel<Customer>> addCustomer(@RequestBody Customer customer){
        return customersService.saveCustomer(customer);
    }
    /*
    *return list of customer products,using ProductDTO and CustomerDTO
    *@PathVariable-id the customer id
    * */
    @ApiOperation(value = "get customer products")
    @RequestMapping(value = "/customerProducts/{id}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<EntityModel<CustomerDTO>> getProducts(@PathVariable Integer id){
        return customersService.getProducts(id);
    }
    /*
    * return list of all the customers,according to their full representation in the database
    * */
    @ApiOperation(value = "get all customers")
    @RequestMapping(value = "/allCustomers", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<CollectionModel<EntityModel<Customer>>> getCustomers(){
        return customersService.listOfAllCustomers();
    }
    /*
    * return specific customer, according to his representation in the database.
    * @PathVariable-id the customer id
    * */
    @ApiOperation(value = "get customer")
    @RequestMapping(value = "/customer/{id}", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<EntityModel<Customer>> getCustomer(@PathVariable Integer id){
       return customersService.getCustomer(id);
    }
    /*
    *delete customer from database,together with the customer, the products belonging to him will be deleted
    *@PathVariable-id the customer id
    * */
    @ApiOperation(value = "Delete a customer")
    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        return customersService.deleteCustomer(id);
    }
    /*
    * allows you to update any desired information about the customer
    *@RequestBody-customer object required
    * */
    @ApiOperation(value = "Update a  customer")
    @RequestMapping(value = "/update/", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<EntityModel<Customer>> updateProduct(@RequestBody Customer customer){
       return customersService.updateCustomer(customer);
    }
    /*
    *get the product customer have in his list by index
    *@RequestParam customerId, @RequestParam productIndex
    * */
    @ApiOperation(value = "get the product customer have in his list by index")
    @RequestMapping(value="/getProduct", method = RequestMethod.GET)
    public ResponseEntity<EntityModel<Product>> getCustomerProduct(@RequestParam(required=true) Integer customerId, @RequestParam(required=true) Integer productIndex){
        return customersService.getCustomerProductInIndex(customerId,productIndex);
    }


}
