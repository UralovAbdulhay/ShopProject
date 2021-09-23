package uz.kapitalbank.testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.kapitalbank.testing.entity.Customer;
import uz.kapitalbank.testing.entity.Payment;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
