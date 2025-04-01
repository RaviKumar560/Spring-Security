package in.sp.main.Configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class UserConfiguration {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails Admin=User.builder()
				.username("Ravi")
				.password(passwordEncoder().encode("Ravi123@"))
				.roles("ADMIN").build();
		
		UserDetails Student=User.builder()
				.username("Yash")
				.password(passwordEncoder().encode("Yash123@"))
				.roles("USER").build();
		return new InMemoryUserDetailsManager(Admin,Student);
			
		
		
	}
	@Bean
	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(req->req
            .requestMatchers(HttpMethod.GET).hasRole("ADMIN")
            .requestMatchers(HttpMethod.POST).hasRole("USER")
            .anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults());
     
        return http.build();
    }
}
