package uz.kapitalbank.testing.service;

import uz.kapitalbank.testing.entity.Invoice;

import java.util.List;

public interface InvoiceService {


    Invoice save(Invoice invoice);

    List<Invoice> findAllExpiredInvoices();
}
