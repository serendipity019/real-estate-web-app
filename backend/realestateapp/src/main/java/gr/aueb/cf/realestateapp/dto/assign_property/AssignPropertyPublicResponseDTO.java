package gr.aueb.cf.realestateapp.dto.assign_property;

import gr.aueb.cf.realestateapp.core.enums.AssignTypeEnum;

import java.time.LocalDateTime;

public record AssignPropertyPublicResponseDTO(
        String uuid,
        String region,
        String county,
        String area,
        String category,
        String type,
        AssignTypeEnum assignTypeEnum,
        String description,
        Integer price,
        Integer squareMeters,
        String street,
        String streetNumber,
        Long postCode,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
