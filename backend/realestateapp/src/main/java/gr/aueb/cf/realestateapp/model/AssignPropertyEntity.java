package gr.aueb.cf.realestateapp.model;

import gr.aueb.cf.realestateapp.core.enums.AssignTypeEnum;
import jakarta.persistence.*;
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

    @Column(name = "assign_purpose", nullable = false)
    @Enumerated(EnumType.STRING)
    private AssignTypeEnum AssignPurpose;
}
