package uz.kapitalbank.testing.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.kapitalbank.testing.entity.superEntity.SuperEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Invoice extends SuperEntity {

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private LocalDateTime issued;

    @Column(nullable = false)
    private LocalDateTime due;

    @OneToOne
//    @Column(nullable = false)
    private Order orders;


}
