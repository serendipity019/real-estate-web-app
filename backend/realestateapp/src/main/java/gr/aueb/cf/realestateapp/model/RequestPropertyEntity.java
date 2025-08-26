package gr.aueb.cf.realestateapp.model;

import gr.aueb.cf.realestateapp.core.enums.RequestTypeEnum;
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
@Table(name = "request_properties")
public class RequestPropertyEntity extends BasePropertyAbstractEntity{

    private Integer priceFrom;

    private Integer priceTo;

    private Integer squareMetersFrom;

    private Integer squareMetersTo;

    @Column(name = "request_purpose", nullable = false)
    @Enumerated(EnumType.STRING)
    private RequestTypeEnum requestPurpose;
}
