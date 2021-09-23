package uz.kapitalbank.testing.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.kapitalbank.testing.entity.Category;
import uz.kapitalbank.testing.entity.MyFile;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPayload {


    private String name;

    private double price;

    private String description;

    private String photoHashId;

    private Long categoryId;
    
}
