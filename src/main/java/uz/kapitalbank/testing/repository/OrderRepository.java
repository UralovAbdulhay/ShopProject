package uz.kapitalbank.testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.kapitalbank.testing.entity.Order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByIdAndActive(Long id, boolean isActive);

    // orders_without_details
    @Query("select o from Order o left join Detail d where  d.id = null and o.createAt < :date1")
    List<Order> getAllOrdersWithoutDetailsBeforeAt(LocalDateTime date1);


}
