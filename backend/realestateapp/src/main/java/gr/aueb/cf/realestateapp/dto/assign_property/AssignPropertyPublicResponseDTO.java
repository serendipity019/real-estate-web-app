package gr.aueb.cf.realestateapp.dto.assign_property;

import java.time.LocalDateTime;

public record AssignPropertyPublicResponseDTO(
        String uuid,
        String region,
        String county,
        String area,
        String category,
        String type,
        String description,
        Integer price,
        Integer squareMeters,
        LocalDateTime updatedAt
) {
}
