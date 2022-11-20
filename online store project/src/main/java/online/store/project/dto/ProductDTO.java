package online.store.project.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import online.store.project.domain.Product;
import lombok.Value;

import java.math.BigDecimal;

@Value
@JsonPropertyOrder({"id","description", "price"})
public class ProductDTO {
    @JsonIgnore
    Product product;
    public Integer getId(){
        return product.getProductId();
    }
    public String getDescription(){
        return product.getDescription();
    }
    public BigDecimal getPrice(){
        return product.getPrice();
    }

}
