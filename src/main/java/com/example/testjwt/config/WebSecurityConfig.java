package com.example.testjwt.config;

import com.example.testjwt.security.jwt.JwtEntryPoint;
import com.example.testjwt.security.jwt.JwtTokenFilter;
import com.example.testjwt.security.userprinciple.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private JwtEntryPoint jwtEntryPoint;

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.csrf(AbstractHttpConfigurer::disable)
//                .cors(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(request -> {
//                    request.anyRequest().permitAll();
////                    request.requestMatchers("/users")
////                            .hasAnyAuthority("USER", "ADMIN");
//                })
////                .formLogin(Customizer.withDefaults()).build();
//                .build();
//
//    }
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
            .csrf(AbstractHttpConfigurer::disable)
            .cors(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(request -> {
                request.requestMatchers("/api/auth/**").permitAll(); // Allow access to authentication endpoints
                request.anyRequest().authenticated(); // Require authentication for all other requests
            })
            .build();
}

}
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http.cors().and().csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/api/auth/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(jwtEntryPoint)
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//    }