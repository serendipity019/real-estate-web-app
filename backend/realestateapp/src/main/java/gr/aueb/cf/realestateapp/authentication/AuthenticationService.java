package gr.aueb.cf.realestateapp.authentication;

import gr.aueb.cf.realestateapp.core.exceptions.AppObjectNotAuthorizedException;
import gr.aueb.cf.realestateapp.core.exceptions.AppObjectNotFoundException;
import gr.aueb.cf.realestateapp.dto.AuthenticationRequestDTO;
import gr.aueb.cf.realestateapp.dto.AuthenticationResponseDTO;
import gr.aueb.cf.realestateapp.model.UserEntity;
import gr.aueb.cf.realestateapp.repository.UserRepository;
import gr.aueb.cf.realestateapp.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO dto)
            throws AppObjectNotAuthorizedException {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.email(), dto.password())
            );

            UserEntity user = userRepository.findByEmail(authentication.getName())
                    .orElseThrow(() -> new AppObjectNotAuthorizedException("User", "User not authorized"));

            if (!user.isActive()) {
                throw new AppObjectNotAuthorizedException("User", "User account is deactivated");
            }

            String token = jwtService.generateToken(authentication.getName(), user.getRole().name());
            return new AuthenticationResponseDTO(user.getName(), user.getSurname(), token); // The getSurname actually take the email
        }
        catch (BadCredentialsException e) {
            throw new AppObjectNotAuthorizedException("User", "Invalid email or password");
        }
    }

}
