package uz.kapitalbank.testing.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.kapitalbank.testing.entity.Invoice;
import uz.kapitalbank.testing.repository.InvoiceRepository;
import uz.kapitalbank.testing.service.InvoiceService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Override
    public Invoice save(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public List<Invoice> findAllExpiredInvoices() {
        return invoiceRepository.findAllExpiredInvoices();
    }
}
