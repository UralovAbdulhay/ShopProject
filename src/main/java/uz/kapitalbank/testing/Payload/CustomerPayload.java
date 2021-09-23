package uz.kapitalbank.testing.Payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPayload {

    private String name;
    private String country;
    private String address;
    private String phone;

}
