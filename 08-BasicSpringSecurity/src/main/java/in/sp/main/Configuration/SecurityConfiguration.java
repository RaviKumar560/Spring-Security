package in.sp.main.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	//for configure to allow multiple user and password to access application.
	@Bean
	public InMemoryUserDetailsManager inMemoryUser() {
		UserDetails u1=User.withDefaultPasswordEncoder()
				.username("yash")
				.password("yash123")
				.build();
		UserDetails u2=User.withDefaultPasswordEncoder()
				.username("jayansh")
				.password("jayansh123")
				.build();
		return new InMemoryUserDetailsManager(u1,u2);
	}

	   @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	       /*
	            http.authorizeHttpRequests((req) -> {
	               req.requestMatchers("/cont").permitAll()  // Public endpoint
	                .anyRequest()
	                .authenticated(); // All other requests need authentication
	            });
	            */
		   http.authorizeHttpRequests((req) -> 
               req.requestMatchers("/cont").permitAll()  // Public endpoint
                .anyRequest()
                .authenticated()// All other requests need authentication
            ).httpBasic(Customizer.withDefaults())
		   .formLogin(Customizer.withDefaults());
	            
	        return http.build();
	        
	    }
}
