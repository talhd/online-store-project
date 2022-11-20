package online.store.project.repositories;
import online.store.project.domain.Store;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;

public class StoreRepositoryImpl {

    @Autowired
    private StoreRepository storeRepository;
    public List<Store> returnAllStoresInCategory(String category){
        return storeRepository.findAll().stream().filter((store -> (store.getCategory().equals(category)))).collect(Collectors.toList());
    }


}
