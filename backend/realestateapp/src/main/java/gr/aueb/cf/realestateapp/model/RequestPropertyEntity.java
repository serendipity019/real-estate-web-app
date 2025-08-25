package gr.aueb.cf.realestateapp.model;

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
@Table(name = "request_properties")
public class RequestPropertyEntity extends BasePropertyAbstractEntity{

    private Integer priceFrom;

    private Integer priceTo;

    private Integer squareMetersFrom;

    private Integer squareMetersTo;
}
