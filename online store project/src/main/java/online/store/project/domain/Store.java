package online.store.project.domain;
import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import javax.persistence.*;
import java.util.List;
@JsonPropertyOrder({"storeId","storeName", "storeOwner","category"})
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="storeId")
public class Store {
    @ApiModelProperty(hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer storeId;
    private String storeName;
    private String storeOwner;
    private String category;
    @OneToMany(mappedBy = "store")
    @JsonManagedReference
    private List<Product> productsList;
    @OneToMany(mappedBy = "store")
    @JsonManagedReference
    private  List<Customer> customersList;

    public Store(String storeName, String storeOwner, String category) {
        this.storeName=storeName;
        this.storeOwner=storeOwner;
        this.category=category;
    }
}