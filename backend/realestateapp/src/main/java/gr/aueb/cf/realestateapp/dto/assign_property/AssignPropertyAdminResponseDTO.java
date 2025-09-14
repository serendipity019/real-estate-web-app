package gr.aueb.cf.realestateapp.dto.assign_property;

import gr.aueb.cf.realestateapp.core.enums.RealEstateStatusEnum;
import gr.aueb.cf.realestateapp.core.enums.RealEstateStatusEnum;

import java.time.LocalDateTime;

public record AssignPropertyAdminResponseDTO(
        String uuid,
        String region,
        String county,
        String area,
        String category,
        String type,
        RealEstateStatusEnum status,
        String description,
        Integer price,
        Integer squareMeters,
        String street,
        String streetNumber,
        Long postCode,
        String createdBy,
        String lastModifiedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
