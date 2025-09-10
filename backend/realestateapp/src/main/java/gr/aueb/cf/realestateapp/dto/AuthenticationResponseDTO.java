package gr.aueb.cf.realestateapp.dto;

import lombok.Builder;

@Builder
public record AuthenticationResponseDTO(
        String firstname,
        String lastname,
        String token
) {
}
