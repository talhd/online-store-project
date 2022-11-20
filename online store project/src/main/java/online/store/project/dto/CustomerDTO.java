package online.store.project.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import online.store.project.domain.Customer;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
@JsonPropertyOrder({"customerName","productList"})
public class  CustomerDTO {
    @JsonIgnore
    Customer customer;
    public String getCustomerName(){
        return customer.getCustomerName();
    }
    public List<ProductDTO> getProductList(){
        return customer.getProductList().stream().map(ProductDTO::new).collect(Collectors.toList());
    }
}
