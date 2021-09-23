package uz.kapitalbank.testing.Payload.orderPayload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private Long customerId;
    private Long productId;
    private int quantity;


}
