package online.store.project.controllers;
import online.store.project.domain.Product;
import online.store.project.dto.ProductDTO;
import online.store.project.factory.ProductDTOFactory;
import online.store.project.factory.ProductEntityFactory;
import online.store.project.services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;
import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/product")
@Api(value="onlinestore")
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService,ProductEntityFactory productEntityFactory,
                                  ProductDTOFactory productDTOFactory) {
        this.productService = productService;
    }
    /*
    *return the list of all products
    * */
    @ApiOperation(value = "View a list of available products",response = Iterable.class)
    @RequestMapping(value = "/list", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity<CollectionModel<EntityModel<Product>>> getAllProduct() {
        return productService.listAllProducts();
    }
    /*
     *Search for a specific product in the database
     * @PathVariable id- product id
     * */
    @ApiOperation(value = "Search a product with an ID",response = Product.class)
    @RequestMapping(value = "/show/{id}", method= RequestMethod.GET, produces = "application/json")
    public  ResponseEntity<EntityModel<Product>> showProduct(@PathVariable Integer id){
        return productService.getProductById(id);
    }
    /*
     *add a new product to the database
     * @RequestBody-product object required
     * */
    @ApiOperation(value = "Add a product")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<EntityModel<Product>> saveProduct(@RequestBody Product product){
       return productService.saveProduct(product);
    }
    /*
     * allows you to update any desired information about the product
     *@RequestBody-product object required
     * */
    @ApiOperation(value = "Update a product")
    @RequestMapping(value = "/update/", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<EntityModel<Product>> updateProduct(@RequestBody Product product){
       return productService.updateProduct(product);
    }
    /*
    * Delete product from db
    * @PathVariable-product id
    * */
    @ApiOperation(value = "Delete a product")
    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        return productService.deleteProduct(id);
    }
    /*
     *get the  product price,using ProductDTO
     * @PathVariable product id
     * */
    @ApiOperation(value = "get product price")
    @RequestMapping(value="productPrice/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<EntityModel<ProductDTO>> getProductPrice(@PathVariable Integer id){
        return productService.getProductDTO(id);
    }
    /*
     *get all product in range of:
     * @RequestParam min, max
     * */
    @ApiOperation(value = "get all product in range")
    @RequestMapping(value="/getProducts", method = RequestMethod.GET)
    public ResponseEntity<CollectionModel<EntityModel<Product>>> getStores(@RequestParam(required=true)BigDecimal min, @RequestParam(required=true) BigDecimal max){
       return productService.getProductsInRange(min,max);
    }
    /*
     * allows you to update the product price
     *@RequestBody-product object required
     * */
    @ApiOperation(value = "update product price")
    @RequestMapping(value="/updatePrice", method = RequestMethod.PUT)
    public CompletableFuture<ResponseEntity<EntityModel<Product>>> updatePrice(@RequestBody Product product){
        return productService.updatePrice(product.getProductId(),product.getPrice());
    }
}
