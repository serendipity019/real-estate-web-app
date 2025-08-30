package gr.aueb.cf.realestateapp.dto.user;

import gr.aueb.cf.realestateapp.core.enums.ContactHours;
import jakarta.validation.constraints.*;

public record UserInsertDTO(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Surname is required")
        String surname,

        @Email(message = "Invalid Email")
        String email,

        @NotBlank(message = "Phone number is required")
        @Pattern(regexp = "^(?:\\d{3})?\\d{10}$", message = "Phone number must be 10 digits or 13 digits with a 3-digit country code.")
        String phone,

        @NotNull
        ContactHours contactHours,

        @Size(min = 6, message = "Password must be at least 6 characters")
        String password
) {

}
