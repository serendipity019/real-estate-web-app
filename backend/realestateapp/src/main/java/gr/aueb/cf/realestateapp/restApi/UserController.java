package gr.aueb.cf.realestateapp.restApi;

import gr.aueb.cf.realestateapp.core.enums.RoleEnum;
import gr.aueb.cf.realestateapp.core.exceptions.AppObjectAlreadyExists;
import gr.aueb.cf.realestateapp.dto.user.UserAdminUpdateDTO;
import gr.aueb.cf.realestateapp.dto.user.UserInsertDTO;
import gr.aueb.cf.realestateapp.dto.user.UserResponseAdminDTO;
import gr.aueb.cf.realestateapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "APIs for managing users in the real estate application")
public class UserController {
    private final UserService userService;

    // Create User
    @Operation(
            summary = "Create a new user",
            description = "Creates a new user with the provided details. Email must be unique."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description =  "User created successfully",
                    content = @Content(schema = @Schema(implementation = UserResponseAdminDTO.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "User with this email already exists",
                    content = @Content
            )
    })
    @PostMapping
    public ResponseEntity<UserResponseAdminDTO> createUser(
            @Parameter(description = "User creation data", required = true)
            @Valid @RequestBody UserInsertDTO userInsertDTO) throws AppObjectAlreadyExists {
        UserResponseAdminDTO createdUser = userService.createUser(userInsertDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    // Update User
    @Operation(
            summary = "Update user",
            description = "Updates an existing user's information. Users can only update their own profile unless they are administrators."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User updated successfully",
                    content = @Content( schema = @Schema(implementation = UserResponseAdminDTO.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Insufficient permissions to update this user",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseAdminDTO> updateUser(
            @Parameter(description = "User ID", required = true, example = "1")
            @PathVariable Long id,
            @Parameter(description = "User update data", required = true)
            @Valid @RequestBody UserAdminUpdateDTO updateDTO, Authentication authentication) {
        String currentUserEmail = authentication.getName();
        UserResponseAdminDTO updatedUser = userService.updateUser(id, updateDTO, currentUserEmail);
        return ResponseEntity.ok(updatedUser);
    }

    // Delete User
    @Operation(
            summary = "Delete User",
            description = "Deactivates a user account. The user record is preserved for historical purposes but marked as inactive."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User deactivated successfully",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "User ID", required = true, example = "1")
            @PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    //Get All Users
    @Operation(
            summary = "Get all users",
            description = "Retrieves a list of all users in the DB. Requires authentication."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Users retrieved successfully",
                    content = @Content(schema = @Schema(implementation = UserResponseAdminDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No users found",
                    content = @Content
            )
    })
    @GetMapping
    public ResponseEntity<List<UserResponseAdminDTO>> getAllUsers() {
        List<UserResponseAdminDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Get User by Email
    @Operation(
            summary = "Get user by email",
            description = "Return a specific user by their email address."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User found",
                    content = @Content(schema = @Schema(implementation = UserResponseAdminDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content
            )
    })
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseAdminDTO> getUserByEmail(
            @Parameter(description = "User email", required = true, example = "user@example.com")
            @PathVariable String email) {
        UserResponseAdminDTO user =userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    // Get User By ID
    @Operation(
            summary = "Get user by ID",
            description = "Return a specific user by their Id."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User found",
                    content = @Content(schema = @Schema(implementation = UserResponseAdminDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found",
                    content = @Content
            )
    })
    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponseAdminDTO> getUserById(
            @Parameter(description = "User ID", required = true, example = "1")
            @PathVariable Long id) {
        UserResponseAdminDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // Get user by Role
    @Operation(
            summary = "Get users by Role",
            description = "Return all the users that have a specific role."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Users found",
                    content = @Content(schema = @Schema(implementation = UserResponseAdminDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Users not found with this role",
                    content = @Content
            )
    })
    @GetMapping("/role/{role}")
    public ResponseEntity<List<UserResponseAdminDTO>> getUsersByRole(
            @Parameter(description = "User Role", required = true, example = "AGENT")
            @PathVariable RoleEnum roleEnum) {
        List<UserResponseAdminDTO> users = userService.getUsersByRole(roleEnum);
        return ResponseEntity.ok(users);
    }

}
