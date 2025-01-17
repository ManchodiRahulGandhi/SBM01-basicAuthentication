package com.nt.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http.csrf().disable()
    		.authorizeHttpRequests((authorize)->{
    			authorize.requestMatchers("user/**").permitAll();
    			authorize.anyRequest().authenticated();
    		}).httpBasic(Customizer.withDefaults());
    	return http.build();
    }
    
    
    @Bean
   public UserDetailsService userDetailsService() {
	   		UserDetails rahul=User.builder()
	   							.username("rahul")
	   							.password(passwordEncoder().encode("rahul123"))
	   							.roles("STUDENT")
	   							.build();
	   		UserDetails rajeev=User.builder()
	   							.username("rajeev")
	   							.password(passwordEncoder().encode("rajeev123"))
	   							.roles("MANAGER")
	   							.build();
	   return new InMemoryUserDetailsManager(rahul, rajeev);
   }
    
}
