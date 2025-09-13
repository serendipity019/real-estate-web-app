package gr.aueb.cf.realestateapp.service;

import gr.aueb.cf.realestateapp.core.enums.RoleEnum;
import gr.aueb.cf.realestateapp.core.exceptions.AppObjectAlreadyExists;
import gr.aueb.cf.realestateapp.dto.user.UserAdminUpdateDTO;
import gr.aueb.cf.realestateapp.dto.user.UserInsertDTO;
import gr.aueb.cf.realestateapp.dto.user.UserResponseAdminDTO;
import gr.aueb.cf.realestateapp.mapper.UserMapper;
import gr.aueb.cf.realestateapp.model.UserEntity;
import gr.aueb.cf.realestateapp.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseAdminDTO createUser(UserInsertDTO userInsertDTO) throws AppObjectAlreadyExists {
        if (userExist(userInsertDTO.email())){
            throw new AppObjectAlreadyExists("USER_ALREADY_EXISTS", "User with email: " + userInsertDTO.email() + " already exists." );
        }

        UserEntity user = new UserEntity();
        userMapper.mapInsertDTOToEntity(userInsertDTO, user);

        UserEntity savedUser = userRepository.save(user);
        return userMapper.mapEntityToAdminResponseDTO(savedUser);
    }

    @Override
    public UserResponseAdminDTO updateUser(Long id, UserAdminUpdateDTO updateDTO, String currentUserEmail) {
        UserEntity user = getUserEntityById(id);

        checkUpdateAuthorization(user, currentUserEmail, updateDTO); // Checking if the current user is authorized to update this user
        userMapper.mapUpdateDTOToEntity(updateDTO, user);

        if(user.getEmail().equals(currentUserEmail)) {
            if (updateDTO.password() != null && !updateDTO.password().isBlank()) {
                user.setPassword(updateDTO.password());
            }
        }

        UserEntity updatedUser = userRepository.save(user);
        return userMapper.mapEntityToAdminResponseDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity user = getUserEntityById(id);
        user.setActive(false); // I want to have the user in my Data for history or statistic purposes.
        userRepository.save(user);
    }

    @Override
    public List<UserResponseAdminDTO> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new EntityNotFoundException("No users found.");
        }

        return users.stream()
                .map(userMapper::mapEntityToAdminResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseAdminDTO getUserByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email: " + email + " not found."));

        return userMapper.mapEntityToAdminResponseDTO(user);
    }

    private UserEntity getUserEntityByEmail(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email: " + email + " not found."));
    }

    @Override
    public UserResponseAdminDTO getUserById(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID: " + id + " not found."));

        return userMapper.mapEntityToAdminResponseDTO(user);
    }

    private  UserEntity getUserEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID: " + id + " not found."));
    }


    @Override
    public List<UserResponseAdminDTO> getUsersByRole(RoleEnum role) {
        List<UserEntity> users = userRepository.findByRole(role);

        if (users.isEmpty()) {
            throw new EntityNotFoundException("User with Role: " + role + " not found.");
        }

        return users.stream()
                .map( userMapper::mapEntityToAdminResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean userExist(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean userIsAdminOrAgent(Long id) {
        UserEntity user = getUserEntityById(id);
        return (user.getRole() == RoleEnum.ADMIN || user.getRole() == RoleEnum.AGENT);
    }

    @Override
    public boolean userIsAdmin(Long id) {
        UserEntity user = getUserEntityById(id);
        return user.getRole() == RoleEnum.ADMIN;
    }

    private String getCurrentUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private void checkUpdateAuthorization(UserEntity targetUser, String currentUserEmail, UserAdminUpdateDTO updateDTO) {
        UserEntity currentUser =  getUserEntityByEmail(currentUserEmail);

        if(currentUser.getRole() == RoleEnum.USER && !currentUser.getId().equals(targetUser.getId())) {
            throw new AccessDeniedException("You can only update your own profile");
        }

        if (!userIsAdmin(currentUser.getId())) {
            if (updateDTO.role() != null && !updateDTO.role().equals(targetUser.getRole())) {
                throw new AccessDeniedException("Only administrators can change user roles.");
            }

            if (updateDTO.isActive() != null && !updateDTO.isActive().equals(targetUser.isActive())) {
                throw new AccessDeniedException("Only administrators can change user active status.");
            }
        }
    }
}
