package gr.aueb.cf.realestateapp.dto.user;

import gr.aueb.cf.realestateapp.core.enums.ContactHours;
import gr.aueb.cf.realestateapp.core.enums.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

// I will use this as update dto for all but must have the functions in the comments
public record UserAdminUpdateDTO(
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

        @NotNull
        RoleEnum role, // Todo: in service only the admin can change this

        @NotNull  // Todo: And this like the above.
        Boolean isActive,

        String password // only the user of this password can see this.
) {
    public UserAdminUpdateDTO {
        if (password != null && password.length() < 6 ) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }
    }
}


