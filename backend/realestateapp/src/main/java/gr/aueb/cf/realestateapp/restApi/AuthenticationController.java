package gr.aueb.cf.realestateapp.restApi;

import gr.aueb.cf.realestateapp.authentication.AuthenticationService;
import gr.aueb.cf.realestateapp.core.exceptions.AppObjectNotAuthorizedException;
import gr.aueb.cf.realestateapp.dto.AuthenticationRequestDTO;
import gr.aueb.cf.realestateapp.dto.AuthenticationResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "APIs for user authentication and JWT token management")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Operation(
            summary = "Authenticate user",
            description = "Authenticates a user with email and password, returns JWT tokwn if successful authentication"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Authentication successful",
                    content = @Content(schema = @Schema(implementation = AuthenticationResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Invalid credentials or user not authorized",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content
            )
    })
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Authentication credentials",
                    required = true,
                    content = @Content(schema = @Schema(implementation = AuthenticationRequestDTO.class))
            )
            @Valid @RequestBody AuthenticationRequestDTO authenticationRequest) throws AppObjectNotAuthorizedException {
        AuthenticationResponseDTO response = authenticationService.authenticate(authenticationRequest);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Validate Token",
            description = "Validates if te current JWT token is still valid. This endpoint requires authentication."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Token is valid"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Token is invalid or expired",
                    content = @Content
            )
    })
    @PostMapping("/validate")
    public ResponseEntity<Void> validateToken() {
        return ResponseEntity.ok().build();
    }

}
