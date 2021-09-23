package uz.kapitalbank.testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.kapitalbank.testing.entity.Customer;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAllByActive(boolean active);

    Optional<Customer> findByIdAndActive(Long id, boolean isActive);

    boolean existsByIdAndActive(Long id, boolean isActive);


    // customers_without_orders
    /*
        simple sql query

        SELECT c from Customer c left join Orders o on c.id = o.id where o.craeteAt like '%2016%' and o.id isnull;
    */

    @Query("select c from Customer c left join Orders o where o.craeteAt like %:date1% and o.id = null  ")
    List<Customer> getAllCustomerWithoutOrders(LocalDate date1);



    // task 9  //number_of_products_in_year
    /*
    * SELECT count(Orders.OrderId), Customers.Country, Orders.OrderDate from Customers
    * inner join Orders on Orders.CustomerID = Customers.CustomerID
    * where Orders.OrderDate like '%1997%' group by Customers.Country
    *
    * */





}
