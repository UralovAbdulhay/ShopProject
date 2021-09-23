package uz.kapitalbank.testing.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.kapitalbank.testing.entity.superEntity.SuperEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Entity

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment extends SuperEntity {

    @Column(nullable = false)
    private double amount;

    @ManyToOne
//    @Column(nullable = false)
    private Invoice invoice;


}
