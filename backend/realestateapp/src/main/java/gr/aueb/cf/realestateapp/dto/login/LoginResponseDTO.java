package gr.aueb.cf.realestateapp.dto.login;

import gr.aueb.cf.realestateapp.dto.user.UserResponseDTO;

import java.time.LocalDateTime;

public record LoginResponseDTO(
        String token,
        UserResponseDTO user,
        LocalDateTime expiresAt
) {
}
