package uz.kapitalbank.testing.Payload.orderPayload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.kapitalbank.testing.entity.Detail;
import uz.kapitalbank.testing.entity.Order;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class OrderResponse {

    private String customerName;
    private long customerId;
    private String productName;
    private long productId;
    private int quantity;
    private double productPrice;
    private LocalDateTime orderDate;

    public static OrderResponse getInstance(Order order, Detail detail) {
        return new OrderResponse(
                order.getCustomer().getName(),
                order.getCustomer().getId(),
                detail.getProduct().getName(),
                detail.getProduct().getId(),
                detail.getQuantity(),
                detail.getProduct().getPrice(),
                order.getCreateAt()
        );
    }

}
