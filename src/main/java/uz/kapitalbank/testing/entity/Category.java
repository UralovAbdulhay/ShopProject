package uz.kapitalbank.testing.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.kapitalbank.testing.entity.superEntity.SuperEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@EqualsAndHashCode(callSuper = true)
@Entity

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category extends SuperEntity {


    @Column(nullable = false)
    private String name;

}
