package uz.kapitalbank.testing.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Product extends SuperEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    private String description;

    private String photoHashId;

    @ManyToOne
    @JsonIgnore
    private Category category;

    
    public Long getCategory() {
        return category.getId();
    }


}
