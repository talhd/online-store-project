package online.store.project.domain;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
@ToString
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"productId","description", "price","catalogNumber","version","imageUrl","store"})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productId;
    @Version
    private Integer version;
    private String catalogNumber;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    @ManyToOne
    @JsonBackReference
    private Store store;
    @ManyToMany(mappedBy = "productList")
    private List<Customer> customerList;

    public Product(String description,BigDecimal price,Integer version,Store store){
        this.description=description;
        this.price=price;
        this.version=version;
        this.store=store;
    }
}
