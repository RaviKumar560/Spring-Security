package in.sp.main.Configuration;

import java.beans.BeanProperty;
import java.net.Authenticator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import in.sp.main.Filter.JwtFilter;
import in.sp.main.Service.CustomerService;
import lombok.SneakyThrows;

@Configuration
@EnableWebSecurity
public class CustomerConfig {
	@Autowired
	private CustomerService service;
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {//use to encrypt the password
		
		return new BCryptPasswordEncoder();
		
	}
	
	@Bean
	//used to load the customer details from db  and provide it to the Authentication manager.
	
	public DaoAuthenticationProvider authprovider() {
		DaoAuthenticationProvider AuthProvider=new DaoAuthenticationProvider();
		AuthProvider.setPasswordEncoder(passwordEncoder());
		AuthProvider.setUserDetailsService(service);
		return AuthProvider;
		
	}
	@Bean
	@SneakyThrows
	//perform authentication
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	    return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	@SneakyThrows
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {
        http.
            authorizeHttpRequests()
            .requestMatchers("register", "login").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.csrf().disable().build();
    }

}
