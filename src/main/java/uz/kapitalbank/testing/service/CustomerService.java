package uz.kapitalbank.testing.service;

import org.springframework.http.ResponseEntity;
import uz.kapitalbank.testing.Payload.CustomerPayload;
import uz.kapitalbank.testing.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {


    public List<Customer> findAll();

    Customer findById(Long id);

    ResponseEntity save(CustomerPayload customerPayload);

    Customer edit(Long id, CustomerPayload customerPayload);

    ResponseEntity delete(Long id);


}
