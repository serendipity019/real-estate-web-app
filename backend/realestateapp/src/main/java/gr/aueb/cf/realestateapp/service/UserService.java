package gr.aueb.cf.realestateapp.service;

import gr.aueb.cf.realestateapp.core.enums.RoleEnum;
import gr.aueb.cf.realestateapp.core.exceptions.AppObjectAlreadyExists;
import gr.aueb.cf.realestateapp.dto.user.UserAdminUpdateDTO;
import gr.aueb.cf.realestateapp.dto.user.UserInsertDTO;
import gr.aueb.cf.realestateapp.dto.user.UserResponseAdminDTO;

import java.util.List;

public interface UserService {
    UserResponseAdminDTO createUser(UserInsertDTO userInsertDTO) throws AppObjectAlreadyExists;
    UserResponseAdminDTO updateUser(Long id, UserAdminUpdateDTO updateDTO, String currentUserEmail);
    void deleteUser(Long id); // actually deactivate
    List<UserResponseAdminDTO> getAllUsers();
    UserResponseAdminDTO getUserByEmail(String email);
    UserResponseAdminDTO getUserById(Long id);
    List<UserResponseAdminDTO> getUsersByRole(RoleEnum role);
    boolean userExist(String email);
    boolean userIsAdminOrAgent(Long id);
    boolean userIsAdmin(Long id);
}
