package uz.kapitalbank.testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.kapitalbank.testing.entity.Invoice;

import java.util.List;


public interface InvoiceRepository extends JpaRepository<Invoice, Long> {


    //expired_invoices
    /*
     * select * from Invoice  where Invoice.due < Invoice.issued
     * */
    @Query("Select i from Invoice i where i.due < i.issued")
    List<Invoice> findAllExpiredInvoices();


    // error_date_invoices
    /*
     *SELECT i from Invoice i inner join i.orders o on i.id = o.invoice_id where i.create_at <= o.create_at
     * */
    @Query("select i from Invoice i inner join i.orders o where i.createAt <= o.createAt")
    List<Invoice> getErrorDateInvoices();


    // overpaid_invoices
    /*
     *select i.id, (sum(p.amount) - i.amount) as residue from Invoice i
     *   inner join Payment p on i.id = p.invoice_id where residue > 0 and i.id = :id
     * */


    @Query("Select i.id,   (sum(p.amount) - i.amount) as residue from Invoice i " +
            "inner join Payment p  having residue > 0 and i.id = :id")
    List<Object[]> getOverpaidInvoices(Long id);





    // orders_without_invoices
    /*
    *
        SELECT Orders.OrderID, Orders.OrderDate, Products.Price * OrderDetails.Quantity as Amount,
        OrderDetails.Quantity as Quantity, Products.Price as Price
        FROM Orders
        left join OrderDetails on OrderDetails.OrderID = Orders.OrderID
        join Products on OrderDetails.ProductID = Products.ProductID
        where OrderDetails.OrderDetailID not null
        ;
    *
    *
    * */


}
