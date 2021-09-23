package uz.kapitalbank.testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.kapitalbank.testing.entity.Category;
import uz.kapitalbank.testing.entity.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Category> findCategoryByIdAndActive(Long id, boolean active);

    Optional<Product> findByIdAndActive(Long id, boolean b);

    boolean existsByIdAndActive(Long id, boolean b);
}
