package gr.aueb.cf.realestateapp.mapper;

import gr.aueb.cf.realestateapp.dto.user.UserAdminUpdateDTO;
import gr.aueb.cf.realestateapp.dto.user.UserInsertDTO;
import gr.aueb.cf.realestateapp.dto.user.UserResponseAdminDTO;
import gr.aueb.cf.realestateapp.dto.user.UserResponseDTO;
import gr.aueb.cf.realestateapp.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public void mapInsertDTOToEntity(UserInsertDTO dto, UserEntity user) {
        user.setName(dto.name());
        user.setSurname(dto.surname());
        user.setEmail(dto.email());
        user.setPhone(dto.phone());
        user.setContactHours(dto.contactHours());
        user.setPassword(passwordEncoder.encode(dto.password()));
    }

    public void mapUpdateDTOToEntity(UserAdminUpdateDTO dto, UserEntity user) {
        user.setName(dto.name());
        user.setSurname(dto.surname());
        user.setEmail(dto.email());
        user.setPhone(dto.phone());
        user.setContactHours(dto.contactHours());
        user.setRole(dto.role());
        user.setActive(dto.isActive());
        user.setPassword(passwordEncoder.encode(dto.password())); // To change the password must have a special procedure
    }

    public UserResponseAdminDTO mapEntityToAdminResponseDTO(UserEntity user) {
        return new UserResponseAdminDTO(
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getPhone(),
                user.getContactHours(),
                user.isActive(),
                user.getRole(),
                user.getCreatedAt(),
                user.isAuthenticated()
        );
    }

    public UserResponseDTO mapEntityToUserResponseDTO(UserEntity user) {
        return new UserResponseDTO(
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getPhone(),
                user.getContactHours(),
                user.isAuthenticated() // This is only for functionality not to show to the user.
        );
    }
}
