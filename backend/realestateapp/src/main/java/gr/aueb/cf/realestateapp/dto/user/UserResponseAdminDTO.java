package gr.aueb.cf.realestateapp.dto.user;

import gr.aueb.cf.realestateapp.core.enums.ContactHours;
import gr.aueb.cf.realestateapp.core.enums.RoleEnum;

public record UserResponseAdminDTO(
        String name,
        String surname,
        String email,
        String phone,
        ContactHours contactHours,
        RoleEnum role

) {
}
