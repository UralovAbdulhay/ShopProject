package uz.kapitalbank.testing.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.kapitalbank.testing.Payload.orderPayload.OrderRequest;
import uz.kapitalbank.testing.model.Result;
import uz.kapitalbank.testing.service.OrderService;

@RestController
@RequestMapping("/api/order")

@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @PostMapping
    public ResponseEntity makeOrder(@RequestBody OrderRequest request) {
       return orderService.makeOrder(request);
    }


    @GetMapping
    public ResponseEntity findByProductId(@RequestParam(name = "order_id") Long id) {
        return ResponseEntity.ok(new Result(true, orderService.findById(id)));
    }


}
