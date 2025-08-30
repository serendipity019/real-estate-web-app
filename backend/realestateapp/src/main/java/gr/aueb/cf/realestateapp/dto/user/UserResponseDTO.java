package gr.aueb.cf.realestateapp.dto.user;

import gr.aueb.cf.realestateapp.core.enums.ContactHours;

public record UserResponseDTO(
        String name,
        String surname, // I want this appearing only if someone is logged in.
        String email, // I want this appearing only if someone is logged in.
        String phone, // I want this appearing only if someone is logged in.
        ContactHours contactHours,
        boolean isAuthenticated
) {
    // I use this method to appear sensitive data only to authenticate users.
    public UserResponseDTO {
        if (!isAuthenticated) {
            surname = null;
            email = null;
            phone = null;
        }
    }
}
