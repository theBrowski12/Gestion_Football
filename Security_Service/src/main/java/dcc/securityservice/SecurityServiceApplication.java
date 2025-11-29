package dcc.securityservice;

import dcc.securityservice.configuration.RsaKeys;
import dcc.securityservice.entities.AppUser;
import dcc.securityservice.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeys.class)
public class SecurityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityServiceApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
    @Bean
    CommandLineRunner init(UserRepository userRepo, PasswordEncoder encoder) {
        return args -> {
            if (userRepo.findByUsername("admin") == null) {
                userRepo.save(AppUser.builder()
                        .username("admin")
                        .password(encoder.encode("1234"))
                        .roles(List.of("ADMIN"))
                        .build());
            }
            if (userRepo.findByUsername("user") == null) {
                userRepo.save(AppUser.builder()
                        .username("user")
                        .password(encoder.encode("1234"))
                        .roles(List.of("USER"))
                        .build());
            }
        };
    }

}
