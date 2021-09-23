package uz.kapitalbank.testing.service;

import org.springframework.http.ResponseEntity;
import uz.kapitalbank.testing.Payload.orderPayload.OrderRequest;
import uz.kapitalbank.testing.Payload.orderPayload.OrderResponse;

public interface OrderService {

    ResponseEntity makeOrder(OrderRequest orderRequest);

    OrderResponse findById(Long id);


}
