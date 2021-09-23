package uz.kapitalbank.testing.service;

import org.springframework.http.ResponseEntity;
import uz.kapitalbank.testing.Payload.CustomerPayload;
import uz.kapitalbank.testing.Payload.ProductPayload;
import uz.kapitalbank.testing.entity.Category;
import uz.kapitalbank.testing.entity.Customer;
import uz.kapitalbank.testing.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(Long id);

    ResponseEntity save(ProductPayload productPayload);

    ResponseEntity edit(Long id, ProductPayload productPayload);

    ResponseEntity delete(Long id);
}
