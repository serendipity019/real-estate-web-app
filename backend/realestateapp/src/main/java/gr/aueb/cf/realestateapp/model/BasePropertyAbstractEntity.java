package gr.aueb.cf.realestateapp.model;

import gr.aueb.cf.realestateapp.core.enums.PropertyStatusEnum;
import gr.aueb.cf.realestateapp.model.static_data.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BasePropertyAbstractEntity extends BaseAbstractEntity {

    @Column(unique = true)
    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id",nullable = false)
    private RegionEntity region;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "county_id", nullable = false)
    private CountyEntity county;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id", nullable = false)
    private AreaEntity area;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private PropertyCategoriesEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", nullable = false)
    private PropertyTypesEntity type;

    @Enumerated(EnumType.STRING)
    @Column(name = "real_estate_status", nullable = false)
    private PropertyStatusEnum realEstateStatus = PropertyStatusEnum.UNOBSERVED;

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

//    @Column(name = "is_active")
//    private boolean active;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @PrePersist
    public void initializeUUID() {
        if (uuid == null || uuid.trim().isEmpty()) uuid = UUID.randomUUID().toString();
    }
}
