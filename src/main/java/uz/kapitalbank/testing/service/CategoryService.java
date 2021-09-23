package uz.kapitalbank.testing.service;

import org.springframework.http.ResponseEntity;
import uz.kapitalbank.testing.Payload.CategoryPayload;
import uz.kapitalbank.testing.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAllByActive();

    Category findByProductId(Long productId);

    Category findById(Long id);

    ResponseEntity save(CategoryPayload category);

    ResponseEntity deleteById(Long id);

    Category edit(Long id, CategoryPayload payload);

}
