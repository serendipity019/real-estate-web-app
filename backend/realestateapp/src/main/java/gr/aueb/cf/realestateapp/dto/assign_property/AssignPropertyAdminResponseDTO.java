package gr.aueb.cf.realestateapp.dto.assign_property;

import gr.aueb.cf.realestateapp.core.enums.PropertyStatusEnum;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

public record AssignPropertyAdminResponseDTO(
        String uuid,
        String region,
        String county,
        String area,
        String category,
        String type,
        PropertyStatusEnum status,
        String description,
        Integer price,
        Integer squareMeters,
        String street,
        String streetNumber,
        String postCode,
        String createdBy,
        String lastModifiedBy,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
