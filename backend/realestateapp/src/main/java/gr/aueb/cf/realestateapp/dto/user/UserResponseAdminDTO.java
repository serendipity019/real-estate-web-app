package gr.aueb.cf.realestateapp.dto.user;

import gr.aueb.cf.realestateapp.core.enums.ContactHours;
import gr.aueb.cf.realestateapp.core.enums.RoleEnum;

import java.time.LocalDateTime;

public record UserResponseAdminDTO(
        String name,
        String surname,
        String email,
        String phone,
        ContactHours contactHours,
        Boolean isActive,
        RoleEnum role,
        LocalDateTime createdAt,
        boolean isAuthenticated
) {
}
