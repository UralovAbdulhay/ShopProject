package uz.kapitalbank.testing.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.kapitalbank.testing.Payload.ProductPayload;
import uz.kapitalbank.testing.entity.Product;
import uz.kapitalbank.testing.exceptions.ResourceNotFoundException;
import uz.kapitalbank.testing.model.Result;
import uz.kapitalbank.testing.repository.ProductRepository;
import uz.kapitalbank.testing.service.ProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final MyFileServiceImpl myFileService;
    private final CategoryServiceImpl categoryService;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }


    @Override
    public ResponseEntity save(ProductPayload productPayload) {

        Product product = productRepository.save(new Product(
                        productPayload.getName(),
                        productPayload.getPrice(),
                        productPayload.getDescription(),
                        productPayload.getPhotoHashId(),
                        categoryService.findById(productPayload.getCategoryId())
                )
        );

        return product != null
                ? ResponseEntity.ok(new Result(true, "Product successfully saved!", product))
                : ResponseEntity.badRequest().body(new Result(false, "Product not created!"));
    }

    @Override
    public ResponseEntity edit(Long id, ProductPayload productPayload) {
        Product product = productRepository.findByIdAndActive(id, true)
                .orElseThrow(() -> new ResourceNotFoundException("Product by id = " + id + " not found"));

        product.setName(productPayload.getName());
        product.setPrice(productPayload.getPrice());
        product.setDescription(productPayload.getDescription());
        product.setCategory(categoryService.findById(productPayload.getCategoryId()));
        product.setPhotoHashId(productPayload.getPhotoHashId());
        Product result = productRepository.save(product);

        return result != null
                ? ResponseEntity.ok(new Result(true, "Product successfully edited!", product))
                : ResponseEntity.badRequest().body(new Result(false, "Product not edited!"));

    }


    @Override
    public ResponseEntity delete(Long id) {
        Product product = productRepository.findByIdAndActive(id, true)
                .orElseThrow(() -> new ResourceNotFoundException("Product by id = " + id + " not found"));
        product.setActive(false);
        productRepository.save(product);

        return productRepository.existsByIdAndActive(id, false)
                ? ResponseEntity.ok(new Result(true, "Product successfully deleted"))
                : ResponseEntity.badRequest().body(new Result(false, "Product not deleted"));
    }

}
