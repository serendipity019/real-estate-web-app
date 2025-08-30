package gr.aueb.cf.realestateapp.dto.request_property;

import gr.aueb.cf.realestateapp.core.enums.PropertyStatusEnum;

import java.time.LocalDateTime;

public record RequestPropertyAdminResponseDTO(
        String uuid,
        String region,
        String county,
        String area,
        String category,
        String type,
        String description,
        Integer priceFrom,
        Integer priceTo,
        Integer squareMetersFrom,
        Integer squareMetersTo,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        PropertyStatusEnum status,
        boolean isActive,
        String createdBy,
        String lastModifiedBy
) {
}
