package gr.aueb.cf.realestateapp.config;

import gr.aueb.cf.realestateapp.core.enums.ContactHours;
import gr.aueb.cf.realestateapp.core.enums.RoleEnum;
import gr.aueb.cf.realestateapp.model.UserEntity;
import gr.aueb.cf.realestateapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminInitializer {
    @Bean
    public PasswordEncoder thisPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    CommandLineRunner adminInitDB(UserRepository userRepository){
        return args -> {
            String adminEmail = "admin@realestate.com";

            if(userRepository.findByEmail(adminEmail).isEmpty()) {
                UserEntity admin = new UserEntity();
                admin.setName("Admin");
                admin.setSurname("Admin");
                admin.setEmail(adminEmail);
                admin.setPhone("2104578126");
                admin.setContactHours(ContactHours.ALL_DAY);
                admin.setRole(RoleEnum.ADMIN);
                admin.setPassword(thisPasswordEncoder().encode("admin1234"));

                userRepository.save(admin);
                System.out.println("Default admin created with email: " + adminEmail);
            }
        };
    }
}
