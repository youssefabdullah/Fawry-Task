package com.example.Fawry_Back_End.config;

import com.example.Fawry_Back_End.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class Security  {
    private final JwtAthFilter jwtAthFilter;

    private final UserService userDao;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        Customizer<HttpBasicConfigurer<HttpSecurity>> httpBasicCustomizer = null;
        httpSecurity
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))  // Enable CORS with custom configuration
                .csrf(csrf -> csrf.disable())  // Disable CSRF protection for stateless JWT
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/login", "/signup", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()  // Allow public access to login and signup
                        .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")  // Protect admin endpoints
                        .requestMatchers("/user/**").hasAnyAuthority("USER", "ADMIN")  // Protect user endpoints for USER or ADMIN roles
                        .anyRequest().authenticated()  // All other requests require authentication
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // Use stateless session
                .authenticationProvider(authenticationProvider())  // Configure custom authentication provider
                .addFilterBefore(jwtAthFilter, UsernamePasswordAuthenticationFilter.class);  // Add JWT filter before UsernamePasswordAuthenticationFilter

        return httpSecurity.build();

    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("http://localhost:4200");  // Allow Angular frontend origin
        corsConfig.addAllowedHeader("Authorization");  // Allow Authorization header
        corsConfig.addAllowedHeader("Content-Type");  // Allow Content-Type header
        corsConfig.addAllowedMethod("GET");  // Allow GET method
        corsConfig.addAllowedMethod("POST");  // Allow POST method
        corsConfig.addAllowedMethod("PUT");  // Allow PUT method
        corsConfig.addAllowedMethod("DELETE");  // Allow DELETE method
        corsConfig.addAllowedMethod("OPTIONS");  // Allow OPTIONS method for preflight requests
        corsConfig.setAllowCredentials(true);  // Allow credentials (Authorization header)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);  // Apply CORS configuration to all endpoints
        return source;
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
//        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                return userDao.findUserByUserName(email);
            }
        };
    }
}
