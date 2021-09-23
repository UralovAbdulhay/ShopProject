package uz.kapitalbank.testing.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.kapitalbank.testing.Payload.CustomerPayload;
import uz.kapitalbank.testing.entity.Category;
import uz.kapitalbank.testing.entity.Customer;
import uz.kapitalbank.testing.exceptions.ResourceNotFoundException;
import uz.kapitalbank.testing.model.Result;
import uz.kapitalbank.testing.repository.CustomerRepository;
import uz.kapitalbank.testing.service.CustomerService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;


    @Override
    public List<Customer> findAll() {
        return customerRepository.findAllByActive(true);
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findByIdAndActive(id, true)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found by product_id = " + id));
    }

    @Override
    public ResponseEntity save(CustomerPayload customerPayload) {

        Customer customer = new Customer();
        customer.setName(customerPayload.getName());
        customer.setAddress(customerPayload.getAddress());
        customer.setCountry(customerPayload.getCountry());
        customer.setPhone(customerPayload.getPhone());

        Customer savedCustom = customerRepository.save(customer);

        return savedCustom != null
                ? ResponseEntity.ok(new Result(true, "Category successfully saved!", savedCustom))
                : ResponseEntity.badRequest().body(new Result(false, "Category not created!"));
    }

    @Override
    public Customer edit(Long id, CustomerPayload customerPayload) {
        return null;
    }

    @Override
    public ResponseEntity delete(Long id) {
        Customer customer = customerRepository.findByIdAndActive(id, true)
                .orElseThrow(() -> new ResourceNotFoundException("Customer by id = " + id + " not found"));
        customer.setActive(false);
        customerRepository.save(customer);

        return customerRepository.existsByIdAndActive(id, false)
                ? ResponseEntity.ok(new Result(true, "Customer successfully deleted"))
                : ResponseEntity.badRequest().body(new Result(false, "Customer not deleted"));
    }
}
