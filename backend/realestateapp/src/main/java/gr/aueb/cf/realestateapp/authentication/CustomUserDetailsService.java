package gr.aueb.cf.realestateapp.authentication;

import gr.aueb.cf.realestateapp.model.UserEntity;
import gr.aueb.cf.realestateapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    /**
     * In this app the users make login with their emails
     * @param email the email identifying the user whose data is required.
     * @return userDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
          UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(
                "User with email: " + email + " not found"));

          if (!user.isActive()) {
              throw new UsernameNotFoundException("User account is deactivated");
          }

        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name())))
                .build();
    }
}
