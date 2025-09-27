package com.example.document_review.configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

//        return httpSecurity
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/user/register").permitAll()
//                        .requestMatchers("/user/login").permitAll()
//                        .requestMatchers("/comment").permitAll()
//                        .requestMatchers("/part/basic/save").permitAll()
//                        .requestMatchers("/part/fanBlades/save").permitAll()
//                        .requestMatchers("/part/fanBlades/all").permitAll()
//                        .requestMatchers("/part/all").permitAll()
//                        .requestMatchers("/comment/all").permitAll()
//                        .requestMatchers("/part/{id}").permitAll()
//                        .requestMatchers("/document/upload").permitAll()
//                        .requestMatchers("/document/getAll").permitAll()
//                        .requestMatchers("/document/get/part/{id}").permitAll()
//                        .requestMatchers("/document/{filename}").permitAll()
//                        .requestMatchers("/document/**").permitAll()
//                        .requestMatchers("/comment/getAllByDocumentIdAndUserId/**").permitAll()
//                        .requestMatchers("/comment/approve/{commentId}").permitAll()
//                        .requestMatchers("/comment/rateComment/{commentId}/{newRate}").permitAll()
//                        .requestMatchers("/user/home").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .headers(headers -> headers
//                        .frameOptions(frameOptions -> frameOptions.disable())
//                )
//                .httpBasic(Customizer.withDefaults())
//                .build();
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable) // novo, zamena za csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/register").permitAll()
                        .requestMatchers("/user/login").permitAll()
                        .requestMatchers("/comment").permitAll()
                        .requestMatchers("/part/basic/save").permitAll()
                        .requestMatchers("/part/fanBlades/save").permitAll()
                        .requestMatchers("/part/fanBlades/all").permitAll()
                        .requestMatchers("/part/all").permitAll()
                        .requestMatchers("/comment/all").permitAll()
                        .requestMatchers("/part/{id}").permitAll()
                        .requestMatchers("/document/upload").permitAll()
                        .requestMatchers("/document/getAll").permitAll()
                        .requestMatchers("/document/get/part/{id}").permitAll()
                        .requestMatchers("/document/{filename}").permitAll()
                        .requestMatchers("/document/**").permitAll()
                        .requestMatchers("/comment/getAllByDocumentIdAndUserId/**").permitAll()
                        .requestMatchers("/comment/approve/{commentId}").permitAll()
                        .requestMatchers("/comment/rateComment/{commentId}/{newRate}").permitAll()
                        .requestMatchers("/user/home").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public AuthenticationProvider authenticationProvider() throws Exception {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailsService);
//        provider.setPasswordEncoder(bCryptPasswordEncoder());
//        return provider;
//    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());

        return new ProviderManager(authProvider);
    }

}

