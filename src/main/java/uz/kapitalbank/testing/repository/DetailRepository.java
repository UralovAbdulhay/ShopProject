package uz.kapitalbank.testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.kapitalbank.testing.entity.Customer;
import uz.kapitalbank.testing.entity.Detail;
import uz.kapitalbank.testing.entity.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DetailRepository extends JpaRepository<Detail, Long> {

    Optional<Detail> findByOrdersId(Long id);


}
