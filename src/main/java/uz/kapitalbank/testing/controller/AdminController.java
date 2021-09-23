package uz.kapitalbank.testing.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.kapitalbank.testing.Payload.CustomerPayload;
import uz.kapitalbank.testing.model.Result;
import uz.kapitalbank.testing.service.impl.CustomerServiceImpl;

@RestController
@RequestMapping("/api/admin")

@RequiredArgsConstructor

public class AdminController {

    private final CustomerServiceImpl customerService;

    @PostMapping("/customer/add")
    public ResponseEntity addCustomer(@RequestBody CustomerPayload customerPayload) {
        return customerService.save(customerPayload);
    }




}
