package uz.kapitalbank.testing.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.kapitalbank.testing.Payload.CategoryPayload;
import uz.kapitalbank.testing.entity.Category;
import uz.kapitalbank.testing.exceptions.ResourceNotFoundException;
import uz.kapitalbank.testing.model.Result;
import uz.kapitalbank.testing.repository.CategoryRepository;
import uz.kapitalbank.testing.repository.ProductRepository;
import uz.kapitalbank.testing.service.CategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    public List<Category> findAllByActive() {
        return categoryRepository.findAllByActive(true);
    }

    @Override
    public Category findByProductId(Long productId) {
        return productRepository.findCategoryByIdAndActive(productId, true)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found by product_id = " + productId));
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findByIdAndActive(id, true)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found by id = " + id));
    }

    @Override
    public ResponseEntity save(CategoryPayload payload) {

        if (!categoryRepository.existsByNameAndActive(payload.getName(), true)) {
            Category category = categoryRepository.save(new Category(payload.getName()));
            return category != null
                    ? ResponseEntity.ok(new Result(true, "Category successfully saved!", category))
                    : ResponseEntity.badRequest().body(new Result(false, "Category not created!"));
        } else {
            return ResponseEntity.badRequest().body(new Result(false, "This name already exist!"));
        }

    }

    @Override
    public ResponseEntity deleteById(Long id) {

        Category category = categoryRepository.findByIdAndActive(id, true)
                .orElseThrow(() -> new ResourceNotFoundException("Category by id = " + id + " not found"));
        category.setActive(false);
        categoryRepository.save(category);

        return categoryRepository.existsByIdAndActive(id, false)
                ? ResponseEntity.ok(new Result(true, "Category successfully deleted"))
                : ResponseEntity.badRequest().body(new Result(false, "Category not deleted"));

    }

    @Override
    public Category edit(Long id, CategoryPayload payload) {
        Category category = categoryRepository.findByIdAndActive(id, true)
                .orElseThrow(() -> new ResourceNotFoundException("Category by id = " + id + " not found"));
        category.setName(payload.getName());
        return categoryRepository.save(category);
    }


}

