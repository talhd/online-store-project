package online.store.project;
import online.store.project.domain.Customer;
import online.store.project.domain.Product;
import online.store.project.domain.Store;
import online.store.project.repositories.CustomersRepository;
import online.store.project.repositories.ProductRepository;
import online.store.project.repositories.StoreRepository;
import online.store.project.services.CustomersService;
import online.store.project.services.ProductService;
import online.store.project.services.StoreService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
class SeedDB {
    @Bean
    CommandLineRunner initDatabase(StoreRepository storeRepository, CustomersRepository customersRepository,ProductRepository productRepository) {
        return args -> {
            Store store= storeRepository.save(new Store("Linux","Linus Torvalds","software"));


            Product productUbuntu=productRepository.save(new Product("Ubuntu", new BigDecimal(0),22,store));
            Product productRedhat=productRepository.save(new Product("Red Hat",new BigDecimal(349),9,store));
            Product productArch=productRepository.save(new Product("Arch Linux",new BigDecimal(0),20220601,store));

            List<Product> list=new ArrayList<>();
            list.add(productUbuntu);
            list.add(productRedhat);
            list.add(productArch);
            customersRepository.save(new Customer("Steve Jobs",list,store));

        };
    }
}
