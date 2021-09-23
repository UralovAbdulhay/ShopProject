package uz.kapitalbank.testing.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.kapitalbank.testing.Payload.PaymentResponse;
import uz.kapitalbank.testing.entity.Invoice;
import uz.kapitalbank.testing.entity.Payment;
import uz.kapitalbank.testing.exceptions.ResourceNotFoundException;
import uz.kapitalbank.testing.repository.InvoiceRepository;
import uz.kapitalbank.testing.repository.PaymentRepository;
import uz.kapitalbank.testing.service.PaymentService;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;

    @Override
    public PaymentResponse makePayment(Long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice with id = " + invoiceId + " not found"));

        Payment payment = paymentRepository.save(new Payment(invoice.getAmount(), invoice));

        return new PaymentResponse(payment != null ? "SUCCESS" : "FAILED", payment);
    }

    @Override
    public Payment findById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment with id = " + id + " not found"));

    }
}
