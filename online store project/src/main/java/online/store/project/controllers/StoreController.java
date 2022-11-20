package online.store.project.controllers;
import online.store.project.domain.Store;
import online.store.project.services.StoreService;
import online.store.project.services.StoreServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/store")
@Api(value="onlinestore", description="Operations pertaining to products in Online Store")
public class StoreController {
    private static final Logger logger = LoggerFactory.getLogger(StoreServiceImpl.class.getSimpleName());
    private StoreService storeService;
    @Autowired
    public void setProductService(StoreService storeService) {
        this.storeService = storeService;
    }

    /*
    * add a new store to db
    * @RequestBody-store object required
    * */
    @ApiOperation(value = "Add a store")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<EntityModel<Store>> saveStore(@RequestBody Store store){
        return storeService.saveStore(store);
    }
    /*
     * remove store to db
     * @PathVariable store id
     * */
    @ApiOperation(value = "remove store")
    @RequestMapping(value = "/removeStore/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<String> removeStore(@PathVariable Integer id){
        return storeService.removeStore(id);
    }
    /*
     * get all the stores  from db
     * */
    @ApiOperation(value = "get all the stores")
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<CollectionModel<EntityModel<Store>>> getAllStores(){
        return storeService.listOfAllStores();
    }
    /*
     * return specific store, according to his representation in the database.
     * @PathVariable-the store id
     * */
    @ApiOperation(value = "get store")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<EntityModel<Store>> getStore(@PathVariable Integer id){
        return storeService.getStore(id);
    }
    /*
     * allows you to update any desired information about the store
     *@RequestBody-store object required
     * */
    @ApiOperation(value = "update store")
    @RequestMapping(value = "/updateStore/", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<EntityModel<Store>> getStore(@RequestBody Store store){
       return storeService.updateStore(store);
    }
    /*
     * get all the stores in a category
     *@RequestParam category-the desired category
     * */
    @ApiOperation(value = "get all the store in category")
    @RequestMapping(value="/getStore", method = RequestMethod.GET)
    public ResponseEntity<CollectionModel<EntityModel<Store>>> getStores(@RequestParam(required=true) String category){
       return storeService.getAllStoreInCategory(category);
    }


}
