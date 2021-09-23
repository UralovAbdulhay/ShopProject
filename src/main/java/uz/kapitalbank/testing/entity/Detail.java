package uz.kapitalbank.testing.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.kapitalbank.testing.entity.superEntity.SuperEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Entity

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Detail extends SuperEntity {


    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.EAGER)
//    @Column(nullable = false)
    private Order orders;

    @ManyToOne(fetch = FetchType.EAGER)
//    @Column(nullable = false)
    private Product product;

    public double getAmount() {
        return product.getPrice() * quantity;
    }

}
