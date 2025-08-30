package gr.aueb.cf.realestateapp.dto.request_property;

import java.time.LocalDateTime;

public record RequestPropertyPublicResponseDTO(
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
        LocalDateTime updatedAt
) {
}
