package uz.kapitalbank.testing.service;

import uz.kapitalbank.testing.Payload.PaymentResponse;
import uz.kapitalbank.testing.entity.Payment;

public interface PaymentService {

    PaymentResponse makePayment(Long invoiceId);

    Payment findById(Long id);

}
