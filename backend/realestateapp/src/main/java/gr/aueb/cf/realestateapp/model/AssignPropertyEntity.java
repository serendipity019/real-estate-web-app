package gr.aueb.cf.realestateapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "assign_properties")
public class AssignPropertyEntity extends BasePropertyAbstractEntity{

    @Column(nullable = false)
    private Integer price;

    @Column(name = "square_meters", nullable = false)
    private Integer squareMeters;
}
