package uz.kapitalbank.testing.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.kapitalbank.testing.Payload.orderPayload.OrderRequest;
import uz.kapitalbank.testing.Payload.orderPayload.OrderResponse;
import uz.kapitalbank.testing.entity.Detail;
import uz.kapitalbank.testing.entity.Invoice;
import uz.kapitalbank.testing.entity.Order;
import uz.kapitalbank.testing.exceptions.ResourceNotFoundException;
import uz.kapitalbank.testing.repository.OrderRepository;
import uz.kapitalbank.testing.service.OrderService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CustomerServiceImpl customerService;
    private final ProductServiceImpl productService;
    private final DetailServiceImpl detailService;
    private final InvoiceServiceImpl invoiceService;

    private final OrderRepository orderRepository;


    @Override
    public ResponseEntity makeOrder(OrderRequest request) {

        Long customerId = request.getCustomerId();
        Long productId = request.getProductId();
        int quantity = request.getQuantity();

        Order order = orderRepository.save(new Order(customerService.findById(customerId)));

        Detail detail = detailService.save(new Detail(quantity, order, productService.findById(productId)));

        Invoice invoice = invoiceService.save(new Invoice(detail.getAmount(), LocalDateTime.now(), LocalDateTime.now().plusDays(7), order));

        Map<Object, Object> result = new HashMap<>();

        if (invoice != null) {
            result.put("status", "SUCCESS");
            result.put("invoiceNumber", invoice.getId());
        } else {
            result.put("status", "FAILED");
            result.put("invoiceNumber", null);
        }

        return ResponseEntity.ok(result);
    }

    @Override
    public OrderResponse findById(Long id) {
        Order order = orderRepository.findByIdAndActive(id, true)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found by id = " + id));
        Detail detail = detailService.findByOrderId(order.getId());
        return OrderResponse.getInstance(order, detail);
    }
}
