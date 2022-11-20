package online.store.project.domain;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.*;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="customerId")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer customerId;
    private String customerName;
    @ManyToMany
    private List<Product> productList;
    @ManyToOne
    @JsonBackReference
    private Store store;
    public Customer(String customerName,List<Product> productList,Store store){
        this.customerName=customerName;
        this.productList=productList;
        this.store=store;
    }
}
