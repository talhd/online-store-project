package online.store.project.repositories;
import online.store.project.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@RepositoryRestResource
public interface StoreRepository extends JpaRepository<Store,Integer> {
    List<Store> returnAllStoresInCategory(String category);

}
