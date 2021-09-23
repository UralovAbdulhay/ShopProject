package uz.kapitalbank.testing.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.kapitalbank.testing.entity.Payment;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {

    private String paymentStatus;
    private Payment paymentDetails;

}
