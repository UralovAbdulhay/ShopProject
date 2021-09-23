package uz.kapitalbank.testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.kapitalbank.testing.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByActive(boolean active);

    Optional<Category> findByIdAndActive(Long id, boolean isActive);

    boolean existsByIdAndActive(Long id, boolean isActive);

    boolean existsByNameAndActive(String name, boolean isActive);



}
