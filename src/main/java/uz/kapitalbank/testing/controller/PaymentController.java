package uz.kapitalbank.testing.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.kapitalbank.testing.model.Result;
import uz.kapitalbank.testing.service.impl.PaymentServiceImpl;

@RestController
@RequestMapping("/api/payment")

@RequiredArgsConstructor
public class PaymentController {

    private final PaymentServiceImpl paymentService;

    @GetMapping("")
    public ResponseEntity getById(@RequestParam("payment_id") Long id) {
        return ResponseEntity.ok(new Result(true, paymentService.findById(id)));
    }


    @PostMapping("/add")
    public ResponseEntity makePayment(@RequestBody Long invoiceId ) {
        return ResponseEntity.ok(new Result(true, paymentService.makePayment(invoiceId)));
    }

}
